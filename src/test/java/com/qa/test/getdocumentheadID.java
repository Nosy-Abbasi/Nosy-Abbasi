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

public class getdocumentheadID {
String token = getAuthToken.getAuthTokenID();

@SuppressWarnings("unchecked")
@Test

public int documentcategoryID(String Value) {
	RestAssured.baseURI = resturi.BASE_URI;
	Query query = new Query();
	query.setQuery("query ($iLimit: Int!, $iPageNo: Int!, $jCriteria: [jCriteria]) {\r\n"
			+ "  getDocumentHead(iLimit: $iLimit, iPageNo: $iPageNo, jCriteria: $jCriteria) {\r\n"
			+ "    success\r\n"
			+ "    message\r\n"
			+ "    messagecode\r\n"
			+ "    data {\r\n"
			+ "      iRowIndex\r\n"
			+ "      iDocumentHeadId\r\n"
			+ "      sDocumentHeadName\r\n"
			+ "      iDocumentCategoryId\r\n"
			+ "      sDocumentCategoryName\r\n"
			+ "      iTotalCount\r\n"
			+ "    }\r\n"
			+ "  }\r\n"
			+ "}\r\n"
			+ "");
	
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("iLimit", -1);
	jsonObject.put("iPageNo", 0);
	
	JSONObject jCriteriaV = new JSONObject();
	jCriteriaV.put("typeDef", "sDocumentCategoryName");
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
	System.out.println("Designationidsd: " + jp.get("data.getDocumentHead.data[0].iDocumentCategoryId"));
//	int [] FinalData;
//	FinalData.push([data.getDocumentHead.data[0].iDocumentCategoryId]);
//	FinalData.push([data.getDocumentHead.data[0].iDocumentHeadId]);
	return jp.get("data.getDocumentHead.data[0].iDocumentCategoryId");
}

public int documentheadID(String Value) {
	RestAssured.baseURI = resturi.BASE_URI;
	Query query = new Query();
	query.setQuery("query ($iLimit: Int!, $iPageNo: Int!, $jCriteria: [jCriteria]) {\r\n"
			+ "  getDocumentHead(iLimit: $iLimit, iPageNo: $iPageNo, jCriteria: $jCriteria) {\r\n"
			+ "    success\r\n"
			+ "    message\r\n"
			+ "    messagecode\r\n"
			+ "    data {\r\n"
			+ "      iRowIndex\r\n"
			+ "      iDocumentHeadId\r\n"
			+ "      sDocumentHeadName\r\n"
			+ "      iDocumentCategoryId\r\n"
			+ "      sDocumentCategoryName\r\n"
			+ "      iTotalCount\r\n"
			+ "    }\r\n"
			+ "  }\r\n"
			+ "}\r\n"
			+ "");
	
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("iLimit", -1);
	jsonObject.put("iPageNo", 0);
	
	JSONObject jCriteriaV = new JSONObject();
	jCriteriaV.put("typeDef", "sDocumentHeadName");
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
	System.out.println("Designationidsd: " + jp.get("data.getDocumentHead.data[0].iDocumentHeadId"));
//	int [] FinalData;
//	FinalData.push([data.getDocumentHead.data[0].iDocumentCategoryId]);
//	FinalData.push([data.getDocumentHead.data[0].iDocumentHeadId]);
	return jp.get("data.getDocumentHead.data[0].iDocumentHeadId");
}
}