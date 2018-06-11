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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
public class HuajiaoSpider {

	private final static String PLATFORM = "huajiao";
	private final static String PLATFORM_CN = "花椒";
	private final static String UA_CHROME = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
	private final static String DOMAIN = "www.huajiao.com";
	// 明星
	private final static String PREFIX1 = "http://www.huajiao.com/category/1?pageno=";
	// 女生
	private final static String PREFIX2 = "http://www.huajiao.com/category/2?pageno=";
	// 男生
	private final static String PREFIX3 = "http://www.huajiao.com/category/5?pageno=";
	
//	private HuajiaoSpider() {
//	}
//
//	private static HuajiaoSpider single = null;
//
//	public static HuajiaoSpider getInstance() {
//		if (single == null) {
//			single = new HuajiaoSpider();
//		}
//		return single;
//	}

	public ArrayList<String> getAllStarArticleLinks() {
		ArrayList<String> articleLinks = new ArrayList<String>();
		String url = PREFIX1 + 1;
		Document document = null;
		try {
			document = Jsoup.connect(url).header("User-Agent", UA_CHROME).timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements elements = document.select(".live");
		for (int i = 0; i < elements.size(); i++) {
			String path = elements.get(i).select(".link").attr("href");
			if("".equals(path)) continue;
//			String link = DOMAIN + path;
			String link = UrlUtil.toUriWithHttp(DOMAIN, path);
			articleLinks.add(link);
		}
		return articleLinks;
	}
	
	// 获得女生特定页的links
	public ArrayList<String> getGirlArticleLinks(int page) {
		ArrayList<String> articleLinks = new ArrayList<String>();
		String url = PREFIX2 + page;
		Document document = null;
		try {
			document = Jsoup.connect(url).header("User-Agent", UA_CHROME).timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements elements = document.select("a.link");
		
		for (int i = 0; i < elements.size(); i++) {
			String path = elements.get(i).attr("href");
			if("".equals(path)) continue;
			String link = UrlUtil.toUriWithHttp(DOMAIN, path);
			articleLinks.add(link);
		}
		return articleLinks;
	}
	
	// 获得女生所有页的links
	public ArrayList<String> getAllGirlArticleLinks() {
		ArrayList<String> articleLinks = new ArrayList<String>();
		String url = PREFIX2 + 1;
		Document document = null;
		try {
			document = Jsoup.connect(url).header("User-Agent", UA_CHROME).timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int pageSum = Integer.parseInt(document.select("li.last").attr("tabindex"));
		
		for (int i = 0; i < pageSum; i++) {
			articleLinks.addAll(getGirlArticleLinks(i+1));
		}
		return articleLinks;
	}
	
	// 获得男生特定页的links
	public ArrayList<String> getBoyArticleLinks(int page) {
		ArrayList<String> articleLinks = new ArrayList<String>();
		String url = PREFIX3 + page;
		Document document = null;
		try {
			document = Jsoup.connect(url).header("User-Agent", UA_CHROME).timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements elements = document.select("a.link");
		
		for (int i = 0; i < elements.size(); i++) {
			String path = elements.get(i).attr("href");
			if("".equals(path)) continue;
			String link = UrlUtil.toUriWithHttp(DOMAIN, path);
			articleLinks.add(link);
		}
		return articleLinks;
	}
	
	// 获得男生所有页的links
	public ArrayList<String> getAllBoyArticleLinks() {
		ArrayList<String> articleLinks = new ArrayList<String>();
		String url = PREFIX3 + 1;
		Document document = null;
		try {
			document = Jsoup.connect(url).header("User-Agent", UA_CHROME).timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String temp = document.select("li.last").attr("tabindex");
		int pageSum = Integer.parseInt(temp==""?"0":temp);
		
		for (int i = 0; i < pageSum; i++) {
			articleLinks.addAll(getGirlArticleLinks(i+1));
		}
		return articleLinks;
	}
	
	public ArrayList<String> getAllAricleLinks(){
		ArrayList<String> allArticleLinks = new ArrayList<String>();
		allArticleLinks.addAll(getAllGirlArticleLinks());
		allArticleLinks.addAll(getAllBoyArticleLinks());
		allArticleLinks.addAll(getAllStarArticleLinks());
		return allArticleLinks;
	}
	
	// 爬一个主播信息
	public List<Data> getOneContent(String url){
		ArrayList<Data> dataList = new ArrayList<Data>();
		Document document = null;
		try {
			document = Jsoup.connect(url).header("User-Agent", UA_CHROME).timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String roomId = url.substring(url.lastIndexOf("/") + 1);
		String id = PLATFORM + roomId;
		String link = "http://" + DOMAIN + "/l/" + roomId;
		String anchorName = document.select(".base-info h3").text();
		String roomName = anchorName + "正在直播";
		String category = "秀场";
		String roomPic = document.select("div.avatar img").attr("src");
		int watcherSum = NumUtil.chineseConvertNum(document.select("div.watches strong").text());
		dataList.add(new Data(id, PLATFORM_CN, roomId, link, roomName, anchorName, roomPic, category, watcherSum, new Date()));
		return dataList;
	}
	
	public List<Data> getAllContents() {
		ArrayList<Data> dataList = new ArrayList<Data>();
		ArrayList<String> linkList = new ArrayList<String>();
		linkList = getAllAricleLinks();
		for (String url : linkList) {
			dataList.addAll((getOneContent(url)));
		}
//		System.out.println("huajiaoTotal:" + dataList.size());
//		System.out.println("total:" + dataList.size() + ", " + dataList);
		return dataList;

	}

//	public static void main(String[] args) throws IOException {
//		new HuajiaoSpider().getAllContents();
//	}
}
