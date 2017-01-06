/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.internal.Messages;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;

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
     * The URI of the model containing the default value of the properties page
     * to create.
     */
    private static final URI DEFAULT_RULES_RESOURCE_URI = URI.createURI("platform:/plugin/org.eclipse.sirius.properties.core/model/properties.odesign", true); //$NON-NLS-1$

    /**
     * The resource set used to load the default rules.
     */
    private ResourceSet resourceSet = new ResourceSetImpl();

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
        Resource resource = this.resourceSet.getResource(DEFAULT_RULES_RESOURCE_URI, true);
        if (resource == null) {
            SiriusPropertiesCorePlugin.getPlugin().error(Messages.DefaultRulesProvider_DefaultPropertiesNotFound);
        } else {
            List<EObject> contents = resource.getContents();
            if (contents.size() > 0 && contents.get(0) instanceof ViewExtensionDescription) {
                return (ViewExtensionDescription) contents.get(0);
            }
        }
        return null;
    }

}
