package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.User;
import models.UserContact;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        String user = Security.connected();
        System.out.println("User Details "+user);
        User owner= User.find("byEmail",user).first();
		List <UserContact> contacts=UserContact.find("byOwner",owner).fetch();
    	render(contacts);
    }

    public static void show(Long id) {
        String user = Security.connected();
    	UserContact contact = UserContact.findById(id);
        render(contact);
    }
    
    public static void postUserContact(String interval, String username, String dob) {
    	
    	String user = Security.connected();
        System.out.println("User :: "+user);
        User owner= User.find("byEmail",user).first();
        
        // this is to get a prveios day
        DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
        Date convertedDate=null,notifyDate=null;
        try{
        	convertedDate = (Date) formatter.parse(dob);
            notifyDate= new Date(convertedDate.getTime() - (1000 * 60 * 60 * 24));
        }catch(Exception ex){
        	System.out.println("Exception while converating Date : "+ex);
        }
       
        System.out.println(notifyDate+" :: "+dob+" :: "+convertedDate);
        // take only dd and mm from notifyDate
        
        int intrval = 24-Integer.parseInt(interval);
        String intvl = intrval <= 9 ? "0"+intrval : String.valueOf(intrval);
        String dd = String.valueOf(notifyDate.getDate());
        String mm = String.valueOf(notifyDate.getMonth() + 1);
        
        String finalDay = dd+mm+intvl;
        
        System.out.println(finalDay+" :: "+dd+" :: "+mm+"::"+intvl);
        
        UserContact contact=new UserContact(username,dob,finalDay,interval,owner);
        contact.save();
        show(contact.id);
    }
    
    public static void edit(Long id) {
        String user = Security.connected();
    	UserContact contact = UserContact.findById(id);
    	render(contact);
    }
    
    public static void updateUserContact(String interval, String username, String dob, Long contact_id) {
        System.out.println(contact_id+"::"+interval);
        UserContact contact=UserContact.findById(contact_id);
        contact.processInput(dob, interval);
        contact.contact_name=username;
        contact.save();
        show(contact_id);
    }
    	
    public static void delete(Long id) {
        String user = Security.connected();
        User owner= User.find("byEmail",user).first();
        
        UserContact contact = UserContact.findById(id);
    	if (owner.id==contact.owner.id){
    		contact.delete();
    	}
    	index();
    }
}