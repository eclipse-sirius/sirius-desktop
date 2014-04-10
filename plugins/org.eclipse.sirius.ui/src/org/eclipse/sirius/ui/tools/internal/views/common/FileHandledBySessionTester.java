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

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.FileQuery;

/**
 * Property tester to check that an {@link IFile} is:
 * <UL>
 * <LI>in a modeling project,</LI>
 * <LI>or a session resource file,</LI>
 * <LI>or the semantic resource of a transient session (no session file, the
 * session data is in memory).</LI>
 * </UL>
 * 
 * @author mporhel
 */
public class FileHandledBySessionTester extends PropertyTester {

    /**
     * Constructor.
     */
    public FileHandledBySessionTester() {
    }

    /**
     * {@inheritDoc}
     */
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
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
