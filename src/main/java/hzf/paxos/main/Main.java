package hzf.paxos.main;

import hzf.paxos.doer.Acceptor;
import hzf.paxos.doer.Proposer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 主函数 
 * @author linjx
 *
 */
public class Main
{
	private static final int NUM_OF_PROPOSER = 3;
	private static final int NUM_OF_ACCEPTOR = 7;
	public static CountDownLatch latch = new CountDownLatch(NUM_OF_PROPOSER);
	
	public static void main(String[] args)
	{
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(20));

		List<Acceptor> acceptors = new ArrayList<Acceptor>();
		for	(int i=0;i<NUM_OF_ACCEPTOR;i++)
		{
			acceptors.add(new Acceptor());
		}

		for	(int i = 0; i < NUM_OF_PROPOSER; i++)
		{
			Proposer proposer =  new Proposer(i, i + "#Proposer", NUM_OF_PROPOSER, acceptors);
			executor.execute(proposer);
		}

		executor.shutdown();

		try
		{
		    Thread.sleep(1000);
		}
		catch (Exception e) { e.printStackTrace(); }

		for (Acceptor acceptor : acceptors)
		{
			System.out.println(acceptor);
		}
	}
}
