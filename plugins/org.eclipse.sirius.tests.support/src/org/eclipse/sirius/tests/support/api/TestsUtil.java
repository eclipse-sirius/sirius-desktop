/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 *      Ketan Padegaonkar and others - the various waitUntil() methods come
 *        from org.eclipse.swtbot.swt.finder.SWTBotFactory
 */
package org.eclipse.sirius.tests.support.api;

import org.eclipse.core.runtime.Platform;
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

    /**
     * Constructor.
     */
    protected TestsUtil() {
        // Nothing
    }

    /**
     * Wait the end of the asynchronous calls in UI Thread and ignore the
     * Exception. <B>Use this exclusively</B> in the setup method to ensure a
     * clean environment.
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
     * Tests whether the environment is configured to skip non-critical tests
     * which take a long time. This possibility to skip some tests should only
     * be used on developer machines to get faster feedback, and never on a
     * continuous integration server.
     * 
     * @return <code>true</code> if the environment is setup to skip long tests.
     */
    public static boolean shouldSkipLongTests() {
        return "true".equals(System.getProperty("org.eclipse.sirius.tests.skipLongTests"));
    }

    /**
     * Tests whether unreliable tests should be run. See
     * {@link #shouldSkipUnreliableTests()}. Can be used in tests as:
     * 
     * <pre>
     * Assume.assumeTrue(TestUtil.shouldRunUnreliableTests())
     * </pre>
     * 
     * at the beginning of such tests.
     * 
     * @return <code>true</code> iff unreliable tests should be run.
     */
    public static boolean shouldRunUnreliableTests() {
        return !TestsUtil.shouldSkipUnreliableTests();
    }

    /**
     * Tests whether the environment is configured to skip tests which are known
     * to be unreliable (i.e. they sometimes work, sometimes fail).
     * 
     * @return <code>true</code> if the environment is setup to skip unreliable
     *         tests.
     */
    public static boolean shouldSkipUnreliableTests() {
        return "true".equals(System.getProperty("org.eclipse.sirius.tests.skipUnreliableTests"));
    }

    /**
     * Tests whether long running tests should be run. See
     * {@link #shouldSkipLongTests()}. Can be used in tests as:
     * 
     * <pre>
     * Assume.assumeTrue(TestUtil.shouldRunLongTests())
     * </pre>
     * 
     * at the beginning of such tests.
     * 
     * @return <code>true</code> iff long running tests should be run.
     */
    public static boolean shouldRunLongTests() {
        return !TestsUtil.shouldSkipLongTests();
    }

    /**
     * Tells if the current platform corresponds to juno3 (i.e. Eclipse 3.8).
     * 
     * @return true if the current platform corresponds to juno3 (i.e. Eclipse
     *         3.8), false else
     */
    public static boolean isJuno3Platform() {
        boolean isJuno3Platform = false;
        String platformVersion = Platform.getBundle("org.eclipse.core.runtime").getHeaders().get("Bundle-Version");
        if (platformVersion.startsWith("3.8")) {
            isJuno3Platform = true;
        }
        return isJuno3Platform;
    }

    /**
     * Tells if the current platform corresponds to Juno, Kepler, .. (i.e.
     * Eclipse 4.x).
     * 
     * @return true if the current platform corresponds to eclipse 4.x, false
     *         otherwise.
     */
    public static boolean isEclipse4xPlatform() {
        /*
         * Juno/Kepler Core Runtime plugins version are 3.8/3.9 and not 4.x. So
         * the "org.eclipse.ui.workbench" is used instead.
         */

        boolean isEclipse4Platform = false;
        Version junoStart = Version.parseVersion("3.103");
        Bundle uiWorkbenchBundle = Platform.getBundle("org.eclipse.ui.workbench");
        if (uiWorkbenchBundle != null && uiWorkbenchBundle.getVersion().compareTo(junoStart) >= 0) {
            isEclipse4Platform = true;
        }
        return isEclipse4Platform;
    }

    /**
     * Copied and adapted from
     * org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(ICondition, long,
     * long)
     * 
     * Waits until the condition has been meet, or the timeout is reached. The
     * interval is the delay between evaluating the condition after it has
     * failed.
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
     * Copied and adapted from
     * org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(ICondition, long,
     * long)
     * 
     * Waits until the condition has been meet, or the timeout is reached. The
     * interval is the delay between evaluating the condition after it has
     * failed.
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
     * Copied and adapted from
     * org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(ICondition, long,
     * long)
     * 
     * Waits until the condition has been meet, or the timeout is reached. The
     * interval is the delay between evaluating the condition after it has
     * failed.
     * 
     * Timeout: 5000 Interval : 500
     * 
     * @param condition
     *            the condition to be evaluated.
     */
    public static void waitUntil(ICondition condition) {
        TestsUtil.waitUntil(condition, 5000, 500);
    }
}
