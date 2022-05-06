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

public class PasswordPolicyTest {
	String token = getAuthToken.getAuthTokenID();
	getpasswordpolicyID getID= new getpasswordpolicyID();
	
	//----------------------------------------Get--------------------------------------//
	
	@DataProvider
	public Object [][] getQueryData() {
		return new Object [][] { { -1, 0 } };
	}
	
	@Test(dataProvider = "getQueryData")
	
	public void getAllPasswordPolicy(int iLimit, int iPageNo) {
		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		query.setQuery("query ($iLimit: Int!, $iPageNo: Int!, $iPasswordPolicyId: Int, $bIsDefault: Boolean, $iStatusId: Int, $iDocumentEntityTypeId: Int) {\r\n"
				+ "  getPasswordPolicy(\r\n"
				+ "    iLimit: $iLimit\r\n"
				+ "    iPageNo: $iPageNo\r\n"
				+ "    iPasswordPolicyId: $iPasswordPolicyId\r\n"
				+ "    bIsDefault: $bIsDefault\r\n"
				+ "    iStatusId: $iStatusId\r\n"
				+ "    iDocumentEntityTypeId: $iDocumentEntityTypeId\r\n"
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
		Map<String, Object> authPayload = new HashMap<String, Object>();
		authPayload.put("iLimit", iLimit);
		authPayload.put("iPageNo", iPageNo);
		query.setVariables(authPayload);
		System.out.println(authPayload);
		given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200);
	}
	
	//----------------------------------------Save--------------------------------------//
	
	@DataProvider(name = "savePasswordPolicy")
	public Iterator<String[]> verifyDataProvider() throws IOException, IOException{
		return CSVReaderUtil.readFromCSV("passwordPolicy-master-data.csv").iterator();
	}
	
	@Test(dataProvider = "savePasswordPolicy")
	public void savePasswordPolicy(String name, String DaysToExpire, String PasswordTypeId, String DisallowLastPassword,
			String IsNumeral, String IsLowerCase, String MinimumLength, String IsUpperCase, String IsSpecialCase,
			String NotificationDays, String IsDefault, String StatusId, String message) {
		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		query.setQuery("query ($sPasswordPolicyName: String, $iDaysToExpire: Int, $iPasswordTypeId: Int, $iDisallowLastPassword: Int, $bIsNumeral: Boolean, $bIsLowerCase: Boolean, $iMinimumLength: Int, $bIsUpperCase: Boolean, $bIsSpecialCase: Boolean, $iNotificationDays: Int, $bIsDefault: Boolean, $iStatusId: Int) {\r\n"
				+ "  insertPasswordPolicy(\r\n"
				+ "    sPasswordPolicyName: $sPasswordPolicyName\r\n"
				+ "    iDaysToExpire: $iDaysToExpire\r\n"
				+ "    iPasswordTypeId: $iPasswordTypeId\r\n"
				+ "    iDisallowLastPassword: $iDisallowLastPassword\r\n"
				+ "    bIsNumeral: $bIsNumeral\r\n"
				+ "    bIsLowerCase: $bIsLowerCase\r\n"
				+ "    iMinimumLength: $iMinimumLength\r\n"
				+ "    bIsUpperCase: $bIsUpperCase\r\n"
				+ "    bIsSpecialCase: $bIsSpecialCase\r\n"
				+ "    iNotificationDays: $iNotificationDays\r\n"
				+ "    bIsDefault: $bIsDefault\r\n"
				+ "    iStatusId: $iStatusId\r\n"
				+ "  ) {\r\n"
				+ "    success\r\n"
				+ "    message\r\n"
				+ "    messagecode\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "");
		
		Map<String, Object> PasswordPolicyName = new HashMap<String, Object>();
		PasswordPolicyName.put("sPasswordPolicyName", name);
		PasswordPolicyName.put("iDaysToExpire",Integer.parseInt(DaysToExpire));
		PasswordPolicyName.put("iPasswordTypeId", Integer.parseInt(PasswordTypeId));
		PasswordPolicyName.put("iDisallowLastPassword", Integer.parseInt(DisallowLastPassword));
		PasswordPolicyName.put("bIsNumeral", Boolean.parseBoolean(IsNumeral));
		PasswordPolicyName.put("bIsLowerCase", Boolean.parseBoolean(IsLowerCase));
		PasswordPolicyName.put("iMinimumLength", Integer.parseInt(MinimumLength));
		PasswordPolicyName.put("bIsUpperCase", Boolean.parseBoolean(IsUpperCase));
		PasswordPolicyName.put("bIsSpecialCase", Boolean.parseBoolean(IsSpecialCase));
		PasswordPolicyName.put("iNotificationDays", Integer.parseInt(NotificationDays));
		PasswordPolicyName.put("bIsDefault", Boolean.parseBoolean(IsDefault));
		PasswordPolicyName.put("iStatusId", Integer.parseInt(StatusId));
		query.setVariables(PasswordPolicyName);
		given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
		.body("data.insertPasswordPolicy.message", Matchers.equalToIgnoringCase(message));
	}
	
	//----------------------------------------Update--------------------------------------//
	
	@DataProvider
	public Object [][] updatePasswordPolicy() {
		return new Object [][] {{"AT_UpdatePasswordPolicyNew",10,1,1,true,true,8,true,false,1,false,0 }};
		}
	
	@Test(dataProvider = "updatePasswordPolicy")
	public void updatePasswordPolicy(String name, int DaysToExpire, int PasswordTypeId, int DisallowLastPassword,
			boolean IsNumeral, boolean IsLowerCase, int MinimumLength, boolean IsUpperCase, boolean IsSpecialCase,
			int NotificationDays, boolean IsDefault, int StatusId) {
		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		getpasswordpolicyID getID = new getpasswordpolicyID();
		query.setQuery("query ($iPasswordPolicyId: Int, $sPasswordPolicyName: String, $iDaysToExpire: Int, $iPasswordTypeId: Int, $iDisallowLastPassword: Int, $bIsNumeral: Boolean, $bIsLowerCase: Boolean, $iMinimumLength: Int, $bIsUpperCase: Boolean, $bIsSpecialCase: Boolean, $iNotificationDays: Int, $bIsDefault: Boolean, $iStatusId: Int) {\r\n"
				+ "  updatePasswordPolicy(\r\n"
				+ "    iPasswordPolicyId: $iPasswordPolicyId\r\n"
				+ "    sPasswordPolicyName: $sPasswordPolicyName\r\n"
				+ "    iDaysToExpire: $iDaysToExpire\r\n"
				+ "    iPasswordTypeId: $iPasswordTypeId\r\n"
				+ "    iDisallowLastPassword: $iDisallowLastPassword\r\n"
				+ "    bIsNumeral: $bIsNumeral\r\n"
				+ "    bIsLowerCase: $bIsLowerCase\r\n"
				+ "    iMinimumLength: $iMinimumLength\r\n"
				+ "    bIsUpperCase: $bIsUpperCase\r\n"
				+ "    bIsSpecialCase: $bIsSpecialCase\r\n"
				+ "    iNotificationDays: $iNotificationDays\r\n"
				+ "    bIsDefault: $bIsDefault\r\n"
				+ "    iStatusId: $iStatusId\r\n"
				+ "  ) {\r\n"
				+ "    success\r\n"
				+ "    message\r\n"
				+ "    messagecode\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "");
		int passwordpolicyID = getID.passwordpolicyID("AT_UpdatePasswordPolicy");
		Map<String, Object> PasswordPolicyName = new HashMap<String, Object>();
		PasswordPolicyName.put("iPasswordPolicyId", passwordpolicyID);
		PasswordPolicyName.put("sPasswordPolicyName", name);
		PasswordPolicyName.put("iDaysToExpire", DaysToExpire);
		PasswordPolicyName.put("iPasswordTypeId", PasswordTypeId);
		PasswordPolicyName.put("iDisallowLastPassword", DisallowLastPassword);
		PasswordPolicyName.put("bIsNumeral", IsNumeral);
		PasswordPolicyName.put("bIsLowerCase", IsLowerCase);
		PasswordPolicyName.put("iMinimumLength", MinimumLength);
		PasswordPolicyName.put("bIsUpperCase", IsUpperCase);
		PasswordPolicyName.put("bIsSpecialCase", IsSpecialCase);
		PasswordPolicyName.put("iNotificationDays", NotificationDays);
		PasswordPolicyName.put("bIsDefault", IsDefault);
		PasswordPolicyName.put("iStatusId", StatusId);
		query.setVariables(PasswordPolicyName);
		given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
		.body("data.updatePasswordPolicy.message", Matchers.equalToIgnoringCase("Record Updated Successfully"));
	}
	
	//----------------------------------------Update Validation--------------------------------------//
	
	@DataProvider(name = "updateValidationPasswordPolicy")
	public Iterator<String[]> verifyDataProviderValidation() throws IOException, IOException{
		return CSVReaderUtil.readFromCSV("PasswordPolicyValidation-master-data.csv").iterator();
	}
		@Test(dataProvider = "updateValidationPasswordPolicy")
	
		public void updateValidationPasswordPolicy(String name, String DaysToExpire, String PasswordTypeId, String DisallowLastPassword,
				String IsNumeral, String IsLowerCase, String MinimumLength, String IsUpperCase, String IsSpecialCase,
				String NotificationDays, String IsDefault, String StatusId, String message) {
			RestAssured.baseURI = resturi.BASE_URI;
			Query query = new Query();
			query.setQuery("query ($iPasswordPolicyId: Int, $sPasswordPolicyName: String, $iDaysToExpire: Int, $iPasswordTypeId: Int, $iDisallowLastPassword: Int, $bIsNumeral: Boolean, $bIsLowerCase: Boolean, $iMinimumLength: Int, $bIsUpperCase: Boolean, $bIsSpecialCase: Boolean, $iNotificationDays: Int, $bIsDefault: Boolean, $iStatusId: Int) {\r\n"
					+ "  updatePasswordPolicy(\r\n"
					+ "    iPasswordPolicyId: $iPasswordPolicyId\r\n"
					+ "    sPasswordPolicyName: $sPasswordPolicyName\r\n"
					+ "    iDaysToExpire: $iDaysToExpire\r\n"
					+ "    iPasswordTypeId: $iPasswordTypeId\r\n"
					+ "    iDisallowLastPassword: $iDisallowLastPassword\r\n"
					+ "    bIsNumeral: $bIsNumeral\r\n"
					+ "    bIsLowerCase: $bIsLowerCase\r\n"
					+ "    iMinimumLength: $iMinimumLength\r\n"
					+ "    bIsUpperCase: $bIsUpperCase\r\n"
					+ "    bIsSpecialCase: $bIsSpecialCase\r\n"
					+ "    iNotificationDays: $iNotificationDays\r\n"
					+ "    bIsDefault: $bIsDefault\r\n"
					+ "    iStatusId: $iStatusId\r\n"
					+ "  ) {\r\n"
					+ "    success\r\n"
					+ "    message\r\n"
					+ "    messagecode\r\n"
					+ "  }\r\n"
					+ "}\r\n"
					+ "");
			
			int passwordpolicyID = getID.passwordpolicyID("AT_UpdatePasswordPolicyValidation");
			Map<String, Object> PasswordPolicyName = new HashMap<String, Object>();
			PasswordPolicyName.put("iPasswordPolicyId", passwordpolicyID);
			PasswordPolicyName.put("sPasswordPolicyName", name);
			PasswordPolicyName.put("iDaysToExpire",Integer.parseInt(DaysToExpire));
			PasswordPolicyName.put("iPasswordTypeId", Integer.parseInt(PasswordTypeId));
			PasswordPolicyName.put("iDisallowLastPassword", Integer.parseInt(DisallowLastPassword));
			PasswordPolicyName.put("bIsNumeral", Boolean.parseBoolean(IsNumeral));
			PasswordPolicyName.put("bIsLowerCase", Boolean.parseBoolean(IsLowerCase));
			PasswordPolicyName.put("iMinimumLength", Integer.parseInt(MinimumLength));
			PasswordPolicyName.put("bIsUpperCase", Boolean.parseBoolean(IsUpperCase));
			PasswordPolicyName.put("bIsSpecialCase", Boolean.parseBoolean(IsSpecialCase));
			PasswordPolicyName.put("iNotificationDays", Integer.parseInt(NotificationDays));
			PasswordPolicyName.put("bIsDefault", Boolean.parseBoolean(IsDefault));
			PasswordPolicyName.put("iStatusId", Integer.parseInt(StatusId));
			query.setVariables(PasswordPolicyName);
			given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
			.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
			.body("data.updatePasswordPolicy.message", Matchers.equalToIgnoringCase(message));
		}
		
	//----------------------------------------Delete--------------------------------------//
	
	@DataProvider
	public Object [][] deletePasswordPolicy() {
		return new Object [][] {{"AT_UpdatePasswordPolicyNew",10,1,1,true,true,8,true,false,1,false,0 }};
	}
	
	@Test(dataProvider = "deletePasswordPolicy")
	public void deletePasswordPolicy(String name, int DaysToExpire, int PasswordTypeId, int DisallowLastPassword,
			boolean IsNumeral, boolean IsLowerCase, int MinimumLength, boolean IsUpperCase, boolean IsSpecialCase,
			int NotificationDays, boolean IsDefault, int StatusId) {
		RestAssured.baseURI = resturi.BASE_URI;
		Query query = new Query();
		getpasswordpolicyID getID = new getpasswordpolicyID();
		query.setQuery("query ($iPasswordPolicyId: Int!) {\r\n"
				+ "  deletePasswordPolicy(iPasswordPolicyId: $iPasswordPolicyId) {\r\n"
				+ "    success\r\n"
				+ "    message\r\n"
				+ "    messagecode\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "");
		
		int passwordpolicyID = getID.passwordpolicyID("AT_DeletePasswordPolicy");
		Map<String, Object> PasswordPolicyName = new HashMap<String, Object>();
		PasswordPolicyName.put("iPasswordPolicyId", passwordpolicyID);
		query.setVariables(PasswordPolicyName);
		given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(query).when()
		.log().all().post("/graphql").then().log().all().assertThat().statusCode(200)
		.body("data.deletePasswordPolicy.message", Matchers.equalToIgnoringCase("Record Deleted Successfully"));
	}
}