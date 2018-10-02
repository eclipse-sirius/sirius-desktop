/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.query;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * A class aggregating all the queries (read-only!) having a {@link ToolSection}
 * as a starting point.
 * 
 * @author mporhel
 * 
 */
public class ToolSectionQuery {

    private ToolSection toolSection;

    /**
     * Create a new query.
     * 
     * @param toolSection
     *            the element to query.
     */
    public ToolSectionQuery(ToolSection toolSection) {
        this.toolSection = toolSection;
    }

    /**
     * Return a collections with the toolSection and all his subSections.
     * 
     * @return List of sections
     */
    public EList<ToolSection> getAllSections() {
        final EList<ToolSection> result = new BasicEList<ToolSection>();
        if (toolSection != null) {
            result.add(toolSection);
            result.addAll(getAllSections(toolSection.getSubSections()));
        }
        return result;
    }

    /**
     * Return a collections with the toolSections and all their subSections.
     * 
     * @param toolSections
     *            The toolSections to use
     * @return List of sections
     */
    private EList<ToolSection> getAllSections(final EList<ToolSection> toolSections) {
        final EList<ToolSection> result = new BasicEList<ToolSection>();
        for (final ToolSection ts : toolSections) {
            result.addAll(new ToolSectionQuery(ts).getAllSections());
        }
        return result;
    }

    /**
     * Return a collections with the operationActions of the current section.
     * 
     * @param session
     *            The session
     * @return List of operationActions
     */
    public EList<OperationAction> getOperationActions(final Session session) {
        final EList<OperationAction> result = new BasicEList<OperationAction>();
        for (final ToolEntry tool : new DiagramComponentizationManager().getToolEntries(session.getSelectedViewpoints(false), toolSection)) {
            if (tool instanceof OperationAction) {
                result.add((OperationAction) tool);
            }
        }
        return result;
    }

    /**
     * Return a collections with the externalJavaAction of this section.
     * 
     * @param session
     *            The session
     * @return List of externalJavaAction
     */
    public EList<ExternalJavaAction> getExternalJavaActions(final Session session) {
        final EList<ExternalJavaAction> result = new BasicEList<ExternalJavaAction>();
        for (final ToolEntry tool : new DiagramComponentizationManager().getToolEntries(session.getSelectedViewpoints(false), toolSection)) {
            if (tool instanceof ExternalJavaAction) {
                result.add((ExternalJavaAction) tool);
            }
        }
        return result;
    }
}
