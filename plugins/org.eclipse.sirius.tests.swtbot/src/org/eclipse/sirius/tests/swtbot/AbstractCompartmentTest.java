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

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Compartments-related tests.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public abstract class AbstractCompartmentTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/compartments/";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String VSM_FILE = "compartments.odesign";

    protected static final String VSM_RESOURCE = "platform:/resource/DesignerTestProject/" + VSM_FILE;

    protected static final String VSM_NAME = "Compartments";

    protected static final String FILE_DIR = "/";

    protected static final String HORIZONTAL_STACK_REPRESENTATION_NAME = "Diag with HStack";

    protected static final String HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME = "new Diag with HStack";

    protected static final String VERTICAL_STACK_REPRESENTATION_NAME = "Diag with VStack";

    protected static final String VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME = "new Diag with VStack";

    protected static final String PACKAGE_CREATION_TOOL_NAME = "newPackage";

    protected static final String CLASS_NODE_CREATION_TOOL_NAME = "newClassNode";

    protected static final String CLASS_LIST_CREATION_TOOL_NAME = "newClassList";

    protected static final String ATTRIBUTE_CREATION_TOOL_NAME = "newAttribute";

    protected static final String COMPARTMENT_NAME = "P1";

    protected static final String NEW_COMPARTMENT_NAME = "newP2";

    protected static final String PACKAGE_3_NAME = "P3";

    protected static final String PACKAGE_4_NAME = "P4";

    protected static final String NEW_PACKAGE_1_NAME = "newP1";

    protected static final String LEFT_CLASS_NAME = "LeftClass1";

    protected static final String CENTER_CLASS_NAME = "CenterClass2";

    protected static final String RIGHT_CLASS_NAME = "RightClass3";

    protected static final String NEW_CLASS_NODE_NAME = "newClassNode1";

    protected static final String NEW_CLASS_LIST_4_NAME = "newClassList4";

    protected static final String NEW_CLASS_LIST_1_NAME = "newClassList1";

    protected static final String NEW_ATTRIBUTE_NODE_NAME = "newAttr2";

    protected static final String COMPARTMENT_MAPPING = "Compartment";

    protected static final String LIST_REGIONS_MAPPING = "ListRegions";

    protected static final String FREE_FORM_REGIONS_MAPPING = "FreeFormRegions";

    protected static final String COMPARTMENT_STYLE_MAPPING = "Gradient white to light_gray";

    protected static final String REGIONS_STYLE_MAPPING = "Gradient regionInterpolatedColor to regionInterpolatedColor";

    protected static final String PROPERTIES = "Properties";

    protected static final String GENERAL = "General";

    protected static final String LABEL = "Label";

    protected static final String BORDER = "Border";

    protected SWTBotSiriusDiagramEditor diagramEditor;

    protected SWTBotVSMEditor vsmEditor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Open representation.
     * 
     * @param representationName
     *            the representation name
     * @param representationInstanceName
     *            the representation instance name
     */
    protected void openRepresentation(String representationName, String representationInstanceName) {
        diagramEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), representationName, representationInstanceName, DDiagram.class);
    }

    /**
     * Gets the semantic element associated to the given edit part.
     * 
     * @param editPartName
     *            the edit part name
     * @return the semantic element associated to the given edit part
     */
    protected DDiagramElementContainer getDiagramElement(String editPartName) {
        SWTBotGefEditPart editPart = diagramEditor.getEditPart(editPartName, AbstractDiagramElementContainerEditPart.class);
        DDiagramElementContainer diagramElement = (DDiagramElementContainer) ((IGraphicalEditPart) editPart.part()).resolveSemanticElement();
        return diagramElement;
    }

    /**
     * Open the VSM editor.
     */
    protected void openVSM() {
        vsmEditor = openViewpointSpecificationModel(VSM_FILE);
    }
}
