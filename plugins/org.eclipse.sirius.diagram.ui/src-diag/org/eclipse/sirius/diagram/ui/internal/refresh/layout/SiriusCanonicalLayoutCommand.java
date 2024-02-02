/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES and others.
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
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
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

    private List<IAdaptable> borderedChildViewsAdapters;

    private List<IAdaptable> centeredChildViewsAdapters;

    private List<IAdaptable> referenceChildViewsAdapters;

    private String specificLayoutType;

    private boolean deferredExecution;

    /**
     * Construct a command to layout given views with the default algorithm.
     * 
     * @param domain
     *            the transactional editing domain
     * @param parent
     *            the container or the diagram of all given views
     * @param views
     *            the views to layout, should be in the same container
     * @param layoutType
     *            the layout type, {@link org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType}
     * @return the layout command
     */
    static SiriusCanonicalLayoutCommand normal(TransactionalEditingDomain domain, IGraphicalEditPart parent, List<IAdaptable> views, String layoutType) {
        return new SiriusCanonicalLayoutCommand(domain, parent, views, null, null, null, layoutType, false);
    }

    /**
     * Construct a command to layout given views with the creation algorithm: this is the layout applied to views when
     * they are created.
     * 
     * <ul>
     * <li>When view creation contains a specified position (tool click position, drag&drop release position...):<br>
     * → We position the first element at its specified position and the others in succession (to the right, downwards
     * or diagonally, depending on the setting).</li>
     * <li>When there are no element coordinates, if the element is created on the diagram:<br>
     * → position the first in the center and the others in succession (to the right, downwards or diagonally, depending
     * on the setting).</li>
     * <li>When there are no element coordinates, if the element is created in a container:<br>
     * → position the first one at the top left of the container and the others in succession (to the right, downwards
     * or diagonally, depending on the setting).</li>
     * </ul>
     * 
     * @param domain
     *            the transactional editing domain
     * @param parent
     *            the container or the diagram of all given views
     * @param views
     *            the views to layout, should be in the same container
     * @param referenceViews
     *            The first views are used as reference positions for placing the others. Without this, the first view
     *            will be at the center of the diagram or at the top left of the container.
     * @param layoutType
     *            the layout type, {@link org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType}
     * @return the layout command
     */
    static SiriusCanonicalLayoutCommand initial(TransactionalEditingDomain domain, IGraphicalEditPart parent, List<IAdaptable> views, List<IAdaptable> referenceViews, String layoutType) {
        return new SiriusCanonicalLayoutCommand(domain, parent, null, null, views, referenceViews, layoutType, false);
    }

    /**
     * Construct a command to layout given views with the border node layout algorithm.
     * 
     * @param domain
     *            the transactional editing domain
     * @param parent
     *            the container or the diagram of all given views
     * @param views
     *            the views to layout, should be in the same container
     * @return the layout command
     */
    static SiriusCanonicalLayoutCommand border(TransactionalEditingDomain domain, IGraphicalEditPart parent, List<IAdaptable> views) {
        return new SiriusCanonicalLayoutCommand(domain, parent, null, views, null, null, SiriusLayoutDataManager.KEEP_FIXED, true);
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
    private SiriusCanonicalLayoutCommand(TransactionalEditingDomain domain, IGraphicalEditPart parentEditPart, List<IAdaptable> childViewsAdapters, List<IAdaptable> borderedChildViewsAdapters,
            List<IAdaptable> centeredChildViewsAdapters, List<IAdaptable> referenceChildViewsAdapters, String specificLayoutType, boolean deferredExecution) {
        super(domain, Messages.SiriusCanonicalLayoutCommand_label);
        this.parentEditPart = parentEditPart;
        this.childViewsAdapters = childViewsAdapters;
        this.centeredChildViewsAdapters = centeredChildViewsAdapters;
        this.referenceChildViewsAdapters = referenceChildViewsAdapters;
        this.borderedChildViewsAdapters = borderedChildViewsAdapters;
        this.specificLayoutType = specificLayoutType;
        this.deferredExecution = deferredExecution;
    }

    /**
     * Overridden to execute a DeferredLayoutCommand.
     */
    @Override
    protected void doExecute() {
        Display.getDefault().asyncExec(() -> {
            if (deferredExecution) {
                // Defer execution to the end of the async execution queue.
                Display.getDefault().asyncExec(() -> {
                    internalExecute();
                });
            } else {
                internalExecute();
            }
        });

    }

    private void internalExecute() {
        if (childViewsAdapters == null && centeredChildViewsAdapters == null && borderedChildViewsAdapters == null) {
            executeLayoutOnDiagramOpening();
        } else {
            executeLayoutDueToExternalChanges();
        }

    }

    private void executeLayoutOnDiagramOpening() {
        org.eclipse.gef.commands.Command arrangeCmd = SiriusLayoutDataManager.INSTANCE.getArrangeCreatedViewsOnOpeningCommand(parentEditPart);
        if (arrangeCmd != null && arrangeCmd.canExecute()) {
            arrangeCmd.execute();
        }
    }

    private void executeLayoutDueToExternalChanges() {
        org.eclipse.gef.commands.Command arrangeCmd = SiriusLayoutDataManager.INSTANCE.getArrangeCreatedViewsCommand(childViewsAdapters, borderedChildViewsAdapters, centeredChildViewsAdapters,
                referenceChildViewsAdapters, parentEditPart, specificLayoutType);
        if (arrangeCmd != null && arrangeCmd.canExecute() && parentEditPart != null && parentEditPart.getRoot() != null) {
            arrangeCmd.execute();
        }
    }
}
