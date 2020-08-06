package org.dew.oauth2;

import java.util.UUID;

/**
 * Test implementation of IOAuth2.
 */
public 
class MockOAuth2 implements IOAuth2 
{
  @Override
  public OAuthError validateAuthorizationRequest(AuthorizationRequest authorizationRequest) {
    
    String responseType = authorizationRequest.getResponseType();
    String clientId     = authorizationRequest.getClientId();
    String clientSecret = authorizationRequest.getClientSecret();
    String scope        = authorizationRequest.getScope();
    String redirectURI  = authorizationRequest.getRedirectURI();
    
    if(responseType == null || responseType.length() == 0) {
      return new OAuthError(400, "invalid_request", "Request was missing the 'response_type' parameter.");
    }
    
    if(redirectURI == null || redirectURI.length() == 0) {
      return new OAuthError(400, "invalid_request", "Request was missing the 'redirect_uri' parameter.");
    }
    
    if(!responseType.equals("code")) {
      return new OAuthError(400, "unsupported_response_type", "Unsupported response type.");
    }
    
    if(clientId != null && clientId.length() > 0 && !clientId.equals("test")) {
      return new OAuthError(401, "invalid_client", "Invalid client.");
    }
    
    if(scope != null && scope.length() > 0 && scope.length() < 2) {
      return new OAuthError(401, "invalid_scope", "Invalid scope.");
    }
    
    if(clientSecret != null && clientSecret.length() > 0) {
      try {
        boolean clientAuthenticated = authenticateClient(clientId, clientSecret);
        
        if(!clientAuthenticated) {
          return new OAuthError(401, "unauthorized_client", "Unauthorized client.");
        }
      }
      catch(Exception ex) {
        return new OAuthError(ex);
      }
    }
    
    return null;
  }
  
  @Override
  public OAuthError validateTokenRequest(TokenRequest tokenRequest) {
    
    String grantType    = tokenRequest.getGrantType();
    String username     = tokenRequest.getUsername();
    String password     = tokenRequest.getPassword();
    String clientId     = tokenRequest.getClientId();
    String clientSecret = tokenRequest.getClientSecret();
    String scope        = tokenRequest.getScope();
    
    if(grantType == null || grantType.length() == 0) {
      return new OAuthError(400, "invalid_request", "Request was missing the 'grant_type' parameter.");
    }
    
    if(!"password".equals(grantType)) {
      return new OAuthError(400, "unsupported_grant_type", "Unsupported grant_type.");
    }
    
    if(username == null || username.length() == 0) {
      return new OAuthError(400, "invalid_request", "Request was missing the 'username' parameter.");
    }
    
    if(password == null || password.length() == 0) {
      return new OAuthError(400, "invalid_request", "Request was missing the 'password' parameter.");
    }
    
    if(clientId != null && clientId.length() > 0 && !clientId.equals("test")) {
      return new OAuthError(401, "invalid_client", "Invalid client.");
    }
    
    if(scope != null && scope.length() > 0 && scope.length() < 2) {
      return new OAuthError(401, "invalid_scope", "Invalid scope.");
    }
    
    if(clientSecret != null && clientSecret.length() > 0) {
      try {
        boolean clientAuthenticated = authenticateClient(clientId, clientSecret);
        
        if(!clientAuthenticated) {
          return new OAuthError(401, "unauthorized_client", "Unauthorized client.");
        }
      }
      catch(Exception ex) {
        return new OAuthError(ex);
      }
    }
    
    return null;
  }
  
  @Override
  public AuthorizationResponse requestAuthorization(AuthorizationRequest authorizationRequest) throws Exception {
    
    String state = authorizationRequest.getState();
    
    if(state != null && state.length() > 0) {
      return new AuthorizationResponse(UUID.randomUUID().toString(), state);
    }
    
    return null;
  }
  
  @Override
  public TokenResponse requestToken(TokenRequest tokenRequest) throws Exception {
    
    if(tokenRequest == null) return null;
    
    String accessToken = authenticate(tokenRequest);
    
    if(accessToken == null || accessToken.length() == 0) {
      return null;
    }
    
    return new TokenResponse(accessToken, "bearer", 3600, tokenRequest.getScope());
  }

  @Override
  public UserInfo getUserInfo(String token) throws Exception {
    
    if(token == null || token.length() == 0) {
      return null;
    }
    
    return new UserInfo("12345", "Test", "Dev", "admin", "test.dev@example.com");
  }
  
  protected
  String authenticate(TokenRequest tokenRequest)
      throws Exception
  {
    if(tokenRequest == null) return null;
    
    String username     = tokenRequest.getUsername();
    String password     = tokenRequest.getPassword();
    
    if(username == null || username.length() == 0) {
      return null;
    }
    if(password == null || password.length() == 0) {
      return null;
    }
    
    if(password.equals("pass1234")) {
      return UUID.randomUUID().toString();
    }
    
    return null;
  }
  
  protected
  boolean authenticateClient(String clientId, String clientSecret)
      throws Exception
  {
    if(clientId == null || clientId.length() == 0) {
      return false;
    }
    if(clientSecret == null || clientSecret.length() == 0) {
      return false;
    }
    
    if(clientId.equals(clientSecret)) {
      return true;
    }
    
    return false;
  }
}
