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

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramInternalQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Tests the style customization.
 * 
 * @author mporhel
 */
public class WorkspaceImageTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "tc2262_2.ecore";

    private static final String DESIGN_FILE = "tc2262_2.odesign";

    private static final String SESSION_FILE = "tc2262_2.aird";

    private static final String DATA_UNIT_DIR = "data/unit/style/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME = "new tc2262";

    private static final String REPRESENTATION_NAME = "tc2262";

    /**
     * Current editor.
     */
    protected SWTBotSiriusDiagramEditor editor;

    protected DDiagram dDiagram;

    protected DDiagramInternalQuery query;

    private Dimension realImageSize = new Dimension(20, 70);

    private double imageRatio = (double) realImageSize.width / realImageSize.height;

    private int defaultWidth = 30;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, DESIGN_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        IDDiagramEditPart part = (IDDiagramEditPart) editor.mainEditPart().part();
        dDiagram = (DDiagram) ((Diagram) part.getModel()).getElement();
        query = new DDiagramInternalQuery(dDiagram);
    }

    /**
     * Close error log.
     * 
     * @throws Exception
     *             any exception
     */
    protected void closeErrorLogView() throws Exception {
        bot.viewByTitle("Error Log").close();
    }

    /**
     * Check nodes created with "-1" as size computation expression.
     * 
     * @throws Exception
     */
    public void testWorkspaceImageRealSize() throws Exception {
        validateSize(EcorePackage.eINSTANCE.getEAttribute(), realImageSize);
        validateSize(EcorePackage.eINSTANCE.getEOperation(), new Dimension(defaultWidth, new Double(defaultWidth / imageRatio).intValue()));
        validateSize(EcorePackage.eINSTANCE.getEReference(), realImageSize);
    }

    private void validateSize(final EClass type, Dimension expectedSize) {

        Predicate<DNode> expectedType = new Predicate<DNode>() {
            public boolean apply(DNode input) {
                return type.isInstance(input.getTarget());
            }
        };

        List<DNode> nodesFromType = Lists.newArrayList(Iterables.filter(query.getNodes(), expectedType));

        // one nodes per node edit part type.
        assertEquals(4, nodesFromType.size());

        for (DNode node : nodesFromType) {

            Integer expectedDWidth;
            Integer expectedDHeight;
            if (expectedSize == realImageSize) {
                expectedDWidth = -1;
                expectedDHeight = -1;
            } else {
                expectedDWidth = expectedSize.width / LayoutUtils.SCALE;
                expectedDHeight = expectedDWidth;
            }

            assertEquals(expectedDWidth, node.getWidth());
            assertEquals(expectedDHeight, node.getHeight());
            SWTBotGefEditPart swtBotEditPart = editor.getEditPart(node.getName(), IAbstractDiagramNodeEditPart.class);
            IGraphicalEditPart editPart = (IGraphicalEditPart) swtBotEditPart.part();
            Node gmfNode = (Node) editPart.getNotationView();
            assertNotNull(gmfNode);
            Size layoutConstraint = (Size) gmfNode.getLayoutConstraint();
            assertEquals(expectedSize.width, layoutConstraint.getWidth());
            assertEquals(expectedSize.height, layoutConstraint.getHeight());

            WorkspaceImageFigure figure = getWorkspaceImageFigure(editPart.getFigure());
            assertNotNull(figure);
            assertEquals("Figure bounds: " + figure.getBounds() + ", GMF bounds: " + gmfNode.getLayoutConstraint(), imageRatio, figure.getImageAspectRatio());
            assertEquals(expectedSize, figure.getBounds().getSize());
        }
    }

    /**
     * Finds the SquareFigure children that contains the Color informations.
     * 
     * @param figure
     * @return
     */
    private WorkspaceImageFigure getWorkspaceImageFigure(IFigure figure) {
        while (!(figure instanceof WorkspaceImageFigure) && figure.getChildren() != null && !figure.getChildren().isEmpty()) {
            if (!(Iterables.isEmpty(Iterables.filter(figure.getChildren(), WorkspaceImageFigure.class)))) {
                return Iterables.get((Iterables.filter(figure.getChildren(), WorkspaceImageFigure.class)), 0);
            }
            for (Object object : figure.getChildren()) {
                if (object instanceof IFigure) {
                    IFigure childFigure = (IFigure) object;
                    WorkspaceImageFigure wkpImgFig = getWorkspaceImageFigure(childFigure);
                    if (wkpImgFig != null) {
                        return wkpImgFig;
                    }
                }
            }
        }
        return null;
    }
}
