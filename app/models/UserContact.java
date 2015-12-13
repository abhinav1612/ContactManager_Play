package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class UserContact extends Model {

	@Required
	public String contact_name;

	public String dob_dd_mm_yy;

	public String notification_period;
	public String notification_interval;

	@ManyToOne
	public User owner;

	public UserContact(String contact_name, String dob_dd_mm_yy,
			String notification_period, String notification_interval, User owner) {
		super();
		this.contact_name = contact_name;
		this.dob_dd_mm_yy = dob_dd_mm_yy;
		this.notification_period = notification_period;
		this.notification_interval = notification_interval;
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "UserContact [contact_name=" + contact_name + ", owner=" + owner
				+ "]";
	}

	public boolean processInput(String dob, String interval) {

		boolean flag = true;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
		Date convertedDate = null, notifyDate = null;
		try {
			convertedDate = (Date) formatter.parse(dob);
			notifyDate = new Date(convertedDate.getTime()
					- (1000 * 60 * 60 * 24));
		} catch (Exception ex) {
			System.out.println("Exception while converating Date : " + ex);
			flag=false;
		} 

		System.out.println(notifyDate + " :: " + dob + " :: " + convertedDate);
		// take only dd and mm from notifyDate

		int intrval = 24 - Integer.parseInt(interval);
		String intvl = intrval <= 9 ? "0" + intrval : String.valueOf(intrval);
		String dd = String.valueOf(notifyDate.getDate());
		String mm = String.valueOf(notifyDate.getMonth() + 1);

		String finalDay = dd + mm + intvl;

		System.out.println(finalDay + " :: " + dd + " :: " + mm + "::" + intvl);
		this.notification_interval=interval;
		this.dob_dd_mm_yy=dob;
		this.notification_period=finalDay;
		
		return flag;
	}
	
	public static List<UserContact> getEligibleContact(String time){
		System.out.println("Checking Eligible Contact... "+time);
		return UserContact.find("byNotification_period",time).fetch();
	}
}
