/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.tools.internal.menu;

import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;
import org.eclipse.sirius.properties.PropertiesPackage;

public class StyleMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * Create the menu.
     */
    public StyleMenuBuilder() {
        addValidType(PropertiesPackage.eINSTANCE.getGroupStyle());
        addValidType(PropertiesPackage.eINSTANCE.getGroupConditionalStyle());
        addValidType(PropertiesPackage.eINSTANCE.getWidgetStyle());
        addValidType(PropertiesPackage.eINSTANCE.getWidgetConditionalStyle());
    }

    @Override
    public String getLabel() {
        return "New Style";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.STYLE;
    }
}
