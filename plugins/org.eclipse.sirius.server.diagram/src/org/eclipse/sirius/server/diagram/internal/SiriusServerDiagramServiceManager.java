/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.server.diagram.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramMessage;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramService;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * The Sirius server diagram service manager is used to instantiate and dispose
 * the diagram services when necessary.
 *
 * @author sbegaudeau
 */
public class SiriusServerDiagramServiceManager {

    /**
     * The cache of the diagram service for a specific representation
     * identifier.
     */
    private Map<String, SiriusDiagramService> representation2services = new HashMap<>();

    /**
     * The cache of the representation identifier for a specific diagram
     * service.
     */
    private Map<SiriusDiagramService, String> service2representations = new HashMap<>();

    /**
     * The cache of session identifiers for a specific diagram service.
     */
    private Map<SiriusDiagramService, List<String>> service2identifiers = new HashMap<>();

    /**
     * The cache of the diagram service for a specific session identifier.
     */
    private Map<String, SiriusDiagramService> identifier2services = new HashMap<>();

    /**
     * Acquire an instance of a diagram service.
     *
     * @param identifier
     *            The identifier of the WebSocket session
     * @param projectName
     *            The name of the project
     * @param representationName
     *            The name of the representation
     * @param callback
     *            The callback used for the diagram service to send data back to
     *            the client
     * @return An instance of the diagram service or null, if the parameter do
     *         not match a valid diagram
     */
    public SiriusDiagramService acquire(String identifier, String projectName, String representationName, Consumer<SiriusDiagramMessage> callback) {
        synchronized (this.representation2services) {
            String representationId = projectName + representationName;
            SiriusDiagramService diagramService = this.representation2services.get(representationId);
            if (diagramService == null) {
                Optional<Session> optionalSession = this.getSession(projectName);
                Optional<DDiagram> optionalDDiagram = optionalSession.flatMap(session -> this.getDDiagramFromSession(session, representationName));
                if (optionalSession.isPresent() && optionalDDiagram.isPresent()) {
                    Session session = optionalSession.get();
                    DDiagram dDiagram = optionalDDiagram.get();

                    diagramService = new SiriusDiagramService(session, dDiagram, callback);
                    diagramService.initialize();

                    this.representation2services.put(representationId, diagramService);
                    this.service2representations.put(diagramService, representationId);
                    this.identifier2services.put(identifier, diagramService);
                    List<String> identifiers = this.service2identifiers.getOrDefault(diagramService, new ArrayList<>());
                    identifiers.add(identifier);
                    this.service2identifiers.put(diagramService, identifiers);
                }
            }
            return diagramService;
        }
    }

    /**
     * Returns the {@link Session} found for the given project name.
     *
     * @param projectName
     *            The name of the project containing the aird
     * @return The {@link Session} found or an empty optional
     */
    private Optional<Session> getSession(String projectName) {
        // @formatter:off
        Optional<IProject> optionalProject = Optional.of(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName));
        Optional<ModelingProject> optionalModelingProject = optionalProject.filter(ModelingProject::hasModelingProjectNature)
                .map(iProject -> ModelingProject.asModelingProject(iProject).get());
        return optionalModelingProject.map(modelingProject -> {
            Session session = modelingProject.getSession();
            if (session == null) {
                URI sessionResourceURI = modelingProject.getMainRepresentationsFileURI(new NullProgressMonitor()).get();
                session = SessionManager.INSTANCE.openSession(sessionResourceURI , new NullProgressMonitor(), SiriusPlugin.getDefault().getUiCallback());
            }
            return session;
        });
        // @formatter:on
    }

    /**
     * Returns the {@link DDiagram} found for the given {@link Session} and the
     * representation name.
     *
     * @param session
     *            The {@link Session}
     * @param representationName
     *            The name of the {@link DDiagram}
     * @return The {@link DDiagram} found or an empty optional
     */
    private Optional<DDiagram> getDDiagramFromSession(Session session, String representationName) {
        Collection<DRepresentationDescriptor> representations = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        // @formatter:off
        Optional<DDiagram> optionalDDiagram = representations.stream()
                .filter(repDesc->repDesc.getRepresentation() instanceof DDiagram)
                .filter(repDesc -> {
                    return Optional.ofNullable(representationName).orElse("").equals(repDesc.getName()); //$NON-NLS-1$
                })
                .map(repDesc->(DDiagram) repDesc.getRepresentation())
                .findFirst();
        // @formatter:on
        return optionalDDiagram;
    }

    /**
     * Release the diagram services used by the given session identifier.
     *
     * @param identifier
     *            The identifier of the session
     */
    public void release(String identifier) {
        synchronized (this.representation2services) {
            Optional<SiriusDiagramService> optionalDiagramService = Optional.ofNullable(this.identifier2services.remove(identifier));
            optionalDiagramService.ifPresent(diagramService -> {
                this.service2identifiers.remove(diagramService);
                String representationId = this.service2representations.remove(diagramService);
                this.representation2services.remove(representationId);

                diagramService.dispose();
            });
        }
    }
}
