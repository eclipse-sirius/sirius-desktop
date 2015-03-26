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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DStylizable</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> An element that has a style. <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDStylizable()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface DStylizable extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return the style of the element. <!-- end-model-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    Style getStyle();

} // DStylizable
