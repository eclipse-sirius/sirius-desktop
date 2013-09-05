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
package org.eclipse.sirius.editor.tools.internal.presentation;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

import org.eclipse.sirius.business.api.componentization.SiriusResourceHandler;
import org.eclipse.sirius.business.api.query.SiriusQuery;
import org.eclipse.sirius.business.internal.movida.DynamicVSMLoader;
import org.eclipse.sirius.business.internal.movida.dependencies.Relation;
import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.description.Group;
import org.eclipse.sirius.description.Sirius;

/**
 * A content adapter for a whole VSM, which detects when the requirements
 * declared by the Siriuss it contain change.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class VSMRequirementChangeAdapter extends EContentAdapter {
    private final Resource resource;

    private final DynamicVSMLoader loader;

    /**
     * For each Sirius URI directly required, the set of Siriuss which
     * require it.
     */
    private final Multimap<URI, Sirius> requirements = HashMultimap.create();

    public VSMRequirementChangeAdapter(Resource res, DynamicVSMLoader loader) {
        this.resource = res;
        this.loader = loader;
    }

    public void install() {
        Relation<URI> requires = loader.getRegistry().getRelations().getRequires();
        SiriusResourceHandler handler = loader.getRegistry().getSiriusResourceHandler();
        for (Sirius vp : handler.collectSiriusDefinitions(resource)) {
            for (URI required : requires.apply(new SiriusQuery(vp).getSiriusURI().get())) {
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
        if (notification.getNotifier() instanceof Sirius && feature == DescriptionPackage.eINSTANCE.getSirius_Customizes() || feature == DescriptionPackage.eINSTANCE.getSirius_Reuses()) {
            switch (notification.getEventType()) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                Object added = notification.getNewValue();
                if (added instanceof List<?>) {
                    handleRequirementsAdded((Sirius) notification.getNotifier(), Iterables.filter((List<?>) added, URI.class));
                } else if (added instanceof URI) {
                    handleRequirementsAdded((Sirius) notification.getNotifier(), Collections.singleton((URI) added));
                }
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                Object removed = notification.getOldValue();
                if (removed instanceof List<?>) {
                    handleRequirementsRemoved((Sirius) notification.getNotifier(), Iterables.filter((List<?>) removed, URI.class));
                } else if (removed instanceof URI) {
                    handleRequirementsRemoved((Sirius) notification.getNotifier(), Collections.singleton((URI) removed));
                }
                break;
            default:
                break;
            }
        } else if (notification.getNotifier() instanceof Group && feature == DescriptionPackage.eINSTANCE.getGroup_OwnedSiriuss()) {
            switch (notification.getEventType()) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                Object added = notification.getNewValue();
                if (added instanceof List<?>) {
                    handleSiriussAdded(Iterables.filter((List<?>) added, Sirius.class));
                } else if (added instanceof Sirius) {
                    handleSiriussAdded(Collections.singleton((Sirius) added));
                }
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                Object removed = notification.getOldValue();
                if (removed instanceof List<?>) {
                    handleSiriussRemoved(Iterables.filter((List<?>) removed, Sirius.class));
                } else if (removed instanceof URI) {
                    handleSiriussRemoved(Collections.singleton((Sirius) removed));
                }
                break;
            default:
                break;
            }
        }
    }

    private void addRequirement(URI required, Sirius vp) {
        boolean wasNotRequired = requirements.get(required).isEmpty();
        requirements.put(required, vp);
        if (wasNotRequired) {
            loader.require(required);
        }
    }

    private void removeRequirement(URI required, Sirius vp) {
        boolean removed = requirements.remove(required, vp);
        if (removed && requirements.get(required).isEmpty()) {
            loader.unrequire(required);
        }
    }

    private void handleRequirementsAdded(Sirius vp, Iterable<URI> added) {
        for (URI uri : added) {
            addRequirement(uri, vp);
        }
    }

    private void handleRequirementsRemoved(Sirius vp, Iterable<URI> removed) {
        for (URI uri : removed) {
            removeRequirement(uri, vp);
        }
    }

    private void handleSiriussAdded(Iterable<Sirius> added) {
        Relation<URI> requires = loader.getRegistry().getRelations().getRequires();
        for (Sirius viewpoint : added) {
            for (URI uri : requires.apply(new SiriusQuery(viewpoint).getSiriusURI().get())) {
                addRequirement(uri, viewpoint);
            }
        }
    }

    private void handleSiriussRemoved(Iterable<Sirius> removed) {
        for (Sirius viewpoint : removed) {
            for (URI uri : requirements.keySet()) {
                removeRequirement(uri, viewpoint);
            }
        }
    }
}
