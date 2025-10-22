/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.WithDRepresentationElementType;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Test rounded corner style for container (VP-2700).
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RoundedCornerRefreshTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/refresh/roundedCorner/";

    private static final String SEMANTIC_RESOURCE_FILENAME = "VP-2700.ecore";

    private static final String SESSION_RESOURCE_FILENAME = "VP-2700.aird";

    private static final String MODELER_RESOURCE_FILENAME = "VP-2700.odesign";

    private static final String REPRESENTATION_INSTANCE_NAME = "new VP-2700_Diagram";

    private static final String REPRESENTATION_NAME = "VP-2700_Diagram";

    private List<SWTBotGefEditPart> dNodeContainerEditPartBots;

    private Resource modelerResource;

    private List<ContainerMapping> containerMappings;

    /**
     * Initialize the {@link NodeStyleDescription} test fields. The odesign
     * reference in another ResourseSet to simulate odesign editing in the
     * odesign editor.
     */
    private void initializeContainerStyleDescriptions() {
        ResourceSet resourceSet = new ResourceSetImpl();
        Viewpoint viewpoint = localSession.getOpenedSession().getSelectedViewpoints(false).iterator().next();
        URI modelerResourceURI = viewpoint.eResource().getURI();
        modelerResource = resourceSet.getResource(modelerResourceURI, true);

        containerMappings = Lists.newArrayList(Iterators.filter(modelerResource.getAllContents(), ContainerMapping.class));
    }

    /**
     * Initialize the SWTBot fields to be used for the resize tests.
     */
    private void initializeEditPartBots() {
        SWTBotGefEditPart rootEditPartBot = editor.rootEditPart();

        dNodeContainerEditPartBots = rootEditPartBot.descendants(new WithDRepresentationElementType<DDiagramElementContainer>(DDiagramElementContainer.class));
    }

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_RESOURCE_FILENAME, SESSION_RESOURCE_FILENAME, MODELER_RESOURCE_FILENAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILENAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        initEditor();

        initializeContainerStyleDescriptions();
        initializeEditPartBots();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean getAutoRefreshMode() {
        return true;
    }

    private void initEditor() {
        if (editor != null) {

            editor.setSnapToGrid(false);

            editor.setFocus();
        }
    }

    /**
     * Test that rounded Corner defined in *.aird is correct according to the
     * modeler rounded Corner definition.
     */
    public void testRoundedCornerChanges() {

        // Checks at the diagram opening
        assertRoundedCornerEquality();

        for (ContainerMapping containerMapping : containerMappings) {
            enableRoundedCorner(true, containerMapping);
            initializeEditPartBots();

            assertRoundedCornerEquality();

            changeRoundedCorner(containerMapping, 50, 50);
            initializeEditPartBots();

            assertRoundedCornerEquality();
        }

        for (ContainerMapping containerMapping : containerMappings) {
            enableRoundedCorner(false, containerMapping);
            initializeEditPartBots();

            assertRoundedCornerEquality();

            changeRoundedCorner(containerMapping, 100, 100);
            initializeEditPartBots();

            assertRoundedCornerEquality();
        }

        for (ContainerMapping containerMapping : containerMappings) {
            enableRoundedCorner(true, containerMapping);
            initializeEditPartBots();

            assertRoundedCornerEquality();

            changeChildrenPresentationFeature(containerMapping);
            // reinit the editPart bot because changing
            // ContainerMapping.listContainer feature replace editPart
            // (DNodeContainerEditPart<->DNodeListEditPart)
            initializeEditPartBots();

            assertRoundedCornerEquality();
        }

    }

    private void changeChildrenPresentationFeature(ContainerMapping containerMapping) {
        ContainerLayout childrenPresentation = containerMapping.getChildrenPresentation();
        if (ContainerLayout.LIST == childrenPresentation) {
            containerMapping.setChildrenPresentation(ContainerLayout.FREE_FORM);
        } else {
            containerMapping.setChildrenPresentation(ContainerLayout.LIST);
        }

    }

    private void assertRoundedCornerEquality() {

        for (SWTBotGefEditPart dNodeContainerEditPartBot : dNodeContainerEditPartBots) {
            ContainerStyleDescription containerStyleDescription = getContainerStyleDescription(dNodeContainerEditPartBot);
            int expectedArcWidth = containerStyleDescription.isRoundedCorner() ? containerStyleDescription.getArcWidth().intValue() : 0;
            assertEquals("the figure width arc corner should  be equals to its ContainerStyleDescription.arcWidth : " + containerStyleDescription.eContainer(), expectedArcWidth,
                    getFigureArcWidth(dNodeContainerEditPartBot));

            int expectedArcHeight = containerStyleDescription.isRoundedCorner() ? containerStyleDescription.getArcHeight().intValue() : 0;
            assertEquals("the figure height arc corner should  be equals to its ContainerStyleDescription.arcHeight : " + containerStyleDescription.eContainer(), expectedArcHeight,
                    getFigureArcHeigth(dNodeContainerEditPartBot));
        }

    }

    /**
     * Get the rounded corner width from the figure for the specified editPart
     * bot.
     * 
     * NOTE : for other OS than win32, a width of 1 corresponds to 0 in the
     * odesign (see ViewNodeContainerRoundedRectangleFigureDesc/
     * ViewNodeListRoundedRectangleFigureDesc#paintClientArea()).
     * 
     * @param dNodeContainerEditPartBot
     *            the specified editPart bot
     * @return the rounded corner width from the figure
     */
    private int getFigureArcWidth(SWTBotGefEditPart dNodeContainerEditPartBot) {
        int arcWidth = 0;
        EditPart editPart = dNodeContainerEditPartBot.part();
        assertTrue(editPart instanceof IGraphicalEditPart);
        IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) editPart;
        IFigure figure = graphicalEditPart.getFigure();
        if (!figure.getChildren().isEmpty()) {
            IFigure firstChild = figure.getChildren().get(0);
            if (!firstChild.getChildren().isEmpty()) {
                IFigure secondChild = firstChild.getChildren().get(0);
                if (secondChild instanceof GradientRoundedRectangle) {
                    GradientRoundedRectangle roundedCornderFigure = (GradientRoundedRectangle) secondChild;
                    arcWidth = roundedCornderFigure.getCornerWidth();
                }
            }
        }
        if (!"win32".equals(SWT.getPlatform()) && arcWidth == 1) {
            arcWidth = 0;
        }
        return arcWidth;
    }

    /**
     * Get the rounded corner height from the figure for the specified editPart
     * bot.
     * 
     * NOTE : for other OS than win32, a height of 1 corresponds to 0 in the
     * odesign (see ViewNodeContainerRoundedRectangleFigureDesc/
     * ViewNodeListRoundedRectangleFigureDesc#paintClientArea()).
     * 
     * @param dNodeContainerEditPartBot
     *            the specified editPart bot
     * @return the rounded corner height from the figure
     */
    private int getFigureArcHeigth(SWTBotGefEditPart dNodeContainerEditPartBot) {
        int arcHeight = 0;
        EditPart editPart = dNodeContainerEditPartBot.part();
        assertTrue(editPart instanceof IGraphicalEditPart);
        IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) editPart;
        IFigure figure = graphicalEditPart.getFigure();
        if (!figure.getChildren().isEmpty()) {
            IFigure firstChild = figure.getChildren().get(0);
            if (!firstChild.getChildren().isEmpty()) {
                IFigure secondChild = firstChild.getChildren().get(0);
                if (secondChild instanceof GradientRoundedRectangle) {
                    GradientRoundedRectangle roundedCornerFigure = (GradientRoundedRectangle) secondChild;
                    arcHeight = roundedCornerFigure.getCornerHeight();
                }
            }
        }
        if (!"win32".equals(SWT.getPlatform()) && arcHeight == 1) {
            arcHeight = 0;
        }
        return arcHeight;
    }

    private void changeRoundedCorner(ContainerMapping containerMapping, int arcWidth, int arcHeight) {
        ContainerStyleDescription containerStyleDescription = containerMapping.getStyle();
        containerStyleDescription = getInstanceFromModeler(containerStyleDescription);
        containerStyleDescription.setArcWidth(arcWidth);
        containerStyleDescription.setArcHeight(arcHeight);
        SWTBotUtils.waitAllUiEvents();
        try {
            modelerResource.save(Collections.emptyMap());
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        } finally {
            SWTBotUtils.waitAllUiEvents();
            bot.sleep(1000);
            SWTBotUtils.waitAllUiEvents();
            SWTBotUtils.waitAllUiEvents();
            localSession.save();
            SWTBotUtils.waitAllUiEvents();
        }

    }

    private void enableRoundedCorner(boolean roundedCorner, ContainerMapping containerMapping) {
        ContainerStyleDescription containerStyleDescription = containerMapping.getStyle();
        containerStyleDescription = getInstanceFromModeler(containerStyleDescription);
        containerStyleDescription.setRoundedCorner(roundedCorner);

        ICondition done = new OperationDoneCondition();
        try {
            modelerResource.save(Collections.emptyMap());
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        } finally {
            bot.waitUntil(done);
            SWTBotUtils.waitAllUiEvents();
            bot.sleep(1000);
            SWTBotUtils.waitAllUiEvents();
            SWTBotUtils.waitAllUiEvents();
            localSession.save();
            SWTBotUtils.waitAllUiEvents();
        }
    }

    private ContainerStyleDescription getContainerStyleDescription(SWTBotGefEditPart dNodeContainerEditPartBot) {
        DDiagramElementContainer dDiagramElementContainer = getDDiagramElementContainer(dNodeContainerEditPartBot);
        Style style = dDiagramElementContainer.getStyle();
        StyleDescription styleDescription = style.getDescription();
        assertTrue(styleDescription instanceof ContainerStyleDescription);
        ContainerStyleDescription containerStyleDescription = (ContainerStyleDescription) styleDescription;
        return containerStyleDescription;
    }

    private DDiagramElementContainer getDDiagramElementContainer(SWTBotGefEditPart dNodeContainerEditPartBot) {
        EditPart editPart = dNodeContainerEditPartBot.part();
        Object model = editPart.getModel();
        assertTrue(model instanceof View);
        View view = (View) model;
        EObject element = view.getElement();
        SWTBotUtils.waitAllUiEvents();
        assertTrue(element instanceof DDiagramElementContainer);
        DDiagramElementContainer dDiagramElementContainer = (DDiagramElementContainer) element;
        return dDiagramElementContainer;
    }

    @SuppressWarnings("unchecked")
    private <T extends EObject> T getInstanceFromModeler(T modelerElement) {
        String uriFragment = modelerElement.eResource().getURIFragment(modelerElement);
        URI modelerElementURI = modelerResource.getURI().appendFragment(uriFragment);
        return (T) modelerResource.getResourceSet().getEObject(modelerElementURI, true);
    }

    @Override
    protected void tearDown() throws Exception {
        dNodeContainerEditPartBots = null;
        modelerResource = null;
        containerMappings = null;
        super.tearDown();
    }

}
