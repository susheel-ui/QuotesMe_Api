# QuotesMe_Api
Root Map
https://quotesme-api.onrender.com
GET
/public
This endpoint retrieves public information.
Request
This is a simple HTTP GET request to retrieve public information.
Response
The response returned is in XML format with a status code of 200. As per the user's request, the response is documented as a JSON schema:\
Create User
/public/create-user
This endpoint allows the creation of a new user.
Request Body
username (string, required): The username of the new user.
password (string, required): The password of the new user.

Response
The response is in JSON format and has the following schema:


JSON
{

"id": {
"timestamp": "string",
"date": "string"
},
"username": "string",
"password": "string",
"userQuotes": ["string"],
"roles": ["string"]

}
The response includes the newly created user's ID, username, password, user quotes, and roles.

GET
/public/get-all-quotes
This endpoint retrieves all quotes available in the public domain.
Request
This is a simple GET request and does not require a request body.
Response
The response is a JSON array containing objects with the following properties:
id (object) - An object with timestamp and date properties
body (string) - The quote body
categories (string) - Categories associated with the quote
author (string) - The author of the quote
date (string) - The date of the quote

Example response:


JSON








[
{
"id": {
"timestamp": 0,
"date": ""
},
"body": "",
"categories": "",
"author": "",
"date": null
}
]


JSON Schema


JSON








{
"type": "array",
"items": {
"type": "object",
"properties": {
"id": {
"type": "object",
"properties": {
"timestamp": {
"type": "number"
},
"date": {
"type": "string"
}
}
},
"body": {
"type": "string"
},
"categories": {
"type": "string"
},
"author": {
"type": "string"
},
"date": {
"type": ["string", "null"]
}
}
}
}

public
﻿

GET
getAllQuotes
•••••••/public/get-all-quotes
GET
/public/get-all-quotes
localhost:8080/public/get-all-quotes

This endpoint retrieves all quotes available in the public domain.

Request
This is a simple GET request and does not require a request body.

Response
The response is a JSON array containing objects with the following properties:

id (object) - An object with timestamp and date properties
body (string) - The quote body
categories (string) - Categories associated with the quote
author (string) - The author of the quote
date (string) - The date of the quote
Example response:

JSON
[
{
"id": {
"timestamp": 0,
"date": ""
},
"body": "",
"categories": "",
"author": "",
"date": null
}
]
JSON Schema
JSON
{
"type": "array",
"items": {
"type": "object",
"properties": {
"id": {
"type": "object",
"properties": {
"timestamp": {
"type": "number"
},
"date": {
"type": "string"
}
}
},
"body": {
"type": "string"
},
"categories": {
"type": "string"
},
"author": {
"type": "string"
},
"date": {
"type": ["string", "null"]
}
}
}
}
﻿

POST
Signup
•••••••/public/create-user
Create User
/public/create-user

This endpoint allows the creation of a new user.

Request Body
username (string, required): The username of the new user.
password (string, required): The password of the new user.
Response
The response is in JSON format and has the following schema:

JSON
{
"id": {
"timestamp": "string",
"date": "string"
},
"username": "string",
"password": "string",
"userQuotes": ["string"],
"roles": ["string"]
}
The response includes the newly created user's ID, username, password, user quotes, and roles.

﻿

Body
raw (json)
json
{
"username":"anjul",
"password":"anjul"
}
GET
healthCheck
•••••••/public
GET
/public
This endpoint retrieves public information.

Request
This is a simple HTTP GET request to retrieve public information.

Response
The response returned is in XML format with a status code of 200. As per the user's request, the response is documented as a JSON schema:

JSON
{
"type": "object",
"properties": {
"status": {
"type": "number",
"description": "The status code of the response"
},
"content-type": {
"type": "string",
"description": "The type of content in the response"
},
"data": {
"type": "null",
"description": "The data content of the response"
}
}
}
﻿

UserTesting
﻿

GET
Login
•••••••/user
The GET request to localhost:8080/user retrieves user information. The response returned with a status code of 200 and a content type of text/xml. To document the response as a JSON schema, the XML response should be converted to a corresponding JSON schema.

﻿

Authorization
Basic Auth
Username
<username>
Password
<password>
PUT
updateUser
•••••••/user
This endpoint allows you to update user information via an HTTP PUT request to localhost:8080/user. The request should include a raw request body with the user's username and password.

Request Body
username (string): The username of the user.
password (string): The password of the user.
Response
Upon a successful execution, the endpoint returns a status code of 200

﻿

Authorization
Basic Auth
Username
<username>
Password
<password>
Body
raw (json)
json
{
"username":"ankita"
}
DELETE
DeleteUser
•••••••}/user
This endpoint allows you to update user information via an HTTP PUT request to localhost:8080/user. The request should include a raw request body with the user's username and password.

Request Body
username (string): The username of the user.
password (string): The password of the user.
Response
Upon a successful execution, the endpoint returns a status code of 200

﻿

Authorization
Basic Auth
Username
<username>
Password
<password>
Quotes
﻿

GET
GetUserQuoutes
•••••••/quotes/user-quotes
﻿

Authorization
Basic Auth
Username
<username>
Password
<password>
POST
newQuote_usingId
•••••••/quotes
﻿

Authorization
Basic Auth
Username
<username>
Password
<password>
Body
raw (json)
json
{
"body": "Another way to find loving one is not serching",
"categories": "self Motivication"
}
PUT
updateQuote
•••••••/quotes/680ffbf13235df7d345d4e2e
﻿

Authorization
Basic Auth
Username
<username>
Password
<password>
Body
raw (json)
json
{
"body": "Hello worldly",
"categories": "Open Sources"
}
DELETE
DeleteQuote
•••••••/quotes
﻿

Body
raw (json)
json
{
"id":"67f23d75494f80108d6b3ed3"
}



