# EmployeeCURD
CURD Operation on Employee database using Spring Rest

#Configuration
Create server.properties file inside the "\hsqldb-2.4.1\hsqldb" folder with following properties
server.database.0 = file:hsqldb/demodb
server.dbname.0 = testdb

#Running
Start the HSQL Server by the command "java -classpath lib/hsqldb.jar org.hsqldb.server.Server"
Build the project using maven. At the time of build, All test cases will be run for CURD operation   
