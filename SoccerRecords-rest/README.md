# SoccerRecords Spring-Rest

To run SoccerRecords Spring-Rest:

 - List all teams: 
	curl -i -X GET http://localhost:8080/pa165/rest/teams
 - List a team by its id:
	curl -i -X GET http://localhost:8080/pa165/rest/teams/9
 - Create a team:	
	curl -X GET -i -H "Content-Type: application/json" --data '{"id":"11L", "name":"Manchester City","city":"Manchester","country":"Enland"}' http://localhost:8080/pa165/rest/teams/creat
 - Delete a team:
	curl -i -X DELETE http://localhost:8080/pa165/rest/teams/9

