/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.spec;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.sirius.viewpoint.impl.DSourceFileLinkImpl;

/**
 * Implementation of DSourceFileLinkImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public class DSourceFileLinkSpec extends DSourceFileLinkImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DSourceFileLinkImpl#isAvailable()
     */
    @Override
    public boolean isAvailable() {
        if (getFilePath() != null) {
            final Object file = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(getFilePath()));
            if (file instanceof IFile) {
                final IFile iFile = (IFile) file;
                return iFile.isAccessible() && iFile.exists();
            }
        }
        return false;
    }

}
