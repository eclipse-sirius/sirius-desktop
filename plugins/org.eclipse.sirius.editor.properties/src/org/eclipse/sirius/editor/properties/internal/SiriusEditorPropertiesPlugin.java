/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.editor.properties.api.IDefaultWidgetDescriptionFactory;
import org.eclipse.sirius.editor.properties.tools.internal.menu.widgets.DefaultBooleanDescriptionFactory;
import org.eclipse.sirius.editor.properties.tools.internal.menu.widgets.DefaultEnumerationDescriptionFactory;
import org.eclipse.sirius.editor.properties.tools.internal.menu.widgets.DefaultMonolineTextDescriptionFactory;
import org.eclipse.sirius.editor.properties.tools.internal.menu.widgets.DefaultMultilineTextDescriptionFactory;
import org.eclipse.sirius.editor.properties.tools.internal.menu.widgets.DefaultMultivaluedEAttributeDescriptionFactory;

/**
 * This is the central singleton for the SiriusEditorProperties edit plugin.
 * 
 * @author mbats
 */
public final class SiriusEditorPropertiesPlugin extends EMFPlugin {
    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.editor.properties"; //$NON-NLS-1$

    /**
     * Singleton instance.
     */
    public static final SiriusEditorPropertiesPlugin INSTANCE = new SiriusEditorPropertiesPlugin();

    private static Implementation plugin;

    /**
     * Create the instance.
     */
    public SiriusEditorPropertiesPlugin() {
        super(new ResourceLocator[0]);
    }

    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     */
    public static class Implementation extends EclipseUIPlugin {
        /**
         * The path of the icon used for the command creating a widget for all
         * the features.
         */
        public static final String CREATE_WIDGET_FOR_ALL_FEATURES_ICON_PATH = "icons/full16/CreateWidgetForAllFeatures.gif"; //$NON-NLS-1$

        /**
         * Creates an instance.
         */
        public Implementation() {
            plugin = this;
        }

        /**
         * Returns a list of all the default widget description factory which
         * can create a default widget description for the given domain class
         * and structural feature.
         * 
         * @param domainClass
         *            The domain class
         * @param eStructuralFeature
         *            The structural feature
         * @return A list of default widget description factory
         */
        public List<IDefaultWidgetDescriptionFactory> getDefaultWidgetDescriptionFactory(EClass domainClass, EStructuralFeature eStructuralFeature) {
            List<IDefaultWidgetDescriptionFactory> defaultFactories = new ArrayList<>();

            defaultFactories.add(new DefaultMonolineTextDescriptionFactory());
            defaultFactories.add(new DefaultMultilineTextDescriptionFactory());
            defaultFactories.add(new DefaultBooleanDescriptionFactory());
            defaultFactories.add(new DefaultEnumerationDescriptionFactory());
            defaultFactories.add(new DefaultMultivaluedEAttributeDescriptionFactory());

            List<IDefaultWidgetDescriptionFactory> factories = new ArrayList<>();
            for (IDefaultWidgetDescriptionFactory factory : defaultFactories) {
                if (factory.canCreate(domainClass, eStructuralFeature)) {
                    factories.add(factory);
                }
            }
            return factories;
        }
    }
}
