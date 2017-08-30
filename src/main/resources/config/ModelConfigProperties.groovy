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
        srcLogicalSchemaForModel = 'SRC_ORACLE_LS'
        srcModelName = 'Source Employee Model'
        srcModelCode = 'SRC_EMP_MODEL'
        trgLogicalSchemaForModel = 'TRG_ORACLE_LS'
        trgModelName = 'Target Employee Model'
        trgModelCode = 'TRG_EMP_MODEL'
    }

    test {
        odiSupervisorUser = 'SUPERVISOR'
        odiSupervisorPassword = 'SUPERVISOR'
        masterRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        masterRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        masterRepositoryJdbcUser = 'TEST_MREP'
        masterRepositoryJdbcPassword = 'password123'
        workRepositoryName = 'TEST_WORKREP'
        srcLogicalSchemaForModel = 'SRC_ORACLE_LS'
        srcModelName = 'Source Employee Model'
        srcModelCode = 'SRC_EMP_MODEL'
        trgLogicalSchemaForModel = 'TRG_ORACLE_LS'
        trgModelName = 'Target Employee Model'
        trgModelCode = 'TRG_EMP_MODEL'
    }

    prod {
        odiSupervisorUser = 'SUPERVISOR'
        odiSupervisorPassword = 'SUPERVISOR'
        masterRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        masterRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        masterRepositoryJdbcUser = 'PROD_MREP'
        masterRepositoryJdbcPassword = 'password123'
        workRepositoryName = 'PROD_WORKREP'
        srcLogicalSchemaForModel = 'SRC_ORACLE_LS'
        srcModelName = 'Source Employee Model'
        srcModelCode = 'SRC_EMP_MODEL'
        trgLogicalSchemaForModel = 'TRG_ORACLE_LS'
        trgModelName = 'Target Employee Model'
        trgModelCode = 'TRG_EMP_MODEL'
    }
}
