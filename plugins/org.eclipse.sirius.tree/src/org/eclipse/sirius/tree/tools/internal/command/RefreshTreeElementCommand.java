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
package org.eclipse.sirius.tree.tools.internal.command;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.business.internal.refresh.DTreeElementSynchronizerSpec;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * Create tree item refresh command.
 * 
 * @author nlepine
 */
public class RefreshTreeElementCommand extends RecordingCommand {

    private DTreeItem treeElement;

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain.
     * @param treeElement
     *            the treeElement to refresh.
     * @param monitor
     *            a progress monitor.
     */
    public RefreshTreeElementCommand(TransactionalEditingDomain domain, DTreeItem treeElement, IProgressMonitor monitor) {
        super(domain, "Refresh representation");
        this.treeElement = treeElement;
    }

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain.
     * @param treeElement
     *            the treeElement to refresh.
     */
    public RefreshTreeElementCommand(TransactionalEditingDomain domain, DTreeItem treeElement) {
        this(domain, treeElement, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        DTree tree = TreeHelper.getTree(treeElement);
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(tree.getTarget());
        ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(tree);
        DTreeElementSynchronizerSpec synchronizer = new DTreeElementSynchronizerSpec(interpreter, accessor);
        synchronizer.refreshItemAndChildren(treeElement);
    }
}
