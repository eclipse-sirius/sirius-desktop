/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.internal.business;

import java.util.Collections;
import java.util.Set;

import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIProject;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.AirdPickingChoice;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SemanticModelPage;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.VpSelectionAfterWizard;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.WizardOk;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.dialog.ViewpointSelectionDialog;
import org.eclipse.sirius.tests.swtbot.support.api.widget.WrappedSWTBotRadio;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

import com.google.common.collect.Sets;

/**
 * Local session creation wizard.
 * 
 * @author dlecan
 */
public class UISessionCreationWizard implements SessionChoice {

    private static final String DEFAULT_SESSION_NAME = "representations";

    private final AirdPickingChoice airdPickingChoice = new AirdPickingChoice() {
        @Override
        public WizardOk select(final UIResource res) {
            sessionResource = res;
            return wizardOK;

        }

        @Override
        public WizardOk withDefaultSessionName() {
            UISessionCreationWizard.this.withDefaultSessionName();
            return wizardOK;
        }

        @Override
        public UILocalSession withDefaultSessionNameWithoutViewpointSelection() {
            UISessionCreationWizard.this.withDefaultSessionName();
            bot.tree().getAllItems()[0].select();
            final SWTBotButton button = bot.button("Finish");
            SWTBotShell dialogShell = bot.activeShell();
            bot.waitUntil(new ItemEnabledCondition(button));
            button.click();
            bot.waitUntil(Conditions.shellCloses(dialogShell));
            sessionResource = new UIResource(uiProject, UISessionCreationWizard.DEFAULT_SESSION_NAME + "." + SiriusUtil.SESSION_RESOURCE_EXTENSION);
            return new UILocalSession(getSessionResource());
        }
    };

    private final SWTWorkbenchBot bot;

    private UIResource modelResource;

    private ViewpointSelectionDialog selectionDialog;

    private final SemanticModelPage semanticModelPage = new SemanticModelPage() {

        @Override
        public AirdPickingChoice select(final UIResource res) {
            UISessionCreationWizard.this.sessionResource = res;
            return airdPickingChoice;
        }

    };

    private String sessionName;

    private UIResource sessionResource;

    private UIProject uiProject;

    private final VpSelectionAfterWizard vpSelection = new VpSelectionAfterWizard() {

        @Override
        public UILocalSession selectNoViewpoint() {
            UISessionCreationWizard.this.selectNoViewpoint();
            final UILocalSession lSession = UISessionCreationWizard.this.finish();
            return lSession;
        }

        @Override
        public UILocalSession selectViewpoints(final String... vps) {
            UISessionCreationWizard.this.selectViewpoints(Sets.newHashSet(vps));
            final UILocalSession lSession = UISessionCreationWizard.this.finish();
            return lSession;
        }
    };

    private final WizardOk wizardOK = new WizardOk() {
        @Override
        public VpSelectionAfterWizard finish() {
            return vpSelection;
        }

    };

    private SWTBotShell wizardShell;

    /**
     * Constructor.
     */
    public UISessionCreationWizard() {
        bot = new SWTWorkbenchBot();
    }

    /**
     * Constructor.
     * 
     * @param pUIResource
     *            UI resource model used to open the wizard.
     */
    public UISessionCreationWizard(final UIResource pUIResource) {
        this();
        modelResource = pUIResource;
        selectionDialog = new ViewpointSelectionDialog(bot);
        wizardShell = bot.shell("New Representations File");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AirdPickingChoice emptySession() {
        SWTBotRadio emptySessionRadioButton = bot.radio(0);
        emptySessionRadioButton = new WrappedSWTBotRadio(emptySessionRadioButton);
        emptySessionRadioButton.click();

        final SWTBotButton button = bot.button("Next >");
        bot.waitUntil(new ItemEnabledCondition(button));
        button.click();
        return airdPickingChoice;
    }

    /**
     * Finish the wizard.
     * 
     * @return {@link UILocalSession} to handle local session.
     */
    public UILocalSession finish() {
        bot.waitUntil(Conditions.shellCloses(wizardShell));

        return new UILocalSession(getModelResource(), getSessionResource());
    }

    /**
     * From already selected semantic resource.
     * 
     * @return Current {@link UISessionCreationWizard}.
     */
    @Override
    public AirdPickingChoice fromAlreadySelectedSemanticResource() {
        new WrappedSWTBotRadio(bot.radio("Initialization from a semantic resource")).click();
        final SWTBotButton button = bot.button("Finish");
        bot.waitUntil(new ItemEnabledCondition(button));
        button.click();
        SWTBotUtils.waitProgressMonitorClose("New Representations File");
        return airdPickingChoice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SemanticModelPage fromSemanticResource() {
        return semanticModelPage;
    }

    /**
     * Returns the model resource.
     * 
     * @return The model resource.
     */
    public UIResource getModelResource() {
        return modelResource;
    }

    /**
     * Returns the session resource.
     * 
     * @return The session resource.
     */
    public UIResource getSessionResource() {

        if (sessionResource == null) {

            UIProject project = uiProject;
            if (project == null) {
                project = modelResource.getProject();
            }
            sessionResource = new UIResource(project, modelResource.getPath(), sessionName + "." + SiriusUtil.SESSION_RESOURCE_EXTENSION);
        }

        return sessionResource;
    }

    /**
     * In method.
     * 
     * @param pUiProject
     *            Project where to create Local Session.
     * @return Current {@link UISessionCreationWizard}.
     */
    @Override
    public UISessionCreationWizard in(final UIProject pUiProject) {
        this.uiProject = pUiProject;
        return this;
    }

    /**
     * "Select Viewpoints" operation, but without viewpoint selection.
     * 
     * @return Current {@link UISessionCreationWizard}
     * @see #selectViewpoints(Set)
     */
    public UISessionCreationWizard selectNoViewpoint() {
        return selectViewpoints(Collections.<String> emptySet());
    }

    /**
     * Select one viewpoint.
     * 
     * @param viewpointToSelect
     *            Viewpoint to select to finish the wizard
     * @return Current {@link UISessionCreationWizard}
     * @see #selectViewpoints(Set)
     */
    public UISessionCreationWizard selectViewpoint(final String viewpointToSelect) {
        return selectViewpoints(Collections.singleton(viewpointToSelect));
    }

    /**
     * "Select Viewpoints" operation (when creating a new local session, either
     * via wizard or drag and drop of model in local session view).
     * 
     * @param viewpointsToSelect
     *            Viewpoint to select to finish the wizard
     * @return Current {@link UISessionCreationWizard}
     */
    public UISessionCreationWizard selectViewpoints(final Set<String> viewpointsToSelect) {
        selectionDialog.selectViewpoint(viewpointsToSelect, Collections.<String> emptySet());
        return this;
    }

    /**
     * Create new local session with default name.
     * 
     * @return Current {@link UISessionCreationWizard}.
     */
    public UISessionCreationWizard withDefaultSessionName() {
        if (modelResource == null) {
            sessionName = UISessionCreationWizard.DEFAULT_SESSION_NAME;
        } else {
            final int dotPos = modelResource.getName().lastIndexOf('.');
            if (dotPos > -1) {
                sessionName = modelResource.getName().substring(0, dotPos);
            }
        }
        return this;
    }

    /**
     * With session name method.
     * 
     * @param name
     *            Session name to define.
     * @return Current {@link UISessionCreationWizard}.
     */
    public UISessionCreationWizard withSessionName(final String name) {
        this.sessionName = name;
        return this;
    }

}
