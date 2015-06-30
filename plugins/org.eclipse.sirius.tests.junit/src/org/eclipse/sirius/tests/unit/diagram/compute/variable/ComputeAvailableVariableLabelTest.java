/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.compute.variable;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

/**
 * Verify that all mapping object (container, node, listElement, begin, center
 * and end label) access to variable $Diagram for label.
 * 
 * Test VP-2305.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class ComputeAvailableVariableLabelTest extends SiriusDiagramTestCase {

    protected static final String FOLDER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/computelabel/";

    private static final String DEFAULT_SEMANTIC_MODEL_PATH = FOLDER_PATH + "TestComputeLabel.ecore";

    private static final String DEFAULT_SESSION_FILE_PATH = FOLDER_PATH + "TestComputeLabel.aird";

    private static final String DEFAULT_MODELER_PATH = FOLDER_PATH + "testComputeLabelDiagram.odesign";

    protected static final String REPRESENTATION_DECRIPTION_NAME = "DiagramTestComputeLabel";

    protected DDiagram diagram;

    protected String representationName = "p1 package entities";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(getSemanticModelPath(), getModelerPath(), getSessionFilePath());
        assertEquals("Just one representation must be present in session", 1, getRepresentations(REPRESENTATION_DECRIPTION_NAME).size());
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DECRIPTION_NAME).toArray()[0];
        assertNotNull(diagram);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
    }

    /**
     * Test all representation elements name contains the representation name.
     */
    public void testComputeAvailableVariableDiagramForLabel() {
        List<DDiagramElement> diagramElements = diagram.getDiagramElements();
        int nbContainer = 0;
        for (DDiagramElement diagramElement : diagramElements) {
            assertTrue("The diagram element does not contain label in the name of the representation", diagramElement.getName().contains(representationName));
            if (diagramElement instanceof DEdge) {
                if (((DEdge) diagramElement).getBeginLabel().length() > 0) {
                    assertTrue("The begin label does not contains the representation name", ((DEdge) diagramElement).getBeginLabel().contains(representationName));
                    assertTrue("The end label does not contains the representation name", ((DEdge) diagramElement).getEndLabel().contains(representationName));
                }
            }
            if (diagramElement instanceof DDiagramElementContainer) {
                nbContainer++;
            }
        }
        assertEquals("Creation Container tool does not create new Container", 3, nbContainer);
    }

    /**
     * Test creation container tool access to variable $diagram.
     */
    public void testComputeVariableDiagramForLabelFromCreationContainerTool() {
        List<DDiagramElement> diagramElements = diagram.getDiagramElements();
        int nbContainer = 0;
        for (DDiagramElement diagramElement : diagramElements) {
            if (diagramElement instanceof DDiagramElementContainer) {
                nbContainer++;
            }
        }
        assertEquals("Creation Container tool does not create new Container", 3, nbContainer);
        assertTrue("Creation Container tool fail", applyContainerCreationTool("Class", diagram, diagram));
        nbContainer = 0;
        for (DDiagramElement elements : diagram.getDiagramElements()) {
            assertTrue("The diagram element does not contain label in the name of the representation", elements.getName().contains(representationName));
            if (elements instanceof DDiagramElementContainer) {
                nbContainer++;
            }
        }
        assertEquals("Creation Container tool does not create new Container", 4, nbContainer);
    }

    /**
     * Test creation node tool access to variable $diagram.
     */
    public void testComputeVariableDiagramForLabelFromCreationNodeTool() {
        List<DDiagramElement> diagramElements = diagram.getDiagramElements();
        DDiagramElementContainer containerSC1 = null;
        for (DDiagramElement diagramElement : diagramElements) {
            if ((representationName + "SC1").equals(diagramElement.getName())) {
                containerSC1 = (DDiagramElementContainer) diagramElement;
            }
        }
        assertTrue("Creation node tool fail", applyNodeCreationTool("Attribute", diagram, containerSC1));
        int nbNode = 0;
        for (EObject dNode : ((DNodeList) containerSC1).getOwnedElements()) {
            if (dNode instanceof DNodeListElement) {
                DNodeListElement listElt = (DNodeListElement) dNode;
                assertTrue("The node name don't contains the representation name: " + listElt.getName(), listElt.getName().contains(representationName));
                nbNode++;
            }
        }
        assertEquals("Creation node tool does not create new node", 1, nbNode);
    }

    /**
     * Test creation bordered node tool access to variable $diagram.
     */
    public void testComputeVariableDiagramForLabelFromCreationBorderedNodeTool() {
        List<DDiagramElement> diagramElements = diagram.getDiagramElements();
        DNode nodePackage = null;
        for (DDiagramElement diagramElement : diagramElements) {
            if ((representationName + "new EPackage1").equals(diagramElement.getName())) {
                nodePackage = (DNode) diagramElement;
            }
        }
        assertTrue("Creation node tool fail", applyNodeCreationTool("Package", diagram, nodePackage));
        int nbNode = 0;
        for (EObject dNode : nodePackage.getOwnedBorderedNodes()) {
            if (dNode instanceof DNode) {
                assertTrue("The node name don't contains the representation name", ((DNode) dNode).getName().contains(representationName));
                nbNode++;
            }
        }
        assertEquals("Creation node tool does not create new node", 1, nbNode);
    }

    /**
     * Test creation edge tool access to variable $diagram.
     */
    public void testComputeVariableDiagramForLabelFromCreationEdgeTool() {
        List<DDiagramElement> diagramElements = diagram.getDiagramElements();
        DDiagramElementContainer containerSC1 = null;
        DDiagramElementContainer containerC2 = null;
        for (DDiagramElement diagramElement : diagramElements) {
            if ((representationName + "SC1").equals(diagramElement.getName())) {
                containerSC1 = (DDiagramElementContainer) diagramElement;
            }
            if ((representationName + "C2").equals(diagramElement.getName())) {
                containerC2 = (DDiagramElementContainer) diagramElement;
            }
        }
        assertEquals("Test data have changed", 1, diagram.getEdges().size());
        assertTrue("Creation edge tool fail", applyEdgeCreationTool("Reference", diagram, containerSC1, containerC2));
        assertEquals("The edge creation tool does not create new edge", 2, diagram.getEdges().size());
        for (DEdge dEdge : diagram.getEdges()) {
            assertTrue("The edge label don't contains the representation name", dEdge.getName().contains(representationName));
            if (dEdge.getBeginLabel().length() > 0) {
                assertTrue("The begin label don't contains the representation name", dEdge.getBeginLabel().contains(representationName));
                assertTrue("The end label don't contains the representation name", dEdge.getEndLabel().contains(representationName));
            }
        }
    }

    /**
     * Returns the path of the aird file to open. Subclasses can override this
     * method.
     * 
     * @return the path of the aird file to open
     */
    protected String getSessionFilePath() {
        return DEFAULT_SESSION_FILE_PATH;
    }

    /**
     * Returns the path of the semantic model file to open. Subclasses can
     * override this method.
     * 
     * @return the path of the semantic model file to open
     */
    protected String getSemanticModelPath() {
        return DEFAULT_SEMANTIC_MODEL_PATH;
    }

    /**
     * Returns the path of the modeler path to use. Subclasses can override this
     * method.
     * 
     * @return the path of the semantic model file to open
     */
    protected String getModelerPath() {
        return DEFAULT_MODELER_PATH;
    }
}
