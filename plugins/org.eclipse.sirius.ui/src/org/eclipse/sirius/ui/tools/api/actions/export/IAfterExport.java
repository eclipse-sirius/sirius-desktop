/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.api.actions.export;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;

/**
 * The interface to implement to contribute an action after the diagrams export
 * as images.
 * 
 * @author mchauvin
 */
public interface IAfterExport {

    /**
     * This method is called after a global export operation.
     */
    void afterExportAction();

    /**
     * This method is called after each diagram export operation.
     * 
     * @param element
     *            the viewpoint metamodel element exported
     * @param filePath
     *            the file path of the image
     */
    void afterExportRepresentationAsImage(EObject element, IPath filePath);

}
