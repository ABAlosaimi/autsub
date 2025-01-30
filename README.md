# Company's Subscription Management API

A Spring Boot REST API for managing company subscriptions palns by providing a list of featuers that will eaable the company to tacke a faster decisions.

---

## API Endpoints

### Company Registration
- **Method**: `POST`
- **Endpoint**: `/company/auth/register`
- **Request Body** (JSON):
  ```json
  {
    "name": "string",
    "password": "string",
    "email": "string",
    "address": "string",
    "industry": "string",
    "commercial_Registration_Number": "string"
  }


### Response

- **Status code**: `201`
- response body:
  ```json
  { "accessToken": "ACCESS_TOKEN"}

---

  
  


  
