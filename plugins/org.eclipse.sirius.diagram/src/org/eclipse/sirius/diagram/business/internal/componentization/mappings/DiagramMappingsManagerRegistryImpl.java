/*******************************************************************************
 * Copyright (c) 2009, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.componentization.mappings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsRegistry;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.internal.Messages;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Registry of diagram mappings.
 *
 * @author mchauvin
 * @since 0.9.0
 */
public final class DiagramMappingsManagerRegistryImpl extends AdapterImpl implements DiagramMappingsManagerRegistry {

    private Map<DDiagram, DiagramMappingsManager> diagramMappingsManagers = new HashMap<DDiagram, DiagramMappingsManager>();

    /**
     * Construct a new {@link DiagramMappingsManagerRegistryImpl} instance.
     */
    private DiagramMappingsManagerRegistryImpl() {
        diagramMappingsManagers = new HashMap<DDiagram, DiagramMappingsManager>();
        SessionManager.INSTANCE.addSessionsListener(new SessionManagerListener.Stub() {
            @Override
            public void notifyRemoveSession(final Session removedSession) {
                // In normal condition this clean was already done during the
                // closing of the session (see the notify method above).
                cleanDiagramMappingsManagers(removedSession);
            }

            @Override
            public void notify(Session closingSession, int notification) {
                if (notification == SessionListener.CLOSING) {
                    cleanDiagramMappingsManagers(closingSession);
                }
            }
        });
    }

    /**
     * Construct a new instance.
     *
     * @return a new instance
     */
    public static DiagramMappingsManagerRegistry init() {
        return new DiagramMappingsManagerRegistryImpl();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry#getDiagramMappingsManager(Session,
     *      DDiagram)
     */
    @Override
    public DiagramMappingsManager getDiagramMappingsManager(final Session session, final DDiagram diagram) {
        if (diagram == null) {
            throw new IllegalArgumentException(Messages.DiagramMappingsManagerRegistryImpl_diagramParamErrorMsg);
        }
        if (diagramMappingsManagers.containsKey(diagram)) {
            return diagramMappingsManagers.get(diagram);
        } else {
            final DiagramDescription desc = diagram.getDescription();
            final DiagramDescriptionMappingsRegistry mappingsRegistry = DiagramDescriptionMappingsRegistry.INSTANCE;
            final DiagramDescriptionMappingsManager descManager = mappingsRegistry.getDiagramDescriptionMappingsManager(session, desc);

            final DiagramMappingsManager newManager = new DiagramMappingsManagerImpl(diagram, descManager);
            diagram.eAdapters().add(this);
            if (session != null) {
                newManager.computeMappings(session.getSelectedViewpoints(false), false);
            } else {
                newManager.computeMappings(null, false);
            }
            diagramMappingsManagers.put(diagram, newManager);
            return newManager;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void notifyChanged(final Notification msg) {
        final Object notifier = msg.getNotifier();
        if (notifier instanceof DDiagram) {
            final int featureID = msg.getFeatureID(DDiagram.class);
            if (featureID == DiagramPackage.DDIAGRAM__ACTIVATED_LAYERS) {

                switch (msg.getEventType()) {
                case Notification.ADD:
                case Notification.REMOVE:
                    computeMappings((DDiagram) notifier, (Layer) msg.getNewValue());
                    break;
                default:
                    break;
                }
            }
        }
    }

    private void computeMappings(final DDiagram diagram, final Layer layer) {
        final DiagramMappingsManager manager = diagramMappingsManagers.get(diagram);
        if (manager != null) {

            boolean needToRecomputeDescMappings = false;
            if (layer != null && layer.eContainer() instanceof DiagramExtensionDescription) {
                needToRecomputeDescMappings = true;
            }
            Session sess = SessionManager.INSTANCE.getSession(((DSemanticDiagram) diagram).getTarget());
            if (sess != null) {
                manager.computeMappings(sess.getSelectedViewpoints(false), needToRecomputeDescMappings);
            } else {
                manager.computeMappings(null, needToRecomputeDescMappings);
            }
        }
    }

    private void cleanDiagramMappingsManagers(final Session session) {

        final Set<DDiagram> diagramInSession = new HashSet<DDiagram>();
        for (final DRepresentation representation : DialectManager.INSTANCE.getAllLoadedRepresentations(session)) {
            if (representation instanceof DDiagram) {
                diagramInSession.add((DDiagram) representation);
            }
        }
        final Set<DDiagram> keysToRemove = new HashSet<DDiagram>();
        for (final DDiagram diagram : diagramMappingsManagers.keySet()) {
            if (diagramInSession.contains(diagram)) {
                keysToRemove.add(diagram);
            }
        }
        for (final DDiagram keyToRemove : keysToRemove) {
            diagramMappingsManagers.remove(keyToRemove);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry#removeDiagramMappingsManagers(org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager)
     */
    @Override
    public void removeDiagramMappingsManagers(DiagramMappingsManager manager) {
        final Set<DDiagram> toRemove = new LinkedHashSet<DDiagram>();
        for (final Map.Entry<DDiagram, DiagramMappingsManager> entry : diagramMappingsManagers.entrySet()) {
            if (entry.getValue() == manager) {
                toRemove.add(entry.getKey());
            }
        }
        for (final DDiagram diagramToRemove : toRemove) {
            diagramMappingsManagers.remove(diagramToRemove);
        }
    }
}
