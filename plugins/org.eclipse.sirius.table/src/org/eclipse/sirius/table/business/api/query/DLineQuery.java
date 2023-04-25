/*******************************************************************************
 * Copyright (c) 2011, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.business.api.query;

import java.util.Objects;

import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * A class aggregating all the queries (read-only!) having a {@link DLine} as a
 * starting point.
 * 
 * @author mporhel
 * 
 */
@Deprecated
public class DLineQuery {

    private final DLine line;

    /**
     * Creates a new query.
     * 
     * @param line
     *            the line to query.
     */
    public DLineQuery(DLine line) {
        this.line = Objects.requireNonNull(line);
    }

    /**
     * Check if the current line can be deleted: it must have a mapping, and can
     * have a delete tool. If there is a delete tool, it must define an initial
     * operation and its precondition evaluation must return true.
     * 
     * @return true if the current line can be deleted
     */
    public boolean canBeDeleted() {
        boolean result = true;
        LineMapping originMapping = line.getOriginMapping();

        if (originMapping == null) {
            result = false;
        } else {
            DeleteLineTool delete = originMapping.getDelete();
            if (delete != null) {
                if (delete.getFirstModelOperation() == null) {
                    result = false;
                } else {
                    if (!StringUtil.isEmpty(delete.getPrecondition())) {
                        final IInterpreter interpreter = InterpreterUtil.getInterpreter(line.getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.ROOT, TableHelper.getTable(line).getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, line.getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, ((LineContainer) line.eContainer()).getTarget());
                        try {
                            result = interpreter.evaluateBoolean(line.getTarget(), delete.getPrecondition());
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
