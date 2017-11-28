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
package org.eclipse.sirius.tests.unit.diagram.layout.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.LayoutHelper;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.LayoutHelper.LayoutDifference;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataHelper;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.AdvancedSiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.semantic.SiriusLayoutDataManagerForSemanticElements;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.impl.DRepresentationImpl;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Test class for {@link SiriusLayoutDataManagerForSemanticElements}.
 * 
 * @author dlecan
 */
public class AbstractSiriusLayoutDataManagerForSemanticElementsTest extends SiriusDiagramTestCase {

    protected static final boolean REGENERATE_TEST_DATA = false;

    protected static final Comparator<DRepresentation> USING_NAME = new Comparator<DRepresentation>() {
        public int compare(final DRepresentation o1, final DRepresentation o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    protected static final Predicate<Diagram> NO_RAW_DIAGRAM = new Predicate<Diagram>() {
        public boolean apply(final Diagram input) {
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

        protected final int numberOfNodeLayoutData;

        protected final int numberOfEdgeLayoutData;

        protected boolean raw = false;

        protected DiagramEditPart diagramEditPart;

        protected DiagramEditor rawDiagramEditor;

        protected Diagram(final String name, final int numberOfNodeLayoutData, final int numberOfEdgeLayoutData) {
            this.name = name;
            this.numberOfNodeLayoutData = numberOfNodeLayoutData;
            this.numberOfEdgeLayoutData = numberOfEdgeLayoutData;
        }

        protected Diagram(final String name, final int numberOfNodeLayoutData, final int numberOfEdgeLayoutData, final boolean raw) {
            this.name = name;
            this.numberOfNodeLayoutData = numberOfNodeLayoutData;
            this.numberOfEdgeLayoutData = numberOfEdgeLayoutData;
            this.raw = raw;
        }
    }

    protected static final int ITERATIONS = 20;

    protected static final String XMI_PREFIX = "storedLayout-";

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

    protected static final Diagram DIAG_TYPE6_MYPACKAGE = new Diagram("DiagType6 of MyPackage", 7, 2);

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

    protected static final Representation REPRES_TYPE9 = new Representation("DiagType9", DIAG_TYPE9_MYPACKAGE, DIAG_TYPE9_RAW);

    protected static final Representation[] ALL_REPRESENTATIONS = { REPRES_TYPE1, REPRES_TYPE2, REPRES_TYPE3, REPRES_TYPE4, REPRES_TYPE5, REPRES_TYPE6, REPRES_TYPE7, REPRES_TYPE8, REPRES_TYPE9 };

    protected static final String[][] ENCODED_CHARS = { { " ", "__" } };

    protected final List<IEditorPart> editorParts = new ArrayList<>();

    protected static final double LOW_ZOOM_LEVEL = 0.50;

    // Really, shouldn't be higher than 15px
    protected static final double LOW_ZOOM_DISTANCE = 15;

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
        String representationsPath = PLUGIN_PATH + getPlatformRelatedDataPath() + SESSION_MODEL_NAME;
        String semanticPath = PLUGIN_PATH + getPlatformRelatedDataPath() + SEMANTIC_MODEL_NAME;
        String modelerPath = PLUGIN_PATH + getPlatformRelatedDataPath() + MODELER_NAME;
        genericSetUp(semanticPath, modelerPath, representationsPath);

        // Disable refresh on opening
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        TestsUtil.emptyEventsFromUIThread();
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

        final List<DRepresentation> allDDiagrams = Lists.newArrayList(getRepresentations(representation.name));

        assertEquals("The number of expected diagrams is wrong", representation.diagrams.size(), allDDiagrams.size());

        Collections.sort(allDDiagrams, USING_NAME);

        Iterable<Diagram> diagrams;
        if (rawFiltered) {
            // Raw diagram must be excluded, it is "special"
            diagrams = Iterables.filter(representation.diagrams, NO_RAW_DIAGRAM);
        } else {
            diagrams = representation.diagrams;
        }

        for (final Diagram diagram : diagrams) {

            final DRepresentation key = new DRepresentationImpl() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public String getName() {
                    return diagram.name;
                }
            };

            final int search = Collections.binarySearch(allDDiagrams, key, USING_NAME);

            assertTrue("Diagram is not found in representation", search > -1);

            final DDiagram dDiagram = (DDiagram) allDDiagrams.get(search);

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
        for (final DRepresentation representation : getRepresentations(diagram.parent.name)) {
            final DDiagram dDiagram = (DDiagram) representation;

            if (diagram.name.equals(dDiagram.getName())) {
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

    protected LayoutDifference<?> loadAndCompare(final Diagram diag, final String path, AdvancedSiriusLayoutDataManager newManager, Configuration configuration) throws IOException {
        // Load referenced data
        final List<EObject> contents = loadReferenceData(path);

        // Data will be sorted by keys, which are comparable
        final Map<LayoutDataKey, EObject> expected = new TreeMap<LayoutDataKey, EObject>();
        for (final EObject eObject : contents) {
            final LayoutDataKey key = LayoutDataHelper.INSTANCE.createKey((AbstractLayoutData) eObject);
            expected.put(key, eObject);
        }

        // Sort elements, SemanticNodeLayoutDataKey are comparable
        final Map<LayoutDataKey, AbstractLayoutData> rootNodeLayoutData = new TreeMap<LayoutDataKey, AbstractLayoutData>(newManager.getRootNodeLayoutData());

        // Compare results
        final LayoutDifference<?> difference = LayoutHelper.INSTANCE.computeFirstLayoutDifference(expected.values(), rootNodeLayoutData.values(), configuration);
        return difference;
    }

    // Let this "unused" method, as it can be enabled to save diagram layout
    // data
    // Data will be saved in org.eclipse.sirius.tests project folder
    protected void saveDiagram(final Diagram diagram, final Collection<? extends AbstractLayoutData> layoutData) throws IOException {
        final String pathName = getPlatformRelatedXmiDataPath() + encodeDiagramName(diagram) + XMI_EXTENSION;
        saveDiagram(layoutData, pathName);
    }

    protected void saveDiagram(final Collection<? extends AbstractLayoutData> layoutData, final String path) throws IOException {
        final ResourceSet resourceSet = new ResourceSetImpl();
        final URI uri = URI.createFileURI(path);
        final Resource resource = ModelUtils.createResource(uri, resourceSet);
        resource.getContents().addAll(layoutData);
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
        String path = DATA_PATH;
        String platformVersion = (String) Platform.getBundle("org.eclipse.core.runtime").getHeaders().get("Bundle-Version");
        if (platformVersion.startsWith("3.3") || platformVersion.startsWith("3.4") || platformVersion.startsWith("3.5")) {
            path = DATA_PATH + "3.5/";
        } else if (platformVersion.startsWith("3.6")) {
            path = DATA_PATH + "3.6/";
        }
        return path;
    }

}
