/*******************************************************************************
 * Copyright (c) 2014, 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.util.LazyCrossReferencer;
import org.eclipse.sirius.viewpoint.DRepresentation;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * A LazyCrossReferencer for the session. {@link LazyCrossReferencer#initialize()} is overridden in order to only add
 * the adapter at the first use.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 *
 */
public class SessionLazyCrossReferencer extends LazyCrossReferencer {
    private DAnalysisSessionImpl session;

    /**
     * Construct from an opened session.
     * 
     * @param session
     *            an opened session
     */
    public SessionLazyCrossReferencer(DAnalysisSessionImpl session) {
        this.session = session;
    }

    @Override
    protected void initialize() {
        super.initialize();

        Collection<Resource> semanticResources = session.getSemanticResources();
        EList<Resource> controlledResources = session.getControlledResources();
        Set<Resource> allSessionResources = session.getAllSessionResources();
        Collection<Resource> srmFiles = session.getSrmResources();

        Iterable<Resource> resources = Iterables.concat(semanticResources, controlledResources, allSessionResources);
        for (Resource resource : resources) {
            List<Adapter> adapters = resource.eAdapters();
            // add only if it was not added between creation and
            // initialization
            if (!adapters.contains(this)) {
                adapters.add(this);
            }
        }
        for (Resource resource : srmFiles) {
            // Since https://bugs.eclipse.org/bugs/show_bug.cgi?id=471106 fix in EMF (initially fixed in Sirius by
            // https://git.eclipse.org/r/#/c/50654/) some feature can be indexed twice by the crossreferencer. To avoid
            // that behavior we perform a resolve all before installing the XRef.
            EcoreUtil.resolveAll(resource);
            List<Adapter> adapters = resource.eAdapters();
            // add only if it was not added between creation and
            // initialization
            if (!adapters.contains(this)) {
                adapters.add(this);
            }
        }
    }

    @Override
    protected void selfAdapt(Notification notification) {
        if (isTopLevelRepresentationRemoval(notification)) {
            if (!unloadedResources.contains(notification.getNotifier())) {
                handleContainment(notification);
            }
        }
        super.selfAdapt(notification);
    }

    /**
     * Say if a {@link DRepresentation} is being removed from the resource.
     * 
     * @param notification
     *            the notification
     * @return true is the DRepresentation is being removed.
     */
    public static boolean isTopLevelRepresentationRemoval(Notification notification) {
        Object notifier = notification.getNotifier();
        boolean isResourceContentChange = notifier instanceof Resource && notification.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS;
        final boolean isRepresentationRemoval;
        if (notification.getEventType() == Notification.REMOVE && notification.getOldValue() instanceof DRepresentation) {
            isRepresentationRemoval = true;
        } else if (notification.getEventType() == Notification.REMOVE_MANY) {
            Collection<?> removed = (Collection<?>) notification.getOldValue();
            isRepresentationRemoval = Iterables.all(removed, Predicates.instanceOf(DRepresentation.class));
        } else {
            isRepresentationRemoval = false;
        }
        return isResourceContentChange && isRepresentationRemoval;
    }
}
