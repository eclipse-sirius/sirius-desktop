/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.selection;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog displaying a list of EObjects.
 * 
 * @author cbrun
 * 
 */
public class EMFMessageDialog extends MessageDialog {

    private Collection<EObject> instances;

    private AdapterFactory factory;

    /**
     * Create a new dialog with a message and displaying an EObject list.
     * 
     * @param parentShell
     *            the current shell.
     * @param factory
     *            the adapter factory from which item/label providers will be
     *            retrieved for the list of EObjects.
     * @param eObjects
     *            the list of EObjects to display.
     * @param dialogTitle
     *            the title.
     * @param dialogTitleImage
     *            an Image.
     * @param dialogMessage
     *            the message.
     * @param dialogImageType
     *            the dialog image type.
     * @param dialogButtonLabels
     *            the button labels.
     * @param defaultIndex
     *            the default index.
     */
    // CHECKSTYLE:OFF : we really need so much arguments.
    public EMFMessageDialog(final Shell parentShell, final AdapterFactory factory, final Collection<EObject> eObjects, final String dialogTitle, final Image dialogTitleImage, final String dialogMessage,
            final int dialogImageType, final String[] dialogButtonLabels, final int defaultIndex) {
        super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, dialogButtonLabels, defaultIndex);
        this.instances = eObjects;
        this.factory = factory;
        // CHECKSTYLE:ON
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.MessageDialog#createCustomArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createCustomArea(final Composite parent) {
        return createTableViewer(parent).getControl();
    }

    /**
     * Create a tableViewer
     * 
     * @param parent
     *            the parent composite
     * @return the created tableViewer
     */
    private TableViewer createTableViewer(final Composite parent) {
        TableViewer viewer = null;
        viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        viewer.getControl().setLayoutData(gridData);
        viewer.getTable().setHeaderVisible(false);
        viewer.getTable().setLinesVisible(false);
        viewer.setContentProvider(new ArrayContentProvider());
        final AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(factory);
        viewer.setLabelProvider(labelProvider);
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setInput(this.instances);
        return viewer;
    }

    /**
     * Convenience method to open a simple Yes/No question dialog.
     * 
     * @param parent
     *            the parent shell of the dialog, or <code>null</code> if none
     * @param factory
     *            the adapter factory for EMF instances.
     * @param eObjects
     *            the list of EObjects to display.
     * @param title
     *            the dialog's title, or <code>null</code> if none
     * @param message
     *            the message
     * @return <code>true</code> if the user presses the OK button,
     *         <code>false</code> otherwise
     */
    public static boolean openQuestionWithEObjects(final Shell parent, final AdapterFactory factory, final Collection<EObject> eObjects, final String title, final String message) {
        final MessageDialog dialog = new EMFMessageDialog(parent, factory, eObjects, title, null, // accept
                message, QUESTION, new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL }, 1); // yes
        return dialog.open() == 0;
    }
}
