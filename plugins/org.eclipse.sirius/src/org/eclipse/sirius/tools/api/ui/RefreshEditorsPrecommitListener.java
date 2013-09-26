/*******************************************************************************
 * Copyright (c) 2011, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.preferences.DesignerPreferencesKeys;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.tools.api.command.ui.RefreshFilterManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A listener to refresh all Sirius opened editors. It is used as :
 * <UL>
 * <LI>precommit listener : on semantic modifications (with
 * transactionAboutToCommit())</LI>
 * <LI>session listener : on reloading of resources detecting by the session
 * itself (with notify())</LI>
 * </UL>
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class RefreshEditorsPrecommitListener extends ResourceSetListenerImpl implements SessionListener {

    /**
     * The editing domain used to create the refresh command.
     */
    TransactionalEditingDomain transactionalEditingDomain;

    /**
     * True if this listener must launch a refresh even if the autoRefresh is
     * off.
     */
    private boolean forceRefresh;

    /**
     * Representations to refresh even if any editors is opened on it.
     */
    private Collection<DRepresentation> representationsToForceRefresh = new ArrayList<DRepresentation>();

    /**
     * A list of {@link PostRefreshCommandFactory} that is called to complete
     * the refresh command. The commands provided by the factory is added after
     * the refresh command if it can be executed.
     */
    private Collection<PostRefreshCommandFactory> postRefreshCommandFactories = new ArrayList<PostRefreshCommandFactory>();

    /**
     * Default constructor.
     * 
     * @param transactionalEditingDomain
     *            The editing domain used to create the refresh command, this
     *            {@link TransactionalEditingDomain} must be the same as
     *            indicated by received {@link ResourceSetChangeEvent}s.
     */
    public RefreshEditorsPrecommitListener(TransactionalEditingDomain transactionalEditingDomain) {
        super(NotificationFilter.NOT_TOUCH);
        this.transactionalEditingDomain = transactionalEditingDomain;
    }

    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * Do a refresh only if there is at least one notification that concern
     * another thing that an aird Resource.
     * 
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)
     */
    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        Command result = null;
        if (needsRefresh()) {

            boolean impactingNotification = isImpactingNotification(event);
            // Do nothing if the notification concern only elements of aird
            // resource and that the representationsToForceRefresh is empty.
            if (impactingNotification || !representationsToForceRefresh.isEmpty()) {
                Option<? extends Command> optionCommand = getRefreshOpennedRepresentationsCommand(impactingNotification);
                if (optionCommand.some()) {
                    result = optionCommand.get();
                }
            }
        }
        setForceRefresh(false);
        representationsToForceRefresh.clear();
        if (result == null) {
            result = super.transactionAboutToCommit(event);
        }
        return result;
    }

    private boolean isImpactingNotification(final ResourceSetChangeEvent event) {
        boolean isImpactingNotification = false;
        for (Notification notification : Iterables.filter(event.getNotifications(), Notification.class)) {
            Object notifier = notification.getNotifier();
            if (notifier instanceof EObject) {
                EObject eObjectNotifier = (EObject) notifier;
                Resource notifierResource = eObjectNotifier.eResource();
                if (notifierResource != null) {
                    if (!(notifierResource.getContents().get(0) instanceof DAnalysis)) {
                        isImpactingNotification = true;
                        break;
                    }
                }
            }
        }
        return isImpactingNotification;
    }

    /**
     * Compute the refresh command or null if no refresh is needed.
     * 
     * @param isChanged
     *            True if there is some semantic changes or a reloading of the
     *            session, false otherwise
     * @return An optional command if at least one refresh is needed.
     */
    private Option<? extends Command> getRefreshOpennedRepresentationsCommand(boolean isChanged) {
        // Get all Sirius editors (that respects the
        // DialectEditor interface) */
        Option<? extends Command> result = Options.newNone();
        Collection<DRepresentation> representationsToRefresh = new LinkedHashSet<DRepresentation>();
        if (isChanged) {
            representationsToRefresh.addAll(RefreshFilterManager.INSTANCE.getOpenedRepresantationsToRefresh());
        }
        representationsToRefresh.addAll(representationsToForceRefresh);

        // Refresh only the editors of the current editing domain.
        for (DRepresentation rep : Lists.newArrayList(representationsToRefresh)) {
            if (transactionalEditingDomain != TransactionUtil.getEditingDomain(rep)) {
                representationsToRefresh.remove(rep);
            }
        }

        if (!representationsToRefresh.isEmpty()) {
            CompoundCommand cc = new CompoundCommand();
            cc.append(new RefreshRepresentationsCommand(transactionalEditingDomain, representationsToRefresh));

            // Additionnal commands ?
            for (PostRefreshCommandFactory listener : postRefreshCommandFactories) {
                cc.appendIfCanExecute(listener.getPostCommandToExecute(transactionalEditingDomain, representationsToRefresh));
            }
            postRefreshCommandFactories.clear();

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
     * Force this PrecommitListener to launch a refresh the next time it is
     * called.
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
     * Check if someone notify me for a force refresh or if the preference
     * AutoRefresh is on.
     * 
     * @return true if a refresh must be launch.
     */
    private boolean isAutoRefresh() {
        return Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, DesignerPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionListener#notify(int)
     */
    public void notify(int changeKind) {
        if (SessionListener.REPLACED == changeKind) {
            if (needsRefresh()) {
                setForceRefresh(false);
                // The session has detected a reload of a resource (possibly
                // because
                // of an outside modification of the resource) so we must also
                // launch a refresh.
                Option<? extends Command> optionCommand = getRefreshOpennedRepresentationsCommand(true);
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
     * Add a new PostRefreshCommandFactory to the
     * RefreshEditorsPrecommitListener. The commands provided by the factory is
     * added after the refresh command if it can be executed.
     * 
     * The factory is removed after the first refresh.
     * 
     * @param factory
     *            new PostRefreshCommandFactory to add.
     */
    public void addPostRefreshCommandFactory(final PostRefreshCommandFactory factory) {
        postRefreshCommandFactories.add(factory);
    }
}
