package org.dew.oauth2;

import java.lang.reflect.Array;

import java.net.URLEncoder;

import java.security.MessageDigest;
import java.security.SecureRandom;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Utilities.
 */
public 
class Utils 
{
  public static
  String toBase64URLEncoded(byte[] arrayOfBytes)
  {
    if(arrayOfBytes == null || arrayOfBytes.length == 0) {
      return "";
    }
    String b64 =  new String(Base64Coder.encode(arrayOfBytes));
    // Base64 URL Variant
    return b64.replace('+', '-').replace('/', '_').replace("=", "");
  }
  
  public static
  String generateState() 
  {
    SecureRandom secureRandom = new SecureRandom();
    byte[] arrayOfRandomBytes = new byte[24];
    secureRandom.nextBytes(arrayOfRandomBytes);
    // java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(arrayOfRandomBytes)
    return toBase64URLEncoded(arrayOfRandomBytes);
  }
  
  // PKCE (Proof Key for Code Exchange)
  public static
  String generateCodeVerifier() 
  {
    SecureRandom secureRandom = new SecureRandom();
    byte[] arrayOfRandomBytes = new byte[32];
    secureRandom.nextBytes(arrayOfRandomBytes);
    // java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(arrayOfRandomBytes)
    return toBase64URLEncoded(arrayOfRandomBytes);
  }
  
  // PKCE (Proof Key for Code Exchange)
  public static
  String generateCodeChallange(String codeVerifier)
  {
    try {
      byte[] bytes = codeVerifier.getBytes("US-ASCII");
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.update(bytes, 0, bytes.length);
      byte[] digest = messageDigest.digest();
      // java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(arrayOfRandomBytes)
      return toBase64URLEncoded(digest);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return "";
  }
  
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
  String toQueryString(Map<String, Object> map)
  {
    if(map == null || map.isEmpty()) {
      return "";
    }
    StringBuilder sb = new StringBuilder(map.size() * 2 * 8);
    Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
    while(iterator.hasNext()) {
      Map.Entry<String, Object> entry = iterator.next();
      String key = entry.getKey();
      Object val = entry.getValue();
      appendParam(sb, key, val);
    }
    return sb.toString();
  }
  
  @SuppressWarnings("unchecked")
  public static
  String toHeaderValue(Map<String, Object> map)
  {
    if(map == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder(map.size() * 2 * 8);
    Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
    while(iterator.hasNext()) {
      Map.Entry<String, Object> entry = iterator.next();
      String key = entry.getKey();
      Object val = entry.getValue();
      if(val == null) {
        continue;
      }
      else if(val instanceof String) {
        sb.append(" " + key + "=" + toJSON((String)val));
      }
      else if(val instanceof Number) {
        sb.append(" " + key + "=" + val);
      }
      else if(val instanceof Boolean) {
        sb.append(" " + key + "=" + val);
      }
      else if(val instanceof Date) {
        sb.append(" " + key + "=\"" + formatDate((Date) val) + "\"");
      }
      else if(val instanceof Calendar) {
        sb.append(" " + key + "=\"" + formatDate((Calendar) val) + "\"");
      }
      else if(val instanceof Map) {
        sb.append(" " + key + "=" + toJSON((Map<String, Object>) val));
      }
      else if(val instanceof Collection) {
        sb.append(" " + key + "=" + toJSON((Collection<?>) val));
      }
      else if(val.getClass().isArray()) {
        sb.append(" " + key + "=" + toJSONArray(val));
      }
      else if(val instanceof IOAuthObject) {
        sb.append(" " + key + "=" + ((IOAuthObject)val).toJSON());
      }
      else {
        sb.append(" " + key + "=" + toJSON(val.toString()));
      }
    }
    if(sb.length() == 0) return "";
    return sb.substring(1);
  }
  
  /**
   * Library independent JSON object serialization implementation. 
   * 
   * @param map Map<String, Object>
   * @return JSON representation
   */
  @SuppressWarnings("unchecked")
  public static
  String toJSON(Map<String, Object> map)
  {
    if(map == null) {
      return "null";
    }
    StringBuilder sb = new StringBuilder(map.size() * 2 * 8);
    Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
    while(iterator.hasNext()) {
      Map.Entry<String, Object> entry = iterator.next();
      String key = entry.getKey();
      Object val = entry.getValue();
      if(val == null) {
        continue;
      }
      else if(val instanceof String) {
        sb.append(",\"" + key + "\":" + toJSON((String)val));
      }
      else if(val instanceof Number) {
        sb.append(",\"" + key + "\":" + val);
      }
      else if(val instanceof Boolean) {
        sb.append(",\"" + key + "\":" + val);
      }
      else if(val instanceof Date) {
        sb.append(",\"" + key + "\":\"" + formatDate((Date) val) + "\"");
      }
      else if(val instanceof Calendar) {
        sb.append(",\"" + key + "\":\"" + formatDate((Calendar) val) + "\"");
      }
      else if(val instanceof Map) {
        sb.append(",\"" + key + "\":" + toJSON((Map<String, Object>) val));
      }
      else if(val instanceof Collection) {
        sb.append(",\"" + key + "\":" + toJSON((Collection<?>) val));
      }
      else if(val.getClass().isArray()) {
        sb.append(",\"" + key + "\":" + toJSONArray(val));
      }
      else if(val instanceof IOAuthObject) {
        sb.append(",\"" + key + "\":" + ((IOAuthObject)val).toJSON());
      }
      else {
        sb.append(",\"" + key + "\":" + toJSON(val.toString()));
      }
    }
    if(sb.length() == 0) return "{}";
    return "{" + sb.substring(1) + "}";
  }
  
  /**
   * Library independent JSON array serialization implementation. 
   * 
   * @param col Collection<?>
   * @return JSON representation
   */
  @SuppressWarnings("unchecked")
  public static
  String toJSON(Collection<?> col)
  {
    if(col == null) {
      return "null";
    }
    if(col.size() == 0) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder(col.size() * 8);
    Iterator<?> iterator = col.iterator();
    while(iterator.hasNext()) {
      Object val = iterator.next();
      if(val == null) {
        sb.append(",null");
      }
      else if(val instanceof String) {
        sb.append("," + toJSON((String) val));
      }
      else if(val instanceof Number) {
        sb.append("," + val);
      }
      else if(val instanceof Boolean) {
        sb.append("," + val);
      }
      else if(val instanceof Date) {
        sb.append(",\"" + formatDate((Date) val) + "\"");
      }
      else if(val instanceof Calendar) {
        sb.append(",\"" + formatDate((Calendar) val) + "\"");
      }
      else if(val instanceof Map) {
        sb.append("," + toJSON((Map<String, Object>) val));
      }
      else if(val instanceof Collection) {
        sb.append("," + toJSON((Collection<?>) val));
      }
      else if(val.getClass().isArray()) {
        sb.append("," + toJSONArray(val));
      }
      else if(val instanceof IOAuthObject) {
        sb.append("," + ((IOAuthObject)val).toJSON());
      }
      else {
        sb.append("," + toJSON(val.toString()));
      }
    }
    if(sb.length() == 0) return "[]";
    return "[" + sb.substring(1) + "]";
  }
  
  /**
   * Library independent JSON array serialization implementation. 
   * 
   * @param object Object
   * @return JSON representation
   */
  @SuppressWarnings("unchecked")
  public static
  String toJSONArray(Object object)
  {
    if(object == null) {
      return "null";
    }
    if(object instanceof Collection) {
      return toJSON((Collection<?>) object);
    }
    if(!object.getClass().isArray()) {
      ArrayList<Object> arrayList = new ArrayList<Object>(1);
      arrayList.add(object);
      return toJSON((Collection<?>) object);
    }
    int length = Array.getLength(object);
    StringBuilder sb = new StringBuilder(length * 8);
    for(int i = 0; i < length; i++) {
      Object val = Array.get(object, i);
      if(val == null) {
        sb.append(",null");
      }
      else if(val instanceof String) {
        sb.append("," + toJSON((String) val));
      }
      else if(val instanceof Number) {
        sb.append("," + val);
      }
      else if(val instanceof Boolean) {
        sb.append("," + val);
      }
      else if(val instanceof Date) {
        sb.append(",\"" + formatDate((Date) val) + "\"");
      }
      else if(val instanceof Calendar) {
        sb.append(",\"" + formatDate((Calendar) val) + "\"");
      }
      else if(val instanceof Map) {
        sb.append("," + toJSON((Map<String, Object>) val));
      }
      else if(val instanceof Collection) {
        sb.append("," + toJSON((Collection<?>) val));
      }
      else if(val.getClass().isArray()) {
        sb.append("," + toJSONArray(val));
      }
      else if(val instanceof IOAuthObject) {
        sb.append("," + ((IOAuthObject)val).toJSON());
      }
      else {
        sb.append("," + toJSON(val.toString()));
      }
    }
    if(sb.length() == 0) return "[]";
    return "[" + sb.substring(1) + "]";
  }
  
  /**
   * Library independent JSON string serialization implementation. 
   * 
   * @param string String
   * @return JSON representation
   */
  public static 
  String toJSON(String string) 
  {
    if(string == null) {
      return "null";
    }
    if(string.length() == 0) {
      return "\"\"";
    }
    char b;
    char c = 0;
    String hhhh;
    int i;
    int len = string.length();
    StringBuilder sb = new StringBuilder(len + 2);
    sb.append('"');
    for(i = 0; i < len; i += 1) {
      b = c;
      c = string.charAt(i);
      switch(c) {
        case '\\':
        case '"':
          sb.append('\\');
          sb.append(c);
        break;
        case '/':
          if(b == '<') {
            sb.append('\\');
          }
          sb.append(c);
        break;
        case '\b':
          sb.append("\\b");
        break;
        case '\t':
          sb.append("\\t");
        break;
        case '\n':
          sb.append("\\n");
        break;
        case '\f':
          sb.append("\\f");
        break;
        case '\r':
          sb.append("\\r");
        break;
        default:
          if(c < ' ' ||(c >= '\u0080' && c < '\u00a0') ||(c >= '\u2000' && c < '\u2100')) {
            sb.append("\\u");
            hhhh = Integer.toHexString(c);
            sb.append("0000", 0, 4 - hhhh.length());
            sb.append(hhhh);
          } 
          else {
            sb.append(c);
          }
      }
    }
    sb.append('"');
    return sb.toString();
  }
  
  public static
  String getJSONVal(String jsonObject, String key, String defaultValue)
  {
    if(jsonObject == null || jsonObject.length() == 0 || jsonObject.indexOf('{') < 0) {
      return defaultValue;
    }
    if(key == null || key.length() == 0) {
      return defaultValue;
    }
    // Find key
    int k = jsonObject.indexOf("\"" + key + "\"");
    if(k < 0) {
      return defaultValue;
    }
    // Separator key-value
    int s = jsonObject.indexOf(':', k + key.length() + 2);
    if(s < 0) {
      return defaultValue;
    }
    // Find end value
    int  e = -1;
    // Previous char
    char cp = '\0';
    // String delimiter char
    char cs = '\0';
    // Is string?
    boolean jsonString = false;
    for(int i = s; i < jsonObject.length() - 1; i++) {
      char c = jsonObject.charAt(i);
      if(c == '"' && cp != '\\') {
        if(jsonString) {
          if(cs == '"') {
            // End string
            jsonString = false;
          }
        }
        else {
          // Start string
          jsonString = true;
          cs = '"';
        }
      }
      else if(c == '\'' && cp != '\\') {
        if(jsonString) {
          if(cs == '\'') {
            // End string
            jsonString = false;
          }
        }
        else {
          // Start string
          jsonString = true;
          cs = '\'';
        }
      }
      else if(c == ',' || c == '}') {
        if(!jsonString) {
          // End value found
          e = i;
          break;
        }
      }
      // Set previous char
      cp = c;
    }
    if(e < 0) {
      e = jsonObject.length();
    }
    String value = jsonObject.substring(s + 1, e).trim();
    if(value.equals("null")) {
      return defaultValue;
    }
    // Strip string
    if(value.startsWith("\"") && value.endsWith("\"") && value.length() > 1) {
      value = value.substring(1, value.length() - 1);
    }
    else if(value.startsWith("'") && value.endsWith("'") && value.length() > 1) {
      value = value.substring(1, value.length() - 1);
    }
    // Replace escape characters
    return value.replace("\\\"", "\"").replace("\\'", "'").replace("\\n", "\n").replace("\\t", "\t");
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
  boolean toBoolean(Object value)
  {
    if(value == null) {
      return false;
    }
    if(value instanceof Boolean) {
      return ((Boolean) value).booleanValue();
    }
    if(value instanceof String) {
      String s = (String) value;
      if(s.length() == 0) return false;
      return ("1TtYySs").indexOf(s.charAt(0)) >= 0;
    }
    if(value instanceof Number) {
      int i = ((Number) value).intValue();
      return i != 0;
    }
    return false;
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
