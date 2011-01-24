package com.exedosoft.plat.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.exedosoft.plat.util.DOGlobals;

/**
 * 
 * 需要注意的是线程池必须使用shutdown来显式关闭， 否则主线程就无法退出。shutdown也不会阻塞主线程。
 * 
 * @author anolesoft
 * 
 */

public class ExecutorManager {

	private static Log log = LogFactory.getLog(ExecutorManager.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	final static ExecutorService threadPool = Executors
			.newFixedThreadPool(DOGlobals.EXECUTOR_POOL_SIZE);

	public final static ExecutorService threadOnly = Executors
			.newSingleThreadExecutor();

	final static Lock lock = new ReentrantLock();

	public final static Object lockObect = new Object();

	// 产生Condition对象

	public final static Condition exeCondition = lock.newCondition();

	public static final CyclicBarrier mBarrier = null;

	static boolean isAllBlock = false;

	public static void initPool() {
		log.info("Init the Executor Pools::" + DOGlobals.EXECUTOR_POOL_SIZE);
	}

	public static void submit(Callable c) {// //barrier barrier

		threadPool.submit(c);

	}

	public static void submit(Runnable c) {
		threadPool.submit(c);
	}

	public static void blockAll() {
		isAllBlock = true;
	}

	public static void shutdown() {
		threadPool.shutdown();
		log
				.info("ShutDown the Executor Pools::"
						+ DOGlobals.EXECUTOR_POOL_SIZE);
	}

	/**
	 * 要读取规则定义才行
	 * 
	 * @return
	 */
	public static boolean isExecuteByTime() {

		if (isAllBlock) {
			return false;
		}

		// if(!judgeFromTimeDefine()){
		// return false;
		// }
		try {
			Boolean jj = null;
			try {
				jj = judgeFromTimeDefine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Calendar cc = Calendar.getInstance();
			

			if (jj == null
					&& (cc.get(Calendar.DAY_OF_WEEK) == 1 || cc
							.get(Calendar.DAY_OF_WEEK) == 7)) {
				return false;
			}else{
				/////////如果是例外日期，则返回false,如果是增加的日期，采用缺省的true
				if(jj!=null && !jj.booleanValue()){
					return false;
				}
			}
			if (cc.get(Calendar.HOUR_OF_DAY) < 8
					|| cc.get(Calendar.HOUR_OF_DAY) >= 22) {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public static Boolean judgeFromTimeDefine() throws ParseException {

		Date dDate = new Date();
		String curDate = sdf.format(dDate);

		Document doc = DOGlobals.timeDefineDoc;
		if (doc == null) {
			log.error("无法解析日历配置!!!!!");
			return null;
		}

		NodeList nodeListAdd = doc.getElementsByTagName("add_date");
		for (int i = 0; i < nodeListAdd.getLength(); i++) {
			Element element = (Element) nodeListAdd.item(i);
			String nodeValue = null;
			if (element.getFirstChild() != null) {
				nodeValue = element.getFirstChild().getNodeValue();
			}
			if (curDate.equals(nodeValue)) {
				return Boolean.TRUE;
			}

		}

		NodeList nodeListRemove = doc.getElementsByTagName("exp_remove");
		for (int i = 0; i < nodeListRemove.getLength(); i++) {
			Element element = (Element) nodeListRemove.item(i);
			NodeList children = element.getChildNodes();

			String fromDate = "";
			String toDate = "";
			for (int iChild = 0; iChild < children.getLength(); iChild++) {
				Node node = (Node) children.item(iChild);
				if (node instanceof Element) {
					Element e = (Element) node;
					if ("".equals(fromDate)) {
						fromDate = e.getFirstChild().getNodeValue();
					} else {
						toDate = e.getFirstChild().getNodeValue();
					}
				}
			}
			Date dFrom = sdf.parse(fromDate);
			Date dTo = sdf.parse(toDate);

			if (dDate.equals(dFrom) || dDate.equals(dTo)) {
				return Boolean.FALSE;
			}

			if (dDate.after(dFrom) && dDate.before(dTo)) {
				return Boolean.FALSE;
			}
		}

		return null;
	}

	public static void main(String[] args) throws InterruptedException {
		//
		// try {
		// ExecutorManager.judgeFromTimeDefine();
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		Calendar cc = Calendar.getInstance();

		System.out.println(cc.get(Calendar.DAY_OF_WEEK));

		//		
		// (cc.get(Calendar.DAY_OF_WEEK) == 6
		// || cc.get(Calendar.DAY_OF_WEEK) == 7)) {
		// return false;
		// }
		// 开始的倒数锁
		// final CountDownLatch begin = new CountDownLatch(1);
		// // 结束的倒数锁
		// final CountDownLatch end = new CountDownLatch(10);
		// // 十名选手
		// threadPool.shutdown();
		//
		// for (int index = 0; index < 10; index++) {
		// final int NO = index + 1;
		// Runnable run = new Runnable() {
		// public void run() {
		// try {
		// begin.await();
		// Thread.sleep((long) (Math.random() * 10000));
		// System.out.println("No." + NO + " arrived");
		// } catch (InterruptedException e) {
		// } finally {
		// end.countDown();
		// }
		// }
		// };
		// threadPool.submit(run);
		// }
		// System.out.println("Game Start");
		// begin.countDown();
		// end.await();
		// System.out.println("Game Over");
		// threadPool.shutdown();
	}

}
