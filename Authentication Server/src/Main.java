import java.util.Scanner;


public class Main {
	public static void main(String args[]){
		AuthenticationServer server;
		int port = 8888;
		
		if(args.length > 1){
			try{
				port = Integer.parseInt(args[0]);
			}catch(NumberFormatException e){
				System.out.println("Usage: AuthenticationServer <port>");
				System.exit(1);
			}
		}
		
		System.out.println("Authentication Server Starting \nport: " + port);
		server = new AuthenticationServer(port);
		serverStart(server);
		
	}
	
	private static void serverStart(AuthenticationServer server){
		Scanner scan = new Scanner(System.in);
		String s = "";
		
		server.start();
		
		while(!s.equals("q")){
			s = scan.next();
			
			switch (s){
			case "q":
				System.out.println("Quitting");
				server.stop();
				scan.close();
				System.exit(1);
				break;
			case "d":
				server.getDetails();
				break;
			default:
				System.out.println("Command not recognized");
			}
		}
	}
}
