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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusTextEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TextEditorAppearedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
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

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC, AIRD, ODESIGN);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, AIRD);

        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);
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
        String precondition = null;
        addDirectEditToolToVSM(diagramDescription, precondition);

        checkDirectEditLabel(precondition);
    }

    /**
     * Tests that the direct edit tool is not available if the tool has false
     * precondition.
     * 
     * @throws Exception
     *             thrown to fail
     */
    public void testDirectEditToolWithPrecondition() throws Exception {

        DDiagram dDiagram = (DDiagram) getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME);
        DiagramDescription diagramDescription = dDiagram.getDescription();

        // we create a new direct edit tool in VSM while the diagram is open
        String precondition = "[false/]";
        addDirectEditToolToVSM(diagramDescription, precondition);

        checkDirectEditLabel(precondition);
    }

    /**
     * Tests that the direct edit on a Note requires only two clicks.
     * 
     * @throws Exception
     *             thrown to fail
     */
    public void testDirectEditOnNote() throws Exception {
        if (!System.getProperty("os.name").equals("Linux")) {
            editor.activateTool("Note");
            editor.click(100, 100);
            editor.directEditType("Note Label");

            editor.click(CLASS_NAME, AbstractDiagramContainerEditPart.class);
            SWTBotUtils.waitAllUiEvents();

            checkDirectEditLabel(null, "Note Label", SiriusNoteEditPart.class);
        }
    }

    /**
     * Tests that the direct edit on a Note requires only two clicks.
     * 
     * @throws Exception
     *             thrown to fail
     */
    public void testDirectEditOnText() throws Exception {
        if (!System.getProperty("os.name").equals("Linux")) {
            editor.activateTool("Text");
            editor.click(100, 100);
            editor.directEditType("Text Label");

            editor.click(CLASS_NAME, AbstractDiagramContainerEditPart.class);
            SWTBotUtils.waitAllUiEvents();

            checkDirectEditLabel(null, "Text Label", SiriusTextEditPart.class);
        }
    }

    private void checkDirectEditLabel(String precondition) {
        checkDirectEditLabel(precondition, CLASS_NAME, AbstractDiagramContainerEditPart.class);
    }

    private void checkDirectEditLabel(String precondition, final String editPartlabel, Class<? extends IGraphicalEditPart> editPartClass) {
        // Click a first time on the edit part to select it.
        editor.click(editPartlabel, editPartClass);
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new CheckSelectedCondition(editor, editPartlabel, editPartClass));
        // Click a second time on the edit part to enable the direct edit mode.
        editor.clickCentered(editPartlabel, editPartClass);

        bot.waitUntil(new TextEditorAppearedCondition(editor, editPartClass, precondition), 3000);
        if ("[false/]".equals(precondition)) {
            return;
        }
        /* Find the text widget and check its label now. */
        List<Text> controls = editor.bot().getFinder().findControls(editor.getWidget(), new IsInstanceOf<Text>(Text.class), true);
        if (controls.size() == 1) {
            final Text textControl = controls.get(0);
            UIThreadRunnable.syncExec(new VoidResult() {
                @Override
                public void run() {
                    assertEquals(editPartlabel, textControl.getText());
                }
            });
        } else {
            throw new WidgetNotFoundException(String.format("Expected to find one text control, but found %s.  Is the editor in direct-edit mode?", controls.size()));
        }
        // Edit the current edit part by adding a suffix to its current name.
        editor.directEditType("newLabel");
        // Check that an editPart exists with the expected new name.
        editor.getEditPart("newLabel", editPartClass);
    }

    private void addDirectEditToolToVSM(DiagramDescription diagramDescription, String precondition) throws Exception {
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
        if (precondition != null) {
            directEditLabel.setPrecondition(precondition);
        }

        containerMapping.setLabelDirectEdit(directEditLabel);
        InitialOperation initialOperation = directEditLabel.getInitialOperation();

        SetValue setValue = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createSetValue();
        initialOperation.setFirstModelOperations(setValue);
        setValue.setFeatureName("name");
        setValue.setValueExpression("[arg0/]");

        odesignResource.save(Collections.emptyMap());
    }

}
