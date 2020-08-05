package org.dew.oauth2;

import java.io.IOException;

import java.util.UUID;

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
    
    if(!validateTokenRequest(response, tokenRequest)) return;
    
    String accessToken = null;
    try {
      
      accessToken = authenticate(tokenRequest);
    
    }
    catch(Exception ex) {
      sendError(response, ex);
      return;
    }
    
    if(accessToken == null || accessToken.length() == 0) {
      sendError(response, 401, "access_denied", "Access denied.");
    }
    
    TokenResponse tokenResponse = new TokenResponse(accessToken, "bearer", 3600, tokenRequest.getScope());
    
    sendReponse(response, tokenResponse);
  }
  
  protected
  String authenticate(TokenRequest tokenRequest)
      throws Exception
  {
    String username     = tokenRequest.getUsername();
    String password     = tokenRequest.getPassword();
    
    if(username == null || username.length() == 0) {
      return null;
    }
    if(password == null || password.length() == 0) {
      return null;
    }
    
    if(username.equals(password)) {
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
  
  protected
  boolean validateTokenRequest(HttpServletResponse response, TokenRequest tokenRequest)
      throws ServletException, IOException
  {
    String grantType    = tokenRequest.getGrantType();
    String username     = tokenRequest.getUsername();
    String password     = tokenRequest.getPassword();
    String clientId     = tokenRequest.getClientId();
    String clientSecret = tokenRequest.getClientSecret();
    String scope        = tokenRequest.getScope();
    
    if(grantType == null) {
      sendError(response, 400, "invalid_request", "Request was missing the 'grant_type' parameter.");
      return false;
    }
    if(!"password".equals(grantType)) {
      sendError(response, 400, "unsupported_grant_type", "Unsupported 'grant_type'.");
      return false;
    }
    if(username == null || username.length() == 0) {
      sendError(response, 400, "invalid_request", "Request was missing the 'username' parameter.");
      return false;
    }
    if(password == null || password.length() == 0) {
      sendError(response, 400, "invalid_request", "Request was missing the 'password' parameter.");
      return false;
    }
    if(clientId != null && clientId.length() > 0 && !clientId.equals("test")) {
      sendError(response, 401, "invalid_client", "Invalid client.");
      return false;
    }
    if(clientSecret != null && clientSecret.length() > 0) {
      try {
        boolean clientAuthenticated = authenticateClient(clientId, clientSecret);
        
        if(!clientAuthenticated) {
          sendError(response, 401, "unauthorized_client", "Client authentication failed.");
          return false;
        }
      }
      catch(Exception ex) {
        sendError(response, ex);
        return false;
      }
    }
    if(scope != null && scope.length() > 0 && scope.length() < 2) {
      sendError(response, 400, "invalid_scope", "Invalid scope.");
      return false;
    }
    return true;
  }
  
  protected
  void sendReponse(HttpServletResponse response, TokenResponse tokenResponse)
      throws ServletException, IOException
  {
    if(tokenResponse == null) return;
    
    String jsonTokenReponse = tokenResponse.toJson();
    
    response.setContentType("application/json;charset=UTF-8");
    response.setContentLength(jsonTokenReponse.length());
    response.getWriter().println(jsonTokenReponse);
  }
  
  protected
  void sendError(HttpServletResponse response, int status, String error, String errorDescription)
      throws ServletException, IOException
  {
    sendError(response, status, error, errorDescription, null);
  }
  
  protected
  void sendError(HttpServletResponse response, Exception exception)
      throws ServletException, IOException
  {
    sendError(response, 500, "server_error", exception.toString(), null);
  }
  
  protected
  void sendError(HttpServletResponse response, int status, String error, String errorDescription, String errorURI)
      throws ServletException, IOException
  {
    if(error == null || error.length() == 0) {
      error = "invalid_request";
    }
    if(errorDescription == null || errorDescription.length() == 0) {
      errorDescription = error;
    }
    
    String jsonError = "{";
    jsonError += "\"error\":\"" + jsonError.replace('"', '\'') + "\"";
    jsonError += ",\"error_description\":\"" + errorDescription.replace('"', '\'') + "\"";
    if(errorURI != null && errorURI.length() > 0) {
      jsonError += ",\"error_uri\":\"" + errorURI.replace('"', '\'') + "\"";
    }
    
    response.setStatus(status);
    response.setContentType("application/json;charset=UTF-8");
    response.setContentLength(jsonError.length());
    response.getWriter().println(jsonError);
  }
}
