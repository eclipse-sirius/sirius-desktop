/*******************************************************************************
 * Copyright (c) 2008, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

/**
 * Wizard to select representations from an Aird.
 *
 * @author cbrun
 */
public class RepresentationsSelectionWizardPage extends WizardPage {

    /** The title of the page. */
    private static final String PAGE_TITLE = Messages.RepresentationsSelectionWizardPage_pageTitle;

    /** The page is completed. */
    private static final int CODE_OK = 0;

    /** The user has not selected one or more diagrams. */
    private static final int CODE_NO_SEL = 1;

    /**
     * A representation cannot be extracted because the container is read only
     */
    private static final int CODE_ERROR_READONLY = 2;

    /** The composite control of the page. */
    private Composite pageComposite;

    /** The table viewer. */
    private CheckboxTreeViewer treeViewer;

    /** The filter. */

    private final Session root;

    private final Collection<DRepresentationDescriptor> preselection;

    /**
     * Create a new <code>DescDiagramSelectionWizardPage</code>.
     *
     * @param root
     *            the root object
     * @param representations
     *            the preselection.
     */
    public RepresentationsSelectionWizardPage(final Session root, final Collection<DRepresentationDescriptor> representations) {
        super(RepresentationsSelectionWizardPage.PAGE_TITLE);
        this.setTitle(RepresentationsSelectionWizardPage.PAGE_TITLE);
        this.root = root;
        this.preselection = representations;
        if (preselection.size() > 0) {
            setPageComplete(true);
        }
        setMessage(Messages.RepresentationsSelectionWizardPage_message, IMessageProvider.INFORMATION);
    }

    @Override
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);

        pageComposite = new Composite(parent, SWT.NONE);
        pageComposite.setLayout(new GridLayout());
        pageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        this.treeViewer = createTreeViewer(pageComposite);
        treeViewer.setInput(root);
        // treeViewer.addFilter(this.representationSelectionFilter);
        treeViewer.expandAll();
        treeViewer.collapseAll();
        setControl(pageComposite);

        for (final DRepresentationDescriptor preselected : preselection) {
            this.treeViewer.setChecked(preselected, true);
        }

    }

    /**
     * Create the table viewer.
     *
     * @param parent
     *            the parent composite.
     * @return the table viewer.
     */
    private CheckboxTreeViewer createTreeViewer(final Composite parent) {
        final ContainerCheckedTreeViewer viewer = new DescDiagramSelectionTreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        viewer.getControl().setLayoutData(gridData);
        viewer.getTree().setHeaderVisible(false);
        viewer.getTree().setLinesVisible(false);
        viewer.addCheckStateListener(new SiriusDiagramSelectionCheckStateListener());

        viewer.setContentProvider(new SessionContentProvider(this.root));
        viewer.setLabelProvider(new AdapterFactoryLabelProvider(ViewHelper.INSTANCE.createAdapterFactory()));
        return viewer;
    }

    /**
     * Change the page according to the new state of the selection.
     *
     * @author cbrun
     */
    private final class SiriusDiagramSelectionCheckStateListener implements ICheckStateListener {
        @Override
        public void checkStateChanged(final CheckStateChangedEvent event) {
            final int result = checkSelection(getSelectedElements());
            switch (result) {
            case CODE_OK:
            case CODE_NO_SEL:
                setMessage(Messages.RepresentationsSelectionWizardPage_message, IMessageProvider.INFORMATION);
                setPageComplete(true);
                break;
            case CODE_ERROR_READONLY:
                setMessage(Messages.RepresentationsSelectionWizardPage_readOnlyContainerError, IMessageProvider.ERROR);
                setPageComplete(false);
                break;
            default:
                setMessage(Messages.RepresentationsSelectionWizardPage_unknownCodeError, IMessageProvider.ERROR);
                setPageComplete(false);
                break;
            }
        }
    }

    private int checkSelection(final Collection<DRepresentationDescriptor> selectedRepresentations) {
        int result = RepresentationsSelectionWizardPage.CODE_OK;
        if (selectedRepresentations.isEmpty()) {
            result = RepresentationsSelectionWizardPage.CODE_NO_SEL;
        } else {
            for (DRepresentationDescriptor item : selectedRepresentations) {
                // check permission authority
                EObject container = item.eContainer();
                if (container instanceof DView) {
                    IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(container);
                    if (permissionAuthority != null && !permissionAuthority.canDeleteInstance(item)) {
                        // Cannot remove representation from the container
                        result = RepresentationsSelectionWizardPage.CODE_ERROR_READONLY;
                        break; // quit the while
                    }
                }
            } // while
        }
        return result;
    }

    /**
     * Selects only diagrams.
     *
     * @author cbrun
     */
    private class DescDiagramSelectionTreeViewer extends ContainerCheckedTreeViewer {

        /**
         * Create a new <code>DescDiagramSelectionTreeViewer</code>.
         *
         * @param parent
         *            the parent composite.
         */
        DescDiagramSelectionTreeViewer(final Composite parent) {
            super(parent);
        }

        /**
         * Create a new <code>DescDiagramSelectionTreeViewer</code>.
         *
         * @param parent
         *            the parent composite.
         * @param style
         *            the style.
         */
        DescDiagramSelectionTreeViewer(final Composite parent, final int style) {
            super(parent, style);
        }

        /**
         * Create a new <code>DescDiagramSelectionTreeViewer</code>.
         *
         * @param tree
         *            the tree.
         */
        DescDiagramSelectionTreeViewer(final Tree tree) {
            super(tree);
        }

        @Override
        protected void doCheckStateChanged(final Object element) {
            //
            // Check all diagrams that are under this element.
            final Widget item = findItem(element);
            if (item instanceof TreeItem) {
                final TreeItem treeItem = (TreeItem) item;
                if (!(element instanceof DRepresentationDescriptor)) {
                    final boolean result = updateChildrenItems(treeItem, treeItem.getChecked());
                    if (result) {
                        treeItem.setGrayed(true);
                    } else {
                        treeItem.setGrayed(false);
                    }
                    treeItem.setChecked(result);
                }
                updateParentItems(treeItem);
            }

        }

        /**
         * Updates the check / gray state of all parent items
         */
        private void updateParentItems(final TreeItem item) {
            if (item != null && !(item.getData() instanceof DRepresentationDescriptor)) {
                final Item[] children = getChildren(item);
                boolean containsChecked = false;
                for (Item element : children) {
                    final TreeItem curr = (TreeItem) element;
                    containsChecked = containsChecked || curr.getChecked();
                }
                item.setChecked(containsChecked);
                item.setGrayed(containsChecked);
            }
            if (item != null) {
                updateParentItems(item.getParentItem());
            }
        }

        /**
         * Updates the check state of all created children
         *
         * @return <code>true</code> if an element as been checked.
         */
        private boolean updateChildrenItems(final TreeItem parent, final boolean state) {
            boolean result = false;
            final Item[] children = getChildren(parent);
            for (Item element : children) {
                final TreeItem curr = (TreeItem) element;
                if (curr.getData() instanceof DRepresentationDescriptor && ((curr.getChecked() != state) || curr.getGrayed())) {
                    curr.setChecked(state);
                    curr.setGrayed(false);
                    result = result || state;
                    result = result || updateChildrenItems(curr, state);
                } else if (curr.getData() != null && ((curr.getChecked() != state) || curr.getGrayed())) {
                    final boolean childrenResult = updateChildrenItems(curr, state);
                    if (childrenResult) {
                        curr.setChecked(true);
                        curr.setGrayed(true);
                    } else {
                        curr.setChecked(false);
                        curr.setGrayed(false);
                    }
                    result = result || childrenResult;
                }
            }
            return result;
        }
    }

    /**
     * Return all selected elements.
     *
     * @return all selected elements.
     */
    @SuppressWarnings("unchecked")
    public Collection<DRepresentationDescriptor> getSelectedElements() {
        final Collection<DRepresentationDescriptor> result = new HashSet<DRepresentationDescriptor>();
        result.addAll((Collection<? extends DRepresentationDescriptor>) Arrays.asList(treeViewer.getCheckedElements()));
        result.removeAll(Arrays.asList(treeViewer.getGrayedElements()));
        return result;
    }

    /**
     * Session content provider.
     */
    private static final class SessionContentProvider implements ITreeContentProvider {

        private static Object[] empty = new Object[0];

        /** The semantic EMF content provider. */
        private final AdapterFactoryContentProvider semanticProvider;

        private final Session session;

        /**
         * Create a new <code>SemanticDViewContentProvider</code> with the
         * specified analysis.
         *
         * @param functionnalAnalysis
         *            the analysis to browse.
         */
        SessionContentProvider(final Session session) {
            this.session = session;
            final AdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
            this.semanticProvider = new AdapterFactoryContentProvider(adapterFactory);
        }

        @Override
        public Object[] getChildren(final Object parentElement) {
            Object[] children = SessionContentProvider.empty;
            if (parentElement instanceof Session) {
                children = ((Session) parentElement).getSemanticResources().toArray();
            } else if (parentElement instanceof Resource) {
                // Test Resource before EObject because with CDO a CDOResource
                // is a EObject
                children = ((Resource) parentElement).getContents().toArray();
            } else if (parentElement instanceof EObject && !(parentElement instanceof DRepresentationDescriptor)) {
                final EObject parent = (EObject) parentElement;
                final Collection<DRepresentationDescriptor> repDescriptors = this.findRepDescriptors(parent);
                Object[] semantic = this.semanticProvider.getChildren(parentElement);
                semantic = filtersSemanticFromAnotherResource(parent.eResource(), semantic);
                final Object[] result = new Object[repDescriptors.size() + semantic.length];
                int i = 0;
                final Iterator<DRepresentationDescriptor> iterRepDescriptor = repDescriptors.iterator();
                while (iterRepDescriptor.hasNext()) {
                    result[i++] = iterRepDescriptor.next();
                }
                System.arraycopy(semantic, 0, result, repDescriptors.size(), semantic.length);
                children = result;
            }
            return children;
        }

        private Object[] filtersSemanticFromAnotherResource(final Resource resource, final Object[] semantic) {
            final Collection<Object> filtered = new ArrayList<Object>();
            for (final Object object : semantic) {
                filtered.add(object);
            }
            for (final Object object : semantic) {
                if (object instanceof EObject) {
                    Resource r = ((EObject) object).eResource();
                    if (r != null && resource != r && session.getSemanticResources().contains(r)) {
                        filtered.remove(object);
                    }
                }
            }

            return filtered.toArray();
        }

        @Override
        public Object getParent(final Object element) {
            if (element instanceof EObject) {
                final EObject current = (EObject) element;
                final EObject parent = current instanceof DRepresentationDescriptor ? ((DRepresentationDescriptor) current).getTarget() : current.eContainer();
                return parent;
            }
            return null;
        }

        @Override
        public boolean hasChildren(final Object element) {
            return getChildren(element).length > 0;
        }

        @Override
        public Object[] getElements(final Object inputElement) {
            return getChildren(inputElement);
        }

        @Override
        public void dispose() {
            this.semanticProvider.dispose();
        }

        @Override
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // empty
        }

        /**
         * Return all the DRepresentationDescriptor for the specified viewpoint.
         *
         * @param semanticElement
         *            the parent semantic element.
         * @return all the diagrams for the specified viewpoint.
         */
        private Collection<DRepresentationDescriptor> findRepDescriptors(final EObject semanticElement) {
            if (semanticElement == null) {
                return Collections.emptySet();
            }
            return DialectManager.INSTANCE.getRepresentationDescriptors(semanticElement, session);
        }
    }

}
