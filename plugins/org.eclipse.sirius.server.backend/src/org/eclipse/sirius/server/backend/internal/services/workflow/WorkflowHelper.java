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
package org.eclipse.sirius.server.backend.internal.services.workflow;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.workflow.ActivityDescription;
import org.eclipse.sirius.workflow.PageDescription;
import org.eclipse.sirius.workflow.SectionDescription;
import org.eclipse.sirius.workflow.WorkflowDescription;

/**
 * Utility methods to manipulate Workflows and their elements.
 *
 * @author pcdavid
 *
 */
// CHECKSTYLE:OFF
public final class WorkflowHelper {
    private final Session session;

    public static WorkflowHelper on(Session session) {
        return new WorkflowHelper(Objects.requireNonNull(session));
    }

    private WorkflowHelper(Session session) {
        this.session = session;
    }

    /**
     * Finds all the workflow pages that apply to the given session. This may
     * include pages defined in different workflows, but pages that originate
     * from a given workflow are guaranteed to be contiguous.
     *
     * @return all the workflow pages that apply to the session.
     */
    public Stream<PageDescription> getPageDescriptions() {
        return getWorkflowDescriptions().flatMap(w -> w.getPages().stream());
    }

    /**
     * Returns the workflow descriptions from the given session.
     *
     * @return The workflow descriptions from the given session
     */
    public Stream<WorkflowDescription> getWorkflowDescriptions() {
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

    public Optional<PageDescription> findPageById(String pageIdentifier) {
        return findById(getPageDescriptions(), pageIdentifier);
    }

    public Optional<SectionDescription> findSectionById(String pageId, String sectionId) {
        return findPageById(pageId).flatMap(page -> findById(page.getSections(), sectionId));
    }

    public Optional<ActivityDescription> findActivityById(String pageId, String sectionId, String activityId) {
        return findSectionById(pageId, sectionId).flatMap(section -> findById(section.getActivities(), activityId));
    }

    private <T extends IdentifiedElement> Optional<T> findById(Collection<T> candidates, String id) {
        return findById(candidates.stream(), id);
    }

    private <T extends IdentifiedElement> Optional<T> findById(Stream<T> candidates, String id) {
        return candidates.filter(elt -> Objects.equals(elt.getName(), id)).findFirst();
    }

}
