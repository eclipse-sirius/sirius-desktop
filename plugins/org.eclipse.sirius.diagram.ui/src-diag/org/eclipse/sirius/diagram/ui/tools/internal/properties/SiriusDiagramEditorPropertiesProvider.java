/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.tools.api.ui.property.IPropertiesProvider;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Implementation ...
 * 
 * @author mchauvin
 */
public class SiriusDiagramEditorPropertiesProvider implements IPropertiesProvider {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.ui.property.IPropertiesProvider#getProperty(int)
     */
    public boolean getProperty(final int key) throws IllegalArgumentException {

        if (IPropertiesProvider.KEY_AUTO_REFRESH == key) {
            return Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null);
        }
        throw IPropertiesProvider.EXCEPTION_PROPERTY_NOT_FOUND;
    }

}
