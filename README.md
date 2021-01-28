# microservicesDemo

These is Demo application for demonstrating microservices. In this case I have created application about cart management of an online store.
This application could be very huge hence I am depicting only for very small part of the application 

# Architecture
Architecture of online stores can be lot complicated. I have tried to draw it as much as possible, but still tere are many areas which in themselves can be further architected
This is the simple architectural drawing of the system. 
Link : 

This demo consists of 5 main micro serviecs developed using spring boot: 

	-	CART-SERVICE (port: 9001)  ( this microservice talks to other microservie to complete its functionality)  
	
	-	LOCATION-SERVICE (port: 9002)
	
	-	ORDER-SERVICE (port: 9003)
	
	-	PAYMENT-SERVICE (port: 9004)
	
	-	USER-SERVICE (port: 9004)
	
Apart from these applications there are 2 more applications
	
	-	Service-Discovery (for registring and discovering services)  Port: 
	
	-	API-Gateway (acts as facade/gateway by forwarding to requests to correct applications ) Port: 9100
	
	-	Hystrix dashboard port:9200
	
# Steps to run the applications: 
	
	-	download/clone the application 
	
	-	clean and build all the applications
	
	-	run service registry
	
	-	run all other applications
	
	-	open http://localhost:8761/ - here you should see all the microservices registered along with API-GATEWAY 
	
	-	Now open Postman or any other client 
	
	-	Call the post endpoint from postman:  http://localhost:9100/cart/checkout/ with following data: 
			{
				"userId":2,
				"paymentCardInfo": {
					"cardNumber": "4005-5087-6743-8700-6547",
					"cardSequence": 123	
				},
				"amount": 100, 
				"pickupOrder" : true,
				"itemsList": [{"itemId":1234 , "itemAmount": 40},{"itemId":2354 , "itemAmount": 60}],
				"locationId": 3
			}
			( Note: URL which we mention here has port 9100, when out cart application is running on 9001, this is because API gateway will map this request and forward it to cart microservice)  

	-	This should call other microservices based on service-registry and give the results. 
	
	-	This is loadbalanced already and hence we can add more nodes easily and scale application 
	
	-	You can also query using other rest endpoints from other application using rest endpoint to test the application
	
#	For running hystrix-dashboard 

	- go to http://localhost:9200/hystrix 
	
	- give URL in the input box as http://localhost:9100/actuator/hystrix.stream
	
	- Click on monitor stream

#	Things not covered in this application

	-	authentication and authorization
	
	-	token management 
	
	-	logging can be improved 
	
	- 	unit test and integration tests 
	
	-	Other microservice in the chain 
	
	-	Other technologies like queues, caches, reports, reconcilation engine and other offline jobs 
	
	-	tracing of the requests
	
	-	mentaining app configs externally
	
	
	
