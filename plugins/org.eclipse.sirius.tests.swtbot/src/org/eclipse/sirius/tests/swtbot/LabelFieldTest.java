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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Test;

/**
 * Test that label field was selected when user focus on. Test if user remove label field when focus out the label field
 * is fills with id field value.
 * 
 * @author jdupont
 */
public class LabelFieldTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Sirius Specific Model.
     */
    private static final String VSM = "ecore.odesign";

    /**
     * 
     */
    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    /**
     * Test repository.
     */
    private static final String DATA_UNIT_DIR = "data/unit/vsm/";

    /**
     * Sirius Group.
     */
    private static final String GROUP = "Ecore Editing Workbench V4.6";

    /**
     * Properties view.
     */
    protected static final String PROPERTIES = "Properties";

    /**
     * Properties view tab General.
     */
    private static final String GENERAL = "General";

    /**
     * Sirius name.
     */
    private static final String VIEWPOIT_NAME = "Design";

    /**
     * SwtBotText corresponding to label field.
     */
    private SWTBotText labelText;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        // opening VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);

        // access to viewpoint element naming Design
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode(VIEWPOIT_NAME).select();

        // accesses to property view
        SWTBotView propertiesView = bot.viewByTitle(PROPERTIES);
        propertiesView.setFocus();

        // accesses to tab General
        SWTBotSiriusHelper.selectPropertyTabItem(GENERAL, propertiesView.bot());

        // access to label field
        labelText = propertiesView.bot().text(1);
        // focus on label field
        labelText.setFocus();
    }

    /**
     * Test that label field was removed if is same that id or selected otherwise when user focus on.
     */
    @Test
    public void testSelectedLabelFieldOnFocus() {
        assertEquals("the label field should not be selected", false, isFieldLabelAllCharacterSelected(labelText));
        assertEquals("the label field must be deleted", "", labelText.getText());
        // Rename label field
        labelText.setText("Test");
        // access to Model file extension field
        bot.viewByTitle(PROPERTIES).bot().text(2).setFocus();
        // access to label field
        bot.viewByTitle(PROPERTIES).bot().text(1).setFocus();
        assertEquals("the label field must be selected", true, isFieldLabelAllCharacterSelected(labelText));
    }

    /**
     * Test if user remove label field when focus out the label field is fills with id field value.
     */
    public void testLabelFieldFillsIfEmpty() {
        // remove label field value
        labelText.setText("");
        // check that label field is empty
        assertEquals("The label text must be empty", "", labelText.getText());
        // access to Model file extension field
        bot.viewByTitle(PROPERTIES).bot().text(2).setFocus();
        // check that the label field is fills
        assertEquals("The label text must be fills", VIEWPOIT_NAME, labelText.getText());
    }

    /**
     * Open viewpoint specification model.
     * 
     * @param viewpointSpecificationModel
     *            the name of viewpoint specification model (.odesing)
     * @return odesignEditor
     */
    @Override
    public SWTBotVSMEditor openViewpointSpecificationModel(String viewpointSpecificationModel) {
        SWTBotCommonHelper.openEditor(getProjectName(), viewpointSpecificationModel);
        SWTBotVSMEditor odesignEditor = SWTBotVSMHelper.getVSMEditorContainingName(viewpointSpecificationModel);
        odesignEditor.setFocus();
        return odesignEditor;
    }

    /**
     * Check that all character label field are selected.
     * 
     * @param label
     *            the field label
     * @return true if field is selected, false otherwise
     */
    private boolean isFieldLabelAllCharacterSelected(SWTBotText label) {
        RunnableWithResult<Boolean> runnable = new RunnableWithResult.Impl<Boolean>() {
            @Override
            public void run() {
                labelText.widget.getDisplay().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        setResult(labelText.getText().length() != 0 && labelText.widget.getSelectionCount() == labelText.getText().length());
                    }
                });
            }
        };
        labelText.widget.getDisplay().syncExec(runnable);
        return runnable.getResult().booleanValue();
    }

}
