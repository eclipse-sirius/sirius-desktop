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
package org.eclipse.sirius.diagram.description.tool;

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Diagram Navigation Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription#getDiagramDescription
 * <em>Diagram Description</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDiagramNavigationDescription()
 * @model
 * @generated
 */
public interface DiagramNavigationDescription extends RepresentationNavigationDescription {
    /**
     * Returns the value of the '<em><b>Diagram Description</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Diagram Description</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Diagram Description</em>' reference.
     * @see #setDiagramDescription(DiagramDescription)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDiagramNavigationDescription_DiagramDescription()
     * @model required="true"
     * @generated
     */
    DiagramDescription getDiagramDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription#getDiagramDescription
     * <em>Diagram Description</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Diagram Description</em>' reference.
     * @see #getDiagramDescription()
     * @generated
     */
    void setDiagramDescription(DiagramDescription value);

} // DiagramNavigationDescription
