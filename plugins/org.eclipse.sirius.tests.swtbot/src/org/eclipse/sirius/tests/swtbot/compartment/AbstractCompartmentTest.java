/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.compartment;

import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.compartment.ICompartmentTests;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Compartments-related tests.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public abstract class AbstractCompartmentTest extends AbstractSiriusSwtBotGefTestCase implements ICompartmentTests {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

        // Activate auto refresh
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
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
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), representationName, representationInstanceName, DDiagram.class, true, true);
    }

    /**
     * Gets the semantic element associated to the given edit part.
     * 
     * @param editPartName
     *            the edit part name
     * @return the semantic element associated to the given edit part
     */
    protected DDiagramElementContainer getDiagramElementContainer(String editPartName) {
        SWTBotGefEditPart editPart = editor.getEditPart(editPartName, AbstractDiagramElementContainerEditPart.class);
        DDiagramElementContainer diagramElement = (DDiagramElementContainer) ((AbstractDiagramElementContainerEditPart) editPart.part()).resolveDiagramElement();
        return diagramElement;
    }
}
