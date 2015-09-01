/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.tools.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, TreePlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String DTreeItemExpansionChangeCommand_collapseItem;

    @TranslatableMessage
    public static String DTreeItemExpansionChangeCommand_expandItem;

    @TranslatableMessage
    public static String DTreeItemLocalRefreshCommand_refreshLocally;

    @TranslatableMessage
    public static String DTreeItemUserInteraction_treeItemExpanding;

    @TranslatableMessage
    public static String DTreeRefresh_noMapping;

    @TranslatableMessage
    public static String DTreeRefresh_nonsense;

    @TranslatableMessage
    public static String DTreeRefresh_unknownDescriptor;

    @TranslatableMessage
    public static String DTreeRefresh_unknownRepresentationContainer;

    @TranslatableMessage
    public static String DTreeUserInteraction_treeRefresh;

    @TranslatableMessage
    public static String MappingBasedPartition_semanticCandidateEvaluationError;

    @TranslatableMessage
    public static String RefreshTreeElementTask_label;

    @TranslatableMessage
    public static String TreeCommandFactory_directEdit;

    @TranslatableMessage
    public static String TreeCommandFactory_dropItem;

    @TranslatableMessage
    public static String TreeCommandFactory_operationAction;

    @TranslatableMessage
    public static String TreeDialectServices_createTree;

    @TranslatableMessage
    public static String TreeDialectServices_initializeTree;

    @TranslatableMessage
    public static String TreeDialectServices_refreshImpactedElements;

    @TranslatableMessage
    public static String TreeDialectServices_treeRefresh;

    @TranslatableMessage
    public static String TreeItemMappingExpression_preconditionEvaluationError;

    @TranslatableMessage
    public static String TreeToolVariables_container;

    @TranslatableMessage
    public static String TreeToolVariables_element;

    @TranslatableMessage
    public static String TreeToolVariables_precedingSiblings;

    @TranslatableMessage
    public static String TreeToolVariables_root;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
