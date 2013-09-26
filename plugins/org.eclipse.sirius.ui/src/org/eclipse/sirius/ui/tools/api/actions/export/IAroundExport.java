/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.actions.export;

import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * The interface to implement to contribute an action to execute in instead of
 * the standard action to export the diagram as images.
 * 
 * @author mchauvin
 */
public interface IAroundExport {

    /**
     * This method will be called to export diagram as images instead of the
     * standard action to export diagrams as images.
     * 
     * @param representations
     *            a list of {@link DRepresentations} instances candidates to be
     *            exported
     * @param destination
     *            the destination folder chosen by the end user
     * @param format
     *            the file format represented by the file extension.
     */
    void aroundExportAction(Collection<DRepresentation> representations, IPath destination, String format);

}
