/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.ui.tools.internal.views.common.item;

import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * Display the semantic models associated to the session but not in the current project. This implementation initialize
 * the session from the constructor. It is not dynamically updated compared to the implementation
 * {@link ProjectDependenciesItemImpl}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class NoDynamicProjectDependencies extends AbstractProjectDependenciesItem {

    /**
     * The session from which external dependencies will be loaded.
     */
    private Session session;

    /**
     * Initialize new instance.
     * 
     * @param theProject
     *            the project containing this item.
     * @param theSession
     *            the session associated to this item.
     */
    public NoDynamicProjectDependencies(IProject theProject, Session theSession) {
        super(theProject);
        this.session = theSession;
    }

    /**
     * Return the session of the modeling project.
     *
     * @return the session of the modeling project
     */
    @Override
    public Option<Session> getSession() {
        if (session != null) {
            return Options.newSome(session);
        }
        return Options.newNone();
    }

}
