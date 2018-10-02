/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * A policy implementing save operation.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface SavingPolicy {
    /**
     * Save the given resources with the provided options. The caller has the
     * responsibility to call this method in a
     * {@link org.eclipse.core.resources.IWorkspaceRunnable} if it modifies the
     * workspace.
     * 
     * @param resourcesToSave
     *            the resources to save
     * @param options
     *            the options to use for saving
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of the saving
     * @return the list of saved resource
     */
    Collection<Resource> save(Iterable<Resource> resourcesToSave, Map<?, ?> options, IProgressMonitor monitor);
}
