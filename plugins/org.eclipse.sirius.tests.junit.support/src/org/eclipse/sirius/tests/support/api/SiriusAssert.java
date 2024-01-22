/**
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others
 * This program and the accompanying materials
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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.sirius.business.api.color.AbstractColorUpdater;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.tools.internal.preferences.SiriusDiagramInternalPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.junit.Assert;
import org.junit.ComparisonFailure;

import junit.framework.TestCase;

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
        if (Objects.equals(expected, actual)) {
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

    private static IFile getWorkspaceFile(final String wksPath) {
        IFile result = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(wksPath));
        // Refresh the file to synchronize the workspace.
        try {
            result.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
        } catch (CoreException e) {
            Assert.fail(e.getMessage());
        }
        return result;
    }
    
    /**
     * Assert that a file with the given absolute workspace path exists in the
     * workspace.
     * 
     * @param wksPath
     *            the file's path
     */
    public static void assertFileExists(final String wksPath) {
        Assert.assertTrue("The file \"" + wksPath + "\" does not exist.", //$NON-NLS-1$
                getWorkspaceFile(wksPath).exists());
    }

    /**
     * Assert that a file with the given absolute workspace path exists in the
     * workspace and the content equals the expected file.
     * <p>
     * Line endings are ignored.
     * </p>
     * 
     * @param expected 
     *            reference file
     * @param wksPath
     *            workspace path of the file to test
     * @param Charset
     *            charset of files.
     */
    public static void assertFileContent(final String bundleID, final String expected, final String wksPath, Charset cs) {
        IFile fileToTest = getWorkspaceFile(wksPath);
        String message = "Tested file \"" + wksPath  //$NON-NLS-1$
                + "\" does not match reference file \"" + expected + "\"."; //$NON-NLS-1$ //$NON-NLS-2$
        File expectedFile = FileProvider.getDefault().getFile(bundleID, new Path(expected));
        try {
            assertEquals(message, 
                    getFileContent(expectedFile.toPath(), cs), 
                    getFileContent(fileToTest.getRawLocation().toFile().toPath(), cs)
            );
        } catch (IOException e) {
            throw new AssertionError(message, e);
        }
    }
    
    private static String getFileContent(java.nio.file.Path file, Charset cs) throws IOException {
        return Files.lines(file, cs).collect(Collectors.joining("\n")); //$NON-NLS-1$
    }

    /**
     * Assert that a file with the given absolute workspace path exists in the
     * workspace and the content equals the expected file.
     * <p>
     * Line endings are ignored. Bytes from the file are decoded into characters
     * using the {@link StandardCharsets#UTF_8 UTF-8} {@link Charset charset}.
     * </p>
     * 
     * @param expected 
     *            reference file
     * @param wksPath
     *            workspace path of the file to test
     */
    public static void assertFileContent(final String bundleID, final String expected, final String wksPath) {
        assertFileContent(bundleID, expected, wksPath, StandardCharsets.UTF_8);
    }
    
    
    /**
     * Assert that there is no Sirius core preference changed in Sirius UI store.
     * 
     * @param preferenceKey
     *            The key of the preference to check
     */
    public static void assertNoSiriusCorePreferenceChangedinSiriusUIStore(String preferenceKey) {
        TestCase.assertFalse("The DesignerPreferenceKey named " + preferenceKey + " should not be modified in the UI store.",
                Arrays.asList(SiriusPreferencesKeys.values()).stream().map(SiriusPreferencesKeys::name).anyMatch(name -> Objects.equals(preferenceKey, name)));
    }

    /**
     * Assert that there is no Diagram core preference changed in Diagram UI store.
     * 
     * @param preferenceKey
     *            The key of the preference to check
     */
    public static void assertNoDiagramCorePreferenceChangedinDiagramUIStore(String preferenceKey) {
        Collection<String> coreKeys = new ArrayList<>();
        for (SiriusDiagramInternalPreferencesKeys key : SiriusDiagramInternalPreferencesKeys.values()) {
            coreKeys.add(key.name());
        }
        for (SiriusDiagramPreferencesKeys key : SiriusDiagramPreferencesKeys.values()) {
            coreKeys.add(key.name());
        }
        coreKeys.add(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE);
        coreKeys.add(SiriusDiagramCorePreferences.PREF_LINE_STYLE);
        assertFalse("The Diagram core preference named " + preferenceKey + " should not be modified in the Diagram UI store.", coreKeys.contains(preferenceKey));
    }

    /**
     * Assert that there is no Diagram UI preference changed in Diagram core store.
     * 
     * @param preferenceKey
     *            The key of the preference to check
     */
    public static void assertNoDiagramUIPreferenceChangedinDiagramCoreStore(String preferenceKey) {
        Collection<String> uiKeys = new ArrayList<>();
        for (SiriusDiagramUiInternalPreferencesKeys key : SiriusDiagramUiInternalPreferencesKeys.values()) {
            uiKeys.add(key.name());
        }
        for (SiriusDiagramUiPreferencesKeys key : SiriusDiagramUiPreferencesKeys.values()) {
            uiKeys.add(key.name());
        }
        assertFalse("The Diagram UI preference named " + preferenceKey + " should not be modified in the Diagram core store.", uiKeys.contains(preferenceKey));
    }
}
