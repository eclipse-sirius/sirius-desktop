/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Node Mapping</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Represents a node mapping. A node mapping allows to create nodes (ViewNode). <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.NodeMapping#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.NodeMapping#getConditionnalStyles <em>Conditionnal
 * Styles</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getNodeMapping()
 * @model
 * @generated
 */
public interface NodeMapping extends AbstractNodeMapping, DragAndDropTargetDescription {
    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> The style of the node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(NodeStyleDescription)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getNodeMapping_Style()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    NodeStyleDescription getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.description.NodeMapping#getStyle <em>Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(NodeStyleDescription value);

    /**
     * Returns the value of the '<em><b>Conditionnal Styles</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription} . <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> All conditional styles. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Conditionnal Styles</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getNodeMapping_ConditionnalStyles()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ConditionalNodeStyleDescription> getConditionnalStyles();

} // NodeMapping
