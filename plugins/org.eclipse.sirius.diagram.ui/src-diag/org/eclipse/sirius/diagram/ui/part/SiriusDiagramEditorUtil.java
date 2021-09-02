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

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetFactory;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @was-generated
 */
public class SiriusDiagramEditorUtil {

    /**
     * @was-generated
     */
    public static Map getSaveOptions() {
        Map saveOptions = new HashMap();
        saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
        saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
        return saveOptions;
    }

    /**
     * @was-generated
     */
    public static boolean openDiagram(Resource diagram) throws PartInitException {
        String path = diagram.getURI().toPlatformString(true);
        IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(path));
        if (workspaceResource instanceof IFile) {
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            return null != page.openEditor(new FileEditorInput((IFile) workspaceResource), DDiagramEditor.EDITOR_ID);
        }
        return false;
    }

    /**
     * @was-generated
     */
    public static void setCharset(IFile file) {
        if (file == null) {
            return;
        }
        try {
            file.setCharset("UTF-8", new NullProgressMonitor()); //$NON-NLS-1$
        } catch (CoreException e) {
            DiagramPlugin.getDefault().logError(MessageFormat.format(org.eclipse.sirius.diagram.ui.provider.Messages.SiriusDiagramEditorUtil_charsetError, file.getFullPath()), e);
        }
    }

    /**
     * @was-generated
     */
    public static String getUniqueFileName(IPath containerFullPath, String fileName, String extension) {
        if (containerFullPath == null) {
            containerFullPath = new Path(""); //$NON-NLS-1$
        }
        if (fileName == null || fileName.trim().length() == 0) {
            fileName = org.eclipse.sirius.diagram.ui.provider.Messages.SiriusDiagramEditorUtil_defaultFileName;
        }
        IPath filePath = containerFullPath.append(fileName);
        if (extension != null && !extension.equals(filePath.getFileExtension())) {
            filePath = filePath.addFileExtension(extension);
        }
        extension = filePath.getFileExtension();
        fileName = filePath.removeFileExtension().lastSegment();
        int i = 1;
        while (ResourcesPlugin.getWorkspace().getRoot().exists(filePath)) {
            i++;
            filePath = containerFullPath.append(fileName + i);
            if (extension != null) {
                filePath = filePath.addFileExtension(extension);
            }
        }
        return filePath.lastSegment();
    }

    /**
     * Runs the wizard in a dialog.
     * 
     * @was-generated
     */
    public static void runWizard(Shell shell, Wizard wizard, String settingsKey) {
        IDialogSettings pluginDialogSettings = DiagramUIPlugin.getPlugin().getDialogSettings();
        IDialogSettings wizardDialogSettings = pluginDialogSettings.getSection(settingsKey);
        if (wizardDialogSettings == null) {
            wizardDialogSettings = pluginDialogSettings.addNewSection(settingsKey);
        }
        wizard.setDialogSettings(wizardDialogSettings);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.create();
        dialog.getShell().setSize(Math.max(500, dialog.getShell().getSize().x), 500);
        dialog.open();
    }

    /**
     * This method should be called within a workspace modify operation since it creates resources.
     * 
     * @was-generated
     */
    public static Resource createDiagram(URI diagramURI, URI modelURI, IProgressMonitor progressMonitor) {
        final ResourceSet set = ResourceSetFactory.createFactory().createResourceSet(diagramURI);
        TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain(set);
        progressMonitor.beginTask(Messages.SiriusDiagramEditorUtil_CreateDiagramProgressTask, 3);
        final Resource diagramResource = editingDomain.getResourceSet().createResource(diagramURI);
        final Resource modelResource = editingDomain.getResourceSet().createResource(modelURI);
        final String diagramName = diagramURI.lastSegment();
        AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain, Messages.SiriusDiagramEditorUtil_CreateDiagramCommandLabel, Collections.EMPTY_LIST) {
            @Override
            protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
                DDiagram model = createInitialModel();
                attachModelToResource(model, modelResource);

                Diagram diagram = ViewService.createDiagram(model, DDiagramEditPart.MODEL_ID, DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT);
                if (diagram != null) {
                    diagramResource.getContents().add(diagram);
                    diagram.setName(diagramName);
                    diagram.setElement(model);
                }

                try {
                    modelResource.save(org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditorUtil.getSaveOptions());
                    diagramResource.save(org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditorUtil.getSaveOptions());
                } catch (IOException e) {

                    DiagramPlugin.getDefault().logError(org.eclipse.sirius.diagram.ui.provider.Messages.SiriusDiagramEditorUtil_modelAndDiagramResourceSaveError, e);
                }
                return CommandResult.newOKCommandResult();
            }
        };
        try {
            OperationHistoryFactory.getOperationHistory().execute(command, new SubProgressMonitor(progressMonitor, 1), null);
        } catch (ExecutionException e) {
            DiagramPlugin.getDefault().logError(org.eclipse.sirius.diagram.ui.provider.Messages.SiriusDiagramEditorUtil_modelAndDiagramCreationError, e);
        }
        setCharset(WorkspaceSynchronizer.getFile(modelResource));
        setCharset(WorkspaceSynchronizer.getFile(diagramResource));
        return diagramResource;
    }

    /**
     * Create a new instance of domain element associated with canvas. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @was-generated
     */
    private static DDiagram createInitialModel() {
        return DiagramFactory.eINSTANCE.createDDiagram();
    }

    /**
     * Store model element in the resource. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @was-generated
     */
    private static void attachModelToResource(DDiagram model, Resource resource) {
        resource.getContents().add(model);
    }

    /**
     * @was-generated
     */
    public static void selectElementsInDiagram(IDiagramWorkbenchPart diagramPart, List/* EditPart */ editParts) {
        diagramPart.getDiagramGraphicalViewer().deselectAll();

        EditPart firstPrimary = null;
        for (Iterator it = editParts.iterator(); it.hasNext();) {
            EditPart nextPart = (EditPart) it.next();
            diagramPart.getDiagramGraphicalViewer().appendSelection(nextPart);
            if (firstPrimary == null && nextPart instanceof IPrimaryEditPart) {
                firstPrimary = nextPart;
            }
        }

        if (!editParts.isEmpty()) {
            diagramPart.getDiagramGraphicalViewer().reveal(firstPrimary != null ? firstPrimary : (EditPart) editParts.get(0));
        }

    }

    /**
     * Select all the editParts. The last editPart of this list will be the primarySelected element.
     * 
     * @was-generated-not
     */
    public static void selectWithoutRevealElementsInDiagram(IDiagramWorkbenchPart diagramPart, List<EditPart> editParts) {
        diagramPart.getDiagramGraphicalViewer().deselectAll();

        for (Iterator<EditPart> it = editParts.iterator(); it.hasNext();) {
            EditPart nextPart = it.next();
            diagramPart.getDiagramGraphicalViewer().appendSelection(nextPart);
        }
    }

    /**
     * @was-generated
     */
    public static class LazyElement2ViewMap {
        /**
         * @was-generated
         */
        private Map element2ViewMap;

        /**
         * @was-generated
         */
        private View scope;

        /**
         * @was-generated
         */
        private Set elementSet;

        /**
         * @was-generated
         */
        public final List editPartTmpHolder = new ArrayList();

        /**
         * @was-generated
         */
        public LazyElement2ViewMap(View scope, Set elements) {
            this.scope = scope;
            this.elementSet = elements;
        }

        /**
         * @was-generated
         */
        public final Map getElement2ViewMap() {
            if (element2ViewMap == null) {
                element2ViewMap = new HashMap();
                // map possible notation elements to itself as these can't be
                // found by view.getElement()
                for (Iterator it = elementSet.iterator(); it.hasNext();) {
                    EObject element = (EObject) it.next();
                    if (element instanceof View) {
                        View view = (View) element;
                        if (view.getDiagram() == scope.getDiagram()) {
                            element2ViewMap.put(element, element); // take only
                            // those
                            // that part
                            // of our
                            // diagram
                        }
                    }
                }

                buildElement2ViewMap(scope, element2ViewMap, elementSet);
            }
            return element2ViewMap;
        }

        /**
         * @was-generated
         */
        static Map buildElement2ViewMap(View parentView, Map element2ViewMap, Set elements) {
            if (elements.size() == element2ViewMap.size())
                return element2ViewMap;

            if (parentView.isSetElement() && !element2ViewMap.containsKey(parentView.getElement()) && elements.contains(parentView.getElement())) {
                element2ViewMap.put(parentView.getElement(), parentView);
                if (elements.size() == element2ViewMap.size())
                    return element2ViewMap;
            }

            for (Iterator it = parentView.getChildren().iterator(); it.hasNext();) {
                buildElement2ViewMap((View) it.next(), element2ViewMap, elements);
                if (elements.size() == element2ViewMap.size())
                    return element2ViewMap;
            }
            for (Iterator it = parentView.getSourceEdges().iterator(); it.hasNext();) {
                buildElement2ViewMap((View) it.next(), element2ViewMap, elements);
                if (elements.size() == element2ViewMap.size())
                    return element2ViewMap;
            }
            for (Iterator it = parentView.getSourceEdges().iterator(); it.hasNext();) {
                buildElement2ViewMap((View) it.next(), element2ViewMap, elements);
                if (elements.size() == element2ViewMap.size())
                    return element2ViewMap;
            }
            return element2ViewMap;
        }
    } // LazyElement2ViewMap

}
