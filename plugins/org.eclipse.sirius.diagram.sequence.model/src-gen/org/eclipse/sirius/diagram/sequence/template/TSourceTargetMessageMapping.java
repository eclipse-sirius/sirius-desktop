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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>TSource Target Message Mapping</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#getSource <em>Source</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#getSourceFinderExpression
 * <em>Source Finder Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#getTargetFinderExpression
 * <em>Target Finder Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#isUseDomainElement <em>Use Domain
 * Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTSourceTargetMessageMapping()
 * @model abstract="true"
 * @generated
 */
public interface TSourceTargetMessageMapping extends TMessageMapping {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageExtremity}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Source</em>' reference list.
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTSourceTargetMessageMapping_Source()
     * @model required="true"
     * @generated
     */
    EList<TMessageExtremity> getSource();

    /**
     * Returns the value of the '<em><b>Source Finder Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The elements that are represented by this connection. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Source Finder Expression</em>' attribute.
     * @see #setSourceFinderExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTSourceTargetMessageMapping_SourceFinderExpression()
     * @model dataType="org.eclipse.sirius.description.InterpretedExpression" annotation =
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an
     *        EObject.'" annotation= "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='the
     *        current DDiagram.' viewpoint='(deprecated) the current DDiagram.' viewPoint='(deprecated) the current
     *        DDiagram.'"
     * @generated
     */
    String getSourceFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#getSourceFinderExpression
     * <em>Source Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source Finder Expression</em>' attribute.
     * @see #getSourceFinderExpression()
     * @generated
     */
    void setSourceFinderExpression(String value);

    /**
     * Returns the value of the '<em><b>Target Finder Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The elements that are represented by this connection. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Target Finder Expression</em>' attribute.
     * @see #setTargetFinderExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTSourceTargetMessageMapping_TargetFinderExpression()
     * @model dataType="org.eclipse.sirius.description.InterpretedExpression" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an
     *        EObject.'" annotation= "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='the
     *        current DDiagram.' viewpoint='(deprecated) the current DDiagram.' viewPoint='(deprecated) the current
     *        DDiagram.'"
     * @generated
     */
    String getTargetFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#getTargetFinderExpression
     * <em>Target Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target Finder Expression</em>' attribute.
     * @see #getTargetFinderExpression()
     * @generated
     */
    void setTargetFinderExpression(String value);

    /**
     * Returns the value of the '<em><b>Use Domain Element</b></em>' attribute. The default value is
     * <code>"false"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Use Domain Element</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Use Domain Element</em>' attribute.
     * @see #setUseDomainElement(boolean)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTSourceTargetMessageMapping_UseDomainElement()
     * @model default="false"
     * @generated
     */
    boolean isUseDomainElement();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#isUseDomainElement <em>Use
     * Domain Element</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Use Domain Element</em>' attribute.
     * @see #isUseDomainElement()
     * @generated
     */
    void setUseDomainElement(boolean value);

} // TSourceTargetMessageMapping
