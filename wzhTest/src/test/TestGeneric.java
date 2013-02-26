package test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import model.BaseDao;

public class TestGeneric<T> extends Test implements BaseDao<T>{
	public Class<T> cls;
	public TestGeneric(){
		cls=(Class<T>)this.getClass();		
		cls=(Class<T>)cls.getGenericSuperclass();
		//Type sup=cls.getGenericSuperclass();
		//ParameterizedType pt=(ParameterizedType)sup;
		//System.out.println(sup);
		//Type[] args=pt.getActualTypeArguments();
		//System.out.println(args[0]);
		//cls=(Class<T>)args[0];
	}

	/**Generic·ºÐÍ
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestGeneric tg=new TestGeneric();
		try {
			System.out.println(tg.cls.newInstance());
			Class<TestGeneric> t=TestGeneric.class.asSubclass(tg.cls);
			System.out.println(t);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public static void t1(){
		
	}

}
