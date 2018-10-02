/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.internal.resource;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * {@link Resource.Factory} used for in memory Resource creation.
 * 
 * @author edugueperoux
 */
public class InMemoryResourceFactoryImpl extends ResourceFactoryImpl implements Resource.Factory {

    /**
     * Constant defining in memory resource buffer size.
     */
    public static final int BUFFER_SIZE = 1000000;

    /**
     * Maps of buffers for in memory resource.
     */
    public static final Map<URI, byte[]> IN_MEMORY_BUFFERS = new HashMap<URI, byte[]>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Resource createResource(URI uri) {
        return new InMemoryResourceImpl(uri);
    }

}
