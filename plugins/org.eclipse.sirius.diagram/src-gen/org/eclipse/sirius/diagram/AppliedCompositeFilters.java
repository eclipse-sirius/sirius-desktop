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
package org.eclipse.sirius.diagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Applied Composite Filters</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Graphical filter listing the active filters applied
 * on a diagram element. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.AppliedCompositeFilters#getCompositeFilterDescriptions
 * <em>Composite Filter Descriptions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getAppliedCompositeFilters()
 * @model
 * @generated
 */
public interface AppliedCompositeFilters extends GraphicalFilter {
    /**
     * Returns the value of the '<em><b>Composite Filter Descriptions</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Composite Filter Descriptions</em>' reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Composite Filter Descriptions</em>'
     *         reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getAppliedCompositeFilters_CompositeFilterDescriptions()
     * @model
     * @generated
     */
    EList<CompositeFilterDescription> getCompositeFilterDescriptions();

} // AppliedCompositeFilters
