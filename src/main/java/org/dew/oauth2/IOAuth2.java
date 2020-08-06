package org.dew.oauth2;

/**
 * Interface for OAuth 2.0 protocol support.
 */
public 
interface IOAuth2 
{
  /**
   * Validation token request.
   * 
   * @param tokenRequest TokenRequest
   * @return OAuthError, null if tokenRequest is valid.
   */
  public OAuthError validateTokenRequest(TokenRequest tokenRequest);
  
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
