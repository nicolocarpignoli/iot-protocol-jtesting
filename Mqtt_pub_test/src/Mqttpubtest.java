
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Mqttpubtest implements MqttCallback {
	MqttClient client;
	int sent = 0;
	MqttMessage message = new MqttMessage();

	public static void main(String[] args) throws InterruptedException, MqttException {
		Mqttpubtest paho = new Mqttpubtest();
		paho.loop();
	}

	public void loop() throws InterruptedException, MqttException{
		String addr = "tcp://your_ip_addr:your_port_num";
		client = new MqttClient(addr, "Sending");
		client.connect();
		while(true){
			doDemo();
			Thread.sleep(500);
		}
	}

	public void doDemo() {   
		try {
			message.setPayload((""+sent).getBytes());
			client.publish("proto/demo", message);
			System.out.println(sent + "---" + System.currentTimeMillis());
			sent++;
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
			throws Exception {		
	}


	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub

	}
}

