/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

/**
 * A listener for diagram outline page events.
 * 
 * @author mchauvin
 */
public interface DiagramOutlinePageListener {

    /** outline page id. */
    int OUTLINE = DiagramOutlinePage.ID_OUTLINE;

    /** overview page id. */
    int OVERVIEW = DiagramOutlinePage.ID_OVERVIEW;

    /**
     * Activate given page.
     * 
     * @param page
     *            activated page
     */
    void activate(int page);

    /**
     * Deactivate given page.
     * 
     * @param page
     *            deactivated page
     */
    void deactivate(int page);

}
