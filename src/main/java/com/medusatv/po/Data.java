/**
* @Title: Data.java
* @Package com.decamincow.es.po
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月19日
* @version V1.0
*/
package com.medusatv.po;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ClassName: Data
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月19日
 *
 */
@Document(indexName = "medusatv", type = "data", refreshInterval = "-1")
// 平台名 房间id 房间名 主播名 主播头像 房间截图 关注数 人数
public class Data {

	@Id
	@Field(index = FieldIndex.not_analyzed)
	private String id;

	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String platformname;

	@Field(type = FieldType.String)
	private String roomid;

	@Field(type = FieldType.String)
	private String link;

	@Field(type = FieldType.String, index = FieldIndex.analyzed, analyzer = "ik", searchAnalyzer = "ik")
	private String roomname;

	@Field(type = FieldType.String, index = FieldIndex.analyzed, analyzer = "ik", searchAnalyzer = "ik")
	private String anchorname;

	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String roompic;

	@Field(type = FieldType.String, index = FieldIndex.analyzed, analyzer = "ik", searchAnalyzer = "ik")
//	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String category;

	@Field(type = FieldType.Integer, index = FieldIndex.not_analyzed)
	private int watcher;

	@Field(type = FieldType.Date, index = FieldIndex.not_analyzed)
	private Date timestamp;

	/**
	 * 创建一个新的实例 Data.
	 *
	 * @param id
	 * @param platformname
	 * @param roomid
	 * @param link
	 * @param roomname
	 * @param anchorname
	 * @param roompic
	 * @param category
	 * @param watcher
	 * @param timestamp
	 */
	public Data(String id, String platformname, String roomid, String link, String roomname, String anchorname,
			String roompic, String category, int watcher, Date timestamp) {
		super();
		this.id = id;
		this.platformname = platformname;
		this.roomid = roomid;
		this.link = link;
		this.roomname = roomname;
		this.anchorname = anchorname;
		this.roompic = roompic;
		this.category = category;
		this.watcher = watcher;
		this.timestamp = timestamp;
	}

	/**
	 * 创建一个新的实例 Data.
	 *
	 */
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlatformname() {
		return platformname;
	}

	public void setPlatformname(String platformname) {
		this.platformname = platformname;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getAnchorname() {
		return anchorname;
	}

	public void setAnchorname(String anchorname) {
		this.anchorname = anchorname;
	}

	public String getRoompic() {
		return roompic;
	}

	public void setRoompic(String roompic) {
		this.roompic = roompic;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getWatcher() {
		return watcher;
	}

	public void setWatcher(int watcher) {
		this.watcher = watcher;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", platformname=" + platformname + ", roomid=" + roomid + ", link=" + link
				+ ", roomname=" + roomname + ", anchorname=" + anchorname + ", roompic=" + roompic + ", category="
				+ category + ", watcher=" + watcher + ", timestamp=" + timestamp + "]";
	}

}
