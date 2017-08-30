package org.odideveloper.config

import oracle.odi.core.OdiInstance
import oracle.odi.core.config.MasterRepositoryDbInfo
import oracle.odi.core.config.OdiInstanceConfig
import oracle.odi.core.config.PoolingAttributes
import oracle.odi.core.config.WorkRepositoryDbInfo
import oracle.odi.core.persistence.transaction.ITransactionDefinition
import oracle.odi.core.persistence.transaction.ITransactionManager
import oracle.odi.core.persistence.transaction.ITransactionStatus
import oracle.odi.core.persistence.transaction.support.DefaultTransactionDefinition
import oracle.odi.core.security.Authentication
import oracle.odi.domain.model.OdiModel

import oracle.odi.domain.topology.OdiContext
import oracle.odi.domain.topology.OdiLogicalSchema
import oracle.odi.domain.topology.finder.IOdiContextFinder
import oracle.odi.domain.topology.finder.IOdiLogicalSchemaFinder

class ModelConfig {

    static void configure(String environment) {

        ConfigObject config = new ConfigSlurper(environment).parse(
                getClass().getResource('/config/ModelConfigProperties.groovy'))

        String odiSupervisorUser = config.odiSupervisorUser
        String odiSupervisorPassword = config.odiSupervisorPassword

        String masterRepositoryJdbcUrl = config.masterRepositoryJdbcUrl
        String masterRepositoryJdbcDriver = config.masterRepositoryJdbcDriver
        String masterRepositoryJdbcUser = config.masterRepositoryJdbcUser
        String masterRepositoryJdbcPassword = config.masterRepositoryJdbcPassword
        String workRepositoryName = config.workRepositoryName

        String srcLogicalSchemaForModel = config.srcLogicalSchemaForModel
        String srcModelName = config.srcModelName
        String srcModelCode = config.srcModelCode
        String trgLogicalSchemaForModel = config.trgLogicalSchemaForModel
        String trgModelName = config.trgModelName
        String trgModelCode = config.trgModelCode

        MasterRepositoryDbInfo mRepDbInfo= new MasterRepositoryDbInfo(masterRepositoryJdbcUrl,
                masterRepositoryJdbcDriver,
                masterRepositoryJdbcUser,
                masterRepositoryJdbcPassword.toCharArray(),
                new PoolingAttributes())
        WorkRepositoryDbInfo wRepDbInfo= new WorkRepositoryDbInfo(workRepositoryName,
                new PoolingAttributes())
        OdiInstance odiInstance = OdiInstance.createInstance(new OdiInstanceConfig(mRepDbInfo, wRepDbInfo))
        Authentication auth = odiInstance.getSecurityManager().createAuthentication(odiSupervisorUser, odiSupervisorPassword.toCharArray())
        odiInstance.getSecurityManager().setCurrentThreadAuthentication(auth)

        try {
            ITransactionDefinition txnDef = new DefaultTransactionDefinition()
            ITransactionManager tm = odiInstance.getTransactionManager()
            ITransactionStatus txnStatus = tm.getTransaction(txnDef)


            OdiLogicalSchema srcLogicalSchema = ((IOdiLogicalSchemaFinder)
                    odiInstance.getTransactionalEntityManager().
                            getFinder(OdiLogicalSchema.class)).findByName(srcLogicalSchemaForModel)
            OdiContext sdkContext = ((IOdiContextFinder)odiInstance.
                    getTransactionalEntityManager().getFinder(OdiContext.class)).
                    findDefaultContext()
            OdiModel srcModel = new OdiModel(srcLogicalSchema, srcModelName,
                    srcModelCode)
            srcModel.setReverseContext(sdkContext)
            odiInstance.getTransactionalEntityManager().persist(srcModel)

            OdiLogicalSchema trgLogicalSchema = ((IOdiLogicalSchemaFinder)
                    odiInstance.getTransactionalEntityManager().
                            getFinder(OdiLogicalSchema.class)).findByName(trgLogicalSchemaForModel)
            OdiModel trgModel = new OdiModel(trgLogicalSchema, trgModelName,
                    trgModelCode)
            trgModel.setReverseContext(sdkContext)
            odiInstance.getTransactionalEntityManager().persist(trgModel)

//            OdiDataStore odiDataStore = new OdiDataStore(trgModel, 'TRG_EMP')

            tm.commit(txnStatus)
            auth.close()
            odiInstance.close()
        }
        catch (Exception e) {
            auth.close()
            odiInstance.close()
            println(e)
        }
    }
}
