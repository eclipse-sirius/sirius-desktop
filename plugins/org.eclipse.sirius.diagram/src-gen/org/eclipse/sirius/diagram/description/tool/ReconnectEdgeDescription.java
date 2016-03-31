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

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Reconnect Edge Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A tool that describes how to reconnect a ViewEdge.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getReconnectionKind
 * <em>Reconnection Kind</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getTarget
 * <em>Target</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getSourceView
 * <em>Source View</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getTargetView
 * <em>Target View</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getElement
 * <em>Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getEdgeView
 * <em>Edge View</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getReconnectEdgeDescription()
 * @model
 * @generated
 */
public interface ReconnectEdgeDescription extends MappingBasedToolDescription {
    /**
     * Returns the value of the '<em><b>Reconnection Kind</b></em>' attribute.
     * The default value is <code>"RECONNECT_TARGET"</code>. The literals are
     * from the enumeration
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectionKind}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The kind of reconnection : SOURCE : the source of the ViewEdge can be
     * reconnected but not the target. TARGET : the target of the ViewEdge can
     * be reconnected but not the source.
     *
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Reconnection Kind</em>' attribute.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectionKind
     * @see #setReconnectionKind(ReconnectionKind)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getReconnectEdgeDescription_ReconnectionKind()
     * @model default="RECONNECT_TARGET" required="true"
     * @generated
     */
    ReconnectionKind getReconnectionKind();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getReconnectionKind
     * <em>Reconnection Kind</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Reconnection Kind</em>' attribute.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectionKind
     * @see #getReconnectionKind()
     * @generated
     */
    void setReconnectionKind(ReconnectionKind value);

    /**
     * Returns the value of the '<em><b>Source</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The semantic element of the source view of the reconnection operation.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Source</em>' containment reference.
     * @see #setSource(SourceEdgeCreationVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getReconnectEdgeDescription_Source()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    SourceEdgeCreationVariable getSource();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getSource
     * <em>Source</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source</em>' containment reference.
     * @see #getSource()
     * @generated
     */
    void setSource(SourceEdgeCreationVariable value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The semantic element of the target view of the reconnection operation.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Target</em>' containment reference.
     * @see #setTarget(TargetEdgeCreationVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getReconnectEdgeDescription_Target()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    TargetEdgeCreationVariable getTarget();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getTarget
     * <em>Target</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target</em>' containment reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(TargetEdgeCreationVariable value);

    /**
     * Returns the value of the '<em><b>Source View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The source view of the reconnection operation. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Source View</em>' containment reference.
     * @see #setSourceView(SourceEdgeViewCreationVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getReconnectEdgeDescription_SourceView()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    SourceEdgeViewCreationVariable getSourceView();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getSourceView
     * <em>Source View</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source View</em>' containment
     *            reference.
     * @see #getSourceView()
     * @generated
     */
    void setSourceView(SourceEdgeViewCreationVariable value);

    /**
     * Returns the value of the '<em><b>Target View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The target view of the reconnection operation. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Target View</em>' containment reference.
     * @see #setTargetView(TargetEdgeViewCreationVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getReconnectEdgeDescription_TargetView()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    TargetEdgeViewCreationVariable getTargetView();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getTargetView
     * <em>Target View</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target View</em>' containment
     *            reference.
     * @see #getTargetView()
     * @generated
     */
    void setTargetView(TargetEdgeViewCreationVariable value);

    /**
     * Returns the value of the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The semantic element of the ViewEdge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Element</em>' containment reference.
     * @see #setElement(ElementSelectVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getReconnectEdgeDescription_Element()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ElementSelectVariable getElement();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getElement
     * <em>Element</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Element</em>' containment reference.
     * @see #getElement()
     * @generated
     */
    void setElement(ElementSelectVariable value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The first operation. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment
     *         reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getReconnectEdgeDescription_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment
     *            reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

    /**
     * Returns the value of the '<em><b>Edge View</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edge View</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Edge View</em>' containment reference.
     * @see #setEdgeView(ElementSelectVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getReconnectEdgeDescription_EdgeView()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ElementSelectVariable getEdgeView();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getEdgeView
     * <em>Edge View</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Edge View</em>' containment
     *            reference.
     * @see #getEdgeView()
     * @generated
     */
    void setEdgeView(ElementSelectVariable value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation" required="true"
     * @generated
     */
    EList<EdgeMapping> getMappings();

} // ReconnectEdgeDescription
