/*******************************************************************************
 * Copyright (c) 2000, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Obeo - Adaptations.
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * An abstract base class for content outline pages.
 * <p>
 * Clients who are defining an editor may elect to provide a corresponding
 * content outline page. This content outline page will be presented to the user
 * via the standard Content Outline View (the user decides whether their
 * workbench window contains this view) whenever that editor is active. This
 * class should be subclassed.
 * </p>
 * <p>
 * Internally, each content outline page consists of a filtered tree viewer;
 * selections made in the tree viewer are reported as selection change events by
 * the page (which is a selection provider). The tree viewer is not created
 * until <code>createPage</code> is called; consequently, subclasses must extend
 * <code>createControl</code> to configure the tree viewer with a proper content
 * provider, label provider, and input element.
 * </p>
 * <p>
 * Note that those wanting to use a control other than internally created
 * <code>FilteredTree</code> will need to implement
 * <code>IContentOutlinePage</code> directly rather than subclassing this class.
 * </p>
 * 
 * @author mchauvin
 */
public abstract class AbstractExtendedContentOutlinePage extends Page implements IContentOutlinePage, ISelectionChangedListener, ISelectionListener {

    /**
     * The delay to use for scheduling the setSelectionJob.
     */
    private static final int SET_SELECTION_JOB_SCHEDULE_DELAY = 300;

    /**
     * Job used to set selection.
     */
    protected Job setSelectionJob;

    private ListenerList selectionChangedListeners = new ListenerList();

    private Composite control;

    private FilteredTree filteredTree;

    private TreeViewer treeViewer;

    /**
     * Create a new extended content outline page.
     */
    protected AbstractExtendedContentOutlinePage() {
        super();
    }

    /**
     * Returns whether or not there are less than two views in the list.
     * 
     * @param tree
     * @return <code>true</code> if there are less than two views in the list.
     */
    private boolean hasAtMostOneView(final TreeViewer tree) {
        final ITreeContentProvider contentProvider = (ITreeContentProvider) tree.getContentProvider();
        final Object[] children = contentProvider.getElements(tree.getInput());

        boolean result = false;

        if (children.length <= 1) {
            if (children.length == 0) {
                result = true;
            } else {
                result = !contentProvider.hasChildren(children[0]);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
     */
    @Override
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
        selectionChangedListeners.add(listener);
    }

    /**
     * Creates the filtered tree control. Subclasses may redefine to provide
     * custom control.
     * 
     * @param parent
     *            the parent composite
     * @return a FilteredTree control instance
     */
    protected FilteredTree createFilteredTree(final Composite parent) {
        final int style = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;
        return SWTUtil.createFilteredTree(parent, style, new PatternFilter());
    }

    /**
     * The <code>AbstractExtendedContentOutlinePage</code> implementation of
     * this <code>IContentOutlinePage</code> method creates a filtered tree
     * viewer.
     * 
     * @param parent
     *            the parent composite
     */
    @Override
    public void createControl(final Composite parent) {

        control = SWTUtil.createCompositeBothFill(parent, 1, false);

        filteredTree = createFilteredTree(control);
        treeViewer = filteredTree.getViewer();
        treeViewer.addSelectionChangedListener(this);

        // listen to events which occurs outside of and in the outline
        getSite().getPage().addSelectionListener(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.Page#dispose()
     */
    @Override
    public void dispose() {
        // remove listeners
        getSite().getPage().removeSelectionListener(this);
        treeViewer.removeSelectionChangedListener(this);
        treeViewer.getTree().dispose();
        filteredTree.dispose();
        super.dispose();

    }

    // TODOMCH think to something better this
    /**
     * Method called at the end of the control creation.
     * 
     * @param parent
     *            the parent composite.
     */
    public void endControl(final Composite parent) {
        filteredTree.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));

        // if the tree has only one or zero views, disable the filter text
        // control
        if (hasAtMostOneView(filteredTree.getViewer())) {
            final Text filterText = filteredTree.getFilterControl();
            if (filterText != null) {
                filterText.setEnabled(false);
            }
        }
    }

    /**
     * Fires a selection changed event.
     * 
     * @param selection
     *            the new selection
     */
    protected void fireSelectionChanged(final ISelection selection) {
        // create an event
        final SelectionChangedEvent event = new SelectionChangedEvent(this, selection);

        // fire the event
        final Object[] listeners = selectionChangedListeners.getListeners();
        for (int i = 0; i < listeners.length; ++i) {
            final ISelectionChangedListener l = (ISelectionChangedListener) listeners[i];
            SafeRunner.run(new SafeRunnable() {
                @Override
                public void run() {
                    l.selectionChanged(event);
                }
            });
        }
    }

    /**
     * Returns the main control. {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.Page#getControl()
     */
    @Override
    public Control getControl() {
        return control;
    }

    /**
     * Returns this page's tree viewer.
     * 
     * @return this page's tree viewer, or <code>null</code> if
     *         <code>createControl</code> has not been called yet
     */
    protected TreeViewer getTreeViewer() {
        return treeViewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.Page#init(org.eclipse.ui.part.IPageSite)
     */
    @Override
    public void init(final IPageSite pageSite) {
        super.init(pageSite);
        pageSite.setSelectionProvider(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
     */
    @Override
    public ISelection getSelection() {
        if (treeViewer == null) {
            return StructuredSelection.EMPTY;
        }
        return treeViewer.getSelection();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
     */
    @Override
    public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
        selectionChangedListeners.remove(listener);
    }

    /**
     * Gives notification that the tree selection has changed. {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        fireSelectionChanged(event.getSelection());
    }

    /**
     * Sets focus to a part in the page.
     */
    @Override
    public void setFocus() {
        treeViewer.getControl().setFocus();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setSelection(final ISelection selection) {

        // Setting the selection on the treeViewer can be rather long, as
        // outline is refreshed. Hence, we launch the refresh of the treeviewer
        // inside a Job, that will be canceled if a new selection is set.
        if (setSelectionJob != null) {
            setSelectionJob.cancel();
        }
        setSelectionJob = new Job(Messages.AbstractExtendedContentOutlinePage_setSelectionJobName) {
            @Override
            protected IStatus run(IProgressMonitor monitor) {
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        if (treeViewer != null) {
                            treeViewer.setSelection(selection);
                        }
                    }
                });
                return Status.OK_STATUS;
            }
        };
        setSelectionJob.setSystem(true);
        setSelectionJob.schedule(SET_SELECTION_JOB_SCHEDULE_DELAY);
    }

    /**
     * Returns a StructuredSelection that contains all DiagramElements matching
     * the EditParts contained in the given original Selection.
     * 
     * @param originalSelection
     *            the original selection to wrap
     * @return a StructuredSelection that contains all DiagramElements matching
     *         the EditParts contained in the given original Selection
     */
    protected IStructuredSelection createStructuredSelectionWrapper(final IStructuredSelection originalSelection) {
        final List<Object> elements = new ArrayList<Object>(originalSelection.toList().size());
        final Iterator<?> it = originalSelection.toList().iterator();
        while (it.hasNext()) {
            final Object obj = it.next();
            if (obj instanceof EditPart) {
                final EditPart editPart = (EditPart) obj;
                if (editPart.getModel() instanceof View) {
                    final EObject element = ((View) (editPart.getModel())).getElement();
                    if (element != null) {
                        elements.add(element);
                    }
                }
            } else {
                elements.add(obj);
            }
        }
        return new StructuredSelection(elements);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        if (!(part instanceof ContentOutline)) {
            if (selection instanceof IStructuredSelection) {
                setSelection(createStructuredSelectionWrapper((IStructuredSelection) selection));
            }
        }
    }
}
