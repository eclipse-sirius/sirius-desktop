/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.presentation;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.sirius.business.api.componentization.ViewpointResourceHandler;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.internal.movida.DynamicVSMLoader;
import org.eclipse.sirius.ext.base.relations.Relation;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

/**
 * A content adapter for a whole VSM, which detects when the requirements
 * declared by the Viewpoints it contain change.
 * 
 * @author pierre-charles.david@obeo.fr
 */
class VSMRequirementChangeAdapter extends EContentAdapter {
    private final Resource resource;

    private final DynamicVSMLoader loader;

    /**
     * For each Sirius URI directly required, the set of Viewpoints which
     * require it.
     */
    private final Multimap<URI, Viewpoint> requirements = HashMultimap.create();

    VSMRequirementChangeAdapter(Resource res, DynamicVSMLoader loader) {
        this.resource = res;
        this.loader = loader;
    }

    public void install() {
        Relation<URI> requires = loader.getRegistry().getRelations().getRequires();
        ViewpointResourceHandler handler = loader.getRegistry().getSiriusResourceHandler();
        for (Viewpoint vp : handler.collectViewpointDefinitions(resource)) {
            for (URI required : requires.apply(new ViewpointQuery(vp).getViewpointURI().get())) {
                addRequirement(required, vp);
            }
        }
        resource.eAdapters().add(this);
    }

    public void uninstall() {
        resource.eAdapters().remove(this);
    }

    @Override
    public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);
        Object feature = notification.getFeature();
        if (notification.getNotifier() instanceof Viewpoint && feature == DescriptionPackage.eINSTANCE.getViewpoint_Customizes() || feature == DescriptionPackage.eINSTANCE.getViewpoint_Reuses()) {
            switch (notification.getEventType()) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                Object added = notification.getNewValue();
                if (added instanceof List<?>) {
                    handleRequirementsAdded((Viewpoint) notification.getNotifier(), Iterables.filter((List<?>) added, URI.class));
                } else if (added instanceof URI) {
                    handleRequirementsAdded((Viewpoint) notification.getNotifier(), Collections.singleton((URI) added));
                }
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                Object removed = notification.getOldValue();
                if (removed instanceof List<?>) {
                    handleRequirementsRemoved((Viewpoint) notification.getNotifier(), Iterables.filter((List<?>) removed, URI.class));
                } else if (removed instanceof URI) {
                    handleRequirementsRemoved((Viewpoint) notification.getNotifier(), Collections.singleton((URI) removed));
                }
                break;
            default:
                break;
            }
        } else if (notification.getNotifier() instanceof Group && feature == DescriptionPackage.eINSTANCE.getGroup_OwnedViewpoints()) {
            switch (notification.getEventType()) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                Object added = notification.getNewValue();
                if (added instanceof List<?>) {
                    handleViewpointsAdded(Iterables.filter((List<?>) added, Viewpoint.class));
                } else if (added instanceof Viewpoint) {
                    handleViewpointsAdded(Collections.singleton((Viewpoint) added));
                }
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                Object removed = notification.getOldValue();
                if (removed instanceof List<?>) {
                    handleViewpointsRemoved(Iterables.filter((List<?>) removed, Viewpoint.class));
                } else if (removed instanceof Viewpoint) {
                    handleViewpointsRemoved(Collections.singleton((Viewpoint) removed));
                }
                break;
            default:
                break;
            }
        }
    }

    private void addRequirement(URI required, Viewpoint vp) {
        boolean wasNotRequired = requirements.get(required).isEmpty();
        requirements.put(required, vp);
        if (wasNotRequired) {
            loader.require(required);
        }
    }

    private void removeRequirement(URI required, Viewpoint vp) {
        boolean removed = requirements.remove(required, vp);
        if (removed && requirements.get(required).isEmpty()) {
            loader.unrequire(required);
        }
    }

    private void handleRequirementsAdded(Viewpoint vp, Iterable<URI> added) {
        for (URI uri : added) {
            addRequirement(uri, vp);
        }
    }

    private void handleRequirementsRemoved(Viewpoint vp, Iterable<URI> removed) {
        for (URI uri : removed) {
            removeRequirement(uri, vp);
        }
    }

    private void handleViewpointsAdded(Iterable<Viewpoint> added) {
        Relation<URI> requires = loader.getRegistry().getRelations().getRequires();
        for (Viewpoint viewpoint : added) {
            for (URI uri : requires.apply(new ViewpointQuery(viewpoint).getViewpointURI().get())) {
                addRequirement(uri, viewpoint);
            }
        }
    }

    private void handleViewpointsRemoved(Iterable<Viewpoint> removed) {
        for (Viewpoint viewpoint : removed) {
            for (URI uri : requirements.keySet()) {
                removeRequirement(uri, viewpoint);
            }
        }
    }
}
