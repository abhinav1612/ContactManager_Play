package jobs;

import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job {
 
	public Bootstrap(){
		System.out.println("Inside bootstrap");
	}
    public void doJob() {
        // Check if the database is empty
    	System.out.println("Inside bootstrap do job");
        if(User.count() == 0) {
        	System.out.println("No user exists, creating new User [admin@scalegrid.com, admin]");
        	new User("admin@scalegrid.com", "admin", "admin").save();
        }
    }
 
}