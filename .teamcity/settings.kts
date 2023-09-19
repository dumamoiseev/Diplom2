import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildFeatures.pullRequests
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
            id = "PROJECT_EXT_11"
            displayName = "true!"
            appId = "388894"
            clientId = "Iv1.7f794f35ab833ac3"
            clientSecret = "credentialsJSON:44f37ca7-4adf-487f-8cea-768fade974fe"
            privateKey = "credentialsJSON:4f1a5ccd-ca9e-49ae-a96a-175ea8790c81"
            ownerUrl = "https://github.com/dumamoiseev"
        }
        githubAppConnection {
            id = "PROJECT_EXT_14"
            displayName = "fake!"
            appId = "12312312311"
            clientId = "123123123"
            clientSecret = "credentialsJSON:03c11ea2-e79b-4508-bb23-484668345ccd"
            privateKey = "credentialsJSON:0915168c-4378-41c9-a42e-ca990b20fa99"
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
            branchFilter = "+:develop2"
        }
    }

    features {
        perfmon {
        }
        pullRequests {
            vcsRootExtId = "${DslContext.settingsRoot.id}"
            provider = github {
                authType = storedToken {
                    tokenId = "tc_token_id:CID_7adc2a68e4f0125c5f5c9ddb7b7806a2:-1:94a1c6e2-b4c4-41f1-993f-9c8e0278ed59"
                }
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
    }
})
