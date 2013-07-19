package test;

//import net.spy.memcached.MemcachedClient;

import com.meetup.memcached.MemcachedClient;
import com.meetup.memcached.SockIOPool;

public class MemCachedTest {

	private static MemcachedClient mcc = new MemcachedClient();

	static {
		String[] servers = { "127.0.0.1:11211" };
		// ����һ�����ӳ�
		SockIOPool pool = SockIOPool.getInstance();
		// ���û��������
		pool.setServers(servers);
		// ���ó�ʼ������������С������������������Լ������ʱ��
		pool.setInitConn(50);
		pool.setMinConn(50);
		pool.setMaxConn(500);
		pool.setMaxIdle(1000 * 60 * 60);
		// �������߳�˯��ʱ�䣬ÿ30������һ�Σ�ά�����ӳش�С
		pool.setMaintSleep(30);
		// �ر��׽��ֻ���
		pool.setNagle(false);
		// ���ӽ�����ĳ�ʱʱ��
		pool.setSocketTO(3000);
		// ���ӽ���ʱ�ĳ�ʱʱ��
		pool.setSocketConnectTO(0);
		// ��ʼ�����ӳ�
		pool.initialize();
	}

	protected MemCachedTest() {

	}

	public static MemcachedClient getInstance() {
		return mcc;
	}

	public static void main(String[] args) {

		MemcachedClient mcc = MemCachedTest.getInstance();
		for (int i = 0; i < 10; i++) {
			boolean success = mcc.set("" + i, "Hello!");
			String result = (String) mcc.get("" + i);
			System.out.println(String.format("set( %d ): %s", i, success));
			System.out.println(String.format("get( %d ): %s", i, result));
		}

		System.out.println("\n\t -- sleeping --\n");
		try {
			Thread.sleep(10000);
		} catch (Exception ex) {
		}

		for (int i = 0; i < 10; i++) {
			boolean success = mcc.set("" + i, "Hello!");
			String result = (String) mcc.get("" + i);
			System.out.println(String.format("set( %d ): %s", i, success));
			System.out.println(String.format("get( %d ): %s", i, result));
		}
	}
}