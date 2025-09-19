/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.refresh;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.sirius.common.acceleo.aql.business.internal.AQLSiriusInterpreter;
import org.eclipse.sirius.diagram.BracketEdgeStyle;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EcoreIntrinsicExtender;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import junit.framework.TestCase;

/**
 * Test for VP-2075 (but adapted for each kind of mapping), local refresh should
 * update the style for every shape.
 * 
 * So far two things are tested :
 * 
 * 1 - the fact that the style will change (only checking its type) depending on
 * some predicates using the conditional style.
 * 
 * 2 - the fact that the style will be customized if a customization is enabled
 * (here again depending on a predicate on the model)
 * 
 * This test can be executed in standalone as long as the Acceleo3 build used
 * has this commit :
 * http://git.eclipse.org/c/m2t/org.eclipse.acceleo.git/commit/
 * ?id=4616d4721076ee2702e54b0c058c52b73589e59c
 * 
 * This test is using the elementsync.odesign file and should be kept in sync
 * with this file. It refers to some of the .odesign elements directly by the
 * indices.
 * 
 * @author cbrun
 */
public class DDiagramElementSynchronizerTest extends TestCase {

    private DiagramDescription description;

    private DSemanticDiagram diagram;

    private ModelAccessor accessor;

    private AQLSiriusInterpreter interpreter;


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        prepareForLoadingOdesign();

        Resource res = loadFromClassLoader("elementsync.odesign");

        description = (DiagramDescription) ((Viewpoint) ((Group) res.getContents().get(0)).getOwnedViewpoints().get(0)).getOwnedRepresentations().get(0);
        interpreter = new AQLSiriusInterpreter();

        accessor = new ModelAccessor();
        accessor.addExtender(new EcoreIntrinsicExtender(), ExtenderConstants.HIGH_PRIORITY);

        EObject model = EcoreUtil.copy(EcorePackage.eINSTANCE);
        /*
         * A model without resource will be detected as dangling (see
         * org.eclipse.sirius.business.internal.experimental.sync.
         * AbstractSynchronizerHelper.isTargetDying(DSemanticDecorator)) and as
         * such will not be updated.
         */
        Resource modelResource = new ResourceImpl();
        modelResource.getContents().add(model);

        diagram = DiagramFactory.eINSTANCE.createDSemanticDiagram();
        diagram.setTarget(model);
        diagram.setDescription(description);
        diagram.getActivatedLayers().add(description.getDefaultLayer());
    }

    private void prepareForLoadingOdesign() {
        EPackage.Registry.INSTANCE.put(ViewpointPackage.eNS_URI, ViewpointPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(DescriptionPackage.eNS_URI, DescriptionPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(org.eclipse.sirius.diagram.sequence.description.DescriptionPackage.eNS_URI, org.eclipse.sirius.diagram.sequence.description.DescriptionPackage.eINSTANCE);
    }

    /**
     * Test that the {@link DDiagramElementSynchronizer} will update a node
     * style depending on the mapping and current model state.
     * 
     * @throws Exception
     */
    public void testNodeConditionalStyle() throws Exception {
        /*
         * prepare the node.
         */
        DNode node = DiagramFactory.eINSTANCE.createDNode();
        diagram.getOwnedDiagramElements().add(node);
        node.setActualMapping(description_default_containermapping());
        node.setTarget(diagram.getTarget());

        /*
         * See VP-2075
         */
        refreshUsingDDiagramElementSynchronizer(node);

        assertNotNull("a style should have been set", node.getOwnedStyle());
        assertTrue("the default style is a basic shape (bundled image)", node.getOwnedStyle() instanceof BundledImage);
        refreshUsingDDiagramElementSynchronizer(node);

        renamePackageToEnableConditionalStyle(node.getTarget());

        refreshUsingDDiagramElementSynchronizer(node);

        assertTrue("now that the model has changed, the style should be a square.", node.getOwnedStyle() instanceof Square);

    }

    /**
     * shortcut for getting elements from elementsync.odesign.
     * 
     * @return the mapping named "Container"
     */
    private NodeMapping description_default_containermapping() {
        return description.getDefaultLayer().getNodeMappings().get(0);
    }

    /**
     * shortcut for getting elements from elementsync.odesign.
     * 
     * @return the mapping named "List"
     */
    private ContainerMapping description_default_listcontainermapping() {
        return description.getDefaultLayer().getContainerMappings().get(0);
    }

    /**
     * Test that the {@link DDiagramElementSynchronizer} will update a container
     * style depending on the mapping and current model state.
     * 
     * @throws Exception
     */
    public void testContainerRefreshConditionalStyle() throws Exception {
        /*
         * prepare the container.
         */
        DNodeContainer container = DiagramFactory.eINSTANCE.createDNodeContainer();
        diagram.getOwnedDiagramElements().add(container);
        container.setActualMapping(description_default_listcontainermapping());
        container.setTarget(diagram.getTarget());

        refreshUsingDDiagramElementSynchronizer(container);

        assertNotNull("a style should have been set", container.getOwnedStyle());
        assertTrue("the default style is a gradient", container.getOwnedStyle() instanceof FlatContainerStyle);

        renamePackageToEnableConditionalStyle(container.getTarget());

        refreshUsingDDiagramElementSynchronizer(container);

        assertTrue("now that the model has changed, the style should be a parallelogram.", container.getOwnedStyle() instanceof ShapeContainerStyle);

        doTestStyleCustomization(container);

    }

    private void doTestStyleCustomization(DDiagramElement container) {

        renamePackageToEnableStyleCustomization(container.getTarget());

        refreshUsingDDiagramElementSynchronizer(container);

        assertEquals("now that the model has changed, the style customization should have changed the label to the nsURI", EcorePackage.eNS_URI, container.getName());
    }

    /**
     * Test that the {@link DDiagramElementSynchronizer} will update a
     * listcontainer style depending on the mapping and current model state.
     * 
     * @throws Exception
     */
    public void testListRefreshConditionalStyle() throws Exception {
        /*
         * prepare the list.
         */
        DNodeList container = DiagramFactory.eINSTANCE.createDNodeList();
        diagram.getOwnedDiagramElements().add(container);
        container.setActualMapping(description_default_listcontainermapping());
        container.setTarget(diagram.getTarget());

        refreshUsingDDiagramElementSynchronizer(container);

        assertNotNull("a style should have been set", container.getOwnedStyle());
        assertTrue("the default style is a gradient", container.getOwnedStyle() instanceof FlatContainerStyle);

        renamePackageToEnableConditionalStyle(container.getTarget());

        refreshUsingDDiagramElementSynchronizer(container);

        assertTrue("now that the model has changed, the style should be a parallelogram.", container.getOwnedStyle() instanceof ShapeContainerStyle);

        doTestStyleCustomization(container);
    }

    /**
     * Test that the {@link DDiagramElementSynchronizer} will update an edge
     * style depending on the mapping and current model state.
     * 
     * @throws Exception
     */
    public void testEdgeRefreshConditionalStyle() throws Exception {
        DEdge edge = DiagramFactory.eINSTANCE.createDEdge();
        diagram.getOwnedDiagramElements().add(edge);
        edge.setActualMapping(description.getDefaultLayer().getEdgeMappings().get(0));
        edge.setTarget(diagram.getTarget());
        refreshUsingDDiagramElementSynchronizer(edge);
        assertNotNull("a style should have been set", edge.getOwnedStyle());
        assertFalse("the default style is a classic edge (not a bracket)", edge.getOwnedStyle() instanceof BracketEdgeStyle);

        renamePackageToEnableConditionalStyle(edge.getTarget());
        refreshUsingDDiagramElementSynchronizer(edge);

        assertTrue("now that the model has changed, the style should be a bracket", edge.getOwnedStyle() instanceof BracketEdgeStyle);

        doTestStyleCustomization(edge);

    }

    private void renamePackageToEnableConditionalStyle(EObject container) {
        ((EPackage) container).setName("aPackageStartingWithA");
    }

    private void renamePackageToEnableStyleCustomization(EObject eObject) {
        ((EPackage) eObject).setName("bPackageStartingWithB");
    }

    public void testListRefreshChangingMappingType() throws Exception {
        DNodeList container = DiagramFactory.eINSTANCE.createDNodeList();
        diagram.getOwnedDiagramElements().add(container);
        container.setActualMapping(description_default_listcontainermapping());
        container.setTarget(diagram.getTarget());
        refreshUsingDDiagramElementSynchronizer(container);
        assertNotNull("a style should have been set", container.getOwnedStyle());
        assertTrue("the default style is a gradient", container.getOwnedStyle() instanceof FlatContainerStyle);
        ((EPackage) container.getTarget()).setName("aPackageStartingWithA");

        refreshUsingDDiagramElementSynchronizer(container);

        assertTrue("now that the model has changed, the style should be a parallelogram.", container.getOwnedStyle() instanceof ShapeContainerStyle);
    }

    public void testContainerRefreshChangingMappingType() throws Exception {
        DNodeContainer container = DiagramFactory.eINSTANCE.createDNodeContainer();
        diagram.getOwnedDiagramElements().add(container);
        container.setActualMapping(description.getDefaultLayer().getContainerMappings().get(1));
        container.setTarget(diagram.getTarget());
        refreshUsingDDiagramElementSynchronizer(container);
        assertNotNull("a style should have been set", container.getOwnedStyle());
        assertTrue("the default style is a gradient", container.getOwnedStyle() instanceof FlatContainerStyle);
        ((EPackage) container.getTarget()).setName("aPackageStartingWithA");

        refreshUsingDDiagramElementSynchronizer(container);

        assertTrue("now that the model has changed, the style should be a parallelogram.", container.getOwnedStyle() instanceof ShapeContainerStyle);
    }

    private void refreshUsingDDiagramElementSynchronizer(DDiagramElement container) {
        DDiagramElementSynchronizer sync = new DDiagramElementSynchronizer(diagram, interpreter, accessor);
        sync.refresh(container);
    }

    /**
     * Tries and locate a model in the current class' classpath.
     * 
     * @param string
     *            Relative path to the model we seek (relative to this class).
     * @return The loaded resource.
     * @throws IOException
     *             Thrown if we could not access either this class' resource, or
     *             the file towards which <code>string</code> points.
     */
    protected Resource loadFromClassLoader(String string) throws IOException {
        final URL fileURL = getClass().getResource(string);
        final InputStream str = fileURL.openStream();
        final URI uri = URI.createURI(fileURL.toString());

        Resource.Factory resourceFactory = Resource.Factory.Registry.INSTANCE.getFactory(uri);
        if (resourceFactory == null) {
            // Most likely a standalone run. Try with a plain XMI resource
            resourceFactory = new XMIResourceFactoryImpl();
        }

        // resourceFactory cannot be null
        Resource res = resourceFactory.createResource(uri);
        res.load(str, Collections.emptyMap());
        str.close();
        return res;
    }

}
