/*******************************************************************************
 * Copyright (c) 2011, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.layout;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.AbstractCommand.NonDirtying;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.swt.widgets.Display;

/**
 * Specific Command to do layout for newly created views in pre-commit without being in the IOperationHistory by being a
 * {@link NonDirtying} . </br>
 * <b>NOTE : the use of {@link NonDirtying} is a workaround to not have layout of created views (in pre-commit) in the
 * undo stack, but this doesn't seems break the undo/redo model because here we only changes Bounds.</b>.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SiriusCanonicalLayoutCommand extends RecordingCommand implements NonDirtying {

    private IGraphicalEditPart parentEditPart;

    private List<IAdaptable> childViewsAdapters;

    private List<IAdaptable> centeredChildViewsAdapters;

    private String specificLayoutType;

    /**
     * Constructor used to do a layout on all created views child (directly or indirectly) of Diagram. </br>
     * NOTE : to use at diagram representation opening.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} on which executes this command
     * @param diagramEditPart
     *            the {@link DiagramEditPart} on which do the layout
     */
    public SiriusCanonicalLayoutCommand(TransactionalEditingDomain domain, DiagramEditPart diagramEditPart) {
        this(domain, diagramEditPart, null, null);
    }

    /**
     * Constructor to do layout on all created views child directly of a View. </br>
     * NOTE : to use when external changes occurs.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} on which executes this command
     * @param parentEditPart
     *            the {@link IGraphicalEditPart} on which do the layout
     * @param childViewsAdapters
     *            list of {@link IAdaptable} for created Views to layout
     * @param centeredChildViewsAdapters
     *            list of {@link IAdaptable} for created Views to layout but which must be layouted in the center of
     *            their containers
     */
    public SiriusCanonicalLayoutCommand(TransactionalEditingDomain domain, IGraphicalEditPart parentEditPart, List<IAdaptable> childViewsAdapters, List<IAdaptable> centeredChildViewsAdapters) {
        this(domain, parentEditPart, childViewsAdapters, centeredChildViewsAdapters, LayoutType.DEFAULT);
    }

    public SiriusCanonicalLayoutCommand(TransactionalEditingDomain domain, IGraphicalEditPart parentEditPart, List<IAdaptable> childViewsAdapters, List<IAdaptable> centeredChildViewsAdapters,
            String specificLayoutType) {
        super(domain, Messages.SiriusCanonicalLayoutCommand_label);
        this.parentEditPart = parentEditPart;
        this.childViewsAdapters = childViewsAdapters;
        this.centeredChildViewsAdapters = centeredChildViewsAdapters;
        this.specificLayoutType = specificLayoutType;
    }

    /**
     * Overridden to execute a DeferredLayoutCommand.
     */
    @Override
    protected void doExecute() {
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                if (childViewsAdapters == null && centeredChildViewsAdapters == null) {
                    executeLayoutOnDiagramOpening();
                } else {
                    executeLayoutDueToExternalChanges();
                }
            }
        });

    }

    private void executeLayoutOnDiagramOpening() {
        org.eclipse.gef.commands.Command arrangeCmd = SiriusLayoutDataManager.INSTANCE.getArrangeCreatedViewsOnOpeningCommand(parentEditPart);
        if (arrangeCmd != null && arrangeCmd.canExecute()) {
            arrangeCmd.execute();
        }
    }

    private void executeLayoutDueToExternalChanges() {
        org.eclipse.gef.commands.Command arrangeCmd = SiriusLayoutDataManager.INSTANCE.getArrangeCreatedViewsCommand(childViewsAdapters, centeredChildViewsAdapters, parentEditPart,
                specificLayoutType);
        if (arrangeCmd != null && arrangeCmd.canExecute() && parentEditPart != null && parentEditPart.getRoot() != null) {
            arrangeCmd.execute();
        }
    }
}
