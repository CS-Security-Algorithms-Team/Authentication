import java.util.Scanner;


public class Main {
	public static void main(String args[]){
		AuthenticationServer server;
		int lPort = 9989;
		int sPort = 9999;
		
		if(args.length > 2){
			try{
				lPort = Integer.parseInt(args[0]);
				sPort = Integer.parseInt(args[1]);
			}catch(NumberFormatException e){
				System.out.println("Usage: AuthenticationServer <Listening Port> <Sending Port>");
				System.exit(1);
			}
		}
		
		System.out.println("Authentication Server Starting \nListening: " + lPort + "\nSending: " + sPort);
		server = new AuthenticationServer(lPort, sPort);
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
