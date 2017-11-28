/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DTableLineLabelProvider;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;
import org.eclipse.ui.dialogs.SelectionDialog;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * Hide/reveal lines action. It opens a selection dialog.
 *
 * @author dlecan
 */
public class HideRevealLinesAction extends AbstractHideRevealAction<DLine> {

    private final TreeViewer treeViewer;

    /**
     * @author dlecan
     */
    private static final class DLineContentProvider implements ITreeContentProvider {

        /**
         * {inheritDoc}
         */
        @Override
        @SuppressWarnings("unchecked")
        public Object[] getElements(final Object inputElement) {
            if (inputElement instanceof EList<?>) {
                final List<DLine> visibleLines = (EList<DLine>) inputElement;
                return visibleLines.toArray(new DLine[visibleLines.size()]);
            }
            return null;
        }

        /**
         * {inheritDoc}
         */
        @Override
        public void dispose() {
            // Nothing
        }

        /**
         * {inheritDoc}
         */
        @Override
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // Nothing
        }

        /**
         * {@inheritDoc}
         *
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        @Override
        public Object[] getChildren(final Object parentElement) {
            Object[] result = null;
            if (parentElement instanceof DLine) {
                final List<DLine> visibleLines = ((DLine) parentElement).getLines();
                result = visibleLines.toArray();
            }
            return result;
        }

        /**
         * {@inheritDoc}
         *
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
         */
        @Override
        public Object getParent(final Object element) {
            if (element instanceof DLine) {
                final DLine line = (DLine) element;
                return line.eContainer();
            }
            return null;
        }

        /**
         * {@inheritDoc}
         *
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        @Override
        public boolean hasChildren(final Object element) {
            final Object[] children = getChildren(element);
            return children != null && children.length > 0;
        }
    }

    /**
     * Constructor.
     *
     * @param dTable
     *            {@link DTable} to use
     * @param editingDomain
     *            Editing domain.
     * @param tableCommandFactory
     *            table command factory.
     * @param treeViewer
     *            Tree viewer of the current table.
     */
    public HideRevealLinesAction(final DTable dTable, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory, final TreeViewer treeViewer) {
        super(dTable, Messages.HideRevealLinesAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.HIDE_REVEAL_IMG), editingDomain, tableCommandFactory);
        this.treeViewer = treeViewer;
    }

    private ITreeContentProvider getContentProvider() {
        return new DLineContentProvider();
    }

    private ILabelProvider getLabelProvider() {
        ILabelDecorator decorator = PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator();
        return new DTableLineLabelProvider(decorator);
    }

    @Override
    protected SelectionDialog createSelectionDialog() {
        final CheckedTreeSelectionDialog dlg = new CheckedTreeSelectionDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), getLabelProvider(), getContentProvider());
        dlg.setInput(getTable().getLines());

        // The new tree viewer respects the same expanded state as table tree
        // viewer
        dlg.setExpandedElements(treeViewer.getExpandedElements());
        return dlg;
    }

    @Override
    protected List<DLine> getAllElements() {
        final List<DLine> result = getAllLines(getTable().getLines());

        return result;
    }

    private List<DLine> getAllLines(final List<DLine> lines) {
        final List<DLine> result = new ArrayList<>();
        for (final DLine dLine : lines) {
            result.add(dLine);
            result.addAll(getAllLines(dLine.getLines()));
        }
        return result;
    }

    @Override
    protected Collection<DLine> getInitialVisibleElements() {
        return Collections2.filter(getAllElements(), new Predicate<DLine>() {

            @Override
            public boolean apply(final DLine input) {
                return input.isVisible();
            }
        });
    }

    @Override
    protected String getSetVisibleMethodName() {
        return TablePackage.eINSTANCE.getDLine_Visible().getName();
    }

    @Override
    protected String getMessage() {
        return Messages.HideRevealLinesAction_dialogMsg;
    }

    @Override
    protected String getTitle() {
        return Messages.HideRevealLinesAction_dialogTitle;
    }

    @Override
    protected boolean isVisibleElement(final DLine element) {
        return element.isVisible();
    }

}
