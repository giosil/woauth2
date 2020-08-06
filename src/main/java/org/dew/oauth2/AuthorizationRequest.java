package org.dew.oauth2;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public 
class AuthorizationRequest implements Serializable
{
  private static final long serialVersionUID = 315462885487810167L;
  
  private String responseType;  // REQUIRED
  private String clientId;      // REQUIRED
  private String clientSecret;  // OPTIONAL
  private String scope;         // OPTIONAL
  private String state;         // RECOMMENDED
  private String redirectURI;   // REQUIRED
  
  public AuthorizationRequest()
  {
    this.responseType = "code";
  }
  
  public AuthorizationRequest(String clientId, String state, String redirectURI)
  {
    this.responseType = "code";
    this.clientId     = clientId;
    this.state        = state;
    this.redirectURI  = redirectURI;
  }
  
  public AuthorizationRequest(HttpServletRequest request)
  {
    this.responseType = request.getParameter("response_type");
    this.clientId     = request.getParameter("client_id");
    this.clientSecret = request.getParameter("client_secret");
    this.scope        = request.getParameter("scope");
    this.state        = request.getParameter("state");
    this.redirectURI  = request.getParameter("redirect_uri");
    
    if(responseType == null || responseType.length() == 0) {
      responseType = "code";
    }
  }
  
  public AuthorizationRequest(Map<String, Object> map)
  {
    this.responseType = Utils.toString(map.get("response_type"));
    this.clientId     = Utils.toString(map.get("client_id"));
    this.clientSecret = Utils.toString(map.get("client_secret"));
    this.scope        = Utils.toString(map.get("scope"));
    this.state        = Utils.toString(map.get("state"));
    this.redirectURI  = Utils.toString(map.get("redirect_uri"));
    
    if(responseType == null || responseType.length() == 0) {
      responseType = "code";
    }
  }
  
  public String getResponseType() {
    return responseType;
  }

  public void setResponseType(String responseType) {
    this.responseType = responseType;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getRedirectURI() {
    if(redirectURI == null) redirectURI = "";
    return redirectURI;
  }

  public void setRedirectURI(String redirectURI) {
    this.redirectURI = redirectURI;
  }

  public String toQueryString() {
    if(responseType == null || responseType.length() == 0) {
      responseType = "code";
    }
    
    StringBuilder sb = new StringBuilder();
    Utils.appendParam(sb, "response_type", responseType);
    Utils.appendParam(sb, "client_id",     clientId);
    Utils.appendParam(sb, "client_secret", clientSecret);
    Utils.appendParam(sb, "scope",         scope);
    Utils.appendParam(sb, "state",         state);
    Utils.appendParam(sb, "redirect_uri",  redirectURI);
    return sb.toString();
  }
  
  public Map<String, Object> toMap() {
    if(responseType == null || responseType.length() == 0) {
      responseType = "code";
    }
    
    Map<String, Object> mapResult = new HashMap<String, Object>();
    mapResult.put("response_type", responseType);
    mapResult.put("client_id",     clientId);
    mapResult.put("client_secret", clientSecret);
    mapResult.put("scope",         scope);
    mapResult.put("state",         state);
    mapResult.put("redirect_uri",  redirectURI);
    return mapResult;
  }
  
  @Override
  public boolean equals(Object object) {
    if(object instanceof AuthorizationRequest) {
      return this.toString().equals(object.toString());
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return toString().hashCode();
  }
  
  @Override
  public String toString() {
    return "AuthorizationRequest(" + responseType + "," + clientId + "," + scope + "," + state + "," + redirectURI + ")";
  }
}
