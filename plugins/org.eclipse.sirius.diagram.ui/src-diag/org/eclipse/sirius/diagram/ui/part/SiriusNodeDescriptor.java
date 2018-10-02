/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.part;

import org.eclipse.emf.ecore.EObject;

/**
 * @was-generated
 */
public class SiriusNodeDescriptor {

    /**
     * @was-generated
     */
    private EObject myModelElement;

    /**
     * @was-generated
     */
    private int myVisualID;

    /**
     * @was-generated
     */
    private String myType;

    /**
     * @was-generated
     */
    public SiriusNodeDescriptor(EObject modelElement, int visualID) {
        myModelElement = modelElement;
        myVisualID = visualID;
    }

    /**
     * @was-generated
     */
    public EObject getModelElement() {
        return myModelElement;
    }

    /**
     * @was-generated
     */
    public int getVisualID() {
        return myVisualID;
    }

    /**
     * @was-generated
     */
    public String getType() {
        if (myType == null) {
            myType = SiriusVisualIDRegistry.getType(getVisualID());
        }
        return myType;
    }

}
