# HTTP Fundamentals — Quick Revision

## 1. HTTP

**HTTP = HyperText Transfer Protocol**

Used for communication between client and server.

```text
Client → HTTP Request → Server
Client ← HTTP Response ← Server
```

---

## 2. HTTP Request

Contains:

```text
Method
URL
Headers
Parameters
Request Body
```

Example:

```http
POST /users
Content-Type: application/json

{
  "name": "Siddarth"
}
```

---

## 3. HTTP Response

Contains:

```text
Status Code
Headers
Response Body
```

Example:

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "id": 101,
  "name": "Siddarth"
}
```

---

## 4. HTTP Methods

| Method | Purpose             |
| ------ | ------------------- |
| GET    | Retrieve            |
| POST   | Create/Submit       |
| PUT    | Full update/replace |
| PATCH  | Partial update      |
| DELETE | Delete              |

**Remember:** `PUT → full`, `PATCH → partial`

---

## 5. URL Structure

```text
https://api.example.com/users/123?page=2
│       │               │        │
│       │               │        └─ Query Parameter
│       │               └────────── Path
│       └────────────────────────── Host
└────────────────────────────────── Protocol
```

---

## 6. Parameters

### Path Parameter

Identifies a resource:

```http
GET /users/123
```

`123` = Path Parameter

### Query Parameter

Filters/modifies request:

```http
GET /users?page=2&limit=10
```

`page`, `limit` = Query Parameters

**Shortcut:**

```text
Path  → Which resource?
Query → How do I want the data?
```

---

## 7. Important Headers

```http
Content-Type: application/json
Accept: application/json
Authorization: Bearer <token>
```

* **Content-Type** → format of data being sent
* **Accept** → preferred response format
* **Authorization** → authentication credentials/token

---

## 8. Request Body

Data sent to the server.

```json
{
  "name": "Siddarth",
  "email": "test@example.com"
}
```

Commonly used with:

```text
POST
PUT
PATCH
```

---

## 9. Status Codes

### 2xx — Success

```text
200 → OK
201 → Created
204 → No Content
```

### 4xx — Request/Client-related errors

```text
400 → Bad Request
401 → Unauthorized
403 → Forbidden
404 → Not Found
409 → Conflict
```

### 5xx — Server errors

```text
500 → Internal Server Error
502 → Bad Gateway
503 → Service Unavailable
```

**401 vs 403:**

```text
401 → Authentication problem
403 → Permission/Authorization problem
```

---

## 10. HTTP vs HTTPS

```text
HTTP  → Standard HTTP communication
HTTPS → HTTP + TLS encryption
```

Production APIs generally use HTTPS.

---

## 11. API Testing Mindset

For every API, validate:

```text
Request
 ├─ Method
 ├─ URL
 ├─ Headers
 ├─ Parameters
 └─ Body

Response
 ├─ Status Code
 ├─ Headers
 ├─ Body
 ├─ Data/Schema
 └─ Response Time
```

Also test:

```text
Positive scenarios
Negative scenarios
Authentication
Authorization
Error handling
```

---

## 🎯 Interview Cheat Sheet

```text
HTTP      → Client-server communication protocol
GET       → Retrieve
POST      → Create/Submit
PUT       → Full update
PATCH     → Partial update
DELETE    → Delete

Request   → Method + URL + Headers + Params + Body
Response  → Status + Headers + Body

2xx       → Success
4xx       → Request/Client error
5xx       → Server error

401       → Authentication
403       → Authorization

Path      → Identifies resource
Query     → Filters/modifies request

REST API  → Commonly uses HTTP methods to operate on resources
```
