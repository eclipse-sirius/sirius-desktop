/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.api;

import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.ui.business.api.dialect.ExportResult;

/**
 * An {@link ExportResult} with additional diagram-specific metadata.
 *
 * @author pcdavid
 */
public class DiagramExportResult extends ExportResult {
    private final double scalingFactor;

    /**
     * Create a new DiagramExportResult.
     *
     * @param diagram
     *            the diagram the was exported.
     * @param scalingFactor
     *            the scaling factor that was used when exporting the diagram.
     * @param exportedFiles
     *            the files produced by this export.
     */
    public DiagramExportResult(DDiagram diagram, double scalingFactor, Collection<IPath> exportedFiles) {
        super(diagram, exportedFiles);
        this.scalingFactor = scalingFactor;
    }

    /**
     * Returns the scaling factor that was used when exporting the diagram. 1.0 represents a 100% zoom level, 2.0 is
     * 200% zoom level, etc.
     *
     * @return the scaling factor that was used when exporting the diagram.
     */
    public double getScalingFactor() {
        return scalingFactor;
    }
}
