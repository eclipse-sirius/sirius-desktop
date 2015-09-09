/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
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
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.internal.session.danalysis.ResourceSaveDiagnose;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A policy implementing save operation.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public class SavingPolicyImpl extends AbstractSavingPolicy {
    private Map<?, ?> saveOptions;

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain to use
     */
    public SavingPolicyImpl(TransactionalEditingDomain domain) {
        super(domain);
    }

    /**
     * Determines if a resource should be saved by actually saving it in a
     * temporary location and comaring the result to the current serialization.
     * The result is safe, but the method is costly.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected Collection<Resource> computeResourcesToSave(Set<Resource> scope, Map<?, ?> options, IProgressMonitor monitor) {
        this.saveOptions = options;
        final Predicate<Resource> savingFilter = new Predicate<Resource>() {
            @Override
            public boolean apply(final Resource input) {
                return shouldSave(input);
            }
        };
        return Lists.newArrayList(Iterables.filter(scope, savingFilter));
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
            SiriusPlugin.getDefault().error(Messages.SavingPolicyImpl_savingErrorMsg, e);
        }
        return hasChangesToSave;
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

}
