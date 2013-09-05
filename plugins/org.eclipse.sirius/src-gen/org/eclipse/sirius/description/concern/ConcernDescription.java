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
package org.eclipse.sirius.description.concern;

import org.eclipse.emf.common.util.EList;

import org.eclipse.sirius.description.DocumentedElement;
import org.eclipse.sirius.description.IdentifiedElement;
import org.eclipse.sirius.description.filter.FilterDescription;
import org.eclipse.sirius.description.tool.BehaviorTool;
import org.eclipse.sirius.description.validation.ValidationRule;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Description</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Describe a concern. A concern could be seen as an
 * aspect. It allows to enable some filters, validation rules or behaviors in
 * one click. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.concern.ConcernDescription#getFilters
 * <em>Filters</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.concern.ConcernDescription#getRules
 * <em>Rules</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.concern.ConcernDescription#getBehaviors
 * <em>Behaviors</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.description.concern.ConcernPackage#getConcernDescription()
 * @model
 * @generated
 */
public interface ConcernDescription extends DocumentedElement, IdentifiedElement {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Filters</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.description.filter.FilterDescription}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filters</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Filters</em>' reference list.
     * @see org.eclipse.sirius.description.concern.ConcernPackage#getConcernDescription_Filters()
     * @model type="viewpoint.description.filter.FilterDescription"
     * @generated
     */
    EList<FilterDescription> getFilters();

    /**
     * Returns the value of the '<em><b>Rules</b></em>' reference list. The list
     * contents are of type
     * {@link org.eclipse.sirius.description.validation.ValidationRule}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rules</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> All rules of this concern.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Rules</em>' reference list.
     * @see org.eclipse.sirius.description.concern.ConcernPackage#getConcernDescription_Rules()
     * @model type="org.eclipse.sirius.description.validation.ValidationRule"
     * @generated
     */
    EList<ValidationRule> getRules();

    /**
     * Returns the value of the '<em><b>Behaviors</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.description.tool.BehaviorTool}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Behaviors</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> All behaviors of the
     * concern. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Behaviors</em>' reference list.
     * @see org.eclipse.sirius.description.concern.ConcernPackage#getConcernDescription_Behaviors()
     * @model type="viewpoint.description.tool.BehaviorTool"
     * @generated
     */
    EList<BehaviorTool> getBehaviors();

} // ConcernDescription
