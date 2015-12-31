package mysparkapp;

public class UserClass {
	private String id;
	private String firstname;
	private String middlename;
	private String lastname;
	private String age;
	private String gender;
	private String phone;
	private String zipcode;
	

	public UserClass(String id,String firstname,String middlename, String lastname,String phone,String age,String gender,String zip) {
		this.id = id;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.age = age;
		this.phone = phone;
		this.gender = gender;
		this.zipcode = zip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddleName() {
		return middlename;
	}

	public void setMiddleName(String middlename) {
		this.middlename = middlename;
	}
	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
	

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
	this.age = age;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
