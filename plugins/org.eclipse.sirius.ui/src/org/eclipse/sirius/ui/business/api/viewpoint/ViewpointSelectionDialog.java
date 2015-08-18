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
package org.eclipse.sirius.ui.business.api.viewpoint;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.internal.movida.ViewpointDependenciesTracker;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;

/**
 * A dialog box which allow end-users to select which viewpoints are enabled
 * inside a session.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ViewpointSelectionDialog extends TitleAreaDialog {
    /**
     * The possible states for an item in the selection dialog.
     */
    enum State {
        /**
         * The item is checked, meaning the corresponding viewpoints and all the
         * item's parent are part of the selection.
         */
        CHECKED,
        /**
         * The item is checked, but grayed out, meaning the corresponding
         * viewpoint is part of the selection, but the item's parent is not.
         * This is possible if a viewpoint appears as a sub-item of several
         * other viewpoints.
         */
        GRAY_CHECKED,
        /**
         * The item is unchecked, meaning the corresponding viewpoints is not
         * part of the selection.
         */
        UNCHECKED,
    }

    // CHECKSTYLE:OFF
    private class Item {
        public final Viewpoint viewpoint;

        public final Item parent;

        public final List<Item> descendants = Lists.newArrayList();

        public Item(Item parent, Viewpoint vp) {
            this.parent = parent;
            this.viewpoint = vp;
            index.put(viewpoint, this);
        }

        public String getLabel() {
            return new IdentifiedElementQuery(viewpoint).getLabel();
        }

        public URI getViewpointURI() {
            return new ViewpointQuery(this.viewpoint).getViewpointURI().get();
        }

        private void addToSelection() {
            selection.select(getViewpointURI());
        }

        private void removeFromSelection() {
            selection.deselect(getViewpointURI());
        }

        private boolean isInSelection() {
            return selection.getSelected().contains(getViewpointURI());
        }

        /**
         * Select the viewpoint corresponding to this element and all the item's
         * parents' viewpoints.
         */
        public void onChecked() {
            for (Item current = this; current != null; current = current.parent) {
                current.addToSelection();
            }
        }

        /**
         * Deselect the viewpoint.
         */
        public void onUnchecked() {
            onUnchecked(this);
        }

        private void onUnchecked(Item unchecked) {
            if (unchecked == this) {
                removeFromSelection();
            } else if (isInSelection()) {
                if (Iterables.all(Iterables.filter(index.get(this.viewpoint), Predicates.not(Predicates.equalTo(this))), new Predicate<Item>() {
                    public boolean apply(Item input) {
                        return input.parent != null && !input.parent.isInSelection();
                    }
                })) {
                    removeFromSelection();
                }
            }
            for (Item child : descendants) {
                child.onUnchecked(unchecked);
            }
        }

        public void setInitialUiStateRecursive(State parentState) {
            if (isInSelection()) {
                if (parentState == State.CHECKED) {
                    setUiState(State.CHECKED);
                } else {
                    setUiState(State.GRAY_CHECKED);
                }
            } else {
                setUiState(State.UNCHECKED);
            }
            for (Item child : descendants) {
                child.setInitialUiStateRecursive(getUiState());
            }
        }

        protected State getUiState() {
            if (tree.getGrayed(this)) {
                return State.GRAY_CHECKED;
            } else if (tree.getChecked(this)) {
                return State.CHECKED;
            } else {
                return State.UNCHECKED;
            }
        }

        protected void setUiState(State s) {
            switch (s) {
            case CHECKED:
                tree.setChecked(this, true);
                tree.setGrayed(this, false);
                break;
            case GRAY_CHECKED:
                tree.setGrayChecked(this, true);
                break;
            case UNCHECKED:
                tree.setChecked(this, false);
                break;
            }
        }

        public void fillDescendants() {
            URI uri = getViewpointURI();
            for (URI childURI : customizeTracker.getReverseDependencies(uri)) {
                Item child = new Item(this, registry.getViewpoint(childURI));
                descendants.add(child);
                child.fillDescendants();
            }
            Collections.sort(descendants, Ordering.natural().onResultOf(new Function<Item, String>() {
                public String apply(Item from) {
                    return from.viewpoint.getName();
                }
            }));
        }

        @Override
        public String toString() {
            if (parent != null) {
                return parent.toString() + " > " + this.getLabel(); //$NON-NLS-1$
            } else {
                return this.getLabel();
            }
        }
    }

    // CHECKSTYLE:ON

    /**
     * The content provider for the tree of viewpoints.
     */
    private final class ViewpointSelectionContentProvider implements ITreeContentProvider {
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            // Ignore.
        }

        public void dispose() {
            // Ignore.
        }

        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof List<?> && Iterables.all((List<?>) inputElement, Predicates.instanceOf(Item.class))) {
                @SuppressWarnings("unchecked")
                List<Item> topItems = (List<Item>) inputElement;
                return topItems.toArray();
            } else {
                return null;
            }
        }

        public boolean hasChildren(Object element) {
            if (element instanceof Item) {
                return !((Item) element).descendants.isEmpty();
            } else {
                return false;
            }
        }

        public Object getParent(Object element) {
            if (element instanceof Item) {
                return ((Item) element).parent;
            } else {
                return null;
            }
        }

        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof Item) {
                return ((Item) parentElement).descendants.toArray();
            } else {
                return null;
            }
        }
    }

    private CheckboxTreeViewer tree;

    private Label description;

    private final ViewpointRegistry registry;

    private final org.eclipse.sirius.business.internal.movida.ViewpointSelection selection;

    private final ViewpointDependenciesTracker customizeTracker;

    private final List<Item> input;

    private Multimap<Viewpoint, Item> index = ArrayListMultimap.create();

    /**
     * Constructor.
     * 
     * @param parentShell
     *            the parent shell for the dialog box.
     * @param regsitry
     *            the registry in which to look for available viewpoints.
     * @param selection
     *            the selection of viewpoints to edit.
     * @param fileExtensions
     *            the extensions of the semantic resources in the session to
     *            configure; used to restrict the set of viewpoints displayed.
     */
    public ViewpointSelectionDialog(Shell parentShell, ViewpointRegistry regsitry, org.eclipse.sirius.business.internal.movida.ViewpointSelection selection, Collection<String> fileExtensions) {
        super(parentShell);
        // CHECKSTYLE:OFF really, checkstyle?
        setShellStyle(SWT.CLOSE | SWT.MAX | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.RESIZE);
        // CHECKSTYLE:ON
        this.registry = Preconditions.checkNotNull(regsitry);
        this.selection = Preconditions.checkNotNull(selection);
        this.customizeTracker = this.registry.createTrackerFor(this.registry.getRelations().getCustomize());
        this.input = computeItemHierarchy(fileExtensions);
    }

    private List<Item> computeItemHierarchy(final Collection<String> fileExtensions) {
        final Predicate<Viewpoint> isTopLevel = new Predicate<Viewpoint>() {
            public boolean apply(final Viewpoint vp) {
                boolean top = vp != null && vp.getCustomizes().isEmpty();
                boolean matchesSemancitModel = Iterables.any(fileExtensions, new Predicate<String>() {
                    public boolean apply(String ext) {
                        return new ViewpointQuery(vp).handlesSemanticModelExtension(ext);
                    }
                });
                return top && matchesSemancitModel;
            }
        };

        List<Item> roots = Lists.newArrayList(Iterables.transform(Iterables.filter(this.registry.getViewpoints(), isTopLevel), new Function<Viewpoint, Item>() {
            public Item apply(Viewpoint from) {
                return new Item(null, from);
            }
        }));
        for (Item item : roots) {
            item.fillDescendants();
        }
        Collections.sort(roots, Ordering.natural().onResultOf(new Function<Item, String>() {
            public String apply(Item from) {
                return from.viewpoint.getName();
            }
        }));
        return roots;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(Composite parent) {
        Control contents = super.createContents(parent);
        setTitle("Viewpoints Selection");
        setMessage("Select which viewpoints should be enabled in the session.");
        Shell shell = parent.getShell();
        shell.setText("Viewpoints Selection");
        shell.pack();
        shell.setSize(shell.getSize().x, 500);
        return contents;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        composite.setLayout(new GridLayout(1, false));

        tree = new CheckboxTreeViewer(composite);
        tree.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
        configureTreeViewer();

        Group group = new Group(composite, SWT.SHADOW_IN);
        group.setText("Description");
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        FillLayout layout = new FillLayout(SWT.HORIZONTAL);
        layout.marginHeight = 5;
        layout.marginWidth = 5;
        group.setLayout(layout);

        ScrolledComposite scrolledComposite = new ScrolledComposite(group, SWT.H_SCROLL | SWT.V_SCROLL);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);

        description = new Label(scrolledComposite, SWT.WRAP);
        scrolledComposite.setContent(description);
        return composite;
    }

    private void configureTreeViewer() {
        tree.setContentProvider(new ViewpointSelectionContentProvider());
        tree.setLabelProvider(new CellLabelProvider() {
            @Override
            public void update(ViewerCell cell) {
                Item item = (Item) cell.getElement();
                cell.setText(item.getLabel());
                cell.setImage(SiriusEditPlugin.getPlugin().getBundledImage("icons/full/obj16/Viewpoint.gif")); //$NON-NLS-1$
            }

            @Override
            public String getToolTipText(Object element) {
                Item item = (Item) element;
                return item.getViewpointURI().toString();
            }
        });
        tree.addCheckStateListener(new ICheckStateListener() {
            public void checkStateChanged(CheckStateChangedEvent event) {
                if (event.getElement() instanceof Item) {
                    Item item = (Item) event.getElement();
                    if (event.getChecked()) {
                        item.onChecked();
                    } else if (item.getUiState() == State.GRAY_CHECKED) {
                        item.onChecked();
                    } else {
                        item.onUnchecked();
                    }
                    for (Item root : input) {
                        root.setInitialUiStateRecursive(State.CHECKED);
                    }
                    selection.validate();
                    if (!selection.isValid()) {
                        setErrorMessage(selection.getErrorMessage());
                    } else {
                        setErrorMessage(null);
                    }
                    getButton(IDialogConstants.OK_ID).setEnabled(selection.isValid());
                }
            }
        });
        tree.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection sel = event.getSelection();
                if (sel instanceof IStructuredSelection) {
                    Item item = (Item) ((IStructuredSelection) sel).getFirstElement();
                    if (item != null && item.viewpoint != null) {
                        description.setText(item.viewpoint.getEndUserDocumentation());
                    } else {
                        description.setText(""); //$NON-NLS-1$
                    }
                } else {
                    description.setText(""); //$NON-NLS-1$
                }
            }
        });
        tree.setInput(input);
        for (Item root : input) {
            root.setInitialUiStateRecursive(State.CHECKED);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean close() {
        this.customizeTracker.dispose();
        return super.close();
    }
}
