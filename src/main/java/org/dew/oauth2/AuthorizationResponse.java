package org.dew.oauth2;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public 
class AuthorizationResponse implements Serializable
{
  private static final long serialVersionUID = 1226914339886200052L;
  
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
  
  public String toQueryString() {
    StringBuilder sb = new StringBuilder();
    Utils.appendParam(sb, "code",  code);
    Utils.appendParam(sb, "state", state);
    return sb.toString();
  }
  
  public Map<String, Object> toMap() {
    Map<String, Object> mapResult = new HashMap<String, Object>();
    mapResult.put("code",  code);
    mapResult.put("state", state);
    return mapResult;
  }
  
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
