/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES and others.
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

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.junit.Assert;

/**
 * Tests dedicated to decorators provided by the VSM.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class DecoratorTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MAPPING_BASED_DECORATOR_REPRESENTATION_DESCRIPTION_NAME = "MappingBasedDecoration";

    private static final String MAPPING_BASED_DECORATOR_REPRESENTATION_NAME = "new MappingBasedDecoration";

    private static final String SEMANTIC_BASED_DECORATOR_REPRESENTATION_DESCRIPTION_NAME = "MappingBasedDecoration";

    private static final String SEMANTIC_BASED_DECORATOR_REPRESENTATION_NAME = "new MappingBasedDecoration";

    private static final String AIRD = "representations.aird";

    private static final String PATH = "/data/unit/decorator/";

    private static final String ODESIGN = "sprint.odesign";

    private static final String SEMANTIC = "sprint.uml";

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
    }

    /**
     * Test that a mapping based decorator with a precondition disappears and
     * reappears correctly when its precondition gets false and then true.
     */
    public void testMappingBasedDecoratorManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), MAPPING_BASED_DECORATOR_REPRESENTATION_DESCRIPTION_NAME, MAPPING_BASED_DECORATOR_REPRESENTATION_NAME,
                DDiagram.class, false);
        doTestDecorator();
    }

    /**
     * Test that a semantic based decorator with a precondition disappears and
     * reappears correctly when its precondition gets false and then true.
     */
    public void testSemanticBasedDecoratorAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), SEMANTIC_BASED_DECORATOR_REPRESENTATION_DESCRIPTION_NAME, SEMANTIC_BASED_DECORATOR_REPRESENTATION_NAME,
                DDiagram.class, false);
        doTestDecorator();
    }

    /**
     * Test that a mapping based decorator with a precondition disappears and
     * reappears correctly when its precondition gets false and then true.
     */
    public void testMappingBasedDecoratorAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), MAPPING_BASED_DECORATOR_REPRESENTATION_DESCRIPTION_NAME, MAPPING_BASED_DECORATOR_REPRESENTATION_NAME,
                DDiagram.class, false);
        doTestDecorator();
    }

    /**
     * Test that a semantic based decorator with a precondition disappears and
     * reappears correctly when its precondition gets false and then true.
     */
    public void testSemanticBasedDecoratorManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), SEMANTIC_BASED_DECORATOR_REPRESENTATION_DESCRIPTION_NAME, SEMANTIC_BASED_DECORATOR_REPRESENTATION_NAME,
                DDiagram.class, false);
        doTestDecorator();
    }

    /**
     * Test that a decorator with a precondition disappears and reappears
     * correctly when its precondition gets false and then true.
     */
    private void doTestDecorator() {
        DNode dNode = (DNode) ((Node) editor.getEditPart("400m", DNodeEditPart.class).part().getModel()).getElement();
        Assert.assertEquals("Unexpected number of decoration", 1, dNode.getDecorations().size());
        TransactionalEditingDomain transactionalEditingDomain = localSession.getOpenedSession().getTransactionalEditingDomain();
        // Rename an element displayed with a decorator to a value making the
        // decorator precondition wrong
        if (dNode.getTarget() instanceof org.eclipse.uml2.uml.Class) {
            final org.eclipse.uml2.uml.Class umlClass = (org.eclipse.uml2.uml.Class) dNode.getTarget();
            transactionalEditingDomain.getCommandStack().execute(new RecordingCommand(transactionalEditingDomain) {
                @Override
                protected void doExecute() {
                    umlClass.setName("false_" + umlClass.getName());
                }
            });
        }
        Assert.assertEquals("Unexpected number of decoration", 0, dNode.getDecorations().size());

        // Rename an element displayed with a decorator to a value making the
        // decorator precondition true
        if (dNode.getTarget() instanceof org.eclipse.uml2.uml.Class) {
            final org.eclipse.uml2.uml.Class umlClass = (org.eclipse.uml2.uml.Class) dNode.getTarget();
            transactionalEditingDomain.getCommandStack().execute(new RecordingCommand(transactionalEditingDomain) {
                @Override
                protected void doExecute() {
                    umlClass.setName(umlClass.getName().replaceFirst("false_", ""));
                }
            });
        }
        Assert.assertEquals("Unexpected number of decoration", 1, dNode.getDecorations().size());
    }

    @Override
    protected void tearDown() throws Exception {
        editor.close();
        editor = null;
        super.tearDown();
    }
}
