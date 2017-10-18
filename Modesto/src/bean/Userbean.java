package bean;



import android.graphics.Bitmap;

public class Userbean {
		String username,password,name,address,user_contact,email;
		double id;
		Bitmap display;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getUser_contact() {
			return user_contact;
		}
		public void setUser_contact(String user_contact) {
			this.user_contact = user_contact;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public double getId() {
			return id;
		}
		public void setId(double id) {
			this.id = id;
		}
		public Bitmap getDisplay() {
			return display;
		}
		public void setDisplay(Bitmap bmp) {
			this.display = bmp;
		}
		public void clearUsername() {
			this.username = "";
		}
		public void clearPassword() {
			this.password = "";
		}
		public void clearName() {
			this.name = "";
		}
		public void clearAddress() {
			this.address = "";
		}
		public void clearUser_contact() {
			this.user_contact = "";
		}
		public void clearEmail() {
			this.email = "";
		}
		
}
