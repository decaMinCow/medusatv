/**
* @Title: Channel.java
* @Package com.decamincow.spider.vo.longzhu
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月22日
* @version V1.0
*/
package com.medusatv.spider.vo.longzhu;

/**
 * @ClassName: Channel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月22日
 *
 */
public class Channel {

	private String name;// 人名
	private String domain;
	private String status;// 房间名

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
