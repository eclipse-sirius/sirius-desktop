/******************************************************************************
 * Copyright (c) 2007, 2025 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.ruler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.ISurfaceEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.CompoundSnapToHelperEx;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.SnapToGuidesEx;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.SnapToHelperUtil;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;

/**
 * Utility class for the snapping behavior. This class adds the capability to
 * snap to all visible figures.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusSnapToHelperUtil extends SnapToHelperUtil {
    // CHECKSTYLE:OFF Method duplicate from SnapToHelperUtil and not formatted
    // to facilitate comparison.
    /**
     * returns the the appropriate snap helper(s), this method will always reach
     * for the first reachable DiagramEditPart using the passed edit part, then
     * use this Diagram edit part to get the snap helper
     * 
     * @param editPart
     *            , edit part to get the snap helper for
     * @return
     */
    static public Object getSnapHelper(GraphicalEditPart editPart) {
        // get the diagram Edit Part
        GraphicalEditPart diagramEditPart = editPart;
        while (diagramEditPart != null && !(diagramEditPart instanceof DiagramEditPart)) {
            diagramEditPart = (GraphicalEditPart) diagramEditPart.getParent();
        }

        if (diagramEditPart == null) {
            return null;
        }

        // for snap to geometry, attempt to locate a compartment as a parent
        GraphicalEditPart parent = editPart;
        // If the editPart is an AbstractDiagramNodeEditPart, this means that
        // element to snap is a border node of a DNode, this is an exception
        // where we do not attempt to locate a compartment as a parent.
        if (!(parent instanceof AbstractDiagramNodeEditPart)) {
            while (parent != null && !(parent instanceof ISurfaceEditPart)) {
                parent = (GraphicalEditPart) parent.getParent();
            }
        }

        if (parent == null) {
            parent = diagramEditPart;
        }

        List<SnapToHelper> snapStrategies = new ArrayList<SnapToHelper>();
        EditPartViewer viewer = diagramEditPart.getViewer();

        Boolean val = (Boolean) viewer.getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);

        if (val != null && val.booleanValue()) {
            snapStrategies.add(new SnapToGuidesEx(diagramEditPart));
        }

        val = (Boolean) viewer.getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED);
        if (val != null && val.booleanValue()) {
            snapStrategies.add(new SiriusSnapToGeometry(parent));
        }

        val = (Boolean) viewer.getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);

        if (val != null && val.booleanValue()) {
            snapStrategies.add(new SiriusSnapToGrid(diagramEditPart));
        }

        if (snapStrategies.size() == 0) {
            return null;
        }

        if (snapStrategies.size() == 1) {
            return snapStrategies.get(0);
        }

        SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
        for (int i = 0; i < snapStrategies.size(); i++) {
            ss[i] = snapStrategies.get(i);
        }
        return new CompoundSnapToHelperEx(ss);
    }
    // CHECKSTYLE:ON
}
