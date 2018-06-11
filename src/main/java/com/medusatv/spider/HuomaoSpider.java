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
import com.medusatv.spider.vo.huomao.HuomaoChannel;
import com.medusatv.util.NumUtil;

/**
 * @ClassName: LiveSpider
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月21日
 *
 */
@Service
public class HuomaoSpider {

	private final static String PLATFORM = "huomao";
	private final static String PLATFORM_CN = "火猫";
	private final static String UA_CHROME = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
	private final static String DOMAIN = "www.huomao.com/";
	private final static String url = "http://www.huomao.com/channels/channel.json?page=1&page_size=300&cache_time="+ System.currentTimeMillis()/1000 +"&game_url_rule=all";
	private int IS_BREAK = 20;// 判断连续多少个0人关注之后停掉
	private int BREAK_COUNT = 0;// 判断停止循环的计数器
	
//	private static HuomaoSpider single = null;
//
//	public static HuomaoSpider getInstance() {
//		if (single == null) {
//			single = new HuomaoSpider();
//		}
//		return single;
//	}

	public ArrayList<Data> getAllContents() {
		ArrayList<Data> dataList = new ArrayList<Data>();
		String doc;
		try {
			doc = Jsoup.connect(url)
					.ignoreContentType(true)
					.header("User-Agent", UA_CHROME)
					.timeout(5000).get()
					.select(" body")
					.text();

			JSONObject object = JSON.parseObject(doc);
			Map<?, ?> map = (Map<?, ?>) object.get("data");
			Object jsonArray = map.get("channelList");
			List<HuomaoChannel> list = JSON.parseArray(jsonArray + "", HuomaoChannel.class);
			if (list.size() == 0)
				return null; // 如果没数据则返回
			for (HuomaoChannel item : list) {
				String roomId = item.getRoom_number();
				String id = PLATFORM + roomId;
//				String link = DOMAIN + roomId;
				String link = "http://" + DOMAIN + roomId;
				String roomName = item.getChannel();
				String anchorName = item.getNickname();
				String category = item.getGameCname();
				String roomPic = item.getImage();
				int watcherSum = NumUtil.chineseConvertNum(item.getViews());
				String isLive = item.getIs_live();
				// 直播状态才能插入
				if("1".equals(isLive)){
					dataList.add(new Data(id, PLATFORM_CN, roomId, link, roomName, anchorName, roomPic, category, watcherSum, new Date()));
				} 
				// 如果连续不直播状态或者观看人数为0超过指定限度则跳出循环
				if (watcherSum == 0 || "0".equals(isLive))
					BREAK_COUNT++;
				if (BREAK_COUNT > IS_BREAK) {
					BREAK_COUNT = 0;
//					System.out.println("huomaoTotal:" + dataList.size());
					return dataList;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("huomaoTotal:" + dataList.size());
//		System.out.println("huomao:" + dataList);
		return dataList;
	}


//	public static void main(String[] args) throws IOException {
//		  new HuomaoSpider().getAllContents();
//	}

}
