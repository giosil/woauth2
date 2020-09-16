package org.dew.oauth2;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public 
class OAuth2Client 
{
  private String urlTokenRequest = "http://localhost:8080/woauth2/token";
  private String urlGetUserInfo  = "http://localhost:8080/woauth2/userinfo";
  private boolean debug = false;
  private int timeOut   = 30000;
  
  public OAuth2Client()
  {
  }
  
  public OAuth2Client(String urlTokenRequest)
  {
    this.urlTokenRequest = urlTokenRequest;
  }
  
  public OAuth2Client(String urlTokenRequest, String urlGetUserInfo)
  {
    this.urlTokenRequest = urlTokenRequest;
    this.urlGetUserInfo  = urlGetUserInfo;
  }
  
  public String getUrlTokenRequest() {
    return urlTokenRequest;
  }

  public String getUrlGetUserInfo() {
    return urlGetUserInfo;
  }

  public boolean isDebug() {
    return debug;
  }

  public int getTimeOut() {
    return timeOut;
  }

  public void setUrlTokenRequest(String urlTokenRequest) {
    this.urlTokenRequest = urlTokenRequest;
  }

  public void setUrlGetUserInfo(String urlGetUserInfo) {
    this.urlGetUserInfo = urlGetUserInfo;
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  public void setTimeOut(int timeOut) {
    this.timeOut = timeOut;
  }

  public 
  String accessTokenRequest(String username, String password)
    throws Exception
  {
    return accessTokenRequest(username, password, null, null);
  }
  
  public 
  String accessTokenRequest(String username, String password, String clientId)
    throws Exception
  {
    return accessTokenRequest(username, password, clientId, null);
  }
  
  public 
  String accessTokenRequest(String username, String password, String clientId, String clientSecret)
    throws Exception
  {
    if(username == null || username.length() == 0) {
      throw new Exception("Invalid username");
    }
    if(password == null || password.length() == 0) {
      throw new Exception("Invalid password");
    }
    if(urlTokenRequest == null || urlTokenRequest.length() < 3) {
      throw new Exception("Invalid urlTokenRequest");
    }
    
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("grant_type", "password");
    parameters.put("username",   username);
    parameters.put("password",   password);
    if(clientId != null && clientId.length() > 0) {
      parameters.put("client_id", clientId);
    }
    if(clientSecret != null && clientSecret.length() > 0) {
      parameters.put("client_secret", clientSecret);
    }
    
    return http("POST", urlTokenRequest, null, parameters);
  }
  
  public 
  String refreshTokenRequest(String refreshToken)
    throws Exception
  {
    return refreshTokenRequest(refreshToken, null, null);
  }
  
  public 
  String refreshTokenRequest(String refreshToken, String clientId)
    throws Exception
  {
    return refreshTokenRequest(refreshToken, clientId, null);
  }
  
  public 
  String refreshTokenRequest(String refreshToken, String clientId, String clientSecret)
    throws Exception
  {
    if(refreshToken == null || refreshToken.length() == 0) {
      throw new Exception("Invalid refreshToken");
    }
    if(urlTokenRequest == null || urlTokenRequest.length() < 3) {
      throw new Exception("Invalid urlTokenRequest");
    }
    
    boolean isJSONObject = refreshToken.indexOf('{') >= 0;
    if(isJSONObject) {
      refreshToken = Utils.getJSONVal(refreshToken, "refresh_token", null);
      
      if(refreshToken == null || refreshToken.length() == 0) {
        throw new Exception("Invalid refresh_token");
      }
    }
    
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("grant_type",    "refresh_token");
    parameters.put("refresh_token", refreshToken);
    if(clientId != null && clientId.length() > 0) {
      parameters.put("client_id", clientId);
    }
    if(clientSecret != null && clientSecret.length() > 0) {
      parameters.put("client_secret", clientSecret);
    }
    
    return http("POST", urlTokenRequest, null, parameters);
  }
  
  public 
  String getUserInfo(String token)
    throws Exception
  {
    if(token == null || token.length() == 0) {
      throw new Exception("Invalid token");
    }
    if(urlGetUserInfo == null || urlGetUserInfo.length() < 3) {
      throw new Exception("Invalid urlGetUserInfo");
    }
    
    boolean isJSONObject = token.indexOf('{') >= 0;
    if(isJSONObject) {
      token = Utils.getJSONVal(token, "access_token", null);
      
      if(token == null || token.length() == 0) {
        throw new Exception("Invalid access_token");
      }
    }
    
    return http("GET", urlGetUserInfo, token, null);
  }
  
  protected
  String http(String method, String url, String token, Map<String, Object> parameters)
    throws Exception
  {
    if(debug) {
      System.out.println("OAuth2Client http(" + method + "," + dec(url) + "," + token + "," + parameters + ")...");
    }
    if(url == null || url.length() == 0 || url.equals("test")) {
      System.out.println("OAuth2Client http(" + method + "," + dec(url) + "," + token + "," + parameters + ") -> \"\"");
      return "";
    }
    if(url.indexOf(":") < 0) {
      url = "http://" + url;
    }
    
    if(method == null || method.length() < 2) {
      method = "GET";
    }
    
    if("GET".equalsIgnoreCase(method) && parameters != null && !parameters.isEmpty()) {
      if(url.indexOf('?') >= 0) {
        if(url.endsWith("?")) {
          url += Utils.toQueryString(parameters);
        }
        else {
          url += "&" + Utils.toQueryString(parameters);
        }
      }
      else {
        url += "?" + Utils.toQueryString(parameters);
      }
    }
    
    boolean doOutput = false;
    
    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
    
    connection.setRequestMethod(method.toUpperCase());
    if(token != null && token.length() > 0) {
      connection.addRequestProperty("Authorization", "Bearer " + token);
    }
    connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    if("POST".equalsIgnoreCase(method) && parameters != null && !parameters.isEmpty()) {
      connection.setDoOutput(true);
      doOutput = true; 
    }
    if(timeOut > 0) {
      connection.setConnectTimeout(timeOut);
      connection.setReadTimeout(timeOut);
    }
    
    int statusCode = 0;
    boolean error  = false;
    OutputStream out = null;
    try {
      if(doOutput) {
        out = connection.getOutputStream();
        out.write(Utils.toQueryString(parameters).getBytes("UTF-8"));
        out.flush();
        out.close();
      }
      statusCode = connection.getResponseCode();
      error = statusCode >= 400;
    }
    catch(Exception ex) {
      if(debug) System.out.println("OAuth2Client http(" + method + "," + dec(url) + "," + token + "," + parameters + ") -> " + statusCode + " " + ex);
      throw ex;
    }
    finally {
      if(out != null) try{ out.close(); } catch(Exception ex) {}
    }
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      BufferedInputStream  bin = new BufferedInputStream(error ? connection.getErrorStream() : connection.getInputStream());
      byte[] buff = new byte[1024];
      int n;
      while((n = bin.read(buff)) > 0) baos.write(buff, 0, n);
      baos.flush();
      baos.close();
    }
    finally {
      if(connection != null) try{ connection.disconnect(); } catch(Exception ex) {}
    }
    
    byte[] abResponse = new String(baos.toByteArray(), "UTF-8").getBytes();
    
    String result = new String(abResponse);
    if(error) {
      if(result.length() == 0 || result.equalsIgnoreCase("error")) {
        result += " (HTTP " + statusCode + ")";
      }
    }
    
    if(error) {
      if(debug) {
        System.out.println("OAuth2Client http(" + method + "," + dec(url) + "," + token + "," + parameters + ") -> " + statusCode + " Exception(" + result + ")");
      }
      throw new Exception(result);
    }
    if(debug) {
      System.out.println("OAuth2Client http(" + method + "," + dec(url) + "," + token + "," + parameters + ") -> " + statusCode + " " + result + ")");
    }
    return result;
  }
  
  protected static
  String enc(String s)
  {
    if(s == null) return "null";
    try { return URLEncoder.encode(s, "UTF-8"); } catch(Throwable ignore) {}
    return s;
  }
  
  protected static
  String dec(String s)
  {
    if(s == null) return "null";
    try { return URLDecoder.decode(s, "UTF-8"); } catch(Throwable ignore) {}
    return s;
  }
  
  @Override
  public boolean equals(Object object) {
    if(object instanceof OAuth2Client) {
      return this.toString().equals(object.toString());
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return toString().hashCode();
  }
  
  @Override
  public String toString() {
    return "OAuthClient(" + urlTokenRequest + "," + urlGetUserInfo + ")";
  }
}
