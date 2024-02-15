/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.tools;

import java.util.Arrays;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;

/**
 * Tests that GMF bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=333856 is correctly fixed in the version of GMF we
 * use (forked or not). See VP-1500. The test uses models from the related VP-1688.
 * 
 * @author pcdavid
 */
public class GMFbugTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/vp-1688/vp-1688.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/vp-1688/vp-1688.odesign";

    private static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/vp-1688/vp-1688.aird";

    private static final String VIEWPOINT_NAME = "VP-1688";

    private static final String REPRESENTATION_DESC_NAME = "VP-1688";

    private DDiagram diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
        initViewpoint(VIEWPOINT_NAME);

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
    }

    /**
     * Test for VP-1500.
     */
    public void testNoErrorWhenGroupingClassesIntoPackage() {
        DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals(0, ((EPackage) semanticModel).getESubpackages().size());

        EClassifier classB = ((EPackage) semanticModel).getEClassifier("B");
        assertNotNull(classB);
        DDiagramElement bDiagramElement = getFirstNodeElement(diagram, classB);
        assertNotNull(bDiagramElement);

        EClassifier classC = ((EPackage) semanticModel).getEClassifier("C");
        assertNotNull(classC);
        DDiagramElement cDiagramElement = getFirstNodeElement(diagram, classC);
        assertNotNull(cDiagramElement);

        AbstractToolDescription tool = getTool(diagram, "Group Classes");
        assertTrue(tool instanceof OperationAction);
        Command cmd = getCommand(diagram, tool, Arrays.asList((EObject) bDiagramElement, (EObject) cDiagramElement));
        platformProblemsListener.setErrorCatchActive(true);
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
        refresh(diagram);
        assertFalse("The 'Group Classes' action triggered an error.", platformProblemsListener.doesAnErrorOccurs());
        assertEquals(1, ((EPackage) semanticModel).getESubpackages().size());
        EPackage newPkg = ((EPackage) semanticModel).getESubpackages().get(0);
        assertNotNull(newPkg);
        assertEquals("newPackage", newPkg.getName());
        assertEquals(2, newPkg.getEClassifiers().size());
        assertSame(classB, newPkg.getEClassifier("B"));
        assertSame(classC, newPkg.getEClassifier("C"));
    }

    /**
     * Test for VP-1688.
     * 
     * FIXME: The test does not fail even with the fix for VP-1688 disabled. It works as expected when doing the same
     * operations manually: fails without the fix, passes with the fix.
     * 
     * @throws Exception
     *             in case of error
     */
    public void testNotErrorOnUndoRedoOfGroupingClasses() throws Exception {
        testNoErrorWhenGroupingClassesIntoPackage();
        platformProblemsListener.setErrorCatchActive(true);
        assertTrue(session.getTransactionalEditingDomain().getCommandStack().canUndo());
        session.getTransactionalEditingDomain().getCommandStack().undo();
        assertTrue(undo());
        assertTrue(undo());
        assertNotNull(((EPackage) semanticModel).getEClassifier("B"));
        assertNotNull(((EPackage) semanticModel).getEClassifier("C"));
        assertEquals(0, ((EPackage) semanticModel).getESubpackages().size());
        assertFalse(platformProblemsListener.doesAnErrorOccurs());
        assertTrue(session.getTransactionalEditingDomain().getCommandStack().canRedo());
        session.getTransactionalEditingDomain().getCommandStack().redo();
        refresh(diagram);
        assertNull(((EPackage) semanticModel).getEClassifier("B"));
        assertNull(((EPackage) semanticModel).getEClassifier("C"));
        assertEquals(1, ((EPackage) semanticModel).getESubpackages().size());
        assertFalse(platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }

}
