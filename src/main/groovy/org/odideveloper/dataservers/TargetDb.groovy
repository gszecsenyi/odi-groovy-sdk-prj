package org.odideveloper.dataservers

import groovy.sql.Sql

class TargetDb {

    static void provision(String environment) {

        ConfigObject config = new ConfigSlurper(environment).parse(getClass().getResource('/dataservers/TargetDbProperties.groovy'))

        String sourceDbUrl = config.targetDbUrl
        String sourceDbDriver = config.targetDbDriver
        String sourceDbUser = config.targetDbUser
        String sourceDbPassword = config.targetDbPassword

        def sql = Sql.newInstance(sourceDbUrl, sourceDbUser, sourceDbPassword, sourceDbDriver)

        def createTableScript = """
            DECLARE 
                table_exists INTEGER; 
            BEGIN 
                select count(*) into table_exists from sys.all_tables where table_name='TRG_EMP';
                IF table_exists=1 THEN 
                    execute immediate 'drop table TRG_EMP';
                END IF;
                execute immediate 'create table TRG_EMP ( 
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

        println("Target DB provisioned!")
    }
}