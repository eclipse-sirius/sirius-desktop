/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.routing;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.collect.Iterables;

/**
 * 
 * @author cbrun
 */
public class EdgeRoutingStyleTests extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/org.eclipse.emf.ecore/model/Ecore.ecore";

    private static final String MODELER_PATH = "/org.eclipse.sirius.tests.junit/data/unit/routing/routing.odesign";

    private static final String REPRESENTATION_DESC_NAME = "Folding";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
    }

    public void testTreeRoutingStyleIsAppliedDuringDiagramCreation() {
        initViewpoint("TestFolding");

        DDiagram diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        DiagramDocumentEditor editor = (DiagramDocumentEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        Iterable<IDiagramEdgeEditPart> connections = Iterables.filter(editor.getDiagramEditPart().getConnections(), IDiagramEdgeEditPart.class);

        for (IDiagramEdgeEditPart connection : connections) {
            Edge edge = (Edge) connection.getModel();
            final RoutingStyle rstyle = (RoutingStyle) edge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
            assertEquals(Routing.TREE_LITERAL, rstyle.getRouting());
        }
    }
}
