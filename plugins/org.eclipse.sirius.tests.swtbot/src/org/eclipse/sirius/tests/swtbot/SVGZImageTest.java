/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test that svgz image (compressed svg image) are loaded without problems.
 * 
 * @author <a href="mailto:jessy.mallet@obeo.fr">Jessy Mallet</a>
 */
public class SVGZImageTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VSM_FILE = "Bug431075.odesign";

    private static final String MODEL = "Bug431075.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String FILE_DIR = "/";

    private static final String DATA_UNIT_DIR = "data/unit/swtImageBundle/bug431075/";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Diagram";

    private static final String REPRESENTATION_NAME = "new Diagram";

    private static final String IMAGE_FILE = "tiger.svgz";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE, IMAGE_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Test that svgz image is correctly displayed.
     */
    public void testSvgzImageLoad() {
        // Open the diagram
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);
        SWTBotGefEditPart editPartBot = editor.getEditPart("C1").parent();
        EditPart editPart = editPartBot.part();
        IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) editPart.getChildren().get(1);
        IFigure figure = (IFigure) graphicalEditPart.getFigure().getChildren().get(0);
        SVGWorkspaceImageFigure svgWorkspaceImageFigure = (SVGWorkspaceImageFigure) figure;
        String uri = svgWorkspaceImageFigure.getURI();
        WorkspaceImage workspaceImage = getWorkspaceImage(editPartBot);
        String imageFile = FileProvider.getDefault().getFile(new Path(workspaceImage.getWorkspacePath())).toURI().toString();
        assertEquals("The svg image doesn't corresponds to " + workspaceImage.getWorkspacePath(), imageFile, uri);
    }

    private WorkspaceImage getWorkspaceImage(SWTBotGefEditPart editPartBot) {
        WorkspaceImage workspaceImage = null;
        EditPart editPart = editPartBot.part();
        if (editPart instanceof IDiagramElementEditPart) {
            IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) editPart;
            DDiagramElement dDiagramElement = diagramElementEditPart.resolveDiagramElement();
            Style style = dDiagramElement.getStyle();
            if (style instanceof WorkspaceImage) {
                workspaceImage = (WorkspaceImage) style;
            }
        }
        return workspaceImage;
    }
}
