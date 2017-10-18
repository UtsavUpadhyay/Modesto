package com.DaoOBJ;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.ArrayList;

import android.util.Log;
import bean.Userbean;

@SuppressWarnings({"unchecked","rawtypes"})
public class Daoaccess {

	ArrayList al=new ArrayList();
	ArrayList al1=new ArrayList();
	Socket client;
	ObjectOutputStream oos;
	Connection con;
	ObjectInputStream ois;
	Boolean flagLogin;
	int i;
	
//	public int insertion(Userbean l)
//	{
//		int i=0;
//	try {
//		
//		DBconnect db=new DBconnect();
//		con=db.dbCon();
//		Statement stmt=con.createStatement();
//		String sql="insert into user_table(username,password,name,contact_details,address,email,user_display,id) values ('"+l.getUsername()+"','"+l.getPassword()+"','"+l.getName()+"','"+l.getUser_contact()+"','"+l.getAddress()+"','"+l.getEmail()+"','"+l.getDisplay()+"','"+l.getId()+"')";
//		
//		String sql1="select * from userdata";
//		ResultSet rs=stmt.executeQuery(sql1);
//		boolean flag=false;
//		while(rs.next())
//			if(l.getUsername().equals(rs.getString("username")))
//				flag=true;
//		System.out.println(l.getUsername());
//		System.out.println(l.getPassword());
//			if(flag==false)
//			{
//					if(l.getUsername().isEmpty() || l.getPassword().isEmpty())
//					{	
//						System.out.println("in if");
//						System.out.println("something went wrong");
//						i=0;
//						
//					}
//					else
//					{
//						System.out.println("in else");
//						i=stmt.executeUpdate(sql);
//					}
//			}
//			else
//			{
//				System.out.println("choose another username");
//				i=2;
//			}			
//		
//		} catch (Exception e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	return i;
//	
//	}
		
	
	public int connectIP(Userbean ub,int path)
	{
		Log.d("hello", "in connectip ");
			al.add("connectIP");
			al.add(ub.getUsername());
			al.add(ub.getPassword());
			al.add(ub.getName());
			al.add(ub.getEmail());
			al.add(ub.getUser_contact());
			al.add(ub.getAddress());
			if(ub.getDisplay()!=null)
			al.add(ub.getDisplay());
			try {
				if(path==1)
				{
				client=new Socket("192.168.1.105", 56253);
				Log.d("hello", "in doin back "+al);
				Log.d("hello", "in doin back "+client.getPort()+"  "+client.isConnected()+"  "+client.getInetAddress());
				if(client!=null && al!=null)
				{
					oos = new ObjectOutputStream(client.getOutputStream());
					oos.writeObject(al);
					ois=new ObjectInputStream(client.getInputStream());
					Log.d("hello", "after taking input stream "+al);
					i=(Integer) ois.readObject();
					Log.d("hello", "in connect restaurant "+i);
				}
				ois.close();
				oos.close();
				client.close();
				}
				if(path==0)
				{
				client=new Socket("192.168.1.105", 56272);
				Log.d("hello", "in doin back "+al);
				Log.d("hello", "in doin back "+client.getPort()+"  "+client.isConnected()+"  "+client.getInetAddress());
				if(client!=null && al!=null)
				{
					oos = new ObjectOutputStream(client.getOutputStream());
					oos.writeObject(al);
					ois=new ObjectInputStream(client.getInputStream());
					Log.d("hello", "after taking input stream "+al);
					i=(Integer) ois.readObject();
					Log.d("hello", "in connect restaurant "+i);
				}
				ois.close();
				oos.close();
				client.close();
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				
				Log.d("unknown host", "host not found");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("server error", "server not exist");
			
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			return i;
		}

	
	public ArrayList connectRestaurant(String msg,String cat) {
		try {
			String category=cat;
			client=new Socket("192.168.1.105", 56253);
			if(client!=null)
			Log.d("hello", "in connect restaurant "+client.getPort()+"  "+client.getInetAddress());
			al.add("connectRestaurant");
			al.add(category);
			al.add(msg);
			
			Log.d("hello", "client connected "+al);
			if(client!=null && msg!=null)
			{
				Log.d("hello", "in if "+al);
				oos = new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(al);
				Log.d("hello", "after writing object "+al);
				
				ois=new ObjectInputStream(client.getInputStream());
				Log.d("hello", "after taking input stream "+al);
				
//					if(ois.readObject().toString().isEmpty())
//					{
//						Log.d("hello", "error in reading "+al1);
//					}
//					else if(ois.readObject()!=null)
//					{
						al1=(ArrayList) ois.readObject();
						Log.d("hello", "in connect restaurant "+al1);
					
//					}
					al.clear();
					ois.close();
					oos.close();
					client.close();
					
				
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			
			Log.d("unknown host", "host not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("server error", "server not exist");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return al1;
		
		// TODO Auto-generated method stub
		
	}

	public void sendRating(ArrayList snd) {
		// TODO Auto-generated method stub		
		try {
			client=new Socket("192.168.1.105", 56254);
			Log.d("hello", "in doin back "+snd);
			Log.d("hello", "in doin back "+client.getPort()+"  "+client.isConnected()+"  "+client.getInetAddress());
			if(client!=null && snd!=null)
			{
				Log.d("hello", "In snd Rating method : "+snd);
				oos = new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(snd);
				Log.d("hello", "object written : "+snd);
			}
			oos.close();
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			
			Log.d("unknown host", "host not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("server error", "server not exist");
		}
	}
	public void sendComRevw(ArrayList comsnd) {
		// TODO Auto-generated method stub		
		try {
			client=new Socket("192.168.1.105", 56281);
			Log.d("hello", "in doin back "+comsnd);
			Log.d("hello", "in doin back "+client.getPort()+"  "+client.isConnected()+"  "+client.getInetAddress());
			if(client!=null && comsnd!=null)
			{
				Log.d("hello", "In snd Rating method : "+comsnd);
				oos = new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(comsnd);
				Log.d("hello", "object written : "+comsnd);
			}
			oos.close();
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			
			Log.d("unknown host", "host not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("server error", "server not exist");
		}
	}

	public ArrayList<String> getChatPlaces() {
		// TODO Auto-generated method stub		
		ArrayList<String> chat=new ArrayList<String>();
		try {
			
			client=new Socket("192.168.1.105", 56250);
			Log.d("hello", "chat :  "+client.getPort()+"  "+client.isConnected()+"  "+client.getInetAddress());
			if(client!=null)
			{
				
				Log.d("hello", "In chat method : ");
				ois = new ObjectInputStream(client.getInputStream());
				chat=(ArrayList<String>) ois.readObject();
				Log.d("hello", "object written : "+chat);
			}
			ois.close();
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			
			Log.d("unknown host", "host not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("server error", "server not exist");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chat;
	}

	
	public Boolean chkLogin(String string, String string2) {
		// TODO Auto-generated method stub
		try {
		client=new Socket("192.168.1.105", 56255);
		Log.d("hello", "in doin back "+string+"     "+string2);
		Log.d("hello", "in doin back "+client.getPort()+"  "+client.isConnected()+"  "+client.getInetAddress());
		if(client!=null)
		{
			
			al.add(string);
			al.add(string2);
			Log.d("hello", "In snd chklogin method : ");
			oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(al);
			ois=new ObjectInputStream(client.getInputStream());
			Log.d("hello", "after taking input stream "+al);
			try {
				flagLogin=(Boolean) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.d("hello", "in chklogin "+flagLogin);
		}
		al.clear();
		oos.close();
		client.close();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		
		Log.d("unknown host", "host not found");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Log.d("server error", "server not exist");
	}
		return flagLogin;
	}


	public ArrayList<ArrayList<String>> getReviewCommentData(String str) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> al_user_data=new ArrayList<ArrayList<String>>();
		try {
			al_user_data.clear();
			client=new Socket("192.168.1.105", 56261);
			Log.d("hello", "in review comment ");
			Log.d("hello", "in review comment "+client.getPort()+"  "+client.isConnected()+"  "+client.getInetAddress());
			if(client!=null)
			{
				Log.d("hello", "In get review comm. method : ");
				oos = new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(str);
				ois=new ObjectInputStream(client.getInputStream());
				Log.d("hello", "after taking input stream ");
				try {
					al_user_data=(ArrayList<ArrayList<String>>) ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d("hello", "in chklogin "+al_user_data);
			}
			ois.close();
			oos.close();
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			
			Log.d("unknown host", "host not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("server error", "server not exist");
		}
			return al_user_data;
		
	}


	public ArrayList<String> getUserData(String struser) {
		// TODO Auto-generated method stub
		ArrayList<String> al_userdata=new ArrayList<String>();
		try {
			al_userdata.clear();
			client=new Socket("192.168.1.105", 56262);
			Log.d("hello", "in userdata method ");
			Log.d("hello", "in userdata method "+client.getPort()+"  "+client.isConnected()+"  "+client.getInetAddress());
			if(client!=null)
			{
				Log.d("hello", "In get userdata method : ");
				oos = new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(struser);
				ois=new ObjectInputStream(client.getInputStream());
				Log.d("hello", "after taking input stream ");
				try {
					al_userdata=(ArrayList<String>) ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d("hello", "in chklogin "+al_userdata);
			}
			ois.close();
			oos.close();
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			
			Log.d("unknown host", "host not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("server error", "server not exist");
		}
		
		return al_userdata;
	}
}