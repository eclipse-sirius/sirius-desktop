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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * Layers tests for Entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class EntitiesDiagramDropTests extends SiriusDiagramTestCase implements EcoreModeler {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(PACKAGES_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
    }

    public void testDropExternalEClass() {

        EPackage root = (EPackage) semanticModel;
        EPackage child = root.getESubpackages().iterator().next();

        final DDiagram rootdiagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, root).iterator().next();
        final DDiagram childdiagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, child).iterator().next();

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain(), "Remove the package from diagram") {

            @Override
            protected void doExecute() {
                rootdiagram.getOwnedDiagramElements().remove(0);
            }
        };
        executeCommand(cmd);

        deactivateLayer(rootdiagram, LAYER_PACKAGE_NAME);
        deactivateLayer(childdiagram, LAYER_PACKAGE_NAME);

        refresh(rootdiagram);
        refresh(childdiagram);

        assertEquals("Can't find the root EClass nodelist", 1, getDiagramElementsFromLabel(rootdiagram, "Root", DNodeList.class).size());
        assertEquals("Can't find the child EClass nodelist", 1, getDiagramElementsFromLabel(childdiagram, "Child", DNodeList.class).size());

        assertEquals("We should have no edge in the diagram", 0, rootdiagram.getEdges().size());
        assertEquals("We should have no edge in the diagram", 0, childdiagram.getEdges().size());

        /*
         * Now let's drop the child in the root diagram
         */

        dropSemantic(child.getEClassifier("Child"), rootdiagram, null);

        refresh(rootdiagram);

        assertEquals("Can't find the child EClass nodelist in the root diagram", 1, getDiagramElementsFromLabel(rootdiagram, "Child", DNodeList.class).size());

        assertEquals("We should have two edges in the diagram : ref from root to child and super class reference", 3, rootdiagram.getEdges().size());

        dropSemantic(root.getEClassifier("Root"), childdiagram, null);

        refresh(childdiagram);

        assertEquals("Can't find the root EClass nodelist in the child diagram", 1, getDiagramElementsFromLabel(childdiagram, "Root", DNodeList.class).size());

        assertEquals("We should have three edges in the diagram : ref from child to root  and super class reference", 3, childdiagram.getEdges().size());

    }

}
