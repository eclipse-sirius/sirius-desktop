/*******************************************************************************
 * Copyright (c) 2012, 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionEventBroker;
import org.eclipse.sirius.business.internal.session.SessionEventBrokerImpl;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.helper.display.VisibilityPropagatorAdapter;

/**
 * Register all gmf diagram updaters.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class GMFDiagramUpdater {

    private NotationVisibilityUpdater notationVisibilityUpdater;

    private FontFormatUpdater viewFontChangesRefactorer;

    private FilterListener filterListener;

    private GMFBoundsUpdater gmfBoundsUpdater;

    private BackgroundUpdater backgroundUpdater;

    private VisibilityUpdater visibilityUpdater;

    private DDiagramHiddenElementsUpdater dDiagramHiddenElementsUpdater;

    private EdgeStyleUpdater edgeStyleUpdater;

    private SessionEventBroker eventBroker;

    private VisibilityPropagatorAdapter visibilityPropagator;

    private EdgeLayoutUpdaterModelChangeTrigger edgeLayoutUpdaterChangeTrigger;

    /**
     * Default constructor.
     * 
     * @param session
     *            the {@link Session}
     * @param dDiagram
     *            the {@link DDiagram}
     */
    public GMFDiagramUpdater(Session session, DDiagram dDiagram) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        notationVisibilityUpdater = new NotationVisibilityUpdater(session);
        viewFontChangesRefactorer = new FontFormatUpdater(domain);

        eventBroker = session.getEventBroker();
        filterListener = new FilterListener(dDiagram, session.getTransactionalEditingDomain());

        eventBroker.addLocalTrigger(SessionEventBrokerImpl.asFilter(new FilterListenerScope()), filterListener);
        edgeLayoutUpdaterChangeTrigger = new EdgeLayoutUpdaterModelChangeTrigger(session, dDiagram);
        gmfBoundsUpdater = new GMFBoundsUpdater(domain, dDiagram);
        backgroundUpdater = new BackgroundUpdater(domain, dDiagram);
        visibilityUpdater = new VisibilityUpdater(domain, dDiagram);
        dDiagramHiddenElementsUpdater = new DDiagramHiddenElementsUpdater(domain, dDiagram);
        visibilityPropagator = new VisibilityPropagatorAdapter(session, dDiagram);

        edgeStyleUpdater = new EdgeStyleUpdater(domain, session.getSemanticCrossReferencer());
    }

    /**
     * Dispose the gmf diagram updaters.
     */
    public void dispose() {
        visibilityPropagator.dispose();
        notationVisibilityUpdater.dispose();
        viewFontChangesRefactorer.dispose();
        gmfBoundsUpdater.dispose();
        backgroundUpdater.dispose();
        visibilityUpdater.dispose();
        dDiagramHiddenElementsUpdater.dispose();
        edgeStyleUpdater.dispose();
        eventBroker.removeLocalTrigger(filterListener);
        edgeLayoutUpdaterChangeTrigger.dispose();
        edgeLayoutUpdaterChangeTrigger = null;
        eventBroker = null;
    }
}
