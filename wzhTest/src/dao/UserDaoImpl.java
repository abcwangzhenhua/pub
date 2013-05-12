package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import model.User;

public class UserDaoImpl implements UserDao{
	private static Session session;
	static{
		Configuration config=new AnnotationConfiguration();
		config.configure();
		SessionFactory sessionFactory=config.buildSessionFactory();
		session=sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean saveUser(User user) {		
		Transaction tx=session.beginTransaction();
		session.save(user);
		tx.commit();
		return true;
	}

}
