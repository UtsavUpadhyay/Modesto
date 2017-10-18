package com.ServerStart;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.DaoController.DaoObj;

@SuppressWarnings({ "rawtypes", "resource" })
public class ServerProg {
	
	
	public static void main(String args[]) throws IOException
	{

		int port=56253,i=3;
//		int port1=56251;
		DaoObj dao=new DaoObj();
		ServerSocket ss = null;
		Socket client;
		
		ArrayList al=new ArrayList();
		ArrayList al_place=new ArrayList();
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
	        	try{
	        		dao.startup();
	        	dao.insertionPlace();
	        	dao.insertionRestaurant();
	        	dao.insertionDining();
	        	dao.insertionTheatre();
	        	System.out.println("waiting for connection");
//	        	client1 = ss1.accept();
	            // Create the Client Socket
	        	client = ss.accept();
	        	
	            System.out.println("Socket Established...");
	            
	            
	            if(client!=null)
	            {
	            	ObjectInputStream ois=new ObjectInputStream(client.getInputStream());
	            	al=(ArrayList)ois.readObject();
	            
	            if(al!=null)
	            {
	            	if(al.get(0).equals("connectIP"))
	            	{	
	            		i=dao.insertion(al);
	            		if(i!=3)
	            		{
            			try{
    	            	ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
    	            	oos.writeObject(i);
    	            	System.out.println("object written" + i);
    	            	i=3;
    	            	oos.close();
            			}catch(IOException e)
            			{
            				e.printStackTrace();
            			}
	            		}
	            	}
	            	else if(al.get(0).equals("connectRestaurant"))
	            	{
	            		al_place=dao.getPlace(al);
	            		if(al_place!=null)
	    	            {
	            			try{
	    	            	ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
	    	            	oos.writeObject(al_place);
	    	            	System.out.println("object written" + al_place);
	    	            	al_place.clear();
	    	            	oos.close();
	            			}catch(IOException e)
	            			{
	            				e.printStackTrace();
	            			}
	    	            	
	    	            }
	            		else
	            		{
	            			System.out.println("cannot write here");
	            		}
	            	}
	            }
	            
	            ois.close();
	            al.clear();
	            client.close();
	            }
	            
	            
	        }
	        catch (Exception e) {
				// TODO: handle exception
	        	e.printStackTrace();
	        	System.out.println("exception occured");
			}
	}
}
}
