/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.tools.api.editing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
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
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.Messages;
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

    private final ArrayList<IFileModificationValidator> fileModificationValidators;

    /**
     * Create a new listener.
     */
    public FileStatusPrecommitListener() {
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

    @Override
    public Command transactionAboutToCommit(final ResourceSetChangeEvent event) throws RollbackException {
        boolean defensiveEditValidation = Platform.getPreferencesService().getBoolean("org.eclipse.sirius.common.ui", CommonPreferencesConstants.PREF_DEFENSIVE_EDIT_VALIDATION, true, null); //$NON-NLS-1$
        Command cmd = super.transactionAboutToCommit(event);
        if (defensiveEditValidation) {
            Set<Resource> changedRes = getModifiedResources(event);
            Set<IFile> filesToValidate = getFilesToValidate(changedRes);
            if (!filesToValidate.isEmpty()) {
                final RollbackException cancelException = new RollbackException(new Status(IStatus.CANCEL, DslCommonPlugin.PLUGIN_ID, Messages.FileStatusPrecommitListener_fileModificationValidationStatus));
                final MultiStatus status = new MultiStatus(DslCommonPlugin.PLUGIN_ID, IStatus.ERROR, Messages.FileStatusPrecommitListener_fileModificationValidationStatus, cancelException);
                if (fileModificationValidators.isEmpty()) {
                    // No extension found, use the default process.
                    status.add(ResourcesPlugin.getWorkspace().validateEdit(filesToValidate.toArray(new IFile[filesToValidate.size()]), IWorkspace.VALIDATE_PROMPT));
                } else {
                    for (final IFileModificationValidator fileModificationValidator : fileModificationValidators) {
                        final IStatus validationStatus = fileModificationValidator.validateEdit(filesToValidate);
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

    private Set<Resource> getModifiedResources(ResourceSetChangeEvent event) {
        Set<Resource> changedRes = new LinkedHashSet<>();
        if (!event.getTransaction().isReadOnly()) {
            for (Notification notif : event.getNotifications()) {
                if (notif.getNotifier() instanceof EObject eObject) {
                    Resource res = eObject.eResource();
                    if (resourceChange(res, notif)) {
                        changedRes.add(res);
                    }
                }
            }
        }
        return changedRes;
    }

    private Set<IFile> getFilesToValidate(Set<Resource> changedRes) {
        Set<IFile> filesToValidate = new HashSet<>();
        Iterator<Resource> it = changedRes.iterator();
        while (it.hasNext()) {
            Resource nextResource = it.next();
            IFile file = WorkspaceSynchronizer.getFile(nextResource);
            if (file != null && file.isReadOnly()) {
                filesToValidate.add(file);
            }
        }
        return filesToValidate;
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
