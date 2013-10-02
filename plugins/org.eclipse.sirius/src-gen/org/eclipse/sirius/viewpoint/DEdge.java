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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.IEdgeMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>View Edge</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A view edge. It is a connection between two
 * EdgeTarget. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#getOwnedStyle <em>Owned Style
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#getSize <em>Size</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#getSourceNode <em>Source Node
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#getTargetNode <em>Target Node
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#getActualMapping <em>Actual
 * Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#getRoutingStyle <em>Routing
 * Style</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#isIsFold <em>Is Fold</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#isIsMockEdge <em>Is Mock Edge
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#getOriginalStyle <em>Original
 * Style</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#getPath <em>Path</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#getArrangeConstraints <em>
 * Arrange Constraints</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#getBeginLabel <em>Begin Label
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DEdge#getEndLabel <em>End Label</em>}
 * </li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDEdge()
 * @model
 * @generated
 */
public interface DEdge extends DDiagramElement, EdgeTarget {
    /**
     * Returns the value of the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Style</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The style of the
     * connection. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Owned Style</em>' containment reference.
     * @see #setOwnedStyle(EdgeStyle)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getViewEdge_OwnedStyle()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EdgeStyle getOwnedStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getOwnedStyle
     * <em>Owned Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Owned Style</em>' containment
     *            reference.
     * @see #getOwnedStyle()
     * @generated
     */
    void setOwnedStyle(EdgeStyle value);

    /**
     * Returns the value of the '<em><b>Size</b></em>' attribute. The default
     * value is <code>"1"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Size</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The line width. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Size</em>' attribute.
     * @see #setSize(Integer)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getViewEdge_Size()
     * @model default="1"
     * @generated
     */
    @Deprecated
    Integer getSize();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DEdge#getSize
     * <em>Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Size</em>' attribute.
     * @see #getSize()
     * @generated
     */
    @Deprecated
    void setSize(Integer value);

    /**
     * Returns the value of the '<em><b>Source Node</b></em>' reference. It is
     * bidirectional and its opposite is '
     * {@link org.eclipse.sirius.viewpoint.EdgeTarget#getOutgoingEdges
     * <em>Outgoing Edges</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Node</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The source of the
     * connection. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Source Node</em>' reference.
     * @see #setSourceNode(EdgeTarget)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getViewEdge_SourceNode()
     * @see org.eclipse.sirius.viewpoint.EdgeTarget#getOutgoingEdges
     * @model opposite="outgoingEdges" required="true"
     * @generated
     */
    EdgeTarget getSourceNode();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getSourceNode
     * <em>Source Node</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source Node</em>' reference.
     * @see #getSourceNode()
     * @generated
     */
    void setSourceNode(EdgeTarget value);

    /**
     * Returns the value of the '<em><b>Target Node</b></em>' reference. It is
     * bidirectional and its opposite is '
     * {@link org.eclipse.sirius.viewpoint.EdgeTarget#getIncomingEdges
     * <em>Incoming Edges</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Node</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The target of the
     * connection. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Target Node</em>' reference.
     * @see #setTargetNode(EdgeTarget)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getViewEdge_TargetNode()
     * @see org.eclipse.sirius.viewpoint.EdgeTarget#getIncomingEdges
     * @model opposite="incomingEdges" required="true"
     * @generated
     */
    EdgeTarget getTargetNode();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getTargetNode
     * <em>Target Node</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Node</em>' reference.
     * @see #getTargetNode()
     * @generated
     */
    void setTargetNode(EdgeTarget value);

    /**
     * Returns the value of the '<em><b>Origin Mapping</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Origin Mapping</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The mapping that has
     * created the view edge. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Origin Mapping</em>' reference.
     * @see #setActualMapping(EdgeMapping)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getViewEdge_OriginMapping()
     * @model required="true"
     * @generated
     */
    IEdgeMapping getActualMapping();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getActualMapping
     * <em>Actual Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Actual Mapping</em>' reference.
     * @see #getActualMapping()
     * @generated
     */
    void setActualMapping(IEdgeMapping value);

    /**
     * Returns the value of the '<em><b>Routing Style</b></em>' attribute. The
     * default value is <code>"straight"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.viewpoint.EdgeRouting}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Routing Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The routing style of the
     * edge. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Routing Style</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.EdgeRouting
     * @see #setRoutingStyle(EdgeRouting)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getViewEdge_RoutingStyle()
     * @model default="straight" required="true"
     * @generated
     */
    @Deprecated
    EdgeRouting getRoutingStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getRoutingStyle
     * <em>Routing Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Routing Style</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.EdgeRouting
     * @see #getRoutingStyle()
     * @generated
     */
    @Deprecated
    void setRoutingStyle(EdgeRouting value);

    /**
     * Returns the value of the '<em><b>Is Fold</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Fold</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Is Fold</em>' attribute.
     * @see #setIsFold(boolean)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getViewEdge_IsFold()
     * @model
     * @generated
     */
    @Deprecated
    boolean isIsFold();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DEdge#isIsFold <em>Is Fold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Is Fold</em>' attribute.
     * @see #isIsFold()
     * @generated
     */
    @Deprecated
    void setIsFold(boolean value);

    /**
     * Returns the value of the '<em><b>Is Mock Edge</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Mock Edge</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> <code>true</code> if the
     * edge is an edge that is displayed only to have the plus image to
     * decollapse a branch. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Is Mock Edge</em>' attribute.
     * @see #setIsMockEdge(boolean)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getViewEdge_IsMockEdge()
     * @model
     * @generated
     */
    @Deprecated
    boolean isIsMockEdge();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DEdge#isIsMockEdge
     * <em>Is Mock Edge</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Is Mock Edge</em>' attribute.
     * @see #isIsMockEdge()
     * @generated
     */
    @Deprecated
    void setIsMockEdge(boolean value);

    /**
     * Returns the value of the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * instance of style that is contained by the mapping. The ownedStyle
     * reference should be a copy of this style. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Original Style</em>' reference.
     * @see #setOriginalStyle(Style)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getViewEdge_OriginalStyle()
     * @model
     * @generated
     */
    Style getOriginalStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getOriginalStyle
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
     * Returns the value of the '<em><b>Path</b></em>' reference list. The list
     * contents are of type {@link org.eclipse.sirius.viewpoint.EdgeTarget}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Path</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Path</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDEdge_Path()
     * @model
     * @generated
     */
    EList<EdgeTarget> getPath();

    /**
     * Returns the value of the '<em><b>Arrange Constraints</b></em>' attribute
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.ArrangeConstraint}. The literals are
     * from the enumeration
     * {@link org.eclipse.sirius.viewpoint.ArrangeConstraint}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Arrange Constraints</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Arrange Constraints</em>' attribute list.
     * @see org.eclipse.sirius.viewpoint.ArrangeConstraint
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDEdge_ArrangeConstraints()
     * @model default=""
     * @generated
     */
    EList<ArrangeConstraint> getArrangeConstraints();

    /**
     * Returns the value of the '<em><b>Begin Label</b></em>' attribute. The
     * default value is <code>""</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The name of the representation.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Begin Label</em>' attribute.
     * @see #setBeginLabel(String)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDEdge_BeginLabel()
     * @model default=""
     * @generated
     */
    String getBeginLabel();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getBeginLabel
     * <em>Begin Label</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Begin Label</em>' attribute.
     * @see #getBeginLabel()
     * @generated
     */
    void setBeginLabel(String value);

    /**
     * Returns the value of the '<em><b>End Label</b></em>' attribute. The
     * default value is <code>""</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The name of the representation.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>End Label</em>' attribute.
     * @see #setEndLabel(String)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDEdge_EndLabel()
     * @model default=""
     * @generated
     */
    String getEndLabel();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getEndLabel <em>End Label</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>End Label</em>' attribute.
     * @see #getEndLabel()
     * @generated
     */
    void setEndLabel(String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * <code>true</code> if the edge is the root of a folded branch. <!--
     * end-model-doc -->
     * 
     * @model kind="operation"
     * @generated
     */
    @Deprecated
    boolean isRootFolding();

} // DEdge
