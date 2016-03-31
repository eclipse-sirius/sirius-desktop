/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Drag And Drop Target Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A DragAndDropTargetDescription is a Description or
 * Mapping that can have many DropTools <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DragAndDropTargetDescription#getDropDescriptions
 * <em>Drop Descriptions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDragAndDropTargetDescription()
 * @model abstract="true"
 * @generated
 */
public interface DragAndDropTargetDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Drop Descriptions</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Drop Descriptions</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Drop Descriptions</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDragAndDropTargetDescription_DropDescriptions()
     * @model
     * @generated
     */
    EList<ContainerDropDescription> getDropDescriptions();

} // DragAndDropTargetDescription
