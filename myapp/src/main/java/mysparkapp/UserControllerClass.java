package mysparkapp;

import static spark.Spark.*;



public class UserControllerClass {
	
	public UserControllerClass(final UserServiceClass userService) {

		// Route to get all users
		get("/users", (req, res) -> userService.getAllusers(), JsonUtilClass.json());
		
		post("/userget", (req, res) -> userService.getUser(req.queryParams("id")), JsonUtilClass.json());
		
		// Route to create a new user
		post("/usersnew", (req, res) -> userService.createUser(
				req.queryParams("id"),
				req.queryParams("firstname"),req.queryParams("middlename"),req.queryParams("lastname"),req.queryParams("phone"),
				req.queryParams("age"),req.queryParams("gender"),req.queryParams("zip")
		), JsonUtilClass.json());
		
		// Route to update a user
		put("/userupdate", (req, res) -> userService.updateUser(
				req.queryParams("id"),
				req.queryParams("firstname"),req.queryParams("middlename"),req.queryParams("lastname"),req.queryParams("phone"),
			req.queryParams("age"),req.queryParams("gender"),req.queryParams("zip")
        ), JsonUtilClass.json());
		
		post("/usersdelete", (req, res) -> userService.deteleUser(req.queryParams("id")), JsonUtilClass.json());
		
		after((req, res) -> {
			res.type("application/json");
		});
		
		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(400);
			try {
				res.body(JsonUtilClass.toJson(e));
			} catch (Exception e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		});
	}
	
	
	
	

}
