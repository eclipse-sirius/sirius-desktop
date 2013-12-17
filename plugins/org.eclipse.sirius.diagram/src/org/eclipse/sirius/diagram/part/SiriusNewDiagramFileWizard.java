/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.part;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.internal.edit.parts.DDiagramEditPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * @was-generated
 */
public class SiriusNewDiagramFileWizard extends Wizard {

    /**
     * @was-generated
     */
    private WizardNewFileCreationPage myFileCreationPage;

    /**
     * @was-generated
     */
    private ModelElementSelectionPage diagramRootElementSelectionPage;

    /**
     * @was-generated
     */
    private TransactionalEditingDomain myEditingDomain;

    /**
     * @was-generated
     */
    public SiriusNewDiagramFileWizard(URI domainModelURI, EObject diagramRoot, TransactionalEditingDomain editingDomain) {
        assert domainModelURI != null : "Domain model uri must be specified"; //$NON-NLS-1$
        assert diagramRoot != null : "Doagram root element must be specified"; //$NON-NLS-1$
        assert editingDomain != null : "Editing domain must be specified"; //$NON-NLS-1$

        myFileCreationPage = new WizardNewFileCreationPage(Messages.SiriusNewDiagramFileWizard_CreationPageName, StructuredSelection.EMPTY);
        myFileCreationPage.setTitle(Messages.SiriusNewDiagramFileWizard_CreationPageTitle);
        myFileCreationPage.setDescription(NLS.bind(Messages.SiriusNewDiagramFileWizard_CreationPageDescription, DDiagramEditPart.MODEL_ID));
        IPath filePath;
        String fileName = domainModelURI.trimFileExtension().lastSegment();
        if (domainModelURI.isPlatformResource()) {
            filePath = new Path(domainModelURI.trimSegments(1).toPlatformString(true));
        } else if (domainModelURI.isFile()) {
            filePath = new Path(domainModelURI.trimSegments(1).toFileString());
        } else {
            // TODO : use some default path
            throw new IllegalArgumentException("Unsupported URI: " + domainModelURI); //$NON-NLS-1$
        }
        myFileCreationPage.setContainerFullPath(filePath);
        myFileCreationPage.setFileName(SiriusDiagramEditorUtil.getUniqueFileName(filePath, fileName, "airview")); //$NON-NLS-1$

        diagramRootElementSelectionPage = new DiagramRootElementSelectionPage(Messages.SiriusNewDiagramFileWizard_RootSelectionPageName);
        diagramRootElementSelectionPage.setTitle(Messages.SiriusNewDiagramFileWizard_RootSelectionPageTitle);
        diagramRootElementSelectionPage.setDescription(Messages.SiriusNewDiagramFileWizard_RootSelectionPageDescription);
        diagramRootElementSelectionPage.setModelElement(diagramRoot);

        myEditingDomain = editingDomain;
    }

    /**
     * @was-generated
     */
    public void addPages() {
        addPage(myFileCreationPage);
        addPage(diagramRootElementSelectionPage);
    }

    /**
     * @was-generated
     */
    public boolean performFinish() {
        List affectedFiles = new LinkedList();
        IFile diagramFile = myFileCreationPage.createNewFile();
        SiriusDiagramEditorUtil.setCharset(diagramFile);
        affectedFiles.add(diagramFile);
        URI diagramModelURI = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
        ResourceSet resourceSet = myEditingDomain.getResourceSet();
        final Resource diagramResource = resourceSet.createResource(diagramModelURI);
        AbstractTransactionalCommand command = new AbstractTransactionalCommand(myEditingDomain, Messages.SiriusNewDiagramFileWizard_InitDiagramCommand, affectedFiles) {

            protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
                int diagramVID = SiriusVisualIDRegistry.getDiagramVisualID(diagramRootElementSelectionPage.getModelElement());
                if (diagramVID != DDiagramEditPart.VISUAL_ID) {
                    return CommandResult.newErrorCommandResult(Messages.SiriusNewDiagramFileWizard_IncorrectRootError);
                }
                Diagram diagram = ViewService.createDiagram(diagramRootElementSelectionPage.getModelElement(), DDiagramEditPart.MODEL_ID, SiriusDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
                diagramResource.getContents().add(diagram);
                return CommandResult.newOKCommandResult();
            }
        };
        try {
            OperationHistoryFactory.getOperationHistory().execute(command, new NullProgressMonitor(), null);
            diagramResource.save(SiriusDiagramEditorUtil.getSaveOptions());
            SiriusDiagramEditorUtil.openDiagram(diagramResource);
        } catch (ExecutionException e) {
            SiriusDiagramEditorPlugin.getInstance().logError("Unable to create model and diagram", e); //$NON-NLS-1$
        } catch (IOException ex) {
            SiriusDiagramEditorPlugin.getInstance().logError("Save operation failed for: " + diagramModelURI, ex); //$NON-NLS-1$
        } catch (PartInitException ex) {
            SiriusDiagramEditorPlugin.getInstance().logError("Unable to open editor", ex); //$NON-NLS-1$
        }
        return true;
    }

    /**
     * @was-generated
     */
    private static class DiagramRootElementSelectionPage extends ModelElementSelectionPage {

        /**
         * @was-generated
         */
        protected DiagramRootElementSelectionPage(String pageName) {
            super(pageName);
        }

        /**
         * @was-generated
         */
        protected String getSelectionTitle() {
            return Messages.SiriusNewDiagramFileWizard_RootSelectionPageSelectionTitle;
        }

        /**
         * @was-generated
         */
        protected boolean validatePage() {
            if (selectedModelElement == null) {
                setErrorMessage(Messages.SiriusNewDiagramFileWizard_RootSelectionPageNoSelectionMessage);
                return false;
            }
            boolean result = ViewService.getInstance().provides(
                    new CreateDiagramViewOperation(new EObjectAdapter(selectedModelElement), DDiagramEditPart.MODEL_ID, SiriusDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT));
            setErrorMessage(result ? null : Messages.SiriusNewDiagramFileWizard_RootSelectionPageInvalidSelectionMessage);
            return result;
        }
    }
}
