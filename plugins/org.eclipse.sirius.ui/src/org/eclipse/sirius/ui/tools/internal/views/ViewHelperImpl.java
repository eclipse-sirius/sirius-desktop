/*******************************************************************************
 * Copyright (c) 2008, 2010, 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionsUIManager;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionWrapperContentProvider;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension.IContextMenuActionProvider;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension.ISessionViewExtension;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * An helper to provide facilities to view which extends viewpoint.
 * 
 * @author mchauvin
 */
public final class ViewHelperImpl implements ViewHelper {

    private static Collection<ISessionViewExtension> extensions = new ArrayList<>();

    /**
     * Avoid instantiation.
     */
    private ViewHelperImpl() {

    }

    /**
     * Create the shared instance.
     * 
     * @return the singleton
     */
    public static ViewHelper init() {
        return new ViewHelperImpl();
    }

    @Override
    public AdapterFactory createAdapterFactory() {
        final List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        factories.add(DialectUIManager.INSTANCE.createAdapterFactory());
        factories.add(FeatureExtensionsUIManager.INSTANCE.createAdapterFactory());
        factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        factories.add(new ReflectiveItemProviderAdapterFactory());
        return new ComposedAdapterFactory(factories);
    }

    @Override
    public ITreeContentProvider createContentProvider() {
            SessionWrapperContentProvider sessionWrapperContentProvider = new SessionWrapperContentProvider(new AdapterFactoryContentProvider(createAdapterFactory()));
            ITreeContentProvider contentProvider = new GroupingContentProvider(sessionWrapperContentProvider);
            Collection<ITreeContentProvider> liveProviders = Collections2.transform(extensions, new Function<ISessionViewExtension, ITreeContentProvider>() {
                @Override
                public ITreeContentProvider apply(ISessionViewExtension from) {
                    return from.getContentProvider();
                }
            });
            sessionWrapperContentProvider.setExtensions(liveProviders);
            return contentProvider;
    }

    /**
     * Get the context menu providers from extensions.
     * 
     * @return the providers
     */
    public Collection<IContextMenuActionProvider> getContextMenuActionsProviders() {
        Collection<IContextMenuActionProvider> liveProviders = Collections2.transform(extensions, new Function<ISessionViewExtension, IContextMenuActionProvider>() {
            @Override
            public IContextMenuActionProvider apply(ISessionViewExtension from) {
                return from.getContextMenuActionProvider();
            }
        });
        return liveProviders;
    }

    /**
     * Add an extension. This is not API.
     * 
     * @param extension
     *            the extension to add
     */
    public void addExtension(ISessionViewExtension extension) {
        extensions.add(extension);
    }

    /**
     * Remove an extension. This is not API.
     * 
     * @param extension
     *            the extension to remove
     */
    public void removeExtension(ISessionViewExtension extension) {
        extensions.remove(extension);
    }

}
