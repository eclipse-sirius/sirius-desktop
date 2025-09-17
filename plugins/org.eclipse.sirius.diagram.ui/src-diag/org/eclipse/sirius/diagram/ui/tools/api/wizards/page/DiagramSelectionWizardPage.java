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
package org.eclipse.sirius.diagram.ui.tools.api.wizards.page;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * Wizard to select diagrams from an Aird.
 * 
 * @author ymortier
 */
public class DiagramSelectionWizardPage extends WizardPage {

    /** The page is completed. */
    private static final int CODE_OK = 0;

    /** The user has not selected one or more diagrams. */
    private static final int CODE_NO_SEL = 1;

    /** The user has selected an object that is not a diagram. */
    private static final int CODE_ERROR = 2;

    /** The composite control of the page. */
    private Composite pageComposite;

    /** The table viewer. */
    private CheckboxTreeViewer treeViewer;

    /** The root element. */
    private DView root;

    /** The filter. */
    private ViewerFilter diagramSelectionFilter;

    /**
     * Create a new <code>DescDiagramSelectionWizardPage</code>.
     * 
     * @param root
     *            the root object
     */
    public DiagramSelectionWizardPage(final DView root) {
        super(Messages.DiagramSelectionWizardPage_title);
        initialize(root, new DiagramSelectionFilter());
    }

    /**
     * Create a new <code>DescDiagramSelectionWizardPage</code>.
     * 
     * @param root
     *            the root object
     * @param diagramSelectionFilter
     *            specific diagramSelectionFilter (the default
     *            diagramSelectionFilter display all the DDiagram instances).
     * @since 0.9.0
     */
    public DiagramSelectionWizardPage(final DView root, final ViewerFilter diagramSelectionFilter) {
        super(Messages.DiagramSelectionWizardPage_title);
        initialize(root, diagramSelectionFilter);
    }

    private void initialize(final DView aRoot, final ViewerFilter aDiagramSelectionFilter) {
        this.root = aRoot;
        this.setPageComplete(false);
        this.diagramSelectionFilter = aDiagramSelectionFilter;

        setMessage(Messages.DiagramSelectionWizardPage_message, IMessageProvider.INFORMATION);

        if (root.getViewpoint() != null && !StringUtil.isEmpty(new IdentifiedElementQuery(root.getViewpoint()).getLabel())) {
            this.setTitle(MessageFormat.format(Messages.DiagramSelectionWizardPage_titleFor, new IdentifiedElementQuery(root.getViewpoint()).getLabel()));
        } else {
            this.setTitle(Messages.DiagramSelectionWizardPage_title);
        }
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

        this.treeViewer = createTreeViewer(pageComposite);
        treeViewer.setInput(root.getModels().iterator().next());
        treeViewer.addFilter(this.diagramSelectionFilter);
        treeViewer.expandAll();
        treeViewer.collapseAll();
        setControl(pageComposite);
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

        // TODOYMO check the type of root ! Change the type in constructor !
        viewer.setContentProvider(new SemanticDViewContentProvider(this.root));
        final ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        viewer.setLabelProvider(new AdapterFactoryLabelProvider(composedAdapterFactory));

        return viewer;
    }

    /**
     * Filter the tree.
     * 
     * @author ymortier
     */
    private final class DiagramSelectionFilter extends ViewerFilter {

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        @Override
        public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
            if (element instanceof EObject && treeViewer.getContentProvider() != null) {
                final EObject eObject = (EObject) element;
                boolean resultForThis = baseSelect(eObject);
                final Object[] children = ((ITreeContentProvider) treeViewer.getContentProvider()).getChildren(element);
                if (children != null) {
                    for (int i = 0; i < children.length && !resultForThis; i++) {
                        resultForThis = select(viewer, element, children[i]);
                    }
                }
                return resultForThis;
            }
            return false;
        }

        private boolean baseSelect(final Object element) {
            return element instanceof DDiagram;
        }
    }

    /**
     * Change the page according to the new state of the selection.
     * 
     * @author ymortier
     */
    private final class SiriusDiagramSelectionCheckStateListener implements ICheckStateListener {

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
         */
        @Override
        public void checkStateChanged(final CheckStateChangedEvent event) {
            final int result = checkSelection(getSelectedElements());
            switch (result) {
            case CODE_OK:
                setMessage(Messages.DiagramSelectionWizardPage_message, IMessageProvider.INFORMATION);
                setPageComplete(true);
                break;
            case CODE_NO_SEL:
                setMessage(Messages.DiagramSelectionWizardPage_message, IMessageProvider.INFORMATION);
                setPageComplete(false);
                break;
            case CODE_ERROR:
                setMessage(Messages.SiriusDiagramSelectionCheckStateListener_errorMsg, IMessageProvider.ERROR);
                setPageComplete(false);
                break;
            default:
                setMessage(Messages.SiriusDiagramSelectionCheckStateListener_unknwonCodeResult, IMessageProvider.ERROR);
                setPageComplete(false);
                break;
            }
        }

    }

    private int checkSelection(final Collection<?> selectedItems) {
        int result = CODE_OK;
        if (selectedItems.isEmpty()) {
            result = CODE_NO_SEL;
        } else {
            final Iterator<?> iterItems = selectedItems.iterator();
            while (iterItems.hasNext()) {
                final Object next = iterItems.next();
                if (!(next instanceof DDiagram)) {
                    result = CODE_ERROR;
                }
            }
        }
        return result;
    }

    /**
     * Selects only diagrams.
     * 
     * @author ymortier
     */
    public static class DescDiagramSelectionTreeViewer extends ContainerCheckedTreeViewer {

        /**
         * Create a new <code>DescDiagramSelectionTreeViewer</code>.
         * 
         * @param parent
         *            the parent composite.
         */
        public DescDiagramSelectionTreeViewer(final Composite parent) {
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
        public DescDiagramSelectionTreeViewer(final Composite parent, final int style) {
            super(parent, style);
        }

        /**
         * Create a new <code>DescDiagramSelectionTreeViewer</code>.
         * 
         * @param tree
         *            the tree.
         */
        public DescDiagramSelectionTreeViewer(final Tree tree) {
            super(tree);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.dialogs.ContainerCheckedTreeViewer#doCheckStateChanged(java.lang.Object)
         */
        @Override
        protected void doCheckStateChanged(final Object element) {
            //
            // Check all diagrams that are under this element.
            final Widget item = findItem(element);
            if (item instanceof TreeItem) {
                final TreeItem treeItem = (TreeItem) item;
                if (!(element instanceof DDiagram)) {
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
            if (item != null && !(item.getData() instanceof DDiagram)) {
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
                if (curr.getData() instanceof DDiagram && ((curr.getChecked() != state) || curr.getGrayed())) {
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
    public Collection<?> getSelectedElements() {
        final Collection<Object> result = new HashSet<Object>();
        result.addAll(Arrays.asList(treeViewer.getCheckedElements()));
        result.removeAll(Arrays.asList(treeViewer.getGrayedElements()));
        return result;
    }

    /**
     * Get the root element.
     * 
     * @return the root element used to create this wizard page
     */
    public DView getRoot() {
        return this.root;
    }

    private static final class SemanticDViewContentProvider implements ICommonContentProvider {

        private static Object[] empty = new Object[0];

        /** The functional analysis to browse. */
        private DView representationContainer;

        /** The semantic EMF content provider. */
        private AdapterFactoryContentProvider semanticProvider;

        /**
         * Create a new <code>SemanticDViewContentProvider</code> with the
         * specified analysis.
         * 
         * @param functionnalAnalysis
         *            the analysis to browse.
         */
        SemanticDViewContentProvider(final DView functionnalAnalysis) {
            this.representationContainer = functionnalAnalysis;
            final AdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
            this.semanticProvider = new AdapterFactoryContentProvider(adapterFactory);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.navigator.ICommonContentProvider#init(org.eclipse.ui.navigator.ICommonContentExtensionSite)
         */
        @Override
        public void init(final ICommonContentExtensionSite config) {
            // empty.
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        @Override
        public Object[] getChildren(final Object parentElement) {
            if (parentElement instanceof EObject && !(parentElement instanceof DDiagram)) {
                final EObject parent = (EObject) parentElement;
                final Collection<DRepresentation> representations = this.findRepresentations(parent);
                final Object[] semantic = this.semanticProvider.getChildren(parentElement);
                final Object[] result = new Object[representations.size() + semantic.length];
                int i = 0;
                final Iterator<DRepresentation> iterRepresentation = representations.iterator();
                while (iterRepresentation.hasNext()) {
                    result[i++] = iterRepresentation.next();
                }
                System.arraycopy(semantic, 0, result, representations.size(), semantic.length);
                return result;
            }
            return empty;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
         */
        @Override
        public Object getParent(final Object element) {
            if (element instanceof EObject) {
                final EObject current = (EObject) element;
                final EObject parent = current instanceof DSemanticDiagram ? ((DSemanticDiagram) current).getTarget() : current.eContainer();
                return parent;
            }
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        @Override
        public boolean hasChildren(final Object element) {
            return getChildren(element).length > 0;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        @Override
        public Object[] getElements(final Object inputElement) {
            return getChildren(inputElement);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        @Override
        public void dispose() {
            this.semanticProvider.dispose();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        @Override
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // empty
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.navigator.IMementoAware#restoreState(org.eclipse.ui.IMemento)
         */
        @Override
        public void restoreState(final IMemento memento) {
            // empty.
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.navigator.IMementoAware#saveState(org.eclipse.ui.IMemento)
         */
        @Override
        public void saveState(final IMemento memento) {
            // empty.
        }

        /**
         * Return all the diagrams for the specified viewpoint.
         * 
         * @param semanticElement
         *            the parent semantic element.
         * @return all the diagrams for the specified viewpoint.
         */
        private Collection<DRepresentation> findRepresentations(final EObject semanticElement) {
            if (semanticElement == null) {
                return Collections.emptySet();
            }
            final Collection<DRepresentation> result = new HashSet<DRepresentation>();
            final Iterator<DRepresentation> itRepresentations = new DViewQuery(this.representationContainer).getLoadedRepresentations().iterator();
            while (itRepresentations.hasNext()) {
                final DRepresentation representation = itRepresentations.next();
                if (representation instanceof DSemanticDecorator) {
                    if (semanticElement.equals(((DSemanticDecorator) representation).getTarget())) {
                        result.add(representation);
                    }
                }
            }
            return result;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canFlipToNextPage() {
        return getNextPage() != null;
    }

}
