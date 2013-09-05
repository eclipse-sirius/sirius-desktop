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
package org.eclipse.sirius.business.internal.helper.delete;

import org.eclipse.sirius.business.api.delete.IDeleteHook;

/**
 * Describes a extension to be contributed programmatically to the
 * {@link DeleteHookDescriptorRegistry} of "org.eclipse.sirius.deleteHook"
 * extension point.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class StandaloneDeleteHookDescriptor implements IDeleteHookDescriptor {

    /** id of this descriptor. */
    private final String id;

    /**
     * We only need to create the instance once, this will keep reference to it.
     */
    private IDeleteHook extension;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param id
     *            Id of this descriptor
     * 
     * @param extension
     *            the IDeleteHook implementation.
     */
    public StandaloneDeleteHookDescriptor(String id, IDeleteHook extension) {
        this.id = id;
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
    public IDeleteHook getIDeleteHook() {
        return extension;
    }
}
