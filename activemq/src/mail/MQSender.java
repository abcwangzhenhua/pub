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
		// ConnectionFactory �����ӹ�����JMS ������������
        ActiveMQConnectionFactory connectionFactory;
        // Connection ��JMS �ͻ��˵�JMS Provider ������
        QueueConnection connection = null;
        // Session�� һ�����ͻ������Ϣ���߳�
        QueueSession session=null;
        // Destination ����Ϣ��Ŀ�ĵ�;��Ϣ���͸�˭.
        Queue queue;
        // MessageProducer����Ϣ������
        MessageProducer producer;
        // TextMessage message;
        // ����ConnectionFactoryʵ�����󣬴˴�����ActiveMq��ʵ��jar
        connectionFactory = new ActiveMQConnectionFactory(url);
        try {
            // ����ӹ����õ����Ӷ���
            connection = connectionFactory.createQueueConnection();
            // ����
            connection.start();
            // ��ȡ��������
            session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
            // ��ȡsessionע�����ֵxingbo.xu-queue��һ����������queue��������ActiveMq��console����
            queue = session.createQueue(QUEUE_NAME);
            // �õ���Ϣ�����ߡ������ߡ�
            producer = session.createProducer(queue);
            // ���ò��־û����˴�ѧϰ��ʵ�ʸ�����Ŀ����
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // ������Ϣ���˴�д������Ŀ���ǲ��������߷�����ȡ
            for (int i = 1; i <= SEND_NUMBER; i++) {
                TextMessage message = session.createTextMessage("ActiveMq ���͵���Ϣ" + i);
                //session.createObjectMessage(arg0);               
                // ������Ϣ��Ŀ�ĵط�
                System.out.println("MQSender������Ϣ��" + "ActiveMq ���͵���Ϣ" + i);
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
