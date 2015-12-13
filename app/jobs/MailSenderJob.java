package jobs;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import mail.Email;
import mail.MailMessage;
import models.User;
import models.UserContact;
import play.jobs.Job;
import play.jobs.On;
import play.libs.Mail;

/**
 * This job is schedule to run at every 1 hr
 * Purpose: This job will pick up all those contacts whose birthday falls in the running time frame
 * 
 * Format of Notification saved: DDMMHH
 * If a person's birthday is on 16th Dec 1989, and the user wants the intimation around 3 hours back 
 * then the data stored would be
 * 
 * 151221
 * 
 * Assuming, this batch is running on 15th December at 9pm (21 clock) then this contact will be picked
 * up and the user will be intimated via email
 * 
 * @author scalegrid
 *
 */
//@On("0 0 0/1 * * ?")	// Run the job at every 1hr interval
  @On("0 0/1 * * * ?")	// Run the job at every 1 minute interval (for testing purpose)
public class MailSenderJob extends Job {
	
	@Override
	public void doJob() {
		// get the current day, month and hour
		System.out.println("\nStarting a job to send mail ------");
		Calendar now = Calendar.getInstance();
		int day = now.get(Calendar.DAY_OF_MONTH);
		int month = now.get(Calendar.MONTH) + 1;
		int hour = now.get(Calendar.HOUR_OF_DAY);
		String format = ""+day+month+hour;
		
		// Delete Me: I'm there only for testing
		// List<UserContact> userContactList = getUserContactList();
		
		// get the list of users for which you need to send the mail
		List<UserContact> userContactList = UserContact.getEligibleContact(format);
		System.out.println("List of User Contacts: " + userContactList);
		System.out.println("Number of mails to send: " + userContactList.size());
		
		if(userContactList != null && userContactList.size() > 0) {
			// Now prepare the email object and send the mail
			sendMail(userContactList);	
		} else {
			System.out.println("No mail send");
		}

		System.out.println("Job end ******************* \n");
		System.out.println("P.S. Please check the sent folder of Email: abhinav.scalegridtest@gmail.com and Password: scalegrid \n\n\n");
	}
	
	private static void sendMail(List<UserContact> userContactList) {
		for(UserContact userContact : userContactList) {
	    	try {
				// Note: for the demo purpose, ignoring the case in which mail sending is failed
				// in real time, we can keep track of such mails, and initiate a re-send
				// send the mail -- since sending mail is asynchronous, so it won't be a blocking call
	    		Mail.send(MailMessage.getMailMessage(userContact));
	    		System.out.println("Mail send: " + userContact);
			} catch(Exception e) {
	    		// In real time application - this will be replaced with Logger
	    		System.out.println("Exception while sending mail: " + userContact + " Exception: " + e);
	    	}
		}
	}
	
	// I'm used only for debugging: dummy data
	private static List<UserContact> getUserContactList() {
		List<UserContact> userContactList = new LinkedList<UserContact>();

		User user = new User("abhinav1216@gmail.com", "abhinav", "Abhinav Verma");
		for(int i=0; i<10; ++i) {
			UserContact uc = new UserContact("abhinav", "16121989", "161223", "1", user);
			userContactList.add(uc);
		}
		
		return userContactList;
	}
}
