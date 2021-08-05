/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.extender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.sirius.business.api.extender.MetamodelDescriptorManager;
import org.eclipse.sirius.business.api.extender.MetamodelDescriptorProvider;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Iterables;

/**
 * Implementation for the manager.
 * 
 * @author cbrun
 * 
 */
public class MetamodelDescriptorManagerImpl implements MetamodelDescriptorManager {

    private Collection<MetamodelDescriptorProvider> providers = new ArrayList<MetamodelDescriptorProvider>();

    /**
     * return a new instance.
     * 
     * @return a new instance.
     */
    public static MetamodelDescriptorManager init() {
        final MetamodelDescriptorManagerImpl manager = new MetamodelDescriptorManagerImpl();
        if (SiriusPlugin.IS_ECLIPSE_RUNNING) {
            final List<MetamodelDescriptorProvider> parsedDialects = EclipseUtil.getExtensionPlugins(MetamodelDescriptorProvider.class, MetamodelDescriptorManager.ID,
                    MetamodelDescriptorManager.CLASS_ATTRIBUTE);
            for (final MetamodelDescriptorProvider provider : parsedDialects) {
                manager.enableProvider(provider);
            }
        }
        return manager;
    }

    private void enableProvider(final MetamodelDescriptorProvider provider) {
        providers.add(provider);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Collection<MetamodelDescriptor> provides(final Collection<Viewpoint> enabledViewpoints) {
        final Collection<MetamodelDescriptor> result = new LinkedHashSet<>();
        for (MetamodelDescriptorProvider provider : Iterables.filter(providers, MetamodelDescriptorProvider.class)) {
            final Collection<MetamodelDescriptor> provided = provider.provides(enabledViewpoints);
            if (provided != null) {
                result.addAll(provided);
            }
        }
        return result;
    }
}
