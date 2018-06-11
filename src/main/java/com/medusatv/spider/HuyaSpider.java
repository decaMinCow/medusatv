/**
* @Title: LiveSpider.java
* @Package com.decamincow.spider.spider
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月21日
* @version V1.0
*/
package com.medusatv.spider;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.medusatv.po.Data;
import com.medusatv.spider.vo.huya.HuyaChannel;

/**
 * @ClassName: LiveSpider
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月21日
 *
 */
@Service
public class HuyaSpider {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final static String PLATFORM = "huya";
	private final static String PLATFORM_CN = "虎牙";
	private final static String UA_CHROME = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
	private final static String DOMAIN = "www.huya.com/";
	private final static String PREFIX = "http://www.huya.com/cache.php?m=Live&do=ajaxAllLiveByPage&page=";
	private final static String SUFFIX = "&pageNum=1";
	private final static int PAGE_SIZE = 20;
	private static int PAGE_SUM = 60;
	private int IS_BREAK = 50;// 判断连续多少个0人关注之后停掉
	private int BREAK_COUNT = 0;// 判断停止循环的计数器
	
	public HuyaSpider() {
		init();
	}

//	private static HuyaSpider single = null;
//
//	public static HuyaSpider getInstance() {
//		if (single == null) {
//			single = new HuyaSpider();
//		}
//		return single;
//	}

	public ArrayList<Data> getPageContents(int page) {
		String url = PREFIX + page + SUFFIX;
		ArrayList<Data> dataList = new ArrayList<Data>();
		String doc = null;
		try {
			doc = Jsoup.connect(url).ignoreContentType(true).header("User-Agent", UA_CHROME).timeout(5000).get()
					.select(" body").text();
			JSONObject object = null;
			doc = doc.replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2"); //过滤接口错误数据
			try {
				object = JSON.parseObject(doc);
			} catch (Exception e) {
				return null;
			}
			Map<?, ?> map = (Map<?, ?>) object.get("data");
			Object jsonArray = map.get("list");
			PAGE_SUM = (Integer.parseInt((String) map.get("total")) / PAGE_SIZE) + 1;
			List<HuyaChannel> list = JSON.parseArray(jsonArray + "", HuyaChannel.class);
			if (list.size() == 0)
				return null; // 如果没数据则返回
			for (HuyaChannel item : list) {
				String roomId = item.getPrivateHost();
				String id = PLATFORM + roomId;
//				String link = DOMAIN + roomId;
				String link = "http://" + DOMAIN + roomId;
				String roomName = item.getIntroduction();
				String anchorName = item.getNick();
				String category = item.getGameFullName();
				String roomPic = item.getScreenshot();
				int watcherSum = item.getTotalCount();
				dataList.add(
						new Data(id, PLATFORM_CN, roomId, link, roomName, anchorName, roomPic, category, watcherSum, new Date()));
				if (watcherSum == 0)
					BREAK_COUNT++;
				if (BREAK_COUNT > IS_BREAK) {
					BREAK_COUNT = 0;
					return dataList;
				}
			}
		} catch (IOException e) {
//			System.out.println(doc);
			e.printStackTrace();
		}
		return dataList;
	}

	public List<Data> getAllContents() {
		ArrayList<Data> dataList = new ArrayList<Data>();
		for (int i = 0; i < PAGE_SUM; i++) {
			ArrayList<Data> list = new ArrayList<Data>();
			list = getPageContents(i + 1);
			if (null == list)
				continue;
			if (list.size() == 0)
				break;
			dataList.addAll(list);
		}
//		System.out.println("huyaTotal:" + dataList.size());
//		System.out.println("huya:" + dataList);
//		System.out.println("total:" + dataList.size() + ", " + dataList);
		return dataList;
	}

	public void init() {
		String url = PREFIX + 1 + SUFFIX;
		String doc = "";
		try {
			doc = Jsoup.connect(url).ignoreContentType(true).header("User-Agent", UA_CHROME).timeout(5000).get()
					.select(" body").text();
		} catch (IOException e) {
			e.printStackTrace();
		}
		doc = doc.replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2");
		JSONObject object = JSON.parseObject(doc);
		Map<?, ?> map = (Map<?, ?>) object.get("data");
		PAGE_SUM = (Integer.parseInt((String) map.get("total")) / PAGE_SIZE) + 1;
	}
	
//	public static void main(String[] args) throws IOException {
//		new HuyaSpider().getAllContents();
//	}

}
