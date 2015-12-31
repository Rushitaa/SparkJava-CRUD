package mysparkapp;

import java.util.*;

public class UserServiceClass {
	private Map<String, UserClass> usersMap = new HashMap<String, UserClass>();
	
	//get specific user

		public UserClass getUser(String id) 
		{
			return usersMap.get(id);
		}
	
	//function to get the list of all users
		public List<UserClass> getAllusers() 
		{   boolean flag = 	usersMap.values().isEmpty();
			if(flag == true)
		  {
			 System.out.println("No existing User..");
		     return new ArrayList<>();
		  }
			else
			{
			System.out.println(usersMap.values());
			return new ArrayList<>(usersMap.values());
			}
		}
		
	//function to create a new user
	public Object createUser(String id,String firstname,String middlename, String lastname,String phone,String age,String gender,String zipcode) throws Exception 
	{
		UserClass user = usersMap.get(id);
		if (user == null){
			try{
				validateCreateUser(id,firstname,middlename, lastname, phone, age, gender, zipcode);
			}catch(Exception exp){
				System.out.println(exp.getMessage());
				Object ob = new String("Unable to create user:" + exp.getMessage());
				return ob;
			}
		
		UserClass AddUser = new UserClass(id,firstname,middlename, lastname, phone, age, gender, zipcode);
		usersMap.put(AddUser.getId(), AddUser);
		return AddUser;
		}
		else
		throw new IllegalArgumentException("User already exists..");		
	}
	

	public boolean deteleUser(String id)
	{   
		UserClass user = usersMap.get(id);
		if(user == null){
			throw new IllegalArgumentException("No user with id '" + id + "' found");
		}
		else
		{
			usersMap.remove(id);
		}
		return true;
	}

	
	//function to update details of an existing user
	@SuppressWarnings("null")
	public Object updateUser(String id,String firstname,String middlename, String lastname,String phone,String age,String gender,String zipcode) 
	{
		UserClass user = usersMap.get(id);
		if (user == null) {
			try
			{
			throw new IllegalArgumentException("No user found with id '" + id + "'");
			}
			catch(Exception exp){
				System.out.println(exp.getMessage());
				Object ob = new String(exp.getMessage());
				return ob;	
			}
			
		}
		
		if(firstname!=null ||firstname.isEmpty()==false)
		user.setFirstName(firstname);
		
		if(middlename!=null ||middlename.isEmpty()==false)
		user.setMiddleName(middlename);
		
		if(lastname!=null ||lastname.isEmpty()==false)
		user.setLastName(lastname);
		
		
		if(age!=null ||age.isEmpty()==false)
		{
			validateAgeOfUser(age);
			user.setAge(age);
		}
		
		if(phone!=null ||phone.isEmpty()==false)
		{
			validatePhoneOfUser(phone);
			user.setPhone(phone);
		}
		
		if(zipcode!=null ||zipcode.isEmpty()==false)
		{
			validateZipcode(zipcode);
			user.setZipcode(zipcode);
		}
		
		return user;
	}
	
	//String to Integer parsing
	public static boolean isInteger(String s) 
	{
		try
		{
			Double.parseDouble(s);
		    return true;
		}
		catch (NumberFormatException ex)
		{
		   return false;
		}
	}

	//Validate for Create User
	private void validateCreateUser(String id,String firstname,String middlename, String lastname,String phone,String age,String gender,String zipcode) 
	{
		if (firstname == null || firstname.isEmpty() || firstname.matches("^[a-zA-Z]+$")==false) 
		{
			throw new IllegalArgumentException("Firstname is invalid or empty..");
		}
		
		if (lastname == null || lastname.isEmpty() || lastname.matches("^[a-zA-Z]+$")==false)
		{
			throw new IllegalArgumentException("Lastname is invalid or empty..");
		}
		
		validateAgeOfUser(age);
		validatePhoneOfUser(phone);
		validateZipcode(zipcode);
		
	}
	
	//Validate age of the user : non zero and non empty field
	private void validateAgeOfUser(String age) 
	{
		if(age==null || age.isEmpty()||isInteger(age)==false)
		{
			throw new IllegalArgumentException("Invalid age entered..");
		}
	}
	
	//Validate phone number of the user : 10 digits and non empty field
	private void validatePhoneOfUser(String phone) 
	{
		if(phone==null || phone.isEmpty()||phone.length()!=10||isInteger(phone)==false)
		{
			throw new IllegalArgumentException("Invalid Phone number entered..");
		}	
	}
	
	//Validate zipcode of the user
	private void validateZipcode(String zipcode) 
	{
			if(zipcode.length()!=5||isInteger(zipcode)==false )
			{
				throw new IllegalArgumentException("Invalid Zipcode entered..");
			}
	}
		
}
