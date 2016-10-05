# Plan DSL for Bamboo - Treat your build plans as code!

[![Release](https://img.shields.io/github/release/mibexsoftware/bamboo-plan-dsl.svg?label=maven version)](https://jitpack.io/#mibexsoftware/bamboo-plan-dsl)
![Travis build status](https://travis-ci.org/mibexsoftware/bamboo-plan-dsl-plugin.svg?branch=master)

Note that this repository only contains the source for the plan DSL which allows you write your build
plan configurations with autocompletion, syntax highlighting and documentation in your IDE. The 
[Bamboo plug-in](https://marketplace.atlassian.com/plugins/ch.mibex.bamboo.plandsl) itself is available at the 
Atlassian Marketplace. 

For more information, please consult our [wiki](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki).

## Motivation
We once decided to tag the latest commit of a new release whenever we ship our software to production. Adding the 
necessary build task to achieve this to all our Bamboo build plans was a huge effort because we basically had to click 
through the configuration UI of every build plan. Also, we didn't have the possibility to rollback our plan 
configurations to a specific state and we were not able to see what has changed since the last working configuration.
This was the time where we decided to build this plug-in.

The Atlassian Bamboo plug-in allows you to specify your build plan configurations with a Groovy-based DSL. It is 
conceptually similar and inspired by the well-known Job DSL plug-in for Jenkins. By using Groovy, you have the 
flexibility of a programming language that is well-known for its strong meta-programming and scripting support. With our
Groovy-baed DSL, you can textually describe your build plans and all its associated Bamboo concepts like stages, jobs, 
tasks, build variables, etc. Here's an example of a simple build plan to run `mvn install`:

```groovy
project("MYPROJECT") {
    name "My project"

    plan("MYPLAN") {
        name "My plan"
        
        stage("My stage") {
            description "My stage"
            manual false

            job("MYJOB") {
                name "My job"
                description "This ismy job"

                tasks {
                    maven3("build plug-in") {
                        goal "install"
                    }
                }
            }
        }
    }
}
```

By applying configuration as code with our plug-in, you will always able to go back in the history of your DSL repository
when a build breaks. And you spend less time clicking through the dozens of configuration screens the Bamboo UI 
provides. And last but not least, it is also much more fun to hack your build plans :-)


## Basic usage
See the [wiki](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki) for more details and examples.

1. Write your plan Groovy DSL files in your favourite IDE with syntax highlighting, code completion and documentation.
[See the wiki instructions on this](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki/IDE-support).
2. Add the plug-ins **seed task** to a build plan and reference the DSL files from the plan's associated repository.
3. When you run the plan with the seed task, the plug-in will automatically create and update all plans specified
   in the DSL files. You can see which plans got created/updated on the build summary page.
   
   
## Artifacts
You can find the latest JAR file for the DSL in the [projects releases](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/releases).
Note that you can also access the DSL JAR in your Maven/Gradle build as explained in the 
[IDE support section in the wiki](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki/IDE-support). 


## Implemented Bamboo Features
This list shows the already supported features by the Plan DSL (note that although a lot of tasks are not yet natively
supported by the plug-in, they can still be used with the 
[custom syntax explained in the wiki](https://github.com/mibexsoftware/bamboo-plan-dsl-plugin/wiki/Bamboo-tasks)).

- Project 
    - [x] Project details
- Plans
    - [x] Plan details
    - [x] Stages
    - [x] Repositories
    - [x] Triggers
    - Branches
        - [x] Branches Options
        - [x] Branch Details
        - [ ] Source repository
        - [ ] Notifications
        - [x] Variables
    - [ ] Dependencies
    - [ ] Permissions
    - [x] Notifications
    - [ ] Variables
    - [ ] Miscellaneous
- Stage
    - [x] Stage details
- Job
    - [x] Job details
    - [x] Tasks
    - [ ] Requirements
    - [x] Artifacts
    - [ ] Miscellaneous
- Tasks (tasks not yet supported can still be used by using "customTask")
    - [ ] Ant
    - [x] Artifact Download
    - [ ] AWA CodeDeploy
    - [ ] Bower
    - [x] Command
    - [x] Deploy Plugin
    - [ ] Deploy Tomcat Application
    - [ ] Docker
    - [ ] Dump variables to log
    - [ ] Grails
    - [ ] Grunt 0.4.x
    - [ ] Gulp
    - [ ] Heroku: Deploy WAR Artifact
    - [ ] Inject Bamboo variables
    - [ ] JUnit Parser
    - [ ] Maven 1.x
    - [ ] Maven 2.x
    - [x] Maven 3.x
    - [ ] Maven Dependencies Processor
    - [ ] MBUnit Parser
    - [ ] Mocha Test Parser
    - [ ] Mocha Test Runner
    - [ ] MSBuild
    - [ ] MSTest Parser
    - [ ] MSTest Runner
    - [ ] NAnt
    - [ ] Node.js
    - [ ] Nodeunit
    - [ ] npm
    - [ ] NUnit Parser
    - [ ] NUnit Runner
    - [ ] PHPUnit
    - [ ] PHPUnit 3.3.x
    - [ ] Reload Tomcat Application
    - [ ] SCP Task
    - [x] Script
    - [x] Source Code Checkout
    - [ ] SSH Task
    - [ ] Start Tomcat Application
    - [ ] Stop Tomcat Application
    - [ ] TestNG Parser
    - [ ] Undeploy Tomcat Application
    - [ ] VCS Branching
    - [ ] VCS Tagging
    - [ ] Visual Studio
- Deployment Projects
    - [x] Details
    - [ ] Project permissions
    - Environments
        - [x] Details
        - [x] Tasks
        - [x] Triggers
        - [ ] Agent assignments
        - [ ] Notifications
        - [ ] Variables
        - [ ] Environment permissions
