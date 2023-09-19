import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubAppConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubIssues
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

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

    vcsRoot(HttpsGithubComDumamoiseevDumamoiseevQaJavaDiplom2refsHeadsDevelop2)

    buildType(Build)

    features {
        githubIssues {
            id = "PROJECT_EXT_10"
            displayName = "dumamoiseev/dumamoiseev-QA-java-diplom-2"
            repositoryURL = "https://github.com/dumamoiseev/dumamoiseev-QA-java-diplom-2"
            authType = storedToken {
                tokenId = "tc_token_id:CID_2c1fa9908f5d7d1498b9694660846af9:-1:e85b7b74-6abb-4d19-92a1-6c7b817d427b"
            }
        }
        githubAppConnection {
            id = "PROJECT_EXT_13"
            displayName = "test"
            appId = "123123"
            clientId = "123"
            clientSecret = "credentialsJSON:b8f3f133-2e3f-4419-82e8-6391ba826149"
            privateKey = "credentialsJSON:63236a69-7055-41bf-9f19-0b40bf62a039"
            ownerUrl = "https://github.com/username"
        }
    }
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(HttpsGithubComDumamoiseevDumamoiseevQaJavaDiplom2refsHeadsDevelop2)
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
        pullRequests {
            vcsRootExtId = "${HttpsGithubComDumamoiseevDumamoiseevQaJavaDiplom2refsHeadsDevelop2.id}"
            provider = github {
                authType = storedToken {
                    tokenId = "tc_token_id:CID_2c1fa9908f5d7d1498b9694660846af9:-1:ca1aab37-916a-410d-aeaa-d895e1c983c9"
                }
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
        commitStatusPublisher {
            vcsRootExtId = "${HttpsGithubComDumamoiseevDumamoiseevQaJavaDiplom2refsHeadsDevelop2.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = storedToken {
                    tokenId = "tc_token_id:CID_2c1fa9908f5d7d1498b9694660846af9:-1:331b670c-fb80-499a-bf6f-4ae74c0c15b5"
                }
            }
        }
    }
})

object HttpsGithubComDumamoiseevDumamoiseevQaJavaDiplom2refsHeadsDevelop2 : GitVcsRoot({
    name = "https://github.com/dumamoiseev/dumamoiseev-QA-java-diplom-2#refs/heads/develop2"
    url = "https://github.com/dumamoiseev/dumamoiseev-QA-java-diplom-2"
    branch = "refs/heads/develop2"
    branchSpec = "refs/heads/*"
    authMethod = token {
        userName = "oauth2"
        tokenId = "tc_token_id:CID_2c1fa9908f5d7d1498b9694660846af9:-1:90fd9adb-0888-4126-a205-4357d33f17a7"
    }
})
