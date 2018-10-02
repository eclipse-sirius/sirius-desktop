/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ecore.design.service;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreEditorPlugin;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class DesignServices {

    /**
     * Shows the Properties View. (See Double Click Action in Design Sirius)
     * 
     * @param object
     *            Any EObject
     */
    public void showPropertiesViewAction(EObject object) {
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.ui.views.PropertySheet");
        } catch (PartInitException exception) {
            EcoreEditorPlugin.INSTANCE.log(exception);
        }
    }
}
