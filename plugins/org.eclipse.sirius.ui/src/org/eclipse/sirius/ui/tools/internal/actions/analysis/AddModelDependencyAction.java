/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.analysis;

import java.lang.reflect.InvocationTargetException;
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
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.tools.api.actions.analysis.IAddModelDependencyWizard;
import org.eclipse.sirius.ui.tools.internal.dialogs.SemanticResourceDialog;
import org.eclipse.sirius.ui.tools.internal.operations.SemanticResourceAdditionOperation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.collect.Lists;

/**
 * Action to add a model dependency to a modeling project.
 * 
 * @author mchauvin
 */
public class AddModelDependencyAction extends Action {

    Session session;

    /**
     * Creates a new instance of this action.
     * 
     * @param session
     *            the session for which we will add a model dependency
     */
    public AddModelDependencyAction(Session session) {
        super("Add Model");
        final ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/add.gif"); //$NON-NLS-1$
        setImageDescriptor(descriptor);
        this.session = session;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        super.run();

        final IAddModelDependencyWizard wizard = IAddModelDependencyWizardRegistry.getCreateOrAddModelDependencyWizard(Lists.newArrayList(this.session));
        if (wizard != null) {
            wizard.setSessions(Lists.newArrayList(this.session));
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
        final ResourceDialog resourceDialog = new SemanticResourceDialog(Display.getCurrent().getActiveShell(), "Select resources to add", SWT.OPEN | SWT.MULTI);

        if (resourceDialog.open() == Window.OK) {
            List<URI> uris = resourceDialog.getURIs();
            if (!uris.isEmpty()) {
                SemanticResourceAdditionOperation semanticResourceAdditionOperation = new SemanticResourceAdditionOperation(Collections.singleton(session), uris);
                try {
                    new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(true, false, semanticResourceAdditionOperation);
                    Collection<Object> results = semanticResourceAdditionOperation.getResults();
                    for (Object result : results) {
                        if (result instanceof Session) {
                            // We open a viewpoint selection dialog
                            ViewpointSelection.openViewpointsSelectionDialog((Session) result);
                        }
                    }
                } catch (InvocationTargetException e) {
                    SiriusPlugin.getDefault().error("Error adding model dependency", e);
                } catch (InterruptedException e) {
                    SiriusPlugin.getDefault().error("Error adding model dependency", e);
                }
            }
        }
    }

}
