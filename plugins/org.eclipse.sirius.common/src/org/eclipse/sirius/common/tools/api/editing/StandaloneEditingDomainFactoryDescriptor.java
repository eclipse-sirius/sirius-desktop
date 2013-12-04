/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.editing;


/**
 * Describes a extension to be contributed programmatically to the
 * {@link EditingDomainFactoryRegistry} of
 * "org.eclipse.sirius.common.editingDomainFactory" extension point.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * 
 * @since 0.9.0
 */
public class StandaloneEditingDomainFactoryDescriptor implements EditingDomainFactoryDescriptor {

    /** id of this descriptor. */
    private final String id;

    /**
     * the override attribute value of this {@link IEditingDomainFactory}.
     */
    private String overrideValue;

    /**
     * We only need to create the instance once, this will keep reference to it.
     */
    private IEditingDomainFactory extension;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param id
     *            Id of this descriptor
     * 
     * @param extension
     *            the IEditingDomainFactory implementation.
     * @param overrideValue
     *            the override attribute value, id of the extension to override
     *            or null if any to override
     */
    public StandaloneEditingDomainFactoryDescriptor(String id, String overrideValue, IEditingDomainFactory extension) {
        this.id = id;
        this.overrideValue = overrideValue;
        this.extension = extension;
    }

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public String getOverrideValue() {
        return overrideValue;
    }

    /**
     * {@inheritDoc}
     */
    public IEditingDomainFactory getEditingDomainFactory() {
        return extension;
    }
}
