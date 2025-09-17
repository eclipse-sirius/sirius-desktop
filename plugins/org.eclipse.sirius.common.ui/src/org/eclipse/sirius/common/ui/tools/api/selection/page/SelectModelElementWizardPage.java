/*******************************************************************************
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.selection.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.common.ui.Messages;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * Wizard page useful to select an EMF element from the workspace.
 * 
 * @author cbrun
 */
public class SelectModelElementWizardPage extends WizardPage {

    /** The jface tree viewer. */
    protected TreeViewer myTreeViewer;

    /** the tree viewer content filter. */
    protected ModelFilesFilter filter;

    /** teh resource set for models. */
    protected ResourceSet resourceSet;

    private Composite topLevel;

    private EObject mySelectedModelElement;

    private String modelElementType;

    private Collection<String> fileExtensions;

    private AdapterFactoryLabelProvider myAdapterFactoryLabelProvider;

    private AdapterFactoryContentProvider myAdapterFactoryContentProvider;

    private IFile selectedFile;

    /**
     * Create a new dialog to select a given element.
     * 
     * @param pageName
     *            wizard page name.
     * 
     * @param factory
     *            adapter factory used to represent the models contents.
     * @param typeName
     *            {@link org.eclipse.emf.ecore.EClass} name of the type to
     *            select.
     * @param fileExtensions
     *            the file extensions the dialog should show.
     */
    public SelectModelElementWizardPage(final String pageName, final AdapterFactory factory, final Collection<String> fileExtensions, final String typeName) {
        this(pageName, factory, fileExtensions, typeName, null);
    }

    /**
     * Create a new dialog to select a given element.
     * 
     * @param pageName
     *            wizard page name.
     * 
     * @param factory
     *            adapter factory used to represent the models contents.
     * @param typeName
     *            {@link org.eclipse.emf.ecore.EClass} name of the type to
     *            select.
     * @param fileExtensions
     *            the file extensions the dialog should show.
     * @param selectedFile
     *            already selected file.
     */
    public SelectModelElementWizardPage(final String pageName, final AdapterFactory factory, final Collection<String> fileExtensions, final String typeName, final IFile selectedFile) {
        super(pageName);
        resourceSet = new ResourceSetImpl();
        this.fileExtensions = fileExtensions;
        this.modelElementType = typeName;
        myAdapterFactoryContentProvider = new AdapterFactoryContentProvider(factory);
        myAdapterFactoryLabelProvider = new AdapterFactoryLabelProvider(factory);
        this.selectedFile = selectedFile;
    }

    @Override
    public void createControl(final Composite parent) {

        initializeDialogUnits(parent);
        // top level group
        topLevel = new Composite(parent, SWT.NONE);
        topLevel.setLayout(new GridLayout());
        topLevel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
        topLevel.setFont(parent.getFont());
        PlatformUI.getWorkbench().getHelpSystem().setHelp(topLevel, IIDEHelpContextIds.NEW_FILE_WIZARD_PAGE);

        // resource and container group
        createModelBrowser(topLevel);
        // Show description on opening
        setErrorMessage(null);
        setControl(topLevel);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.DialogPage#dispose()
     */
    @Override
    public void dispose() {
        this.topLevel.dispose();
    }

    /**
     * OK button enabler selection change adapter.
     * 
     * @author mchauvin
     */
    private final class OkButtonEnabler implements ISelectionChangedListener {

        public void selectionChanged(final SelectionChangedEvent event) {
            if (event.getSelection() instanceof IStructuredSelection) {
                final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                if (selection.size() == 1) {
                    Object selectedElement = selection.getFirstElement();
                    if (selectedElement instanceof IWrapperItemProvider) {
                        selectedElement = ((IWrapperItemProvider) selectedElement).getValue();
                    }
                    if (selectedElement instanceof FeatureMap.Entry) {
                        selectedElement = ((FeatureMap.Entry) selectedElement).getValue();
                    }
                    if (selectedElement instanceof EObject) {
                        mySelectedModelElement = (EObject) selectedElement;
                        setPageComplete(SelectModelElementWizardPage.eInternalInstanceOf(mySelectedModelElement, modelElementType));
                        return;
                    }
                }
            }
            mySelectedModelElement = null;
            setPageComplete(false);
        }
    }

    private void createModelBrowser(final Composite composite) {
        myTreeViewer = new TreeViewer(composite, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER) {

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
        final GridData layoutData = new GridData(GridData.FILL_BOTH);
        layoutData.heightHint = 300;
        layoutData.widthHint = 300;
        myTreeViewer.getTree().setLayoutData(layoutData);
        myTreeViewer.setContentProvider(new ModelElementsTreeContentProvider());
        myTreeViewer.setLabelProvider(new ModelElementsTreeLabelProvider());

        setInput();

        filter = new ModelFilesFilter();
        myTreeViewer.addFilter(filter);
        myTreeViewer.addSelectionChangedListener(new OkButtonEnabler());
    }

    /**
     * Set the input of the treeviewer subclasses could modify.
     */
    protected void setInput() {
        myTreeViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
    }

    /**
     * Check that the provided file is valid.
     * 
     * @param file
     *            the file to check
     * @return <code>true</code> if the file is valid, <code>false</code>
     *         otherwise
     */
    protected boolean isValidModelFile(final IFile file) {
        if (fileExtensions == null || fileExtensions.size() == 0) {
            return true;
        }

        boolean isValid = false;

        final String fileExtension = file.getFullPath().getFileExtension();
        final Iterator<String> it = fileExtensions.iterator();
        while (it.hasNext()) {
            final String extension = it.next();
            if (fileExtension != null && fileExtension.equals(extension)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    /**
     * The content provider.
     * 
     * @author mchauvin
     */
    private final class ModelElementsTreeContentProvider implements ITreeContentProvider {

        private ITreeContentProvider myWorkbenchContentProvider = new WorkbenchContentProvider();

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        public Object[] getChildren(final Object parentElement) {

            Object[] children = myWorkbenchContentProvider.getChildren(parentElement);
            if (children != null && children.length > 0) {
                return children;
            }

            if (parentElement instanceof IFile) {
                final IFile modelFile = (IFile) parentElement;
                final IPath resourcePath = modelFile.getFullPath();
                Resource modelResource = null;
                try {
                    modelResource = resourceSet.getResource(URI.createPlatformResourceURI(resourcePath.toString(), true), true);

                } catch (final WrappedException exception) {
                    // file seems to be not valid => log it as a warning
                    SiriusTransPlugin.getPlugin().error(MessageFormat.format(Messages.SelectModelElementWizardPage_invalidFile, resourcePath.toString()), exception);
                }
                if (modelResource == null) {
                    children = Collections.EMPTY_LIST.toArray();
                } else {
                    children = myAdapterFactoryContentProvider.getChildren(modelResource);
                }
            } else {
                children = myAdapterFactoryContentProvider.getChildren(parentElement);
            }

            return children;
        }

        @Override
        public Object getParent(final Object element) {
            Object parent = myWorkbenchContentProvider.getParent(element);
            if (parent != null) {
                return parent;
            }
            if (element instanceof EObject) {
                final EObject eObject = (EObject) element;
                if (eObject.eContainer() == null) {
                    final URI eObjectResourceURI = eObject.eResource().getURI();
                    if (eObjectResourceURI.isFile()) {
                        final String path = eObjectResourceURI.path();
                        parent = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(path));
                    }
                }
                if (parent == null) {
                    parent = myAdapterFactoryContentProvider.getParent(eObject);
                }
            }

            return parent;
        }

        @Override
        public boolean hasChildren(final Object element) {
            if (element instanceof IFile) {
                return isValidModelFile((IFile) element);
            }

            boolean hasChildren = false;

            hasChildren = myWorkbenchContentProvider.hasChildren(element) || myAdapterFactoryContentProvider.hasChildren(element);

            return hasChildren;
        }

        @Override
        public Object[] getElements(final Object inputElement) {
            final Object[] elements = myWorkbenchContentProvider.getElements(inputElement);
            return elements;
        }

        @Override
        public void dispose() {
            myWorkbenchContentProvider.dispose();
            myAdapterFactoryContentProvider.dispose();
        }

        @Override
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            myWorkbenchContentProvider.inputChanged(viewer, oldInput, newInput);
            myAdapterFactoryContentProvider.inputChanged(viewer, oldInput, newInput);
        }

    }

    /**
     * The label provider.
     * 
     * @author mchauvin
     */
    private final class ModelElementsTreeLabelProvider implements ILabelProvider {

        private WorkbenchLabelProvider myWorkbenchLabelProvider = new WorkbenchLabelProvider();

        @Override
        public Image getImage(final Object element) {
            final Image result = myWorkbenchLabelProvider.getImage(element);
            return result != null ? result : myAdapterFactoryLabelProvider.getImage(element);
        }

        @Override
        public String getText(final Object element) {

            final String result = myWorkbenchLabelProvider.getText(element);
            return result != null && result.length() > 0 ? result : myAdapterFactoryLabelProvider.getText(element);
        }

        @Override
        public void addListener(final ILabelProviderListener listener) {
            myWorkbenchLabelProvider.addListener(listener);
            myAdapterFactoryLabelProvider.addListener(listener);
        }

        @Override
        public void dispose() {
            myWorkbenchLabelProvider.dispose();
            myAdapterFactoryLabelProvider.dispose();
        }

        @Override
        public boolean isLabelProperty(final Object element, final String property) {
            return myWorkbenchLabelProvider.isLabelProperty(element, property) || myAdapterFactoryLabelProvider.isLabelProperty(element, property);
        }

        @Override
        public void removeListener(final ILabelProviderListener listener) {
            myWorkbenchLabelProvider.removeListener(listener);
            myAdapterFactoryLabelProvider.removeListener(listener);
        }

    }

    private boolean containsAValidFile(final IContainer container) {
        final Collection<Boolean> result = new ArrayList<Boolean>();
        final IResourceVisitor visitor = new IResourceVisitor() {

            @Override
            public boolean visit(final IResource resource) throws CoreException {
                if (resource instanceof IFile) {
                    if (isValidModelFile((IFile) resource)) {
                        result.add(Boolean.TRUE);
                    }
                }
                return result.size() == 0;
            }
        };
        try {
            container.accept(visitor);
        } catch (final CoreException e) {
            // do nothing
        }
        return result.size() != 0;
    }

    /**
     * The viewer filter.
     * 
     * @author mchauvin
     */
    private final class ModelFilesFilter extends ViewerFilter {
        @Override
        public boolean select(final Viewer viewer, final Object parentElement, final Object element) {

            boolean isSelected = true;

            if (element instanceof IProject) {

                if (((IProject) element).isOpen()) {
                    isSelected = containsAValidFile((IContainer) element);
                } else {
                    isSelected = false;
                }

            } else if (element instanceof IContainer) {

                isSelected = containsAValidFile((IContainer) element);

            } else if (element instanceof IFile) {

                final IFile file = (IFile) element;
                isSelected = isValidModelFile(file);

            }

            return isSelected;
        }
    }

    /**
     * Get the selected model element.
     * 
     * @return the selected element.
     */
    public EObject getSelectedModelElement() {
        return mySelectedModelElement;
    }

    /**
     * Get the selected file.
     * 
     * @return the selected file.
     */
    public IFile getSelectedFile() {
        return selectedFile;
    }

    private static boolean eInternalInstanceOf(final EObject eObject, final String type) {
        if (eObject == null) {
            return false;
        }
        final List<String> allTypes = new LinkedList<String>();
        allTypes.add(eObject.eClass().getName());
        final Iterator<EClass> iterSuperTypes = eObject.eClass().getEAllSuperTypes().iterator();
        while (iterSuperTypes.hasNext()) {
            allTypes.add(eObject.eClass().getName());
        }
        return allTypes.contains(type);
    }
}
