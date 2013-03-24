package mail;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MQReceiver implements MessageListener{
	private static final String url = "tcp://localhost:61616";
	private static final String QUEUE_NAME = "testQ";
	public void receiveMessage() {
		QueueConnection connection = null;
		QueueSession session = null;
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					url);
			connection = connectionFactory.createQueueConnection();
			connection.start();
			session = connection.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(QUEUE_NAME);
			QueueReceiver queueReceiver = session.createReceiver(queue);
			queueReceiver.setMessageListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
				try {
					if(session!=null) session.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if(connection!=null) connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
	@Override
	public void onMessage(Message msg) {
		try {
			if (msg instanceof TextMessage) {
				TextMessage txtMsg = (TextMessage) msg;
				String info = txtMsg.getText();
				System.out.println("MQReceiver:"+info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		MQReceiver mq = new MQReceiver();
		mq.receiveMessage();
	}
}