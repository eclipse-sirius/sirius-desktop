/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.properties.defaultrules.api;

import java.util.Optional;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.defaultrules.internal.Messages;
import org.eclipse.sirius.properties.defaultrules.internal.SiriusPropertiesDefaultRulesPlugin;
import org.eclipse.sirius.viewpoint.description.Group;

/**
 * Utility class used to load and hold the default rules.
 * 
 * @author sbegaudeau
 */
public final class DefaultRulesProvider {

    /**
     * The sole instance of the default rule provider.
     */
    public static final DefaultRulesProvider INSTANCE = new DefaultRulesProvider();

    /**
     * The URI of the model containing the default value of the properties page to create.
     */
    private static final URI DEFAULT_RULES_RESOURCE_URI = URI.createURI("platform:/plugin/org.eclipse.sirius.properties.defaultrules/model/properties.odesign", true); //$NON-NLS-1$

    /**
     * The resource set used to load the default rules.
     */
    private ResourceSet defaultRulesResourceSet = new ResourceSetImpl();

    /**
     * The constructor.
     */
    private DefaultRulesProvider() {
        // prevent instantiation
    }

    /**
     * Returns the root element of the default rules.
     * 
     * @return The root element of the default rules
     */
    public ViewExtensionDescription getDefaultRules() {
        return this.getDefaultRules(this.defaultRulesResourceSet);
    }

    /**
     * Loads the default rules in the given resource set and returns the root element of the default rules.
     * 
     * @param resourceSet
     *            The resource set in which the default rules should be loaded
     * 
     * @return The root element of the default rules
     */
    public ViewExtensionDescription getDefaultRules(ResourceSet resourceSet) {
        Optional<Resource> optionalResource = Optional.ofNullable(resourceSet.getResource(DEFAULT_RULES_RESOURCE_URI, true));

        if (!optionalResource.isPresent()) {
            IStatus status = new Status(IStatus.ERROR, SiriusPropertiesDefaultRulesPlugin.PLUGIN_ID, Messages.DefaultRulesProvider_DefaultPropertiesNotFound);
            SiriusPropertiesDefaultRulesPlugin.getPlugin().getLog().log(status);
        }

        // @formatter:off
        return optionalResource.flatMap(resource -> {
            return resource.getContents().stream()
                    .filter(Group.class::isInstance)
                    .map(Group.class::cast)
                    .flatMap(group -> group.getExtensions().stream())
                    .filter(ViewExtensionDescription.class::isInstance)
                    .map(ViewExtensionDescription.class::cast)
                    .findFirst();
        }).orElse(null);
        // @formatter:on
    }
}
