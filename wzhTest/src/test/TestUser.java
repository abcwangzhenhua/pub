package test;

import model.User;
import dao.UserDao;
import dao.UserDaoImpl;

public class TestUser {

	public static void main(String[] args) {
		UserDao userDao=new UserDaoImpl();
		User user=new User();
		//user.setC_id("111");
		user.setC_user("www");
		user.setKey("kkk");
		boolean flag=userDao.saveUser(user);
		System.out.println(flag);
	}

}
