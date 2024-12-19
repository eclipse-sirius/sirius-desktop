## Eclipse Sirius Desktop

Sirius enables the specification of a modeling workbench in terms of graphical, table or tree editors with validation rules and actions using declarative descriptions.

For more details see [the project's website](https://eclipse.dev/sirius).

### Building

The build uses [Tycho](https://github.com/eclipse-tycho/).
To launch a complete build, issue:

```sh
mvn clean package
```

from the top-level directory. The resulting update-site (p2 repository) can be found in `packaging/org.eclipse.sirius.update/target/repository`.

By default the build uses a Neon-based target platform. You can specify a different platform like this:

```sh
mvn clean package -Dplatform-version-name=name
```

where `name` can be any of the following values target platform definitions found in `releng/org.eclipse.sirius.targets` (without the `sirius_` prefix).
The default is `2023-03`.

### Continuous Integration

The official builds are run in the Eclipse Foundation's infrastructure, at <https://ci.eclipse.org/sirius>.

### Update Sites

Update Sites (p2 repositories) are available at:

* Releases: <https://download.eclipse.org/sirius/updates/releases/>
* Milestones: <https://download.eclipse.org/sirius/updates/milestones/>
  * These are only kept until the corresponding version is actually released.
* Latest version built from `master`: <https://download.eclipse.org/sirius/updates/nightly/latest/>
* Older releases: <https://archive.eclipse.org/sirius/updates/releases/>

### License

Everything in this repository is Open Source.
Except when explicitly mentioned otherwise, the license is Eclipse Public License - v 2.0.
