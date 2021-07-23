package com.soho.akka;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.Random;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import scala.concurrent.duration.Duration;

/**
 * 
 * @author git
 *
 */

public class CreationApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (args.length == 0 || args[0].equals("CalculatorWorker"))
			startRemoteWorkerSystem();
		if (args.length == 0 || args[0].equals("Creation"))
			startRemoteCreationSystem();

	}

	//从系统层面看,就是创建了scheduler线程+dispatcher线程池
	public static void startRemoteWorkerSystem() {
		ActorSystem.create("CalculatorWorkerSystem", ConfigFactory.load(("calculator")));
		System.out.println("Started CalculatorWorkerSystem");
	}

	public static void startRemoteCreationSystem() {
		//从系统层面看,就是创建了scheduler线程+dispatcher线程池
		final ActorSystem system = ActorSystem.create("CreationSystem", ConfigFactory.load("remotecreation1"));
		// 创建CreationActor. 这个是root Actor.
		final ActorRef actor = system.actorOf(Props.create(CreationActor.class), "creationActor");

		System.out.println("Started CreationSystem , create CreationActor is ." + actor );
		final Random r = new Random();
		system.scheduler().schedule(Duration.create(1, SECONDS), Duration.create(1, SECONDS), new Runnable() {
			@Override
			public void run() {
				if (r.nextInt(100) % 2 == 0) {
					System.out.println("ActorSystem tell CreationActor Op.Multiply.");
					actor.tell(new Op.Multiply(r.nextInt(100), r.nextInt(100)), null);
				} else {
					System.out.println("ActorSystem tell CreationActor Op.Divide.");
					actor.tell(new Op.Divide(r.nextInt(10000), r.nextInt(99) + 1), null);
				}
			}
		}, system.dispatcher());
	}

}
