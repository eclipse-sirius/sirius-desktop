/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.synchronizer.Maybe;

public class SpecifierMonitor {

    /**
     * Warns user about the given exception.
     * 
     * @param string
     *            the message to use for warning
     * @param e
     *            the exception that occurred
     * @param specificationAttachment
     *            the specification attachment
     */
    public void warning(String string, Exception e, Maybe<? extends EObject> specificationAttachment) {
        // TODO Auto-generated method stub

    }

}
