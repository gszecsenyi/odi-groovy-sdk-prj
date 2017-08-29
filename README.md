## Groovy ODI SDK Development

### Requirements
* JDK 1.6.37
* Oracle 11g XE
* ODI 11g
* Maven 3.2.5

For convenience, please update below environment variables
> `JAVA_HOME` = JDK 1.6.0.37 path - eg: C:\Program Files\Java\jdk1.6.0_37
> `M2_HOME` = Maven 3.2.5 - eg: C:\apache-maven-3.2.5
> `ODI_HOME` = ODI 11g home - eg: C:\oracle\product\11.1.1\Oracle_ODI_1
> `PATH` - append $JAVA_HOME/bin; $M2_HOME/bin


### Maven Project

##### SDK Libs
ODI SDK Libs are supplied by Oracle & these external libs need to be installed into Local Maven repository for ODI development.
```
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/odi-core.jar -DgroupId=org.oracle.odisdk -DartifactId=odi-core -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/spring-core.jar -DgroupId=org.oracle.odisdk -DartifactId=spring-core -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/dms.jar -DgroupId=org.oracle.odisdk -DartifactId=dms -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/ojdl.jar -DgroupId=org.oracle.odisdk -DartifactId=ojdl -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/SchemaVersion.jar -DgroupId=org.oracle.odisdk -DartifactId=SchemaVersion -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/commons-lang-2.2.jar -DgroupId=org.oracle.odisdk -DartifactId=commons-lang-2.2 -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/spring-jdbc.jar -DgroupId=org.oracle.odisdk -DartifactId=spring-jdbc -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/spring-dao.jar -DgroupId=org.oracle.odisdk -DartifactId=spring-dao -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/spring-beans.jar -DgroupId=org.oracle.odisdk -DartifactId=spring-beans -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/commons-logging-1.1.1.jar -DgroupId=org.oracle.odisdk -DartifactId=commons-logging-1.1.1 -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/bsf.jar -DgroupId=org.oracle.odisdk -DartifactId=bsf -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/bsh-2.0b2.jar -DgroupId=org.oracle.odisdk -DartifactId=bsh-2.0b2 -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/javolution.jar -DgroupId=org.oracle.odisdk -DartifactId=javolution -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/eclipselink.jar -DgroupId=org.oracle.odisdk -DartifactId=eclipselink -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/persistence.jar -DgroupId=org.oracle.odisdk -DartifactId=persistence -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/oracle.ucp_11.1.0.jar -DgroupId=org.oracle.odisdk -DartifactId=oracle.ucp_11.1.0 -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/oracledi.sdk/lib/commons-collections-3.2.jar -DgroupId=org.oracle.odisdk -DartifactId=commons-collections-3.2 -Dversion=11.1.1 -Dpackaging=jar

mvn install:install-file -Dfile=$ODI_HOME/modules/oracle.jdbc_11.1.1/ojdbc6dms.jar -DgroupId=org.oracle.odisdk -DartifactId=ojdbc6dms -Dversion=11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=$ODI_HOME/modules/oracle.jps_11.1.1/jps-api.jar -DgroupId=org.oracle.odisdk -DartifactId=jps-api -Dversion=11.1.1 -Dpackaging=jar
```

##### SDK Scripts

Under `$ODI_HOME/oracledi.sdk/lib/scripts/xml` we have basic configuration scripts for ODI repositories. The `scripts` need to exist in the same directory as .jar (For IntelliJ IDE, update the classpath via `Module Settings` as mentioned [here](https://stackoverflow.com/questions/854264/how-to-add-directory-to-classpath-in-an-application-run-profile-in-intellij-idea)
Unfortunately `scripts` can't be packaged inside jar due to limitation from Oracle ODI (?) - [link](https://stackoverflow.com/questions/6192661/how-to-reference-a-resource-file-correctly-for-jar-and-debugging) 

### Preparing ODI Repo Database

Deals with creating just the schemas. The configuration will be done via code
```sql
-- DEVELOPMENT ENVIRONMENT
drop user DEV_MREP cascade;
drop user DEV_WREP cascade;

create user DEV_MREP identified by password123;
grant connect, resource to DEV_MREP;

create user DEV_WREP identified by password123;
grant connect, resource to DEV_WREP;

-- TEST ENVIRONMENT
drop user TEST_MREP cascade;
drop user TEST_WREP cascade;

create user TEST_MREP identified by password123;
grant connect, resource to TEST_MREP;

create user TEST_WREP identified by password123;
grant connect, resource to TEST_WREP;


-- PROD ENVIRONMENT
drop user PROD_MREP cascade;
drop user PROD_WREP cascade;

create user PROD_MREP identified by password123;
grant connect, resource to PROD_MREP;

create user PROD_WREP identified by password123;
grant connect, resource to PROD_WREP;
```

### Maven Build
Project is packaged into executable jar
```
git clone https://github.com/kannan-ra/odi-groovy-sdk-prj.git
cd odi-groovy-sdk-prj

mvn clean install
java -jar ./target/odi-groovy-sdk-prj-0.1-SNAPSHOT.jar <environment>
```

The main-class set to `org.odideveloper.OdiConfigRunner` via manifest.

###### ODI environments
The code supports different environments like `dev, test, prod`. Update the properties under `src/main/resources/config` to suit.