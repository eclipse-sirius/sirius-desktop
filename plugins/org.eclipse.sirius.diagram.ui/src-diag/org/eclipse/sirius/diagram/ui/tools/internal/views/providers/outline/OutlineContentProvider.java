/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.provider.DiagramItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeBeginLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeEndLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DNodeLabelItemProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DiagramOutlinePageListener;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.provider.ViewpointItemProviderAdapterFactory;

/**
 * This class is responsible of the outline tree viewer content.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public class OutlineContentProvider implements ITreeContentProvider, DiagramOutlinePageListener {

    /** The EMF adapter */
    OutlineContentResourceSetListener outlineContentResourceSetListener = new OutlineContentResourceSetListener();

    private boolean checkParent(final EObject parent) {

        boolean check = false;

        // should never happen but avoid an NPE if it happens
        if (parent == null) {
            check = true;
        } else if (parent instanceof DDiagram) {
            check = true;
        } else if (parent instanceof DDiagramElementContainer) {
            check = true;
        }

        return check;
    }

    private DDiagram getDiagramContainer(final EObject element) {

        DDiagram dia = null;

        if (element instanceof DDiagram) {
            dia = (DDiagram) element;
        } else if (element != null) {
            dia = getDiagramContainer(element.eContainer());
        }
        return dia;
    }

    private List<Object> clearFilteredElements(final List<? extends Object> elements, final DDiagram vp) {
        Session session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) vp).getTarget());
        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, vp);
        final List<Object> result = new ArrayList<Object>(elements.size());
        final Iterator<? extends Object> it = elements.iterator();
        while (it.hasNext()) {
            final Object element = it.next();
            if (element instanceof DDiagramElement) {
                DDiagramElement dde = (DDiagramElement) element;
                if (!isFiltered(mappingManager, dde)) {
                    result.add(dde);
                }
            } else if (element instanceof AbstractDDiagramElementLabelItemProvider) {
                Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) element).getDiagramElementTarget();
                if (optionTarget.some() && !isFiltered(mappingManager, optionTarget.get())) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    /**
     * Check if the diagram element is filtered.
     * 
     * @param dde
     *            The diagram element to check.
     */
    private boolean isFiltered(DiagramMappingsManager session, DDiagramElement dde) {
        boolean isFiltered = true;
        if (dde.isVisible()) {
            isFiltered = false;
        } else if (LayerHelper.isInActivatedLayer(session, dde) && !new DDiagramElementQuery(dde).isFiltered()) {
            isFiltered = false;
        }
        return isFiltered;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    @Override
    public Object getParent(final Object element) {

        Object theParent = null;

        // diagram is the root object
        if (element instanceof DDiagram) {
            return theParent;
        } else if (element instanceof DNode) {

            final DNode vn = (DNode) element;
            final EObject parent = vn.eContainer();

            if (parent instanceof DNode) {
                theParent = parent;
            } else if (checkParent(parent)) {
                theParent = parent;
            }
        }

        else if (element instanceof DDiagramElement) {

            final DDiagramElement vpe = (DDiagramElement) element;
            final EObject parent = vpe.eContainer();

            if (checkParent(parent)) {
                theParent = parent;
            }
        }

        return theParent;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(final Object parentElement) {

        Object[] children = null;

        if (parentElement instanceof DDiagram) {
            final DDiagram diagram = (DDiagram) parentElement;
            children = clearFilteredElements(diagram.getOwnedDiagramElements(), diagram).toArray();
        }

        else if (parentElement instanceof DNode) {
            final DNode dNode = (DNode) parentElement;
            List<Object> originalChildren = new ArrayList<Object>(dNode.getOwnedBorderedNodes());

            // if the current node should have a DNodeLabelItem has children
            if (DNodeLabelItemProvider.hasRelevantLabelItem(dNode)) {
                originalChildren.add(0, new DNodeLabelItemProvider(getAdapterFactory(), dNode));
            }
            children = clearFilteredElements(originalChildren, getDiagramContainer(dNode)).toArray();
        }

        else if (parentElement instanceof DEdge) {
            final DEdge dEdge = (DEdge) parentElement;
            List<Object> originalChildren = new ArrayList<Object>();

            // if the current node should have a DEdgeLabelItem has children
            if (DEdgeBeginLabelItemProvider.hasRelevantLabelItem(dEdge)) {
                originalChildren.add(0, new DEdgeBeginLabelItemProvider(getAdapterFactory(), dEdge));
            }
            if (DEdgeLabelItemProvider.hasRelevantLabelItem(dEdge)) {
                originalChildren.add(0, new DEdgeLabelItemProvider(getAdapterFactory(), dEdge));
            }
            if (DEdgeEndLabelItemProvider.hasRelevantLabelItem(dEdge)) {
                originalChildren.add(0, new DEdgeEndLabelItemProvider(getAdapterFactory(), dEdge));
            }
            children = clearFilteredElements(originalChildren, getDiagramContainer(dEdge)).toArray();
        }

        else if (parentElement instanceof DDiagramElementContainer) {
            final DDiagramElementContainer diagramElementContainer = (DDiagramElementContainer) parentElement;
            children = clearFilteredElements(diagramElementContainer.getElements(), getDiagramContainer(diagramElementContainer)).toArray();
        }

        return children;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren(final Object element) {

        boolean hasChildren = false;

        if (element instanceof DDiagram || element instanceof DNode || element instanceof DEdge || element instanceof DDiagramElementContainer) {
            hasChildren = getChildren(element).length > 0;
        }
        // in other case return false
        return hasChildren;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(final Object inputElement) {

        // in case of diagram return the viewpoint
        if (inputElement instanceof Diagram) {
            final Diagram diagram = (Diagram) inputElement;
            final EObject viewpointDiagram = diagram.getElement();
            if (viewpointDiagram != null) {
                return new Object[] { viewpointDiagram };
            }
        }

        // all other case return the element
        return new Object[] { inputElement };
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {
        // do nothing
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        // Only remove listener of oldInput if old input is valid
        if (oldInput != null && (!(oldInput instanceof EObject) || ((EObject) oldInput).eResource() != null)) {
            removeListenerFrom((View) oldInput);
        }
        if (newInput != null) {
            outlineContentResourceSetListener.setViewer(viewer);
            addListenerTo((View) newInput);
        }
    }

    private void removeListenerFrom(final View oldInput) {
        final EObject element = oldInput.getElement();
        if (element instanceof DDiagram) {
            final DDiagram diagram = (DDiagram) element;
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);
            /* Editing domain is null */
            if (domain != null) {
                domain.removeResourceSetListener(outlineContentResourceSetListener);
            }
        }
    }

    private void addListenerTo(final View newInput) {
        final EObject element = newInput.getElement();
        if (element instanceof DDiagram) {
            final DDiagram diagram = (DDiagram) element;
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);
            if (domain != null) {
                domain.addResourceSetListener(outlineContentResourceSetListener);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.outline.DiagramOutlinePageListener#activate(int)
     */
    @Override
    public void activate(int page) {
        this.outlineContentResourceSetListener.activate(page);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.outline.DiagramOutlinePageListener#deactivate(int)
     */
    @Override
    public void deactivate(int page) {
        this.outlineContentResourceSetListener.deactivate(page);
    }

    /**
     * Returns the adapter factory used by this viewer.
     * 
     * @return The adapter factory used by this viewer.
     */
    public AdapterFactory getAdapterFactory() {
        List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        factories.add(new ViewpointItemProviderAdapterFactory());
        factories.add(new DiagramItemProviderAdapterFactory());
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new EcoreItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());
        return new ComposedAdapterFactory(factories);
    }
}
