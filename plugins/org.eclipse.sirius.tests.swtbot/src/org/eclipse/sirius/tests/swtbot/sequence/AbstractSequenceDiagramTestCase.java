/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceMessageViewQuery;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.OperandEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageNameEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckToolIsActivated;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.matchers.AbstractMatcher;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Abstract base class for SWTbot tests on Sequence Diagram, with helper
 * methods.
 * 
 * @author pcdavid
 */
public abstract class AbstractSequenceDiagramTestCase extends AbstractSiriusSwtBotGefTestCase {

    // CHECKSTYLE:OFF
    protected static final String REPRESENTATION_DESCRIPTION_NAME = "Sequence Diagram Description";

    protected static final String DATA_UNIT_DIR = "/data/unit/sequence/";

    protected final Point origin = new Point(LayoutConstants.LIFELINES_START_X, LayoutConstants.LIFELINES_START_Y);

    protected static final String FILE_DIR = "/";

    protected static final String SEMANTIC_PATH = TEMP_PROJECT_NAME + "/lifelines.interactions";

    protected static final String INIT_ERROR_MSG = "An error occurs during tests initialization";

    // Lifelines

    protected static final String LIFELINE_A = "a : A";

    protected static final String LIFELINE_B = "b : B";

    protected static final String LIFELINE_C = "c : C";

    protected static final String LIFELINE_D = "d : D";

    protected static final String LIFELINE_E = "e : E";

    protected static final String NEW_LIFELINE = "newParticipant4 : ";

    protected static final String NEW_LIFELINE_5 = "newParticipant5 : ";

    protected static final String NEW_LIFELINE_D = "Participant d";

    // States

    protected static final String STATE_S1 = "s1";

    protected static final String STATE_S2 = "s2";

    // Simple messages

    protected static final String FIRST_MESSAGE = "m1";

    protected static final String FIRST_MESSAGE_ON_LIFELINE_A = "m1";

    protected static final String SECOND_MESSAGE = "m2";

    protected static final String SECOND_MESSAGE_ON_LIFELINE_A = "m2";

    protected static final String SECOND_MESSAGE_ON_LIFELINE_C = "m2";

    protected static final String THIRD_MESSAGE = "m3";

    protected static final String THIRD_MESSAGE_ON_LIFELINE_A = "m3";

    protected static final String FOURTH_MESSAGE = "m4";

    protected static final String FOURTH_MESSAGE_ON_LIFELINE_B = "m4";

    protected static final String FIFTH_MESSAGE = "m5";

    protected static final String SIXTH_MESSAGE = "m6";

    protected static final String SEVENTH_MESSAGE = "m7";

    protected static final String SEVENTH_MESSAGE_ON_LIFELINE_C = "m7";

    protected static final String EIGHTH_MESSAGE = "m8";

    protected static final String NINETH_MESSAGE = "m9";

    protected static final String NINETH_MESSAGE_ON_LIFELINE_C = "m9";

    protected static final String TENTH_MESSAGE = "m10";

    protected static final String ELEVENTH_MESSAGE = "m11";

    protected static final String TWELFTH_MESSAGE = "m12";

    protected static final String THIRTEENTH_MESSAGE = "m13";

    protected static final String FOURTEENTH_MESSAGE = "m14";

    protected static final String FIFTEENTH_MESSAGE = "m15";

    protected static final String SIXTEENTH_MESSAGE = "m16";

    // Return messages

    protected static final String RETURN_MESSAGE = "";

    // Creation messages

    protected static final String FIRST_CREATE_MESSAGE = "m_create1";

    protected static final String FIRST_CREATE_MESSAGE_ON_LIFELINE_A = "m_create1";

    protected static final String FIRST_CREATE_MESSAGE_ON_NEW_PARTICIPANT = "m_create1";

    protected static final String SECOND_CREATE_MESSAGE = "m_create2";

    protected static final String THIRD_CREATE_MESSAGE = "m_create3";

    protected static final String SEVENTH_CREATE_MESSAGE = "m_create7";

    protected static final String TENTH_CREATE_MESSAGE = "m_create10";

    // Destroy messages

    protected static final String FIRST_DESTROY_MESSAGE = "m_destroy1";

    protected static final String THIRD_DESTROY_MESSAGE = "m_destroy3";

    protected static final String FOURTH_DESTROY_MESSAGE = "m_destroy4";

    protected static final String EIGHTH_DESTROY_MESSAGE = "m_destroy8";

    protected static final String NINETH_DESTROY_MESSAGE = "m_destroy9";

    // Sync calls

    protected static final String FIRST_SYNC_CALL = "m1";

    protected static final String FIRST_MESSAGE_SYNC_CALL_TO_SELF = "m1";

    protected static final String THIRD_MESSAGE_SYNC_CALL_TO_SELF = "m3";

    protected static final String THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_B = "m3";

    protected static final String THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C = "m3";

    protected static final String FIFTH_MESSAGE_SYNC_CALL_TO_SELF = "m5";

    protected static final String FIRST_MESSAGE_SYNC_CALL = "m1";

    protected static final String THIRD_MESSAGE_SYNC_CALL = "m3";

    protected static final String FIFTH_MESSAGE_SYNC_CALL = "m5";

    protected static final String FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A = "m5";

    protected static final String FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B = "m5";

    protected static final String FIFTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE = "m5";

    protected static final String SIXTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE = "m6";

    protected static final String SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE = "m7";

    protected static final String SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A = "m7";

    protected static final String SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B = "m7";

    protected static final String SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C = "m7";

    protected static final String EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C = "m8";

    // Messages to self

    protected static final String FIRST_MESSAGE_TO_SELF = "m1";

    protected static final String SECOND_MESSAGE_TO_SELF = "m2";

    protected static final String THIRD_MESSAGE_TO_SELF = "m3";

    // Undo labels

    protected static final String REFRESH_ORDERING_COMMAND = "Refresh graphical ordering";

    protected static final String LAYOUT_COMMAND = "Refresh graphical layout";

    protected static final String UNDO_LAYOUT_COMMAND = "Undo " + LAYOUT_COMMAND;

    protected static final String ARRANGE_ALL_COMMAND = "Arrange All";

    // protected static final String UNDO_MESSAGE_TO_SELF_SET_BENDPOINTS =
    // "Undo Set edge bendpoints";

    protected static final String UNDO_MOVE_LIFELINE = "Undo Move down lifeli...ticipant message";

    protected static final String UNDO_RESIZE_LIFELINE = "Undo Resize lifeline ...ticipant message";

    protected static final String VIEWPOINT_NAME = "Interactions";

    // CHECKSTYLE:ON

    /**
     * Save the status of the Old UI preference.
     */
    protected boolean oldUiPreference;

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

    /**
     * Get the full name of the representation id displayed in the category
     * subtree of the model content view.
     * 
     * @return the full name of the representation id displayed in the category
     *         subtree of the model content view
     */
    protected String getRepresentationIdNameFromCategoryView() {
        return REPRESENTATION_DESCRIPTION_NAME + " " + getRepresentationId();
    }

    /**
     * Get the representation (diagram.DDiagram) name as defined in the .aird
     * model.
     * 
     * @return the representation name as defined in the .aird model
     */
    protected abstract Option<String> getDRepresentationName();

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        if (getTypesSemanticModel() != null) {
            copyFileToTestProject(Activator.PLUGIN_ID, getPath(), getTypesSemanticModel());
        }
        if (getSemanticModel() != null) {
            copyFileToTestProject(Activator.PLUGIN_ID, getPath(), getSemanticModel());
        }
        if (getSessionModel() != null) {
            copyFileToTestProject(Activator.PLUGIN_ID, getPath(), getSessionModel());
        }
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);
        changeDiagramPreference(SiriusDiagramPreferencesKeys.PREF_DISPLAY_HEADER_SECTION.name(), false);

        if (getSessionModel() == null) {

        } else {
            sessionAirdResource = new UIResource(designerProject, FILE_DIR, getSessionModel());
            localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
            Option<String> dRepresentationName = getDRepresentationName();
            if (dRepresentationName.some()) {
                editor = openDiagram(localSession.getOpenedSession(), getRepresentationId(), dRepresentationName.get(), DDiagram.class);
            }
        }

        initEditor();
    }

    protected void initEditor() {
        if (editor != null) {
            editor.setFocus();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean getAutoRefreshMode() {
        return true;
    }

    protected void assertNoEditPartWithLabel(final String label) {
        try {
            editor.getEditPart(label);
            fail(WidgetNotFoundException.class + " expected for element \"" + label + "\"");
        } catch (final WidgetNotFoundException e) {
            // Expected, the edit part must not be found
        }
    }

    protected SWTBotMenu arrangeAll() {
        // Give the focus to the editor
        editor.setFocus();
        // Select the diagram itself
        editor.select(editor.mainEditPart());
        // Launch the arrange via the menu bar
        return bot.menu("Diagram").menu("Arrange").menu("All").click();
    }

    protected void resizeLifeline(String lifelineLabel, int resizeValue) {
        EndOfLifeEditPart eol = Iterables.getOnlyElement(Iterables.filter(getLifelineEditPart(lifelineLabel).getChildren(), EndOfLifeEditPart.class));
        Point center = eol.getFigure().getBounds().getCenter();
        editor.drag(center, center.getTranslated(new Dimension(0, resizeValue)));
    }

    /**
     * Close the error log view.
     * 
     * @throws Exception
     *             In case of problem.
     * @deprecated Use {@link #closeErrorLogViewByAPI()} instead. This method is
     *             faster.
     */
    protected void closeErrorLogView() throws Exception {
        bot.viewByTitle("Error Log").close();
    }

    protected void closeErrorLogViewByAPI() throws Exception {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                IViewPart errorLogView = page.findView("org.eclipse.pde.runtime.LogView");
                page.hideView(errorLogView);
            }
        });
    }

    protected void createMessage(String toolName, String sourceLifelineName, int sourceY, String targetLifelineName, int targetY) {
        int sourceX = getLifelineScreenX(sourceLifelineName);
        int targetX = getLifelineScreenX(targetLifelineName);
        createMessage(toolName, sourceX, sourceY, targetX, targetY);
    }

    /**
     * Try to create a syncCall from start to end Point
     * 
     * @param start
     *            Point of the first click
     * @param end
     *            Point of the second click
     */
    protected void createSyncCall(Point start, Point end) {
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, start, end);
    }

    /**
     * Try to create a asyncCall from start to end Point
     * 
     * @param start
     *            Point of the first click
     * @param end
     *            Point of the second click
     */
    protected void createAsyncCall(Point start, Point end) {
        createMessage(InteractionsConstants.ASYNC_CALL_TOOL_ID, start, end);
    }

    protected void createMessage(String toolName, int sourceX, int sourceY, String targetLifelineName, int targetY) {
        int targetX = getLifelineScreenX(targetLifelineName);
        createMessage(toolName, sourceX, sourceY, targetX, targetY);
    }

    /**
     * Create a message of type toolName from (sourceX, sourceY) to (targetX,
     * targetY)
     * 
     * @param toolName
     *            id of the tool
     * @param sourceX
     *            x coordinate of the source point
     * @param sourceY
     *            y coordinate of the target point
     * @param targetX
     *            x coordinate of the source point
     * @param targetY
     *            y coordinate of the target point
     */
    protected void createMessage(String toolName, int sourceX, int sourceY, int targetX, int targetY) {
        ICondition done = new CheckToolIsActivated(editor, toolName);
        editor.activateTool(toolName);
        bot.waitUntil(done);
        editor.click(sourceX, sourceY);
        editor.click(targetX, targetY);
    }

    /**
     * Create a message of type toolName from (sourceX, sourceY) to (targetX,
     * targetY)
     * 
     * @param toolName
     *            id of the tool
     * @param source
     *            coordinates of the source point
     * @param target
     *            coordinates of the target point
     */
    protected void createMessage(String toolName, Point source, Point target) {
        createMessage(toolName, source.x, source.y, target.x, target.y);
    }

    /**
     * Create a new InteractionUse where its top left corner is start is its
     * bottom right corner is finish
     * 
     * @param start
     *            {@link Point} of the first click
     * @param finish
     *            {@link Point} of the second click
     */
    protected void createInteractionUse(Point start, Point finish) {
        applyTwoClicDragTool(InteractionsConstants.INTERACTION_USE_TOOL_ID, start, finish);
    }

    /**
     * Create a new InteractionUse with a single click
     * 
     * @param singleClickPoint
     *            {@link Point} of the single click
     */
    protected void createInteractionUse(Point singleClickPoint) {
        applyOneClicTool(InteractionsConstants.INTERACTION_USE_TOOL_ID, singleClickPoint.x, singleClickPoint.y);
    }

    /**
     * Create a new InteractionUse with a single click
     * 
     * @param singleClickPoint
     *            {@link Point} of the single click
     * 
     * @return the newly created InteractionUse
     * 
     */
    protected SWTBotGefEditPart createInteractionUseWithResult(final Point singleClickPoint) {
        ICondition done = new OperationDoneCondition();
        createInteractionUse(singleClickPoint);
        bot.waitUntil(done);

        List<SWTBotGefEditPart> editParts = editor.editParts(new AbstractMatcher<InteractionUseEditPart>() {
            @Override
            protected boolean doMatch(Object item) {
                if (!(item instanceof InteractionUseEditPart)) {
                    return false;
                }
                InteractionUseEditPart editPart = (InteractionUseEditPart) item;
                boolean selected = editPart.getSelected() == EditPart.SELECTED_PRIMARY;
                Rectangle bounds = editPart.getFigure().getBounds();
                boolean correctLocation = bounds.contains(singleClickPoint.getTranslated(0, 5));
                return selected || correctLocation;
            }

            public void describeTo(Description description) {
            }
        });
        if (editParts.isEmpty()) {
            return null;
        } else {
            SWTBotGefEditPart interactionUseEditPartBot = editParts.get(0);
            return interactionUseEditPartBot;
        }
    }

    /**
     * Create a new InteractionUse where its top left corner is start is its
     * bottom right corner is finish
     * 
     * @param start
     *            {@link Point} of the first click
     * @param finish
     *            {@link Point} of the second click
     * 
     * @return the newly created InteractionUse
     */
    protected SWTBotGefEditPart createInteractionUseWithResult(final Point start, final Point finish) {
        ICondition done = new OperationDoneCondition();
        createInteractionUse(start, finish);
        bot.waitUntil(done);
        List<SWTBotGefEditPart> editParts = editor.editParts(new AbstractMatcher<InteractionUseEditPart>() {
            @Override
            protected boolean doMatch(Object item) {
                if (!(item instanceof InteractionUseEditPart)) {
                    return false;
                }
                InteractionUseEditPart editPart = (InteractionUseEditPart) item;
                boolean selected = editPart.getSelected() == EditPart.SELECTED_PRIMARY;
                Rectangle bounds = editPart.getFigure().getBounds();
                Rectangle container = new Rectangle(start, finish).translate(0, 20);
                boolean probablyCorrectLocation = container.intersects(bounds);
                return selected || probablyCorrectLocation;
            }

            public void describeTo(Description description) {
            }
        });
        if (editParts.isEmpty()) {
            return null;
        } else {
            SWTBotGefEditPart interactionUseEditPartBot = editParts.get(0);
            return interactionUseEditPartBot;
        }
    }

    /**
     * Create a new CombinedFragment where its top left corner is start is its
     * bottom right corner is finish
     * 
     * @param start
     *            {@link Point} of the first click
     * @param finish
     *            {@link Point} of the second click
     */
    protected void createCombinedFragment(Point start, Point finish) {
        applyTwoClicDragTool(InteractionsConstants.COMBINET_FRAGMENT_TOOL_ID, start, finish);
    }

    /**
     * Create a new CombinedFragment with a single click
     * 
     * @param singleClickPoint
     *            {@link Point} of the single click
     */
    protected void createCombinedFragment(Point singleClickPoint) {
        applyOneClicTool(InteractionsConstants.COMBINET_FRAGMENT_TOOL_ID, singleClickPoint.x, singleClickPoint.y);
    }

    /**
     * Create a new CombinedFragment where its top left corner is start is its
     * bottom right corner is finish
     * 
     * @param start
     *            {@link Point} of the first click
     * @param finish
     *            {@link Point} of the second click
     * 
     * @return the newly created CombinedFragment
     */
    protected SWTBotGefEditPart createCombinedFragmentWithResult(final Point start, final Point finish) {
        List<SWTBotGefEditPart> descendantsBefore = editor.rootEditPart().descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class));

        ICondition done = new OperationDoneCondition();
        createCombinedFragment(start, finish);
        bot.waitUntil(done);

        List<SWTBotGefEditPart> descendantsAfter = editor.rootEditPart().descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class));
        if (descendantsBefore.size() == descendantsAfter.size() - 1) {
            descendantsAfter.removeAll(descendantsBefore);
            Assert.assertEquals(1, descendantsAfter.size());
            SWTBotGefEditPart resultEditPartBot = descendantsAfter.get(0);
            return resultEditPartBot;
        } else {
            return null;
        }
    }

    /**
     * Create a new CombinedFragment with a single click
     * 
     * @param singleClickPoint
     *            {@link Point} of the single click
     * 
     * @return the newly created CombinedFragment
     */
    protected SWTBotGefEditPart createCombinedFragmentWithResult(final Point singleClickPoint) {
        List<SWTBotGefEditPart> descendantsBefore = editor.rootEditPart().descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class));
        createCombinedFragment(singleClickPoint);
        List<SWTBotGefEditPart> descendantsAfter = editor.rootEditPart().descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class));
        if (descendantsBefore.size() == descendantsAfter.size() - 1) {
            descendantsAfter.removeAll(descendantsBefore);
            Assert.assertEquals(1, descendantsAfter.size());
            SWTBotGefEditPart resultEditPartBot = descendantsAfter.get(0);
            return resultEditPartBot;
        } else {
            return null;
        }
    }

    /**
     * Create a new CombinedFragment's Operand where its top left corner is
     * start is its bottom right corner is finish
     * 
     * @param start
     *            {@link Point} of the first click
     * @param finish
     *            {@link Point} of the second click
     */
    protected void createCombinedFragmentOperand(Point start, Point finish) {
        applyTwoClicDragTool(InteractionsConstants.COMBINET_FRAGMENT_OPERAND_TOOL_ID, start, finish);

    }

    /**
     * Create a new CombinedFragment's Operand with a single click
     * 
     * @param singleClickPoint
     *            {@link Point} of the single click
     */
    protected void createCombinedFragmentOperand(Point singleClickPoint) {
        applyOneClicTool(InteractionsConstants.COMBINET_FRAGMENT_OPERAND_TOOL_ID, singleClickPoint.x, singleClickPoint.y);
    }

    /**
     * Create a new CombinedFragment's Operand where its top left corner is
     * start is its bottom right corner is finish
     * 
     * @param start
     *            {@link Point} of the first click
     * @param finish
     *            {@link Point} of the second click
     * 
     * @return the newly created CombinedFragment's Operand
     */
    protected SWTBotGefEditPart createCombinedFragmentOperandWithResult(final Point start, final Point finish) {
        ICondition done = new OperationDoneCondition();
        createCombinedFragmentOperand(start, finish);
        bot.waitUntil(done);

        List<SWTBotGefEditPart> editParts = editor.editParts(new AbstractMatcher<OperandEditPart>() {
            @Override
            protected boolean doMatch(Object item) {
                if (!(item instanceof OperandEditPart)) {
                    return false;
                }
                OperandEditPart editPart = (OperandEditPart) item;
                boolean selected = editPart.getSelected() == EditPart.SELECTED_PRIMARY;
                Rectangle bounds = editPart.getFigure().getBounds();
                Rectangle container = new Rectangle(start, finish);
                boolean probablyCorrectLocation = container.intersects(bounds);
                return selected || probablyCorrectLocation;
            }

            public void describeTo(Description description) {
            }
        });
        if (editParts.isEmpty()) {
            return null;
        } else {
            SWTBotGefEditPart resultEditPartBot = editParts.get(0);
            return resultEditPartBot;
        }
    }

    /**
     * Create a new CombinedFragment's Operand with a single click
     * 
     * @param singleClickPoint
     *            {@link Point} of the single click
     * 
     * @return the newly created CombinedFragment's Operand
     */
    protected Option<SWTBotGefEditPart> createCombinedFragmentOperandWithResult(final Point singleClickPoint) {
        List<SWTBotGefEditPart> combinedFragments = editor.rootEditPart().descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class));
        Assert.assertNotNull(combinedFragments);
        Assert.assertFalse(combinedFragments.isEmpty());

        Predicate<SWTBotGefEditPart> combinedFragmentIncludingClickLocation = new Predicate<SWTBotGefEditPart>() {

            public boolean apply(SWTBotGefEditPart input) {
                if (input.part() instanceof CombinedFragmentEditPart) {
                    CombinedFragmentEditPart combinedFragmentEditPart = (CombinedFragmentEditPart) input.part();
                    return combinedFragmentEditPart.getFigure().getBounds().contains(singleClickPoint) && combinedFragmentEditPart.getISequenceEvent().getVerticalRange().includes(singleClickPoint.y);
                }
                return false;
            }
        };

        SWTBotGefEditPart deeepestCombinedFragmentBotAtLocation = null;
        for (SWTBotGefEditPart combinedFragmentBot : Iterables.filter(combinedFragments, combinedFragmentIncludingClickLocation)) {
            if (deeepestCombinedFragmentBotAtLocation == null) {
                deeepestCombinedFragmentBotAtLocation = combinedFragmentBot;
            } else {
                CombinedFragmentEditPart deeepestCombinedFragmentEditPart = (CombinedFragmentEditPart) deeepestCombinedFragmentBotAtLocation.part();
                CombinedFragmentEditPart combinedFragmentEditPart = (CombinedFragmentEditPart) combinedFragmentBot.part();
                if (deeepestCombinedFragmentEditPart.getISequenceEvent().getVerticalRange().includes(combinedFragmentEditPart.getISequenceEvent().getVerticalRange())) {
                    deeepestCombinedFragmentBotAtLocation = combinedFragmentBot;
                }
            }
        }

        Assert.assertNotNull(deeepestCombinedFragmentBotAtLocation);

        List<SWTBotGefEditPart> descendantsBefore = deeepestCombinedFragmentBotAtLocation.descendants(IsInstanceOf.instanceOf(OperandEditPart.class));

        ICondition done = new OperationDoneCondition();
        createCombinedFragmentOperand(singleClickPoint);
        bot.waitUntil(done);

        List<SWTBotGefEditPart> descendantsAfter = deeepestCombinedFragmentBotAtLocation.descendants(IsInstanceOf.instanceOf(OperandEditPart.class));
        if (descendantsBefore.size() == descendantsAfter.size() - 1) {
            descendantsAfter.removeAll(descendantsBefore);
            Assert.assertEquals(1, descendantsAfter.size());
            SWTBotGefEditPart resultEditPartBot = descendantsAfter.get(0);
            return Options.newSome(resultEditPartBot);
        } else {
            return Options.newNone();
        }
    }

    private void applyOneClicTool(String toolName, int xLocation, int yLocation) {
        ICondition done = new CheckToolIsActivated(editor, toolName);
        editor.activateTool(toolName);
        bot.waitUntil(done);
        editor.click(xLocation, yLocation);
    }

    /**
     * @param interactionUseToolId
     * @param start
     * @param finish
     */
    private void applyTwoClicDragTool(String toolName, Point start, Point finish) {
        ICondition done = new CheckToolIsActivated(editor, toolName);
        editor.activateTool(toolName);
        bot.waitUntil(done);
        editor.drag(start, finish);
    }

    protected void createParticipant(int xLocation, int yLocation) {
        applyOneClicTool(InteractionsConstants.PARTICIPANT_TOOL_ID, xLocation, yLocation);
    }

    /**
     * Create a new {@link InstanceRoleEditPart}'s figure named name and drop it
     * to the specified location
     * 
     * @param name
     *            name of the newly created InstanceRole
     * @param xLocation
     *            x coordinate where to drop the newly created InstanceRole
     * @param yLocation
     *            y coordinate where to drop the newly created InstanceRole
     * @return the newly created InstanceRole
     */
    protected SWTBotGefEditPart createParticipant(String name, final int xLocation, final int yLocation) {
        ICondition done = new OperationDoneCondition();
        createParticipant(xLocation, yLocation);
        bot.waitUntil(done);

        List<SWTBotGefEditPart> editParts = editor.editParts(new AbstractMatcher<InstanceRoleEditPart>() {
            @Override
            protected boolean doMatch(Object item) {
                if (!(item instanceof InstanceRoleEditPart)) {
                    return false;
                }
                InstanceRoleEditPart editPart = (InstanceRoleEditPart) item;
                return editPart.getSelected() == EditPart.SELECTED_PRIMARY;
            }

            public void describeTo(Description description) {
            }
        });
        if (editParts.isEmpty()) {
            return null;
        } else {
            SWTBotGefEditPart instanceRoleEditPartBot = editParts.get(0);
            editor.directEditType(name, instanceRoleEditPartBot);
            return instanceRoleEditPartBot;
        }
    }

    protected void createExecution(int xLocation, int yLocation) {
        applyOneClicTool(InteractionsConstants.EXECUTION_TOOL_ID, xLocation, yLocation);
    }

    protected Option<SWTBotGefEditPart> createExecutionWithResult(Point location) {
        return createExecutionWithResult(location.x, location.y);
    }

    protected Option<SWTBotGefEditPart> createExecutionWithResult(int xLocation, int yLocation) {
        List<SWTBotGefEditPart> descendantsBefore = editor.rootEditPart().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class));
        createExecution(xLocation, yLocation);
        List<SWTBotGefEditPart> descendantsAfter = editor.rootEditPart().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class));

        if (descendantsBefore.size() == descendantsAfter.size() - 1) {
            descendantsAfter.removeAll(descendantsBefore);
            Assert.assertEquals(1, descendantsAfter.size());
            SWTBotGefEditPart resultEditPartBot = descendantsAfter.get(0);
            return Options.newSome(resultEditPartBot);
        } else {
            return Options.newNone();
        }
    }

    protected void createState(int xLocation, int yLocation) {
        applyOneClicTool(InteractionsConstants.STATE_TOOL_ID, xLocation, yLocation);
    }

    protected Option<SWTBotGefEditPart> createStateWithResult(Point location) {
        return createStateWithResult(location.x, location.y);
    }

    protected Option<SWTBotGefEditPart> createStateWithResult(int xLocation, int yLocation) {
        List<SWTBotGefEditPart> descendantsBefore = editor.rootEditPart().descendants(IsInstanceOf.instanceOf(StateEditPart.class));
        createState(xLocation, yLocation);
        List<SWTBotGefEditPart> descendantsAfter = editor.rootEditPart().descendants(IsInstanceOf.instanceOf(StateEditPart.class));

        if (descendantsBefore.size() == descendantsAfter.size() - 1) {
            descendantsAfter.removeAll(descendantsBefore);
            Assert.assertEquals(1, descendantsAfter.size());
            SWTBotGefEditPart resultEditPartBot = descendantsAfter.get(0);
            return Options.newSome(resultEditPartBot);
        } else {
            return Options.newNone();
        }
    }

    protected void createPunctualState(int xLocation, int yLocation) {
        applyOneClicTool(InteractionsConstants.PUNCTUAL_STATE_TOOL_ID, xLocation, yLocation);
    }

    protected Option<SWTBotGefEditPart> createPunctualStateWithResult(Point location) {
        return createPunctualStateWithResult(location.x, location.y);
    }

    protected Option<SWTBotGefEditPart> createPunctualStateWithResult(int xLocation, int yLocation) {
        List<SWTBotGefEditPart> descendantsBefore = editor.rootEditPart().descendants(IsInstanceOf.instanceOf(StateEditPart.class));
        createPunctualState(xLocation, yLocation);
        List<SWTBotGefEditPart> descendantsAfter = editor.rootEditPart().descendants(IsInstanceOf.instanceOf(StateEditPart.class));

        if (descendantsBefore.size() == descendantsAfter.size() - 1) {
            descendantsAfter.removeAll(descendantsBefore);
            Assert.assertEquals(1, descendantsAfter.size());
            SWTBotGefEditPart resultEditPartBot = descendantsAfter.get(0);
            return Options.newSome(resultEditPartBot);
        } else {
            return Options.newNone();
        }
    }

    protected void assertMessageVerticalPosition(String messageName, int expectedPosition) {
        assertNamedMessageHasValidScreenBounds(messageName, new Rectangle(0, expectedPosition, 0, 0), true, false);
    }

    protected void assertMessageVerticalPosition(SequenceMessageEditPart smep, int expectedPosition) {
        assertMessageHasValidBounds(smep, "The given message is not at the expected vertical position", new Rectangle(0, expectedPosition, 0, 0), true, false, true);
    }

    protected SequenceMessageEditPart getSequenceMessageEditPart(String name) {
        SequenceMessageEditPart result = null;

        // Get the first found return...
        if (RETURN_MESSAGE.equals(name)) {
            result = getFirstReturnMessage();
        } else {
            SWTBotGefEditPart editPart = editor.getEditPart(name);
            assertTrue("There is no graphical edit part named " + name, editPart.part() instanceof SequenceMessageEditPart || editPart.part() instanceof SequenceMessageNameEditPart);
            if (editPart.part() instanceof SequenceMessageEditPart) {
                result = (SequenceMessageEditPart) editPart.part();
            } else if (editPart.part() instanceof SequenceMessageNameEditPart) {
                result = (SequenceMessageEditPart) editPart.part().getParent();
            } else if (editPart.parent() != null && editPart.parent().part() instanceof SequenceMessageEditPart) {
                result = (SequenceMessageEditPart) editPart.parent().part();
            }
        }
        return result;
    }

    protected SequenceMessageEditPart getFirstReturnMessage() {
        SequenceMessageEditPart result = null;
        for (SWTBotGefConnectionEditPart part : editor.getConnectionsEditPart()) {
            if (part.part() instanceof SequenceMessageEditPart) {
                Iterable<SequenceMessageNameEditPart> filter = Iterables.filter(part.part().getChildren(), SequenceMessageNameEditPart.class);
                if (!Iterables.isEmpty(filter)) {

                    IFigure figure = filter.iterator().next().getFigure();
                    if (figure instanceof SiriusWrapLabel && RETURN_MESSAGE.equals(((SiriusWrapLabel) figure).getText())) {
                        result = (SequenceMessageEditPart) part.part();
                        break;
                    }
                }
            }
        }
        return result;
    }

    protected int getSequenceMessageVerticalPosition(String name) {
        Edge edge = (Edge) getSequenceMessageEditPart(name).getNotationView();
        return new SequenceMessageViewQuery(edge).getFirstPointVerticalPosition(true);
    }

    protected Point getSequenceMessageScreenCenteredPosition(String name) {
        SequenceMessageEditPart sequenceMessageEditPart = getSequenceMessageEditPart(name);
        Point location = sequenceMessageEditPart.getConnectionFigure().getPoints().getMidpoint().getCopy();
        GraphicalHelper.logical2screen(location, sequenceMessageEditPart);
        return location;
    }

    protected Point getSequenceMessageScreenCenteredPosition(SequenceMessageEditPart sequenceMessageEditPart) {
        Point location = sequenceMessageEditPart.getConnectionFigure().getPoints().getMidpoint().getCopy();
        GraphicalHelper.logical2screen(location, sequenceMessageEditPart);
        return location;
    }

    protected int getSequenceMessageScreenVerticalPosition(String name) {
        SequenceMessageEditPart sequenceMessageEditPart = getSequenceMessageEditPart(name);
        Point location = sequenceMessageEditPart.getConnectionFigure().getPoints().getFirstPoint().getCopy();
        GraphicalHelper.logical2screen(location, sequenceMessageEditPart);
        return location.y;
    }

    protected int getSequenceMessageScreenVerticalPosition(SequenceMessageEditPart sequenceMessageEditPart) {
        Point location = sequenceMessageEditPart.getConnectionFigure().getPoints().getFirstPoint().getCopy();
        GraphicalHelper.logical2screen(location, sequenceMessageEditPart);
        return location.y;
    }

    protected Point getSequenceMessageFirstBendpointScreenPosition(String name) {
        SequenceMessageEditPart sequenceMessageEditPart = getSequenceMessageEditPart(name);
        return getSequenceMessageFirstBendpointScreenPosition(sequenceMessageEditPart);
    }

    protected Point getSequenceMessageFirstBendpointScreenPosition(SequenceMessageEditPart sequenceMessageEditPart) {
        Point location = sequenceMessageEditPart.getConnectionFigure().getPoints().getFirstPoint().getCopy();
        GraphicalHelper.logical2screen(location, sequenceMessageEditPart);
        return location;
    }

    protected Point getSequenceMessageBendpointScreenPosition(SequenceMessageEditPart sequenceMessageEditPart, int i) {
        if (sequenceMessageEditPart.getConnectionFigure().getPoints().size() >= i + 1) {
            Point location = sequenceMessageEditPart.getConnectionFigure().getPoints().getPoint(i).getCopy();
            GraphicalHelper.logical2screen(location, sequenceMessageEditPart);
            return location;
        }
        return null;
    }

    protected Point getSequenceMessageLastBendpointScreenPosition(String name) {
        SequenceMessageEditPart sequenceMessageEditPart = getSequenceMessageEditPart(name);
        return getSequenceMessageLastBendpointScreenPosition(sequenceMessageEditPart);
    }

    protected Point getSequenceMessageLastBendpointScreenPosition(SequenceMessageEditPart sequenceMessageEditPart) {
        Point location = sequenceMessageEditPart.getConnectionFigure().getPoints().getLastPoint().getCopy();
        GraphicalHelper.logical2screen(location, sequenceMessageEditPart);
        return location;
    }

    protected int getSequenceMessageLastPointScreenVerticalPosition(String name) {
        return getSequenceMessageLastPointScreenVerticalPosition(getSequenceMessageEditPart(name));
    }

    protected int getSequenceMessageLastPointScreenVerticalPosition(SequenceMessageEditPart sequenceMessageEditPart) {
        Point location = sequenceMessageEditPart.getConnectionFigure().getPoints().getLastPoint().getCopy();
        GraphicalHelper.logical2screen(location, sequenceMessageEditPart);
        return location.y;
    }

    protected int getLifelineLength(String name) {
        assertTrue(name + " edit part is not an InstanceRoleEditPart", editor.getEditPart(name).part().getParent() instanceof InstanceRoleEditPart);
        InstanceRoleEditPart instanceRoleEditPart = (InstanceRoleEditPart) editor.getEditPart(name).part().getParent();
        LifelineEditPart lifelineEditPart = Iterables.getOnlyElement(Iterables.filter(instanceRoleEditPart.getChildren(), LifelineEditPart.class));

        return lifelineEditPart.getISequenceEvent().getVerticalRange().width();
    }

    protected ExecutionEditPart getExecutionEditPart(String lifelineLabel, int index) {
        SWTBotGefEditPart editPart = editor.getEditPart(lifelineLabel);
        assertTrue("There is no graphical edit part named " + lifelineLabel, editPart.part() instanceof GraphicalEditPart);

        GraphicalEditPart part = (GraphicalEditPart) editPart.part().getParent();
        List<ExecutionEditPart> allExecutions = EditPartsHelper.getAllExecutions((IGraphicalEditPart) part);

        if (0 <= index && index < allExecutions.size()) {
            return allExecutions.get(index);
        } else {
            return null;
        }
    }

    protected void checkLifelineSize(String lifelineName, int specifiedVSize) {
        assertEquals("Both constants should be equals.", LayoutConstants.UNIT, LayoutUtils.SCALE);
        checkLifelineSize(lifelineName, specifiedVSize, specifiedVSize * LayoutUtils.SCALE);
    }

    protected void checkLifelineSize(String lifelineName, int specifiedVSize, int expectedSize) {
        LifelineEditPart lep = getLifelineEditPart(lifelineName);
        assertEquals("wrong expected vertical range width", expectedSize, lep.getISequenceEvent().getVerticalRange().width());

        DNode lepNode = (DNode) lep.resolveDiagramElement();
        assertEquals("Not expected specified size", specifiedVSize, ((SquareDescription) lepNode.getActualMapping().getStyle()).getHeight().intValue());
        assertEquals("Wrong viewpoint node size", specifiedVSize, lepNode.getHeight().intValue());
        assertEquals("Wrong style size", specifiedVSize, ((Square) lepNode.getOwnedStyle()).getHeight().intValue());

        // Lifeline minimum vertical size : 400 pix.
        int pixSize = Math.max(expectedSize, LayoutConstants.LIFELINES_MIN_Y - LayoutConstants.LIFELINES_START_X - getLogicalSize(lifelineName).height);
        Node lepView = (Node) lep.getNotationView();
        assertEquals("Wrong notation node size", pixSize, ((Size) lepView.getLayoutConstraint()).getHeight());

        IFigure figure = lep.getFigure();
        assertEquals("Wrong notation node size", pixSize, figure.getBounds().height);
    }

    @SuppressWarnings("restriction")
    protected SequenceMessageEditPart getReturnSyncCall(String lifelineLabel, int index) {
        ExecutionEditPart executionEditPart = getExecutionEditPart(lifelineLabel, index);

        int maxVerticalPosition = Integer.MIN_VALUE;
        SequenceMessageEditPart returnMessage = null;
        // find the last return message from source connections
        for (SequenceMessageEditPart smep : Iterables.filter(executionEditPart.getSourceConnections(), SequenceMessageEditPart.class)) {
            // find the last SequenceMessageEditPart with empty label
            Iterable<SequenceMessageNameEditPart> smnepList = Iterables.filter(smep.getChildren(), SequenceMessageNameEditPart.class);
            for (SequenceMessageNameEditPart sequenceMessageNameEditPart : smnepList) {
                if ("".equals(sequenceMessageNameEditPart.getEditText()) && getSequenceMessageScreenVerticalPosition(smep) > maxVerticalPosition) {
                    maxVerticalPosition = getSequenceMessageScreenVerticalPosition(smep);
                    returnMessage = smep;
                }
            }
        }
        assertNotNull("No return message have been found on the execution index 0 on lifeline " + lifelineLabel, returnMessage);
        return returnMessage;
    }

    @SuppressWarnings("restriction")
    protected int getReturnSyncCallScreenPosition(String lifelineLabel, int index) {
        ExecutionEditPart executionEditPart = getExecutionEditPart(lifelineLabel, index);

        int maxVerticalPosition = Integer.MIN_VALUE;
        SequenceMessageEditPart returnMessage = null;
        // find the last return message from source connections
        for (SequenceMessageEditPart smep : Iterables.filter(executionEditPart.getSourceConnections(), SequenceMessageEditPart.class)) {
            // find the last SequenceMessageEditPart with empty label
            Iterable<SequenceMessageNameEditPart> smnepList = Iterables.filter(smep.getChildren(), SequenceMessageNameEditPart.class);
            for (SequenceMessageNameEditPart sequenceMessageNameEditPart : smnepList) {
                if ("".equals(sequenceMessageNameEditPart.getEditText()) && getSequenceMessageScreenVerticalPosition(smep) > maxVerticalPosition) {
                    maxVerticalPosition = getSequenceMessageScreenVerticalPosition(smep);
                    returnMessage = smep;
                }
            }
        }
        assertNotNull("No return message have been found on the execution index 0 on lifeline " + lifelineLabel, returnMessage);
        return maxVerticalPosition;
    }

    /**
     * Validate that
     * <ul>
     * <li>Sequence Events graphical and semantic orderings are consistent</li>
     * <li>InstanceRoles graphical and semantic oredrings are consistent</li>
     * </ul>
     * 
     * @return
     */
    protected SequenceDDiagram validateOrdering() {
        SequenceDiagram sequenceDiagram = getSequenceDiagram();
        // Validates the semantic ordering equals the graphical ordering
        assertOrderingsInSync(sequenceDiagram);

        return sequenceDiagram.getSequenceDDiagram();
    }

    /**
     * Get the {@link SequenceDiagram} associated to the current editor. Asserts
     * checks thaht it is not null.
     * 
     * @return the {@link SequenceDiagram} associated to the current editor.
     */
    protected SequenceDiagram getSequenceDiagram() {
        SequenceDiagramEditPart sequenceDiagramEditPart = Iterables.getOnlyElement(Iterables.filter(editor.rootEditPart().part().getChildren(), SequenceDiagramEditPart.class));
        assertNotNull("No SequenceDiagramEditPart found", sequenceDiagramEditPart);

        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(sequenceDiagramEditPart);
        assertNotNull("No SequenceDiagram found", sequenceDiagram);
        return sequenceDiagram;
    }

    private void assertOrderingsInSync(SequenceDiagram sequenceDiagram) {
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();

        // Check vertical ordering
        assertTrue("The vertical semantic ordering does not match its corresponding graphical ordering",
                Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));

        // Check horizontal ordering
        Iterable<EObject> instanceRoleGraphicalOrdering = Iterables.transform(sequenceDiagram.getSortedInstanceRole(), ISequenceEvent.SEMANTIC_TARGET);
        EList<EObject> semanticInstanceRoles = sequenceDDiagram.getInstanceRoleSemanticOrdering().getSemanticInstanceRoles();
        assertTrue("The horizontal semantic ordering does not match its corresponding the graphical ordering", Iterables.elementsEqual(semanticInstanceRoles, instanceRoleGraphicalOrdering));

    }

    protected void validateOrdering(int expectedEndsNumber) {
        SequenceDDiagram sequenceDDiagram = validateOrdering();

        assertEquals("The number of event in semantic ordering is not as expected", expectedEndsNumber, sequenceDDiagram.getSemanticOrdering().getEventEnds().size());
        assertEquals("The number of event in graphical ordering is not as expected", expectedEndsNumber, sequenceDDiagram.getGraphicalOrdering().getEventEnds().size());
    }

    protected SequenceMessageEditPart getSelectedMessage() {
        ISelection selection = editor.getSelection();
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            if (structuredSelection.size() == 1 && structuredSelection.getFirstElement() instanceof SequenceMessageEditPart) {
                return (SequenceMessageEditPart) structuredSelection.getFirstElement();
            }
        }
        return null;
    }

    protected LifelineEditPart getLifelineEditPart(String lifelineName) {
        Predicate<SWTBotGefEditPart> pred = new Predicate<SWTBotGefEditPart>() {
            public boolean apply(SWTBotGefEditPart input) {
                return input.part() instanceof LifelineEditPart;
            };
        };
        return (LifelineEditPart) Iterables.getOnlyElement(Iterables.filter(editor.getEditPart(lifelineName).parent().children(), pred)).part();
    }

    protected void validateSequenceMessageCenteredOnTarget(String messageLabel) {
        SequenceMessageEditPart sequenceMessageEditPart = getSequenceMessageEditPart(messageLabel);
        EditPart target = sequenceMessageEditPart.getTarget();
        assertTrue("The target is not a IGraphicalEditPart", target instanceof IGraphicalEditPart);

        Rectangle eolBounds = getBounds((IGraphicalEditPart) target, true);
        Point lastPointVerticalPosition = getSequenceMessageLastBendpointScreenPosition(sequenceMessageEditPart).getCopy();

        assertEquals("The message vertical position does not match with the middle of target", lastPointVerticalPosition.y, eolBounds.getCenter().y, 1);
    }

    protected void selectEditParts(EditPart... editPartsToSelect) {
        if (editPartsToSelect == null || editPartsToSelect.length == 0)
            return;

        selectEditParts(Lists.newArrayList(editPartsToSelect));
    }

    protected void selectEditParts(final Collection<? extends EditPart> editPartsToSelect) {
        if (editPartsToSelect == null || editPartsToSelect.size() == 0)
            return;

        Matcher<? extends EditPart> m = new BaseMatcher<EditPart>() {
            public boolean matches(Object item) {
                // TODO Auto-generated method stub
                return editPartsToSelect.contains(item);
            }

            public void describeTo(Description description) {
            }
        };

        editor.select(editor.editParts(m));
    }

    protected void assertExecutionDoesNotExist(String lifeline, int index) {
        ExecutionEditPart executionEditPart = getExecutionEditPart(lifeline, index);
        assertNull("The execution index " + index + " on lifeline " + lifeline + " should not been found", executionEditPart);
    }

    protected void assertStateDoesNotExist(String stateLabel) {
        SWTBotGefEditPart stateEditPart = null;
        try {
            stateEditPart = editor.getEditPart(stateLabel);
            fail("State " + stateLabel + " should not exist");
        } catch (WidgetNotFoundException e) {
            assertNull("The state " + stateLabel + " should not been found", stateEditPart);
        }
    }

    protected Rectangle assertInteractionUseHasValidScreenBounds(InteractionUseEditPart part, Rectangle expectedBounds) {
        String errorCommonLabel = "The interaction use named " + ((DDiagramElement) ((Node) part.getModel()).getElement()).getName();
        return assertSequenceNodeHasValidBounds(part, errorCommonLabel, expectedBounds, true, true);
    }

    protected Rectangle assertInteractionUseHasValidScreenBounds(InteractionUseEditPart part, Rectangle expectedBounds, boolean fullTest) {
        String errorCommonLabel = "The interaction use named " + ((DDiagramElement) ((Node) part.getModel()).getElement()).getName();
        return assertSequenceNodeHasValidBounds(part, errorCommonLabel, expectedBounds, fullTest, true);
    }

    protected Rectangle assertExecutionHasValidLogicalBounds(String lifeline, int index, Rectangle expectedBounds) {
        return assertExecutionHasValidLogicalBounds(lifeline, index, expectedBounds, true);
    }

    protected Rectangle assertExecutionHasValidLogicalBounds(ExecutionEditPart part, Rectangle expectedBounds) {
        String errorCommonLabel = "The execution named " + part.getNodeLabel().getText();
        return assertSequenceNodeHasValidBounds(part, errorCommonLabel, expectedBounds, true, false);
    }

    protected Rectangle assertExecutionHasValidLogicalBounds(ExecutionEditPart part, Rectangle expectedBounds, boolean fullTest) {
        String errorCommonLabel = "The execution named " + part.getNodeLabel().getText();
        return assertSequenceNodeHasValidBounds(part, errorCommonLabel, expectedBounds, fullTest, false);
    }

    protected Rectangle assertExecutionHasValidScreenBounds(ExecutionEditPart part, Rectangle expectedBounds) {
        String errorCommonLabel = "The execution named " + part.getNodeLabel().getText();
        return assertSequenceNodeHasValidBounds(part, errorCommonLabel, expectedBounds, true, true);
    }

    protected Rectangle assertExecutionHasValidScreenBounds(String lifeline, int index, Rectangle expectedBounds) {
        return assertExecutionHasValidScreenBounds(lifeline, index, expectedBounds, true);
    }

    protected Rectangle assertExecutionHasValidScreenBounds(String lifeline, int index, Rectangle expectedBounds, boolean fullTest) {
        String errorCommonLabel = "The execution index " + index + " on lifeline " + lifeline;
        ExecutionEditPart executionEditPart = getExecutionEditPart(lifeline, index);
        assertNotNull(errorCommonLabel + " has not been found", executionEditPart);
        return assertSequenceNodeHasValidBounds(executionEditPart, errorCommonLabel, expectedBounds, fullTest, true);
    }

    protected Rectangle assertExecutionHasValidLogicalBounds(String lifeline, int index, Rectangle expectedBounds, boolean fullTest) {
        String errorCommonLabel = "The execution index " + index + " on lifeline " + lifeline;
        ExecutionEditPart executionEditPart = getExecutionEditPart(lifeline, index);
        assertNotNull(errorCommonLabel + " has not been found", executionEditPart);
        return assertSequenceNodeHasValidBounds(executionEditPart, errorCommonLabel, expectedBounds, fullTest, false);
    }

    protected Rectangle assertStateHasValidLogicalBounds(StateEditPart part, Rectangle expectedBounds, boolean fullTest) {
        String errorCommonLabel = "The state named " + part.getNodeLabel().getText();
        return assertSequenceNodeHasValidBounds(part, errorCommonLabel, expectedBounds, fullTest, false);
    }

    protected Rectangle assertStateHasValidLogicalBounds(StateEditPart part, Rectangle expectedBounds) {
        String errorCommonLabel = "The state named " + part.getNodeLabel().getText();
        return assertSequenceNodeHasValidBounds(part, errorCommonLabel, expectedBounds, true, false);
    }

    protected Rectangle assertStateHasValidScreenBounds(StateEditPart part, Rectangle expectedBounds, boolean fullTest) {
        String errorCommonLabel = "The state named " + part.getNodeLabel().getText();
        return assertSequenceNodeHasValidBounds(part, errorCommonLabel, expectedBounds, fullTest, true);
    }

    protected Rectangle assertStateHasValidScreenBounds(StateEditPart part, Rectangle expectedBounds) {
        String errorCommonLabel = "The state named " + part.getNodeLabel().getText();
        return assertSequenceNodeHasValidBounds(part, errorCommonLabel, expectedBounds, true, true);
    }

    protected Rectangle assertStateHasValidLogicalBounds(SWTBotGefEditPart bot, Rectangle expectedBounds, boolean fullTest) {
        Assert.assertTrue(bot.part() instanceof StateEditPart);
        return assertStateHasValidLogicalBounds((StateEditPart) bot.part(), expectedBounds, fullTest);
    }

    protected Rectangle assertStateHasValidLogicalBounds(SWTBotGefEditPart bot, Rectangle expectedBounds) {
        Assert.assertTrue(bot.part() instanceof StateEditPart);
        return assertStateHasValidLogicalBounds((StateEditPart) bot.part(), expectedBounds, true);
    }

    protected Rectangle assertStateHasValidScreenBounds(SWTBotGefEditPart bot, Rectangle expectedBounds, boolean fullTest) {
        Assert.assertTrue(bot.part() instanceof StateEditPart);
        return assertStateHasValidScreenBounds((StateEditPart) bot.part(), expectedBounds, fullTest);
    }

    protected Rectangle assertStateHasValidScreenBounds(SWTBotGefEditPart bot, Rectangle expectedBounds) {
        Assert.assertTrue(bot.part() instanceof StateEditPart);
        return assertStateHasValidScreenBounds((StateEditPart) bot.part(), expectedBounds, true);
    }

    protected Rectangle assertSequenceNodeHasValidBounds(ISequenceEventEditPart sequenceNodeEditPart, String errorCommonLabel, Rectangle expectedBounds, boolean fullTest, boolean screen) {
        checkRange(errorCommonLabel, expectedBounds, screen, sequenceNodeEditPart);

        Rectangle bounds = getSequenceNodeBounds(sequenceNodeEditPart, screen);

        assertEquals(errorCommonLabel + " is not displayed at the expected vertical position", expectedBounds.y, bounds.y, 2);
        assertEquals(errorCommonLabel + " is not displayed with the expected height", expectedBounds.height, bounds.height, 2);

        if (fullTest) {
            assertEquals(errorCommonLabel + " is not displayed at the expected horizontal position", expectedBounds.x, bounds.x, 1);
            assertEquals(errorCommonLabel + " is not displayed with the expected width", expectedBounds.width, bounds.width, 1);
        }

        return bounds;
    }

    protected Rectangle assertNamedMessageHasValidLogicalBounds(String message, Rectangle expectedBounds, boolean isLeftToRight) {
        return assertNamedMessageHasValidLogicalBounds(message, expectedBounds, isLeftToRight, true);
    }

    protected Rectangle assertNamedMessageHasValidLogicalBounds(String message, Rectangle expectedBounds, boolean isLeftToRight, boolean fullTest) {
        SequenceMessageEditPart smep = getSequenceMessageEditPart(message);
        String errorLabel = "the message named " + message;

        return assertMessageHasValidBounds(smep, errorLabel, expectedBounds, isLeftToRight, fullTest, false);
    }

    protected Rectangle assertNamedMessageHasValidScreenBounds(String message, Rectangle expectedBounds, boolean isLeftToRight) {
        return assertNamedMessageHasValidScreenBounds(message, expectedBounds, isLeftToRight, true);
    }

    protected Rectangle assertNamedMessageHasValidScreenBounds(String message, Rectangle expectedBounds, boolean isLeftToRight, boolean fullTest) {
        SequenceMessageEditPart smep = getSequenceMessageEditPart(message);
        String errorLabel = "the message named " + message;

        return assertMessageHasValidBounds(smep, errorLabel, expectedBounds, isLeftToRight, fullTest, true);
    }

    protected Rectangle assertMessageHasValidScreenBounds(SequenceMessageEditPart smep, Rectangle expectedBounds, boolean isLeftToRight, boolean fullTest) {
        String errorLabel = "the message";
        return assertMessageHasValidBounds(smep, errorLabel, expectedBounds, isLeftToRight, fullTest, true);
    }

    protected Rectangle assertReturnMessageHasValidLogicalBounds(String lifeline, int index, Rectangle expectedBounds, boolean isLeftToRight) {
        return assertReturnMessageHasValidLogicalBounds(lifeline, index, expectedBounds, isLeftToRight, true);
    }

    protected Rectangle assertReturnMessageHasValidLogicalBounds(String lifeline, int index, Rectangle expectedBounds, boolean isLeftToRight, boolean fullTest) {
        SequenceMessageEditPart message = getReturnSyncCall(lifeline, index);
        String errorLabel = "the return message on the execution on " + lifeline + " index " + index;

        return assertMessageHasValidBounds(message, errorLabel, expectedBounds, isLeftToRight, fullTest, false);
    }

    protected Rectangle assertReturnMessageHasValidScreenBounds(String lifeline, int index, Rectangle expectedBounds, boolean isLeftToRight) {
        return assertReturnMessageHasValidScreenBounds(lifeline, index, expectedBounds, isLeftToRight, true);
    }

    protected Rectangle assertReturnMessageHasValidScreenBounds(String lifeline, int index, Rectangle expectedBounds, boolean isLeftToRight, boolean fullTest) {
        SequenceMessageEditPart message = getReturnSyncCall(lifeline, index);
        String errorLabel = "the return message on the execution on " + lifeline + " index " + index;

        return assertMessageHasValidBounds(message, errorLabel, expectedBounds, isLeftToRight, fullTest, true);
    }

    private Rectangle assertMessageHasValidBounds(SequenceMessageEditPart message, String errorLabel, Rectangle expectedBounds, boolean isLeftToRight, boolean fullTest, boolean screen) {
        String fPoint = "The first bendpoint of ";
        String lPoint = "The last bendpoint of ";

        checkRange(errorLabel, expectedBounds, screen, message);

        Point fBendpointScreenPos = getSequenceMessageFirstBendpointScreenPosition(message);
        Point lBendpointScreenPos = getSequenceMessageLastBendpointScreenPosition(message);

        if (!screen) {
            GraphicalHelper.screen2logical(fBendpointScreenPos, message);
            GraphicalHelper.screen2logical(lBendpointScreenPos, message);
        }

        Point expectedStart = isLeftToRight ? expectedBounds.getTopLeft() : expectedBounds.getTopRight();
        Point expectedEnd = isLeftToRight ? expectedBounds.getBottomRight() : expectedBounds.getBottomLeft();

        String vPos = " is not displayed at the expected vertical position";
        assertEquals(fPoint + errorLabel + vPos, expectedStart.y, fBendpointScreenPos.y, 2);
        assertEquals(lPoint + errorLabel + vPos, expectedEnd.y, lBendpointScreenPos.y, 2);
        if (fullTest) {
            String hPos = " is not displayed at the expected horizontal position";
            assertEquals(fPoint + errorLabel + hPos, expectedStart.x, fBendpointScreenPos.x, 2);
            assertEquals(lPoint + errorLabel + hPos, expectedEnd.x, lBendpointScreenPos.x, 2);
        }

        Rectangle rectangle = isLeftToRight ? new Rectangle(fBendpointScreenPos, lBendpointScreenPos) : new Rectangle(lBendpointScreenPos, fBendpointScreenPos);
        Message msg = (Message) message.getISequenceEvent();
        if (msg.isReflective() && expectedBounds.width != 0 && fullTest) {
            Point secondBendpointScreenPos = getSequenceMessageBendpointScreenPosition(message, 1);
            Point thirdBendpointScreenPos = getSequenceMessageBendpointScreenPosition(message, 2);
            if (!screen) {
                GraphicalHelper.screen2logical(secondBendpointScreenPos, message);
                GraphicalHelper.screen2logical(thirdBendpointScreenPos, message);
            }
            assertEquals("Reflexive message should have a vertical side.", secondBendpointScreenPos.x, thirdBendpointScreenPos.x, 1);
            assertEquals("Reflexive message does not have the good width.", expectedBounds.width, secondBendpointScreenPos.x - fBendpointScreenPos.x, 1);

            rectangle.width = expectedBounds.width;
        }
        return rectangle;
    }

    private void checkRange(String errorLabel, Rectangle expectedBounds, boolean screen, ISequenceEventEditPart isep) {
        ISequenceEvent ise = isep.getISequenceEvent();
        Range range = ise.getVerticalRange();

        Point top = expectedBounds.getTop().getCopy();
        Point bottom = expectedBounds.getBottom().getCopy();

        if (screen) {
            GraphicalHelper.screen2logical(top, isep);
            GraphicalHelper.screen2logical(bottom, isep);
        }

        assertEquals(errorLabel + "do not have the good range lower bound.", top.y, range.getLowerBound(), 1);
        assertEquals(errorLabel + "do not have the good range upper bound.", bottom.y, range.getUpperBound(), 2);

        if (ise instanceof Message) {
            SequenceMessageViewQuery query = new SequenceMessageViewQuery(((Message) ise).getNotationEdge());
            Range rangeFromSource = query.getVerticalRange(true);
            Range rangeFromTarget = query.getVerticalRange(false);

            assertEquals(errorLabel + " do  not have consistent range lower bound source/target", rangeFromSource.getLowerBound(), rangeFromTarget.getLowerBound(), 1);
            assertEquals(errorLabel + " do  not have consistent range upper bound source/target", rangeFromSource.getUpperBound(), rangeFromTarget.getUpperBound(), 1);
        }
    }

    // bounds methods
    private Rectangle getBounds(IGraphicalEditPart editPart, boolean screen) {
        Rectangle bounds = editPart.getFigure().getBounds().getCopy();
        if (screen) {
            GraphicalHelper.logical2screen(bounds, editPart);
        }
        return bounds;
    }

    /**
     * Returns the bounds of the first edit part with requested name, relative
     * to screen or logical regarding the screen parameter.
     * 
     * @param name
     *            name of the edit part.
     * @param screen
     *            relative to the screen or logical.
     * @return the bounds of the first edit part with requested name.
     */
    public Rectangle getBounds(String name, boolean screen) {
        SWTBotGefEditPart editPart = editor.getEditPart(name);
        assertTrue("There is no graphical edit part named " + name, editPart.part() instanceof IGraphicalEditPart);

        IGraphicalEditPart part = (IGraphicalEditPart) editPart.part();
        return getBounds(part, screen);
    }

    protected Point getLogicalPosition(String name) {
        return getBounds(name, false).getLocation();
    }

    protected Point getScreenPosition(String name) {
        return getBounds(name, true).getLocation();
    }

    protected Dimension getLogicalSize(String name) {
        return getBounds(name, false).getSize();
    }

    protected Dimension getScreenSize(String name) {
        return getBounds(name, true).getSize();
    }

    protected Dimension getSize(String name, double zoomAmount) {
        Dimension size = getLogicalSize(name);
        return size.getScaled(zoomAmount);
    }

    private Rectangle getExecutionBounds(String lifelineLabel, int index, boolean screen) {
        ExecutionEditPart executionEditPart = getExecutionEditPart(lifelineLabel, index);
        assertNotNull("The execution index " + index + " on lifeline " + lifelineLabel + " has not been found", executionEditPart);

        return getBounds(executionEditPart, screen);
    }

    protected Rectangle getSequenceNodeBounds(ISequenceEventEditPart part, boolean screen) {
        return getBounds(part, screen);
    }

    protected Rectangle getExecutionScreenBounds(ExecutionEditPart part) {
        return getSequenceNodeBounds(part, true);
    }

    protected Rectangle getStateScreenBounds(StateEditPart part) {
        return getSequenceNodeBounds(part, true);
    }

    protected Rectangle getExecutionLogicalBounds(String lifelineLabel, int index) {
        return getExecutionBounds(lifelineLabel, index, false);
    }

    protected Rectangle getExecutionScreenBounds(String lifelineLabel, int index) {
        return getExecutionBounds(lifelineLabel, index, true);
    }

    protected Dimension getExecutionLogicalDimension(String lifelineLabel, int index) {
        return getExecutionLogicalBounds(lifelineLabel, index).getSize();
    }

    protected Dimension getExecutionScreenDimension(String lifelineLabel, int index) {
        return getExecutionScreenBounds(lifelineLabel, index).getSize();
    }

    protected Point getExecutionLogicalPosition(String lifelineLabel, int index) {
        return getExecutionLogicalBounds(lifelineLabel, index).getLocation();
    }

    protected Point getExecutionScreenPosition(String lifelineLabel, int index) {
        return getExecutionScreenBounds(lifelineLabel, index).getLocation();
    }

    protected Dimension getExecutionDimension(String lifelineLabel, int index, double zoomAmount) {
        Dimension dimension = getExecutionLogicalDimension(lifelineLabel, index);
        dimension.scale(zoomAmount);
        return dimension;
    }

    protected int getLifelineScreenX(String lifelineName) {
        int lifelineX = getBounds(lifelineName, true).getCenter().x;
        return lifelineX;
    }

    protected SWTBotGefEditPart getBotEditPart(ExecutionEditPart parentExec) {
        return editor.getEditPart(parentExec.getFigure().getBounds().getCopy().getCenter(), ExecutionEditPart.class);
    }

    protected void changeCollapseFilterActivation() {
        ICondition done = new OperationDoneCondition();
        SWTBotTable filterTable;
        try {
            filterTable = bot.viewByTitle("Outline").bot().table(1);
        } catch (IndexOutOfBoundsException e) {
            filterTable = bot.viewByTitle("Outline").bot().table(0);
        }
        filterTable.click(0, 0);
        bot.waitUntil(done);
    }

    @Override
    protected void tearDown() throws Exception {
        if (editor != null) {
            // Many tests pass the editor in full screen (maximized) and do not
            // restore the initial state. This can have side effects on the
            // following tests. To avoid this, a restore is force here.
            editor.restore();
        }
        super.tearDown();
    }
}
