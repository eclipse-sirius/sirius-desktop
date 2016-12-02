/**
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

}
