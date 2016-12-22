/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.api;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This interface is used to create a default widget description for a feature.
 * 
 * @author sbegaudeau
 */
public interface IDefaultWidgetDescriptionFactory {

    /**
     * Indicates if the factory can handle the creation of a default widget
     * description for the given domain class and structural feature.
     * 
     * @param domainClass
     *            The domain class
     * @param eStructuralFeature
     *            The structural feature
     * @return <code>true</code> if the factory can create the default widget
     *         description, <code>false</code> otherwise
     */
    boolean canCreate(EClass domainClass, EStructuralFeature eStructuralFeature);

    /**
     * Creates the default widget description for the given domain class and
     * structural feature.
     * 
     * @param domainClass
     *            The domain class
     * @param eStructuralFeature
     *            The structural feature
     * @return A newly created default widget description
     */
    DefaultWidgetDescription create(EClass domainClass, EStructuralFeature eStructuralFeature);
}
