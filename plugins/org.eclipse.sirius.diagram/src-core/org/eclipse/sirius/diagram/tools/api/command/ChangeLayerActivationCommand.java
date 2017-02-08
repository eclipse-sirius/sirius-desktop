/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.api.command;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.listener.Notification;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.Messages;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.Layer;

/**
 * Specific command to change layer activation.
 * 
 * @author mporhel
 */
public final class ChangeLayerActivationCommand extends RecordingCommand {

    private DDiagram dDiagram;

    private Layer layer;

    private IProgressMonitor monitor;

    /**
     * Default Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param dDiagram
     *            the {@link DDiagram} for which change the activated layers
     * @param layer
     *            the {@link Layer} concerned by this change
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of layer
     *            activation changes
     */
    public ChangeLayerActivationCommand(TransactionalEditingDomain domain, DDiagram dDiagram, Layer layer, IProgressMonitor monitor) {
        super(domain, dDiagram.getActivatedLayers().contains(layer) ? Messages.ChangeLayerActivationCommand_hideLabel
                : MessageFormat.format(Messages.ChangeLayerActivationCommand_showLabel, new IdentifiedElementQuery(layer).getLabel()));
        this.dDiagram = dDiagram;
        this.layer = layer;
        this.monitor = monitor;
    }

    @Override
    protected void doExecute() {
        try {
            monitor.beginTask(Messages.ChangeLayerActivationCommand_executeMsg, 3);
            boolean launchRefresh = !LayerHelper.containsOnlyTools(layer) || layer.getCustomization() != null;
            if (!launchRefresh) {
                NotificationUtil.sendNotification(dDiagram, Notification.Kind.START, Notification.VISIBILITY);
            }
            monitor.worked(1);
            boolean transientLayer = LayerHelper.isTransientLayer(layer);
            if (!transientLayer) {
                if (dDiagram.getActivatedLayers().contains(layer)) {
                    dDiagram.getActivatedLayers().remove(layer);
                } else {
                    dDiagram.getActivatedLayers().add(layer);
                }
            } else {
                if (dDiagram.getActivatedTransientLayers().contains(layer)) {
                    dDiagram.getActivatedTransientLayers().remove(layer);
                } else {
                    dDiagram.getActivatedTransientLayers().add((AdditionalLayer) layer);
                }
            }
            monitor.worked(1);

            if (launchRefresh) {
                new RefreshRepresentationsCommand(TransactionUtil.getEditingDomain(dDiagram), new SubProgressMonitor(monitor, 1), dDiagram).execute();
            } else {
                NotificationUtil.sendNotification(dDiagram, Notification.Kind.STOP, Notification.VISIBILITY);
            }
            monitor.worked(1);
        } finally {
            monitor.done();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        dDiagram = null;
        layer = null;
    }

}
