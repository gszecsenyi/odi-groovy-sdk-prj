package dataservers

environments {
    dev {
        sourceDbUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        sourceDbDriver = 'oracle.jdbc.OracleDriver'
        sourceDbUser = 'SRC_DEV'
        sourceDbPassword = 'password123'
    }

    test {
        sourceDbUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        sourceDbDriver = 'oracle.jdbc.OracleDriver'
        sourceDbUser = 'SRC_TEST'
        sourceDbPassword = 'password123'
    }

    prod {
        sourceDbUrl = 'jdbc:oracle:thin:@localhost:1521:XE'
        sourceDbDriver = 'oracle.jdbc.OracleDriver'
        sourceDbUser = 'SRC_PROD'
        sourceDbPassword = 'password123'
    }
}
