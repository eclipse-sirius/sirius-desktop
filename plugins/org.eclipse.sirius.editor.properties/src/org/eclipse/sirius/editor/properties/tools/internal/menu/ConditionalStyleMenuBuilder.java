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

import org.eclipse.sirius.editor.properties.internal.Messages;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * The menu builder for the conditional style.
 * 
 * @author sbegaudeau
 */
public class ConditionalStyleMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * Create the menu.
     */
    public ConditionalStyleMenuBuilder() {
        this.addValidType(PropertiesPackage.eINSTANCE.getGroupConditionalStyle());
        this.addValidType(PropertiesPackage.eINSTANCE.getWidgetConditionalStyle());
    }

    @Override
    public String getLabel() {
        return Messages.ConditionalStyleMenuBuilder_label;
    }

    @Override
    public int getPriority() {
        return PropertiesMenuBuilderConstants.CONDITIONAL_STYLE;
    }
}
