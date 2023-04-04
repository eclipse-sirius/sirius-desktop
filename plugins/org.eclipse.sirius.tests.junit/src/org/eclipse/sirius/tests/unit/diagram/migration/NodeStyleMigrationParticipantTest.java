/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.migration;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.business.internal.migration.NodeStyleMigrationParticipant;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.EllipseEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NotationViewIDs;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SquareEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Séraphin Costa
 *
 */
public class NodeStyleMigrationParticipantTest extends SiriusDiagramTestCase {
    private static final String PATH = "/data/unit/migration/do_not_migrate/nodeStyle/";

    private static final String SESSION_RESOURCE_NAME = "representations.aird";

    private static final String SEMANTIC_MODEL_NAME = "My.ecore";

    private static final String SESSION_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME;

    private static final String SEMANTIC_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_MODEL_NAME;

    private static final String SESSION_RESOURCE_LOCAL_PATH = SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME;

    private static final String SEMANTIC_RESOURCE_LOCAL_PATH = SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_NAME;

    private record TestData(String uid, String gmfUid, String name, boolean boldState, boolean inconsistentStyle, boolean labelHaveStyle) {
    }

    private static final TestData testDatas[] = {
            new TestData("_YRCPgNIIEe2iY95l9G0Iag", "_YRx2YdIIEe2iY95l9G0Iag", "base", false, false, true), //
            new TestData("_yDhWINIIEe2iY95l9G0Iag", "_yDikQdIIEe2iY95l9G0Iag", "bold", true, false, true), //
            new TestData("_8NDK4NIIEe2iY95l9G0Iag", "_8NDx8dIIEe2iY95l9G0Iag", "resetok", false, false, true), //
            new TestData("_BlG0sNIJEe2iY95l9G0Iag", "_BlIp4dIJEe2iY95l9G0Iag", "resetfail", false, true, false), //
            new TestData("_QbyVENIJEe2iY95l9G0Iag", "_QbzjMdIJEe2iY95l9G0Iag", "resetfail-unboldlab", false, false, false), //
            new TestData("_uZF2UNIJEe2iY95l9G0Iag", "_uZF2VdIJEe2iY95l9G0Iag", "resetfail-unboldnode", false, false, false)
    };

    private class XMLCheckerState extends DefaultHandler {
        class Element {
            public String qName;

            public Attributes attributes;

            public Element(String qName, Attributes attributes) {
                this.qName = qName;
                this.attributes = attributes;
            }

            public boolean is(String expected) {
                return qName.equals(expected);
            }

            public String attr(String attrName) {
                return attributes.getValue(attrName);
            }

            public boolean attrEq(String attrName, String attrValue) {
                return attrValue.equals(attributes.getValue(attrName));
            }
        }

        class TestNode {
            public String elementId;

            public boolean labelHaveStyle = false;

            public boolean isBold = false;

            public TestNode(String elementId) {
                this.elementId = elementId;
            }
        }

        private boolean hasBeenMigrated;

        Optional<TestData> testData = Optional.empty();

        ArrayList<String> elementsStack = new ArrayList<>();

        Optional<TestNode> nodeE1 = Optional.empty(); // node in root

        Optional<TestNode> nodeO1 = Optional.empty(); // node in container

        Optional<TestNode> nodeA1 = Optional.empty(); // bordered node (in container)

        Optional<String> labelId = Optional.empty();

        public XMLCheckerState(boolean hasBeenMigrated) {
            this.hasBeenMigrated = hasBeenMigrated;
        }

        boolean inRootOfNode(TestNode node) {
            return elementsStack.get(elementsStack.size() - 1) == node.elementId;
        }

        private void startDiagramNode(TestNode node, Element element, int nodeNameType) {
            if (inRootOfNode(node)) {
                if (element.is("styles")) {
                    node.isBold = Optional.ofNullable(element.attr("bold")) //
                            .map(boldStr -> Boolean.valueOf(boldStr)).orElse(false);
                } else if (element.is("children") && element.attrEq("xmi:type", "notation:Node")) {
                    String elementId = element.attr("xmi:id");
                    int nodeType = SiriusVisualIDRegistry.getVisualID(element.attr("type"));
                    if (nodeType == nodeNameType) {
                        labelId = Optional.of(elementId);
                    }

                }
            } else if (labelId.isPresent()) {
                if (element.is("styles")) {
                    node.labelHaveStyle = true;
                }
            }
        }

        private void endDiagramNode(TestNode node, String elementId, int nodeNameType) {
            labelId = labelId.filter(id -> !elementId.equals(id)); // check end of label
        }

        private void startDiagramElement(Element element) {
            nodeE1.ifPresent(node -> {
                startDiagramNode(node, element, NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID);
            });
            nodeA1.ifPresent(node -> {
                startDiagramNode(node, element, NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID);
            });
            nodeO1.ifPresent(node -> {
                startDiagramNode(node, element, NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID);
            });
            if (element.is("children") && element.attrEq("xmi:type", "notation:Node")) {
                int nodeType = SiriusVisualIDRegistry.getVisualID(element.attr("type"));
                String elementId = element.attr("xmi:id");
                if (nodeType == DNodeEditPart.VISUAL_ID) {
                    assertTrue("invalid aird, found leaf node in node", nodeE1.isEmpty());
                    nodeE1 = Optional.of(new TestNode(elementId));
                } else if (nodeType == DNode3EditPart.VISUAL_ID) {
                    assertTrue("invalid aird, found leaf node in node", nodeA1.isEmpty());
                    nodeA1 = Optional.of(new TestNode(elementId));
                } else if (nodeType == DNode4EditPart.VISUAL_ID) {
                    assertTrue("invalid aird, found leaf node in node", nodeO1.isEmpty());
                    nodeO1 = Optional.of(new TestNode(elementId));
                }
            }
        }

        private void endDiagramElement(String elementId) {

            // check if end is reached
            nodeE1.ifPresent(node -> {
                if (node.elementId == elementId) {
                    checkNodeXMI(node);
                    nodeE1 = Optional.empty();
                } else {
                    endDiagramNode(node, elementId, NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID);
                }
            });
            nodeA1.ifPresent(node -> {
                if (node.elementId == elementId) {
                    checkNodeXMI(node);
                    nodeA1 = Optional.empty();
                } else {
                    endDiagramNode(node, elementId, NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID);
                }
            });
            nodeO1.ifPresent(node -> {
                if (node.elementId == elementId) {
                    checkNodeXMI(node);
                    nodeO1 = Optional.empty();
                } else {
                    endDiagramNode(node, elementId, NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID);
                }
            });
        }

        private void checkNodeXMI(TestNode node) {
            TestData reference = testData.orElseThrow();
            if (hasBeenMigrated) {
                // node is valid if there is no style on label
                assertFalse("In node of diagram " + reference.name + ": Unexpected style on label after", node.labelHaveStyle);
                assertEquals("Style of a node of " + reference.name + " must be consistent", node.isBold, reference.boldState);
            } else {
                // node is valid if there is no style on label
                assertEquals(!node.labelHaveStyle, reference.labelHaveStyle);
                if (reference.inconsistentStyle()) {
                    assertNotEquals("Style of a node of " + reference.name + " must be inconsistent", //
                            node.isBold, reference.boldState);
                } else {
                    assertEquals("Style of a node of " + reference.name + " must be consistent", //
                            node.isBold, reference.boldState);
                }
            }
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            Element element = new Element(qName, attributes);
            testData.ifPresentOrElse(currentTest -> { // if testData is present
                // 1. process element
                startDiagramElement(element);

                // 2. add to stack (path memorization)
                elementsStack.add(element.attr("xmi:id"));

                // 3. process children with next calls of startElement
            }, () -> { // else
                if (element.is("data") && element.attrEq("xmi:type", "notation:Diagram")) {
                    String uid = element.attr("element");
                    String gmfUid = element.attr("xmi:id");

                    testData = Arrays.stream(testDatas) //
                            .filter(testData -> testData.uid().equals(uid) && testData.gmfUid().equals(gmfUid)) //
                            .findAny();
                }
            });
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (testData.isPresent()) {
                if (elementsStack.isEmpty()) {
                    testData = Optional.empty();
                } else {
                    // 4. remove from stack
                    String elementId = elementsStack.remove(elementsStack.size() - 1);

                    endDiagramElement(elementId);
                }
            }
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_NAME, SESSION_RESOURCE_NAME);
        genericSetUp(Collections.singletonList(SEMANTIC_RESOURCE_LOCAL_PATH), Collections.<String> emptyList(),
                SESSION_RESOURCE_LOCAL_PATH);
    }

    private void checkFileState(boolean hasBeenMigrated) {
        Version migrationVersion = new NodeStyleMigrationParticipant().getMigrationVersion();

        URI resourceUri = hasBeenMigrated ? //
                URI.createPlatformResourceURI(SESSION_RESOURCE_LOCAL_PATH, true) //
                : URI.createPlatformPluginURI(SESSION_RESOURCE_PATH, true);

        if (!hasBeenMigrated) {
            // Check that the migration of the session resource is needed or not according to hasBeenMigrated.
            Version loadedVersion = checkRepresentationFileMigrationStatus(resourceUri, true);

            boolean fileIsNew = migrationVersion.compareTo(loadedVersion) <= 0; // migrationVersion <= loadedVersion
            assertEquals("Unexpected file version", fileIsNew, hasBeenMigrated);
        }

        ExtensibleURIConverterImpl uriConverterImpl = new ExtensibleURIConverterImpl();
        InputStream inputStream = null;
        try {
            inputStream = uriConverterImpl.createInputStream(resourceUri);

            SAXParser parser = SAXParserFactory.newDefaultInstance().newSAXParser();
            parser.parse(inputStream, new XMLCheckerState(hasBeenMigrated));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            fail("XML parse exception: " + e.toString() + ".see console log for more informations");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Test that the data were not migrated on the repository. It allows to check the effect of the migration in the
     * other test.
     */
    public void testMigrationIsNeededOnData() {
        checkFileState(false);
    }

    /**
     * Check that label have no style
     */
    private void checkLabelHasNoStyle(View view) {
        assertTrue("checkLabelHasNoStyle fail", view.getStyles().isEmpty());
    }

    /**
     * Check that the GMF node style consistent with Sirius label style
     */
    private void checkNodeStyle(FontStyle gmfStyle, BasicLabelStyle dStyle) {
        assertEquals("Inconsistant style: font color", RGBValues.integerToRGBValues(gmfStyle.getFontColor()), dStyle.getLabelColor());
        assertEquals("Inconsistant style: font height", gmfStyle.getFontHeight(), dStyle.getLabelSize());
        assertEquals("Inconsistant style: bold", gmfStyle.isBold(), dStyle.getLabelFormat().contains(FontFormat.BOLD_LITERAL));
        assertEquals("Inconsistant style: italic", gmfStyle.isItalic(), dStyle.getLabelFormat().contains(FontFormat.ITALIC_LITERAL));
        assertEquals("Inconsistant style: strike through", gmfStyle.isStrikeThrough(), dStyle.getLabelFormat().contains(FontFormat.STRIKE_THROUGH_LITERAL));
        assertEquals("Inconsistant style: underline", gmfStyle.isUnderline(), dStyle.getLabelFormat().contains(FontFormat.UNDERLINE_LITERAL));
    }

    /**
     * Check
     *
     * - if label of node was removed
     *
     * - style consistency with Sirius node style
     *
     * - state of bold
     *
     * @param node
     *            GMF node
     * @param labelType
     *            label node type (visual ID)
     * @param shapeType
     *            shape node type (visual ID)
     */
    private void checkNode(View node, int labelType, int shapeType, boolean boldState) {
        checkLabelHasNoStyle(getChildByType(node, labelType));
        getChildByType(node, shapeType); // checkExistance

        DNode dNode = (DNode) node.getElement();

        BasicLabelStyle dStyle = (BasicLabelStyle) dNode.getStyle();
        FontStyle gmfStyle = (FontStyle) node.getStyle(NotationPackage.eINSTANCE.getFontStyle());
        checkNodeStyle(gmfStyle, dStyle);

        assertEquals(gmfStyle.isBold(), boldState);
    }

    /**
     * Return a child of a GMF view with specified type
     */
    private View getChildByType(View view, int type) {
        EList<View> children = view.getChildren();
        return children.stream().filter(child -> {
            return SiriusVisualIDRegistry.getVisualID(child.getType()) == type;
        }).findAny().get();
    }

    private void checkNodes(Diagram diagram, boolean boldState) {
        // E1: Node
        View nodeE1 = getChildByType(diagram, DNodeEditPart.VISUAL_ID);
        checkNode(nodeE1, NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID, SquareEditPart.VISUAL_ID, boldState);

        // C1: Container
        View nodeC1 = getChildByType(diagram, DNodeContainerEditPart.VISUAL_ID);

        getChildByType(nodeC1, DNodeContainerNameEditPart.VISUAL_ID); // checkExistance
        View container = getChildByType(nodeC1, DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID);
        View nodeO1 = getChildByType(nodeC1, DNode4EditPart.VISUAL_ID); // O1: BorderNode

        // A1: SubNode
        View nodeA1 = getChildByType(container, DNode3EditPart.VISUAL_ID);
        checkNode(nodeA1, NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID, SquareEditPart.VISUAL_ID, boldState);

        checkNode(nodeO1, NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID, EllipseEditPart.VISUAL_ID, boldState);
    }

    void checkDiagram(String name, boolean boldState) {
        DRepresentation rep = getRepresentationsByName(name).stream().findFirst().get();
        DDiagram dDiagram = (DDiagram) rep;

        DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(dDiagram);
        // check null with optional API
        Diagram diagram = Optional.ofNullable(query.getAssociatedGMFDiagram().get()).get();

        checkNodes(diagram, boldState);
    }

    /**
     * Check that Sirius and GMF style are consistent
     */
    public void testNodeStyleConstancy() {
        for (TestData testData : testDatas) {
            checkDiagram(testData.name, testData.boldState);
        }

        session.save(defaultProgress);
        checkFileState(true);
    }
}
