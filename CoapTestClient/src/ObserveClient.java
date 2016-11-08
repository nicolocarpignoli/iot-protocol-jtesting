import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;

public class ObserveClient {
	
	public static void main(String args[]) {
		URI uri = null; // URI parameter of the request
		if (args.length > 0) {
			
			try {
				uri = new URI(args[0]);
			} catch (URISyntaxException e) {
				System.err.println("Invalid URI: " + e.getMessage());
				System.exit(-1);
			}
			CoapClient client = new CoapClient(uri);
			System.out.println("connected with uri: " + uri);
			CoapObserveRelation relation =    client.observe(new CoapHandler() {
				public void onLoad(CoapResponse response) {
					System.out.println(response.getResponseText() + "---" + System.currentTimeMillis());
		        }
		        public void onError() {
		        	System.err.println();
		        }
	        });			
			while(true){}
		}
	}
}
