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
package org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.provider;

import org.eclipse.emf.common.notify.Adapter;

/**
 * Spec class for the adapter factory.
 * 
 * @author sbegaudeau
 */
public class PropertiesExtWidgetsReferenceItemProviderAdapterFactorySpec extends PropertiesExtWidgetsReferenceItemProviderAdapterFactory {

    @Override
    public Adapter createExtReferenceDescriptionAdapter() {
        if (extReferenceDescriptionItemProvider == null) {
            extReferenceDescriptionItemProvider = new ExtReferenceDescriptionItemProviderSpec(this);
        }

        return extReferenceDescriptionItemProvider;
    }

    @Override
    public Adapter createExtReferenceWidgetStyleAdapter() {
        if (extReferenceWidgetStyleItemProvider == null) {
            extReferenceWidgetStyleItemProvider = new ExtReferenceWidgetStyleItemProviderSpec(this);
        }
        return extReferenceWidgetStyleItemProvider;
    }

    @Override
    public Adapter createExtReferenceWidgetConditionalStyleAdapter() {
        if (extReferenceWidgetConditionalStyleItemProvider == null) {
            extReferenceWidgetConditionalStyleItemProvider = new ExtReferenceWidgetConditionalStyleItemProviderSpec(this);
        }
        return extReferenceWidgetConditionalStyleItemProvider;
    }

    @Override
    public Adapter createExtReferenceOverrideDescriptionAdapter() {
        if (extReferenceOverrideDescriptionItemProvider == null) {
            extReferenceOverrideDescriptionItemProvider = new ExtReferenceOverrideDescriptionItemProviderSpec(this);
        }

        return extReferenceOverrideDescriptionItemProvider;
    }
}
