package com.billhub.qa.utils;

public class Invoice {
	
	public String invoiceNumber;
    public String baseAmount;
    public String igst;
    public String subServiceCategory;
    public String cd;					// additional amount
    public String tcs;
    public String hsnCode;
    public String endCustomer;
    public String comment;
	public String quantity;
	public String submittingAt;
	public String submittingTo;

    public Invoice(String invoiceNumber, String baseAmount, String igst, String subServiceCategory, String cd,
                   String tcs, String hsnCode, String endCustomer, String comment, String quantity,String submittingAt, String submittingTo) {
        
    	this.invoiceNumber = invoiceNumber;
        this.baseAmount = baseAmount;
        this.igst = igst;
        this.subServiceCategory = subServiceCategory;
        this.cd = cd;
        this.tcs = tcs;
        this.hsnCode = hsnCode;
        this.endCustomer = endCustomer;
        this.comment = comment;
        this.quantity = quantity;
        this.submittingAt = submittingAt;
        this.submittingTo = submittingTo;
    }

}
