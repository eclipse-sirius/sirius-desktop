/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionEventBroker;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.internal.operation.CenterEdgeEndModelChangeOperation;
import org.eclipse.sirius.diagram.ui.internal.refresh.SiriusDiagramSessionEventBroker;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A Model Change Trigger that execute the
 * {@link CenterEdgeEndModelChangeOperation} when features defined in
 * {@link RefreshEdgeLayoutScopePredicate} are updated.
 * 
 * @author Florian Barbin
 * 
 */
public class EdgeLayoutUpdaterModelChangeTrigger implements ModelChangeTrigger {

    public static final int PRIORITY = SiriusDiagramSessionEventBroker.PRIORITY + 1;

    private TransactionalEditingDomain transactionalEditingDomain;

    private SessionEventBroker eventBroker;

    /**
     * Constructor. Add this EdgeLayoutUpdaterModelChangeTrigger to the session
     * event broker of the given session.
     * 
     * @param transactionalEditingDomain
     *            the editing domain.
     * @param session
     *            the session.
     * @param dDiagram
     *            the ddiagram.
     */
    public EdgeLayoutUpdaterModelChangeTrigger(Session session, DDiagram dDiagram) {
        this.transactionalEditingDomain = session.getTransactionalEditingDomain();
        eventBroker = session.getEventBroker();

        eventBroker.addLocalTrigger(new RefreshEdgeLayoutNotificationFilter(dDiagram), this);
    }

    @Override
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        Command command = new CompoundCommand();

        // this list contains gmf edges for which we already created a
        // CenterEdgeEndModelChangeOperation. This list aims to avoid creating
        // multi operation for a same gmfEdge in the case we are several
        // notification for it.
        List<Edge> edgesWithCreatedCommand = new ArrayList<Edge>();

        for (Notification notification : notifications) {
            Object notifier = notification.getNotifier();
            Edge gmfEdge = null;
            if (notifier instanceof DEdge) {
                gmfEdge = SiriusGMFHelper.getGmfEdge((DEdge) notifier);
            } else if (notifier instanceof EdgeStyle) {
                EObject container = ((EdgeStyle) notifier).eContainer();
                if (container instanceof DEdge) {
                    gmfEdge = SiriusGMFHelper.getGmfEdge((DEdge) container);
                }
            }
            if (gmfEdge != null && !edgesWithCreatedCommand.contains(gmfEdge)) {
                ((CompoundCommand) command).append(CommandFactory.createRecordingCommand(transactionalEditingDomain, new CenterEdgeEndModelChangeOperation(gmfEdge)));
                edgesWithCreatedCommand.add(gmfEdge);
            }
        }
        return Options.newSome(command);
    }

    @Override
    public int priority() {
        return PRIORITY;
    }

    /**
     * Dispose this trigger. The trigger is removed from the session event
     * broker.
     */
    public void dispose() {
        eventBroker.removeLocalTrigger(this);
        eventBroker = null;
        transactionalEditingDomain = null;

    }
}
