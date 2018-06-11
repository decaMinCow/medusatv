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
import com.medusatv.spider.vo.longzhu.LongzhuChannel;
import com.medusatv.util.NumUtil;

/**
 * @ClassName: LiveSpider
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月21日
 *
 */
@Service
public class LongzhuSpider {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final static String PLATFORM = "longzhu";
	private final static String PLATFORM_CN = "龙珠";
	private final static int PAGE_SIZE = 50;
	private final static String UA_CHROME = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
	private final static String DOMAIN = "star.longzhu.com/";
	private final static String PREFIX = "http://api.plu.cn/tga/streams?max-results=" + PAGE_SIZE + "&start-index=";
	private final static String SUFFIX = "&sort-by=views&filter=0&game=0";
	private static int PAGE_SUM = 60;
	private int IS_BREAK = 50;// 判断连续多少个0人关注之后停掉
	private int BREAK_COUNT = 0;// 判断停止循环的计数器

	public LongzhuSpider() {
		init();
	}

	// private LongzhuSpider() {
	// }
	//
	// private static LongzhuSpider single = null;
	//
	// public static LongzhuSpider getInstance() {
	// if (single == null) {
	// single = new LongzhuSpider();
	// }
	// return single;
	// }

	public ArrayList<Data> getPageContents(int page) {
		page = (page - 1) * 50 + 1;
		String url = PREFIX + page + SUFFIX;
		ArrayList<Data> dataList = new ArrayList<Data>();
		String doc;
		try {
			doc = Jsoup.connect(url).ignoreContentType(true).header("User-Agent", UA_CHROME).timeout(5000).get()
					.select(" body").text();
			JSONObject object = null;
			try {
				object = JSON.parseObject(doc);
				
			} catch (Exception e) {
//				e.printStackTrace();
				return null;
			}
			Map<?, ?> map = (Map<?, ?>) object.get("data");
			Object jsonArray = map.get("items");
			List<LongzhuChannel> list = JSON.parseArray(jsonArray + "", LongzhuChannel.class);
			if (list.size() == 0)
				return null; // 如果没数据则返回
			for (LongzhuChannel item : list) {
				String roomId = item.getChannel().getDomain();
				String id = PLATFORM + roomId;
//				String link = DOMAIN + roomId;
				String link = "http://" + DOMAIN + roomId;
				String roomName = item.getChannel().getStatus();
				String anchorName = item.getChannel().getName();
				String category = item.getGame().get(0).getName();
				String roomPic = item.getPreview();
				int watcherSum = NumUtil.chineseConvertNum(item.getViewers());
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
//			e.printStackTrace();
		}
		return dataList;
	}

	public void init() {
		String url = PREFIX + 1 + SUFFIX;
		String doc = null;
		try {
			doc = Jsoup.connect(url).ignoreContentType(true).header("User-Agent", UA_CHROME).timeout(5000).get()
					.select(" body").text();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject object = JSON.parseObject(doc);
		Map<?, ?> map = (Map<?, ?>) object.get("data");
		PAGE_SUM = Integer.parseInt(map.get("totalItems").toString()) / 50 + 1;
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
//		System.out.println("longzhuTotal:" + dataList.size());
		// System.out.println("longzhu:" + dataList);
		// System.out.println("total:" + dataList.size() + ", " + dataList);
		return dataList;
	}

//	public static void main(String[] args) throws IOException {
//		new LongzhuSpider().getAllContents();
//		// liveSpider.getPageContents(36, 20);
//	}

}
