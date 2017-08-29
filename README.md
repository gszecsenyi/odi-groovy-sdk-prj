## Groovy ODI SDK Development

### A. Requirements
* JDK 1.6.37
* Oracle 11g XE
* ODI 11g
* Maven 3.2.5

For convenience, please update below environment variables

```
JAVA_HOME = JDK 1.6.0.37 path - eg: C:\Program Files\Java\jdk1.6.0_37
M2_HOME = Maven 3.2.5 - eg: C:\apache-maven-3.2.5
ODI_HOME = ODI 11g home - eg: C:\oracle\product\11.1.1\Oracle_ODI_1
PATH - append $JAVA_HOME/bin; $M2_HOME/bin
```

---

### B. Maven Project

##### SDK Libs
ODI SDK Libs are supplied by Oracle & these external libs need to be installed into Local Maven repository for ODI development.
Use the command-snippets [here](./docs/ODI-Sdk-Libs-Mvn-Install.sh) to install libs in ODI-Installation directory to Local .M2 repository. Corporate Central Repository (such as artifactory, nexus) can also install these libs & may ease the trouble installing locally. 

These libs are then used as dependencies inside `pom.xml`

##### SDK Scripts

Under `$ODI_HOME/oracledi.sdk/lib/scripts/xml` we have basic configuration scripts for ODI repositories. The `scripts` need to exist in the same directory as .jar (For IntelliJ IDE, update the classpath via `Module Settings` as mentioned [here](https://stackoverflow.com/questions/854264/how-to-add-directory-to-classpath-in-an-application-run-profile-in-intellij-idea))
Unfortunately `scripts` can't be packaged inside jar due to limitation from Oracle ODI (?) - [link](https://stackoverflow.com/questions/6192661/how-to-reference-a-resource-file-correctly-for-jar-and-debugging) 

### Preparing Oracle 11 XE Database

Use DBA login to create schemas for ODI-Repo, Source & Target databases
Sql Snippets at [ODI-Repo-Sql](./docs/ODI-Repo-Schema-Creation.sql) & [Src-Trg-Sql](./docs/Source-Taget-Schema-Creation.sql)

---

### C. Maven Build
Project is packaged into executable jar
```bash
git clone https://github.com/kannan-ra/odi-groovy-sdk-prj.git
cd odi-groovy-sdk-prj

mvn clean install
java -jar ./target/odi-groovy-sdk-prj-0.1-SNAPSHOT.jar <environment>
```

The main-class set to `org.odideveloper.OdiConfigRunner` via manifest.

###### ODI environments
The code supports different environments like `dev, test, prod`. Update the properties under `src/main/resources/config` to suit.

---

### D. ODI Configuration

[OdiConfigRunner](./src/main/groovy/org/odideveloper/OdiConfigRunner.groovy) is the main class to start the configuration -

Currently these configurations are implemented
* ODI-Repositories - [RepoConfig](./src/main/groovy/org/odideveloper/config/RepoConfig.groovy)
* Project & Folder - [ProjectConfig](./src/main/groovy/org/odideveloper/config/ProjectConfig.groovy)
* Topology - [TopologyConfig](./src/main/groovy/org/odideveloper/config/TopologyConfig.groovy)

---