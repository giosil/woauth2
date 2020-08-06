package org.dew.oauth2;

/**
 * Interface for OAuth 2.0 protocol support.
 */
public 
interface IOAuth2 
{
  /**
   * Validation authorization request.
   * 
   * @param authorizationRequest AuthorizationRequest
   * @return OAuthError, null if authorizationRequest is valid.
   */
  public OAuthError validateAuthorizationRequest(AuthorizationRequest authorizationRequest);
  
  /**
   * Validation token request.
   * 
   * @param tokenRequest TokenRequest
   * @return OAuthError, null if tokenRequest is valid.
   */
  public OAuthError validateTokenRequest(TokenRequest tokenRequest);
  
  /**
   * Request token authorization.
   * 
   * @param authorizationRequest AuthorizationRequest
   * @return AuthorizationResponse, null if not authenticated / authorized.
   * @throws Exception
   */
  public AuthorizationResponse requestAuthorization(AuthorizationRequest authorizationRequest) throws Exception;
  
  /**
   * Request token.
   * 
   * @param tokenRequest TokenRequest
   * @return TokenResponse, null if not authenticated / authorized.
   * @throws Exception
   */
  public TokenResponse requestToken(TokenRequest tokenRequest) throws Exception;
  
  /**
   * Get user info.
   * 
   * @param token String
   * @return UserInfo, null if token is not valid.
   * @throws Exception
   */
  public UserInfo getUserInfo(String token) throws Exception;
}
