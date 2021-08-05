/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.actions.analysis;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.tools.api.actions.analysis.IAddModelDependencyWizard;
import org.eclipse.sirius.ui.tools.internal.dialogs.SemanticResourceDialog;
import org.eclipse.sirius.ui.tools.internal.operations.SemanticResourceAdditionOperation;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Action to add a model dependency to a modeling project.
 * 
 * @author mchauvin
 */
public class AddModelDependencyAction extends Action {

    Session session;

    /**
     * True if the viewpoints selection windows should be open after adding the model dependency to the session. False
     * otherwise.
     */
    private boolean openViewpointSelectionAfterAddition;

    /**
     * Creates a new instance of this action.
     * 
     * @param session
     *            the session for which we will add a model dependency
     */
    public AddModelDependencyAction(Session session) {
        this(session, true);
    }

    /**
     * Creates a new instance of this action.
     * 
     * @param session
     *            the session for which we will add a model dependency
     * @param openViewpointSelectionAfterAddition
     *            true if the viewpoints selection windows should be open after adding the model dependency to the
     *            session. False otherwise.
     */
    public AddModelDependencyAction(Session session, boolean openViewpointSelectionAfterAddition) {
        super(Messages.AddModelDependencyAction_title);
        final ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/add.gif"); //$NON-NLS-1$
        setImageDescriptor(descriptor);
        this.session = session;
        this.openViewpointSelectionAfterAddition = openViewpointSelectionAfterAddition;
    }

    @Override
    public void run() {
        super.run();

        final IAddModelDependencyWizard wizard = IAddModelDependencyWizardRegistry.getCreateOrAddModelDependencyWizard(new ArrayList<Session>(Arrays.asList(this.session)));
        if (wizard != null) {
            wizard.setSessions(new ArrayList<Session>(Arrays.asList(this.session)));
            final WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
            dialog.create();
            dialog.getShell().setText(wizard.getWizardTitle());
            dialog.open();
        } else {
            // If no custom CreateOrAddResourceWizard can be applied, we use a
            // default Resource dialog
            createSemanticResourceDialog();
        }
    }

    private void createSemanticResourceDialog() {
        final ResourceDialog resourceDialog = new SemanticResourceDialog(Display.getCurrent().getActiveShell(), Messages.AddModelDependencyAction_resourceSelectionMessage, SWT.OPEN | SWT.MULTI);

        if (resourceDialog.open() == Window.OK) {
            List<URI> uris = resourceDialog.getURIs();
            if (!uris.isEmpty()) {
                SemanticResourceAdditionOperation semanticResourceAdditionOperation = new SemanticResourceAdditionOperation(Collections.singleton(session), uris);
                try {
                    new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(true, false, semanticResourceAdditionOperation);
                    Collection<Object> results = semanticResourceAdditionOperation.getResults();
                    for (Object result : results) {
                        if (result instanceof Session && openViewpointSelectionAfterAddition) {
                            // We open a viewpoint selection dialog
                            ViewpointSelection.openViewpointsSelectionDialog((Session) result);
                        }
                    }
                } catch (InvocationTargetException e) {
                    SiriusPlugin.getDefault().error(Messages.AddModelDependencyAction_error, e);
                } catch (InterruptedException e) {
                    SiriusPlugin.getDefault().error(Messages.AddModelDependencyAction_error, e);
                }
            }
        }
    }

}
