package org.dew.oauth2;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public 
class AuthorizationRequest implements IOAuthObject, Serializable
{
  private static final long serialVersionUID = -7301587844052799362L;
  
  private String responseType;  // REQUIRED
  private String clientId;      // REQUIRED
  private String clientSecret;  // OPTIONAL
  private String scope;         // OPTIONAL
  private String state;         // RECOMMENDED
  private String redirectURI;   // REQUIRED
  private String nonce;         // OPTIONAL
  private String login_hint;    // OPTIONAL (example: user name, email)
  private String hd;            // OPTIONAL (hosted domain)
  
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
    this.nonce        = request.getParameter("nonce");
    this.login_hint   = request.getParameter("login_hint");
    this.hd           = request.getParameter("hd");
    
    if(responseType == null || responseType.length() == 0) {
      responseType = "code";
    }
  }
  
  public AuthorizationRequest(Map<String, Object> map)
  {
    fromMap(map);
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
  
  public String getNonce() {
    return nonce;
  }

  public void setNonce(String nonce) {
    this.nonce = nonce;
  }

  public String getLogin_hint() {
    return login_hint;
  }

  public void setLogin_hint(String login_hint) {
    this.login_hint = login_hint;
  }

  public String getHd() {
    return hd;
  }

  public void setHd(String hd) {
    this.hd = hd;
  }
  
  // IOAuthObject
  
  @Override
  public void fromMap(Map<String, Object> map) {
    if(map == null) return;
    
    this.responseType = Utils.toString(map.get("response_type"));
    this.clientId     = Utils.toString(map.get("client_id"));
    this.clientSecret = Utils.toString(map.get("client_secret"));
    this.scope        = Utils.toString(map.get("scope"));
    this.state        = Utils.toString(map.get("state"));
    this.redirectURI  = Utils.toString(map.get("redirect_uri"));
    this.nonce        = Utils.toString(map.get("nonce"));
    this.login_hint   = Utils.toString(map.get("login_hint"));
    this.hd           = Utils.toString(map.get("hd"));
    
    if(responseType == null || responseType.length() == 0) {
      responseType = "code";
    }
  }
  
  @Override
  public Map<String, Object> toMap() {
    if(responseType == null || responseType.length() == 0) {
      responseType = "code";
    }
    Map<String, Object> mapResult = new LinkedHashMap<String, Object>();
    mapResult.put("response_type", responseType);
    mapResult.put("client_id",     clientId);
    mapResult.put("client_secret", clientSecret);
    mapResult.put("scope",         scope);
    mapResult.put("state",         state);
    mapResult.put("redirect_uri",  redirectURI);
    mapResult.put("nonce",         nonce);
    mapResult.put("login_hint",    login_hint);
    mapResult.put("hd",            hd);
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
