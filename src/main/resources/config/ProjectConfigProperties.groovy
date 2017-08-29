package config

environments {
    dev {
        odiSupervisorUser = 'SUPERVISOR'
        odiSupervisorPassword = 'SUPERVISOR'
        masterRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        masterRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        masterRepositoryJdbcUser = 'DEV_MREP'
        masterRepositoryJdbcPassword = 'password123'
        workRepositoryName = 'DEV_WORKREP'
        odiProjectName = 'Dev ODI Project'
        odiProjectCode = 'DEV_ODI_PRJ'
        odiProjectFolder = 'Dev Env Folder'
    }

    test {
        odiSupervisorUser = 'SUPERVISOR'
        odiSupervisorPassword = 'SUPERVISOR'
        masterRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        masterRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        masterRepositoryJdbcUser = 'TEST_MREP'
        masterRepositoryJdbcPassword = 'password123'
        workRepositoryName = 'TEST_WORKREP'
        odiProjectName = 'Test ODI Project'
        odiProjectCode = 'TEST_ODI_PRJ'
        odiProjectFolder = 'Test Env Folder'
    }

    prod {
        odiSupervisorUser = 'SUPERVISOR'
        odiSupervisorPassword = 'SUPERVISOR'
        masterRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        masterRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        masterRepositoryJdbcUser = 'PROD_MREP'
        masterRepositoryJdbcPassword = 'password123'
        workRepositoryName = 'PROD_WORKREP'
        odiProjectName = 'Prod ODI Project'
        odiProjectCode = 'PROD_ODI_PRJ'
        odiProjectFolder = 'Prod Env Folder'
    }
}
