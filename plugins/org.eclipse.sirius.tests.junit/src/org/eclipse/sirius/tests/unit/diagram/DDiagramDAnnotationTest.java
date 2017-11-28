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
package org.eclipse.sirius.tests.unit.diagram;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.SequenceFactory;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

import junit.framework.TestCase;

/**
 * Test to add a DAnnotation to a representation (Diagram, tree, table and
 * sequence).
 * 
 * @author jdupont
 * 
 */
public class DDiagramDAnnotationTest extends TestCase {

    private DAnnotation annotation;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
        annotation.setSource("Annotation");
        annotation.getDetails().put("Annotation", "Details of annotation");
    }

    /**
     * Test add DAnnotation on diagram.
     */
    public void test_DDiagram_AddDAnnotation() {
        DDiagram diagram = DiagramFactory.eINSTANCE.createDDiagram();
        diagram.getEAnnotations().add(annotation);
        assertEquals(annotation, diagram.getDAnnotation("Annotation"));
    }

    /**
     * Test add DAnnotation on table.
     */
    public void test_DTableAddDAnnotation() {
        DTable table = TableFactory.eINSTANCE.createDTable();
        table.getEAnnotations().add(annotation);
        assertEquals(annotation, table.getDAnnotation("Annotation"));
    }

    /**
     * Test add DAnnotation on sequence diagram.
     */
    public void test_DSequenceDiagram_AddDAnnotation() {
        SequenceDDiagram sequenceDiagram = SequenceFactory.eINSTANCE.createSequenceDDiagram();
        sequenceDiagram.getEAnnotations().add(annotation);
        assertEquals(annotation, sequenceDiagram.getDAnnotation("Annotation"));
    }
}
