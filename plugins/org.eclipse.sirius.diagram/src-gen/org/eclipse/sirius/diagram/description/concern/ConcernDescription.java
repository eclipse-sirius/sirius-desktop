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
package org.eclipse.sirius.diagram.description.concern;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

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
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.concern.ConcernDescription#getFilters
 * <em>Filters</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.concern.ConcernDescription#getRules
 * <em>Rules</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.concern.ConcernDescription#getBehaviors
 * <em>Behaviors</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.concern.ConcernPackage#getConcernDescription()
 * @model
 * @generated
 */
public interface ConcernDescription extends DocumentedElement, IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Filters</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.diagram.description.filter.FilterDescription}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * All filters of this concern. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Filters</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.concern.ConcernPackage#getConcernDescription_Filters()
     * @model
     * @generated
     */
    EList<FilterDescription> getFilters();

    /**
     * Returns the value of the '<em><b>Rules</b></em>' reference list. The list
     * contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * All rules of this concern. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Rules</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.concern.ConcernPackage#getConcernDescription_Rules()
     * @model
     * @generated
     */
    EList<ValidationRule> getRules();

    /**
     * Returns the value of the '<em><b>Behaviors</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.diagram.description.tool.BehaviorTool}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All
     * behaviors of the concern. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Behaviors</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.concern.ConcernPackage#getConcernDescription_Behaviors()
     * @model
     * @generated
     */
    EList<BehaviorTool> getBehaviors();

} // ConcernDescription
