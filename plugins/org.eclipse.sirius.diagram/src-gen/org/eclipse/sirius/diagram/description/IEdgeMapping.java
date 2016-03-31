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
package org.eclipse.sirius.diagram.description;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.EdgeStyle;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>IEdge Mapping</b></em>'. <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getIEdgeMapping()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IEdgeMapping extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model
     * @generated
     */
    EdgeStyle getBestStyle(EObject modelElement, EObject viewVariable, EObject containerVariable);

} // IEdgeMapping
