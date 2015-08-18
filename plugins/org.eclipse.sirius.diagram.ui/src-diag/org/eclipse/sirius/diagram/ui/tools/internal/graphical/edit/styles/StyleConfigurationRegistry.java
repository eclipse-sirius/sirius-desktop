/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.styles;

import java.lang.ref.WeakReference;
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
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SafeStyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * Registry of configurations.
 * 
 * @author ymortier
 */
public final class StyleConfigurationRegistry extends SessionManagerListener.Stub implements IStyleConfigurationRegistry {

    private static final SimpleStyleConfiguration DEFAULT_CONFIGURATION = new SimpleStyleConfiguration();

    private static final ProfilerTask GET_CONFIG = new ProfilerTask("Sirius", "get style configuration");

    /** All providers. */
    private static List<IStyleConfigurationProvider> styleConfigurationProviders = new ArrayList<IStyleConfigurationProvider>();

    /** Name of the extension point to parse for style configuration providers. */
    private static final String STYLE_CONFIGURATION_PROVIDER_EXTENSION_POINT = "org.eclipse.sirius.diagram.ui.styleConfigurationProvider"; //$NON-NLS-1$

    /** Externalized here to avoid too many distinct usages. */
    private static final String TAG_ENGINE = "styleConfigurationProvider"; //$NON-NLS-1$

    static {
        StyleConfigurationRegistry.parseExtensionMetadata();
    }

    /** The shared INSTANCE. */
    private static final IStyleConfigurationRegistry INSTANCE = new StyleConfigurationRegistry();

    /** Map of styles to StyleConfiguration. */
    private final Map<StyleWrapper, StyleConfiguration> styleToConfig = new WeakHashMap<StyleWrapper, StyleConfiguration>();

    /** Cache of the logical arguments and result of the previous invocation. */
    private WeakReference<Invocation> lastInvocation;

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
                            final IStyleConfigurationProvider styleConfigurationProvider = (IStyleConfigurationProvider) configElement.createExecutableExtension("providerClass"); //$NON-NLS-1$
                            StyleConfigurationRegistry.styleConfigurationProviders.add(styleConfigurationProvider);
                        } catch (final CoreException e) {
                            DiagramPlugin.getDefault().logError("Impossible to load the style configuration provider : " + configElement.getName(), e);
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

    @Override
    public StyleConfiguration getStyleConfiguration(final DiagramElementMapping vpElementMapping, final Style style) {
        StyleConfiguration result = null;
        DslCommonPlugin.PROFILER.startWork(GET_CONFIG);
        if (style != null) {
            // Fast path: for repeated calls with the same mapping & style
            // description (very common case), avoid the whole overhead of
            // StyleWrapper and its costly hashCode() computation.
            StyleDescription styleDescription = style.getDescription();
            if (lastInvocation != null) {
                Invocation inv = lastInvocation.get();
                if (inv != null && inv.matches(vpElementMapping, styleDescription)) {
                    result = inv.configuration;
                }
            }
            if (result == null) {
                // Slow path
                StyleWrapper styleWrapper = new StyleWrapper(vpElementMapping, styleDescription);
                result = getStyleConfiguration(styleWrapper, style);
                lastInvocation = new WeakReference<StyleConfigurationRegistry.Invocation>(new Invocation(vpElementMapping, styleDescription, result));
            }
        } else {
            result = StyleConfigurationRegistry.DEFAULT_CONFIGURATION;
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
            styleConfiguration = createStyleConfiguration(styleWrapper.vpElementMapping, style);
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
            styleConfiguration = StyleConfigurationRegistry.DEFAULT_CONFIGURATION;
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
            DiagramPlugin.getDefault().logError(
                    "The style configuration provider " + styleConfigurationProvider.getClass().getName() + " has threw an exception in its provides method and has been deactivated.", e);
            currentIterator.remove();
        }
        return result;
    }

    /**
     * Encapsulate the (logical) parameters and result of a single invocation to
     * getStyleConfiguration(). Used to optimize the common case where
     * getStyleConfiguration() will be called many times in a row with different
     * Style instances, but all pointing to the same StyleDescription (the
     * actual value relevant to the computation of the result).
     */
    private static final class Invocation {
        DiagramElementMapping elementMapping;

        StyleDescription styleDescription;

        StyleConfiguration configuration;

        Invocation(DiagramElementMapping mapping, StyleDescription styleDescription, StyleConfiguration configuration) {
            this.elementMapping = mapping;
            this.styleDescription = styleDescription;
            this.configuration = configuration;
        }

        boolean matches(DiagramElementMapping mapping, StyleDescription styleDesc) {
            return this.elementMapping == mapping && this.styleDescription == styleDesc;
        }
    }

    /**
     * This class wraps a {@link DiagramElementMapping} and {@link Style} to
     * redefine the equals and hashCode methods.
     * 
     * @author ymortier
     */
    private static class StyleWrapper {

        /** The viewpoint element mapping. */
        final DiagramElementMapping vpElementMapping;

        /** The style description. */
        private final StyleDescription style;

        /** The cached hashCode. */
        private final int hashCode;

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
            this.hashCode = computeHashCode();
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        protected int computeHashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((style == null) ? 0 : EcoreUtil.getURI(style).hashCode());
            result = prime * result + ((vpElementMapping == null) ? 0 : EcoreUtil.getURI(vpElementMapping).hashCode());
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof StyleWrapper) {
                StyleWrapper styleWrapper = (StyleWrapper) obj;
                return EqualityHelper.areEquals(this.style, styleWrapper.style) && EqualityHelper.areEquals(styleWrapper.vpElementMapping, this.vpElementMapping);
            } else {
                return false;
            }
        }
    }

    @Override
    public void viewpointDeselected(Viewpoint deselectedSirius) {
        Set<StyleWrapper> styleWrappersToDelete = new HashSet<StyleWrapper>();
        for (StyleWrapper wrapper : styleToConfig.keySet()) {
            if (EcoreUtil.isAncestor(deselectedSirius, wrapper.vpElementMapping)) {
                styleWrappersToDelete.add(wrapper);
            }
        }
        for (final StyleWrapper wrapperToDelete : styleWrappersToDelete) {
            styleToConfig.remove(wrapperToDelete);
        }
    }

}
