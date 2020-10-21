/*******************************************************************************
 * Copyright (c) 2009, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.sirius.diagram.sequence.business.internal.refresh.RefreshLayoutCommand;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.command.DoNothingCommand;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.util.EditPartTools;

/**
 * The layout provider for sequence diagrams.
 * 
 * @author ymortier, mporhel
 */
public class SequenceLayoutProvider extends AbstractLayoutProvider {
    @Override
    public Command layoutEditParts(List selectedObjects, IAdaptable layoutHint) {
        SequenceDiagramEditPart sdep = getParentSequenceDiagramEditPart(selectedObjects);
        boolean isArrangeAll = isArrangeAll(sdep, selectedObjects);
        if (sdep != null && isArrangeAll) {
            return createArrangeAllCommand(sdep);
        } else {
            return DoNothingCommand.INSTANCE;
        }
    }

    private boolean isArrangeAll(SequenceDiagramEditPart sdep, List selectedObjects) {
        boolean result = false;
        if (sdep != null && selectedObjects != null) {
            result = sdep.getChildren().size() == selectedObjects.size() && sdep.getChildren().containsAll(selectedObjects);
        }
        return result;
    }

    private Command createArrangeAllCommand(SequenceDiagramEditPart sdep) {
        TransactionalEditingDomain transactionalEditingDomain = sdep.getEditingDomain();
        ICommand cmd = new GMFCommandWrapper(transactionalEditingDomain, new RefreshLayoutCommand(transactionalEditingDomain, sdep.getDiagramView(), true, true));
        cmd.setLabel(Messages.SequenceLayoutProvider_arrangeAllCommand);
        return new ICommandProxy(cmd);
    }

    private SequenceDiagramEditPart getParentSequenceDiagramEditPart(List<?> selectedObjects) {
        if (!selectedObjects.isEmpty()) {
            return EditPartTools.getParentOfType((EditPart) selectedObjects.iterator().next(), SequenceDiagramEditPart.class);
        } else {
            return null;
        }
    }
}
