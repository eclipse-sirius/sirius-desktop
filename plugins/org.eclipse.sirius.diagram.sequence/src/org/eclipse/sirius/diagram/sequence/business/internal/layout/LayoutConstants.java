/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.business.internal.layout;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A utility class to centralize all the dimensions ("magic number") used during
 * the layout of sequence diagrams. Unless otherwise stated, all the dimensions
 * and locations are in pixels.
 * 
 * @author pcdavid, smonnier, mporhel, edugueperoux
 */
public final class LayoutConstants {

    /**
     * This should be LayoutUtils.SCALE, but we do not want to depend here on
     * the plug-in where LayoutUtils is defined.
     */
    public static final int UNIT = 10;

    /**
     * The minimal amount of vertical space between the bottom of an instance
     * role and the first element (message or execution) on its root execution
     * for packing layout.
     */
    public static final int TIME_START_OFFSET = 3 * UNIT;

    /**
     * The minimal amount of vertical space between the bottom of an instance
     * role and the first element (message or execution) on its root execution
     * for non-packing layout.
     */
    public static final int TIME_START_MIN_OFFSET = 1 * UNIT;

    /**
     * The minimal amount of vertical space between the last element (message or
     * execution) on a root execution and the bottom of the root execution.
     */
    public static final int TIME_STOP_OFFSET = 5 * UNIT;

    /**
     * The x position of the first left lifeline.
     */
    public static final int LIFELINES_START_X = 5 * UNIT;

    /**
     * The lifeline top position.
     */
    public static final int LIFELINES_START_Y = 5 * UNIT;

    /**
     * The minimum horizontal gap to keep between two neighboring lifelines.
     */
    public static final int LIFELINES_MIN_X_GAP = 1 * UNIT;

    /**
     * The minimum horizontal gap to keep between two neighboring lifelines.
     */
    public static final int LIFELINES_MIN_PACKED_X_GAP = 3 * UNIT;

    /**
     * The minimum vertical range upper bound for lifelines.
     */
    public static final int LIFELINES_MIN_Y = 20 * UNIT;

    /**
     * The minimum vertical space to leave between two successive messages.
     */
    public static final int MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP = 2 * UNIT;

    /**
     * The minimum margin between the start/finish of an execution and its
     * first/last child.
     */
    public static final int EXECUTION_CHILDREN_MARGIN = 5;

    /**
     * The default width used for newly created interaction uses.
     */
    public static final int DEFAULT_INTERACTION_USE_WIDTH = 6 * UNIT;

    /**
     * The default width used for newly created combined fragments.
     */
    public static final int DEFAULT_COMBINED_FRAGMENT_WIDTH = 10 * UNIT;

    /**
     * The default width used for newly created operands.
     */
    public static final int DEFAULT_OPERAND_WIDTH = 10 * UNIT;

    /**
     * The default height used for newly created interaction uses.
     */
    public static final int DEFAULT_INTERACTION_USE_HEIGHT = 5 * UNIT;

    /**
     * How much space between the top of a combined fragment and the top of its
     * first operand.
     */
    public static final int COMBINED_FRAGMENT_TITLE_HEIGHT = 3 * UNIT;

    /**
     * The default height used for newly created operand.
     */
    public static final int DEFAULT_OPERAND_HEIGHT = 6 * UNIT;

    /**
     * The default height used for newly created combined fragments.
     */
    public static final int DEFAULT_COMBINED_FRAGMENT_HEIGHT = COMBINED_FRAGMENT_TITLE_HEIGHT + DEFAULT_OPERAND_HEIGHT;

    /**
     * The default height used for newly created executions.
     */
    public static final int DEFAULT_EXECUTION_HEIGHT = 3 * UNIT;

    /**
     * The default width used for newly created executions.
     */
    public static final int DEFAULT_EXECUTION_WIDTH = 2 * UNIT;

    /**
     * How much space there is between bendpoints of an oblique message.
     */
    public static final int DEFAULT_MESSAGE_OBLIQUE_HEIGHT_PACKING = 5;

    /**
     * The default minimal delta between source and target of an oblique message.
     */
    public static final int DEFAULT_MESSAGE_MIN_OBLIQUE_HEIGHT = 5;

    /**
     * How much space there is between bendpoints of a message to self.
     */
    public static final int MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP = UNIT;

    /**
     * How much space there is between bendpoints of a message to self.
     */
    public static final int MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP = 3 * UNIT;

    /**
     * How much space there is between bendpoints of a two included messages to
     * self.
     */
    public static final int MESSAGE_TO_SELF_HORIZONTAL_GAP = 2 * UNIT;

    /**
     * The default width used for lost messages.
     */
    public static final int LOST_MESSAGE_DEFAULT_WIDTH = 6 * UNIT;

    /** Defines the visual appearance of operands. */
    public static final int[] OPERAND_DASH_STYLE = new int[] { 5, 5 };

    /**
     * Default height of execution after a layout, also the default height of
     * syncCall/asyncCall execution after creation.
     */
    public static final int INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT = 50;

    /**
     * Default height of execution after a layout.
     */
    public static final int INTERACTION_STATE_MIN_HEIGHT_AFTER_LAYOUT = 30;

    /**
     * Margin between a parent frame and its children.
     */
    public static final int BORDER_FRAME_MARGIN = UNIT;

    /**
     * Tool creation flag for directly created DDiagramElement.
     */
    public static final Rectangle TOOL_CREATION_FLAG = new Rectangle(-1, -1, 0, 0);

    /**
     * Tool creation flag for created DDiagramElement from semantic.
     */
    public static final Rectangle TOOL_CREATION_FLAG_FROM_SEMANTIC = new Rectangle(-1, -2, 0, 0);

    /**
     * External Reparent / Reconnect detection flag.
     */
    public static final Rectangle EXTERNAL_CHANGE_FLAG = new Rectangle(-2, 0, 0, 0);

    private LayoutConstants() {
        // Prevents instantiation.
    }
}
