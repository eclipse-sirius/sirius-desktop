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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DNodeListElement;
import org.eclipse.sirius.viewpoint.NodeStyle;
import org.eclipse.sirius.viewpoint.description.style.NodeStyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Node Mapping</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Represents a node mapping. A node mapping allows to
 * create nodes (ViewNode). <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.NodeMapping#getStyle <em>
 * Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.NodeMapping#getConditionnalStyles
 * <em>Conditionnal Styles</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getNodeMapping()
 * @model
 * @generated
 */
public interface NodeMapping extends AbstractNodeMapping, DragAndDropTargetDescription {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The style of the node.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(NodeStyle)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getNodeMapping_Style()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    NodeStyleDescription getStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.NodeMapping#getStyle
     * <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(NodeStyleDescription value);

    /**
     * Returns the value of the '<em><b>Conditionnal Styles</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.ConditionalNodeStyleDescription}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditionnal Styles</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> All conditional styles.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Conditionnal Styles</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getNodeMapping_ConditionnalStyles()
     * @model type="viewpoint.description.ConditionalNodeStyleDescription"
     *        containment="true" resolveProxies="true"
     * @generated
     */
    EList<ConditionalNodeStyleDescription> getConditionnalStyles();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Create a node for the specified element.
     * 
     * @param modelElement
     *            The element.
     * @param container
     *            The container of the element. <!-- end-model-doc -->
     * @model
     * @generated
     */
    DNode createNode(EObject modelElement, EObject container, DDiagram viewPoint);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Update the node.
     * 
     * @param node
     *            The node to update. <!-- end-model-doc -->
     * @model
     * @generated
     */
    @Deprecated
    void updateNode(DNode node);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Retrieve the best style to use.
     * 
     * @param modelElement
     *            The model element.
     * @param viewVariable
     *            The view of the element. <!-- end-model-doc -->
     * @model
     * @generated
     */
    @Deprecated
    NodeStyle getBestStyle(EObject modelElement, EObject viewVariable, EObject containerVariable);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Create a list element. This method is equivalent of createNode but
     * returns a ViewNodeListElement.
     * 
     * @param modelElement
     *            The semantic model element. <!-- end-model-doc -->
     * @model
     * @generated
     */
    @Deprecated
    DNodeListElement createListElement(EObject modelElement, DDiagram diagram);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Update a DNodeListElement.
     * 
     * @param listElement
     *            The view node list element to update <!-- end-model-doc -->
     * @model
     * @generated
     */
    @Deprecated
    void updateListElement(DNodeListElement listElement);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return all semantic elements that are candidates for the mapping.
     * 
     * @param semanticOrigin
     *            The root element.
     * @param container
     *            The container element <!-- end-model-doc -->
     * @model type="org.eclipse.emf.ecore.EObject"
     * @generated
     */
    @Deprecated
    EList<EObject> getNodesCandidates(EObject semanticOrigin, EObject container);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return all semantic elements that are candidates for the mapping.
     * 
     * @param semanticOrigin
     *            The root element.
     * @param container
     *            The container of the element.
     * @param containerView
     *            The view of the container. <!-- end-model-doc -->
     * @model type="org.eclipse.emf.ecore.EObject"
     * @generated
     */
    @Deprecated
    EList<EObject> getNodesCandidates(EObject semanticOrigin, EObject container, EObject containerView);

} // NodeMapping
