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
package org.eclipse.sirius.business.api.session;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.business.internal.session.danalysis.ResourceSaveDiagnose;

/**
 * A policy implementing save operation.
 * 
 * @author mchauvin
 * @since 2.7
 */
public class SavingPolicyImpl implements SavingPolicy {

    private TransactionalEditingDomain domain;

    private Map<?, ?> saveOptions;

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain to use
     */
    public SavingPolicyImpl(final TransactionalEditingDomain domain) {
        this.domain = domain;
    }

    /**
     * Tells if a save is needed for the specified resource
     */
    private boolean shouldSave(final Resource resource) {
        boolean shouldSave = hasChangesToSave(resource);
        if (!shouldSave) {
            final ResourceStatus resourceStatus = ResourceSetSync.getStatus(resource);
            shouldSave = resourceStatus == ResourceStatus.DELETED || resourceStatus == ResourceStatus.CONFLICTING_DELETED;
        }
        return shouldSave;
    }

    /**
     * Check if a resource has changes to save.
     * 
     * @param resource
     *            the resource to check
     * @return <code>true</code> if the resource has changes to save,
     *         <code>false</code> otherwise
     */
    protected boolean hasChangesToSave(final Resource resource) {
        boolean hasChangesToSave = false;
        final ResourceSaveDiagnose diagnose = new ResourceSaveDiagnose(resource);
        try {
            Map<Object, Object> mergedOptions = new HashMap<Object, Object>(getDefaultSaveOptions());
            if (saveOptions != null) {
                mergedOptions.putAll(saveOptions);
            }
            hasChangesToSave = diagnose.isSaveable() && diagnose.hasDifferentSerialization(mergedOptions);
        } catch (final IOException e) {
            SiriusPlugin.getDefault().error("Error saving resource", e);
        }
        return hasChangesToSave;
    }

    /**
     * The specified options to save are merged with
     * {@link Resource#OPTION_SAVE_ONLY_IF_CHANGED} having
     * {@link Resource#OPTION_SAVE_ONLY_IF_CHANGED_FILE_BUFFER} as value to do a
     * save only if serialization is different.
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SavingPolicy#save(java.lang.Iterable,
     *      java.util.Map)
     */
    public Collection<Resource> save(final Iterable<Resource> allResources, final Map<?, ?> options) {
        return save(allResources, options, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Resource> save(final Iterable<Resource> allResources, final Map<?, ?> options, IProgressMonitor monitor) {
        this.saveOptions = options;
        final Predicate<Resource> savingFilter = new Predicate<Resource>() {
            public boolean apply(final Resource input) {
                return shouldSave(input);
            }
        };
        final Collection<Resource> resourcesToSave = Lists.newArrayList(Iterables.filter(allResources, savingFilter));
        try {
            monitor.beginTask("Save Session", IProgressMonitor.UNKNOWN);
            if (alreadyIsInWorkspaceModificationOperation()) {
                wrappedSave(resourcesToSave, allResources, options, new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN));
            } else {
                final IWorkspace workspace = ResourcesPlugin.getWorkspace();
                try {
                    workspace.run(new IWorkspaceRunnable() {
                        public void run(final IProgressMonitor monitor) throws CoreException {
                            wrappedSave(resourcesToSave, allResources, options, monitor);
                        }
                    }, new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN));
                } catch (final CoreException e) {
                    SiriusPlugin.getDefault().error("Core exception while saving session", e);
                }
            }
        } finally {
            monitor.done();
        }
        return resourcesToSave;
    }

    private void wrappedSave(final Iterable<Resource> resourcesToSave, final Iterable<Resource> allResources, final Map<?, ?> options, IProgressMonitor monitor) {
        try {
            monitor.beginTask("Session Saving", IProgressMonitor.UNKNOWN);
            if (options == null) {
                ResourceSetSync.getOrInstallResourceSetSync(domain).save(resourcesToSave, allResources, getDefaultSaveOptions());
            } else {
                ResourceSetSync.getOrInstallResourceSetSync(domain).save(resourcesToSave, allResources, options);
            }
        } catch (final IOException e) {
            SiriusPlugin.getDefault().error("error while saving session", e);
        } catch (final InterruptedException e) {
            SiriusPlugin.getDefault().error("error while saving session", e);
        } finally {
            monitor.done();
        }
    }

    private boolean alreadyIsInWorkspaceModificationOperation() {
        final Job currentJob = Job.getJobManager().currentJob();
        return currentJob != null && currentJob.getRule() != null;
    }

    private Map<?, ?> getDefaultSaveOptions() {
        final Map<Object, Object> defaultSaveOptions = new HashMap<Object, Object>();
        defaultSaveOptions.put(XMLResource.OPTION_FLUSH_THRESHOLD, Integer.valueOf(0x01000000));
        defaultSaveOptions.put(XMLResource.OPTION_USE_FILE_BUFFER, Boolean.TRUE);
        return defaultSaveOptions;
    }

}
