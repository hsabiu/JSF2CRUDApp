# Implementation:
This directory contains a JavaEE project implemented using JSF2 and Bootstrap v4. The project was developed using the eclipse IDE and tested on GlassFish 5.0 server. In addition, the project uses a MySQL database for data storage.

# Deployment:
To test this project, you most have a runtime server such as GlassFish, Tomcat etc. installed. In addition, you most have a MySQL server running. 

This document is long, so if you already have all the systems setup, you can skip to Section 4, which provide the information about the various functionalities implemented.


## Importing the project into eclipse IDE
The first step to testing this project is to import the entire project directory into eclipse workspace. This can be done using the following steps.
* File -> Import
* General -> Existing Projects into Workspace -> Next
* Select the project root directory by clicking on Browse -> Finish

NOTE: If you get an error after importing the project into eclipse, it is probably because you have a different runtime server installed. To fix this, right click on the project directory and select Properties. In the search area, search for "Project Facets" and click. On the right side, select the "Runtimes" tab and change to the runtime you have in your eclipse installation. Hopefully this should fix the error.

## Setting up the runtime
If you don't have any server configured in your eclipse installation, follow the steps below to add a new Server.

* Window -> Show view -> Servers
* Right click on an empty space on the Servers tab and choose New -> Server
* Choose the Server you want to configure by expanding one of the directories e.g GlassFish
* Provide information about the server such as host name, server name. 
* If you don't have any runtime installed, select add at the right end of the **"Server runtime environment section"**
* Provide the name, location of the runtime root directory (you have to download this), and the location of your JDK, then click Finish. This would get you back to the new server setup page where you will click on Finish. You probably have to reboot eclipse after this step.
* You would see the server you just installed in the "Servers". If it is not running, right click on the server and choose restart.
      
## Importing the test data into MySQL
This step assume you have a MySQL database installed and running on your system. I have included a database dump file called peopleware.sql containing a record of the tables structure and the data from the database I used for testing this progect in the form of a list of SQL statements.

To import the data into MySQL, you can use the comman line by following the step below.

* Open terminal and navigate to the root directory containing peopleware.sql
* Type the command: mysql -u username -p peopleware < peopleware.sql

Alternatively, you can use a GUI application such as MySQL Workbench or Sequel Pro to import the data. For example, in MySQL Workbench, you can use the steps below:

     File -> Open SQL Script -> Select the peopleware.sql from your filesystem -> and run the script


## Running the application
* his project has the following directory structure:

      people-ware/ - This is the root directory of the project
      people-ware/WebContent/ - This directory contain all the HTML, CSS and JavaScript code 
      people-ware/src/com/peopleware/jsf/ - This directory contain all the .java files

* To test the application, please ensure that your server is running, and that you have the database loaded into MySQL. You may also have to change the database connection URL inside DBManager class located in people-ware/src/com/peopleware/jsf/. You should provide the correct URL to your MySQL server and also the correct login credentials (user name and password).

* If everything is setup correctly, right click on people-ware/WebContent/index.xhtml and select Run As -> Run on Server (you may have to select your runtime here). This should open the index file of the webpage inside a browser.

  * The index.xhtml file shows a list of all job offers together with information such as job title, posted date, employer name and contact number, job description, and requirements. This data is read from the "jobposts" table of the "peopleware" database. At the top of the index page is a navigatation that would take you to the "EMPLOYERS" and "APPLICANTS" pages.

  When you click on any of the job offers, you would be taken to a page that provides a table with a list of all the applicants that qualified for this job offer. This list is ranked based on the criteria defined in the project specification document. The most qualified candidate is the first on the table, with a rank of 1. If there is no any candidate that qualifies for this job offer, the table would be empty.

  * On the "EMPLOYERS" page, you have two options, "Post Job" or "All Applicants". The "Post Job" button would provide a form in which you can enter the information about the job offer such employer name and contact number. On submitting this form, the data would be stored in the "jobposts" table of the "peopleware" database.

  The "All Applicants" button would take you to a page listing the basic information of all the candidates currently subscribed to the system (in the order they appear in the database). 

  * The last part of the system is the "APPLICANTS" tab, which give an option for the applicants to subscribe to the system by entering their information including name, email, contact number etc. This can be done by clicking the "Subscribe" button and entering the information in the provided form. On submitting this form, the data entered would be stored in the "applicants" table of the "peopleware" database.

# Final notes:
All the required features of this project has been implemented as per the specification document 
provided. In addition, the following optional features has been implemented:

* Posting new job offers
* Listing all applicants


# Files included:
1. Class diagram - The image file called ClassDiagram.png contains the class diagram of the system.
2. Source code - The people-ware directory contains all the files of this project
3. Installation notes - This is the README.txt file
4. Script - The peopleware.sql file contain the sql statements that could be used to preload data into the database
   
# Features not implemented:
All mandatory features of the system has been implemented. However, there are few functionalities that are not implemented such as form validation. Since these features are not explicitly requested  in the project specification, I classify them as low priority and decided to implement them at the last phase of the project. Unfortunately, I was not able to implement these features.

Furthermore, when posting a job, you can only select one academic degree requirement at this time. For the technical skills field, the data can be entered in the following format:

    "Skill_1":Experience_Level_1,"Skill_2":Experience_Level_2  eg. “Java”:5,“Bootstrap”:2



