package hzf.paxos.main;

import hzf.paxos.doer.Acceptor;
import hzf.paxos.doer.Proposer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主函数 
 * @author linjx
 *
 */
public class Main
{
	private static final int NUM_OF_PROPOSER = 13;
	private static final int NUM_OF_ACCEPTOR = 7;
	public static CountDownLatch latch = new CountDownLatch(NUM_OF_PROPOSER);;
	
	public static void main(String[] args)
	{
		List<Acceptor> acceptors = new ArrayList<Acceptor>();
		for	(int i=0;i<NUM_OF_ACCEPTOR;i++)
		{
			acceptors.add(new Acceptor());
		}
		
		ExecutorService es = Executors.newCachedThreadPool();
		for	(int i = 0; i < NUM_OF_PROPOSER; i++)
		{
			Proposer proposer =  new Proposer(i, i + "#Proposer", NUM_OF_PROPOSER, acceptors);
			es.submit(proposer);
		}
		es.shutdown();
	}
}
