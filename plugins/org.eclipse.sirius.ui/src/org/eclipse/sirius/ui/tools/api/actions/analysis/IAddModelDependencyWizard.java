/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.actions.analysis;

import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.INewWizard;

/**
 * An interface representing a wizard that allows to add semantic resources to a
 * {@link Session}.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public interface IAddModelDependencyWizard extends INewWizard {

    /**
     * Indicates if this wizard can be apply on the given sessions.
     * 
     * @param sessions
     *            the candidate sessions for this wizard
     * @return true if this wizard can be applied on the given sessions, false
     *         otherwise (default wizard will be used).
     */
    boolean canApply(Collection<Session> sessions);

    /**
     * Set the available sessions.
     * 
     * @param availableSessions
     *            the available sessions
     */
    void setSessions(List<Session> availableSessions);

    /**
     * Returns the title to use for this wizard.
     * 
     * @return the title to use for this wizard
     */
    String getWizardTitle();
}
