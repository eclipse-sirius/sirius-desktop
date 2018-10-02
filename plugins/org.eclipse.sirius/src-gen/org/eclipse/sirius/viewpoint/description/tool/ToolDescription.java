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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getIconPath <em>Icon Path</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getElement <em>Element</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getElementView <em>Element View</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getToolDescription()
 * @model
 * @generated
 */
public interface ToolDescription extends MappingBasedToolDescription {
    /**
     * Returns the value of the '<em><b>Icon Path</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The path of the icon to display in the palette.
     * If unset, the icon corresponding to the semantic element associated with the mapping will be displayed. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Icon Path</em>' attribute.
     * @see #setIconPath(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getToolDescription_IconPath()
     * @model default=""
     * @generated
     */
    String getIconPath();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getIconPath <em>Icon
     * Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Icon Path</em>' attribute.
     * @see #getIconPath()
     * @generated
     */
    void setIconPath(String value);

    /**
     * Returns the value of the '<em><b>Element</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The variable container that represents the semantic element of the
     * clicked view. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Element</em>' containment reference.
     * @see #setElement(ElementVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getToolDescription_Element()
     * @model containment="true" resolveProxies="true" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='element'" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables type='ecore.EObject'"
     * @generated
     */
    ElementVariable getElement();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getElement
     * <em>Element</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Element</em>' containment reference.
     * @see #getElement()
     * @generated
     */
    void setElement(ElementVariable value);

    /**
     * Returns the value of the '<em><b>Element View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The variable that represents the clicked view. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Element View</em>' containment reference.
     * @see #setElementView(ElementViewVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getToolDescription_ElementView()
     * @model containment="true" resolveProxies="true" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='elementView'" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables type='ecore.EObject'"
     * @generated
     */
    ElementViewVariable getElementView();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getElementView
     * <em>Element View</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Element View</em>' containment reference.
     * @see #getElementView()
     * @generated
     */
    void setElementView(ElementViewVariable value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The first operation to execute. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getToolDescription_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

} // ToolDescription
