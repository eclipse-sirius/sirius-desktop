/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.api.refresh.ColorFactory;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;

/**
 * Tests for Entities diagram of ecore modeler : derived and containment
 * references.
 * 
 * @author nlepine
 */
public class EntitiesDiagramDerivedReferenceTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/directEdit/testOperation.ecore";

    private static final String REPRESENTATIONS_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/directEdit/testOperation.aird";
	
    private DDiagram diagram;

    private VisualBindingManager colorManager;

    private ColorFactory colorFactory;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_PATH);

        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, semanticModel).toArray()[0];
        colorManager = new VisualBindingManager();
        colorManager.init(10, 10);
        colorFactory = new ColorFactory(colorManager);
    }

    public void testDerivedReferences() {
        EPackage ePackage = (EPackage) semanticModel;
        EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        EReference reference = eClass.getEReferences().get(0);
        assertNotNull(reference);

        DDiagramElement diagramElement = getFirstDiagramElement(diagram, reference);
        assertTrue(diagramElement instanceof DEdge);
        DEdge edge = (DEdge) diagramElement;
        assertTrue(!edge.getName().startsWith("/"));
        assertEquals(((EdgeStyle) edge.getStyle()).getSourceArrow(), EdgeArrows.NO_DECORATION_LITERAL);
        assertEquals(((EdgeStyle) edge.getStyle()).getTargetArrow(), EdgeArrows.INPUT_ARROW_LITERAL);
        assertEquals(colorFactory.create(((EdgeStyle) edge.getStyle()).getStrokeColor()), colorFactory.gray());

        // set derived reference
        setDerivedReference(reference, true);
        refresh(diagram);
        diagramElement = getFirstDiagramElement(diagram, reference);
        assertTrue(diagramElement instanceof DEdge);
        edge = (DEdge) diagramElement;
        assertTrue(edge.getName().contains("/" + reference.getName()));
        assertEquals(((EdgeStyle) edge.getStyle()).getSourceArrow(), EdgeArrows.NO_DECORATION_LITERAL);
        assertEquals(((EdgeStyle) edge.getStyle()).getTargetArrow(), EdgeArrows.INPUT_ARROW_LITERAL);
        assertEquals(colorFactory.create(((EdgeStyle) edge.getStyle()).getStrokeColor()), colorFactory.blue());

        // set containment but not derived reference
        setDerivedReference(reference, false);
        setContainmentReference(reference, true);
        refresh(diagram);
        diagramElement = getFirstDiagramElement(diagram, reference);
        assertTrue(diagramElement instanceof DEdge);
        edge = (DEdge) diagramElement;
        assertFalse(edge.getName().contains("/" + reference.getName()));
        assertEquals(((EdgeStyle) edge.getStyle()).getSourceArrow(), EdgeArrows.FILL_DIAMOND_LITERAL);
        assertEquals(((EdgeStyle) edge.getStyle()).getTargetArrow(), EdgeArrows.NO_DECORATION_LITERAL);
        assertEquals(colorFactory.create(((EdgeStyle) edge.getStyle()).getStrokeColor()), colorFactory.black());

        // set derived reference
        setDerivedReference(reference, true);
        refresh(diagram);
        diagramElement = getFirstDiagramElement(diagram, reference);
        assertTrue(diagramElement instanceof DEdge);
        edge = (DEdge) diagramElement;
        assertTrue(edge.getName().contains("/" + reference.getName()));
        assertEquals(((EdgeStyle) edge.getStyle()).getSourceArrow(), EdgeArrows.FILL_DIAMOND_LITERAL);
        assertEquals(((EdgeStyle) edge.getStyle()).getTargetArrow(), EdgeArrows.NO_DECORATION_LITERAL);
        assertEquals(colorFactory.create(((EdgeStyle) edge.getStyle()).getStrokeColor()), colorFactory.blue());
    }

    private void setDerivedReference(final EReference reference, final boolean b) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                reference.setDerived(b);
            }

        });
    }

    private void setContainmentReference(final EReference reference, final boolean b) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                reference.setContainment(b);
            }

        });
    }

}
