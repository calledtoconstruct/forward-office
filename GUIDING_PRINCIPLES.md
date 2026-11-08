# Guiding principles: calledtoconstruct/forward-office

## Purpose and scope
A link shortener web app (Maven artifact `golink`, 'Link shortener').

## Stack and layout
Java 21, Spring Boot 3.3 (web, actuator, data JPA, Thymeleaf), Flyway migrations, H2 database, Lombok. Single Maven module; tests in `src/test/java` (`GolinkApplicationTests`).

## Commands
| Check | Command | Evidence |
|---|---|---|
| Build | `mvn clean package` | README.md |
| Run | `mvn spring-boot:run` | README.md |
| Type-check | Covered by the Maven compile | - |
| Changed-feature tests | `mvn test -Dtest=<TestClass>` | pom.xml (spring-boot-starter-test) |
| Full suite | `mvn test` (also part of `mvn package`) | pom.xml |
| CI | None: no workflow files | repository tree |

Commands marked as not written down come from the build files, not from project docs; confirm them before relying on them.

## Before every push
Before every push: run the build and type-check/lint that exist here, plus only the tests for the files or feature you changed (team rule, ideas #14 note 6797). Do not run the full suite locally as a routine gate; this repository has no CI that runs the full suite, so run the full suite only when the change is broad, and say in the PR that CI coverage is missing. Merge needs a positive review. Explain any new failure your change caused. Never weaken a valid test to make it pass, and never invent a command this file does not list.

## Project rules
- Schema changes go through new Flyway migration files; never edit a migration that has already been applied.
- Keep the standard Spring Boot layering already in the code (controllers, services, repositories).

## What to avoid
- Committing to the default branch; work on a branch and open a pull request.
- Deleting files, branches or history, or changing repository settings.
- Adding tooling, CI or dependencies that the change does not need.
- Rules copied from other projects (file-size limits, clocks, quotas) that this repository never adopted.

## Delivery and safety
- No deployment is configured in the repository.

## Open questions
- No CI; `mvn package` runs the whole test suite locally, so it is also the full check.

## Sources
Read from the `main` branch on 2026-11-08 ET: `README.md`, `pom.xml`, repository tree.

## Instruction updates
Check `guiding-principles.version` at the start of each turn and when you enter this project; reload this file when the version changes. Change this file, its version and date, and `PRINCIPLES_HISTORY.md` in the same commit. Team-wide role instructions are versioned separately in the team-process repository.
