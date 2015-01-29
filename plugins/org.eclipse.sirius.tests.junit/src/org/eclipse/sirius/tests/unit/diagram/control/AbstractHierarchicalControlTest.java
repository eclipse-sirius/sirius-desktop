/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.control;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;

/**
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public abstract class AbstractHierarchicalControlTest extends AbstractControlTest {

    /**
     * Returns the {@link EPackage} with the given name, contained by the given
     * root.
     * 
     * @param name
     *            the name of the searched EPackage
     * @param root
     *            the root in which search the EPackage
     * @return the {@link EPackage} with the given name, contained by the given
     *         root, or null if no EPackage has been found
     */
    protected EObject findPackageNamed(final String name, final EObject root) {
        final TreeIterator<EObject> iter = root.eAllContents();
        while (iter.hasNext()) {
            final EObject current = iter.next();
            if (current instanceof EPackage && ((EPackage) current).getName() != null && ((EPackage) current).getName().equals(name)) {
                return current;
            }
        }
        return null;
    }

    /**
     * Ensures that each file located at the given paths does not exist.
     * 
     * @param wksPaths
     *            the paths of the file to check
     */
    protected void assertFilesDoNotExist(final String... wksPaths) {
        for (String path : wksPaths) {
            path = TEMPORARY_PROJECT_NAME + path;
            assertTrue("Workspace file " + path + " should not exist.", fileDoesNotExist(wksPaths));
        }
    }

    /**
     * Ensures that each file located at the given paths exists.
     * 
     * @param wksPaths
     *            the paths of the file to check
     */
    protected void assertFilesExist(final String... wksPaths) {
        for (String path : wksPaths) {
            path = TEMPORARY_PROJECT_NAME + path;
            assertTrue("Workspace file " + path + " should exist.", ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path)).exists());
        }
    }

    /**
     * Ensures that each file located at the given paths does not exist, waiting
     * for a short delay if the file still exists.
     * 
     * @param wksPaths
     *            the paths of the files to check
     * @return true if all files do not exist, false otherwise
     */
    protected boolean fileDoesNotExist(final String... wksPaths) {
        long startTime = System.currentTimeMillis();
        long delay = 5000;
        while (System.currentTimeMillis() - startTime < delay) {
            boolean fileDoesNotExists = true;
            for (String path : wksPaths) {
                path = TEMPORARY_PROJECT_NAME + path;
                fileDoesNotExists = fileDoesNotExists && !ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path)).exists();
            }
            if (fileDoesNotExists) {
                return true;
            }
        }
        return false;
    }

    /**
     * Copies the files located at the given paths into the test project.
     * 
     * @param filePaths
     *            the paths of the files to copy
     */
    protected void copyFilesToTestProject(String... filePaths) {
        EclipseTestsSupportHelper.INSTANCE.createProject(TEMPORARY_PROJECT_NAME);
        for (final String path : filePaths) {
            final String pluginPath = "/data/unit/control" + path;
            final String wksPath = TEMPORARY_PROJECT_NAME + path;
            EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, pluginPath, wksPath);
        }
    }

    /**
     * Opens a non controlled session.
     * 
     * @throws Exception
     */
    protected abstract void openNonControlledSession() throws Exception;

}
