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
package org.eclipse.sirius.business.internal.query;

import org.eclipse.core.internal.resources.ProjectInfo;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link IResourceDelta} as a starting point.
 * 
 * @author lredor
 */
public class ResourceDeltaQuery {

    private IResourceDelta resourceDelta;

    /**
     * Create a new query.
     * 
     * @param resourceDelta
     *            the resource delta to query.
     */
    public ResourceDeltaQuery(IResourceDelta resourceDelta) {
        this.resourceDelta = resourceDelta;
    }

    /**
     * Check if the modeling nature has been added in this resource delta.
     * 
     * @return true if the nature has been added, false otherwise.
     */
    public boolean hasModelingNatureAdded() {
        return !hasModelingNature(getOldProjectDescription()) && hasModelingNature(getNewProjectDescription());
    }

    /**
     * Check if the modeling nature has been removed in this resource delta.
     * 
     * @return true if the nature has been removed, false otherwise.
     */
    public boolean hasModelingNatureRemoved() {
        return hasModelingNature(getOldProjectDescription()) && !hasModelingNature(getNewProjectDescription());
    }
    
    private boolean hasModelingNature(Option<IProjectDescription> project) {
        return project.some() && project.get().hasNature(ModelingProject.NATURE_ID);
    }
    
    private Option<IProjectDescription> getOldProjectDescription() {
        return getProjectDescription(ReflectionHelper.getFieldValueWithoutException(resourceDelta, "oldInfo")); //$NON-NLS-1$
    }

    private Option<IProjectDescription> getNewProjectDescription() {
        return getProjectDescription(ReflectionHelper.getFieldValueWithoutException(resourceDelta, "newInfo")); //$NON-NLS-1$
    }

    @SuppressWarnings("restriction")
    private Option<IProjectDescription> getProjectDescription(Option<Object> info) {
        if (info.some() && info.get() instanceof ProjectInfo) {
            IProjectDescription oldProjectDescription = ((ProjectInfo) info.get()).getDescription();
            return Options.fromNullable(oldProjectDescription);
        } else {
            return Options.newNone();
        }
    }

}
