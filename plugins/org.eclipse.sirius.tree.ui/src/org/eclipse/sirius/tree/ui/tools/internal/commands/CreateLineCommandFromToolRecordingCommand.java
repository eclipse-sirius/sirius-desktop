/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;

/**
 * A Recording Command to call
 * tableCommandFactory.buildCreateLineCommandFromTool.
 * 
 * @author smonnier
 */
public class CreateLineCommandFromToolRecordingCommand extends RecordingCommand {

    /**
     * The line concerned with this action
     */
    private DTreeItem line;

    private DTree tree;

    private ITreeCommandFactory treeCommandFactory;

    private TreeItemCreationTool tool;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param label
     *            the name of the command.
     * @param line
     *            the line in which to create the new line.
     * @param tree
     *            the tree in which to create the line.
     * @param treeCommandFactory
     *            the factory to use to build commands.
     * @param tool
     *            the line creation tool.
     */
    public CreateLineCommandFromToolRecordingCommand(TransactionalEditingDomain domain, String label, DTreeItem line, DTree tree, ITreeCommandFactory treeCommandFactory, TreeItemCreationTool tool) {
        super(domain, label);
        this.line = line;
        this.tree = tree;
        this.treeCommandFactory = treeCommandFactory;
        this.tool = tool;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        // The CreateLineAction can be launch from the table
        EObject target;
        DTreeItemContainer lineContainer;
        if (line != null) {
            target = line.getTarget();
            lineContainer = (DTreeItemContainer) line.eContainer();
        } else {
            target = tree.getTarget();
            lineContainer = tree;
        }
        treeCommandFactory.buildCreateLineCommandFromTool(lineContainer, target, tool).execute();
    }

}
