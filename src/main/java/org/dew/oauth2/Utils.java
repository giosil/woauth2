package org.dew.oauth2;

import java.net.URLEncoder;
import java.util.*;

/**
 * Utilities.
 */
public 
class Utils 
{
  public static
  String encode(Object value)
  {
    if(value == null) return "";
    String sValue = toString(value, "");
    try {
      return URLEncoder.encode(sValue, "UTF-8");
    }
    catch(Exception ex) {
      return sValue;
    }
  }
  
  public static
  void appendParam(StringBuilder sb, String parameterName, Object value)
  {
    if(value == null) return;
    
    if(sb.length() == 0) {
      sb.append(parameterName + "=" + encode(value));
    }
    else {
      sb.append("&" + parameterName + "=" + encode(value));
    }
  }
  
  public static
  String toString(Object value)
  {
    if(value == null) {
      return null;
    }
    if(value instanceof String) {
      return (String) value;
    }
    if(value instanceof Calendar) {
      return formatDate((Calendar) value);
    }
    if(value instanceof Date) {
      return formatDate((Date) value);
    }
    return value.toString();
  }
  
  public static
  String toString(Object value, String sDefault)
  {
    if(value == null) {
      return sDefault;
    }
    if(value instanceof String) {
      return (String) value;
    }
    if(value instanceof Calendar) {
      return formatDate((Calendar) value);
    }
    if(value instanceof Date) {
      return formatDate((Date) value);
    }
    return value.toString();
  }
  
  public static
  int toInt(Object value)
  {
    if(value == null) {
      return 0;
    }
    if(value instanceof String) {
      int iValue = 0;
      try { iValue = Integer.parseInt(((String) value).trim()); } catch(Exception ex) {}
      return iValue;
    }
    if(value instanceof Number) {
      return ((Number) value).intValue();
    }
    if(value instanceof Calendar) {
      return calendarToInt((Calendar) value);
    }
    if(value instanceof Date) {
      return dateToInt((Date) value);
    }
    String sValue = value.toString();
    if(sValue == null) return 0;
    int iValue = 0;
    try { iValue = Integer.parseInt(sValue.trim()); } catch(Exception ex) {}
    return iValue;
  }
  
  public static
  int toInt(Object value, int iDefault)
  {
    if(value == null) {
      return iDefault;
    }
    if(value instanceof String) {
      int iValue = iDefault;
      try { iValue = Integer.parseInt(((String) value).trim()); } catch(Exception ex) {}
      return iValue;
    }
    if(value instanceof Number) {
      return ((Number) value).intValue();
    }
    if(value instanceof Calendar) {
      return calendarToInt((Calendar) value);
    }
    if(value instanceof Date) {
      return dateToInt((Date) value);
    }
    String sValue = value.toString();
    if(sValue == null) return 0;
    int iValue = iDefault;
    try { iValue = Integer.parseInt(sValue.trim()); } catch(Exception ex) {}
    return iValue;
  }
  
  public static
  String formatDate(Calendar cal)
  {
    if(cal == null) return "";
    
    int iYear  = cal.get(Calendar.YEAR);
    int iMonth = cal.get(Calendar.MONTH) + 1;
    int iDay   = cal.get(Calendar.DATE);
    
    String sYear  = String.valueOf(iYear);
    String sMonth = iMonth < 10 ? "0" + iMonth : String.valueOf(iMonth);
    String sDay   = iDay   < 10 ? "0" + iDay   : String.valueOf(iDay);
    
    return sYear + "-" + sMonth + "-" + sDay;
  }
  
  public static
  String formatDate(Date date)
  {
    if(date == null) return "";
    
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(date.getTime());
    
    int iYear  = cal.get(Calendar.YEAR);
    int iMonth = cal.get(Calendar.MONTH) + 1;
    int iDay   = cal.get(Calendar.DATE);
    
    String sYear  = String.valueOf(iYear);
    String sMonth = iMonth < 10 ? "0" + iMonth : String.valueOf(iMonth);
    String sDay   = iDay   < 10 ? "0" + iDay   : String.valueOf(iDay);
    
    return sYear + "-" + sMonth + "-" + sDay;
  }
  
  public static
  int calendarToInt(Calendar cal)
  {
    if(cal == null) return 0;
    
    long timeInMillis = cal.getTimeInMillis();
    
    return (int) (timeInMillis / 1000);
  }
  
  public static
  int dateToInt(Date date)
  {
    if(date == null) return 0;
    
    long timeInMillis = date.getTime();
    
    return (int) (timeInMillis / 1000);
  }
}
