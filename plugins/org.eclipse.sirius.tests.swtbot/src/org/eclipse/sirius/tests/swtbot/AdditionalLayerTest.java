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
package org.eclipse.sirius.tests.swtbot;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import com.google.common.collect.Iterables;

/**
 * This class aims to test the behavior of additional layers. A mandatory
 * additional layer (optional attribute at false) should be activated when the
 * diagram is initialized and refreshed (if in the mean time, a new mandatory
 * layer appears in VSM).
 * 
 * @author fbarbin
 * 
 */
public class AdditionalLayerTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VSM = "vp-4179.odesign";

    private static final String AIRD = "vp-4179.aird";

    private static final String MODEL = "vp-4179.ecore";

    private static final String DATA_UNIT_DIR = "data/unit/layers/vp-4179/";

    private Session session;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM, AIRD, MODEL);
    }

    /**
     * Tests that mandatory additional layers are correctly activated when
     * initializing the diagram.
     */
    public void testAddtionalLayerAtDiagramCreationTime() {
        openSessionAndCreateRepresentation();

        checkActivatedAdditionalLayers(3);

        // I can't access to layer menu items to check those which are available
        // checkMenu();

    }

    private void openSessionAndCreateRepresentation() {
        sessionAirdResource = new UIResource(designerProject, "/", AIRD);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotTreeItem modelElementItem = localSession.getLocalSessionBrowser().perSemantic().expandNode("test").click();
        final UIDiagramRepresentation diagramRepresentation = localSession.newDiagramRepresentation("new vp-4179", "vp-4179").on(modelElementItem).withDefaultName().ok();
        editor = diagramRepresentation.open().getEditor();
    }

    /**
     * Tests that a new mandatory layer added in VSM is correctly activated when
     * refreshing the diagram.
     */
    public void testAddMandatoryAddtionalLayerInVSM() {
        openSessionAndCreateRepresentation();
        addNewMandatoryLayer();
        refreshRepresentation();
        checkActivatedAdditionalLayers(4);
    }

    /**
     * Tests that a new mandatory layer added by a new viewpoint activation is
     * correctly activated when refreshing the diagram.
     */
    public void testActivateNewVPWithMandatoryLayer() {
        openSessionAndCreateRepresentation();
        session = localSession.getOpenedSession();
        localSession.changeViewpointSelection(Collections.singleton("vp-4179_AL_ext"), Collections.<String> emptySet());
        refreshRepresentation();
        checkActivatedAdditionalLayers(4);
    }

    private void refreshRepresentation() {
        session = localSession.getOpenedSession();
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();

        DAnalysis analysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        Collection<DRepresentation> representations = new DViewQuery(analysis.getOwnedViews().get(0)).getLoadedRepresentations();

        RefreshRepresentationsCommand command = new RefreshRepresentationsCommand(domain, new NullProgressMonitor(), representations);
        domain.getCommandStack().execute(command);

    }

    private void addNewMandatoryLayer() {
        Resource vsmResource = new ResourceSetImpl().getResource(URI.createPlatformResourceURI(TEMP_PROJECT_NAME + "/" + VSM, true), true);
        Group group = (Group) vsmResource.getContents().get(0);
        final EList<AdditionalLayer> additionalLayers = ((DiagramDescription) group.getOwnedViewpoints().get(0).getOwnedRepresentations().get(0)).getAdditionalLayers();

        final AdditionalLayer additionalLayer = DescriptionFactory.eINSTANCE.createAdditionalLayer();
        additionalLayer.setOptional(false);
        additionalLayer.setName("mandatory");
        additionalLayer.setLabel("mandatory");

        additionalLayers.add(additionalLayer);
        try {
            vsmResource.save(Collections.emptyMap());
        } catch (IOException e) {
            fail("Can't save vsm resource");
        }
    }

    private void checkActivatedAdditionalLayers(int expectedNumber) {

        session = localSession.getOpenedSession();
        DAnalysis analysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        EList<Layer> activatedLayers = ((DDiagram) new DViewQuery(analysis.getOwnedViews().get(0)).getLoadedRepresentations().iterator().next()).getActivatedLayers();
        Iterable<AdditionalLayer> additionalLayers = Iterables.filter(activatedLayers, AdditionalLayer.class);
        assertEquals("There should be " + expectedNumber + " activated additional layers.", expectedNumber, Iterables.size(additionalLayers));

    }
}
