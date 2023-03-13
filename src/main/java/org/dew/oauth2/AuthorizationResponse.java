package org.dew.oauth2;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public 
class AuthorizationResponse implements IOAuthObject, Serializable
{
  private static final long serialVersionUID = 6019253093762604892L;

  private String code;
  private String state;
  private String error;
  private String errorDescription;
  
  public AuthorizationResponse()
  {
  }
  
  public AuthorizationResponse(String code)
  {
    this.code  = code;
  }
  
  public AuthorizationResponse(String code, String state)
  {
    this.code  = code;
    this.state = state;
  }
  
  public AuthorizationResponse(HttpServletRequest request)
  {
    this.code  = request.getParameter("code");
    this.state = request.getParameter("state");
    this.error = request.getParameter("error");
    this.errorDescription = request.getParameter("error_description");
  }
  
  public AuthorizationResponse(Map<String, Object> map)
  {
    fromMap(map);
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
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

  // IOAuthObject

  @Override
  public void fromMap(Map<String, Object> map) {
    if(map == null) return;
    
    this.code  = Utils.toString(map.get("code"));
    this.state = Utils.toString(map.get("state"));
    this.error = Utils.toString(map.get("error"));
    this.errorDescription = Utils.toString(map.get("error_description"));
  }
  
  @Override
  public Map<String, Object> toMap() {
    Map<String, Object> mapResult = new LinkedHashMap<String, Object>();
    mapResult.put("code",  code);
    mapResult.put("state", state);
    mapResult.put("error", error);
    mapResult.put("error_description", errorDescription);
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
    if(object instanceof AuthorizationResponse) {
      String sCode = ((AuthorizationResponse) object).getCode();
      if(code == null && sCode == null) return true;
      return code != null && code.equals(sCode);
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    if(code == null) return 0;
    return code.hashCode();
  }
  
  @Override
  public String toString() {
    return "TokenResponse(" + code + "," + state + ")";
  }
}
