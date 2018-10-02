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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Pane Based Selection Wizard
 * Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getElement
 * <em>Element</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getContainerView
 * <em>Container View</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getContainer
 * <em>Container</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getIconPath <em>Icon
 * Path</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getWindowTitle
 * <em>Window Title</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getWindowImagePath
 * <em>Window Image Path</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getMessage
 * <em>Message</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getChoiceOfValuesMessage
 * <em>Choice Of Values Message</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getCandidatesExpression
 * <em>Candidates Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#isTree
 * <em>Tree</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getRootExpression
 * <em>Root Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getChildrenExpression
 * <em>Children Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getSelectedValuesMessage
 * <em>Selected Values Message</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getPreSelectedCandidatesExpression
 * <em>Pre Selected Candidates Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription()
 * @model
 * @generated
 */
public interface PaneBasedSelectionWizardDescription extends AbstractToolDescription {
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
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_Element()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ElementSelectVariable getElement();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getElement
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
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_ContainerView()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ContainerViewVariable getContainerView();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getContainerView
     * <em>Container View</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_Container()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    SelectContainerVariable getContainer();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getContainer
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
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

    /**
     * Returns the value of the '<em><b>Icon Path</b></em>' attribute. The default value is
     * <code>"/org.eclipse.sirius.ui/icons/full/obj16/PaneBasedSelectionWizardDescription.gif"</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Icon Path</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Icon Path</em>' attribute.
     * @see #setIconPath(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_IconPath()
     * @model default="/org.eclipse.sirius.ui/icons/full/obj16/PaneBasedSelectionWizardDescription.gif"
     *        dataType="org.eclipse.sirius.viewpoint.description.ImagePath" required="true"
     * @generated
     */
    String getIconPath();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getIconPath <em>Icon
     * Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_WindowTitle()
     * @model default="Selection Wizard" required="true"
     * @generated
     */
    String getWindowTitle();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getWindowTitle
     * <em>Window Title</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_WindowImagePath()
     * @model dataType="org.eclipse.sirius.viewpoint.description.ImagePath"
     * @generated
     */
    String getWindowImagePath();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getWindowImagePath
     * <em>Window Image Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Window Image Path</em>' attribute.
     * @see #getWindowImagePath()
     * @generated
     */
    void setWindowImagePath(String value);

    /**
     * Returns the value of the '<em><b>Message</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Message</em>' attribute.
     * @see #setMessage(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_Message()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TranslatableMessage"
     * @generated
     */
    String getMessage();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getMessage
     * <em>Message</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Message</em>' attribute.
     * @see #getMessage()
     * @generated
     */
    void setMessage(String value);

    /**
     * Returns the value of the '<em><b>Choice Of Values Message</b></em>' attribute. The default value is
     * <code>"Choice of values"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Choice Of Values Message</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Choice Of Values Message</em>' attribute.
     * @see #setChoiceOfValuesMessage(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_ChoiceOfValuesMessage()
     * @model default="Choice of values" dataType="org.eclipse.sirius.viewpoint.description.TranslatableMessage"
     * @generated
     */
    String getChoiceOfValuesMessage();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getChoiceOfValuesMessage
     * <em>Choice Of Values Message</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Choice Of Values Message</em>' attribute.
     * @see #getChoiceOfValuesMessage()
     * @generated
     */
    void setChoiceOfValuesMessage(String value);

    /**
     * Returns the value of the '<em><b>Candidates Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Candidates Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Candidates Expression</em>' attribute.
     * @see #setCandidatesExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_CandidatesExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables
     *        diagram='diagram.DSemanticDiagram | the current DDiagram.' containerView='viewpoint.DSemanticDecorator |
     *        the selected view.' container='ecore.EObject | the semantic element of containerView.'"
     * @generated
     */
    String getCandidatesExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getCandidatesExpression
     * <em>Candidates Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Candidates Expression</em>' attribute.
     * @see #getCandidatesExpression()
     * @generated
     */
    void setCandidatesExpression(String value);

    /**
     * Returns the value of the '<em><b>Tree</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Set to true if you want a tree representation of the selection candidates. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Tree</em>' attribute.
     * @see #setTree(boolean)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_Tree()
     * @model required="true"
     * @generated
     */
    boolean isTree();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#isTree <em>Tree</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Tree</em>' attribute.
     * @see #isTree()
     * @generated
     */
    void setTree(boolean value);

    /**
     * Returns the value of the '<em><b>Root Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Root Expression</em>' attribute.
     * @see #setRootExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_RootExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables
     *        diagram='diagram.DSemanticDiagram | the current DDiagram.' containerView='viewpoint.DSemanticDecorator |
     *        the selected view.' container='ecore.EObject | the semantic element of containerView.'"
     * @generated
     */
    String getRootExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getRootExpression
     * <em>Root Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Root Expression</em>' attribute.
     * @see #getRootExpression()
     * @generated
     */
    void setRootExpression(String value);

    /**
     * Returns the value of the '<em><b>Children Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Children Expression</em>' attribute.
     * @see #setChildrenExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_ChildrenExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables
     *        diagram='diagram.DSemanticDiagram | the current DDiagram.' containerView='viewpoint.DSemanticDecorator |
     *        the selected view.' container='ecore.EObject | the semantic element of containerView.'"
     * @generated
     */
    String getChildrenExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getChildrenExpression
     * <em>Children Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Children Expression</em>' attribute.
     * @see #getChildrenExpression()
     * @generated
     */
    void setChildrenExpression(String value);

    /**
     * Returns the value of the '<em><b>Selected Values Message</b></em>' attribute. The default value is
     * <code>"Selected values"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Selected Values Message</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Selected Values Message</em>' attribute.
     * @see #setSelectedValuesMessage(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_SelectedValuesMessage()
     * @model default="Selected values" dataType="org.eclipse.sirius.viewpoint.description.TranslatableMessage"
     * @generated
     */
    String getSelectedValuesMessage();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getSelectedValuesMessage
     * <em>Selected Values Message</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Selected Values Message</em>' attribute.
     * @see #getSelectedValuesMessage()
     * @generated
     */
    void setSelectedValuesMessage(String value);

    /**
     * Returns the value of the '<em><b>Pre Selected Candidates Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pre Selected Candidates Expression</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Pre Selected Candidates Expression</em>' attribute.
     * @see #setPreSelectedCandidatesExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables
     *        diagram='diagram.DSemanticDiagram | the current DDiagram.' containerView='viewpoint.DSemanticDecorator |
     *        the selected view.' container='ecore.EObject | the semantic element of containerView.'"
     * @generated
     */
    String getPreSelectedCandidatesExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getPreSelectedCandidatesExpression
     * <em>Pre Selected Candidates Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Pre Selected Candidates Expression</em>' attribute.
     * @see #getPreSelectedCandidatesExpression()
     * @generated
     */
    void setPreSelectedCandidatesExpression(String value);

} // PaneBasedSelectionWizardDescription
