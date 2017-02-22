/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import java.util.Collection;
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
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.sorter.CommonItemSorter;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import com.google.common.collect.Lists;

/**
 * Page to select representations from an Aird.
 *
 * @author nlepine
 */
public class RepresentationSelectionWizardPage extends WizardPage {

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
        super(Messages.RepresentationSelectionWizardPage_title);
        this.setTitle(Messages.RepresentationSelectionWizardPage_title);
        this.root = root;
        setMessage(Messages.RepresentationSelectionWizardPage_message);
    }

    @Override
    public boolean canFlipToNextPage() {
        boolean result = false;

        setErrorMessage(null); // clear previous error if exists
        ISelection selection = treeViewer.getSelection();
        if (selection instanceof StructuredSelection && ((StructuredSelection) selection).getFirstElement() instanceof RepresentationDescription) {
            RepresentationDescription representationDescription = (RepresentationDescription) ((StructuredSelection) selection).getFirstElement();
            result = true; // set to true before permission authority check

            if (root instanceof DAnalysisSessionImpl) {
                Collection<DView> containers = ((DAnalysisSessionImpl) root).getAvailableRepresentationContainers(representationDescription);

                // If containers is empty, a new one will be created, so the
                // wizard is available
                if (!containers.isEmpty()) {
                    // Try to find one valid container candidate
                    result = false;
                    for (DView container : containers) {
                        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(container);
                        if (permissionAuthority == null || permissionAuthority.canCreateIn(container)) {
                            result = true;
                            break;
                        }
                    } // for
                }
            }

            if (result) {
                setRepresentation(representationDescription);
            } else {
                setErrorMessage(Messages.RepresentationSelectionWizardPage_errorReadonlyContainer);
            }
        }

        return result;
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

    @Override
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);

        pageComposite = new Composite(parent, SWT.NONE);
        pageComposite.setLayout(new GridLayout());
        pageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        this.treeViewer = createTreeViewer(pageComposite);
        treeViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof Viewpoint && ((Viewpoint) element).getOwnedRepresentations().isEmpty()) {
                    return false;
                }
                return true;
            }
        });
        treeViewer.setInput(root);
        treeViewer.expandAll();
        treeViewer.collapseAll();
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                setPageComplete(isPageComplete());
                if (selectionWizard != null) {
                    selectionWizard.setRepresentation(getRepresentation());
                    selectionWizard.update();
                }
            }
        });

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

    /**
     * Session content provider.
     */
    private static final class SessionContentProvider implements ITreeContentProvider {

        private static Object[] empty = new Object[0];

        @Override
        public Object[] getChildren(final Object parentElement) {
            Object[] children = SessionContentProvider.empty;
            if (parentElement instanceof Session) {
                children = ((Session) parentElement).getSelectedViewpoints(false).toArray();
            } else if (parentElement instanceof Viewpoint) {
                List<RepresentationDescription> reps = Lists.newArrayList(((Viewpoint) parentElement).getOwnedRepresentations());
                Collections.sort(reps, new Comparator<RepresentationDescription>() {
                    @Override
                    public int compare(RepresentationDescription rep1, RepresentationDescription rep2) {
                        return CommonItemSorter.compareRepresentationDescriptions(rep1, rep2);
                    };
                });
                children = reps.toArray();
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
}
