Authour: Habib Sabiu
Date: July 26, 2017

IMPLEMENTATION:
=================================================================================================
This directory contains a JavaEE project implemented using JSF2 and Bootstrap v4. The project was
developed using the eclipse IDE and tested on GlassFish 5.0 server. In addition, the project uses
a MySQL database for data storage.


DEPLOYMENT:
=================================================================================================
To test this project, you most have a runtime server such as GlassFish, Tomcat etc. installed. 
In addition, you most have a MySQL server running.

1. Importing the project into eclipse
The first step to testing this project is to import the entire project directory into eclispse 
workspace. This can be done using the following steps.

    - File -> Import
    - General -> Existing Projects into Workspace -> Next
    - Select the project root directory by clicking on Browse -> Finish

NOTE: If you get an error after importing the project into eclipse, it is probably because you
have a different runtime server installed. To fix this, right click on the project directory and
select Properties. In the search area, search for "Project Facets" and click. On the right side,
select the "Runtimes" tab and change to the runtime you have in your eclispe installation. Hopefully 
this would fix the error.

2. Setting up the runtime
If you don't have any server configured in your eclise installation, follow the steps below to add 
a new Server.

    - Window -> Show view -> Servers
    - Right click on an empty space on the Servers tab and choose New -> Server
    - Choose the Server you want to configure by expanding one of the directories e.g GlassFish
    - Provide information about the server such as host name, server name. 
    - If you don't have any runtime installed, select add at the right end of the "Server runtime
      environment section"
    - Provide the name, location of the runtime root directory (you have to download this), and
      the location of your JDK. Click Finish. This would bring you back to the new server setup
      page where you will click on Finish. You probably have to reboot eclipse after this step.
    - You should see the server you just installed in the "Servers". If it is not running, right
      click on the server and choose restart.
      
3. Importing the test data into MySQL
This step assume you have a MySQL database installed and running on your system. I have included
a database dump file called peopleware.sql located inside people-ware/db/ containing a record of 
the tables structure and the data from the database I used for testing this progect in the form 
of a list of SQL statements.

To import the data into MySQL, you can use the commanline by following the step below.

    - Open terminal and navigate to people-ware/db/ directory
    - Type the command: mysql -u username -p peopleware < peopleware.sql

Alternatively, you can use a GUI application such as MySQL Workbench or Sequel Pro to import the 
data. For example, in MySQL Workbench, you can use the steps below:

    - File -> Open SQL Script... -> Select the peopleware.sql from your file system -> run the script

4. Testing the application

    - This project has the following directory structure:
      people-ware/ - This is the root directory of the project
      people-ware/WebContent/ - This directory contain all the HTML, CSS and JavaScript code 
      people-ware/src/com/peopleware/jsf - This directory contain all the .java files

    - To run the application, please ensure that your server is running, and you have the database
      loaded into MySQL. You may also have to change the database connection url inside DBManager class
      located in people-ware/src/com/peopleware/jsf. You should provide the correct url to your MySQL
      server and also the correct login credentials (user name and password).

    - If everything is setup correctly, right click on people-ware/WebContent/index.xhtml and select
      Run As -> Run on Server (you may have to select your runtime here). This should open the index 
      file of the webpage inside a browser.

          - The index.xhtml file 

