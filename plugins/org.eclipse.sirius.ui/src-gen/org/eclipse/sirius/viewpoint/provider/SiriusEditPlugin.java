/**
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.viewpoint.provider;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.api.preferences.DCorePreferences;
import org.eclipse.sirius.ui.business.internal.dialect.LogThroughActiveDialectEditorLogListener;
import org.eclipse.sirius.ui.business.internal.session.GenericSWTCallBack;
import org.eclipse.sirius.ui.business.internal.session.factory.UISessionFactoryDescriptorRegistryListener;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.api.profiler.SiriusTasks;
import org.eclipse.sirius.ui.tools.api.properties.ISiriusPropertySheetPageProvider;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.resourcelistener.IModelingProjectResourceListener;
import org.eclipse.sirius.ui.tools.internal.actions.analysis.IAddModelDependencyWizardRegistryListener;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.resourcelistener.ModelingProjectResourceListenerRegistry;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension.tab.ModelExplorerTabRegistryListener;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.resourcelistener.ISessionFileLoadingListener;
import org.eclipse.sirius.viewpoint.description.audit.provider.AuditItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.style.provider.StyleItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.tool.provider.ToolItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.validation.provider.ValidationItemProviderAdapterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.osgi.framework.BundleContext;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * This is the central singleton for the Viewpoint edit plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public final class SiriusEditPlugin extends EMFPlugin {
    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final SiriusEditPlugin INSTANCE = new SiriusEditPlugin();

    public static final String ID = "org.eclipse.sirius.ui"; //$NON-NLS-1$

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static Implementation plugin;

    /**
     * Create the instance. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SiriusEditPlugin() {
        super(new ResourceLocator[] { EcoreEditPlugin.INSTANCE, });
    }

    /**
     * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the singleton instance.
     * @not-generated
     */
    @Override
    public ResourceLocator getPluginResourceLocator() {
        final List<ResourceLocator> overridingLocator = EclipseUtil.getExtensionPlugins(ResourceLocator.class, "org.eclipse.sirius.ui.resourcelocator", "class"); //$NON-NLS-1$ //$NON-NLS-2$
        if (overridingLocator.size() > 0) {
            return overridingLocator.get(0);
        }
        return SiriusEditPlugin.plugin;
    }

    /**
     * return the original (non overriden) resource locator.
     *
     * @return the original (non overriden) resource locator.
     */
    public ResourceLocator getOriginalResourceLocator() {
        return SiriusEditPlugin.plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the singleton instance.
     * @generated
     */
    public static Implementation getPlugin() {
        return SiriusEditPlugin.plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>. <!-- begin-user-doc --> Extend UI plug-in<!--
     * end-user-doc -->
     *
     * @not-generated
     */
    public static class Implementation extends EclipseUIPlugin {

        private ComposedAdapterFactory adapterFactory;

        private Map<ImageDescriptor, Image> descriptorsToImages;

        private Multimap<String, ISiriusPropertySheetPageProvider> propertySheetPageProviders = HashMultimap.create();

        /**
         * The registry listener that will be used to listen to extension changes.
         */
        private ModelExplorerTabRegistryListener tabRegistryListener;

        /**
         * The listener that will be used to listen to resource changes in modeling project
         */
        private IModelingProjectResourceListener modelingProjectListener;

        private UISessionFactoryDescriptorRegistryListener uiSessionFactoryDescriptorRegistryListener;

        private IAddModelDependencyWizardRegistryListener resourceWizardRegistryListener;

        /**
         * Listeners that will be used to listen to session's file opening.
         */
        private Set<ISessionFileLoadingListener> sessionFileLoadingListenersRegistry;

        /**
         * Storage for Sirius core preferences which are managed by Sirius UI Preference page.
         */
        private ScopedPreferenceStore siriusCorePreferenceStore;

        /**
         * Creates an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated NOT
         */
        public Implementation() {
            super();

            // Remember the static instance.
            //
            SiriusEditPlugin.plugin = this;
            SiriusPlugin.getDefault().setUiCallback(new GenericSWTCallBack());
            sessionFileLoadingListenersRegistry = new HashSet<ISessionFileLoadingListener>();
        }

        /**
         * Returns all listeners that must be worn when a session file has been loaded.
         *
         * @return all listeners that must be worn when a session file has been loaded.
         */
        public Set<ISessionFileLoadingListener> getSessionFileLoadingListeners() {
            return sessionFileLoadingListenersRegistry;
        }

        /**
         * Add the given listener to the registry.
         *
         * @param sessionFileLoadingListener
         *            the listener to add.
         */
        public void addSessionFileLoadingListener(ISessionFileLoadingListener sessionFileLoadingListener) {
            if (sessionFileLoadingListenersRegistry != null) {
                sessionFileLoadingListenersRegistry.add(sessionFileLoadingListener);
            }
        }

        /**
         * Add the given listener from the registry.
         *
         * @param sessionFileLoadingListener
         *            the listener to add.
         */
        public void removeSessionFileLoadingListener(ISessionFileLoadingListener sessionFileLoadingListener) {
            if (sessionFileLoadingListenersRegistry != null) {
                sessionFileLoadingListenersRegistry.remove(sessionFileLoadingListener);
            }
        }

        /**
         * {@inheritDoc}
         *
         * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
         */
        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);
            initPreferences();

            adapterFactory = createAdapterFactory();
            descriptorsToImages = new HashMap<ImageDescriptor, Image>();

            tabRegistryListener = new ModelExplorerTabRegistryListener();
            tabRegistryListener.init();

            modelingProjectListener = ModelingProjectResourceListenerRegistry.getModelingProjectResourceListener();
            modelingProjectListener.init();

            uiSessionFactoryDescriptorRegistryListener = new UISessionFactoryDescriptorRegistryListener();
            uiSessionFactoryDescriptorRegistryListener.init();

            resourceWizardRegistryListener = new IAddModelDependencyWizardRegistryListener();
            resourceWizardRegistryListener.init();

            registerPropertySheetPageProviders();

            try {
                SiriusTasks.initSiriusTasks();
            } catch (IllegalArgumentException e) {
                final IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.OK, e.getMessage(), e);
                SiriusEditPlugin.getPlugin().getLog().log(status);
            }

            try {
                // Just a call to load all plugins that provide a specific
                // resource locator (can be usefull as for automaticall test).
                EclipseUtil.getExtensionPlugins(ResourceLocator.class, "org.eclipse.sirius.ui.resourcelocator", "class"); //$NON-NLS-1$ //$NON-NLS-2$
            } finally {
                // Do nothing
            }

            try {
                // Registering a log listener allowing to react to permission
                // issues by displaying it in pop-ups
                Platform.addLogListener(LogThroughActiveDialectEditorLogListener.INSTANCE);
            } finally {
                // Do nothing
            }
        }

        private void registerPropertySheetPageProviders() {
            for (IConfigurationElement ce : EclipseUtil.getConfigurationElementsFor("org.eclipse.sirius.ui.siriusPropertySheetPageProvider")) { //$NON-NLS-1$
                try {
                    String contribId = ce.getAttribute("contributorId"); //$NON-NLS-1$
                    if (contribId != null) {
                        ISiriusPropertySheetPageProvider provider = (ISiriusPropertySheetPageProvider) ce.createExecutableExtension("class"); //$NON-NLS-1$
                        this.propertySheetPageProviders.put(contribId, provider);
                    }
                } catch (CoreException e) {
                    getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.OK, e.getMessage(), e));
                }
            }
        }

        private void initPreferences() {
            final IPreferencesService service = Platform.getPreferencesService();
            /* init the visual binding manager cache with the max sizes */
            final int maxColorSize = service.getInt(SiriusEditPlugin.ID, DCorePreferences.COLOR_REGISTRY_MAX_SIZE, DCorePreferences.COLOR_REGISTRY_MAX_SIZE_DEFAULT_VALUE, null);
            final int maxFontSize = service.getInt(SiriusEditPlugin.ID, DCorePreferences.FONT_REGISTRY_MAX_SIZE, DCorePreferences.FONT_REGISTRY_MAX_SIZE_DEFAULT_VALUE, null);
            VisualBindingManager.getDefault().init(maxColorSize, maxFontSize);
        }

        /**
         *
         * {@inheritDoc}
         *
         * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
         */
        @Override
        public void stop(BundleContext context) throws Exception {

            try {
                adapterFactory.dispose();
                adapterFactory = null;
            } catch (NullPointerException e) {
                // can occur when using CDO (if the view is
                // closed when transactions have been closed )
            }
            /*
             * Disposing the images
             */
            Iterator<Image> it = descriptorsToImages.values().iterator();
            while (it.hasNext()) {
                Image img = it.next();
                if (img != null) {
                    img.dispose();
                }
            }

            tabRegistryListener.dispose();
            tabRegistryListener = null;
            modelingProjectListener.dispose();
            modelingProjectListener = null;
            uiSessionFactoryDescriptorRegistryListener.dispose();
            uiSessionFactoryDescriptorRegistryListener = null;
            resourceWizardRegistryListener.dispose();
            resourceWizardRegistryListener = null;
            sessionFileLoadingListenersRegistry = null;

            try {
                Platform.removeLogListener(LogThroughActiveDialectEditorLogListener.INSTANCE);
            } finally {
                // Do nothing, make sure that super.stop is called
            }

            super.stop(context);
            VisualBindingManager.getDefault().dispose();
        }

        /**
         * Returns an image descriptor for the image file at the given plug-in relative path.
         *
         * @param path
         *            the path
         * @return the image descriptor
         */
        public static ImageDescriptor getBundledImageDescriptor(String path) {
            return AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, path);
        }

        /**
         * Respects images residing in any plug-in. If path is relative, then this bundle is looked up for the image,
         * otherwise, for absolute path, first segment is taken as id of plug-in with image
         *
         * @param path
         *            the path to image, either absolute (with plug-in id as first segment), or relative for bundled
         *            images
         * @return the image descriptor
         */
        public static ImageDescriptor findImageDescriptor(String path) {
            final IPath p = new Path(path);
            if (p.isAbsolute() && p.segmentCount() > 1) {
                return AbstractUIPlugin.imageDescriptorFromPlugin(p.segment(0), p.removeFirstSegments(1).makeAbsolute().toString());
            } else {
                return Implementation.getBundledImageDescriptor(p.makeAbsolute().toString());
            }
        }

        /**
         * Returns an image for the image file at the given plug-in relative path. Client do not need to dispose this
         * image. Images will be disposed automatically.
         *
         * @param path
         *            the path
         * @return image instance
         */
        public Image getBundledImage(String path) {
            Image image = getImageRegistry().get(path);
            if (image == null) {
                getImageRegistry().put(path, Implementation.getBundledImageDescriptor(path));
                image = getImageRegistry().get(path);
            }
            return image;
        }

        /**
         * Get an item descriptor
         *
         * @param item
         *            the object item
         * @return an image descriptor.
         */
        public ImageDescriptor getItemImageDescriptor(final Object item) {
            IItemLabelProvider labelProvider = (IItemLabelProvider) adapterFactory.adapt(item, IItemLabelProvider.class);
            if (labelProvider != null) {
                return ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(item));
            }
            return null;
        }

        /**
         * Get the text of an item.
         *
         * @param item
         *            the object item.
         * @return the label.
         */
        public String getItemText(final Object item) {
            IItemLabelProvider labelProvider = (IItemLabelProvider) adapterFactory.adapt(item, IItemLabelProvider.class);
            if (labelProvider != null) {
                return labelProvider.getText(item);
            }
            return null;
        }

        /**
         * Get the most specific IPropertySheetPage adapted for the specified source.
         *
         * @param source
         *            the source part.
         * @param contributorId
         *            the contributor id.
         * @return a IPropertySheetPage adapted for the specified source, or <code>null</code> if none was contributed.
         */
        public IPropertySheetPage getPropertySheetPage(Object source, String contributorId) {
            for (ISiriusPropertySheetPageProvider provider : this.propertySheetPageProviders.get(contributorId)) {
                IPropertySheetPage page = provider.getPropertySheetPage(source, contributorId);
                if (page != null) {
                    return page;
                }
            }
            return null;
        }

        /**
         * Create the adapter factories.
         *
         * @return the created adapter factories
         */
        protected ComposedAdapterFactory createAdapterFactory() {
            List<ComposeableAdapterFactory> factories = new ArrayList<ComposeableAdapterFactory>();
            factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
            fillItemProviderFactories(factories);
            return new ComposedAdapterFactory(factories);
        }

        protected void fillItemProviderFactories(List<ComposeableAdapterFactory> factories) {
            factories.add(new ViewpointItemProviderAdapterFactory());
            factories.add(new DescriptionItemProviderAdapterFactory());
            factories.add(new StyleItemProviderAdapterFactory());
            factories.add(new ToolItemProviderAdapterFactory());
            factories.add(new ValidationItemProviderAdapterFactory());
            factories.add(new AuditItemProviderAdapterFactory());
            factories.add(new EcoreItemProviderAdapterFactory());
            factories.add(new ResourceItemProviderAdapterFactory());
            factories.add(new ReflectiveItemProviderAdapterFactory());
        }

        public AdapterFactory getItemProvidersAdapterFactory() {
            if (adapterFactory == null) {
                adapterFactory = createAdapterFactory();
            }
            return adapterFactory;
        }

        /**
         *
         * @param desc
         *            an image descriptor.
         * @return an Image instance
         */
        public Image getImage(ImageDescriptor desc) {
            if (!descriptorsToImages.containsKey(desc)) {
                descriptorsToImages.put(desc, desc.createImage());
            }
            return descriptorsToImages.get(desc);
        }

        /**
         * Return callBack.
         *
         * @return the uiCallback
         */
        public UICallBack getUiCallback() {
            return SiriusPlugin.getDefault().getUiCallback();
        }

        /**
         * Modify callBack.
         *
         * @param uiCallback
         *            the uiCallback to set
         */
        public void setUiCallback(UICallBack uiCallback) {
            SiriusPlugin.getDefault().setUiCallback(uiCallback);
        }

        /**
         * Return the preference store to control Sirius core preferences from the UI plugin. This method aims at having
         * only one instance of ScopedPreferenceStore for org.eclipse.sirius preference store.
         * 
         * @See {@link SiriusEditPlugin.Implementation#getPreferenceStore()}
         * @return the preference store to control Sirius core preferences from the UI plugin.
         */
        public IPreferenceStore getCorePreferenceStore() {
            // Create the preference store lazily.
            if (siriusCorePreferenceStore == null) {
                siriusCorePreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, SiriusPlugin.INSTANCE.getSymbolicName());
            }
            return siriusCorePreferenceStore;
        }
    }

    /**
     * Returns the ImageDescriptor that can be used to create the image resource associated with the key. The
     * description will typically be in the form of a URL to the image data.
     *
     * @param key
     *            the key of the image resource.
     * @return the ImageDescriptor on the image resource.
     * @not-generated
     */
    public ImageDescriptor getImageDescriptor(String key) {
        return ImageDescriptor.createFromURL((URL) getImage(key));
    }
}
