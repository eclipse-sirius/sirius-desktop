/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;

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
     * Determines if a resource should be saved by actually saving it in a temporary location and comparing the result
     * to the current serialization. The result is safe, but the method is costly.
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
     * @return <code>true</code> if the resource has changes to save, <code>false</code> otherwise
     */
    protected boolean hasChangesToSave(final Resource resource) {
        Map<Object, Object> mergedOptions = new HashMap<Object, Object>(getDefaultSaveOptions());
        if (saveOptions != null) {
            mergedOptions.putAll(saveOptions);
        }

        return hasDifferentSerialization(resource, mergedOptions);
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
