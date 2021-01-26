/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.tools.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, TablePlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF
    @TranslatableMessage
    public static String ContributionTrakingIdentifier_ElementWithoutMatchingData;

    @TranslatableMessage
    public static String DTableSynchronizerImpl_refreshColumnMapping;

    @TranslatableMessage
    public static String DTableSynchronizerImpl_refreshCrossTabel;

    @TranslatableMessage
    public static String DTableSynchronizerImpl_refreshEditionTabel;

    @TranslatableMessage
    public static String DTableSynchronizerImpl_refreshIntersectionMapping;

    @TranslatableMessage
    public static String DTableSynchronizerImpl_refreshLineMapping;

    @TranslatableMessage
    public static String TableCommandFactory_addValue;

    @TranslatableMessage
    public static String TableCommandFactory_clearValue;

    @TranslatableMessage
    public static String TableCommandFactory_setCellContent;

    @TranslatableMessage
    public static String TableCommandFactory_setValue;

    @TranslatableMessage
    public static String TableDialectServices_CreateTable;

    @TranslatableMessage
    public static String TableDialectServices_InitializeTable;

    @TranslatableMessage
    public static String TableDialectServices_RefreshImpactedElements;

    @TranslatableMessage
    public static String TableDialectServices_RefreshTable;

    @TranslatableMessage
    public static String TableExportHelper_ExceptionOnSave;

    @TranslatableMessage
    public static String TableToolVariables_CellEditorResult;

    @TranslatableMessage
    public static String TableToolVariables_CurrentSemanticElement;

    @TranslatableMessage
    public static String TableToolVariables_LineElement;

    @TranslatableMessage
    public static String TableToolVariables_SemanticColumnElement;

    @TranslatableMessage
    public static String TableToolVariables_SemanticElementOfContainerView;

    @TranslatableMessage
    public static String TableToolVariables_SemanticLineElement;

    @TranslatableMessage
    public static String TableToolVariables_SemanticRootElement;

    @TranslatableMessage
    public static String TableToolVariables_TableElement;

    @TranslatableMessage
    public static String Table_UnexpectedExceptionMessage;

    @TranslatableMessage
    public static String Table_WrongStyleAttribute;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
