import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class AmqpPubTest {

  private static final String EXCHANGE_NAME = "topic_logs";
  private static int sent = 0;

  public static void main(String[] argv) {
    Connection connection = null;
    Channel channel = null;
    try {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      connection = factory.newConnection();
      channel = connection.createChannel();

      channel.exchangeDeclare(EXCHANGE_NAME, "topic");
      while(true){
    	  publish(channel,argv);
    	  System.out.println(sent + "---" + System.currentTimeMillis());
    	  sent++;
    	  Thread.sleep(500);
      }
    }
    catch  (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (connection != null) {
        try {
          connection.close();
        }
        catch (Exception ignore) {}
      }
    }
  }
  
  private static void publish(Channel channel, String[] argv) throws UnsupportedEncodingException, IOException{
	  String routingKey = getRouting(argv);
      String message = ""+sent;
      channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
  }

  private static String getRouting(String[] strings){
    if (strings.length < 1)
    	    return "test.test";
    return strings[0];
  }
}