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

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Mapping Based Decoration</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A MappingBasedDecoration applies decorations on
 * views that are issued from one or more mappings. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.MappingBasedDecoration#getMappings
 * <em>Mappings</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getMappingBasedDecoration()
 * @model
 * @generated
 */
public interface MappingBasedDecoration extends DecorationDescription {
    /**
     * Returns the value of the '<em><b>Mappings</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mappings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getMappingBasedDecoration_Mappings()
     * @model required="true"
     * @generated
     */
    EList<DiagramElementMapping> getMappings();

} // MappingBasedDecoration
