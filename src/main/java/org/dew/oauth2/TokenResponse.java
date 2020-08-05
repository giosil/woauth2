package org.dew.oauth2;

import java.io.Serializable;

public 
class TokenResponse implements Serializable
{
  private static final long serialVersionUID = -6390127889288970145L;
  
  private String accessToken;
  private String tokenType;
  private int expiresIn;
  private String refreshToken;
  private String scope;
  private String idToken;
  
  public TokenResponse()
  {
  }
  
  public TokenResponse(String accessToken)
  {
    this.accessToken = accessToken;
    this.tokenType   = "bearer";
  }
  
  public TokenResponse(String accessToken, String tokenType)
  {
    this.accessToken = accessToken;
    this.tokenType   = tokenType;
  }
  
  public TokenResponse(String accessToken, String tokenType, int expiresIn)
  {
    this.accessToken = accessToken;
    this.tokenType   = tokenType;
    this.expiresIn   = expiresIn;
  }
  
  public TokenResponse(String accessToken, String tokenType, int expiresIn, String scope)
  {
    this.accessToken = accessToken;
    this.tokenType   = tokenType;
    this.expiresIn   = expiresIn;
    this.scope       = scope;
  }
  
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getIdToken() {
    return idToken;
  }

  public void setIdToken(String idToken) {
    this.idToken = idToken;
  }

  public String toJson() {
    if(accessToken == null) accessToken = "";
    if(tokenType   == null) tokenType   = "bearer";
    String result = "{";
    result += "\"access_token\":\"" + accessToken.replace('"', '\'') + "\"";
    result += ",\"token_type\":\"" + tokenType.replace('"', '\'') + "\"";
    result += ",\"expires_in\":" + expiresIn;
    if(refreshToken != null && refreshToken.length() > 0) {
      result += ",\"refresh_token\":\"" + refreshToken.replace('"', '\'') + "\"";
    }
    if(scope != null && scope.length() > 0) {
      result += ",\"scope\":\"" + scope.replace('"', '\'') + "\"";
    }
    if(idToken != null && idToken.length() > 0) {
      result += ",\"id_token\":\"" + idToken.replace('"', '\'') + "\"";
    }
    result += "}";
    return result;
  }
  
  @Override
  public boolean equals(Object object) {
    if(object instanceof TokenResponse) {
      String sAccessToken = ((TokenResponse) object).getAccessToken();
      if(accessToken == null && sAccessToken == null) return true;
      return accessToken != null && accessToken.equals(sAccessToken);
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    if(accessToken == null) return 0;
    return accessToken.hashCode();
  }
  
  @Override
  public String toString() {
    return "TokenResponse(" + accessToken + "," + expiresIn + ")";
  }
}
