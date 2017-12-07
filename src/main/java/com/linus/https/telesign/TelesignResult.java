package com.linus.https.telesign;

public class TelesignResult {
	private boolean success;
    private String errorMessgae;
    private String referenceId;
    private String statusCode;
    
    public TelesignResult() {
        super();
        success = false;
        errorMessgae = "errorMessgae placeHolder";
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getErrorMessgae() {
        return errorMessgae;
    }
    public void setErrorMessgae(String errorMessgae) {
        this.errorMessgae = errorMessgae;
    }
    public String getReferenceId() {
        return referenceId;
    }
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
