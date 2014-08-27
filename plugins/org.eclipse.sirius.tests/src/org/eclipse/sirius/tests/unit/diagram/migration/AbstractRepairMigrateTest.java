/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.repair.SiriusRepairProcess;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

/**
 * Common test methods for repair/migrate process tests.
 * 
 * @author dlecan
 */
public abstract class AbstractRepairMigrateTest extends SiriusDiagramTestCase {

    /**
     * Get file uri from file name.
     * 
     * @param fileName
     *            File name.
     * @return Corresponding {@link URI}.
     */
    protected URI getFileURI(final String fileName) {
        return URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + fileName, true);
    }

    /**
     * Method to run repair process.
     * 
     * @param fileName
     *            File name on which to run migration process.
     * @throws Exception
     */
    protected void runRepairProcess(final String fileName) throws Exception {
        SiriusRepairProcess process = new SiriusRepairProcess(getFile(fileName), true);
        process.run(new NullProgressMonitor());
        process.dispose();
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
    }
}
