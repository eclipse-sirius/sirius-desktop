/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.spec;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.EdgeMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DSemanticDiagramHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramElementSpecOperations;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.impl.DEdgeImpl;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.Style;

import com.google.common.collect.Lists;

/**
 * Implementation of DEdgeImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public class DEdgeSpec extends DEdgeImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DEdgeImpl#getMapping()
     */
    @Override
    public DiagramElementMapping getMapping() {
        return new IEdgeMappingQuery(getActualMapping()).getEdgeMapping().get();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DEdgeImpl#refresh()
     */
    @Override
    public void refresh() {
        Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(getActualMapping()).getEdgeMapping();
        if (edgeMapping.some()) {
            edgeMapping.get().updateEdge(this);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DEdgeImpl#getStyle()
     */
    @Override
    public Style getStyle() {
        return getOwnedStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementImpl#getParentDiagram()
     */
    @Override
    public DDiagram getParentDiagram() {
        return DDiagramElementSpecOperations.getParentDiagram(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DEdgeImpl#isFold(java.util.Map)
     */
    @Override
    @Deprecated
    public boolean isFold(final Map<?, ?> alreadyManagedElements) {
        throw new UnsupportedOperationException("Deprecated method.");
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DEdgeImpl#validate()
     */
    @Override
    public boolean validate() {
        boolean result = true;
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.VALIDATE_EDGE_KEY);
        if (getActualMapping() != null) {
            if (cantFindSemanticTargetFromSemanticSource()) {
                result = false;
            }
        }

        EObject root = null;
        final DSemanticDiagram diagram;
        if (getParentDiagram() instanceof DSemanticDiagram) {
            diagram = (DSemanticDiagram) getParentDiagram();
            root = DSemanticDiagramHelper.getRootContent(diagram);
        } else {
            diagram = null;
        }

        if (diagram != null && root != null && this.getActualMapping() != null) {
            final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(root);

            // semantic candidates.
            Option<EdgeMapping> edgeMappingOption = new IEdgeMappingQuery(getActualMapping()).getEdgeMapping();
            if (edgeMappingOption.some() && edgeMappingOption.get().isUseDomainElement()) {
                final Collection<EObject> candidates = this.getSemanticCandidates(interpreter, root, diagram);
                if (!candidates.contains(this.getTarget())) {
                    DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.VALIDATE_EDGE_KEY);
                    return false;
                }
            }

            // precondition.
            if (edgeMappingOption.some() && getSourceNode() instanceof DSemanticDecorator && getTargetNode() instanceof DSemanticDecorator) {
                EdgeMapping edgeMapping = edgeMappingOption.get();
                EdgeMappingQuery edgeMappingQuery = new EdgeMappingQuery(edgeMapping);
                result = result && edgeMappingQuery.evaluatePrecondition(diagram, diagram, interpreter, getTarget(), (DSemanticDecorator) getSourceNode(), (DSemanticDecorator) getTargetNode());
            }
        }

        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.VALIDATE_EDGE_KEY);
        return result;
    }

    private boolean cantFindSemanticTargetFromSemanticSource() {

        DDiagram vp = null;
        boolean result = false;

        final EObject srcNode = getSourceNode();

        if (srcNode instanceof DDiagramElement) {
            vp = ((DDiagramElement) srcNode).getParentDiagram();
        }

        Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(getActualMapping()).getEdgeMapping();
        if (edgeMapping.some() && srcNode instanceof DSemanticDecorator) {
            final EObject tgNode = getTargetNode();
            if (tgNode instanceof DSemanticDecorator) {
                final EObject semanticSource = ((DSemanticDecorator) srcNode).getTarget();
                final EObject semanticTarget = ((DSemanticDecorator) tgNode).getTarget();

                result = !findTargetFromSource(edgeMapping.get(), vp, semanticSource, semanticTarget);
            }
        }
        return result;
    }

    private boolean findTargetFromSource(final EdgeMapping mapping, final DDiagram vp, final EObject source, final EObject target) {

        boolean result = false;

        if (mapping.isUseDomainElement()) {
            final EObject edgeTarget = getTarget();
            if (mapping.getEdgeTargetCandidates(edgeTarget, vp).contains(target)) {
                if (mapping.getEdgeSourceCandidates(edgeTarget, vp).contains(source)) {
                    result = true;
                }
            }
        } else {
            if (mapping.getEdgeTargetCandidates(source, vp).contains(target)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DEdgeImpl#isRootFolding()
     */
    @Override
    @Deprecated
    public boolean isRootFolding() {
        throw new UnsupportedOperationException("Deprecated method.");
    }

    /**
     * 
     * @param model
     * @param diagram
     * @return
     */
    private Collection<EObject> getSemanticCandidates(final IInterpreter interpreter, final EObject model, final DDiagram diagram) {
        Collection<EObject> semanticCandidates = null;
        final Option<EdgeMapping> actualEdgeMappingOption = new IEdgeMappingQuery(getActualMapping()).getEdgeMapping();
        if (actualEdgeMappingOption.some()) {
            EdgeMapping actualEdgeMapping = actualEdgeMappingOption.get();
            if (actualEdgeMapping.isUseDomainElement() && actualEdgeMapping.getSemanticCandidatesExpression() != null
                    && !StringUtil.isEmpty(actualEdgeMapping.getSemanticCandidatesExpression().trim())) {
                interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
                interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT_2, diagram);
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
                interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, diagram);
                final EObject context = model;
                try {
                    semanticCandidates = interpreter.evaluateCollection(context, actualEdgeMapping.getSemanticCandidatesExpression());
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(getActualMapping(), DescriptionPackage.eINSTANCE.getDiagramElementMapping_SemanticCandidatesExpression(), e);
                } finally {
                    interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT_2);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
                }
            } else {
                final ModelAccessor extPackage = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(model);

                semanticCandidates = Lists.newArrayList();
                final Session session = SessionManager.INSTANCE.getSession(model);
                for (final Resource resource : session.getSemanticResources()) {
                    for (final EObject root : resource.getContents()) {
                        semanticCandidates.addAll(extPackage.eAllContents(root, "EObject"));
                    }
                }
            }
        }
        if (semanticCandidates == null) {
            semanticCandidates = Collections.emptySet();
        }
        return semanticCandidates;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (getTargetNode() != null && getSourceNode() != null) {
            return "Edge source:" + getSourceNode() + " | target:" + getTargetNode() + " on semantic" + getTarget();
        } else {
            return super.toString();
        }
    }

    /**
     * Overridden because of
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=89325. {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DEdgeImpl#getPath()
     */
    @SuppressWarnings("serial")
    @Override
    public EList<EdgeTarget> getPath() {
        if (path == null) {
            path = new EObjectResolvingEList<EdgeTarget>(EdgeTarget.class, this, DiagramPackage.DEDGE__PATH) {
                /**
                 * {@inheritDoc}
                 */
                @Override
                protected boolean isUnique() {
                    return false;
                }

            };
        }
        return path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eNotify(Notification notification) {
        if (notification.getEventType() == Notification.SET && notification.getFeatureID(DEdge.class) == DiagramPackage.DEDGE__ACTUAL_MAPPING) {
            if (notification.getOldValue() instanceof EdgeMappingImport && EdgeMappingImportWrapper.getWrapper((EdgeMappingImport) notification.getOldValue()) == notification.getNewValue()) {
                // silently replace the EdgeMappingImport by its corresponding
                // wrapper : occurs only once on the first refresh after load.
                // During save, the mapping s serialized, not the wrapper.
                return;
            }
        }
        super.eNotify(notification);
    }
}
