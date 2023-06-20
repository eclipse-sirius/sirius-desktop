/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.diagram;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.DiagramEventBrokerThreadSafe;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;

/**
 * A {@link DiagramEventBrokerThreadSafe} which may refresh figure according to non GMF event.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SiriusDiagramEventBroker extends DiagramEventBrokerThreadSafe {

    private static String LISTEN_TO_ALL_FEATURES = "*"; //$NON-NLS-1$

    public SiriusDiagramEventBroker(TransactionalEditingDomain editingDomain) {
        super(editingDomain);
    }

    /**
     * Overridden from super class to manage the case if (notifier instanceof BasicLabelStyle)
     */
    @Override
    protected Set getInterestedNotificationListeners(Notification event, NotifierToKeyToListenersSetMap listeners) {
        // Set listenerSet = super.getInterestedNotificationListeners(event, listeners);
        Set listenerSet = new LinkedHashSet();

        Collection c = getNotificationListeners(event.getNotifier(), event.getFeature(), listeners);
        if (c != null) {
            listenerSet.addAll(c);
        }

        EObject notifier = (EObject) event.getNotifier();

        // the Visibility Event get fired to all interested listeners in the
        // container
        if (NotationPackage.eINSTANCE.getView_Visible().equals(event.getFeature()) && notifier.eContainer() != null) {
            listenerSet.addAll(getNotificationListeners(notifier.eContainer(), listeners));
        } else if (notifier instanceof EAnnotation) {
            addListenersOfNotifier(listenerSet, notifier.eContainer(), event, listeners);
        }
        else if (notifier instanceof BasicLabelStyle) {
            // get the DEdge from the labelStyle associated to an edge label
            if (notifier.eContainer() != null && notifier.eContainer().eContainer() instanceof DEdge dEdge) {
                addListenersOfNotifier(listenerSet, dEdge, event, listeners);
            }
        } else if (!(notifier instanceof View)) {
            while (notifier != null && !(notifier instanceof View)) {
                notifier = notifier.eContainer();
            }
            addListenersOfNotifier(listenerSet, notifier, event, listeners);
        }
        return listenerSet;
    }

    private Set getNotificationListeners(Object notifier, NotifierToKeyToListenersSetMap listeners) {
        return listeners.getListeners(notifier, LISTEN_TO_ALL_FEATURES);
    }

    /**
     * Copied from super class
     * @param listenerSet
     * @param notifier
     */
    private void addListenersOfNotifier(Set listenerSet, EObject notifier, Notification event, NotifierToKeyToListenersSetMap listeners) {
        if (notifier != null) {
            Collection c = getNotificationListeners(notifier, event.getFeature(), listeners);
            if (c != null) {
                if (listenerSet.isEmpty())
                    listenerSet.addAll(c);
                else {
                    Iterator i = c.iterator();
                    while (i.hasNext()) {
                        Object o = i.next();
                        listenerSet.add(o);
                    }
                }
            }
        }
    }

    /**
     * Copied from super class
     * @param notifier
     * @param key
     * @param listeners
     * @return
     */
    private Set getNotificationListeners(Object notifier, Object key, NotifierToKeyToListenersSetMap listeners) {
        if (key != null) {
            if (!key.equals(LISTEN_TO_ALL_FEATURES)) {
                Set listenersSet = new LinkedHashSet();
                Collection c = listeners.getListeners(notifier, key);
                if (c != null && !c.isEmpty())
                    listenersSet.addAll(c);
                c = listeners.getListeners(notifier, LISTEN_TO_ALL_FEATURES);
                if (c != null && !c.isEmpty())
                    listenersSet.addAll(c);
                return listenersSet;
            } else if (key.equals(LISTEN_TO_ALL_FEATURES)) {
                return listeners.getAllListeners(notifier);
            }
        }
        return listeners.getAllListeners(notifier);
    }
}
