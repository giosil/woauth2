package org.dew.test;

import org.dew.oauth2.IOAuth2;
import org.dew.oauth2.MockOAuth2;
import org.dew.oauth2.OAuthError;
import org.dew.oauth2.TokenRequest;
import org.dew.oauth2.TokenResponse;
import org.dew.oauth2.UserInfo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestWOAuth2 extends TestCase {
  
  protected IOAuth2 oauth2 = new MockOAuth2();
  
  public TestWOAuth2(String testName) {
    super(testName);
  }
  
  public static Test suite() {
    return new TestSuite(TestWOAuth2.class);
  }
  
  public void testApp() {
    TokenRequest tokenRequest = new TokenRequest("password", "admin", "admin", "test");
    
    System.out.println("oauth2.validateTokenRequest(" + tokenRequest + ")...");
    
    OAuthError error = oauth2.validateTokenRequest(tokenRequest);
    
    if(error != null) {
      System.out.println("oauth2.validateTokenRequest(" + tokenRequest + ") -> " + error.toJson());
    }
    else {
      System.out.println("oauth2.validateTokenRequest(" + tokenRequest + ") -> null");
    }
    
    System.out.println("oauth2.requestToken(" + tokenRequest + ")...");
    
    TokenResponse tokenResponse = null;
    try {
      tokenResponse = oauth2.requestToken(tokenRequest);
      
      if(tokenResponse != null) {
        System.out.println("oauth2.requestToken(" + tokenRequest + ") -> " + tokenResponse.toJson());
      }
      else {
        System.out.println("oauth2.requestToken(" + tokenRequest + ") -> null");
      }
    }
    catch(Exception ex) {
      System.out.println("oauth2.requestToken(" + tokenRequest + "): " + ex);
    }
    
    if(tokenResponse != null) {
      System.out.println("oauth2.getUserInfo(" + tokenResponse.getAccessToken() + ")...");
      
      UserInfo userInfo = null;
      try {
        userInfo = oauth2.getUserInfo(tokenResponse.getAccessToken());
        
        if(userInfo != null) {
          System.out.println("oauth2.getUserInfo(" + tokenResponse.getAccessToken() + ") -> " + userInfo.toJson());
        }
        else {
          System.out.println("oauth2.getUserInfo(" + tokenResponse.getAccessToken() + ") -> null");
        }
      }
      catch(Exception ex) {
        System.out.println("oauth2.getUserInfo(" + tokenResponse.getAccessToken() + "): " + ex);
      }
    }
  }
  
}
