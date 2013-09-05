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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import com.google.common.collect.Lists;

import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.description.RepresentationDescription;
import org.eclipse.sirius.description.Sirius;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.sorter.CommonItemSorter;

/**
 * Page to select representations from an Aird.
 * 
 * @author nlepine
 */
public class RepresentationSelectionWizardPage extends WizardPage {

    private static final String SELECT_REPRESENTATIONS = "Select a representation type";

    /** The title of the page. */
    private static final String PAGE_TITLE = "Create a new representation";

    /** The table viewer. */
    private TreeViewer treeViewer;

    /** The filter. */

    private final Session root;

    private Composite pageComposite;

    private RepresentationDescription representation;

    private SemanticElementSelectionWizardPage selectionWizard;

    /**
     * Create a new <code>RepresentationSelectionWizardPage</code>.
     * 
     * @param root
     *            the root object
     */
    public RepresentationSelectionWizardPage(final Session root) {
        super(PAGE_TITLE);
        this.setTitle(PAGE_TITLE);
        this.root = root;
        setMessage(SELECT_REPRESENTATIONS);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
     */
    public boolean canFlipToNextPage() {
        ISelection selection = treeViewer.getSelection();
        if (selection instanceof StructuredSelection && ((StructuredSelection) selection).getFirstElement() instanceof RepresentationDescription) {
            setRepresentation((RepresentationDescription) ((StructuredSelection) selection).getFirstElement());
            return true;
        }
        return false;
    }

    private void setRepresentation(RepresentationDescription firstElement) {
        this.representation = firstElement;
    }

    public RepresentationDescription getRepresentation() {
        return representation;
    }

    public void setSelectionWizard(SemanticElementSelectionWizardPage selectionWizard) {
        this.selectionWizard = selectionWizard;
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
        treeViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof Sirius && ((Sirius) element).getOwnedRepresentations().isEmpty()) {
                    return false;
                }
                return true;
            }
        });
        treeViewer.setInput(root);
        treeViewer.expandAll();
        treeViewer.collapseAll();
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                setPageComplete(isPageComplete());
                if (selectionWizard != null) {
                    selectionWizard.setRepresentation(getRepresentation());
                    selectionWizard.update();
                }
            }
        });

        // Button button = new Button(pageComposite, SWT.PUSH);
        // button.setText("Activate viewpoints");
        // button.addSelectionListener(new SelectionAdapter() {
        // @Override
        // public void widgetSelected(final SelectionEvent event) {
        // activateSiriusButtonPressed();
        // }
        // });
        // button.setFont(parent.getFont());
        // button.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        setControl(pageComposite);
    }

    // protected void activateSiriusButtonPressed() {
    // PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
    // public void run() {
    // SiriusSelection.openSiriussForSessionSelectionDialog(root);
    // // todo virer la dialogue de aird selection
    // }
    // });
    // }

    /**
     * Create the table viewer.
     * 
     * @param parent
     *            the parent composite.
     * @return the table viewer.
     */
    private TreeViewer createTreeViewer(final Composite parent) {
        final int style = SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;
        final FilteredTree tree = SWTUtil.createFilteredTree(parent, style, new PatternFilter());
        TreeViewer viewer = tree.getViewer();

        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        viewer.getControl().setLayoutData(gridData);
        viewer.getTree().setHeaderVisible(false);
        viewer.getTree().setLinesVisible(false);
        viewer.setContentProvider(new SessionContentProvider());
        viewer.setLabelProvider(new AdapterFactoryLabelProvider(ViewHelper.INSTANCE.createAdapterFactory()));
        return viewer;
    }

    /**
     * return if the page is the current page.
     * 
     * @return if the page is the current page.
     */
    public boolean isCurrentPageOnWizard() {
        return super.isCurrentPage();
    }

    private static final class SessionContentProvider implements ITreeContentProvider {

        private static Object[] empty = new Object[0];

        /**
         * Create a new <code>SemanticContentProvider</code> with the specified
         * session.
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
                children = ((Session) parentElement).getSelectedSiriuss(false).toArray();
            } else if (parentElement instanceof Sirius) {
                List<RepresentationDescription> reps = Lists.newArrayList(((Sirius) parentElement).getOwnedRepresentations());
                Collections.sort(reps, new Comparator<RepresentationDescription>() {
                    public int compare(RepresentationDescription rep1, RepresentationDescription rep2) {
                        return CommonItemSorter.compareRepresentationDescriptions(rep1, rep2);
                    };
                });
                children = reps.toArray();
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

}
