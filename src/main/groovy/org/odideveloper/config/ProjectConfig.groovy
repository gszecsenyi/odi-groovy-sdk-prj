package org.odideveloper.config

import oracle.odi.core.config.MasterRepositoryDbInfo
import oracle.odi.core.config.WorkRepositoryDbInfo
import oracle.odi.core.config.PoolingAttributes
import oracle.odi.core.OdiInstance
import oracle.odi.core.config.OdiInstanceConfig
import oracle.odi.core.security.Authentication
import oracle.odi.core.persistence.transaction.ITransactionDefinition
import oracle.odi.core.persistence.transaction.support.DefaultTransactionDefinition
import oracle.odi.core.persistence.transaction.ITransactionManager
import oracle.odi.core.persistence.transaction.ITransactionStatus
import oracle.odi.domain.project.OdiProject
import oracle.odi.domain.project.OdiFolder

class ProjectConfig {

    static void configure(String environment) {

        ConfigObject config = new ConfigSlurper(environment).parse(
                getClass().getResource('/config/ProjectConfigProperties.groovy'))

        String odiSupervisorUser = config.odiSupervisorUser
        String odiSupervisorPassword = config.odiSupervisorPassword

        String masterRepositoryJdbcUrl = config.masterRepositoryJdbcUrl
        String masterRepositoryJdbcDriver = config.masterRepositoryJdbcDriver
        String masterRepositoryJdbcUser = config.masterRepositoryJdbcUser
        String masterRepositoryJdbcPassword = config.masterRepositoryJdbcPassword
        String workRepositoryName = config.workRepositoryName

        String odiProjectName = config.odiProjectName
        String odiProjectCode = config.odiProjectCode
        String odiProjectFolder = config.odiProjectFolder

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

            OdiProject sdkProject = new OdiProject(odiProjectName, odiProjectCode)
            OdiFolder sdkFolder = new OdiFolder(sdkProject, odiProjectFolder)
            odiInstance.getTransactionalEntityManager().persist(sdkProject)

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
