/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;

/**
 * A filler has the responsibility to fill a toolbar manager with contributions.
 * 
 * @author mchauvin
 */
public interface TabbarFiller {

    /**
     * Set the workbench part.
     * 
     * @param workbenchPart
     *            the workbench part
     */
    void setPart(IDiagramWorkbenchPart workbenchPart);

    /**
     * Fill the tab bar.
     */
    void fill();

    /**
     * Dispose the references to workbench, but not the filled contributions.
     */
    void dispose();
}
