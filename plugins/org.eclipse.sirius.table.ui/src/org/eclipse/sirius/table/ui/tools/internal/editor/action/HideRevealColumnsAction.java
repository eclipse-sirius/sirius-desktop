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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.dialogs.SelectionDialog;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * Hide/reveal columns action.
 *
 * @author dlecan
 */
public class HideRevealColumnsAction extends AbstractHideRevealAction<DColumn> {

    /**
     * @author dlecan
     */
    private static final class DColumnLabelProvider extends LabelProvider {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getText(final Object element) {
            String result;
            if (element instanceof DColumn) {
                result = ((DColumn) element).getLabel();
            } else {
                result = super.getText(element);
            }
            return result;
        }
    }

    /**
     * @author dlecan
     */
    private static final class EListDColumnContentProvider implements IStructuredContentProvider {
        /**
         * {inheritDoc}
         */
        @Override
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // Nothing
        }

        /**
         * {inheritDoc}
         */
        @Override
        public void dispose() {
            // nothing
        }

        /**
         * {inheritDoc}
         */
        @Override
        @SuppressWarnings("unchecked")
        public Object[] getElements(final Object inputElement) {
            Object[] result = null;

            if (inputElement instanceof EList<?>) {
                final EList<DColumn> elements = (EList<DColumn>) inputElement;
                result = elements.toArray(new DColumn[elements.size()]);
            }

            return result;
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
     */
    public HideRevealColumnsAction(final DTable dTable, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory) {
        super(dTable, Messages.HideRevealColumnsAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.HIDE_REVEAL_IMG), editingDomain, tableCommandFactory);
    }

    private ILabelProvider getLabelProvider() {
        return new DColumnLabelProvider();
    }

    private IStructuredContentProvider getContentProvider() {
        return new EListDColumnContentProvider();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<DColumn> getInitialVisibleElements() {
        return Collections2.filter(getAllElements(), new Predicate<DColumn>() {

            @Override
            public boolean apply(final DColumn input) {
                return input.isVisible();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<DColumn> getAllElements() {
        return getTable().getColumns();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected SelectionDialog createSelectionDialog() {
        return new ListSelectionDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), getAllElements(), getContentProvider(), getLabelProvider(), getTitle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getSetVisibleMethodName() {
        return TablePackage.eINSTANCE.getDColumn_Visible().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getMessage() {
        return Messages.HideRevealColumnsAction_dialogMsg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTitle() {
        return Messages.HideRevealColumnsAction_dialogTitle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isVisibleElement(final DColumn element) {
        return element.isVisible();
    }
}
