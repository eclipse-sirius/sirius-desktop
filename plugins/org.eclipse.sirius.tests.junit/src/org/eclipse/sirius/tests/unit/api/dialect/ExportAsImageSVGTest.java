/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.api.dialect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.constants.XMLConstants;
import org.apache.batik.util.SVGConstants;
import org.apache.batik.util.XMLResourceDescriptor;
import org.eclipse.core.runtime.IPath;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.ui.business.api.DiagramExportResult;
import org.eclipse.sirius.diagram.ui.tools.internal.render.SiriusDiagramSVGGenerator;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Test export image as SVG with diagrams containing SVG images.
 * 
 * @author nlepine
 */
public class ExportAsImageSVGTest extends AbstractExportAsImageTest {

    private static final String DATA_FOLFER_TEST = "/data/unit/ExportAsImageSVG/"; //$NON-NLS-1$

    private static final String SEMANTIC_MODEL_RELATIVE_PATH = DATA_FOLFER_TEST + SEMANTIC_MODEL_FILE_NAME;

    private static final String MODELER_FILE_NAME = "svg.odesign"; //$NON-NLS-1$

    private static final String MODELER_RELATIVE_PATH = DATA_FOLFER_TEST + MODELER_FILE_NAME;

    private static final String MODELER_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + MODELER_FILE_NAME; //$NON-NLS-1$

    private static final String AIRD_RELATIVE_PATH = DATA_FOLFER_TEST + AIRD_FILE_NAME;

    private static final String REPRESENTATION_BORDERED_NODE = "new SVGImages_Bordered_Node"; //$NON-NLS-1$

    private static final String REPRESENTATION_BORDERED_NODE_RESIZE = "new SVGImages_Bordered_Node_Resize"; //$NON-NLS-1$

    private static final String REPRESENTATION_CONTAINER = "new SVGImages_Container"; //$NON-NLS-1$

    private static final String REPRESENTATION_CONTAINER_IMAGE = "new SVGImages_Container_Image"; //$NON-NLS-1$

    private static final String REPRESENTATION_CONTAINER_PARALLELOGRAM = "new SVGImages_Container_Parallelogram"; //$NON-NLS-1$

    private static final String REPRESENTATION_CONTAINER_RESIZE = "new SVGImages_Container_Resize"; //$NON-NLS-1$

    private static final String REPRESENTATION_NODE = "new SVGImages_Node"; //$NON-NLS-1$

    private static final String REPRESENTATION_NODE_RESIZE = "new SVGImages_Node_Resize"; //$NON-NLS-1$

    private static final String IMAGES_SYSTEM_ACTOR_SVG = "/images/SystemActor.svg"; //$NON-NLS-1$

    private static final String IMAGES_SQUARE_SVG = "/images/square.svg"; //$NON-NLS-1$

    private static final String IMAGES_FAN_SVG = "/images/Fan.svg"; //$NON-NLS-1$

    private static final String IMAGES_CLOUD_SVG = "/images/Cloud.svg"; //$NON-NLS-1$

    private static final String IMAGES_BELLSPROUT_SVG = "/images/Bellsprout.svg"; //$NON-NLS-1$

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, MODELER_RELATIVE_PATH, MODELER_WORKSPACE_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, SEMANTIC_MODEL_RELATIVE_PATH, SEMANTIC_MODEL_WORKSPACE_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, AIRD_RELATIVE_PATH, AIRD_WORKSPACE_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_FOLFER_TEST + IMAGES_BELLSPROUT_SVG, TEMPORARY_PROJECT_NAME + IMAGES_BELLSPROUT_SVG);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_FOLFER_TEST + IMAGES_CLOUD_SVG, TEMPORARY_PROJECT_NAME + IMAGES_CLOUD_SVG);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_FOLFER_TEST + IMAGES_FAN_SVG, TEMPORARY_PROJECT_NAME + IMAGES_FAN_SVG);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_FOLFER_TEST + IMAGES_SQUARE_SVG, TEMPORARY_PROJECT_NAME + IMAGES_SQUARE_SVG);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_FOLFER_TEST + IMAGES_SYSTEM_ACTOR_SVG, TEMPORARY_PROJECT_NAME + IMAGES_SYSTEM_ACTOR_SVG);

        genericSetUp(SEMANTIC_MODEL_WORKSPACE_PATH, MODELER_WORKSPACE_PATH, AIRD_WORKSPACE_PATH);

        TestsUtil.emptyEventsFromUIThread();

        System.setProperty(SiriusDiagramSVGGenerator.ENABLE_EMBEDDED_SVG_IN_SVG_EXPORT, "true"); //$NON-NLS-1$
    }

    @Override
    protected void tearDown() throws Exception {
        System.setProperty(SiriusDiagramSVGGenerator.ENABLE_EMBEDDED_SVG_IN_SVG_EXPORT, "false"); //$NON-NLS-1$
        super.tearDown();
    }

    /**
     * Tests the export of diagrams as image with SVG format and auto-scale deactivated. SVG export does not support
     * auto-scaling so it should not be taken in consideration.
     * 
     * @throws Exception
     */
    public void testExportAsSVGNoAutoScaling() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            DiagramExportResult exportResult = exportImage(getRepresentation(REPRESENTATION_NODE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.NO_SCALING);
            checkResults(exportResult, 21, 7, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_NODE_RESIZE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.NO_SCALING);
            checkResults(exportResult, 21, 19, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.NO_SCALING);
            checkResults(exportResult, 21, 7, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER_RESIZE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.NO_SCALING);
            checkResults(exportResult, 21, 21, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER_IMAGE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.NO_SCALING);
            checkResults(exportResult, 22, 8, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER_PARALLELOGRAM), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.NO_SCALING);
            checkResults(exportResult, 21, 7, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_BORDERED_NODE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.NO_SCALING);
            checkResults(exportResult, 15, 5, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_BORDERED_NODE_RESIZE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.NO_SCALING);
            checkResults(exportResult, 15, 15, 0.0);
        }
    }

    /**
     * Tests the export of diagrams as image with SVG format and auto-scale activated. SVG export does not support
     * auto-scaling so it should not be taken in consideration.
     * 
     * @throws Exception
     */
    public void testExportAsSVGAutoScaling() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            DiagramExportResult exportResult = exportImage(getRepresentation(REPRESENTATION_NODE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING);
            checkResults(exportResult, 21, 7, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_NODE_RESIZE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING);
            checkResults(exportResult, 21, 19, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING);
            checkResults(exportResult, 21, 7, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER_RESIZE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING);
            checkResults(exportResult, 21, 21, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER_IMAGE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING);
            checkResults(exportResult, 22, 8, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER_PARALLELOGRAM), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING);
            checkResults(exportResult, 21, 7, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_BORDERED_NODE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING);
            checkResults(exportResult, 15, 5, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_BORDERED_NODE_RESIZE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING);
            checkResults(exportResult, 15, 15, 0.0);
        }
    }

    /**
     * Tests the export of diagrams as image with SVG format and auto-scale activated. SVG export does not support
     * auto-scaling so it should not be taken in consideration.
     * 
     * @throws Exception
     */
    public void testExportAsSVGAutoScalingIfLarger() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            DiagramExportResult exportResult = exportImage(getRepresentation(REPRESENTATION_NODE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            checkResults(exportResult, 21, 7, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_NODE_RESIZE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            checkResults(exportResult, 21, 19, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            checkResults(exportResult, 21, 7, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER_RESIZE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            checkResults(exportResult, 21, 21, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER_IMAGE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            checkResults(exportResult, 22, 8, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER_PARALLELOGRAM), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            checkResults(exportResult, 21, 7, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_BORDERED_NODE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            checkResults(exportResult, 15, 5, 0.0);
            exportResult = exportImage(getRepresentation(REPRESENTATION_BORDERED_NODE_RESIZE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            checkResults(exportResult, 15, 15, 0.0);
        }
    }

    /**
     * Tests the export of diagrams as image with SVG format with traceability activated. We expect a specific number of
     * "diagram:semanticTargetId" attributes in the SVG file. Note that we also test that no diagram:semanticTargetId
     * attributes are visible in other tests using SVG format, with deactivated traceability.
     * 
     * @throws Exception
     */
    public void testExportAsSVGWithTraceability() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            DiagramExportResult exportResult = exportImage(getRepresentation(REPRESENTATION_NODE), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.NO_SCALING, true);
            checkResults(exportResult, 21, 7, 0.0);
            checkTraceability(exportResult.getExportedFiles(), true);
            exportResult = exportImage(getRepresentation(REPRESENTATION_CONTAINER), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.NO_SCALING, true);
            checkResults(exportResult, 21, 7, 0.0);
            checkTraceability(exportResult.getExportedFiles(), true);
        }
    }

    /**
     * Check results.
     * 
     * @param exportResult
     *            DiagramExportResult
     * @param nbUseTags
     *            int
     * @param nbSymbolTags
     *            int
     * @param scalingFactor
     *            double
     * @throws IOException
     */
    private void checkResults(DiagramExportResult exportResult, int nbUseTags, int nbSymbolTags, double scalingFactor) throws IOException {
        Set<IPath> exportedFiles = exportResult.getExportedFiles();
        assertEquals("No export file has been produced", 1, exportedFiles.size()); //$NON-NLS-1$

        double scalingFactorComputed = exportResult.getScalingFactor();
        assertEquals("The scaling factor was wrongly computed.", scalingFactor, scalingFactorComputed); //$NON-NLS-1$

        int margin = exportResult.getMargin();
        assertEquals("The expected margin is not right.", 10, margin); //$NON-NLS-1$

        Document doc = getSVGDocument(exportedFiles);

        assertEquals("The expected SVG document structure is incorrect.", 1, getElements(doc).size()); //$NON-NLS-1$
        assertEquals("The expected SVG document structure is incorrect.", 2, getElements(doc.getChildNodes().item(0)).size()); //$NON-NLS-1$
        List<Node> useNodes = getAllNodesWithName(doc, SVGConstants.SVG_USE_TAG);
        List<Node> symbolNodes = getAllNodesWithName(doc, SVGConstants.SVG_SYMBOL_TAG);
        assertEquals("The expected SVG document must have" + nbUseTags + " use tags.", nbUseTags, useNodes.size()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("The expected SVG document must have " + nbSymbolTags + " symbol tags.", nbSymbolTags, symbolNodes.size()); //$NON-NLS-1$//$NON-NLS-2$
        checkUseAndSymbolTags(useNodes, symbolNodes);
    }

    /**
     * Check use and symbol tags.
     * 
     * @param useNodes
     *            List<Node>
     * @param symbolNodes
     *            List<Node>
     */
    private void checkUseAndSymbolTags(List<Node> useNodes, List<Node> symbolNodes) {
        // check all use referenced ids corresponds with one symbol tag id
        useNodes.forEach(n -> {
            if (!hasCorrespondingSymbol(symbolNodes, n)) {
                fail("Use tag " + n.getAttributes().getNamedItemNS(XMLConstants.XLINK_NAMESPACE_URI, XMLConstants.XLINK_HREF_ATTRIBUTE) + " has no corresponding symbol tag."); //$NON-NLS-1$ //$NON-NLS-2$
            }
        });

    }

    /**
     * Return if n use tag reference link corresponds to a symbol tag id.
     * 
     * @param symbolNodes
     *            List<Node>
     * @param n
     *            Node
     * @return if symbolNodes.id contains n.xlink:ref
     */
    private boolean hasCorrespondingSymbol(List<Node> symbolNodes, Node n) {
        for (Node symbol : symbolNodes) {
            Node symbolId = symbol.getAttributes().getNamedItem(SVGConstants.SVG_ID_ATTRIBUTE);
            Node useRef = n.getAttributes().getNamedItemNS(XMLConstants.XLINK_NAMESPACE_URI, XMLConstants.XLINK_HREF_ATTRIBUTE);
            if (symbolId != null && useRef != null && useRef.getNodeValue().contains(symbolId.getNodeValue())) {
                return true;
            }

        }
        return false;
    }

    /**
     * Get all element children.
     * 
     * @param node
     *            Node
     * @return all element children.
     */
    private List<Element> getElements(Node node) {
        List<Element> result = new ArrayList<>();
        for (Node c = node.getFirstChild(); c != null; c = c.getNextSibling()) {
            if (c instanceof Element) {
                result.add((Element) c);
            }
        }
        return result;
    }

    /**
     * Get all nodes with tag name in node hierarchy.
     * 
     * @param node
     *            Node
     * @param tagName
     *            String
     * @return all nodes with tag name in node hierarchy.
     */
    private List<Node> getAllNodesWithName(Node node, String tagName) {
        List<Node> result = new ArrayList<>();
        if (isTagWithName(node, tagName)) {
            result.add(node);
        }
        for (Node c = node.getFirstChild(); c != null; c = c.getNextSibling()) {
            result.addAll(getAllNodesWithName(c, tagName));
        }
        return result;
    }

    /**
     * Check if node tag name is tagName
     * 
     * @param node
     *            Node
     * @param tagName
     *            String
     * @return if node tag name is tagName
     */
    private boolean isTagWithName(Node node, String tagName) {
        if (tagName.equals(node.getNodeName())) {
            return true;
        }
        return false;
    }

    /**
     * @param exportedFiles
     *            Set<IPath>
     * @return SVG document corresponding to exportedFiles[0]
     * @throws IOException
     */
    public Document getSVGDocument(Set<IPath> exportedFiles) throws IOException {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        IPath filePath = exportedFiles.iterator().next();
        return f.createDocument(filePath.toFile().toURI().toString());
    }

    @Override
    protected void checkTraceability(Map<String, Integer> semanticTargetIdToOccurrences) {
        assertEquals("The class C1 id is not exported in the SVG file as expected", Integer.valueOf(17), semanticTargetIdToOccurrences.get("platform:/resource/DesignerTestProject/My.ecore#//p1/C1")); //$NON-NLS-1$//$NON-NLS-2$
        assertEquals("The class C2 id is not exported in the SVG file as expected", Integer.valueOf(17), semanticTargetIdToOccurrences.get("platform:/resource/DesignerTestProject/My.ecore#//p1/C2")); //$NON-NLS-1$//$NON-NLS-2$
        assertEquals("The class C3 id is not exported in the SVG file as expected", Integer.valueOf(17), semanticTargetIdToOccurrences.get("platform:/resource/DesignerTestProject/My.ecore#//p1/C3")); //$NON-NLS-1$ //$NON-NLS-2$

    }

}
