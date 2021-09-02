/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * A migration participant following the bug #539550 to fix all Note, Text, Note Attachment whose element has not been
 * set to null.
 * 
 * See also {@link #needsElementUpdateToExplicitNull(View)} and {@link #updateElementToExplicitNull(View)}
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 *
 */
public class ViewWithNullElementMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The VP version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("14.1.0.2018092712000"); //$NON-NLS-1$

    /**
     * The name of the feature DView.ownedRepresentations which has been deleted.
     */
    public static final String DVIEW_OWNED_REPRESENTATIONS_UNKNOWN_FEATURE = "ownedRepresentations"; //$NON-NLS-1$

    /**
     * The label of the feature name of a DRepresentation when serialized.
     */
    protected static final String FEATURE_NAME_LABEL = "name"; //$NON-NLS-1$

    /**
     * A map associating {@link DRepresentation} to their name.
     */
    protected Map<DRepresentation, String> representationToNameMap = new HashMap<>();

    /**
     * True if a corrupted note attachment has been removed.
     */
    private boolean repairOccurred;

    /**
     * Use to log migration result.
     */
    private StringBuilder sb;

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            for (DView dView : dAnalysis.getOwnedViews()) {
                for (DDiagram dDiagram : Iterables.filter(new DViewQuery(dView).getLoadedRepresentations(), DDiagram.class)) {
                    repairViewWihtoutElement(dDiagram);
                }
            }
            super.postLoad(dAnalysis, loadedVersion);
        }
    }

    private void repairViewWihtoutElement(DDiagram dDiagram) {
        DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(dDiagram);
        if (diagramCreationUtil.findAssociatedGMFDiagram()) {
            Diagram gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
            repairViewWihtoutElement(gmfDiagram, representationToNameMap.get(dDiagram));
        }
    }

    @Override
    public void postLoad(XMLResource resource, String loadedVersion) {
        repairOccurred = false;
        sb = new StringBuilder(Messages.ViewWithNullElementMigrationParticipant_title);
        super.postLoad(resource, loadedVersion);
        if (repairOccurred) {
            DiagramPlugin.getDefault().logInfo(sb.toString());
        }
    }

    private void repairViewWihtoutElement(Diagram gmfDiagram, String diagramName) {
        int fixedViews = 0;
        TreeIterator<EObject> iterator = gmfDiagram.eAllContents();
        while (iterator.hasNext()) {
            EObject elt = iterator.next();
            if (elt instanceof View && needsElementUpdateToExplicitNull((View) elt)) {
                iterator.prune();
                updateElementToExplicitNull((View) elt);
                fixedViews++;
            }
        }

        if (fixedViews == 1) {
            repairOccurred = true;
            sb.append(MessageFormat.format(Messages.ViewWithNullElementMigrationParticipant_singleMessage, diagramName));
        } else if (fixedViews > 1) {
            repairOccurred = true;
            sb.append(MessageFormat.format(Messages.ViewWithNullElementMigrationParticipant_message, fixedViews, diagramName));
        }
    }

    @Override
    protected void handleFeature(EObject owner, EStructuralFeature unkownFeature, Object valueOfUnknownFeature) {
        // case where migration participant DRepInDViewToRootObjectsAndWithDRepDescRepPathMigrationParticipant has not
        // been triggered yet. DRepresentations are found from DVIEW_OWNED_REPRESENTATIONS_UNKNOWN_FEATURE not existing
        // anymore feature.
        if (DVIEW_OWNED_REPRESENTATIONS_UNKNOWN_FEATURE.equals(unkownFeature.getName())) {
            if (valueOfUnknownFeature instanceof DDiagram && owner instanceof DView) {
                repairViewWihtoutElement((DDiagram) valueOfUnknownFeature);
            }
        } else if (owner instanceof DRepresentation && FEATURE_NAME_LABEL.equals(unkownFeature.getName())) {
            representationToNameMap.put((DRepresentation) owner, (String) valueOfUnknownFeature);
        }
    }

    /**
     * Check if a View match the known cases of broken views with invalid element: Note, Text and Note Attachment with
     * element set to null and isSetElement() returning false.
     * 
     * See also {@link #updateElementToExplicitNull(View)}
     * 
     * @param view
     *            the view to check.
     * @return true if the view needs to be updated, false otherwise.
     */
    public static boolean needsElementUpdateToExplicitNull(View view) {
        if (!view.isSetElement()) {
            String viewType = view.getType();
            boolean knownBrokenNodeCase = view instanceof Shape && (ViewType.NOTE.equals(viewType) || ViewType.TEXT.equals(viewType));
            boolean knownBrokenEdgeCase = view instanceof Connector && ViewType.NOTEATTACHMENT.equals(viewType);
            return knownBrokenNodeCase || knownBrokenEdgeCase;
        }
        return false;
    }

    /**
     * Fix known cases of broken View with invalid element: Note, Text and Note Attachment with element set to null and
     * isSetElement() returning false.
     * 
     * This method will iterate over the whole content tree of the broken View to fix their children.
     * 
     * See how such elements are constructed in
     * {@link org.eclipse.gmf.runtime.diagram.ui.view.factories.BasicNodeViewFactory#createView()},
     * {@link org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory#createView()} and
     * {@link org.eclipse.gmf.runtime.diagram.ui.view.factories.TextShapeViewFactory}
     * 
     * See also {@link #needsElementUpdateToExplicitNull(View)}
     * 
     * @param view
     *            the view to fix.
     */
    public static void updateElementToExplicitNull(View view) {
        view.setElement(null);

        // Update the element reference of all children Views
        view.eAllContents().forEachRemaining(elt -> {
            if (elt instanceof View && !((View) elt).isSetElement()) {
                ((View) elt).setElement(null);
            }
        });
    }
}
