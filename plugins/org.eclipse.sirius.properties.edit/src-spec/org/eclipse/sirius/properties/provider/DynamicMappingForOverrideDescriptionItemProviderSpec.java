/**
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
 *
 */
package org.eclipse.sirius.properties.provider;

import java.util.Optional;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.emf.edit.provider.StyledString.Style;
import org.eclipse.sirius.properties.DynamicMappingForOverrideDescription;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class DynamicMappingForOverrideDescriptionItemProviderSpec extends DynamicMappingForOverrideDescriptionItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public DynamicMappingForOverrideDescriptionItemProviderSpec(AdapterFactory adapterFactory) {
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
        DynamicMappingForOverrideDescription element = (DynamicMappingForOverrideDescription) object;
        StyledString label = new StyledString("For "); //$NON-NLS-1$

        String iterator = Optional.ofNullable(element.getIterator()).orElse(""); //$NON-NLS-1$
        if (!iterator.isEmpty()) {
            label.append(iterator);
        } else {
            label.append("iterator", Style.QUALIFIER_STYLER); //$NON-NLS-1$
        }

        label.append(" in "); //$NON-NLS-1$

        String iterableExpression = Optional.ofNullable(element.getIterableExpression()).orElse(""); //$NON-NLS-1$
        if (!iterableExpression.isEmpty()) {
            label.append(iterableExpression);
        } else {
            label.append("iterable expression", Style.QUALIFIER_STYLER); //$NON-NLS-1$
        }

        return label;
    }

}
