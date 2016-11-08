import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;


public class ObservableResource extends CoapResource{
	private int var = 0;
	public ObservableResource(String name) {
		super(name);
		Timer timer=new Timer();
		timer.schedule(new UpdateTask(this),0,500);
	}

	public void handleGET(CoapExchange exchange){
		exchange.respond(ResponseCode.CONTENT, var+"", MediaTypeRegistry.TEXT_PLAIN);	}

	private class UpdateTask extends TimerTask{
		private CoapResource mCoapres;
		public UpdateTask(CoapResource coapres){
			mCoapres=coapres;
		}
		@Override
		public void run(){
			var++;
			mCoapres.changed();
			System.out.println(var + "---" + System.currentTimeMillis());
		}
	}

}
