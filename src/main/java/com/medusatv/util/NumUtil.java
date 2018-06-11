/**
* @Title: NumUtil.java
* @Package com.decamincow.spider.util
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月21日
* @version V1.0
*/
package com.medusatv.util;

/**
* @ClassName: NumUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author decamincow
* @date 2016年9月21日
*
*/
public class NumUtil {
	
	public static int chineseConvertNum(String num){
		num = num.replace(",", "");
		int result;
		if("万".equals(num.substring(num.length()-1))){
		   result =	(int)(Float.parseFloat(num.substring(0, num.length()-1)) * 10000);
		}else{
			result = Integer.parseInt(num);
		}
		return result;
	}

}
