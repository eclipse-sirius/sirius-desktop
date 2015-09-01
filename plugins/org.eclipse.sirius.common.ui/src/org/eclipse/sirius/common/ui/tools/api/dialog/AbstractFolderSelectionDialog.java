/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Obeo - comments and checkstyle and minor changes.
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.dialog;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.common.ui.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.NewFolderDialog;
import org.eclipse.ui.views.navigator.ResourceComparator;

/**
 * Dialog to select folders.
 * 
 * @author IBM Corporation
 */
public abstract class AbstractFolderSelectionDialog extends ElementTreeSelectionDialog implements ISelectionChangedListener {

    private Button fNewFolderButton;

    private IContainer fSelectedContainer;

    /**
     * Creates a new {@link AbstractFolderSelectionDialog}.
     * 
     * @param parent
     *            the shell.
     * @param labelProvider
     *            a label provider.
     * @param contentProvider
     *            a content provider.
     */
    public AbstractFolderSelectionDialog(final Shell parent, final ILabelProvider labelProvider, final ITreeContentProvider contentProvider) {
        super(parent, labelProvider, contentProvider);
        setComparator(new ResourceComparator(ResourceComparator.NAME));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.dialogs.ElementTreeSelectionDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite result = (Composite) super.createDialogArea(parent);

        getTreeViewer().addSelectionChangedListener(this);
        getTreeViewer().expandToLevel(2);
        fNewFolderButton = new Button(result, SWT.PUSH);
        fNewFolderButton.setText(Messages.AbstractFolderSelectionDialog_newFolder);
        fNewFolderButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                newFolderButtonPressed();
            }
        });
        fNewFolderButton.setFont(parent.getFont());
        fNewFolderButton.setLayoutData(new GridData());

        // Modified by Obeo (copy/paste
        // org.eclipse.pde.internal.ui.util.SWTUtil.setButtonDimensionHint(Button)
        Dialog.applyDialogFont(fNewFolderButton);
        final Object gd = fNewFolderButton.getLayoutData();
        if (gd instanceof GridData) {
            if (fNewFolderButton.getFont().equals(JFaceResources.getDefaultFont())) {
                fNewFolderButton.setFont(JFaceResources.getDialogFont());
            }
            final GC gc = new GC(fNewFolderButton);
            gc.setFont(fNewFolderButton.getFont());
            final FontMetrics fFontMetrics = gc.getFontMetrics();
            gc.dispose();
            final int widthHint = Dialog.convertHorizontalDLUsToPixels(fFontMetrics, IDialogConstants.BUTTON_WIDTH);
            ((GridData) gd).widthHint = Math.max(widthHint, fNewFolderButton.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
        }

        Dialog.applyDialogFont(result);
        return result;
    }

    private void updateNewFolderButtonState() {
        final IStructuredSelection selection = (IStructuredSelection) getTreeViewer().getSelection();
        fSelectedContainer = null;
        if (selection.size() == 1) {
            final Object first = selection.getFirstElement();
            if (first instanceof IContainer) {
                fSelectedContainer = (IContainer) first;
            }
        }
        fNewFolderButton.setEnabled(fSelectedContainer != null);
    }

    /**
     * New Folder.
     */
    protected void newFolderButtonPressed() {
        final NewFolderDialog dialog = new NewFolderDialog(getShell(), fSelectedContainer);
        if (dialog.open() == Window.OK) {
            final TreeViewer treeViewer = getTreeViewer();
            treeViewer.refresh(fSelectedContainer);
            Object createdFolder;
            if (dialog.getResult() != null) {
                createdFolder = dialog.getResult()[0];
                treeViewer.reveal(createdFolder);
                treeViewer.setSelection(new StructuredSelection(createdFolder));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(final SelectionChangedEvent event) {
        updateNewFolderButtonState();
    }

}
