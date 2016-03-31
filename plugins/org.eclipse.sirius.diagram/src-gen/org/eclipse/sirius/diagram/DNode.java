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
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.Style;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DNode</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A node. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.DNode#getWidth <em>Width</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DNode#getHeight <em>Height</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DNode#getOwnedStyle
 * <em>Owned Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DNode#getLabelPosition
 * <em>Label Position</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DNode#getResizeKind
 * <em>Resize Kind</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DNode#getOriginalStyle
 * <em>Original Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DNode#getActualMapping
 * <em>Actual Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DNode#getCandidatesMapping
 * <em>Candidates Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getDNode()
 * @model
 * @generated
 */
public interface DNode extends AbstractDNode, EdgeTarget, DragAndDropTarget {
    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Width</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(Integer)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDNode_Width()
     * @model
     * @generated
     */
    Integer getWidth();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.DNode#getWidth
     * <em>Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(Integer value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Height</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(Integer)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDNode_Height()
     * @model
     * @generated
     */
    Integer getHeight();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.DNode#getHeight
     * <em>Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(Integer value);

    /**
     * Returns the value of the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The style of the node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Owned Style</em>' containment reference.
     * @see #setOwnedStyle(NodeStyle)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDNode_OwnedStyle()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    NodeStyle getOwnedStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DNode#getOwnedStyle
     * <em>Owned Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Owned Style</em>' containment
     *            reference.
     * @see #getOwnedStyle()
     * @generated
     */
    void setOwnedStyle(NodeStyle value);

    /**
     * Returns the value of the '<em><b>Label Position</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.eclipse.sirius.diagram.LabelPosition}. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The position of the label
     * : BORDER : The label is around the node, on the border. NODE : the label
     * is in the node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Position</em>' attribute.
     * @see org.eclipse.sirius.diagram.LabelPosition
     * @see #setLabelPosition(LabelPosition)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDNode_LabelPosition()
     * @model
     * @generated
     */
    LabelPosition getLabelPosition();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DNode#getLabelPosition
     * <em>Label Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Position</em>' attribute.
     * @see org.eclipse.sirius.diagram.LabelPosition
     * @see #getLabelPosition()
     * @generated
     */
    void setLabelPosition(LabelPosition value);

    /**
     * Returns the value of the '<em><b>Resize Kind</b></em>' attribute. The
     * default value is <code>"NONE"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.diagram.ResizeKind}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * <code>true</code> if the node is resizable. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Resize Kind</em>' attribute.
     * @see org.eclipse.sirius.diagram.ResizeKind
     * @see #setResizeKind(ResizeKind)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDNode_ResizeKind()
     * @model default="NONE"
     * @generated
     */
    ResizeKind getResizeKind();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DNode#getResizeKind
     * <em>Resize Kind</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Resize Kind</em>' attribute.
     * @see org.eclipse.sirius.diagram.ResizeKind
     * @see #getResizeKind()
     * @generated
     */
    void setResizeKind(ResizeKind value);

    /**
     * Returns the value of the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * instance of style that is contained by the mapping. The ownedStyle
     * reference should be a copy of this style. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Original Style</em>' reference.
     * @see #setOriginalStyle(Style)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDNode_OriginalStyle()
     * @model
     * @generated
     */
    Style getOriginalStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DNode#getOriginalStyle
     * <em>Original Style</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Original Style</em>' reference.
     * @see #getOriginalStyle()
     * @generated
     */
    void setOriginalStyle(Style value);

    /**
     * Returns the value of the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * actual mapping of this node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Actual Mapping</em>' reference.
     * @see #setActualMapping(NodeMapping)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDNode_ActualMapping()
     * @model required="true"
     * @generated
     */
    NodeMapping getActualMapping();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DNode#getActualMapping
     * <em>Actual Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Actual Mapping</em>' reference.
     * @see #getActualMapping()
     * @generated
     */
    void setActualMapping(NodeMapping value);

    /**
     * Returns the value of the '<em><b>Candidates Mapping</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.NodeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * candidates mapping of this node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Candidates Mapping</em>' reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDNode_CandidatesMapping()
     * @model annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel deprecated='This element should not be used'"
     * @generated
     */
    EList<NodeMapping> getCandidatesMapping();

} // DNode
