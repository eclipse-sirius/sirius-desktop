/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.migration;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.osgi.framework.Version;

import org.eclipse.sirius.description.DescriptionPackage;

/**
 * The migration code of Sirius 6.9.0.
 * 
 * @author fbarbin
 * 
 */
public class DiagramRepresentationsFileMigrationParticipantV690 {

    /**
     * The VP version for this migration.
     */
    public static final Version MIGRATION_VERSION = new Version("6.9.0.201308011200");

    /**
     * Provides AdditionalLayer instead of OptionalLayer.
     * 
     * @param ePackage
     *            the package of the type.
     * @param name
     *            the name of the type.
     * @return an AdditionalLayer or null if the given type is not an
     *         OptionalLayer.
     */
    public EClassifier getType(EPackage ePackage, String name) {
        if (ePackage.getNsURI() != null && ePackage.getNsURI().equals(DescriptionPackage.eINSTANCE.getNsURI()) && name.equals("OptionalLayer")) {
            return DescriptionPackage.eINSTANCE.getAdditionalLayer();
        }
        return null;
    }

}
