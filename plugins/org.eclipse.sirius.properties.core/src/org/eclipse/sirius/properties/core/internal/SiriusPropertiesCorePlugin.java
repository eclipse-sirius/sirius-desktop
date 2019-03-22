/*******************************************************************************
 * Copyright (c) 2017, 2019 Obeo.
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
package org.eclipse.sirius.properties.core.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.eef.EefPackage;
import org.eclipse.eef.common.api.AbstractEEFEclipsePlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.sirius.ext.ide.api.AbstractRegistryEventListener;
import org.eclipse.sirius.ext.ide.api.DescriptorRegistryEventListener;
import org.eclipse.sirius.ext.ide.api.IItemDescriptor;
import org.eclipse.sirius.ext.ide.api.IItemRegistry;
import org.eclipse.sirius.ext.ide.api.ItemRegistry;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionConverter;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionWithInitialOperationConverter;
import org.eclipse.sirius.properties.core.api.IDescriptionConverter;
import org.eclipse.sirius.properties.core.api.IDescriptionLinkResolver;
import org.eclipse.sirius.properties.core.api.IDescriptionPreprocessor;
import org.eclipse.sirius.properties.core.internal.converter.PropertiesDescriptionConverterSwitch;
import org.eclipse.sirius.properties.core.internal.converter.PropertyValidationRuleLinkResolver;
import org.eclipse.sirius.properties.core.internal.converter.SemanticValidationRuleDescriptionConverter;
import org.eclipse.sirius.properties.core.internal.preprocessor.GroupDescriptionPreprocessorLinkResolver;
import org.eclipse.sirius.properties.core.internal.preprocessor.PropertiesDescriptionPreprocessorSwitch;
import org.eclipse.sirius.properties.core.internal.preprocessor.PropertyValidationRulePreprocessorLinkResolver;
import org.eclipse.sirius.tools.internal.validation.EValidatorAdapter;
import org.eclipse.sirius.viewpoint.description.validation.RuleAudit;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;
import org.osgi.framework.BundleContext;

/**
 * The plugin for <code>org.eclipse.sirius.properties.core</code>.
 * 
 * @author sbegaudeau
 */
@SuppressWarnings("restriction") // EValidatorAdapter for the validation of the properties
public class SiriusPropertiesCorePlugin extends EMFPlugin {
    /**
     * The identifier of the plugin.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.properties.core"; //$NON-NLS-1$

    /**
     * The sole instance of the plugin.
     */
    public static final SiriusPropertiesCorePlugin INSTANCE = new SiriusPropertiesCorePlugin();

    /**
     * The sole instance of the bundle activator.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    public SiriusPropertiesCorePlugin() {
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
     * The bundle activator.
     * 
     * @author sbegaudeau
     */
    public static class Implementation extends AbstractEEFEclipsePlugin {
        /**
         * The name of the extension point for the {@link IDescriptionConverter} .
         */
        private static final String DESCRIPTION_CONVERTER_EXTENSION_POINT = "descriptionConverter"; //$NON-NLS-1$

        /**
         * The name of the extension point for the {@link IDescriptionPreprocessor} .
         */
        private static final String DESCRIPTION_PREPROCESSOR_EXTENSION_POINT = "descriptionPreprocessor"; //$NON-NLS-1$

        /**
         * The name of the extension point for the {@link IDescriptionLinkResolver}.
         */
        private static final String DESCRIPTION_CONVERTER_LINK_RESOLVER_EXTENSION_POINT = "descriptionLinkResolver"; //$NON-NLS-1$

        /**
         * The name of the extension point for the {@link IDescriptionLinkResolver}.
         */
        private static final String DESCRIPTION_PREPROCESSOR_LINK_RESOLVER_EXTENSION_POINT = "descriptionPreprocessorLinkResolver"; //$NON-NLS-1$

        /**
         * The {@link IItemRegistry} used to retrieve the {@link IDescriptionConverter}.
         */
        private IItemRegistry<IDescriptionConverter> descriptionConverterRegistry;

        /**
         * The {@link IItemRegistry} used to retrieve the {@link IDescriptionPreprocessor}.
         */
        private IItemRegistry<IDescriptionPreprocessor> descriptionPreprocessorRegistry;

        /**
         * The {@link IItemRegistry} used to retrieve the {@link IDescriptionLinkResolver}.
         */
        private IItemRegistry<IDescriptionLinkResolver> descriptionConverterLinkResolverRegistry;

        /**
         * The {@link IItemRegistry} used to retrieve the {@link IDescriptionLinkResolver}.
         */
        private IItemRegistry<IDescriptionLinkResolver> descriptionPreprocessorLinkResolverRegistry;

        /**
         * The extension registry listener for the {@link IDescriptionConverter} .
         */
        private AbstractRegistryEventListener descriptionConverterListener;

        /**
         * The extension registry listener for the {@link IDescriptionPreprocessor} .
         */
        private AbstractRegistryEventListener descriptionPreprocessorListener;

        /**
         * The extension registry listener for the {@link IDescriptionLinkResolver}.
         */
        private AbstractRegistryEventListener descriptionConverterLinkResolverListener;

        /**
         * The extension registry listener for the {@link IDescriptionLinkResolver}.
         */
        private AbstractRegistryEventListener descriptionPreprocessorLinkResolverListener;

        /**
         * Check if the converter registry has been read.
         */
        private boolean hasReadDescriptionConverterRegistry;

        /**
         * Check if the converter link resolver registry has been read.
         */
        private boolean hasReadDescriptionConverterLinkResolverRegistry;

        /**
         * Check if the preprocessor registry has been read.
         */
        private boolean hasReadDescriptionPreprocessorRegistry;

        /**
         * Check if the preprocessor link resolver registry has been read.
         */
        private boolean hasReadDescriptionPreprocessorLinkResolverRegistry;

        /**
         * The constructor.
         */
        public Implementation() {
            super(PLUGIN_ID);
            SiriusPropertiesCorePlugin.plugin = this;
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);

            // Sets the validator for these model.
            EValidator.Registry.INSTANCE.put(PropertiesPackage.eINSTANCE, new EValidatorAdapter());

            IExtensionRegistry registry = Platform.getExtensionRegistry();
            this.descriptionConverterRegistry = new ItemRegistry<>();
            this.descriptionConverterListener = new DescriptorRegistryEventListener<>(PLUGIN_ID, DESCRIPTION_CONVERTER_EXTENSION_POINT, this.descriptionConverterRegistry);
            registry.addListener(this.descriptionConverterListener, PLUGIN_ID + '.' + DESCRIPTION_CONVERTER_EXTENSION_POINT);

            this.descriptionPreprocessorRegistry = new ItemRegistry<>();
            this.descriptionPreprocessorListener = new DescriptorRegistryEventListener<>(PLUGIN_ID, DESCRIPTION_PREPROCESSOR_EXTENSION_POINT, this.descriptionPreprocessorRegistry);
            registry.addListener(this.descriptionPreprocessorListener, PLUGIN_ID + '.' + DESCRIPTION_PREPROCESSOR_EXTENSION_POINT);

            this.descriptionConverterLinkResolverRegistry = new ItemRegistry<>();
            this.descriptionConverterLinkResolverListener = new DescriptorRegistryEventListener<>(PLUGIN_ID, DESCRIPTION_CONVERTER_LINK_RESOLVER_EXTENSION_POINT,
                    this.descriptionConverterLinkResolverRegistry);
            registry.addListener(this.descriptionConverterLinkResolverListener, PLUGIN_ID + '.' + DESCRIPTION_CONVERTER_LINK_RESOLVER_EXTENSION_POINT);

            this.descriptionPreprocessorLinkResolverRegistry = new ItemRegistry<>();
            this.descriptionPreprocessorLinkResolverListener = new DescriptorRegistryEventListener<>(PLUGIN_ID, DESCRIPTION_PREPROCESSOR_LINK_RESOLVER_EXTENSION_POINT,
                    this.descriptionPreprocessorLinkResolverRegistry);
            registry.addListener(this.descriptionPreprocessorLinkResolverListener, PLUGIN_ID + '.' + DESCRIPTION_PREPROCESSOR_LINK_RESOLVER_EXTENSION_POINT);

        }

        @Override
        public void stop(BundleContext context) throws Exception {
            IExtensionRegistry registry = Platform.getExtensionRegistry();
            registry.removeListener(this.descriptionConverterListener);
            registry.removeListener(this.descriptionConverterLinkResolverListener);
            registry.removeListener(this.descriptionPreprocessorListener);
            registry.removeListener(this.descriptionPreprocessorLinkResolverListener);

            this.descriptionConverterListener = null;
            this.descriptionConverterRegistry = null;
            this.descriptionConverterLinkResolverListener = null;
            this.descriptionConverterLinkResolverRegistry = null;

            this.descriptionPreprocessorListener = null;
            this.descriptionPreprocessorRegistry = null;
            this.descriptionPreprocessorLinkResolverListener = null;
            this.descriptionPreprocessorLinkResolverRegistry = null;

            super.stop(context);
        }

        /**
         * Returns the description converter used to create the EEF description EObject from the given Sirius one.
         * 
         * @param description
         *            The Sirius description EObject
         * @return The converter found or <code>null</code> if none could be found
         */
        public Optional<IDescriptionConverter> getDescriptionConverter(EObject description) {
            if (!this.hasReadDescriptionConverterRegistry) {
                this.descriptionConverterListener.readRegistry(Platform.getExtensionRegistry());
                this.hasReadDescriptionConverterRegistry = true;
            }
            List<IItemDescriptor<IDescriptionConverter>> itemDescriptors = this.descriptionConverterRegistry.getItemDescriptors();
            Optional<IDescriptionConverter> converter = itemDescriptors.stream().map(IItemDescriptor::getItem).filter(descriptionConverter -> descriptionConverter.canHandle(description)).findFirst();

            if (!converter.isPresent()) {
                if (description instanceof SemanticValidationRule) {
                    converter = Optional.of(new SemanticValidationRuleDescriptionConverter());
                } else if (description instanceof RuleAudit) {
                    converter = Optional.of(new DefaultDescriptionConverter<>(RuleAudit.class, EefPackage.Literals.EEF_RULE_AUDIT_DESCRIPTION));
                } else if (description instanceof ValidationFix) {
                    converter = Optional.of(new DefaultDescriptionWithInitialOperationConverter<>(ValidationFix.class, EefPackage.Literals.EEF_VALIDATION_FIX_DESCRIPTION,
                            EefPackage.Literals.EEF_VALIDATION_FIX_DESCRIPTION__FIX_EXPRESSION));
                } else {
                    PropertiesDescriptionConverterSwitch descriptionConverterSwitch = new PropertiesDescriptionConverterSwitch();
                    converter = descriptionConverterSwitch.doSwitch(description);
                }
            }
            return converter;
        }

        /**
         * Returns the description preprocessor used to create the Sirius resolved description EObject from the given
         * Sirius one.
         * 
         * @param description
         *            The Sirius description EObject
         * @return The preprocessor found or <code>null</code> if none could be found
         */
        public Optional<IDescriptionPreprocessor> getDescriptionPreprocessor(EObject description) {
            if (!this.hasReadDescriptionPreprocessorRegistry) {
                this.descriptionPreprocessorListener.readRegistry(Platform.getExtensionRegistry());
                this.hasReadDescriptionPreprocessorRegistry = true;
            }
            List<IItemDescriptor<IDescriptionPreprocessor>> itemDescriptors = this.descriptionPreprocessorRegistry.getItemDescriptors();
            Optional<IDescriptionPreprocessor> preprocessor = itemDescriptors.stream().map(IItemDescriptor::getItem).filter(descriptionPreprocessor -> descriptionPreprocessor.canHandle(description))
                    .findFirst();

            if (!preprocessor.isPresent()) {
                PropertiesDescriptionPreprocessorSwitch descriptionPreprocessorSwitch = new PropertiesDescriptionPreprocessorSwitch();
                preprocessor = descriptionPreprocessorSwitch.doSwitch(description);
            }
            return preprocessor;
        }

        /**
         * Returns the link resolvers used to update the converted description.
         * 
         * @return The link resolvers
         */
        public List<IDescriptionLinkResolver> getDescriptionConverterLinkResolvers() {
            if (!this.hasReadDescriptionConverterLinkResolverRegistry) {
                this.descriptionConverterLinkResolverListener.readRegistry(Platform.getExtensionRegistry());
                this.hasReadDescriptionConverterLinkResolverRegistry = true;
            }
            List<IDescriptionLinkResolver> linkResolvers = new ArrayList<>();
            linkResolvers.add(new PropertyValidationRuleLinkResolver());

            List<IItemDescriptor<IDescriptionLinkResolver>> itemDescriptors = this.descriptionConverterLinkResolverRegistry.getItemDescriptors();
            itemDescriptors.stream().map(IItemDescriptor::getItem).forEach(linkResolvers::add);

            return linkResolvers;
        }

        /**
         * Returns the link resolvers used to update the preprocessor description.
         * 
         * @return The link resolvers
         */
        public List<IDescriptionLinkResolver> getDescriptionPreprocessorLinkResolvers() {
            if (!this.hasReadDescriptionPreprocessorLinkResolverRegistry) {
                this.descriptionPreprocessorLinkResolverListener.readRegistry(Platform.getExtensionRegistry());
                this.hasReadDescriptionPreprocessorLinkResolverRegistry = true;
            }
            List<IDescriptionLinkResolver> linkResolvers = new ArrayList<>();

            linkResolvers.add(new GroupDescriptionPreprocessorLinkResolver());
            linkResolvers.add(new PropertyValidationRulePreprocessorLinkResolver());

            List<IItemDescriptor<IDescriptionLinkResolver>> itemDescriptors = this.descriptionPreprocessorLinkResolverRegistry.getItemDescriptors();
            itemDescriptors.stream().map(IItemDescriptor::getItem).forEach(linkResolvers::add);

            return linkResolvers;
        }
    }
}
