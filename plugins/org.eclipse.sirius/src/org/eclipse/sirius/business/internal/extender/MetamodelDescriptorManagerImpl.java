/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.extender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.business.api.extender.MetamodelDescriptorManager;
import org.eclipse.sirius.business.api.extender.MetamodelDescriptorProvider;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

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
    public Collection<MetamodelDescriptor> provides(final Collection<Viewpoint> enabledViewpoints) {
        final Collection<MetamodelDescriptor> result = Sets.newLinkedHashSet();
        for (MetamodelDescriptorProvider provider : Iterables.filter(providers, MetamodelDescriptorProvider.class)) {
            final Collection<MetamodelDescriptor> provided = provider.provides(enabledViewpoints);
            if (provided != null) {
                result.addAll(provided);
            }
        }
        return result;
    }
}
