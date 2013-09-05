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
package org.eclipse.sirius.diagram.business.internal.parser;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.business.api.query.AirDResouceQuery;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.api.session.resource.DResource;
import org.eclipse.sirius.business.internal.resource.AirDCrossReferenceAdapter;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;

/**
 * Aird resource to provide custom factory.
 * 
 * @author mchauvin
 */
public class AirDResourceImpl extends GMFResource implements DResource, AirdResource {

    /**
     * The number of current load in progress. Usefull for determine if the
     * current load is the first one or is a load triggered by a resolve (for
     * fragmented files for examples).
     */
    private static ThreadLocal<Integer> nbLoadInProgress = new ThreadLocal<Integer>() {
        @Override
        protected synchronized Integer initialValue() {
            return Integer.valueOf(0);
        }
    };

    /**
     * Constructor.
     * 
     * @param uri
     *            the URI
     */
    public AirDResourceImpl(final URI uri) {
        super(uri);
    }

    /**
     * Increment the number of load in progress.
     */
    protected static void incrementLoadInProgress() {
        nbLoadInProgress.set(Integer.valueOf(nbLoadInProgress.get().intValue() + 1));
    }

    /**
     * Decrement the number of load in progress.
     */
    protected static void decrementLoadInProgress() {
        nbLoadInProgress.set(Integer.valueOf(nbLoadInProgress.get().intValue() - 1));
    }

    /**
     * Check if there is a load in progress.
     * 
     * @return true if at least one load is in progress, false otherwise.
     */
    protected static boolean hasLoadInProgress() {
        return nbLoadInProgress.get().intValue() != 0;
    };

    /**
     * {@inheritDoc}
     */
    @Override
    protected Adapter createModificationTrackingAdapter() {
        // we override the modification tracking adapter to ignore any
        // notification that has no feature.
        return new ModificationTrackingAdapter() {
            @Override
            public void notifyChanged(final Notification notification) {
                if (!isModified() && AirDResourceImpl.isModifyingChange(notification)) {
                    super.notifyChanged(notification);
                }
            }

        };
    }

    // CHECKSTYLE:OFF
    /**
     * Determines whether or not <code>notification</code> indicates a modifying
     * change to a GMF resource
     * 
     * @param notification
     *            a notification of some concrete change in the resource set
     * @return whether this change is an abstract change to some resource, for
     *         the purpose of tracking undo context
     */
    public static boolean isModifyingChange(final Notification notification) {
        return !notification.isTouch() && !AirDResourceImpl.isTransient(notification.getNotifier(), notification.getFeature());
    }

    /**
     * Check if the feature or one of the notifier's containers is transient.
     * 
     * @param notifier
     *            a notifier
     * @param feature
     *            the feature that changed
     * 
     * @return <code>true</code> if the feature is transient or if the notifier
     *         or any of its ancestors is contained by a transient reference;
     *         <code>false</code>, otherwise
     */
    private static boolean isTransient(final Object notifier, final Object feature) {
        if (feature instanceof EStructuralFeature) {
            if (((EStructuralFeature) feature).isTransient()) {
                return true;
            } else {
                // calling isTransient could be a lengthy operation.
                // It is safe to cast because the adapter is only
                // attached to EObjects, not to the resource
                return AirDResourceImpl.isTransient((EObject) notifier);
            }
        }
        return false;
    }

    /**
     * Is object transient?
     */
    private static boolean isTransient(EObject eObject) {
        EStructuralFeature containmentFeature = eObject.eContainmentFeature();
        while (containmentFeature != null) {
            if (containmentFeature.isTransient()) {
                return true;
            }
            eObject = eObject.eContainer();
            if (eObject != null) {
                containmentFeature = eObject.eContainmentFeature();
            } else {
                break;
            }
        }
        return false;
    }

    /**
     * @{inheritDoc
     * 
     * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#load(java.util.Map)
     */
    @Override
    public void load(Map<?, ?> options) throws IOException {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.LOAD_AIRD_KEY);
        AirDResourceImpl.incrementLoadInProgress();
        try {
            super.load(options);
        } finally {
            AirDResourceImpl.decrementLoadInProgress();
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.LOAD_AIRD_KEY);
        // AirDResourceMigration migration = new AirDResourceMigration(this);
        // // Notify user only if there is no more load in progress.
        // migration.migrate(!AirDResourceImpl.hasLoadInProgress());
    }

    @Override
    protected void doUnload() {
        Option<AirDCrossReferenceAdapter> result = new AirDResouceQuery(this).getAirDCrossReferenceAdapter();
        if (result.some()) {
            eAdapters().remove(result.get());
        }
        super.doUnload();
    }

    @Override
    protected XMLHelper createXMLHelper() {

        return new RepresentationsFileXMIHelper(this);
    }
}
