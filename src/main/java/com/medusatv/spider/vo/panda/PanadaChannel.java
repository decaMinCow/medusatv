/**
* @Title: Item.java
* @Package com.decamincow.spider.vo
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月22日
* @version V1.0
*/
package com.medusatv.spider.vo.panda;

/**
 * @ClassName: Item
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月22日
 *
 */
public class PanadaChannel {

	private String id;

	private String name;

	private int person_num;

	private Classification classification;

	private Pictures pictures;

	private Userinfo userinfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPerson_num() {
		return person_num;
	}

	public void setPerson_num(int person_num) {
		this.person_num = person_num;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public Pictures getPictures() {
		return pictures;
	}

	public void setPictures(Pictures pictures) {
		this.pictures = pictures;
	}

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

}
// "id": "485118",
// "name": "Hello!\u5973\u795e49\u5929",
// "person_num": "376955",
// "classification": {
// "cname": "\u718a\u732b\u661f\u79c0",
// "ename": "yzdr"
// },
// "pictures": {
// "img":
// "http:\/\/i8.pdim.gs\/45\/c5074c01568f106aa8dbbcb680be2c15\/w338\/h190.jpg",
// "qrcode": "http:\/\/i6.pdim.gs\/c76cbd2a14f257c0d0ec2b332adad2db.png"
// },
// "tag": "\u5973\u795e",
// "tag_color": "6",
// "room_type": "1",
// "rtype_value": "",
// "status": "2",
// "userinfo": {
// "nickName": "\u5973\u795e\u4e3b\u76f4\u64ad\u95f4",
// "rid": 38713582,
// "avatar": "http:\/\/i7.pdim.gs\/0257040380bd7d294a8017532c07e39b.jpg",
// "userName": "PandaTv38713582"
// }