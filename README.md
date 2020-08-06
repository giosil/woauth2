# WOAuth2

A simple OAuth 2.0 protocol implementation.

## Build

- `git clone https://github.com/giosil/woauth2.git`
- `mvn clean install`

## Request Authorization

**Request:**

```
GET /woauth2/authorize?response_type=code&client_id=test&state=xyz&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fwoauth2%2Fcb HTTP/1.1
Content-type: application/x-www-form-urlencoded
```

**Response:**

```
HTTP/1.1 302 Found
Location: http://localhost:8080/woauth2/cb?code=SplxlOBeZQQYbYS6WxSbIA&state=xyz
```

**Failure:**

```
HTTP/1.1 302 Found
Location: http://localhost:8080/woauth2/cb?error=access_denied&state=xyz
```

## Access Token Request

**Request:**

```
POST /woauth2/token HTTP/1.1
Content-type: application/x-www-form-urlencoded

grant_type=password&username=admin&password=pass1234&client_id=test
```

**Response:**

```json
{
  "access_token": "ea39e8df-b8c7-4024-9bdb-08b7ce808917",
  "token_type": "bearer",
  "expires_in": 3600,
}
```

**Failure:**

```
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{"error":"access_denied", "error_description":"Access denied."}
```

## Get user info (Standard Claims OpenId)

**Request:**

```
GET /woauth2/userinfo HTTP/1.1
Authorization: Bearer ea39e8df-b8c7-4024-9bdb-08b7ce808917
```

**Response:**

```json
{
  "sub"         : "12345",
  "name"        : "Test Dev",
  "given_name"  : "Test",
  "family_name" : "Dev",
  "nickname"    : "admin",
  "email"       : "test.dev@example.com"
}
```

**Failure:**

```
HTTP/1.1 403 Forbidden
WWW-Authenticate: Bearer error="invalid_token" error_description="Invalid token."
```

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
