# SoccerRecords Spring-Rest

To run SoccerRecords Spring-Rest:

 - List all teams: 
	curl -i -X GET http://localhost:8080/pa165/rest/teams
 - List a team by its id:
	curl -i -X GET http://localhost:8080/pa165/rest/teams/9
 - Create a team:	
	curl -X POST -i -H "Content-Type: application/json" --data '{"name":"aaa","city":"Manchester","country":"Enland"}' http://localhost:8080/pa165/rest/teams/creat
 - Delete a team:
	curl -X DELETE -i -H  http://localhost:8080/pa165/rest/teams/9


