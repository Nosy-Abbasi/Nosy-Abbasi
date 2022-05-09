package com.qa.pojos;

public class DocumentHeadQueryVariable {
	
	 private int iLimit;
		private int iPageNo;
		
		private String name;
		private int iDocumentHeadId;
		
		private int iDocumentCategoryId;
		private String sDocumentCategoryName;
		
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
			   return iDocumentHeadId;
		   }
		   
		   public void setid(int iDocumentHeadId) {
			   this.iDocumentHeadId=iDocumentHeadId;
		   }
		   
		   public int getDocumentCategoryId() {
			   return iDocumentCategoryId;
		   }
		   
		   public void setDocumentCategoryId(int iDocumentCategoryId) {
			   this.iDocumentCategoryId=iDocumentCategoryId;
		   }
		   
		   public String getDocumentCategoryName() {
			   return sDocumentCategoryName;
		   }
		   
		   public void setDocumentCategoryName(String sDocumentCategoryName) {
			   this.sDocumentCategoryName=sDocumentCategoryName;
		   }
}