/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusStyleClipboard;
import org.eclipse.sirius.viewpoint.DStylizable;

/**
 * This command copies the style of the last element selected into {@link SiriusStyleClipboard}.
 * 
 * @author Séraphin Costa <seraphin.costa@obeosoft.com>
 *
 */
public final class CopyStyleCommand extends Command {
    List<?> selectionsRaw;

    /**
     * Create the command given the selection.
     * 
     * @param selectionsRaw
     *            The list of selected objects
     */
    public CopyStyleCommand(List<?> selectionsRaw) {
        super(Messages.CopyFormatDataCommand_label);

        this.selectionsRaw = selectionsRaw;
    }

    @Override
    public boolean canUndo() {
        return false;
    }

    Optional<IGraphicalEditPart> getLastSelectedPart() {
        return selectionsRaw.stream() //
                .filter(IGraphicalEditPart.class::isInstance) //
                .map(IGraphicalEditPart.class::cast) //
                .reduce((first, second) -> second); // <=> findLast()
    }

    private EditPart getTargetEditPart(EditPart selection) {
        if (selection instanceof LabelEditPart editPart) {
            return editPart.getParent();
        } else {
            return selection;
        }
    }

    @Override
    public void execute() {
        getLastSelectedPart().ifPresent((IGraphicalEditPart selection) -> {
            if (getTargetEditPart(selection) instanceof IGraphicalEditPart sourceGraphicalEditPart) {
                final EObject diagramElementRaw = sourceGraphicalEditPart.resolveSemanticElement();
                final Object gmfViewRaw = sourceGraphicalEditPart.getModel();
                if (diagramElementRaw instanceof DStylizable diagramElement && gmfViewRaw instanceof View gmfView) {
                    SiriusStyleClipboard.getInstance().store(gmfView, diagramElement.getStyle());
                }
            }
        });
    }
}
