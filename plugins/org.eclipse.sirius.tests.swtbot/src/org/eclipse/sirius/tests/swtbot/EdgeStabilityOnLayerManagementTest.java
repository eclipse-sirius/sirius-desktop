/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartIsNotDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusOutlineView;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.junit.Assert;

/**
 * Validate edge stability with layer management (activation, deactivation).
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class EdgeStabilityOnLayerManagementTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME_WITH_OPTIONAL_LAYER = "new doremi-2678 with optional layer";

    private static final String REPRESENTATION_INSTANCE_NAME_WITHOUT_OPTIONAL_LAYER = "new doremi-2678 without optional layer";

    private static final String REPRESENTATION_NAME = "doremi-2678";

    private static final String VSM_FILE = "doremi-2678.odesign";

    private static final String MODEL = "doremi-2678.ecore";

    private static final String SESSION_FILE = "doremi-2678.aird";

    private static final String DATA_UNIT_DIR = "data/unit/edgeStabilityOnLayerManagement/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * This test validates that deactivating an optional layer properly
     * refreshes edge on edge.
     * 
     * We have two edge mappings : <li>
     * <ul>
     * - an edge mapping with source and target as EClass and AbstractEClass( on
     * the optional layer)
     * </ul>
     * <ul>
     * - an edge mapping between EAnnotation and the previous edge mapping
     * </ul>
     * 
     * This test validate that on optional layer deactivation, the edge between
     * EClasses are still visible and the edge between EAnnotations and the
     * previous edges are also visible.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeStabilityOnLayerDeactivation() throws Exception {
        // Open representation with optional layer activated
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME_WITH_OPTIONAL_LAYER, DDiagram.class);

        // Validate before optional layer deactivation
        validateEditPartsVisibilityWithOptionalLayer();

        // Deactivate Abstract EClass layer
        final SiriusOutlineView outlineView = designerViews.openOutlineView();
        outlineView.layers();
        outlineView.layers().activateLayer("Abstract EClass");
        SWTBotUtils.waitAllUiEvents();

        // Validate after optional layer deactivation
        validateEditPartsVisibilityWithoutOptionalLayer();

        localSession.save();
        localSession.closeNoDirty();
    }

    /**
     * This test validates that on optional layer activation, all the edges
     * hidden in the previous test (testEdgeStabilityOnLayerDeactivation) are
     * visible again.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeStabilityOnLayerActivation() throws Exception {
        // Open representation with optional layer deactivated
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME_WITHOUT_OPTIONAL_LAYER, DDiagram.class);

        // Validate before optional layer activation
        validateEditPartsVisibilityWithoutOptionalLayer();

        // Activate Abstract EClass layer
        final SiriusOutlineView outlineView = designerViews.openOutlineView();
        outlineView.layers();
        outlineView.layers().activateLayer("Abstract EClass");
        SWTBotUtils.waitAllUiEvents();

        // Validate after optional layer activation
        validateEditPartsVisibilityWithOptionalLayer();

        localSession.save();
        localSession.closeNoDirty();
    }

    /**
     * Validate edit part visibility without optional layer.
     * 
     */
    private void validateEditPartsVisibilityWithoutOptionalLayer() {
        bot.waitUntil(new CheckEditPartIsNotDisplayed("C2", editor));

        // Validate containers display
        SWTBotGefEditPart c1EP = editor.getEditPart("C1", AbstractBorderedShapeEditPart.class);
        try {
            editor.getEditPart("C2", AbstractBorderedShapeEditPart.class);
        } catch (WidgetNotFoundException e) {
            // Normal behaviour, C2 should not be displayed anymore
        }
        SWTBotGefEditPart c3EP = editor.getEditPart("C3", AbstractBorderedShapeEditPart.class);
        SWTBotGefEditPart annotationEP = editor.getEditPart("This is an annotation to my references", AbstractBorderedShapeEditPart.class);

        // Validate edges between eclasses display
        List<SWTBotGefConnectionEditPart> c1EdgeEPList = editor.getConnectionEditPart(c3EP, c1EP);
        Assert.assertEquals("1 connection is expected between C3 and C1", 1, c1EdgeEPList.size());

        // Validate edges between eannotation and references display
        List<SWTBotGefConnectionEditPart> annotToC1EPList = editor.getConnectionEditPart(annotationEP, c1EdgeEPList.get(0));
        Assert.assertEquals("1 connection is expected between the annotation and C1", 1, annotToC1EPList.size());
    }

    /**
     * Validate edit part visibility with optional layer.
     * 
     */
    private void validateEditPartsVisibilityWithOptionalLayer() {
        bot.waitUntil(new CheckEditPartIsDisplayed("C2", editor));

        // Validate containers display
        SWTBotGefEditPart c1EP = editor.getEditPart("C1", AbstractBorderedShapeEditPart.class);
        SWTBotGefEditPart c2EP = editor.getEditPart("C2", AbstractBorderedShapeEditPart.class);
        SWTBotGefEditPart c3EP = editor.getEditPart("C3", AbstractBorderedShapeEditPart.class);
        SWTBotGefEditPart annotationEP = editor.getEditPart("This is an annotation to my references", AbstractBorderedShapeEditPart.class);

        // Validate edges between eclasses display
        List<SWTBotGefConnectionEditPart> c1EdgeEPList = editor.getConnectionEditPart(c3EP, c1EP);
        Assert.assertEquals("1 connection is expected between C3 and C1", 1, c1EdgeEPList.size());
        List<SWTBotGefConnectionEditPart> c2EdgeEPList = editor.getConnectionEditPart(c1EP, c2EP);
        Assert.assertEquals("1 connection is expected between C1 and C2", 1, c2EdgeEPList.size());
        List<SWTBotGefConnectionEditPart> c3EdgeEPList = editor.getConnectionEditPart(c2EP, c3EP);
        Assert.assertEquals("1 connection is expected between C2 and C3", 1, c3EdgeEPList.size());

        // Validate edges between eannotation and references display
        List<SWTBotGefConnectionEditPart> annotToC1EPList = editor.getConnectionEditPart(annotationEP, c1EdgeEPList.get(0));
        Assert.assertEquals("1 connection is expected between the annotation and C1", 1, annotToC1EPList.size());
        List<SWTBotGefConnectionEditPart> annotToC2EPList = editor.getConnectionEditPart(annotationEP, c2EdgeEPList.get(0));
        Assert.assertEquals("1 connection is expected between the annotation and C2", 1, annotToC2EPList.size());
        List<SWTBotGefConnectionEditPart> annotToC3EPList = editor.getConnectionEditPart(annotationEP, c3EdgeEPList.get(0));
        Assert.assertEquals("1 connection is expected between the annotation and C3", 1, annotToC3EPList.size());
        Assert.assertEquals("3 references from annotation should be visible", 3, annotationEP.sourceConnections().size());
    }

}
