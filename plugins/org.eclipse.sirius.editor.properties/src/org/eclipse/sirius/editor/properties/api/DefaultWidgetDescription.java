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

import org.eclipse.sirius.properties.WidgetDescription;

/**
 * The description of a widget to be created by default.
 * 
 * @author sbegaudeau
 */
public class DefaultWidgetDescription {
    /**
     * The label of the widget.
     */
    private String label;

    /**
     * The widget description.
     */
    private WidgetDescription widgetDescription;

    /**
     * The constructor.
     * 
     * @param label
     *            The label
     * @param widgetDescription
     *            The widget description
     */
    public DefaultWidgetDescription(String label, WidgetDescription widgetDescription) {
        this.label = label;
        this.widgetDescription = widgetDescription;
    }

    /**
     * Returns the label.
     * 
     * @return The label
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Returns the widget description.
     * 
     * @return The widget description
     */
    public WidgetDescription getWidgetDescription() {
        return this.widgetDescription;
    }
}
