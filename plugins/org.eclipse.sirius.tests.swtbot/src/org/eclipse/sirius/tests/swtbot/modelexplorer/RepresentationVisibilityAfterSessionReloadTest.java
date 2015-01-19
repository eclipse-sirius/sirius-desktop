/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.modelexplorer;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test representation visibility in Model Explorer view after session reload.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class RepresentationVisibilityAfterSessionReloadTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String SEMANTIC_RESOURCE_NAME = "My.ecore";

    private static final String SESSION_RESOURCE_NAME = "representations.aird";

    private static final String PATH = "data/unit/modelExplorer/";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "P0 package entities";

    private UIResource sessionAirdResource;

    private SWTBot modelExplorerViewBot;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
        SWTBotUtils.waitAllUiEvents();
        modelExplorerView.setFocus();
        modelExplorerViewBot = modelExplorerView.bot();
    }

    /**
     * Ensure that the representation still appears under its semantic resource
     * in the Model Explorer view after session reload.
     */
    public void testRepresentationVisibilityAfterSessionReload() {
        // Check that the representation appears in Model Explorer before
        // session reload
        checkRepresentationVisibility(true);
        // Edit the semantic model
        editTheSemanticModel();
        // Check that the representation still appears in Model Explorer after
        // session reload
        checkRepresentationVisibility(false);
    }

    /**
     * Check that the representation appears in Model Explorer before or after
     * session reload.
     * 
     * @param sessionReload
     *            true before session reload, otherwise false.
     */
    private void checkRepresentationVisibility(boolean sessionReload) {
        SWTBotTreeItem projectTreeItemBot = modelExplorerViewBot.tree().expandNode(getProjectName(), false);
        SWTBotTreeItem representationTreeItemBot = projectTreeItemBot.getNode(SESSION_RESOURCE_NAME).expand();
        SWTBotTreeItem semanticResourceTreeItemBot = representationTreeItemBot.getNode(1).expand();
        SWTBotTreeItem rootPackageTreeItemBot = semanticResourceTreeItemBot.getNode(0).expand();
        // Ensure that the representation exists in the root package
        if (sessionReload) {
            assertTrue("The representation '" + REPRESENTATION_DESCRIPTION_NAME + "' should exist in " + rootPackageTreeItemBot.getText() + " before session reload", rootPackageTreeItemBot.getNodes()
                    .contains(REPRESENTATION_DESCRIPTION_NAME));
        } else {
            assertTrue("The representation '" + REPRESENTATION_DESCRIPTION_NAME + "' should exist in " + rootPackageTreeItemBot.getText() + " after session reload", rootPackageTreeItemBot.getNodes()
                    .contains(REPRESENTATION_DESCRIPTION_NAME));
        }
    }

    /**
     * Edit the semantic model and save resource.
     */
    private void editTheSemanticModel() {
        TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        ResourceSet set = domain.getResourceSet();

        Iterator<Resource> resourcesIterator = localSession.getOpenedSession().getSemanticResources().iterator();
        EPackage ePackage = null;
        if (resourcesIterator.hasNext()) {
            Resource semanticResource = resourcesIterator.next();
            if (!semanticResource.getContents().isEmpty()) {
                ePackage = ((EPackage) semanticResource.getContents().get(0)).getESubpackages().get(0);
            }
        }
        try {
            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(ePackage.eResource().getURI(), set);
            assertFalse("The editing domain of each root semantic must be different.", domain.equals(TransactionUtil.getEditingDomain(ePackage)));
            domain.getCommandStack().execute(new RecordingCommand(domain, "Remove all classes") {
                @Override
                protected void doExecute() {
                    ePackageInAnotherResourceSet.getEClassifiers().clear();
                }
            });
            ePackageInAnotherResourceSet.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Promblem when saving the resource in another resourceSet : " + e.getMessage());
        }
    }
}
