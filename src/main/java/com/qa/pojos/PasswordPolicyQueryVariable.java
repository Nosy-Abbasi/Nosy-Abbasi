package com.qa.pojos;

public class PasswordPolicyQueryVariable {
	
    private int iLimit;
	private int iPageNo;
	
	private String name;
	private int iPasswordPolicyId;
	
	private int iStatusId;
	private int iPasswordTypeId;
	private int iNotificationDays;
	private int iMinimumLength;
	private int iDisallowLastPassword;
	private int iDaysToExpire;
	private boolean bIsUpperCase;
	private boolean bIsSpecialCase;
	private boolean bIsNumeral;
	private boolean bIsLowerCase;
	private boolean bIsDefault;
	
	public int getPage() {
		return iPageNo;
	   }
	public void setpage(int iPageNo) {
		   this.iPageNo = iPageNo;
	   }
		
	public int getLimit() {
		   return iLimit;
	   }
	   
	   public void setLimit(int iLimit) {
		   this.iLimit=iLimit;
	   }

	   public String getName() {
		   return name;
	   }
	   
	   public void setName(String name) {
		   this.name=name;
	   }
	   
	   public int getid() {
		   return iPasswordPolicyId;
	   }
	   
	   public void setid(int iPasswordPolicyId) {
		   this.iPasswordPolicyId=iPasswordPolicyId;
	   }
	   
	   public int getstatusid() {
		   return iStatusId;
	   }
	   
	   public void setstatusid(int iStatusId) {
		   this.iStatusId=iStatusId;
	   }
	   
	   
	   public int getPasswordTypeId() {
		   return iPasswordTypeId;
	   }
		   public void setPasswordTypeId(int iPasswordTypeId) {
			   this.iPasswordTypeId=iPasswordTypeId;
	   }
		   
		   
		public int getNotificationDays() {
			return iNotificationDays;
		}
		public void setNotificationDays(int iNotificationDays ) {
			this.iNotificationDays=iNotificationDays;
		}

	   public int getMinimumLength() {
		   return iMinimumLength;
	   }
	   public void setMinimumLength(int iMinimumLength) {
		   this.iMinimumLength=iMinimumLength;
	   }
	   
	   public int getDisallowLastPassword() {
		   return iDisallowLastPassword;
	   }
	   public void setDisallowLastPassword(int iDisallowLastPassword) {
		   this.iDisallowLastPassword=iDisallowLastPassword;
	   }
	   
	   public int getDaysToExpire() {
		   return iDaysToExpire;
	   }
	   public void setDaysToExpire(int iDaysToExpire) {
		   this.iDaysToExpire=iDaysToExpire;
	   }
	   
	   public boolean getIsUpperCase() {
		   return bIsUpperCase;
	   }
	   public void setIsUpperCase(boolean bIsUpperCase) {
		   this.bIsUpperCase=bIsUpperCase;
	   }
	   
	   public boolean getIsSpecialCase() {
		   return bIsSpecialCase;
	   }
	   public void setIsSpecialCase(boolean bIsSpecialCase) {
		   this.bIsSpecialCase=bIsSpecialCase;
	   }
	   
	   public boolean getIsNumeral() {
		   return bIsNumeral;
	   }
	   public void setIsNumeral(boolean bIsNumeral) {
		   this.bIsNumeral=bIsNumeral;
	   }
	   
	   public boolean getIsLowerCase() {
		   return bIsLowerCase;
	   }
	   public void setIsLowerCase(boolean bIsLowerCase) {
		   this.bIsLowerCase=bIsLowerCase;
	   }
	   
	   public boolean getIsDefault() {
		   return bIsDefault;
	   }
	   public void setIsDefault(boolean bIsDefault) {
		   this.bIsDefault=bIsDefault;
	   }
}