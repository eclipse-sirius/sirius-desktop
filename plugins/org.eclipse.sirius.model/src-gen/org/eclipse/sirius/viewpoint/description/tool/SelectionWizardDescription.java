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

import org.eclipse.sirius.viewpoint.description.SelectionDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Selection Wizard Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getElement <em>Element</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getContainerView <em>Container
 * View</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getContainer
 * <em>Container</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getIconPath <em>Icon
 * Path</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getWindowTitle <em>Window
 * Title</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getWindowImagePath <em>Window
 * Image Path</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSelectionWizardDescription()
 * @model
 * @generated
 */
public interface SelectionWizardDescription extends AbstractToolDescription, SelectionDescription {
    /**
     * Returns the value of the '<em><b>Element</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Element</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Element</em>' containment reference.
     * @see #setElement(ElementSelectVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSelectionWizardDescription_Element()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ElementSelectVariable getElement();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getElement
     * <em>Element</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Element</em>' containment reference.
     * @see #getElement()
     * @generated
     */
    void setElement(ElementSelectVariable value);

    /**
     * Returns the value of the '<em><b>Container View</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Container View</em>' containment reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Container View</em>' containment reference.
     * @see #setContainerView(ContainerViewVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSelectionWizardDescription_ContainerView()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ContainerViewVariable getContainerView();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getContainerView <em>Container
     * View</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Container View</em>' containment reference.
     * @see #getContainerView()
     * @generated
     */
    void setContainerView(ContainerViewVariable value);

    /**
     * Returns the value of the '<em><b>Container</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Container</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Container</em>' containment reference.
     * @see #setContainer(SelectContainerVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSelectionWizardDescription_Container()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    SelectContainerVariable getContainer();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getContainer
     * <em>Container</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Container</em>' containment reference.
     * @see #getContainer()
     * @generated
     */
    void setContainer(SelectContainerVariable value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initial Operation</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSelectionWizardDescription_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getInitialOperation <em>Initial
     * Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

    /**
     * Returns the value of the '<em><b>Icon Path</b></em>' attribute. The default value is
     * <code>"/org.eclipse.sirius.ui/icons/full/obj16/SelectionWizardDescription.gif"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Icon Path</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Icon Path</em>' attribute.
     * @see #setIconPath(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSelectionWizardDescription_IconPath()
     * @model default="/org.eclipse.sirius.ui/icons/full/obj16/SelectionWizardDescription.gif"
     *        dataType="org.eclipse.sirius.viewpoint.description.ImagePath" required="true"
     * @generated
     */
    String getIconPath();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getIconPath <em>Icon Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Icon Path</em>' attribute.
     * @see #getIconPath()
     * @generated
     */
    void setIconPath(String value);

    /**
     * Returns the value of the '<em><b>Window Title</b></em>' attribute. The default value is
     * <code>"Selection Wizard"</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Title of
     * the dialog. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Window Title</em>' attribute.
     * @see #setWindowTitle(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSelectionWizardDescription_WindowTitle()
     * @model default="Selection Wizard" required="true"
     * @generated
     */
    String getWindowTitle();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getWindowTitle <em>Window
     * Title</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Window Title</em>' attribute.
     * @see #getWindowTitle()
     * @generated
     */
    void setWindowTitle(String value);

    /**
     * Returns the value of the '<em><b>Window Image Path</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Window Image Path</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Window Image Path</em>' attribute.
     * @see #setWindowImagePath(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSelectionWizardDescription_WindowImagePath()
     * @model dataType="org.eclipse.sirius.viewpoint.description.ImagePath"
     * @generated
     */
    String getWindowImagePath();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getWindowImagePath <em>Window
     * Image Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Window Image Path</em>' attribute.
     * @see #getWindowImagePath()
     * @generated
     */
    void setWindowImagePath(String value);

} // SelectionWizardDescription
