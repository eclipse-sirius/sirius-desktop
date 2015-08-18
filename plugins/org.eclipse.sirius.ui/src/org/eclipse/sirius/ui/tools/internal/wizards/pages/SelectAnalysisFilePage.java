/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.dialog.FolderSelectionDialog;
import org.eclipse.sirius.common.ui.tools.api.dialog.NewFileDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * A wizard page to select a viewpoint analysis model file.
 * 
 * @author cbrun
 */
public class SelectAnalysisFilePage extends WizardPage {

    private static final String SEPARATOR = "/"; //$NON-NLS-1$

    /**
     * the tree viewer.
     */
    protected TreeViewer workspaceViewer;

    private Group newFileGroup;

    private Group existingFileGroup;

    private Group exportFolderGroup;

    private Button pickNewFile;

    private Button pickExistingFile;

    private Text newFilePath;

    private Button newFileBrowse;

    private String selectedFilePath;

    private Button pickFolder;

    private Text exportFolderPath;

    private Button exportFolderBrowse;

    /**
     * Constructor.
     * 
     * @param pageName
     *            the page name
     */
    public SelectAnalysisFilePage(final String pageName) {
        super(pageName);
        validate();
    }

    /**
     * Get the selected file.
     * 
     * @return the file path the user selected in the form of
     *         "/project/folder/file.aird"
     */
    public String getSelectedFilePath() {
        return selectedFilePath;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);
        setDescription("Select the destination file for the views");
        // top level group
        final Composite topLevel = new Composite(parent, SWT.NONE);
        topLevel.setFont(parent.getFont());
        createNewFileGroup(topLevel);
        createFolderExportGroup(topLevel);
        createExistingFileGroup(topLevel);
        parent.setSize(new Point(300, 200));
        topLevel.setLayout(new GridLayout());
        // Show description on opening
        setErrorMessage(null);
        setControl(topLevel);

        newFilePath.setEnabled(true);
        newFileBrowse.setEnabled(true);
        workspaceViewer.getTree().setEnabled(false);
        pickExistingFile.setSelection(false);
        pickFolder.setSelection(false);
        exportFolderPath.setEnabled(false);
        exportFolderBrowse.setEnabled(false);
        pickNewFile.setSelection(true);

    }

    private void createFolderExportGroup(final Composite parent) {
        String groupLabel = "Extract in one distinct file per view";
        String pickButtonLabel = "Folder";

        exportFolderGroup = createExportGroup(parent, groupLabel);

        pickFolder = createPickButton(pickButtonLabel, exportFolderGroup);
        pickFolder.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(final SelectionEvent e) {

            }

            public void widgetSelected(final SelectionEvent e) {
                /*
                 * new file panel
                 */
                pickNewFile.setSelection(false);
                newFilePath.setEnabled(false);
                newFileBrowse.setEnabled(false);
                /*
                 * existing file panel
                 */
                workspaceViewer.getTree().setEnabled(false);
                pickExistingFile.setSelection(false);
                /*
                 * my panel
                 */
                exportFolderPath.setEnabled(true);
                exportFolderBrowse.setEnabled(true);
                pickFolder.setSelection(true);
                selectedFilePath = exportFolderPath.getText();
                validate();
            }
        });

        exportFolderPath = createExportText(exportFolderGroup);

        exportFolderBrowse = createBroweButton(exportFolderGroup);
        exportFolderBrowse.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            public void widgetSelected(final SelectionEvent e) {
                browseForFolder();

            }
        });
    }

    private Button createBroweButton(Group parentGroup) {
        GridData gridData2 = new GridData();
        gridData2.horizontalAlignment = GridData.END;
        gridData2.verticalAlignment = GridData.CENTER;

        Button browseButton = new Button(parentGroup, SWT.NONE);
        browseButton.setText("Browse...");
        browseButton.setLayoutData(gridData2);
        return browseButton;
    }

    /**
     * This method initializes the new file group
     * 
     */
    private void createNewFileGroup(final Composite parent) {
        String groupLabel = "Extract in one distinct file per view";
        String pickButtonLabel = "New File";

        newFileGroup = createExportGroup(parent, groupLabel);

        pickNewFile = createPickButton(pickButtonLabel, newFileGroup);
        pickNewFile.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            public void widgetSelected(final SelectionEvent e) {
                /*
                 * my panel
                 */
                newFilePath.setEnabled(true);
                newFileBrowse.setEnabled(true);
                selectedFilePath = newFilePath.getText();
                /*
                 * existing file panel
                 */
                workspaceViewer.getTree().setEnabled(false);
                pickExistingFile.setSelection(false);
                /*
                 * folder panel
                 */
                exportFolderPath.setEnabled(false);
                pickFolder.setSelection(false);
                validate();
            }
        });

        newFilePath = createExportText(newFileGroup);

        newFileBrowse = createBroweButton(newFileGroup);
        newFileBrowse.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            public void widgetSelected(final SelectionEvent e) {
                browseForNewFile();
            }
        });
    }

    private Group createExportGroup(final Composite parent, String groupLabel) {
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.verticalAlignment = GridData.CENTER;

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;

        Group group = new Group(parent, SWT.NONE);
        group.setText(groupLabel);
        group.setLayoutData(gridData);
        group.setLayout(gridLayout);
        return group;
    }

    private Text createExportText(Group parentGroup) {
        GridData gridData3 = new GridData();
        gridData3.horizontalAlignment = GridData.FILL;
        gridData3.grabExcessHorizontalSpace = true;
        gridData3.verticalAlignment = GridData.CENTER;

        Text exportText = new Text(parentGroup, SWT.BORDER);
        exportText.setLayoutData(gridData3);
        exportText.setEditable(false);
        return exportText;
    }

    private Button createPickButton(String pickButtonLabel, Group parentGroup) {
        Button pickButton = new Button(parentGroup, SWT.RADIO);
        pickButton.setText(pickButtonLabel);
        return pickButton;
    }

    private void browseForNewFile() {
        final NewFileDialog dialog = new NewFileDialog("newfile." + SiriusUtil.SESSION_RESOURCE_EXTENSION);
        if (dialog.open() == Window.OK && dialog.getFirstResult() instanceof IContainer) {
            final IContainer folder = (IContainer) dialog.getFirstResult();
            final String newFileName = dialog.getNewFileName();
            if (!StringUtil.isEmpty(folder.getProjectRelativePath().toOSString())) {
                selectedFilePath = SEPARATOR + folder.getProject().getName() + SEPARATOR + folder.getProjectRelativePath() + SEPARATOR + newFileName;
            } else {
                selectedFilePath = SEPARATOR + folder.getProject().getName() + SEPARATOR + newFileName;
            }
            newFilePath.setText(selectedFilePath);
            validate();
        }
    }

    private void browseForFolder() {
        final FolderSelectionDialog dialog = new FolderSelectionDialog("Please select a Folder");
        if (dialog.open() == Window.OK && dialog.getFirstResult() instanceof IContainer) {
            final IContainer folder = (IContainer) dialog.getFirstResult();
            if (!StringUtil.isEmpty(folder.getProjectRelativePath().toOSString())) {
                selectedFilePath = SEPARATOR + folder.getProject().getName() + SEPARATOR + folder.getProjectRelativePath() + SEPARATOR;
            } else {
                selectedFilePath = SEPARATOR + folder.getProject().getName() + SEPARATOR;
            }
            exportFolderPath.setText(selectedFilePath);
        }
        validate();
    }

    /**
     * This method initializes the existing file creation group
     */
    private void createExistingFileGroup(final Composite parent) {
        final GridData gridData4 = new GridData();
        gridData4.horizontalAlignment = GridData.FILL;
        gridData4.grabExcessHorizontalSpace = true;
        gridData4.grabExcessVerticalSpace = true;
        gridData4.verticalAlignment = GridData.FILL;
        final GridData gridData1 = new GridData();
        gridData1.horizontalAlignment = GridData.FILL;
        gridData1.grabExcessHorizontalSpace = true;
        gridData1.grabExcessVerticalSpace = true;
        gridData1.verticalAlignment = GridData.FILL;
        existingFileGroup = new Group(parent, SWT.NONE);
        existingFileGroup.setLayout(new GridLayout());
        existingFileGroup.setText("Extract in an existing file");
        existingFileGroup.setLayoutData(gridData1);
        pickExistingFile = new Button(existingFileGroup, SWT.RADIO);
        pickExistingFile.setText("Existing File");
        pickExistingFile.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            public void widgetSelected(final SelectionEvent e) {
                /*
                 * new file panel
                 */
                newFilePath.setEnabled(false);
                newFileBrowse.setEnabled(false);
                pickNewFile.setSelection(false);
                /*
                 * folder panel
                 */
                exportFolderPath.setEnabled(false);
                pickFolder.setSelection(false);
                selectedFilePath = null;
                /*
                 * my panel
                 */
                workspaceViewer.getTree().setEnabled(true);
                pickExistingFile.setSelection(true);
                final Object element = ((StructuredSelection) workspaceViewer.getSelection()).getFirstElement();
                if (element instanceof IFile) {
                    final IFile file = (IFile) element;
                    selectedFilePath = SEPARATOR + file.getProject().getName() + SEPARATOR + file.getProjectRelativePath().toOSString();
                }
                validate();
            }
        });

        workspaceViewer = new TreeViewer(existingFileGroup, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER) {
            @Override
            public void setSelection(final ISelection selection, final boolean reveal) {
                final Object element = ((StructuredSelection) selection).getFirstElement();
                Widget item = findItem(element);
                if (item == null) {
                    final Object[] expandedElements = getExpandedElements();

                    expandAll();
                    item = findItem(element);
                    setExpandedElements(expandedElements);
                }
                if (item != null && item.getData() != null) {
                    super.setSelection(new StructuredSelection(item.getData()), reveal);
                }
            }
        };
        workspaceViewer.getTree().setLayoutData(gridData4);
        workspaceViewer.setContentProvider(new WorkbenchContentProvider());
        workspaceViewer.setLabelProvider(new WorkbenchLabelProvider());
        workspaceViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
        workspaceViewer.addFilter(new ViewerFilter() {
            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                if (element instanceof IFile) {
                    final IFile file = (IFile) element;
                    if (file.getFileExtension() != null && !file.getFileExtension().equals(SiriusUtil.SESSION_RESOURCE_EXTENSION)) {
                        return false;
                    }
                }
                return true;
            }
        });
        workspaceViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(final SelectionChangedEvent event) {
                selectedFilePath = null;
                if (event.getSelection() instanceof StructuredSelection) {
                    final Object element = ((StructuredSelection) event.getSelection()).getFirstElement();
                    if (element instanceof IFile) {
                        final IFile file = (IFile) element;
                        selectedFilePath = SEPARATOR + file.getProject().getName() + SEPARATOR + file.getProjectRelativePath().toOSString();
                    }
                }
                validate();
            }
        });
    }

    private void validate() {
        setPageComplete(true);
        if (selectedFilePath == null) {
            setPageComplete(false);
            return;
        }
        if (StringUtil.isEmpty(selectedFilePath)) {
            setPageComplete(false);
        }
    }
}
