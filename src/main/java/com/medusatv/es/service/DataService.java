/**
* @Title: DataService.java
* @Package com.decamincow.es.service
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月20日
* @version V1.0
*/
package com.medusatv.es.service;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.medusatv.es.repository.DataRepository;
import com.medusatv.po.Data;


/**
* @ClassName: DataService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author decamincow
* @date 2016年9月20日
*
*/
@Service
public class DataService {
	
	private String delaySeconds = "1080";
	
	@Autowired
	DataRepository dataRepository;
	
	public List<Data> save(List<Data> dataList){
		return (List<Data>) dataRepository.save(dataList);
	}
	
	public Data save(Data data){
		return dataRepository.save(data);
	}
	
	/**
	* @Title: search
	* @Description: 通过关键词，页数，每页大小搜索结果。按观看人数倒序排列。
	* @param @param keyWord 搜索关键词
	* @param @param page 页数
	* @param @param pageSize 每页数量
	* @param @return    参数
	* @return List<Data>    返回类型
	* @throws
	*/
	public List<Data> search(String keyWord, int page, int pageSize){
		
		PageRequest pageRequest = new PageRequest(page, pageSize, Sort.Direction.DESC, "watcher");
		
		// 过滤掉下播的主播
//		QueryBuilder range = QueryBuilders.rangeQuery("timestamp");
		QueryBuilder range = QueryBuilders.rangeQuery("timestamp").gt("now-"+ delaySeconds +"s");
		
		QueryBuilder anchorname = QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("anchorname", keyWord))
				.must(range);
		
		QueryBuilder roomid = QueryBuilders.boolQuery()
				.must(QueryBuilders.termQuery("roomid", keyWord))
				.must(range);
		
		QueryBuilder roomname = QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("roomname", keyWord))
				.must(range);
		
		QueryBuilder category = QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("category", keyWord))
				.must(range);
		

		QueryBuilder qb = QueryBuilders.boolQuery()
				.should(anchorname)
				.should(roomid)
				.should(roomname)
				.should(category);
		
		return dataRepository.search(qb, pageRequest).getContent();
		
	}
	
	/**
	* @Title: search
	* @Description: 通过页数，每页大小搜索结果。按观看人数倒序排列。
	* @param @param page 页数
	* @param @param pageSize 每页大小
	* @param @return    参数
	* @return List<Data>    返回类型
	* @throws
	*/
	public List<Data> search(int page, int pageSize){
		
		PageRequest pageRequest = new PageRequest(page, pageSize, Sort.Direction.DESC, "watcher");
		
		// 过滤掉下播的主播
		QueryBuilder range = QueryBuilders.rangeQuery("timestamp").gt("now-"+ delaySeconds +"s");
//		QueryBuilder range = QueryBuilders.rangeQuery("timestamp");
		
		
		QueryBuilder qb = QueryBuilders.boolQuery()
										.should(range);
		
		return dataRepository.search(qb, pageRequest).getContent();
		
	}

}
