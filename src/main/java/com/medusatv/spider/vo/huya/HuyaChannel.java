/**
* @Title: Channel.java
* @Package com.decamincow.spider.vo.huya
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月22日
* @version V1.0
*/
package com.medusatv.spider.vo.huya;

/**
 * @ClassName: Channel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月22日
 *
 */
public class HuyaChannel {

	private String privateHost;
	private int totalCount;
	private String gameFullName;
	private String screenshot;
	private String introduction;
	private String nick;

	public String getPrivateHost() {
		return privateHost;
	}

	public void setPrivateHost(String privateHost) {
		this.privateHost = privateHost;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getGameFullName() {
		return gameFullName;
	}

	public void setGameFullName(String gameFullName) {
		this.gameFullName = gameFullName;
	}

	public String getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

}
