package com.soho.guava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;


public class GraphTest {
	
	private static final Logger logger = LoggerFactory.getLogger(GraphTest.class);
	
	public static void testGraph() {
		
		MutableGraph<Integer> graph1 = GraphBuilder.directed() //指定为有向图
			    .nodeOrder(ElementOrder.<Integer>insertion()) //节点按插入顺序输出
			    //(还可以取值无序unordered()、节点类型的自然顺序natural())
			    .expectedNodeCount(100) //预期节点数
			    .build();
		
		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
