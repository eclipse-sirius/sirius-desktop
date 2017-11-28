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
package org.eclipse.sirius.tests.unit.diagram.layers;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.Layer;

import junit.framework.TestCase;

/**
 * Tests edge mapping import basic functionalities;
 * 
 * @author mchauvin
 */
public class EdgeMappingImportTests extends TestCase {

    private Layer layer;

    private EdgeMapping importedEdgeMapping;

    private EdgeMappingImport edgeMappingImport;

    private EdgeMapping edgeMapping;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        layer = DescriptionFactory.eINSTANCE.createLayer();
        importedEdgeMapping = DescriptionFactory.eINSTANCE.createEdgeMapping();
        importedEdgeMapping.setDomainClass("uhuh");
        layer.getEdgeMappings().add(importedEdgeMapping);

        edgeMappingImport = DescriptionFactory.eINSTANCE.createEdgeMappingImport();
        edgeMappingImport.setImportedMapping(importedEdgeMapping);
        layer.getEdgeMappingImports().add(edgeMappingImport);

        edgeMapping = new IEdgeMappingQuery(edgeMappingImport).getEdgeMapping().get();
    }

    public void testContainer() throws Exception {
        assertEquals(edgeMappingImport.eContainer(), edgeMapping.eContainer());
    }

    public void testInternalContainer() {
        assertEquals(((InternalEObject) edgeMappingImport).eInternalContainer(), ((InternalEObject) edgeMapping).eInternalContainer());
    }

    public void testContainerFeatureID() {
        assertEquals(((InternalEObject) edgeMappingImport).eContainerFeatureID(), ((InternalEObject) edgeMapping).eContainerFeatureID());
    }

    public void testResource() throws Exception {
        final Resource rs = new XMLResourceImpl();
        rs.getContents().add(layer);
        assertEquals(edgeMappingImport.eResource(), edgeMapping.eResource());
    }

    public void testDirectResource() {
        final Resource rs = new XMLResourceImpl();
        rs.getContents().add(layer);
        assertEquals(((InternalEObject) edgeMappingImport).eDirectResource(), ((InternalEObject) edgeMapping).eDirectResource());
    }

    public void testInternalResource() {
        final Resource rs = new XMLResourceImpl();
        rs.getContents().add(layer);
        assertEquals(((InternalEObject) edgeMappingImport).eInternalResource(), ((InternalEObject) edgeMapping).eInternalResource());
    }

    public void testDomainClass() throws Exception {
        final Resource rs = new XMLResourceImpl();
        rs.getContents().add(layer);
        assertEquals(importedEdgeMapping.getDomainClass(), edgeMapping.getDomainClass());
    }

}
