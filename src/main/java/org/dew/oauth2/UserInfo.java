package org.dew.oauth2;

import java.io.Serializable;

public 
class UserInfo implements Serializable
{
  private static final long serialVersionUID = 2215603393991908598L;
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
  
  public String toJson() {
    if(sub == null) sub = "";
    
    String result = "{";
    result += "\"sub\":\"" + sub.replace('"', '\'') + "\"";
    if(name != null && name.length() > 0) {
      result += ",\"name\":\"" + name.replace('"', '\'') + "\"";
    }
    if(givenName != null && givenName.length() > 0) {
      result += ",\"given_name\":\"" + givenName.replace('"', '\'') + "\"";
    }
    if(familyName != null && familyName.length() > 0) {
      result += ",\"family_name\":\"" + familyName.replace('"', '\'') + "\"";
    }
    if(middleName != null && middleName.length() > 0) {
      result += ",\"middle_name\":\"" + middleName.replace('"', '\'') + "\"";
    }
    if(nickname != null && nickname.length() > 0) {
      result += ",\"nickname\":\"" + nickname.replace('"', '\'') + "\"";
    }
    if(preferredUsername != null && preferredUsername.length() > 0) {
      result += ",\"preferred_username\":\"" + preferredUsername.replace('"', '\'') + "\"";
    }
    if(profile != null && profile.length() > 0) {
      result += ",\"profile\":\"" + profile.replace('"', '\'') + "\"";
    }
    if(picture != null && picture.length() > 0) {
      result += ",\"picture\":\"" + picture.replace('"', '\'') + "\"";
    }
    if(website != null && website.length() > 0) {
      result += ",\"website\":\"" + website.replace('"', '\'') + "\"";
    }
    if(email != null && email.length() > 0) {
      result += ",\"email\":\"" + email.replace('"', '\'') + "\"";
      result += ",\"email_verified\":" + emailVerified;
    }
    if(gender != null && gender.length() > 0) {
      result += ",\"gender\":\"" + gender.replace('"', '\'') + "\"";
    }
    if(birthdate != null && birthdate.length() > 0) {
      result += ",\"birthdate\":\"" + birthdate.replace('"', '\'') + "\"";
    }
    if(zoneinfo != null && zoneinfo.length() > 0) {
      result += ",\"zoneinfo\":\"" + zoneinfo.replace('"', '\'') + "\"";
    }
    if(locale != null && locale.length() > 0) {
      result += ",\"locale\":\"" + locale.replace('"', '\'') + "\"";
    }
    if(phoneNumber != null && phoneNumber.length() > 0) {
      result += ",\"phone_number\":\"" + phoneNumber.replace('"', '\'') + "\"";
      result += ",\"phone_number_verified\":" + phoneNumberVerified;
    }
    if(addressFormatted != null && addressFormatted.length() > 0) {
      result += ",\"address\":{";
      result += "\"formatted\":\"" + addressFormatted.replace('"', '\'') + "\"";
      if(addressStreet != null && addressStreet.length() > 0) {
        result += ",\"street_address\":\"" + addressStreet.replace('"', '\'') + "\"";
      }
      if(addressLocality != null && addressLocality.length() > 0) {
        result += ",\"locality\":\"" + addressLocality.replace('"', '\'') + "\"";
      }
      if(addressRegion != null && addressRegion.length() > 0) {
        result += ",\"region\":\"" + addressRegion.replace('"', '\'') + "\"";
      }
      if(addressPostalCode != null && addressPostalCode.length() > 0) {
        result += ",\"postal_code\":\"" + addressPostalCode.replace('"', '\'') + "\"";
      }
      if(addressCountry != null && addressCountry.length() > 0) {
        result += ",\"country\":\"" + addressCountry.replace('"', '\'') + "\"";
      }
      result += "}";
    }
    if(updatedAt > 0) {
      result += ",\"updated_at\":" + updatedAt;
    }
    result += "}";
    return result;
  }
  
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
    return "UserInfo(" + sub + ")";
  }
}
