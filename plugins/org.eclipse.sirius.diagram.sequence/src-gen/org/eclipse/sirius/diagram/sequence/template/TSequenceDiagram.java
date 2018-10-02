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
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>TSequence Diagram</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getDomainClass <em>Domain Class</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getEndsOrdering <em>Ends Ordering</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getLifelineMappings <em>Lifeline
 * Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getMessageMappings <em>Message Mappings</em>
 * }</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTSequenceDiagram()
 * @model
 * @generated
 */
public interface TSequenceDiagram extends RepresentationTemplate, TTransformer {
    /**
     * Returns the value of the '<em><b>Ends Ordering</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ends Ordering</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Ends Ordering</em>' attribute.
     * @see #setEndsOrdering(String)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTSequenceDiagram_EndsOrdering()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables eventEnds='a
     *        List&lt;EObject&gt; containing the semantic event ends.'"
     * @generated
     */
    String getEndsOrdering();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getEndsOrdering
     * <em>Ends Ordering</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Ends Ordering</em>' attribute.
     * @see #getEndsOrdering()
     * @generated
     */
    void setEndsOrdering(String value);

    /**
     * Returns the value of the '<em><b>Lifeline Mappings</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lifeline Mappings</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Lifeline Mappings</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTSequenceDiagram_LifelineMappings()
     * @model containment="true"
     * @generated
     */
    EList<TLifelineMapping> getLifelineMappings();

    /**
     * Returns the value of the '<em><b>Message Mappings</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message Mappings</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Message Mappings</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTSequenceDiagram_MessageMappings()
     * @model containment="true"
     * @generated
     */
    EList<TMessageMapping> getMessageMappings();

    /**
     * Returns the value of the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The domain class of the mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Domain Class</em>' attribute.
     * @see #setDomainClass(String)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTSequenceDiagram_DomainClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName" required="true"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getDomainClass
     * <em>Domain Class</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Domain Class</em>' attribute.
     * @see #getDomainClass()
     * @generated
     */
    void setDomainClass(String value);

} // TSequenceDiagram
