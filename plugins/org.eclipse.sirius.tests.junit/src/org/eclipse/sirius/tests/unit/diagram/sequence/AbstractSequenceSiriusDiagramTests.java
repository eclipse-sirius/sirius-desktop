/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.sequence;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.Model;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.junit.Assert;

import com.google.common.base.Objects;

public abstract class AbstractSequenceSiriusDiagramTests extends SiriusDiagramTestCase {

    public static final String UNIT_DATA_ROOT = "/data/sequence/unit/";

    protected DDiagramEditor editorPart;

    protected Diagram diagramView;

    protected SequenceDDiagram sequenceDDiagram;

    protected DiagramEditPart diagramEditPart;

    protected Interaction interaction;

    protected final Point origin = new Point(LayoutConstants.LIFELINES_START_X, LayoutConstants.LIFELINES_START_Y);

    /**
     * Get the path to the models to copy in project to test.
     * 
     * @return the path to the models to copy in project to test
     */
    protected abstract String getPath();

    /**
     * Get the interaction semantic model.
     * 
     * @return the interaction semantic model
     */
    protected abstract String getSemanticModel();

    /**
     * Get the interaction type semantic model.
     * 
     * @return the interaction type semantic model
     */
    protected abstract String getTypesSemanticModel();

    /**
     * Get the .aird model.
     * 
     * @return the .aird model
     */
    protected abstract String getSessionModel();

    /**
     * Get the representation id name to use as defined in the odesign.
     * 
     * @return the representation id name to use as defined in the odesign
     */
    protected abstract String getRepresentationId();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        if (getSessionModel() != null) {
            genericSetUp(getPath() + getSemanticModel(), InteractionsConstants.VSM_PATH, getPath() + getSessionModel());
        } else {
            genericSetUp(getPath() + getSemanticModel(), InteractionsConstants.VSM_PATH);
        }
        if (semanticModel instanceof Model) {
            Model model = (Model) semanticModel;
            interaction = model.getOwnedInteractions().get(0);
        } else if (semanticModel instanceof Interaction) {
            interaction = (Interaction) semanticModel;
        }

        if (getTypesSemanticModel() != null) {
            loadParticipantTypesResource(getPath() + getTypesSemanticModel());
        }
    }

    private void loadParticipantTypesResource(String modelPath) {
        try {
            ModelUtils.load(URI.createPlatformPluginURI(modelPath, true), session.getTransactionalEditingDomain().getResourceSet());
        } catch (IOException e) {
            Assert.fail(e.getLocalizedMessage());
        }
    }

    protected Option<SequenceDiagram> openSequenceDiagramOfType(String name, String type) {
        Option<SequenceDDiagram> sequenceDDiagramOfType = getSequenceDDiagramOfType(name, type);
        Assert.assertTrue("Sequence diagram " + name + " not found in " + getPath() + getSessionModel(), sequenceDDiagramOfType.some());
        sequenceDDiagram = sequenceDDiagramOfType.get();
        editorPart = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, sequenceDDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        diagramEditPart = ((DiagramEditor) editorPart).getDiagramEditPart();
        diagramView = diagramEditPart.getDiagramView();
        return ISequenceElementAccessor.getSequenceDiagram(diagramView);
    }

    protected Option<SequenceDDiagram> getSequenceDDiagramOfType(String name, String type) {
        Collection<DRepresentationDescriptor> sequenceDiagrams = getRepresentationDescriptors(type);
        for (DRepresentationDescriptor representationDescriptor : sequenceDiagrams) {
            if (representationDescriptor.getRepresentation() instanceof SequenceDDiagram && name.equals(representationDescriptor.getName())) {
                return Options.newSome((SequenceDDiagram) representationDescriptor.getRepresentation());
            }
        }
        return Options.newNone();
    }

    protected Option<SequenceDiagram> createSequenceDiagramOfType(String type) {
        initViewpoint(InteractionsConstants.VIEWPOINT_NAME);
        sequenceDDiagram = (SequenceDDiagram) createRepresentation(type, interaction);
        editorPart = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, sequenceDDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        diagramEditPart = ((DiagramEditor) editorPart).getDiagramEditPart();
        diagramView = diagramEditPart.getDiagramView();
        return ISequenceElementAccessor.getSequenceDiagram(diagramView);
    }

    /**
     * Get a copy of the bounds of dDiagramElement, which can be a Node or a
     * Edge. if dDiagramElement comes from a Edge, we calculate the bounds from
     * its bendpoints
     * 
     * @param dDiagramElement
     * @param semanticElement
     * @return bounds of Node or Edge of dDiagramElement if exists else return
     *         bounds(-1,-1,-1,-1)
     */
    protected Bounds getBounds(DDiagramElement dDiagramElement, EObject semanticElement) {
        View view = getGmfView(dDiagramElement);
        Bounds boundsCopy = NotationFactory.eINSTANCE.createBounds();
        if (view instanceof Node) {
            Node node = (Node) view;
            Bounds bounds = (Bounds) node.getLayoutConstraint();
            boundsCopy.setX(bounds.getX());
            boundsCopy.setY(bounds.getY());
            boundsCopy.setWidth(bounds.getWidth());
            boundsCopy.setHeight(bounds.getHeight());
            return boundsCopy;
        } else if (view instanceof Edge) {
            Edge edge = (Edge) view;
            DEdge dEdge = (DEdge) edge.getElement();
            IGraphicalEditPart editPart = getEditPart(dEdge);
            if (editPart.getFigure() instanceof Polyline) {
                Polyline polyline = (Polyline) editPart.getFigure();
                Rectangle bounds = polyline.getPoints().getBounds();
                Bounds gmfBounds = NotationFactory.eINSTANCE.createBounds();
                gmfBounds.setX(bounds.x);
                gmfBounds.setY(bounds.y);
                gmfBounds.setWidth(bounds.width);
                gmfBounds.setHeight(bounds.height);
                return gmfBounds;
            }
        }
        return boundsCopy;
    }

    protected Option<Lifeline> getLifelineByName(SequenceDiagram sd, String name) {
        return getLifelineByName(sd.getAllLifelines(), name);
    }

    protected Option<Lifeline> getLifelineByName(Collection<Lifeline> lifelines, String name) {
        for (Lifeline lifeline : lifelines) {
            if (Objects.equal(getLifelineSemanticName(lifeline), name)) {
                return Options.newSome(lifeline);
            }
        }
        return Options.newNone();
    }

    protected String getLifelineSemanticName(Lifeline lifeline) {
        Option<EObject> target = lifeline.getSemanticTargetElement();
        if (target.some()) {
            Participant p = (Participant) target.get();
            return p.getName();
        }
        return null;
    }

    protected Option<Message> getMessageByName(SequenceDiagram sd, String name) {
        return getMessageByName(sd.getAllMessages(), name);
    }

    protected Option<Message> getMessageByName(Collection<Message> messages, String name) {
        for (Message message : messages) {
            if (Objects.equal(getMessageSemanticName(message), name)) {
                return Options.newSome(message);
            }
        }
        return Options.newNone();
    }

    protected String getMessageSemanticName(Message message) {
        Option<EObject> target = message.getSemanticTargetElement();
        if (target.some()) {
            org.eclipse.sirius.sample.interactions.Message m = (org.eclipse.sirius.sample.interactions.Message) target.get();
            return m.getName();
        }
        return null;
    }

    protected Option<InteractionUse> getInteractionUseByName(SequenceDiagram sd, String name) {
        return getInteractionUseByName(sd.getAllInteractionUses(), name);
    }

    protected Option<InteractionUse> getInteractionUseByName(Collection<InteractionUse> ius, String name) {
        for (InteractionUse iu : ius) {
            if (Objects.equal(getInteractionUseSemanticName(iu), name)) {
                return Options.newSome(iu);
            }
        }
        return Options.newNone();
    }

    protected String getInteractionUseSemanticName(InteractionUse iu) {
        Option<EObject> target = iu.getSemanticTargetElement();
        if (target.some()) {
            org.eclipse.sirius.sample.interactions.InteractionUse m = (org.eclipse.sirius.sample.interactions.InteractionUse) target.get();
            return m.getType();
        }
        return null;
    }

    protected Option<CombinedFragment> getCombinedFragmentByName(SequenceDiagram sd, String name) {
        return getCombinedFragmentByName(sd.getAllCombinedFragments(), name);
    }

    protected Option<CombinedFragment> getCombinedFragmentByName(Collection<CombinedFragment> cfs, String name) {
        for (CombinedFragment cf : cfs) {
            if (Objects.equal(getCombinedFragmentSemanticName(cf), name)) {
                return Options.newSome(cf);
            }
        }
        return Options.newNone();
    }

    protected String getCombinedFragmentSemanticName(CombinedFragment cf) {
        Option<EObject> target = cf.getSemanticTargetElement();
        if (target.some()) {
            org.eclipse.sirius.sample.interactions.CombinedFragment m = (org.eclipse.sirius.sample.interactions.CombinedFragment) target.get();
            return m.getOperator();
        }
        return null;
    }

    protected Option<Execution> getExecutionByName(SequenceDiagram sd, String name) {
        return getExecutionByName(sd.getAllExecutions(), name);
    }

    protected Option<Execution> getExecutionByName(Collection<Execution> executions, String name) {
        for (Execution execution : executions) {
            if (Objects.equal(getExecutionSemanticName(execution), name)) {
                return Options.newSome(execution);
            }
        }
        return Options.newNone();
    }

    protected String getExecutionSemanticName(Execution execution) {
        Option<EObject> target = execution.getSemanticTargetElement();
        if (target.some()) {
            org.eclipse.sirius.sample.interactions.Execution m = (org.eclipse.sirius.sample.interactions.Execution) target.get();
            return m.getName();
        }
        return null;
    }

    protected Option<State> getStateByName(SequenceDiagram sd, String name) {
        return getStateByName(sd.getAllStates(), name);
    }

    protected Option<State> getStateByName(Collection<State> states, String name) {
        for (State state : states) {
            if (Objects.equal(getStateSemanticName(state), name)) {
                return Options.newSome(state);
            }
        }
        return Options.newNone();
    }

    protected String getStateSemanticName(State state) {
        Option<EObject> target = state.getSemanticTargetElement();
        if (target.some()) {
            org.eclipse.sirius.sample.interactions.State m = (org.eclipse.sirius.sample.interactions.State) target.get();
            return m.getName();
        }
        return null;
    }

    protected Option<Operand> getOperandByName(SequenceDiagram sd, String name) {
        return getOperandByName(sd.getAllOperands(), name);
    }

    protected Option<Operand> getOperandByName(Collection<Operand> operands, String name) {
        for (Operand operand : operands) {
            if (Objects.equal(getOperandSemanticName(operand), name)) {
                return Options.newSome(operand);
            }
        }
        return Options.newNone();
    }

    protected String getOperandSemanticName(Operand operand) {
        Option<EObject> target = operand.getSemanticTargetElement();
        if (target.some()) {
            org.eclipse.sirius.sample.interactions.Operand m = (org.eclipse.sirius.sample.interactions.Operand) target.get();
            return m.getName();
        }
        return null;
    }

    protected Rectangle asRectangle(Bounds bounds) {
        return new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    protected void createNewParticipant(Point location) {
        final AbstractToolDescription tool = getTool(sequenceDDiagram, InteractionsConstants.PARTICIPANT_TOOL_ID);
        CreateRequest req = new CreateRequest();
        req.setLocation(location.getCopy());
        req.setFactory(new CreationFactory() {

            @Override
            public Object getObjectType() {
                return tool;
            }

            @Override
            public Object getNewObject() {
                return tool;
            }
        });
        diagramEditPart.performRequest(req);
    }

    @Override
    protected void tearDown() throws Exception {
        if (editorPart != null) {
            DialectUIManager.INSTANCE.closeEditor(editorPart, false);
            TestsUtil.synchronizationWithUIThread();
        }

        super.tearDown();
    }
}
