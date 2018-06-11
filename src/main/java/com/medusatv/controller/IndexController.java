/**
* @Title: IndexController.java
* @Package com.medusatv.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月25日
* @version V1.0
*/
package com.medusatv.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medusatv.es.service.DataService;
import com.medusatv.po.Data;

/**
* @ClassName: IndexController
* @Description: 主页相关
* @author decamincow
* @date 2016年9月25日
*
*/

@Controller
public class IndexController {
	
	@Autowired
	private DataService dataService;
	
	private static final int PAGE_SIZE = 120;
	
	private static final int DEFAULT_PAGE = 0;
	
//	@RequestMapping(value = { "/", "/index" })
//	public String index() {
//		return "index";
//	}
	@GetMapping(value = { "/", "/index" })
	public String index(Model model) {
		model.addAttribute("dataList", dataService.search(0, PAGE_SIZE));
		model.addAttribute("tail1", "<div class=\"clearfix visible-xs-block\"></div>");
		model.addAttribute("tail2", "<div id=\"mark\" class=\"clearfix visible-lg-block visible-md-block visible-sm-block visible-xs-block\"></div>");
		model.addAttribute("page", DEFAULT_PAGE);
		return "index";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam(name = "keyWord", defaultValue = "") String keyWord, Model model) {
//		System.out.println(1111111);
		List<Data> dataList;
		if("".equals(keyWord)){
			dataList = dataService.search(0, PAGE_SIZE);
		} else{
			dataList = dataService.search(keyWord, DEFAULT_PAGE, PAGE_SIZE);
			if(dataList.size() == 0 || null == dataList){
				dataList = dataService.search(0, PAGE_SIZE);
			}
		}
		model.addAttribute("dataList", dataList);
		model.addAttribute("tail1", "<div class=\"clearfix visible-xs-block\"></div>");
		model.addAttribute("tail2", "<div id=\"mark\" class=\"clearfix visible-lg-block visible-md-block visible-sm-block visible-xs-block\"></div>");
		model.addAttribute("page", DEFAULT_PAGE);
		return "index";
	}
	
	@RequestMapping("/vidodetail")
	public String vidodetail(@RequestParam(name = "anchorname") String anchorname, 
						 @RequestParam(name = "roomname") String roomname, 
						 @RequestParam(name = "category") String category, 
						 @RequestParam(name = "watcher") String watcher, 
						 @RequestParam(name = "link") String link,
						 Model model) {
		model.addAttribute("anchorname", anchorname);
		model.addAttribute("roomname", roomname);
		model.addAttribute("category", category);
		model.addAttribute("watcher", watcher);
		model.addAttribute("link", link);
		
		try {
			anchorname = URLEncoder.encode(anchorname, "UTF-8");
			roomname = URLEncoder.encode(roomname, "UTF-8");
			category = URLEncoder.encode(category, "UTF-8");
			watcher = URLEncoder.encode(watcher, "UTF-8");
			link = URLEncoder.encode(link, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			// TODO
		}
		
		return "redirect:/detail?anchorname="+anchorname+"&roomname="+roomname+"&category="+category+"&watcher="+watcher+"&link="+link;
//		return "detail";
	}
	
	
	@RequestMapping("/detail")
	public String detail(@RequestParam(name = "anchorname") String anchorname, 
						 @RequestParam(name = "roomname") String roomname, 
						 @RequestParam(name = "category") String category, 
						 @RequestParam(name = "watcher") String watcher, 
						 @RequestParam(name = "link") String link,
						 Model model) {
		model.addAttribute("anchorname", anchorname);
		model.addAttribute("roomname", roomname);
		model.addAttribute("category", category);
		model.addAttribute("watcher", watcher);
		model.addAttribute("link", link);
		
		return "detail";
	}	
//	@RequestMapping("/detail")
//	public String detail(@RequestParam(name = "anchorname") String anchorname, 
//			@RequestParam(name = "roomname") String roomname, 
//			@RequestParam(name = "category") String category, 
//			@RequestParam(name = "watcher") String watcher, 
//			@RequestParam(name = "link") String link, 
//			@RequestParam(name = "roompic") String roompic, 
//			@RequestParam(name = "platformname") String platformname, 
//			Model model) {
//		model.addAttribute("anchorname", anchorname);
//		model.addAttribute("roomname", roomname);
//		model.addAttribute("category", category);
//		model.addAttribute("watcher", watcher);
//		model.addAttribute("link", link);
//		model.addAttribute("roompic", roompic);
//		model.addAttribute("platformname", platformname);
////		return "redirect:/detail";
//		return "detail";
//	}
	
//	@RequestMapping("/loadmore")
//    @ResponseBody
//    public String addsystem(@RequestParam(name = "page", defaultValue = "1") int page, HashMap<String, Object> map){
//		map.put("page", page);
//		map.put("dataList", dataService.search(page, 100));
//		String json = JSON.toJSONString(map);
//		System.out.println(json);
//    	return json;
//    }
	
//	@RequestMapping("/loadmore")
//    public List<Data> loadmore(@RequestParam(name = "page", defaultValue = "1") int page){
//    	map.put("page", page);
//    	map.put("dataList", dataService.search(page, 40));
//    	model.addAttribute("dataList", dataService.search(page, 40));
//    	model.addAttribute("page", page);
//    	System.out.println(page + "," + dataService.search(page, 40).size());
//    	return dataService.search(page, 40);
//    }
	
//	@RequestMapping("/detail/{anchorname}/{roomname}/{category}/{watcher}/{link}")
//	public String detail( @PathVariable("anchorname") String anchorname,
//						  @PathVariable("roomname") String roomname,
//						  @PathVariable("category") String category,
//						  @PathVariable("watcher") int watcher,
//						  @PathVariable("link") String link,
//						  Model model) {
//
//		model.addAttribute("anchorname", anchorname);
//		link = link.replace("*", ".");
//		link = link.replace("$", "/");
//		model.addAttribute("roomname", roomname);
//		model.addAttribute("category", category);
//		model.addAttribute("watcher", watcher);
//		model.addAttribute("link", link);
//		return "detail";
//	}
	
}
