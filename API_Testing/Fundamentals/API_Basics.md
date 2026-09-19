# API Basics — SDET Revision Notes

## 1. What is an API?

**API = Application Programming Interface**

An API allows two software systems/components to communicate with each other.

In a typical application:

```text
User
 ↓
UI / Mobile App
 ↓
API Request
 ↓
Backend / Server
 ↓
Business Logic
 ↓
Database
 ↓
API Response
 ↓
UI / Mobile App
```

### Simple example

When a user clicks **Login**:

```text
User enters mobile/password
        ↓
Application sends login API request
        ↓
Backend validates credentials
        ↓
Backend checks required data
        ↓
Backend sends response
        ↓
Application displays result
```

API testing focuses on the communication between the client and backend without necessarily going through the UI.

---

# 2. UI Testing vs API Testing

## UI Testing

```text
Test
 ↓
UI
 ↓
Frontend
 ↓
Backend/API
 ↓
Database
```

Example:

```text
Open Login screen
 ↓
Enter mobile number
 ↓
Enter OTP
 ↓
Click Login
 ↓
Verify Home screen
```

## API Testing

```text
Test
 ↓
API
 ↓
Backend
 ↓
Database
```

Example:

```text
POST /login
 ↓
Validate status code
 ↓
Validate response body
 ↓
Validate authentication/token
```

### Key difference

> UI testing validates the application through the user's interface, while API testing directly validates backend communication and business logic.

---

# 3. What is REST?

**REST = Representational State Transfer**

REST is an architectural style commonly used for building web APIs.

REST APIs commonly use HTTP methods to perform operations on resources.

Example resource:

```text
/users
```

Common operations:

| HTTP Method | Purpose                   | Example           |
| ----------- | ------------------------- | ----------------- |
| GET         | Retrieve data             | `GET /users`      |
| POST        | Create/submit data        | `POST /users`     |
| PUT         | Replace/update resource   | `PUT /users/1`    |
| PATCH       | Partially update resource | `PATCH /users/1`  |
| DELETE      | Delete resource           | `DELETE /users/1` |

---

# 4. HTTP Methods

## GET

Used to retrieve data.

```http
GET /users/10
```

Meaning:

> Give me user with ID 10.

---

## POST

Commonly used to create a resource or submit data.

```http
POST /users
```

Request body:

```json
{
  "name": "Siddarth",
  "email": "siddarth@example.com"
}
```

---

## PUT

Generally used to replace/update a resource.

```http
PUT /users/10
```

Example:

```json
{
  "name": "Siddarth",
  "email": "new@example.com",
  "city": "Delhi"
}
```

---

## PATCH

Used for a partial update.

```http
PATCH /users/10
```

Example:

```json
{
  "city": "Bangalore"
}
```

Only the specified field needs to be modified.

### Remember

```text
PUT   → Full replacement/update
PATCH → Partial update
```

Individual APIs may define their own exact behavior.

---

## DELETE

Used to delete a resource.

```http
DELETE /users/10
```

---

# 5. What is an Endpoint?

An **endpoint** is a specific API URL/path used to perform an operation.

Example:

```text
https://api.example.com/users/10
```

Here:

```text
https://api.example.com
        ↓
Base URL

/users/10
        ↓
API path / endpoint
```

Different endpoints may represent different operations:

```text
GET    /users
GET    /users/10
POST   /users
PUT    /users/10
DELETE /users/10
```

---

# 6. API Request

A request is sent from the client to the server.

A request can contain:

```text
HTTP Method
URL
Headers
Path Parameters
Query Parameters
Request Body
```

Example:

```http
POST /users
Content-Type: application/json
Authorization: Bearer xyz123
```

Request body:

```json
{
  "name": "Siddarth",
  "email": "siddarth@example.com"
}
```

---

# 7. API Response

The server sends a response back to the client.

A response can contain:

```text
Status Code
Response Headers
Response Body
Response Time
```

Example:

```http
HTTP/1.1 201 Created
Content-Type: application/json
```

Response body:

```json
{
  "id": 101,
  "name": "Siddarth",
  "email": "siddarth@example.com"
}
```

---

# 8. HTTP Status Codes

## 2xx — Success

| Code | Meaning    |
| ---- | ---------- |
| 200  | OK         |
| 201  | Created    |
| 202  | Accepted   |
| 204  | No Content |

### Remember

```text
2xx = Request was successfully processed
```

---

## 4xx — Client-side/request errors

| Code | Meaning               |
| ---- | --------------------- |
| 400  | Bad Request           |
| 401  | Unauthorized          |
| 403  | Forbidden             |
| 404  | Not Found             |
| 409  | Conflict              |
| 422  | Unprocessable Content |

### 401 vs 403

**401 Unauthorized**

Usually means:

> Authentication is missing, invalid, or unsuccessful.

Example:

```text
Invalid/expired token
```

**403 Forbidden**

Usually means:

> The server understood who you are, but you don't have permission to perform the operation.

Example:

```text
Normal user trying to access an admin-only API
```

---

## 5xx — Server-side errors

| Code | Meaning               |
| ---- | --------------------- |
| 500  | Internal Server Error |
| 502  | Bad Gateway           |
| 503  | Service Unavailable   |

### Remember

```text
4xx → Problem with request/client side
5xx → Server-side failure
```

This is a general classification; the exact meaning depends on the API and situation.

---

# 9. Headers

Headers provide additional information about the request or response.

### Common request headers

```http
Content-Type: application/json
Authorization: Bearer xyz123
Accept: application/json
```

### Important headers

**Content-Type**

Tells the server what format the request body uses.

```http
Content-Type: application/json
```

**Authorization**

Used to provide authentication credentials/token.

```http
Authorization: Bearer abc123
```

**Accept**

Tells the server what response format the client prefers.

```http
Accept: application/json
```

---

# 10. Request Body

The request body contains data sent to the server.

Example:

```http
POST /users
```

```json
{
  "name": "Siddarth",
  "email": "siddarth@example.com"
}
```

Request bodies are commonly used with:

```text
POST
PUT
PATCH
```

GET requests generally don't use a request body.

---

# 11. Path Parameters

A path parameter is part of the URL path and usually identifies a specific resource.

Example:

```http
GET /users/123
```

Here:

```text
123 = Path Parameter
```

Conceptually:

```text
/users/{userId}
```

Request:

```text
/users/123
```

---

# 12. Query Parameters

Query parameters are added after `?` in a URL.

Example:

```http
GET /users?page=2&limit=10
```

Here:

```text
page = 2
limit = 10
```

Query parameters are commonly used for:

```text
Filtering
Searching
Sorting
Pagination
Optional parameters
```

Example:

```http
GET /users?city=Delhi
```

---

# 13. Path Parameter vs Query Parameter

### Path parameter

Used to identify a specific resource.

```http
/users/123
```

```text
123 → user ID
```

### Query parameter

Used to modify/filter the request.

```http
/users?city=Delhi
```

```text
city=Delhi → filter
```

### Easy way to remember

```text
Path  → Which resource?
Query → What/how should I retrieve it?
```

---

# 14. JSON

**JSON = JavaScript Object Notation**

It is commonly used to exchange data between client and server.

Example:

```json
{
  "id": 101,
  "name": "Siddarth",
  "active": true
}
```

Common JSON data types:

```text
String
Number
Boolean
Object
Array
null
```

Example:

```json
{
  "name": "Siddarth",
  "age": 25,
  "active": true,
  "skills": ["Java", "Selenium", "API Testing"]
}
```

---

# 15. What is API Testing?

API testing validates whether an API behaves correctly.

We validate:

```text
Status Code
Response Body
Response Headers
Response Schema
Business Logic
Data Accuracy
Authentication
Authorization
Error Handling
Response Time
```

---

# 16. Positive Testing

Test valid inputs and expected behavior.

Example:

```text
POST /users
```

Valid request:

```json
{
  "name": "Siddarth",
  "email": "siddarth@example.com"
}
```

Verify:

```text
Expected status code
Expected response
User created
Required fields present
```

---

# 17. Negative Testing

Test invalid inputs and unexpected situations.

Examples:

```text
Missing required field
Invalid data
Invalid ID
Invalid authentication
Expired token
Wrong HTTP method
Empty request
Invalid JSON
Duplicate data
```

Example:

```json
{
  "name": "",
  "email": "invalid-email"
}
```

Verify that the API returns an appropriate error response.

---

# 18. Authentication vs Authorization

### Authentication

> Who are you?

Example:

```text
Username + Password
OTP
Token
OAuth
```

### Authorization

> What are you allowed to do?

Example:

```text
Admin → Can delete users
Normal User → Cannot delete users
```

### Easy memory trick

```text
Authentication → Identity
Authorization  → Permission
```

---

# 19. API Testing Example — Login

Suppose an application has:

```http
POST /login
```

Request:

```json
{
  "mobile": "9876543210",
  "password": "password123"
}
```

Backend:

```text
Receive request
      ↓
Validate request
      ↓
Authenticate user
      ↓
Check backend/database
      ↓
Apply business logic
      ↓
Generate authentication/session information
      ↓
Return response
```

Response:

```json
{
  "success": true,
  "message": "Login successful",
  "token": "abc123"
}
```

As an SDET, validate:

```text
Status code
success = true
message
token exists
token format
response schema
response time
```

---

# 20. API Testing Example — OTP

Example:

```http
POST /verify-otp
```

Request:

```json
{
  "mobile": "9876543210",
  "otp": "123456"
}
```

### Positive scenarios

```text
Correct OTP
Valid mobile number
Valid OTP within expiry
```

### Negative scenarios

```text
Wrong OTP
Expired OTP
Empty OTP
Invalid OTP format
Missing mobile number
Invalid mobile number
Too many attempts
Missing/invalid authentication
```

Don't simply verify:

```text
200 = Pass
```

Instead verify the **complete response and business behavior**.

---

# 21. Mocking vs Real Backend

A **mock** simulates a dependency or backend response.

For example:

```text
Application
    ↓
Mock API
    ↓
Fake response
```

Example mock response:

```json
{
  "success": true,
  "balance": 50000
}
```

Mocking can be useful when:

```text
Backend isn't ready
External service is unavailable
You need predictable test data
You want to test specific failure scenarios
```

### Important

Don't assume mocked data is part of the real production API flow.

---

# 22. Why API Testing is Important for SDETs

API testing allows us to:

* Test backend logic directly
* Test without depending on UI
* Find defects earlier
* Run tests faster
* Validate positive and negative scenarios
* Test authentication/authorization
* Validate data and business rules
* Automate large numbers of scenarios

---

# 23. UI + API Automation

A strong SDET doesn't choose only one.

A realistic test strategy might be:

```text
              Application
                   |
       ┌───────────┴───────────┐
       ↓                       ↓
      UI                     APIs
       ↓                       ↓
User workflows          Business logic
```

Use API automation for extensive backend validation and UI automation for critical end-to-end user journeys.

---

# 24. Interview Quick Answers

### What is an API?

> An API is an interface that allows different software components to communicate with each other. In API testing, we directly validate requests, responses, business logic, authentication, and error handling.

### What is REST API?

> A REST API is an API following REST architectural principles and commonly using HTTP methods such as GET, POST, PUT, PATCH, and DELETE to operate on resources.

### GET vs POST?

```text
GET  → Retrieve data
POST → Create/submit data
```

### PUT vs PATCH?

```text
PUT   → Generally replaces/updates the complete resource
PATCH → Generally updates part of a resource
```

### Path parameter vs query parameter?

```text
Path  → Identifies a resource
Query → Filters/modifies the request
```

### 401 vs 403?

```text
401 → Authentication problem
403 → Authenticated but not permitted
```

### What do you validate in an API?

```text
Status code
Response body
Headers
Schema
Business logic
Authentication
Authorization
Error handling
Response time
```

---

# 25. The Mental Model to Remember

Whenever you see an API, think:

```text
                 API
                  |
       ┌──────────┼──────────┐
       ↓          ↓          ↓
    REQUEST    BACKEND    RESPONSE
       |          |          |
       ↓          ↓          ↓
    Method     Business    Status
    URL        Logic       Body
    Headers    Database    Headers
    Params                 Time
    Body
```

And as an SDET:

```text
REQUEST
   ↓
Is the request correct?
   ↓
BACKEND
   ↓
Is the business logic correct?
   ↓
RESPONSE
   ↓
Is the response correct?
   ↓
PASS / FAIL
```

## 🎯 Day-25 takeaway

For today, **don't worry about REST Assured code yet**.

Make sure these concepts are solid:

```text
API
REST
HTTP
Endpoint
Request
Response
HTTP Methods
Status Codes
Headers
Request Body
Path Parameters
Query Parameters
JSON
Authentication
Authorization
Positive Testing
Negative Testing
Mocking
```

Once these are clear, **Postman becomes much easier**, and tomorrow when you start REST Assured, you'll understand what the Java code is actually doing rather than just memorizing:

```java
given()
    .when()
    .get("/users")
    .then()
    .statusCode(200);
```
