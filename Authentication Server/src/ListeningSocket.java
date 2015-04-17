import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.SimpleDateFormat;


public class ListeningSocket extends Thread implements Runnable {

	private ServerSocket server;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String message;
	private int lPort;
	private int sPort;
	
	public ListeningSocket(int l, int s){
		message = "NaN";
		lPort = l;
		sPort = s;
		/*
		try {
			server = new ServerSocket(4848);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	@Override
	public void run() {
		while(!this.isInterrupted()){
			try {
				//Server is initialized in the while loop to avoid a JVM bind
				//after several iterations of the loop over the same socket. 
				//[Note*] Use higher ports since lower ports seem to bind more frequently.
				server = new ServerSocket(lPort);
				//
				System.out.println("Waiting for connection..");
				socket = server.accept();
				//
				in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				message = in.readUTF();
				System.out.println("Message recieved: " + message + " from " + socket.getInetAddress());
				socket.close();
				
				socket = new Socket("127.0.0.1", sPort);
				out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF(generateToken());
				out.flush();
				socket.close();
				server.close();
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
				break;
			}
		}
		
	}
	
	
	public int getPortListen(){
		return lPort;
	}
	
	public int getPortSending(){
		return sPort;
	}
	
	public String getMessage(){
		return message;
	}
	
	private String generateToken(){
		//generates random 32 character string
		SecureRandom random = new SecureRandom();
		String s = new BigInteger(130, random).toString(32);
		
		//appends timestamp
		SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
		Date cDate = new Date(System.currentTimeMillis());
		String date = format.format(cDate);
		System.out.println("Timestamp: " + date.toString());
		//Token is appended to the front to make it readable by the gateway 
		s = "TOKEN|" + s + "|" + date + "|";
		
		return encrypt(s);
	}
	
	private String encrypt(String s){
		//Not sure if we need to actually encrypt the token 
		//I'll leave this empty method here just in case I need to add it
		return s;
	}

}
