/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.modelingproject;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A specific Sirius job for the representationsFile jobs (to add a specific
 * family).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public abstract class AbstractRepresentationsFileJob extends WorkspaceJob {
    /**
     * The family id for this kind of job.
     */
    public static final String FAMILY = SiriusPlugin.ID + ".representationsFile"; //$NON-NLS-1$

    /**
     * Default constructor.
     * 
     * @param name
     *            the name of the job (a human-readable value that is displayed
     *            to users)
     */
    public AbstractRepresentationsFileJob(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
     */
    @Override
    public boolean belongsTo(Object family) {
        return FAMILY.equals(family);
    }
}
