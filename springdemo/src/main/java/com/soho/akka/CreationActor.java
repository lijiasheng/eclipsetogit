package com.soho.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class CreationActor  extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CreationActor onReceive:" +  message );
		if (message instanceof Op.MathOp) {
			System.out.println("CreationActor receive Op.MathOp ");
			//创建child actor. 
			ActorRef calculator = getContext().actorOf(Props.create(CalculatorActor.class));
			
			System.out.println("CreationActor create CalculatorActor is " + calculator );
			System.out.println("CreationActor tell CalculatorActor message.");
			calculator.tell(message, getSelf() );

		} else if (message instanceof Op.MultiplicationResult) {
			System.out.println("CreationActor receive Op.MultiplicationResult");
			Op.MultiplicationResult result = (Op.MultiplicationResult) message;
			System.out.printf("Mul result: %d * %d = %d\n", result.getN1(), result.getN2(), result.getResult());
			//停止child actor.
			getContext().stop(getSender());

		} else if (message instanceof Op.DivisionResult) {
			System.out.println("CreationActor Op.DivisionResult");
			Op.DivisionResult result = (Op.DivisionResult) message;
			System.out.printf("Div result: %.0f / %d = %.2f\n", result.getN1(), result.getN2(), result.getResult());
			getContext().stop(getSender());

		} else {
			unhandled(message);
		}
	}
}
