package mail;

public class Email {
	// properties needed to sent the mail
	private long id;
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String body;
	
    /**
     * TODO: Include the following
     * 1. List of attachments
     * 2. CC/ BCC
     * 3. Mail sending date
     */
    
    public long getId() {
		return id;
	}
    
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFromEmail() {
		return fromEmail;
	}
	
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	
	public String getToEmail() {
		return toEmail;
	}
	
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "Email(id=" + this.id + ", "
				+ "fromEmail=" + this.fromEmail + ", "
				+ "toEmail=" + this.toEmail + ", "
				+ "subject=" + this.subject + ", "
				+ "body=" + this.body + ")";
	}
}
