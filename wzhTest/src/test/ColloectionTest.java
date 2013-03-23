package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColloectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		t1();
	}
	public static void t2(){
		List list=new ArrayList();
		list.add("0");
		list.add("1");
		list.add("2");
		List subList=list.subList(0, 2);
		subList.set(0, 3);
		subList.set(1, 4);
		System.out.println(list.get(0)+","+list.get(1)+","+list.get(2));
	}
	public static void t1(){
		Map map=new HashMap();
		for(int i=0;i<10000;i++){
			map.put(i, i);
		}
		System.out.println("集合大小："+map.size());
		new Thread(new RemoveThread(map)).start();
		new Thread(new RemoveThread(map)).start();
	}

}
class RemoveThread implements Runnable{
	private Map map;
	private static Integer i=0;
	private static String LOCK="";
	public RemoveThread(Map map){
		this.map=map;
	}
	public boolean remove(){
		try{
			System.out.println(this.hashCode()+":正移除：("+i+")"+map.remove(i++));
			return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public void run() {
		while(true&&map.size()>0){
			synchronized(LOCK){
				LOCK=""+Math.random();
				remove();
				System.out.println(i);
			}
		}		
	}
}
