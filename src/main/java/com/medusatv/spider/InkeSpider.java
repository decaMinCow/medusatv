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
import com.medusatv.spider.vo.inke.InkeChannel;

/**
 * @ClassName: LiveSpider
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月21日
 *
 */
@Service
public class InkeSpider {

	private final static String PLATFORM = "inke";
	private final static String PLATFORM_CN = "映客";
	private final static String UA_CHROME = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
//	private final static String PREFIX = "www.inke.cn/live.html?uid=";
	private final static String PREFIX = "http://www.inke.cn/live.html?uid=";
	private final static String SUFFIX = "&id=";
	private final static String URL = "http://webapi.busi.inke.cn/web/live_hotlist_pc";
	private int IS_BREAK = 50;// 判断连续多少个0人关注之后停掉
	private int BREAK_COUNT = 0;// 判断停止循环的计数器
	
//	private static InkeSpider single = null;
//
//	public static InkeSpider getInstance() {
//		if (single == null) {
//			single = new InkeSpider();
//		}
//		return single;
//	}

	public ArrayList<Data> getAllContents() {
		String url = URL;
		ArrayList<Data> dataList = new ArrayList<Data>();
		String doc;
		try {
			doc = Jsoup.connect(url)
					.header("User-Agent", UA_CHROME).get().select(" body").text();

			JSONObject object = JSON.parseObject(doc);
			Map<?, ?> map = (Map<?, ?>) object.get("data");
			Object jsonArray = map.get("hotlists");
			List<InkeChannel> list = JSON.parseArray(jsonArray + "", InkeChannel.class);
			if (list.size() == 0)
				return null; // 如果没数据则返回
			for (InkeChannel item : list) {
				String roomId = item.getId();
				String id = PLATFORM + roomId;
				String liveid = item.getLiveid();
				String link = PREFIX + id + SUFFIX + liveid;
				String roomName = item.getTitle();
				String anchorName = item.getNick();
				String category = "星秀";
				String roomPic = item.getPortrait();
				int watcherSum = item.getOnline_users();
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
//		System.out.println("inkeTotal:" + dataList.size());
//		System.out.println("inke:" + dataList);
//		System.out.println("total:" + dataList.size() + ", " + dataList);
		return dataList;
	}

//	public static void main(String[] args) throws IOException {
//		 new InkeSpider().getAllContents();
//	}

}
