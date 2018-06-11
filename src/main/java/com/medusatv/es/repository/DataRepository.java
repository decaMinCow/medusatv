/**
* @Title: DataRepository.java
* @Package com.decamincow.es.repository
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月20日
* @version V1.0
*/
package com.medusatv.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.medusatv.po.Data;


/**
* @ClassName: DataRepository
* @Description: TODO(这里用一句话描述这个类的作用)
* @author decamincow
* @date 2016年9月20日
*
*/
public interface DataRepository extends ElasticsearchRepository<Data, String> {
	
	
//	 <S extends T> S save(S entity); //1
//
//	    T findOne(ID primaryKey);       //2
//
//	    Iterable<T> findAll();          //3
//
//	    Long count();                   //4
//
//	    void delete(T entity);          //5
//
//	    boolean exists(ID primaryKey);  //6
//
	    // … more functionality omitted.

}
