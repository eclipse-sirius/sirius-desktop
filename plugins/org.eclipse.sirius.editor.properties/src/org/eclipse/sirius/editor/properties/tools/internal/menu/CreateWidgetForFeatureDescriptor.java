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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.editor.properties.api.DefaultWidgetDescription;
import org.eclipse.sirius.properties.GroupDescription;

/**
 * The descriptor used to hold the parameter of the command creating a widget
 * from a structural feature.
 * 
 * @author sbegaudeau
 */
public class CreateWidgetForFeatureDescriptor {

    /**
     * The description of the group.
     */
    private GroupDescription groupDescription;

    /**
     * The domain class.
     */
    private EClass domainClass;

    /**
     * The image descriptor of the icon of the widget description.
     */
    private ImageDescriptor imageDescriptor;

    /**
     * The description of the default widget.
     */
    private DefaultWidgetDescription defaultWidgetDescription;

    /**
     * The constructor.
     * 
     * @param groupDescription
     *            The description of the group
     * @param domainClass
     *            The domain class
     * @param imageDescriptor
     *            The image descriptor
     * @param defaultWidgetDescription
     *            The description of the default widget
     */
    public CreateWidgetForFeatureDescriptor(GroupDescription groupDescription, EClass domainClass, ImageDescriptor imageDescriptor, DefaultWidgetDescription defaultWidgetDescription) {
        this.groupDescription = groupDescription;
        this.domainClass = domainClass;
        this.imageDescriptor = imageDescriptor;
        this.defaultWidgetDescription = defaultWidgetDescription;
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

    /**
     * Returns the image descriptor.
     * 
     * @return The image descriptor
     */
    public ImageDescriptor getImageDescriptor() {
        return this.imageDescriptor;
    }

    /**
     * Returns the description of the default widget.
     * 
     * @return The description of the default widget
     */
    public DefaultWidgetDescription getDefaultWidgetDescription() {
        return this.defaultWidgetDescription;
    }

}
