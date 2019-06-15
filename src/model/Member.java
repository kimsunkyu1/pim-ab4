package model;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Member { // DB의 레코드와 매핑되는 객체
	private StringProperty uemail; // DB의 필드와 매핑
	private StringProperty upw;
	private StringProperty uname;
	private StringProperty uaddress;
	private StringProperty uage;
	private StringProperty ubirth;
	private StringProperty ucontact;
	/*
    private final IntegerProperty zipcode;
    private ObjectProperty<LocalDate> birthday;
	*/
	public Member() {
		this(null, null, null, null, null, null, null);
	}
	
	public Member(String email, String pw, String name, String birth, String age, String address, String contact) {
		this.uemail = new SimpleStringProperty(email);
		this.upw = new SimpleStringProperty(pw);
		this.uname = new SimpleStringProperty(name);
		this.ubirth = new SimpleStringProperty(birth);
		this.uage = new SimpleStringProperty(age);
		this.uaddress = new SimpleStringProperty(address);
		this.ucontact = new SimpleStringProperty(contact);
	}
	
	public String getUemail() {
		return this.uemail.get();
	}
	public void setUemail(String uemail) {
		this.uemail.set(uemail);
	}
    public StringProperty uemailProperty() {
        return uemail;
    }
    
    public String getUpw() {
    	return this.upw.get();
    }
    public void setUpw(String upw) {
    	this.upw.set(upw);
    }
    public StringProperty upwProperty() {
        return upw;
    }
    
    public String getUname() {
    	return this.uname.get();
    }
    public void setUname(String uname) {
    	this.uname.set(uname);
    }
    public StringProperty unameProperty() {
        return uname;
    }

	public String getUcontact() {
		return this.ucontact.get();
	}
	public void setUcontact(String ucontact) {
		this.ucontact.set(ucontact);
	}
	public StringProperty ucontactProperty() {
        return ucontact;
    }
	
	
	public String getUbirth() {
		return this.ubirth.get();
	}
	public void setUbirth(String ubirth) {
		this.ubirth.set(ubirth);
	}
	public StringProperty ubirthProperty() {
        return ubirth;
    }

	
	public String getUage() {
		return this.uage.get();
	}
	public void setUage(String uage) {
		this.uage.set(uage);
	}
	public StringProperty uageProperty() {
        return uage;
    }
	
	public String getUaddress() {
		return this.uaddress.get();
	}
	public void setUaddress(String uaddress) {
		this.uaddress.set(uaddress);
	}
	public StringProperty uaddressProperty() {
        return uaddress;
    }
	

	
}
