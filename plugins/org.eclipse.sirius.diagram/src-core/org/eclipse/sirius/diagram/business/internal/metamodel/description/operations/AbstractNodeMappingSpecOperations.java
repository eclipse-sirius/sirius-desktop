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
package org.eclipse.sirius.diagram.business.internal.metamodel.description.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayMode;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayService;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.INodeMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.MappingHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Implementation of AbstractNodeMappingImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public final class AbstractNodeMappingSpecOperations {

    /**
     * Avoid instantiation.
     */
    private AbstractNodeMappingSpecOperations() {
        // empty.
    }

    private static boolean isInstanceOf(final AbstractNodeMapping mapping, final EObject eObj, final String typename) {
        return SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(eObj).eInstanceOf(eObj, typename);
    }

    private static Iterator<EObject> extEAllContents(final AbstractNodeMapping mapping, final EObject eObj) {

        final Collection<EObject> targetCandidates = new ArrayList<>();
        final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(eObj);
        final Session session = SessionManager.INSTANCE.getSession(eObj);
        if (session != null) {
            for (final Resource resource : session.getSemanticResources()) {
                for (final EObject root : resource.getContents()) {
                    targetCandidates.addAll(accessor.eAllContents(root, mapping.getDomainClass()));
                }
            }
        }
        return targetCandidates.iterator();
    }

    /**
     * create bordering nodes.
     * 
     * @param mapping
     *            the mapping that owned border node mappings.
     * @param modelElement
     *            the root model element
     * @param dDiagramElement
     *            the view point element on which to create new bordering nodes
     * @param filterSemantic
     *            a collection of objects from model to exclude from the creation process
     * @param diagram
     *            a {@link DDiagram} instance
     */
    public static void createBorderingNodes(final AbstractNodeMapping mapping, final EObject modelElement, final DDiagramElement dDiagramElement, final Collection filterSemantic,
            final DDiagram diagram) {
        final EObject containerVariable = dDiagramElement.getTarget();
        EObjectQuery eObjectQuery = new EObjectQuery(modelElement);
        Session session = eObjectQuery.getSession();
        final Iterator<NodeMapping> it = MappingHelper.getAllBorderedNodeMappings(mapping).iterator();
        while (it.hasNext()) {
            final NodeMapping borderMapping = it.next();
            if (new DiagramElementMappingQuery(borderMapping).isSynchronizedAndCreateElement(diagram)) {
                final Iterator<EObject> it2 = AbstractNodeMappingSpecOperations.getSemanticIterator(borderMapping, modelElement, diagram, dDiagramElement);
                while (it2.hasNext()) {
                    final EObject eObj = it2.next();
                    if (eObj != null) {
                        final EObjectCouple couple = new EObjectCouple(eObj, borderMapping, RefreshIdsHolder.getOrCreateHolder(diagram));
                        if (AbstractNodeMappingSpecOperations.isInstanceOf(mapping, eObj, borderMapping.getDomainClass())
                                && SiriusElementMappingSpecOperations.checkPrecondition(borderMapping, eObj, modelElement, dDiagramElement) && !filterSemantic.contains(couple)) {
                            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(eObj);
                            final DNode newBorderNode = new NodeMappingHelper(interpreter).createNode((INodeMappingExt) borderMapping, eObj, containerVariable, diagram);
                            if (dDiagramElement instanceof DNode) {
                                ((DNode) dDiagramElement).getOwnedBorderedNodes().add(newBorderNode);
                            } else if (dDiagramElement instanceof DNodeContainer) {
                                ((DNodeContainer) dDiagramElement).getOwnedBorderedNodes().add(newBorderNode);
                            }
                            setInitialVisibility(newBorderNode, diagram, session);
                        }
                    }
                }
            }
        }
    }

    private static Iterator<EObject> getSemanticIterator(final AbstractNodeMapping mapping, final EObject context, final EObject diagram, final EObject containerView) {
        final String expression = mapping.getSemanticCandidatesExpression();
        if (context != null && context.eResource() != null && expression != null && !StringUtil.isEmpty(expression.trim())) {
            final IInterpreter acceleoInterpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(context);
            final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(acceleoInterpreter);

            acceleoInterpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
            acceleoInterpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT_2, diagram);
            acceleoInterpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
            acceleoInterpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, containerView);
            final Collection<EObject> resultNode = safeInterpreter.evaluateCollection(context, mapping, DescriptionPackage.eINSTANCE.getDiagramElementMapping_SemanticCandidatesExpression());
            acceleoInterpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            acceleoInterpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
            acceleoInterpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT_2);
            acceleoInterpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            return resultNode.iterator();
        }
        return AbstractNodeMappingSpecOperations.extEAllContents(mapping, context);
    }

    /**
     * Evaluate the label of the element using the label computation expression.
     * 
     * @param interpreter
     *            the model request interpreter instance
     * @param view
     *            the view
     * @param featureOwner
     *            the object instance which owns the label feature
     * @param featureDescription
     *            the feature which owns the label
     * @return the computed label if the the evaluation succeed, null otherwise
     */
    public static String evaluateLabel(final IInterpreter interpreter, final DSemanticDecorator view, final EObject featureOwner, final EStructuralFeature featureDescription) {
        String label = null;
        final String labelComputationExpression = (String) featureOwner.eGet(featureDescription);
        if (labelComputationExpression != null) {
            try {
                interpreter.setVariable(IInterpreterSiriusVariables.VIEW, view);
                label = interpreter.evaluateString(view.getTarget(), labelComputationExpression);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(featureOwner, featureDescription, e);
                label = IInterpreterMessages.DEFAULT_NAME_ON_FACTORY_EXCEPTION;
            } finally {
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            }
        }
        return label;
    }

    /**
     * Set the initial visibility of {@link DDiagramElement} and of its label.
     * 
     * @param diagramElement
     *            The {@link DDiagramElement}
     * @param diagram
     *            The {@link DDiagram} containing the {@link DDiagramElement}
     * @param session
     *            The session containing the {@link DDiagram}
     */
    public static void setInitialVisibility(final DDiagramElement diagramElement, DDiagram diagram, Session session) {
        final DisplayService service = DisplayServiceManager.INSTANCE.getDisplayService(DisplayMode.CREATION);
        if (service != null && diagramElement != null && diagram != null) {
            DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, diagram);
            diagramElement.setVisible(service.computeVisibility(mappingManager, diagram, diagramElement));
            if (!service.computeLabelVisibility(diagram, diagramElement)) {
                HideFilterHelper.INSTANCE.hideLabel(diagramElement);
            }
        }
    }
}
