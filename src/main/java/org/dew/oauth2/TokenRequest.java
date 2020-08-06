package org.dew.oauth2;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Bean TokenRequest.
 */
public 
class TokenRequest implements Serializable
{
  private static final long serialVersionUID = -661009814854940618L;
  
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

  public String toQueryString() {
    StringBuilder sb = new StringBuilder();
    Utils.appendParam(sb, "grant_type",    grantType);
    Utils.appendParam(sb, "username",      username);
    Utils.appendParam(sb, "password",      password);
    Utils.appendParam(sb, "client_id",     clientId);
    Utils.appendParam(sb, "client_secret", clientSecret);
    Utils.appendParam(sb, "scope",         scope);
    Utils.appendParam(sb, "refresh_token", refreshToken);
    Utils.appendParam(sb, "tenant",        tenant);
    Utils.appendParam(sb, "code",          code);
    Utils.appendParam(sb, "code_verifier", codeVerifier);
    Utils.appendParam(sb, "redirect_uri",  redirectURI);
    return sb.toString();
  }
  
  public Map<String, Object> toMap() {
    Map<String, Object> mapResult = new HashMap<String, Object>();
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
