/*******************************************************************************
 * Copyright (c) 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
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
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.helper.SelectionDescriptionHelper;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.api.selection.EMFMessageDialog;
import org.eclipse.sirius.common.ui.tools.api.selection.EObjectSelectionWizard;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.resource.LoadEMFResourceRunnableWithProgress;
import org.eclipse.sirius.ui.tools.api.Messages;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;

/**
 * Implementation of the {@link UICallBack} interface using SWT.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public abstract class AbstractSWTCallback implements UICallBack {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#askForVariableValues(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable)
     */
    public Collection<EObject> askForVariableValues(final EObject model, final SelectModelElementVariable variable) throws InterruptedException {
        Collection<EObject> variableValues = new ArrayList<EObject>();
        final TreeItemWrapper input = new TreeItemWrapper(null, null);
        computeInput(model, variable, input);
        final EObjectSelectionWizard wizard = new EObjectSelectionWizard(EObjectSelectionWizard.WIZARD_GENERIC_DIALOG_TITLE, variable.getMessage(), null, input,
                ViewHelper.INSTANCE.createAdapterFactory());
        wizard.setMany(variable.isMultiple());
        final WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#askForDetailName(java.lang.String)
     */
    public String askForDetailName(final String defaultName) throws InterruptedException {
        return askForDetailName(defaultName, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#askForDetailName(java.lang.String)
     */
    public String askForDetailName(final String defaultName, final String representationDescription) throws InterruptedException {
        String description = null;
        if (representationDescription != null && representationDescription.trim().length() > 0) {
            description = Messages.createRepresentationInputDialog_RepresentationDescriptionLabel + representationDescription;
        }
        if (description == null) {
            description = ""; //$NON-NLS-1$
        } else {
            description += "\n\n"; //$NON-NLS-1$
        }
        description += Messages.createRepresentationInputDialog_NewRepresentationNameLabel;
        final InputDialog askSiriusName = new InputDialog(Display.getDefault().getActiveShell(), Messages.createRepresentationInputDialog_Title, description, defaultName, new IInputValidator() {

            public String isValid(final String newText) {
                return null;
            }
        });
        if (askSiriusName.open() == Window.OK) {
            return askSiriusName.getValue();
        }
        throw new InterruptedException("Cancel");
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#openEObjectsDialogMessage(java.util.Collection,
     *      java.lang.String, java.lang.String)
     */
    public boolean openEObjectsDialogMessage(final Collection<EObject> objects, final String title, final String message) {
        return EMFMessageDialog.openQuestionWithEObjects(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), ViewHelper.INSTANCE.createAdapterFactory(), objects, title, message);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void openRepresentation(final Session openedSession, final DRepresentation representation) {
        try {
            new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    DialectUIManager.INSTANCE.openEditor(openedSession, representation, monitor);
                }
            });
        } catch (InvocationTargetException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
        } catch (InterruptedException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Resource loadResource(final EditingDomain domain, final IFile file) {
        final LoadEMFResourceRunnableWithProgress operation = new LoadEMFResourceRunnableWithProgress(domain.getResourceSet(), file);
        try {
            operation.run(new NullProgressMonitor());
        } catch (final InvocationTargetException e) {
            SiriusTransPlugin.INSTANCE.error("error loading EMF resource", e);
        } catch (final InterruptedException e) {
            SiriusTransPlugin.INSTANCE.error("error loading EMF resource", e);
        }
        return operation.getLoadedResource();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws InterruptedException
     */
    public Collection<EObject> askForEObjects(String message, TreeItemWrapper input, AdapterFactory factory) throws InterruptedException {
        final EObjectSelectionWizard wizard = new EObjectSelectionWizard(EObjectSelectionWizard.WIZARD_GENERIC_DIALOG_TITLE, message, null, input, factory);
        wizard.setMany(true);
        final WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
        final int result = dialog.open();
        if (result == Window.OK) {
            return wizard.getSelectedEObjects();
        }
        throw new InterruptedException();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws InterruptedException
     */
    public EObject askForEObject(String message, TreeItemWrapper input, AdapterFactory factory) throws InterruptedException {
        final EObjectSelectionWizard wizard = new EObjectSelectionWizard(EObjectSelectionWizard.WIZARD_GENERIC_DIALOG_TITLE, message, null, input, factory);
        wizard.setMany(false);
        final WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
        final int result = dialog.open();
        if (result == Window.OK) {
            return wizard.getSelectedEObject();
        }
        throw new InterruptedException();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#shouldReload(Resource)
     */
    public boolean shouldReload(final Resource resource) {
        return openQuestion("Reload the resource?", getCommonMessage(resource) + "externally changed, should we reload it?");
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#shouldRemove(Resource)
     */
    public boolean shouldRemove(final Resource resource) {
        return openQuestion("Remove the resource?", getCommonMessage(resource) + "externally deleted, should we remove it and lose changes?");
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#shouldClose(Session,
     *      Resource)
     */
    public boolean shouldClose(final Session session, final Resource resource) {
        return openQuestion("Close the representations file?", getCommonMessage(resource) + "deleted and contains critical data, should we close it?");
    }

    /**
     * Convenience method to open a simple Yes/No question dialog.
     * 
     * @param title
     *            the dialog's title, or <code>null</code> if none
     * @param message
     *            the message
     * @return <code>true</code> if the user presses the Yes button,
     *         <code>false</code> otherwise
     */
    private boolean openQuestion(final String title, final String message) {
        if (inUIThread()) {
            return MessageDialog.openQuestion(getActiveShell(), title, message);
        } else {
            RunnableWithResult<Boolean> reload = new RunnableWithResult.Impl<Boolean>() {
                public void run() {
                    setResult(MessageDialog.openQuestion(getActiveShell(), title, message));
                }
            };
            PlatformUI.getWorkbench().getDisplay().syncExec(reload);
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
     * <LI>"project's session name" if the session has a second segment (that is
     * the project name).</LI>
     * <LI>"toPlatformString URI" if the session uses a InMemoryQuery</LI>
     * <LI>"the toString URI" otherwise.</LI>
     * </UL>
     * <BR>
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#getSessionDisplayed(org.eclipse.sirius.business.api.session.Session)
     */
    public String getSessionNameToDisplayWhileSaving(Session session) {
        String name = ""; //$NON-NLS-1$
        if (session != null) {
            String projectName = null;
            Resource representationsFileResource = session.getSessionResource();
            URI representationsFileURI = representationsFileResource.getURI();
            if (representationsFileURI.segments().length > 1) {
                projectName = representationsFileURI.segment(1);
            }
            if (ResourceSetSync.getStatus(representationsFileResource).equals(ResourceStatus.SYNC)) {
                name = "Models";
            } else {
                if (semanticResourcesDirty(session)) {
                    name = "Models and Representations";
                } else {
                    name = "Representations";
                }
            }
            URIQuery uriQuery = new URIQuery(representationsFileURI);
            if (projectName != null) {
                name += " for project " + projectName;
            } else if (uriQuery.isInMemoryURI()) {
                name += " for \"" + uriQuery.toPlatformString() + "\"";
            } else {
                name += " for \"" + representationsFileURI.toString() + "\"";
            }
        }

        return name;
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

    private String getCommonMessage(final Resource resource) {
        return "The " + resource.getURI() + " resource has been ";
    }

    private boolean inUIThread() {
        return Display.getCurrent() != null;
    }

    private Shell getActiveShell() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ui.UICallBack#openError(java.lang.String,
     *      java.lang.String)
     */
    public void openError(String title, String message) {
        if (inUIThread()) {
            MessageDialog.openError(getActiveShell(), title, message);
        }
    }

}
