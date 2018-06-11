/**
* @Title: ScheduledTasks
* @Package com.decamincow.spider.task
* @Description: 定时爬或通过API接口获取数据
* @author decamincow
* @date 2016年9月22日
* @version V1.0
*/
package com.medusatv.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.medusatv.es.service.DataService;
import com.medusatv.po.Data;
import com.medusatv.spider.DouyuSpider;
import com.medusatv.spider.HuajiaoSpider;
import com.medusatv.spider.HuomaoSpider;
import com.medusatv.spider.HuyaSpider;
import com.medusatv.spider.InkeSpider;
import com.medusatv.spider.LongzhuSpider;
import com.medusatv.spider.PandaSpider;
import com.medusatv.spider.QuanminSpider;
import com.medusatv.spider.ZhanqiSpider;

/**
 * @ClassName: ScheduledTasks
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author decamincow
 * @date 2016年9月22日
 *
 */
@Component
public class ScheduledTasks {
	
	private final static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS,
			new LinkedBlockingQueue<Runnable>(10));

	@Autowired
	private DouyuSpider douyuSpider;
	@Autowired
	private HuajiaoSpider huajiaoSpider;
	@Autowired
	private HuomaoSpider huomaoSpider;
	@Autowired
	private HuyaSpider huyaSpider;
	@Autowired
	private InkeSpider inkeSpider;
	@Autowired
	private LongzhuSpider longzhuSpider;
	@Autowired
	private PandaSpider pandaSpider;
	@Autowired
	private QuanminSpider quanminSpider;
	@Autowired
	private ZhanqiSpider zhanqiSpider;

	@Autowired
	private DataService dataService;

	List<Data> dataList;

	@Scheduled(cron = "* 0/18 *  * * ? ")
	public void douyuSpider(){
		
		try {

//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
				Long startTime = System.currentTimeMillis() / 1000;
				douyuSpider.getAllContents();
				Long endTime1 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "斗鱼抓取用时：" + (endTime1 - startTime));
				dataList = new ArrayList<Data>();
				dataList = douyuSpider.getAllContents();
				dataService.save(dataList);
				Long endTime2 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "斗鱼存储用时：" + (endTime2 - startTime));


//			}
//		});
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Scheduled(cron = "* 2/18 *  * * ? ")
	public void huajiaoSpider(){
		
		try{

//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
				Long startTime = System.currentTimeMillis() / 1000;
				huajiaoSpider.getAllContents();
				Long endTime1 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "花椒抓取用时：" + (endTime1 - startTime));
				dataList = new ArrayList<Data>();
				dataList = huajiaoSpider.getAllContents();
				dataService.save(dataList);
				Long endTime2 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "花椒存储用时：" + (endTime2 - startTime));
//			}
//		});
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Scheduled(cron = "* 4/18 *  * * ? ")
	public void huomaoSpider(){
		
		try{

//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
				Long startTime = System.currentTimeMillis() / 1000;
				huomaoSpider.getAllContents();
				Long endTime1 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "火猫抓取用时：" + (endTime1 - startTime));
				dataList = new ArrayList<Data>();
				dataList = huomaoSpider.getAllContents();
				dataService.save(dataList);
				Long endTime2 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "火猫存储用时：" + (endTime2 - startTime));
//			}
//		});
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}	

	@Scheduled(cron = "* 6/18 *  * * ? ")
	public void huyaSpider(){
		
		try{

//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
				Long startTime = System.currentTimeMillis() / 1000;
				huyaSpider.getAllContents();
				Long endTime1 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "虎牙抓取用时：" + (endTime1 - startTime));
				dataList = new ArrayList<Data>();
				dataList = huyaSpider.getAllContents();
				dataService.save(dataList);
				Long endTime2 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "虎牙存储用时：" + (endTime2 - startTime));
//			}
//		});
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}		

	@Scheduled(cron = "* 8/18 *  * * ? ")
	public void inkeSpider(){
		
		try{

//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
				Long startTime = System.currentTimeMillis() / 1000;
				inkeSpider.getAllContents();
				Long endTime1 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "映客抓取用时：" + (endTime1 - startTime));
				dataList = new ArrayList<Data>();
				dataList = inkeSpider.getAllContents();
				dataService.save(dataList);
				Long endTime2 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "映客存储用时：" + (endTime2 - startTime));
//			}
//		});
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}	

	@Scheduled(cron = "* 10/18 *  * * ? ")
	public void longzhuSpider(){
		
		try{

//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
				Long startTime = System.currentTimeMillis() / 1000;
				longzhuSpider.getAllContents();
				Long endTime1 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "龙珠抓取用时：" + (endTime1 - startTime));
				dataList = new ArrayList<Data>();
				dataList = longzhuSpider.getAllContents();
				dataService.save(dataList);
				Long endTime2 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "龙珠存储用时：" + (endTime2 - startTime));
//			}
//		});
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}		

	@Scheduled(cron = "* 12/18 *  * * ? ")
	public void pandaSpider(){
		
		try{

//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
				Long startTime = System.currentTimeMillis() / 1000;
				pandaSpider.getAllContents();
				Long endTime1 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "熊猫抓取用时：" + (endTime1 - startTime));
				dataList = new ArrayList<Data>();
				dataList = pandaSpider.getAllContents();
				dataService.save(dataList);
				Long endTime2 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "熊猫存储用时：" + (endTime2 - startTime));
//			}
//		});
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}	

	@Scheduled(cron = "* 14/18 *  * * ? ")
	public void quanminSpider(){
		
		try{

//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
				Long startTime = System.currentTimeMillis() / 1000;
				quanminSpider.getAllContents();
				Long endTime1 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "全民抓取用时：" + (endTime1 - startTime));
				dataList = new ArrayList<Data>();
				dataList = quanminSpider.getAllContents();
				dataService.save(dataList);
				Long endTime2 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "全民存储用时：" + (endTime2 - startTime));
//			}
//		});
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}		

	@Scheduled(cron = "* 16/18 *  * * ? ")
	public void zhanqiSpider(){
		
		try{

//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
				Long startTime = System.currentTimeMillis() / 1000;
				zhanqiSpider.getAllContents();
				Long endTime1 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "战旗抓取用时：" + (endTime1 - startTime));
				dataList = new ArrayList<Data>();
				dataList = zhanqiSpider.getAllContents();
				dataService.save(dataList);
				Long endTime2 = System.currentTimeMillis() / 1000;
				logger.info(Thread.currentThread().getId() + "战旗存储用时：" + (endTime2 - startTime));
//			}
//		});
		
		}catch (Exception e) {
			e.printStackTrace();
		}

}	
	
//	public void spider() {
		
//	
//	@Scheduled(cron = "* 0/2 *  * * ? ")
//	public void spider() {
//		
//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				Long startTime = System.currentTimeMillis() / 1000;
//				douyuSpider.getAllContents();
//				Long endTime1 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "斗鱼抓取用时：" + (endTime1 - startTime));
//				dataList = new ArrayList<Data>();
//				dataList = douyuSpider.getAllContents();
//				dataService.save(dataList);
//				Long endTime2 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "斗鱼存储用时：" + (endTime2 - startTime));
//				
//				
//			}
//		});
//		
////		threadPoolExecutor.execute(new Runnable() {
////			@Override
////			public void run() {
////				Long startTime = System.currentTimeMillis() / 1000;
////				huajiaoSpider.getAllContents();
////				Long endTime1 = System.currentTimeMillis() / 1000;
////				logger.info(Thread.currentThread().getId() + "花椒抓取用时：" + (endTime1 - startTime));
////				dataList = new ArrayList<Data>();
////				dataList = huajiaoSpider.getAllContents();
////				dataService.save(dataList);
////				Long endTime2 = System.currentTimeMillis() / 1000;
////				logger.info(Thread.currentThread().getId() + "花椒存储用时：" + (endTime2 - startTime));
////			}
////		});
//		
//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				Long startTime = System.currentTimeMillis() / 1000;
//				huomaoSpider.getAllContents();
//				Long endTime1 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "火猫抓取用时：" + (endTime1 - startTime));
//				dataList = new ArrayList<Data>();
//				dataList = huomaoSpider.getAllContents();
//				dataService.save(dataList);
//				Long endTime2 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "火猫存储用时：" + (endTime2 - startTime));
//			}
//		});
//		
//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				Long startTime = System.currentTimeMillis() / 1000;
//				huyaSpider.getAllContents();
//				Long endTime1 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "虎牙抓取用时：" + (endTime1 - startTime));
//				dataList = new ArrayList<Data>();
//				dataList = huyaSpider.getAllContents();
//				dataService.save(dataList);
//				Long endTime2 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "虎牙存储用时：" + (endTime2 - startTime));
//			}
//		});
//		
////		threadPoolExecutor.execute(new Runnable() {
////			@Override
////			public void run() {
////				Long startTime = System.currentTimeMillis() / 1000;
////				inkeSpider.getAllContents();
////				Long endTime1 = System.currentTimeMillis() / 1000;
////				logger.info(Thread.currentThread().getId() + "映客抓取用时：" + (endTime1 - startTime));
////				dataList = new ArrayList<Data>();
////				dataList = inkeSpider.getAllContents();
////				dataService.save(dataList);
////				Long endTime2 = System.currentTimeMillis() / 1000;
////				logger.info(Thread.currentThread().getId() + "映客存储用时：" + (endTime2 - startTime));
////			}
////		});
//		
//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				Long startTime = System.currentTimeMillis() / 1000;
//				longzhuSpider.getAllContents();
//				Long endTime1 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "龙珠抓取用时：" + (endTime1 - startTime));
//				dataList = new ArrayList<Data>();
//				dataList = longzhuSpider.getAllContents();
//				dataService.save(dataList);
//				Long endTime2 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "龙珠存储用时：" + (endTime2 - startTime));
//			}
//		});
//		
//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				Long startTime = System.currentTimeMillis() / 1000;
//				pandaSpider.getAllContents();
//				Long endTime1 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "熊猫抓取用时：" + (endTime1 - startTime));
//				dataList = new ArrayList<Data>();
//				dataList = pandaSpider.getAllContents();
//				dataService.save(dataList);
//				Long endTime2 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "熊猫存储用时：" + (endTime2 - startTime));
//			}
//		});
//		
//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				Long startTime = System.currentTimeMillis() / 1000;
//				quanminSpider.getAllContents();
//				Long endTime1 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "全民抓取用时：" + (endTime1 - startTime));
//				dataList = new ArrayList<Data>();
//				dataList = quanminSpider.getAllContents();
//				dataService.save(dataList);
//				Long endTime2 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "全民存储用时：" + (endTime2 - startTime));
//			}
//		});
//		
//		threadPoolExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				Long startTime = System.currentTimeMillis() / 1000;
//				zhanqiSpider.getAllContents();
//				Long endTime1 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "战旗抓取用时：" + (endTime1 - startTime));
//				dataList = new ArrayList<Data>();
//				dataList = zhanqiSpider.getAllContents();
//				dataService.save(dataList);
//				Long endTime2 = System.currentTimeMillis() / 1000;
//				logger.info(Thread.currentThread().getId() + "战旗存储用时：" + (endTime2 - startTime));
//			}
//		});
//	}

}
