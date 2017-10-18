package com.ServerStart;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.DaoController.DaoObj;

@SuppressWarnings({ "resource" })
public class ServerPlace {
	
	
	public static void main(String args[]) throws IOException
	{

		int port=56250;
//		int port1=56251;
		DaoObj dao=new DaoObj();
		ServerSocket ss = null;
		Socket client;
		
		ArrayList<String> al=new ArrayList<String>();
		InetAddress ip=InetAddress.getLocalHost();
		
	 try {
//		 	ss1 = new ServerSocket(port1,10,ip);
	        ss = new ServerSocket(port,10,ip);
	        
	 }catch(IOException io){
		System.out.println("cannot listen on this port");
	 }
	 	if(ss!=null)
	 		System.out.println("server active on "+ss.getLocalPort()+" ip is "+ss.getInetAddress()+" again ip is "+ip.getHostName());
//	 	if(ss1!=null)
//	 	System.out.println("server active on "+ss1.getLocalPort()+" ip is "+ss1.getInetAddress()+" again ip is "+ip.getHostName());
	        while (true) {    
	        	
	        	System.out.println("waiting for connection");
//	        	client1 = ss1.accept();
	            // Create the Client Socket
	        	client = ss.accept();
	        	
	            System.out.println("Socket Established...");
	            System.out.println("object read first" + al);
	            if(client!=null)
	            {
	            	ObjectOutputStream ois=new ObjectOutputStream(client.getOutputStream());
	            	System.out.println("object after call read" + al);
	            	al=dao.sendChatPlaces();
					ois.writeObject(al);
					System.out.println("object sent" + al);
	                al.clear();
	    	        ois.close();
	            	}
	           client.close();
	    }
	}
}
