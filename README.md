Petstore API Order Tests (BDD - Rest Assured) 
========================================
The aim of this framework is provides an automation suite test frame to test the order methods of Petstore APIs.

The Order scenarios is be located within the feature files: 
 - src/test/resources/features/Order.feature


```
To run framwork you need:
- Java 1.8+
- Maven
- An IDE
```
**Important: This framework has only been tested on a Mac. If possible please use a Mac to run the test framework**

In order to execute the automation suite navigate to the Project directory within a Terminal window and run the command: **'mvn clean test'**.

Report file can be found *'target/reports/test-report/index.html'*

*Assumption:* Before run the suite an order with 4 id should be available or created for "Delete an order successfully" scenario. 

The Endpoint do not return standard response. Especially for delete order the return message might change.

