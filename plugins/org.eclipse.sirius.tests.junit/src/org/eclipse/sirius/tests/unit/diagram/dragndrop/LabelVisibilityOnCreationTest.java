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
package org.eclipse.sirius.tests.unit.diagram.dragndrop;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Tests that the label visibility on creation.
 * 
 * See VP-4044.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class LabelVisibilityOnCreationTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/dragndrop/VP-3894/";

    private static final String MODELER_RESOURCE_NAME = "VP-3894.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3894.ecore";

    private static final String SESSION_RESOURCE_NAME = "VP-3894.aird";

    private DDiagram dDiagram;

    private DDiagramEditor dDiagramEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, MODELER_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME);
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME), Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME),
                TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
        Iterator<DRepresentation> iterator = allRepresentations.iterator();
        iterator.next();
        iterator.next();
        dDiagram = (DDiagram) iterator.next();
    }

    /**
     * Test that that creation of a DNode with a borderedNode creation the
     * borderedNode without label because the mapping of the borderedNode has
     * HideLabelByDefault at true.
     */
    public void testDNodeCreationWithBorderedNodeWithHideLabelByDefault() {
        dDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        int dDiagramEltsNb = dDiagram.getOwnedDiagramElements().size();
        boolean toolExecuted = applyNodeCreationTool("EClassWithEAttribute", dDiagram, dDiagram);
        assertTrue("The creation tool has been executed", toolExecuted);
        assertEquals("A new DDiagramElement must be created", dDiagramEltsNb + 1, dDiagram.getOwnedDiagramElements().size());
        DDiagramElement createdDDiagramElement = dDiagram.getDiagramElements().get(dDiagramEltsNb);
        assertTrue(createdDDiagramElement instanceof DNode);
        DNode createdDNode = (DNode) createdDDiagramElement;
        assertTrue("The label of the created DNode must be hidden because its HideLabelByDefault attribute in the odesign is at true", new DDiagramElementQuery(createdDNode).isLabelHidden());
        assertEquals("The created DNode must have a borderedNode created", 1, createdDNode.getOwnedBorderedNodes().size());
        DNode createdBorderedNode = createdDNode.getOwnedBorderedNodes().get(0);
        assertTrue("The label of the borderedNode must be hidden because its HideLabelByDefault attribute in the odesign is at true", new DDiagramElementQuery(createdBorderedNode).isLabelHidden());
    }

    @Override
    protected void tearDown() throws Exception {
        dDiagram = null;
        DialectUIManager.INSTANCE.closeEditor(dDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        dDiagramEditor = null;
        super.tearDown();
    }
}
