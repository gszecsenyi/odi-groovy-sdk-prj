package org.odideveloper.dataservers

import groovy.sql.Sql

class SourceDb {

    static void provision(String environment) {

        ConfigObject config = new ConfigSlurper(environment).parse(getClass().getResource('/dataservers/SourceDbProperties.groovy'))

        String sourceDbUrl = config.sourceDbUrl
        String sourceDbDriver = config.sourceDbDriver
        String sourceDbUser = config.sourceDbUser
        String sourceDbPassword = config.sourceDbPassword

        def sql = Sql.newInstance(sourceDbUrl, sourceDbUser, sourceDbPassword, sourceDbDriver)

        def createTableScript = """
            DECLARE 
                table_exists INTEGER; 
            BEGIN 
                select count(*) into table_exists from sys.all_tables where table_name='SRC_EMP';
                IF table_exists=1 THEN 
                    execute immediate 'drop table SRC_EMP';
                END IF;
                execute immediate 'create table SRC_EMP ( 
                    EMPNO number(4) primary key,
                    ENAME varchar2(10) not null,
                    JOB varchar2(9),
                    MGR number(4),
                    HIREDATE date,
                    SAL number(7,2),
                    COMM number(7,2),
                    DEPTNO number(2))';
            END;
            """

        sql.execute(createTableScript)
        sql.close()

        println("Source DB provisioned!")
    }
}