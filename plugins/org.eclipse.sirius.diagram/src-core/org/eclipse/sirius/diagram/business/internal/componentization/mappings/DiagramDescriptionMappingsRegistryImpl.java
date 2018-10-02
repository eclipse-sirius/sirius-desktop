/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsRegistry;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * The implementation of {@link DiagramDescriptionMappingsRegistry}.
 *
 * @author mchauvin
 * @since 0.9.0
 */
public final class DiagramDescriptionMappingsRegistryImpl implements DiagramDescriptionMappingsRegistry {

    private final Map<Key, DiagramDescriptionMappingsManager> diagramDescriptionMappingsManagers = new HashMap<Key, DiagramDescriptionMappingsManager>();

    /**
     * Avoid instantiation.
     */
    private DiagramDescriptionMappingsRegistryImpl() {
        SessionManager.INSTANCE.addSessionsListener(new SessionManagerListener.Stub() {
            @Override
            public void notifyRemoveSession(final Session removedSession) {
                // In normal condition this clean was already done during the
                // closing of the session (see the notify method above).
                cleanDiagramDescriptionMappingsManagers(removedSession);
            }

            @Override
            public void viewpointDeselected(final Viewpoint deselectedSirius) {
                computeMappings();
            }

            @Override
            public void viewpointSelected(final Viewpoint selectedSirius) {
                computeMappings();
            }

            @Override
            public void notify(Session closingSession, int notification) {
                if (notification == SessionListener.CLOSING) {
                    cleanDiagramDescriptionMappingsManagers(closingSession);
                }
            }
        });
    }

    /**
     * Create a new instance.
     *
     * @return a new instance
     */
    public static DiagramDescriptionMappingsRegistry init() {
        return new DiagramDescriptionMappingsRegistryImpl();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsRegistry#getDiagramDescriptionMappingsManager(org.eclipse.sirius.business.api.session.Session,
     *      org.eclipse.sirius.viewpoint.description.DiagramDescription)
     */
    public DiagramDescriptionMappingsManager getDiagramDescriptionMappingsManager(final Session session, final DiagramDescription description) {
        final Key key = new Key(session, description);
        if (diagramDescriptionMappingsManagers.containsKey(key)) {
            return diagramDescriptionMappingsManagers.get(key);
        } else {
            final DiagramDescriptionMappingsManager newManager = new DiagramDescriptionMappingsManagerImpl(description);
            diagramDescriptionMappingsManagers.put(key, newManager);
            if (session != null) {
                newManager.computeMappings(session.getSelectedViewpoints(false));
            } else {
                newManager.computeMappings(null);
            }
            return newManager;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsRegistry#computeMappings()
     */
    public void computeMappings() {

        cleanDiagramDescriptionNoMoreInResource();

        for (final Entry<Key, DiagramDescriptionMappingsManager> manager : diagramDescriptionMappingsManagers.entrySet()) {
            if (manager.getKey().session != null) {
                manager.getValue().computeMappings(manager.getKey().session.getSelectedViewpoints(false));
            } else {
                manager.getValue().computeMappings(null);
            }
        }
    }

    private void cleanDiagramDescriptionNoMoreInResource() {
        final Set<Key> keysToRemove = new HashSet<Key>();
        for (final Key key : diagramDescriptionMappingsManagers.keySet()) {
            if (key.description.eResource() == null) {
                keysToRemove.add(key);
            }
        }
        for (final Key keyToRemove : keysToRemove) {
            final DiagramDescriptionMappingsManager manager = diagramDescriptionMappingsManagers.get(keyToRemove);
            diagramDescriptionMappingsManagers.remove(keyToRemove);
            manager.dispose();
        }
    }

    private void cleanDiagramDescriptionMappingsManagers(final Session session) {
        final Set<Key> keysToRemove = new HashSet<Key>();
        for (final Key key : diagramDescriptionMappingsManagers.keySet()) {
            if (key.session == session) {
                keysToRemove.add(key);
            }
        }
        for (final Key keyToRemove : keysToRemove) {
            // diagramDescriptionMappingsManagers.remove(keyToRemove);
            final DiagramDescriptionMappingsManager manager = diagramDescriptionMappingsManagers.get(keyToRemove);
            diagramDescriptionMappingsManagers.remove(keyToRemove);
            manager.dispose();
        }
    }

    /**
     * A class to serve as key in the map
     *
     * @author mchauvin
     */
    private class Key {

        private final Session session;

        private final DiagramDescription description;

        Key(final Session session, final DiagramDescription description) {
            this.session = session;
            this.description = description;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + DiagramDescriptionMappingsRegistryImpl.this.hashCode();
            result = prime * result + ((description == null) ? 0 : description.hashCode());
            result = prime * result + ((session == null) ? 0 : session.hashCode());
            return result;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Object#equals(java.lang.Object)
         */
        // CHECKSTYLE:OFF
        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }

            final Key other = (Key) obj;
            return description == other.description && session == other.session;
        }
    }
    // CHECKSTYLE:ON

}
