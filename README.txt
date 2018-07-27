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
      
3. Setting up MySQL and importing the data

4. Testing the application

