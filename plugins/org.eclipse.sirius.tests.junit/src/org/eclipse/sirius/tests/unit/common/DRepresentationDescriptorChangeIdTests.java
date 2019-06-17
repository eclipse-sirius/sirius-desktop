/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.api.command.view.CreateDDiagramElementCommand;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.common.command.EPackageEClassifiersAppenderRecordingCommand;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.ui.IEditorPart;


/**
 * This class tests that changeId on {@link DRepresentationDescriptor} is updated correctly when needed.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DRepresentationDescriptorChangeIdTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
    }

    /**
     * Tests that when a new {@link DRepresentationElement} is created, then the change id of the associated
     * {@link DRepresentationDescriptor} is updated.
     */
    public void testDRepresentationElementUpdate() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final EPackage ePackage = (EPackage) semanticModel;
        final EClass eClass = createEClass(ePackage);

        final Layer layer = diagram.getDescription().getDefaultLayer();
        ContainerMapping classMapping = getContainerMapping(layer, "EC EClass");

        String changeIdBeforeModification = new DRepresentationQuery(diagram).getRepresentationDescriptor().getChangeId();
        RecordingCommand cmd = new CreateDDiagramElementCommand(session.getTransactionalEditingDomain(), eClass, classMapping, diagram);
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);

        String changeIdAfterModification = new DRepresentationQuery(diagram).getRepresentationDescriptor().getChangeId();

        assertNotEquals("Change id has not been updated.", changeIdBeforeModification, changeIdAfterModification);

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests that when a GMF element like the {@link Bounds} change, then the change id of the associated
     * {@link DRepresentationDescriptor} is updated.
     */
    public void testGMFElementUpdate() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final EPackage ePackage = (EPackage) semanticModel;
        final EClass eClass = createEClass(ePackage);

        final Layer layer = diagram.getDescription().getDefaultLayer();
        ContainerMapping classMapping = getContainerMapping(layer, "EC EClass");


        RecordingCommand cmd = new CreateDDiagramElementCommand(session.getTransactionalEditingDomain(), eClass, classMapping, diagram);
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);

        String changeIdBeforeModification = new DRepresentationQuery(diagram).getRepresentationDescriptor().getChangeId();
        changeLayoutConstraint();
        String changeIdAfterModification = new DRepresentationQuery(diagram).getRepresentationDescriptor().getChangeId();

        assertNotEquals("Change id has not been updated.", changeIdBeforeModification, changeIdAfterModification);

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    private void changeLayoutConstraint() {
        EList<AnnotationEntry> ownedAnnotationEntries = diagram.getOwnedAnnotationEntries();
        for (AnnotationEntry annotationEntry : ownedAnnotationEntries) {
            EObject data = annotationEntry.getData();
            if (data instanceof Diagram) {
                Diagram diagram = (Diagram) data;
                List<?> children = diagram.getChildren();
                for (Object object : children) {
                    if (object instanceof Node) {
                        Node node = (Node) object;
                        Bounds bounds = NotationFactory.eINSTANCE.createBounds();
                        bounds.setX(2);
                        session.getTransactionalEditingDomain().getCommandStack()
                                .execute(new SetCommand(session.getTransactionalEditingDomain(), node, NotationPackage.eINSTANCE.getNode_LayoutConstraint(), bounds));
                    }
                }
            }
        }
    }

    /**
     * Tests that when a new {@link DRepresentation} is modified, then the change id of the associated
     * {@link DRepresentationDescriptor} is updated.
     */
    public void testDRepresentationUpdate() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();


        String changeIdBeforeModification = new DRepresentationQuery(diagram).getRepresentationDescriptor().getChangeId();

        session.getTransactionalEditingDomain().getCommandStack()
                .execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

                    @Override
                    protected void doExecute() {
                        diagram.getDiagramElements().remove(0);
                    }
                });

        String changeIdAfterModification = new DRepresentationQuery(diagram).getRepresentationDescriptor().getChangeId();

        assertNotEquals("Change id has not been updated.", changeIdBeforeModification, changeIdAfterModification);

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests that when a new {@link DRepresentationDescriptor} is modified, then its change id is not modified.
     */
    public void testDRepresentationDescriptorNoChange() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        String changeIdBeforeModification = new DRepresentationQuery(diagram).getRepresentationDescriptor().getChangeId();

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                new DRepresentationQuery(diagram).getRepresentationDescriptor().setName("new Name");
            }
        });

        String changeIdAfterModification = new DRepresentationQuery(diagram).getRepresentationDescriptor().getChangeId();

        assertEquals("Change id should not have been changed", changeIdBeforeModification, changeIdAfterModification);

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests that when a transient feature related to a representation is modified, then its change id is not modified.
     */
    public void testTransientFeatureNoChange() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        String changeIdBeforeModification = new DRepresentationQuery(diagram).getRepresentationDescriptor().getChangeId();

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                diagram.getUiState().getToolSections().add(ViewpointFactory.eINSTANCE.createToolSectionInstance());
            }
        });

        String changeIdAfterModification = new DRepresentationQuery(diagram).getRepresentationDescriptor().getChangeId();

        assertEquals("Change id should not have been changed", changeIdBeforeModification, changeIdAfterModification);

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
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
