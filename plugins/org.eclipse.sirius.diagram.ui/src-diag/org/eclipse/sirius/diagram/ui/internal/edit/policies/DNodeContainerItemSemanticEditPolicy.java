/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

import com.google.common.collect.Iterables;

/**
 * @was-generated
 */
public class DNodeContainerItemSemanticEditPolicy extends AbstractDDiagramElementContainerItemSemanticEditPolicy {

    /**
     * @was-generated
     */
    protected void addDestroyChildNodesCommand(CompoundCommand cmd) {
        View view = (View) getHost().getModel();
        EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
        if (annotation != null) {
            return;
        }
        for (Node node : Iterables.filter(view.getChildren(), Node.class)) {
            switch (SiriusVisualIDRegistry.getVisualID(node)) {
            case DNode4EditPart.VISUAL_ID:
                cmd.add(getDestroyElementCommand(node));
                break;
            case DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID:
            case DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID:
                for (Node cnode : Iterables.filter(node.getChildren(), Node.class)) {
                    switch (SiriusVisualIDRegistry.getVisualID(cnode)) {
                    case DNode3EditPart.VISUAL_ID:
                    case DNodeContainer2EditPart.VISUAL_ID:
                    case DNodeList2EditPart.VISUAL_ID:
                        cmd.add(getDestroyElementCommand(cnode));
                        break;
                    }
                }
                break;
            default:
                break;
            }
        }
    }
}
