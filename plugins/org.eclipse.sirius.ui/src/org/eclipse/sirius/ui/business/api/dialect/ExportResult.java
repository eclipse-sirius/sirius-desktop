/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.ui.business.api.dialect;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Provides meta-data about the result of a represention export.
 * 
 * @author pcdavid
 */
public class ExportResult {
    /**
     * The representation that was exported.
     */
    private final DRepresentation representation;

    /**
     * The paths to all the files produced by the export operation.
     */
    private final Set<IPath> exportedFiles = new LinkedHashSet<>();

    /**
     * Create a new {@link ExportResult}.
     * 
     * @param representation
     *            the representation that was exported.
     * @param exportedFiles
     *            the files produced by this exoort.
     */
    public ExportResult(DRepresentation representation, Collection<IPath> exportedFiles) {
        this.representation = representation;
        this.exportedFiles.addAll(exportedFiles);
    }

    /**
     * Returns the representation that was exported.
     * 
     * @return the representation that was exported.
     */
    public DRepresentation getRepresentation() {
        return representation;
    }

    /**
     * Returns the paths of the files produced by the export operation.
     * 
     * @return the paths of the files produced by the export operation.
     */
    public Set<IPath> getExportedFiles() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(exportedFiles));
    }
}
