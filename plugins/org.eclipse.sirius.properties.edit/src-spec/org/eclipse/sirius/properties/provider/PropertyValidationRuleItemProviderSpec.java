/**
 * Copyright (c) 2016, 2017 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import java.util.Optional;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.sirius.properties.PropertyValidationRule;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class PropertyValidationRuleItemProviderSpec extends PropertyValidationRuleItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public PropertyValidationRuleItemProviderSpec(AdapterFactory adapterFactory) {
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
        PropertyValidationRule propertyValidationRule = (PropertyValidationRule) object;
        String label = Optional.ofNullable(propertyValidationRule.getName()).orElse(""); //$NON-NLS-1$
        if (label.isEmpty()) {
            label = this.getString("_UI_PropertyValidationRule_type"); //$NON-NLS-1$
        }
        return new StyledString(label);
    }
}
