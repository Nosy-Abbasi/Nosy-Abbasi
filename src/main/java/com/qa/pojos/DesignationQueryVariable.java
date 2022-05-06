package com.qa.pojos;

public class DesignationQueryVariable {
	
	private int iLimit;
	
	private int iPageNo;
	private String name;
	
	private int iDesignationId;
	 
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
	   return iDesignationId;
   }
   
   public void setid(int iDesignationId) {
	   this.iDesignationId=iDesignationId;
   }
}