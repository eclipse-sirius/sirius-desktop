/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * A ModelChangeTrigger listener to synchronize the name of a
 * {@link DRepresentationDescriptor} with the name of its associated
 * {@link DRepresentation}.
 * 
 * @author lfasani
 */
public class RepresentationNameSynchroListener implements ModelChangeTrigger {

    /**
     * Priority of this {@link ModelChangeTrigger}.
     */
    public static final int PRIORITY = 0;

    private TransactionalEditingDomain domain;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the contextual {@link TransactionalEditingDomain}
     */
    public RepresentationNameSynchroListener(TransactionalEditingDomain domain) {
        this.domain = domain;
    }

    @Override
    public int priority() {
        return PRIORITY;
    }

    @Override
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        CompoundCommand cmd = new CompoundCommand();
        for (Notification notification : notifications) {
            Object notifier = notification.getNotifier();
            Object feature = notification.getFeature();
            if (notifier instanceof DRepresentation && feature == ViewpointPackage.Literals.DREPRESENTATION__NAME) {
                DRepresentation representation = (DRepresentation) notifier;
                DRepresentationDescriptor repDescriptor = new DRepresentationQuery(representation).getRepresentationDescriptor();
                cmd.append(new SetCommand(domain, repDescriptor, ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__NAME, representation.getName()));
            } else if (notifier instanceof DRepresentationDescriptor && feature == ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__NAME) {
                DRepresentationDescriptor repDescriptor = (DRepresentationDescriptor) notifier;
                DRepresentation representation = repDescriptor.getRepresentation();
                if (representation != null) {
                    cmd.append(new SetCommand(domain, representation, ViewpointPackage.Literals.DREPRESENTATION__NAME, repDescriptor.getName()));
                }
            }
        }
        return Options.newSome(cmd.unwrap());
    }
}
