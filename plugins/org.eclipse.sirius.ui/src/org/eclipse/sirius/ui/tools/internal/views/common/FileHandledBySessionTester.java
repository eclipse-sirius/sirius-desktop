/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.ModelExplorerView;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;

/**
 * Property tester to check that an {@link IFile} is:
 * <UL>
 * <LI>in a modeling project,</LI>
 * <LI>or a session resource file,</LI>
 * <LI>or is an aird file loaded in a session</LI>
 * <LI>or the semantic resource of a transient session (no session file, the session data is in memory).</LI>
 * </UL>
 * 
 * @author mporhel
 */
public class FileHandledBySessionTester extends PropertyTester {
    /**
     * Property verifying that a given element in an IFile representing an aird that has a corresponding session
     * loading.
     */
    private static final String SESSION_FILE_PROPERTY = "isSessionFile"; //$NON-NLS-1$

    /**
     * Constructor.
     */
    public FileHandledBySessionTester() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (SESSION_FILE_PROPERTY.equals(property)) {
            return isSessionFile(receiver);
        } else {
            boolean result = false;
            if (receiver instanceof IFile) {
                IFile receiverFile = (IFile) receiver;

                // Modeling project should show expansion arrows during session load
                if (ModelingProject.hasModelingProjectNature(receiverFile.getProject())) {
                    result = true;
                } else {
                    result = new FileQuery(receiverFile.getFileExtension()).isSessionResourceFile() || !FileSessionFinder.getSelectedSessions(Collections.singletonList(receiverFile)).isEmpty();
                }
            }
            return result;
        }
    }

    /**
     * Returns true if the given element in an IFile representing a session file.
     * 
     * @param element
     *            element to test.
     * @return true if the given element in an IFile representing a session file. False otherwise.
     */
    private boolean isSessionFile(Object element) {
        boolean isSessionResourceFile = false;
        if (!ModelExplorerView.class.getTypeName().equals(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getClass().getTypeName()) && element instanceof IFile) {
            List<Session> relatedSessions = FileSessionFinder.getRelatedSessions(Lists.newArrayList((IFile) element), false, false);
            if (!relatedSessions.isEmpty()) {
                isSessionResourceFile = true;
            } else {
                isSessionResourceFile = new FileQuery((IFile) element).isSessionResourceFile();
            }
        }
        return isSessionResourceFile;
    }
}
