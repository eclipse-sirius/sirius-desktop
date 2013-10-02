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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Semantic View Point</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A semantic viewpoint is a viewpoint that is
 * rattached to a semantic element. <!-- end-model-doc -->
 * 
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDSemanticDiagram()
 * @model
 * @generated
 */
public interface DSemanticDiagram extends DDiagram, DSemanticDecorator {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return the EObject from where the creation of the viewpoint starts. <!--
     * end-model-doc -->
     * 
     * @model kind="operation"
     * @generated
     */
    @Deprecated
    EObject getRootContent();
} // DSemanticDiagram
