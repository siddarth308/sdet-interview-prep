# Postman Basics — SDET Revision Notes

## 1. What is Postman?

Postman is an API client/testing tool used to:

* Send HTTP requests
* Inspect API responses
* Test APIs manually
* Write API assertions
* Organize requests into collections
* Manage environments and variables

```text
Postman
   ↓
Build HTTP Request
   ↓
Send
   ↓
Receive Response
   ↓
Validate
```

---

# 2. Basic Request Flow

```text
Method + URL
     ↓
Params / Headers / Body
     ↓
Send
     ↓
Response
     ↓
Tests / Assertions
```

---

# 3. HTTP Methods in Postman

| Method | Purpose             |
| ------ | ------------------- |
| GET    | Retrieve data       |
| POST   | Create/submit       |
| PUT    | Full update/replace |
| PATCH  | Partial update      |
| DELETE | Delete              |

---

# 4. GET Request

Example:

```text
GET https://jsonplaceholder.typicode.com/users
```

Specific user:

```text
GET https://jsonplaceholder.typicode.com/users/1
```

Validate:

```text
Status code
Response body
Required fields
Response time
```

---

# 5. Query Parameters

In Postman:

**Params → Key / Value**

Example:

```text
Key   = username
Value = Bret
```

Generates:

```text
?username=Bret
```

Example:

```text
GET /users?username=Bret
```

---

# 6. Path Parameters

Example:

```text
GET /users/1
```

Here:

```text
1 = userId
```

Conceptually:

```text
/users/{userId}
```

---

# 7. Headers

Go to:

**Headers**

Common headers:

```text
Content-Type: application/json
Accept: application/json
Authorization: Bearer <token>
```

### Remember

```text
Content-Type → Format of request body
Accept       → Preferred response format
Authorization → Authentication credentials/token
```

---

# 8. POST Request

Example:

```text
POST https://jsonplaceholder.typicode.com/posts
```

Go to:

**Body → raw → JSON**

Body:

```json
{
  "title": "SDET",
  "body": "Learning API Automation",
  "userId": 1
}
```

Expected result for this API:

```text
201 Created
```

Validate:

```text
Status code
Response body
Created fields
```

---

# 9. PUT Request

Example:

```text
PUT /posts/1
```

Body:

```json
{
  "id": 1,
  "title": "Updated SDET",
  "body": "API Automation",
  "userId": 1
}
```

Generally used for full resource replacement/update.

---

# 10. PATCH Request

Example:

```text
PATCH /posts/1
```

Body:

```json
{
  "title": "Updated Title"
}
```

Generally used for partial updates.

```text
PUT   → Full update/replacement
PATCH → Partial update
```

---

# 11. DELETE Request

Example:

```text
DELETE /posts/1
```

Validate the response status according to the API contract.

---

# 12. Postman Tests

Tests are JavaScript assertions that run **after the API response is received**.

Modern Postman UI:

```text
Scripts → Post-response
```

Example:

```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});
```

Click **Send** → Postman executes the test automatically.

---

# 13. Response Body Test

Example response:

```json
{
  "id": 1,
  "name": "Leanne Graham"
}
```

Test:

```javascript
pm.test("User ID is 1", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData.id).to.eql(1);
});
```

Multiple tests:

```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("User ID is 1", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData.id).to.eql(1);
});

pm.test("User name exists", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData.name).to.exist;
});
```

Result:

```text
✓ Status code is 200
✓ User ID is 1
✓ User name exists
```

---

# 14. Response Time Test

```javascript
pm.test("Response time < 1000ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(1000);
});
```

Use a threshold based on the API's actual performance requirement in real projects.

---

# 15. Collections

A **Collection** organizes related API requests.

Example:

```text
API Testing
│
├── Users
│   ├── Get Users
│   ├── Get User
│   ├── Create User
│   ├── Update User
│   └── Delete User
│
└── Posts
    ├── Get Posts
    └── Create Post
```

---

# 16. Variables

Variables store reusable values.

Example:

```text
{{baseUrl}}
{{userId}}
{{token}}
```

Request:

```text
{{baseUrl}}/users/{{userId}}
```

Benefits:

* Avoid hardcoding
* Reuse values
* Easier environment switching

---

# 17. Environments

Different environments may have different URLs:

```text
DEV
QA
STAGING
PROD
```

Example:

```text
{{baseUrl}}
```

QA:

```text
https://qa-api.example.com
```

DEV:

```text
https://dev-api.example.com
```

Switching environments changes the value of `{{baseUrl}}`.

---

# 18. API Chaining

One API's response can be used by another API.

Example:

```text
Login API
   ↓
Receive token
   ↓
Save token
   ↓
Profile API
   ↓
Authorization: Bearer <token>
```

This is commonly used for authentication workflows.

---

# 19. Postman API Testing Mindset

For every request, validate:

### Request

```text
Method
URL
Parameters
Headers
Body
```

### Response

```text
Status Code
Headers
Body
Schema/Data
Response Time
```

### Negative cases

```text
Invalid input
Missing input
Invalid ID
Invalid authentication
Expired token
Unauthorized access
Duplicate data
```

---

# 20. Postman → REST Assured Mapping

| Postman               | REST Assured               |
| --------------------- | -------------------------- |
| Method                | `get()`, `post()`, etc.    |
| Params                | `.queryParam()`            |
| Headers               | `.header()`                |
| Body                  | `.body()`                  |
| Send                  | `.when()`                  |
| Response              | `.then()`                  |
| Tests                 | Assertions                 |
| Environment variables | Config/properties          |
| Collection            | Automation framework/tests |

Example:

### Postman

```text
GET /users/1
```

Test:

```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});
```

### REST Assured

```java
given()
    .when()
    .get("/users/1")
    .then()
    .statusCode(200);
```

---

# 🎯 Quick Revision

```text
Postman
   ↓
API Client + Testing Tool

GET       → Retrieve
POST      → Create/Submit
PUT       → Full update
PATCH     → Partial update
DELETE    → Delete

Params    → Query parameters
Headers   → HTTP metadata
Body      → Request data

Send      → Execute request
Response  → Server result

Scripts → Post-response
         → Write assertions

Collection → Group requests
Variables  → Reusable values
Environment → DEV/QA/STAGING/PROD

API Chain:
Login → Token → Authenticated API
```

## ⭐ Remember

> **Postman is not just a tool for sending requests. For an SDET, it is a tool to build, inspect, validate, and automate API test scenarios.**
