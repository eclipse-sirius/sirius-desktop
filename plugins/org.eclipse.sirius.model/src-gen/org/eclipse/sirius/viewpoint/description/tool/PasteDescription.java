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
package org.eclipse.sirius.viewpoint.description.tool;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Paste Description</b></em>'. <!-- end-user-doc
 * -->
 *
 * <!-- begin-model-doc --> Tool that describes a paste operation. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getContainer <em>Container</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getContainerView <em>Container View</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getCopiedView <em>Copied View</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getCopiedElement <em>Copied Element</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPasteDescription()
 * @model
 * @generated
 */
public interface PasteDescription extends MappingBasedToolDescription {
    /**
     * Returns the value of the '<em><b>Container</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The semantic element of the new container view. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Container</em>' containment reference.
     * @see #setContainer(DropContainerVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPasteDescription_Container()
     * @model containment="true" resolveProxies="true" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='newSemanticContainer'"
     * @generated
     */
    DropContainerVariable getContainer();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getContainer
     * <em>Container</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Container</em>' containment reference.
     * @see #getContainer()
     * @generated
     */
    void setContainer(DropContainerVariable value);

    /**
     * Returns the value of the '<em><b>Container View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The new view container (DRepresentation of DRepresentationElement).
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Container View</em>' containment reference.
     * @see #setContainerView(ContainerViewVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPasteDescription_ContainerView()
     * @model containment="true" resolveProxies="true" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='newContainerView'"
     * @generated
     */
    ContainerViewVariable getContainerView();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getContainerView
     * <em>Container View</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Container View</em>' containment reference.
     * @see #getContainerView()
     * @generated
     */
    void setContainerView(ContainerViewVariable value);

    /**
     * Returns the value of the '<em><b>Copied View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The copied view. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Copied View</em>' containment reference.
     * @see #setCopiedView(ElementViewVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPasteDescription_CopiedView()
     * @model containment="true" resolveProxies="true" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='oldSemanticElement'"
     * @generated
     */
    ElementViewVariable getCopiedView();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getCopiedView
     * <em>Copied View</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Copied View</em>' containment reference.
     * @see #getCopiedView()
     * @generated
     */
    void setCopiedView(ElementViewVariable value);

    /**
     * Returns the value of the '<em><b>Copied Element</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The copied semantic element. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Copied Element</em>' containment reference.
     * @see #setCopiedElement(ElementVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPasteDescription_CopiedElement()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ElementVariable getCopiedElement();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getCopiedElement
     * <em>Copied Element</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Copied Element</em>' containment reference.
     * @see #getCopiedElement()
     * @generated
     */
    void setCopiedElement(ElementVariable value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The first operation. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPasteDescription_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    EList<PasteTargetDescription> getContainers();

} // PasteDescription
