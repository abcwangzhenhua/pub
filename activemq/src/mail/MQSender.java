package mail;

import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MQSender {
	private static final int SEND_NUMBER = 10000;
	private static final String url = "tcp://localhost:61616";
	private static final String QUEUE_NAME = "testQ";
	public static void main(String[] args) {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接
        ActiveMQConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        QueueConnection connection = null;
        // Session： 一个发送或接收消息的线程
        QueueSession session=null;
        // Destination ：消息的目的地;消息发送给谁.
        Queue queue;
        // MessageProducer：消息发送者
        MessageProducer producer;
        // TextMessage message;
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
        connectionFactory = new ActiveMQConnectionFactory(url);
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createQueueConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            queue = session.createQueue(QUEUE_NAME);
            // 得到消息生成者【发送者】
            producer = session.createProducer(queue);
            // 设置不持久化，此处学习，实际根据项目决定
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 构造消息，此处写死，项目就是参数，或者方法获取
            for (int i = 1; i <= SEND_NUMBER; i++) {
                TextMessage message = session.createTextMessage("ActiveMq 发送的消息" + i);
                //session.createObjectMessage(arg0);               
                // 发送消息到目的地方
                System.out.println("MQSender发送消息：" + "ActiveMq 发送的消息" + i);
                producer.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	if(session!=null) session.close();
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }

	}

}
