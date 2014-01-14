/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.wizards;

// Start of user code imports

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.viewpoint.description.style.StyleFactory;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

// End of user code imports

/**
 * This is a simple wizard for creating a new model file.
 */
public class StyleModelWizard extends Wizard implements INewWizard {
    /**
     * This caches an instance of the model package.
     */
    protected StylePackage stylePackage = StylePackage.eINSTANCE;

    /**
     * This caches an instance of the model factory.
     */
    protected StyleFactory styleFactory = stylePackage.getStyleFactory();

    /**
     * This is the file creation page.
     */
    protected StyleModelWizardNewFileCreationPage newFileCreationPage;

    /**
     * This is the initial object creation page.
     */
    protected StyleModelWizardInitialObjectCreationPage initialObjectCreationPage;

    /**
     * Remember the selection during initialization for populating the default
     * container.
     */
    protected IStructuredSelection selection;

    /**
     * Remember the workbench during initialization.
     */
    protected IWorkbench workbench;

    /**
     * Caches the names of the types that can be created as the root object.
     */
    protected List<String> initialObjectNames;

    /**
     * This just records the information.
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        setWindowTitle(SiriusEditorPlugin.INSTANCE.getString("_UI_Wizard_label"));
        setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(SiriusEditorPlugin.INSTANCE.getImage("full/wizban/NewModel")));
    }

    /**
     * Returns the names of the types that can be created as the root object.
     */
    protected Collection<String> getInitialObjectNames() {
        if (initialObjectNames == null) {
            initialObjectNames = new ArrayList<String>();
            for (Iterator<EClassifier> classifiers = stylePackage.getEClassifiers().iterator(); classifiers.hasNext();) {
                EClassifier eClassifier = classifiers.next();
                if (eClassifier instanceof EClass) {
                    EClass eClass = (EClass) eClassifier;
                    if (!eClass.isAbstract()) {
                        initialObjectNames.add(eClass.getName());
                    }
                }
            }
            Collections.sort(initialObjectNames, java.text.Collator.getInstance());
        }

        // Start of user code getInitialObjectNames

        // End of user code getInitialObjectNames
        return initialObjectNames;
    }

    /**
     * Create a new model.
     */
    protected EObject createInitialModel() {
        EClass eClass = (EClass) stylePackage.getEClassifier(initialObjectCreationPage.getInitialObjectName());
        EObject rootObject = styleFactory.create(eClass);

        // Start of user code createInitialModel

        // End of user code createInitialModel

        return rootObject;
    }

    /**
     * Do the work after everything is specified.
     */
    public boolean performFinish() {
        try {
            // Remember the file.
            //
            final IFile modelFile = getModelFile();

            // Do the work within an operation.
            //
            WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
                protected void execute(IProgressMonitor progressMonitor) {
                    try {
                        // Create a resource set
                        //
                        ResourceSet resourceSet = new ResourceSetImpl();

                        // Get the URI of the model file.
                        //
                        URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);

                        // Create a resource for this file.
                        //
                        Resource resource = resourceSet.createResource(fileURI);

                        // Add the initial model object to the contents.
                        //
                        EObject rootObject = createInitialModel();
                        if (rootObject != null) {
                            resource.getContents().add(rootObject);
                        }

                        // Save the contents of the resource to the file system.
                        //
                        Map<Object, Object> options = new HashMap<Object, Object>();
                        options.put(XMLResource.OPTION_ENCODING, initialObjectCreationPage.getEncoding());
                        resource.save(options);
                    } catch (Exception exception) {
                        SiriusEditorPlugin.INSTANCE.log(exception);
                    } finally {
                        progressMonitor.done();
                    }
                }
            };

            getContainer().run(false, false, operation);

            // Select the new file resource in the current view.
            //
            IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
            IWorkbenchPage page = workbenchWindow.getActivePage();
            final IWorkbenchPart activePart = page.getActivePart();
            if (activePart instanceof ISetSelectionTarget) {
                final ISelection targetSelection = new StructuredSelection(modelFile);
                getShell().getDisplay().asyncExec(new Runnable() {
                    public void run() {
                        ((ISetSelectionTarget) activePart).selectReveal(targetSelection);
                    }
                });
            }

            // Open an editor on the new file.
            //
            try {
                page.openEditor(new FileEditorInput(modelFile), workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString()).getId());
            } catch (PartInitException exception) {
                MessageDialog.openError(workbenchWindow.getShell(), SiriusEditorPlugin.INSTANCE.getString("_UI_OpenEditorError_label"), exception.getMessage());
                return false;
            }

            return true;
        } catch (Exception exception) {
            SiriusEditorPlugin.INSTANCE.log(exception);
            return false;
        }
    }

    /**
     * This is the one page of the wizard.
     */
    public class StyleModelWizardNewFileCreationPage extends WizardNewFileCreationPage {
        /**
         * Pass in the selection.
         */
        public StyleModelWizardNewFileCreationPage(String pageId, IStructuredSelection selection) {
            super(pageId, selection);
        }

        /**
         * The framework calls this to see if the file is correct.
         */
        protected boolean validatePage() {
            if (super.validatePage()) {
                // Make sure the file ends in ".style".
                //
                String requiredExt = SiriusEditorPlugin.INSTANCE.getString("_UI_SiriusEditorFilenameExtension");
                String enteredExt = new Path(getFileName()).getFileExtension();
                if (enteredExt == null || !enteredExt.equals(requiredExt)) {
                    setErrorMessage(SiriusEditorPlugin.INSTANCE.getString("_WARN_FilenameExtension", new Object[] { requiredExt }));
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }

        public IFile getModelFile() {
            return ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName()));
        }
    }

    /**
     * This is the page where the type of object to create is selected.
     */
    public class StyleModelWizardInitialObjectCreationPage extends WizardPage {
        protected Combo initialObjectField;

        protected List<String> encodings;

        protected Combo encodingField;

        /**
         * Pass in the selection.
         */
        public StyleModelWizardInitialObjectCreationPage(String pageId) {
            super(pageId);
        }

        public void createControl(Composite parent) {
            Composite composite = new Composite(parent, SWT.NONE);
            {
                GridLayout layout = new GridLayout();
                layout.numColumns = 1;
                layout.verticalSpacing = 12;
                composite.setLayout(layout);

                GridData data = new GridData();
                data.verticalAlignment = GridData.FILL;
                data.grabExcessVerticalSpace = true;
                data.horizontalAlignment = GridData.FILL;
                composite.setLayoutData(data);
            }

            Label containerLabel = new Label(composite, SWT.LEFT);
            {
                containerLabel.setText(SiriusEditorPlugin.INSTANCE.getString("_UI_ModelObject"));

                GridData data = new GridData();
                data.horizontalAlignment = GridData.FILL;
                containerLabel.setLayoutData(data);
            }

            initialObjectField = new Combo(composite, SWT.BORDER);
            {
                GridData data = new GridData();
                data.horizontalAlignment = GridData.FILL;
                data.grabExcessHorizontalSpace = true;
                initialObjectField.setLayoutData(data);
            }

            for (Iterator<String> i = getInitialObjectNames().iterator(); i.hasNext();) {
                initialObjectField.add(getLabel(i.next()));
            }

            if (initialObjectField.getItemCount() == 1) {
                initialObjectField.select(0);
            }
            initialObjectField.addModifyListener(validator);

            Label encodingLabel = new Label(composite, SWT.LEFT);
            {
                encodingLabel.setText(SiriusEditorPlugin.INSTANCE.getString("_UI_XMLEncoding"));

                GridData data = new GridData();
                data.horizontalAlignment = GridData.FILL;
                encodingLabel.setLayoutData(data);
            }
            encodingField = new Combo(composite, SWT.BORDER);
            {
                GridData data = new GridData();
                data.horizontalAlignment = GridData.FILL;
                data.grabExcessHorizontalSpace = true;
                encodingField.setLayoutData(data);
            }

            for (Iterator<String> i = getEncodings().iterator(); i.hasNext();) {
                encodingField.add(i.next());
            }

            encodingField.select(0);
            encodingField.addModifyListener(validator);

            setPageComplete(validatePage());
            setControl(composite);
        }

        protected ModifyListener validator = new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                setPageComplete(validatePage());
            }
        };

        protected boolean validatePage() {
            return getInitialObjectName() != null && getEncodings().contains(encodingField.getText());
        }

        public void setVisible(boolean visible) {
            super.setVisible(visible);
            if (visible) {
                if (initialObjectField.getItemCount() == 1) {
                    initialObjectField.clearSelection();
                    encodingField.setFocus();
                } else {
                    encodingField.clearSelection();
                    initialObjectField.setFocus();
                }
            }
        }

        public String getInitialObjectName() {
            String label = initialObjectField.getText();

            for (Iterator<String> i = getInitialObjectNames().iterator(); i.hasNext();) {
                String name = i.next();
                if (getLabel(name).equals(label)) {
                    return name;
                }
            }
            return null;
        }

        public String getEncoding() {
            return encodingField.getText();
        }

        /**
         * Returns the label for the specified type name.
         */
        protected String getLabel(String typeName) {
            try {
                return org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin.INSTANCE.getString("_UI_" + typeName + "_type");
            } catch (MissingResourceException mre) {
                SiriusEditorPlugin.INSTANCE.log(mre);
            }
            return typeName;
        }

        protected Collection<String> getEncodings() {
            if (encodings == null) {
                encodings = new ArrayList<String>();
                for (StringTokenizer stringTokenizer = new StringTokenizer(SiriusEditorPlugin.INSTANCE.getString("_UI_XMLEncodingChoices")); stringTokenizer.hasMoreTokens();) {
                    encodings.add(stringTokenizer.nextToken());
                }
            }
            return encodings;
        }
    }

    /**
     * The framework calls this to create the contents of the wizard.
     */
    public void addPages() {
        // Create a page, set the title, and the initial model file name.
        //
        newFileCreationPage = new StyleModelWizardNewFileCreationPage("Whatever", selection);
        newFileCreationPage.setTitle(SiriusEditorPlugin.INSTANCE.getString("_UI_StyleModelWizard_label"));
        newFileCreationPage.setDescription(SiriusEditorPlugin.INSTANCE.getString("_UI_StyleModelWizard_description"));
        newFileCreationPage
                .setFileName(SiriusEditorPlugin.INSTANCE.getString("_UI_SiriusEditorFilenameDefaultBase") + "." + SiriusEditorPlugin.INSTANCE.getString("_UI_SiriusEditorFilenameExtension"));
        addPage(newFileCreationPage);

        // Try and get the resource selection to determine a current directory
        // for the file dialog.
        //
        if (selection != null && !selection.isEmpty()) {
            // Get the resource...
            //
            Object selectedElement = selection.iterator().next();
            if (selectedElement instanceof IResource) {
                // Get the resource parent, if it's a file.
                //
                IResource selectedResource = (IResource) selectedElement;
                if (selectedResource.getType() == IResource.FILE) {
                    selectedResource = selectedResource.getParent();
                }

                // This gives us a directory...
                //
                if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
                    // Set this for the container.
                    //
                    newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

                    // Make up a unique new name here.
                    //
                    String defaultModelBaseFilename = SiriusEditorPlugin.INSTANCE.getString("_UI_SiriusEditorFilenameDefaultBase");
                    String defaultModelFilenameExtension = SiriusEditorPlugin.INSTANCE.getString("_UI_SiriusEditorFilenameExtension");
                    String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;
                    for (int i = 1; ((IContainer) selectedResource).findMember(modelFilename) != null; ++i) {
                        modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension;
                    }
                    newFileCreationPage.setFileName(modelFilename);
                }
            }
        }
        initialObjectCreationPage = new StyleModelWizardInitialObjectCreationPage("Whatever2");
        initialObjectCreationPage.setTitle(SiriusEditorPlugin.INSTANCE.getString("_UI_StyleModelWizard_label"));
        initialObjectCreationPage.setDescription(SiriusEditorPlugin.INSTANCE.getString("_UI_Wizard_initial_object_description"));
        addPage(initialObjectCreationPage);
    }

    /**
     * Get the file from the page.
     */
    public IFile getModelFile() {
        return newFileCreationPage.getModelFile();
    }
}
