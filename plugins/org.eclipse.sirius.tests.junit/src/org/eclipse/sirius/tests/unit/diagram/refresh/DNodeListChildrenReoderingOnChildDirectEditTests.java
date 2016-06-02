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
package org.eclipse.sirius.tests.unit.diagram.refresh;

import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;

/**
 * Test that the DDiagramSynchronizer is called after a direct edit of
 * DNodeListElement to reorder the DNodeList children (DNodeListElement).
 * 
 * @author edugueperoux
 */
public class DNodeListChildrenReoderingOnChildDirectEditTests extends SiriusDiagramTestCase {

    private String PATH = "/data/unit/refresh/compartmentListEltsReorderingOnDirectEdit/";

    private String SEMANTIC_MODEL_FILENAME = "vp1753.ecore";

    private String MODELER_MODEL_FILENAME = "vp1753.odesign";

    private String SESSION_MODEL_FILENAME = "vp1753.aird";

    private Diagram diagram;

    private DRepresentation dRepresentation;

    private IEditorPart editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + MODELER_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SESSION_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);

        genericSetUp(Collections.<String> emptyList(), Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_FILENAME), TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        Resource sessionResource = session.getSessionResource();
        diagram = GMFNotationHelper.getGMFDiagrams(sessionResource).iterator().next();

        DView dView = session.getOwnedViews().iterator().next();
        dRepresentation = new DViewQuery(dView).getLoadedRepresentations().get(0);
        editor = DialectUIManager.INSTANCE.openEditor(session, dRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test that renaming the EAttribute named "a" to "z" reorder it to the end
     * of the list.
     * 
     * @throws Exception
     */
    public void testDNodeListChildrenReorderingOnDirectEditAToZ() throws Exception {

        DDiagram dDiagram = (DDiagram) dRepresentation;
        DNodeList dNodeList = (DNodeList) dDiagram.getOwnedDiagramElements().get(0);
        DDiagramElement dNodeListElementNamedA = getDiagramElementsFromLabel(dDiagram, "a").get(0);
        DDiagramElement dNodeListElementNamedC = getDiagramElementsFromLabel(dDiagram, "c").get(0);
        DDiagramElement dNodeListElementNamedY = getDiagramElementsFromLabel(dDiagram, "y").get(0);

        String newName = "z";
        applyDirectEditTool("Edit EAttribute Name", dDiagram, dNodeListElementNamedA, newName);
        // TestsUtil.synchronizationWithUIThread();

        Assert.assertEquals(newName, dNodeListElementNamedA.getName());
        Assert.assertEquals(3, dNodeList.getOwnedElements().size());
        Assert.assertEquals(1, diagram.getChildren().size());
        View eClassView = (View) diagram.getChildren().get(0);
        View compartmentView = (View) eClassView.getChildren().get(1);
        Assert.assertEquals(3, compartmentView.getChildren().size());

        Assert.assertEquals(0, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedC));
        Assert.assertEquals(1, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedY));
        Assert.assertEquals(2, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedA));

        Assert.assertEquals(dNodeListElementNamedC, ((View) compartmentView.getChildren().get(0)).getElement());
        Assert.assertEquals(dNodeListElementNamedY, ((View) compartmentView.getChildren().get(1)).getElement());
        Assert.assertEquals(dNodeListElementNamedA, ((View) compartmentView.getChildren().get(2)).getElement());

        // Undo
        undo();
        // TestsUtil.synchronizationWithUIThread();

        Assert.assertEquals(0, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedA));
        Assert.assertEquals(1, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedC));
        Assert.assertEquals(2, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedY));

        Assert.assertEquals(dNodeListElementNamedA, ((View) compartmentView.getChildren().get(0)).getElement());
        Assert.assertEquals(dNodeListElementNamedC, ((View) compartmentView.getChildren().get(1)).getElement());
        Assert.assertEquals(dNodeListElementNamedY, ((View) compartmentView.getChildren().get(2)).getElement());

        // Redo
        redo();
        // TestsUtil.synchronizationWithUIThread();

        Assert.assertEquals(0, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedC));
        Assert.assertEquals(1, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedY));
        Assert.assertEquals(2, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedA));

        Assert.assertEquals(dNodeListElementNamedC, ((View) compartmentView.getChildren().get(0)).getElement());
        Assert.assertEquals(dNodeListElementNamedY, ((View) compartmentView.getChildren().get(1)).getElement());
        Assert.assertEquals(dNodeListElementNamedA, ((View) compartmentView.getChildren().get(2)).getElement());

    }

    /**
     * Test that renaming the EAttribute named "y" to "b" reorder it to the end
     * of the list.
     * 
     * @throws Exception
     */
    public void testDNodeListChildrenReorderingOnDirectEditYToB() throws Exception {

        DDiagram dDiagram = (DDiagram) dRepresentation;
        DNodeList dNodeList = (DNodeList) dDiagram.getOwnedDiagramElements().get(0);
        DDiagramElement dNodeListElementNamedA = getDiagramElementsFromLabel(dDiagram, "a").get(0);
        DDiagramElement dNodeListElementNamedC = getDiagramElementsFromLabel(dDiagram, "c").get(0);
        DDiagramElement dNodeListElementNamedY = getDiagramElementsFromLabel(dDiagram, "y").get(0);

        String newName = "b";
        applyDirectEditTool("Edit EAttribute Name", dDiagram, dNodeListElementNamedY, newName);
        // TestsUtil.synchronizationWithUIThread();
        // TestsUtil.emptyEventsFromUIThread();

        Assert.assertEquals(newName, dNodeListElementNamedY.getName());
        Assert.assertEquals(3, dNodeList.getOwnedElements().size());
        Assert.assertEquals(1, diagram.getChildren().size());
        View eClassView = (View) diagram.getChildren().get(0);
        View compartmentView = (View) eClassView.getChildren().get(1);
        Assert.assertEquals(3, compartmentView.getChildren().size());

        Assert.assertEquals(0, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedA));
        Assert.assertEquals(1, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedY));
        Assert.assertEquals(2, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedC));

        Assert.assertEquals(dNodeListElementNamedA, ((View) compartmentView.getChildren().get(0)).getElement());
        Assert.assertEquals(dNodeListElementNamedY, ((View) compartmentView.getChildren().get(1)).getElement());
        Assert.assertEquals(dNodeListElementNamedC, ((View) compartmentView.getChildren().get(2)).getElement());

        // Undo
        undo();
        TestsUtil.synchronizationWithUIThread();

        Assert.assertEquals(0, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedA));
        Assert.assertEquals(1, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedC));
        Assert.assertEquals(2, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedY));

        Assert.assertEquals(dNodeListElementNamedA, ((View) compartmentView.getChildren().get(0)).getElement());
        Assert.assertEquals(dNodeListElementNamedC, ((View) compartmentView.getChildren().get(1)).getElement());
        Assert.assertEquals(dNodeListElementNamedY, ((View) compartmentView.getChildren().get(2)).getElement());

        // Redo
        redo();
        TestsUtil.synchronizationWithUIThread();

        Assert.assertEquals(0, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedA));
        Assert.assertEquals(1, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedY));
        Assert.assertEquals(2, dNodeList.getOwnedElements().indexOf(dNodeListElementNamedC));

        Assert.assertEquals(dNodeListElementNamedA, ((View) compartmentView.getChildren().get(0)).getElement());
        Assert.assertEquals(dNodeListElementNamedY, ((View) compartmentView.getChildren().get(1)).getElement());
        Assert.assertEquals(dNodeListElementNamedC, ((View) compartmentView.getChildren().get(2)).getElement());

    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        editor = null;
        for (Viewpoint viewpoint : viewpoints) {
            ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
        }
        viewpoints.clear();
        diagram = null;
        super.tearDown();
    }

}
