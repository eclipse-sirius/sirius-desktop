# org.eclipse.sirius.server.diagram.sprotty

The code in this plug-in is experimental. It is made available for
interested people to experiment with it, but is not supported.

Note that it does not build out-of-the box, so by default it is not
integrated into the main build: it depends on Eclipse Sprotty 0.6.0
which at this time can not be easily consumed by other "classical"
Eclipse projects (at least ones made of Eclipse Platform Plug-ins).

If you want to build it, you will need to:

1. Get the official Sprotty 0.6.0 JARs [from Maven
   Central](https://search.maven.org/search?q=g:org.eclipse.sprotty)
   (here with their SHA1) and put them inside the `lib` and `lib_src`
   folders inside this plug-ins:

    - `9080e69f2669a0e7c64072b98bc75beb0667abac`  `lib/org.eclipse.sprotty-0.6.0.jar`
    - `66ac6d156cd92241a87448b75a55503174ba8006`  `lib/org.eclipse.sprotty.layout-0.6.0.jar`
    - `ba72351a960b3d9d60c654b65ad48369a447db69`  `lib/org.eclipse.sprotty.server-0.6.0.jar`
    - `6557f422ccd50cb861feb24b03073cd7fc80f8b4`  `lib_src/org.eclipse.sprotty-0.6.0-javadoc.jar`
    - `bd2b961981f8f810e060c8e0569b65bf64a18626`  `lib_src/org.eclipse.sprotty-0.6.0-sources.jar`
    - `dd6b278a592376c59530bee3603401229b2be1fa`  `lib_src/org.eclipse.sprotty.layout-0.6.0-javadoc.jar`
    - `ee9378042abd40ed9d5aaac635492122ab6e8178`  `lib_src/org.eclipse.sprotty.layout-0.6.0-sources.jar`
    - `b193cb530b4393949c5070563a534a45929dfa26`  `lib_src/org.eclipse.sprotty.server-0.6.0-javadoc.jar`
    - `81d229f90609a3d96cc1002c2f935424e7c9f716`  `lib_src/org.eclipse.sprotty.server-0.6.0-sources.jar`

2. In `packaging/org.eclipse.sirius.parent/pom.xml`, enable the
   `org.eclipse.sirius.server.diagram.sprotty` module (found inside
   the `headless-server` profile), which is commented out by default.

3. In `packaging/org.eclipse.sirius.server.feature/feature.xml`, enable the
   `org.eclipse.sirius.server.diagram.sprotty` plug-in, which is commented out
   by default.
