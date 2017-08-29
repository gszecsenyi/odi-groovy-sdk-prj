package dataservers

environments {
    dev {
        targetDbUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        targetDbDriver = 'oracle.jdbc.OracleDriver'
        targetDbUser = 'TRG_DEV'
        targetDbPassword = 'password123'
    }

    test {
        targetDbUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        targetDbDriver = 'oracle.jdbc.OracleDriver'
        targetDbUser = 'TRG_TEST'
        targetDbPassword = 'password123'
    }

    prod {
        targetDbUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        targetDbDriver = 'oracle.jdbc.OracleDriver'
        targetDbUser = 'TRG_PROD'
        targetDbPassword = 'password123'
    }
}
