package org.odideveloper.config

import oracle.odi.core.OdiInstance
import oracle.odi.core.config.MasterRepositoryDbInfo
import oracle.odi.core.config.OdiInstanceConfig
import oracle.odi.core.config.PoolingAttributes
import oracle.odi.core.repository.WorkRepository
import oracle.odi.core.security.Authentication
import oracle.odi.setup.*
import oracle.odi.setup.support.MasterRepositorySetupImpl
import oracle.odi.setup.support.WorkRepositorySetupImpl

class RepoConfig {

    static void configure(String environment) {

        ConfigObject config = new ConfigSlurper(environment).parse(
                getClass().getResource('/config/RepoConfigProperties.groovy'))

        String odiSupervisorUser = config.odiSupervisorUser
        String odiSupervisorPassword = config.odiSupervisorPassword
        String dbaUser = config.dbaUser
        char[] dbaPwd = config.dbaPwd


        TechnologyName masterRepositoryTechnology = TechnologyName.ORACLE
        String masterRepositoryJdbcUrl = config.masterRepositoryJdbcUrl
        String masterRepositoryJdbcDriver = config.masterRepositoryJdbcDriver
        String masterRepositoryJdbcUser = config.masterRepositoryJdbcUser
        String masterRepositoryJdbcPassword = config.masterRepositoryJdbcPassword
        Integer masterRepositoryId = config.masterRepositoryId


        TechnologyName workRepositoryTechnology = TechnologyName.ORACLE
        String workRepositoryJdbcUrl = config.workRepositoryJdbcUrl
        String workRepositoryJdbcDriver = config.workRepositoryJdbcDriver
        String workRepositoryJdbcUser = config.workRepositoryJdbcUser
        String workRepositoryJdbcPassword = config.workRepositoryJdbcPassword
        String workRepositoryName = config.workRepositoryName
        WorkRepository.WorkType wRepType = WorkRepository.WorkType.valueOf((String)config.workRepositoryType)
        Integer workRepositoryId = config.workRepositoryId


        IMasterRepositorySetup masterRepositorySetup = new MasterRepositorySetupImpl()
        AuthenticationConfiguration authConf = AuthenticationConfiguration.createStandaloneAuthenticationConfiguration(odiSupervisorPassword.toCharArray())
        JdbcProperties mRepJdbcInfo = new JdbcProperties(masterRepositoryJdbcUrl,
                masterRepositoryJdbcDriver,
                masterRepositoryJdbcUser,
                masterRepositoryJdbcPassword.toCharArray())
        masterRepositorySetup.createMasterRepository(mRepJdbcInfo,
                dbaUser, dbaPwd, masterRepositoryId,
                masterRepositoryTechnology, true, authConf, null)


        MasterRepositoryDbInfo mRepDbInfo = new MasterRepositoryDbInfo(masterRepositoryJdbcUrl,
                masterRepositoryJdbcDriver,
                masterRepositoryJdbcUser,
                masterRepositoryJdbcPassword.toCharArray(),
                new PoolingAttributes())
        OdiInstance odiInstance = OdiInstance.createInstance(new OdiInstanceConfig(mRepDbInfo, null))
        Authentication auth = odiInstance.getSecurityManager().createAuthentication(odiSupervisorUser, odiSupervisorPassword.toCharArray())

        try {
            odiInstance.getSecurityManager().setCurrentThreadAuthentication(auth)

            IWorkRepositorySetup workRepositorySetup = new WorkRepositorySetupImpl(odiInstance)
            JdbcProperties wRepJdbcInfo = new JdbcProperties(workRepositoryJdbcUrl, workRepositoryJdbcDriver, workRepositoryJdbcUser, workRepositoryJdbcPassword)
            workRepositorySetup.createWorkRepository(wRepType,
                    wRepJdbcInfo, workRepositoryId,
                    workRepositoryName, workRepositoryTechnology,
                    true, null)
        }
        catch (Exception e) {
            auth.close()
            odiInstance.close()
            println(e)
        }
    }
}