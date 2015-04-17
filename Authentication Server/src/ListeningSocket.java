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
	private int port;
	
	public ListeningSocket(int p){
		message = "NaN";
		port = p;
		
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		try{
			while(!this.isInterrupted()){
				System.out.println("Waiting for connection..");
				socket = server.accept();
				
				in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				message = in.readUTF();
				System.out.println("Message recieved: " + message + " from " + socket.getInetAddress());

				out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF(generateToken());
				out.flush();
				socket.close();
			}
			server.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}	
	}
	
	
	public int getPort(){
		return port;
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
