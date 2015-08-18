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
package org.eclipse.sirius.common.ui.tools.api.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * This dialog can be used to select a workspace resource.
 * 
 * @author lgoubet <a
 *         href="mailto:laurent.goubet@obeo.fr">laurent.goubet@obeo.fr</a>
 */
public class WorkspaceResourceDialog extends Dialog {
    /** Extensions of the files to display. */
    protected final List<String> extensions;

    /** Will hold the files selected by the user. */
    private final List<IResource> selectedResources = new ArrayList<IResource>();

    /**
     * Style of the dialog. Can be either {@link SWT#MULTI} or
     * {@link SWT#SINGLE}.
     */
    private final int selectionStyle;

    /** Title of this dialog. */
    private final String title;

    /** Viewer used to display the workspace resources. */
    private TreeViewer viewer;

    /**
     * Instantiates a WorkspaceResourceDialog without filtering its view.
     * <p>
     * <code>style</code> can be either {@link SWT#MULTI} or {@link SWT#SINGLE}
     * (default).
     * </p>
     * 
     * @param parent
     *            The parent Shell.
     * @param style
     *            Style of the dialog.
     * @param dialogTitle
     *            Title of this dialog.
     */
    public WorkspaceResourceDialog(final Shell parent, final int style, final String dialogTitle) {
        this(parent, style, dialogTitle, null);
    }

    /**
     * Instantiates a WorkspaceResourceDialog restricting its view to files with
     * a given extension.
     * <p>
     * <code>style</code> can be either {@link SWT#MULTI} or {@link SWT#SINGLE}
     * (default).
     * </p>
     * 
     * @param parent
     *            The parent Shell.
     * @param style
     *            Style of the dialog.
     * @param dialogTitle
     *            Title of this dialog.
     * @param fileExtensions
     *            Only files with this file extension will be shown.
     *            <code>null</code> or <code>*</code> will display all files.
     */
    public WorkspaceResourceDialog(final Shell parent, final int style, final String dialogTitle, final List<String> fileExtensions) {
        super(parent);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        title = dialogTitle;
        extensions = fileExtensions;
        if (style == SWT.MULTI || style == SWT.SINGLE) {
            selectionStyle = style;
        } else {
            selectionStyle = SWT.SINGLE;
        }
    }

    /**
     * Returns the selected resources.
     * 
     * @return The selected resources, <code>null</code> if none or canceled.
     */
    public List<IResource> getSelectedResources() {
        if (getReturnCode() == OK) {
            return selectedResources;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);
        getShell().setText(title);
        createViewer(composite);
        final GridData data = new GridData(GridData.FILL_BOTH);
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        data.heightHint = 400;
        data.widthHint = 300;
        viewer.getControl().setLayoutData(data);
        return composite;
    }

    /**
     * Creates the TreeViewer used as this dialog's navigator.
     * 
     * @param parent
     *            Parent composite of the navigator.
     */
    protected void createViewer(final Composite parent) {
        viewer = new TreeViewer(parent, selectionStyle | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        viewer.setUseHashlookup(true);
        viewer.setContentProvider(new WorkbenchContentProvider());
        viewer.setLabelProvider(WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider());
        viewer.addFilter(new FileExtensionFilter());
        viewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
        viewer.getTree().addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(final SelectionEvent e) {
                // This will happen in the event of a double click
                if (!viewer.getControl().isDisposed()) {
                    validate();
                }
            }

            public void widgetSelected(final SelectionEvent e) {
                validate();
            }
        });
        viewer.addDoubleClickListener(new IDoubleClickListener() {
            public void doubleClick(final DoubleClickEvent e) {
                if (validate()) {
                    okPressed();
                }
            }
        });
    }

    /**
     * This will return <code>true</code> if the user has selected a resource
     * and this resource has the required extension if any.
     * 
     * @return <code>true</code> if a resource has been selected and its
     *         extension is one of the expected, <code>false</code> otherwise.
     */
    public boolean validate() {
        selectedResources.clear();
        final ISelection selection = viewer.getSelection();
        final List<IResource> result = new ArrayList<IResource>();
        if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
            final Iterator<?> selectionIterator = ((IStructuredSelection) selection).iterator();
            while (selectionIterator.hasNext()) {
                final Object next = selectionIterator.next();
                if (next instanceof IResource) {
                    result.add((IResource) next);
                } else if (next instanceof IAdaptable) {
                    final IResource resource = (IResource) ((IAdaptable) next).getAdapter(IResource.class);
                    if (resource != null) {
                        result.add(resource);
                    }
                }
            }
        }

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getType() == IResource.FILE) {
                selectedResources.add(result.get(i));
            }
        }
        getButton(Window.OK).setEnabled(selectedResources.size() > 0);
        return selectedResources.size() > 0;
    }

    /**
     * Instances of this class will be used to filter out of the navigator the
     * resources which file extension is not one of the expected.
     * 
     * @author Laurent Goubet <a
     *         href="mailto:laurent.goubet@obeo.fr">laurent.goubet@obeo.fr</a>
     */
    private class FileExtensionFilter extends ViewerFilter {
        /**
         * Default constructor.
         */
        public FileExtensionFilter() {
            super();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        @Override
        public boolean select(final Viewer theViewer, final Object parentElement, final Object element) {
            if (extensions == null || extensions.size() == 0 || extensions.contains("*")) { //$NON-NLS-1$
                return true;
            }

            IResource resource = null;
            if (element instanceof IResource) {
                resource = (IResource) element;
            } else if (element instanceof IAdaptable) {
                resource = (IResource) ((IAdaptable) element).getAdapter(IResource.class);
            }

            boolean isValid = false;

            if (resource != null && !resource.isDerived()) {

                // In the case of a container, we'll probe its content to see if
                // it contains a valid file
                if (resource.getType() == IResource.FOLDER || resource.getType() == IResource.PROJECT) {
                    try {
                        final IResource[] members = ((IContainer) resource).members();
                        for (IResource member : members) {
                            isValid = select(theViewer, parentElement, member);
                            if (isValid) {
                                // we found a valid resource in this folder, it
                                // is
                                // useless to probe any further
                                break;
                            }
                        }
                    } catch (final CoreException e) {
                        // This shouldn't happen
                    }
                }
                // In the case of a file, we'll check if its extension is one of
                // the expected
                if (resource.getType() == IResource.FILE) {
                    if (extensions.size() > 0) {
                        for (int i = 0; i < extensions.size(); i++) {
                            if (extensions.get(i).toString().equalsIgnoreCase(resource.getFileExtension())) {
                                isValid = true;
                                // found a valid file, no need to probe any
                                // further
                                break;
                            }
                        }
                    } else {
                        isValid = true;
                    }
                }
            }
            return isValid;
        }
    }
}
