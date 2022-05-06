package com.qa.test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.qa.config.resturi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class getAuthToken {

	public static String getAuthTokenID() {

		RestAssured.baseURI = resturi.isp;
		Map<String, Object> isp = new HashMap<String, Object>();
		isp.put("ispcode", "bmsqaauto");

		Response ispres = given().log().all().contentType(ContentType.JSON).body(isp).when().log().all().post();
		String ispjsonString = ispres.asString();
		JsonPath ispcode = JsonPath.given(ispjsonString);
		String ispCode = ispcode.get("ispcode").toString();
		ispCode = ispCode.substring(1, ispCode.length() - 1);

		RestAssured.baseURI = resturi.userLogin;

		Map<String, String> authToken = new HashMap<String, String>();
		authToken.put("ispcode", ispCode);
		authToken.put("password", "bmsqaauto1212");
		authToken.put("username", "#bmsqaauto#");

		Response userres = given().log().all().contentType(ContentType.JSON).body(authToken).when().log().all().post();
		String userjsonString = userres.asString();

		JsonPath token = JsonPath.given(userjsonString);

		ArrayList<String> arr = token.get("token");

		return arr.get(0);

	}
}