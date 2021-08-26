/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.migration;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.helper.filter.FilterService;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.internal.Messages;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * This migration participant sorts the already activated filters if it is not already done. They are now sorted when
 * they are added to the list.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ActivatedFilterSortingMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * This version corresponds to the sorting of the activated filters when they are added to the list.
     */
    public static final Version MIGRATION_VERSION_FILTER_SORTING = new Version("14.3.0.201909031200"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION_FILTER_SORTING;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION_FILTER_SORTING) < 0) {
            int updateDiagrams = 0;
            Collection<DDiagram> diagramWithActivatedfilters = getDiagramWithActivatedFilters(dAnalysis);
            for (DDiagram dDiagram : diagramWithActivatedfilters) {
                boolean migrationOccured = sortActivatedFilters(dDiagram);
                if (migrationOccured) {
                    updateDiagrams++;
                }
            }

            if (updateDiagrams > 0) {
                String migrationMessage = MessageFormat.format(Messages.ActivatedFilterSortingMigrationParticipant_updatedDiagrams, updateDiagrams);
                DiagramPlugin.getDefault().logInfo(migrationMessage);
            }
        }
    }

    private boolean sortActivatedFilters(DDiagram diagram) {
        EList<FilterDescription> activatedFilter = diagram.getActivatedFilters();
        List<FilterDescription> sortedFilters = FilterService.sortFilters(activatedFilter);
        if (!Iterables.elementsEqual(sortedFilters, activatedFilter)) {
            activatedFilter.clear();
            activatedFilter.addAll(sortedFilters);
            return true;
        }
        return false;
    }

    private Collection<DDiagram> getDiagramWithActivatedFilters(DAnalysis dAnalysis) {
        Stream<DRepresentation> representationStream = dAnalysis.getOwnedViews().stream().flatMap(view -> view.getOwnedRepresentationDescriptors().stream()).map(desc -> desc.getRepresentation());
        return representationStream.filter(DDiagram.class::isInstance).map(DDiagram.class::cast).filter(diagram -> !diagram.getActivatedFilters().isEmpty()).collect(Collectors.toList());
    }

}
