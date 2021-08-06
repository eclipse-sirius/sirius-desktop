/**
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.viewpoint;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>UI State</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> This abstraction is used to store transient UI informations. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.UIState#isInverseSelectionOrder <em>Inverse Selection Order</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.UIState#getElementsToSelect <em>Elements To Select</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.UIState#getDecorationImage <em>Decoration Image</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.UIState#getToolSections <em>Tool Sections</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.UIState#getSubDiagramDecorationDescriptors <em>Sub Diagram Decoration
 * Descriptors</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getUIState()
 * @model
 * @generated
 */
public interface UIState extends EObject {
    /**
     * Returns the value of the '<em><b>Inverse Selection Order</b></em>' attribute. The default value is
     * <code>"false"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Inverse Selection Order</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Inverse Selection Order</em>' attribute.
     * @see #setInverseSelectionOrder(boolean)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getUIState_InverseSelectionOrder()
     * @model default="false" transient="true"
     * @generated
     */
    boolean isInverseSelectionOrder();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.UIState#isInverseSelectionOrder <em>Inverse Selection
     * Order</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Inverse Selection Order</em>' attribute.
     * @see #isInverseSelectionOrder()
     * @generated
     */
    void setInverseSelectionOrder(boolean value);

    /**
     * Returns the value of the '<em><b>Elements To Select</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.emf.ecore.EObject}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Elements To Select</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Elements To Select</em>' reference list.
     * @see #isSetElementsToSelect()
     * @see #unsetElementsToSelect()
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getUIState_ElementsToSelect()
     * @model resolveProxies="false" unsettable="true" transient="true"
     * @generated
     */
    EList<EObject> getElementsToSelect();

    /**
     * Unsets the value of the '{@link org.eclipse.sirius.viewpoint.UIState#getElementsToSelect <em>Elements To
     * Select</em>}' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isSetElementsToSelect()
     * @see #getElementsToSelect()
     * @generated
     */
    void unsetElementsToSelect();

    /**
     * Returns whether the value of the '{@link org.eclipse.sirius.viewpoint.UIState#getElementsToSelect <em>Elements To
     * Select</em>}' reference list is set. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return whether the value of the '<em>Elements To Select</em>' reference list is set.
     * @see #unsetElementsToSelect()
     * @see #getElementsToSelect()
     * @generated
     */
    boolean isSetElementsToSelect();

    /**
     * Returns the value of the '<em><b>Image Decoration</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Image Decoration</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> This map associates a Decoration to its computed decoration(Object
     * as value) which can be either an Image or an IFigure. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Image Decoration</em>' attribute.
     * @see #setDecorationImage(Map)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getUIState_DecorationImage()
     * @model transient="true"
     * @generated
     */
    Map<Decoration, Object> getDecorationImage();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.UIState#getDecorationImage <em>Decoration Image</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Decoration Image</em>' attribute.
     * @see #getDecorationImage()
     * @generated
     */
    void setDecorationImage(Map<Decoration, Object> value);

    /**
     * Returns the value of the '<em><b>Tool Sections</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.ToolSectionInstance}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tool Sections</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Tool Sections</em>' reference list.
     * @see #isSetToolSections()
     * @see #unsetToolSections()
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getUIState_ToolSections()
     * @model resolveProxies="false" unsettable="true" transient="true"
     * @generated
     */
    EList<ToolSectionInstance> getToolSections();

    /**
     * Unsets the value of the '{@link org.eclipse.sirius.viewpoint.UIState#getToolSections <em>Tool Sections</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isSetToolSections()
     * @see #getToolSections()
     * @generated
     */
    void unsetToolSections();

    /**
     * Returns whether the value of the '{@link org.eclipse.sirius.viewpoint.UIState#getToolSections <em>Tool
     * Sections</em>}' reference list is set. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return whether the value of the '<em>Tool Sections</em>' reference list is set.
     * @see #unsetToolSections()
     * @see #getToolSections()
     * @generated
     */
    boolean isSetToolSections();

    /**
     * Returns the value of the '<em><b>Sub Diagram Decoration Descriptors</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> This map associates a Decoration to its computed image (Object as
     * value) which can be either an Image or an IFigure. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Sub Diagram Decoration Descriptors</em>' attribute.
     * @see #setSubDiagramDecorationDescriptors(Map)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getUIState_SubDiagramDecorationDescriptors()
     * @model transient="true"
     * @generated
     */
    Map<Object, Object> getSubDiagramDecorationDescriptors();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.UIState#getSubDiagramDecorationDescriptors <em>Sub
     * Diagram Decoration Descriptors</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Sub Diagram Decoration Descriptors</em>' attribute.
     * @see #getSubDiagramDecorationDescriptors()
     * @generated
     */
    void setSubDiagramDecorationDescriptors(Map<Object, Object> value);

} // UIState
