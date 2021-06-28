# Graph Insight Dashboard

This project provides Java backend support for Disney-Graph Insight Dashboard APIs.

## Getting started

To run locally you will need:
- update the application.properties to use dev properties by setting the key 'spring.profiles.active' to 'dev'
- update the application-dev.properties with DB username and password, DB url is already updated.
- Application can be run in two ways:
	- From Eclipse IDE: Goto to the main java class which is FbAdIdApplication.java and run it as Java Program
	- From terminal: 
		Do maven build : mvn clean package and run the below command in terminal 
		   java -jar <Dir path to Application>/target/gid-0.0.1-SNAPSHOT.jar
	-Checking if app is up:
		Application health check api : http://localhost:8091/gid/api/appHealth
		DB Health check api: http://localhost:8091/gid/api/dbHealth	   

- check logs:
	the log file directory is mentioned in 'log4j.properties'
	
- check apis:
	All the apis can be tested via Swagger UI, available at : http://localhost:8091/gid/api/swagger-ui.html#/
	
- Metrics/monitoring
	- prometheus metrics are available at : http://localhost:8091/gid/api/prometheus
	
## To run in Kubernetes cluster
 
 - After the code changes, push the changes to gitlab
 - GID has continuous delivery to dev.
 - Check logs in K8 cluster:
 	-fetch the K8 pod : kubectl get pods -n <namespace> (for dev it is 'techfinapps-dev')
 	-kubectl exec -it <pod name> /bin/bash -n <namespace>
 	-check app logs in the dir as mentioned in log4j.properties
 	-check container logs : kubectl logs -f <pod name> -n techfinapps-dev
 	
 - Currently the api is deployed in K8 cluster here: 
    https://fintech-gid-api-spring-new-public.us-iad-a.k8s.oracledatacloud.com/gid/api/appHealth	
 	
 - CI/CD for prod will be done manual, work is in progress. 
	
	
	
		
	
	
	
	
	

			 