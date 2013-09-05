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
package org.eclipse.sirius.ecore.design.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.codegen.ecore.ui.EmptyProjectWizard;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import com.google.common.collect.Lists;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.DView;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.description.Sirius;
import org.eclipse.sirius.provider.SiriusEditPlugin;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.wizards.page.SiriussSelectionWizardPage;
import org.eclipse.sirius.ecore.design.service.EcoreSamplePlugin;

/**
 * Wizard to create an EMF project with the {@link ModelingProject} nature, an
 * Ecore model and its associated genmodel and an aird file.
 * 
 * @author mporhel
 */
public class EcoreModelerWizard extends EmptyProjectWizard {

    /** The name of the Design viewpoint. */
    private static final String DESIGN_VIEWPOINT_NAME = "Design"; //$NON-NLS-1$ 

    private EcoreModelSpecPage modelPage;

    private SiriussSelectionWizardPage viewpointsSelectionWizardPage;

    /**
     * Constructor.
     */
    public EcoreModelerWizard() {
        super();
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        super.init(workbench, selection);
        setWindowTitle("New Ecore Modeling Project"); //$NON-NLS-1$ 
        setDefaultPageImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(EcoreSamplePlugin.PLUGIN_ID, "icons/full/wizban/EcoreModelingProject_wizban.png"));
        setNeedsProgressMonitor(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        WizardNewProjectCreationPage wizardNewProjectCreationPage = new WizardNewProjectCreationPage(SiriusEditPlugin.getPlugin().getString("_UI_ModelingProjectWizard_label")) {

            @Override
            public void createControl(Composite parent) {
                super.createControl(parent);

                Composite control = (Composite) getControl();
                GridLayout layout = new GridLayout();
                control.setLayout(layout);

                createWorkingSetGroup(control, new StructuredSelection(), new String[] { "org.eclipse.jdt.ui.JavaWorkingSetPage", //$NON-NLS-1$
                        "org.eclipse.pde.ui.pluginWorkingSet", "org.eclipse.ui.resourceWorkingSetPage" }); //$NON-NLS-1$ //$NON-NLS-2$

                Dialog.applyDialogFont(control);
            }

            @Override
            protected boolean validatePage() {
                if (super.validatePage()) {
                    IPath locationPath = getLocationPath();
                    genModelProjectLocation = Platform.getLocation().equals(locationPath) ? null : locationPath;
                    IPath projectPath = getProjectHandle().getFullPath();
                    genModelContainerPath = projectPath.append("src"); //$NON-NLS-1$ 

                    updateModelPagePackageName(getProjectName());
                    return true;
                } else {
                    return false;
                }
            }

            private void updateModelPagePackageName(String projectName) {
                if (!StringUtil.isEmpty(projectName)) {
                    String packageName = getPackageName(projectName);
                    if (!StringUtil.isEmpty(packageName)) {
                        modelPage.setPackageNameSilently(packageName);
                    }
                }
            }

        };

        wizardNewProjectCreationPage.setInitialProjectName(initialProjectName);
        wizardNewProjectCreationPage.setTitle("Create a new Ecore Modeling project"); //$NON-NLS-1$ 
        wizardNewProjectCreationPage.setDescription("Enter a project name"); //$NON-NLS-1$    

        modelPage = new EcoreModelSpecPage("Ecore model parameters"); //$NON-NLS-1$ 
        modelPage.setTitle("Model settings"); //$NON-NLS-1$ 
        modelPage.setDescription("Define the model settings"); //$NON-NLS-1$ 

        viewpointsSelectionWizardPage = new SiriussSelectionWizardPage(null, Lists.newArrayList(DESIGN_VIEWPOINT_NAME)) {
            @Override
            protected Collection<String> computeSemanticFileExtensions(Session session) {
                Set<String> fileExtensions = new HashSet<String>();
                fileExtensions.add("ecore");
                return fileExtensions;
            }
        };

        // Fix for VP-3711 to avoid a NPE on 3.8
        ReflectionHelper.setFieldValueWithoutException(this, "newProjectCreationPage", wizardNewProjectCreationPage);

        addPage(wizardNewProjectCreationPage);
        addPage(modelPage);
        addPage(viewpointsSelectionWizardPage);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        boolean finished = super.performFinish();

        if (finished && project != null) {
            final EcoreModelingProjectCreationOperation ecoreModelingProjectCreationOperation = new EcoreModelingProjectCreationOperation(project, modelPage.getEPackage(),
                    modelPage.getEcoreFileName(), modelPage.getGenModelFileName(), modelPage.getRepresentationFileName(), new LinkedHashSet<Sirius>(viewpointsSelectionWizardPage.getSiriuss()));
            try {
                getContainer().run(true, false, ecoreModelingProjectCreationOperation);
            } catch (InvocationTargetException e) {
                IStatus status = new Status(IStatus.ERROR, EcoreSamplePlugin.PLUGIN_ID, IStatus.ERROR, e.getMessage(), e);
                EcoreSamplePlugin.getDefault().getLog().log(status);
                finished = false;
            } catch (InterruptedException e) {
                IStatus status = new Status(IStatus.ERROR, EcoreSamplePlugin.PLUGIN_ID, IStatus.ERROR, e.getMessage(), e);
                EcoreSamplePlugin.getDefault().getLog().log(status);
                finished = false;
            }
            try {
                getContainer().run(false, false, new IRunnableWithProgress() {

                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        try {
                            monitor.beginTask("Open the diagram metamodel", 2);

                            IFile ecoreModel = ecoreModelingProjectCreationOperation.getEcoreModel();
                            if (ecoreModel != null && ecoreModel.exists()) {
                                selectAndReveal(ecoreModel);
                            } else {
                                selectAndReveal(project);
                            }
                            monitor.worked(1);

                            // Open the entities diagram, of the root of the
                            // ecore
                            // file,
                            // that is automatically created (because the
                            // Entities
                            // diagram has
                            // the "initialization" to true).
                            // This can fail if the user deselects the "Design"
                            // viewpoint in the second page of the wizard.
                            openFirstRepresentation(project, new SubProgressMonitor(monitor, 1));
                        } finally {
                            monitor.done();
                        }
                    }
                });
            } catch (InvocationTargetException e) {
                IStatus status = new Status(IStatus.ERROR, EcoreSamplePlugin.PLUGIN_ID, IStatus.ERROR, e.getMessage(), e);
                EcoreSamplePlugin.getDefault().getLog().log(status);
                finished = false;
            } catch (InterruptedException e) {
                IStatus status = new Status(IStatus.ERROR, EcoreSamplePlugin.PLUGIN_ID, IStatus.ERROR, e.getMessage(), e);
                EcoreSamplePlugin.getDefault().getLog().log(status);
                finished = false;
            }
        }
        return finished;
    }

    /**
     * Selects and reveals the newly added resource in all parts of the active
     * workbench window's active page.
     * 
     * @see ISetSelectionTarget
     */
    protected void selectAndReveal(IResource newResource) {
        BasicNewResourceWizard.selectAndReveal(newResource, workbench.getActiveWorkbenchWindow());
        EclipseUIUtil.expand(newResource, workbench.getActiveWorkbenchWindow());
    }

    /**
     * Open the first representation containing in the representation file of
     * this Modeling project.
     * 
     * @param project
     *            The modeling project containing the representations file.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of first
     *            {@link DRepresentation} opening
     */
    private void openFirstRepresentation(IProject project, IProgressMonitor monitor) {
        Option<ModelingProject> opionalModelingProject = ModelingProject.asModelingProject(project);
        if (opionalModelingProject.some()) {
            Session session = opionalModelingProject.get().getSession();
            if (session != null) {
                if (!session.getSelectedViews().isEmpty()) {
                    DView view = session.getSelectedViews().iterator().next();
                    if (!view.getOwnedRepresentations().isEmpty()) {
                        DRepresentation representationToOpen = view.getOwnedRepresentations().get(0);
                        DialectUIManager.INSTANCE.openEditor(session, representationToOpen, monitor);
                    }
                }
            }
        }
    }

    private String getPackageName(String projectName) {
        String packageName = projectName.substring(0, 1).toLowerCase() + projectName.substring(1);
        int index = packageName.lastIndexOf('.');
        if (index != -1) {
            packageName = packageName.substring(index + 1);
        }
        return packageName;
    }

    private class EcoreModelSpecPage extends WizardPage {

        private static final String NAMESPACE_GROUP_LABEL = "Namespace properties";//$NON-NLS-1$ 

        private static final String NS_PREFIX_FIELD_LABEL = "Ns Prefix"; //$NON-NLS-1$ 

        private static final String NS_URI_FIELD_LABEL = "Ns Uri"; //$NON-NLS-1$ 

        private static final String USE_DEFAULT_LABEL = "Use default namespace parameters"; //$NON-NLS-1$ 

        private static final String PACKAGE_FIELD_LABEL = "Main package name"; //$NON-NLS-1$ 

        private static final String DEFAULT_URI_BASE = "http://www.example.org/"; //$NON-NLS-1$ 

        private static final String DEFAULT_COMMON_FILE_NAME = "model"; //$NON-NLS-1$ 

        // widgets
        private Text packageNameField;

        private Text nsPrefixField;

        private Text nsUriField;

        private Button useDefaultsButton;

        private EPackage ePackage;

        private boolean silentModificationEnabled = true;

        private final ModifyListener nsModifyListener = new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                if (!(useDefaultsButton != null && useDefaultsButton.getSelection())) {
                    setPageComplete(validatePage());
                }
            }
        };

        public EcoreModelSpecPage(String name) {
            super(name);
        }

        /**
         * Allow an other page to set the package name until the user manualy
         * modify it.
         * 
         * @param packageName
         *            the package name to set.
         */
        public void setPackageNameSilently(String packageName) {
            if (packageNameField != null && silentModificationEnabled) {
                packageNameField.setText(packageName);
            }
        }

        /**
         * {@inheritDoc}
         */
        public void createControl(Composite parent) {
            Composite composite = new Composite(parent, SWT.NULL);
            composite.setLayout(new GridLayout());
            composite.setLayoutData(new GridData(GridData.FILL_BOTH));

            createModelGroup(composite);
            createNamespaceGroup(composite, false);

            setPageComplete(validatePage());

            setControl(composite);
            Dialog.applyDialogFont(composite);
        }

        private void createModelGroup(Composite parent) {
            packageNameField = createLabelAndText(parent, PACKAGE_FIELD_LABEL, getPackageName());
            packageNameField.addModifyListener(new ModifyListener() {

                public void modifyText(ModifyEvent e) {
                    if (packageNameField.isVisible()) {
                        silentModificationEnabled = false;
                    }
                    updateNamespaceValues();
                    setPageComplete(validatePage());
                }
            });

        }

        private void createNamespaceGroup(Composite composite, boolean custom) {
            Group nsGroup = new Group(composite, SWT.NONE);
            nsGroup.setFont(composite.getFont());
            nsGroup.setText(NAMESPACE_GROUP_LABEL);
            nsGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
            nsGroup.setLayout(new GridLayout(1, false));

            useDefaultsButton = new Button(nsGroup, SWT.CHECK | SWT.RIGHT);
            useDefaultsButton.setText(USE_DEFAULT_LABEL);

            GridData buttonData = new GridData();
            buttonData.horizontalSpan = 4;
            useDefaultsButton.setLayoutData(buttonData);
            useDefaultsButton.setSelection(!custom);

            nsUriField = createLabelAndText(nsGroup, NS_URI_FIELD_LABEL, getNsUri());
            nsPrefixField = createLabelAndText(nsGroup, NS_PREFIX_FIELD_LABEL, getNsPRefix());

            nsUriField.addModifyListener(nsModifyListener);
            nsPrefixField.addModifyListener(nsModifyListener);
            setNameSpaceGroupActivation(custom);

            useDefaultsButton.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    boolean useDefault = useDefaultsButton.getSelection();
                    if (useDefault) {
                        updateNamespaceValues();
                    }
                    setNameSpaceGroupActivation(!useDefault);
                    setPageComplete(validatePage());
                }
            });
        }

        private void setNameSpaceGroupActivation(boolean enabled) {
            if (nsUriField != null) {
                nsUriField.setEnabled(enabled);
            }

            if (nsPrefixField != null) {
                nsPrefixField.setEnabled(enabled);
            }
        }

        private void updateNamespaceValues() {
            if (useDefaultsButton != null && useDefaultsButton.getSelection()) {
                String packageName = getPackageName();
                if (nsPrefixField != null) {
                    nsPrefixField.setText(packageName);
                }

                if (nsUriField != null) {
                    nsUriField.setText(getDefaultNsUri(packageName));
                }
            }
        }

        private Text createLabelAndText(Composite parent, String label, String initialValue) {
            Composite group = new Composite(parent, SWT.NONE);
            GridLayout layout = new GridLayout();
            layout.numColumns = 2;
            group.setLayout(layout);
            group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

            // label
            Label labelField = new Label(group, SWT.NONE);
            labelField.setText(label);
            labelField.setFont(parent.getFont());

            // text
            Text textField = new Text(group, SWT.BORDER);
            GridData data = new GridData(GridData.FILL_HORIZONTAL);
            data.widthHint = 250;
            textField.setLayoutData(data);
            textField.setFont(parent.getFont());
            if (initialValue != null) {
                textField.setText(initialValue);
            }
            return textField;
        }

        public EPackage getEPackage() {
            if (ePackage == null) {
                initEPackage();
            }
            return ePackage;
        }

        private void initEPackage() {
            EClass eClass = (EClass) EcorePackage.eINSTANCE.getEClassifier("EPackage");
            EObject rootObject = EcoreFactory.eINSTANCE.create(eClass);

            if (rootObject instanceof EPackage) {
                ePackage = (EPackage) rootObject;
                updateEPackage(ePackage);
            }
        }

        private void updateEPackage(EPackage p) {
            p.setName(getPackageName());
            p.setNsPrefix(getNsPRefix());
            p.setNsURI(getNsUri());
        }

        /**
         * Returns whether this page's controls currently all contain valid
         * values.
         * 
         * @return <code>true</code> if all controls are valid, and
         *         <code>false</code> if at least one is invalid
         */
        protected boolean validatePage() {
            if (ePackage == null) {
                initEPackage();
            } else {
                updateEPackage(ePackage);
            }

            EcoreValidator validator = new EcoreValidator();
            BasicDiagnostic diagnostic = new BasicDiagnostic();
            boolean validName = validator.validateENamedElement_WellFormedName(ePackage, diagnostic, null);
            boolean validNsUri = validator.validateEPackage_WellFormedNsURI(ePackage, diagnostic, null);
            boolean validNsPrefix = validator.validateEPackage_WellFormedNsPrefix(ePackage, diagnostic, null);

            boolean validEPackage = validName && validNsUri && validNsPrefix;
            if (validEPackage) {
                setErrorMessage(null);
            } else {
                StringBuilder sb = new StringBuilder();
                buildCompoundMessage(sb, diagnostic);
                setErrorMessage(sb.toString());
            }
            return validEPackage;
        }

        private void buildCompoundMessage(StringBuilder sb, Diagnostic diagnostic) {
            if (!StringUtil.isEmpty(diagnostic.getMessage()) && diagnostic.getSeverity() != Diagnostic.OK) {
                sb.append(diagnostic.getMessage());
                sb.append("\n");
            }

            for (Diagnostic child : diagnostic.getChildren()) {
                buildCompoundMessage(sb, child);
            }
        }

        private String getPackageName() {
            if (packageNameField == null) {
                return DEFAULT_COMMON_FILE_NAME;
            }

            return packageNameField.getText().trim();
        }

        private String getNsUri() {
            if (nsUriField == null) {
                return getDefaultNsUri(getPackageName());
            }

            return nsUriField.getText().trim();
        }

        private String getDefaultNsUri(String packageName) {
            return DEFAULT_URI_BASE + packageName;
        }

        private String getNsPRefix() {
            if (nsPrefixField == null) {
                return getPackageName();
            }

            return nsPrefixField.getText().trim();
        }

        public String getRepresentationFileName() {
            return getPackageName() + "." + SiriusUtil.SESSION_RESOURCE_EXTENSION; //$NON-NLS-1$ 
        }

        public String getEcoreFileName() {
            return getPackageName() + ".ecore"; //$NON-NLS-1$ 
        }

        public String getGenModelFileName() {
            return getPackageName() + ".genmodel"; //$NON-NLS-1$ 
        }
    }
}
