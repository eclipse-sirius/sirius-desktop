/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
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
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseNodeStyle(org.eclipse.sirius.diagram.NodeStyle)
         */
        @Override
        public RGBValues caseNodeStyle(NodeStyle object) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseBundledImage(org.eclipse.sirius.diagram.BundledImage)
         */
        @Override
        public RGBValues caseBundledImage(BundledImage object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseCustomStyle(org.eclipse.sirius.diagram.CustomStyle)
         */
        @Override
        public RGBValues caseCustomStyle(CustomStyle object) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDot(org.eclipse.sirius.diagram.Dot)
         */
        @Override
        public RGBValues caseDot(Dot object) {
            return object.getBackgroundColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseEllipse(org.eclipse.sirius.diagram.Ellipse)
         */
        @Override
        public RGBValues caseEllipse(Ellipse object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseGaugeCompositeStyle(org.eclipse.sirius.diagram.GaugeCompositeStyle)
         */
        @Override
        public RGBValues caseGaugeCompositeStyle(GaugeCompositeStyle object) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseLozenge(org.eclipse.sirius.diagram.Lozenge)
         */
        @Override
        public RGBValues caseLozenge(Lozenge object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseNote(org.eclipse.sirius.diagram.Note)
         */
        @Override
        public RGBValues caseNote(Note object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseSquare(org.eclipse.sirius.diagram.Square)
         */
        @Override
        public RGBValues caseSquare(Square object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseWorkspaceImage(org.eclipse.sirius.diagram.WorkspaceImage)
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
