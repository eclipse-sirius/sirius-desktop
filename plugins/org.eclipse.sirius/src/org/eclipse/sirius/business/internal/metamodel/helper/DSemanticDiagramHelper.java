/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.SiriusHelper;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.business.internal.helper.refresh.EdgeFilter;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tools.api.command.semantic.RemoveDanglingReferences;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tools.api.validation.constraint.AbstractDDiagramConstraint;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Utility class to factor customizations for DSemanticDiagram and related.
 * 
 * @author pcdavid
 */
public final class DSemanticDiagramHelper {
    private DSemanticDiagramHelper() {
        // Prevent instantiation.
    }

    /**
     * Clean the viewpoint, delete all elements that are obsolete.
     * 
     * @param self
     *            the semantic diagram.
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#clean()
     */
    public static void clean(DSemanticDiagram self) {
        AbstractDDiagramConstraint.deactivate();
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.RESOLVE_ALL_KEY);
        ModelUtils.resolveAll(self);
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.RESOLVE_ALL_KEY);
        final Iterator<EObject> it = self.eAllContents();
        while (it.hasNext()) {
            final EObject next = it.next();
            /*
             * At first we'll remove the possible dangling references in the
             * semantic elements as we don't want these dangling to cause proxy
             * invalidation and then node/container deletion. Especially in
             * diagrams with "created=false" elements.
             */
            if (next instanceof DRepresentationElement) {
                final DRepresentationElement elem = (DRepresentationElement) next;
                SiriusHelper.unSetHarmlessDanglingReferences(elem);
            }
        }
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CLEANING_NODES_KEY);
        DSemanticDiagramHelper.cleanNodes(self);
        DSemanticDiagramHelper.cleanContainers(self);
        DSemanticDiagramHelper.cleanListElements(self);
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CLEANING_NODES_KEY);
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CLEANING_REMOVEDANGLING_KEY);
        RemoveDanglingReferences.removeDanglingReferences(self.getRootContent());
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CLEANING_REMOVEDANGLING_KEY);
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CLEANING_EDGES_KEY);
        DSemanticDiagramHelper.cleanEdges(self);
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CLEANING_EDGES_KEY);
        AbstractDDiagramConstraint.activate();
    }

    /**
     * Finds the element which should be considered as the root of the diagram's
     * content. It may differ from the diagram's target element if a
     * <em>root expression</em> has been defined.
     * 
     * @param self
     *            the diagram.
     * @return the element which should be considered as the root of the
     *         diagram's content.
     */
    public static EObject getRootContent(DSemanticDiagram self) {
        EObject rootContent = self.getTarget();
        if (self.getDescription() != null && self.getDescription().getRootExpression() != null && !StringUtil.isEmpty(self.getDescription().getRootExpression().trim())) {
            final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(self.getTarget());
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, self);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, self);

            try {
                rootContent = interpreter.evaluateEObject(self.getTarget(), self.getDescription().getRootExpression());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(self.getDescription(), DescriptionPackage.eINSTANCE.getDiagramDescription_RootExpression(), e);
            }

            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        }
        return rootContent;
    }

    /**
     * validate proxies of the object
     * 
     * @param eObject
     *            the object
     * @return <code>true</code> if proxies are valid.
     */
    private static boolean validateProxies(final EObject eObject) {
        return ModelUtils.validateProxies(eObject);
    }

    /**
     * return <code>true</code> is the edge is broken (obsolete).
     * 
     * @param edge
     *            the edge.
     * @return <code>true</code> is the edge is broken (obsolete).
     */
    private static boolean isBroken(final DEdge edge) {

        boolean isBroken = false;

        if (!DSemanticDiagramHelper.validateProxies(edge)) {
            isBroken = true;
        } else if (edge.getSourceNode() == null || edge.getSourceNode().eResource() == null) {
            isBroken = true;
        } else if (edge.getTargetNode() == null || edge.getTargetNode().eResource() == null) {
            isBroken = true;
        } else {
            if (edge.getSourceNode() instanceof DSemanticDecorator) {
                if (((DSemanticDecorator) edge.getSourceNode()).getTarget() == null) {
                    isBroken = true;
                } else if (((DSemanticDecorator) edge.getSourceNode()).getTarget().eResource() == null) {
                    isBroken = true;
                }
            }
            if (edge.getTargetNode() instanceof DSemanticDecorator) {
                if (((DSemanticDecorator) edge.getTargetNode()).getTarget() == null) {
                    isBroken = true;
                } else if (((DSemanticDecorator) edge.getTargetNode()).getTarget().eResource() == null) {
                    isBroken = true;
                }
            }
        }
        return isBroken;
    }

    private static void cleanListElements(DSemanticDiagram self) {
        final Set<DNodeListElement> instancesToRemove = new HashSet<DNodeListElement>();
        final Iterator<DDiagramElementContainer> it = self.getContainers().iterator();
        while (it.hasNext()) {
            final DDiagramElementContainer container = it.next();
            if (container instanceof DNodeList) {
                final DNodeList list = (DNodeList) container;
                final Iterator<DNodeListElement> child = list.getOwnedElements().iterator();
                while (child.hasNext()) {
                    final DNodeListElement elem = child.next();
                    if (elem.getTarget() == null) {
                        instancesToRemove.add(elem);
                    } else if (elem.getTarget().eResource() == null) {
                        instancesToRemove.add(elem);
                    }
                    if (!DSemanticDiagramHelper.validateProxies(elem)) {
                        instancesToRemove.add(elem);
                    }

                    if (!elem.validate()) {
                        instancesToRemove.add(elem);
                    }
                }
            }
        }
        final Iterator<DNodeListElement> it2 = instancesToRemove.iterator();
        while (it2.hasNext()) {
            final EObject toRemove = it.next();
            SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(toRemove).eRemove(toRemove);
        }
    }

    /**
     * Clean all nodes that are obsolete.
     */
    private static void cleanNodes(DSemanticDiagram self) {
        final Set<DNode> instancesToRemove = new HashSet<DNode>();
        Iterator<DNode> it = self.getNodes().iterator();
        while (it.hasNext()) {
            final DNode next = it.next();
            final EObject container = next.eContainer();
            if (container instanceof DDiagramElement && !new DDiagramElementQuery((DDiagramElement) container).isIndirectlyHidden() || !(container instanceof DDiagramElement)) {
                /*
                 * First check for resources, unresolved proxies and null values
                 */
                if (((DSemanticDecorator) next).getTarget() == null) {
                    instancesToRemove.add(next);
                } else if (((DSemanticDecorator) next).getTarget().eResource() == null) {
                    instancesToRemove.add(next);
                }
                if (!DSemanticDiagramHelper.validateProxies(next)) {
                    instancesToRemove.add(next);
                }
                if (!instancesToRemove.contains(next)) {
                    /*
                     * Now check for the semantic validation for the remaining
                     * elements
                     */

                    if (!next.validate()) {
                        instancesToRemove.add(next);
                    }
                    if (next instanceof DDiagramElementContainer) {
                        if (!((DDiagramElementContainer) next).validate()) {
                            instancesToRemove.add(next);
                        }
                    }
                }
            }
        }
        it = instancesToRemove.iterator();
        while (it.hasNext()) {
            final DNode toRemove = it.next();
            SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(toRemove).eRemove(toRemove);
        }
    }

    private static void cleanContainers(DSemanticDiagram self) {
        final Set<DDiagramElementContainer> instancesToRemove = new HashSet<DDiagramElementContainer>();
        Iterator<DDiagramElementContainer> it = self.getContainers().iterator();
        while (it.hasNext()) {
            final DDiagramElementContainer next = it.next();
            final EObject container = next.eContainer();
            if (container instanceof DDiagramElement && !new DDiagramElementQuery((DDiagramElement) container).isIndirectlyHidden() || !(container instanceof DDiagramElement)) {
                /*
                 * First check for resources, unresolved proxies and null values
                 */

                if (((DSemanticDecorator) next).getTarget() == null) {
                    instancesToRemove.add(next);
                } else if (((DSemanticDecorator) next).getTarget().eResource() == null) {
                    instancesToRemove.add(next);
                }
                if (!DSemanticDiagramHelper.validateProxies(next)) {
                    instancesToRemove.add(next);
                }

                if (!instancesToRemove.contains(next)) {
                    if (!next.validate()) {
                        instancesToRemove.add(next);
                    }

                }
            }
        }
        it = instancesToRemove.iterator();
        while (it.hasNext()) {
            final EObject toRemove = it.next();
            SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(toRemove).eRemove(toRemove);
        }
    }

    /**
     * Delete all edges that are obsolete.
     */
    private static void cleanEdges(DSemanticDiagram self) {
        final Set<DEdge> instancesToRemove = new HashSet<DEdge>();
        final List<DEdge> edges = self.getEdges();
        Iterator<DEdge> it = edges.iterator();
        while (it.hasNext()) {
            final DEdge next = it.next();
            /*
             * First check for resources, unresolved proxies and null values
             */
            if (((DSemanticDecorator) next).getTarget() == null) {
                instancesToRemove.add(next);
            } else if (((DSemanticDecorator) next).getTarget().eResource() == null) {
                instancesToRemove.add(next);
            }
            if (!DSemanticDiagramHelper.validateProxies(next)) {
                instancesToRemove.add(next);
            }
            if (!new DDiagramElementQuery(next).isIndirectlyHidden()) {
                if (DSemanticDiagramHelper.isBroken(next)) {
                    instancesToRemove.add(next);
                }

            }
        }
        /*
         * Here we will remove potential doublons using the EdgeFilter
         */
        final Collection<EdgeFilter> edgeFilters = new HashSet<EdgeFilter>();
        final Iterator<DEdge> itEdge = edges.iterator();
        while (itEdge.hasNext()) {
            final DEdge edge = itEdge.next();
            if (!instancesToRemove.contains(edge)) {
                final EdgeFilter filter = new EdgeFilter(edge);
                if (edgeFilters.contains(filter)) {
                    instancesToRemove.add(edge);
                } else {
                    edgeFilters.add(filter);
                }
            }

        }
        /*
         * Now check for the semantic validation for the remaining elements
         */
        it = edges.iterator();
        while (it.hasNext()) {
            final DEdge next = it.next();
            if (!instancesToRemove.contains(next)) {
                if (!next.validate()) {
                    instancesToRemove.add(next);
                }
            }
        }

        it = instancesToRemove.iterator();
        while (it.hasNext()) {
            final DEdge toRemove = it.next();
            toRemove.setTargetNode(null);
            toRemove.setSourceNode(null);
            SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(toRemove).eRemove(toRemove);
        }
    }
}
