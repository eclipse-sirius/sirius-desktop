/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.modelers.ecore;

import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.repair.SiriusRepairProcess;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.unit.common.AbstractEcoreSynchronizerTest;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Styles customizations tests for Entities diagram of ecore modeler.
 * 
 * @author cbrun
 */
public class EntitiesDiagramStyleCustomizationTests extends AbstractEcoreSynchronizerTest {

    /**
     * The name of the new diagram created for this test.
     */
    private static final String NEW_DIAGRAM_NAME = "Test class diagram";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activateViewpoint(EcoreModeler.DESIGN_VIEWPOINT_NAME);
    }

    @Override
    protected String getSemanticResourcePath() {
        return "/org.eclipse.emf.ecore/model/Ecore.ecore";
    }

    public void testCustomStyleKeepingDuringRefresh() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Entities");
        assertNotNull("The unit test data seems incorrect", classDiag);
        prepareSynchronizer(classDiag, NEW_DIAGRAM_NAME);
        final DNodeList nodeList = (DNodeList) getDiagramElementsFromLabel(getRefreshedDiagram(), "EClass").get(0);
        ContainerStyle originalStyle = nodeList.getOwnedStyle();
        assertNotNull("we should get a node list corresponding to the EClass representation", nodeList);
        final WorkspaceImage customizedWorkspaceImageStyle = getCustomizedWorkspaceImageStyle("/org.eclipse.sirius/images/acceleo.gif");

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command setCustomizedWorkspaceImageStyleCmd = SetCommand.create(domain, nodeList, DiagramPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, customizedWorkspaceImageStyle);

        domain.getCommandStack().execute(setCustomizedWorkspaceImageStyleCmd);

        getRefreshedDiagram();

        final DNodeList retrievedNodeList = (DNodeList) getDiagramElementsFromLabel(sync, "EClass").get(0);
        assertEquals("We should have only the WorkspaceImage.workspacePath feature customized", Collections.singletonList(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName()),
                retrievedNodeList.getOwnedStyle().getCustomFeatures());
        assertTrue("we should have our customized WorkspaceImage style and not the original one", EcoreUtil.equals(customizedWorkspaceImageStyle, retrievedNodeList.getOwnedStyle()));

        Command decustomizeStyleCmd = RemoveCommand.create(domain, retrievedNodeList.getOwnedStyle(), ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES,
                retrievedNodeList.getOwnedStyle().getCustomFeatures());
        domain.getCommandStack().execute(decustomizeStyleCmd);

        getRefreshedDiagram();
        final DNodeList reinited = (DNodeList) getDiagramElementsFromLabel(sync, "EClass").get(0);
        assertFalse("we should have the original and not the custom as we removed the customization marker", EcoreUtil.equals(customizedWorkspaceImageStyle, reinited.getOwnedStyle()));
        // TODO: This change seems strange and let me thing that there will be another problem of code in Sirius or
        // other that uses EcoreUtil.equals.
        assertTrue("we should have the original style", new EqualityHelper().equals(originalStyle, reinited.getOwnedStyle()));
    }

    public void testCustomStyleKeepingDuringRepair() throws Exception {

        DiagramDescription classDiag = findDiagramDescription("Entities");
        assertNotNull("The unit test data seems incorrect", classDiag);
        prepareSynchronizer(classDiag, NEW_DIAGRAM_NAME);
        final DNodeList nodeList = (DNodeList) getDiagramElementsFromLabel(getRefreshedDiagram(), "EClass").get(0);
        assertNotNull("we should get a node list corresponding to the EClass representation", nodeList);
        String workspacePath = "/org.eclipse.sirius/images/acceleo.gif";
        WorkspaceImage customizedWorkspaceImageStyle = getCustomizedWorkspaceImageStyle(workspacePath);

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command setCustomizedWorkspaceImageStyleCmd = SetCommand.create(domain, nodeList, DiagramPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, customizedWorkspaceImageStyle);

        domain.getCommandStack().execute(setCustomizedWorkspaceImageStyleCmd);

        getRefreshedDiagram();

        // Get the session uri before close: the session resource will be null
        // after.
        session.save(new NullProgressMonitor());
        URI sessionResourceURI = session.getSessionResource().getURI();
        session.close(new NullProgressMonitor());

        IFile sessionResourceFile = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getFile(sessionResourceURI.segment(2));
        // migration done automatically at loading time
        SiriusRepairProcess process = new SiriusRepairProcess(sessionResourceFile, false);
        process.run(new NullProgressMonitor());
        process.dispose();

        session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        uiSession.open();
        semanticModel = session.getSemanticResources().iterator().next().getContents().get(0);

        assertNotNull("we should be able to retrieve the new session from the model", session);

        DRepresentation newRep = null;
        for (DRepresentationDescriptor representationDescriptor : DialectManager.INSTANCE.getRepresentationDescriptors(semanticModel, session)) {
            if (NEW_DIAGRAM_NAME.equals(representationDescriptor.getName())) {
                newRep = representationDescriptor.getRepresentation();
                break;
            }
        }

        assertNotNull("We should retrieve the representation with name : \"" + NEW_DIAGRAM_NAME + "\"", newRep);
        final DNodeList retrievedNodeList = (DNodeList) getDiagramElementsFromLabel((DDiagram) newRep, "EClass").get(0);
        assertEquals("We should have only the WorkspaceImage.workspacePath feature customized", Collections.singletonList(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName()),
                retrievedNodeList.getOwnedStyle().getCustomFeatures());
        assertTrue("we should have our customized WorkspaceImage style and not the original one", retrievedNodeList.getOwnedStyle() instanceof WorkspaceImage);
        customizedWorkspaceImageStyle = (WorkspaceImage) retrievedNodeList.getOwnedStyle();
        assertEquals("We should have only the WorkspaceImage.workspacePath feature customized", workspacePath, customizedWorkspaceImageStyle.getWorkspacePath());
    }

    private WorkspaceImage getCustomizedWorkspaceImageStyle(String workspacePath) {
        WorkspaceImage image = DiagramFactory.eINSTANCE.createWorkspaceImage();
        image.getCustomFeatures().add(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName());
        image.setWorkspacePath(workspacePath);
        return image;
    }

}
