/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation 
 *    IBM - Initial API and implementation for the parts taken
 *          from org.eclipse.emf.ecore.action.CreateDynamicInstanceAction
 *******************************************************************************/
package org.eclipse.sirius.sample.ecore.design.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.DynamicModelWizard;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A dynamic action which can be invoked from the Sirius Ecore modeler to
 * create a dynamic instance of an EClass in the model being edited.
 * <p>
 * Most of the code has been takenh from
 * {@link org.eclipse.emf.ecore.action.CreateDynamicInstanceAction} and adapted
 * to be usable in the context of an {@link IExternalJavaAction}.
 * 
 * @author pcdavid
 */
public class CreateDynamicInstanceAction implements IExternalJavaAction {
    private static final URI PLATFORM_RESOURCE = URI.createPlatformResourceURI("/", false);

    /**
     * The action expects a single EClass as input.
     */
    public boolean canExecute(Collection<? extends EObject> selections) {
        return getSemanticTarget(selections) != null;
    }

    private EClass getSemanticTarget(Collection<? extends EObject> selections) {
        if (selections == null || selections.size() != 1) {
            return null;
        } else {
            final EObject selection = selections.iterator().next();
            final EObject semanticTarget;
            if (selection instanceof DSemanticDecorator) {
                semanticTarget = ((DSemanticDecorator) selection).getTarget();
            } else {
                semanticTarget = selection;
            }
            if (semanticTarget instanceof EClass) {
                return (EClass) semanticTarget;
            } else {
                return null;
            }
        }
    }

    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        EClass eClass = getSemanticTarget(selections);
        URI uri = eClass.eResource().getURI();
        IStructuredSelection selection = StructuredSelection.EMPTY;
        if (uri != null && uri.isHierarchical()) {
            if (uri.isRelative() || (uri = uri.deresolve(PLATFORM_RESOURCE)).isRelative()) {
                IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toString()));
                if (file.exists()) {
                    selection = new StructuredSelection(file);
                }
            }
        }

        DynamicModelWizard dynamicModelWizard = new DynamicModelWizard(eClass);
        dynamicModelWizard.init(PlatformUI.getWorkbench(), selection);
        dynamicModelWizard.setWindowTitle("Create Dynamic Instance");
        WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), dynamicModelWizard);

        wizardDialog.open();
    }
}
