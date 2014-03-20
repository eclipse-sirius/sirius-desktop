/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.properties;

import org.eclipse.sirius.diagram.ui.tools.internal.properties.SiriusDiagramEditorPropertiesProvider;
import org.eclipse.sirius.tools.api.ui.property.IPropertiesProvider;

/**
 * Service to get the Designer UI properties.
 * 
 * @author mchauvin
 */
public final class PropertiesService {

    private static final PropertiesService INSTANCE = new PropertiesService();

    private IPropertiesProvider provider = new SiriusDiagramEditorPropertiesProvider();

    /**
     * Avoid instantiation
     */
    private PropertiesService() {
    }

    /**
     * Get the properties provider.
     * 
     * @return the properties provider
     */
    public IPropertiesProvider getPropertiesProvider() {
        return provider;
    }

    /**
     * Get the service instance.
     * 
     * @return the service instance.
     */
    public static PropertiesService getInstance() {
        return INSTANCE;
    }
}
