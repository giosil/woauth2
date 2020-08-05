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
 * GET /woauth2/userinfo HTTP/1.1
 * Authorization: Bearer ea39e8df-b8c7-4024-9bdb-08b7ce808917
 * 
 * <b>Response:</b>
 * <pre>
 * {
 *   "sub"         : "12345",
 *   "name"        : "Test Dev",
 *   "given_name"  : "Test",
 *   "family_name" : "Dev",
 *   "nickname"    : "admin",
 *   "email"       : "test.dev@example.com"
 * }
 * </pre>
 */
@WebServlet(name = "WebUserInfo", loadOnStartup = 0, urlPatterns = { "/userinfo" })
public 
class WebUserInfo extends HttpServlet 
{
  private static final long serialVersionUID = 471049230849878501L;
  
  @Override
  protected 
  void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    String sAuthorization = request.getHeader("Authorization");
    
    if(sAuthorization == null || sAuthorization.length() == 0) {
      response.addHeader("WWW-Authenticate", "Bearer");
      response.sendError(401); // Unauthorized
      return;
    }
    
    if(!sAuthorization.startsWith("Bearer ") || sAuthorization.length() < 8) {
      response.addHeader("WWW-Authenticate", "Bearer");
      response.sendError(401); // Unauthorized
      return;
    }
    
    String token = sAuthorization.substring(7);
    if(token == null || token.length() < 2) {
      response.addHeader("WWW-Authenticate", "Bearer error=\"invalid_token\" error_description=\"Invalid token\"");
      response.sendError(403); // Forbidden
      return;
    }
    
    UserInfo userInfo = null;
    try {
      
      userInfo = readUserInfo(token);
    
    }
    catch(Exception ex) {
      response.sendError(500, ex.toString());
      return;
    }
    
    if(userInfo == null) {
      response.addHeader("WWW-Authenticate", "Bearer error=\"insufficient_scope\" error_description=\"Bearer access token has insufficient privileges\"");
      response.sendError(403); // Forbidden
      return;
    }
    
    sendReponse(response, userInfo);
  }
  
  @Override
  protected 
  void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    doGet(request, response);
  }
  
  protected
  UserInfo readUserInfo(String token)
  {
    if(token == null || token.length() == 0) {
      return null;
    }
    
    return new UserInfo("12345", "Test", "Dev", "admin", "test.dev@example.com");
  }
  
  protected
  void sendReponse(HttpServletResponse response, UserInfo userInfo)
      throws ServletException, IOException
  {
    if(userInfo == null) return;
    
    String jsonUserinfo = userInfo.toJson();
    
    response.setContentType("application/json;charset=UTF-8");
    response.setContentLength(jsonUserinfo.length());
    response.getWriter().println(jsonUserinfo);
  }
}