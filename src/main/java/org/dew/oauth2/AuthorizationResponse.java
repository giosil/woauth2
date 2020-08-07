package org.dew.oauth2;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public 
class AuthorizationResponse implements IOAuthObject, Serializable
{
  private static final long serialVersionUID = -5352209232102547902L;
  
  private String code;
  private String state;
  
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
  }
  
  public AuthorizationResponse(Map<String, Object> map)
  {
    this.code  = Utils.toString(map.get("code"));
    this.state = Utils.toString(map.get("state"));
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
  
  // IOAuthObject
  
  @Override
  public Map<String, Object> toMap() {
    Map<String, Object> mapResult = new LinkedHashMap<String, Object>();
    mapResult.put("code",  code);
    mapResult.put("state", state);
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
