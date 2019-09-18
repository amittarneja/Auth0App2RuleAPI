Instructions for running the code.

I developed the code using Eclipse, JAVA, Maven, and Apache Tomcat (to deploy the code). Please follow the instruction to deploy the Webservice.

1. Open the code in Eclipse and perform Maven clean/Install.
2. Right-click on the project -> properties -> Deployment assembly -> Add -> Java Build Path Entries -> Maven dependencies -> Apply and close. This step is to avoid the NOCLASSDEFFOUNDERROR exception.
3. Right-click on the project and select Run As -> Run on Server (I ran it on Apache Tomcat). Follow the instruction in the link to set up Tomcat in eclipse (https://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.stardust.docs.wst%2Fhtml%2Fwst-integration%2Fconfiguration.html).
4. Use your Auth0 API token and Domain URL to get the response from the Auth0 Management V2 API.
5. In the POSTMAN app, Create a New GET request and fill the request information. Enter the URL as http://localhost:8080/Auth0App2RuleAPI/app2rules and Add the header information as shown in the Technical Specification Documents attached.

