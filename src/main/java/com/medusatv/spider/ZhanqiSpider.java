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
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.medusatv.po.Data;
import com.medusatv.spider.vo.zhanqi.ZhanqiChannel;

/**
 * @ClassName: LiveSpider
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月21日
 *
 */
@Service
public class ZhanqiSpider {

	private final static String PLATFORM = "zhanqi";
	private final static String PLATFORM_CN = "战旗";
	private final static String UA_CHROME = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
	private final static String DOMAIN = "www.zhanqi.tv/";
	private final static String PREFIX = "https://www.zhanqi.tv/api/static/live.hots/30-";
	private final static String SUFFIX = ".json";
	private final static int PAGE_SIZE = 30;
	private static int PAGE_SUM = 60;
	private int IS_BREAK = 50;// 判断连续多少个0人关注之后停掉
	private int BREAK_COUNT = 0;// 判断停止循环的计数器
	
	public ZhanqiSpider() {
		init();
	}

//	private static ZhanqiSpider single = null;
//
//	public static ZhanqiSpider getInstance() {
//		if (single == null) {
//			single = new ZhanqiSpider();
//		}
//		return single;
//	}

	public ArrayList<Data> getPageContents(int page) {
		String url = PREFIX + page + SUFFIX;
		ArrayList<Data> dataList = new ArrayList<Data>();
		String doc;
		try {
			doc = Jsoup.connect(url)
					.header("User-Agent", UA_CHROME).get().select(" body").text();

			JSONObject object = null;
			try {
				 object = JSON.parseObject(doc);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(object == null) return null;
			
			Map<?, ?> map = (Map<?, ?>) object.get("data");
			Object jsonArray = map.get("rooms");
			List<ZhanqiChannel> list = JSON.parseArray(jsonArray + "", ZhanqiChannel.class);
			if (list.size() == 0)
				return null; // 如果没数据则返回
			for (ZhanqiChannel item : list) {
				String roomId = item.getCode();
				String id = PLATFORM + roomId;
//				String link = DOMAIN + roomId;
				String link = "https://" + DOMAIN + roomId;
				String roomName = item.getTitle();
				String anchorName = item.getNickname();
				String category = item.getGameName();
				String roomPic = item.getBpic();
				int watcherSum = item.getOnline();
				dataList.add(new Data(id, PLATFORM_CN, roomId, link, roomName, anchorName, roomPic, category, watcherSum, new Date()));
				if (watcherSum == 0)
					BREAK_COUNT++;
				if (BREAK_COUNT > IS_BREAK) {
					BREAK_COUNT = 0;
					return dataList;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public List<Data> getAllContents() {
		ArrayList<Data> dataList = new ArrayList<Data>();
		for (int i = 0; i < PAGE_SUM; i++) {
			ArrayList<Data> list = new ArrayList<Data>();
			list = getPageContents(i + 1);
			if (null == list || list.size() == 0)
				break;
			dataList.addAll(list);
		}
		
//		System.out.println("zhanqiTotal:" + dataList.size());
//		 System.out.println("zhanqi:" + dataList.size() + ", " + dataList);
//		 System.out.println("total:" + dataList.size() + ", " + dataList);
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
		JSONObject object = JSON.parseObject(doc);
		Map<?, ?> map = (Map<?, ?>) object.get("data");
		PAGE_SUM = (Integer.parseInt(map.get("cnt") + "") / PAGE_SIZE) + 1;

	}

//	public static void main(String[] args) throws IOException {
//		 new ZhanqiSpider().getAllContents();
//	}

}
