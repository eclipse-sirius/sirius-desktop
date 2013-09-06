/*******************************************************************************
 * Copyright (c) 2012, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.nature;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.collect.Sets;

import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.provider.SiriusEditPlugin;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;

/**
 * Enable toggle to modeling project.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
@SuppressWarnings("restriction")
public class ModelingToggleNatureAction extends AbstractHandler {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Set<IProject> selectedProjects = getSelectedProjects(event);
        for (IProject project : selectedProjects) {
            toggleNature(project);
        }
        return null;
    }

    private Set<IProject> getSelectedProjects(ExecutionEvent event) {
        Set<IProject> selectedProjects = Sets.newHashSet();
        ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
        if (currentSelection instanceof IStructuredSelection) {
            for (Object object : ((IStructuredSelection) currentSelection).toList()) {
                if (object instanceof IProject) {
                    selectedProjects.add((IProject) object);
                } else if (object instanceof JavaProject) {
                    JavaProject javaProject = (JavaProject) object;
                    selectedProjects.add(javaProject.getProject());
                } else if (Platform.getAdapterManager().getAdapter(object, IProject.class) instanceof IProject) {
                    selectedProjects.add((IProject) Platform.getAdapterManager().getAdapter(object, IProject.class));
                }
            }
        }
        return selectedProjects;
    }

    /**
     * Toggles sample nature on a project.
     * 
     * @param project
     *            to have sample nature added or removed
     */
    private void toggleNature(final IProject project) {
        try {
            PlatformUI.getWorkbench().getProgressService().run(true, false, new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    try {
                        if (ModelingProject.hasModelingProjectNature(project)) {
                            ModelingProjectManager.INSTANCE.removeModelingNature(project, monitor);
                        } else {
                            ModelingProjectManager.INSTANCE.convertToModelingProject(project, monitor);
                        }
                    } catch (CoreException e) {
                        UICallBack uiCallback = SiriusEditPlugin.getPlugin().getUiCallback();
                        if (uiCallback != null) {
                            uiCallback.openError("Convert Project to Modeling project", "Impossible to convert the project: " + e.getMessage());
                        }
                    }
                }
            });
        } catch (final InvocationTargetException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            }
            throw new RuntimeException(e);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
