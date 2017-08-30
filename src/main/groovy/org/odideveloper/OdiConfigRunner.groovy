package org.odideveloper

import org.odideveloper.config.ModelConfig
import org.odideveloper.config.TopologyConfig
import org.odideveloper.config.ProjectConfig
import org.odideveloper.config.RepoConfig
import org.odideveloper.dataservers.SourceDb
import org.odideveloper.dataservers.TargetDb

class OdiConfigRunner {

    static void main(String[] args) {

        // dealing with environment
        def environment = "dev"
        if (args.length > 0 && ["dev", "test", "prod"].contains(args[0])) {
            environment = args[0]
        }
        println("Setting environment to $environment")

        // Create/Update the Master & Work Repositories
        RepoConfig.configure(environment)

        // Create Project & Folder
        ProjectConfig.configure(environment)

        // Provision DBs
        SourceDb.provision(environment)
        TargetDb.provision(environment)

        // Topology
        TopologyConfig.configure(environment)

        // Models
        ModelConfig.configure(environment)
    }
}
