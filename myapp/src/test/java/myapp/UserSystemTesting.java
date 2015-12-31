package myapp;

import static org.junit.Assert.*;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import spark.Spark;
import spark.utils.IOUtils;
import mysparkapp.Main;
import org.junit.*;
import org.json.*;




public class UserSystemTesting {
	private static class Response {

		public final String body;
		public final int status;

		public Response(int status, String body) {
			this.status = status;
			this.body = body;
		}

	}

	
	@BeforeClass
	public static void beforeClass() {
		Main.main(null);
	}

	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}
	
	@Test
	public void testAll(){
		getAllUsersFailure();
		createNewUser();
		createNewUser1();
		updateExistingUser();
		newUserFailure();
		newUserFirstnameEmpty();
		userUpdateFailure();
		getAllUsersFinally();
	}

	
//	@Test
	public void createNewUser() 
	{
		Response res = request("POST", "/usersnew?id=1&firstname=snehal&lastname=wane&middlename=sona&phone=4186504604&age=25&gender=F&zip=60616");
		JSONObject objJson = new JSONObject(res.body);
		System.out.println(res.body);	
	 	assertEquals(200, res.status);
		assertEquals(objJson.getString("firstname"),"snehal");
		assertEquals(objJson.getString("lastname"),"wane");
		assertEquals(objJson.getString("middlename"),"sona");
		assertEquals(objJson.getString("phone"),"4186504604");
		assertEquals(objJson.getString("age"),"25");
		assertEquals(objJson.getString("gender"),"F");
		assertEquals(objJson.getString("zipcode"),"60616");
	
    }
	
//	@Test
	public void createNewUser1() 
	{
		Response res = request("POST", "/usersnew?id=2&firstname=rush&lastname=gupta&middlename=san&phone=4084293930&age=25&gender=F&zip=95050");
		JSONObject objJson = new JSONObject(res.body);
		System.out.println(res.body);	
	 	assertEquals(200, res.status);
		assertEquals(objJson.getString("firstname"),"rush");
		assertEquals(objJson.getString("lastname"),"gupta");
		assertEquals(objJson.getString("middlename"),"san");
		assertEquals(objJson.getString("phone"),"4084293930");
		assertEquals(objJson.getString("age"),"25");
		assertEquals(objJson.getString("gender"),"F");
		assertEquals(objJson.getString("zipcode"),"95050");
	
    }
	
	
// @Test -- Invalid Phone Number	
	public void newUserFailure() 
	{ 
		Response res = request("POST", "/usersnew?id=3&firstname=johny&lastname=ham&middlename=wang&phone=518654604&age=21&gender=M&zip=12222");
		if(res.body.contains("Invalid Phone number entered..")){
			System.out.println("Passed:" + res.body);
		}else{
			System.out.println("Failed");
		}
	}
	
	// @Test -- Invalid firstname	
		public void newUserFirstnameEmpty() 
		{ 
			Response res = request("POST", "/usersnew?id=4&firstname=&lastname=ham&middlename=wang&phone=518654604&age=21&gender=M&zip=12222");
			if(res.body.contains("Firstname is invalid or empty..")){
				System.out.println("Passed:" + res.body);
			}else{
				System.out.println("Failed");
			}
		}
				
// @Test -- update exiting user	
	public void updateExistingUser()
	{  
		Response res = request("PUT", "/userupdate?id=1&firstname=saumya&lastname=sharma&middlename=hibare&phone=4088930039&age=26&gender=F&zip=95050");
		JSONObject objJson = new JSONObject(res.body);
		System.out.println(res.body);	
	 	assertEquals(200, res.status);
	 	assertEquals(objJson.getString("firstname"),"saumya");
		assertEquals(objJson.getString("lastname"),"sharma");
		assertEquals(objJson.getString("middlename"),"hibare");
		assertEquals(objJson.getString("phone"),"4088930039");
		assertEquals(objJson.getString("age"),"26");
		assertEquals(objJson.getString("gender"),"F");
		assertEquals(objJson.getString("zipcode"),"95050");
			
	}
	
	
	//@Test -- update failure due to non existing user id
	public void userUpdateFailure() {
		Response res = request("PUT", "/userupdate?id=3&firstname=Johny&lastname=donovan&middlename=western&phone=5186504694&age=22&gender=F&zip=122522");
		if(res.body.contains("No user found ")){
			System.out.println("Passed:" + res.body);
		}else{
			System.out.println("Failed");
		}
	}
	
	//@Test -- get all users failure
	public void getAllUsersFailure(){
		Response res = request("GET","/users");
        assertEquals(200,res.status);
	}
	
	//@Test -- get all users 
		public void getAllUsersFinally(){
			Response res = request("GET","/users");
			System.out.println(res.body);
	        assertEquals(200,res.status);
		}
	
	
	private Response request(String method, String path) {
		try {
			URL url = new URL("http://localhost:4567" + path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.connect();
			String body = IOUtils.toString(connection.getInputStream());
			return new Response(connection.getResponseCode(), body);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Sending request failed: " + e.getMessage());
			return null;
		}
	}

}
