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
package org.eclipse.sirius.ui.business.api.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.action.AbstractExternalJavaAction;
import org.eclipse.ui.PlatformUI;

/**
 * An external Java action which opens the Eclipse Helps system at a section
 * specified as a parameter.
 * <p>
 * Action parameters
 * <ul>
 * <li><code>href</code>: the URL of the help resource.</li>
 * </ul>
 * 
 * @author pcdavid
 */
public class OpenHelpSectionAction extends AbstractExternalJavaAction {
    /**
     * {@inheritDoc}
     */
    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        String href = getParameter(parameters, "href", String.class); //$NON-NLS-1$
        PlatformUI.getWorkbench().getHelpSystem().displayHelpResource(href);
    }
}
