/*******************************************************************************
 * Copyright (c) 2019 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.graphql.emf.api;

/**
 * Utility class holding some constants related to EMF.
 * 
 * @author sbegaudeau
 */
public final class EMFSchemaConstants {

    /**
     * The name of the type EObject.
     */
    public static final String EOBJECT_TYPE = "EObject"; //$NON-NLS-1$

    /**
     * The name of the type DynamicEObject.
     */
    public static final String DYNAMICEOBJECT_TYPE = "DynamicEObject"; //$NON-NLS-1$

    /**
     * The name of the type EPackage.
     */
    public static final String EPACKAGE_TYPE = "EPackage"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private EMFSchemaConstants() {
        // Do nothing
    }
}
