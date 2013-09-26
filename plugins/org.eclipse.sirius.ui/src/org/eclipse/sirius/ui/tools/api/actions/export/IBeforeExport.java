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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * The interface to implement to contribute an action before the representations
 * export as images.
 * 
 * @author mchauvin
 */
public interface IBeforeExport {

    /**
     * This method is called before a global export operation is started.
     * 
     * @param representations
     *            a list of {@link EObject} instances candidates to be exported
     * @param destination
     *            the destination folder chosen by the end user
     * @param format
     *            file format represented by the file extension.
     */
    void beforeExportAction(Collection<DRepresentation> representations, IPath destination, String format);

    /**
     * This method is called before each representation export operation.
     * 
     * @param element
     *            the viewpoint metamodel element exported
     * @param filePath
     *            the file path of the image
     */
    void beforeExportRepresentationAsImage(EObject element, IPath filePath);
}
