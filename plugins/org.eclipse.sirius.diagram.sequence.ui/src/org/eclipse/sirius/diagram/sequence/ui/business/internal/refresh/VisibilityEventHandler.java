/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.business.internal.refresh;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.ui.SequenceDiagramUIPlugin;
import org.eclipse.sirius.diagram.sequence.ui.business.api.diagramtype.SequenceDiagramTypeProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.util.NotificationQuery;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * A listener which refreshes and synchronizes the global ordering of elements
 * after a command has been executed.
 * 
 * @author pcdavid
 */
public class VisibilityEventHandler extends ResourceSetListenerImpl {
    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    @Override
    public boolean isPostcommitOnly() {
        return false;
    }

    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    /**
     * Aborts any transaction which change the visibility of elements, as this
     * is not supported in sequence diagrams.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        if (containsVisibilityEvent(event)) {
            throw new RollbackException(new Status(IStatus.ERROR, SequenceDiagramUIPlugin.PLUGIN_ID, "Hide/Reveal is not supported in Sequence Diagrams"));
        }
        return null;
    }

    private boolean containsVisibilityEvent(ResourceSetChangeEvent event) {
        Predicate<Notification> isVisibilityEvent = new Predicate<Notification>() {
            public boolean apply(Notification input) {
                NotificationQuery nq = new NotificationQuery(input);
                return nq.isViewBecomingInvisibleEvent() || nq.isHideFilterAddEvent();
            }
        };

        Predicate<Notification> isAlwaysVisibleSequenceElement = new Predicate<Notification>() {
            public boolean apply(Notification input) {
                Object notifier = input.getNotifier();
                if (notifier instanceof DDiagramElement) {
                    DDiagramElement dde = (DDiagramElement) notifier;
                    DDiagram parentDiagram = dde.getParentDiagram();
                    return parentDiagram instanceof SequenceDDiagram && !(new SequenceDiagramTypeProvider().allowsHideReveal(dde));
                }
                return false;
            }
        };
        return Iterables.any(Iterables.filter(event.getNotifications(), Notification.class), Predicates.and(isAlwaysVisibleSequenceElement, isVisibilityEvent));
    }
}
