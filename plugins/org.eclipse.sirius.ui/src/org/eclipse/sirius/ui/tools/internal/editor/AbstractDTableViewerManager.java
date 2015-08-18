/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.util.Policy;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;

/**
 * This class manages the tree viewer for display the DTable or DTree
 * representation.
 * 
 * @author nlepine
 * 
 */
public abstract class AbstractDTableViewerManager {

    /**
     * Constant for the layout Data.
     * 
     * @See org.eclipse.jface.layout.AbstractColumnLayout.LAYOUT_DATA
     */
    public static final String LAYOUT_DATA = Policy.JFACE + ".LAYOUT_DATA"; //$NON-NLS-1$

    /**
     * Boolean for OS.
     */
    public static final boolean IS_GTK_OS = "gtk".equals(SWT.getPlatform()); //$NON-NLS-1$

    /**
     * Use to store the semantic column in SWT column.
     */
    public static final String TABLE_COLUMN_DATA = "org.eclipse.sirius.table.ui.dTableColumn"; //$NON-NLS-1$

    /**
     * The imageRegistry for the action images.
     */
    protected static ImageRegistry imageRegistry = new ImageRegistry();

    /**
     * the treeViewer associate with the DTableViewer.
     */
    protected AbstractDTreeViewer treeViewer;

    /**
     * the model editing by this Viewer.
     */
    protected final DRepresentation dRepresentation;

    /**
     * The model accessor.
     */
    protected final ModelAccessor accessor;

    /**
     * The editing domain.
     */
    protected final TransactionalEditingDomain editingDomain;

    /**
     * The command factory.
     */
    protected final ICommandFactory tableCommandFactory;

    /**
     * The associated editor.
     */
    protected final AbstractDTreeEditor treeEditor;

    /**
     * Menu manager.
     */
    protected final MenuManager mgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$

    /**
     * Indicates whether the odesign file has changed since the last load of the
     * cached menus.
     */
    protected boolean descriptionFileChanged = true;

    /**
     * Listener for the sort by column.
     */
    protected Listener sortListener;

    /**
     * The constructor.
     * 
     * @param parent
     *            The parent composite
     * @param input
     *            The input DTable
     * @param editingDomain
     *            The transactional editing domain of this viewer
     * @param accessor
     *            The accessor for the model
     * @param tableCommandFactory
     *            The EMF command factory
     * @param tableEditor
     *            The associated editor
     */
    public AbstractDTableViewerManager(final Composite parent, final DRepresentation input, final TransactionalEditingDomain editingDomain, final ModelAccessor accessor,
            final ICommandFactory tableCommandFactory, final AbstractDTreeEditor tableEditor) {
        this.dRepresentation = input;
        this.editingDomain = editingDomain;
        this.accessor = accessor;
        this.tableCommandFactory = tableCommandFactory;
        this.treeEditor = tableEditor;
    }

    public static ImageRegistry getImageRegistry() {
        return imageRegistry;
    }

    /**
     * Release resources.
     */
    public void dispose() {
        // Tell the label provider to release its resources
        treeViewer.getLabelProvider().dispose();
        // Dispose the menus
        mgr.dispose();
    }

    /**
     * Create the TreeViewer.
     * 
     * @param composite
     *            parent composite for the tree viewer
     */
    protected abstract void createTreeViewer(final Composite composite);

    /**
     * Initialize a cache and add, if needed, the contextual menu for the table. <BR>
     * Cached the actions of creation and deletion in order to increase
     * performance and not calculate it on each contextual menu.<BR>
     * Problem for action on column header :
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=23103 <BR>
     */
    public abstract void fillMenu();

    /**
     * Get selection.
     * 
     * @return currently selected item
     */
    public ISelection getSelection() {
        return treeViewer.getSelection();
    }

    /**
     * Return the parent composite.
     * 
     * @return parent composite
     */
    public Control getControl() {
        return treeViewer.getTree().getParent();
    }

    /**
     * Return the accessor.
     * 
     * @return the accessor.
     */
    public ModelAccessor getAccessor() {
        return accessor;
    }

    /**
     * {@inheritDoc}
     * 
     */
    public AbstractDTreeViewer getTreeViewer() {
        return treeViewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.ui.tools.internal.editor.IDTableViewer#setSelection(org.eclipse.jface.viewers.ISelection,
     *      boolean)
     */
    public void setSelection(final ISelection selection, final boolean reveal) {
        treeViewer.setSelection(selection, reveal);
    }

    /**
     * Return the transactional editing domain.
     * 
     * @return the transactional editing domain
     */
    public TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * Return the corresponding editor.
     * 
     * @return a table editor
     */
    public AbstractDTreeEditor getEditor() {
        return treeEditor;
    }

    /**
     * Changed descriptionFileChanged state.
     * 
     * @param modified
     *            Indicates whether the odesign file has changed since the last
     *            load menus
     */
    public void setDescriptionFileChanged(final boolean modified) {
        descriptionFileChanged = modified;
    }

}
