/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.layout.ordering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Tree ordering to order semantic elements.
 * 
 * @author ymortier
 */
public abstract class AbstractSemanticTreeOrdering extends AbstractTreeViewOrdering {
    @Override
    public List<View> getChildren(final View parent, final List<View> views) {
        // FIXME YMO doesn't work fine if there are many views that have the
        // same target.
        final List<EObject> semantics = new ArrayList<>(views.size());
        //
        // Gets the parent semantic element.
        final EObject semanticParent = this.resolveSemanticElement(parent);
        if (semanticParent == null) {
            return Collections.EMPTY_LIST;
        }
        //
        // Keep a trace semantic -> view.
        final Map<EObject, View> semanticToView = new HashMap<>();
        final Iterator<View> iterViews = views.iterator();
        //
        // Find all semantic elements.
        while (iterViews.hasNext()) {
            final View currentView = iterViews.next();
            // Only Nodes are able to compose a tree.
            if (currentView instanceof Node) {
                final EObject semanticElement = this.resolveSemanticElement(currentView);
                if (semanticElement != null) {
                    semantics.add(semanticElement);
                    semanticToView.put(semanticElement, currentView);
                }
            }
        }
        //
        // Gets children.
        final List<EObject> semanticChildren = this.getSemanticChildren(semanticParent, semantics);
        final List<View> viewRoots = new ArrayList<>(semanticChildren.size());
        //
        // Gets view roots.
        final Iterator<EObject> iterSemanticRoots = semanticChildren.iterator();
        while (iterSemanticRoots.hasNext()) {
            viewRoots.add(semanticToView.get(iterSemanticRoots.next()));
        }
        return viewRoots;
    }

    @Override
    public List<View> getRoots(final List<View> views) {
        // FIXME YMO doesn't work fine if there are many views that have the
        // same target.
        final List<EObject> semantics = new ArrayList<>(views.size());
        //
        // Keep a trace semantic -> view.
        final Map<EObject, View> semanticToView = new HashMap<>();
        final Iterator<View> iterViews = views.iterator();
        //
        // Find all semantic elements.
        while (iterViews.hasNext()) {
            final View currentView = iterViews.next();
            // Only Nodes are able to compose a tree.
            if (currentView instanceof Node) {
                final EObject semanticElement = this.resolveSemanticElement(currentView);
                if (semanticElement != null) {
                    semantics.add(semanticElement);
                    semanticToView.put(semanticElement, currentView);
                }
            }
        }
        //
        // Gets roots.
        final List<EObject> semanticRoots = this.getSemanticRoots(semantics);
        final List<View> viewRoots = new ArrayList<>(semanticRoots.size());
        //
        // Gets view roots.
        final Iterator<EObject> iterSemanticRoots = semanticRoots.iterator();
        while (iterSemanticRoots.hasNext()) {
            viewRoots.add(semanticToView.get(iterSemanticRoots.next()));
        }
        return viewRoots;
    }

    /**
     * Return the semantic elements that are the roots of the tree.
     * 
     * @param eObjects
     *            the semantic elements that are on the diagram.
     * @return the semantic elements that are the roots of the tree.
     */
    public abstract List<EObject> getSemanticRoots(List<EObject> eObjects);

    /**
     * Return the semantic elements that are the children of
     * <code>semanticParent</code>.
     * 
     * @param semanticParent
     *            the semantic parent.
     * @param candidates
     *            all semantic candidates.
     * @return the semantic elements that are the children of
     *         <code>semanticParent</code>.
     */
    public abstract List<EObject> getSemanticChildren(EObject semanticParent, List<EObject> candidates);

    /**
     * Resolves the real semantic element of the specified GMF view.
     * 
     * @param gmfView
     *            the GMF view.
     * @return the real semantic element of the specified GMF view.
     */
    protected EObject resolveSemanticElement(final View gmfView) {
        EObject semanticElement = null;
        final EObject semanticView = ViewUtil.resolveSemanticElement(gmfView);
        if (semanticView instanceof DSemanticDecorator) {
            semanticElement = ((DSemanticDecorator) semanticView).getTarget();
        }
        return semanticElement;
    }

}
