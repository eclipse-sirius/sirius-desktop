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
package org.eclipse.sirius.tests.unit.api.vsm.editor;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.provider.ContainerMappingImportItemProvider;
import org.eclipse.sirius.diagram.description.provider.ContainerMappingItemProvider;
import org.eclipse.sirius.diagram.description.provider.NodeMappingImportItemProvider;
import org.eclipse.sirius.diagram.description.provider.NodeMappingItemProvider;
import org.eclipse.sirius.viewpoint.description.provider.DescriptionItemProviderAdapterFactory;

import junit.framework.TestCase;

/**
 * Simulate drag and drop in odesign editor of Node mapping to Node mapping or
 * to Container mapping and verify that in first case the Node mapping is a node
 * and in the second case the Node mapping was a bordered node.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class DragAndDropNodeTest extends TestCase {

    /**
     * Simulate the drag in odesign editor of Node mapping in Container mapping.
     * Verify that the Node mapping is a node and not a bordered node.
     */
    public void testSimulateDropNodeInContainer() {
        final AdapterFactory adapterFactory = new DescriptionItemProviderAdapterFactory();
        ContainerMapping containerMapping = DescriptionFactory.eINSTANCE.createContainerMapping();
        NodeMapping nodeMapping = DescriptionFactory.eINSTANCE.createNodeMapping();
        ContainerProvider provide = new ContainerProvider(adapterFactory);
        assertEquals("The node mapping must be a node and not a borderedNode", DescriptionPackage.eINSTANCE.getContainerMapping_SubNodeMappings(),
                provide.getChildFeatureContainer(containerMapping, nodeMapping));
    }

    /**
     * Simulate the drag in odesign editor of Node mapping in Node mapping.
     * Verify that the node is a bordered node and not a node.
     */
    public void testSimulateDropNodeInNode() {
        final AdapterFactory adapterFactory = new DescriptionItemProviderAdapterFactory();
        NodeMapping nodeMapping = DescriptionFactory.eINSTANCE.createNodeMapping();
        NodeProvider provide = new NodeProvider(adapterFactory);
        assertEquals("The node mapping must be a bordered node and not a node", DescriptionPackage.eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings(),
                provide.getChildFeatureNode(nodeMapping, nodeMapping));
    }

    /**
     * Simulate the drag in odesign editor of Node mapping in Node Import
     * mapping. Verify that the Node mapping is a bordered node and not a node.
     */
    public void testSimulateDropNodeInNodeImport() {
        final AdapterFactory adapterFactory = new DescriptionItemProviderAdapterFactory();
        NodeMappingImport nodeMappingImport = DescriptionFactory.eINSTANCE.createNodeMappingImport();
        NodeMapping nodeMapping = DescriptionFactory.eINSTANCE.createNodeMapping();
        NodeImportProvider provide = new NodeImportProvider(adapterFactory);
        assertEquals("The node mapping must be a bordered node and not a node", DescriptionPackage.eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings(),
                provide.getChildFeatureNodeImport(nodeMappingImport, nodeMapping));
    }

    /**
     * Simulate the drag in odesign editor of Node mapping in Container Import
     * mapping. Verify that the Node mapping is a node and not a bordered node.
     */
    public void testSimulateDropNodeInContainerImport() {
        final AdapterFactory adapterFactory = new DescriptionItemProviderAdapterFactory();
        ContainerMappingImport containerMappingImport = DescriptionFactory.eINSTANCE.createContainerMappingImport();
        NodeMapping nodeMapping = DescriptionFactory.eINSTANCE.createNodeMapping();
        ContainerImportProvider provide = new ContainerImportProvider(adapterFactory);
        assertEquals("The node must be a node and not a bordered node", DescriptionPackage.eINSTANCE.getContainerMapping_SubNodeMappings(),
                provide.getChildFeatureContainerImport(containerMappingImport, nodeMapping));
    }

    /**
     * Override ContainerMappingIteMProvider to access at getChildFeature
     * method.
     * 
     * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
     * 
     */
    private class ContainerProvider extends ContainerMappingItemProvider {

        public ContainerProvider(AdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        public EStructuralFeature getChildFeatureContainer(Object object, Object child) {
            return getChildFeature(object, child);
        }

    }

    /**
     * Override NodeMappingItemProvider to access at getChildFeature method.
     * 
     * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
     * 
     */
    private class NodeProvider extends NodeMappingItemProvider {

        public NodeProvider(AdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        public EStructuralFeature getChildFeatureNode(Object object, Object child) {
            return getChildFeature(object, child);
        }

    }

    /**
     * Override NodeMappingImportItemProvider to access at getChildFeature
     * method.
     * 
     * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
     * 
     */
    private class NodeImportProvider extends NodeMappingImportItemProvider {

        public NodeImportProvider(AdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        public EStructuralFeature getChildFeatureNodeImport(Object object, Object child) {
            return getChildFeature(object, child);
        }

    }

    /**
     * Override ContainerMappingImportItemProvider to access at getChildFeature
     * method.
     * 
     * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
     * 
     */
    private class ContainerImportProvider extends ContainerMappingImportItemProvider {

        public ContainerImportProvider(AdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        public EStructuralFeature getChildFeatureContainerImport(Object object, Object child) {
            return getChildFeature(object, child);
        }

    }
}
