/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.tools.internal.util.GMFNotationUtilities;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EMFUtil;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.osgi.framework.Version;

/**
 *
 * This migration participant fix inconsistent GMF node style with Sirius node style.
 *
 * @author Séraphin Costa
 *
 */
public class NodeStyleMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * Migration version.
     */
    public static final Version MIGRATION_VERSION = new Version("15.2.0.202303281325"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    /**
     * Update the GMF label style to match Sirius label style
     *
     * @param target
     *            The GMF label style
     * @param source
     *            The Sirius label style reference
     */
    private void updateStyle(FontStyle target, BasicLabelStyle source) {
        boolean isBold = source.getLabelFormat().contains(FontFormat.BOLD_LITERAL);
        boolean isItalic = source.getLabelFormat().contains(FontFormat.ITALIC_LITERAL);
        boolean isUnderline = source.getLabelFormat().contains(FontFormat.UNDERLINE_LITERAL);
        boolean isStricke = source.getLabelFormat().contains(FontFormat.STRIKE_THROUGH_LITERAL);
        int labelSize = source.getLabelSize();
        int labelColor = source.getLabelColor().toInteger();

        target.setBold(isBold);
        target.setFontColor(labelColor);
        target.setFontHeight(labelSize);
        target.setItalic(isItalic);
        target.setUnderline(isUnderline);
        target.setStrikeThrough(isStricke);
    }

    /**
     * Update the GMF node to migrate and fix the style of the label
     */
    private void updateNode(View node) {
        EList<View> children = node.getChildren();
        Optional<View> labelOpt = children.stream() //
                .filter(GMFNotationUtilities::viewIsLabel) //
                .findAny();

        // 1. remove the style of GMF label
        labelOpt.ifPresent(label -> {
            label.getStyles().clear();
        });

        // 2. get GMF node style and Sirius node style
        Optional<FontStyle> nodeStyleOpt = node.getStyles().stream() //
                .filter(style -> style instanceof FontStyle) //
                .findAny();

        nodeStyleOpt.ifPresent(fontStyle -> {
            if (node.getElement() instanceof DNode dNode) {
                if (dNode.getStyle() instanceof BasicLabelStyle dLabelStyle) {
                    // 3. convert/apply Sirius node style to GMF node style
                    updateStyle(fontStyle, dLabelStyle);
                }
            }
        });
    }

    /**
     * Update the GMF diagram to migrate and fix the style of the label
     */
    private void migrateDiagram(Diagram diagram) {
        EMFUtil.<View> getTreeStream(diagram, view -> view.getChildren()) //
                .filter(GMFNotationUtilities::viewIsNode) //
                .forEach(this::updateNode);
    }

    /**
     * This method return the associated GMF diagram of a Sirius diagram
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
     * This method return all representations descriptors of a view.
     *
     * @param dView
     *            the Sirius viewpoint.
     * @return java stream of all representation descriptors of view.
     */
    private Stream<DRepresentationDescriptor> getRepresentationsDescriptors(DView dView) {
        return new DViewQuery(dView).getLoadedRepresentationsDescriptors().stream();
    }

    /**
     * This method is overridden to fix file with inconsistent GMF node style with Sirius node style.
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
