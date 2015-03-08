# FundsXpert API #
API for requesting data from the backend

Each of the subsections is a different table in the database. The tables within are the allowed methods of that subsection.
*unless otherwise specified all arguments are String.

## user ##
type  | method name | arguments
----- | ----------- | ---------
GET | ping | NONE
POST| login | username,password
POST| addUser | username,password,email,name
POST| deleteUser | username
GET | getAllActiveUsers | NONE

## contact ##
type  | method name | arguments
----- | ----------- | ---------
GET | ping | NONE
POST| addContact | name,email,message,(int)refundAmount
POST| removeContact | id
GET | selectAll | NONE
POST| getById | id
POST| getByName | name


## news ##
type  | method name | arguments
----- | ----------- | ---------
GET | ping | NONE
POST| addNews | title,text,user
POST| removeNews | id
GET | selectAll | NONE
POST| getById | id
POST| getByTitle | title


## appraisal ##
type  | method name | arguments
----- | ----------- | ---------
GET | ping | NONE
GET | getAppraisalLocation/{pid} | pid
GET | getAppraisalLocationByDistrict/{district} | district
GET | getAppraisalLocationByAddress/{address} | address
POST| addAppraisalLocation | DTO
POST| updateAppraisalLocation | DTO
GET | getAppraisalData/{taxYear}/{pid} | taxYear,pid
GET | getAppraisalDataInAssessedRange/{taxYear}/{fromValue}/{toValue} | taxYear,fromValue,toValue


# Example AngularJS Usage #
```JavaScript
var userData = {'username':'mike','password':'blah'} // arguments go here in JSON notation

var request = {
    method: 'POST', // replace with method above
    // path must be altered to point at correct table
    url: 'http://localhost:8080/fx-rest-1.0/user/login', // for my local test machine
    			OR
    url: 'http://www.mikemeding.com/fx/user/login', // for my deployed backend    
    data: userData
};

$http(request)
    .success(function (data, status, headers, config, response) { // If call successful
        console.log("Login Sucessful");
        console.log('status: ' + status);
        // DO SUCCESS STUFF HERE
    })
    .error(function (data, status, headers, config, response) { // If call fails
        console.log("Login Failed");
        console.log('response: ' + response); // response will contain reason why                
        // DO FAIL STUFF HERE
    });
```
