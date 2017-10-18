package com.DaoController;

import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbconnection.DBconnect;


public class DaoObj {

	Connection con;
	DBconnect db=new DBconnect();
	ArrayList ar=new ArrayList();
	String category,place_name;
	@SuppressWarnings({ "rawtypes", "unused"})
	public int insertion(ArrayList al)
	{
		boolean flag=false;
		String username,password,name,contact_details,address,email;
		Image user_display;
		int i=3;
	try {
		
		
		con=db.dbCon();
		Statement stmt=con.createStatement();
		
		username=(String)al.get(1);
		password=(String)al.get(2);
		name=(String)al.get(3);
		email=(String)al.get(4);
		contact_details=(String)al.get(5);
		address=(String)al.get(6);
			
		
		String sql="insert into user_table(username,password,name,contact_details,address,email) values ('"+username+"','"+password+"','"+name+"','"+contact_details+"','"+address+"','"+email+"')";
		
		String sql1="select * from user_table";
		ResultSet rs=stmt.executeQuery(sql1);
		
		while(rs.next())
			if((al.get(1).toString()).equals(rs.getString("username")))
				flag=true;
		System.out.println(flag);
		System.out.println(al.get(1));
		System.out.println(al.get(2));
			if(flag==false)
			{
					if(username.isEmpty() || password.isEmpty())
					{	
						System.out.println("in if");
						System.out.println("something went wrong");
						i=0;
						
					}
					else
					{
						System.out.println("in else");
						i=stmt.executeUpdate(sql);
					}
			}
			else
			{
				System.out.println("choose another username");
				i=2;
			}			
		
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return i;
	
	}
	
	public void insertionPlace()
	{
		boolean flag=true;
		boolean flag1=true;
		int i = 0,j = 0,k = 0,l = 0,m = 0,n = 0,o = 0;
		String place_name,address,category,contact_rest,msg = "";
		int cost_person;
		if(flag)
		{
	try {
		con=db.dbCon();
		Statement stmt=con.createStatement();
		String sql1="select * from hangout_details";
		ResultSet rs=stmt.executeQuery(sql1);
		place_name="Zen Cafe";
		address="Address: at the Gufa, University Road, near CEPT, Ahmedabad, Gujarat 380009";
		category="Hangout Place";
		contact_rest="07965459298";
		cost_person=200;
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		String sql="insert into hangout_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
		i=stmt.executeUpdate(sql);
		flag1=true;
		}
		place_name="Shambhoo";
		address="Address: Shop No. 1, 2 Devangan Apartments, H.L. Commerce Road, Navrangpura, Ahmedabad";
		category="Hangout Place";
		contact_rest="07965101111";
		cost_person=100;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into hangout_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			j=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="JAVA+";
		address="Address: Ramdev Nagar , Satellite Road, Courtyard, Ahmedabad, Gujarat 380015";
		category="Hangout Place";
		contact_rest="07966185000";
		cost_person=150;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into hangout_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			k=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="CafeLevel";
		address="Address: Opposite S K Farm, Behind Rajpath Club, S G Highway, Bodakdev, Ahmedabad";
		category="Hangout Place";
		contact_rest="+919898906143";
		cost_person=100;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into hangout_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			l=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="Project Cafe";
		address="Address: Yellow Bunglow No. 7, Gujarat Siddharth Society,, Dr Vikram Sarabhai Marg, Gulbai Tekra, Ahmedabad, Gujarat 380009";
		category="Hangout Place";
		contact_rest="07940192287";
		cost_person=350;
		
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;	
		sql="insert into hangout_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			m=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="Jaggernaut Arena";
		address="Address: 203, Sindhu Bhavan Marg, PRL Colony, Thaltej, Ahmedabad, Gujarat 380054";
		category="Hangout Place";
		contact_rest="07698004941";
		cost_person=300;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into hangout_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			n=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="Sphere Lounge";
		address="Address :Prahlad Nagar, Hotel Ramada, Corporate Road, Opposite Prahlad Nagar Garden, Prahlad Nagar, Ahmedabad";
		category="Hangout Place";
		contact_rest="+919033461923";
		cost_person=500;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into hangout_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			o=stmt.executeUpdate(sql);
			flag1=true;
		}
		flag=true;
		if(i==1 && j==1 && k==1 && l==1 && m==1 && n==1 && o==1 )
			msg="successfully updated every row";
		
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	System.out.println("here we go. "+msg);
	
	}
	
	public void insertionRestaurant()
	{
		boolean flag=true;
		boolean flag1=true;
		int i = 0,j = 0,k = 0,l = 0,m = 0,n = 0,o = 0;
		String place_name,address,category,contact_rest,msg = "";
		int cost_person;
		if(flag)
		{
	try {
		con=db.dbCon();
		Statement stmt=con.createStatement();
		String sql1="select * from restaurant_details";
		ResultSet rs=stmt.executeQuery(sql1);
		place_name="Autograph - Armoise Hotel";
		address="Armoise Hotel, Opposite Nirman Bhavan, Behind Navarangpura Bus Stand, Off C G Road, Ahmedabad";
		category="Restaurant";
		contact_rest="07930641900";
		cost_person=400;
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		String sql="insert into restaurant_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
		i=stmt.executeUpdate(sql);
		flag1=true;
		}
		place_name="Kabir Restaurant";
		address="JB Tower, Opposite Doordarshan Kendra, Drive In Road, Gurukul, Ahmedabad";
		category="Restaurant";
		contact_rest="07926852786";
		cost_person=300;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into restaurant_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			j=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="Cellad Eatery";
		address="4th Floor, Gulmohar Park, Satellite, AhmedabadAddress: Ramdev Nagar , Satellite Road, Courtyard, Ahmedabad, Gujarat 380015";
		category="Restaurant";
		contact_rest="+919974405858";
		cost_person=350;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into restaurant_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			k=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="Atithi Restaurant";
		address="Mohini, Near Shraddha Petrol Pump, Judges Bungalow Road, Bodakdev, Ahmedabad";
		category="Restaurant";
		contact_rest="07926858806";
		cost_person=250;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into restaurant_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			l=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="Alimentos";
		address="1st Floor, Sheetal Varsha Arcade, Near Girish Cold Drinks, Navrangpura, Ahmedabad 380009";
		category="Restaurant";
		contact_rest="07965222440";
		cost_person=375;
		
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;	
		sql="insert into restaurant_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			m=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="Bawarchi Restaurant";
		address="Dwarkesh Complex, Near Hotel Klassic Gold, Off C G Road, Navrangpura, Ahmedabad";
		category="Restaurant";
		contact_rest="07926565970";
		cost_person=450;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into restaurant_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			n=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="Saffron Restaurant";
		address="Sankalp Square, Drive In Road, Gurukul, Ahmedabad";
		category="Restaurant";
		contact_rest="07927462999";
		cost_person=400;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into restaurant_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			o=stmt.executeUpdate(sql);
			flag1=true;
		}
		flag=true;
		if(i==1 && j==1 && k==1 && l==1 && m==1 && n==1 && o==1 )
			msg="successfully updated every row";
		
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	System.out.println("here we go. "+msg);
	
	}
	
	public void insertionDining()
	{
		boolean flag=true;
		boolean flag1=true;
		int i = 0,j = 0,k = 0,l = 0,m = 0,n = 0,o = 0;
		String place_name,address,category,contact_rest,msg = "";
		int cost_person;
		if(flag)
		{
	try {
		con=db.dbCon();
		Statement stmt=con.createStatement();
		String sql1="select * from dining_details";
		ResultSet rs=stmt.executeQuery(sql1);
		place_name="Souq Bistro & Grills";
		address="Ground Floor, Acropolis Mall, S G Highway, Thaltej, Ahmedabad";
		category="Dining";
		contact_rest="07965101033";
		cost_person=750;
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		String sql="insert into dining_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
		i=stmt.executeUpdate(sql);
		flag1=true;
		}
		place_name="Rajwadu";
		address="Near Jivraj Tolnaka, Ambaji Temple, Malav Talav, Vejalpur, Ahmedabad";
		category="Dining";
		contact_rest="07926643839";
		cost_person=550;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into dining_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			j=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="70 Degrees East";
		address="E-201, Second Floor, Ozone Desire, 100ft, Hebatpur, Adjacent Columbia Asia Hospital, Hebatpur Road, Thaltej, Ahmedabad";
		category="Dining";
		contact_rest="+919913433397";
		cost_person=400;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into dining_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			k=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="Nini Kitchen";
		address="12, First Floor, Camps Corner 2, Opposite Prahlad Nagar Garden, Prahlad Nagar, Ahmedabad 380015";
		category="Dining";
		contact_rest="07930641734";
		cost_person=325;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into dining_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			l=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="650-The Global Kitchen";
		address="Shreekunj Mandapam, Beside Golden Tulip Bunglows & Tulip Citadel, Manekbaug, Ambavadi, Ahmedabad";
		category="Dining";
		contact_rest="+919824090111";
		cost_person=400;
		
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;	
		sql="insert into dining_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			m=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="Barbeque Nation";
		address="3, Shivalik III, Near Mile Stone Complex, Drive In Road, Gurukul, Ahmedabad";
		category="Dining";
		contact_rest="07930641647";
		cost_person=375;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into dining_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			n=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="Patang";
		address="Chinubhai Tower, Nehru Bridge Corner, Ashram Road, Ahmedabad";
		category="Dining";
		contact_rest="07926586200";
		cost_person=400;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into dining_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			o=stmt.executeUpdate(sql);
			flag1=true;
		}
		flag=true;
		if(i==1 && j==1 && k==1 && l==1 && m==1 && n==1 && o==1 )
			msg="successfully updated every row";
		
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	System.out.println("here we go. "+msg);
	
	}
	
	public void insertionTheatre()
	{
		boolean flag=true;
		boolean flag1=true;
		int i = 0,j = 0,k = 0,l = 0,m = 0,n = 0,o = 0;
		String place_name,address,category,contact_rest,msg = "";
		int cost_person;
		if(flag)
		{
	try {
		con=db.dbCon();
		Statement stmt=con.createStatement();
		String sql1="select * from theatre_details";
		ResultSet rs=stmt.executeQuery(sql1);
		place_name="CITYGOLD";
		address="Address: Ellis Bridge, Ashram Road, Ahmedabad, Gujarat 380009, India";
		category="Theatres";
		contact_rest="07926587780";
		cost_person=100;
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		String sql="insert into theatre_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
		i=stmt.executeUpdate(sql);
		flag1=true;
		}
		place_name="PVR CINEMAS";
		address="PVR Acropolis Mall, Thaltej Cross Road, Near Gurdwara, Ahmedabad";
		category="Theatres";
		contact_rest="+919601895182";
		cost_person=180;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into theatre_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			j=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="WIDE ANGLE";
		address="Satellite Crossing, Sarkhej - Gandhinagar Highway, Ahmedabad, Gujarat 380015, India";
		category="Theatres";
		contact_rest="+917926929721";
		cost_person=100;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into theatre_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			k=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="MUKTA ARTS";
		address="4th Floor,Gulmohar Park Mall, Ramdevnagar Satellite Road, Ahmedabad, Gujarat 380015, India";
		category="Theatres";
		contact_rest="07930464500";
		cost_person=90;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into theatre_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			l=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="CINEMAX";
		address="Red Carpet Building, Sola, Ahmedabad - 380060, Opposite High Court";
		category="Theatres";
		contact_rest="+917965495182";
		cost_person=100;
		
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;	
		sql="insert into theatre_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			m=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="BIG CINEMAS";
		address="511, 3rd Floor, Himalaya Mall, Drive In Road, Ahmedabad - 380052, Near Indraprastha Tower";
		category="Theatres";
		contact_rest="+917933014218";
		cost_person=150;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into theatre_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			n=stmt.executeUpdate(sql);
			flag1=true;
		}
		place_name="DRIVE IN";
		address="Drive In Road, Ahmedabad - 380052, Opposite Doordarsan Kendra";
		category="Theatres";
		contact_rest="+917927454600";
		cost_person=50;
		rs=stmt.executeQuery(sql1);
		while(rs.next())
			if(place_name.equals(rs.getString("place_name")))
				flag1=false;
		sql="insert into theatre_details(place_name,address,category,contact_rest,cost_person) values ('"+place_name+"','"+address+"','"+category+"','"+contact_rest+"','"+cost_person+"')";
		if(flag1)
		{
			o=stmt.executeUpdate(sql);
			flag1=true;
		}
		flag=true;
		if(i==1 && j==1 && k==1 && l==1 && m==1 && n==1 && o==1 )
			msg="successfully updated every row";
		
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	System.out.println("here we go. "+msg);
	
	}
	
	public ArrayList getPlace(ArrayList al) {
		// TODO Auto-generated method stub
		category=(String) al.get(1);					//Restaurant
		place_name=(String) al.get(2);					//place_name
		con=db.dbCon();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String sql="select * from "+category+"_details where place_name='"+place_name+"'";
				System.out.println("place is "+place_name);
				ResultSet rs=stmt.executeQuery(sql);
				if(rs.next())
				{
				ar.add(rs.getString(1));
				ar.add(rs.getString(2));
				ar.add(rs.getString(3));
				ar.add(rs.getInt(4));
				ar.add(rs.getString(5));
				}
			
			System.out.println("array values are "+ ar);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error in connect Restaurant");
		}
		
		return ar;
	}

	public void setRating(ArrayList al) {
		// TODO Auto-generated method stub
		category=(String) al.get(2);					//Restaurant
		Float rating=(Float) al.get(1);
		place_name=(String) al.get(0);
		con=db.dbCon();
		Statement stmt;
			try {
				stmt = con.createStatement();
				String sql="insert into ratings(ratings,place_name,category) values ('"+rating+"','"+place_name+"','"+category+"')";
				int i=stmt.executeUpdate(sql);
				System.out.println("updated rows"+i);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public ArrayList<String> sendChatPlaces() {
		// TODO Auto-generated method stub
		con=db.dbCon();
		Statement stmt;
		ArrayList<String> chat=new ArrayList<String>();
			try {
				stmt = con.createStatement();
				String sql="select place_name from places";
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next())
					chat.add(rs.getString("place_name").toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return chat;
	}
	
	public Boolean getLogin(ArrayList al) {
		// TODO Auto-generated method stub
		String uName,uPass;
		uName=(String) al.get(0);
		uPass=(String) al.get(1);
		System.out.println("strings are : "+uName +"    "+uPass);
		boolean flag=false;
		try{
			con=db.dbCon();
			Statement stmt=con.createStatement();
			String sql="select username,password from user_table where username='"+uName+"'";
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next())
				if(rs.getString("password").equals(uPass))
					flag=true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}

	public void setComment(ArrayList al) {
		// TODO Auto-generated method stub
//		0 place 1 name 2 review 3 measure 4 comment 5 type 6 ratings
		place_name=(String) al.get(0);
		category=(String) al.get(5);	//Restaurant
		String username=(String) al.get(1);
		Float rating=0.0f;
		String review=(String) al.get(2);
		String comment=(String) al.get(4);
		if(al.size()>6){
		rating=(Float) al.get(6);
		}
		con=db.dbCon();
		Statement stmt;
			try {
				stmt = con.createStatement();
				String sql="insert into comments(comments,place_name,category,username) values ('"+comment+"','"+place_name+"','"+category+"','"+username+"')";
				String sql1="insert into reviews(reviews,place_name,category,username) values ('"+review+"','"+place_name+"','"+category+"','"+username+"')";
				int i=stmt.executeUpdate(sql);
				int j=stmt.executeUpdate(sql1);
				if(al.get(3).toString().equals("comment"))
				{
					String sql2="insert into "+category+"_review(user_reviews,place_name,user_comments,username,user_ratings) values ('"+review+"','"+place_name+"','"+comment+"','"+username+"','"+rating+"')";
					int k=stmt.executeUpdate(sql2);
					System.out.println("updated rows"+k);
				}
				System.out.println("updated rows"+i +"   "+j);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void setCommReview(ArrayList al) {
		// TODO Auto-generated method stub
//		0 place 1 name 2 review 3 measure 4 comment 5 type 6 ratings
		place_name=(String) al.get(1);
		
		String username=(String) al.get(0);
		
		String review=(String) al.get(2);
		
		
		con=db.dbCon();
		Statement stmt;
			try {
				stmt = con.createStatement();
				
				String sql1="insert into community_review(community_reviews,place_name,username) values ('"+review+"','"+place_name+"','"+username+"')";
				
				int j=stmt.executeUpdate(sql1);
				
				System.out.println("updated rows   "+j);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void startup(){
		Statement stmt;
		boolean flag=true;
		boolean flag1=true;
		if(flag)
		{
		try {
			con=db.dbCon();
			stmt=con.createStatement();
			String sql2="select place_name from places";
			ResultSet rs1=stmt.executeQuery(sql2);
			
			String sql="select place_name from ratings";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				System.out.println("inside loop");
				while(rs1.next())
				{
					System.out.println("inside second one");
					if(rs1.getString("place_name").equals(rs.getString("place_name")))
						flag1=false;
				}
				String sql1="insert into places(place_name) values ('"+rs.getString("place_name")+"')";
				if(flag1)
				{
				int i=stmt.executeUpdate(sql1);
				System.out.println("Rows affected "+i);
				
				}
				flag1=true;
			}
			
			sql="select place_name,address from restaurant_details";
			rs=con.createStatement().executeQuery(sql);
			rs1=con.createStatement().executeQuery(sql2);
			while(rs.next())
			{
				
				while(rs1.next())
				{
					
					if(rs1.getString("place_name").equals(rs.getString("place_name")))
						flag1=false;
				}
				String sql1="insert into places(place_name,place_address) values ('"+rs.getString("place_name")+"','"+rs.getString("address")+"')";
				if(flag1)
				{
				int i=con.createStatement().executeUpdate(sql1);
				System.out.println("Rows affected "+i);
				}
				flag1=true;
			}
			sql="select place_name,address from theatre_details";
			rs=con.createStatement().executeQuery(sql);
			rs1=con.createStatement().executeQuery(sql2);
			while(rs.next())
			{
				while(rs1.next())
					if(rs1.getString("place_name").equals(rs.getString("place_name")))
						flag1=false;
				String sql1="insert into places(place_name,place_address) values ('"+rs.getString("place_name")+"','"+rs.getString("address")+"')";
				if(flag1)
				{
				int i=con.createStatement().executeUpdate(sql1);
				System.out.println("Rows affected "+i);
				
				}
				flag1=true;
			}
			sql="select place_name,address from dining_details";
			rs=con.createStatement().executeQuery(sql);
			rs1=con.createStatement().executeQuery(sql2);
			while(rs.next())
			{
				while(rs1.next())
					if(rs1.getString("place_name").equals(rs.getString("place_name")))
						flag1=false;
				String sql1="insert into places(place_name,place_address) values ('"+rs.getString("place_name")+"','"+rs.getString("address")+"')";
				if(flag1)
				{
				int i=con.createStatement().executeUpdate(sql1);
				System.out.println("Rows affected "+i);
				
				}
				flag1=true;
			}
			sql="select place_name,address from hangout_details";
			rs=con.createStatement().executeQuery(sql);
			rs1=con.createStatement().executeQuery(sql2);
			while(rs.next())
			{
				while(rs1.next())
					if(rs1.getString("place_name").equals(rs.getString("place_name")))
						flag1=false;
				String sql1="insert into places(place_name,place_address) values ('"+rs.getString("place_name")+"','"+rs.getString("address")+"')";
				if(flag1)
				{
				int i=con.createStatement().executeUpdate(sql1);
				System.out.println("Rows affected "+i);
				
				}
				flag1=true;
			}
			flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		}
		
	}

	public ArrayList<ArrayList<String>> getReviewComment(String username) {
		// TODO Auto-generated method stub
		ArrayList<String> uReview=new ArrayList<String>();
		ArrayList<String> uComment=new ArrayList<String>();
		ArrayList<ArrayList<String>> al_user_data=new ArrayList<ArrayList<String>>();
		try{
			uReview.clear();
			uComment.clear();
			al_user_data.clear();
			con=db.dbCon();
			Statement stmt=con.createStatement();
			Statement stmt1=con.createStatement();
			String sql="SELECT reviews,place_name FROM reviews WHERE username='"+username+"'";
			String sql1="SELECT comments,place_name FROM comments WHERE username='"+username+"'";
			ResultSet rsrev=stmt.executeQuery(sql);
			ResultSet rscom1=stmt1.executeQuery(sql1);
			while(rsrev.next())
			{
				uReview.add(rsrev.getString("place_name") +" : "+ rsrev.getString("reviews"));
			}
			while(rscom1.next())
			{
				uComment.add(rscom1.getString("place_name") +" : "+ rscom1.getString("comments"));
			}
			if(uReview!=null || uComment!=null)
			{
				al_user_data.add(uReview);
				al_user_data.add(uComment);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return al_user_data;
		}

	public ArrayList<String> getUserData(String username) {
		// TODO Auto-generated method stub
		con=db.dbCon();
		ArrayList<String> al_userdata=new ArrayList<String>();
		
		try {
			Statement stmt=con.createStatement();
			String sql="SELECT * FROM user_table WHERE username='"+username+"'";
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				al_userdata.add(rs.getString(1));
				al_userdata.add(rs.getString(2));
				al_userdata.add(rs.getString(3));
				al_userdata.add(rs.getString(4));
				al_userdata.add(rs.getString(5));
				al_userdata.add(rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al_userdata;
	}

	public int updateInsertion(ArrayList al) {
		boolean flag=false;
		String username,password,name,contact_details,address,email;
		Image user_display;
		int i=3;
	try {
		
		
		con=db.dbCon();
		Statement stmt=con.createStatement();
		
		username=(String)al.get(1);
		password=(String)al.get(2);
		name=(String)al.get(3);
		email=(String)al.get(4);
		contact_details=(String)al.get(5);
		address=(String)al.get(6);
			
		
		String sql="update user_table set password='"+password+"',name='"+name+"',contact_details='"+contact_details+"',address='"+address+"',email='"+email+"' where username='"+username+"'";
		
		System.out.println(flag);
		System.out.println(al.get(1));
		System.out.println(al.get(2));
			if(username.isEmpty() || password.isEmpty())
					{	
						System.out.println("in if");
						System.out.println("something went wrong");
						i=0;
						
					}
					else
					{
						System.out.println("in else");
						i=stmt.executeUpdate(sql);
					}
			
						
		
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	al.clear();
	return i;
	
	
	}
}