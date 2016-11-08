
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class Mqttsubtest implements MqttCallback{

	MqttClient client;

	public static void main(String[] args) throws InterruptedException, MqttException {
	    Mqttsubtest paho = new Mqttsubtest();
	    paho.loop();
	}

	public Mqttsubtest() {
	}

	public void loop() throws InterruptedException, MqttException{
		String addr = "tcp://your_ip_addr:your_port_num";
		client = new MqttClient(addr, "Receving");
	    client.connect();
	    client.setCallback(this);
	    client.subscribe("proto/demo");
	    while(true){}
	}

	@Override
	public void connectionLost(Throwable cause) {
	    // TODO Auto-generated method stub
	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
	        throws Exception {
		System.out.println(message.toString() + "---" + System.currentTimeMillis());
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}

	}
