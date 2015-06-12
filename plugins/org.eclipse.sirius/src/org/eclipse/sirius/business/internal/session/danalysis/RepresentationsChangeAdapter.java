/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.collect.Iterables;

/**
 * Adapter which notify session listener when representation is added or
 * removed.
 * 
 * @author mchauvin
 */
public class RepresentationsChangeAdapter extends AdapterImpl {

    private Session session;

    /**
     * Default constructor.
     * 
     * @param session
     *            the {@link Session} on which this adapter is used.
     */
    public RepresentationsChangeAdapter(Session session) {
        super();
        this.session = session;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyChanged(final org.eclipse.emf.common.notify.Notification n) {
        final Object notifier = n.getNotifier();

        /* add and remove adapter on DView instances */
        if (notifier instanceof DAnalysis && n.getFeatureID(DAnalysis.class) == ViewpointPackage.DANALYSIS__OWNED_VIEWS) {
            switch (n.getEventType()) {
            case Notification.ADD:
                final DView newView = (DView) n.getNewValue();
                registerView(newView);
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                safeUnregisterViews(n.getOldValue());
                break;
            default:
                break;
            }
            /* update editors and views on representation creation or deletion */
        } else if (notifier instanceof DView && n.getFeatureID(DView.class) == ViewpointPackage.DVIEW__OWNED_REPRESENTATIONS) {
            switch (n.getEventType()) {
            case Notification.ADD:
                SessionManager.INSTANCE.notifyRepresentationCreated(session);
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                SessionManager.INSTANCE.notifyRepresentationDeleted(session);
                break;
            default:
                break;
            }
        }
    }

    private void safeUnregisterViews(Object oldValue) {
        if (oldValue instanceof DView) {
            final DView oldView = (DView) oldValue;
            unregisterView(oldView);
        } else if (oldValue instanceof Collection<?>) {
            for (DView oldView : Iterables.filter((Collection<?>) oldValue, DView.class)) {
                unregisterView(oldView);
            }
        }

    }

    /**
     * Add this adapter to an analysis.
     * 
     * @param analysis
     *            the analysis
     */
    public void registerAnalysis(final DAnalysis analysis) {
        if (!analysis.eAdapters().contains(this)) {
            analysis.eAdapters().add(this);
        }
        for (final DView view : analysis.getOwnedViews()) {
            registerView(view);
        }
    }

    /**
     * Remove this adapter to an analysis.
     * 
     * @param analysis
     *            the analysis
     */
    public void unregisterAnalysis(final DAnalysis analysis) {
        if (analysis.eAdapters().contains(this)) {
            analysis.eAdapters().remove(this);
        }
        for (final DView view : analysis.getOwnedViews()) {
            unregisterView(view);
        }
    }

    /**
     * Add this adapter to an analysis
     * 
     * @param editor
     *            the editor
     */
    private void registerView(final DView view) {
        if (!view.eAdapters().contains(this)) {
            view.eAdapters().add(this);
        }
    }

    /**
     * Remove this adapter to an analysis.
     * 
     * @param analysis
     *            the analysis
     */
    private void unregisterView(final DView view) {
        if (view.eAdapters().contains(this)) {
            view.eAdapters().remove(this);
        }
    }
}
