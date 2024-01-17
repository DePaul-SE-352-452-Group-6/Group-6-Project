## Group-6-Project
This is group 6 project for [DePaul SE452](http://www.cdm.depaul.edu/academics/pages/courseinfo.aspx?Subject=SE&CatalogNbr=452) which serves as a backend service for video game development similar to [PlayFab](https://playfab.com/).
<br>
* Milestone 1: Define the project and base structure and areas where each members will be working on
* Milestone 2: Add SQL Persistence, Cameron added Account and Account Currency relational data as well as associated unit tests. Zhihong added Currencies, Digital Good data and Account Inventory relational data as well as associated unit tests. Matthew added Leaderboard relational data and unit tests.
* Milestone 3: Add Docker,Documentation, Build Actions, Cameron, Zhihong and Matthew worked on Swagger, Docker, and CRUD service layer for our respective packages.
* Final Milestone: Add Security, nosql and UI layer, Cameron worked on security, Matthew worked on leaderboard UI implementation, Zhihong worked on nosql, UI layer and all relative tests on digital assets in terms of Currency, DigitalGood and NoAccountInventory.

## Project Members

| Member | Feature
| ----------- | -----------
| Cameron | Account Creation and Account Currency
| Matthew | Leaderboards
| Zhihong | Currencies, Digital Goods and Account Inventory

<br/>

## Conflict Resolution
Conflicts need to be resolved during a meeting if possible. Otherwise conflicts may be resolved using merging software and majority vote on important decision making. If no majority is met then a coinflip will be conducted.

<br/>

## Communication Mechanism
We will use Discord to conduct meetings and the channel is [HERE](https://discord.gg/UEtEBERd).
Our schedules are listed on [When2Meet](https://www.when2meet.com/?19400023-628gl) and we will meet on Saturdays at 4PM

<br/>

## Decision Made
| # | Area  | Decision | Alternative | Rationale
| ----------- | ----------- | --- | --- |--- |
| 1 | IDE | IntelliJ |Visual Code | Everyone is familiar with it already
| 2 | Dependency Management  | Gradle | Maven | Gradle is easier to get running and doesn't require Maven to be installed on the local machine to get running
| 3 | Code  | Lombok | N/A | It is what was covered in class
| 4 | Configuration Management  | Yaml | Properties | It is what was covered in class and we are new to using it so it will be good to learn it for the future

## Meeting Journal
| # | Date | Summary |
| ----------- | ----------- | ---
| 1 | 4/2/23 | Review Milestone 1 submission and walk through sourcecontrol software and repository
| 2 | 4/16/23 | Review Milestone 2 requirements and submission
| 3 | 5/11/23 | Review Docker Stuff to get it working

## About The Project
Start the services using Docker Compose:<br>

docker-compose up -d<br>
This command will start all the services defined in the docker-compose.yml file, including PostgreSQL, Elasticsearch, Kibana, and the application itself.<br>

## Accessing the Services
App: https://localhost:8443    !!!you can use these users to login in((larry, divad)  (michael, divad)  (steven, divad))<br>
Elasticsearch: http://localhost:9200<br>
Kibana: http://localhost:5601<br>

Application: Accessible on the port configured in the docker-compose.yml file.<br><br>

Stopping the Services<br>
To stop and remove all the running services, you can use the following command:<br>
docker-compose down<br>

## Apendix
![Milestone 1 Screenshot](/img/milestone-1.png?raw=true "Milestone 1")
![Milestone 2 Screenshot](/img/milestone-2-account-working-code.png?raw=true "Milestone 2 Working Code")
![Milestone 2 Screenshot](/img/milestone-2-account-database.png?raw=true "Milestone 2 Working Account Data")
![Milestone 3 Screenshot](/img/milestone-3-account-service.png?raw=true "Milestone 3 Working Account Service Layer and Documentation")
![Milestone 3 Screenshot](/img/milestone-3-ci-cd.png?raw=true "Milestone 3 Working CI/CD")
![Milestone 3 Screenshot](/img/milestone-3-docker.png?raw=true "Milestone 3 Working Docker Upload")
![image1](https://user-images.githubusercontent.com/129224800/232379484-9a3e8617-4ed3-42a3-bf19-8901096a57cc.png)
![image2](https://user-images.githubusercontent.com/129224800/232379638-cf8f361d-8205-4dbc-98b8-df54d50be8bb.png)
![image](https://user-images.githubusercontent.com/129224800/232624999-791405cc-7954-441a-a5fd-a9caeb24f867.png)
![ServiceTest running condition(Milestone3)](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/33efe219-d956-49b5-8fae-ef7d210e1c24)
![restful service on swagger(Milestone3)](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/26ba8e64-936c-4af8-8651-94bc14cc8140)
![post operation add to h2 database successfully(Milestone3)](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/ba3b243c-3c74-4ffc-8839-cc308a5eb3f4)
![docker running condition(Milestone3)](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/a9fad559-cc6a-43a8-b7f6-3c0610f68328)
![logging page](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/aca41ff7-fe7e-401e-9186-614c81083a2e)
![nonrelational-swagger](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/b1abc140-dba7-4d06-aafb-c71ac250e374)
![mongodb-post](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/695ab039-fa09-45e8-9026-52da3b08d3a2)
![currency-BeforeAndAfterModify](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/3c5b7595-5a12-4fb5-a90b-da7f856ec209)
![digitalGood-BeforeAndAfterModify](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/d06933b8-f911-4839-a046-067936a78780)
![normalDelete](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/f897ccc2-64b6-4868-967d-625aa5c3cade)
![delete operation involved Cascading relationship-returnPage](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/ea8c28b2-bc7e-47bd-a80b-dbc9ef599620)
![Leaderboard HTML Representation](/img/final-leaderboard-html.png?raw=true "Leaderboard HTML")

![4fbd75a875f10f50e64682eebd54478](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/7e0fb351-5d1f-418f-9fe6-d8a7ceb77c65)
![21979891875a437ae4e0e6cd4aab2f6](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/de27b335-5752-4abc-a6ed-341312495919)
![1110291cfaec65ac99c0a60c446aef5](https://github.com/DePaul-SE-352-452-Group-6/Group-6-Project/assets/129224800/6a872f55-98d1-46e5-936d-6dfd9429d395)




