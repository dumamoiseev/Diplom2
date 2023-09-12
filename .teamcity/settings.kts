import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubAppConnection
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.05"

project {

    buildType(Build)

    features {
        githubAppConnection {
            id = "PROJECT_EXT_10"
            displayName = "new!"
            appId = "123"
            clientId = "123"
            clientSecret = "credentialsJSON:03c11ea2-e79b-4508-bb23-484668345ccd"
            privateKey = "credentialsJSON:4f1a5ccd-ca9e-49ae-a96a-175ea8790c81"
            ownerUrl = "https://github.com/username"
        }
        githubAppConnection {
            id = "PROJECT_EXT_8"
            displayName = "GitHub App"
            appId = "3384537845"
            clientId = "ergerg24"
            clientSecret = "credentialsJSON:29d9e908-22c2-4857-9649-b9425db519fb"
            privateKey = "credentialsJSON:4f1a5ccd-ca9e-49ae-a96a-175ea8790c81"
            ownerUrl = "https://github.com/username"
        }
        githubAppConnection {
            id = "PROJECT_EXT_9"
            displayName = "added"
            appId = "123"
            clientId = "123"
            clientSecret = "credentialsJSON:03c11ea2-e79b-4508-bb23-484668345ccd"
            privateKey = "credentialsJSON:4f1a5ccd-ca9e-49ae-a96a-175ea8790c81"
            ownerUrl = "https://github.com/username"
        }
    }
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})
