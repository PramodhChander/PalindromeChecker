## Palindrome Checker

### Description
This repository contains a REST API isPalindrome that checks whether given text input is palindrome or not.
It performs caching for the computed result, saves the result in text file and loads them into cache during bootup
It also has an extensible Validate annotation that currently validates for no numbers or space in input text but can be extended to any logic
It also contains both end to end and unit testing.
Repo also contains jar file in for direct execution.

### Steps to test the Api:

1. Download the jar from [Jar File](https://github.com/PramodhChander/PalindromeChecker/blob/master/out/artifacts/palindromechecker_jar/palindromechecker.jar) <br />
2. Run the jar file using the below command <br />
   `java -jar palindromechecker.jar` <br />
   Note: Make sure that java path is set and command is run from the downloaded path <br />
3. Hit the API from post man or with below curl command <br />
`curl --location 'http://localhost:8080/palindromechecker/api/v1/isPalindrome' \ 
--header 'Content-Type: application/json' \ 
--data '{ 
    "userName":"Pramodh", 
    "text":"malayalam" 
}'`
4. Observe the response  <br />

**Endpoint:** <br />
`http://localhost:8080/palindromechecker/api/v1/isPalindrome`

**Request:** <br />
```json
{ 
    "userName":"Pramodh", // Username which can be used for future authorizations 
    "text":"malayalam" // text field to check for palindrome 
}
```

**Success Response: (Http Response code - 200)** <br />
```json
{ 
    "status": "Success", // Tells whether the API invocation is Success or Failure or Error (in case of Exception) 
    "isPalindrome": true, // Flag which marks whether the given text is palindrome or not 
    "message": "malayalam is a Palindrome" // Generic message that can contain error message incase of any error 
}
```

**Failure Response: (Http Response code - 400)** <br />
```json
{ 
    "status": "Failed", 
    "message": "Validation Error: userName field is mandatory" 
}
```
