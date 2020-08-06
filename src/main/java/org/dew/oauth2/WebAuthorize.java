package org.dew.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token service implementation.
 *
 * <b>Request:</b>
 * GET /woauth2/authorize?response_type=code&client_id=test&state=xyz&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fwoauth2%2Fcb HTTP/1.1
 *
 * <b>Response:</b>
 * <pre>
 * HTTP/1.1 302 Found
 * Location: http://localhost:8080/woauth2/cb?code=SplxlOBeZQQYbYS6WxSbIA&state=xyz
 * </pre>
 */
@WebServlet(name = "WebAuthorize", loadOnStartup = 0, urlPatterns = { "/authorize" })
public 
class WebAuthorize extends HttpServlet 
{
  private static final long serialVersionUID = -1421805358589073521L;
  
  protected IOAuth2 oauth2;
  
  public
  void init()
    throws ServletException
  {
    oauth2 = new BaseOAuth2();
  }
  
  @Override
  protected 
  void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    AuthorizationRequest authorizationRequest = new AuthorizationRequest(request);
    
    OAuthError error = oauth2.validateAuthorizationRequest(authorizationRequest);
    
    if(error != null) {
      response.setHeader("Location", authorizationRequest.getRedirectURI() + "?" + error.toQueryString());
      response.sendError(302);
      return;
    }
    
    AuthorizationResponse authorizationResponse = null;
    try {
      
      authorizationResponse = oauth2.requestAuthorization(authorizationRequest);
    
    }
    catch(Exception ex) {
      response.setHeader("Location", authorizationRequest.getRedirectURI() + "?" + new OAuthError(ex).toQueryString());
      response.sendError(302);
      return;
    }
    
    if(authorizationResponse == null || authorizationResponse.getCode() == null) {
      response.setHeader("Location", authorizationRequest.getRedirectURI() + "?" + OAuthError.ACCESS_DENIED.toQueryString());
      response.sendError(302);
      return;
    }
    
    response.setHeader("Location", authorizationRequest.getRedirectURI() + "?" + authorizationResponse.toQueryString());
    response.sendError(302);
  }
  
  @Override
  protected 
  void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    doGet(request, response);
  }
}
