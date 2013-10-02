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
package org.eclipse.sirius.viewpoint.description.tool;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Delete Element Description</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Tool that describes how to delete a
 * ViewPointElement. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getElement
 * <em>Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getElementView
 * <em>Element View</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getContainerView
 * <em>Container View</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getHook
 * <em>Hook</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getDeleteElementDescription()
 * @model
 * @generated
 */
public interface DeleteElementDescription extends MappingBasedToolDescription {
    /**
     * Returns the value of the '<em><b>Mappings</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping}.
     * It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getDeletionDescription
     * <em>Deletion Description</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mappings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The mapping that create
     * elements that are deleted by this tool. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Mappings</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getDeleteElementDescription_Mappings()
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getDeletionDescription
     * @model type="viewpoint.description.DiagramElementMapping"
     *        opposite="deletionDescription" required="true"
     * @generated
     */
    EList<DiagramElementMapping> getMappings();

    /**
     * Returns the value of the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Element</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The semantic element of
     * the DDiagramElement to delete. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Element</em>' containment reference.
     * @see #setElement(ElementDeleteVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getDeleteElementDescription_Element()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ElementDeleteVariable getElement();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getElement
     * <em>Element</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Element</em>' containment reference.
     * @see #getElement()
     * @generated
     */
    void setElement(ElementDeleteVariable value);

    /**
     * Returns the value of the '<em><b>Element View</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Element View</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Element View</em>' containment reference.
     * @see #setElementView(ElementDeleteVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getDeleteElementDescription_ElementView()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    ElementDeleteVariable getElementView();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getElementView
     * <em>Element View</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Element View</em>' containment
     *            reference.
     * @see #getElementView()
     * @generated
     */
    void setElementView(ElementDeleteVariable value);

    /**
     * Returns the value of the '<em><b>Container View</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Container View</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The view that contains the
     * DDiagramElement to delete. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Container View</em>' containment reference.
     * @see #setContainerView(ContainerViewVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getDeleteElementDescription_ContainerView()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ContainerViewVariable getContainerView();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getContainerView
     * <em>Container View</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Container View</em>' containment
     *            reference.
     * @see #getContainerView()
     * @generated
     */
    void setContainerView(ContainerViewVariable value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initial Operation</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The first operation. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Initial Operation</em>' containment
     *         reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getDeleteElementDescription_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getInitialOperation
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
     * Returns the value of the '<em><b>Hook</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Hook</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Hook</em>' containment reference.
     * @see #setHook(DeleteHook)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getDeleteElementDescription_Hook()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    DeleteHook getHook();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getHook
     * <em>Hook</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Hook</em>' containment reference.
     * @see #getHook()
     * @generated
     */
    void setHook(DeleteHook value);

} // DeleteElementDescription
