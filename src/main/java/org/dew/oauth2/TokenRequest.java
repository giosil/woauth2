package org.dew.oauth2;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public 
class TokenRequest implements Serializable
{
  private static final long serialVersionUID = 386263065295718046L;
  
  private String grantType;
  private String username;
  private String password;
  private String clientId;
  private String clientSecret;
  private String scope;
  // Authorization
  private String tenant;
  private String code;
  private String codeVerifier;
  private String redirectURI;
  
  public TokenRequest()
  {
  }
  
  public TokenRequest(HttpServletRequest request)
  {
    this.grantType    = request.getParameter("grant_type");
    this.username     = request.getParameter("username");
    this.password     = request.getParameter("password");
    this.clientId     = request.getParameter("client_id");
    this.clientSecret = request.getParameter("client_secret");
    this.scope        = request.getParameter("scope");
    this.tenant       = request.getParameter("tenant");
    this.code         = request.getParameter("code");
    this.codeVerifier = request.getParameter("code_verifier");
    this.redirectURI  = request.getParameter("redirect_uri");
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
