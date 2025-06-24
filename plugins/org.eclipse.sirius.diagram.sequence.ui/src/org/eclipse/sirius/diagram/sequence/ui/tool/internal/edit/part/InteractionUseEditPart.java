/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Gate;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.FrameMapping;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ExecutionOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.InteractionUseResizableEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceNodeCreationPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.GateItemLocator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceNoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools.RubberbandDragTracker;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.swt.SWT;

/**
 * Special edit part for interaction uses.
 * 
 * @author pcdavid, smonnier
 * 
 */
public class InteractionUseEditPart extends DNodeContainerEditPart implements ISequenceEventEditPart {
    /**
     * The centered label.
     */
    private SiriusWrapLabel fExpressionLabelFigure;

    /**
     * Post commit listener to refresh the centered label on targeted interaction change.
     */
    private NotificationListener usedInteractionLabelUpdater = new NotificationListener() {

        @Override
        public void notifyChanged(Notification notification) {
            if (!notification.isTouch()) {
                refreshUsedInteractionLabel();
            }
        }
    };

    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public InteractionUseEditPart(View view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNotify() {
        SequenceEditPartsOperations.registerDiagramElement(this, resolveDiagramElement());
        super.addNotify();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeNotify() {
        super.removeNotify();
        SequenceEditPartsOperations.unregisterDiagramElement(this, resolveDiagramElement());
    }

    /**
     * Overridden to install a specific edit policy managing the moving and resizing requests on lifelines.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (EditPolicy.PRIMARY_DRAG_ROLE.equals(key)) {
            super.installEditPolicy(key, new InteractionUseResizableEditPolicy());
        } else {
            super.installEditPolicy(key, editPolicy);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        ExecutionOperations.replaceEditPolicy(this, EditPolicy.CONTAINER_ROLE, new SequenceNodeCreationPolicy(), NodeCreationEditPolicy.class);

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    /**
     * Computes the center label expression.
     * 
     * @return the center label expression
     */
    protected String computeCenterLabelExpression() {
        String centeredLabelText = ""; //$NON-NLS-1$
        if (resolveSemanticElement() instanceof DNodeContainer) {
            DNodeContainer dNodeContainer = (DNodeContainer) resolveSemanticElement();
            if (dNodeContainer.eContainer() != null) {
                ContainerMapping actualMapping = dNodeContainer.getActualMapping();
                if (actualMapping instanceof FrameMapping) {
                    FrameMapping frameMapping = (FrameMapping) actualMapping;
                    String centerLabelExpression = frameMapping.getCenterLabelExpression();
                    EObject eObject = dNodeContainer.getTarget();
                    try {
                        centeredLabelText = evaluationExpression(eObject, centerLabelExpression);
                    } catch (EvaluationException e) {
                        RuntimeLoggerManager.INSTANCE.error(frameMapping, DescriptionPackage.eINSTANCE.getFrameMapping_CenterLabelExpression(), e);
                    }
                }
            }
        }
        return centeredLabelText;
    }

    private String evaluationExpression(EObject self, String expression) throws EvaluationException {
        IInterpreter interpreter = null;
        if (self != null) {
            interpreter = InterpreterUtil.getInterpreter(self);
            if (interpreter != null) {
                return interpreter.evaluateString(self, expression);
            }
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * Refresh the centered label text.
     */
    private void refreshUsedInteractionLabel() {
        if (fExpressionLabelFigure != null) {
            fExpressionLabelFigure.setText(computeCenterLabelExpression());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Overridden to add a post commit notification listener on all semantic elements for the centered label refresh.
     */
    @Override
    public void activate() {
        super.activate();

        DiagramEventBroker broker = null;
        final TransactionalEditingDomain theEditingDomain = getEditingDomain();
        if (theEditingDomain != null) {
            broker = DiagramEventBroker.getInstance(theEditingDomain);
        }

        if (broker != null) {
            final Iterator<EObject> iterSemanticElements = resolveAllSemanticElements().iterator();
            while (iterSemanticElements.hasNext()) {
                final EObject semantic = iterSemanticElements.next();
                broker.addNotificationListener(semantic, usedInteractionLabelUpdater);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Overridden to remove the post commit notification listener on all semantic elements for the centered label
     * refresh.
     */
    @Override
    public void deactivate() {
        super.deactivate();

        DiagramEventBroker broker = null;
        final TransactionalEditingDomain theEditingDomain = getEditingDomain();
        if (theEditingDomain != null) {
            broker = DiagramEventBroker.getInstance(theEditingDomain);
        }

        if (broker != null) {
            final Iterator<EObject> iterSemanticElements = resolveAllSemanticElements().iterator();
            while (iterSemanticElements.hasNext()) {
                final EObject semantic = iterSemanticElements.next();
                broker.removeNotificationListener(semantic, usedInteractionLabelUpdater);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISequenceEvent getISequenceEvent() {
        return ISequenceElementAccessor.getInteractionUse(getNotationView()).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NodeFigure createMainFigure() {
        NodeFigure figure = super.createMainFigure();

        if (figure instanceof DefaultSizeNodeFigure) {
            final EObject eObj = this.resolveSemanticElement();
            if (eObj instanceof DDiagramElementContainer) {
                final DDiagramElementContainer container = (DDiagramElementContainer) eObj;
                if (container.getOwnedStyle() instanceof FlatContainerStyle) {
                    ((DefaultSizeNodeFigure) figure).setDefaultSize(LayoutConstants.DEFAULT_INTERACTION_USE_WIDTH, LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT);
                }
            }
        }

        // Add a centered label on the figure
        fExpressionLabelFigure = new SiriusWrapLabel();
        refreshUsedInteractionLabel();
        fExpressionLabelFigure.setTextWrap(true);
        fExpressionLabelFigure.setLabelAlignment(SWT.CENTER);
        figure.add(fExpressionLabelFigure);

        return figure;
    }
    @Override
    public DragTracker getDragTracker(Request request) {
        if (request instanceof SelectionRequest && ((SelectionRequest) request).isAltKeyPressed()) {
            return new RubberbandDragTracker();
        }
        return new SequenceNoCopyDragEditPartsTrackerEx(this);
    }
    

    @Override
    public IBorderItemLocator createBorderItemLocator(IFigure figure, DDiagramElement vpElementBorderItem) {
        if (Gate.viewpointElementPredicate().apply(vpElementBorderItem)) {
            return new GateItemLocator(this, figure);
        } else {
            return super.createBorderItemLocator(figure, vpElementBorderItem);
        }
    }

}
