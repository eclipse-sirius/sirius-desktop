/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.migration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramInternalQuery;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.osgi.framework.Version;

/**
 * Migration participant which optimize computed {@link StyleDescription}
 * storage in a {@link DDiagram} by removing duplicates.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class ComputedStyleDescriptionCachePackingFileMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /** The VP version for which this migration is added. */
    public static final Version MIGRATION_VERSION = new Version("10.0.0.201502231700"); //$NON-NLS-1$

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        super.postLoad(dAnalysis, loadedVersion);
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            for (DView dView : dAnalysis.getOwnedViews()) {
                for (DRepresentation dRepresentation : dView.getOwnedRepresentations()) {
                    if (dRepresentation instanceof DDiagram) {
                        packCustomStyleDescriptionRegistry((DDiagram) dRepresentation);
                    }
                }
            }
        }
    }

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    /**
     * Optimize a DDiagram by keeping only unique computed StyleDescriptions and
     * updating the styles accordingly.
     * 
     * @param dDiagram
     *            the diagram to optimize.
     */
    private void packCustomStyleDescriptionRegistry(DDiagram dDiagram) {
        ComputedStyleDescriptionRegistry registry = new DDiagramInternalQuery(dDiagram).getComputedStyleDescriptionRegistry(false);
        if (registry != null) {
            Collection<StyleDescription> computedStyleDescriptions = registry.getComputedStyleDescriptions();
            Collection<StyleDescription> packedList = new ArrayList<StyleDescription>();
            for (Style style : new DDiagramInternalQuery(dDiagram).getAllStyles()) {
                StyleDescription usedDescription = style.getDescription();
                if (usedDescription != null && computedStyleDescriptions.contains(usedDescription)) {
                    StyleDescription equivalent = null;
                    Iterator<StyleDescription> it = packedList.iterator();
                    while (it.hasNext() && equivalent == null) {
                        StyleDescription cur = it.next();
                        if (EcoreUtil.equals(usedDescription, cur)) {
                            equivalent = cur;
                        }
                    }

                    if (equivalent != null) {
                        style.setDescription(equivalent);
                    } else {
                        packedList.add(usedDescription);
                    }
                }
            }
            if (!packedList.isEmpty()) {
                computedStyleDescriptions.retainAll(packedList);
            }
        }
    }

}
