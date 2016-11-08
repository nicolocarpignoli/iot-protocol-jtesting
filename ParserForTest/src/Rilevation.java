
public class Rilevation {
	private long time;
	private int payload;
	
	public Rilevation(int pay, long timing){
		payload = pay;
		time = timing;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getPayload() {
		return payload;
	}
	public void setPayload(int payload) {
		this.payload = payload;
	}
}
