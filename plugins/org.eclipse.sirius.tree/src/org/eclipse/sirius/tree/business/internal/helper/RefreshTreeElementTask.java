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
package org.eclipse.sirius.tree.business.internal.helper;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.tools.internal.command.RefreshTreeElementCommand;

/**
 * Task for refresh tree item elements.
 * 
 * @author nlepine
 * 
 */
public class RefreshTreeElementTask extends AbstractCommandTask {

    private EObject uniqueRefreshable;

    private final TransactionalEditingDomain editingDomain;

    private Collection<DTreeElement> refreshablesList;

    /** The monitor for the execution of this task */
    private final IProgressMonitor monitor;

    /**
     * Default constructor.
     * 
     * @param objectToRefresh
     *            the object to refresh
     * @param editingDomain
     *            the transactional editing domain
     */
    public RefreshTreeElementTask(final EObject objectToRefresh, final TransactionalEditingDomain editingDomain) {
        this(objectToRefresh, editingDomain, new NullProgressMonitor());
    }

    /**
     * Constructor with monitor.
     * 
     * @param objectToRefresh
     *            the object to refresh
     * @param editingDomain
     *            the transactional editing domain
     * @param monitor
     *            The monitor for the execution of this task
     */
    public RefreshTreeElementTask(final EObject objectToRefresh, final TransactionalEditingDomain editingDomain, final IProgressMonitor monitor) {
        this.uniqueRefreshable = objectToRefresh;
        this.editingDomain = editingDomain;
        this.monitor = monitor;
    }

    /**
     * Default constructor.
     * 
     * @param objectsToRefresh
     *            the object to refresh
     * @param editingDomain
     *            the transactional editing domain
     */
    public RefreshTreeElementTask(Collection<DTreeElement> objectsToRefresh, TransactionalEditingDomain editingDomain) {
        this(objectsToRefresh, editingDomain, new NullProgressMonitor());
    }

    /**
     * Constructor with monitor.
     * 
     * @param objectsToRefresh
     *            the object to refresh
     * @param editingDomain
     *            the transactional editing domain
     * @param monitor
     *            The monitor for the execution of this task
     */
    public RefreshTreeElementTask(Collection<DTreeElement> objectsToRefresh, TransactionalEditingDomain editingDomain, IProgressMonitor monitor) {
        this.refreshablesList = objectsToRefresh;
        this.editingDomain = editingDomain;
        this.monitor = monitor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {
        if (uniqueRefreshable instanceof DTree) {
            editingDomain.getCommandStack().execute(new RefreshRepresentationsCommand(editingDomain, monitor, (DTree) uniqueRefreshable));
        }
        if (uniqueRefreshable instanceof DTreeElement) {
            editingDomain.getCommandStack().execute(new RefreshTreeElementCommand(editingDomain, (DTreeItem) uniqueRefreshable, monitor));
        }
        if (refreshablesList != null) {
            final Iterator<DTreeElement> it = refreshablesList.iterator();
            while (it.hasNext()) {
                final Object obj = it.next();
                if (obj instanceof DTree) {
                    editingDomain.getCommandStack().execute(new RefreshRepresentationsCommand(editingDomain, monitor, (DTree) obj));
                } else if (obj instanceof DTreeElement) {
                    editingDomain.getCommandStack().execute(new RefreshTreeElementCommand(editingDomain, (DTreeItem) obj, monitor));
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getLabel() {
        return "Refresh element task";
    }
}
