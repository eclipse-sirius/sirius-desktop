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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.componentization.ViewpointResourceHandler;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A {@link ViewpointResourceHandler} which handles all the resources handled by
 * any of a configurable set of primitive handlers. If multiple handlers can
 * handle a given resource, only the first one (in the order of their
 * registration) is used.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class CompositeViewpointResourceHandler implements ViewpointResourceHandler {
    private final List<ViewpointResourceHandler> handlers = new ArrayList<>();

    /**
     * Registers a new {@link ViewpointResourceHandler}. Does nothing if it is
     * already registered.
     * 
     * @param handler
     *            the handler for some new type of Sirius resource to
     *            support.
     */
    public synchronized void addResourceType(ViewpointResourceHandler handler) {
        if (!this.handlers.contains(handler)) {
            this.handlers.add(handler);
        }
    }

    /**
     * Unregisters a {@link ViewpointResourceHandler}. Does nothing if it was
     * not already registered.
     * 
     * @param handler
     *            the handler for some new type of Sirius resource to
     *            support.
     */
    public synchronized void removeResourceType(ViewpointResourceHandler handler) {
        this.handlers.remove(handler);
    }

    /**
     * {@inheritDoc}
     */
    public synchronized boolean handles(final URI uri) {
        return Iterables.any(handlers, new Predicate<ViewpointResourceHandler>() {
            public boolean apply(ViewpointResourceHandler handler) {
                return handler.handles(uri);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public synchronized Set<Viewpoint> collectViewpointDefinitions(Resource res) {
        final URI uri = res.getURI();
        for (ViewpointResourceHandler handler : handlers) {
            if (handler.handles(uri)) {
                return handler.collectViewpointDefinitions(res);
            }
        }
        return Collections.emptySet();
    }
}
