/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.Point;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper.FormatDifference;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.unit.diagram.format.data.manager.mappingbased.MappingBasedTestConfiguration;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test class for {@link MappingBasedSiriusFormatDataManager}. Inspired from
 * {@link SiriusFormatDataManagerForSemanticElementsApplyWithPredefinedDataTest}.
 * 
 * @author adieumegard
 */
@RunWith(value = Parameterized.class)
public class AbstractMappingBasedSiriusFormatDataManagerTest extends AbstractSiriusFormatDataManagerForSemanticElementsTest {

    protected final Representation representationToCopyFormat;

    /** Name of the file containing the semantic model for target mapped elements */
    protected static final String SEMANTIC_TARGET_MODEL_NAME = "MyTarget.ecore";

    /** Semantic model for target mapped elements */
    protected EObject semanticTargetModel;

    protected static final String MAPPING_XMI_PREFIX = "storedMapping-";

    @SuppressWarnings("hiding")
    protected static final String DATA_PATH = "data/unit/layout/mappingbased/";

    @SuppressWarnings("hiding")
    protected static final String FULL_DATA_PATH = PLUGIN_PATH + DATA_PATH;

    /** Mapping xmi files path */
    protected static final String FULL_MAPPINGS_FOLDER = FULL_DATA_PATH + XMI_FOLDER;

    protected static class RepresentationWithNotes extends Representation {

        protected RepresentationWithNotes(String name, final Diagram... diagrams) {
            super(name, diagrams);
        }

    }

    protected static final ResourceSetListenerImpl ROLLBACK_LISTENER = new ResourceSetListenerImpl() {
        @Override
        public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
            throw new RollbackException(new Status(IStatus.ERROR, SiriusTestsPlugin.PLUGIN_ID, "Don't want to change this diagram"));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isAggregatePrecommitListener() {
            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isPrecommitOnly() {
            return true;
        }
    };

    /**
     * Compute configuration for source to target EObjects mapping. Uses all the source model elements.
     * 
     * @return
     */
    protected MappingBasedTestConfiguration getFullTestConfiguration() {
        Map<String, String> full_map = new HashMap<String, String>();
        full_map.put("//p1", "//targetp1");
        full_map.put("//p1/C1-1", "//targetp1/targetC1-1");
        full_map.put("//p1/C1-1/aC1-2", "//targetp1/targetC1-1/targetaC1-2");
        full_map.put("//p1/C1-1/aC1-2/@eGenericType", "//targetp1/targetC1-1/targetaC1-2/@eGenericType");
        full_map.put("//p1/C1-1/aC1-1-1", "//targetp1/targetC1-1/targetaC1-1-1");
        full_map.put("//p1/C1-1/aC1-1-1/@eGenericType", "//targetp1/targetC1-1/targetaC1-1-1/@eGenericType");
        full_map.put("//p1/C1-2", "//targetp1/targetC1-2");
        full_map.put("//p1/C1-3", "//targetp1/targetC1-3");
        full_map.put("//p1/C1-3/m1", "//targetp1/targetC1-3/targetm1");
        full_map.put("//p1/p1-1", "//targetp1/targetp1-1");
        full_map.put("//p1/p1-1/C1-1-1", "//targetp1/targetp1-1/targetC1-1-1");
        full_map.put("//p1/p1-1/C1-1-1/m1", "//targetp1/targetp1-1/targetC1-1-1/targetm1");
        full_map.put("//p1/p1-1/C1-1-2", "//targetp1/targetp1-1/targetC1-1-2");
        full_map.put("//p1/p1-2", "//targetp1/targetp1-2");
        full_map.put("//p1/p1-2/C1-2-1", "//targetp1/targetp1-2/targetC1-2-1");
        full_map.put("//p1/p1-3", "//targetp1/targetp1-3");
        full_map.put("//p2", "//targetp2");
        full_map.put("//p2/p2-1", "//targetp2/targetp2-1");
        full_map.put("//p2/p2-1/new%20EClass%201", "//targetp2/targetp2-1/targetnew%20EClass%201");
        full_map.put("//p2/p2-1/new%20EClass%201/m1", "//targetp2/targetp2-1/targetnew%20EClass%201/targetm1");
        full_map.put("//p2/p2-2", "//targetp2/targetp2-2");
        full_map.put("//p3", "//targetp3");
        full_map.put("//p4", "//targetp4");

        return new MappingBasedTestConfiguration(semanticModel, semanticTargetModel, full_map, null, "full");
    }

    /**
     * Compute configuration for source to target EObjects mapping. Uses a subset of the source model elements.
     * 
     * @return
     */
    protected MappingBasedTestConfiguration getSubsetTestConfiguration() {
        Map<String, String> subset_map = new HashMap<String, String>();
        subset_map.put("//p1", "//targetp1");
        subset_map.put("//p1/C1-1", "//targetp1/targetC1-1");
        subset_map.put("//p1/C1-1/aC1-1-1", "//targetp1/targetC1-1/targetaC1-1-1");
        subset_map.put("//p1/C1-1/aC1-1-1/@eGenericType", "//targetp1/targetC1-1/targetaC1-1-1/@eGenericType");
        subset_map.put("//p1/C1-2", "//targetp1/targetC1-2");
        subset_map.put("//p1/p1-1", "//targetp1/targetp1-1");
        subset_map.put("//p1/p1-1/C1-1-1", "//targetp1/targetp1-1/targetC1-1-1");
        subset_map.put("//p1/p1-1/C1-1-1/m1", "//targetp1/targetp1-1/targetC1-1-1/targetm1");
        subset_map.put("//p2", "//targetp2");
        subset_map.put("//p2/p2-1", "//targetp2/targetp2-1");
        subset_map.put("//p2/p2-1/new%20EClass%201", "//targetp2/targetp2-1/targetnew%20EClass%201");
        subset_map.put("//p2/p2-2", "//targetp2/targetp2-2");
        subset_map.put("//p4", "//targetp4");

        return new MappingBasedTestConfiguration(semanticModel, semanticTargetModel, subset_map, null, "subset");
    }

    private String oldFont;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        // Load target mapping model
        semanticModel = getModelFromPath(PLUGIN_PATH + getPlatformRelatedDataPath() + getSemanticModelName(), session);
        semanticTargetModel = getModelFromPath(PLUGIN_PATH + getPlatformRelatedDataPath() + getSemanticTargetModelName(), session);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        oldFont = changeDefaultFontName("Sans");
    }

    @Override
    protected String getDataPath() {
        return DATA_PATH;
    }

    @Override
    protected List<String> getSemanticModelPaths() {
        return Arrays.asList(PLUGIN_PATH + getPlatformRelatedDataPath() + getSemanticModelName(), PLUGIN_PATH + getPlatformRelatedDataPath() + getSemanticTargetModelName());
        // return Arrays.asList(TEMPORARY_PROJECT_NAME + "/" + getSemanticModelName(), TEMPORARY_PROJECT_NAME + "/" +
        // getSemanticTargetModelName());
    }

    protected EObject getModelFromPath(String semanticTargetPath, Session session) {
        URI semanticTargetModelURI = toURI(semanticTargetPath);
        if (semanticTargetModelURI != null) {
            Optional<Resource> optRes = session.getSemanticResources().stream().filter(res -> res.getURI().equals(semanticTargetModelURI)).findFirst();
            if (optRes.isPresent()) {
                Resource res = optRes.get();
                return res.getContents().get(0);
            }
        }
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        // TODO Auto-generated method stub
        super.tearDown();

        if (oldFont != null) {
            changeDefaultFontName(oldFont);
        }
    }

    protected String getSemanticTargetModelName() {
        return SEMANTIC_TARGET_MODEL_NAME;
    }

    protected EObject addModelToSession(Session theSession, String semanticTargetPath) {
        URI semanticTargetModelURI = toURI(semanticTargetPath);
        if (semanticTargetModelURI != null) {
            if (!theSession.getSemanticResources().stream().anyMatch(res -> res.getURI().equals(semanticTargetModelURI))) {
                Command addSemanticResourceCmd = new AddSemanticResourceCommand(theSession, semanticTargetModelURI, new NullProgressMonitor());
                theSession.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);
            }
        }
        Iterator<Resource> resourcesIterator = theSession.getSemanticResources().iterator();
        while (resourcesIterator.hasNext()) {
            Resource semanticTargetResource = resourcesIterator.next();
            if (semanticTargetResource.getURI().equals(semanticTargetModelURI)) {
                if (!semanticTargetResource.getContents().isEmpty()) {
                    return semanticTargetResource.getContents().get(0);
                }
            }
        }
        return null;
    }

    /**
     * Constructor for parameterized test.
     * 
     * @param representationToCopyFormat
     *            a representation description
     * @param representationToPasteFormat
     *            a representation description
     * @throws Exception
     */
    public AbstractMappingBasedSiriusFormatDataManagerTest(Representation representationToCopyFormat) throws Exception {
        this.representationToCopyFormat = representationToCopyFormat;
    }

    /**
     * Overridden version of the loadAndCompare method taking into account the need to filter the elements to compare
     * (only take the elements that are provided in the mapping).
     * 
     * @param path
     *            The path to the file to load.
     * @param newManager
     *            The FormatDataManager containing the checked diagram FormatData.
     * @param configuration
     *            The test configuration.
     * @param explicitMappingTestConfiguration
     *            The mapping test configuration.
     * @return
     * @throws IOException
     */
    protected FormatDifference<?> loadAndCompareFiltered(final String path, MappingBasedSiriusFormatDataManager newManager, Configuration configuration,
            MappingBasedTestConfiguration explicitMappingTestConfiguration) throws IOException {
        // Load referenced data
        final List<EObject> contents = loadReferenceData(path);

        // Data will be sorted by id and content
        final List<AbstractFormatData> expected = new ArrayList<AbstractFormatData>();
        for (final EObject eObject : contents) {
            expected.add((AbstractFormatData) eObject);
        }
        Collections.sort(expected, new Comparator<EObject>() {
            @Override
            public int compare(EObject o1, EObject o2) {
                AbstractFormatData formatData1 = (AbstractFormatData) o1;
                AbstractFormatData formatData2 = (AbstractFormatData) o2;
                return toComparableString(formatData1).compareTo(toComparableString(formatData2));
            }
        });

        // Get checked diagram filtered and sorted FormatData
        Collection<AbstractFormatData> filteredData = getSortedAbstractFormatDataFromManager(explicitMappingTestConfiguration, newManager);

        // Compare results
        final FormatDifference<?> difference = FormatHelper.INSTANCE.computeFirstFormatDifference(expected, filteredData, configuration);
        return difference;
    }

    /**
     * Overridden version of the SaveDiagram method saving format data of a diagram. This version only save FormatData
     * for diagram elements that represent non filtered elements (elements that are present in the
     * {@code explicitMappingTestConfiguration}).
     * 
     * @param path
     *            The file path where to save the FormatData information.
     * @param explicitMappingTestConfiguration
     *            The mapping test configuration.
     * @param newManager
     *            The FormatDataManager containing the saved diagram FormatData.
     * @throws IOException
     */
    protected void saveDiagramFiltered(final String path, final MappingBasedTestConfiguration explicitMappingTestConfiguration, MappingBasedSiriusFormatDataManager newManager) throws IOException {
        final ResourceSet resourceSet = new ResourceSetImpl();
        final URI uri = URI.createFileURI(path);
        final Resource resource = ModelUtils.createResource(uri, resourceSet);

        Collection<AbstractFormatData> filteredData = getSortedAbstractFormatDataFromManager(explicitMappingTestConfiguration, newManager);
        resource.getContents().addAll(filteredData);

        resource.save(Collections.EMPTY_MAP);
    }

    /**
     * Retrieve FormatData among the one present in {@code newManager} for every element present in the mapping provided
     * in {@code explicitMappingTestConfiguration}.
     * 
     * @param explicitMappingTestConfiguration
     *            The mapping test configuration.
     * @param newManager
     *            The FormatDataManager containing the diagram FormatData.
     * @return
     */
    private Collection<AbstractFormatData> getSortedAbstractFormatDataFromManager(MappingBasedTestConfiguration explicitMappingTestConfiguration, MappingBasedSiriusFormatDataManager newManager) {
        final Collection<Map<String, NodeFormatData>> rootNodeFormatData = newManager.getRootNodeFormatData().values();
        final Collection<Map<String, EdgeFormatData>> edgesFormatData = newManager.getEdgeFormatData().values();

        List<AbstractFormatData> filteredData = filterAndRetrieveNodeFormatData(explicitMappingTestConfiguration, rootNodeFormatData);
        filteredData.addAll(filterAndRetrieveEdgeFormatData(explicitMappingTestConfiguration, edgesFormatData));
        Collections.sort(filteredData, new Comparator<EObject>() {
            @Override
            public int compare(EObject o1, EObject o2) {
                AbstractFormatData formatData1 = (AbstractFormatData) o1;
                AbstractFormatData formatData2 = (AbstractFormatData) o2;
                return toComparableString(formatData1).compareTo(toComparableString(formatData2));
            }
        });

        return filteredData;
    }

    /**
     * Computes a String representation for the AbstractFormatData. We need a string representation that is independent
     * of the Object ID, the ID used here is the id of the target element. This method is based on the code from the
     * toString methods of {@link NodeFormatData} and {@link EdgeFormatData}.
     * 
     * @param formatData
     *            The format data to convert.
     * @return
     */
    private String toComparableString(AbstractFormatData formatData) {
        StringBuffer result = new StringBuffer(formatData.getId());
        if (formatData instanceof NodeFormatData) {
            NodeFormatData nFormatData = (NodeFormatData) formatData;
            Point location = nFormatData.getLocation();
            if (location != null) {
                result.append(" (x: "); //$NON-NLS-1$
                result.append(location.getX());
                result.append(", y: "); //$NON-NLS-1$
                result.append(location.getY());
                result.append(") "); //$NON-NLS-1$
            }

            result.append(" (width: "); //$NON-NLS-1$
            result.append(nFormatData.getWidth());
            result.append(", height: "); //$NON-NLS-1$
            result.append(nFormatData.getHeight());
            result.append(')');
        } else {
            EdgeFormatData eFormatData = (EdgeFormatData) formatData;
            result.append(" (sourceTerminal: "); //$NON-NLS-1$
            result.append(eFormatData.getSourceTerminal());
            result.append(", targetTerminal: "); //$NON-NLS-1$
            result.append(eFormatData.getTargetTerminal());
            result.append(", routing: "); //$NON-NLS-1$
            result.append(eFormatData.getRouting());
            result.append(", jumpLinkStatus: "); //$NON-NLS-1$
            result.append(eFormatData.getJumpLinkStatus());
            result.append(", jumpLinkType: "); //$NON-NLS-1$
            result.append(eFormatData.getJumpLinkType());
            result.append(", reverseJumpLink: "); //$NON-NLS-1$
            result.append(eFormatData.isReverseJumpLink());
            result.append(", smoothness: "); //$NON-NLS-1$
            result.append(eFormatData.getSmoothness());
            result.append(')');
        }
        return result.toString();
    }

    /**
     * Filter the list of {@link NodeFormatData} values of {@code formatData} to only keep format data corresponding to
     * elements not filtered in the mapping configuration.
     * 
     * @param explicitMappingTestConfiguration
     *            The mapping configuration.
     * @param formatData
     *            The format data to filter.
     * @return
     */
    private List<AbstractFormatData> filterAndRetrieveNodeFormatData(MappingBasedTestConfiguration explicitMappingTestConfiguration, Collection<Map<String, NodeFormatData>> formatData) {
        List<AbstractFormatData> result = new ArrayList<AbstractFormatData>();

        Collection<EObject> acceptedValues = explicitMappingTestConfiguration.getObjectsMap().values();
        List<String> acceptedValuesIDs = acceptedValues.stream().map(val -> EcoreUtil.getURI(val).fragment()).collect(Collectors.toList());
        Iterator<Map<String, NodeFormatData>> formatDataIterator = formatData.iterator();

        while (formatDataIterator.hasNext()) {
            Map<String, NodeFormatData> formatDataMap = formatDataIterator.next();
            if (!formatDataMap.isEmpty()) {
                formatDataMap.forEach((k, v) -> {
                    if (acceptedValuesIDs.contains(v.getId())) {
                        result.add(copyFormatDataWithoutChildren(v));
                    }
                    if (v instanceof NodeFormatData) {
                        result.addAll(filterAndRetrieveSubFormatData(v.getChildren(), acceptedValuesIDs));
                    }
                });
            }
        }

        return result;
    }

    /**
     * Filter the list of {@link EdgeFormatData} values of {@code formatData} to only keep format data corresponding to
     * elements not filtered in the mapping configuration.
     * 
     * @param explicitMappingTestConfiguration
     *            The mapping configuration.
     * @param formatData
     *            The format data to filter.
     * @return
     */
    private List<AbstractFormatData> filterAndRetrieveEdgeFormatData(MappingBasedTestConfiguration explicitMappingTestConfiguration, Collection<Map<String, EdgeFormatData>> formatData) {
        List<AbstractFormatData> result = new ArrayList<AbstractFormatData>();

        Collection<EObject> acceptedValues = explicitMappingTestConfiguration.getObjectsMap().values();
        List<String> acceptedValuesIDs = acceptedValues.stream().map(val -> EcoreUtil.getURI(val).fragment()).collect(Collectors.toList());
        Iterator<Map<String, EdgeFormatData>> formatDataIterator = formatData.iterator();

        while (formatDataIterator.hasNext()) {
            Map<String, EdgeFormatData> formatDataMap = formatDataIterator.next();
            if (!formatDataMap.isEmpty()) {
                formatDataMap.forEach((k, v) -> {
                    if (acceptedValuesIDs.contains(v.getId())) {
                        result.add(copyFormatDataWithoutChildren(v));
                    }
                    if (v instanceof NodeFormatData) {
                        result.addAll(filterAndRetrieveSubFormatData(((NodeFormatData) v).getChildren(), acceptedValuesIDs));
                    }
                });
            }
        }

        return result;
    }

    /**
     * Filter the list of children {@link NodeFormatData} of {@code formatData} to only keep format data corresponding
     * to elements not filtered in the mapping configuration.
     * 
     * @param explicitMappingTestConfiguration
     *            The mapping configuration.
     * @param formatData
     *            The format data to filter.
     * @return
     */
    private List<AbstractFormatData> filterAndRetrieveSubFormatData(EList<NodeFormatData> children, List<String> acceptedValuesIDs) {
        List<AbstractFormatData> result = new ArrayList<AbstractFormatData>();
        children.forEach(nfd -> {
            if (acceptedValuesIDs.contains(nfd.getId())) {
                result.add(copyFormatDataWithoutChildren(nfd));
                if (nfd instanceof NodeFormatData) {
                    result.addAll(filterAndRetrieveSubFormatData(nfd.getChildren(), acceptedValuesIDs));
                }
            }
        });
        return result;
    }

    /**
     * @param toCopy
     *            The element to copy
     * @return A copy of a format data and remove the content of the children collection in the copy
     */
    private AbstractFormatData copyFormatDataWithoutChildren(AbstractFormatData toCopy) {
        AbstractFormatData fullCopy = SiriusCopierHelper.copyWithNoUidDuplication(toCopy);
        if (toCopy instanceof NodeFormatData) {
            ((NodeFormatData) fullCopy).getChildren().clear();
        }
        return fullCopy;
    }

    protected String encodeDiagramName(final String name) {
        String diagramName = name;
        for (final String[] couple : ENCODED_CHARS) {
            diagramName = diagramName.replaceAll(couple[0], couple[1]);
        }

        return XMI_PREFIX + diagramName;
    }

    /**
     * Helper to retrieve a DiagramEditPart from a DRepresentation.
     * 
     * @param session
     * @param representation
     * @return
     */
    protected Collection<DiagramEditPart> getDiagramEditPart(Session session, DRepresentation representation) {
        final List<DiagramEditPart> result = new ArrayList<DiagramEditPart>();
        final Collection<EObject> data = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, representation);
        Shell shell = new Shell();
        for (final EObject dataElement : data) {
            if (dataElement instanceof org.eclipse.gmf.runtime.notation.Diagram) {
                final org.eclipse.gmf.runtime.notation.Diagram diagram = (org.eclipse.gmf.runtime.notation.Diagram) dataElement;
                final DiagramEditPartService tool = new DiagramEditPartService();
                final DiagramEditPart diagramEditPart = tool.createDiagramEditPart(diagram, shell, PreferencesHint.USE_DEFAULTS);
                result.add(diagramEditPart);
            }
        }
        Display.getCurrent().asyncExec(new Runnable() {
            @Override
            public void run() {
                shell.dispose();
            }
        });
        return result;
    }

    @Override
    protected String getPlatformRelatedDataPath() {
        String path = getDataPath();
        String platformVersion = Platform.getBundle("org.eclipse.core.runtime").getHeaders().get("Bundle-Version");
        if (platformVersion.startsWith("3.3") || platformVersion.startsWith("3.4") || platformVersion.startsWith("3.5")) {
            path = getDataPath() + "3.5/";
        } else if (platformVersion.startsWith("3.6")) {
            path = getDataPath() + "3.6/";
        }
        return path;
    }

    protected DRepresentation getDRepresentation(DiagramEditPart diagram) {
        org.eclipse.gmf.runtime.notation.Diagram diag = diagram.getDiagramView();
        if (diag != null && diag.eResource() != null && diag.getElement() instanceof DRepresentation) {
            return (DRepresentation) diag.getElement();
        }
        return null;
    }

    protected void exportDiagramToTempFolder(String name, DRepresentation dDiagram) {
        final File temporaryFolder = new File("path/to/folder");
        try {
            temporaryFolder.createNewFile();
            IPath tempsrcFilePath = new Path(temporaryFolder.getCanonicalPath()).append(name + ".png");
            System.out.println(tempsrcFilePath.toOSString());
            DialectUIManager.INSTANCE.export(dDiagram, session, tempsrcFilePath, new ExportFormat(ExportDocumentFormat.NONE, ImageFileFormat.PNG), new NullProgressMonitor());
        } catch (IOException | SizeTooLargeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change the default font.
     *
     * @param fontName
     *            the font name to set as default.
     * @return the previous default font name.
     */
    protected String changeDefaultFontName(String fontName) {
        IPreferenceStore preferenceStore = (IPreferenceStore) DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT.getPreferenceStore();
        FontData fontData = PreferenceConverter.getFontData(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT);

        // Get the actual font.
        String oldName = fontData.getName();

        // Change the font.
        fontData.setName(fontName);
        PreferenceConverter.setDefault(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT, fontData);
        return oldName;
    }
}
