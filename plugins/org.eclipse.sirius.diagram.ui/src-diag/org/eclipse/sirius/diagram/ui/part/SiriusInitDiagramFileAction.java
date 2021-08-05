/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.part;

import java.text.MessageFormat;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetFactory;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @was-generated
 */
public class SiriusInitDiagramFileAction implements IObjectActionDelegate {

    /**
     * @was-generated
     */
    private IWorkbenchPart targetPart;

    /**
     * @was-generated
     */
    private URI domainModelURI;

    /**
     * @was-generated
     */
    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.targetPart = targetPart;
    }

    /**
     * @was-generated
     */
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        domainModelURI = null;
        action.setEnabled(false);
        if (selection instanceof IStructuredSelection == false || selection.isEmpty()) {
            return;
        }
        IFile file = (IFile) ((IStructuredSelection) selection).getFirstElement();
        domainModelURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
        action.setEnabled(true);
    }

    /**
     * @was-generated
     */
    private Shell getShell() {
        return targetPart.getSite().getShell();
    }

    /**
     * @was-generated
     */
    @Override
    public void run(IAction action) {
        final ResourceSet set = ResourceSetFactory.createFactory().createResourceSet(domainModelURI);
        TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain(set);
        EObject diagramRoot = null;
        try {
            Resource resource = set.getResource(domainModelURI, true);
            diagramRoot = resource.getContents().get(0);
        } catch (WrappedException ex) {
            DiagramPlugin.getDefault().logError(MessageFormat.format(org.eclipse.sirius.diagram.ui.provider.Messages.SiriusInitDiagramFileAction_loadResourceError, domainModelURI), ex);
        }
        if (diagramRoot == null) {
            MessageDialog.openError(getShell(), Messages.SiriusInitDiagramFileAction_InitDiagramFileResourceErrorDialogTitle,
                    Messages.SiriusInitDiagramFileAction_InitDiagramFileResourceErrorDialogMessage);
            return;
        }
        Wizard wizard = new SiriusNewDiagramFileWizard(domainModelURI, diagramRoot, editingDomain);
        wizard.setWindowTitle(MessageFormat.format(Messages.SiriusInitDiagramFileAction_InitDiagramFileWizardTitle, DDiagramEditPart.MODEL_ID));
        SiriusDiagramEditorUtil.runWizard(getShell(), wizard, "InitDiagramFile"); //$NON-NLS-1$
    }
}
