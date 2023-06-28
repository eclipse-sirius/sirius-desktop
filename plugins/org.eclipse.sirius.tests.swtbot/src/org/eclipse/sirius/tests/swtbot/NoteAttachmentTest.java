/*******************************************************************************
 * Copyright (c) 2017, 2023 Obeo and others.
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
package org.eclipse.sirius.tests.swtbot;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusTextEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Verifies that note attachments (for purely graphical elements: notes, texts, representation links), which are not
 * handled directly/only by Sirius but inherited from GMF, work correctly. This class also verifies that when moving
 * element in diagram (inside, outside container for example), that attached purely graphical elements (notes, texts,
 * representation links) still attached
 */
@SuppressWarnings("nls")
public class NoteAttachmentTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String DATA_DIR = "data/unit/purelyGraphicalElementsBehavior/";

    private static final String MODEL_FILE = "My.ecore";

    private static final String VP_FILE = "My.odesign";

    private static final String REPR_FILE = "representations.aird";

    record EdgeInfo(NodeInfo sourceInfo, NodeInfo targetInfo) {
        @Override
        public String toString() {
            return "(" + sourceInfo + " => " + targetInfo + ")";
        }
    }

    record NodeInfo(Optional<String> description, Optional<String> name) {
        public static NodeInfo generateInfo(GraphicalEditPart editPart) {
            Optional<String> description;
            Optional<String> name;

            if (editPart.getModel() instanceof Shape shape) {
                description = Optional.of(shape.getDescription());
            } else {
                description = Optional.empty();
            }

            View view = (View) editPart.getModel();
            if (view.getElement() instanceof DRepresentationElement siriusElement) {
                name = Optional.of(siriusElement.getName());
            } else {
                name = Optional.empty();
            }

            return new NodeInfo(description, name);
        }

        @Override
        public String toString() {
            if (name.isEmpty() && description.isEmpty()) {
                // note: it could probably be something other than NoteAttachment,
                // but in this case and because it's a test, it will be a NoteAttachment
                return "[NoteAttachment]";
            } else {
                return (name.orElse("") + " " + description.orElse("")).trim();
            }
        }
    }

    // information about test data
    record TestDataInfo(String representationName, String representationDescriptionName) {
    }

    private static final TestDataInfo SCENARIO_A_CONTAINER = new TestDataInfo( //
            "scenarioA-diagramWithContainerDnDAndSameMapping-full", //
            "DiagramWithContainerDnDAndSameMapping");

    private static final TestDataInfo SCENARIO_A_NODE = new TestDataInfo( //
            "scenarioA-diagramWithNodeDnDAndSameMapping-full", //
            "DiagramWithNodeDnDAndSameMapping");

    private static final TestDataInfo SCENARIO_A_EDGE = new TestDataInfo( //
            "scenarioA-diagramWithEdgeDnDAndSameMapping-full", //
            "DiagramWithNodeDnDAndSameMapping");

    private static final TestDataInfo SCENARIO_B = new TestDataInfo( //
            "scenarioB-diagramWithContainerDnDAndSameMapping", //
            "DiagramWithContainerDnDAndSameMapping");

    private static final TestDataInfo SCENARIO_C = new TestDataInfo( //
            "scenarioC-diagramWithNodeDnDAndMappingChange-full", //
            "DiagramWithNodeDnDAndMappingChange");

    private static final TestDataInfo SCENARIO_D = new TestDataInfo( //
            "scenarioD-diagramWithFakeNodeDnD", //
            "DiagramWithFakeNodeDnD");

    private static final TestDataInfo SCENARIO_E = new TestDataInfo( //
            "scenarioE-diagramWithUnsynchronizedHierarchy", //
            "DiagramWithUnsynchronizedHierarchy");

    private static final TestDataInfo SCENARIO_F = new TestDataInfo( //
            "scenarioF-diagramWithContainerDnDAndSameMapping-full", //
            "DiagramWithContainerDnDAndSameMapping");

    private static final TestDataInfo SCENARIO_G = new TestDataInfo( //
            "scenarioG-diagramWithNodeAndBorderNodeDnDAndSameMapping-full", //
            "DiagramWithNodeAndBorderNodeDnDAndSameMapping");

    private static final TestDataInfo SCENARIO_H = new TestDataInfo( //
            "scenarioH-diagramWithNodeAndBorderNodeDnDAndMappingChange-full", //
            "DiagramWithNodeAndBorderNodeDnDAndMappingChange");

    private static final TestDataInfo SCENARIO_I = new TestDataInfo( //
            "scenarioI-diagramWithReconnect-full", //
            "DiagramWithReconnect");

    private static final TestDataInfo SCENARIO_K = new TestDataInfo( //
            "ScenarioK-diagramWithCompartmentDnDAndSameMapping", //
            "DiagramWithCompartmentDnDAndSameMapping");

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_DIR, MODEL_FILE, VP_FILE, REPR_FILE);
        sessionAirdResource = new UIResource(designerProject, REPR_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Initialize representation with `info`
     */
    private void init(TestDataInfo info) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation( //
                localSession.getOpenedSession(), // session
                info.representationDescriptionName, //
                info.representationName, //
                DDiagram.class // representation type
        );
    }

    /**
     * @return Edit Part of all notes, texts and representation links in given parameter `editPart`
     */
    private List<String> getNotesTypes(EditPart editPart) {
        List<EditPart> elements = editPart.getChildren();
        return elements.stream().filter(element -> {
            View gmfView = (View) element.getModel();
            return ViewType.NOTE.equals(gmfView.getType()) || ViewType.TEXT.equals(gmfView.getType());
        }).map(note -> {
            View gmfView = (View) note.getModel();
            return gmfView.getType();
        }).toList();
    }

    /**
     * @return Edit Part of all notes attachments in the diagram
     */
    private List<ConnectionEditPart> getNotesAttachmentsEditPart() {
        return editor.getConnectionsEditPart().stream().filter(edge -> {
            if (edge.part().getModel() instanceof Connector connector) {
                return ViewType.NOTEATTACHMENT.equals(connector.getType());
            } else {
                return false;
            }
        }).map(edgeBot -> edgeBot.part()).toList();
    }

    /**
     * @return information about source and target of all notes attachment
     */
    private List<EdgeInfo> getNoteAttachmentInfo() {
        return getNotesAttachmentsEditPart().stream().map(edge -> {
            NodeInfo source = NodeInfo.generateInfo((GraphicalEditPart) edge.getSource());
            NodeInfo target = NodeInfo.generateInfo((GraphicalEditPart) edge.getTarget());
            return new EdgeInfo(source, target);
        }).collect(Collectors.toList());
    }

    /**
     * Returns a predicate which checks that the element is not in the given list `otherEdgesInfo`
     */
    private Predicate<EdgeInfo> notIn(List<EdgeInfo> otherEdgesInfo) {
        return Predicate.not((EdgeInfo edgeInfo) -> {
            return otherEdgesInfo.stream().anyMatch(otherEdgeInfo -> otherEdgeInfo.equals(edgeInfo));
        });
    }

    /**
     * Check if information about edges are identical (same information for source and target)
     */
    private void checkDiffEdgesInfo(String message, List<EdgeInfo> expectedEdgesInfo, List<EdgeInfo> edgesInfo) {
        List<EdgeInfo> missingEdgesInfo = expectedEdgesInfo.stream() //
                .filter(notIn(edgesInfo)) //
                .collect(Collectors.toList());

        List<EdgeInfo> excessEdgesInfo = edgesInfo.stream() //
                .filter(notIn(expectedEdgesInfo)) //
                .collect(Collectors.toList());

        if (!missingEdgesInfo.isEmpty() && !excessEdgesInfo.isEmpty()) {
            message += ": { Missing edges: " + missingEdgesInfo + ", ";
            message += "Excess edges : " + excessEdgesInfo + " }";
            fail(message);
        } else if (!missingEdgesInfo.isEmpty()) {
            message += ": { Missing edges: " + missingEdgesInfo + " }";
            fail(message);
        } else if (!excessEdgesInfo.isEmpty()) {
            message += ": { Excess edges : " + excessEdgesInfo + " }";
            fail(message);
        } // else: ok, no difference
    }

    /**
     * Drag & Drop element in background of diagram (at position (2, 2), we assume there is no element here)
     */
    private void moveOut(String sourceName, Class<? extends EditPart> sourceType) {
        SWTBotGefEditPart editPart = editor.getEditPart(sourceName, sourceType);
        editor.drag(editPart, new Point(2, 2));
    }

    /**
     * Drag & Drop element in container (at position (2, 2) of element, we assume there is no sub element here)
     */
    private void moveIn(String sourceName, Class<? extends EditPart> sourceType, String targetName, Class<? extends EditPart> targetType) {
        SWTBotGefEditPart source = editor.getEditPart(sourceName, sourceType);
        SWTBotGefEditPart target = editor.getEditPart(targetName, targetType);

        Point targetPoint = editor.getAbsoluteLocation((GraphicalEditPart) target.part());
        editor.drag(source, targetPoint.getTranslated(2, 2));
    }

    /**
     * Scenario A (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>`p2` is container in other container</li>
     * <li>`p2` have attached notes</li>
     * <li>move `p2` in diagram background</li>
     * <li>`p2` have same mapping in diagram background</li>
     * </ul>
     */
    public void testMoveContainerOutSameMapping() {
        init(SCENARIO_A_CONTAINER);

        var oldEdges = getNoteAttachmentInfo();

        moveOut("p2", AbstractDiagramContainerEditPart.class);

        var newEdges = getNoteAttachmentInfo();

        checkDiffEdgesInfo("At least one note attachment changed during move", oldEdges, newEdges);
    }

    /**
     * Scenario A (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>`ClassA` and `ClassB` are node in container</li>
     * <li>`element` is edge between `ClassA` and `ClassB`</li>
     * <li>`element` have attached notes</li>
     * <li>move `ClassA` in diagram background</li>
     * <li>`ClassA` have same mapping in diagram background</li>
     * </ul>
     */
    public void testMoveNodeOutWithEdgeSameMapping() {
        init(SCENARIO_A_EDGE);

        var oldEdges = getNoteAttachmentInfo();

        moveOut("ClassA", AbstractDiagramNodeEditPart.class);

        var newEdges = getNoteAttachmentInfo();

        checkDiffEdgesInfo("At least one note attachment changed during move", oldEdges, newEdges);
    }

    /**
     * Scenario A (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>`ClassA` is node in container</li>
     * <li>`ClassA` have attached notes</li>
     * <li>move `ClassA` in diagram background</li>
     * <li>`ClassA` have same mapping in diagram background</li>
     * </ul>
     */
    public void testMoveNodeOutSameMapping() {
        init(SCENARIO_A_NODE);

        var oldEdges = getNoteAttachmentInfo();

        moveOut("ClassA", AbstractDiagramNodeEditPart.class);

        var newEdges = getNoteAttachmentInfo();

        checkDiffEdgesInfo("At least one note attachment changed during move", oldEdges, newEdges);
    }

    /**
     * Scenario B (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>`p11` is container in container</li>
     * <li>`p11` contain some elements with attached notes</li>
     * <li>move `p11` in another container</li>
     * <li>`p11` have same mapping in diagram background</li>
     * </ul>
     */
    public void testMoveContainerSameMapping() {
        init(SCENARIO_B);

        var oldEdges = getNoteAttachmentInfo();

        moveIn("p11", AbstractDiagramContainerEditPart.class, //
                "p2", AbstractDiagramContainerEditPart.class);

        var newEdges = getNoteAttachmentInfo();

        checkDiffEdgesInfo("At least one note attachment changed during move", oldEdges, newEdges);
    }

    /**
     * Scenario C (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>`ClassA` is node in container</li>
     * <li>`ClassA` have attached notes</li>
     * <li>move `ClassA` in diagram background</li>
     * <li>`ClassA` have different mapping in diagram background</li>
     * </ul>
     */
    public void testMoveNodeOutDiffMapping() {
        init(SCENARIO_C);

        var oldEdges = getNoteAttachmentInfo();

        moveOut("ClassA", AbstractDiagramNodeEditPart.class);

        var newEdges = getNoteAttachmentInfo();

        checkDiffEdgesInfo("At least one note attachment changed during move", oldEdges, newEdges);
    }

    private void testMoveOutUnsync(boolean prefRmNote) {
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), prefRmNote);

        init(SCENARIO_D);

        DiagramEditPart diagram = editor.getDiagramEditPart();

        // elements before
        List<String> oldNotes = getNotesTypes(diagram);
        List<ConnectionEditPart> oldEdges = getNotesAttachmentsEditPart();

        assertEquals("Wrong number of notes/representation links (in the initial data)", //
                6, oldNotes.stream().filter(noteType -> ViewType.NOTE.equals(noteType)).count());
        assertEquals("Wrong number of texts (in the initial data)", //
                2, oldNotes.stream().filter(noteType -> ViewType.TEXT.equals(noteType)).count());
        assertEquals("Wrong number of notes attachments (in the initial data)", 8, oldEdges.size());

        // move
        moveOut("ClassA", AbstractDiagramNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        // elements after
        List<String> newNotes = getNotesTypes(diagram);
        List<ConnectionEditPart> newEdges = getNotesAttachmentsEditPart();
        if (prefRmNote) {
            assertEquals("Wrong number of notes/representation links after moving `ClassA` (with pref remove note enabled)", //
                    0, newNotes.stream().filter(noteType -> ViewType.NOTE.equals(noteType)).count());
            assertEquals("Wrong number of texts after moving `ClassA` (with pref remove note disabled)", //
                    0, newNotes.stream().filter(noteType -> ViewType.TEXT.equals(noteType)).count());
        } else {
            assertEquals("Wrong number of notes/representation links after moving `ClassA` (with pref remove note disabled)", //
                    6, newNotes.stream().filter(noteType -> ViewType.NOTE.equals(noteType)).count());
            assertEquals("Wrong number of texts after moving `ClassA` (with pref remove note disabled)", //
                    2, newNotes.stream().filter(noteType -> ViewType.TEXT.equals(noteType)).count());
        }
        assertEquals("Wrong number of notes attachments after moving `ClassA`", 0, newEdges.size());
    }

    /**
     * Scenario D (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>Diagram is unsynchronized</li>
     * <li>`ClassA` is node in container</li>
     * <li>`ClassA` have attached notes</li>
     * <li>move `ClassA` in diagram background</li>
     * <li>attached note will be removed</li>
     * </ul>
     */
    public void testMoveOutUnsyncRmNote() {
        testMoveOutUnsync(true);
    }

    /**
     * Scenario D (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>Diagram is unsynchronized</li>
     * <li>`ClassA` is node in container</li>
     * <li>`ClassA` have attached notes</li>
     * <li>move `ClassA` in diagram background</li>
     * <li>attached note will be kept</li>
     * </ul>
     */
    public void testMoveOutUnsyncKeepNote() {
        testMoveOutUnsync(false);
    }

    /**
     * Scenario E (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>Diagram is unsynchronized</li>
     * <li>In real model :
     * <ul>
     * <li>`p1` contain `p1-1`</li>
     * <li>`p1-1` contain `p1-1-1`</li>
     * <li>`p1-1-1` contain `Class A`</li>
     * </ul>
     * <li>Diagram represent only the node `ClassA` in the container `p1`</li>
     * <li>Move semantic (real model) p1-1 in p1</li>
     * </ul>
     */
    public void testMoveSemanticUnsync() {
        init(SCENARIO_E);

        // edges before
        var oldEdges = getNoteAttachmentInfo();

        // move
        var projectResource = new UIResource(designerProject, MODEL_FILE);
        SWTBotTreeItem model = localSession.getSemanticResourceNode(projectResource);
        SWTBotTreeItem p11 = model.expandNode("root") //
                .expandNode("scenarioE") //
                .expandNode("p1") //
                .getNode("p1-1");

        Point p1Location = editor.getLocation("p1", AbstractDiagramContainerEditPart.class);
        p1Location.translate(2, 2);
        var p1LocationSWT = new org.eclipse.swt.graphics.Point(p1Location.x, p1Location.y);
        p11.dragAndDrop(editor.getCanvas(), p1LocationSWT);

        // edges after
        var newEdges = getNoteAttachmentInfo();

        checkDiffEdgesInfo("At least one note attachment changed during move", oldEdges, newEdges);
    }

    /**
     * Scenario F (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>`p2` is in `p1`</li>
     * <li>`p2` contains note</li>
     * <li>move `p2` in diagram background</li>
     * </ul>
     */
    public void testMoveOutWithInternNote() {
        init(SCENARIO_F);

        // elements before
        SWTBotGefEditPart p2 = editor.getEditPart("p2", AbstractDiagramContainerEditPart.class);
        List<SWTBotGefEditPart> oldP2RawContent = p2.children();
        List<SWTBotGefEditPart> oldNotes = oldP2RawContent.get(1).children();
        assertEquals("Wrong number of notes/texts/representation links in p2 (in the initial data)", 3, oldNotes.size());

        // move
        editor.drag(p2, 2, 2);

        // elements after
        SWTBotGefEditPart p2new = editor.getEditPart("p2", AbstractDiagramContainerEditPart.class);
        List<SWTBotGefEditPart> newP2RawContent = p2new.children();
        List<SWTBotGefEditPart> newNotes = newP2RawContent.get(1).children();
        assertEquals("Wrong number of notes/texts/representation links in p2 after move", 3, newNotes.size());
    }

    /**
     * Scenario G (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>`ClassA` is in `p1`</li>
     * <li>`ClassA` have border node</li>
     * <li>`ClassA` have attached note</li>
     * <li>border node of `ClassA` have attached note</li>
     * <li>move `ClassA` in diagram background</li>
     * </ul>
     */
    public void testMoveWithBorderNodeSameMapping() {
        init(SCENARIO_G);

        var oldEdges = getNoteAttachmentInfo();

        moveOut("ClassA", AbstractDiagramNodeEditPart.class);

        var newEdges = getNoteAttachmentInfo();

        checkDiffEdgesInfo("At least one note attachment changed during move", oldEdges, newEdges);
    }

    /**
     * Scenario H (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>`ClassA` is in `p1`</li>
     * <li>`ClassA` have border node</li>
     * <li>`ClassA` have attached note</li>
     * <li>border node of `ClassA` have attached note</li>
     * <li>move `ClassA` in diagram background</li>
     * <li>`ClassA` have different mapping in `p1` and in background diagram</li>
     * </ul>
     */
    public void testMoveWithBorderNodeDiffMapping() {
        init(SCENARIO_H);

        var oldEdges = getNoteAttachmentInfo();
        assertEquals("Missing note attachement in diagram at start", 8, oldEdges.size());

        moveOut("ClassA", AbstractDiagramNodeEditPart.class);

        var newEdges = getNoteAttachmentInfo();

        assertTrue("Expected broken note attachment when there are several mappings", newEdges.isEmpty());
    }

    /**
     * Scenario I (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>`ClassA` is pin of `p1`</li>
     * <li>`ClassB` is pin of `p2`</li>
     * <li>`elementMapping` is edge from `ClassA` to `ClassB`</li>
     * <li>`elementMapping` have attached note</li>
     * <li>move pin `ClassB` in `p3`</li>
     * </ul>
     */
    public void testMoveConnectedPin() {
        init(SCENARIO_I);

        var oldEdges = getNoteAttachmentInfo();

        moveIn("ClassB", AbstractDiagramBorderNodeEditPart.class, "p3", AbstractDiagramContainerEditPart.class);

        var newEdges = getNoteAttachmentInfo();

        checkDiffEdgesInfo("At least one note attachment changed during move", oldEdges, newEdges);
    }

    /**
     * Scenario J (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>`ClassA` is pin of `p1`</li>
     * <li>`ClassB` and `ClassC` is pin of `p2`</li>
     * <li>`elementMapping` is edge from `ClassA` to `ClassB`</li>
     * <li>`elementMapping` have attached note</li>
     * <li>move target of `elementMapping` from `ClassB` to `ClassC`</li>
     * </ul>
     */
    public void testMoveEdge() {
        // same representation for scenario I and J
        init(SCENARIO_I);

        SWTBotGefEditPart classA = editor.getEditPart("ClassA", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart classB = editor.getEditPart("ClassB", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart classC = editor.getEditPart("ClassC", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefConnectionEditPart elementMapping = editor.getConnectionEditPart(classA, classB).get(0);

        // edges before
        var oldEdges = getNoteAttachmentInfo();

        // move
        editor.select(elementMapping);
        if (elementMapping.part().getFigure() instanceof ViewEdgeFigure figure) {
            PointList pointList = figure.getPoints();
            Point target_source = pointList.getLastPoint().getCopy();
            Point target_target = editor.getLocation(classC).getTranslated(1, 1);
            editor.drag(target_source, target_target);
        } else {
            fail("Invalid figure for edge");
        }

        // edges after
        var newEdges = getNoteAttachmentInfo();

        checkDiffEdgesInfo("At least one note attachment changed during move", oldEdges, newEdges);
    }

    /**
     * Scenario K (see bugzilla 581811 specification for more information):
     * <ul>
     * <li>`p2` is in `p1`</li>
     * <li>`p2` contains note</li>
     * <li>`p2` have vertical stack layout</li>
     * <li>move `p2` in diagram background</li>
     * </ul>
     */
    public void testMoveOutCompartmentWithInternNote() {
        init(SCENARIO_K);

        // elements before
        SWTBotGefEditPart p2 = editor.getEditPart("p2", AbstractDiagramContainerEditPart.class);
        List<String> notesTypesBefore = getNotesTypes(p2.part());
        assertEquals("Wrong number of notes/representation links in p2 (in the initial data)", //
                2, notesTypesBefore.stream().filter(noteType -> ViewType.NOTE.equals(noteType)).count());
        assertEquals("Wrong number of texts (in the initial data)", //
                1, notesTypesBefore.stream().filter(noteType -> ViewType.TEXT.equals(noteType)).count());

        // move
        editor.drag(p2, 2, 2);

        // elements after
        SWTBotGefEditPart p2new = editor.getEditPart("p2", AbstractDiagramContainerEditPart.class);
        List<String> notesTypesAfter = getNotesTypes(p2new.part());
        assertEquals("Wrong number of notes/representation links in p2 after move", //
                2, notesTypesAfter.stream().filter(noteType -> ViewType.NOTE.equals(noteType)).count());
        assertEquals("Wrong number of texts in p2 after move", //
                1, notesTypesAfter.stream().filter(noteType -> ViewType.TEXT.equals(noteType)).count());
    }

    /**
     * Checks that Note attachments can be selected.
     * 
     * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=527391"
     */
    @SuppressWarnings("restriction")
    public void test_NoteAttachments_can_be_selected() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", " package entities", DDiagram.class);
        List<NoteAttachmentEditPart> notes = editor.getConnectionsEditPart().stream().map(SWTBotGefConnectionEditPart::part).filter(NoteAttachmentEditPart.class::isInstance)
                .map(NoteAttachmentEditPart.class::cast).collect(Collectors.toList());
        assertEquals("The test diagram should contain 2 note attachments", 2, notes.size());
        Optional<NoteAttachmentEditPart> attachmentToNote = notes.stream().filter(n -> n.getTarget() instanceof NoteEditPart).findFirst();
        assertTrue("Could not find the note attachment edit part", attachmentToNote.isPresent());
        Optional<NoteAttachmentEditPart> attachmentToText = notes.stream().filter(n -> n.getSource() instanceof TextEditPart).findFirst();
        assertTrue("Could not find the text attachment edit part", attachmentToText.isPresent());
        // Make sure the diagram's background is selected first.
        editor.click(new Point(10, 10));
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                ISelection sel = editor.getSelection();
                return (sel instanceof StructuredSelection) && ((StructuredSelection) sel).getFirstElement() == editor.getDiagramEditPart();
            }

            @Override
            public String getFailureMessage() {
                return "The diagram was not selected";
            }
        });
        // Select the note attachment
        editor.click(new Point(417, 111));
        bot.waitUntil(new CheckSelectedCondition(editor, attachmentToNote.get()));
        // Select the text attachment
        editor.click(new Point(433, 223));
        bot.waitUntil(new CheckSelectedCondition(editor, attachmentToText.get()));

    }

    /**
     * Checks that a Note attachment can be created between a Node and a Note.
     * 
     * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=581740"
     */
    public void test_NoteAttachmentCreation_betweenNodeAndNote() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", " package entities", DDiagram.class);
        // Create the note attachment
        createNoteAttachment("NewEClass2", DNodeListEditPart.class, "A note", SiriusNoteEditPart.class);

        // Check that a note attachment has been created.
        checkNoteAttachmentEditPartFrom("NewEClass2", 1);
    }

    /**
     * Checks that a Note attachment can be created between a Node and a Text.
     * 
     * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=581740"
     */
    public void test_NoteAttachmentCreation_betweenNodeAndText() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", " package entities", DDiagram.class);
        // Create the note attachment
        createNoteAttachment("NewEClass2", DNodeListEditPart.class, "A text", SiriusTextEditPart.class);

        // Check that a note attachment has been created.
        checkNoteAttachmentEditPartFrom("NewEClass2", 1);
    }

    /**
     * Checks that a Note attachment can not be created between a Node and another Node.
     * 
     * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=581740"
     */
    public void test_NoteAttachmentCreation_betweenNodeAndNode() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", " package entities", DDiagram.class);
        // Create the note attachment
        createNoteAttachment("NewEClass2", DNodeListEditPart.class, "A", DNodeListEditPart.class);

        // Check that a note attachment has been created.
        checkNoteAttachmentEditPartFrom("NewEClass2", 0);
    }

    /**
     * Checks that Note is deleted on all cases of indirect "deletion" (node, edge, another note attachment). Before
     * this fix, the behavior was not the same for edge and node.
     * 
     * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=581752"
     */
    public void test_Note_deletion() {
        List<String> namesOfNotesToCheck = Arrays.asList("NoteOnNode", "NoteOnEdge", "NoteOnNoteAttachment");
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", "ghostCase package entities", DDiagram.class);
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), true);
        for (String nameOfNoteToCheck : namesOfNotesToCheck) {
            checkNoteAttachmentEditPartFrom(nameOfNoteToCheck, 1);
        }
        // Select packageA
        editor.select(editor.getEditPart("packageA", AbstractDiagramContainerEditPart.class));
        bot.waitUntil(new CheckSelectedCondition(editor, "packageA", AbstractDiagramContainerEditPart.class));
        // Delete it
        deleteSelectedElement();
        SWTBotUtils.waitAllUiEvents();

        // Check that the note has been removed.
        for (String nameOfNoteToCheck : namesOfNotesToCheck) {
            try {
                editor.getEditPart(nameOfNoteToCheck, SiriusNoteEditPart.class);
                fail("The Note \"" + nameOfNoteToCheck + "\" should be deleted during the deletion of \"packageA\".");
            } catch (WidgetNotFoundException e) {
                // This is the expected behavior
            }
        }
    }

    private void createNoteAttachment(String sourceName, Class<? extends EditPart> expectedSourceClass, String targetName, Class<? extends EditPart> expectedTargetClass) {
        SWTBotGefEditPart source = editor.getEditPart(sourceName, expectedSourceClass);
        SWTBotGefEditPart target = editor.getEditPart(targetName, expectedTargetClass);

        Point sourcePoint = GraphicalHelper.getAbsoluteBoundsIn100Percent((IGraphicalEditPart) source.part()).getCenter();
        Point targetPoint = GraphicalHelper.getAbsoluteBoundsIn100Percent((IGraphicalEditPart) target.part()).getCenter();

        editor.activateTool("Note Attachment");
        editor.click(sourcePoint);
        editor.click(targetPoint);
    }

    @SuppressWarnings("restriction")
    private void checkNoteAttachmentEditPartFrom(String sourceName, int expectedNumberOfNoteAttachment) {
        SWTBotGefEditPart sourcePart = editor.getEditPart(sourceName).parent();
        assertEquals("Bad number of note attachment from " + sourceName, expectedNumberOfNoteAttachment, sourcePart.sourceConnections().size());
        for (int i = 0; i < expectedNumberOfNoteAttachment; i++) {
            SWTBotGefConnectionEditPart swtBotConnectionEditPart = sourcePart.sourceConnections().get(i);
            assertTrue("The connection from " + sourceName + ", at index " + i + " is not a note attachment.", swtBotConnectionEditPart.part() instanceof NoteAttachmentEditPart);
        }
    }
}
