/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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

package org.eclipse.sirius.editor.properties.tools.internal.menu;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * The descriptor used to create a widget for all the features.
 * 
 * @author sbegaudeau
 */
public class CreateWidgetForAllFeaturesDescriptor {

    /**
     * The description of the container of the controls.
     */
    private EObject controlsContainerDescription;

    /**
     * The domain class.
     */
    private EClass domainClass;

    /**
     * The constructor.
     * 
     * @param controlsContainerDescription
     *            The description of the container of the controls
     * @param domainClass
     *            The domain class
     */
    public CreateWidgetForAllFeaturesDescriptor(EObject controlsContainerDescription, EClass domainClass) {
        this.controlsContainerDescription = controlsContainerDescription;
        this.domainClass = domainClass;
    }

    /**
     * Returns the description of the container of the controls.
     * 
     * @return The description of the container of the controls
     */
    public EObject getControlsContainerDescription() {
        return this.controlsContainerDescription;
    }

    /**
     * Returns the domain class.
     * 
     * @return The domain class
     */
    public EClass getDomainClass() {
        return this.domainClass;
    }

}
