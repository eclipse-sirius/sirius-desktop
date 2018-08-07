/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.graphql.internal;

import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Utility functions to manipulate optionals.
 * 
 * @author sbegaudeau
 */
public final class SiriusGraphQLOptionalUtils {

    /**
     * The constructor.
     */
    private SiriusGraphQLOptionalUtils() {
        // Prevent instantiation
    }

    /**
     * Returns an optional containing the project computed from the given object or an empty optional if it could not be
     * found.
     * 
     * @param object
     *            The name of the project as an Object
     * @return An optional with the project found or an empty optional
     */
    public static Optional<IProject> projectFromName(Object object) {
        // @formatter:off
        return Optional.ofNullable(object)
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(ResourcesPlugin.getWorkspace().getRoot()::getProject);
        // @formatter:on
    }

    /**
     * Returns an optional containing the viewpoint computed from the given object or an empty optional if it could not
     * be found.
     * 
     * @param object
     *            The identifier of the viewpoint as an Object
     * @return An optional with the viewpoint found or an empty optional
     */
    public static Optional<Viewpoint> viewpointFromIdentifier(Object object) {
        // @formatter:off
        return Optional.ofNullable(object)
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .flatMap(viewpointIdentifier -> {
                    return ViewpointRegistry.getInstance().getViewpoints().stream()
                            .filter(viewpoint -> viewpointIdentifier.equals(viewpoint.getName()))
                            .findFirst();
                });
        // @formatter:on
    }

    /**
     * Returns an optional resource representing the given iFile in the session.
     * 
     * @param session
     *            The Sirius session
     * @param iFile
     *            The file
     * @return An optional resource or an empty optional if the file is not a semantic resource from the given session
     */
    public static Optional<Resource> toResource(Session session, IFile iFile) {
        ResourceSet resourceSet = session.getTransactionalEditingDomain().getResourceSet();
        String path = iFile.getFullPath().toString();
        URI uri = URI.createPlatformResourceURI(path, true);

        return Optional.ofNullable(resourceSet.getResource(uri, true));
    }

    /**
     * Returns an optional with the modeling project for the given IProject.
     * 
     * @param iProject
     *            The project
     * @return An optional with the modeling project if the given IProject is indeed a modeling project, an empty
     *         optional otherwise
     */
    public static Optional<ModelingProject> toModelingProject(IProject iProject) {
        // @formatter:off
        return Optional.of(iProject).filter(ModelingProject::hasModelingProjectNature)
                .map(project -> ModelingProject.asModelingProject(project).get());
        // @formatter:on
    }

    /**
     * Returns an optional with the session of the given project.
     * 
     * @param iProject
     *            The project
     * @return An optional with the session of the given project or an empty optional if the session could not be found
     *         or created
     */
    public static Optional<Session> toSession(IProject iProject) {
        // @formatter:off
        return Optional.of(iProject).flatMap(SiriusGraphQLOptionalUtils::toModelingProject)
                .flatMap(SiriusGraphQLOptionalUtils::toSession);
        // @formatter:on
    }

    /**
     * Returns an optional with the session of the given modeling project.
     * 
     * @param modelingProject
     *            The modeling project
     * @return An optional with the session of the given modeling project or an empty optional if the session could not
     *         be found or created
     */
    public static Optional<Session> toSession(ModelingProject modelingProject) {
        // @formatter:off
        return Optional.of(modelingProject).map(project -> {
            return Optional.ofNullable(project.getSession()).orElseGet(() -> {
                URI sessionResourceURI = project.getMainRepresentationsFileURI(new NullProgressMonitor()).get();
                UICallBack uiCallback = SiriusPlugin.getDefault().getUiCallback();
                return SessionManager.INSTANCE.openSession(sessionResourceURI, new NullProgressMonitor(), uiCallback);
            });
        });
        // @formatter:on
    }
}
