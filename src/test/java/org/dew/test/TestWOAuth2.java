package org.dew.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestWOAuth2 extends TestCase {
  
  public TestWOAuth2(String testName) {
    super(testName);
  }
  
  public static Test suite() {
    return new TestSuite(TestWOAuth2.class);
  }
  
  public void testApp() {
    System.out.println("TestWOAuth2 test");
  }
  
}
