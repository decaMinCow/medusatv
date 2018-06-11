/**
* @Title: Item.java
* @Package com.decamincow.spider.vo.longzhu
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月22日
* @version V1.0
*/
package com.medusatv.spider.vo.longzhu;

import java.util.List;

/**
 * @ClassName: Item
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月22日
 *
 */
public class LongzhuChannel {

	private String preview;
	private String viewers;
	private List<Game> game;
	private Channel channel;

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getViewers() {
		return viewers;
	}

	public void setViewers(String viewers) {
		this.viewers = viewers;
	}

	public List<Game> getGame() {
		return game;
	}

	public void setGame(List<Game> game) {
		this.game = game;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}
// "preview":
// "http:\/\/img2.plures.net\/live\/screenshots\/442045\/2a8a\/546a\/2803\/9218\/0d05\/592a\/f29c\/b184_1474513726.jpg",
// "preview2":
// "http:\/\/longzhu.kssws.ks-cdn.com\/record\/live\/04378293485647d8bfcf31d5b6545a9f\/picture\/1470144031098_506990884\/04378293485647d8bfcf31d5b6545a9f-1470144051.jpg@base@tag=imgScale&w=320&h=320&m=1&q=70&c=1",
// "preview_auto": true,
// "game": [{
// "Id": "7",
// "id": "7",
// "Name": "穿越火线",
// "name": "穿越火线",
// "tag": "cf"
// }],
// "channel": {
// "id": "442045",
// "name": "志康",
// "avatar":
// "http:\/\/img2.plures.net\/0710\/5746\/55fa\/d3a1\/4083\/bf8c\/e8d8\/912b.jpg",
// "grade": 15,
// "url": "http:\/\/star.longzhu.com\/102617",
// "h5": "http:\/\/star.longzhu.com\/m\/102617",
// "status": "树秋啊~ 狙太傲！",
// "broadcast_begin": 1474510739000,
// "broadcast_duration": "3062",
// "videos": "0",
// "_type": "1",
// "_subtype": "5",
// "belle": "0",
// "domain": "102617",
// "flowers": "1996860",
// "followers": "81501",
// "glamours": "5831690",
// "tag": "瞬狙大魔王",
// "vid": 0,
// "weight": 0,
// "live_source": 8,
// "stream_types": 12
// },
// "viewers": "27210"