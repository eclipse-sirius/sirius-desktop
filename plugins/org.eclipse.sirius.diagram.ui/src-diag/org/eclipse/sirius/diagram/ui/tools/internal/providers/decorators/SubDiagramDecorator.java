/*******************************************************************************
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.providers.decorators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ui.tools.api.profiler.SiriusTasks;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.swt.graphics.Image;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Decorator showing a diagram icon.
 * 
 * @author cbrun
 */
public class SubDiagramDecorator extends AbstractDecorator {

    private static final ProfilerTask DECORATOR_REFRESH = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, Messages.SubDiagramDecorator_taskName, SiriusTasks.IMAGES_VIEWPOINT);

    private Session session;

    /**
     * Create a decorator.
     * 
     * @param decoratorTarget
     *            target to decorate.
     */
    public SubDiagramDecorator(IDecoratorTarget decoratorTarget) {
        super(decoratorTarget);
        View view = (View) getDecoratorTarget().getAdapter(View.class);
        DRepresentationElement model = (DRepresentationElement) view.getElement();
        this.session = SessionManager.INSTANCE.getSession(model.getTarget());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate() {
        // Nothing to do.
    }

    @Override
    public void deactivate() {
        super.deactivate();
        session = null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator#refresh()
     */
    @Override
    public void refresh() {
        DslCommonPlugin.PROFILER.startWork(DECORATOR_REFRESH);
        removeDecoration();
        View view = (View) getDecoratorTarget().getAdapter(View.class);
        if (view != null && view.eResource() != null) {
            EditPart editPart = (EditPart) getDecoratorTarget().getAdapter(EditPart.class);
            if (editPart == null || editPart.getParent() == null || editPart.getViewer() == null) {
                return;
            }
            if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
                IFigure figure = ((org.eclipse.gef.GraphicalEditPart) editPart).getFigure();
                Dimension size = figure.getSize();
                if (size.width <= 30 && size.width > 0 && size.height <= 30 && size.height > 0) {
                    return;
                }
            }
            EObject model = view.getElement();
            if (model instanceof DRepresentationElement) {
                DRepresentationElement node = (DRepresentationElement) model;
                if (shouldHaveSubDiagDecoration(node)) {

                    /* leave a chance to display port */
                    int margin = -((IBorderItemOffsets.DEFAULT_OFFSET.width / 2) + 1);
                    if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
                        margin = MapModeUtil.getMapMode(((org.eclipse.gef.GraphicalEditPart) editPart).getFigure()).DPtoLP(margin);
                    }

                    setDecoration(getDecoratorTarget().addShapeDecoration(getSubDiagramImage(), IDecoratorTarget.Direction.SOUTH_EAST, margin, false));

                }
            }
        }
        DslCommonPlugin.PROFILER.stopWork(DECORATOR_REFRESH);
    }

    private Image getSubDiagramImage() {
        return WorkspaceImageFigure.flyWeightImage(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.HAS_DIAG_IMG));
    }

    private boolean shouldHaveSubDiagDecoration(DRepresentationElement node) {
        EObject target = node.getTarget();
        boolean shouldHaveSubDiagramDecorator = false;
        if (target != null && target.eResource() != null) {

            if (session != null && !parentHasSameSemanticElement(node)) {
                // Does the target element has any representation on it? Exclude
                // the current representation itself to avoid redundant markers.
                DRepresentation representation = new DRepresentationElementQuery(node).getParentRepresentation();
                Predicate<DRepresentation> otherReperesentation = Predicates.not(Predicates.equalTo(representation));
                shouldHaveSubDiagramDecorator = Iterables.any(DialectManager.INSTANCE.getRepresentations(target, session), otherReperesentation);
                if (node.getMapping() != null && !shouldHaveSubDiagramDecorator) {
                    shouldHaveSubDiagramDecorator = checkRepresentationNavigationDescriptions(node);
                }
            }
        }
        return shouldHaveSubDiagramDecorator;
    }

    /**
     * Tests whether the specified node has the same semantic element as its
     * parent.
     */
    private boolean parentHasSameSemanticElement(DRepresentationElement element) {
        return (element.eContainer() instanceof DDiagramElement) && ((DDiagramElement) element.eContainer()).getTarget() == element.getTarget();
    }

    private boolean checkRepresentationNavigationDescriptions(DRepresentationElement element) {

        EObject target = element.getTarget();
        if (session.isOpen()) {

            IInterpreter interpreter = session.getInterpreter();

            for (RepresentationNavigationDescription navDesc : element.getMapping().getNavigationDescriptions()) {
                if (isFromActiveViewpoint(navDesc.getRepresentationDescription())) {
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
                        if (checkRepresentationNavigationDescription(interpreter, navDesc, element)) {
                            return true;
                        }
                    }

                    interpreter.unSetVariable(navDesc.getContainerVariable().getName());
                    interpreter.unSetVariable(navDesc.getContainerViewVariable().getName());
                }
            }
        }
        return false;
    }

    private boolean isFromActiveViewpoint(final RepresentationDescription description) {
        final Viewpoint vp = ViewpointRegistry.getInstance().getViewpoint(description);
        return vp != null && session.getSelectedViewpoints(false).contains(vp);
    }

    private boolean checkRepresentationNavigationDescription(IInterpreter interpreter, RepresentationNavigationDescription navDesc, DRepresentationElement element) {
        Collection<EObject> candidates = null;
        if (!StringUtil.isEmpty(navDesc.getBrowseExpression())) {
            candidates = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(element.getTarget(), navDesc,
                    ToolPackage.eINSTANCE.getRepresentationNavigationDescription_BrowseExpression());
        } else {
            candidates = new ArrayList<EObject>();
            Iterator<EObject> it = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(element.getTarget()).eAllContents(element.getTarget());
            while (it.hasNext()) {
                candidates.add(it.next());
            }
        }

        for (EObject candidate : candidates) {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(candidate, session);
            for (DRepresentation representation : representations) {
                if (navDesc.getRepresentationDescription() != null && navDesc.getRepresentationDescription().equals(DialectManager.INSTANCE.getDescription(representation))) {
                    return true;
                }
            }
        }

        return false;
    }
}
