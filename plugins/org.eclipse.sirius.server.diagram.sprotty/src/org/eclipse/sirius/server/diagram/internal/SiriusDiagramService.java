/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo
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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sprotty.IDiagramServer;
import org.eclipse.sprotty.IDiagramServer.Provider;

/**
 * The {@link SiriusDiagramService} is used to create new instances of the {@link SiriusDiagramServer}.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramService implements HttpSessionListener, Provider {

	/**
	 * The prefix of the project part of the client identifier.
	 */
	private static final String PROJECT__PREFIX = "__PROJECT__"; //$NON-NLS-1$

	/**
	 * The prefix of the aird part of the client identifier.
	 */
	private static final String AIRD__PREFIX = "__AIRD__"; //$NON-NLS-1$

	/**
	 * The prefix of the representation part of the client identifier.
	 */
	private static final String REPRESENTATION__PREFIX = "__REPRESENTATION__"; //$NON-NLS-1$

	/**
	 * The prefix of the client id part of the client identifier.
	 */
	private static final String CLIENT_ID__PREFIX = "__CLIENT_ID__"; //$NON-NLS-1$

	/**
	 * The logger.
	 */
	private static Logger LOG = Logger.getLogger(SiriusDiagramService.class);

	/**
	 * The map of all the {@link SiriusDiagramServer} and their identifier.
	 */
	private Map<String, SiriusDiagramServer> diagramServers = new HashMap<>();

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.sprotty.IDiagramServer.Provider#getDiagramServer(java.lang.String)
	 */
	@Override
	public IDiagramServer getDiagramServer(String clientId) {
		synchronized (this.diagramServers) {
			Optional<SiriusDiagramServer> optionalSiriusDiagramServer = Optional.ofNullable(this.diagramServers.get(clientId));
			if (!optionalSiriusDiagramServer.isPresent()) {
				return this.createDiagramServer(clientId).orElse(null);
			}
			return optionalSiriusDiagramServer.orElse(null);
		}
	}

	/**
	 * Creates and registers the {@link SiriusDiagramServer} for the given client identifier.
	 *
	 * @param clientId
	 *            The client identifier
	 * @return The {@link SiriusDiagramServer} for the given client identifier or an empty optional if the identifier is
	 *         not relevant
	 */
	private Optional<SiriusDiagramServer> createDiagramServer(String clientId) {
		int projectPrefixIndex = clientId.indexOf(PROJECT__PREFIX);
		int airdPrefixIndex = clientId.indexOf(AIRD__PREFIX);
		int representationPrefixIndex = clientId.indexOf(REPRESENTATION__PREFIX);
		int clientIdPrefixIndex = clientId.indexOf(CLIENT_ID__PREFIX);

		String projectName = clientId.substring(projectPrefixIndex + PROJECT__PREFIX.length(), airdPrefixIndex);
		String airdFullPath = clientId.substring(airdPrefixIndex + AIRD__PREFIX.length(), representationPrefixIndex);
		String representationName = clientId.substring(representationPrefixIndex + REPRESENTATION__PREFIX.length(), clientIdPrefixIndex);

		Optional<Session> optionalSession = this.getSession(projectName, airdFullPath);
		Optional<DDiagram> optionalDDiagram = optionalSession.flatMap(session -> this.getDDiagramFromSession(session, representationName));
		if (optionalSession.isPresent() && optionalDDiagram.isPresent()) {
			Session session = optionalSession.get();
			DDiagram dDiagram = optionalDDiagram.get();

			SiriusDiagramServer siriusDiagramServer = new SiriusDiagramServer(session, dDiagram, clientId);
			this.diagramServers.put(clientId, siriusDiagramServer);
			return Optional.of(siriusDiagramServer);
		}
		return Optional.empty();
	}

	/**
	 * Returns the {@link Session} found for the given project name and aird path.
	 *
	 * @param projectName
	 *            The name of the project containing the aird
	 * @param airdFullPath
	 *            The path of the aird in the project
	 * @return The {@link Session} found or an empty optional
	 */
	private Optional<Session> getSession(String projectName, String airdFullPath) {
		Optional<String> optionalProjectName = Optional.ofNullable(projectName);
		Optional<String> optionalAird = Optional.ofNullable(airdFullPath);

		Optional<IProject> optionalProject = optionalProjectName.map(ResourcesPlugin.getWorkspace().getRoot()::getProject);
		Optional<IFile> optionalFile = optionalProject.flatMap(project -> optionalAird.map(Path::new).map(project::getFile));

		// @formatter:off
		return SessionManager.INSTANCE.getSessions().stream().filter(session -> {
			Resource sessionResource = session.getSessionResource();
			URI sessionResourceURI = sessionResource.getURI();

			Optional<URI> optionalAirdFileURI = optionalFile.filter(IFile::exists)
					.map(IFile::getFullPath)
					.map(IPath::toString)
					.map(fullPath -> URI.createPlatformResourceURI(fullPath, true));

			return optionalAirdFileURI.map(sessionResourceURI::equals).orElse(Boolean.FALSE).booleanValue();
		}).findFirst();
		// @formatter:on
	}

	/**
	 * Returns the {@link DDiagram} found for the given {@link Session} and the representation name.
	 *
	 * @param session
	 *            The {@link Session}
	 * @param representationName
	 *            The name of the {@link DDiagram}
	 * @return The {@link DDiagram} found or an empty optional
	 */
	private Optional<DDiagram> getDDiagramFromSession(Session session, String representationName) {
		Collection<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentations(session);
		// @formatter:off
		return representations.stream()
				.filter(DDiagram.class::isInstance)
				.map(DDiagram.class::cast)
				.filter(dDiagram -> {
					return Optional.ofNullable(representationName).orElse("").equals(dDiagram.getName()); //$NON-NLS-1$
				})
				.findFirst();
		// @formatter:on
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		LOG.info("Session created"); //$NON-NLS-1$
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		LOG.info("Session destroyed"); //$NON-NLS-1$
	}

}
