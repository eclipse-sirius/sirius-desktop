/*******************************************************************************
 * Copyright (c) 2017, 2019 Obeo.
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
package org.eclipse.sirius.tests.unit.api.representation;

import java.util.Collections;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.internal.representation.DRepresentationLocationManager;
import org.eclipse.sirius.business.internal.resource.SiriusRepresentationXMIResourceImpl;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Test class to check the {@link DRepresentationLocationManager} behavior with the DRepLocationRuleForLocalResource
 * extension.
 * 
 * @author fbarbin
 *
 */
public class DRepresentationLocationManagerTest extends GenericTestCase {

    private boolean oldPropertyValue;

    private static final String EXPECTED_SRM_PATH = SiriusTestCase.TEMPORARY_PROJECT_NAME + "/.representations/";

    private static final String PATH = "/data/unit/session/open/";

    private static final String SEMANTIC_MODEL_FILENAME = "test.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME;

    protected static final String MODELER_PATH = "org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME);
        genericSetUp(SEMANTIC_MODEL_FILENAME_PATH, Collections.emptyList());
        oldPropertyValue = TestsUtil.isCreateRepresentationInSeparateResource();
    }

    /**
     * Test the LocationManager With representations in the aird resource.
     */
    public void testDRepresentationLocationManagerWithSplitDeactivated() {
        TestsUtil.setCreateRepresentationInSeparateResource(false);

        // We test that even with forbidden URI characters in the description name, the URI will be properly normalized.
        String descriptionName = "desc?-|ri ption";
        Predicate<Resource> predicate = (r) -> {
            return session.getSessionResource().equals(r);
        };
        checkDRepresentationLocationManager(descriptionName, DDiagram.class, predicate, session.getSessionResource());
        checkDRepresentationLocationManager(descriptionName, DTable.class, predicate, session.getSessionResource());
        checkDRepresentationLocationManager(descriptionName, DTree.class, predicate, session.getSessionResource());
    }

    /**
     * Test the LocationManager With representations separates in different resources.
     */
    public void testDRepresentationLocationManagerWithSplitActivated() {
        TestsUtil.setCreateRepresentationInSeparateResource(true);

        // We test that even with forbidden URI characters in the description name, the URI will be properly normalized.
        String descriptionName = "desc?-|ri ption";
        String pattern = URI.createPlatformResourceURI(EXPECTED_SRM_PATH + descriptionName, true) + "_.*.srm";

        // We create several representations to make sure they have a different Resource URI from the previous one.
        URI previousURI = checkDRepresentationLocationManager(descriptionName, DDiagram.class, new ResourceNamePredicate(pattern, null, SiriusRepresentationXMIResourceImpl.class),
                session.getSessionResource());
        previousURI = checkDRepresentationLocationManager(descriptionName, DTable.class, new ResourceNamePredicate(pattern, previousURI, SiriusRepresentationXMIResourceImpl.class),
                session.getSessionResource());
        previousURI = checkDRepresentationLocationManager(descriptionName, DDiagram.class, new ResourceNamePredicate(pattern, previousURI, SiriusRepresentationXMIResourceImpl.class),
                session.getSessionResource());
        checkDRepresentationLocationManager(descriptionName, DTree.class, new ResourceNamePredicate(pattern, previousURI, SiriusRepresentationXMIResourceImpl.class), session.getSessionResource());

    }

    /**
     * Test that the {@link DRepresentationLocationManager} is properly integrated in the context of the creation of a
     * representation by using Sirius API.
     * 
     * @throws Exception
     *             if an error occurs while trying to load the VSM.
     */
    public void testDRepresentationLocationManagerIntegration() throws Exception {
        TestsUtil.setCreateRepresentationInSeparateResource(true);
        loadModeler(toURI(MODELER_PATH, ResourceURIType.RESOURCE_PLUGIN_URI), session.getTransactionalEditingDomain());
        initViewpoint("Design");
        Resource semanticResource = session.getSemanticResources().iterator().next();
        DRepresentation dRepresentation = createRepresentation("Entities", semanticResource.getContents().get(0));
        String pattern = URI.createPlatformResourceURI(EXPECTED_SRM_PATH + "Entities_" + dRepresentation.getUid() + ".srm", true).toString();
        Predicate<Resource> predicate = new ResourceNamePredicate(pattern, null, SiriusRepresentationXMIResourceImpl.class);
        assertTrue("Unexpected resource URI for the new created representation", predicate.test(dRepresentation.eResource()));
    }

    /**
     * Create a new sample representation of type "representationType" with a Description "descriptionName". Test the
     * DRepresentationLocationManager with this representation in the context of the given sessionResource.
     * 
     * @param descriptionName
     *            the description name.
     * @param representationType
     *            the type of the representation to create.
     * @param expectedResult
     *            the predicate that will check the result of the location manager.
     * @param sessionResource
     *            the main session resource.
     * @return the resource URI that hold the created representation.
     */
    public static URI checkDRepresentationLocationManager(String descriptionName, Class<? extends DRepresentation> representationType, Predicate<Resource> expectedResult, Resource sessionResource) {
        DRepresentationLocationManager locationManager = new DRepresentationLocationManager();
        DRepresentation dRepresentation = createSampleDRepresentation(descriptionName, DDiagram.class);
        Resource resource = locationManager.getOrCreateRepresentationResource(dRepresentation, sessionResource);
        assertTrue("Unexpected resource location (" + resource.getURI() + ") for representation of type " + descriptionName, expectedResult.test(resource));
        return resource.getURI();

    }

    private static DRepresentation createSampleDRepresentation(String descriptionName, Class<? extends DRepresentation> clazz) {
        DRepresentation representation = null;
        if (DDiagram.class.equals(clazz)) {
            representation = DiagramFactory.eINSTANCE.createDSemanticDiagram();
            DiagramDescription description = DescriptionFactory.eINSTANCE.createDiagramDescription();
            description.setName(descriptionName);
            ((DDiagram) representation).setDescription(description);

        } else if (DTree.class.equals(clazz)) {
            representation = TreeFactory.eINSTANCE.createDTree();
            TreeDescription description = org.eclipse.sirius.tree.description.DescriptionFactory.eINSTANCE.createTreeDescription();
            description.setName(descriptionName);
            ((DTree) representation).setDescription(description);
        } else if (DTable.class.equals(clazz)) {
            representation = TableFactory.eINSTANCE.createDTable();
            TableDescription description = org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory.eINSTANCE.createCrossTableDescription();
            description.setName(descriptionName);
            ((DTable) representation).setDescription(description);
        }
        return representation;
    }

    /**
     * This predicate tests whether the resource URI matches the given pattern and is different from the previous one.
     * 
     * @author fbarbin
     *
     */
    public class ResourceNamePredicate implements Predicate<Resource> {

        private String patternString;

        private URI previousURI;

        private Class<? extends Resource> resourceType;

        public ResourceNamePredicate(String pattern, URI previousURI, Class<? extends Resource> resourceType) {
            this.previousURI = previousURI;
            this.patternString = pattern;
            this.resourceType = resourceType;
        }

        @Override
        public boolean test(Resource r) {

            boolean value = this.resourceType.isInstance(r);
            if (value) {
                Pattern pattern = Pattern.compile(patternString);
                Matcher matcher = pattern.matcher(r.getURI().toString());
                value = matcher.matches();
            }
            if (value && previousURI != null) {
                value = !r.getURI().toString().equals(previousURI.toString());
            }
            return value;
        }

    }

    @Override
    protected void tearDown() throws Exception {
        try {
            super.tearDown();
        } finally {
            TestsUtil.setCreateRepresentationInSeparateResource(oldPropertyValue);
        }
    }
}
