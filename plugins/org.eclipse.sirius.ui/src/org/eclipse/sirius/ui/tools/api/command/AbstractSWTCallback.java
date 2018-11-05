/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.api.command;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.helper.SelectionDescriptionHelper;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.migration.AirdResourceVersionMismatchException;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileVersionSAXParser;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.api.selection.EMFMessageDialog;
import org.eclipse.sirius.common.ui.tools.api.selection.EObjectSelectionWizard;
import org.eclipse.sirius.common.ui.tools.api.selection.WizardDialogClosableByWizard;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.common.ui.tools.internal.util.MigrationUIUtil;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.resource.LoadEMFResourceRunnableWithProgress;
import org.eclipse.sirius.ui.tools.api.Messages;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.selection.TypedVariableValueDialog;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.TypedVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * Implementation of the {@link UICallBack} interface using SWT.
 *
 * @author mchauvin
 * @since 0.9.0
 */
public abstract class AbstractSWTCallback implements UICallBack {
    @Override
    public Collection<EObject> askForVariableValues(final EObject model, final SelectModelElementVariable variable) throws InterruptedException {
        Collection<EObject> variableValues = new ArrayList<EObject>();
        final TreeItemWrapper input = new TreeItemWrapper(null, null);
        computeInput(model, variable, input);
        final EObjectSelectionWizard wizard = new EObjectSelectionWizard(EObjectSelectionWizard.WIZARD_GENERIC_DIALOG_TITLE, variable.getMessage(), null, input,
                ViewHelper.INSTANCE.createAdapterFactory());
        wizard.setMany(variable.isMultiple());
        final WizardDialogClosableByWizard dialog = new WizardDialogClosableByWizard(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
        wizard.setDialog(dialog);
        final int result = dialog.open();
        if (result == Window.OK) {
            if (!variable.isMultiple() && wizard.getSelectedEObject() != null) {
                variableValues.add(wizard.getSelectedEObject());
            } else {
                final EList<EObject> value = new BasicEList<EObject>();
                value.addAll(wizard.getSelectedEObjects());
                variableValues = value;
            }
        } else {
            throw new InterruptedException();
        }
        return variableValues;
    }

    private void computeInput(final EObject model, final SelectModelElementVariable variable, final TreeItemWrapper input) {
        if (model != null) {
            final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(model);
            if (variable.getCandidatesExpression() != null) {
                final DRepresentation representation = model instanceof DRepresentationElement ? SiriusUtil.findRepresentation((DRepresentationElement) model) : null;
                interpreter.setVariable(this.getVariableNameForRepresentation(), representation);
                SelectionDescriptionHelper.computeInput(variable, model, interpreter, input);
                interpreter.unSetVariable(this.getVariableNameForRepresentation());
            }
        }
    }

    /**
     * Get the interpreter variable name for representation.
     *
     * @return the variable interpreter name for representation
     */
    protected abstract String getVariableNameForRepresentation();

    @Override
    public String askForDetailName(final String defaultName) throws InterruptedException {
        return askForDetailName(defaultName, null);
    }

    @Override
    @Deprecated
    public String askForDetailName(final String defaultName, final String representationDescriptionDoc) throws InterruptedException {
        return askForDetailName(defaultName, Messages.createRepresentationInputDialog_DefaultRepresentationDescName, representationDescriptionDoc);
    }

    @Override
    public String askForDetailName(final String defaultName, final String representationDescriptionName, final String representationDescriptionDoc) throws InterruptedException {
        String description = null;
        if (representationDescriptionDoc != null && representationDescriptionDoc.trim().length() > 0) {
            description = MessageFormat.format(Messages.createRepresentationInputDialog_RepresentationDescriptionLabel, representationDescriptionDoc);
        }
        if (description == null) {
            description = ""; //$NON-NLS-1$
        } else {
            description += "\n\n"; //$NON-NLS-1$
        }
        description += Messages.createRepresentationInputDialog_NewRepresentationNameLabel;
        final InputDialog askSiriusName = new InputDialog(Display.getDefault().getActiveShell(), MessageFormat.format(Messages.createRepresentationInputDialog_Title, representationDescriptionName),
                description, defaultName, new IInputValidator() {

                    @Override
                    public String isValid(final String newText) {
                        return null;
                    }
                });
        if (askSiriusName.open() == Window.OK) {
            return askSiriusName.getValue();
        }
        throw new InterruptedException(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_askForDetailName_canceled);
    }

    @Override
    public boolean openEObjectsDialogMessage(final Collection<EObject> objects, final String title, final String message) {
        return EMFMessageDialog.openQuestionWithEObjects(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), ViewHelper.INSTANCE.createAdapterFactory(), objects, title, message);
    }

    @Override
    public void openRepresentation(final Session openedSession, final DRepresentation representation) {
        try {
            new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    DialectUIManager.INSTANCE.openEditor(openedSession, representation, monitor);
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
        }
    }

    @Override
    public Resource loadResource(final EditingDomain domain, final IFile file) {
        final LoadEMFResourceRunnableWithProgress operation = new LoadEMFResourceRunnableWithProgress(domain.getResourceSet(), file);
        try {
            operation.run(new NullProgressMonitor());
        } catch (InvocationTargetException | InterruptedException e) {
            SiriusTransPlugin.INSTANCE.error(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_loadResourceError, e);
        }
        return operation.getLoadedResource();
    }

    @Override
    public Collection<EObject> askForEObjects(String message, TreeItemWrapper input, AdapterFactory factory) throws InterruptedException {
        final EObjectSelectionWizard wizard = new EObjectSelectionWizard(EObjectSelectionWizard.WIZARD_GENERIC_DIALOG_TITLE, message, null, input, factory);
        wizard.setMany(true);
        final WizardDialogClosableByWizard dialog = new WizardDialogClosableByWizard(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
        wizard.setDialog(dialog);
        final int result = dialog.open();
        if (result == Window.OK) {
            return wizard.getSelectedEObjects();
        }
        throw new InterruptedException();
    }

    @Override
    public EObject askForEObject(String message, TreeItemWrapper input, AdapterFactory factory) throws InterruptedException {
        final EObjectSelectionWizard wizard = new EObjectSelectionWizard(EObjectSelectionWizard.WIZARD_GENERIC_DIALOG_TITLE, message, null, input, factory);
        wizard.setMany(false);
        final WizardDialogClosableByWizard dialog = new WizardDialogClosableByWizard(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
        wizard.setDialog(dialog);
        final int result = dialog.open();
        if (result == Window.OK) {
            return wizard.getSelectedEObject();
        }
        throw new InterruptedException();
    }

    @Override
    public List<String> askForTypedVariable(List<TypedVariable> typedVariableList, List<String> defaultValues) throws InterruptedException {
        final TypedVariableValueDialog dialog = new TypedVariableValueDialog(typedVariableList, defaultValues, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        final int result = dialog.open();
        if (result == Window.OK) {
            return dialog.getValues();
        }
        throw new InterruptedException();
    }

    @Override
    public boolean shouldReload(final Resource resource) {
        return openQuestion(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_shouldReload_title,
                MessageFormat.format(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_shouldReload_message, resource.getURI()));
    }

    @Override
    public boolean shouldRemove(final Resource resource) {
        return openQuestion(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_shouldRemove_title,
                MessageFormat.format(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_shouldRemove_message, resource.getURI()));
    }

    @Override
    public boolean shouldClose(final Session session, final Resource resource) {
        return openQuestion(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_shouldClose_title,
                MessageFormat.format(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_shouldClose_message, resource.getURI()));
    }

    @Override
    public boolean shouldDeleteRepresentation(Set<DRepresentationDescriptor> repDescriptors) {
        String deleteRepresenationDialogTitle = org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_DeleteRepresentationAction_title;
        String deletionMessage = org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_DeleteRepresentationAction_message;
        if (repDescriptors.size() >= 2) {
            deleteRepresenationDialogTitle = org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_DeleteRepresentationAction_title_plural;
            deletionMessage = org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_DeleteRepresentationAction_message_plural;
        }
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        return MessageDialog.openConfirm(shell, deleteRepresenationDialogTitle, deletionMessage);
    }

    /**
     * Convenience method to open a simple Yes/No question dialog.
     *
     * @param title
     *            the dialog's title, or <code>null</code> if none
     * @param message
     *            the message
     * @return <code>true</code> if the user presses the Yes button, <code>false</code> otherwise
     */
    private boolean openQuestion(final String title, final String message) {
        if (inUIThread()) {
            return MessageDialog.openQuestion(getActiveShell(), title, message);
        } else {
            RunnableWithResult<Boolean> reload = new RunnableWithResult.Impl<Boolean>() {
                @Override
                public void run() {
                    setResult(MessageDialog.openQuestion(getActiveShell(), title, message));
                }
            };
            EclipseUIUtil.displaySyncExec(reload);
            return reload.getResult();
        }
    }

    /**
     * Return an expression describing what is saving :
     * <UL>
     * <LI>"Models" if only semantic files have been modified,</LI>
     * <LI>"Representations" if only representations files have been modified,</LI>
     * <LI>"Models and Representations" if both.</LI>
     * </UL>
     * suffixed with :
     * <UL>
     * <LI>"project's session name" if the session has a second segment (that is the project name).</LI>
     * <LI>"toPlatformString URI" if the session uses a InMemoryQuery</LI>
     * <LI>"the toString URI" otherwise.</LI>
     * </UL>
     * <BR>
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#getSessionDisplayed(org.eclipse.sirius.business.api.session.Session)
     */
    @Override
    public String getSessionNameToDisplayWhileSaving(Session session) {
        String result = ""; //$NON-NLS-1$
        if (session != null) {
            String projectName = null;
            Resource representationsFileResource = session.getSessionResource();
            URI representationsFileURI = representationsFileResource.getURI();
            if (representationsFileURI.segments().length > 1) {
                projectName = representationsFileURI.segment(1);
            }

            final String location;
            final boolean inProject;
            URIQuery uriQuery = new URIQuery(representationsFileURI);
            if (projectName != null) {
                location = projectName;
                inProject = true;
            } else {
                location = uriQuery.isInMemoryURI() ? uriQuery.toPlatformString() : representationsFileURI.toString();
                inProject = false;
            }

            if (ResourceSetSync.getStatus(representationsFileResource).equals(ResourceStatus.SYNC)) {
                if (inProject) {
                    result = MessageFormat.format(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_modelsInProject, location);
                } else {
                    result = MessageFormat.format(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_models, location);
                }
            } else if (semanticResourcesDirty(session)) {
                if (inProject) {
                    result = MessageFormat.format(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_modelsAndRepresentationsInProject, location);
                } else {
                    result = MessageFormat.format(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_modelsAndRepresentations, location);
                }
            } else {
                if (inProject) {
                    result = MessageFormat.format(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_representationsInProject, location);
                } else {
                    result = MessageFormat.format(org.eclipse.sirius.viewpoint.provider.Messages.AbstractSWTCallback_representations, location);
                }
            }
        }
        return result;
    }

    private boolean semanticResourcesDirty(Session session) {
        for (final Resource semanticResource : getAllSemanticResources(session)) {
            if (!ResourceSetSync.getStatus(semanticResource).equals(ResourceStatus.SYNC)) {
                return true;
            }
        }
        return false;
    }

    private Iterable<Resource> getAllSemanticResources(Session session) {
        if (session instanceof DAnalysisSessionEObject) {
            return Iterables.concat(session.getSemanticResources(), ((DAnalysisSessionEObject) session).getControlledResources());
        }
        return session.getSemanticResources();
    }

    private boolean inUIThread() {
        return Display.getCurrent() != null;
    }

    private Shell getActiveShell() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    }

    @Override
    public void openError(String title, String message) {
        if (inUIThread()) {
            MessageDialog.openError(getActiveShell(), title, message);
        }
    }

    @Override
    public boolean askSessionReopeningWithResourceVersionMismatch(AirdResourceVersionMismatchException e) {
        return false;
    }

    @Override
    public void askUserAndSaveMigratedSession(Session session) {
        if (askUserToSaveAutomaticMigration(session, getSessionNameToDisplayWhileSaving(session))) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(() -> {
                try {
                    new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, new WorkspaceModifyOperation() {
                        @Override
                        protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                            session.save(monitor);
                        }
                    });
                } catch (InvocationTargetException | InterruptedException e) {
                    SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
                }
            });

        }
    }

    /**
     * Test if the session resource has been automatically migrated. If it is the case and this is due to a direct user
     * action, ask user if he wants to save the session
     * 
     * @param session
     *            The session to test
     * @param sessionLabel
     *            the session label to use when interacting with user if needed.
     * @return <code>true</code> if the user want to save the session, <code>false</code> otherwise
     */
    public boolean askUserToSaveAutomaticMigration(Session session, String sessionLabel) {
        for (Resource resource : session.getAllSessionResources()) {
            boolean migrated = isSessionMigrated(resource, session.getSessionResource().getURI());
            if (migrated && MigrationUIUtil.shouldUserBeWarnedAboutMigration(resource)) {
                String message = MessageFormat.format(org.eclipse.sirius.common.ui.Messages.MigrationUIUtil_askToSaveChanges, sessionLabel);
                return SWTUtil.showSaveDialogWithMessage(resource, message, false) == ISaveablePart2.YES;
            }
        }
        return false;
    }

    private boolean isSessionMigrated(Resource resource, URI airdResourceUri) {
        RepresentationsFileVersionSAXParser representationsFileVersionSAXParser = new RepresentationsFileVersionSAXParser(airdResourceUri);
        Version lastMigrationVersion = RepresentationsFileMigrationService.getInstance().getLastMigrationVersion();
        return lastMigrationVersion != null && !lastMigrationVersion.equals(Version.parseVersion(representationsFileVersionSAXParser.getVersion(new NullProgressMonitor())));
    }
}
