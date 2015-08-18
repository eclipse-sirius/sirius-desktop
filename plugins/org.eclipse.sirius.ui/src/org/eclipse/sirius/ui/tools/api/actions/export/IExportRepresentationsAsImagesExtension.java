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

/**
 * The interface which list constants for
 * org.eclipse.sirius.editor.exportRepresentationsAsImagesExtension extension
 * point.
 * 
 * This interface is not intended to be implemented
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public interface IExportRepresentationsAsImagesExtension {

    /** The extension point ID. */
    String ID = "org.eclipse.sirius.ui.exportRepresentationsAsImagesExtension"; //$NON-NLS-1$

    /** The class attribute. */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$
}
