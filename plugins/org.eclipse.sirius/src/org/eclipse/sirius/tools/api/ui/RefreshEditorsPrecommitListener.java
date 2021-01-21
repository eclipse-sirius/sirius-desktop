/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tools.api.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.internal.dialect.command.RefreshImpactedElementsCommand;
import org.eclipse.sirius.business.internal.session.danalysis.DanglingRefRemovalTrigger;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.ui.RefreshFilterManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A listener to refresh all Sirius opened editors. It is used as :
 * <UL>
 * <LI>precommit listener (a model change trigger of the
 * {@link org.eclipse.sirius.business.api.session.SessionEventBroker}) : on semantic modifications (with
 * localChangesAboutToCommit())</LI>
 * <LI>session listener : on reloading of resources detecting by the session itself (with notify())</LI>
 * </UL>
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class RefreshEditorsPrecommitListener implements ModelChangeTrigger, SessionListener {

    /**
     * Priority of this {@link ModelChangeTrigger}.
     */
    public static final int REFRESH_EDITOR_PRIORITY = DanglingRefRemovalTrigger.DANGLING_REFERENCE_REMOVAL_PRIORITY + 1;

    /**
     * Filter {@link Notification}s which are not touch. More filtering work is done later in localChangesAboutToCommit,
     * see isImpactingNotification(Collection<Notification>) which return true as soon as an impacting notification is
     * found. This is not done here for performance reason: we need the container resource of the notifier.
     */
    public static final NotificationFilter IS_IMPACTING = new NotificationFilter.Custom() {
        @Override
        public boolean matches(Notification notification) {
            return !notification.isTouch();
        }
    };

    /**
     * The editing domain used to create the refresh command.
     */
    TransactionalEditingDomain transactionalEditingDomain;

    /**
     * The session.
     */
    Session session;

    /**
     * True if this listener must launch a refresh even if the autoRefresh is off.
     */
    private boolean forceRefresh;

    /**
     * Representations to refresh even if any editors is opened on it.
     */
    private final Collection<DRepresentation> representationsToForceRefresh = new ArrayList<DRepresentation>();

    /**
     * A list of {@link PostRefreshCommandFactory} that is called to complete the refresh command. The commands provided
     * by the factory is added after the refresh command if it can be executed.
     */
    private final Collection<PostRefreshCommandFactory> postRefreshCommandFactories = new ArrayList<PostRefreshCommandFactory>();

    private boolean disabled;

    /**
     * Default constructor.
     * 
     * The editing domain used to create the refresh command.
     * 
     * @param session
     *            the session
     */
    public RefreshEditorsPrecommitListener(Session session) {
        this.transactionalEditingDomain = session.getTransactionalEditingDomain();
        this.session = session;
    }

    @Override
    public int priority() {
        return REFRESH_EDITOR_PRIORITY;
    }

    /**
     * {@inheritDoc}
     * 
     * Do a refresh only if there is at least one notification that concern another thing that an aird Resource.
     */
    @Override
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        Command result = null;
        if (!disabled) {
            if (needsRefresh()) {
                boolean impactingNotification = RefreshHelper.isImpactingNotification(notifications);
                // Do nothing if the notification concern only elements of aird
                // resource that are not in the specific graphical notification to consider (see
                // RefreshHelper.registerImpactingNotification) and that the representationsToForceRefresh is empty.
                if (impactingNotification || !representationsToForceRefresh.isEmpty()) {
                    Option<? extends Command> optionCommand = getRefreshOpenedRepresentationsCommand(impactingNotification);
                    if (optionCommand.some()) {
                        result = optionCommand.get();
                    }
                }
                setForceRefresh(false);
                representationsToForceRefresh.clear();
            } else if (RefreshHelper.isImpactingNotification(notifications)) {
                Option<? extends Command> optionCommand = getRefreshImpactedElementsCommandForOpenedRepresentations(notifications);
                if (optionCommand.some()) {
                    result = optionCommand.get();
                }
            }
        }
        disabled = false;
        return Options.newSome(result);
    }

    /**
     * Compute the refresh command or null if no refresh is needed.
     * 
     * @param isChanged
     *            True if there is some semantic changes or a reloading of the session, false otherwise
     * @return An optional command if at least one refresh is needed.
     */
    private Option<? extends Command> getRefreshOpenedRepresentationsCommand(boolean isChanged) {
        // Get all Sirius editors (that respects the
        // DialectEditor interface) */
        Option<? extends Command> result = Options.newNone();
        Collection<DRepresentation> representationsToRefresh = new LinkedHashSet<DRepresentation>();
        // if ForceRefresh is activate and automaticRefresh is disable, only the current diagram is refreshed.
        if (isChanged && isAutoRefresh()) {
            representationsToRefresh.addAll(RefreshFilterManager.INSTANCE.getOpenedRepresantationsToRefresh());
        }
        representationsToRefresh.addAll(representationsToForceRefresh);

        // Refresh only the editors of the current editing domain.
        restrictRepresentationWithinCurrentEditingDomain(representationsToRefresh);

        if (!representationsToRefresh.isEmpty()) {
            CompoundCommand cc = new CompoundCommand();
            cc.append(new RefreshRepresentationsCommand(transactionalEditingDomain, new NullProgressMonitor(), representationsToRefresh));

            // Additionnal commands ?
            for (PostRefreshCommandFactory listener : postRefreshCommandFactories) {
                cc.appendIfCanExecute(listener.getPostCommandToExecute(transactionalEditingDomain, representationsToRefresh));
            }
            postRefreshCommandFactories.clear();

            result = Options.newSome(cc);
        }
        return result;
    }

    private void restrictRepresentationWithinCurrentEditingDomain(Collection<DRepresentation> representationsToRefresh) {
        for (DRepresentation rep : new ArrayList<DRepresentation>(representationsToRefresh)) {
            if (transactionalEditingDomain != TransactionUtil.getEditingDomain(rep)) {
                representationsToRefresh.remove(rep);
            } else if (rep instanceof DSemanticDecorator) {
                DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) rep;
                EObject target = dSemanticDecorator.getTarget();
                if (target != null && transactionalEditingDomain != TransactionUtil.getEditingDomain(target)) {
                    representationsToRefresh.remove(rep);
                }
            }
        }
    }

    private Option<? extends Command> getRefreshImpactedElementsCommandForOpenedRepresentations(Collection<Notification> notifications) {
        Option<? extends Command> result = Options.newNone();
        Collection<DRepresentation> representationsToRefresh = new LinkedHashSet<DRepresentation>();
        representationsToRefresh.addAll(RefreshFilterManager.INSTANCE.getOpenedRepresantationsToRefresh());

        restrictRepresentationWithinCurrentEditingDomain(representationsToRefresh);

        if (!representationsToRefresh.isEmpty()) {
            CompoundCommand cc = new CompoundCommand();
            cc.append(new RefreshImpactedElementsCommand(transactionalEditingDomain, new NullProgressMonitor(), representationsToRefresh, notifications));
            result = Options.newSome(cc);
        }
        return result;
    }

    /**
     * Return the actual forceRefresh status.
     * 
     * @return the forceRefresh
     */
    public boolean isForceRefresh() {
        return forceRefresh;
    }

    /**
     * Force this PrecommitListener to launch a refresh the next time it is called.
     * 
     * @param forceRefresh
     *            the forceRefresh to set
     */
    public void setForceRefresh(boolean forceRefresh) {
        this.forceRefresh = forceRefresh;
    }

    private boolean needsRefresh() {
        return (isForceRefresh() || isAutoRefresh()) && !(RefreshFilterManager.INSTANCE.getOpenedRepresantationsToRefresh().isEmpty() && representationsToForceRefresh.isEmpty());
    }

    /**
     * Check if someone notify me for a force refresh or if the preference AutoRefresh is on.
     * 
     * @return true if a refresh must be launch.
     */
    private boolean isAutoRefresh() {
        return session.getSiriusPreferences().isAutoRefresh();
    }

    @Override
    public void notify(int changeKind) {
        if (SessionListener.REPLACED == changeKind) {
            if (needsRefresh()) {
                setForceRefresh(false);
                // The session has detected a reload of a resource (possibly
                // because
                // of an outside modification of the resource) so we must also
                // launch a refresh.
                Option<? extends Command> optionCommand = getRefreshOpenedRepresentationsCommand(true);
                representationsToForceRefresh.clear();
                if (optionCommand.some()) {
                    transactionalEditingDomain.getCommandStack().execute(optionCommand.get());
                }
            }
        }
    }

    /**
     * Add a representation to refresh even if no editor is opened on it.
     * 
     * @param representation
     *            Representation to add.
     */
    public void addRepresentationToForceRefresh(DRepresentation representation) {
        if (!representationsToForceRefresh.contains(representation)) {
            representationsToForceRefresh.add(representation);
        }

    }

    /**
     * Add a new PostRefreshCommandFactory to the RefreshEditorsPrecommitListener. The commands provided by the factory
     * is added after the refresh command if it can be executed.
     * 
     * The factory is removed after the first refresh.
     * 
     * @param factory
     *            new PostRefreshCommandFactory to add.
     */
    public void addPostRefreshCommandFactory(final PostRefreshCommandFactory factory) {
        postRefreshCommandFactories.add(factory);
    }

    /**
     * Disable the next refresh attempt in the current precommit loop iteration.
     */
    public void disable() {
        disabled = true;
    }
}
