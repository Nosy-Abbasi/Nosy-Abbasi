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

public class BankTest {
	String token = getAuthToken.getAuthTokenID();

	@DataProvider
	public Object[][] getQueryData() {
		return new Object[][] { { -1, 0 } };
	}

	@Test(dataProvider = "getQueryData")
	public void getAllBank(int iLimit, int iPageNo) {

		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		query.setQuery(" query ($iLimit: Int!, $iPageNo: Int! , $jCriteria: [jCriteria]) {\n"
				+ "    getBank(iLimit: $iLimit, iPageNo: $iPageNo, jCriteria: $jCriteria) {\n" + "      success\n"
				+ "      messagecode\n" + "      message\n" + "      data {\n" + "        iBankId\n" + "      }\n"
				+ "    }\n" + "  }");
		Map<String, Object> authPayload = new HashMap<String, Object>();
		authPayload.put("iLimit", iLimit);
		authPayload.put("iPageNo", iPageNo);
		query.setVariables(authPayload);
		System.out.println(authPayload);
		given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
				.log().all().post("/graphql").then().log().all().assertThat().statusCode(200);

	}

	@DataProvider
	public Object[][] savebank() {
		return new Object[][] { { "UCOBank", "UCO", "code already exists" },
				{ "IDBI", "IDBI", "code already exists" } };
	}

	@DataProvider(name = "savebank1")
	public Iterator<String[]> verifyDataProvider() throws IOException, IOException {
		return CSVReaderUtil.readFromCSV("bank-master-data.csv").iterator();

	}

	// @Test(dataProvider = "savebank1")
	public void savebank(String name, String code, String message) {
		RestAssured.baseURI = "http://192.168.2.212:8000";
		String var = ("query($sBankCode:String!,$sBankName:String!){\n"
				+ "    addBank(sBankCode:$sBankCode,sBankName:$sBankName){\n" + "        message\n"
				+ "        messagecode\n" + "        success\n" + "    }\n" + "}");
		Query query = new Query();
		query.setQuery("query($sBankCode:String!,$sBankName:String!){\n"
				+ "    addBank(sBankCode:$sBankCode,sBankName:$sBankName){\n" + "        message\n"
				+ "        messagecode\n" + "        success\n" + "    }\n" + "}");

		Map<String, Object> BankName = new HashMap<String, Object>();
		BankName.put("sBankCode", code);
		BankName.put("sBankName", name);

		Map<String, Object> final1 = new HashMap<String, Object>();
		final1.put("query", var);
		final1.put("variables", BankName);

		// query.setVariables(BankName);
		// JSONObject jsonString = graphqlToJson(var, BankName);
		// System.out.println("jsonStringasdada@@@@@@" + jsonString);
		given().log().all().contentType(ContentType.JSON)
				.header("Authorization",
						"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c"
								+ "2VyaWQiOjEsImlFbXBsb3llZUlkIjoxLCJpRGVmYXVsdENvbXBhbnlJZCI6"
								+ "IjEiLCJpc3Bjb2RlIjoiYm1zcWFhdXRvIiwiZGJuYW1lIjoiQk1TUUFfQXV0b21hdGlvbiIsInNlcnZlcm"
								+ "lwIjoiMTkyLjE2OC4yLjIxMSIsImxvZ21vbmdvZGIiOiJCTVNRQV9BdXRvbWF0aW9uIiwiaWF0IjoxNjQ4Nj"
								+ "I1OTI3fQ.Z3ZA-R0JNz-fKPgb1H7y4MoT1pgj6qKu3Pb-tSzPMWk")
				.body(final1).when().log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
				.body("data.addBank.message", Matchers.equalToIgnoringCase(message));

//		given().log().all().contentType(ContentType.JSON)
//				.header("Authorization",
//						"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c"
//								+ "2VyaWQiOjEsImlFbXBsb3llZUlkIjoxLCJpRGVmYXVsdENvbXBhbnlJZCI6"
//								+ "IjEiLCJpc3Bjb2RlIjoiYm1zcWFhdXRvIiwiZGJuYW1lIjoiQk1TUUFfQXV0b21hdGlvbiIsInNlcnZlcm"
//								+ "lwIjoiMTkyLjE2OC4yLjIxMSIsImxvZ21vbmdvZGIiOiJCTVNRQV9BdXRvbWF0aW9uIiwiaWF0IjoxNjQ4Nj"
//								+ "I1OTI3fQ.Z3ZA-R0JNz-fKPgb1H7y4MoT1pgj6qKu3Pb-tSzPMWk")
//				.body().post("/graphql").then().log().all().assertThat().statusCode(200);

	}

	@DataProvider
	public Object[][] updatebank() {
		return new Object[][] { { "UCOBank", "UCO" } };
	}

	// @Test(dataProvider = "updatebank")
	public void updateBank(String name, String code) {
		RestAssured.baseURI = "http://192.168.2.212:8000";
		Query query = new Query();
		getbankID getID = new getbankID();
		query.setQuery("query($iBankId:Int!,$sBankCode:String!,$sBankName:String!){\n"
				+ "    updateBank(iBankId:$iBankId,sBankCode:$sBankCode,sBankName:$sBankName){\n" + "        message\n"
				+ "        messagecode\n" + "        success\n" + "    }\n" + "}");

	//	int bankid = getID.bankID("IDBI");
		Map<String, Object> BankName = new HashMap<String, Object>();
		BankName.put("sBankName", name);
		BankName.put("sBankCode", code);
		//BankName.put("iBankId", bankid);
		query.setVariables(BankName);
		given().log().all().contentType(ContentType.JSON).header("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOjEsImlFbXBsb3llZUlkIjoxLCJpRGVmYXVsdENvbXBhbnlJZCI6IjEiLCJpc3Bjb2RlIjoiYm1zcWFhdXRvIiwiZGJuYW1lIjoiQk1TUUFfQXV0b21hdGlvbiIsInNlcnZlcmlwIjoiMTkyLjE2OC4yLjIxMSIsImxvZ21vbmdvZGIiOiJCTVNRQV9BdXRvbWF0aW9uIiwiaWF0IjoxNjQ4NjI1OTI3fQ.Z3ZA-R0JNz-fKPgb1H7y4MoT1pgj6qKu3Pb-tSzPMWk")
				.body(query).when().log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
				.body("message", Matchers.equalToIgnoringCase("Bank Code Already Exists"));

	}
}
