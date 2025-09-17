/*******************************************************************************
 * Copyright (c) 2011, 2025 THALES GLOBAL SERVICES and others.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.AbstractTreeViewer;
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
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * This page provides a UI interface allowing the user to see its semantic models loaded by the current session and to
 * select an element compatible with a representation description to create a representation instance.
 *
 * @author nlepine
 */
public class SemanticElementSelectionWizardPage extends WizardPage {
    /** The composite control of the page. */
    private Composite pageComposite;

    /** The table viewer. */
    private TreeViewer treeViewer;

    /** The session from which representation instance will be created. */
    private final Session session;

    /**
     * The representation description that must be compatible with the semantic model element selected by user with this
     * page UI interface.
     */
    private RepresentationDescription representationDescription;

    /**
     * The tree containing the semantic models and all their elements.
     */
    private FilteredTree tree;

    /**
     * Create a new <code>SemanticElementSelectionWizardPage</code>.
     *
     * @param theSession
     *            the session from which representation instance will be created.
     */
    public SemanticElementSelectionWizardPage(final Session theSession) {
        super(Messages.SemanticElementSelectionWizardPage_title);
        this.setTitle(Messages.SemanticElementSelectionWizardPage_title);
        this.session = theSession;
        setMessage(Messages.SemanticElementSelectionWizardPage_message);
    }

    @Override
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);

        pageComposite = new Composite(parent, SWT.NONE);
        pageComposite.setLayout(new GridLayout());
        pageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        this.treeViewer = createTreeViewer(pageComposite);
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                setPageComplete(isPageComplete());
            }
        });

        treeViewer.setInput(session);
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
        EObjectFilter filter = new EObjectFilter();
        filter.setIncludeLeadingWildcard(true);
        // we set the attribute to false so the filter EObjectFilter of no compatible semantic model element with
        // representation description can be filtered even without filtering text present.
        ReflectionHelper.setFieldValueWithoutException(filter, "useEarlyReturnIfMatcherIsNull", Boolean.FALSE); //$NON-NLS-1$
        tree = SWTUtil.createFilteredTree(parent, style, filter);
        /*
         * If there is a problem accessing/enabling the quick selection mode the best course of action is to fail
         * silently, this mode only provides a slightly improved user experience by automatically selecting the first
         * element which matches the filter and is selectable.
         */
        ReflectionHelper.invokeMethodWithoutException(tree, "setQuickSelectionMode", new Class[] { Boolean.TYPE }, new Object[] { true }); //$NON-NLS-1$
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
                && DialectManager.INSTANCE.canCreate((EObject) ((StructuredSelection) selection).getFirstElement(), representationDescription, false)) {
            return (EObject) ((StructuredSelection) selection).getFirstElement();
        }
        return null;
    }

    @Override
    public boolean isPageComplete() {
        return getSelectedElement() != null;
    }

    private static final class SessionContentProvider implements ITreeContentProvider {

        private static Object[] empty = new Object[0];

        @Override
        public Object[] getChildren(final Object parentElement) {
            Object[] children = SessionContentProvider.empty;
            if (parentElement instanceof Session) {
                children = ((Session) parentElement).getSemanticResources().toArray();
            } else if (parentElement instanceof EObject) {
                children = ((EObject) parentElement).eContents().toArray();
            } else if (parentElement instanceof Resource) {
                children = ((Resource) parentElement).getContents().toArray();
            }
            return children;
        }

        @Override
        public Object getParent(final Object element) {
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
        }

        @Override
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // empty
        }
    }

    /**
     * Set the representation description that must be compatible with the semantic model element selected by user with
     * this page UI interface.
     *
     * @param representation
     *            the representation description that must be compatible with the semantic model element selected by
     *            user with this page UI interface.
     */
    public void setRepresentation(RepresentationDescription representation) {
        this.representationDescription = representation;
    }

    /**
     * Update the page.
     */
    public void update() {
        if (treeViewer != null) {
            treeViewer.refresh();
            if (tree.getFilterControl() != null) {
                treeViewer.expandToLevel(2);
                /*
                 * By setting the text to 'wilcard' ourselves we make sure to trigger the filtering. As the tree has
                 * "QuickSelectionMode" enabled it also makes the tree pre-select the first matching node, saving the
                 * user a manual selection in many cases. XXX: this does not work in the case where the representation
                 * page from CreateRepresentationWizard is automatically filled and this page is provided by default.
                 */
                tree.getFilterControl().setText("*"); //$NON-NLS-1$
            }

        }
    }

    /**
     * Viewer filter for representation creation on EObject. It filters leafs not compatible with representation
     * description either when a filtering text is present or not.
     *
     * @author nlepine
     *
     */
    private final class EObjectFilter extends PatternFilter {

        @Override
        protected boolean isParentMatch(Viewer viewer, Object element) {
            Object[] children = ((ITreeContentProvider) ((AbstractTreeViewer) viewer).getContentProvider()).getChildren(element);

            if ((children != null) && (children.length > 0)) {
                return isAnyVisible(viewer, element, children);
            }
            return false;
        }

        /**
         * Returns true if any of the elements makes it through the filter. This method uses caching if enabled; the
         * computation is done in computeAnyVisible.
         * 
         * This is a clone of {@link PatternFilter#isAnyVisible(Viewer, Object, Object[])} that is private. It does not
         * use the cache system that is also private. If it causes some performance issues, another system should be
         * use.
         *
         * @param viewer
         * @param parent
         * @param elements
         *            the elements (must not be an empty array)
         * @return true if any of the elements makes it through the filter.
         */
        private boolean isAnyVisible(Viewer viewer, Object parent, Object[] elements) {
            return computeAnyVisible(viewer, elements);
        }

        /**
         * Returns true if any of the elements makes it through the filter.
         * 
         * This is a clone of {@link PatternFilter#computeAnyVisible(Viewer, Object[])} that is private.
         *
         * @param viewer
         *            the viewer
         * @param elements
         *            the elements to test
         * @return <code>true</code> if any of the elements makes it through the filter
         */
        private boolean computeAnyVisible(Viewer viewer, Object[] elements) {
            boolean elementFound = false;
            for (int i = 0; i < elements.length && !elementFound; i++) {
                Object element = elements[i];
                elementFound = isElementVisible(viewer, element);
            }
            return elementFound;
        }

        @Override
        protected boolean isLeafMatch(Viewer viewer, Object element) {
            boolean result = false;
            if (representationDescription != null && element instanceof EObject && DialectManager.INSTANCE.canCreate((EObject) element, representationDescription, false)) {
                result = super.isLeafMatch(viewer, element);
            }
            return result;
        }

    }
}
