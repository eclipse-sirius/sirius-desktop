## Eclipse Sirius

Sirius enables the specification of a modeling workbench in terms of graphical, table or tree editors with validation rules and actions using declarative descriptions.

For more details see [the project page](https://www.eclipse.dev/sirius) and [the main wiki page](https://wiki.eclipse.org/Sirius).

### Building

The build uses [Tycho](http://www.eclipse.org/tycho/). To launch a complete build, issue

```
mvn clean package
```

from the top-level directory. The resulting update-site (p2 repository) can be found in `packaging/org.eclipse.sirius.update/target/repository`.

By default the build uses a Neon-based target platform. You can specify a different platform like this:

```
mvn clean package -Dplatform-version-name=name
```

where `name` can be any of the following values:
* `mars` (Eclipse 4.5)
* `neon` (Eclipse 4.6, the default and reference target platform)
* `canary` (uses nightly builds of all our dependencies, only used for testing)

The corresponding target platform definitions can be found in `releng/org.eclipse.sirius.targets`.
