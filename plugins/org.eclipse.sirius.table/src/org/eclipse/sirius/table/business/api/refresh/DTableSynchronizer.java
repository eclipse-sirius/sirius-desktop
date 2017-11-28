/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.api.refresh;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.sirius.table.metamodel.table.DTable;

/**
 * Synchronizer for the DTable.
 * 
 * @author cbrun
 * 
 */
public interface DTableSynchronizer {
    /**
     * Refresh a {@link DTable}.
     * 
     * @param monitor
     *            monitor tracking the progress.
     */
    void refresh(IProgressMonitor monitor);

    /**
     * Set the synchronizer table.
     * 
     * @param newTable
     *            table to set.
     */
    void setTable(DTable newTable);

    /**
     * return the synchronizer table.
     * 
     * @return the synchronizer table.
     */
    DTable getTable();
}
