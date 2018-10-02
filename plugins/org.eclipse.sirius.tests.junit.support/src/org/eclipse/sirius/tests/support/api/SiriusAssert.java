/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.sirius.business.api.color.AbstractColorUpdater;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.junit.Assert;
import org.junit.ComparisonFailure;

import com.google.common.base.Objects;

/**
 * Designer assertions for Junit.
 * 
 * @author dlecan
 */
public class SiriusAssert extends Assert {

    /**
     * Assert not equality.
     * 
     * @param message
     *            Message
     * @param expected
     *            Expected object
     * @param actual
     *            Actual objet
     */
    public static void assertNotEquals(final String message, final Object expected, final Object actual) {
        if (Objects.equal(expected, actual)) {
            SiriusAssert.failEquals(message, expected, actual);
        }
    }

    /**
     * Assert not equality.
     * 
     * @param expected
     *            Expected object
     * @param actual
     *            Actual objet
     */
    public static void assertNotEquals(final Object expected, final Object actual) {
        SiriusAssert.assertNotEquals(null, expected, actual);
    }

    /**
     * Assert not equality.
     * 
     * @param message
     *            Message
     * @param expected
     *            Expected object
     * @param actual
     *            Actual objet
     */
    public static void failEquals(final String message, final Object expected, final Object actual) {
        throw new ComparisonFailure(message, String.valueOf(expected), String.valueOf(actual));
    }

    /**
     * Assert both values are equals (same RGB color values).
     * 
     * @param message
     *            the error message
     * @param expected
     *            the first value.
     * @param actual
     *            the second value.
     */
    public static void assertSameRGB(final String message, final RGBValues expected, final RGBValues actual) {
        if (!AbstractColorUpdater.areEquals(expected, actual)) {
            throw new ComparisonFailure(message, String.valueOf(expected), String.valueOf(actual));
        }
    }

    /**
     * Assert that the file with the given absolute Path exists in the
     * workspace.
     * 
     * @param wksPath
     *            the file's path
     */
    public static void assertFileExists(final String wksPath) {
        IFile fileToTest = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(wksPath));
        // Refresh the file to synchronize the workspace.
        try {
            fileToTest.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
        } catch (CoreException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue("The file \"" + wksPath + "\" does not exist.", fileToTest.exists());
    }

}
