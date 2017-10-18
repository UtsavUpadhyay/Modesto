package bean;

import java.sql.Blob;

public class Communitybean {
		String admin,commuity_name,username;
		Blob community_display;
		public String getAdmin() {
			return admin;
		}
		public void setAdmin(String admin) {
			this.admin = admin;
		}
		public String getCommuity_name() {
			return commuity_name;
		}
		public void setCommuity_name(String commuity_name) {
			this.commuity_name = commuity_name;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Blob getCommunity_display() {
			return community_display;
		}
		public void setCommunity_display(Blob community_display) {
			this.community_display = community_display;
		}
		
}
