/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.description.DragAndDropTargetDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Drag And Drop Target</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A DragAndDropTarget is an element that can managed
 * drop requests. <!-- end-model-doc -->
 * 
 * 
 * @see org.eclipse.sirius.SiriusPackage#getDragAndDropTarget()
 * @model
 * @generated
 */
public interface DragAndDropTarget extends EObject {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return the description of this Drag&Drop Target. <!-- end-model-doc -->
     * 
     * @model kind="operation"
     * @generated
     */
    DragAndDropTargetDescription getDragAndDropDescription();
} // DragAndDropTarget
