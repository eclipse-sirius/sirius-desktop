/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.sequence;

/**
 * Constants useful for tests using the "Interactions" sequence diagrams.
 * 
 * @author pcdavid
 */
public final class InteractionsConstants {

    /**
     * Sirius name for sequence diagrams.
     */
    public static final String VIEWPOINT_NAME = "Interactions";

    /**
     * Representation label for simple sequence representation from the
     * interactions.odesign.
     */
    public static final String SEQUENCE_DIAGRAM_REPRESENTATION_LABEL = "Sequence Diagram on Interaction";

    /**
     * Representation id for simple sequence representation from the
     * interactions.odesign.
     */
    public static final String SEQUENCE_DIAGRAM_REPRESENTATION_ID = "Sequence Diagram on Interaction";

    /**
     * Representation id for sequence with combined fragments from the
     * interactions.odesign.
     */
    public static final String SEQUENCE_DIAGRAM_WITH_COMBINED_FRAGMENTS_REPRESENTATION_ID = "Sequence Diagram with Combined Fragments";

    /**
     * Path to the odesign in runtime for sequence diagrams.
     */
    public static final String VSM_PATH = "/org.eclipse.sirius.sample.interactions.design/description/interaction.odesign";

    // CHECKSTYLE:OFF

    // Creation tools
    public static final String PARTICIPANT_TOOL_ID = "Participant";

    public static final String READ_TOOL_ID = "Read";

    public static final String WRITE_TOOL_ID = "Write";

    public static final String FOUND_READ_TOOL_ID = "Found Read";

    public static final String CALL_TOOL_ID = "Call";

    public static final String RETURN_TOOL_ID = "Return";

    public static final String CREATE_TOOL_ID = "Create";

    public static final String DESTROY_TOOL_ID = "Destroy";

    public static final String EXECUTION_TOOL_ID = "Execution";

    public static final String PUNCTUAL_STATE_TOOL_ID = "Punctual State";

    public static final String STATE_TOOL_ID = "State";

    public static final String SYNC_CALL_TOOL_ID = "Sync Call";

    public static final String ASYNC_CALL_TOOL_ID = "Async Call";

    public static final String ASYNC_CALL_OBLIQUE_TOOL_ID = "Async Call_Oblique";

    public static final String WRITE_OBLIQUE_TOOL_ID = "Write_Oblique";

    public static final String INTERACTION_USE_TOOL_ID = "Interaction Use";

    public static final String COMBINET_FRAGMENT_TOOL_ID = "Combined Fragment";

    public static final String COMBINET_FRAGMENT_OPERAND_TOOL_ID = "Operand";

    // edit tools
    public static final String EDIT_PARTICIPANT_NAME_TOOL_ID = "Edit Participant";

    // delete tools
    public static final String DELETE_END_OF_LIFE_TOOL_ID = "Delete EndOfLife";

    public static final String DELETE_INTERACTION_USE_TOOL_ID = "Delete Interaction Use";

    public static final String DELETE_COMBINED_FRAGMENT_TOOL_ID = "Delete Combined Fragment";

    // reorder tool
    public static final String REORDER_TOOL_ID = "Reorder Executions, Messages";

    // CHECKSTYLE:ON

    private InteractionsConstants() {
    }
}
