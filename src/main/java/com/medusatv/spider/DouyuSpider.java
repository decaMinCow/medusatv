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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.medusatv.po.Data;
import com.medusatv.util.NumUtil;
import com.medusatv.util.UrlUtil;


/**
 * @ClassName: LiveSpider
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月21日
 *
 */
@Service
public class DouyuSpider {

	private final static String PLATFORM = "douyu";
	private final static String PLATFORM_CN = "斗鱼";
	private final static String UA_CHROME = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
	private final static String DOMAIN = "www.douyu.com/";
	private final static String PREFIX = "https://www.douyu.com/directory/all?page=";
	private final static String SUFFIX = "&isAjax=1";
	private final static int PAGE_SIZE = 120;
	private final static int PAGE_SUM = 60;
//	private int num;
	private int IS_BREAK = 50;// 判断连续多少个0人关注之后停掉
	private int BREAK_COUNT = 0;// 判断停止循环的计数器
	
//	private DouyuSpider() {
//	}
//
//	private static DouyuSpider single = null;
//
//	public static DouyuSpider getInstance() {
//		if (single == null) {
//			single = new DouyuSpider();
//		}
//		return single;
//	}

	/**
	 * 从列表页中获取一定数量的文章地址的链接
	 * 
	 * @param url
	 *            文章列表页
	 * @param pageSize
	 *            获取的数量
	 * @return 文章的链接列表
	 */
	public ArrayList<Data> getPageContents(int page, int pageSize) {
		String url = PREFIX + page + SUFFIX;
		ArrayList<Data> dataList = new ArrayList<Data>();
		try {
			Document document = Jsoup.connect(url).header("User-Agent", UA_CHROME).timeout(10000).get();
			Elements elements = document.select("li[data-rid] a");
				for (int i = 0; i < elements.size(); i++) {
					Element element = elements.get(i);
					String roomId = element.attr("data-rid");
					String id = PLATFORM + roomId;
//					String link = DOMAIN + roomId;
					String link = UrlUtil.toUriWithHttps(DOMAIN, roomId);
					String roomName = element.attr("title");
					String DataName = element.select(".fl").text();
					String category = element.select(".tag").text();
					String roomPic = element.select(".imgbox img").attr("data-original");
					int watcherSum = NumUtil.chineseConvertNum(element.select(".dy-num").text());
					dataList.add(new Data(id, PLATFORM_CN, roomId, link, roomName, DataName, roomPic,
							category, watcherSum, new Date()));
					if (watcherSum == 0)
						BREAK_COUNT++;
					if (BREAK_COUNT > IS_BREAK) {
						return dataList;
					}
//					System.out.println("num:" + num + 1 + ", roomId:" + roomId + ", link:" + link + ", roomName:"
//							+ roomName + ", DataName:" + DataName + ", category:" + category + ", roomPic:"
//							+ roomPic + ", watcherSum:" + watcherSum);
//					num++;
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public ArrayList<Data> getAllContents() {
		ArrayList<Data> dataList = new ArrayList<Data>();
		for (int i = 0; i < PAGE_SUM; i++) {
			if (BREAK_COUNT > IS_BREAK) {
				BREAK_COUNT = 0;
				break;
			}
			dataList.addAll(getPageContents(i + 1, PAGE_SIZE));
		}
//		 System.out.println("douyuTotal:" + dataList.size());
//		 System.out.println("douyu:" + dataList);
		 return dataList;
	}

//	public static void main(String[] args) {
//		new DouyuSpider().getAllContents();
//	}

}
