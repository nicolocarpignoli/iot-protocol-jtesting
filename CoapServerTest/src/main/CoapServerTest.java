
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.EndpointManager;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class CoapServerTest extends CoapServer {

  public static void main(String[] args) throws InterruptedException {
	  CoapServerTest server = null;
      try {
          // create server
          server = new CoapServerTest();
          // add endpoints on all IP addresses
          server.addEndpoints();
          server.start();

      } catch (SocketException e) {
          System.err.println("Failed to initialize server: " + e.getMessage());
      }

  }
  
  private static final int COAP_PORT = NetworkConfig.getStandard().getInt(NetworkConfig.Keys.COAP_PORT);

  private void addEndpoints() {
  	for (InetAddress addr : EndpointManager.getEndpointManager().getNetworkInterfaces()) {
  		// only binds to IPv4 addresses and localhost
			if (addr instanceof Inet4Address || addr.isLoopbackAddress()) {
				InetSocketAddress bindToAddress = new InetSocketAddress(addr, COAP_PORT);
				addEndpoint(new CoapEndpoint(bindToAddress));
			}
		}
  }

  private ObservableResource obsRes;
  public CoapServerTest() throws SocketException { 
      // provide an instance of a Hello-World resource
      obsRes = new ObservableResource("observable-resource");
      obsRes.setObservable(true);
      obsRes.getAttributes().setObservable();
      add(obsRes);
  }
  

}