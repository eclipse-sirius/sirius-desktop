To run the tests locally:
- Build the Sirius RCPTT test product : 
./build.sh neon package rcptt
- Launch the tests locally with maven : 
DISPLAY=:1 SWT_GTK3=0 mvn -f plugins/org.eclipse.sirius.tests.rcptt/pom.xml test