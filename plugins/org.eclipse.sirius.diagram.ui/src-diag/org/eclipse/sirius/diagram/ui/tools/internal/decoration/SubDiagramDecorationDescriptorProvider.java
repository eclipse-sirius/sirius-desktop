/*******************************************************************************
 * Copyright (c) 2017, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.decoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.representation.DRepresentationChangeListener.NoSubDecorationDescriptor;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.DecorationDescriptor;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.DecorationDescriptor.DisplayPriority;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.SiriusDecorationDescriptorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.DecorationDistributionDirection;
import org.eclipse.sirius.viewpoint.description.Position;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.swt.graphics.Image;

/**
 * This {@link SiriusDecorationDescriptorProvider} provides a decoration on the bottom right corner when the element
 * provides a detail diagram.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SubDiagramDecorationDescriptorProvider implements SiriusDecorationDescriptorProvider {

    private static final String NAME = "subDiagramStatus"; //$NON-NLS-1$

    @Override
    public boolean provides(IDiagramElementEditPart editPart) {
        try {
            if (editPart instanceof GraphicalEditPart || editPart instanceof AbstractConnectionEditPart) {
                Optional<View> view = Optional.ofNullable((View) editPart.getModel());
                return view.filter(View::isSetElement).map(View::getElement).filter(model -> model instanceof DNode || model instanceof DDiagramElementContainer).isPresent();
            }
        } catch (IllegalStateException e) {
            // Nothing to log here, this can happen if the resource is not accessible anymore (distant resource).
        }
        return false;
    }

    @Override
    public List<DecorationDescriptor> getDecorationDescriptors(IDiagramElementEditPart editPart, Session session) {
        List<DecorationDescriptor> results = new ArrayList<>();
        EObject model = ((View) editPart.getModel()).getElement();
        if (model instanceof DRepresentationElement) {
            DRepresentationElement node = (DRepresentationElement) model;
            DRepresentationElementQuery dRepresentationElementQuery = new DRepresentationElementQuery(node);
            DRepresentation parentRepresentation = dRepresentationElementQuery.getParentRepresentation();
            Object decorationDescriptor = null;
            Map<Object, Object> subDiagramDecorationDescriptors = null;
            if (parentRepresentation != null) {
                subDiagramDecorationDescriptors = parentRepresentation.getUiState().getSubDiagramDecorationDescriptors();
                decorationDescriptor = subDiagramDecorationDescriptors.get(node);

                if (decorationDescriptor instanceof DecorationDescriptor) {
                    results = Arrays.asList((DecorationDescriptor) decorationDescriptor);
                } else if (decorationDescriptor instanceof NoSubDecorationDescriptor) {
                    // Do nothing, evaluation already done in a previous decoration refresh : no found other
                    // representation on the semantic element nor navigation tools with existing target.
                    // See
                    // org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl.DRepresentationChangeListener
                    // for invalidation.
                } else if (shouldHaveSubDiagDecoration(node, parentRepresentation, session)) {
                    DecorationDescriptor decoDesc = new DecorationDescriptor();
                    decoDesc.setName(NAME);
                    decoDesc.setPosition(Position.SOUTH_EAST_LITERAL);
                    decoDesc.setDistributionDirection(DecorationDistributionDirection.HORIZONTAL);
                    decoDesc.setDisplayPriority(DisplayPriority.HIGH_PRIORITY.getValue());
                    decoDesc.setDecorationAsImage(getSubDiagramImage());
                    subDiagramDecorationDescriptors.put(node, decoDesc);
                    results = Arrays.asList(decoDesc);
                } else {
                    subDiagramDecorationDescriptors.put(node, new NoSubDecorationDescriptor());
                }
            }
        }

        return results;
    }

    private Image getSubDiagramImage() {
        return WorkspaceImageFigure.flyWeightImage(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.HAS_DIAG_IMG));
    }

    private boolean shouldHaveSubDiagDecoration(DRepresentationElement node, DRepresentation parentRepresentation, Session session) {
        Map<EObject, Collection<DRepresentationDescriptor>> knownRepDescriptors = new ConcurrentHashMap<>();
        EObject target = node.getTarget();
        boolean shouldHaveSubDiagramDecorator = false;
        if (target != null && target.eResource() != null) {
            if (session != null && !parentHasSameSemanticElement(node, target)) {
                shouldHaveSubDiagramDecorator = checkExistingRepresentationDescriptors(target, node, parentRepresentation, session, knownRepDescriptors);
                if (node.getMapping() != null && !shouldHaveSubDiagramDecorator) {
                    shouldHaveSubDiagramDecorator = checkRepresentationNavigationDescriptions(target, node, session, knownRepDescriptors);
                }
            }
        }
        knownRepDescriptors.clear();
        return shouldHaveSubDiagramDecorator;
    }

    /**
     * Check if an existing {@link DRepresentationDescriptor} as the node target element as target element.
     * 
     * @param knownRepDescriptors
     * 
     * @return the value
     */
    private boolean checkExistingRepresentationDescriptors(EObject semanticObject, DRepresentationElement node, DRepresentation parentRepresentation, Session session,
            Map<EObject, Collection<DRepresentationDescriptor>> knownRepDescriptors) {
        // Does the target element has any representation on it? Exclude
        // the current representation itself to avoid redundant markers.
        DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(parentRepresentation, session).getRepresentationDescriptor();

        return getRepresentationDescriptors(session, semanticObject, knownRepDescriptors).stream().filter(repDesc -> !Objects.equals(repDesc, representationDescriptor)).count() > 0;
    }

    private Collection<DRepresentationDescriptor> getRepresentationDescriptors(Session session, EObject semanticObject, Map<EObject, Collection<DRepresentationDescriptor>> knownRepDescriptors) {
        Collection<DRepresentationDescriptor> repDescs = knownRepDescriptors.get(semanticObject);
        if (repDescs == null) {
            repDescs = DialectManager.INSTANCE.getRepresentationDescriptors(semanticObject, session);
            knownRepDescriptors.put(semanticObject, repDescs);
        }
        return repDescs;
    }

    /**
     * Tests whether the specified node has the same semantic element as its parent.
     * 
     * @param target
     */
    private boolean parentHasSameSemanticElement(DRepresentationElement element, EObject target) {
        EObject eContainer = element.eContainer();
        return (eContainer instanceof DDiagramElement) && ((DDiagramElement) eContainer).getTarget() == target;
    }

    private boolean checkRepresentationNavigationDescriptions(EObject target, DRepresentationElement element, Session session,
            Map<EObject, Collection<DRepresentationDescriptor>> knownRepDescriptors) {
        boolean isAnyRepresentation = false;
        if (session.isOpen()) {

            IInterpreter interpreter = session.getInterpreter();

            Iterator<RepresentationNavigationDescription> navDescIterator = element.getMapping().getNavigationDescriptions().iterator();
            while (!isAnyRepresentation && navDescIterator.hasNext()) {
                RepresentationNavigationDescription navDesc = navDescIterator.next();

                if (isFromActiveViewpoint(navDesc.getRepresentationDescription(), session)) {
                    interpreter.setVariable(navDesc.getContainerVariable().getName(), target);
                    interpreter.setVariable(navDesc.getContainerViewVariable().getName(), element);

                    boolean precondition = true;
                    if (!StringUtil.isEmpty(navDesc.getPrecondition())) {
                        try {
                            precondition = interpreter.evaluateBoolean(target, navDesc.getPrecondition());
                        } catch (EvaluationException e) {
                            RuntimeLoggerManager.INSTANCE.error(navDesc, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
                        }
                    }

                    if (precondition) {
                        isAnyRepresentation = checkRepresentationNavigationDescription(interpreter, navDesc, target, element, session, knownRepDescriptors);
                    }

                    interpreter.unSetVariable(navDesc.getContainerVariable().getName());
                    interpreter.unSetVariable(navDesc.getContainerViewVariable().getName());
                }
            }
        }
        return isAnyRepresentation;
    }

    private boolean isFromActiveViewpoint(final RepresentationDescription description, Session session) {
        final Viewpoint vp = ViewpointRegistry.getInstance().getViewpoint(description);
        return vp != null && session.getSelectedViewpoints(false).contains(vp);
    }

    private boolean checkRepresentationNavigationDescription(IInterpreter interpreter, RepresentationNavigationDescription navDesc, EObject target, DRepresentationElement element, Session session,
            Map<EObject, Collection<DRepresentationDescriptor>> knownRepDescriptors) {
        Collection<EObject> candidates = new ArrayList<EObject>();

        if (!StringUtil.isEmpty(navDesc.getBrowseExpression())) {
            candidates.addAll(RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(target, navDesc, ToolPackage.eINSTANCE.getRepresentationNavigationDescription_BrowseExpression()));
        } else {
            Iterator<EObject> it = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(target).eAllContents(target);
            while (it.hasNext()) {
                candidates.add(it.next());
            }
        }
        long count = 0;
        for (EObject candidate : candidates) {
            count += getRepresentationDescriptors(session, candidate, knownRepDescriptors).stream().filter(repDesc -> {
                return repDesc.getDescription().equals(navDesc.getRepresentationDescription());
            }).count();
        }
        return count > 0;
    }

    @Override
    public void activate(IDecoratorTarget decoratorTarget, IDecorator decorator, org.eclipse.gef.GraphicalEditPart editPart) {
        // do nothing
    }

    @Override
    public void deactivate(IDecorator decorator, org.eclipse.gef.GraphicalEditPart editPart) {
        // do nothing
    }
}
