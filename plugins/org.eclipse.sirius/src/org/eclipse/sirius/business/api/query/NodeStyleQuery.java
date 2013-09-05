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
import org.eclipse.sirius.BundledImage;
import org.eclipse.sirius.CustomStyle;
import org.eclipse.sirius.Dot;
import org.eclipse.sirius.Ellipse;
import org.eclipse.sirius.GaugeCompositeStyle;
import org.eclipse.sirius.Lozenge;
import org.eclipse.sirius.NodeStyle;
import org.eclipse.sirius.Note;
import org.eclipse.sirius.RGBValues;
import org.eclipse.sirius.Square;
import org.eclipse.sirius.WorkspaceImage;
import org.eclipse.sirius.util.SiriusSwitch;

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
    private static final class GetBackgroundColorForNodeStyleSwitch extends SiriusSwitch<RGBValues> {

        /**
         * Default constructor.
         */
        private GetBackgroundColorForNodeStyleSwitch() {
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.util.SiriusSwitch#caseNodeStyle(org.eclipse.sirius.NodeStyle)
         */
        @Override
        public RGBValues caseNodeStyle(NodeStyle object) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.util.SiriusSwitch#caseBundledImage(org.eclipse.sirius.BundledImage)
         */
        @Override
        public RGBValues caseBundledImage(BundledImage object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.util.SiriusSwitch#caseCustomStyle(org.eclipse.sirius.CustomStyle)
         */
        @Override
        public RGBValues caseCustomStyle(CustomStyle object) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.util.SiriusSwitch#caseDot(org.eclipse.sirius.Dot)
         */
        @Override
        public RGBValues caseDot(Dot object) {
            return object.getBackgroundColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.util.SiriusSwitch#caseEllipse(org.eclipse.sirius.Ellipse)
         */
        @Override
        public RGBValues caseEllipse(Ellipse object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.util.SiriusSwitch#caseGaugeCompositeStyle(org.eclipse.sirius.GaugeCompositeStyle)
         */
        @Override
        public RGBValues caseGaugeCompositeStyle(GaugeCompositeStyle object) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.util.SiriusSwitch#caseLozenge(org.eclipse.sirius.Lozenge)
         */
        @Override
        public RGBValues caseLozenge(Lozenge object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.util.SiriusSwitch#caseNote(org.eclipse.sirius.Note)
         */
        @Override
        public RGBValues caseNote(Note object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.util.SiriusSwitch#caseSquare(org.eclipse.sirius.Square)
         */
        @Override
        public RGBValues caseSquare(Square object) {
            return object.getColor();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.util.SiriusSwitch#caseWorkspaceImage(org.eclipse.sirius.WorkspaceImage)
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
