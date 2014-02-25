/*******************************************************************************
 * Copyright (c) 2012, 2013 THALES GLOBAL SERVICES.
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
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Service implementation.
 * 
 * @author fbarbin
 */
public final class ToolBarContextService implements IToolBarContextService {

    private ToolBarManager toolBarManager;

    private IWorkbenchPage page;

    private IDiagramWorkbenchPart part;

    public ToolBarManager getMenuManager() {
        return toolBarManager;
    }

    /**
     * manager setter.
     * 
     * @param manager
     *            the toolbar manager.
     */
    public void setMenuManager(ToolBarManager manager) {
        this.toolBarManager = manager;

    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        this.toolBarManager = null;
        this.page = null;
        this.part = null;
    }

    public IWorkbenchPage getPage() {
        return page;
    }

    /**
     * page setter.
     * 
     * @param page
     *            the workbench page.
     */
    public void setPage(IWorkbenchPage page) {
        this.page = page;
    }

    public IDiagramWorkbenchPart getPart() {
        return part;
    }

    /**
     * part setter.
     * 
     * @param part
     *            the part to set.
     */
    public void setPart(IDiagramWorkbenchPart part) {
        this.part = part;
    }

}
