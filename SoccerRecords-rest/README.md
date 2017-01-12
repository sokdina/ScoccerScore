# SoccerRecords Spring-Rest

To run SoccerRecords Spring-Rest:

 - List all teams: 
	curl -i -X GET http://localhost:8080/pa165/rest/teams
 - List a team by its id:
	curl -i -X GET http://localhost:8080/pa165/rest/teams/3
 - Create a team:	
	curl -X POST -i -H "Content-Type: application/json" --data '{"name":"Valencia CF","city":"Valencia","country":"Spain"}' http://localhost:8080/pa165/rest/teams/create
 - Delete a team:
	curl -i -X DELETE http://localhost:8080/pa165/rest/teams/7
 - Update a team by its id:
	curl -X PUT -i -H "Content-Type: application/json" --data '{"id":9,"name":"CA Osasuna","city":"Pamplona","country":"Spain"}' http://localhost:8080/pa165/rest/teams/9


