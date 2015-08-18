/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.resource;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.query.NotificationQuery;
import org.eclipse.sirius.common.tools.internal.resource.WorkspaceBackend;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * A new implementation of a common synchronizer for the EMF Resource with
 * external events (like, for instance, workspace). You can attach the
 * synchronizer to a {@link ResourceSet} and then either get notified of
 * resource status change or query the synchronizer to get the status.
 * 
 * This implementation is based on several {@link AbstractResourceSyncBackend}
 * dedicated to a particular synchronization. For instance one of the backends
 * takes care of updating the status in regard of the Eclipse Workspace events.
 * 
 * @author cbrun
 * @since 0.9.0
 */
public final class ResourceSetSync extends ResourceSetListenerImpl implements ResourceSyncClient {

    /**
     * A scheme indicating that the resource is located on a cdo repository
     * (only use to not consider CDOResources as read-only, see
     * {@link ResourceSetSync#isReadOnly(Resource)}).
     */
    public static final String CDO_URI_SCHEME = "cdo"; //$NON-NLS-1$

    private static final String FILE_MODIFICATION_VALIDATION_STATUS = "File modification validation status"; //$NON-NLS-1$

    private boolean notificationIsRequired = true;

    private final List<AbstractResourceSyncBackend> backends = Lists.newArrayList();

    private final List<ResourceSyncClient> clients = Lists.newArrayList();

    private final Map<Resource, ResourceStatus> statuses = Maps.newHashMap();

    private Collection<Resource> savedResources = Sets.newLinkedHashSet();

    private final ArrayList<IFileModificationValidator> fileModificationValidators;

    /**
     * The {@link ResourceStatus} represents the in memory status of a resource
     * in regard of the physical one.
     * 
     * @author cbrun
     * 
     */
    public enum ResourceStatus {
        /**
         * The resource in-memory status in regard of the physical data.
         */
        CHANGED, EXTERNAL_CHANGED, CONFLICTING_CHANGED, CONFLICTING_DELETED, DELETED, SYNC, CHANGES_CANCELED, UNKNOWN
    }

    /**
     * Instantiate a new {@link ResourceSetSync}. You should attach it to a
     * {@link ResourceSet} using eAdapters() and kick install() to get
     * everything in place.
     */
    private ResourceSetSync() {
        super(NotificationFilter.ANY);
        addDefaultBackends();
        fileModificationValidators = FileModificationValidatorProvider.INSTANCE.getFileModificationValidator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resourceSetChanged(final ResourceSetChangeEvent event) {
        Collection<ResourceStatusChange> changes = Lists.newArrayList();
        for (Notification notif : Iterables.filter(event.getNotifications(), Notification.class)) {
            if (!isCustom(notif)) {
                notifyChanged(notif, changes);
            }
            if (notif.getNotifier() instanceof ResourceSet) {
                if (notif.getEventType() == Notification.ADD && notif.getNewValue() instanceof Resource) {
                    newResourceOnTheResourceSet((Resource) notif.getNewValue(), changes);
                } else if (notif.getEventType() == Notification.REMOVE && notif.getOldValue() instanceof Resource) {
                    removeResource((Resource) notif.getNewValue());
                }
            }
        }
        notifyClientsInBatch(changes);
    }

    private boolean isCustom(Notification notif) {
        return notif.getEventType() == -1;
    }

    /**
     * This method is an utility method to retrieve or create the synchronizer
     * from a resourceset. If not is found, then it install a new one.
     * 
     * Do not forget to uninstall this synchronizer once you're done with it or
     * you'll have a major memory leak.
     * 
     * @param domain
     *            editing domain to inspect.
     * @return the synchronizer to get the resources status.
     */
    public static ResourceSetSync getOrInstallResourceSetSync(final TransactionalEditingDomain domain) {
        ResourceSetSync sync = ResourceSetSync.getResourceSetSync(domain.getResourceSet());
        if (sync == null) {
            sync = new ResourceSetSync();
            sync.setTarget(domain.getResourceSet());
            sync.install();
            domain.addResourceSetListener(sync);
        }
        return sync;
    }

    /**
     * This method is an utility method to retrieve an existing synchronizer
     * from a resourceset.
     * 
     * Do not forget to uninstall this synchronizer once you're done with it or
     * you'll have a major memory leak.
     * 
     * @param domain
     *            editing domain to inspect.
     * @return an optional synchronizer.
     */
    public static Option<ResourceSetSync> getResourceSetSync(final TransactionalEditingDomain domain) {
        return Options.newSome(getResourceSetSync(domain.getResourceSet()));
    }

    private static ResourceSetSync getResourceSetSync(final ResourceSet resourceSet) {
        if (resourceSet != null) {
            Iterator<MarkerAdapter> it = Iterators.filter(resourceSet.eAdapters().iterator(), MarkerAdapter.class);
            if (it.hasNext()) {
                return it.next().getSync();
            }
        }
        return null;
    }

    /**
     * Return the resource status, unknown if the synchronizer knows nothing
     * about this resource.
     * 
     * @param res
     *            the resource to check.
     * @return the resource status, unknown if the synchronizer knows nothing
     *         about this resource.
     * 
     */
    public static ResourceStatus getStatus(final Resource res) {
        ResourceStatus result = ResourceStatus.UNKNOWN;
        ResourceSet rs = res.getResourceSet();
        if (rs != null) {
            ResourceSetSync rss = ResourceSetSync.getResourceSetSync(rs);
            if (rss != null) {
                result = rss.getResourceStatus(res);
            }
        }
        return result;
    }

    /**
     * Investigate if a resource is read-only.
     * 
     * @param resource
     *            the resource to check.
     * @return if a resource is read-only.
     */
    public static boolean isReadOnly(final Resource resource) {
        boolean readonly = false;
        URI resourceURI = resource.getURI();
        ResourceSet resourceSet = resource.getResourceSet();
        if (resourceURI != null && isMetamodel(resource)
                || (resourceSet != null && Boolean.TRUE.equals(resourceSet.getURIConverter().getAttributes(resourceURI, null).get(URIConverter.ATTRIBUTE_READ_ONLY)))) {
            readonly = true;
        }
        return readonly;
    }

    /**
     * Test if the specified resource corresponds to a {@link EPackage} from
     * central {@link EPackage.Registry} or one from {@link ResourceSet}.
     * 
     * @param resource
     *            the {@link Resource} to test
     * @return true if the resource corresponds to a {@link EPackage}, false
     *         otherwise
     */
    private static boolean isMetamodel(Resource resource) {
        boolean isMetamodel = false;
        URI resourceURI = resource.getURI();
        ResourceSet resourceSet = resource.getResourceSet();
        isMetamodel = EPackage.Registry.INSTANCE.containsKey(resourceURI.toString()) || resourceSet != null && resourceSet.getPackageRegistry().containsKey(resourceURI.toString());
        return isMetamodel;
    }

    /**
     * This method is an utility method to remove the synchronizer from a
     * resourceset.
     * 
     * @param domain
     *            domain to remove the synchronizer from.
     */
    public static void uninstallResourceSetSync(final TransactionalEditingDomain domain) {
        final ResourceSetSync sync = ResourceSetSync.getResourceSetSync(domain.getResourceSet());
        if (sync != null) {
            sync.uninstall();
            for (MarkerAdapter adapter : Lists.newArrayList(Iterables.filter(domain.getResourceSet().eAdapters(), MarkerAdapter.class))) {
                domain.getResourceSet().eAdapters().remove(adapter);
            }
            domain.removeResourceSetListener(sync);
        }
    }

    private void addDefaultBackends() {
        IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();
        if (workspaceRoot != null) {
            backends.add(new WorkspaceBackend(this));
        }
    }

    private ResourceStatus getResourceStatus(final Resource res) {
        return retrieveOldStatus(res);
    }

    /**
     * prepare and init the synchronizer.
     */
    protected void install() {
        for (final AbstractResourceSyncBackend backend : backends) {
            backend.install();
        }
    }

    private void newResourceOnTheResourceSet(final Resource res, Collection<ResourceStatusChange> changes) {
        /*
         * tracking modification means listening to any change to the resource
         * and setting isModified = true if there is a change. The problem is
         * that this behavior is based on eAdapters and as such the
         * isModified=>true change will be made even if a transaction has been
         * canceled (and rollbacked.) as such we should not rely on this
         * mechanism but instead simulate it through our own resource set
         * listener (only notified when a transaction is validated).
         */
        if (res.isModified()) {
            resourceNewStatus(res, ResourceStatus.CHANGED, changes);
        }
    }

    private void removeResource(Resource newValue) {
        statuses.remove(newValue);
    }

    /**
     * {@inheritDoc}
     * 
     * @param changes
     */
    public void notifyChanged(final Notification notification, Collection<ResourceStatusChange> changes) {
        final Object notifier = notification.getNotifier();
        if (notifier instanceof EObject && !notification.isTouch() && !new NotificationQuery(notification).isTransientNotification()) {
            final Resource resource = ((EObject) notifier).eResource();
            if (resource != null) {
                handleResourceChange(resource, changes);
            }
        } else if (notifier instanceof Resource) {
            final Resource res = (Resource) notifier;
            if (notification.getFeatureID(null) == Resource.RESOURCE__IS_MODIFIED && !res.isModified()) {
                resourceNewStatus(res, ResourceStatus.SYNC, changes);
            } else if (res.isModified() && notification.getFeatureID(null) == Resource.RESOURCE__CONTENTS) {
                handleResourceChange(res, changes);
            }
        }
    }

    private void handleResourceChange(final Resource resource, Collection<ResourceStatusChange> changes) {
        resourceNewStatus(resource, ResourceStatus.CHANGED, changes);
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    /**
     * Register a new client to the synchronizer.
     * 
     * @param client
     *            a new client requesting updates.
     */
    public void registerClient(final ResourceSyncClient client) {
        clients.add(client);
    }

    private ResourceStatus retrieveOldStatus(final Resource resource) {
        ResourceStatus status = statuses.get(resource);
        /*
         * If we've got no information that mean we had no event from the
         * backends.
         */
        if (status == null) {
            status = ResourceStatus.UNKNOWN;
        }
        return status;
    }

    /**
     * Tell whether the synchronizer should notify it's client or not. Default
     * is true.
     * 
     * @param newValue
     *            the new value telling whether the notification is required or
     *            not.
     */
    public void setNotificationIsRequired(final boolean newValue) {
        this.notificationIsRequired = newValue;
    }

    private void resourceNewStatus(final Resource resource, final ResourceStatus newStatus, Collection<ResourceStatusChange> changes) {
        final ResourceStatus oldStatus = retrieveOldStatus(resource);
        if (oldStatus != newStatus && !(resource.getURI() != null && resource.getURI().isPlatformPlugin())) {
            statuses.put(resource, newStatus);
            changes.add(new ResourceStatusChange(resource, newStatus, oldStatus));
            notifyClientsWhileProcessing(resource, newStatus, oldStatus);
        }
    }

    private void notifyClientsWhileProcessing(final Resource resource, final ResourceStatus newStatus, final ResourceStatus oldStatus) {
        if (notificationIsRequired) {
            for (final ResourceSyncClient client : Lists.newArrayList(clients)) {
                client.statusChanged(resource, oldStatus, newStatus);
            }
        }
    }

    private void notifyClientsInBatch(Collection<ResourceStatusChange> changes) {
        if (notificationIsRequired && changes.size() > 0) {
            for (final ResourceSyncClient client : Lists.newArrayList(clients)) {
                client.statusesChanged(changes);
            }
        }
    }

    /**
     * Set the resourceSet sync resourceset to listen from.
     * 
     * @param target
     *            the resourceset to listen.
     */
    protected void setTarget(final ResourceSet target) {
        target.eAdapters().add(new MarkerAdapter(this));
        for (final AbstractResourceSyncBackend backend : backends) {
            backend.setResourceSet(target);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void statusChanged(final Resource resource, final ResourceStatus oldStatus, final ResourceStatus newStatus) {
        /*
         * we're handling the changes from the workspace backend in batch in the
         * statusesChanges method.
         */
        statusesChanged(Collections.singletonList(new ResourceStatusChange(resource, newStatus, oldStatus)));
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * You should not need to call this method, it's called by the synchronizer
     * backends to give updates to the synchronizer.
     */
    @Override
    public void statusesChanged(Collection<ResourceStatusChange> changesFromBackend) {
        Collection<ResourceStatusChange> changesToTransmit = Sets.newLinkedHashSet();
        for (ResourceStatusChange changeFromWorkspace : changesFromBackend) {
            if (!itsAChangeWeReExpectingFromOurSave(changeFromWorkspace.getResource(), changeFromWorkspace.getNewStatus())) {

                /*
                 * the ResourceSetSync is client of his backend but leverage
                 * events only when listening.
                 */
                resourceNewStatus(changeFromWorkspace.getResource(), changeFromWorkspace.getNewStatus(), changesToTransmit);
            }
        }

        notifyClientsInBatch(changesToTransmit);

    }

    private boolean itsAChangeWeReExpectingFromOurSave(final Resource resource, final ResourceStatus newStatus) {
        return (newStatus == ResourceStatus.EXTERNAL_CHANGED || newStatus == ResourceStatus.CONFLICTING_CHANGED || newStatus == ResourceStatus.SYNC) && savedResources.remove(resource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuffer buff = new StringBuffer();
        for (final Entry<Resource, ResourceStatus> entry : statuses.entrySet()) {
            buff.append(entry.getKey().getURI());
            buff.append(":"); //$NON-NLS-1$
            buff.append(entry.getValue());
        }

        return buff.toString();
    }

    /**
     * de-init the synchronizer.
     */
    protected void uninstall() {

        for (final AbstractResourceSyncBackend backend : backends) {
            backend.uninstall();
        }
        statuses.clear();
    }

    /**
     * Remove a client from the list of client to notify.
     * 
     * @param client
     *            client to remove.
     */
    public void unregisterClient(final ResourceSyncClient client) {
        clients.remove(client);

    }

    /**
     * Save the resources making sure you won't get notified back.
     * 
     * @param resourcesToSave
     *            resources to save.
     * @param resourcesToUpdateStatus
     *            resources to make "SYNC" once the save is done.
     * @param saveOptions
     *            options for the save operation.
     * @throws IOException
     *             in case of error while saving.
     * @throws InterruptedException
     *             if the operation can't be finished.
     */

    public void save(final Iterable<Resource> resourcesToSave, final Iterable<Resource> resourcesToUpdateStatus, final Map<?, ?> saveOptions) throws IOException, InterruptedException {
        Collection<ResourceStatusChange> changesToTransmit = Sets.newLinkedHashSet();
        doSave(resourcesToSave, saveOptions, changesToTransmit);
        for (Resource resource : resourcesToUpdateStatus) {
            ResourceStatus oldStatus = retrieveOldStatus(resource);
            if (oldStatus == ResourceStatus.UNKNOWN || oldStatus == ResourceStatus.CHANGED) {
                resourceNewStatus(resource, ResourceStatus.SYNC, changesToTransmit);
            }
        }
        notifyClientsInBatch(changesToTransmit);
    }

    private void doSave(final Iterable<Resource> resourcesToSave, final Map<?, ?> saveOptions, Collection<ResourceStatusChange> changesToTransmit) throws InterruptedException, IOException {
        final Collection<IFile> files2Validate = Lists.newArrayList();
        for (Resource resourceToSave : resourcesToSave) {
            if (resourceToSave.getURI().isPlatformResource()) {
                IFile file = WorkspaceSynchronizer.getFile(resourceToSave);
                if (file != null && file.isReadOnly()) {
                    files2Validate.add(file);
                }
            }
        }

        if (files2Validate.size() > 0) {
            final MultiStatus status = new MultiStatus(DslCommonPlugin.PLUGIN_ID, IStatus.ERROR, ResourceSetSync.FILE_MODIFICATION_VALIDATION_STATUS, null);
            if (fileModificationValidators.size() == 0) {
                // No extension found, use the default process.
                status.add(ResourcesPlugin.getWorkspace().validateEdit(files2Validate.toArray(new IFile[files2Validate.size()]), IWorkspace.VALIDATE_PROMPT));
            } else {
                for (final IFileModificationValidator fileModificationValidator : fileModificationValidators) {
                    final IStatus validationStatus = fileModificationValidator.validateEdit(files2Validate);
                    if (validationStatus != null) {
                        status.add(validationStatus);
                        if (!validationStatus.isOK()) {
                            break;
                        }
                    }
                }
            }

            if (status.getChildren().length == 0 || !status.isOK()) {
                throw new InterruptedException(status.getPlugin() + ":" + status.getMessage()); //$NON-NLS-1$
            }
        }

        this.savedResources = Sets.newLinkedHashSet();

        for (final Resource resource : resourcesToSave) {
            try {
                savedResources.add(resource);
                resource.save(saveOptions);
                resource.setModified(false);

            } catch (final UnknownServiceException e) {
                // if we're here we are probably trying to save model which
                // can't be saved (for instance if it's in the plugins)
            }

        }
        for (Resource res : resourcesToSave) {
            resourceNewStatus(res, ResourceStatus.SYNC, changesToTransmit);
        }
    }

    /**
     * Adds a file modification validator manually.
     * 
     * @param fileModificationValidator
     *            : a file modification validator
     */
    public void addFileModificationValidator(final IFileModificationValidator fileModificationValidator) {
        fileModificationValidators.add(fileModificationValidator);
    }

    /**
     * Removes a file modification validator manually.
     * 
     * @param fileModificationValidator
     *            : a file modification validator
     */
    public void removeFileModificationValidator(final IFileModificationValidator fileModificationValidator) {
        fileModificationValidators.remove(fileModificationValidator);
    }

    /**
     * Adapter to retrieve.the resourcesetSynchronizer from any resource set.
     */
    class MarkerAdapter extends AdapterImpl {
        private ResourceSetSync synchronizer;

        public MarkerAdapter(final ResourceSetSync synchronizer) {
            super();
            this.synchronizer = synchronizer;
        }

        public ResourceSetSync getSync() {
            return synchronizer;
        }
    }
}
