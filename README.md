# ContactManager Play
This is the repository to demonstrate PLAY Framework
First Work on PLAY Framework (1.2.4)

Developed a demo multi-user web application that allows the logged in user to create and maintain a list of his contacts.  The app should send an email to the user when there is a contact with an upcoming birthday.  The email should be sent X hours before the birthday where X is configurable by the user.

Demo will work on Play Framework Version : 1.2.4 and Java Version : 1.7.x

In the Real-time application all System.out.println() statements will be replaced by proper Logger statements.

By Default a user will be created with username as "admin@scalegrid.com"  and password as "admin", when first time appliaction is started.

As it is a demo application, proper validation and error handling was not done.

Steps to create a New User

1. Start the application 
2. Navigate to URL http://localhost:9000/contact_manager/users/new, and Login with Username as admin@scalegrid.com and Password as admin.
3. Fill required details in form and submit the form.
4. New User can be seen under http://localhost:9000/contact_manager/users/

Add Contacts to User

1. Navigate to URL http://localhost:9000/contact_manager, Login if required with proper credentials.
2. Fill User Contact form , with required details and submit form.
3. Added User Contact can be seen under My Contacts.

Edit/Delete User Contact

1. Navigate to URL http://localhost:9000/contact_manager, Login if required with proper credentials.
2. Navigate to User Contacts through My Contacts Tab.
3. Select and Modify Require User Contact details from Contact list.

Logic with storing User Contact Information

1. If User Date of Birth is 16-DEC-1989 and User Notification Time(X) is set as 10 hrs before event, the Event Information will be stored as 151214(DDMMHH) in User Conatct Table
where 15 Represents one day before Event that is on 16 and
12 Represents Month of Event and
14 Represents Hour of the day when notification is to be sent.(So here X is set as 10, event starts at 16-DEC Midnight so 24 - 10 = 14)

2. A background Cron Job will run at every 1 hr starting from Midnight. Cron Job will get current date and timestamp, calculate String
in DDMMHH format. Example if current Date is 15-Dec-2015 and Job is running at 14:00 Hrs then resultant string will "151214". It will then fetch all User Contact records wiht same Event Notification String and pass the List of User Contact to Email Module to send notification mail.




Note : The current cron job is configured to run at 1hr,For testing purpose, uncomment the @On("0 0/1 * * * ?") so that the job runs at every minute to test the email sending funcationality.

Play has a close analogy to Rails framework and I have tried using my rails knowledge to get an understanding of Play frameowrk and develop this project.

