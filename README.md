# WOAuth2

A simple OAuth 2.0 protocol implementation.

## Build

- `git clone https://github.com/giosil/woauth2.git`
- `mvn clean install`

## Request Token

**Request:**

```
POST /woauth2/token HTTP/1.1
Content-type: application/x-www-form-urlencoded
```

**grant_type**=password&**username**=admin&**password**=admin&**client_id**=test

**Response:**

```json
{
  "access_token": "ea39e8df-b8c7-4024-9bdb-08b7ce808917",
  "token_type": "bearer",
  "expires_in": 3600,
}
```

## Get User Info

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

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
