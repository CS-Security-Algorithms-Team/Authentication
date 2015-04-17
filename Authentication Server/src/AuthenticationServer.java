
public class AuthenticationServer {
	private ListeningSocket listen;
	
	public AuthenticationServer(int lPort, int sPort){
		listen = new ListeningSocket(lPort, sPort);
	}
	
	public void start(){
		listen.start();
	}
	
	public void stop(){
		listen.interrupt();
	}
	
	public void getDetails(){
		System.out.println("Listening on port: " + listen.getPortListen());
		System.out.println("Broadcasting on port: " + listen.getPortSending());
		System.out.println("Last message recieved: " + listen.getMessage());
	}
	
}
