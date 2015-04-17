
public class AuthenticationServer {
	private ListeningSocket listen;
	
	public AuthenticationServer(int port){
		listen = new ListeningSocket(port);
	}
	
	public void start(){
		listen.start();
	}
	
	public void stop(){
		listen.interrupt();
	}
	
	public void getDetails(){
		System.out.println("Port: " + listen.getPort());
		System.out.println("Last message recieved: " + listen.getMessage());
	}
	
}
