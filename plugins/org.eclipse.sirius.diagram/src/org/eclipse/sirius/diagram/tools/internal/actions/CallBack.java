/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.actions;

import org.eclipse.ui.IEditorPart;
import org.eclipse.sirius.viewpoint.DNavigationLink;

/**
 * A CallBack is usefull when ones want's to add special behavior once the
 * editor is opened.
 * 
 * @author cbrun <a href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 * 
 */
public interface CallBack {
    /**
     * This method is called after the editor has been opened. It's mostly used
     * to select a given element.
     * 
     * @param link
     *            Opened navigation link.
     * @param editor
     *            Opened editor.
     */
    void postOpeningRun(DNavigationLink link, IEditorPart editor);
}
