/**
* @Title: StartupRunner.java
* @Package com.medusatv
* @Description: TODO(用一句话描述该文件做什么)
* @author decamincow
* @date 2016年9月24日
* @version V1.0
*/
package com.medusatv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import com.medusatv.po.Data;

/**
 * @ClassName: StartupRunner
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月24日
 *
 */
@Component
public class StartupRunner implements CommandLineRunner {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	/*
	 * 初始化es数据库
	 */
	@Override
	public void run(String... arg0) throws Exception {
		if (!elasticsearchTemplate.indexExists(Data.class)) {
			elasticsearchTemplate.deleteIndex(Data.class);
			elasticsearchTemplate.createIndex(Data.class);
			elasticsearchTemplate.putMapping(Data.class);
			elasticsearchTemplate.refresh(Data.class);
		}
	}

}
