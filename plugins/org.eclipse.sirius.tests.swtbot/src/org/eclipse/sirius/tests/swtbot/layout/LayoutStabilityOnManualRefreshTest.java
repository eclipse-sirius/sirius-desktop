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
package org.eclipse.sirius.tests.swtbot.layout;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests containers/nodes layout stability during manual refresh.
 * 
 * @author lredor
 */
public class LayoutStabilityOnManualRefreshTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_NAME1 = "withoutPinnedBrothers";

    private static final String REPRESENTATION_NAME2 = "withPinnedBrothers";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String DATA_UNIT_DIR = "data/unit/layout/VP-2602/";

    private static final String FILE_DIR = "/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Test that layout is stable after insertion with manual refresh. <BR>
     * VP-2602 : Case with unpinned brothers
     * 
     * @throws Exception
     *             Test error.
     */
    public void testLayoutStabilityOnOneViewCreatedDuringManualRefreshWithoutPinnedElement() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1, DDiagram.class);
        layoutStabilityOnOneViewCreatedDuringManualRefreshCommon();
    }

    /**
     * Test that layout is stable after insertion with manual refresh. <BR>
     * VP-2602 : Case with pinned brothers
     * 
     * @throws Exception
     *             Test error.
     */
    public void testLayoutStabilityOnOneViewCreatedDuringManualRefreshWithPinnedElement() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2, DDiagram.class);
        layoutStabilityOnOneViewCreatedDuringManualRefreshCommon();
    }

    private void layoutStabilityOnOneViewCreatedDuringManualRefreshCommon() {
        // Gets the location of C11
        SWTBotGefEditPart c11Bot = editor.getEditPart("C11", AbstractDiagramListEditPart.class);
        final Point c11Location = editor.getBounds(c11Bot).getLocation();

        // Gets the location of C12
        SWTBotGefEditPart c12Bot = editor.getEditPart("C12", AbstractDiagramListEditPart.class);
        final Point c12Location = editor.getBounds(c12Bot).getLocation();

        // Load the semantic resource in another resource set, add a package p13
        // in p1 (brother of p11 and p12 that is respectively
        // the container of C11 and C12), and save the resource.
        TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        ResourceSet set = domain.getResourceSet();
        try {
            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(localSession.getOpenedSession().getSemanticResources().iterator().next().getURI(), set);
            domain.getCommandStack().execute(new RecordingCommand(domain, "Add a new package") {

                @Override
                protected void doExecute() {
                    EPackage newPackage = EcoreFactory.eINSTANCE.createEPackage();
                    newPackage.setName("p13");
                    ePackageInAnotherResourceSet.getESubpackages().get(0).getESubpackages().add(newPackage);
                }
            });
            ePackageInAnotherResourceSet.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
        SWTBotUtils.waitAllUiEvents();

        // Launching manual refresh
        editor.click(0, 0);
        editor.save();
        manualRefresh();
        // SWTBotUtils.waitAllUiEvents();

        // Gets the location of C11 after adding the package
        c11Bot = editor.getEditPart("C11", AbstractDiagramListEditPart.class);
        final Point c11Location_after = editor.getBounds(c11Bot).getLocation();

        // Gets the location of C12 after adding the package
        c12Bot = editor.getEditPart("C12", AbstractDiagramListEditPart.class);
        final Point c12Location_after = editor.getBounds(c12Bot).getLocation();

        // Asserts that the classes kept their initial position.
        assertEquals("C11 class has not kept its initial position!", c11Location, c11Location_after);
        assertEquals("C12 class has not kept its initial position!", c12Location, c12Location_after);
    }
}
