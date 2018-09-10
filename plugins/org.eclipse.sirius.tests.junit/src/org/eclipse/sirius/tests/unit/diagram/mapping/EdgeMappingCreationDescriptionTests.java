/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.mapping;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContentLayerHelper;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.tools.api.refresh.BestMappingGetter;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Tests for edge mapping description.
 * 
 * @author lredor
 */
public class EdgeMappingCreationDescriptionTests extends SiriusDiagramTestCase {

    private static final String PATH = "data/unit/mappings/VP-886/";

    private static final String MODELER_RESOURCE_NAME = "VP-886.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-886.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "VP-886.aird";

    private EdgeCreationDescription edgeCreationDescription;

    private EdgeMapping edgeMapping2;

    private DNode source;

    private DNode target;

    /** The semantic element corresponding to the edge. */
    private EReference reference;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + MODELER_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + REPRESENTATIONS_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);

        URI modelerResourceURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME, true);
        Resource modelerResource = session.getTransactionalEditingDomain().getResourceSet().getResource(modelerResourceURI, true);
        Viewpoint viewpoint = ((Group) modelerResource.getContents().get(0)).getOwnedViewpoints().get(0);
        DiagramDescription diagramDescription = (DiagramDescription) viewpoint.getOwnedRepresentations().get(0);
        edgeCreationDescription = (EdgeCreationDescription) diagramDescription.getDefaultLayer().getAllTools().get(0);
        edgeMapping2 = ContentLayerHelper.getAllEdgeMappings(diagramDescription.getDefaultLayer()).get(1);

        DAnalysis dAnalysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        EList<DRepresentationElement> ownedRepresentationElements = new DViewQuery(dAnalysis.getOwnedViews().get(0)).getLoadedRepresentations().get(0).getOwnedRepresentationElements();
        source = (DNode) ownedRepresentationElements.get(0);
        target = (DNode) ownedRepresentationElements.get(1);

        EPackage ePackage = (EPackage) session.getSemanticResources().iterator().next().getContents().get(0);
        EClass eClass1 = (EClass) ePackage.getEClassifiers().get(0);
        reference = eClass1.getEAllReferences().get(0);

    }

    public void testBestMappingWhenEdgeCreationDescriptionHaveTwoMappings() throws Exception {
        EdgeMapping edgeMapping = new BestMappingGetter(source, target, reference).getBestEdgeMapping(edgeCreationDescription.getEdgeMappings());
        assertEquals("Bad edge mapping return", edgeMapping2, edgeMapping);
    }

    @Override
    protected void tearDown() throws Exception {
        edgeCreationDescription = null;
        edgeMapping2 = null;
        source = null;
        target = null;
        reference = null;
        super.tearDown();
    }

}
