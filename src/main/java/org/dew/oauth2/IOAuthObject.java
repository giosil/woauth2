package org.dew.oauth2;

import java.util.Map;

public 
interface IOAuthObject 
{
  public Map<String, Object> toMap();

  public String toQueryString();
  
  public String toJSON();
  
  public String toHeaderValue();
}
