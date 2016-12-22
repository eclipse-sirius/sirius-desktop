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

package org.eclipse.sirius.editor.properties.tools.internal.menu;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.properties.GroupDescription;

/**
 * The descriptor used to create a widget for all the features.
 * 
 * @author sbegaudeau
 */
public class CreateWidgetForAllFeaturesDescriptor {

    /**
     * The description of the group.
     */
    private GroupDescription groupDescription;

    /**
     * The domain class.
     */
    private EClass domainClass;

    /**
     * The constructor.
     * 
     * @param groupDescription
     *            The description of the group
     * @param domainClass
     *            The domain class
     */
    public CreateWidgetForAllFeaturesDescriptor(GroupDescription groupDescription, EClass domainClass) {
        this.groupDescription = groupDescription;
        this.domainClass = domainClass;
    }

    /**
     * Returns the description of the group.
     * 
     * @return The description of the group
     */
    public GroupDescription getGroupDescription() {
        return this.groupDescription;
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
