/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.template;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>TExecution Mapping</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getStartingEndFinderExpression <em>Starting
 * End Finder Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getFinishingEndFinderExpression
 * <em>Finishing End Finder Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#isRecursive <em>Recursive</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getExecutionMappings <em>Execution
 * Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTExecutionMapping()
 * @model
 * @generated
 */
public interface TExecutionMapping extends TAbstractMapping, TMessageExtremity {
    /**
     * Returns the value of the '<em><b>Starting End Finder Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Starting End Finder Expression</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Starting End Finder Expression</em>' attribute.
     * @see #setStartingEndFinderExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTExecutionMapping_StartingEndFinderExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getStartingEndFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getStartingEndFinderExpression
     * <em>Starting End Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Starting End Finder Expression</em>' attribute.
     * @see #getStartingEndFinderExpression()
     * @generated
     */
    void setStartingEndFinderExpression(String value);

    /**
     * Returns the value of the '<em><b>Finishing End Finder Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Finishing End Finder Expression</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Finishing End Finder Expression</em>' attribute.
     * @see #setFinishingEndFinderExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTExecutionMapping_FinishingEndFinderExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getFinishingEndFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getFinishingEndFinderExpression
     * <em>Finishing End Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Finishing End Finder Expression</em>' attribute.
     * @see #getFinishingEndFinderExpression()
     * @generated
     */
    void setFinishingEndFinderExpression(String value);

    /**
     * Returns the value of the '<em><b>Recursive</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Recursive</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Recursive</em>' attribute.
     * @see #setRecursive(boolean)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTExecutionMapping_Recursive()
     * @model required="true"
     * @generated
     */
    boolean isRecursive();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#isRecursive
     * <em>Recursive</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Recursive</em>' attribute.
     * @see #isRecursive()
     * @generated
     */
    void setRecursive(boolean value);

    /**
     * Returns the value of the '<em><b>Execution Mappings</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Execution Mappings</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Execution Mappings</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTExecutionMapping_ExecutionMappings()
     * @model containment="true"
     * @generated
     */
    EList<TExecutionMapping> getExecutionMappings();

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(TExecutionStyle)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTExecutionMapping_Style()
     * @model containment="true" required="true"
     * @generated
     */
    TExecutionStyle getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getStyle
     * <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(TExecutionStyle value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Styles</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTExecutionMapping_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<TConditionalExecutionStyle> getConditionalStyles();

} // TExecutionMapping
