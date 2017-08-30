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
        sourceDsJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        sourceDsJdbcDriver = 'oracle.jdbc.OracleDriver'
        sourceDsJdbcUser = 'SRC_DEV'
        sourceDsJdbcPassword = 'password123'
        targetDsJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        targetDsJdbcDriver = 'oracle.jdbc.OracleDriver'
        targetDsJdbcUser = 'TRG_DEV'
        targetDsJdbcPassword = 'password123'
        contextCode = "DEVELOPMENT"
        srcDsOracleForModel = "DEV_SRC_DS"
        srcLogicalSchemaForModel = "SRC_ORACLE_LS"
        trgDsOracleForModel = "DEV_TRG_DS"
        trgLogicalSchemaForModel = "TRG_ORACLE_LS"
    }

    test {
        odiSupervisorUser = 'SUPERVISOR'
        odiSupervisorPassword = 'SUPERVISOR'
        masterRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        masterRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        masterRepositoryJdbcUser = 'TEST_MREP'
        masterRepositoryJdbcPassword = 'password123'
        workRepositoryName = 'TEST_WORKREP'
        sourceDsJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        sourceDsJdbcDriver = 'oracle.jdbc.OracleDriver'
        sourceDsJdbcUser = 'SRC_TEST'
        sourceDsJdbcPassword = 'password123'
        targetDsJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        targetDsJdbcDriver = 'oracle.jdbc.OracleDriver'
        targetDsJdbcUser = 'TRG_TEST'
        targetDsJdbcPassword = 'password123'
        contextCode = "TEST"
        srcDsOracleForModel = "TEST_SRC_DS"
        srcLogicalSchemaForModel = "SRC_ORACLE_LS"
        trgDsOracleForModel = "TEST_TRG_DS"
        trgLogicalSchemaForModel = "TRG_ORACLE_LS"
    }

    prod {
        odiSupervisorUser = 'SUPERVISOR'
        odiSupervisorPassword = 'SUPERVISOR'
        masterRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        masterRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        masterRepositoryJdbcUser = 'PROD_MREP'
        masterRepositoryJdbcPassword = 'password123'
        workRepositoryName = 'PROD_WORKREP'
        sourceDsJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        sourceDsJdbcDriver = 'oracle.jdbc.OracleDriver'
        sourceDsJdbcUser = 'SRC_PROD'
        sourceDsJdbcPassword = 'password123'
        targetDsJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        targetDsJdbcDriver = 'oracle.jdbc.OracleDriver'
        targetDsJdbcUser = 'TRG_PROD'
        targetDsJdbcPassword = 'password123'
        contextCode = "PRODUCTION"
        srcDsOracleForModel = "PROD_SRC_DS"
        srcLogicalSchemaForModel = "SRC_ORACLE_LS"
        trgDsOracleForModel = "PROD_TRG_DS"
        trgLogicalSchemaForModel = "TRG_ORACLE_LS"
    }
}
