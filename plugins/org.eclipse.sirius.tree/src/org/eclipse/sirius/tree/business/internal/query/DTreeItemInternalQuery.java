/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.business.internal.query;

import java.util.Objects;

import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.description.TreeItemDeletionTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * A class aggregating all the queries (read-only!) having a {@link DTreeItem} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class DTreeItemInternalQuery {

    private final DTreeItem item;

    /**
     * Creates a new query.
     * 
     * @param item
     *            the tree item to query.
     */
    public DTreeItemInternalQuery(DTreeItem item) {
        this.item =  Objects.requireNonNull(item);
    }

    /**
     * Check if the current item can be deleted: it must have a mapping, and can have a delete tool. If there is a
     * delete tool, it must define an initial operation and its precondition evaluation must return true.
     * 
     * @return true if the current line can be deleted
     */
    public boolean canBeDeleted() {
        boolean result = true;
        TreeItemMapping originMapping = item.getActualMapping();

        if (originMapping == null) {
            result = false;
        } else {
            TreeItemDeletionTool delete = originMapping.getDelete();
            if (delete != null) {

                if (delete.getFirstModelOperation() == null) {
                    result = false;
                } else {
                    if (delete.getPrecondition() != null && !StringUtil.isEmpty(delete.getPrecondition().trim())) {
                        final IInterpreter interpreter = InterpreterUtil.getInterpreter(item.getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.ROOT, TreeHelper.getTree(item).getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, item.getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, ((DTreeItemContainer) item.eContainer()).getTarget());
                        try {
                            result = interpreter.evaluateBoolean(item.getTarget(), delete.getPrecondition());
                        } catch (final EvaluationException e) {
                            RuntimeLoggerManager.INSTANCE.error(delete, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
                        }
                        interpreter.unSetVariable(IInterpreterSiriusVariables.ROOT);
                        interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
                        interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
                    }
                }
            }
        }
        return result;
    }

}
