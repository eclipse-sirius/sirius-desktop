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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.UILocalSessionBrowser;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * UI tests on local session.
 * 
 * @author dlecan
 */
public class LocalSessionViewTest extends AbstractScenarioTestCase {

    private static final String DIAG1 = "diag1";

    private static final String VIEWPOINT_DESIGN = "Design";

    private UIResource ecoreEcoreResource;

    private SessionChoice wizard;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        ecoreEcoreResource = new UIResource(designerProject, MODELS_DIR, "Ecore.ecore");
        wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);
    }

    /**
     * Verify that session is not dirty after saving operation.<br/>
     * 
     * @throws Exception
     *             Test error.
     */
    public void _testDirtySavedSession() throws Exception {
        final UILocalSession localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectNoViewpoint();

        localSession.closeAndDiscardChanges();

        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return !localSession.getOpenedSession().isOpen();
            }

            @Override
            public void init(SWTBot bot) {
                // TODO Auto-generated method stub

            }

            @Override
            public String getFailureMessage() {
                // TODO Auto-generated method stub
                return null;
            }
        });

        final SWTBotView modelContentView = bot.viewById("org.eclipse.sirius.ui.tools.views.sessionview");
        modelContentView.setFocus();
        // If session is saved and closed, dirty flag '*' should not be found in
        // view title
        assertThat("Session is still dirty", modelContentView.getTitle(), not(containsString("*")));
    }

    /**
     * This test checks that ui tree stays constant during session operations
     * (diagram creation and save), <i>ie</i> all expanded tree branches stay
     * expanded.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testConstantLocalSessionTreeLayoutDuringSessionOperations() throws Exception {
        final UILocalSession localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(VIEWPOINT_DESIGN);

        // Expand tree
        localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_DESIGN);

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreNode = semanticResourceNode.expandNode("ecore");
        final UIDiagramRepresentation diagramRepresentation = localSession.newDiagramRepresentation("ecore package entities", "Entities").on(ecoreNode).withName(DIAG1).ok();

        SWTBotTreeItem treeItem = localSession.getLocalSessionBrowser().getTreeItem().getNode(UILocalSessionBrowser.PER_CATEGORY_LABEL);
        assertTrue("Tree item is not expanded before saving", isExpanded(treeItem));

        diagramRepresentation.getEditor().setFocus();
        diagramRepresentation.getEditor().click(0, 0);
        diagramRepresentation.save().close();

        treeItem = localSession.getLocalSessionBrowser().getTreeItem().getNode(UILocalSessionBrowser.PER_CATEGORY_LABEL);
        assertTrue("Tree item is not expanded after saving", isExpanded(treeItem));
    }

    // Replace this by treeItem.isExpanded() when it will be available
    private boolean isExpanded(final SWTBotTreeItem treeItem) {
        return UIThreadRunnable.syncExec(treeItem.display, new BoolResult() {
            @Override
            public Boolean run() {
                return treeItem.widget.getExpanded();
            }
        });
    }
}
