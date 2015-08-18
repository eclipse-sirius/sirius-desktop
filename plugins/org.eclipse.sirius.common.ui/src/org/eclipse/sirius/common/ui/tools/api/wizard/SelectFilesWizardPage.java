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

package org.eclipse.sirius.common.ui.tools.api.wizard;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.osgi.framework.Bundle;

import com.google.common.collect.Lists;

// Disable checkstyle. This class comes from Acceleo.
// CHECKSTYLE:OFF

/**
 * The role of this wizard page is to make a collection of all of the checked
 * resources.
 * 
 * @author www.obeo.fr
 */
public class SelectFilesWizardPage extends WizardPage {

    private static final String JOKER = "*"; //$NON-NLS-1$

    // Constants
    private static final int SELECTION_WIDGET_WIDTH = 400;

    private static final int SELECTION_WIDGET_HEIGHT = 300;

    /**
     * The selection group.
     */
    private CheckboxTreeAndListGroup checkboxGroup;

    /**
     * The min length of the selection.
     */
    private int lower;

    /**
     * The max length of the selection.
     */
    private int upper;

    /**
     * The extensions.
     */
    private String[] extensions;

    private IResource initialResourceSelection;

    /**
     * Constructor.
     * 
     * @param pageName
     *            is the name of the page
     * @param lower
     *            is the min length of the selection
     * @param upper
     *            is the max length of the selection
     * @param extensions
     *            is a table of extensions
     */
    public SelectFilesWizardPage(final String pageName, final int lower, final int upper, final String[] extensions) {
        super(pageName);
        setTitle(pageName);
        setDescription("This page is used to select file(s) in the workspace."); //$NON-NLS-1$
        this.lower = lower;
        this.upper = upper;
        this.extensions = extensions;
    }

    public void setInitialSelection(final IStructuredSelection selection) {
        final Object element = selection.getFirstElement();
        if (element instanceof IResource) {
            initialResourceSelection = (IResource) element;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(final Composite parent) {
        final Composite container = new Composite(parent, SWT.NULL);
        final GridLayout containerLayout = new GridLayout();
        containerLayout.numColumns = 1;
        containerLayout.marginTop = 14;
        containerLayout.verticalSpacing = 9;
        containerLayout.marginLeft = 7;
        containerLayout.marginRight = 7;
        container.setLayout(containerLayout);
        final WorkbenchLabelProvider treeLabelProvider = new WorkbenchLabelProvider();
        final WorkbenchLabelProvider listLabelProvider = new WorkbenchLabelProvider();
        checkboxGroup = new CheckboxTreeAndListGroup(container, ResourcesPlugin.getWorkspace().getRoot(), getContentProvider(IResource.FOLDER | IResource.PROJECT | IResource.ROOT), treeLabelProvider,
                getContentProvider(IResource.FILE), listLabelProvider, SWT.NONE, SELECTION_WIDGET_WIDTH, SELECTION_WIDGET_HEIGHT);
        checkboxGroup.addCheckStateListener(new ICheckStateListener() {
            public void checkStateChanged(CheckStateChangedEvent event) {
                dialogChanged();
            }
        });
        checkboxGroup.getTreeViewer().addOpenListener(new IOpenListener() {
            public void open(OpenEvent event) {
                ISelection selection = event.getSelection();
                if (!selection.isEmpty() && selection instanceof StructuredSelection) {
                    checkboxGroup.getTreeViewer().expandToLevel(((StructuredSelection) selection).getFirstElement(), AbstractTreeViewer.ALL_LEVELS);
                }
            }
        });

        if (initialResourceSelection != null) {
            checkboxGroup.initialCheckListItem(initialResourceSelection);
        }

        container.addControlListener(new ControlListener() {
            public void controlMoved(ControlEvent e) {
            }

            public void controlResized(ControlEvent e) {
                TableColumn[] columns = checkboxGroup.getListTable().getColumns();
                for (TableColumn column : columns) {
                    column.pack();
                }
            }
        });

        dialogChanged();
        setControl(container);
    }

    /**
     * Returns the content provider.
     * 
     * @param resourceType
     *            is the type of the resource
     * @return the content provider
     */
    private ITreeContentProvider getContentProvider(final int resourceType) {
        return new WorkbenchContentProvider() {
            @Override
            public Object[] getChildren(Object o) {
                if (resourceType != IResource.FILE && o instanceof IExtensionRegistry) {
                    Set<Bundle> result = new TreeSet<Bundle>(new Comparator<Bundle>() {
                        public int compare(Bundle bundle0, Bundle bundle1) {
                            return bundle0.getSymbolicName().compareTo(bundle1.getSymbolicName());
                        }
                    });
                    return result.toArray();
                } else if (resourceType == IResource.FILE && o instanceof Bundle) {
                    final Bundle bundle = (Bundle) o;
                    final List<IPath> result = new ArrayList<IPath>();
                    for (String extension : extensions) {
                        if (JOKER.equals(extension)) {
                            result.clear();
                            final Enumeration<?> enumeration = bundle.findEntries("/", JOKER, true); //$NON-NLS-1$ 
                            while (enumeration != null && enumeration.hasMoreElements()) {
                                final Object child = enumeration.nextElement();
                                if (child instanceof URL) {
                                    result.add(new Path(bundle.getSymbolicName()).append(((URL) child).getPath()));
                                }
                            }
                            break;
                        } else {
                            final Enumeration<?> enumeration = bundle.findEntries("/", "*." + extension, true); //$NON-NLS-1$ //$NON-NLS-2$
                            while (enumeration != null && enumeration.hasMoreElements()) {
                                final Object child = enumeration.nextElement();
                                if (child instanceof URL) {
                                    result.add(new Path(bundle.getSymbolicName()).append(((URL) child).getPath()));
                                }
                            }
                        }
                    }
                    return result.toArray();
                } else if (resourceType == IResource.FILE && o instanceof IPath) {
                    return new Object[0];
                } else if (o instanceof IContainer) {
                    IResource[] members = null;
                    try {
                        members = ((IContainer) o).members();
                    } catch (final CoreException e) {
                        return new Object[0];
                    }
                    final ArrayList<IResource> results = new ArrayList<IResource>();
                    for (IResource member : members) {
                        if ((member.getType() & resourceType) > 0 && isSignificant(member)) {
                            if (member instanceof IFile) {
                                String extension = member.getFileExtension();
                                if (extension == null) {
                                    extension = ""; //$NON-NLS-1$
                                }
                                for (String extension2 : extensions) {
                                    if (JOKER.equals(extension2) || extension.equals(extension2)) {
                                        results.add(member);
                                        break;
                                    }
                                }
                            } else if (member instanceof IContainer && countMembers((IContainer) member) > 0) {
                                results.add(member);
                            }
                        }
                    }
                    return results.toArray();
                } else if (o instanceof List) {
                    List<Object> result = Lists.newArrayList();
                    Iterator<?> it = ((List<?>) o).iterator();
                    while (it.hasNext()) {
                        Object element = it.next();
                        if (element instanceof IContainer) {
                            if (((IContainer) element).isAccessible() && countMembers((IContainer) element) > 0) {
                                result.add(element);
                            }
                        } else {
                            result.add(element);
                        }
                    }
                    return result.toArray();
                } else {
                    return new Object[0];
                }
            }
        };
    }

    private int countMembers(final IContainer container) {
        try {
            final IResource[] members = container.members();
            int result = 0;
            for (IResource member : members) {
                if (isSignificant(member)) {
                    if (member instanceof IFile) {
                        String extension = member.getFileExtension();
                        if (extension == null) {
                            extension = ""; //$NON-NLS-1$
                        }
                        for (String extension2 : extensions) {
                            if (JOKER.equals(extension2) || extension.equals(extension2)) {
                                result++;
                                break;
                            }
                        }
                    } else if (member instanceof IContainer) {
                        result += countMembers((IContainer) member);
                    }
                }
            }
            return result;
        } catch (final CoreException e) {
            return 0;
        }
    }

    private boolean isSignificant(final IResource resource) {
        return !(resource instanceof IFolder && (resource.getName().startsWith("."))); //$NON-NLS-1$
    }

    /**
     * Validates the changes on the page.
     */
    private void dialogChanged() {
        final List<IPath> items = getAllCheckedListItems();
        if (items.size() < lower) {
            updateStatus("Not enough files were selected."); //$NON-NLS-1$
            return;
        } else if (items.size() > upper && upper != -1) {
            updateStatus("Too many files were selected."); //$NON-NLS-1$
            return;
        }
        updateStatus(null);
    }

    /**
     * Updates the status of the page.
     * 
     * @param message
     *            is the error message.
     */
    private void updateStatus(String message) {
        setMessage(message);
        setPageComplete(message == null);
    }

    /**
     * Returns the selected files.
     * 
     * @return the selected files
     */
    public IPath[] getSelection() {
        List<IPath> items = getAllCheckedListItems();
        return items.toArray(new IPath[items.size()]);
    }

    private List<IPath> getAllCheckedListItems() {
        List<IPath> items = new ArrayList<IPath>();
        Iterator<?> resultEnum = checkboxGroup.getAllCheckedListItems();
        while (resultEnum.hasNext()) {
            Object member = resultEnum.next();
            if (member instanceof IFile) {
                items.add(((IFile) member).getFullPath());
            } else if (member instanceof IPath) {
                items.add((IPath) member);
            }
        }
        return items;
    }

}

// CHECKSTYLE:ON
