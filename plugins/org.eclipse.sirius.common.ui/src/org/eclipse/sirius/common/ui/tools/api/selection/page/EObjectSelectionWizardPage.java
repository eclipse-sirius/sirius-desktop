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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider;
import org.eclipse.sirius.common.ui.tools.api.selection.EObjectSelectionWizard;
import org.eclipse.sirius.common.ui.tools.api.util.TreeItemWrapperContentProvider;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

/**
 * A wizard page to select an EObject.
 * 
 * @author mchauvin
 */
public class EObjectSelectionWizardPage extends AbstractSelectionWizardPage {

    /** */
    Composite pageComposite;

    /** The input. */
    private TreeItemWrapper treeObjects;

    /** The label provider adapter factory. */
    private final AdapterFactoryLabelProvider myAdapterFactoryLabelProvider;

    /** The selected instances * */
    private final List<EObject> selectedEObjects = new ArrayList<EObject>();

    /** The viewer. */
    private TreeViewer treeViewer;

    /** Tell whether multiple selections are authorized or not * */
    private boolean isMany;

    /** Tell whether if first element should automatically be selected * */
    private boolean selectFirst;

    /**
     * Constructor.
     * 
     * @param pageName
     *            the name of the page
     * @param title
     *            the title for this wizard page, or <code>null</code> if none
     * @param imageTitle
     *            the image descriptor for the title of this wizard page, or <code>null</code> if none
     * @param objects
     *            the candidate objects
     * @param factory
     *            the adapter factory to provide text and images
     */
    public EObjectSelectionWizardPage(final String pageName, final String title, final ImageDescriptor imageTitle, final Collection<? extends EObject> objects, final AdapterFactory factory) {
        super(pageName, title, imageTitle);
        setPageComplete(false);
        this.myAdapterFactoryLabelProvider = new AdapterFactoryLabelProvider(factory);
        this.treeObjects = convertToTree(objects);
        this.selectFirst = true;
    }

    /**
     * Constructor.
     * 
     * @param pageName
     *            the name of the page
     * @param title
     *            the title for this wizard page, or <code>null</code> if none
     * @param imageTitle
     *            the image descriptor for the title of this wizard page, or <code>null</code> if none
     * @param treeObjects
     *            the candidate objects
     * @param factory
     *            the adapter factory to provide text and images
     */
    public EObjectSelectionWizardPage(final String pageName, final String title, final ImageDescriptor imageTitle, final TreeItemWrapper treeObjects, final AdapterFactory factory) {
        super(pageName, title, imageTitle);
        setPageComplete(false);
        this.myAdapterFactoryLabelProvider = new AdapterFactoryLabelProvider(factory);
        this.treeObjects = treeObjects;
        this.selectFirst = true;
    }

    private TreeItemWrapper convertToTree(final Collection<?> objects2) {
        final TreeItemWrapper root = new TreeItemWrapper(null, null);
        final Iterator<?> iter = objects2.iterator();
        while (iter.hasNext()) {
            final TreeItemWrapper treeItemWrapper = new TreeItemWrapper(iter.next(), root);
            root.getChildren().add(treeItemWrapper);
        }
        return root;
    }

    /**
     * Tell whether the page should authorize multiple selections.
     * 
     * @param many
     *            true if multiple selection is authorized, false otherwise.
     */
    public void setMany(final boolean many) {
        this.isMany = many;
    }

    /**
     * Select the first element in the list or not.
     * 
     * @param select
     *            <code>true</code> if first element should be automatically selected, <code>false</code> otherwise
     */
    public void setFirstElementSelected(final boolean select) {
        this.selectFirst = select;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);

        pageComposite = new Composite(parent, SWT.NONE);
        pageComposite.setLayout(new GridLayout());
        pageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        createSelectionGroup(pageComposite);
        treeViewer = createTreeViewer(pageComposite);
        treeViewer.setInput(this.treeObjects);

        myViewerfilter.setTreeViewer(treeViewer);

        /* expand before compute prefix */
        expandTreeViewer(treeViewer);

        initRootPrefix();
        if (selectFirst && treeViewer.getTree().getItemCount() > 0) {
            treeViewer.getTree().setSelection(treeViewer.getTree().getItem(0));
            if (treeViewer.getTree().getItem(0).getData() instanceof TreeItemWrapper) {
                selectedEObjects.add((EObject) ((TreeItemWrapper) treeViewer.getTree().getItem(0).getData()).getWrappedObject());
            }
            setPageComplete(true);
        }
        setControl(pageComposite);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.WizardPage#isCurrentPage()
     */
    @Override
    public boolean isCurrentPage() {
        // increases visibility
        return super.isCurrentPage();
    }

    /**
     * Create a tableViewer
     * 
     * @param parent
     *            the parent composite
     * @return the created tableViewer
     */
    private TreeViewer createTreeViewer(final Composite parent) {
        TreeViewer viewer = null;
        if (!isMany) {
            viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        } else {
            viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        }
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        viewer.getControl().setLayoutData(gridData);
        viewer.getTree().setHeaderVisible(false);
        viewer.getTree().setLinesVisible(false);
        final ITreeContentProvider contentProvider = new GroupingContentProvider(new TreeItemWrapperContentProvider());
        viewer.setContentProvider(contentProvider);
        viewer.setLabelProvider(new EObjectSelectionLabelProvider());
        viewer.setComparator(new ViewerComparator());

        viewer.addFilter(this.myViewerfilter);

        viewer.addSelectionChangedListener(new EObjectSelectionListAdapter());

        viewer.getTree().addMouseListener(MouseListener.mouseDoubleClickAdapter(evt -> {

            EObjectSelectionWizard wizard = (EObjectSelectionWizard) this.getWizard();
            if (wizard.getDialog() != null) {
                wizard.getDialog().finishPressed();
            }
        }));
        return viewer;
    }

    /**
     * Compute a common prefix for all items.
     */
    private void initRootPrefix() {
        String prefix = null;
        boolean again = true;

        final int count = this.treeViewer.getTree().getItemCount();

        for (int i = 0; i < count && again; i++) {
            final TreeItem treeItem = this.treeViewer.getTree().getItem(i);
            prefix = computeCommonPrefix(prefix, treeItem);
            if (prefix == null) {
                again = false;
            } else {
                prefix = computeChildrenPrefix(prefix, treeItem);
                if (prefix == null) {
                    again = false;
                }
            }

        }
        if (prefix != null) {
            this.elementsToSelectText.setText(prefix);
            this.elementsToSelectText.setSelection(prefix.length());
        }
    }

    private String computeChildrenPrefix(final String oldPrefix, final TreeItem parent) {
        String prefix = oldPrefix;
        boolean again = true;

        final int count = parent.getItemCount();

        for (int i = 0; i < count && again; i++) {
            final TreeItem treeItem = parent.getItem(i);
            prefix = computeCommonPrefix(prefix, treeItem);
            if (prefix == null) {
                again = false;
            } else {
                prefix = computeChildrenPrefix(prefix, treeItem);
                if (prefix == null) {
                    again = false;
                }
            }
        }
        return prefix;
    }

    private String computeCommonPrefix(final String oldPrefix, final TreeItem treeItem) {
        String prefix = oldPrefix;
        if (prefix == null) {
            // the prefix is equal to the first item (default)
            prefix = treeItem.getText();
        } else {
            if (!treeItem.getText().startsWith(prefix)) {
                // we must find a new prefix.
                int searchIndex = Math.min(prefix.length() - 1, treeItem.getText().length());
                String newPrefix = null;
                while (searchIndex > 0 && newPrefix == null) {
                    if (treeItem.getText().startsWith(prefix.substring(0, searchIndex))) {
                        newPrefix = prefix.substring(0, searchIndex);
                    } else {
                        searchIndex--;
                    }
                }
                if (newPrefix != null) {
                    prefix = newPrefix;
                } else {
                    // no common prefix found.
                    prefix = null;
                }
            }
        }
        return prefix;
    }

    /**
     * Get the selected EObject. If they are several objects selected, return the first.
     * 
     * @return the selected EObject
     */
    public EObject getSelectedEObject() {
        if (selectedEObjects.size() > 0) {
            return this.selectedEObjects.get(0);
        }
        return null;
    }

    /**
     * Get the selected {@link EObject}, if they are many.
     * 
     * @return the list of selected instances.
     */
    public List<EObject> getSelectedEObjects() {
        return this.selectedEObjects;

    }

    /**
     * Selection change adapter.
     * 
     * @author mchauvin
     */
    private final class EObjectSelectionListAdapter implements ISelectionChangedListener {

        @Override
        public void selectionChanged(final SelectionChangedEvent event) {
            if (event.getSelection() instanceof IStructuredSelection) {
                final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                setPageComplete(false);
                if (selection.size() > 0) {
                    final Iterator<?> it = selection.toList().iterator();
                    selectedEObjects.clear();
                    while (it.hasNext()) {
                        final Object selectedElement = it.next();
                        if (selectedElement instanceof TreeItemWrapper) {
                            selectedEObjects.add((EObject) ((TreeItemWrapper) selectedElement).getWrappedObject());
                            setPageComplete(true);
                        } else if (selectedElement instanceof EObject) {
                            selectedEObjects.add((EObject) selectedElement);
                            setPageComplete(true);
                        }

                    }
                    return;
                }
            }
            selectedEObjects.clear();
            setPageComplete(false);
        }
    }

    /**
     * A label provider.
     * 
     * @author mchauvin
     */
    private final class EObjectSelectionLabelProvider extends LabelProvider {

        @Override
        public Image getImage(final Object element) {
            Image result = null;
            if (element instanceof TreeItemWrapper) {
                result = myAdapterFactoryLabelProvider.getImage(((TreeItemWrapper) element).getWrappedObject());
            } else if (element instanceof ItemDecorator) {
                result = ((ItemDecorator) element).getImage();
            } else {
                result = myAdapterFactoryLabelProvider.getImage(element);
            }
            return result;
        }

        @Override
        public String getText(final Object element) {
            String result = null;
            if (element instanceof TreeItemWrapper) {
                result = myAdapterFactoryLabelProvider.getText(((TreeItemWrapper) element).getWrappedObject());
            } else if (element instanceof ItemDecorator) {
                result = ((ItemDecorator) element).getText();
            } else {
                result = myAdapterFactoryLabelProvider.getText(element);
            }
            return result;
        }
    }

    /**
     * Dispose the created swt resources. {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.DialogPage#dispose()
     */
    @Override
    public void dispose() {

        myAdapterFactoryLabelProvider.dispose();

        if (pageComposite != null && !pageComposite.isDisposed()) {
            pageComposite.dispose();
        }

        super.dispose();
    }

    /**
     * Sets the candidates objects and changes the input of the tree viewer.
     * 
     * @param objects
     *            the candidate objects
     */
    public void setTreeObjects(final Collection<? extends EObject> objects) {
        this.treeObjects = convertToTree(objects);
        if (treeViewer != null && treeViewer.getTree() != null && !treeViewer.getTree().isDisposed()) {
            treeViewer.setInput(this.treeObjects);
        }
    }
}
