package com.qa.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.config.resturi;
import com.qa.pojos.Query;
import com.qa.util.CSVReaderUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DesignationTest {
	String token = getAuthToken.getAuthTokenID();
	
	getdesignationID getID= new getdesignationID();
	
	
	//----------------------------------------Get--------------------------------------//
	@DataProvider
	public Object [][] getQueryData() {
		return new Object [][] { { -1, 0 } };
	}
	
	@Test(dataProvider = "getQueryData")
	
	public void getAllDesignation(int iLimit, int iPageNo) {
		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		query.setQuery(" query ($iLimit: Int!, $iPageNo: Int!) {\r\n"
				+ "  getDesignation(iLimit: $iLimit, iPageNo: $iPageNo) {\r\n"
				+ "    data {\r\n"
				+ "      designationid\r\n"
				+ "      designationcode\r\n"
				+ "      designationname\r\n"
				+ "      iDesignationId\r\n"
				+ "      sDesignationCode\r\n"
				+ "      sDesignationName\r\n"
				+ "      iRowIndex\r\n"
				+ "      iTotalCount\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+  "");
		Map<String, Object> authPayload = new HashMap<String, Object>();
		authPayload.put("iLimit", iLimit);
		authPayload.put("iPageNo", iPageNo);
		query.setVariables(authPayload);
		System.out.println(authPayload);
		given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200);

	}
	
	//----------------------------------------Save--------------------------------------//
	
	@DataProvider(name = "saveDesignation")
	public Iterator<String[]> verifyDataProvider() throws IOException, IOException{
		return CSVReaderUtil.readFromCSV("designation-master-data.csv").iterator();
	}
	
	@Test(dataProvider = "saveDesignation")
	
	public void saveDesignation(String name, String code, String message) {
		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		query.setQuery("query ($sDesignationCode: String!, $sDesignationName: String!) {\r\n"
				+ "  addDesignation(\r\n"
				+ "    sDesignationName: $sDesignationName\r\n"
				+ "    sDesignationCode: $sDesignationCode\r\n"
				+ "  ) {\r\n"
				+ "    success\r\n"
				+ "    messagecode\r\n"
				+ "    message\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "");
		
		Map<String, Object> DesignationName = new HashMap<String, Object>();
		DesignationName.put("sDesignationName", name);
		DesignationName.put("sDesignationCode", code);
		query.setVariables(DesignationName);
		given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
		.body("data.addDesignation.message", Matchers.equalToIgnoringCase(message));
	}	
	
	//----------------------------------------Update--------------------------------------//
	
	@DataProvider
	public Object [][] updateDesignation() {
		return new Object [][] {{ "AT_UpdateDesignationNew", "AT_UDesignationN"}};
		}
	
	@Test(dataProvider = "updateDesignation")
	public void updateDesignation(String name, String code) {
		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		getdesignationID getID = new getdesignationID();
	    query.setQuery("query ($iDesignationId: Int!, $sDesignationCode: String!, $sDesignationName: String!) {\r\n"
	    		+ "  updateDesignation(\r\n"
	    		+ "    iDesignationId: $iDesignationId\r\n"
	    		+ "    sDesignationName: $sDesignationName\r\n"
	    		+ "    sDesignationCode: $sDesignationCode\r\n"
	    		+ "  ) {\r\n"
	    		+ "    success\r\n"
	    		+ "    messagecode\r\n"
	    		+ "    message\r\n"
	    		+ "  }\r\n"
	    		+ "}\r\n"
	    		+ "");
	    int designationID = getID.designationID("AT_UpdateDesignation");
	    Map<String, Object> DesignationName = new HashMap<String, Object>();
	    DesignationName.put("sDesignationName", name);
	    DesignationName.put("sDesignationCode", code);
	    DesignationName.put("iDesignationId", designationID);
	    query.setVariables(DesignationName);
	    given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
				.body("data.updateDesignation.message", Matchers.equalToIgnoringCase("Record Updated Successfully"));
	}
	
	//----------------------------------------Update Validation--------------------------------------//
	
	@DataProvider(name = "updateValidationDesignation")
	public Iterator<String[]> verifyDataProviderValidation() throws IOException, IOException{
		return CSVReaderUtil.readFromCSV("designationValidation-master-data.csv").iterator();
	}
	
	@Test(dataProvider = "updateValidationDesignation")
	
	public void updateValidationDesignation(String name, String code, String message) {
		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		getdesignationID getID = new getdesignationID();
	    query.setQuery("query ($iDesignationId: Int!, $sDesignationCode: String!, $sDesignationName: String!) {\r\n"
	    		+ "  updateDesignation(\r\n"
	    		+ "    iDesignationId: $iDesignationId\r\n"
	    		+ "    sDesignationName: $sDesignationName\r\n"
	    		+ "    sDesignationCode: $sDesignationCode\r\n"
	    		+ "  ) {\r\n"
	    		+ "    success\r\n"
	    		+ "    messagecode\r\n"
	    		+ "    message\r\n"
	    		+ "  }\r\n"
	    		+ "}\r\n"
	    		+ "");
	    int designationID = getID.designationID("AT_UpdateDesignationValidation");
	    Map<String, Object> DesignationName = new HashMap<String, Object>();
	    DesignationName.put("sDesignationName", name);
	    DesignationName.put("sDesignationCode", code);
	    DesignationName.put("iDesignationId", designationID);
	    query.setVariables(DesignationName);
	    given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
				.body("data.updateDesignation.message", Matchers.equalToIgnoringCase(message));
	}
	
	//----------------------------------------Delete--------------------------------------//
	
	@DataProvider
	public Object [][] deleteDesignation() {
		return new Object [][] {{ "AT", "AT"}};
		}
	
	@Test(dataProvider = "deleteDesignation")
	public void deleteDesignation(String name, String code) {
		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		getdesignationID getID = new getdesignationID();
	    query.setQuery("query ($iDesignationId: Int!) {\r\n"
	    		+ "  deleteDesignation(iDesignationId: $iDesignationId) {\r\n"
	    		+ "    success\r\n"
	    		+ "    messagecode\r\n"
	    		+ "    message\r\n"
	    		+ "  }\r\n"
	    		+ "}\r\n"
	    		+ "");
	    int designationID = getID.designationID("AT_DeleteDesignation");
	    Map<String, Object> DesignationName = new HashMap<String, Object>();
	    DesignationName.put("iDesignationId", designationID);
	    query.setVariables(DesignationName);
	    given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
				.body("data.deleteDesignation.message", Matchers.equalToIgnoringCase("Record Deleted Successfully"));
	}
}