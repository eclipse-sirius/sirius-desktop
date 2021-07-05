/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 *      Ketan Padegaonkar and others - the various waitUntil() methods come
 *        from org.eclipse.swtbot.swt.finder.SWTBotFactory
 *******************************************************************************/
package org.eclipse.sirius.tests.support.api;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.internal.framework.EquinoxBundle;
import org.eclipse.osgi.storage.BundleInfo.Generation;
import org.eclipse.pde.core.target.ITargetDefinition;
import org.eclipse.pde.core.target.ITargetHandle;
import org.eclipse.pde.core.target.ITargetLocation;
import org.eclipse.pde.core.target.ITargetPlatformService;
import org.eclipse.pde.core.target.LoadTargetDefinitionJob;
import org.eclipse.pde.internal.core.target.TargetPlatformService;
import org.eclipse.ui.PlatformUI;
import org.junit.Assert;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * Useful operation common to test cases.
 * 
 * @author mchauvin
 */
public final class TestsUtil {

    private static final String UI_WORKBENCH_JUNO_START = "3.103";

    private static final String UI_WORKBENCH_KEPLER_START = "3.105";

    private static final String UI_WORKBENCH_LUNA_START = "3.106";

    private static final String UI_WORKBENCH_OXYGEN_START = "3.110";

    private static final String UI_WORKBENCH_PHOTON_START = "3.111";

    private static final String UI_WORKBENCH_202003_START = "3.118";

    private static final String UI_WORKBENCH_202006_START = "3.119";

    private static final String UI_WORKBENCH_202106_START = "3.122";

    private static final String CREATE_REPRESENTATATION_IN_SEPARATE_RESOURCE = "createLocalRepresentationInSeparateResource";

    /**
     * Constructor.
     */
    protected TestsUtil() {
        // Nothing
    }

    /**
     * Wait the end of the asynchronous calls in UI Thread and ignore the Exception. <B>Use this exclusively</B> in the
     * setup method to ensure a clean environment.
     */
    public static void emptyEventsFromUIThread() {
        boolean shouldRetry = false;
        do {
            try {
                TestsUtil.synchronizationWithUIThread();
                shouldRetry = false;
                // CHECKSTYLE:OFF
            } catch (final Exception e) {
                // CHECKSTYLE:ON
                shouldRetry = true;
            }
        } while (shouldRetry);
    }

    /**
     * Wait the end of the asynchronous calls waiting in UI thread.
     */
    public static void synchronizationWithUIThread() {
        while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
            // Do nothing, just wait
        }
    }

    /**
     * Tests whether the environment is configured to skip non-critical tests which take a long time. This possibility
     * to skip some tests should only be used on developer machines to get faster feedback, and never on a continuous
     * integration server.<BR/>
     * 
     * <B>WARNING:</B> All tests using this method must be added in a corresponding tests suite class:
     * org.eclipse.sirius.tests.suite.AllSiriusLongTestSuite, org.eclipse.sirius.tests.swtbot.suite.AllLongTestSuite,
     * ...
     * 
     * @return <code>true</code> if the environment is setup to skip long tests.
     */
    public static boolean shouldSkipLongTests() {
        return "true".equals(System.getProperty("org.eclipse.sirius.tests.skipLongTests"));
    }

    /**
     * Tests whether the environment has a version of gtk that correctly supports GTK3.
     * 
     * @return <code>true</code> if GTK3 is supported, false otherwise.
     */
    public static boolean isGTK3supported() {
        boolean isGTK3supported = true;
        String stringVersion = System.getProperty("org.eclipse.swt.internal.gtk.version");
        if (stringVersion != null) {
            Version minimalRequiredVersion = new Version("3.14.0");
            Version currentVersion = new Version(stringVersion);
            isGTK3supported = currentVersion.compareTo(minimalRequiredVersion) >= 0;
        }
        return isGTK3supported;
    }

    /**
     * Tests whether unreliable tests should be run. See {@link #shouldSkipUnreliableTests()}. Can be used in tests as:
     * 
     * <pre>
     * Assume.assumeTrue(TestUtil.shouldRunUnreliableTests())
     * </pre>
     * 
     * at the beginning of such tests.<BR/>
     * 
     * <B>WARNING:</B> All tests using this method must be added in a corresponding tests suite class:
     * org.eclipse.sirius.tests.suite.AllSiriusUnreliableTestSuite,
     * org.eclipse.sirius.tests.swtbot.suite.AllUnreliableTestSuite, ...
     * 
     * @return <code>true</code> iff unreliable tests should be run.
     */
    public static boolean shouldRunUnreliableTests() {
        return !TestsUtil.shouldSkipUnreliableTests();
    }

    /**
     * Tests whether the environment is configured to skip tests which are known to be unreliable (i.e. they sometimes
     * work, sometimes fail).<BR/>
     * <B>WARNING:</B> All tests using this method must be added in a corresponding tests suite class:
     * org.eclipse.sirius.tests.suite.AllSiriusUnreliableTestSuite,
     * org.eclipse.sirius.tests.swtbot.suite.AllUnreliableTestSuite, ...
     * 
     * @return <code>true</code> if the environment is setup to skip unreliable tests.
     */
    public static boolean shouldSkipUnreliableTests() {
        return "true".equals(System.getProperty("org.eclipse.sirius.tests.skipUnreliableTests"));
    }

    /**
     * Tests whether long running tests should be run. See {@link #shouldSkipLongTests()}. Can be used in tests as:
     * 
     * <pre>
     * Assume.assumeTrue(TestUtil.shouldRunLongTests())
     * </pre>
     * 
     * at the beginning of such tests.<BR/>
     * 
     * <B>WARNING:</B> All tests using this method must be added in a corresponding tests suite class:
     * org.eclipse.sirius.tests.suite.AllSiriusLongTestSuite, org.eclipse.sirius.tests.swtbot.suite.AllLongTestSuite,
     * ...
     * 
     * @return <code>true</code> iff long running tests should be run.
     */
    public static boolean shouldRunLongTests() {
        return !TestsUtil.shouldSkipLongTests();
    }

    /**
     * Whether the createLocalRepresentationInSeparateResource system property is set at true or not.
     * 
     * @return true if createLocalRepresentationInSeparateResource property is set at true, otherwise false.
     */
    public static boolean isCreateRepresentationInSeparateResource() {
        return Boolean.getBoolean(CREATE_REPRESENTATATION_IN_SEPARATE_RESOURCE);
    }

    /**
     * Change the createLocalRepresentationInSeparateResource system property.
     * 
     * @param value
     *            the value
     * 
     * @return the previous value
     */
    public static boolean setCreateRepresentationInSeparateResource(boolean value) {
        String property = System.setProperty(CREATE_REPRESENTATATION_IN_SEPARATE_RESOURCE, String.valueOf(value));
        return Boolean.valueOf(property);
    }

    /**
     * Tests if the EEF-based properties view support is installed.
     * 
     * @return <code>true</code> if the EEF-based properties view support is installed.
     */
    public static boolean isEEFBasedPropertiesViewsSupportInstalled() {
        return Platform.getBundle("org.eclipse.sirius.ui.properties") != null;
    }

    /**
     * Tells if the current platform corresponds to juno3 (i.e. Eclipse 3.8).
     * 
     * @return true if the current platform corresponds to juno3 (i.e. Eclipse 3.8), false else
     */
    public static boolean isJuno3Platform() {
        boolean isJuno3Platform = false;
        String platformVersion = Platform.getBundle("org.eclipse.core.runtime").getHeaders().get("Bundle-Version");
        if (platformVersion.startsWith("3.8")) {
            isJuno3Platform = true;
        }
        return isJuno3Platform && !isJuno4Platform();
    }

    /**
     * Tells if the current platform corresponds to Juno, Kepler, Luna, .. (i.e. Eclipse 4.x).
     * 
     * @return true if the current platform corresponds to eclipse 4.x, false otherwise.
     */
    public static boolean isEclipse4xPlatform() {
        Version junoStart = Version.parseVersion(UI_WORKBENCH_JUNO_START);
        return checkUiWorkbenchVersion(junoStart, null);
    }

    /**
     * Tells if the current platform corresponds to Juno (i.e. Eclipse 4.x).
     * 
     * @return true if the current platform corresponds to Juno 4.x, false otherwise.
     */
    public static boolean isJuno4Platform() {
        Version junoStart = Version.parseVersion(UI_WORKBENCH_JUNO_START);
        Version keplerStart = Version.parseVersion(UI_WORKBENCH_KEPLER_START);
        return checkUiWorkbenchVersion(junoStart, keplerStart);
    }

    private static boolean checkUiWorkbenchVersion(Version versiontStart, Version versionEnd) {
        /*
         * Juno/Kepler Core Runtime plugins version are 3.8/3.9 and not 4.x. So the "org.eclipse.ui.workbench" is used
         * instead.
         */
        Bundle uiWorkbenchBundle = Platform.getBundle("org.eclipse.ui.workbench");
        if (uiWorkbenchBundle != null) {
            return (versiontStart == null || uiWorkbenchBundle.getVersion().compareTo(versiontStart) >= 0) && (versionEnd == null || uiWorkbenchBundle.getVersion().compareTo(versionEnd) < 0);
        }
        return false;
    }

    /**
     * Tells if the current platform corresponds to Kepler.
     * 
     * @return true if the current platform corresponds to Kepler, false otherwise.
     */
    public static boolean isKeplerPlatform() {
        Version keplerStart = Version.parseVersion(UI_WORKBENCH_KEPLER_START);
        Version lunaStart = Version.parseVersion(UI_WORKBENCH_LUNA_START);
        return checkUiWorkbenchVersion(keplerStart, lunaStart);
    }

    /**
     * Tells if the current platform corresponds to Luna.
     * 
     * @return true if the current platform corresponds to Luna, false otherwise.
     */
    public static boolean isLunaPlatform() {
        return checkUiWorkbenchVersion(Version.parseVersion(UI_WORKBENCH_LUNA_START), null);
    }

    /**
     * Tells if the current platform corresponds to a version before Oxygen.
     * 
     * @return true if the current platform corresponds to a version before Oxygen, false otherwise.
     */
    public static boolean isBeforeOxygenPlatform() {
        return checkUiWorkbenchVersion(null, Version.parseVersion(UI_WORKBENCH_OXYGEN_START));
    }

    /**
     * Tells if the current platform corresponds to Oxygen or later.
     * 
     * @return true if the current platform corresponds to Oxygen or later, false otherwise.
     */
    public static boolean isOxygenPlatform() {
        return checkUiWorkbenchVersion(Version.parseVersion(UI_WORKBENCH_OXYGEN_START), null);
    }

    /**
     * Tells if the current platform corresponds to Photon or later.
     * 
     * @return true if the current platform corresponds to Photon or later, false otherwise.
     */
    public static boolean isPhotonPlatformOrLater() {
        return checkUiWorkbenchVersion(Version.parseVersion(UI_WORKBENCH_PHOTON_START), null);
    }

    /**
     * Tells if the current platform corresponds to 2020-03 or later.
     * 
     * @return true if the current platform corresponds to 2020-03 or later, false otherwise.
     */
    public static boolean is202003Platform() {
        return checkUiWorkbenchVersion(Version.parseVersion(UI_WORKBENCH_202003_START), null);
    }

    /**
     * Tells if the current platform corresponds to 2020-06 or later.
     * 
     * @return true if the current platform corresponds to 2020-06 or later, false otherwise.
     */
    public static boolean is202006Platform() {
        return checkUiWorkbenchVersion(Version.parseVersion(UI_WORKBENCH_202006_START), null);
    }

    /**
     * Tells if the current platform corresponds to 2021-06 or later.
     * 
     * @return true if the current platform corresponds to 2021-06 or later, false otherwise.
     */
    public static boolean is202106Platform() {
        return checkUiWorkbenchVersion(Version.parseVersion(UI_WORKBENCH_202106_START), null);
    }

    /**
     * Copied and adapted from org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(ICondition, long, long)
     * 
     * Waits until the condition has been meet, or the timeout is reached. The interval is the delay between evaluating
     * the condition after it has failed.
     * 
     * @param condition
     *            the condition to be evaluated.
     * @param timeout
     *            the timeout.
     * @param interval
     *            The delay time.
     */
    public static void waitUntil(ICondition condition, long timeout, long interval) {
        Assert.assertTrue("interval value is negative", interval >= 0); //$NON-NLS-1$
        Assert.assertTrue("timeout value is negative", timeout >= 0); //$NON-NLS-1$
        long limit = System.currentTimeMillis() + timeout;
        while (true) {
            try {
                if (condition.test()) {
                    return;
                }
                // CHECKSTYLE:OFF
            } catch (Throwable e) {
                // dO nothing
            }
            // CHECKSTYLE:ON

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Assert.fail(e.getMessage());
            }
            if (System.currentTimeMillis() > limit) {
                Assert.fail("Timeout after: " + timeout + " ms.: " + condition.getFailureMessage());
            }
        }
    }

    /**
     * Copied and adapted from org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(ICondition, long, long)
     * 
     * Waits until the condition has been meet, or the timeout is reached. The interval is the delay between evaluating
     * the condition after it has failed.
     * 
     * Interval : 500
     * 
     * @param condition
     *            the condition to be evaluated.
     * @param timeout
     *            the timeout.
     */
    public static void waitUntil(ICondition condition, long timeout) {
        TestsUtil.waitUntil(condition, timeout, 500);
    }

    /**
     * Copied and adapted from org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(ICondition, long, long)
     * 
     * Waits until the condition has been meet, or the timeout is reached. The interval is the delay between evaluating
     * the condition after it has failed.
     * 
     * Timeout: 5000 Interval : 500
     * 
     * @param condition
     *            the condition to be evaluated.
     */
    public static void waitUntil(ICondition condition) {
        TestsUtil.waitUntil(condition, 5000, 500);
    }

    /**
     * Sets a target platform in the test platform to get workspace builds OK with PDE.<BR>
     * Copied and adpated from http://git.eclipse.org/c/gmf-tooling/org.eclipse.gmf-tooling.git/tree/
     * tests/org.eclipse.gmf.tests/src/org/eclipse/gmf/tests/Utils.java
     * 
     * @param currentPluginSymbolicName
     *            The plug-in name from where the tests are currently launched.
     * 
     * @throws CoreException
     *             In case of problem to retrieve current target platform or to save the new one.
     * @throws InterruptedException
     *             if the loading platform job is interrupted while waiting
     */
    @SuppressWarnings("restriction")
    public static void setTargetPlatform(String currentPluginSymbolicName) throws CoreException, InterruptedException {
        String targetName = "PDE Platgorm from OSGi bundles";
        ITargetPlatformService tpService = TargetPlatformService.getDefault();
        ITargetHandle targetHandle = tpService.getWorkspaceTargetHandle();
        if (targetHandle == null || !targetName.equals(targetHandle.getTargetDefinition().getName())) {
            ITargetDefinition targetDef = tpService.newTarget();
            targetDef.setName(targetName);
            Bundle[] bundles = Platform.getBundle("org.eclipse.core.runtime").getBundleContext().getBundles();
            List<ITargetLocation> bundleContainers = new ArrayList<ITargetLocation>();
            Set<File> dirs = new HashSet<File>();
            for (Bundle bundle : bundles) {
                EquinoxBundle bundleImpl = (EquinoxBundle) bundle;
                Generation generation = (Generation) bundleImpl.getModule().getCurrentRevision().getRevisionInfo();
                File file = generation.getBundleFile().getBaseFile();
                File folder = file.getParentFile();
                if (!dirs.contains(folder)) {
                    dirs.add(folder);
                    if (!currentPluginSymbolicName.equals(bundleImpl.getSymbolicName())) {
                        bundleContainers.add(tpService.newDirectoryLocation(folder.getAbsolutePath()));
                    }
                }
            }
            targetDef.setTargetLocations(bundleContainers.toArray(new ITargetLocation[bundleContainers.size()]));
            targetDef.setArch(Platform.getOSArch());
            targetDef.setOS(Platform.getOS());
            targetDef.setWS(Platform.getWS());
            targetDef.setNL(Platform.getNL());
            tpService.saveTargetDefinition(targetDef);

            Job job = new LoadTargetDefinitionJob(targetDef);
            job.schedule();
            job.join();
        }
    }

    /**
     * Tells if the current java runtime version is the expected one.
     * 
     * @param expectedVersion
     *            The expected version (i.e. 7, 8, 9, 10..., 14, ...)
     * 
     * @return true if the current java runtime version is the expected one, false otherwise.
     */
    public static boolean isJavaVersion(int expectedVersion) {
        return getJavaRuntimeVersion() == expectedVersion;
    }

    /**
     * Tells if the current java runtime version is the expected one or older.
     * 
     * @param expectedVersion
     *            The expected version (i.e. 7, 8, 9, 10..., 14, ...)
     * 
     * @return true if the current java runtime version is 8 or lower, false otherwise.
     */
    public static boolean isJavaVersionOrOlder(int expectedVersion) {
        return getJavaRuntimeVersion() <= expectedVersion;
    }

    /**
     * Tells if the current java runtime version is the expected one or older.
     * 
     * @param expectedVersion
     *            The expected version (i.e. 7, 8, 9, 10..., 14, ...)
     * 
     * @return true if the current java runtime version is 8 or lower, false otherwise.
     */
    public static boolean isJavaVersionOrNewer(int expectedVersion) {
        return getJavaRuntimeVersion() >= expectedVersion;
    }

    /**
     * Return the java version according to convention changes (starting with "1." or not).
     * 
     * @return The java version.
     */
    private static int getJavaRuntimeVersion() {
        String version = System.getProperty("java.version");
        if (version.startsWith("1.")) {
            version = version.substring(2, 3);
        } else {
            int dot = version.indexOf(".");
            if (dot != -1) {
                version = version.substring(0, dot);
            }
        }
        return Integer.parseInt(version);
    }
}
