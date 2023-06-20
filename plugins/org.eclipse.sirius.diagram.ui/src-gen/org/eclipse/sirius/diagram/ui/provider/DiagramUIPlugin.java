/**
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.provider;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.Draw2dRenderPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.common.ui.tools.internal.preference.DynamicConfigurationHelper;
import org.eclipse.sirius.diagram.description.concern.provider.ConcernItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.description.filter.provider.FilterItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.provider.DiagramItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.business.internal.image.ImageSelectorDescriptorRegistryListener;
import org.eclipse.sirius.diagram.ui.business.internal.image.refresh.WorkspaceImageFigureRefresher;
import org.eclipse.sirius.diagram.ui.internal.layout.CustomLayoutAlgorithmProviderRegistry;
import org.eclipse.sirius.diagram.ui.internal.refresh.diagram.SiriusDiagramEventBrokerFactory;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.WorkspaceFileResourceChangeListener;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.SiriusDecorationProviderRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.decoration.DescribedDecorationDescriptorProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.decoration.EditModeDecorationDescriptorProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.decoration.SubDiagramDecorationDescriptorProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.decoration.ValidationDecorationDescriptorProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.format.data.extension.FormatDataManagerRegistryListener;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.CustomSiriusDocumentProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.ResourceMissingDocumentProvider;
import org.eclipse.sirius.viewpoint.description.audit.provider.AuditItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.validation.provider.ValidationItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.sirius.viewpoint.provider.ViewpointItemProviderAdapterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * This is the central singleton for the Diagram edit plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated-not
 */
public final class DiagramUIPlugin extends EMFPlugin {

    private static final String DECORATOR_CHECK_PATH = "icons/full/decorator/active.gif"; //$NON-NLS-1$

    public static final String ID = "org.eclipse.sirius.diagram.ui"; //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final PreferencesHint DIAGRAM_PREFERENCES_HINT = new PreferencesHint(DiagramUIPlugin.ID);

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final DiagramUIPlugin INSTANCE = new DiagramUIPlugin();

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
    public DiagramUIPlugin() {
        super(new ResourceLocator[] { EcoreEditPlugin.INSTANCE, SiriusEditPlugin.INSTANCE, });
    }

    /**
     * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the singleton instance.
     * @generated
     */
    @Override
    public ResourceLocator getPluginResourceLocator() {
        return DiagramUIPlugin.plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the singleton instance.
     * @generated
     */
    public static Implementation getPlugin() {
        return DiagramUIPlugin.plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated-not
     */
    public static class Implementation extends EclipseUIPlugin {

        private ComposedAdapterFactory adapterFactory;

        private ILabelProvider labelProvider;

        private Map<ImageWithDimensionDescriptor, Image> descriptorsToImages;

        private ResourceMissingDocumentProvider ressourceMissingDocumentProvider;

        private FormatDataManagerRegistryListener formatDataManagerRegistryListener;

        private ImageSelectorDescriptorRegistryListener imageSelectorDescriptorRegistryListener;

        private WorkspaceImageFigureRefresher workspaceImageFigureRefresher;

        private DynamicDiagramUIPreferences dynamicPreferences;

        /**
         * A registry containing all layout providers that can be specified directly in the VSM. A layout provider provides a
         * layout algorithm that can be used when doing an arrange all on a Sirius diagram.
         */
        private Map<String, CustomLayoutAlgorithm> layoutAlgorithmsRegistry;

        /**
         * A registry updating the {@link Implementation#layoutAlgorithmsRegistry} from extension point declarations.
         */
        private CustomLayoutAlgorithmProviderRegistry layoutAlgorithmProviderRegistry;

        /**
         * Creates an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        public Implementation() {
            super();

            // Remember the static instance.
            //
            DiagramUIPlugin.plugin = this;
        }

        /**
         * Add a new custom layout algorithm to the registry.
         *
         * @param customLayoutAlgorithm
         *                                  the layout algorithm to add.
         */
        public void addLayoutAlgorithm(CustomLayoutAlgorithm customLayoutAlgorithm) {
            layoutAlgorithmsRegistry.put(customLayoutAlgorithm.getId(), customLayoutAlgorithm);
        }

        /**
         * Remove the layout algorithm identified by the given id from the registry.
         *
         * @param layoutProviderId
         *                             the id of the layout algorithm to remove from the registry.
         * @return the layout algorithm removed if such element exists.
         */
        public CustomLayoutAlgorithm removeLayoutAlgorithm(String layoutProviderId) {
            return layoutAlgorithmsRegistry.remove(layoutProviderId);
        }

        /**
         * Returns the unmodifiable registry containing all layout providers that can be specified directly in the VSM.
         *
         * @return an unmodifiable map of CustomLayoutAlgorithm associated to their id.
         */
        public Map<String, CustomLayoutAlgorithm> getLayoutAlgorithms() {
            return Collections.unmodifiableMap(layoutAlgorithmsRegistry);
        }

        /**
         * Logs an error in the error log.
         *
         * @param message
         *                    the message to log (optional).
         * @param e
         *                    the exception (optional).
         */
        public void error(final String message, final Exception e) {
            String msgToDisplay = message;
            if (message == null && e != null) {
                msgToDisplay = e.getMessage();
            }
            if (e instanceof CoreException) {
                this.getLog().log(((CoreException) e).getStatus());
            } else {
                final IStatus status = new Status(IStatus.ERROR, this.getBundle().getSymbolicName(), msgToDisplay, e);
                this.getLog().log(status);
            }
        }

        /**
         * @not-generated create the image registry
         */
        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);
            // By default in Sirius, we don't want to consider the partial jump links. This is a new behavior available
            // in GMF since 1.13.0.
            PolylineConnectionEx.DISABLE_PARTIAL_JUMP_LINKS = true;
            org.eclipse.sirius.ext.gmf.runtime.draw2d.ui.figures.SiriusPolylineConnectionEx.DISABLE_PARTIAL_JUMP_LINKS = true;

            PreferencesHint.registerPreferenceStore(DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT, getPreferenceStore());
            dynamicPreferences = new DynamicDiagramUIPreferences(getPreferenceStore());

            adapterFactory = createAdapterFactory();
            descriptorsToImages = new HashMap<>();
            ressourceMissingDocumentProvider = new ResourceMissingDocumentProvider();

            workspaceImageFigureRefresher = new WorkspaceImageFigureRefresher();
            workspaceImageFigureRefresher.init();

            imageSelectorDescriptorRegistryListener = new ImageSelectorDescriptorRegistryListener();
            imageSelectorDescriptorRegistryListener.init();

            formatDataManagerRegistryListener = new FormatDataManagerRegistryListener();
            formatDataManagerRegistryListener.init();

            layoutAlgorithmsRegistry = new HashMap<>();

            layoutAlgorithmProviderRegistry = new CustomLayoutAlgorithmProviderRegistry(layoutAlgorithmsRegistry);
            Platform.getExtensionRegistry().addListener(layoutAlgorithmProviderRegistry, CustomLayoutAlgorithmProviderRegistry.LAYOUT_ALGORITHM_PROVIDER_EXTENSION_POINT_ID);

            registerCoreDecorationProviders();

            // Force initialization of org.eclipse.gmf.runtime.draw2d.ui.render.awt to register the Batik image formats
            // needed
            Draw2dRenderPlugin.getInstance();

            DiagramEventBroker.registerDiagramEventBrokerFactory(new SiriusDiagramEventBrokerFactory());
        }

        private void registerCoreDecorationProviders() {
            SiriusDecorationProviderRegistry.INSTANCE.addSiriusDecorationDescriptorProvider(new DescribedDecorationDescriptorProvider());
            SiriusDecorationProviderRegistry.INSTANCE.addSiriusDecorationDescriptorProvider(new EditModeDecorationDescriptorProvider());
            SiriusDecorationProviderRegistry.INSTANCE.addSiriusDecorationDescriptorProvider(new SubDiagramDecorationDescriptorProvider());
            SiriusDecorationProviderRegistry.INSTANCE.addSiriusDecorationDescriptorProvider(new ValidationDecorationDescriptorProvider());
        }

        private void unregisterDecorationProviders() {
            SiriusDecorationProviderRegistry.INSTANCE.clear();
        }

        /**
         * @not-generated disposing the images
         */
        @Override
        public void stop(BundleContext context) throws Exception {
            try {
                disposeLabelProvider();

                adapterFactory.dispose();
                adapterFactory = null;
            } catch (NullPointerException e) {
                // can occur when using CDO (if the view is
                // closed when transactions have been closed)
            } catch (IllegalStateException e) {
                // can occur when using CDO (if the view is
                // closed when transactions have been closed)
            }
            layoutAlgorithmsRegistry = null;
            formatDataManagerRegistryListener.dispose();
            formatDataManagerRegistryListener = null;

            workspaceImageFigureRefresher.dispose();
            workspaceImageFigureRefresher = null;

            imageSelectorDescriptorRegistryListener.dispose();
            imageSelectorDescriptorRegistryListener = null;

            layoutAlgorithmProviderRegistry = null;
            layoutAlgorithmsRegistry = null;
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
            /* dispose missing resources creation */
            this.ressourceMissingDocumentProvider.dispose();

            WorkspaceFileResourceChangeListener.getInstance().dispose();

            unregisterDecorationProviders();

            super.stop(context);
        }

        protected void disposeLabelProvider() {
            if (labelProvider instanceof INotifyChangedListener) {
                adapterFactory.removeListener((INotifyChangedListener) labelProvider);
            }
        }

        /**
         * @was-generated NOT use THE mighty factory
         */
        protected ComposedAdapterFactory createAdapterFactory() {
            List<AdapterFactory> factories = new ArrayList<>();
            factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
            fillItemProviderFactories(factories);
            return new ComposedAdapterFactory(factories);
        }

        /**
         * Return a new adapter factory.
         *
         * @return A new adapter factory
         */
        public ComposedAdapterFactory getNewAdapterFactory() {
            return createAdapterFactory();
        }

        protected ILabelProvider createLabelProvider() {
            if (labelProvider == null) {
                labelProvider = new AdapterFactoryLabelProvider(getItemProvidersAdapterFactory());
            }
            return labelProvider;
        }

        /**
         * @was-generated
         */
        protected void fillItemProviderFactories(List<AdapterFactory> factories) {
            factories.add(new ViewpointItemProviderAdapterFactory());
            factories.add(new DiagramItemProviderAdapterFactory());

            factories.add(new org.eclipse.sirius.viewpoint.description.provider.DescriptionItemProviderAdapterFactory());
            factories.add(new org.eclipse.sirius.diagram.description.provider.DescriptionItemProviderAdapterFactory());

            factories.add(new org.eclipse.sirius.viewpoint.description.style.provider.StyleItemProviderAdapterFactory());
            factories.add(new org.eclipse.sirius.diagram.description.style.provider.StyleItemProviderAdapterFactory());

            factories.add(new org.eclipse.sirius.viewpoint.description.tool.provider.ToolItemProviderAdapterFactory());
            factories.add(new org.eclipse.sirius.diagram.description.tool.provider.ToolItemProviderAdapterFactory());

            factories.add(new AuditItemProviderAdapterFactory());
            factories.add(new ConcernItemProviderAdapterFactory());
            factories.add(new FilterItemProviderAdapterFactory());
            factories.add(new ValidationItemProviderAdapterFactory());

            factories.add(new EcoreItemProviderAdapterFactory());
            factories.add(new ResourceItemProviderAdapterFactory());
            factories.add(new ReflectiveItemProviderAdapterFactory());
        }

        /**
         * @not-generated : recreate adapter factory if destroyed..
         */
        public AdapterFactory getItemProvidersAdapterFactory() {
            if (adapterFactory == null) {
                adapterFactory = createAdapterFactory();
            }
            return adapterFactory;
        }

        /**
         * Get the default label provider.
         *
         * @return the label provider single instance
         */
        public ILabelProvider getLabelProvider() {
            if (labelProvider == null) {
                createLabelProvider();
            }
            return labelProvider;
        }

        /**
         * @was-generated
         */
        public ImageDescriptor getItemImageDescriptor(Object item) {
            IItemLabelProvider labelProvider = (IItemLabelProvider) adapterFactory.adapt(item, IItemLabelProvider.class);
            if (labelProvider != null) {
                return ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(item));
            }
            return null;
        }

        /**
         * Returns an image descriptor for the image file at the given plug-in relative path.
         *
         * @was-generated
         * @param path
         *                 the path
         * @return the image descriptor
         */
        public static ImageDescriptor getBundledImageDescriptor(String path) {
            return AbstractUIPlugin.imageDescriptorFromPlugin(DiagramUIPlugin.ID, path);
        }

        /**
         * Returns an image descriptor for the image file at the given url.
         *
         * @param url
         *                the url
         * @return the image descriptor
         */
        public static ImageDescriptor getURLImageDescriptor(URL url) {
            return ImageDescriptor.createFromURL(url);
        }

        /**
         * Respects images residing in any plug-in. If path is relative, then this bundle is looked up for the image, otherwise,
         * for absolute path, first segment is taken as id of plug-in with image
         *
         * @was-generated
         * @param path
         *                 the path to image, either absolute (with plug-in id as first segment), or relative for bundled images
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
         * Respects images residing in any plug-in. If path is relative, then this bundle is looked up for the image, otherwise,
         * for absolute path, first segment is taken as id of plug-in with image
         *
         * @param path
         *                 the path to image, either absolute (with plug-in id as first segment), or relative for bundled images
         * @return the image descriptor
         */
        public static ImageWithDimensionDescriptor findImageWithDimensionDescriptor(String path, final Dimension dimension) {
            ImageDescriptor desc = Implementation.findImageDescriptor(path);
            if (desc != null) {
                return new ImageWithDimensionDescriptorImpl(desc, dimension);
            }
            return null;
        }

        /**
         * Respects images residing in any plug-in. If path is relative, then this bundle is looked up for the image, otherwise,
         * for absolute path, first segment is taken as id of plug-in with image
         *
         * @param path
         *                 the path to image, either absolute (with plug-in id as first segment), or relative for bundled images
         * @return the image descriptor
         */
        public static ImageWithDimensionDescriptor findImageWithDimensionDescriptor(String path) {
            return Implementation.findImageWithDimensionDescriptor(path, ImageWithDimensionDescriptor.NO_RESIZE);
        }

        /**
         * Returns an image descriptor for the image file at the given URL.
         *
         * @param url
         *                the URL.
         * @return the image descriptor.
         */
        public static ImageDescriptor findImageDescriptor(URL url) {
            return Implementation.getURLImageDescriptor(url);
        }

        /**
         * @param imageDescriptor
         * @return the image descriptor with the check decorator
         */
        public static ImageDescriptor getDecoratedCheckedImageDescriptor(final ImageDescriptor imageDescriptor) {
            return Implementation.getDecoratedImageDescriptor(imageDescriptor, DiagramUIPlugin.DECORATOR_CHECK_PATH);
        }

        /**
         * @param image
         * @return the image with the check decorator
         */
        public static Image getDecoratedCheckedImage(final ImageDescriptor imageDescriptor) {
            return Implementation.getDecoratedImage(imageDescriptor, DiagramUIPlugin.DECORATOR_CHECK_PATH);
        }

        /**
         * @param imageDescriptor
         * @param decoratorPath
         * @return the image descriptor decorated
         */
        public static ImageDescriptor getDecoratedImageDescriptor(final ImageDescriptor imageDescriptor, String decoratorPath) {
            if (imageDescriptor != null) {
                return Implementation.getOverlayedDescriptor(DiagramUIPlugin.getPlugin().getImage(imageDescriptor), decoratorPath);
            }
            return imageDescriptor;
        }

        /**
         * @param imagePath
         * @param decoratorPath
         * @return the image descriptor decorated
         */
        public static ImageDescriptor getDecoratedImageDescriptor(final String imagePath, String decoratorPath) {
            return Implementation.getDecoratedImageDescriptor(Implementation.getBundledImageDescriptor(imagePath), decoratorPath);
        }

        /**
         * @param image
         * @param decoratorPath
         * @return the image decorated
         */
        public static Image getDecoratedImage(final ImageDescriptor imageDescriptor, String decoratorPath) {
            Image image = DiagramUIPlugin.getPlugin().getImage(imageDescriptor);
            if (image != null) {
                return DiagramUIPlugin.getPlugin().getImage(Implementation.getOverlayedDescriptor(image, decoratorPath));
            }
            return image;
        }

        /**
         * @param image
         * @param decoratorPath
         * @return the image decorated
         */
        public static Image getDecoratedImage(Image image, String decoratorPath) {
            if (image != null) {
                return DiagramUIPlugin.getPlugin().getImage(Implementation.getOverlayedDescriptor(image, decoratorPath));
            }
            return image;
        }

        private static ImageDescriptor getOverlayedDescriptor(final Image baseImage, final String decoratorPath) {
            final ImageDescriptor decoratorDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(decoratorPath);
            return new DecorationOverlayIcon(baseImage, decoratorDescriptor, IDecoration.BOTTOM_RIGHT);
        }

        /**
         * Returns an image for the image file at the given plug-in relative path. Client do not need to dispose this image.
         * Images will be disposed automatically.
         *
         * @was-generated
         * @param path
         *                 the path
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
         *
         * @param desc
         *                 an image descriptor.
         * @return an Image instance
         */
        public Image getImage(ImageWithDimensionDescriptor desc) {
            if (!descriptorsToImages.containsKey(desc)) {
                final ImageWithDimensionDescriptor fullSizeDesc = new ImageWithDimensionDescriptorImpl(desc.getImageDescriptor());

                if (descriptorsToImages.containsKey(fullSizeDesc)) {
                    final Image fullSize = descriptorsToImages.get(fullSizeDesc);
                    final ImageDescriptor descToUse = ImageDescriptor.createFromImageData(fullSize.getImageData().scaledTo(desc.getDimension().width, desc.getDimension().height));
                    descriptorsToImages.put(desc, descToUse.createImage());
                } else {
                    descriptorsToImages.put(fullSizeDesc, fullSizeDesc.getImageDescriptor().createImage());
                }
                return getImage(desc);
            }
            return descriptorsToImages.get(desc);
        }

        /**
         *
         * @param desc
         *                 an image descriptor.
         * @return an Image instance
         */
        public Image getImage(ImageDescriptor desc) {
            final ImageWithDimensionDescriptor realDesc = new ImageWithDimensionDescriptorImpl(desc);
            if (!descriptorsToImages.containsKey(realDesc)) {
                descriptorsToImages.put(realDesc, desc.createImage());
            }
            return descriptorsToImages.get(realDesc);
        }

        /**
         *
         * @param desc
         *                 an image descriptor.
         * @return an Image instance
         */
        public boolean removeCacheImage(ImageDescriptor desc) {
            final ImageWithDimensionDescriptor realDesc = new ImageWithDimensionDescriptorImpl(desc);
            return removeCacheImage(realDesc);
        }

        private boolean removeCacheImage(ImageWithDimensionDescriptor realDesc) {
            return descriptorsToImages.remove(realDesc) != null;
        }

        /**
         * Get the missing resource document provider.
         *
         * @return the single instance to handle missing document
         */
        public ResourceMissingDocumentProvider getResourceMissingDocumentProvider() {
            return this.ressourceMissingDocumentProvider;
        }

        /**
         * @param transactionalEditingDomain
         * @not-generated
         * @since 0.9.0
         */
        public CustomSiriusDocumentProvider getDocumentProvider(TransactionalEditingDomain transactionalEditingDomain) {
            return new CustomSiriusDocumentProvider(transactionalEditingDomain);
        }

        public DynamicDiagramUIPreferences getDynamicPreferences() {
            return dynamicPreferences;
        }

        /**
         * This class is a mean to keep sync with possible changes of the {@link SiriusDiagramUiPreferencesKeys} preferences.
         */
        public class DynamicDiagramUIPreferences extends DynamicConfigurationHelper {
            /**
             * @see SiriusDiagramUiPreferencesKeys.PREF_AUTHORIZE_DECORATION_OVERLAP
             */
            private boolean authorizeDecorationOverlap;

            DynamicDiagramUIPreferences(IPreferenceStore store) {
                super(store);
                bindBoolean(SiriusDiagramUiPreferencesKeys.PREF_AUTHORIZE_DECORATION_OVERLAP.name(), "authorizeDecorationOverlap"); //$NON-NLS-1$
            }

            public boolean authorizeDecorationOverlap() {
                return authorizeDecorationOverlap;
            }
        }
    }

}
