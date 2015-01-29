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
package org.eclipse.sirius.tests.unit.diagram.tools;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.collect.Iterables;

/**
 * Deletion tests in the case of a selection containing a Part and its own
 * label.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class PartAndLabelPartSelectionDeletionTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/tools/VP-4397/";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-4397.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "VP-4397.aird";

    private static final String MODELER_RESOURCE_NAME = "VP-4397.odesign";

    private DDiagram dDiagram;

    private DDiagramEditor openedDDiagramEditor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME, MODELER_RESOURCE_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);

        dDiagram = (DDiagram) getRepresentations("Entities_VP-4397", semanticModel).iterator().next();
        openedDDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
    }

    /**
     * Deletion of a selection of a DNodeEditPart and its DNodeNameEditPart with
     * a delete tool containing an UnsetTask.
     */
    public void testDeleteSelectionOfNodeAndItsLabel() {
        openedDDiagramEditor.setFocus();
        DDiagramElement dDiagramElement = dDiagram.getOwnedDiagramElements().get(0);
        IGraphicalEditPart editPart = getEditPart(dDiagramElement);
        DNodeNameEditPart nameEditPart = Iterables.getOnlyElement(Iterables.filter(editPart.getChildren(), DNodeNameEditPart.class));
        delete(editPart, nameEditPart);
        TestsUtil.synchronizationWithUIThread();

    }
}
