/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.ToolBarManager;

/**
 * Interface to implement for tab bar contribution.
 * 
 * @author mchauvin
 */
public interface TabbarContribution {

    /**
     * .
     * 
     * @param tb
     *            parent toolbar, in which to contribute tool items
     * @param groupId
     *            the group id where that action will be set up after.
     */
    void create(ToolBarManager tb, String groupId);

    /**
     * .
     * 
     * @param workbenchPart
     *            the workbench part
     */
    void setPart(IDiagramWorkbenchPart workbenchPart);

    /**
     * Dispose this contribution.
     */
    void dispose();

    /**
     * Get the ID of this TabbarContribution.
     * 
     * @return the ID of this TabbarContribution
     */
    String getId();
}
