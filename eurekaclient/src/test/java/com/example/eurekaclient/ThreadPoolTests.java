package com.example.eurekaclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "dev")
public class ThreadPoolTests {

	@Test
	/**
	 * 此方法必须等待所有的任务执行完成后统一返回，一方面内存持有的时间长；另一方面响应性也有一定的影响
	 */
	public void testThreadPoolInvokeAll() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		long startTime = System.currentTimeMillis();
		for (int n = 0; n < 5; n++) {
			List<Callable<Integer>> callables = new ArrayList<>();
			for (int i = 0; i < 8; i++) {
				callables.add(() -> {
					int sleepMs = new Random().nextInt(2000) + 3000;
					System.out.println(Thread.currentThread().getName() + " 休息了" + sleepMs + "毫秒");
					return sleepMs;
				});
			}
			try {
				executorService.invokeAll(callables);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		long endTime = System.currentTimeMillis();
		System.out.println("testThreadPoolInvokeAll Finished. cost Time = " + (endTime - startTime) + "ms");
	}

	@Test
	/**
	 *  CompletionService将Executor（线程池）和BlockingQueue（阻塞队列）结合在一起，
	 同时使用Callable作为任务的基本单元，整个过程生产者不断把Callable
	 任务放入阻塞队列，Executor作为消费者不断把任务取出来执行，并返回结果；
	 */
	public void testThreadPoolCompletionService() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		CompletionService<Integer> service = new ExecutorCompletionService<>(executorService);
		long startTime = System.currentTimeMillis();
		for (int n = 0; n < 5; n++) {
			for (int i = 0; i < 8; i++) {
				service.submit(() -> new Random().nextInt(2000) + 3000);
			}
			// 使用Future<Boolean> flag = service.take()/poll();读取返回的结果,执行快的先返回，不用等所有运行结束
			for(int i = 0 ; i < 8 ; i ++){
				Future<Integer> take = null;
				try {
					take = service.take();//service.poll()方法也可以
					System.out.println("take:" + take.get());
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("testThreadPoolCompletionService Finished. cost Time = " + (endTime - startTime) + "ms");
	}

}
