/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.item;

import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * Display the semantic models associated to the session but not in the current project.
 *
 * @author mchauvin
 */
public class ProjectDependenciesItemImpl extends AbstractProjectDependenciesItem {
    /**
     * Current project, in which the session should be.
     */
    private final ModelingProject modelingProj;

    /**
     * Construct a new instance.
     *
     * @param project
     *            the project
     */
    public ProjectDependenciesItemImpl(ModelingProject project) {
        super(project.getProject());
        this.modelingProj = project;
    }

    /**
     * Return the session of the modeling project.
     *
     * @return the session of the modeling project
     */
    @Override
    public Option<Session> getSession() {
        if (modelingProj != null) {
            return Options.newSome(modelingProj.getSession());
        }
        return Options.newNone();
    }

}
