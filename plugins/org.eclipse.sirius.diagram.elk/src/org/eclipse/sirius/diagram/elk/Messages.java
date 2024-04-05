/*******************************************************************************
 * Copyright (c) 2019, 2024 Obeo.
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
package org.eclipse.sirius.diagram.elk;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtain translated strings.
 *
 * @author lredor
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, DiagramElkPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String ELK090MigrationParticipant_layered_rename_consider_model_order;

    @TranslatableMessage
    public static String ELK090MigrationParticipant_layered_rename_label_port;

    @TranslatableMessage
    public static String ELK090MigrationParticipant_layered_title;

    @TranslatableMessage
    public static String ELK090MigrationParticipant_rectPacking_remove_expandToAspectRatio;

    @TranslatableMessage
    public static String ELK090MigrationParticipant_rectPacking_remove_expandNodes;

    @TranslatableMessage
    public static String ELK090MigrationParticipant_rectPacking_remove_onlyFirstIteration;

    @TranslatableMessage
    public static String ELK090MigrationParticipant_rectPacking_remove_rowCompaction;

    @TranslatableMessage
    public static String ELK090MigrationParticipant_rectPacking_rename_last_place_shift;

    @TranslatableMessage
    public static String ELK090MigrationParticipant_rectPacking_rename_optimization_goal;

    @TranslatableMessage
    public static String ELK090MigrationParticipant_rectPacking_rename_target_width;

    @TranslatableMessage
    public static String ELK090MigrationParticipant_rectPacking_title;

    @TranslatableMessage
    public static String ELK090MigrationParticipant_title;

    @TranslatableMessage
    public static String ElkDiagramLayoutTracer_defaultJavaFolderWillBeUsedMsg;

    @TranslatableMessage
    public static String ElkDiagramLayoutTracer_saveNotPossible;

    // CHECKSTYLE:ON
    private Messages() {
        // Prevents instanciation.
    }
}
