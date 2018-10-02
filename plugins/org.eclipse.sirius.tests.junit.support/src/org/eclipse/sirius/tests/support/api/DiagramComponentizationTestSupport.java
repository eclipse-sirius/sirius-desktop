/**
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.tool.ToolGroup;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * 
 * This class is a workaround to avoid adapting many tests which don't have a
 * Session yet used to retrieve the list of enabled layers or tools. Previously
 * the session itself was passed and the DiagramComponentizationManager would
 * "fail gracefully" when trying to retrieve the enabled viewpoints. Now the
 * DiagramComponentizationManager api requires the list of selected viewpoints
 * and not the session itself and the tests would just fail with a NPE without
 * this extra indirection.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public final class DiagramComponentizationTestSupport {

    private DiagramComponentizationTestSupport() {

    }

    /**
     * Get all the layers of a diagram description.
     * 
     * @param session
     *            the session
     * @param diagram
     *            the diagram description
     * @return all the available layers
     */
    public static List<Layer> getAllLayers(Session session, final DiagramDescription diagram) {
        if (session != null) {
            return new DiagramComponentizationManager().getAllLayers(session.getSelectedViewpoints(false), diagram);
        } else {
            return new DiagramComponentizationManager().getAllLayers(null, diagram);
        }
    }

    /**
     * Get all the edge mappings available for a diagram description.
     * 
     * @param session
     *            the session
     * @param diagram
     *            the diagram description
     * @return all the available edge mappings
     */
    public static List<AbstractToolDescription> getAllTools(Session session, final DiagramDescription diagram) {
        if (session != null) {
            return new DiagramComponentizationManager().getAllTools(session.getSelectedViewpoints(false), diagram);
        } else {
            return new DiagramComponentizationManager().getAllTools(null, diagram);
        }
    }

    /**
     * Get the tool entries available for a section.
     * 
     * @param session
     *            the session.
     * @param section
     *            the section
     * @return all the available tools
     */
    public static List<ToolEntry> getToolEntries(Session session, ToolSection section) {
        if (session != null) {
            return new DiagramComponentizationManager().getToolEntries(session.getSelectedViewpoints(false), section);
        } else {
            return new DiagramComponentizationManager().getToolEntries(null, section);
        }
    }

    /**
     * Get all the container mappings available for a diagram description.
     * 
     * @param session
     *            the session
     * @param diagram
     *            the diagram description
     * @return all the available node mappings
     */
    public static List<ContainerMapping> getAllContainerMappings(Session session, DiagramDescription diagram) {
        if (session != null) {
            return new DiagramComponentizationManager().getAllContainerMappings(session.getSelectedViewpoints(false), diagram);
        } else {
            return new DiagramComponentizationManager().getAllContainerMappings(null, diagram);
        }
    }

    /**
     * Get all the edge mappings available for a diagram description.
     * 
     * @param session
     *            the session
     * @param description
     *            the diagram description
     * @return all the available edge mappings
     */
    public static List<EdgeMapping> getAllEdgeMappings(Session session, DiagramDescription description) {
        if (session != null) {
            return new DiagramComponentizationManager().getAllEdgeMappings(session.getSelectedViewpoints(false), description);
        } else {
            return new DiagramComponentizationManager().getAllEdgeMappings(null, description);
        }
    }

    /**
     * Get all the sections available for a diagram description.
     * 
     * @param session
     *            the session
     * @param diagram
     *            the diagram description
     * @return all the available sections
     */
    public static List<ToolSection> getRootPaletteSections(Session session, DiagramDescription diagram) {
        if (session != null) {
            return new DiagramComponentizationManager().getRootPaletteSections(session.getSelectedViewpoints(false), diagram);
        } else {
            return new DiagramComponentizationManager().getRootPaletteSections(null, diagram);
        }
    }

    /**
     * Get all the tool entries available for a section. The function will check
     * direct and indirect children.
     * 
     * @param session
     *            the viewpoints to consider.
     * @param section
     *            the section
     * @return all the available tools
     */
    public static List<ToolEntry> getAllToolEntries(Session session, ToolSection section) {
        if (session != null) {
            return new DiagramComponentizationManager().getAllToolEntries(session.getSelectedViewpoints(false), section);
        } else {
            return new DiagramComponentizationManager().getAllToolEntries(null, section);
        }
    }

    /**
     * Get the tools available for a tool group.
     * 
     * @param session
     *            the viewpoints to consider.
     * @param toolGroup
     *            the group of tools
     * @return the available tools
     */
    public static List<AbstractToolDescription> getTools(Session session, ToolGroup toolGroup) {
        if (session != null) {
            return new DiagramComponentizationManager().getTools(session.getSelectedViewpoints(false), toolGroup);
        } else {
            return new DiagramComponentizationManager().getTools(null, toolGroup);
        }
    }

}
