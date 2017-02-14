/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.tools.internal.menu;

import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * The menu builder for the widgets.
 * 
 * @author sbegaudeau
 */
public class WidgetsMenuBuilder extends AbstractTypeRestrictingMenuBuilder {

    /**
     * Create the menu.
     */
    public WidgetsMenuBuilder() {
        super();
        addValidType(PropertiesPackage.eINSTANCE.getWidgetDescription());
    }

    @Override
    public String getLabel() {
        return "New Widget";
    }

    @Override
    public int getPriority() {
        return PropertiesMenuBuilderConstants.WIDGETS;
    }
}
