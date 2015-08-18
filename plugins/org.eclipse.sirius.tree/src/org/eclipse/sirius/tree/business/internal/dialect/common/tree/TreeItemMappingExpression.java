/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.dialect.common.tree;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Expression checker.
 * 
 * @author cbrun
 */
public class TreeItemMappingExpression {

    private static final String TREE = "tree"; //$NON-NLS-1$

    private GlobalContext ctx;

    private TreeItemMapping mapping;

    /**
     * Creates a new TreeItemMappingExpression.
     * 
     * @param ctx
     *            The Global context
     * @param mapping
     *            the TreeItemMapping
     */
    public TreeItemMappingExpression(GlobalContext ctx, TreeItemMapping mapping) {
        this.ctx = ctx;
        this.mapping = mapping;
    }

    /**
     * Indicates if the the given semantic element checks the precondition
     * associated to the given DTreeItemContainer.
     * 
     * @param semantic
     *            the semantic element to test
     * @param containerView
     *            the DTreeItemContainer
     * @return true if the the given semantic element checks the precondition
     *         associated to the given DTreeItemContainer, false otherwise
     */
    public boolean checkPrecondition(EObject semantic, DTreeItemContainer containerView) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        IInterpreter interpreter = ctx.getInterpreter();
        boolean result = true;
        if (!StringUtil.isEmpty(mapping.getPreconditionExpression())) {
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, containerView);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, containerView.getTarget());
            interpreter.setVariable(TREE, TreeHelper.getTree(containerView));

            try {
                result = interpreter.evaluateBoolean(semantic, mapping.getPreconditionExpression());
            } catch (final EvaluationException e) {
                SiriusPlugin.getDefault().warning("the following Tree Item mapping precondition could not be correctly evaluated : " + mapping.getPreconditionExpression(), e);
            }
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            interpreter.unSetVariable(TREE);
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        return result;
    }

}
