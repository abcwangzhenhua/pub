package test;

//import net.spy.memcached.MemcachedClient;

import com.meetup.memcached.MemcachedClient;
import com.meetup.memcached.SockIOPool;

public class MemCachedTest {

	private static MemcachedClient mcc = new MemcachedClient();

	static {
		String[] servers = { "127.0.0.1:11211" };
		// 创建一个连接池
		SockIOPool pool = SockIOPool.getInstance();
		// 设置缓存服务器
		pool.setServers(servers);
		// 设置初始化连接数，最小连接数，最大连接数以及最大处理时间
		pool.setInitConn(50);
		pool.setMinConn(50);
		pool.setMaxConn(500);
		pool.setMaxIdle(1000 * 60 * 60);
		// 设置主线程睡眠时间，每30秒苏醒一次，维持连接池大小
		pool.setMaintSleep(30);
		// 关闭套接字缓存
		pool.setNagle(false);
		// 连接建立后的超时时间
		pool.setSocketTO(3000);
		// 连接建立时的超时时间
		pool.setSocketConnectTO(0);
		// 初始化连接池
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