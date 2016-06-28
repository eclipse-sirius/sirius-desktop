/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.properties.samples;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.wizard.ExampleInstallerWizard;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

public class PropertiesTestsExampleWizard extends ExampleInstallerWizard {
    @Override
    /**
     * Import selected example and switch to modeling perspective. {@inheritDoc}
     */
    public boolean performFinish() {

        super.performFinish();

        final IRunnableWithProgress op = new WorkspaceModifyOperation(null) {
            @Override
            protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {

                // Switch to the modeling perspective
                try {
                    PlatformUI.getWorkbench().showPerspective("org.eclipse.sirius.ui.tools.perspective.modeling", //$NON-NLS-1$
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow());
                } catch (final WorkbenchException e) {
                    ErrorDialog.openError(getContainer().getShell(), "Error while opening example", null, e.getStatus());
                }
            }
        };
        try {
            getContainer().run(false, true, op);
        } catch (final InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog.openError(getContainer().getShell(), "Error while opening example", null, ((CoreException) e.getTargetException()).getStatus());
            }
        } catch (final InterruptedException e) {
            return false;
        }

        // Select it in the explorer
        for (final ProjectDescriptor projectDescriptor : getProjectDescriptors()) {
            final IProject project = projectDescriptor.getProject();
            BasicNewResourceWizard.selectAndReveal(project, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
        }

        return true;
    }

}
