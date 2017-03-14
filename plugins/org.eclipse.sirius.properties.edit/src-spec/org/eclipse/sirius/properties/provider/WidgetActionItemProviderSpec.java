/**
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import java.util.Optional;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.sirius.properties.WidgetAction;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class WidgetActionItemProviderSpec extends WidgetActionItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public WidgetActionItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        Object styledText = this.getStyledText(object);
        if (styledText instanceof StyledString) {
            return ((StyledString) styledText).getString();
        }
        return super.getText(object);
    }

    @Override
    public Object getStyledText(Object object) {
        WidgetAction widgetAction = (WidgetAction) object;
        String label = Optional.ofNullable(widgetAction.getLabelExpression()).orElse(""); //$NON-NLS-1$
        if (label.isEmpty()) {
            label = this.getString("_UI_WidgetAction_type"); //$NON-NLS-1$
        }
        return new StyledString(label);
    }

    @Override
    protected CommandParameter createChildParameter(Object feature, Object child) {
        Utils.addNoopNavigationOperations(child);
        return super.createChildParameter(feature, child);
    }
}
