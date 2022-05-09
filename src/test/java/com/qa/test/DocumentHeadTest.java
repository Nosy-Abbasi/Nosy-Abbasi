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

public class DocumentHeadTest {
	
	String token = getAuthToken.getAuthTokenID();
	
	Query query = new Query();
	
	getdocumentheadID getID= new getdocumentheadID();

	//----------------------------------------Get--------------------------------------//
	
	@DataProvider
	public Object [][] getQueryData() {
		return new Object [][] { { -1, 0 } };
	}
	
	@Test(dataProvider = "getQueryData")
	
	public void getAllDocumentHead(int iLimit, int iPageNo) {
		RestAssured.baseURI = resturi.BASE_URI;
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
		
		Map<String, Object> authPayload = new HashMap<String, Object>();
		authPayload.put("iLimit", iLimit);
		authPayload.put("iPageNo", iPageNo);
		query.setVariables(authPayload);
		System.out.println(authPayload);
		given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200);
	}
	
    //----------------------------------------Save--------------------------------------//
	
	@DataProvider(name = "saveDocumentHead")
	public Iterator<String[]> verifyDataProvider() throws IOException, IOException{
		return CSVReaderUtil.readFromCSV("DocumentHead-master-data.csv").iterator();
}
	@Test(dataProvider = "saveDocumentHead")
	
	public void saveDocumentHead(String name, String message) {
		RestAssured.baseURI = resturi.BASE_URI;
		getdocumentheadID getID= new getdocumentheadID();
		query.setQuery("query ($iDocumentCategoryId: Int!, $sDocumentHeadName: String!) {\r\n"
				+ "  insertDocumentHead(\r\n"
				+ "    iDocumentCategoryId: $iDocumentCategoryId\r\n"
				+ "    sDocumentHeadName: $sDocumentHeadName\r\n"
				+ "  ) {\r\n"
				+ "    success\r\n"
				+ "    messagecode\r\n"
				+ "    message\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "");
		
		 int documentcategoryID = getID.documentcategoryID("AT_DocumentCategory");
		 System.out.println("Test: " + documentcategoryID);
		 Map<String, Object> DocumentHeadName = new HashMap<String, Object>();
		 DocumentHeadName.put("sDocumentHeadName", name);
		 DocumentHeadName.put("iDocumentCategoryId", documentcategoryID);
		    query.setVariables(DocumentHeadName);
		    given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
			.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
			.body("data.insertDocumentHead.message", Matchers.equalToIgnoringCase(message)); 
		}	
	
	//----------------------------------------Update--------------------------------------//
	
	@DataProvider
	public Object [][] updateDocumentHead() {
		return new Object [][] {{ "AT_UpdateDocumentHeadNew"}};
		}
	
	@Test(dataProvider = "updateDocumentHead")
	
	public void updateDocumentHead(String name) {
		RestAssured.baseURI = resturi.BASE_URI;
		getdocumentheadID getID= new getdocumentheadID();
	    query.setQuery("query ($iDocumentCategoryId: Int!, $iDocumentHeadId: Int!, $sDocumentHeadName: String!) {\r\n"
	    		+ "  updateDocumentHead(\r\n"
	    		+ "    iDocumentCategoryId: $iDocumentCategoryId\r\n"
	    		+ "    iDocumentHeadId: $iDocumentHeadId\r\n"
	    		+ "    sDocumentHeadName: $sDocumentHeadName\r\n"
	    		+ "  ) {\r\n"
	    		+ "    success\r\n"
	    		+ "    messagecode\r\n"
	    		+ "    message\r\n"
	    		+ "  }\r\n"
	    		+ "}\r\n"
	    		+ "");
	    int documentcategoryID = getID.documentcategoryID("AT_DocumentCategory");
	    int documentheadID = getID.documentheadID("AT_UpdateDocumentHead");
	    Map<String, Object> DocumentHeadName = new HashMap<String, Object>();
	    DocumentHeadName.put("sDocumentHeadName", name);
	    DocumentHeadName.put("iDocumentCategoryId", documentcategoryID);
	    DocumentHeadName.put("iDocumentHeadId", documentheadID);
	    query.setVariables(DocumentHeadName);
	    given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
		.body("data.updateDocumentHead.message", Matchers.equalToIgnoringCase("Record Updated Successfully")); 
	}
	
	//----------------------------------------Update Validation--------------------------------------//
	
	@DataProvider(name = "updateValidationDocumentHead")
	public Iterator<String[]> verifyDataProviderValidation() throws IOException, IOException{
		return CSVReaderUtil.readFromCSV("DocumentHeadValidation-master-data.csv").iterator();
	}
	
	@Test(dataProvider = "updateValidationDocumentHead")
	
	public void updateValidationDocumentHead(String name, String message) {
		RestAssured.baseURI = resturi.BASE_URI;
		getdocumentheadID getID= new getdocumentheadID();
	    query.setQuery("query ($iDocumentCategoryId: Int!, $iDocumentHeadId: Int!, $sDocumentHeadName: String!) {\r\n"
	    		+ "  updateDocumentHead(\r\n"
	    		+ "    iDocumentCategoryId: $iDocumentCategoryId\r\n"
	    		+ "    iDocumentHeadId: $iDocumentHeadId\r\n"
	    		+ "    sDocumentHeadName: $sDocumentHeadName\r\n"
	    		+ "  ) {\r\n"
	    		+ "    success\r\n"
	    		+ "    messagecode\r\n"
	    		+ "    message\r\n"
	    		+ "  }\r\n"
	    		+ "}\r\n"
	    		+ "");
	    int documentcategoryID = getID.documentcategoryID("AT_DocumentCategory");
	    int documentheadID = getID.documentheadID("AT_UpdateDocumentHeadValidation");
	    Map<String, Object> DocumentHeadName = new HashMap<String, Object>();
	    DocumentHeadName.put("sDocumentHeadName", name);
	    DocumentHeadName.put("iDocumentCategoryId", documentcategoryID);
	    DocumentHeadName.put("iDocumentHeadId", documentheadID);
	    query.setVariables(DocumentHeadName);
	    given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
		.body("data.updateDocumentHead.message", Matchers.equalToIgnoringCase(message)); 
	}
	
	//----------------------------------------Delete--------------------------------------//
	
	@DataProvider
	public Object [][] deleteDocumentHead() {
		return new Object [][] {{ "AT"}};
		}
	
	@Test(dataProvider = "deleteDocumentHead")
	
	public void deleteDocumentHead(String name) {
		RestAssured.baseURI = resturi.BASE_URI;
		getdocumentheadID getID= new getdocumentheadID();
	    query.setQuery("query ($iDocumentHeadId: Int!) {\r\n"
	    		+ "  deleteDocumentHead(iDocumentHeadId: $iDocumentHeadId) {\r\n"
	    		+ "    success\r\n"
	    		+ "    messagecode\r\n"
	    		+ "    message\r\n"
	    		+ "  }\r\n"
	    		+ "}\r\n"
	    		+ "");
	    
	    int documentheadID = getID.documentheadID("AT_DeleteDocumentHead");
	    Map<String, Object> DocumentHeadName = new HashMap<String, Object>();
	    DocumentHeadName.put("iDocumentHeadId", documentheadID);
	    query.setVariables(DocumentHeadName);
	    given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
		.body("data.deleteDocumentHead.message", Matchers.equalToIgnoringCase("Record Deleted Successfully"));
}
}