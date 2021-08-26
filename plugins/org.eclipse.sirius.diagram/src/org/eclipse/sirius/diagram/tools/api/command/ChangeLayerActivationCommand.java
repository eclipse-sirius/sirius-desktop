/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.api.command;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.eclipse.sirius.diagram.business.api.helper.decoration.DecorationHelper;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.model.business.internal.helper.LayerModelHelper;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.internal.Messages;
import org.eclipse.sirius.diagram.tools.internal.management.UpdateToolRecordingCommand;

import com.google.common.collect.Lists;

/**
 * Specific command to change layer activation.
 * 
 * @author mporhel
 */
public final class ChangeLayerActivationCommand extends RecordingCommand {

    private DDiagram dDiagram;

    private Layer layer;

    private IProgressMonitor monitor;

    private List<Layer> result;

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
     *            a {@link IProgressMonitor} to show progression of layer activation changes
     */
    public ChangeLayerActivationCommand(TransactionalEditingDomain domain, DDiagram dDiagram, Layer layer, IProgressMonitor monitor) {
        super(domain, new DDiagramQuery(dDiagram).getAllActivatedLayers().contains(layer) ? Messages.ChangeLayerActivationCommand_hideLabel
                : MessageFormat.format(Messages.ChangeLayerActivationCommand_showLabel, new IdentifiedElementQuery(layer).getLabel()));
        this.dDiagram = dDiagram;
        this.layer = layer;
        this.monitor = monitor;
    }

    @Override
    protected void doExecute() {
        try {
            // 553866 : testing editiingDoman of diagram to check if the command can be called
            TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(dDiagram);
            result = new ArrayList<Layer>();
            if (editingDomain == null) {
                return; // do nothing; getResult will return an empty collection
            }
            monitor.beginTask(Messages.ChangeLayerActivationCommand_executeMsg, 3);
            boolean transientLayer = LayerModelHelper.isTransientLayer(layer);
            if (transientLayer) {
                NotificationUtil.sendNotification(dDiagram, Notification.Kind.START, Notification.VISIBILITY);
            }
            monitor.worked(1);
            if (!transientLayer) {
                if (dDiagram.getActivatedLayers().contains(layer)) {
                    dDiagram.getActivatedLayers().remove(layer);
                } else {
                    dDiagram.getActivatedLayers().add(layer);
                }
                result.add(layer);
            } else {
                if (dDiagram.getActivatedTransientLayers().contains(layer)) {
                    if (((AdditionalLayer) layer).isOptional()) {
                        dDiagram.getActivatedTransientLayers().remove(layer);
                        result.add(layer);
                    }
                } else {
                    dDiagram.getActivatedTransientLayers().add((AdditionalLayer) layer);
                    result.add(layer);

                }
            }
            monitor.worked(1);

            new UpdateToolRecordingCommand(TransactionUtil.getEditingDomain(dDiagram), dDiagram, true).execute();
            DiagramPlugin.getDefault().getToolManagement(dDiagram).notifyToolChange();

            if (!transientLayer) {
                new RefreshRepresentationsCommand(TransactionUtil.getEditingDomain(dDiagram), new SubProgressMonitor(monitor, 1), dDiagram).execute();
            } else {
                // update decorations
                DecorationHelper decoHelper = new DecorationHelper(dDiagram);
                decoHelper.updateDecorations(Lists.<Layer> newArrayList(layer));

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

    /**
     * Return the list of layers activated or deactivated by the command Can be used to test if the command succeed.
     */
    @Override
    public Collection<?> getResult() {
        return result;
    }

}
