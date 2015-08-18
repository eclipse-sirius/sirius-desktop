/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.editing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.tools.api.query.NotificationQuery;
import org.eclipse.sirius.common.tools.api.resource.FileModificationValidatorProvider;
import org.eclipse.sirius.common.tools.api.resource.IFileModificationValidator;

/**
 * ResourceSetListener responsible for asking for file edit validation before
 * commits.
 * 
 * @author cedric
 * 
 * @since 0.9.0
 */
public class FileStatusPrecommitListener extends ResourceSetListenerImpl {

    private static final String FILE_MODIFICATION_VALIDATION_STATUS = "File modification validation status"; //$NON-NLS-1$

    private final ArrayList<IFileModificationValidator> fileModificationValidators;

    /**
     * Create a new listener.
     */
    public FileStatusPrecommitListener() {
        super();
        fileModificationValidators = FileModificationValidatorProvider.INSTANCE.getFileModificationValidator();
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
     */
    @Override
    public Command transactionAboutToCommit(final ResourceSetChangeEvent event) throws RollbackException {
        final boolean defensiveEditValidation = Platform.getPreferencesService().getBoolean("org.eclipse.sirius.common.ui", CommonPreferencesConstants.PREF_DEFENSIVE_EDIT_VALIDATION, true, null); //$NON-NLS-1$
        final Command cmd = super.transactionAboutToCommit(event);
        if (defensiveEditValidation) {
            final Set<Resource> changedRes = Sets.newLinkedHashSet();
            if (!event.getTransaction().isReadOnly()) {
                for (org.eclipse.emf.common.notify.Notification notif : Iterables.filter(event.getNotifications(), org.eclipse.emf.common.notify.Notification.class)) {
                    if (notif.getNotifier() instanceof EObject) {
                        final Resource res = ((EObject) notif.getNotifier()).eResource();
                        if (resourceChange(res, notif)) {
                            changedRes.add(res);
                        }
                    }
                }
            }

            final BiMap<IFile, Resource> files2Validate = HashBiMap.create();
            final Iterator<Resource> it = changedRes.iterator();
            while (it.hasNext()) {
                final Resource nextResource = it.next();
                final IFile file = WorkspaceSynchronizer.getFile(nextResource);
                if (file != null && file.isReadOnly()) {
                    files2Validate.put(file, nextResource);
                }
            }

            if (!files2Validate.isEmpty()) {
                final RollbackException cancelException = new RollbackException(new Status(IStatus.CANCEL, DslCommonPlugin.PLUGIN_ID, FILE_MODIFICATION_VALIDATION_STATUS));
                final MultiStatus status = new MultiStatus(DslCommonPlugin.PLUGIN_ID, IStatus.ERROR, FILE_MODIFICATION_VALIDATION_STATUS, cancelException);
                if (fileModificationValidators.isEmpty()) {
                    // No extension found, use the default process.
                    status.add(ResourcesPlugin.getWorkspace().validateEdit(files2Validate.keySet().toArray(new IFile[files2Validate.size()]), IWorkspace.VALIDATE_PROMPT));
                } else {
                    for (final IFileModificationValidator fileModificationValidator : fileModificationValidators) {
                        final IStatus validationStatus = fileModificationValidator.validateEdit(files2Validate.keySet());
                        if (validationStatus != null) {
                            status.add(validationStatus);
                        }
                    }
                }

                if (!status.isOK()) {
                    throw cancelException;
                }
            }
        }
        return cmd;
    }

    /**
     * Check if the notification is a resource change.
     * 
     * @param resource
     *            the resource
     * @param notif
     *            the notification
     * @return <code>true</code> if the resource is not <code>null</code> and
     *         the notification will change the resource
     */
    protected boolean resourceChange(Resource resource, Notification notif) {
        return resource != null && !new NotificationQuery(notif).isTransientNotification();
    }

}
