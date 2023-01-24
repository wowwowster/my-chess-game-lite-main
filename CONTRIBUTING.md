# Contributing to JHipster Lite

Are you ready to contribute to JHipster? We'd love to have you on board, and we will help you as much as we can. Here are the guidelines we'd like you to follow so that we can be of more help:

- [Questions and help](#question)
- [Issues and Bugs](#issue)
- [Bug bounties](#bounties)
- [Feature Requests](#feature)
- [Generator development setup](#setup)
- [Submission Guidelines](#submit)
- [Coding Rules](#rules)
- [Git Commit Guidelines](#commit)
- [Module creation guideline](documentation/module-creation.md)
- [Style](documentation/style.md)

And don't forget we also accept [financial contributions to the project](https://www.jhipster.tech/sponsors/) using OpenCollective.

## <a name="question"></a> Questions and help

This is the JHipster bug tracker, and it is used for [Issues and Bugs](#issue) and for [Feature Requests](#feature). It is **not** a help desk or a support forum.

If you have a question on using JHipster, or if you need help with your JHipster project, please [read our help page](https://www.jhipster.tech/help/) and use the [JHipster tag on StackOverflow](http://stackoverflow.com/tags/jhipster) or join our [Gitter.im chat room](https://gitter.im/jhipster/jhipster-lite).

## <a name="issue"></a> Issues and Bugs

If you find a bug in the source code or a mistake in the documentation, you can help us by [submitting a ticket](https://opensource.guide/how-to-contribute/#opening-an-issue) to our [GitHub issues](https://github.com/jhipster/jhipster-lite/issues). Even better, you can submit a Pull Request to our [JHipster Lite project](https://github.com/jhipster/jhipster-lite) or to our [Documentation project](https://github.com/jhipster/jhipster.github.io).

**Please see the Submission Guidelines below**.

## <a name="bounties"></a> Bug bounties

If you submitted a Pull Request that fixes a ticket with the "\$100" tag, then you are eligible to our bug bounty program! Go to our [bug bounties documentation](https://www.jhipster.tech/bug-bounties/) for more information, and claim your money.

## <a name="feature"></a> Feature Requests

You can request a new feature by submitting a ticket to our [GitHub issues](https://github.com/jhipster/jhipster-lite/issues). If you
would like to implement a new feature then consider what kind of change it is:

- **Major Changes** that you wish to contribute to the project should be discussed first. Please open a ticket which clearly states that it is a feature request in the title and explain clearly what you want to achieve in the description, and the JHipster team will discuss with you what should be done in that ticket. You can then start working on a Pull Request.
- **Small Changes** can be proposed without any discussion. Open up a ticket which clearly states that it is a feature request in the title. Explain your change in the description, and you can propose a Pull Request straight away.

## <a name="setup"></a> Generator development setup

JHipster Lite is a Spring Boot + Vue3 project, using Java 17.

Here are the most important steps.

### Prerequisites

#### Java

You need to have [Java 17](https://openjdk.java.net/projects/jdk/17/) :

```
$ java -version
openjdk version "17.0.1" 2021-10-19
OpenJDK Runtime Environment (build 17.0.1+12-Ubuntu-120.04)
OpenJDK 64-Bit Server VM (build 17.0.1+12-Ubuntu-120.04, mixed mode, sharing)
```

```
$ javac -version
javac 17.0.1
```

#### Node.js and NPM

[Node.js](https://nodejs.org/): we use Node to run a development web server and build the project. Depending on your system, you can install Node either from source or as a pre-packaged bundle.

```
$ node -v
v16.18.0
```

```
$ npm -v
8.19.2
```

#### Docker

To launch containers for development (like Databases, Sonar...) you need:

- [Docker](https://docs.docker.com/engine/install/) _(minimum version: 20.xx.xx)_

```
$ docker -v
Docker version 20.10.17, build 100c701
```

### Fork the jhipster-lite project

Go to the [jhipster-lite project](https://github.com/jhipster/jhipster-lite) and click on the "fork" button. You can then clone your own fork of the project, and start working on it.

[Please read the GitHub forking documentation for more information](https://help.github.com/articles/fork-a-repo)

Using SSH:

```
git clone git@github.com:<YOUR_USERNAME>/jhipster-lite.git
```

Using HTTPS:

```
git clone https://github.com/<YOUR_USERNAME>/jhipster-lite.git
```

Then, go inside your fork and add upstream:

Using SSH:

```
git remote add upstream git@github.com:jhipster/jhipster-lite.git
```

Using HTTPS:

```
git remote add upstream https://github.com/jhipster/jhipster-lite.git
```

The result of remote should be:

```
$ git remote -v
origin	git@github.com:<YOUR_USERNAME>/jhipster-lite.git (fetch)
origin	git@github.com:<YOUR_USERNAME>/jhipster-lite.git (push)
upstream	git@github.com:jhipster/jhipster-lite.git (fetch)
upstream	git@github.com:jhipster/jhipster-lite.git (push)
```

You can edit your `.git/config`, and update this section:

```
[remote "upstream"]
	url = git@github.com:jhipster/jhipster-lite.git
	fetch = +refs/heads/*:refs/remotes/upstream/*
	fetch = +refs/pull/*/head:refs/remotes/upstream/pr/*
```

With this change, you'll be able to use `git fetch upstream` and test all existing pull requests, using `git switch pr/<number>`.

### Set NPM to use the cloned project

In your cloned `jhipster-lite` project, install all project dependencies:

```
npm ci
```

For testing:

```
./mvnw clean verify
```

For testing the front only:

```
npm run test
```

For running the project:

```
./mvnw
```

For running the front only:

```
npm run start
```

### Use a text editor

As modifying the JHipster Lite generator includes modifying Java and Typescript templates, most IDE will not work correctly. We recommend you use a text editor like [IntelliJ](https://www.jetbrains.com/idea/) or [VSCode](https://code.visualstudio.com/) to code your changes.

## <a name="submit"></a> Submission Guidelines

### [Submitting an Issue](https://opensource.guide/how-to-contribute/#opening-an-issue)

Before you submit your issue search the [archive](https://github.com/jhipster/jhipster-lite/issues), maybe your question was already answered.

If your issue appears to be a bug, and has not been reported, open a new issue.
Help us to maximize the effort we can spend fixing issues and adding new
features, by not reporting duplicate issues. Providing the following information will increase the
chances of your issue being dealt with quickly:

- **Overview of the issue** - if an error is being thrown a stack trace helps
- **Motivation for or Use Case** - explain why this is a bug for you
- **Reproduce the error** - an unambiguous set of steps to reproduce the error. If you have a JavaScript error, maybe you can provide a live example with
  [JSFiddle](http://jsfiddle.net/)?
- **Related issues** - has a similar issue been reported before?
- **Suggest a Fix** - if you can't fix the bug yourself, perhaps you can point to what might be
  causing the problem (line of code or commit)
- **JHipster Version(s)** - is it a regression?
- **JHipster history, a `history.json` file generated in the `.jhipster/` folder** - this will help us to replicate the scenario.
- **Browsers and Operating System** - is this a problem with all browsers or only IE8?

Click [here][issue-template] to open a bug issue with a pre-filled template. For feature requests and enquiries you can use [this template][feature-template].

Issues opened without any of these info can be **closed** without any explanation.

### [Submitting a Pull Request](https://opensource.guide/how-to-contribute/#opening-a-pull-request)

Before you submit your pull request consider the following guidelines:

- Search [GitHub](https://github.com/jhipster/jhipster-lite/pulls?utf8=%E2%9C%93&q=is%3Apr) for an open or closed Pull Request
  that relates to your submission.
- If you want to modify the JHipster generator, read our [Generator development setup](#setup)
- Refresh your project

  ```shell
  git switch main
  git fetch upstream
  git rebase upstream/main
  ```

- Make your changes in a new git branch

  ```shell
  git switch -c my-fix-branch
  ```

- Create your patch, **including appropriate test cases**.
- Follow our [Coding Rules](#rules).
- Launch all tests

- ```shell
  ./mvnw clean verify
  ```
- Generate a new JHipster project, and ensure that all tests pass

  ```shell
  ./mvnw clean verify
  ```

- Test that the new project runs correctly:

  ```shell
  ./mvnw spring-boot:run
  ```

- You can generate our Continuous Integration (with GitHub Actions and Azure Pipelines) by following [this](#local-build)

- Commit your changes using a descriptive commit message that follows our
  [commit message conventions](#commit-message-format).

  ```shell
  git commit -a
  ```

  Note: the optional commit `-a` command line option will automatically "add" and "rm" edited files.

- Push your branch to GitHub:

  ```shell
  git push -u origin my-fix-branch
  ```

- In GitHub, send a pull request to `jhipster/jhipster-lite:main`.
- If we suggest changes then

  - Make the required updates.
  - Re-run the JHipster tests on your sample generated project to ensure tests are still passing.
  - Rebase your branch and force push to your GitHub repository (this will update your Pull Request):

    ```shell
    git fetch upstream
    git rebase upstream/main -i
    git push -f origin my-fix-branch
    ```

That's it! Thank you for your contribution!

#### Resolving merge conflicts ("This branch has conflicts that must be resolved")

Sometimes your PR will have merge conflicts with the upstream repository's main branch. There are several ways to solve this but if not done correctly this can end up as a true nightmare. So here is one method that works quite well.

- First, fetch the latest information from the main

  ```shell
  git fetch upstream
  ```

- Rebase your branch against the upstream/main

  ```shell
  git rebase upstream/main
  ```

- Git will stop rebasing at the first merge conflict and indicate which file is in conflict. Edit the file, resolve the conflict then

  ```shell
  git add <the file that was in conflict>
  git rebase --continue
  ```

- The rebase will continue up to the next conflict. Repeat the previous step until all files are merged and the rebase ends successfully.
- Re-run the JHipster tests on your sample generated project to ensure tests are still passing.
- Force push to your GitHub repository (this will update your Pull Request)

  ```shell
  git push -f origin my-fix-branch
  ```

#### After your pull request is merged

After your pull request is merged, you can safely delete your branch and pull the changes
from the main (upstream) repository:

- Delete the remote branch on GitHub either through the GitHub web UI or your local shell as follows:

  ```shell
  git push origin --delete my-fix-branch
  ```

- Switch to the main branch:

  ```shell
  git switch main -f
  ```

- Delete the local branch:

  ```shell
  git branch -D my-fix-branch
  ```

- Update your main with the latest upstream version:

  ```shell
  git pull --ff upstream main
  ```

## <a name="local-build"></a> Running integration tests locally

### Overview

- The `tests-ci/generate.sh` file is used in the Continuous Integration pipeline to test generated projects.
- This script takes the application-name as input. This is the type of project you would like generate.
- Below are the list of applications that can be generated for testing (supported input params for the generate.sh script):
  - spring
  - fullstack
  - fullapp
  - oauth2app
  - mysqlapp
  - mariadbapp
  - mssqlapp
  - flywayapp
  - undertowapp
  - eurekaapp
  - consulapp
  - gatewayapp
  - mongodbapp
  - neo4japp
  - angularapp
  - angularoauth2app
  - reactapp
  - vueapp
  - svelteapp
  - kafkaapp
  - pulsarapp
  - reactiveapp
  - customjhlite
  - typescriptapp

### Generate project builds locally

- Start JHipster Lite application in local machine
  ```shell
  ./mvnw
  ```
- Run the generate.sh script with desired project build name.
  ```shell
  ./tests-ci/generate.sh <application>
  ```
- This will generate the project in `/tmp/jhlite/<application>`. Then, you can test it.
- The project location of the generated build is configured in the `test-ci/modulePayload.json`.

- For example,
  - Running `./tests-ci/generate.sh fullapp` will generate a Spring Boot project `fullapp` in the directory`/tmp/jhlite/`
  - The generated project will have support for maven, sonar, postgresql, liquibase, ehcache, vue-core and lot more capabilities required for a Spring Boot + Vue application.

## <a name="rules"></a> Coding Rules

To ensure consistency throughout the source code, keep these rules in mind as you are working:

- All features or bug fixes **must be tested** by one or more tests.
- All files must follow the [.editorconfig file](http://editorconfig.org/) located at the root of the JHipster generator project. Please note that generated projects use the same `.editorconfig` file, so that both the generator and the generated projects share the same configuration.
- Java files **must be** formatted using Prettier default code style.
- Generators JavaScript files **must follow** the eslint configuration defined at the project root, which is based on [Airbnb JavaScript Style Guide](https://github.com/airbnb/javascript).
- Any client side feature/change should be done for both Angular, React, Vue clients
- Web apps JavaScript files **must follow** [Google's JavaScript Style Guide](https://google.github.io/styleguide/jsguide.html).
- Angular Typescript files **must follow** the [Official Angular style guide](https://angular.io/styleguide).
- React/Redux Typescript files **may follow** the [React/Redux Typescript guide](https://github.com/piotrwitek/react-redux-typescript-guide).

Please ensure to run `npm run prettier:format` on the project root before submitting a pull request.

## <a name="commit"></a> Git Commit Guidelines

We have rules over how our git commit messages must be formatted. Please ensure to [squash](https://help.github.com/articles/about-git-rebase/#commands-available-while-rebasing) unnecessary commits so that your commit history is clean.

If the commit only involves documentation changes you can skip the continuous integration pipelines using `[ci skip]` or `[skip ci]` in your commit message header.

### <a name="commit-message-format"></a> Commit Message Format

Each commit message consists of a **header**, a **body** and a **footer**.

```
<header>
<BLANK LINE>
<body>
<BLANK LINE>
<footer>
```

Any line of the commit message cannot be longer 100 characters! This allows the message to be easier
to read on GitHub as well as in various git tools.

### Header

The Header contains a succinct description of the change:

- use the imperative, present tense: "change" not "changed" nor "changes"
- don't capitalize first letter
- no dot (.) at the end

### Body

If your change is simple, the Body is optional.

Just as in the Header, use the imperative, present tense: "change" not "changed" nor "changes".
The Body should include the motivation for the change and contrast this with previous behavior.

### Footer

The footer is the place to reference GitHub issues that this commit **Closes**.

You **must** use the [GitHub keywords](https://help.github.com/articles/closing-issues-via-commit-messages) for
automatically closing the issues referenced in your commit.

### Example

For example, here is a good commit message:

```
upgrade to Spring Boot 1.1.7

upgrade the Maven and Gradle builds to use the new Spring Boot 1.1.7,
see http://spring.io/blog/2014/09/26/spring-boot-1-1-7-released

Fix #1234
```

### Regular Contributor Guidelines

These are some of the guidelines that we would like to emphasize if you are a regular contributor to the project
or joined the [JHipster team](https://www.jhipster.tech/team/).

- We recommend not committing directly to main, but always submit changes through PRs.
- Before merging, try to get at least one review on the PR.
- Add appropriate labels to issues and PRs that you create (if you have permission to do so).
- Follow the project's [policies](https://www.jhipster.tech/policies/#-policies).
- Follow the project's [Code of Conduct](https://github.com/jhipster/jhipster-lite/blob/main/CODE_OF_CONDUCT.md)
  and be polite and helpful to users when answering questions/bug reports and when reviewing PRs.
- We work on our free time so we have no obligation nor commitment. Work/life balance is important, so don't
  feel tempted to put in all your free time fixing something.

[issue-template]: https://github.com/jhipster/jhipster-lite/issues/new?template=BUG_REPORT.md
[feature-template]: https://github.com/jhipster/jhipster-lite/issues/new?template=FEATURE_REQUEST.md
