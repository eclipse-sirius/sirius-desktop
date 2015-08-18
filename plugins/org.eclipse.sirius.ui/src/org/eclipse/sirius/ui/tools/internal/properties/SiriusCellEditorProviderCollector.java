/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.properties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.ext.emf.ui.ICellEditorProvider;
import org.eclipse.sirius.ext.emf.ui.properties.CellEditorProviderCollector;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * This collector lookups within the
 * {@link SiriusCellEditorProviderCollector#EXTENSION_POINT_ID}.
 * 
 * @author Florian Barbin
 */
public final class SiriusCellEditorProviderCollector implements CellEditorProviderCollector {

    /**
     * Extension point ID.
     */
    public static final String EXTENSION_POINT_ID = "org.eclipse.sirius.ui.siriuspropertiescelleditor"; //$NON-NLS-1$

    /**
     * Singleton.
     */
    private static SiriusCellEditorProviderCollector instance;

    /**
     * The cache to keep ICellEditorProviders once they have been loaded.
     */
    private Set<ICellEditorProvider> cache;

    private SiriusCellEditorProviderCollector() {
    }

    /**
     * Provides the unique instance of this class. To be lazy, the
     * SiriusCellEditorProviderExtensionPoint is instantiate the first time we
     * ask the instance.
     * 
     * @return the SiriusCellEditorProviderExtensionPoint
     */
    public static SiriusCellEditorProviderCollector getInstance() {
        if (instance == null) {
            instance = new SiriusCellEditorProviderCollector();
        }
        return instance;
    }

    /**
     * Retrieves all registered contributions and put them in the cache.
     */
    private void loadCache() {
        cache = new HashSet<ICellEditorProvider>();
        IConfigurationElement[] config = EclipseUtil.getConfigurationElementsFor(EXTENSION_POINT_ID);
        for (IConfigurationElement configurationElement : config) {
            try {
                Object contribution = configurationElement.createExecutableExtension("class"); //$NON-NLS-1$
                if (contribution instanceof ICellEditorProvider) {
                    cache.add((ICellEditorProvider) contribution);
                }
            } catch (CoreException e) {
                SiriusPlugin.getDefault().getLog().log(new Status(Status.WARNING, SiriusPlugin.ID, "Cannot instantiate the Cell Editor Provider contributions", e));
            }
        }
    }

    @Override
    public List<ICellEditorProvider> getCellEditorProviders(EObject eObject, IItemPropertyDescriptor itemPropertyDescriptor) {
        if (cache == null) {
            loadCache();
        }
        List<ICellEditorProvider> cellEditorProviders = new ArrayList<ICellEditorProvider>();
        Iterator<ICellEditorProvider> iterator = cache.iterator();
        while (iterator.hasNext()) {
            ICellEditorProvider currentCellEditorProvider = iterator.next();
            if (currentCellEditorProvider.provides(eObject, itemPropertyDescriptor)) {
                cellEditorProviders.add(currentCellEditorProvider);
            }
        }
        return cellEditorProviders;
    }
}
