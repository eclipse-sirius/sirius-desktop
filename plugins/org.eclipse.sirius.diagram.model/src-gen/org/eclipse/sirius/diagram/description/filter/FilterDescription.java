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
package org.eclipse.sirius.diagram.description.filter;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Describe a filter. A filter allows to hide some elements. <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getFilterDescription()
 * @model abstract="true"
 * @generated
 */
public interface FilterDescription extends DocumentedElement, IdentifiedElement {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Return true if the specified viewpoint
     * element is visible for this filter.
     *
     * @param element
     *            The element to test. <!-- end-model-doc -->
     * @model
     * @generated
     */
    boolean isVisible(DDiagramElement element);

} // FilterDescription
