/*****************************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES
 * All rights reserved.
 *
 * Contributors:
 *      Obeo - Initial API and implementation
 *****************************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.osgi.framework.Version;

/**
 * The representations migration code for the 8.0.1 technical version.
 * 
 * @author Florian Barbin
 * 
 */
public class DiagramRepresentationsFileMigrationParticipantV801 {

    /**
     * The VP version for this migration.
     */
    public static final Version MIGRATION_VERSION = new Version("8.0.1"); //$NON-NLS-1$

    /**
     * We detected a case (see #426439) where a border node label is hidden (The
     * corresponding GMF node is at visible=false) but there is no
     * HideLabelFilter on the DDIagramElement.
     * 
     * @param diagrams
     *            list of GMF Diagram to migrate.
     */
    public void migrateLabelVisibilityInconsistency(List<Diagram> diagrams) {
        for (Diagram diagram : diagrams) {
            Iterator<EObject> iterator = diagram.eAllContents();
            while (iterator.hasNext()) {
                EObject element = iterator.next();
                if (element instanceof Node) {
                    Node node = (Node) element;
                    ViewQuery query = new ViewQuery(node);
                    String type = node.getType();
                    if (query.isForNameEditPart() || SiriusVisualIDRegistry.getType(DEdgeNameEditPart.VISUAL_ID).equals(type)
                            || SiriusVisualIDRegistry.getType(DEdgeBeginNameEditPart.VISUAL_ID).equals(type) || SiriusVisualIDRegistry.getType(DEdgeEndNameEditPart.VISUAL_ID).equals(type)) {

                        EObject nodeElement = node.getElement();
                        // If the GMF Node of the label is hidden
                        // (visible=false) and the ddiagram element label is
                        // visible, We make it hidden.
                        if (!node.isVisible() && isADDiagramElementAndLabelIsVisible(nodeElement)) {
                            HideFilterHelper.INSTANCE.hideLabel((DDiagramElement) nodeElement);
                        }
                    }
                }
            }
        }

    }

    private boolean isADDiagramElementAndLabelIsVisible(EObject nodeElement) {
        if (nodeElement instanceof DDiagramElement && !new DDiagramElementQuery((DDiagramElement) nodeElement).isLabelHidden()) {
            return true;
        }
        return false;
    }

}
