/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;

/**
 * Test the behavior of {@link EclipseUtil}.
 * 
 * @author mporhel
 */
public class EclipseUtilTest extends SiriusTestCase {
    private static final String PATH = "/data/unit/file/";

    private static final String TEST_FILE_1 = "test_file.ecore";

    private static final String TEST_FILE_2 = "another_test_file.ecore";

    private static final String TEST_FILE_3 = "test_vsm.odesign";

    /**
     * {@inheritDoc}
     */
    public void setUp() throws Exception {
        super.createModelingProject = false;
        super.setUp();

        // .project, test_file.ecore, another_test_file.ecore, test_vsm.odesign
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, TEST_FILE_1, TEST_FILE_2, TEST_FILE_3);
    }

    /**
     * Test behavior with no prefix and no suffix specified.
     */
    public void testGetAllFilesFromWorkspace() {
        List<IFile> filesFromWorkspace = EclipseUtil.getFilesFromWorkspace(null, null);
        assertEquals(5, filesFromWorkspace.size());

        filesFromWorkspace = EclipseUtil.getFilesFromWorkspace("", "");
        assertEquals(5, filesFromWorkspace.size());
    }

    /**
     * Test behavior with a specified prefix.
     */
    public void testGetPrefixedFilesFromWorkspace() {
        List<IFile> filesFromWorkspace = EclipseUtil.getFilesFromWorkspace("test_", null);
        assertEquals(2, filesFromWorkspace.size());
    }

    /**
     * Test behavior with a specified suffix.
     */
    public void testGetSuffixedFilesFromWorkspace() {
        List<IFile> filesFromWorkspace = EclipseUtil.getFilesFromWorkspace(null, "ecore");
        assertEquals(2, filesFromWorkspace.size());
    }

    /**
     * Test behavior with specified prefix and suffix.
     */
    public void testGetPrefixedSuffixedFilesFromWorkspace() {
        List<IFile> filesFromWorkspace = EclipseUtil.getFilesFromWorkspace("test_", "ecore");
        assertEquals(1, filesFromWorkspace.size());
    }

    /**
     * {@inheritDoc}
     */
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
