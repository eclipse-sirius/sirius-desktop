/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.graphical.edit.styles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.BundledImage;
import org.eclipse.sirius.BundledImageShape;
import org.eclipse.sirius.Square;
import org.eclipse.sirius.Style;
import org.eclipse.sirius.WorkspaceImage;
import org.eclipse.sirius.business.api.session.SessionManagerListener2;
import org.eclipse.sirius.description.DiagramElementMapping;
import org.eclipse.sirius.description.Sirius;
import org.eclipse.sirius.description.style.StyleDescription;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.SafeStyleConfiguration;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration;

/**
 * Registry of configurations.
 * 
 * @author ymortier
 */
public final class StyleConfigurationRegistry extends SessionManagerListener2.Stub implements IStyleConfigurationRegistry {

    private static final ProfilerTask GET_CONFIG = new ProfilerTask("Sirius", "get style configuration");

    /** All providers. */
    private static List<IStyleConfigurationProvider> styleConfigurationProviders = new ArrayList<IStyleConfigurationProvider>();

    /** Name of the extension point to parse for style configuration providers. */
    private static final String STYLE_CONFIGURATION_PROVIDER_EXTENSION_POINT = "org.eclipse.sirius.diagram.styleConfigurationProvider"; //$NON-NLS-1$

    /** Externalized here to avoid too many distinct usages. */
    private static final String TAG_ENGINE = "styleConfigurationProvider";

    static {
        StyleConfigurationRegistry.parseExtensionMetadata();
    }

    /** The shared INSTANCE. */
    private static final IStyleConfigurationRegistry INSTANCE = new StyleConfigurationRegistry();

    /** Map of styles to StyleConfiguration. */
    private final Map<StyleWrapper, StyleConfiguration> styleToConfig = new WeakHashMap<StyleWrapper, StyleConfiguration>();

    /**
     * Avoid instantiation.
     */
    private StyleConfigurationRegistry() {
        // empty.
    }

    /**
     * This will parse the currently running platform for extensions and store
     * all the match engines that can be found.
     */
    private static void parseExtensionMetadata() {
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            final IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(STYLE_CONFIGURATION_PROVIDER_EXTENSION_POINT).getExtensions();
            for (IExtension extension : extensions) {
                final IConfigurationElement[] configElements = extension.getConfigurationElements();
                for (IConfigurationElement configElement : configElements) {
                    if (configElement.getName().equals(TAG_ENGINE)) {
                        try {
                            final IStyleConfigurationProvider styleConfigurationProvider = (IStyleConfigurationProvider) configElement.createExecutableExtension("providerClass");
                            StyleConfigurationRegistry.styleConfigurationProviders.add(styleConfigurationProvider);
                        } catch (final CoreException e) {
                            SiriusDiagramEditorPlugin.getInstance().logError("Impossible to load the style configuration provider : " + configElement.getName(), e);
                        }
                    }
                }
            }
        }
    }

    /**
     * Return the registry.
     * 
     * @return the registry.
     */
    public static IStyleConfigurationRegistry getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IStyleConfigurationRegistry#getStyleConfiguration(org.eclipse.sirius.description.DiagramElementMapping,
     *      org.eclipse.sirius.Style)
     */
    public StyleConfiguration getStyleConfiguration(final DiagramElementMapping vpElementMapping, final Style style) {
        StyleConfiguration result = new SimpleStyleConfiguration();
        DslCommonPlugin.PROFILER.startWork(GET_CONFIG);
        if (style != null) {
            final StyleWrapper styleWrapper = new StyleWrapper(vpElementMapping, style.getDescription());
            result = getStyleConfiguration(styleWrapper, style);
        }
        DslCommonPlugin.PROFILER.stopWork(GET_CONFIG);
        return result;
    }

    /**
     * Return the {@link StyleConfiguration} corresponding to the specified
     * {@link StyleWrapper}.
     * 
     * @param styleWrapper
     *            the {@link StyleWrapper}.
     * @param style
     *            the style of the element that needs a configuration
     * @return the {@link StyleConfiguration} corresponding to the specified
     *         {@link StyleWrapper}.
     */
    private StyleConfiguration getStyleConfiguration(final StyleWrapper styleWrapper, final Style style) {
        StyleConfiguration styleConfiguration = this.styleToConfig.get(styleWrapper);
        if (styleConfiguration == null) {
            styleConfiguration = createStyleConfiguration(styleWrapper.getVpElementMapping(), style);
            this.styleToConfig.put(styleWrapper, styleConfiguration);
        }
        return styleConfiguration;
    }

    /**
     * Create a {@link StyleConfiguration} corresponding to the specified
     * {@link DiagramElementMapping} and {@link Style}.
     * 
     * @param vpElementMapping
     *            the view point element to customize.
     * @param style
     *            the {@link Style}.
     * @return a {@link StyleConfiguration} corresponding to the specified
     *         {@link Style}.
     */
    private StyleConfiguration createStyleConfiguration(final DiagramElementMapping vpElementMapping, final Style style) {
        //
        // call providers...
        final Iterator<IStyleConfigurationProvider> iterProviders = Collections.unmodifiableList(StyleConfigurationRegistry.styleConfigurationProviders).iterator();
        while (iterProviders.hasNext()) {
            final IStyleConfigurationProvider styleConfigurationProvider = iterProviders.next();
            if (safeProvides(vpElementMapping, style, styleConfigurationProvider, iterProviders)) {
                final StyleConfiguration styleConfiguration = styleConfigurationProvider.createStyleConfiguration(vpElementMapping, style);
                if (styleConfiguration != null) {
                    return new SafeStyleConfiguration(styleConfiguration);
                }
            }
        }
        StyleConfiguration styleConfiguration = null;
        // create default style :
        if (style instanceof BundledImage) {
            final BundledImage bundledImage = (BundledImage) style;
            if (bundledImage.getShape() == BundledImageShape.SQUARE_LITERAL) {
                styleConfiguration = new SquareStyleConfiguration();
            }
        } else if (style instanceof WorkspaceImage) {
            styleConfiguration = new ImageStyleConfiguration();
        } else if (style instanceof Square) {
            styleConfiguration = new SimpleSquareStyleConfiguration();
        }
        if (styleConfiguration == null) {
            styleConfiguration = new SimpleStyleConfiguration();
        }
        return styleConfiguration;
    }

    /**
     * Safe provides ! Catch exception to preserve the integrity of designer.
     */
    private boolean safeProvides(final DiagramElementMapping vpElementMapping, final Style style, final IStyleConfigurationProvider styleConfigurationProvider,
            Iterator<IStyleConfigurationProvider> currentIterator) {
        boolean result = false;
        try {
            if (vpElementMapping != null) {
                result = styleConfigurationProvider.provides(vpElementMapping, style);
            }
            // CHECKSTYLE:OFF
        } catch (final Exception e) {
            // CHECKSTYLE:ON
            SiriusDiagramEditorPlugin.getInstance().logError(
                    "The style configuration provider " + styleConfigurationProvider.getClass().getName() + " has threw an exception in its provides method and has been deactivated.", e);
            currentIterator.remove();
        }
        return result;
    }

    /**
     * This class wraps a {@link DiagramElementMapping} and {@link Style} to
     * redefine the equals and hashCode methods.
     * 
     * @author ymortier
     */
    private static class StyleWrapper {

        /** The style description. */
        private final StyleDescription style;

        /** The viewpoint element mapping. */
        private final DiagramElementMapping vpElementMapping;

        /** The cached hashCode. */
        private Integer hashCode;

        /**
         * Create a new {@link StyleWrapper}.
         * 
         * @param vpElementMapping
         *            the viewpoint element.
         * @param styleDescription
         *            the styledescription.
         */
        public StyleWrapper(final DiagramElementMapping vpElementMapping, final StyleDescription styleDescription) {
            this.vpElementMapping = vpElementMapping;
            this.style = styleDescription;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            if (hashCode == null) {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((style == null) ? 0 : EcoreUtil.getURI(style).hashCode());
                result = prime * result + ((vpElementMapping == null) ? 0 : EcoreUtil.getURI(vpElementMapping).hashCode());
                hashCode = Integer.valueOf(result);
            }
            return hashCode.intValue();
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof StyleWrapper) {
                final StyleWrapper styleWrapper = (StyleWrapper) obj;
                boolean areEquals;
                if (styleWrapper.style == null || this.style == null) {
                    areEquals = styleWrapper.style == this.style;
                } else {
                    areEquals = EqualityHelper.areEquals(this.style, styleWrapper.style);
                }
                boolean result = false;
                if (styleWrapper.vpElementMapping == null || this.vpElementMapping == null) {
                    result = areEquals && styleWrapper.vpElementMapping == this.vpElementMapping;
                } else {
                    result = areEquals && EqualityHelper.areEquals(styleWrapper.vpElementMapping, this.vpElementMapping);
                }
                return result;
            }
            return false;
        }

        /**
         * Return the vpElementMapping.
         * 
         * @return the vpElementMapping.
         */
        public DiagramElementMapping getVpElementMapping() {
            return this.vpElementMapping;
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointDeselected(org.eclipse.sirius.description.Sirius)
     */
    @Override
    public void viewpointDeselected(final Sirius deselectedSirius) {
        final Set<StyleWrapper> styleWrappersToDelete = new HashSet<StyleWrapper>();
        for (StyleWrapper wrapper : styleToConfig.keySet()) {
            if (EcoreUtil.isAncestor(deselectedSirius, wrapper.getVpElementMapping())) {
                styleWrappersToDelete.add(wrapper);
            }
        }
        for (final StyleWrapper wrapperToDelete : styleWrappersToDelete) {
            styleToConfig.remove(wrapperToDelete);
        }
    }

}
