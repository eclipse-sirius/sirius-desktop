/*
 * Copyright (c) 2005-2008 Obeo
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */

package org.eclipse.sirius.common.ui.tools.api.dialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.sirius.common.ui.Messages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * A standard folder selection dialog.
 * 
 * @author www.obeo.fr
 * 
 */
public class FolderSelectionDialog extends AbstractFolderSelectionDialog {

    /**
     * Constructor.
     * 
     * @param message
     *            is the message
     */
    public FolderSelectionDialog(final String message) {
        super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(), FolderSelectionDialog.getContentProvider(IResource.PROJECT
                | IResource.FOLDER));
        setAllowMultiple(false);
        setTitle(Messages.FolderSelectionDialog_title);
        setMessage((message != null) ? message : ""); //$NON-NLS-1$
        setInput(ResourcesPlugin.getWorkspace().getRoot());
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final Control result = super.createDialogArea(parent);
        if (getTreeViewer() != null) {
            getTreeViewer().collapseAll();
        }
        return result;
    }

    /**
     * Returns the content provider.
     * 
     * @param resourceType
     *            is the type of the resource
     * @return the content provider
     */
    protected static ITreeContentProvider getContentProvider(final int resourceType) {
        return new WorkbenchContentProvider() {
            @Override
            public Object[] getChildren(final Object parent) {
                Object[] resultArray = new Object[0];
                if (parent instanceof IContainer) {
                    IResource[] members = null;
                    try {
                        members = ((IContainer) parent).members();
                        final List<IResource> results = new ArrayList<IResource>();
                        for (IResource member : members) {
                            if ((member.getType() & resourceType) > 0) {
                                results.add(member);
                            }
                        }
                        resultArray = results.toArray();
                    } catch (final CoreException e) {
                        resultArray = new Object[0];
                    }
                }
                if (parent instanceof Collection) {
                    resultArray = ((Collection<?>) parent).toArray();
                }
                return resultArray;
            }
        };
    }

}
