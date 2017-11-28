/*******************************************************************************
 * Copyright (c) 2014, 2015 Obeo.
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * Base class that can be easily extended to implement custom saving policies.
 * 
 * @author pcdavid
 */
public abstract class AbstractSavingPolicy implements SavingPolicy {
    /**
     * The editing domain.
     */
    protected final TransactionalEditingDomain domain;

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain to use
     */
    protected AbstractSavingPolicy(TransactionalEditingDomain domain) {
        this.domain = Preconditions.checkNotNull(domain);
    }

    @Override
    public Collection<Resource> save(final Iterable<Resource> allResources, final Map<?, ?> options, IProgressMonitor monitor) {
        final Collection<Resource> resourcesToSave = new ArrayList<>();
        try {
            monitor.beginTask(Messages.AbstractSavingPolicy_saveMsg, IProgressMonitor.UNKNOWN);
            resourcesToSave.addAll(computeResourcesToSave(Sets.newLinkedHashSet(allResources), options, monitor));
            if (options == null) {
                ResourceSetSync.getOrInstallResourceSetSync(domain).save(resourcesToSave, allResources, getDefaultSaveOptions());
            } else {
                ResourceSetSync.getOrInstallResourceSetSync(domain).save(resourcesToSave, allResources, options);
            }
        } catch (final IOException e) {
            SiriusPlugin.getDefault().error(Messages.AbstractSavingPolicy_savingErrorMsg, e);
        } catch (final InterruptedException e) {
            SiriusPlugin.getDefault().error(Messages.AbstractSavingPolicy_savingErrorMsg, e);
        } finally {
            monitor.done();
        }
        return resourcesToSave;
    }

    /**
     * The default saving options to use if none are specified (i.e. if the
     * <code>options</code> argument passed to
     * {@link #save(Iterable, Map, IProgressMonitor)} is <code>null</code>.
     * 
     * @return the default saving otions to use if none are specified.
     */
    protected Map<?, ?> getDefaultSaveOptions() {
        final Map<Object, Object> defaultSaveOptions = new HashMap<Object, Object>();
        defaultSaveOptions.put(XMLResource.OPTION_FLUSH_THRESHOLD, Integer.valueOf(0x01000000));
        defaultSaveOptions.put(XMLResource.OPTION_USE_FILE_BUFFER, Boolean.TRUE);
        return defaultSaveOptions;
    }

    /**
     * Computes the set of resources to save. Subclasses only need to override
     * this method.
     * 
     * @param scope
     *            the set of resources to consider.
     * @param options
     *            the saving options that were requested.
     * @param monitor
     *            the monitor to use to report progress.
     * @return a sub-set of the Resources in scope that must actually be saved
     *         on disk.
     */
    protected abstract Collection<Resource> computeResourcesToSave(Set<Resource> scope, Map<?, ?> options, IProgressMonitor monitor);
}
