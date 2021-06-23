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
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>TLifeline Mapping</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getEolVisibleExpression <em>Eol Visible
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getExecutionMappings <em>Execution
 * Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getInstanceRoleStyle <em>Instance Role
 * Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getLifelineStyle <em>Lifeline Style</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getEndOfLifeStyle <em>End Of Life Style</em>
 * }</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getConditionalLifeLineStyles <em>Conditional
 * Life Line Styles</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTLifelineMapping()
 * @model
 * @generated
 */
public interface TLifelineMapping extends TAbstractMapping, TMessageExtremity {
    /**
     * Returns the value of the '<em><b>Eol Visible Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Eol Visible Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Eol Visible Expression</em>' attribute.
     * @see #setEolVisibleExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTLifelineMapping_EolVisibleExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables containerView='the view that
     *        sould contain the potential views of the checked elements.' container='the semantic element of
     *        $containerView.' viewpoint='(deprecated) the current DSemanticDiagram.' diagram='the current
     *        DSemanticDiagram.'"
     * @generated
     */
    String getEolVisibleExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getEolVisibleExpression <em>Eol Visible
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Eol Visible Expression</em>' attribute.
     * @see #getEolVisibleExpression()
     * @generated
     */
    void setEolVisibleExpression(String value);

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
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTLifelineMapping_ExecutionMappings()
     * @model containment="true"
     * @generated
     */
    EList<TExecutionMapping> getExecutionMappings();

    /**
     * Returns the value of the '<em><b>Instance Role Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instance Role Style</em>' containment reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Instance Role Style</em>' containment reference.
     * @see #setInstanceRoleStyle(NodeStyleDescription)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTLifelineMapping_InstanceRoleStyle()
     * @model containment="true" required="true"
     * @generated
     */
    NodeStyleDescription getInstanceRoleStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getInstanceRoleStyle
     * <em>Instance Role Style</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Instance Role Style</em>' containment reference.
     * @see #getInstanceRoleStyle()
     * @generated
     */
    void setInstanceRoleStyle(NodeStyleDescription value);

    /**
     * Returns the value of the '<em><b>Lifeline Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lifeline Style</em>' containment reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Lifeline Style</em>' containment reference.
     * @see #setLifelineStyle(TLifelineStyle)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTLifelineMapping_LifelineStyle()
     * @model containment="true" required="true"
     * @generated
     */
    TLifelineStyle getLifelineStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getLifelineStyle
     * <em>Lifeline Style</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Lifeline Style</em>' containment reference.
     * @see #getLifelineStyle()
     * @generated
     */
    void setLifelineStyle(TLifelineStyle value);

    /**
     * Returns the value of the '<em><b>End Of Life Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End Of Life Style</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>End Of Life Style</em>' containment reference.
     * @see #setEndOfLifeStyle(NodeStyleDescription)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTLifelineMapping_EndOfLifeStyle()
     * @model containment="true" required="true"
     * @generated
     */
    NodeStyleDescription getEndOfLifeStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getEndOfLifeStyle
     * <em>End Of Life Style</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>End Of Life Style</em>' containment reference.
     * @see #getEndOfLifeStyle()
     * @generated
     */
    void setEndOfLifeStyle(NodeStyleDescription value);

    /**
     * Returns the value of the '<em><b>Conditional Life Line Styles</b></em>' containment reference list. The list
     * contents are of type {@link org.eclipse.sirius.diagram.sequence.template.ConditionalLifelineStyle} . <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All conditional styles. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Conditional Life Line Styles</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTLifelineMapping_ConditionalLifeLineStyles()
     * @model containment="true"
     * @generated
     */
    EList<TConditionalLifelineStyle> getConditionalLifeLineStyles();

} // TLifelineMapping
