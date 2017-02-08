/*******************************************************************************
 * Copyright (c) 2012, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.api.command.ChangeLayerActivationCommand;
import org.eclipse.ui.PlatformUI;

/**
 * A {@link Action} to activate/deactivate layer of diagram.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class LayersActivationAction extends Action {

    private DDiagram dDiagram;

    private Layer layer;

    /**
     * Default constructor.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param style
     *            one of <code>AS_PUSH_BUTTON</code>, <code>AS_CHECK_BOX</code>,
     *            <code>AS_DROP_DOWN_MENU</code>, <code>AS_RADIO_BUTTON</code>,
     *            and <code>AS_UNSPECIFIED</code>.
     * @param dDiagram
     *            the {@link DDiagram} on which change activated layers
     * @param layer
     *            the {@link Layer} the layer to add/remove in/from activated
     *            layers
     */
    public LayersActivationAction(String text, int style, DDiagram dDiagram, Layer layer) {
        super(text, style);
        this.dDiagram = dDiagram;
        this.layer = layer;
    }

    @Override
    public void run() {
        try {
            IRunnableWithProgress runnable = new IRunnableWithProgress() {
                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dDiagram);
                    Command changeActivatedLayersCmd = new ChangeLayerActivationCommand(domain, dDiagram, layer, monitor);
                    domain.getCommandStack().execute(changeActivatedLayersCmd);
                }
            };
            new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(true, false, runnable);
        } catch (final InvocationTargetException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            }
            throw new RuntimeException(e.getCause());
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isChecked() {
        return dDiagram.getActivatedLayers().contains(layer) || dDiagram.getActivatedTransientLayers().contains(layer);
    }

    /**
     * Set action attributes to null.
     */
    public void dispose() {
        dDiagram = null;
        layer = null;
    }
}
