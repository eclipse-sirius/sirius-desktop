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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.editor.properties.api.DefaultWidgetDescription;

/**
 * The descriptor used to hold the parameter of the command creating a widget from a structural feature.
 * 
 * @author sbegaudeau
 */
public class CreateWidgetForFeatureDescriptor {

    /**
     * The description of the container of the controls.
     */
    private EObject controlsContainerDescription;

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
     * @param controlsContainerDescription
     *            The description of the container of the controls
     * @param domainClass
     *            The domain class
     * @param imageDescriptor
     *            The image descriptor
     * @param defaultWidgetDescription
     *            The description of the default widget
     */
    public CreateWidgetForFeatureDescriptor(EObject controlsContainerDescription, EClass domainClass, ImageDescriptor imageDescriptor, DefaultWidgetDescription defaultWidgetDescription) {
        this.controlsContainerDescription = controlsContainerDescription;
        this.domainClass = domainClass;
        this.imageDescriptor = imageDescriptor;
        this.defaultWidgetDescription = defaultWidgetDescription;
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
