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
     * The ID of this TabbarContribution.
     */
    protected String id;

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
     * Default constructor with an empty ID.
     */
    protected AbstractTabbarContribution() {
        id = ""; //$NON-NLS-1$
    }

    /**
     * Default constructor with an ID.
     * 
     * @param id
     *            The id of the TabbarContribution
     */
    protected AbstractTabbarContribution(String id) {
        setId(id);
    }

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

    @Override
    public void setPart(IDiagramWorkbenchPart workbenchPart) {
        this.part = workbenchPart;
    }

    @Override
    public void dispose() {
        this.part = null;
        this.diagram = null;
        this.session = null;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Set the id of this TabbarContribution.
     * 
     * @param id
     *            the id to set
     */
    protected void setId(String id) {
        this.id = id;
    }
}
