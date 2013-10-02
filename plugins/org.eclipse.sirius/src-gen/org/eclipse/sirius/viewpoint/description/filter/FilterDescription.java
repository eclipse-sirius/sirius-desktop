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

import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Description</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Describe a filter. A filter allows to hide some
 * elements. <!-- end-model-doc -->
 * 
 * 
 * @see org.eclipse.sirius.viewpoint.description.filter.FilterPackage#getFilterDescription()
 * @model abstract="true"
 * @generated
 */
public interface FilterDescription extends DocumentedElement, IdentifiedElement {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return true if the specified viewpoint element is visible for this
     * filter.
     * 
     * @param element
     *            The element to test. <!-- end-model-doc -->
     * @model
     * @generated
     */
    boolean isVisible(DDiagramElement element);

} // FilterDescription
