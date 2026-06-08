# Repository Guidelines

## Project Structure & Module Organization

This is an Eclipse Sirius Desktop/Tycho workspace. Runtime and UI plug-ins live under `plugins/`, with Java sources in each bundle's `src/`, generated EMF code in `src-gen/`, models in `model/`, descriptors in `description/`, and UI resources such as `icons/`, `css/`, `schema/`, or `help/` beside the bundle metadata. Tests are separate plug-ins, mainly `plugins/org.eclipse.sirius.tests.junit`, `plugins/org.eclipse.sirius.tests.swtbot`, `plugins/org.eclipse.sirius.tests.tree`, and `plugins/org.eclipse.sirius.tests.ui.properties`. Feature, update-site, and parent build modules are in `packaging/`; target platforms and shared Eclipse formatter/checkstyle settings are in `releng/`.

## Build, Test, and Development Commands

- `mvn clean package`: builds from the root delegating to `packaging/org.eclipse.sirius.parent`; the main p2 repository is produced under `packaging/org.eclipse.sirius.update/target/repository`.
- `./build.sh [platform] [goal] [suites]`: runs the headless full build via the parent POM. Defaults are `2025-09`, `package`, and `junit,swtbot-sequence,swtbot`.
- `mvn -f packaging/org.eclipse.sirius.parent/pom.xml -P headless,full,junit clean verify`: runs the JUnit test profile.
- `mvn -f packaging/org.eclipse.sirius.parent/pom.xml -P headless,full,swtbot clean verify`: runs SWTBot UI tests; expect a longer, UI-sensitive run.

Use Java 21, matching the bundle `Bundle-RequiredExecutionEnvironment` and Tycho configuration.

## Coding Style & Naming Conventions

Java packages and bundles follow `org.eclipse.sirius...` naming. Keep production code in non-test bundles and add regression coverage in the matching `org.eclipse.sirius.tests...` bundle. Import the shared Eclipse settings from `releng/org.eclipse.sirius.settings`: `JavaFormatter.xml`, `CheckstyleConfiguration.xml`, `CleanupProfile.xml`, and `sirius.importorder`. Do not hand-edit generated `src-gen/` code unless the generator source is unavailable or the change is explicitly scoped.

## Testing Guidelines

JUnit suites are configured in the parent POM (`junit`, `gerrit-junit`, `long-tests`, `unreliable-tests`). SWTBot suites use `swtbot`, `swtbot-part1`, `swtbot-part2`, and `swtbot-sequence`. Name test classes descriptively with `Test` or `Tests`, and keep test data under the owning test plug-in's `data/` tree.

## Commit & Pull Request Guidelines

Recent commits use short bracketed prefixes such as `[releng]`, `[fix]`, or an issue number like `[712]`, followed by an imperative summary. Non-committer contributions must include a `Signed-off-by` footer and require an Eclipse Contributor Agreement. Pull requests should describe the behavior change, reference the Bugzilla/GitHub issue when applicable, list the test profile run, and include screenshots for visible UI or diagram changes.
