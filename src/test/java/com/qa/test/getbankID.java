package com.qa.test;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.qa.pojos.Query;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class getbankID {

	@SuppressWarnings("unchecked")
	@Test
	public int bankID() {
		RestAssured.baseURI = "http://192.168.2.212:8000";
		Query query = new Query();
		query.setQuery(" query ($iLimit: Int!, $iPageNo: Int! , $jCriteria: [jCriteria]) {\r\n"
				+ "    getBank(iLimit: $iLimit, iPageNo: $iPageNo, jCriteria: $jCriteria) {\r\n" + "      success\r\n"
				+ "      messagecode\r\n" + "      message\r\n" + "      data {\r\n" + "        iBankId\r\n"
				+ "      }\r\n" + "    }\r\n" + "  }");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("iLimit", -1);
		jsonObject.put("iPageNo", 0);
		JSONObject jCriteriaV = new JSONObject();
		jCriteriaV.put("typeDef", "sBankName");
		jCriteriaV.put("value", "HFC");
		jCriteriaV.put("operation", "=");

		JSONArray array = new JSONArray();
		array.add(jCriteriaV);
		JSONObject jCriteria = new JSONObject();
		jCriteria.put("jCriteria", array);
		jsonObject.putAll(jCriteria);
		query.setVariables(jsonObject);
		Response res = given().log().all().contentType(ContentType.JSON).header("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOjEsImlFbXBsb3llZUlkIjoxLCJpRGVmYXVsdENvbXBhbnlJZCI6IjEiLCJpc3Bjb2RlIjoiYm1zcWFhdXRvIiwiZGJuYW1lIjoiQk1TUUFfQXV0b21hdGlvbiIsInNlcnZlcmlwIjoiMTkyLjE2OC4yLjIxMSIsImxvZ21vbmdvZGIiOiJCTVNRQV9BdXRvbWF0aW9uIiwiaWF0IjoxNjQ4NjI1OTI3fQ.Z3ZA-R0JNz-fKPgb1H7y4MoT1pgj6qKu3Pb-tSzPMWk")
				.body(query).when().log().all().post("/graphql");
		System.out.println("Bankidsddasdasdadsa: " + res);
		String json = res.asString();
		JsonPath jp = new JsonPath(json);
		System.out.println("Bankidsddasdasdadsasad: " + jp.get("data.getBank.data[0].iBankId"));
		return jp.get("data.getBank.data[0].iBankId");
	}

}
