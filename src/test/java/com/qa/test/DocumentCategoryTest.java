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

public class DocumentCategoryTest {
	
	String token = getAuthToken.getAuthTokenID();
	
	getdocumentcategoryID getID= new getdocumentcategoryID();
	
	//----------------------------------------Get--------------------------------------//
	
	@DataProvider
	public Object [][] getQueryData() {
		return new Object [][] { { -1, 0 } };
	}
	
@Test(dataProvider = "getQueryData")
	
	public void getAllDocumentCategory(int iLimit, int iPageNo) {
		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		query.setQuery("query ($iLimit: Int!, $iPageNo: Int!, $jCriteria: [jCriteria]) {\r\n"
				+ "  getDocumentCategories(iLimit: $iLimit, iPageNo: $iPageNo, jCriteria: $jCriteria) {\r\n"
				+ "    data {\r\n"
				+ "      iDocumentCategoryId\r\n"
				+ "      sDocumentCategoryName\r\n"
				+ "      iRowIndex\r\n"
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

			@DataProvider(name = "saveDocumentCategory")
			public Iterator<String[]> verifyDataProvider() throws IOException, IOException{
				return CSVReaderUtil.readFromCSV("DocumentCategory-master-data.csv").iterator();
}

			@Test(dataProvider = "saveDocumentCategory")

			public void saveDocumentCategory(String name, String message) {
				RestAssured.baseURI = resturi.BASE_URI;
				Query query = new Query();
				query.setQuery("query ($sDocumentCategoryName: String) {\r\n"
						+ "  insertDocumentCategories(sDocumentCategoryName: $sDocumentCategoryName) {\r\n"
						+ "    success\r\n"
						+ "    message\r\n"
						+ "    messagecode\r\n"
						+ "  }\r\n"
						+ "}\r\n"
						+ "");

				Map<String, Object> DocumentCategoryName = new HashMap<String, Object>();
				DocumentCategoryName.put("sDocumentCategoryName", name);
				query.setVariables(DocumentCategoryName);
				given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
				.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
				.body("data.insertDocumentCategories.message", Matchers.equalToIgnoringCase(message));
			}
			
			//----------------------------------------Update--------------------------------------//
			
			@DataProvider
			public Object [][] updateDocumentCategory() {
				return new Object [][] {{ "AT_UpdateDocumentCategoryNew"}};
				}
			
			@Test(dataProvider = "updateDocumentCategory")
			
			public void updateDocumentCategory(String name) {
				RestAssured.baseURI = resturi.BASE_URI;
				Query query = new Query();
				getdocumentcategoryID getID = new getdocumentcategoryID();
			    query.setQuery("query ($iDocumentCategoryId: Int!, $sDocumentCategoryName: String!) {\r\n"
			    		+ "  updateDocumentCategories(\r\n"
			    		+ "    iDocumentCategoryId: $iDocumentCategoryId\r\n"
			    		+ "    sDocumentCategoryName: $sDocumentCategoryName\r\n"
			    		+ "  ) {\r\n"
			    		+ "    success\r\n"
			    		+ "    messagecode\r\n"
			    		+ "    message\r\n"
			    		+ "  }\r\n"
			    		+ "}\r\n"
			    		+ "");
			
			    int documentcategoryID = getID.documentcategoryID("AT_UpdateDocumentCategory");
			    Map<String, Object> DocumentCategoryName = new HashMap<String, Object>();
			    DocumentCategoryName.put("sDocumentCategoryName", name);
			    DocumentCategoryName.put("iDocumentCategoryId", documentcategoryID);
			    query.setVariables(DocumentCategoryName);
			    given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
				.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
				.body("data.updateDocumentCategories.message", Matchers.equalToIgnoringCase("Record Updated Successfully")); 		
			}	
			
			//----------------------------------------Update Validation--------------------------------------//
			
			@DataProvider(name = "updateValidationDocumentCategory")
			public Iterator<String[]> verifyDataProviderValidation() throws IOException, IOException{
				return CSVReaderUtil.readFromCSV("DocumentCategoryValidation-master-data.csv").iterator();
			}
			
			@Test(dataProvider = "updateValidationDocumentCategory")
			
			public void updateValidationDocumentCategory(String name,String message) {
				RestAssured.baseURI = resturi.BASE_URI;
				Query query = new Query();
				getdocumentcategoryID getID = new getdocumentcategoryID();
			    query.setQuery("query ($iDocumentCategoryId: Int!, $sDocumentCategoryName: String!) {\r\n"
			    		+ "  updateDocumentCategories(\r\n"
			    		+ "    iDocumentCategoryId: $iDocumentCategoryId\r\n"
			    		+ "    sDocumentCategoryName: $sDocumentCategoryName\r\n"
			    		+ "  ) {\r\n"
			    		+ "    success\r\n"
			    		+ "    messagecode\r\n"
			    		+ "    message\r\n"
			    		+ "  }\r\n"
			    		+ "}\r\n"
			    		+ "");
			
			    int documentcategoryID = getID.documentcategoryID("AT_UpdateDocumentCategoryValidation");
			    Map<String, Object> DocumentCategoryName = new HashMap<String, Object>();
			    DocumentCategoryName.put("sDocumentCategoryName", name);
			    DocumentCategoryName.put("iDocumentCategoryId", documentcategoryID);
			    query.setVariables(DocumentCategoryName);
			    given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
				.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
				.body("data.updateDocumentCategories.message", Matchers.equalToIgnoringCase(message));
			}	
			
			//----------------------------------------Delete--------------------------------------//
			
			@DataProvider
			public Object [][] deleteDocumentCategory() {
				return new Object [][] {{ "AT"}};
				}
			
			@Test(dataProvider = "deleteDocumentCategory")
			
			public void deleteDocumentCategory(String name) {
				RestAssured.baseURI = resturi.BASE_URI;
				Query query = new Query();
				getdocumentcategoryID getID = new getdocumentcategoryID();
			    query.setQuery("query ($iDocumentCategoryId: Int!) {\r\n"
			    		+ "  deleteDocumentCategories(iDocumentCategoryId: $iDocumentCategoryId) {\r\n"
			    		+ "    success\r\n"
			    		+ "    messagecode\r\n"
			    		+ "    message\r\n"
			    		+ "  }\r\n"
			    		+ "}\r\n"
			    		+ "");
			
			    int documentcategoryID = getID.documentcategoryID("AT_DeleteDocumentCategory");
			    Map<String, Object> DocumentCategoryName = new HashMap<String, Object>();
			    DocumentCategoryName.put("iDocumentCategoryId", documentcategoryID);
			    query.setVariables(DocumentCategoryName);
			    given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
				.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
				.body("data.deleteDocumentCategories.message", Matchers.equalToIgnoringCase("Record Deleted Successfully"));    	
			}		
}