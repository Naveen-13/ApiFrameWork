Feature: Goggle Api Test Suite

Scenario Outline: Adding the place details and verifying the data
Given given the Payload "<name>" "<address>" "<language>"
When the request is post type
Then the status code is 200
And the "status" is "OK"

Examples:
	| name 				| address | language |
	| Naveen Bhat | Pune		| French	 |
	| Cypher 			| West si | English  |
