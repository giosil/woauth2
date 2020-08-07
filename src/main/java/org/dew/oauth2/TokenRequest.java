package org.dew.oauth2;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Bean TokenRequest.
 */
public 
class TokenRequest implements IOAuthObject, Serializable
{
  private static final long serialVersionUID = -2059996650185044534L;
  
  private String grantType; // authorization_code, password, client_credentials, refresh_token
  private String username;
  private String password;
  private String clientId;
  private String clientSecret;
  private String scope;
  // Authorization
  private String refreshToken;
  private String tenant;
  private String code;
  private String codeVerifier;
  private String redirectURI;
  
  public TokenRequest()
  {
  }
  
  public TokenRequest(String grantType, String username, String password, String clientId)
  {
    this.grantType = grantType;
    this.username  = username;
    this.password  = password;
    this.clientId  = clientId;
  }
  
  public TokenRequest(HttpServletRequest request)
  {
    this.grantType    = request.getParameter("grant_type");
    this.username     = request.getParameter("username");
    this.password     = request.getParameter("password");
    this.clientId     = request.getParameter("client_id");
    this.clientSecret = request.getParameter("client_secret");
    this.scope        = request.getParameter("scope");
    
    this.refreshToken = request.getParameter("refresh_token");
    this.tenant       = request.getParameter("tenant");
    this.code         = request.getParameter("code");
    this.codeVerifier = request.getParameter("code_verifier");
    this.redirectURI  = request.getParameter("redirect_uri");
  }
  
  public TokenRequest(Map<String, Object> map)
  {
    this.grantType    = Utils.toString(map.get("grant_type"));
    this.username     = Utils.toString(map.get("username"));
    this.password     = Utils.toString(map.get("password"));
    this.clientId     = Utils.toString(map.get("client_id"));
    this.clientSecret = Utils.toString(map.get("client_secret"));
    this.scope        = Utils.toString(map.get("scope"));
    
    this.refreshToken = Utils.toString(map.get("refresh_token"));
    this.tenant       = Utils.toString(map.get("tenant"));
    this.code         = Utils.toString(map.get("code"));
    this.codeVerifier = Utils.toString(map.get("code_verifier"));
    this.redirectURI  = Utils.toString(map.get("redirect_uri"));
  }
  
  public String getGrantType() {
    return grantType;
  }

  public void setGrantType(String grantType) {
    this.grantType = grantType;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCodeVerifier() {
    return codeVerifier;
  }

  public void setCodeVerifier(String codeVerifier) {
    this.codeVerifier = codeVerifier;
  }

  public String getRedirectURI() {
    return redirectURI;
  }

  public void setRedirectURI(String redirectURI) {
    this.redirectURI = redirectURI;
  }
  
  // IOAuthObject
  
  public Map<String, Object> toMap() {
    Map<String, Object> mapResult = new LinkedHashMap<String, Object>();
    mapResult.put("grant_type",    grantType);
    mapResult.put("username",      username);
    mapResult.put("password",      password);
    mapResult.put("client_id",     clientId);
    mapResult.put("client_secret", clientSecret);
    mapResult.put("scope",         scope);
    mapResult.put("refresh_token", refreshToken);
    mapResult.put("tenant",        tenant);
    mapResult.put("code",          code);
    mapResult.put("code_verifier", codeVerifier);
    mapResult.put("redirect_uri",  redirectURI);
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
    if(object instanceof TokenRequest) {
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
    return "TokenRequest(" + grantType + "," + username + "," + code + ")";
  }
}
