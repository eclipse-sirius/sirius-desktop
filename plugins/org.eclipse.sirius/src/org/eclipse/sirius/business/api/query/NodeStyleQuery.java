/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.diagram.GaugeCompositeStyle;
import org.eclipse.sirius.diagram.Lozenge;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.util.DiagramSwitch;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * A class aggregating all the queries (read-only!) having a {@link NodeStyle}
 * as a starting point.
 * 
 * @author lredor
 * 
 */
public class NodeStyleQuery {

    private NodeStyle nodeStyle;

    /**
     * Switch to get the background color for a given node style.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private static final class GetBackgroundColorForNodeStyleSwitch extends DiagramSwitch<RGBValues> {

        /**
         * Default constructor.
         */
        private GetBackgroundColorForNodeStyleSwitch() {
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.util.ViewpointSwitch#caseNodeStyle(org.eclipse.sirius.viewpoint.NodeStyle)
         */
        @Override
        public RGBValues caseNodeStyle(NodeStyle object) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.util.ViewpointSwitch#caseBundledImage(org.eclipse.sirius.viewpoint.BundledImage)
         */
        @Override
        public RGBValues caseBundledImage(BundledImage object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.util.ViewpointSwitch#caseCustomStyle(org.eclipse.sirius.viewpoint.CustomStyle)
         */
        @Override
        public RGBValues caseCustomStyle(CustomStyle object) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.util.ViewpointSwitch#caseDot(org.eclipse.sirius.viewpoint.Dot)
         */
        @Override
        public RGBValues caseDot(Dot object) {
            return object.getBackgroundColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.util.ViewpointSwitch#caseEllipse(org.eclipse.sirius.viewpoint.Ellipse)
         */
        @Override
        public RGBValues caseEllipse(Ellipse object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.util.ViewpointSwitch#caseGaugeCompositeStyle(org.eclipse.sirius.viewpoint.GaugeCompositeStyle)
         */
        @Override
        public RGBValues caseGaugeCompositeStyle(GaugeCompositeStyle object) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.util.ViewpointSwitch#caseLozenge(org.eclipse.sirius.viewpoint.Lozenge)
         */
        @Override
        public RGBValues caseLozenge(Lozenge object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.util.ViewpointSwitch#caseNote(org.eclipse.sirius.viewpoint.Note)
         */
        @Override
        public RGBValues caseNote(Note object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.util.ViewpointSwitch#caseSquare(org.eclipse.sirius.viewpoint.Square)
         */
        @Override
        public RGBValues caseSquare(Square object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.util.ViewpointSwitch#caseWorkspaceImage(org.eclipse.sirius.viewpoint.WorkspaceImage)
         */
        @Override
        public RGBValues caseWorkspaceImage(WorkspaceImage object) {
            return null;
        }
    }

    /**
     * Create a new query.
     * 
     * @param nodeStyle
     *            the element to query.
     */
    public NodeStyleQuery(NodeStyle nodeStyle) {
        this.nodeStyle = nodeStyle;
    }

    /**
     * Get the background color associated with this NodeStyle.
     * 
     * @return the background color associated with this NodeStyle
     */
    public Option<RGBValues> getBackgroundColor() {
        RGBValues color = new GetBackgroundColorForNodeStyleSwitch().doSwitch(nodeStyle);
        if (color == null) {
            return Options.newNone();
        } else {
            return Options.newSome(color);
        }
    }

    /**
     * Get the label color associated with this NodeStyle.
     * 
     * @return the label color associated with this NodeStyle
     */
    public Option<RGBValues> getLabelColor() {
        RGBValues color = nodeStyle.getLabelColor();
        if (color == null) {
            return Options.newNone();
        } else {
            return Options.newSome(color);
        }
    }
}
