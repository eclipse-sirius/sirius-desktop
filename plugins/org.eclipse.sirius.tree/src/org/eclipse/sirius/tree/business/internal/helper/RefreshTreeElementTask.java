/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tree.business.internal.helper;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.internal.refresh.DTreeElementSynchronizerSpec;
import org.eclipse.sirius.tree.tools.internal.Messages;

/**
 * Task for refresh tree item elements.
 * 
 * @author nlepine
 */
public class RefreshTreeElementTask extends AbstractCommandTask {

    private EObject uniqueRefreshable;

    private Collection<DTreeElement> refreshablesList;

    /** The monitor for the execution of this task */
    private final IProgressMonitor monitor;

    /**
     * Default constructor.
     * 
     * @param objectToRefresh
     *            the object to refresh
     */
    public RefreshTreeElementTask(final EObject objectToRefresh) {
        this(objectToRefresh, new NullProgressMonitor());
    }

    /**
     * Constructor with monitor.
     * 
     * @param objectToRefresh
     *            the object to refresh
     * @param monitor
     *            The monitor for the execution of this task
     */
    public RefreshTreeElementTask(final EObject objectToRefresh, final IProgressMonitor monitor) {
        this.uniqueRefreshable = objectToRefresh;
        this.monitor = monitor;
    }

    /**
     * Default constructor.
     * 
     * @param objectsToRefresh
     *            the object to refresh
     */
    public RefreshTreeElementTask(Collection<DTreeElement> objectsToRefresh) {
        this(objectsToRefresh, new NullProgressMonitor());
    }

    /**
     * Constructor with monitor.
     * 
     * @param objectsToRefresh
     *            the object to refresh
     * @param monitor
     *            The monitor for the execution of this task
     */
    public RefreshTreeElementTask(Collection<DTreeElement> objectsToRefresh, IProgressMonitor monitor) {
        this.refreshablesList = objectsToRefresh;
        this.monitor = monitor;
    }

    @Override
    public void execute() {
        if (uniqueRefreshable instanceof DTree) {
            DialectManager.INSTANCE.refresh((DTree) uniqueRefreshable, monitor);
        }
        if (uniqueRefreshable instanceof DTreeElement) {
            refreshTreeElement((DTreeItem) uniqueRefreshable);
        }
        if (refreshablesList != null) {
            final Iterator<DTreeElement> it = refreshablesList.iterator();
            while (it.hasNext()) {
                final Object obj = it.next();
                if (obj instanceof DTree) {
                    DialectManager.INSTANCE.refresh((DTree) obj, monitor);
                } else if (obj instanceof DTreeElement) {
                    refreshTreeElement((DTreeItem) obj);
                }
            }
        }
    }

    private void refreshTreeElement(DTreeItem treeElement) {
        DTree tree = TreeHelper.getTree(treeElement);
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(tree.getTarget());
        ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(tree);
        DTreeElementSynchronizerSpec synchronizer = new DTreeElementSynchronizerSpec(interpreter, accessor);
        synchronizer.refreshItemAndChildren(treeElement);
    }

    @Override
    public String getLabel() {
        return Messages.RefreshTreeElementTask_label;
    }
}
