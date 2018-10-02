/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.business;

/**
 * Class grouping every step of the workflow used during a session creation
 * operation with the wizard.
 * 
 * @author cbrun
 * 
 */
public class UISessionCreationWizardFlow {
    /**
     * Wizard page to decide to go with a new empty session, a session picking a
     * semantic resource or a session using the already selected semantic
     * resource.
     * 
     * @author cbrun
     * 
     */
    public interface SessionChoice {
        /**
         * Decide to create an empty session.
         * 
         * @return next step.
         */
        AirdPickingChoice emptySession();

        /**
         * Decide to pick a semantic resource.
         * 
         * @return the next step.
         */
        SemanticModelPage fromSemanticResource();

        /**
         * decide to use the workspace selected semantic resource.
         * 
         * @return the next step.
         */
        AirdPickingChoice fromAlreadySelectedSemanticResource();

        /**
         * Configure this {@link SessionChoice} to create a session resource in
         * the uiProject.
         * 
         * @param uiProject
         *            the ui wrapper of the project where the session resource
         *            should be created.
         * 
         * @return self
         */
        SessionChoice in(UIProject uiProject);

    }

    /**
     * Wizard page to pick a semantic resource.
     * 
     * @author cbrun
     * 
     */
    public interface SemanticModelPage {
        /**
         * Select a semantic resource.
         * 
         * @param res
         *            semantic resource to pick.
         * @return the next step.
         */
        AirdPickingChoice select(UIResource res);

    }

    /**
     * Wizard page to pick an .aird resource.
     * 
     * @author cbrun
     * 
     */
    public interface AirdPickingChoice {
        /**
         * Select an Aird resource.
         * 
         * @param res
         *            aird resource to select in the wizard.
         * @return the next step.
         */
        WizardOk select(UIResource res);

        /**
         * consider the .aird file is the default one.
         * 
         * @return the next step considering the .aird file will be the default
         *         one.
         */
        WizardOk withDefaultSessionName();

        /**
         * consider the .aird file is the default one.
         * 
         * @return the created session wrapped in {@link UILocalSession}.
         */
        UILocalSession withDefaultSessionNameWithoutViewpointSelection();
    }

    /**
     * The wizard is ok and the user might press finish.
     * 
     * @author cbrun
     * 
     */
    public interface WizardOk {
        /**
         * end the wizard.
         * 
         * @return the next step.
         */
        VpSelectionAfterWizard finish();
    }

    /**
     * The viewpoints selection window poping up after the session creation
     * wizard.
     * 
     * @author cbrun
     * 
     */
    public interface VpSelectionAfterWizard {
        /**
         * Select no viewpoint.
         * 
         * @return the next step.
         */
        UILocalSession selectNoViewpoint();

        /**
         * Select some viewpoints.
         * 
         * @param vps
         *            names of the viewpoints to select.
         * @return the next step.
         */
        UILocalSession selectViewpoints(String... vps);

    }
}
