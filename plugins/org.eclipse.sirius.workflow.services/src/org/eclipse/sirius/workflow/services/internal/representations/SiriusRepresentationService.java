/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.workflow.services.internal.representations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationDescriptionItemImpl;
import org.eclipse.sirius.ui.tools.internal.wizards.CreateRepresentationWizard;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.workflow.services.api.SiriusServicesUtils;
import org.eclipse.sirius.workflow.services.api.representations.CreateRepresentationDto;
import org.eclipse.sirius.workflow.services.api.representations.ISiriusRepresentationService;
import org.eclipse.sirius.workflow.services.api.representations.OpenRepresentationDto;
import org.eclipse.sirius.workflow.services.api.representations.RepresentationDto;
import org.eclipse.sirius.workflow.services.api.representations.RepresentationsDto;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * The implementation of the Sirius service for representations.
 *
 * @author sbegaudeau
 */
public class SiriusRepresentationService implements ISiriusRepresentationService {

	@Override
	public RepresentationsDto getRepresentations(String sessionId) {
		return this.findSessionById(sessionId).map(session -> {
			List<RepresentationDto> representations = DialectManager.INSTANCE.getAllRepresentationDescriptors(session).stream().map(descriptor -> {
				URI uri = EcoreUtil.getURI(descriptor);
				return new RepresentationDto(SiriusServicesUtils.encode(uri.toString()), descriptor.getName());
			}).collect(Collectors.toList());
			return new RepresentationsDto(representations);
		}).orElse(new RepresentationsDto(new ArrayList<>()));
	}

	/**
	 * Returns the session with the identifier matching the given session identifier.
	 *
	 * @param sessionId
	 *            The encoded session identifier
	 * @return An optional with the session found or an empty optional if no matching session has been found
	 */
	private Optional<Session> findSessionById(String sessionId) {
		// @formatter:off
		return SessionManager.INSTANCE.getSessions().stream()
				.filter(session -> sessionId.equals(session.getID()))
				.findFirst();
		// @formatter:on
	}

	@Override
	public Optional<RepresentationDto> createRepresentation(String sessionId, CreateRepresentationDto createRepresentationDto) {
		// @formatter:off
		return this.findSessionById(sessionId).flatMap(session -> {
			return this.findRepresentationDescription(session, createRepresentationDto.getRepresentationId()).flatMap(representationDescription -> {
			    // return DialectManager.INSTANCE.createRepresentation(name, semantic, representationDescription, session, null);
				RepresentationDescriptionItemImpl item = new RepresentationDescriptionItemImpl(session, representationDescription, null);
				CreateRepresentationWizard wizard = new CreateRepresentationWizard(session, item);
				wizard.init();

				Display display = PlatformUI.getWorkbench().getDisplay();
				display.syncExec(() -> {
					final WizardDialog dialog = new WizardDialog(display.getActiveShell(), wizard);
					dialog.setMinimumPageSize(CreateRepresentationWizard.MIN_PAGE_WIDTH, CreateRepresentationWizard.MIN_PAGE_HEIGHT);
					dialog.create();
					dialog.getShell().setText("Create Representation"); //$NON-NLS-1$
					dialog.open();
				});
				return Optional.empty();
			});
		});
		// @formatter:on
	}

	/**
	 * Locate a {@link RepresentationDescription} in a session's enabled Viewpoints from its identifier.
	 *
	 * @param session
	 *            the session.
	 * @param representationDescriptionId
	 *            the identified of the {@link RepresentationDescription} to find.
	 * @return the {@link RepresentationDescription}, if it could be found.
	 */
	private Optional<RepresentationDescription> findRepresentationDescription(Session session, String representationDescriptionId) {
		// @formatter:off
		return session.getSelectedViewpoints(true).stream()
				.flatMap(viewpoint -> viewpoint.getOwnedRepresentations().stream())
				.filter(representationDescription -> representationDescriptionId.equals(representationDescription.getName()))
				.findFirst();
		// @formatter:on
	}

	@Override
	public Optional<RepresentationDto> openRepresentation(String sessionId, OpenRepresentationDto openRepresentationDto) {
		return this.findSessionById(sessionId).flatMap(session -> {
			String representationDescriptionId = SiriusServicesUtils.decode(openRepresentationDto.getRepresentationId());
			ResourceSet resourceSet = session.getTransactionalEditingDomain().getResourceSet();
			DRepresentationDescriptor representationDescriptor = (DRepresentationDescriptor) resourceSet
					.getEObject(URI.createURI(representationDescriptionId), false);
			DRepresentation representation = representationDescriptor.getRepresentation();

			PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
				DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
			});

			return Optional.empty();
		});
	}

}
