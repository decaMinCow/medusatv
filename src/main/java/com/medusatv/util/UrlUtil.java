package com.medusatv.util;

public class UrlUtil {
	
	/**
	 * 从URL中得到域名
	 * @param url
	 * @return
	 */
	public static String getDomain(String url) {
		url = url.replace("http://", "");
		url = url.replace("https://", "");
		if(url.indexOf("/")>0){
			url = url.substring(0, url.indexOf("/"));
		}
		return url;
	}
	
	public static String toUriWithHttp(String domain, String path){
		return "http://" + domain + path;	
	}
	
	public static String toUriWithHttp(String domain) {
		return "http://" + domain;
	}
	
	public static String toUriWithHttps(String domain, String path) {
		return "https://" + domain + path;
	}
	
	public static String toUriWithHttps(String domain) {
		return "https://" + domain;
	}
}
