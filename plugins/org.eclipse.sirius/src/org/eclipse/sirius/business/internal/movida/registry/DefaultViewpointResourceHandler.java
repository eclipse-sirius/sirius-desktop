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
package org.eclipse.sirius.business.internal.movida.registry;

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.componentization.ViewpointResourceHandler;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * The default implementation of {@link ViewpointResourceHandler} for
 * <code>*.odesign</code> files.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class DefaultViewpointResourceHandler implements ViewpointResourceHandler {
    /**
     * {@inheritDoc}
     */
    public boolean handles(URI uri) {
        return uri != null && new FileQuery(uri.fileExtension()).isVSMFile();
    }

    /**
     * {@inheritDoc}
     */
    public Set<Viewpoint> collectViewpointDefinitions(Resource res) {
        Set<Viewpoint> viewpoints = Sets.newHashSet();
        for (Group group : Iterables.filter(res.getContents(), Group.class)) {
            for (Viewpoint viewpoint : group.getOwnedViewpoints()) {
                viewpoints.add(viewpoint);
            }
        }
        return ImmutableSet.copyOf(Iterables.filter(viewpoints, Predicates.notNull()));
    }
}
