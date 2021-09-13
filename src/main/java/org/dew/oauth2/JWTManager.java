package org.dew.oauth2;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Class to create and verify JSON Web Token. 
 */
public 
class JWTManager 
{
  private static String SECRET = "&Nbj4k&L5Bji";
  private static String ISSUER = "org.dew";
  
  public static 
  String createToken(String subject)
    throws Exception
  {
    Algorithm algorithm = Algorithm.HMAC256(SECRET);
    
    String token = JWT.create().withIssuer(ISSUER).withSubject(subject).sign(algorithm);
    
    return token;
  }
  
  public static 
  String verifyToken(String token)
    throws Exception
  {
    Algorithm algorithm = Algorithm.HMAC256(SECRET);
    
    JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
    
    DecodedJWT decodedJWT = verifier.verify(token);
    
    return decodedJWT.getSubject();
  }
}
