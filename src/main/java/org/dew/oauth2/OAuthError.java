package org.dew.oauth2;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Bean error.
 */
public 
class OAuthError implements IOAuthObject, Serializable
{
  private static final long serialVersionUID = 8368264550128374309L;
  
  private int    httpStatus;
  private String error;
  private String errorDescription;
  private String errorURI;
  private String state;
  
  public final static OAuthError SERVER_ERROR        = new OAuthError(500, "server_error",        "Internal server error.");
  public final static OAuthError INVALID_REQUEST     = new OAuthError(400, "invalid_request",     "Invalid request.");
  public final static OAuthError UNAUTHORIZED_CLIENT = new OAuthError(400, "unauthorized_client", "Unauthorized client.");
  public final static OAuthError INVALID_CLIENT      = new OAuthError(401, "invalid_client",      "Invalid client.");
  public final static OAuthError INVALID_SCOPE       = new OAuthError(401, "invalid_scope",       "Invalid scope.");
  public final static OAuthError ACCESS_DENIED       = new OAuthError(401, "access_denied",       "Access denied.");
  public final static OAuthError INVALID_TOKEN       = new OAuthError(403, "invalid_token",       "Invalid token.");
  
  public OAuthError()
  {
  }
  
  public OAuthError(int httpStatus, String error, String errorDescription)
  {
    this.httpStatus       = httpStatus;
    this.error            = error;
    this.errorDescription = errorDescription;
  }
  
  public OAuthError(int httpStatus, String error, String errorDescription, String errorURI)
  {
    this.httpStatus       = httpStatus;
    this.error            = error;
    this.errorDescription = errorDescription;
    this.errorURI         = errorURI;
  }
  
  public OAuthError(String error, String errorDescription)
  {
    this.error            = error;
    this.errorDescription = errorDescription;
  }
  
  public OAuthError(String error, String errorDescription, String errorURI)
  {
    this.error            = error;
    this.errorDescription = errorDescription;
    this.errorURI         = errorURI;
  }
  
  public OAuthError(Exception  ex)
  {
    this.httpStatus       = 500;
    this.error            = "server_error";
    this.errorDescription = ex != null ? ex.toString() : "Internal server error";
  }
  
  public OAuthError(HttpServletRequest request)
  {
    this.httpStatus       = 302; // Found
    this.error            = request.getParameter("error");
    this.errorDescription = request.getParameter("error_description");
    this.errorURI         = request.getParameter("error_uri");
    this.state            = request.getParameter("state");
  }
  
  public OAuthError(Map<String, Object> map)
  {
    fromMap(map);
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  public String getErrorURI() {
    return errorURI;
  }

  public void setErrorURI(String errorURI) {
    this.errorURI = errorURI;
  }

  public int getHttpStatus() {
    if(httpStatus < 200) httpStatus = 400;
    return httpStatus;
  }

  public void setHttpStatus(int httpStatus) {
    this.httpStatus = httpStatus;
  }
  
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
  
  // IOAuthObject
  
  @Override
  public void fromMap(Map<String, Object> map) {
    if(map == null) return;
    
    this.httpStatus       = Utils.toInt(map.get("http_status"));
    this.error            = Utils.toString(map.get("error"));
    this.errorDescription = Utils.toString(map.get("error_description"));
    this.errorURI         = Utils.toString(map.get("error_uri"));
    this.state            = Utils.toString(map.get("state"));
  }
  
  public Map<String, Object> toMap() {
    Map<String, Object> mapResult = new LinkedHashMap<String, Object>();
    mapResult.put("error",             error);
    mapResult.put("error_description", errorDescription);
    mapResult.put("error_uri",         errorURI);
    mapResult.put("state",             state);
    return mapResult;
  }
  
  @Override
  public String toQueryString() {
    return Utils.toQueryString(toMap());
  }
  
  @Override
  public String toJSON() {
    return Utils.toJSON(toMap());
  }
  
  @Override
  public String toHeaderValue() {
    return Utils.toHeaderValue(toMap());
  }
  
  // Object
  
  @Override
  public boolean equals(Object object) {
    if(object instanceof OAuthError) {
      String sError = ((OAuthError) object).getError();
      if(error == null && sError == null) return true;
      return error != null && error.equals(sError);
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    if(error == null) return 0;
    return error.hashCode();
  }
  
  @Override
  public String toString() {
    return "OAuthError(" + error + "," + errorDescription + ")";
  }
}
