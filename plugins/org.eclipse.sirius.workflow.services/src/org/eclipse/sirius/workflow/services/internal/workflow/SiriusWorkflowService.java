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
package org.eclipse.sirius.workflow.services.internal.workflow;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.workflow.ActivityDescription;
import org.eclipse.sirius.workflow.PageDescription;
import org.eclipse.sirius.workflow.SectionDescription;
import org.eclipse.sirius.workflow.WorkflowDescription;
import org.eclipse.sirius.workflow.services.api.workflow.ActionDto;
import org.eclipse.sirius.workflow.services.api.workflow.ISiriusWorkflowService;
import org.eclipse.sirius.workflow.services.api.workflow.PageDto;
import org.eclipse.sirius.workflow.services.api.workflow.SectionDto;
import org.eclipse.sirius.workflow.services.api.workflow.SimplePageDto;
import org.eclipse.sirius.workflow.services.api.workflow.WorkflowDto;

/**
 * The implementation of the Sirius workflow service.
 *
 * @author sbegaudeau
 */
public class SiriusWorkflowService implements ISiriusWorkflowService {

	@Override
	public Optional<WorkflowDto> getWorkflow(String sessionId) {
		URI sessionResourceURI = URI.createURI(sessionId);
		return Optional.ofNullable(SessionManager.INSTANCE.getExistingSession(sessionResourceURI)).flatMap(this::computeWorkflow);
	}

	/**
	 * Computes the workflow of the given session.
	 *
	 * @param session
	 *            The session
	 * @return The workflow computed form the session
	 */
	private Optional<WorkflowDto> computeWorkflow(Session session) {
		// @formatter:off
		List<SimplePageDto> pageDtos = this.getWorkflowDescriptions(session)
				.map(WorkflowDescription::getPages)
				.flatMap(Collection::stream)
				.flatMap(pageDescription -> this.computeSimplePage(session, pageDescription))
				.collect(Collectors.toList());
		// @formatter:on
		if (pageDtos.size() > 0) {
			return Optional.of(new WorkflowDto(pageDtos));
		}
		return Optional.empty();
	}

	/**
	 * Returns the workflow descriptions from the given session.
	 *
	 * @param session
	 *            The session
	 * @return The workflow descriptions from the given session
	 */
	private Stream<WorkflowDescription> getWorkflowDescriptions(Session session) {
		// @formatter:off
		return session.getSelectedViewpoints(true).stream()
				.map(viewpoint -> new EObjectQuery(viewpoint).getFirstAncestorOfType(DescriptionPackage.Literals.GROUP))
				.filter(Option::some)
				.map(Option::get)
				.filter(Group.class::isInstance)
				.map(Group.class::cast)
				.flatMap(group -> group.getExtensions().stream())
				.filter(WorkflowDescription.class::isInstance)
				.map(WorkflowDescription.class::cast);
		// @formatter:on
	}

	/**
	 * Computes a pageDto for the given pageDescription.
	 *
	 * @param session
	 *            the session.
	 * @param pageDescription
	 *            The page description
	 * @return The simplePageDto computed
	 */
	private Stream<SimplePageDto> computeSimplePage(Session session, PageDescription pageDescription) {
		IInterpreter itp = session.getInterpreter();
		EObject self = ((DAnalysisSessionImpl) session).getAnalyses().iterator().next();
		try {
			String title = itp.evaluateString(self, pageDescription.getTitleExpression());
			String description = itp.evaluateString(self, pageDescription.getDescriptionExpression());
			return Stream.of(new SimplePageDto(pageDescription.getName(), title, description));
		} catch (EvaluationException e) {
			return Stream.empty();
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.obeonetwork.jarvis.server.sirius.api.services.workflow.IJarvisSiriusWorkflowService#getPage(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Optional<PageDto> getPage(String sessionId, String pageId) {
		// @formatter:off
		Optional<PageDto> optionalPageDto = SessionManager.INSTANCE.getSessions().stream()
			.filter(session -> sessionId.equals(session.getID()))
			.findFirst()
			.flatMap(session -> {
				return this.getWorkflowDescriptions(session)
					.map(WorkflowDescription::getPages)
					.flatMap(Collection::stream)
					.filter(pageDescription -> Objects.equals(pageId, pageDescription.getName()))
					.findFirst()
					.flatMap(pageDescription -> this.convert(session, pageDescription));
			});
		// @formatter:on
		return optionalPageDto;
	}

	/**
	 * Converts the given description into an optional containing a page dto or an empty page dto.
	 *
	 * @param session
	 *            The Sirius session
	 * @param pageDescription
	 *            The page description
	 * @return An optional containing the converted page dto
	 */
	private Optional<PageDto> convert(Session session, PageDescription pageDescription) {
		IInterpreter itp = session.getInterpreter();
		EObject self = ((DAnalysisSessionImpl) session).getAnalyses().iterator().next();
		try {
			String pageId = pageDescription.getName();
			String label = itp.evaluateString(self, pageDescription.getTitleExpression());
			String description = itp.evaluateString(self, pageDescription.getDescriptionExpression());

			Optional<String> optionalPreviousPageId = this.getPreviousPageId(pageDescription);
			Optional<String> optionalNextPageId = this.getNextPageId(pageDescription);

			// @formatter:off
			List<SectionDto> sectionDtos = pageDescription.getSections().stream()
				.flatMap(sectionDescription -> this.convert(session, sectionDescription))
				.collect(Collectors.toList());
			// @formatter:on

			return Optional
					.of(new PageDto(pageId, label, description, optionalPreviousPageId.orElse(null), optionalNextPageId.orElse(null), sectionDtos));
		} catch (EvaluationException e) {
			return Optional.empty();
		}
	}

	/**
	 * Returns an optional containing the next page identifier or an empty optional if it is the last page.
	 *
	 * @param pageDescription
	 *            The page description
	 * @return An optional containing the next page identifier or an empty optional if it is the last page
	 */
	private Optional<String> getNextPageId(PageDescription pageDescription) {
		WorkflowDescription workflow = (WorkflowDescription) pageDescription.eContainer();
		int index = workflow.getPages().indexOf(pageDescription);
		if (index + 1 < workflow.getPages().size()) {
			return Optional.of(workflow.getPages().get(index + 1).getName());
		}
		return Optional.empty();
	}

	/**
	 * Returns an optional containing the previous page identifier or an empty optional if it is the first page.
	 *
	 * @param pageDescription
	 *            The page description
	 * @return An optional containing the previous page identifier or an empty optional if it is the first page
	 */
	private Optional<String> getPreviousPageId(PageDescription pageDescription) {
		WorkflowDescription workflow = (WorkflowDescription) pageDescription.eContainer();
		int index = workflow.getPages().indexOf(pageDescription);
		if (index > 0) {
			return Optional.of(workflow.getPages().get(index - 1).getName());
		}
		return Optional.empty();
	}

	/**
	 * Converts the given section description into a stream with one section dto or an empty stream if an error occurs.
	 *
	 * @param session
	 *            The Sirius session
	 * @param sectionDescription
	 *            The section description
	 * @return A stream containing the converted section description
	 */
	private Stream<SectionDto> convert(Session session, SectionDescription sectionDescription) {
		IInterpreter itp = session.getInterpreter();
		EObject self = ((DAnalysisSessionImpl) session).getAnalyses().iterator().next();
		try {
			String label = itp.evaluateString(self, sectionDescription.getTitleExpression());

			// @formatter:off
			List<ActionDto> actions = sectionDescription.getActivities().stream()
					.flatMap(activityDescription -> this.convert(session, activityDescription))
					.collect(Collectors.toList());
			// @formatter:on

			return Stream.of(new SectionDto(label, actions));
		} catch (EvaluationException e) {
			return Stream.empty();
		}
	}

	/**
	 * Converts the given activity description into stream containing one action dto or an empty stream if an error
	 * occurs.
	 *
	 * @param session
	 *            The Sirius session
	 * @param activityDescription
	 *            The activity description
	 * @return A stream containing the converted action dto
	 */
	private Stream<ActionDto> convert(Session session, ActivityDescription activityDescription) {
		IInterpreter itp = session.getInterpreter();
		EObject self = ((DAnalysisSessionImpl) session).getAnalyses().iterator().next();
		try {
			String actionId = activityDescription.getName();
			String label = itp.evaluateString(self, activityDescription.getLabelExpression());
			return Stream.of(new ActionDto(actionId, label));
		} catch (EvaluationException e) {
			return Stream.empty();
		}
	}
}
