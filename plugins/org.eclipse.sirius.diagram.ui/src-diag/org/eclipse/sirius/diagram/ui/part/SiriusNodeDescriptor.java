/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
