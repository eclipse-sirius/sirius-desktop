/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

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
     * The current selection.
     */
    private ISelection selection;

    /**
     * Indicating if the action is enabled.
     */
    private boolean enabled;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Object applicationContext = null;
        if (event != null) {
            applicationContext = event.getApplicationContext();
        }
        if (applicationContext instanceof EvaluationContext) {
            EvaluationContext context = (EvaluationContext) applicationContext;
            Object defaultVariable = context.getDefaultVariable();
            if (defaultVariable instanceof List) {
                @SuppressWarnings("unchecked")
                List<Object> variables = (List<Object>) defaultVariable;
                List<IProject> projects = new ArrayList<IProject>();
                for (Object object : variables) {
                    if (object instanceof IProject) {
                        IProject project = (IProject) object;
                        projects.add(project);
                    } else if (object instanceof JavaProject) {
                        JavaProject javaProject = (JavaProject) object;
                        projects.add(javaProject.getProject());
                    } else if (Platform.getAdapterManager().getAdapter(object, IProject.class) instanceof IProject) {
                        projects.add((IProject) Platform.getAdapterManager().getAdapter(object, IProject.class));
                    }
                }
                if (!projects.isEmpty()) {
                    selection = new StructuredSelection(projects);
                }
            }
        }

        if (selection instanceof IStructuredSelection) {
            for (Object element : ((IStructuredSelection) selection).toList()) {
                IProject project = null;
                if (element instanceof IProject) {
                    project = (IProject) element;
                } else if (element instanceof IAdaptable) {
                    project = (IProject) ((IAdaptable) element).getAdapter(IProject.class);
                }
                if (project != null) {
                    toggleNature(project);
                }
            }
        }
        return null;
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.AbstractHandler#setEnabled(java.lang.Object)
     */
    @Override
    public void setEnabled(Object evaluationContext) {
        if (evaluationContext instanceof EvaluationContext) {
            EvaluationContext context = (EvaluationContext) evaluationContext;
            Object defaultVariable = context.getDefaultVariable();
            if (defaultVariable instanceof List && ((List) defaultVariable).size() > 0) {
                List<Object> variables = (List<Object>) defaultVariable;
                for (Object object : variables) {
                    if (object instanceof IProject) {
                        enabled = true;
                    } else if (object instanceof JavaProject) {
                        enabled = true;
                    } else if (Platform.getAdapterManager().getAdapter(object, IProject.class) instanceof IProject) {
                        enabled = true;
                    }
                }
            }
        }
    }

    /**
     * Sets the selection.
     * 
     * @param s
     *            The new selection.
     */
    public void setSelection(ISelection s) {
        this.selection = s;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
