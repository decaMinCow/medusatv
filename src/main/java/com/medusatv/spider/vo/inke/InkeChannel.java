/**
* @Title: InkeChannel.java
* @Package com.decamincow.spider.vo.inke
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月22日
* @version V1.0
*/
package com.medusatv.spider.vo.inke;

/**
 * @ClassName: InkeChannel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月22日
 *
 */
public class InkeChannel {

	private String id;
	private String nick;
	private String portrait;
	private String liveid;
	private String title;
	private int online_users;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getLiveid() {
		return liveid;
	}

	public void setLiveid(String liveid) {
		this.liveid = liveid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOnline_users() {
		return online_users;
	}

	public void setOnline_users(int online_users) {
		this.online_users = online_users;
	}

}
// "data": {
// "hotlists": [{
// "id": 53438311,
// "level": 94,
// "nick": "\u6797\u5a49\u513f\ud83c\udf8f\u306e\u6c42\u5e94\u63f4\ud83d\udc9e",
// "portrait":
// "http:\/\/image.scale.a8.com\/imageproxy2\/dimgm\/scaleImage?url=http%3A%2F%2Fimg.meelive.cn%2FMTQ3NDI2MjAzOTAwOSMyMzgjanBn.jpg&w=346&h=260&s=80&c=0&o=0",
// "liveid": "1474521223377957",
// "title":
// "\u4f60\u4e11\u4f60\u5148\u7761\uff0c\u6211\u7f8e\u6211\u76f4\u64ad\uff01\u6797\u5a49\u513f\ud83c\udf8f\u306e\u6c42\u5e94\u63f4\ud83d\udc9e\u6b63\u5728\u76f4\u64ad\uff0c\u5feb\u6765\u4e00\u8d77\u770b~",
// "city": "",
// "online_users": 22789