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
package org.eclipse.sirius.diagram.sequence.business.internal.refresh;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * Default refresh layout trigger for Sequence Diagram edit part.
 * 
 * @author mporhel
 */
public class RefreshLayoutTrigger implements ModelChangeTrigger {

    /**
     * Priority of this {@link ModelChangeTrigger}.
     */
    private static final int REFRESH_LAYOUT_TRIGGER_PRIORITY = SequenceCanonicalSynchronizerAdapter.SEQUENCE_CANONICAL_REFRESH_PRIORITY + 1;

    private Diagram sequenceDiagram;

    /**
     * Constructor.
     * 
     * @param sequenceDiagram
     *            the sequence diagram to refresh.
     */
    public RefreshLayoutTrigger(Diagram sequenceDiagram) {
        this.sequenceDiagram = sequenceDiagram;
    }

    /**
     * {@inheritDoc}
     */
    public int priority() {
        return REFRESH_LAYOUT_TRIGGER_PRIORITY;
    }

    /**
     * {@inheritDoc}
     */
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(sequenceDiagram);
        if (sequenceDiagram == null || domain == null) {
            return Options.newNone();
        }
        Command refreshLayoutCommand = new RefreshLayoutCommand(domain, sequenceDiagram, true);
        return Options.newSome(refreshLayoutCommand);
    }

}
