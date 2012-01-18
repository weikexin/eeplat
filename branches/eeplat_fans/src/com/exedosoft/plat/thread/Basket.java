package com.exedosoft.plat.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Basket {

	Lock lock = new ReentrantLock();

	// 产生Condition对象

	Condition produced = lock.newCondition();

	Condition consumed = lock.newCondition();

	boolean available = false;

	public void produce() throws InterruptedException {
		
		consumed.await(); 

		lock.lock();

		try {

			if (available) {

				consumed.await(); // 放弃lock进入睡眠

			}

			/* 生产苹果 */

			System.out.println("Apple produced.");

			available = true;

			produced.signal(); // 发信号唤醒等待这个Condition的线程
	

		} finally {

			lock.unlock();

		}

	}

	public void consume() throws InterruptedException {

		lock.lock();

		try {

			if (!available) {

				produced.await();// 放弃lock进入睡眠

			}

			/* 吃苹果 */

			System.out.println("Apple consumed.");

			available = false;

			consumed.signal();// 发信号唤醒等待这个Condition的线程

		} finally {

			lock.unlock();

		}

	}

	public static void main(String[] args) throws InterruptedException {

		final Basket basket = new Basket();

		// 定义一个producer

		Runnable producer = new Runnable() {

			public void run() {

				try {

					basket.produce();

				} catch (InterruptedException ex) {

					ex.printStackTrace();

				}

			}

		};

		// 定义一个consumer

		Runnable consumer = new Runnable() {

			public void run() {

				try {

					basket.consume();

				} catch (InterruptedException ex) {

					ex.printStackTrace();

				}

			}

		};

		// 各产生10个consumer和producer

		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(producer);
		service.submit(producer);
		

//
//		for (int i = 0; i < 10; i++)
//
//			service.submit(consumer);
//
//		Thread.sleep(2000);
//
//		for (int i = 0; i < 10; i++)
//
//			service.submit(producer);
//
//		service.shutdown();

	}

}
