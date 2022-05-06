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
import com.qa.config.resturi;
import com.qa.pojos.Query;
import com.qa.util.CSVReaderUtil;

public class getpasswordpolicyID {
String token = getAuthToken.getAuthTokenID();
@SuppressWarnings("unchecked")
	@Test
	
	public int passwordpolicyID(String Value) {
		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		query.setQuery("query ($iLimit: Int!, $iPageNo: Int!, $iPasswordPolicyId: Int, $bIsDefault: Boolean, $iStatusId: Int, $iDocumentEntityTypeId: Int,$jCriteria: [jCriteria]) {\r\n"
				+ "  getPasswordPolicy(\r\n"
				+ "    iLimit: $iLimit\r\n"
				+ "    iPageNo: $iPageNo\r\n"
				+ "    iPasswordPolicyId: $iPasswordPolicyId\r\n"
				+ "    bIsDefault: $bIsDefault\r\n"
				+ "    iStatusId: $iStatusId\r\n"
				+ "    iDocumentEntityTypeId: $iDocumentEntityTypeId\r\n"
				+ "    jCriteria: $jCriteria\r\n"
				+ "  ) {\r\n"
				+ "    success\r\n"
				+ "    message\r\n"
				+ "    messagecode\r\n"
				+ "    data {\r\n"
				+ "      iPasswordPolicyId\r\n"
				+ "      sPasswordPolicyName\r\n"
				+ "      iDaysToExpire\r\n"
				+ "      iPasswordTypeId\r\n"
				+ "      sPasswordTypeName\r\n"
				+ "      iDisallowLastPassword\r\n"
				+ "      iMinimumLength\r\n"
				+ "      bIsDefault\r\n"
				+ "      bIsNumeral\r\n"
				+ "      bIsLowerCase\r\n"
				+ "      bIsUpperCase\r\n"
				+ "      bIsSpecialCase\r\n"
				+ "      iNotificationDays\r\n"
				+ "      iStatusId\r\n"
				+ "      sStatusName\r\n"
				+ "      sFontColor\r\n"
				+ "      iRowIndex\r\n"
				+ "      iTotalCount\r\n"
				+ "      sDefaultValue\r\n"
				+ "      iDocumentEntityId\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("iLimit", -1);
		jsonObject.put("iPageNo", 0);
		
		JSONObject jCriteriaV = new JSONObject();
		jCriteriaV.put("typeDef", "sPasswordPolicyNamee");
		jCriteriaV.put("value", Value);
		jCriteriaV.put("operation", "=");
		
		JSONArray array = new JSONArray();
		array.add(jCriteriaV);
		JSONObject jCriteria = new JSONObject();
		jCriteria.put("jCriteria", array);
		jsonObject.putAll(jCriteria);
		query.setVariables(jsonObject);
		Response res = given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token)
				.body(query).when().log().all().post("/graphql");
		System.out.println("Designationidsdd: " + res);
		String json = res.asString();
		JsonPath jp = new JsonPath(json);
		System.out.println("Designationidsd: " + jp.get("data.getPasswordPolicy.data[0].iPasswordPolicyId"));
		return jp.get("data.getPasswordPolicy.data[0].iPasswordPolicyId");
}
}