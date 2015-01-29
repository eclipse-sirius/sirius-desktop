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
package org.eclipse.sirius.tests.unit.api.command;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.tools.api.command.view.CreateDDiagramElementCommand;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.common.command.EClassEStructuralFeaturesAppenderRecordingCommand;
import org.eclipse.sirius.tests.unit.common.command.EClassSuperTypesAppenderRecordingCommand;
import org.eclipse.sirius.tests.unit.common.command.EPackageEClassifiersAppenderRecordingCommand;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Tests creation command based on entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class CreateDiagramElementCommandTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
    }

    public void testCreateContainer() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(diagram.getOwnedDiagramElements().isEmpty());

        final EPackage ePackage = (EPackage) semanticModel;
        final EClass eClass = createEClass(ePackage);

        final Layer layer = diagram.getDescription().getDefaultLayer();
        ContainerMapping classMapping = getContainerMapping(layer, "EC EClass");
        RecordingCommand cmd = new CreateDDiagramElementCommand(session.getTransactionalEditingDomain(), eClass, classMapping, diagram);
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);

        assertFalse(diagram.getOwnedDiagramElements().isEmpty());

        final DDiagramElementContainer container = (DDiagramElementContainer) diagram.getOwnedDiagramElements().get(0);
        assertSame(eClass, container.getTarget());
        assertSame(classMapping, container.getMapping());
        assertTrue(container.isVisible());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testCreateNode() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(diagram.getOwnedDiagramElements().isEmpty());

        final EPackage ePackage = (EPackage) semanticModel;
        final EClass eClass = createEClass(ePackage);

        final Layer layer = diagram.getDescription().getDefaultLayer();

        refresh(diagram);

        final DDiagramElementContainer container = (DDiagramElementContainer) getFirstDiagramElement(diagram, eClass);
        NodeMapping nodeMapping = getNodeMapping(layer, "EC EAttribute");

        assertNotNull(container);

        final EAttribute eAttribute = createEAttribute(eClass);
        RecordingCommand cmd = new CreateDDiagramElementCommand(session.getTransactionalEditingDomain(), eAttribute, nodeMapping, container);
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);

        assertFalse(((DNodeList) container).getOwnedElements().isEmpty());
        final DNodeListElement node = ((DNodeList) container).getOwnedElements().get(0);
        assertSame(eAttribute, node.getTarget());
        assertSame(nodeMapping, node.getMapping());
        assertTrue(node.isVisible());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testCreateEdge() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(diagram.getOwnedDiagramElements().isEmpty());

        final EPackage ePackage = (EPackage) semanticModel;
        final EClass eClass = createEClass(ePackage);
        final EClass eClass2 = createEClass(ePackage);

        refresh(diagram);

        final EdgeTarget dClass = (EdgeTarget) getFirstDiagramElement(diagram, eClass);
        final EdgeTarget dClass2 = (EdgeTarget) getFirstDiagramElement(diagram, eClass2);

        session.getTransactionalEditingDomain().getCommandStack().execute(new EClassSuperTypesAppenderRecordingCommand(session.getTransactionalEditingDomain(), eClass, eClass2));

        final Layer layer = diagram.getDescription().getDefaultLayer();
        EdgeMapping edgeMapping = getEdgeMapping(layer, "EC ESupertypes");
        RecordingCommand cmd = new CreateDDiagramElementCommand(session.getTransactionalEditingDomain(), eClass, edgeMapping, dClass, dClass2);
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);

        final DEdge edge = getFirstEdgeElement(diagram, eClass);
        assertSame(edgeMapping, edge.getMapping());
        assertTrue(edge.isVisible());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    private EAttribute createEAttribute(final EClass eClass) {
        final EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        session.getTransactionalEditingDomain().getCommandStack().execute(new EClassEStructuralFeaturesAppenderRecordingCommand(session.getTransactionalEditingDomain(), eClass, eAttribute));
        return eAttribute;
    }

    private EClass createEClass(final EPackage ePackage) {
        final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        session.getTransactionalEditingDomain().getCommandStack().execute(new EPackageEClassifiersAppenderRecordingCommand(session.getTransactionalEditingDomain(), ePackage, eClass));
        return eClass;
    }

    @Override
    protected void tearDown() throws Exception {

        diagram = null;

        super.tearDown();
    }

}
