/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.filter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Mapping Filter</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A filter that filters mappings. <!-- end-model-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.MappingFilter#getMappings
 * <em>Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.MappingFilter#getSemanticConditionExpression
 * <em>Semantic Condition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.MappingFilter#getViewConditionExpression
 * <em>View Condition Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getMappingFilter()
 * @model
 * @generated
 */
public interface MappingFilter extends Filter {
    /**
     * Returns the value of the '<em><b>Mappings</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * All mappings to filter. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getMappingFilter_Mappings()
     * @model
     * @generated
     */
    EList<DiagramElementMapping> getMappings();

    /**
     * Returns the value of the '<em><b>Semantic Condition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The condition to apply on the semantic element, if
     * true the element is filtered. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Semantic Condition Expression</em>'
     *         attribute.
     * @see #setSemanticConditionExpression(String)
     * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getMappingFilter_SemanticConditionExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     * @generated
     */
    String getSemanticConditionExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.filter.MappingFilter#getSemanticConditionExpression
     * <em>Semantic Condition Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic Condition Expression</em>'
     *            attribute.
     * @see #getSemanticConditionExpression()
     * @generated
     */
    void setSemanticConditionExpression(String value);

    /**
     * Returns the value of the '<em><b>View Condition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The condition to apply on the view element. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>View Condition Expression</em>' attribute.
     * @see #setViewConditionExpression(String)
     * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getMappingFilter_ViewConditionExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     * @generated
     */
    String getViewConditionExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.filter.MappingFilter#getViewConditionExpression
     * <em>View Condition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>View Condition Expression</em>'
     *            attribute.
     * @see #getViewConditionExpression()
     * @generated
     */
    void setViewConditionExpression(String value);

} // MappingFilter
