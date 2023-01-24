# JHipster Lite ⚡

[![Build Status][github-actions-jhlite-image]][github-actions-url]
[![Coverage Status][codecov-image]][codecov-url]

## Node.js and NPM

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js](https://nodejs.org/): We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

```
npm install
```

## Maven

To launch tests:

```
./mvnw clean test
```

To launch tests and integration tests:

```
./mvnw clean verify
```

[github-actions-jhlite-image]: https://github.com/jhipster/jhipster-lite/workflows/build/badge.svg
[github-actions-url]: https://github.com/jhipster/jhipster-lite/actions
[codecov-image]: https://codecov.io/gh/jhipster/jhipster-lite/branch/main/graph/badge.svg?token=TGYTFIF15C
[codecov-url]: https://codecov.io/gh/jhipster/jhipster-lite

<!-- jhipster-needle-readme -->
