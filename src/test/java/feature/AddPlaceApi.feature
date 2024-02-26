Feature: Goggle Api Test Suite

Scenario Outline: Adding the place details and verifying the data
Given given the Payload "<name>" "<address>" "<language>"
When the path parameter is "AddPlaceApi" and the request is "POST" type
Then the status code is 200
And the "status" is "OK"
And Verify the place_id created maps to the "<name>" using the "GetPlaceApi"

Examples:
	| name 				| address | language |
	| Naveen Bhat | Pune		| French	 |
	| Cypher 			| West si | English  |
	
Scenario: Deleting the data using place id
Given givent the payload to delete the place
When the path parameter is "DeletePlaceApi" and the request is "POST" type
Then the "status" is "OK"