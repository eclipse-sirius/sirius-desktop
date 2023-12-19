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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewRefactorHelper;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.Style;

/**
 * This class provide command to apply the GMF or Sirius style to the specified element.
 * 
 * @author Séraphin Costa <seraphin.costa@obeosoft.com>
 *
 */
public final class PasteStyleCommandProvider {
    private PasteStyleCommandProvider() {
    }
    private static String getCommandLabel() {
        return Messages.PasteStylePureGraphicalAction_commandLabel;
    }

    /**
     * Returns a command for styling one GMF view to another. Note that the style may only be partially applied if the
     * source and target views are not of the same kind.
     * 
     * @param domain
     *            The editing domain for the command
     * @param targetEditPart
     *            The EditPart to which you want to apply the style (The style will be applied to the GMF view
     *            associated with this EditPart).
     * @param sourceView
     *            The GMF view whose style you want to copy.
     * @return The command that updates the style of the target view.
     */
    public static Command createGMFCommand(TransactionalEditingDomain domain, IGraphicalEditPart targetEditPart, View sourceView) {
        final ViewRefactorHelper helper = new ViewRefactorHelper(targetEditPart.getDiagramPreferencesHint());
        List<EClass> exclusions = List.of(
                NotationPackage.eINSTANCE.getDescriptionStyle(), //
                NotationPackage.eINSTANCE.getImageBufferStyle() //
        );

        ICommand viewStyleCommand = new AbstractTransactionalCommand(domain, getCommandLabel(), null) {
            @Override
            protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {

                helper.copyViewAppearance(sourceView, targetEditPart.getNotationView(), exclusions);
                return CommandResult.newOKCommandResult();
            }
        };

        return new ICommandProxy(viewStyleCommand);
    }

    /**
     * Returns a command that applies a Sirius style to an element. Note that the style may not be applied if the source
     * style is not compatible with the target element (Expected: Node to Node, Container to Container or Edge to Edge).
     * 
     * @param domain
     *            The editing domain for the command
     * @param targetElement
     *            The Sirius element to which you want to apply the style.
     * @param sourceStyle
     *            The style you want to apply.
     * @return The command that updates the style of the target element, or <code>null</code> if the style is not
     *         compatible with the element.
     */
    public static Command createSiriusCommand(TransactionalEditingDomain domain, DStylizable targetElement, Style sourceStyle) {
        Style copiedStyle = SiriusCopierHelper.copyWithNoUidDuplication(sourceStyle);
        
        ICommand viewStyleCommand = null;
        if (targetElement instanceof DEdge && copiedStyle instanceof EdgeStyle) {
            final DEdge edge = (DEdge) targetElement;
            final EdgeStyle edgeStyle = (EdgeStyle) copiedStyle;

            viewStyleCommand = new AbstractTransactionalCommand(domain, getCommandLabel(), null) {
                @Override
                protected CommandResult doExecuteWithResult(final IProgressMonitor progressMonitor, final IAdaptable info) throws ExecutionException {
                    edge.setOwnedStyle(edgeStyle);
                    return CommandResult.newOKCommandResult();
                }
            };
        } else if (targetElement instanceof DNode && copiedStyle instanceof NodeStyle) {
            final DNode node = (DNode) targetElement;
            final NodeStyle nodeStyle = (NodeStyle) copiedStyle;

            viewStyleCommand = new AbstractTransactionalCommand(domain, getCommandLabel(), null) {
                @Override
                protected CommandResult doExecuteWithResult(final IProgressMonitor progressMonitor, final IAdaptable info) throws ExecutionException {
                    node.setOwnedStyle(nodeStyle);
                    return CommandResult.newOKCommandResult();
                }
            };
        } else if (targetElement instanceof DDiagramElementContainer && copiedStyle instanceof ContainerStyle) {
            final DDiagramElementContainer container = (DDiagramElementContainer) targetElement;
            final ContainerStyle containerStyle = (ContainerStyle) copiedStyle;

            viewStyleCommand = new AbstractTransactionalCommand(domain, getCommandLabel(), null) {
                @Override
                protected CommandResult doExecuteWithResult(final IProgressMonitor progressMonitor, final IAdaptable info) throws ExecutionException {
                    container.setOwnedStyle(containerStyle);
                    return CommandResult.newOKCommandResult();
                }
            };

        }

        if (viewStyleCommand == null) {
            return null;
        }
        return new ICommandProxy(viewStyleCommand);
    }
}
