/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.query.IFileQuery;

/**
 * Property tester to check that an {@link IFile} is handled by an opened
 * session, ie:
 * <UL>
 * <LI>a semantic resource of this session,</LI>
 * <LI>a referenced sub representations file,</LI>
 * <LI>a controlled resource.</LI>
 * </UL>
 * 
 * @author lredor
 */
public class FileHandledByOpenedSessionTester extends PropertyTester {

    /**
     * Constructor.
     */
    public FileHandledByOpenedSessionTester() {
    }

    /**
     * {@inheritDoc}
     */
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        boolean result = false;
        if (receiver instanceof IFile) {
            IFile file = (IFile) receiver;
            result = new IFileQuery(file).isResourceHandledByOpenedSession();
        }
        return result;
    }
}
