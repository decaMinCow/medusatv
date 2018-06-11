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
import com.medusatv.spider.vo.panda.PanadaChannel;

/**
 * @ClassName: LiveSpider
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月21日
 *
 */
@Service
public class PandaSpider {

	private final static String PLATFORM = "panda";
	private final static String PLATFORM_CN = "熊猫";
	private final static String UA_CHROME = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
	private final static String DOMAIN = "www.panda.tv/";
	private final static String PREFIX = "http://www.panda.tv/live_lists?status=2&order=person_num&pageno=";
	private final static String SUFFIX = "&pagenum=120";
	private final static int PAGE_SIZE = 120;
	private static int PAGE_SUM = 60;
	private int IS_BREAK = 50;// 判断连续多少个0人关注之后停掉
	private int BREAK_COUNT = 0;// 判断停止循环的计数器
	
	public PandaSpider() {
		init();
	}

//	private static PandaSpider single = null;
//
//	public static PandaSpider getInstance() {
//		if (single == null) {
//			single = new PandaSpider();
//		}
//		return single;
//	}

	public ArrayList<Data> getPageContents(int page) {
		String url = PREFIX + page + SUFFIX + PAGE_SUM;
		ArrayList<Data> dataList = new ArrayList<Data>();
		String doc;
		try {
			doc = Jsoup.connect(url).header("User-Agent", UA_CHROME).get().select(" body").text();

			JSONObject object = JSON.parseObject(doc);
			Object jsonArray = ((Map<?, ?>) object.get("data")).get("items");
			List<PanadaChannel> list = JSON.parseArray(jsonArray + "", PanadaChannel.class);
			if (list.size() == 0)
				return null; // 如果没数据则返回
			for (PanadaChannel item : list) {
				String roomId = item.getId();
				String id = PLATFORM + roomId;
//				String link = DOMAIN + roomId;
				String link = "http://" + DOMAIN + roomId;
				String roomName = item.getName();
				String anchorName = item.getUserinfo().getNickName();
				String category = item.getClassification().getCname();
				String roomPic = item.getPictures().getImg();
				int watcherSum = item.getPerson_num();
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
//		System.out.println("pandaTotal:" + dataList.size());
//		System.out.println("panda:" + dataList);
//		System.out.println("total:" + dataList.size() + ", " + dataList);
		return dataList;
	}

	public void init() {
		String url = PREFIX + 1 + SUFFIX + PAGE_SIZE;
		String doc = "";
		try {
			doc = Jsoup.connect(url).header("User-Agent", UA_CHROME).get().select(" body").text();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject object = JSON.parseObject(doc);
		PAGE_SUM = (int) (object.getIntValue("total") / PAGE_SIZE) + 1;
	}

//	public static void main(String[] args) throws IOException {
//		new PandaSpider().getAllContents();
//	}

}
