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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;

/**
 * Default class to extends for providing new contribution.
 * 
 * @author mchauvin
 */
public abstract class AbstractTabbarContribution implements TabbarContribution {

    /**
     * The current edited diagram.
     */
    protected DDiagram diagram;

    /**
     * The current workbench part.
     */
    protected IDiagramWorkbenchPart part;

    /**
     * The current session.
     */
    protected Session session;

    /**
     * Set the currently edited viewpoint diagram.
     * 
     * @param diagram
     *            the diagram currently edited
     */
    protected void setDiagram(DDiagram diagram) {
        this.diagram = diagram;
        if (diagram instanceof DSemanticDiagram) {
            session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) diagram).getTarget());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.TabbarContribution#setPart(org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart)
     */
    public void setPart(IDiagramWorkbenchPart workbenchPart) {
        this.part = workbenchPart;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.TabbarContribution#dispose()
     */
    public void dispose() {
        this.part = null;
        this.diagram = null;
        this.session = null;
    }

}
