/*
 * Copyright (c) 2006, 2007 Borland Software Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Alexander Shatalin (Borland) - initial API and implementation
 * Cedric Brun      (Obeo) <cedric.brun@obeo.fr>      - Adaptation 
 */

package org.eclipse.sirius.common.ui.tools.api.selection;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * This dialog show the workspace and open the models allowing people to select
 * an element of a given type.
 * 
 * @author cbrun <a href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 * 
 */
public class ModelElementChooserDialog extends Dialog {

    private TreeViewer myTreeViewer;

    private EObject mySelectedModelElement;

    private String modelElementType;

    private AdapterFactoryLabelProvider myAdapterFactoryLabelProvider;

    private AdapterFactoryContentProvider myAdapterFactoryContentProvider;

    private TransactionalEditingDomain myEditingDomain;

    private Collection<String> fileExtensions;

    /**
     * Create a new dialog to select a given element.
     * 
     * @param parentShell
     *            current shell.
     * @param factory
     *            adapter factory used to represent the models contents.
     * @param modelElementType
     *            {@link EClass} name of the type to select.
     */
    public ModelElementChooserDialog(final Shell parentShell, final AdapterFactory factory, final String modelElementType) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        myAdapterFactoryContentProvider = new AdapterFactoryContentProvider(factory);
        myAdapterFactoryLabelProvider = new AdapterFactoryLabelProvider(factory);
        this.modelElementType = modelElementType;
        this.myEditingDomain = WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain();
    }

    /**
     * Create a new dialog to select a given element.
     * 
     * @param parentShell
     *            current shell.
     * @param factory
     *            adapter factory used to represent the models contents.
     * @param modelElementType
     *            {@link EClass} name of the type to select.
     * @param extensions
     *            the file extensions the dialog should show.
     */
    public ModelElementChooserDialog(final Shell parentShell, final AdapterFactory factory, final String modelElementType, final Collection<String> extensions) {
        this(parentShell, factory, modelElementType);
        this.fileExtensions = extensions;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);
        getShell().setText("Select a model element");
        createModelBrowser(composite);
        return composite;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createButtonBar(final Composite parent) {
        final Control buttonBar = super.createButtonBar(parent);
        setOkButtonEnabled(false);
        return buttonBar;
    }

    @Override
    public boolean close() {
        this.myEditingDomain.dispose();
        return super.close();
    }

    private void createModelBrowser(final Composite composite) {
        myTreeViewer = new TreeViewer(composite, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        final GridData layoutData = new GridData(GridData.FILL_BOTH);
        layoutData.heightHint = 300;
        layoutData.widthHint = 300;
        myTreeViewer.getTree().setLayoutData(layoutData);
        myTreeViewer.setContentProvider(new ModelElementsTreeContentProvider());
        myTreeViewer.setLabelProvider(new ModelElementsTreeLabelProvider());
        myTreeViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
        myTreeViewer.addFilter(new ModelFilesFilter());
        myTreeViewer.addSelectionChangedListener(new OkButtonEnabler());
    }

    private void setOkButtonEnabled(final boolean enabled) {
        getButton(IDialogConstants.OK_ID).setEnabled(enabled);
    }

    private boolean isValidModelFile(final IFile file) {
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
     * Gets the URI of the selected element.
     * 
     * @return the URI of the selected element.
     */
    public URI getSelectedModelElementURI() {
        final Resource resource = mySelectedModelElement.eResource();
        return resource.getURI().appendFragment(resource.getURIFragment(mySelectedModelElement));
    }

    /**
     * The content provider.
     * 
     * @author cbrun
     */
    private class ModelElementsTreeContentProvider implements ITreeContentProvider {

        private ITreeContentProvider myWorkbenchContentProvider = new WorkbenchContentProvider();

        @Override
        public Object[] getChildren(final Object parentElement) {
            Object[] result = myWorkbenchContentProvider.getChildren(parentElement);
            if (result == null || result.length <= 0) {
                if (parentElement instanceof IFile) {
                    final IFile modelFile = (IFile) parentElement;
                    final IPath resourcePath = modelFile.getFullPath();
                    final ResourceSet resourceSet = myEditingDomain.getResourceSet();
                    final Resource modelResource = resourceSet.getResource(URI.createPlatformResourceURI(resourcePath.toString(), true), true);
                    result = myAdapterFactoryContentProvider.getChildren(modelResource);
                } else {
                    result = myAdapterFactoryContentProvider.getChildren(parentElement);
                }
            }
            return result;
        }

        @Override
        public Object getParent(final Object element) {
            Object parent = myWorkbenchContentProvider.getParent(element);

            if (parent == null) {
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
            }
            return parent;
        }

        public boolean hasChildren(final Object element) {
            if (element instanceof IFile) {
                return isValidModelFile((IFile) element);
            }
            return myWorkbenchContentProvider.hasChildren(element) || myAdapterFactoryContentProvider.hasChildren(element);
        }

        public Object[] getElements(final Object inputElement) {
            final Object[] elements = myWorkbenchContentProvider.getElements(inputElement);
            return elements;
        }

        public void dispose() {
            myWorkbenchContentProvider.dispose();
            myAdapterFactoryContentProvider.dispose();
        }

        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            myWorkbenchContentProvider.inputChanged(viewer, oldInput, newInput);
            myAdapterFactoryContentProvider.inputChanged(viewer, oldInput, newInput);
        }

    }

    /**
     * The label provider.
     * 
     * @author cbrun
     */
    private class ModelElementsTreeLabelProvider implements ILabelProvider {

        private WorkbenchLabelProvider myWorkbenchLabelProvider = new WorkbenchLabelProvider();

        public Image getImage(final Object element) {
            final Image result = myWorkbenchLabelProvider.getImage(element);
            return result != null ? result : myAdapterFactoryLabelProvider.getImage(element);
        }

        public String getText(final Object element) {
            final String result = myWorkbenchLabelProvider.getText(element);
            return result != null && result.length() > 0 ? result : myAdapterFactoryLabelProvider.getText(element);
        }

        public void addListener(final ILabelProviderListener listener) {
            myWorkbenchLabelProvider.addListener(listener);
            myAdapterFactoryLabelProvider.addListener(listener);
        }

        public void dispose() {
            myWorkbenchLabelProvider.dispose();
            myAdapterFactoryLabelProvider.dispose();
        }

        public boolean isLabelProperty(final Object element, final String property) {
            return myWorkbenchLabelProvider.isLabelProperty(element, property) || myAdapterFactoryLabelProvider.isLabelProperty(element, property);
        }

        public void removeListener(final ILabelProviderListener listener) {
            myWorkbenchLabelProvider.removeListener(listener);
            myAdapterFactoryLabelProvider.removeListener(listener);
        }

    }

    /**
     * The viewer filter.
     * 
     * @author cbun
     */
    private class ModelFilesFilter extends ViewerFilter {

        @Override
        public boolean select(final Viewer viewer, final Object parentElement, final Object element) {

            boolean isSelected = true;

            if (element instanceof IContainer) {
                isSelected = true;
            } else if (element instanceof IFile) {
                final IFile file = (IFile) element;
                isSelected = isValidModelFile(file);
            }
            return isSelected;
        }

    }

    /**
     * OK button selection change listener.
     * 
     * @author cbrun
     */
    private class OkButtonEnabler implements ISelectionChangedListener {

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
                        setOkButtonEnabled(ModelElementChooserDialog.types(mySelectedModelElement).contains(modelElementType));
                        return;
                    }
                }
            }
            mySelectedModelElement = null;
            setOkButtonEnabled(false);
        }

    }

    private static List<String> types(final EObject eObject) {
        final List<String> types = new LinkedList<String>();
        types.add(eObject.eClass().getName());
        for (EClass eClass : eObject.eClass().getEAllSuperTypes()) {
            types.add(eClass.getName());
        }
        return types;
    }

}
