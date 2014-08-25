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
package org.eclipse.sirius.tests.swtbot;

import java.util.Collection;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import com.google.common.collect.Lists;

/**
 * A test case to check that attempting a reconnect with a invalid reconnect
 * edge tool does not throws exception. But a error entry in the error log
 * informing the specifier that the reconnect tool is incorrect.
 * 
 * See VP-3744.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class InvalidReconnectEdgeToolTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "/data/unit/reconnect/VP-3744/";

    private static final String METAMODEL_RESOURCE_NAME = "VP-3744.ecore";

    private static final String MODELER_RESOURCE_NAME = "VP-3744.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3744.xmi";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "VP-3744.aird";

    private static final String VIEWPOINT_NAME = "VP-3744_Viewpoint";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "VP-3744_Diagram";

    private static final String REPRESENTATION_NAME = "new " + REPRESENTATION_DESCRIPTION_NAME;

    private UIDiagramRepresentation diagram;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, METAMODEL_RESOURCE_NAME, MODELER_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        sessionAirdResource = new UIResource(designerProject, "/", REPRESENTATIONS_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_DESCRIPTION_NAME)
                .selectRepresentationInstance(REPRESENTATION_NAME, UIDiagramRepresentation.class).open();

        editor = diagram.getEditor();
    }

    /**
     * A test case to check that attempting a reconnect with a invalid reconnect
     * edge tool does not throws exception. But a error entry in the error log
     * informing the specifier that the reconnect tool is incorrect.
     * 
     * See VP-3744.
     */
    public void testInvalidReconnectEdgeToolTest() {
        final Collection<IStatus> warnings = Lists.newArrayList();
        ILogListener warningDetector = new ILogListener() {

            public void logging(IStatus status, String plugin) {
                if (status.getSeverity() == IStatus.WARNING) {
                    warnings.add(status);
                }
            }
        };
        Platform.addLogListener(warningDetector);

        SWTBotGefEditPart borderedNode1EditPartBot = editor.getEditPart("borderedNode1", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart borderedNode2EditPartBot = editor.getEditPart("borderedNode2", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefConnectionEditPart link1ConnectionEditPartBot = borderedNode1EditPartBot.sourceConnections().get(0);
        ViewEdgeFigure viewEdgeFigure = (ViewEdgeFigure) link1ConnectionEditPartBot.part().getFigure();
        Point from = viewEdgeFigure.getPoints().getPoint(0);
        Point to = editor.getBounds(borderedNode2EditPartBot).getCenter();
        link1ConnectionEditPartBot.select();
        editor.drag(from, to);
        SWTBotUtils.waitAllUiEvents();

        Platform.removeLogListener(warningDetector);
        assertFalse("Reconnect attempt with a invalid reconnect tool should not throws exception", doesAnErrorOccurs());
        assertTrue("Reconnect attempt with a invalid reconnect tool should log one warning", warnings.size() == 1);

        IStatus detectedWarning = warnings.iterator().next();
        assertEquals(IStatus.WARNING, detectedWarning.getSeverity());
        assertEquals("The expected warning should come from org.eclipse.sirius.diagram", "org.eclipse.sirius.diagram", detectedWarning.getPlugin());
        assertEquals("The semantic model was not correctly updated by the reconnect tool, the diagram part of the reconnect cannot be done", detectedWarning.getMessage());
    }

    @Override
    protected void tearDown() throws Exception {
        editor.close();
        diagram = null;
        super.tearDown();
    }

}
