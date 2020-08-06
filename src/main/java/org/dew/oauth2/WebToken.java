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
 * POST /woauth2/token HTTP/1.1
 * Content-type: application/x-www-form-urlencoded
 *
 * grant_type=password&username=admin&password=admin&client_id=test
 * 
 * <b>Response:</b>
 * <pre>
 * {
 *   "access_token": "ea39e8df-b8c7-4024-9bdb-08b7ce808917",
 *   "token_type": "bearer",
 *   "expires_in": 3600,
 * }
 * </pre>
 */
@WebServlet(name = "WebToken", loadOnStartup = 0, urlPatterns = { "/token" })
public 
class WebToken extends HttpServlet 
{
  private static final long serialVersionUID = 5695637401281411445L;
  
  protected IOAuth2 oauth2;
  
  public
  void init()
    throws ServletException
  {
    oauth2 = new MockOAuth2();
  }
  
  @Override
  protected 
  void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
  }
  
  @Override
  protected 
  void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    TokenRequest tokenRequest = new TokenRequest(request);
    
    OAuthError error = oauth2.validateTokenRequest(tokenRequest);
    
    if(error != null) {
      sendError(response, error);
      return;
    }
    
    TokenResponse tokenResponse = null;
    try {
      
      tokenResponse = oauth2.requestToken(tokenRequest);
    
    }
    catch(Exception ex) {
      sendError(response, new OAuthError(ex));
      return;
    }
    
    if(tokenResponse == null || tokenResponse.getAccessToken() == null) {
      sendError(response, OAuthError.ACCESS_DENIED);
      return;
    }
    
    sendReponse(response, tokenResponse);
  }
  
  protected
  void sendReponse(HttpServletResponse response, TokenResponse tokenResponse)
      throws ServletException, IOException
  {
    if(tokenResponse == null) return;
    
    String content = tokenResponse.toJson();
    
    response.setContentType("application/json;charset=UTF-8");
    response.setContentLength(content.length());
    response.getWriter().println(content);
  }
  
  protected
  void sendError(HttpServletResponse response, OAuthError error)
      throws ServletException, IOException
  {
    if(error == null) error = OAuthError.SERVER_ERROR;
    
    String content = error.toJson();
    
    response.setStatus(error.getHttpStatus());
    response.setContentType("application/json;charset=UTF-8");
    response.setContentLength(content.length());
    response.getWriter().println(content);
  }
}
