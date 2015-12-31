package mysparkapp;

import spark.ResponseTransformer;
import com.google.gson.Gson;

public class JsonUtilClass {
	public static String toJson(Object object) throws Exception 
	{
		return new Gson().toJson(object);
	}
	public static ResponseTransformer json() {
		return JsonUtilClass::toJson;
	}
}



