package config

environments {
    dev {
        odiSupervisorUser = 'SUPERVISOR'
        odiSupervisorPassword = 'SUPERVISOR'
        dbaUser = 'system'
        dbaPwd = 'password123'

        masterRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        masterRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        masterRepositoryJdbcUser = 'DEV_MREP'
        masterRepositoryJdbcPassword = 'password123'
        masterRepositoryId = 0

        workRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        workRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        workRepositoryJdbcUser = 'DEV_WREP'
        workRepositoryJdbcPassword = 'password123'
        workRepositoryName = 'DEV_WORKREP'
        workRepositoryType = 'DESIGN'
        workRepositoryId = 1
    }

    test {
        odiSupervisorUser = 'SUPERVISOR'
        odiSupervisorPassword = 'SUPERVISOR'
        dbaUser = 'system'
        dbaPwd = 'password123'

        masterRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        masterRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        masterRepositoryJdbcUser = 'TEST_MREP'
        masterRepositoryJdbcPassword = 'password123'
        masterRepositoryId = 0

        workRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        workRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        workRepositoryJdbcUser = 'TEST_WREP'
        workRepositoryJdbcPassword = 'password123'
        workRepositoryName = 'TEST_WORKREP'
        workRepositoryType = 'DESIGN'
        workRepositoryId = 1
    }

    prod {
        odiSupervisorUser = 'SUPERVISOR'
        odiSupervisorPassword = 'SUPERVISOR'
        dbaUser = 'system'
        dbaPwd = 'password123'

        masterRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        masterRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        masterRepositoryJdbcUser = 'PROD_MREP'
        masterRepositoryJdbcPassword = 'password123'
        masterRepositoryId = 0

        workRepositoryJdbcUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        workRepositoryJdbcDriver = 'oracle.jdbc.OracleDriver'
        workRepositoryJdbcUser = 'PROD_WREP'
        workRepositoryJdbcPassword = 'password123'
        workRepositoryName = 'PROD_WORKREP'
        workRepositoryType = 'DESIGN'
        workRepositoryId = 1
    }
}
