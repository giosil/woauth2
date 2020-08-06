package org.dew.oauth2;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WebCodeBound (Test resource)
 */
@WebServlet(name = "WebCodeBound", loadOnStartup = 0, urlPatterns = { "/cb" })
public 
class WebCodeBound extends HttpServlet 
{
  private static final long serialVersionUID = 3290098484015679889L;
  
  @Override
  protected 
  void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    String parameters = "";
    
    Enumeration<String> parameterNames = request.getParameterNames();
    while(parameterNames.hasMoreElements()) {
      String parameterName  = parameterNames.nextElement();
      String parameterValue = request.getParameter(parameterName);
      
      parameters += "<strong>" + parameterName + "</strong> = " + parameterValue + "<br>";
    }
    
    String html = "<!DOCTYPE html>\n";
    html += "<html>\n";
    html += "<head>\n<title>" + this.getClass().getSimpleName() + "</title>\n</head>\n";
    html += "<body>\n";
    html += "<h1>Parameters:</h1>\n";
    html += "<br>\n";
    html += parameters;
    html += "</body>\n";
    html += "</html>";
    
    response.getWriter().println(html);
  }
  
  @Override
  protected 
  void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    doGet(request, response);
  }
}
