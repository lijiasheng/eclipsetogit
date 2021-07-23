package com.soho.tasks;

import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component("jobService")
public class jobService {

	@Async
	public void job1() {
		try {
			Thread.sleep(10000l);
			Date startdate = new Date();
			System.out.println("job1" + Thread.currentThread());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Async
	public void job2() {

		try {
			Thread.sleep(10000l);
			Date startdate = new Date();
			System.out.println("job2" + Thread.currentThread());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void job3() {

		try {
			Thread.sleep(10000l);
			Date startdate = new Date();
			System.out.println("job3" + Thread.currentThread());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void job4() {

		try {
			Thread.sleep(10000l);
			Date startdate = new Date();
			System.out.println("job4" + Thread.currentThread());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void job5() {

		try {
			Thread.sleep(10000l);
			Date startdate = new Date();
			System.out.println("job5" + Thread.currentThread());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
