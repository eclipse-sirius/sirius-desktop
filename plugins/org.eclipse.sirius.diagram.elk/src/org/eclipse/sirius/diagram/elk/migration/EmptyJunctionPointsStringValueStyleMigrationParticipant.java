/*******************************************************************************
 * Copyright (c) 2024 Obeo
 * 
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
package org.eclipse.sirius.diagram.elk.migration;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.StringValueStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.elk.GmfLayoutCommand;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 *
 * This migration participant fixes the GMF edges that contain an empty "junctionPoints" StringValueStyle. These empty
 * "junctionPoints" StringValueStyle were created due to a bug in ELK./Sirius integration. This migration participant
 * simply removes the empty "junctionPoints" StringValueStyle.
 *
 * @author Laurent Redor
 *
 */
public class EmptyJunctionPointsStringValueStyleMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * Migration version.
     */
    public static final Version MIGRATION_VERSION = new Version("15.4.3.202406261640"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    /**
     * Update the styles of this <code>edge</code> if it contains empty "junctionPoints" StringValueStyle. In this case,
     * this style is removed.
     * 
     * @param edge
     *            the GMF edge
     */
    private void updateEdge(View edge) {
        StringValueStyle stringValueStyle = (StringValueStyle) edge.getStyle(NotationPackage.Literals.STRING_VALUE_STYLE);
        if (stringValueStyle != null && GmfLayoutCommand.JUNCTION_POINTS_STYLE_NAME.equals(stringValueStyle.getName()) && "()".equals(stringValueStyle.getStringValue())) { //$NON-NLS-1$
            edge.getStyles().remove(stringValueStyle);
        }
    }

    /**
     * Update the GMF diagram to migrate all invalid edges.
     * 
     * @param diagram
     *            the GMF diagram
     */
    private void migrateDiagram(Diagram diagram) {
        List<Edge> edges = diagram.getEdges();
        edges.stream().forEach(edge -> updateEdge(edge));
    }

    /**
     * This method returns the associated GMF diagram of a Sirius diagram.
     *
     * @param dDiagram
     *            the Sirius diagram
     * @return the associated GMF diagram if present
     */
    private Optional<Diagram> getGMFDiagram(DDiagram dDiagram) {
        DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(dDiagram);
        return Optional.ofNullable(query.getAssociatedGMFDiagram().get());
    }

    /**
     * This method returns all representations descriptors of a view.
     *
     * @param dView
     *            the Sirius viewpoint.
     * @return java stream of all representation descriptors of the <code>dView</code>.
     */
    private Stream<DRepresentationDescriptor> getRepresentationsDescriptors(DView dView) {
        return new DViewQuery(dView).getLoadedRepresentationsDescriptors().stream();
    }

    /**
     * This method is overridden to fix file with inconsistent GMF edges, ie having an empty "junctionPoints"
     * StringValueStyle.
     */
    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) { // loadedVersion < MIGRATION_VERSION
            // for each diagrams of each viewpoints
            dAnalysis.getOwnedViews().stream() //
                    .flatMap(this::getRepresentationsDescriptors) //
                    .map(descriptor -> descriptor.getRepresentation()) //
                    .filter(representation -> representation instanceof DDiagram) //
                    .map(representation -> (DDiagram) representation) //
                    .map(this::getGMFDiagram) //
                    .flatMap(Optional::stream) //
                    .forEach(this::migrateDiagram);
        }
    }
}
