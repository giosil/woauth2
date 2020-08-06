package org.dew.oauth2;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean TokenResponse.
 */
public 
class TokenResponse implements Serializable
{
  private static final long serialVersionUID = 1189179673497581828L;
  
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
    if(state != null && state.length() > 0) {
      result += ",\"state\":\"" + state.replace('"', '\'') + "\"";
    }
    result += "}";
    return result;
  }
  
  public Map<String, Object> toMap() {
    Map<String, Object> mapResult = new HashMap<String, Object>();
    mapResult.put("access_token",  accessToken);
    mapResult.put("token_type",    tokenType);
    mapResult.put("expires_in",    expiresIn);
    mapResult.put("refresh_token", refreshToken);
    mapResult.put("scope",         scope);
    mapResult.put("state",         state);
    return mapResult;
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
