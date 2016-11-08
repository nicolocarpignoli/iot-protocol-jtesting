import java.io.IOException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;


@WebSocket
public class MyWebSocketHandler {
	private long sentTime = 0;
    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        //System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        //System.out.println("Connect: " + session.getRemoteAddress().getAddress());
        try {
            sentTime = System.currentTimeMillis();
            session.getRemote().sendString(""+sentTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
    	sentTime = System.currentTimeMillis() - Long.parseLong(message);
        System.out.println("delta: " + sentTime);
    }
}