/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * Page to select the semantic element of the new representation.
 * 
 * @author nlepine
 */
public class SemanticElementSelectionWizardPage extends WizardPage {

    private static final String SELECT_SEMANTIC_ELEMENT = "Select a semantic element for the new representation";

    /** The title of the page. */
    private static final String PAGE_TITLE = "Create a new representation";

    /** The composite control of the page. */
    private Composite pageComposite;

    /** The table viewer. */
    private TreeViewer treeViewer;

    /** The Session. */
    private final Session root;

    /** The representation. */
    private RepresentationDescription representationDescription;

    private FilteredTree tree;

    /**
     * Create a new <code>SemanticElementSelectionWizardPage</code>.
     * 
     * @param root
     *            the root object
     */
    public SemanticElementSelectionWizardPage(final Session root) {
        super(PAGE_TITLE);
        this.setTitle(PAGE_TITLE);
        this.root = root;
        setMessage(SELECT_SEMANTIC_ELEMENT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);

        pageComposite = new Composite(parent, SWT.NONE);
        pageComposite.setLayout(new GridLayout());
        pageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        this.treeViewer = createTreeViewer(pageComposite);
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                setPageComplete(isPageComplete());
            }
        });

        treeViewer.setInput(root);
        setControl(pageComposite);
    }

    /**
     * Create the table viewer.
     * 
     * @param parent
     *            the parent composite.
     * @return the table viewer.
     */
    private TreeViewer createTreeViewer(final Composite parent) {
        final int style = SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;
        tree = SWTUtil.createFilteredTree(parent, style, new EObjectFilter());
        /*
         * If there is a problem accessing/enabling the quick selection mode the
         * best course of action is to fail silently, this mode only provides a
         * slightly improved user experience by automatically selecting the
         * first element which matches the filter and is selectable.
         */
        ReflectionHelper.invokeMethodWithoutException(tree, "setQuickSelectionMode", new Class[] { Boolean.TYPE }, new Object[] { true });
        final TreeViewer viewer = tree.getViewer();

        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        viewer.getControl().setLayoutData(gridData);
        viewer.getTree().setHeaderVisible(false);
        viewer.getTree().setLinesVisible(false);
        viewer.setContentProvider(new SessionContentProvider());
        viewer.setLabelProvider(new AdapterFactoryLabelProvider(ViewHelper.INSTANCE.createAdapterFactory()));
        return viewer;
    }

    /**
     * Return the selected element.
     * 
     * @return all selected elements.
     */
    public EObject getSelectedElement() {
        ISelection selection = treeViewer.getSelection();
        if (selection instanceof StructuredSelection && ((StructuredSelection) selection).getFirstElement() instanceof EObject
                && DialectManager.INSTANCE.canCreate((EObject) ((StructuredSelection) selection).getFirstElement(), representationDescription)) {
            return (EObject) ((StructuredSelection) selection).getFirstElement();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
     */
    public boolean isPageComplete() {
        return getSelectedElement() != null;
    }

    private static final class SessionContentProvider implements ITreeContentProvider {

        private static Object[] empty = new Object[0];

        /**
         * Create a new <code>SemanticContentProvider</code>.
         * 
         */
        public SessionContentProvider() {
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        public Object[] getChildren(final Object parentElement) {
            Object[] children = empty;
            if (parentElement instanceof Session) {
                children = ((Session) parentElement).getSemanticResources().toArray();
            } else if (parentElement instanceof EObject) {
                children = ((EObject) parentElement).eContents().toArray();
            } else if (parentElement instanceof Resource) {
                children = ((Resource) parentElement).getContents().toArray();
            }
            return children;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
         */
        public Object getParent(final Object element) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        public boolean hasChildren(final Object element) {
            return getChildren(element).length > 0;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(final Object inputElement) {
            return getChildren(inputElement);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        public void dispose() {
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // empty
        }
    }

    /**
     * Set the representation.
     * 
     * @param representation
     *            RepresentationDescription
     */
    public void setRepresentation(RepresentationDescription representation) {
        this.representationDescription = representation;
    }

    /**
     * Update the page.
     */
    public void update() {
        treeViewer.refresh();
        if (tree.getFilterControl() != null) {
            /*
             * By setting the text to 'wilcard' ourselves we make sure to
             * trigger the filtering. As the tree has "QuickSelectionMode"
             * enabled it also makes the tree pre-select the first matching
             * node, saving the user a manual selection in many cases.
             */
            tree.getFilterControl().setText("*"); //$NON-NLS-1$
        }
    }

    /**
     * Viewer filter for representation creation on EObject.
     * 
     * @author nlepine
     * 
     */
    private class EObjectFilter extends PatternFilter {

        @Override
        protected boolean isLeafMatch(Viewer viewer, Object element) {
            if (representationDescription != null && element instanceof EObject && DialectManager.INSTANCE.canCreate((EObject) element, representationDescription)) {
                return super.isLeafMatch(viewer, element);
            }
            return false;
        }

    }
}
