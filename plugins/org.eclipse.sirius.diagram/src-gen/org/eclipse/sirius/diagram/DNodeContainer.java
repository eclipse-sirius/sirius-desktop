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
package org.eclipse.sirius.diagram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DNode Container</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A classic container. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.DNodeContainer#getOwnedDiagramElements
 * <em>Owned Diagram Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DNodeContainer#getChildrenPresentation
 * <em>Children Presentation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getDNodeContainer()
 * @model
 * @generated
 */
public interface DNodeContainer extends DDiagramElementContainer {
    /**
     * Returns the value of the '<em><b>Owned Diagram Elements</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.DDiagramElement}. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> elements owned by this
     * container. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Owned Diagram Elements</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDNodeContainer_OwnedDiagramElements()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<DDiagramElement> getOwnedDiagramElements();

    /**
     * Returns the value of the '<em><b>Children Presentation</b></em>'
     * attribute. The default value is <code>"FreeForm"</code>. The literals are
     * from the enumeration {@link org.eclipse.sirius.diagram.ContainerLayout}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children Presentation</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Children Presentation</em>' attribute.
     * @see org.eclipse.sirius.diagram.ContainerLayout
     * @see #setChildrenPresentation(ContainerLayout)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDNodeContainer_ChildrenPresentation()
     * @model default="FreeForm" required="true"
     * @generated
     */
    ContainerLayout getChildrenPresentation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DNodeContainer#getChildrenPresentation
     * <em>Children Presentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Children Presentation</em>'
     *            attribute.
     * @see org.eclipse.sirius.diagram.ContainerLayout
     * @see #getChildrenPresentation()
     * @generated
     */
    void setChildrenPresentation(ContainerLayout value);

} // DNodeContainer
