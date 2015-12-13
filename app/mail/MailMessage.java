package mail;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.mail.SimpleEmail;

import models.User;
import models.UserContact;

/**
 * A helper class used to create a MailMessage Object
 * This can be enhanced further to use strategy pattern so that based on different types of Mails (or objects) we need to sent
 * we can have different strategies (say templates)
 */
public class MailMessage {

    public static List<SimpleEmail> getMailMessageList(List<UserContact> userContactList) {
        List<SimpleEmail> simpleMailMessageList = new LinkedList<SimpleEmail>();

        for(UserContact userContact : userContactList) {
        	try {
                simpleMailMessageList.add(getMailMessage(userContact));	
        	} catch(Exception e) {
        		System.out.println("Exception while creating mail message for " + userContact);
        	}
        }

        return simpleMailMessageList;
    }

    public static SimpleEmail getMailMessage(UserContact userContact) throws Exception {
    	SimpleEmail mailMessage = new SimpleEmail();

		User user = userContact.owner;
        mailMessage.setFrom("noreply@scalegrid.com");
        mailMessage.addTo(user.email);
        mailMessage.setSubject(getSubject(userContact.contact_name));
        mailMessage.setMsg(getMessage(user, userContact));    	

        return mailMessage;
    }
   

    private static String getSubject(String name) {
    	return "Birthday Reminder of " + name;
    }
    
    private static String getMessage(User user, UserContact userContact) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Hello ").append(user.fullname).append("\n\n\n")
    		.append("Your Contact " + userContact.contact_name)
    		.append(" has birthday in " + userContact.notification_interval + " hrs !");
    	
    	return sb.toString();
    }
}