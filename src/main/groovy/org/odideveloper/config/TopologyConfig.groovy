package org.odideveloper.config

import oracle.odi.core.OdiInstance
import oracle.odi.core.config.MasterRepositoryDbInfo
import oracle.odi.core.config.OdiInstanceConfig
import oracle.odi.core.config.PoolingAttributes
import oracle.odi.core.config.WorkRepositoryDbInfo
import oracle.odi.core.persistence.IOdiEntityManager
import oracle.odi.core.persistence.transaction.ITransactionStatus
import oracle.odi.core.persistence.transaction.support.ITransactionCallback
import oracle.odi.core.persistence.transaction.support.TransactionTemplate
import oracle.odi.core.security.Authentication
import oracle.odi.domain.topology.*
import oracle.odi.domain.topology.finder.IOdiTechnologyFinder
import oracle.odi.domain.util.ObfuscatedString

class TopologyConfig {

    static void configure(String environment) {

        ConfigObject config = new ConfigSlurper(environment).parse(
                getClass().getResource('/config/DataServerConfigProperties.groovy'))

        String odiSupervisorUser = config.odiSupervisorUser
        String odiSupervisorPassword = config.odiSupervisorPassword

        String masterRepositoryJdbcUrl = config.masterRepositoryJdbcUrl
        String masterRepositoryJdbcDriver = config.masterRepositoryJdbcDriver
        String masterRepositoryJdbcUser = config.masterRepositoryJdbcUser
        String masterRepositoryJdbcPassword = config.masterRepositoryJdbcPassword
        String workRepositoryName = config.workRepositoryName

        String sourceDsJdbcUrl = config.sourceDsJdbcUrl
        String sourceDsJdbcDriver = config.sourceDsJdbcDriver
        String sourceDsJdbcUser = config.sourceDsJdbcUser
        String sourceDsJdbcPassword = config.sourceDsJdbcPassword

        String targetDsJdbcUrl = config.targetDsJdbcUrl
        String targetDsJdbcDriver = config.targetDsJdbcDriver
        String targetDsJdbcUser = config.targetDsJdbcUser
        String targetDsJdbcPassword = config.targetDsJdbcPassword

        String contextCode = config.contextCode
        String srcDsOracleForModel = config.srcDsOracleForModel
        String srcLogicalSchemaForModel = config.srcLogicalSchemaForModel
        String trgDsOracleForModel = config.trgDsOracleForModel
        String trgLogicalSchemaForModel = config.trgLogicalSchemaForModel

        MasterRepositoryDbInfo mRepDbInfo = new MasterRepositoryDbInfo(masterRepositoryJdbcUrl,
                masterRepositoryJdbcDriver,
                masterRepositoryJdbcUser,
                masterRepositoryJdbcPassword.toCharArray(),
                new PoolingAttributes())
        WorkRepositoryDbInfo wRepDbInfo = new WorkRepositoryDbInfo(workRepositoryName,
                new PoolingAttributes())
        OdiInstance odiInstance = OdiInstance.createInstance(new OdiInstanceConfig(mRepDbInfo, wRepDbInfo))

        Authentication auth = odiInstance.getSecurityManager().createAuthentication(odiSupervisorUser, odiSupervisorPassword.toCharArray())

        try {
            TransactionTemplate transaction = new TransactionTemplate(odiInstance.getTransactionManager())
            odiInstance.getSecurityManager().setCurrentThreadAuthentication(auth)

            transaction.execute(new ITransactionCallback()
            {
                public Object doInTransaction(ITransactionStatus pStatus) {

                    System.out.println("Generating Topology...")

                    // Create the context
                    OdiContext context = new OdiContext(contextCode)

                    // Retrieve the Oracle technology from the standard installation
                    OdiTechnology oracleTechnology = ((IOdiTechnologyFinder) odiInstance.getTransactionalEntityManager().getFinder(OdiTechnology.class)).findByCode("ORACLE")

                    // --- Source DS
                    // Create a DataServer in the Oracle technology. Note that creating the new object automatically adds it to the technology
                    OdiDataServer srcOracleDataServer = new OdiDataServer(oracleTechnology, srcDsOracleForModel)
                    // Set additional information in the DataServer, such as JDBC information, username, etc.
                    srcOracleDataServer.setConnectionSettings(new AbstractOdiDataServer.JdbcSettings(sourceDsJdbcUrl, sourceDsJdbcDriver))
                    srcOracleDataServer.setUsername(sourceDsJdbcUser)
                    srcOracleDataServer.setPassword(ObfuscatedString.obfuscate(sourceDsJdbcPassword))
                    // Create a physical schema in the DataServer
                    OdiPhysicalSchema srcOraclePhysicalSchema = new OdiPhysicalSchema(srcOracleDataServer)
                    // Set additional information on the schema
                    srcOraclePhysicalSchema.setSchemaName(sourceDsJdbcUser)
                    srcOraclePhysicalSchema.setWorkSchemaName(sourceDsJdbcUser)
                    // Create the logical schema and the mapping to it in the context
                    OdiLogicalSchema srcOracleLogicalSchema = new OdiLogicalSchema(oracleTechnology, srcLogicalSchemaForModel)
                    new OdiContextualSchemaMapping(context, srcOracleLogicalSchema, srcOraclePhysicalSchema)

                    // --- Target DS
                    // Do the same for the Hypersonic SQL, which will be used as the staging area of the interfaces
                    OdiDataServer trgOracleDataServer = new OdiDataServer(oracleTechnology, trgDsOracleForModel)
                    trgOracleDataServer.setConnectionSettings(new AbstractOdiDataServer.JdbcSettings(targetDsJdbcUrl, targetDsJdbcDriver))
                    trgOracleDataServer.setUsername(targetDsJdbcUser)
                    trgOracleDataServer.setPassword(ObfuscatedString.obfuscate(targetDsJdbcPassword))
                    OdiPhysicalSchema trgOraclePhysicalSchema = new OdiPhysicalSchema(trgOracleDataServer)
                    trgOraclePhysicalSchema.setSchemaName(targetDsJdbcUser)
                    trgOraclePhysicalSchema.setWorkSchemaName(targetDsJdbcUser)
                    OdiLogicalSchema trgOracleLogicalSchema = new OdiLogicalSchema(oracleTechnology, trgLogicalSchemaForModel)
                    new OdiContextualSchemaMapping(context, trgOracleLogicalSchema, trgOraclePhysicalSchema)

                    IOdiEntityManager entityManager = odiInstance.getTransactionalEntityManager()
                    // Persist the oracle technology and all its children: the dataServer, the physical schema, the logical schema and the contextual mapping
                    entityManager.persist(oracleTechnology)
                    // Persist the context (which doesn't have any parent)
                    entityManager.persist(context)
                }
            })
        }
        finally {
            odiInstance.getSecurityManager().clearCurrentThreadAuthentication()
            auth.close()
        }
    }
}
