package org.dew.oauth2;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Bean UserInfo based on standard claims OpenId.
 */
public 
class UserInfo implements IOAuthObject, Serializable
{
  private static final long serialVersionUID = -1596569952840988698L;
  // Standard Claims OpenId
  private String sub;
  private String name;
  private String givenName;
  private String familyName;
  private String middleName;
  private String nickname;
  private String preferredUsername;
  private String profile;
  private String picture;
  private String website;
  private String email;
  private boolean emailVerified;
  private String gender;
  private String birthdate;
  private String zoneinfo;
  private String locale;
  private String phoneNumber;
  private boolean phoneNumberVerified;
  private String addressFormatted;
  private String addressStreet;
  private String addressLocality;
  private String addressRegion;
  private String addressPostalCode;
  private String addressCountry;
  private int updatedAt; // seconds since the Unix epoch (1970-01-01T0:0:0Z)
  
  public UserInfo()
  {
  }
  
  public UserInfo(String sub)
  {
    this.sub = sub;
  }
  
  public UserInfo(String sub, String givenName, String familyName, String nickname, String email)
  {
    this.sub        = sub;
    this.givenName  = givenName;
    this.familyName = familyName;
    this.nickname   = nickname;
    this.email      = email;
    
    this.name       = "";
    if(givenName != null) {
      this.name += givenName;
    }
    if(familyName != null && familyName.length() > 0) {
      if(this.name != null && this.name.length() > 0) {
        this.name += " ";
      }
      this.name += familyName;
    }
  }
  
  public String getSub() {
    return sub;
  }

  public void setSub(String sub) {
    this.sub = sub;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getPreferredUsername() {
    return preferredUsername;
  }

  public void setPreferredUsername(String preferredUsername) {
    this.preferredUsername = preferredUsername;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isEmailVerified() {
    return emailVerified;
  }

  public void setEmailVerified(boolean emailVerified) {
    this.emailVerified = emailVerified;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public String getZoneinfo() {
    return zoneinfo;
  }

  public void setZoneinfo(String zoneinfo) {
    this.zoneinfo = zoneinfo;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public boolean isPhoneNumberVerified() {
    return phoneNumberVerified;
  }

  public void setPhoneNumberVerified(boolean phoneNumberVerified) {
    this.phoneNumberVerified = phoneNumberVerified;
  }

  public String getAddressFormatted() {
    return addressFormatted;
  }

  public void setAddressFormatted(String addressFormatted) {
    this.addressFormatted = addressFormatted;
  }

  public String getAddressStreet() {
    return addressStreet;
  }

  public void setAddressStreet(String addressStreet) {
    this.addressStreet = addressStreet;
  }

  public String getAddressLocality() {
    return addressLocality;
  }

  public void setAddressLocality(String addressLocality) {
    this.addressLocality = addressLocality;
  }

  public String getAddressRegion() {
    return addressRegion;
  }

  public void setAddressRegion(String addressRegion) {
    this.addressRegion = addressRegion;
  }

  public String getAddressPostalCode() {
    return addressPostalCode;
  }

  public void setAddressPostalCode(String addressPostalCode) {
    this.addressPostalCode = addressPostalCode;
  }

  public String getAddressCountry() {
    return addressCountry;
  }

  public void setAddressCountry(String addressCountry) {
    this.addressCountry = addressCountry;
  }

  public int getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(int updatedAt) {
    this.updatedAt = updatedAt;
  }
  
  // Utilities
  public boolean hasAddress()
  {
    if(addressFormatted  != null && addressFormatted.length()  > 0) return true;
    if(addressStreet     != null && addressStreet.length()     > 0) return true;
    if(addressLocality   != null && addressLocality.length()   > 0) return true;
    if(addressRegion     != null && addressRegion.length()     > 0) return true;
    if(addressPostalCode != null && addressPostalCode.length() > 0) return true;
    if(addressCountry    != null && addressCountry.length()    > 0) return true;
    return false;
  }
  
  // IOAuthObject
  
  public Map<String, Object> toMap() {
    Map<String, Object> mapResult = new LinkedHashMap<String, Object>(18);
    mapResult.put("sub",                sub);
    mapResult.put("name",               name);
    mapResult.put("given_name",         givenName);
    mapResult.put("family_name",        familyName);
    mapResult.put("middle_name",        middleName);
    mapResult.put("nickname",           nickname);
    mapResult.put("preferred_username", preferredUsername);
    mapResult.put("profile",            profile);
    mapResult.put("picture",            picture);
    mapResult.put("website",            website);
    if(email != null && email.length() > 0) {
      mapResult.put("email",            email);
      mapResult.put("email_verified",   emailVerified);
    }
    mapResult.put("gender",             gender);
    mapResult.put("birthdate",          birthdate);
    mapResult.put("zoneinfo",           zoneinfo);
    mapResult.put("locale",             locale);
    if(phoneNumber != null && phoneNumber.length() > 0) {
      mapResult.put("phone_number",     phoneNumber);
      mapResult.put("phone_number_verified", phoneNumberVerified);
    }
    if(hasAddress()) {
      Map<String, Object> mapAddress = new LinkedHashMap<String, Object>();
      mapAddress.put("formatted",      addressFormatted);
      mapAddress.put("street_address", addressStreet);
      mapAddress.put("locality",       addressLocality);
      mapAddress.put("region",         addressRegion);
      mapAddress.put("postal_code",    addressPostalCode);
      mapAddress.put("country",        addressCountry);
      
      mapResult.put("address", mapAddress);
    }
    mapResult.put("updated_at", updatedAt);
    return mapResult;
  }
  
  @Override
  public String toQueryString() {
    return Utils.toQueryString(toMap());
  }
  
  @Override
  public String toJSON() {
    return Utils.toJSON(toMap());
  }
  
  @Override
  public String toHeaderValue() {
    return Utils.toHeaderValue(toMap());
  }
  
  // Object
  
  @Override
  public boolean equals(Object object) {
    if(object instanceof UserInfo) {
      String sSub = ((UserInfo) object).getSub();
      if(sub == null && sSub == null) return true;
      return sub != null && sub.equals(sSub);
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    if(sub == null) return 0;
    return sub.hashCode();
  }
  
  @Override
  public String toString() {
    return "UserInfo(" + sub + "," + name + ")";
  }
}
