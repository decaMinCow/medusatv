package com.medusatv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("success");
	}

}



//package com.medusatv;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.medusatv.es.service.DataService;
//import com.medusatv.po.Data;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ApplicationTests {
//
//	private final static Logger logger = LoggerFactory.getLogger(ApplicationTests.class);
//
//	@Autowired
//	private DataService dataService;
//
//	@Autowired
//	private ElasticsearchTemplate elasticsearchTemplate;
//
//	@Before
//	public void before() {
//		if (!elasticsearchTemplate.indexExists(Data.class)) {
//			elasticsearchTemplate.deleteIndex(Data.class);
//			elasticsearchTemplate.createIndex(Data.class);
//			elasticsearchTemplate.putMapping(Data.class);
//			elasticsearchTemplate.refresh(Data.class);
//		}
//	}
//
//	// @Test
//	public void testSave() {
//		Data data = new Data("panda" + "123456", "panda", "123456", "baidu.com", "A总带大家谈谈心", "Aarmani",
//				"http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg", "美女", 2121993, new Data().getTimestamp());
//		dataService.save(data);
//
//	}
//
////	@Test
//	public void testBulkSave() {
//		List<Data> dataList = new ArrayList<Data>();
//		Data data = new Data("panda" + "123456", "panda", "123456", "baidu.com", "A总带大家谈谈心1", "Aarmani",
//				"http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg", "DOTA2", 2121993, new Data().getTimestamp());
//		Data data2 = new Data("douyu" + "66666", "douyu", "66666", "baidu.com", "这是个神奇的直播间1", "神秘",
//				"http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg", "LOL", 10, new Data().getTimestamp());
//		Data data3 = new Data("panda" + "1234567", "测试一下", "123456", "baidu.com", "喷子好多啊1", "呼噜噜",
//				"http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg", "户外", 1938, new Data().getTimestamp());
//		dataList.add(data);
//		dataList.add(data2);
//		dataList.add(data3);
//		dataService.save(dataList);
//
//	}
//
//	@Test
//	public void search() throws Exception {
//
//		System.out.println("result: " + dataService.search("女神", 0, 20));
//	}
//
//	// @Test
//	public void test() {
//		logger.info("test");
//	}
//
//}
