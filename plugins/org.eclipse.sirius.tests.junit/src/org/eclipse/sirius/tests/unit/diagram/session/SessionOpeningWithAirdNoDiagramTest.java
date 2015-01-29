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
package org.eclipse.sirius.tests.unit.diagram.session;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.jdt.ui.actions.NavigateActionGroup;
import org.eclipse.jface.action.IAction;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.google.common.collect.Iterables;

/**
 * Validation tests.
 * 
 * @author smonnier
 */
public class SessionOpeningWithAirdNoDiagramTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/session/noDiagram_noSirius/tc587.ecore";

    private static final String SESSION_PATH_NO_DIAGRAM = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/session/noDiagram_noSirius/tc587_no_diagram.aird";

    private static final String SESSION_PATH_NO_VIEWPOINT = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/session/noDiagram_noSirius/tc587_no_viewpoint.aird";

    private static final String SEMANTIC_MODEL_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + "tc587.ecore";

    private static final String SESSION_WORKSPACE_PATH_NO_DIAGRAM = TEMPORARY_PROJECT_NAME + "/" + "tc587_no_diagram.aird";

    private static final String MODELER_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";

    private static final String MODELER_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + "ecore.odesign";

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testOpenAirdNoDiagram() throws Exception {
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH_NO_DIAGRAM);
        final Iterable<DDiagram> allDiagrams = Iterables.filter(DialectManager.INSTANCE.getAllRepresentations(session), DDiagram.class);

        assertTrue("There must be at least one diagram", allDiagrams.iterator().hasNext());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testOpenAirdNoSirius() throws Exception {
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH_NO_VIEWPOINT);
    }

    /**
     * Test opening session in package explorer view. Test that the double click
     * action not provide error. Test VP-2512
     * 
     * @throws Exception
     *             Test error.
     */
    @SuppressWarnings("restriction")
    public void testOpeningSessionInPackageExplorerView() throws Exception {
        super.setUp();
        // Copy file to runtime workspace
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, "/data/unit/session/noDiagram_noSirius/ecore.odesign", MODELER_WORKSPACE_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, "/data/unit/session/noDiagram_noSirius/tc587.ecore", SEMANTIC_MODEL_WORKSPACE_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, "/data/unit/session/noDiagram_noSirius/tc587_no_diagram.aird", SESSION_WORKSPACE_PATH_NO_DIAGRAM);

        genericSetUp(SEMANTIC_MODEL_WORKSPACE_PATH, MODELER_WORKSPACE_PATH, SESSION_WORKSPACE_PATH_NO_DIAGRAM);

        // Retrieve package explorer view
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IViewPart part = (ViewPart) page.getActivePart();
        if (part instanceof PackageExplorerPart) {
            ((PackageExplorerPart) part).selectAndReveal(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(SESSION_WORKSPACE_PATH_NO_DIAGRAM)));
        }

        // Double click on the aird file.
        NavigateActionGroup fNavigateActionGroup = new NavigateActionGroup(part);
        IAction openAction = fNavigateActionGroup.getOpenAction();
        if (openAction != null && openAction.isEnabled()) {
            openAction.run();
        } else {
            fail("The file can not be opened");
        }
    }
}
