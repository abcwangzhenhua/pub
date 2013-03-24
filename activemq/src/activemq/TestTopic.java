package activemq;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

public class TestTopic {
	private final static String nn="";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection conn=factory.createConnection();
		Session session=conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		conn.start();
		MessageProducer prod=session.createProducer(session.createTopic("logTopic"));
		//TextMessage msg=session.createTextMessage("hello world");
		//ObjectMessage msg=(ActiveMQObjectMessage)session.createObjectMessage("hello world!");
		Logger log = Logger.getLogger(TestTopic.class);
		log.info("Test topic!");
		//LoggingEvent ev=LoggingEvent
		ObjectMessage msg=(ActiveMQObjectMessage)session.createObjectMessage();
		prod.send(msg);
		session.commit();
		prod.close();
		session.close();
		conn.close();
	}

}
