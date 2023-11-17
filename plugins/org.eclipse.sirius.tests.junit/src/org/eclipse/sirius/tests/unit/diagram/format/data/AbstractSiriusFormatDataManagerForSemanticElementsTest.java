/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.format.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper.FormatDifference;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.SiriusFormatDataManagerForSemanticElements;
import org.eclipse.sirius.diagram.ui.tools.internal.format.AdvancedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.format.NodeFormatDataKey;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.ui.IEditorPart;

/**
 * Test class for {@link SiriusFormatDataManagerForSemanticElements}.
 * 
 * @author dlecan
 */
public abstract class AbstractSiriusFormatDataManagerForSemanticElementsTest extends SiriusDiagramTestCase {

    protected static final boolean REGENERATE_TEST_DATA = false;

    protected static final Comparator<DRepresentationDescriptor> USING_NAME = new Comparator<DRepresentationDescriptor>() {
        @Override
        public int compare(final DRepresentationDescriptor o1, final DRepresentationDescriptor o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    protected static final Predicate<Diagram> NO_RAW_DIAGRAM = new Predicate<Diagram>() {
        @Override
        public boolean test(final Diagram input) {
            return !input.raw;
        }
    };

    protected static class Representation {
        protected final String name;

        protected final List<Diagram> diagrams;

        protected Representation(final String name, final Diagram... diagrams) {
            this.name = name;
            this.diagrams = Arrays.asList(diagrams);
            for (final Diagram diagram : diagrams) {
                diagram.parent = Representation.this;
            }
        }
    }

    protected static class Diagram {

        protected Representation parent;

        protected final String name;

        protected final int numberOfNodeFormatData;

        protected final int numberOfEdgeFormatData;

        protected boolean raw = false;

        protected DiagramEditPart diagramEditPart;

        protected DiagramEditor rawDiagramEditor;

        protected Diagram(final String name, final int numberOfNodeFormatData, final int numberOfEdgeFormatData) {
            this.name = name;
            this.numberOfNodeFormatData = numberOfNodeFormatData;
            this.numberOfEdgeFormatData = numberOfEdgeFormatData;
        }

        protected Diagram(final String name, final int numberOfNodeFormatData, final int numberOfEdgeFormatData, final boolean raw) {
            this.name = name;
            this.numberOfNodeFormatData = numberOfNodeFormatData;
            this.numberOfEdgeFormatData = numberOfEdgeFormatData;
            this.raw = raw;
        }
    }

    protected static final int ITERATIONS = 20;

    protected static final String XMI_PREFIX = "storedFormat-";

    protected static final String XMI_EXTENSION = ".xmi";

    protected static final String PLUGIN_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/";

    protected static final String DATA_PATH = "data/unit/layout/data/";

    protected static final String FULL_DATA_PATH = PLUGIN_PATH + DATA_PATH;

    protected static final String XMI_FOLDER = "xmi/";

    protected static final String RAW_FOLDER = "raw/";

    protected static final String SEMANTIC_MODEL_NAME = "My.ecore";

    protected static final String SESSION_MODEL_NAME = "My.aird";

    protected static final String MODELER_NAME = "My.odesign";

    protected static final Diagram DIAG_TYPE1_MYPACKAGE = new Diagram("DiagType1 of MyPackage", 3, 0);

    protected static final Diagram DIAG_TYPE1_RAW = new Diagram("Type1 Raw Diagram", 7, 1, true);

    protected static final Representation REPRES_TYPE1 = new Representation("DiagType1", DIAG_TYPE1_MYPACKAGE, DIAG_TYPE1_RAW);

    //
    protected static final Diagram DIAG_TYPE2_MYPACKAGE = new Diagram("DiagType2 of MyPackage", 16, 2);

    protected static final Representation REPRES_TYPE2 = new Representation("DiagType2", DIAG_TYPE2_MYPACKAGE);

    //
    protected static final Diagram DIAG_TYPE3_MYPACKAGE = new Diagram("DiagType3 of MyPackage", 4, 0);

    protected static final Representation REPRES_TYPE3 = new Representation("DiagType3", DIAG_TYPE3_MYPACKAGE);

    //
    protected static final Diagram DIAG_TYPE4_MYPACKAGE = new Diagram("DiagType4 of MyPackage", 4, 0);

    protected static final Representation REPRES_TYPE4 = new Representation("DiagType4", DIAG_TYPE4_MYPACKAGE);

    //
    protected static final Diagram DIAG_TYPE5_MYPACKAGE = new Diagram("DiagType5 of MyPackage", 7, 0);

    protected static final Representation REPRES_TYPE5 = new Representation("DiagType5", DIAG_TYPE5_MYPACKAGE);

    protected static final Diagram DIAG_TYPE6_MYPACKAGE = new Diagram("DiagType6 of MyPackage", 9, 2);

    private static final Representation REPRES_TYPE6 = new Representation("DiagType6", DIAG_TYPE6_MYPACKAGE);

    protected static final Diagram DIAG_TYPE7_MYPACKAGE = new Diagram("DiagType7 of MyPackage", 16, 0);

    protected static final Diagram DIAG_TYPE7_RAW = new Diagram("Type7 Raw Diagram", 16, 0, true);

    protected static final Representation REPRES_TYPE7 = new Representation("DiagType7", DIAG_TYPE7_MYPACKAGE, DIAG_TYPE7_RAW);

    protected static final Diagram DIAG_TYPE8_MYPACKAGE = new Diagram("DiagType8 of MyPackage", 16, 0);

    protected static final Diagram DIAG_TYPE8_P2 = new Diagram("DiagType8 of p2", 3, 0);

    protected static final Diagram DIAG_TYPE8_RAW = new Diagram("Type8 Raw Diagram", 16, 0, true);

    protected static final Representation REPRES_TYPE8 = new Representation("DiagType8", DIAG_TYPE8_MYPACKAGE, DIAG_TYPE8_P2, DIAG_TYPE8_RAW);

    protected static final Diagram DIAG_TYPE9_MYPACKAGE = new Diagram("DiagType9 of MyPackage", 11, 0);

    protected static final Diagram DIAG_TYPE9_RAW = new Diagram("Type9 Raw Diagram", 11, 0, true);

    protected static final Diagram DIAG_TYPE10_MYPACKAGE = new Diagram("DiagType10 of MyPackage", 4, 2);

    protected static final Diagram DIAG_TYPE10_RAW = new Diagram("Type10 Raw Diagram", 4, 2, true);

    protected static final Representation REPRES_TYPE9 = new Representation("DiagType9", DIAG_TYPE9_MYPACKAGE, DIAG_TYPE9_RAW);

    protected static final Representation REPRES_TYPE10 = new Representation("DiagType10", DIAG_TYPE10_MYPACKAGE, DIAG_TYPE10_RAW);

    protected static final Representation[] ALL_REPRESENTATIONS = { REPRES_TYPE1, REPRES_TYPE2, REPRES_TYPE3, REPRES_TYPE4, REPRES_TYPE5, REPRES_TYPE6, REPRES_TYPE7, REPRES_TYPE8, REPRES_TYPE9,
            REPRES_TYPE10 };

    protected static final String[][] ENCODED_CHARS = { { " ", "__" } };

    protected final List<IEditorPart> editorParts = new ArrayList<>();

    protected static final double LOW_ZOOM_LEVEL = 0.50;

    // Really, shouldn't be higher than 20px
    protected static final double LOW_ZOOM_DISTANCE = 20;

    protected static final double IDENTITY_ZOOM_LEVEL = 1.0;

    protected static final double IDENTITY_ZOOM_DISTANCE = 15;

    protected static final double HIGH_ZOOM_LEVEL = 40.0;

    // Really, shouldn't be higher than 15px
    protected static final double HIGH_ZOOM_DISTANCE = 15;

    protected static final double[][] ALL_ZOOM_DATA = { { LOW_ZOOM_LEVEL, LOW_ZOOM_DISTANCE }, { IDENTITY_ZOOM_LEVEL, IDENTITY_ZOOM_DISTANCE }, { HIGH_ZOOM_LEVEL, HIGH_ZOOM_DISTANCE } };

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, getDataPath(), getSessionModelName(),
        // getSemanticModelName());
        genericSetUp(getSemanticModelPaths(), getModelerPathAsList(), getSessionModelPath());

        // Disable refresh on opening
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        TestsUtil.emptyEventsFromUIThread();
    }

    protected String getDataPath() {
        return DATA_PATH;
    }

    protected List<String> getModelerPathAsList() {
        return Collections.singletonList(PLUGIN_PATH + getPlatformRelatedDataPath() + getModelerName());
    }

    protected List<String> getSemanticModelPaths() {
        return Collections.singletonList(PLUGIN_PATH + getPlatformRelatedDataPath() + getSemanticModelName());
    }

    protected String getSessionModelPath() {
        return PLUGIN_PATH + getPlatformRelatedDataPath() + getSessionModelName();
    }

    protected String getSessionModelName() {
        return SESSION_MODEL_NAME;
    }

    protected String getSemanticModelName() {
        return SEMANTIC_MODEL_NAME;
    }

    protected String getModelerName() {
        return MODELER_NAME;
    }

    protected void changeZoomLevel(Diagram diagram, double zoomLevel) {
        ZoomManager zoomManager = ((DiagramRootEditPart) diagram.diagramEditPart.getRoot()).getZoomManager();
        if (zoomManager.getZoom() != zoomLevel) {
            zoomManager.setZoom(zoomLevel);
        }
    }

    protected List<Diagram> openAllDiagramsInRepresentation(final Representation representation) {
        return openAllDiagramsInRepresentation(representation, false);
    }

    protected List<Diagram> openAllDiagramsInRepresentation(final Representation representation, boolean rawFiltered) {

        final List<Diagram> result = new ArrayList<>();

        final List<DRepresentationDescriptor> allDDiagramDescriptors = new ArrayList<DRepresentationDescriptor>(getRepresentationDescriptors(representation.name));

        assertEquals("The number of expected diagrams is wrong", representation.diagrams.size(), allDDiagramDescriptors.size());

        Collections.sort(allDDiagramDescriptors, USING_NAME);

        Iterable<Diagram> diagrams;
        if (rawFiltered) {
            // Raw diagram must be excluded, it is "special"
            diagrams = representation.diagrams.stream().filter(NO_RAW_DIAGRAM).collect(Collectors.toList());
        } else {
            diagrams = representation.diagrams;
        }

        for (final Diagram diagram : diagrams) {

            DRepresentationDescriptor dRepresentationDescriptorToFind = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();
            dRepresentationDescriptorToFind.setName(diagram.name);

            final int search = Collections.binarySearch(allDDiagramDescriptors, dRepresentationDescriptorToFind, USING_NAME);

            assertTrue("Diagram is not found in representation", search > -1);

            final DDiagram dDiagram = (DDiagram) allDDiagramDescriptors.get(search).getRepresentation();

            final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
            editorParts.add(editorPart);

            final DiagramEditPart diagramEditPart = ((DiagramEditor) editorPart).getDiagramEditPart();

            diagram.diagramEditPart = diagramEditPart;

            result.add(diagram);
        }

        return result;
    }

    protected List<Diagram> getAndOpenAllDiagrams() {
        return getAndOpenAllDiagrams(false);
    }

    /**
     * Returns a list of all {@link NodeFormatData} contained by the given map
     * 
     * @param nodeFormatMap
     *            the map from which we want to extract all {@link NodeFormatData}.
     * @return a list of all {@link NodeFormatData} contained by the given map. An empty list if no such element exists.
     */
    protected List<NodeFormatData> getNodeFormatDataList(Map<? extends FormatDataKey, Map<String, NodeFormatData>> nodeFormatMap) {
        List<NodeFormatData> formatDataList = new ArrayList<NodeFormatData>();
        Collection<Map<String, NodeFormatData>> formatDataMap = nodeFormatMap.values();
        for (Map<String, NodeFormatData> valueMap : formatDataMap) {
            formatDataList.addAll(valueMap.values());
        }
        return formatDataList;

    }

    /**
     * Returns a list of all {@link EdgeFormatData} contained by the given map
     * 
     * @param edgeFormatMap
     *            the map from which we want to extract all {@link EdgeFormatData}.
     * @return a list of all {@link EdgeFormatData} contained by the given map. An empty list if no such element exists.
     */
    protected List<EdgeFormatData> getEdgeFormatDataList(Map<? extends FormatDataKey, Map<String, EdgeFormatData>> edgeFormatMap) {
        List<EdgeFormatData> formatDataList = new ArrayList<EdgeFormatData>();
        Collection<Map<String, EdgeFormatData>> formatDataMap = edgeFormatMap.values();
        for (Map<String, EdgeFormatData> valueMap : formatDataMap) {
            formatDataList.addAll(valueMap.values());
        }
        return formatDataList;

    }

    protected List<Diagram> getAndOpenAllDiagrams(boolean rawFiltered) {
        final List<Diagram> result = new ArrayList<>();
        for (final Representation representation : ALL_REPRESENTATIONS) {
            result.addAll(openAllDiagramsInRepresentation(representation, rawFiltered));
        }
        return result;
    }

    protected String encodeDiagramName(final Diagram diagram) {
        String diagramName = diagram.name;

        for (final String[] couple : ENCODED_CHARS) {
            diagramName = diagramName.replaceAll(couple[0], couple[1]);
        }

        return XMI_PREFIX + diagramName;
    }

    protected DiagramEditPart openRawDiagram(final Diagram diagram) {
        for (final DRepresentationDescriptor representationDescriptor : getRepresentationDescriptors(diagram.parent.name)) {
            final DDiagram dDiagram = (DDiagram) representationDescriptor.getRepresentation();

            if (diagram.name.equals(representationDescriptor.getName())) {
                diagram.rawDiagramEditor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
                TestsUtil.synchronizationWithUIThread();
                break;
            }
        }
        diagram.diagramEditPart = diagram.rawDiagramEditor.getDiagramEditPart();
        return diagram.diagramEditPart;
    }

    protected void closeRawDiagram(final Diagram diagram) {
        SessionUIManager.INSTANCE.getUISession(session).closeEditors(false, (DialectEditor) diagram.rawDiagramEditor);
        TestsUtil.synchronizationWithUIThread();
    }

    protected FormatDifference<?> loadAndCompare(final Diagram diag, final String path, AdvancedSiriusFormatDataManager newManager, Configuration configuration) throws IOException {
        // Load referenced data
        final List<EObject> contents = loadReferenceData(path);

        // Data will be sorted by id
        final List<EObject> expected = new ArrayList<EObject>();
        for (final EObject eObject : contents) {
            expected.add(eObject);
        }
        Collections.sort(expected, new Comparator<EObject>() {
            @Override
            public int compare(EObject o1, EObject o2) {
                AbstractFormatData formatData1 = (AbstractFormatData) o1;
                AbstractFormatData formatData2 = (AbstractFormatData) o2;
                return formatData1.getId().compareTo(formatData2.getId());
            }
        });

        // Sort elements, SemanticNodeFormatDataKey are comparable
        TreeMap<NodeFormatDataKey, Map<String, NodeFormatData>> rootNodeFormatDataMap = new TreeMap<NodeFormatDataKey, Map<String, NodeFormatData>>(newManager.getRootNodeFormatData());

        List<NodeFormatData> nodeFormatDataList = getNodeFormatDataList(rootNodeFormatDataMap);

        // Compare results
        final FormatDifference<?> difference = FormatHelper.INSTANCE.computeFirstFormatDifference(expected, nodeFormatDataList, configuration);
        return difference;
    }

    // Let this "unused" method, as it can be enabled to save diagram format
    // data
    // Data will be saved in org.eclipse.sirius.tests project folder
    protected void saveDiagram(final Diagram diagram, final Collection<Map<String, NodeFormatData>> formatData) throws IOException {
        final String pathName = getPlatformRelatedXmiDataPath() + encodeDiagramName(diagram) + XMI_EXTENSION;
        saveDiagram(formatData, pathName);
    }

    protected void saveDiagram(final Collection<Map<String, NodeFormatData>> formatData, final String path) throws IOException {
        final ResourceSet resourceSet = new ResourceSetImpl();
        final URI uri = URI.createFileURI(path);
        final Resource resource = ModelUtils.createResource(uri, resourceSet);
        Iterator<Map<String, NodeFormatData>> formatDataIterator = formatData.iterator();
        while (formatDataIterator.hasNext()) {
            Map<String, NodeFormatData> formatDataMap = formatDataIterator.next();
            resource.getContents().addAll(formatDataMap.values());

        }

        resource.save(Collections.EMPTY_MAP);
    }

    protected List<EObject> loadReferenceData(final String path) throws IOException {
        final ResourceSet resourceSet = new ResourceSetImpl();
        final URI uri = URI.createPlatformPluginURI(path, true);
        final Resource resource = ModelUtils.createResource(uri, resourceSet);
        resource.load(Collections.EMPTY_MAP);

        return resource.getContents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        for (final IEditorPart editorPart : editorParts) {
            DialectUIManager.INSTANCE.closeEditor(editorPart, false);
            editorPart.getSite().getPage().closeEditor(editorPart, false);
        }
        super.tearDown();

    }

    protected String getPlatformRelatedFullXmiDataPath() {
        return PLUGIN_PATH + getPlatformRelatedXmiDataPath();
    }

    protected String getPlatformRelatedXmiDataPath() {
        return getPlatformRelatedDataPath() + XMI_FOLDER;
    }

    protected String getPlatformRelatedDataPath() {
        return DATA_PATH;
    }

}
