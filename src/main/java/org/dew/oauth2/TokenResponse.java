package org.dew.oauth2;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Bean TokenResponse.
 */
public 
class TokenResponse implements IOAuthObject, Serializable
{
  private static final long serialVersionUID = -7236538781537416017L;
  
  private String accessToken;   // REQUIRED
  private String tokenType;     // REQUIRED
  private int    expiresIn;     // RECOMMENDED
  private String refreshToken;  // OPTIONAL
  private String scope;         // OPTIONAL
  private String state;         // REQUIRED if present in the client authorization request.
  
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
  
  public TokenResponse(Map<String, Object> map)
  {
    if(map == null) return;
    
    this.accessToken  = Utils.toString(map.get("access_token"));
    this.tokenType    = Utils.toString(map.get("token_type"));
    this.expiresIn    = Utils.toInt(map.get("expires_in"));
    this.refreshToken = Utils.toString(map.get("refresh_token"));
    this.scope        = Utils.toString(map.get("scope"));
    this.state        = Utils.toString(map.get("state"));
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

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
  
  // IOAuthObject
    
  public Map<String, Object> toMap() {
    Map<String, Object> mapResult = new LinkedHashMap<String, Object>();
    mapResult.put("access_token",  accessToken);
    mapResult.put("token_type",    tokenType);
    mapResult.put("expires_in",    expiresIn);
    mapResult.put("refresh_token", refreshToken);
    mapResult.put("scope",         scope);
    mapResult.put("state",         state);
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
