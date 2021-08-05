/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.modelingproject;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * A specific Sirius job for the representationsFile jobs (to add a specific family).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public abstract class AbstractRepresentationsFileJob extends WorkspaceJob {

    /** The family id for this kind of job. */
    public static final String FAMILY = SiriusPlugin.ID + ".representationsFile"; //$NON-NLS-1$

    /**
     * A {@link ISchedulingRule} to avoid concurrent execution of {@link AbstractRepresentationsFileJob} .
     */
    private static final ISchedulingRule DEFAULT_SCHEDULING_RULE = new ISchedulingRule() {

        @Override
        public boolean isConflicting(ISchedulingRule rule) {
            return rule == this;
        }

        @Override
        public boolean contains(ISchedulingRule rule) {
            return rule == this;
        }
    };

    /**
     * Default constructor.
     * 
     * @param name
     *            the name of the job (a human-readable value that is displayed to users)
     */
    public AbstractRepresentationsFileJob(String name) {
        super(name);
        setRule(DEFAULT_SCHEDULING_RULE);
    }

    @Override
    public boolean belongsTo(Object family) {
        return FAMILY.equals(family);
    }
}
