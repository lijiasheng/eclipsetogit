package com.soho.akka;

import akka.actor.UntypedActor;

public class CalculatorActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CalculatorActor onReceive:" +  message );
		if (message instanceof Op.Add) {
			System.out.println("CreationActor Op.Add");
			Op.Add add = (Op.Add) message;
			System.out.println("Calculating " + add.getN1() + " + " + add.getN2());
			Op.AddResult result = new Op.AddResult(add.getN1(), add.getN2(), add.getN1() + add.getN2());
			getSender().tell(result, getSelf());

		} else if (message instanceof Op.Subtract) {
			System.out.println("CalculatorActor Op.Subtract");
			Op.Subtract subtract = (Op.Subtract) message;
			System.out.println("Calculating " + subtract.getN1() + " - " + subtract.getN2());
			Op.SubtractResult result = new Op.SubtractResult(subtract.getN1(), subtract.getN2(),
					subtract.getN1() - subtract.getN2());
			getSender().tell(result, getSelf());

		} else if (message instanceof Op.Multiply) {
			System.out.println("CalculatorActor Op.Multiply");
			Op.Multiply multiply = (Op.Multiply) message;
			System.out.println("Calculating " + multiply.getN1() + " * " + multiply.getN2());
			Op.MultiplicationResult result = new Op.MultiplicationResult(multiply.getN1(), multiply.getN2(),
					multiply.getN1() * multiply.getN2());
			getSender().tell(result, getSelf()); //将结果返回给发送方

		} else if (message instanceof Op.Divide) {
			System.out.println("CalculatorActor Op.Divide");
			Op.Divide divide = (Op.Divide) message;
			System.out.println("Calculating " + divide.getN1() + " / " + divide.getN2());
			Op.DivisionResult result = new Op.DivisionResult(divide.getN1(), divide.getN2(),
					divide.getN1() / divide.getN2());
			getSender().tell(result, getSelf());

		} else {
			unhandled(message);
		}
	}
}
