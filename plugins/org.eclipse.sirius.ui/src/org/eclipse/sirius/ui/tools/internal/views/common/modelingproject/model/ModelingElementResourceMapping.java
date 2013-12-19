/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.resources.mapping.ResourceMappingContext;
import org.eclipse.core.resources.mapping.ResourceTraversal;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.sirius.business.api.modelingproject.IModelingElement;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;

/**
 * An abstract super class to describe mappings from a ModelingProject element
 * to a set of resources. The class also provides factory methods to create
 * resource mappings.
 * 
 * Currently, only works for ModelingProject. But could be enhance for more
 * modeling element. See
 * {@link org.eclipse.jdt.internal.corext.util.JavaElementResourceMapping} for
 * example.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public abstract class ModelingElementResourceMapping extends ResourceMapping {

    /**
     * Default constructor.
     */
    protected ModelingElementResourceMapping() {
    }

    /**
     * Return the corresponding modeling element of the current element, null if
     * is is not a modeling projet.
     * 
     * @return the corresponding modeling element of the current element, null
     *         if is is not a modeling projet.
     */
    public IModelingElement getModelingElement() {
        Object o = getModelObject();
        if (o instanceof IModelingElement)
            return (IModelingElement) o;
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.mapping.ResourceMapping#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof ModelingElementResourceMapping))
            return false;
        return getModelingElement().equals(((ModelingElementResourceMapping) obj).getModelingElement());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.mapping.ResourceMapping#hashCode()
     */
    public int hashCode() {
        IModelingElement modelingElement = getModelingElement();
        if (modelingElement == null)
            return super.hashCode();

        return modelingElement.hashCode();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.mapping.ResourceMapping#getModelProviderId()
     */
    @Override
    public String getModelProviderId() {
        return ModelingModelProvider.MODELING_MODEL_PROVIDER_ID;
    }

    // ---- the factory code
    // ---------------------------------------------------------------

    /**
     * Create a ResourceMapping for the project.
     * 
     * @param project
     *            the concerned modeling project
     * @return a new ResourceMapping for this ModelingProject
     */
    public static ResourceMapping create(final ModelingProject project) {
        return new ModelingProjectResourceMapping(project);
    }

    /**
     * A specific {@link ModelingElementResourceMapping} for
     * {@link ModelingProject}.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private static final class ModelingProjectResourceMapping extends ModelingElementResourceMapping {
        private final ModelingProject fProject;

        private ModelingProjectResourceMapping(ModelingProject project) {
            Assert.isNotNull(project);
            fProject = project;
        }

        public Object getModelObject() {
            return fProject;
        }

        public IProject[] getProjects() {
            return new IProject[] { fProject.getProject() };
        }

        public ResourceTraversal[] getTraversals(ResourceMappingContext context, IProgressMonitor monitor) throws CoreException {
            return new ResourceTraversal[] { new ResourceTraversal(new IResource[] { fProject.getProject() }, IResource.DEPTH_INFINITE, 0) };
        }
    }
}
