/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.description.filter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Mapping Filter</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A filter that filters mappings. <!-- end-model-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.filter.MappingFilter#getMappings
 * <em>Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.filter.MappingFilter#getSemanticConditionExpression
 * <em>Semantic Condition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.filter.MappingFilter#getViewConditionExpression
 * <em>View Condition Expression</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.filter.FilterPackage#getMappingFilter()
 * @model
 * @generated
 */
public interface MappingFilter extends Filter {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Mappings</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mappings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> All mappings to filter.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Mappings</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.filter.FilterPackage#getMappingFilter_Mappings()
     * @model
     * @generated
     */
    EList<DiagramElementMapping> getMappings();

    /**
     * Returns the value of the '<em><b>Semantic Condition Expression</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Semantic Condition Expression</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The condition to apply on
     * the semantic element. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Semantic Condition Expression</em>'
     *         attribute.
     * @see #setSemanticConditionExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.filter.FilterPackage#getMappingFilter_SemanticConditionExpression()
     * @model
     * @generated
     */
    String getSemanticConditionExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.filter.MappingFilter#getSemanticConditionExpression
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
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>View Condition Expression</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The condition to apply on
     * the view element. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>View Condition Expression</em>' attribute.
     * @see #setViewConditionExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.filter.FilterPackage#getMappingFilter_ViewConditionExpression()
     * @model
     * @generated
     */
    String getViewConditionExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.filter.MappingFilter#getViewConditionExpression
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
