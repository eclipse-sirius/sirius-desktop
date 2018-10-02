/******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.emf.ui.services.action.AbstractModelActionFilterProvider;

/**
 * An action filter provider for the Diagram UI Actions plugin.
 * 
 * @author cmahoney
 * @canBeSeenBy org.eclipse.gmf.runtime.diagram.ui.actions.*
 */
public class SiriusActionFilterProvider extends AbstractModelActionFilterProvider {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.ui.services.action.AbstractModelActionFilterProvider#doTestAttribute(java.lang.Object,
     *      java.lang.String, java.lang.String)
     */
    @Override
    protected boolean doTestAttribute(Object target, String name, String value) {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.ui.services.action.AbstractModelActionFilterProvider#doProvides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    @Override
    protected boolean doProvides(IOperation operation) {
        return true;
    }

}
