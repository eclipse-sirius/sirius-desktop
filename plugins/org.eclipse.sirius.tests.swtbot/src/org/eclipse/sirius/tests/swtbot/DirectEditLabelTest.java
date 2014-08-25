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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.util.DescriptionResourceFactoryImpl;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

/**
 * Test class that makes sure that direct edit tools are correctly updated when
 * they are modified into the VSM.
 * 
 * See VP-3507.
 * 
 * @author fbarbin
 */
public class DirectEditLabelTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String CLASS_NAME = "Test1";

    private static final String DIRECT_EDIT_NAME = "directEdit";

    private static final String AIRD = "My.aird";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "desc";

    private static final String REPRESENTATION_NAME = "new desc";

    private static final String PATH = "/data/unit/directEdit/VP-3507/";

    private static final String ODESIGN = "My.odesign";

    private static final String SEMANTIC = "My.ecore";

    private static final String FILE_DIR = "/";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC, AIRD, ODESIGN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, AIRD);

        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

    }

    /**
     * Tests that the direct edit tool is available even if the tool is added in
     * the VSM while the diagram is open.
     * 
     * @throws Exception
     *             thrown to fail
     */
    public void testDirectEditToolIfAddedWhileRepresentationIsOpen() throws Exception {

        DDiagram dDiagram = (DDiagram) getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME);
        DiagramDescription diagramDescription = dDiagram.getDescription();

        // we create a new direct edit tool in VSM while the diagram is open
        addDirectEditToolToVSM(diagramDescription);

        checkDirectEditLabel();
    }

    private void checkDirectEditLabel() {
        // Click a first time on the edit part to select it.
        editor.click(CLASS_NAME, AbstractDiagramContainerEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        // Click a second time on the edit part to enable the direct edit mode.
        editor.clickCentered(CLASS_NAME, AbstractDiagramContainerEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        /* wait until text widget appears */
        editor.bot().text();
        /* Find the text widget and check its label now. */
        List<Text> controls = editor.bot().getFinder().findControls(editor.getWidget(), new IsInstanceOf<Text>(Text.class), true);
        if (controls.size() == 1) {
            final Text textControl = controls.get(0);
            UIThreadRunnable.syncExec(new VoidResult() {
                public void run() {
                    assertEquals(CLASS_NAME, textControl.getText());
                }
            });
        } else {
            throw new WidgetNotFoundException(String.format("Expected to find one text control, but found %s.  Is the editor in direct-edit mode?", controls.size()));
        }
        // Edit the current edit part by adding a suffix to its current name.
        editor.directEditType("newLabel");
        // Check that an editPart exists with the expected new name.
        editor.getEditPart("newLabel", AbstractDiagramContainerEditPart.class);
    }

    private void addDirectEditToolToVSM(DiagramDescription diagramDescription) throws Exception {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("odesign", new DescriptionResourceFactoryImpl());
        Resource odesignResource = resourceSet.getResource(diagramDescription.eResource().getURI(), true);
        Group groupInOtherResourceSet = (Group) odesignResource.getContents().get(0);

        DiagramDescription diagramDescriptionInOtherResourceSet = (DiagramDescription) groupInOtherResourceSet.getOwnedViewpoints().get(0).getOwnedRepresentations().get(0);

        ContainerMapping containerMapping = diagramDescriptionInOtherResourceSet.getDefaultLayer().getContainerMappings().get(0);

        ToolSection section = ToolFactory.eINSTANCE.createToolSection();
        diagramDescriptionInOtherResourceSet.getDefaultLayer().getToolSections().add(section);

        DirectEditLabel directEditLabel = ToolFactory.eINSTANCE.createDirectEditLabel();
        section.getOwnedTools().add(directEditLabel);
        directEditLabel.setName(DIRECT_EDIT_NAME);

        containerMapping.setLabelDirectEdit(directEditLabel);
        InitialOperation initialOperation = directEditLabel.getInitialOperation();

        SetValue setValue = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createSetValue();
        initialOperation.setFirstModelOperations(setValue);
        setValue.setFeatureName("name");
        setValue.setValueExpression("[arg0/]");

        odesignResource.save(Collections.emptyMap());
    }

}
