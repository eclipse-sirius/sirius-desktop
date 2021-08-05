/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.layout.ordering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.AbstractTreeViewOrdering;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Ordering for an ordered tree.
 * 
 * @author mchauvin
 */
public class OrderedTreeOrdering extends AbstractTreeViewOrdering {

    private Collection<String> domainClasses;

    private OrderedTreeLayout layout;

    private Map<View, List<Node>> map;

    /**
     * Default constructor.
     * 
     * @param layout
     *            the ordered tree layout
     */
    public OrderedTreeOrdering(final OrderedTreeLayout layout) {
        this.layout = layout;
        this.domainClasses = getDomainClasses();
        map = new WeakHashMap<View, List<Node>>();
    }

    private Collection<String> getDomainClasses() {
        final Collection<String> classes = new ArrayList<String>();
        for (AbstractNodeMapping mapping : layout.getNodeMapping()) {
            if (!StringUtil.isEmpty(mapping.getDomainClass())) {
                classes.add(mapping.getDomainClass());
            }
        }
        return classes;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.common.ui.business.api.layout.AbstractTreeViewOrdering#getRoots(java.util.List)
     */
    @Override
    public List<View> getRoots(final List<View> views) {
        final List<View> result = new LinkedList<View>(views);
        final Iterator<View> iterViews = views.iterator();
        while (iterViews.hasNext()) {
            final View currentView = iterViews.next();
            map.put(currentView, computeChildren(currentView, views));
            for (Node node : map.get(currentView)) {
                result.remove(node);
            }
        }

        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.business.api.layout.AbstractTreeViewOrdering#clear()
     */
    @Override
    protected void clear() {
        this.map.clear();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.common.ui.business.api.layout.AbstractTreeViewOrdering#getChildren(org.eclipse.gmf.runtime.notation.View,
     *      java.util.List)
     */
    @Override
    public List<View> getChildren(final View parent, final List<View> views) {
        if (map.containsKey(parent)) {
            return (List) map.get(parent);
        }
        return Collections.<View> emptyList();
    }

    /**
     * Returns all children of the parent view.
     * 
     * @param parent
     *            the parent view.
     * @param views
     *            the candidates.
     * @return all children of the parent view.
     */
    public List<Node> computeChildren(final View parent, final List<View> views) {

        final List<Node> result = new LinkedList<Node>();

        if (parent.getElement() instanceof DSemanticDecorator) {
            final EObject element = ((DSemanticDecorator) parent.getElement()).getTarget();
            if (element != null && element.eResource() != null) {
                // The element could be null in case of diagram that is not in
                // refreshAuto mode and that have a semantic element deleted.
                final ModelAccessor accesor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(element);

                if (accesor != null && isInstanceOfOneDomainClass(element, accesor)) {

                    final IInterpreter interpreter = InterpreterUtil.getInterpreter(element);
                    final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);

                    final Collection<EObject> acceleoResult = safeInterpreter.evaluateCollection(element, layout, DescriptionPackage.eINSTANCE.getOrderedTreeLayout_ChildrenExpression());
                    final List<EObject> children = new ArrayList<EObject>(acceleoResult);
                    result.addAll(getChildrenNode(views, children));

                }
            }
        }

        return result;
    }

    private List<Node> getChildrenNode(final List<View> views, final List<EObject> children) {
        final List<Node> childrenNodes = new LinkedList<Node>();
        final Iterator<EObject> iterChildren = children.iterator();
        while (iterChildren.hasNext()) {
            final EObject child = iterChildren.next();
            final Iterator<View> iterViews = views.iterator();
            while (iterViews.hasNext()) {
                final View view = iterViews.next();
                if (view instanceof Node && view.getElement() instanceof DSemanticDecorator) {
                    final EObject currentSemantic = ((DSemanticDecorator) view.getElement()).getTarget();
                    if (currentSemantic.equals(child)) {
                        childrenNodes.add((Node) view);
                        break;
                    }
                }
            }
        }
        return childrenNodes;
    }

    private boolean isInstanceOfOneDomainClass(final EObject element, final ModelAccessor accessor) {
        boolean isInstance = false;
        final Iterator<String> it = domainClasses.iterator();
        while (it.hasNext() && !isInstance) {
            isInstance = accessor.eInstanceOf(element, it.next());
        }

        return isInstance;
    }

}
