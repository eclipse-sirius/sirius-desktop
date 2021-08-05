/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.ui.tools.internal.editor.actions;

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeItemTool;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewerManager;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.Iterators;

/**
 * Action to launch the createTool of the treeItemContainer (tree or tree item).
 * 
 * @author nlepine
 */
public class CreateToolItemAction extends AbstractToolItemAction {

    private DTree tree;

    /**
     * Constructor.
     * 
     * @param createTool
     *            The tool to do some other actions
     * @param editingDomain
     *            The transactional editing domain
     * @param tableCommandFactory
     *            The EMF command factory
     */
    public CreateToolItemAction(final TreeItemCreationTool createTool, final TransactionalEditingDomain editingDomain, final ITreeCommandFactory tableCommandFactory) {
        super(MessageTranslator.INSTANCE.getMessage(createTool, new IdentifiedElementQuery(createTool).getLabel()), CreateToolItemAction.findImageDescriptor(createTool), editingDomain,
                tableCommandFactory, createTool);
    }

    private static ImageDescriptor findImageDescriptor(TreeItemCreationTool createTool) {

        ImageDescriptor descriptor = DTreeViewerManager.getImageRegistry().getDescriptor(DTreeViewerManager.CREATE_TREE_ITEM_IMG);
        EObject created = null;

        Iterator<CreateInstance> createInstances = Iterators.filter(createTool.eAllContents(), CreateInstance.class);
        while (created == null && createInstances.hasNext()) {
            CreateInstance map = createInstances.next();
            created = CreateToolItemAction.tryToInstanciateType(createTool, created, map.getTypeName());
        }

        Iterator<TreeItemMapping> it = createTool.getMapping().iterator();
        while (created == null && it.hasNext()) {
            TreeItemMapping map = it.next();
            created = CreateToolItemAction.tryToInstanciateType(createTool, created, map.getDomainClass());
        }

        if (created != null) {
            final IItemLabelProvider labelProvider = (IItemLabelProvider) TreeUIPlugin.getPlugin().getItemProvidersAdapterFactory().adapt(created, IItemLabelProvider.class);
            if (labelProvider != null) {
                ImageDescriptor semanticDescriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(created));
                if (semanticDescriptor != null) {
                    descriptor = semanticDescriptor;
                }

            }
        }

        return descriptor;
    }

    private static EObject tryToInstanciateType(TreeItemCreationTool createTool, EObject created, String map) {
        EObject result = created;
        if (!StringUtil.isEmpty(map)) {
            try {
                final EObject anInstance = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(createTool).createInstance(map);
                result = anInstance;
            } catch (final MetaClassNotFoundException e) {
                /*
                 * silent catch, it's just a bit more magic, if we're able to retrieve the right type then we'll do.
                 */
            }

        }
        return result;
    }

    @Override
    public void run() {
        super.run();
        EObject target;
        DTreeItemContainer lineContainer;
        DTreeItem dTreeItem = getTreeItem();
        if (dTreeItem != null) {
            target = dTreeItem.getTarget();
            lineContainer = (DTreeItemContainer) dTreeItem.eContainer();
        } else {
            target = tree.getTarget();
            lineContainer = tree;
        }

        Command cmd = treeCommandFactory.buildCreateLineCommandFromTool(lineContainer, target, getCreationTool());
        String label = getText();
        cmd = new CommandWrapper(label, label, cmd);
        getEditingDomain().getCommandStack().execute(cmd);
    }

    private TreeItemCreationTool getCreationTool() {
        TreeItemCreationTool tool = null;
        final TreeItemTool tableTool = getTool();
        if (tableTool instanceof TreeItemCreationTool) {
            tool = (TreeItemCreationTool) tableTool;
        }
        return tool;
    }

    @Override
    public boolean canExecute() {
        boolean canExecute = true;
        if (getCreationTool() != null) {
            if (getCreationTool().getFirstModelOperation() == null) {
                canExecute = false;
            } else {
                if (getCreationTool().getPrecondition() != null && !StringUtil.isEmpty(getCreationTool().getPrecondition().trim())) {
                    IInterpreter interpreter = null;
                    try {
                        if (getTreeItem() != null) {
                            interpreter = InterpreterUtil.getInterpreter(getTreeItem().getTarget());
                            interpreter.setVariable(IInterpreterSiriusVariables.ROOT, TreeHelper.getTree(getTreeItem()).getTarget());
                            interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, getTreeItem().getTarget());
                            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, ((DTreeItemContainer) getTreeItem().eContainer()).getTarget());
                            canExecute = interpreter.evaluateBoolean(getTreeItem().getTarget(), getCreationTool().getPrecondition());
                        } else {
                            interpreter = InterpreterUtil.getInterpreter(getTable().getTarget());
                            interpreter.setVariable(IInterpreterSiriusVariables.ROOT, getTable().getTarget());
                            interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, getTable().getTarget());
                            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, null);
                            canExecute = interpreter.evaluateBoolean(getTable().getTarget(), getCreationTool().getPrecondition());
                        }
                    } catch (final EvaluationException e) {
                        RuntimeLoggerManager.INSTANCE.error(getCreationTool(), ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
                    }
                    interpreter.unSetVariable(IInterpreterSiriusVariables.ROOT);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
                }
            }
        }
        return canExecute;
    }

    /**
     * Return the table.
     * 
     * @return the table
     */
    public DTree getTable() {
        return tree;
    }

    /**
     * Set the table on which the tool of this action applied.
     * 
     * @param table
     *            the table to set
     */
    public void setTable(final DTree table) {
        this.tree = table;
    }
}
