/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.creation;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.EditingSessionEvent;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Action to create a representation.
 * 
 * @author mchauvin
 */
public class CreateRepresentationAction extends Action {

    private Session session;

    private EObject selection;

    private RepresentationDescription description;

    private DRepresentation createdDRepresentation;

    private ILabelProvider labelProvider;

    private String name;

    /**
     * Instantiate a new action to create a new representation.
     * 
     * @param session
     *            the session
     * @param selection
     *            the selected object
     * @param representationDescription
     *            the representation description to create
     * @param labelProvider
     *            the label provider
     */
    public CreateRepresentationAction(final Session session, final EObject selection, final RepresentationDescription representationDescription, final ILabelProvider labelProvider) {
        super();
        this.session = session;
        this.selection = selection;
        this.description = representationDescription;
        this.labelProvider = labelProvider;
        updateActionLabels();
    }

    void updateActionLabels() {
        ImageDescriptor descriptor = ImageDescriptor.getMissingImageDescriptor();
        final Image descImage = labelProvider.getImage(description);

        if (descImage != null) {
            descriptor = ImageDescriptor.createFromImage(descImage);
        }

        this.setImageDescriptor(descriptor);

        computeRepresentationName();
        this.setText(name);
        this.setToolTipText(description.getEndUserDocumentation());

        if (session instanceof DAnalysisSessionImpl) {
            // Disable the action in case of the representation cannot be
            // created
            Collection<DView> containers = ((DAnalysisSessionImpl) session).getAvailableRepresentationContainers(description);

            // If containers is empty, a new one will be created, so the action
            // is enabled
            if (!containers.isEmpty()) {
                // Try to find one valid container candidate
                boolean enabled = false;
                for (DView container : containers) {
                    IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(container);
                    if (permissionAuthority == null || permissionAuthority.canCreateIn(container)) {
                        enabled = true;
                        break;
                    }
                } // for

                this.setEnabled(enabled);
            }
        }
    }

    @Override
    public void run() {
        super.run();

        try {
            final String representationName = getRepresentationName();
            if (representationName == null) {
                return;
            }

            IRunnableWithProgress representationCreationRunnable = new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    try {
                        monitor.beginTask(org.eclipse.sirius.viewpoint.provider.Messages.CreateRepresentationAction_creationTask, 5);
                        CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(session, description, selection, representationName,
                                new SubProgressMonitor(monitor, 4));

                        IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);
                        editingSession.notify(EditingSessionEvent.REPRESENTATION_ABOUT_TO_BE_CREATED_BEFORE_OPENING);
                        session.getTransactionalEditingDomain().getCommandStack().execute(createRepresentationCommand);
                        editingSession.notify(EditingSessionEvent.REPRESENTATION_CREATED_BEFORE_OPENING);
                        createdDRepresentation = createRepresentationCommand.getCreatedRepresentation();
                        monitor.worked(1);
                    } finally {
                        monitor.done();
                    }
                }
            };
            PlatformUI.getWorkbench().getProgressService().run(true, false, representationCreationRunnable);
            IRunnableWithProgress runnable = new IRunnableWithProgress() {
                @Override
                public void run(final IProgressMonitor monitor) {
                    try {
                        monitor.beginTask(org.eclipse.sirius.viewpoint.provider.Messages.CreateRepresentationAction_openingTask, 1);
                        DialectUIManager.INSTANCE.openEditor(session, createdDRepresentation, new SubProgressMonitor(monitor, 1));
                    } finally {
                        monitor.done();
                    }
                }

            };
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            IRunnableContext context = new ProgressMonitorDialog(shell);
            PlatformUI.getWorkbench().getProgressService().runInUI(context, runnable, null);
        } catch (final InvocationTargetException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
        } catch (final InterruptedException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
        }

    }

    /**
     * Get the representation name for the created representation. The default
     * implementation open a dialog, subclass if needed.
     * 
     * @return the representation, <code>null</code> if it could not be chosen
     *         or the creation is cancelled.
     */
    protected String getRepresentationName() {
        String descriptionLabel = null;
        if (description.getEndUserDocumentation() != null && description.getEndUserDocumentation().trim().length() > 0) {
            descriptionLabel = MessageFormat.format(Messages.createRepresentationInputDialog_RepresentationDescriptionLabel, description.getEndUserDocumentation());
        }
        if (descriptionLabel == null) {
            descriptionLabel = ""; //$NON-NLS-1$
        } else {
            descriptionLabel += "\n\n"; //$NON-NLS-1$
        }
        descriptionLabel += Messages.createRepresentationInputDialog_NewRepresentationNameLabel;
        final InputDialog askSiriusName = new InputDialog(Display.getDefault().getActiveShell(),
                MessageFormat.format(Messages.createRepresentationInputDialog_Title, MessageTranslator.INSTANCE.getMessage(description, new IdentifiedElementQuery(description).getLabel())),
                descriptionLabel, name, new IInputValidator() {
                    @Override
                    public String isValid(final String newText) {
                        return null;
                    }
                });
        if (askSiriusName.open() == Window.OK) {
            return askSiriusName.getValue();
        }
        return null;
    }

    private void computeRepresentationName() {
        final IInterpreter interpreter = this.session.getInterpreter();
        name = Messages.createRepresentationInputDialog_NamePrefix + MessageTranslator.INSTANCE.getMessage(description, new IdentifiedElementQuery(description).getLabel());
        if (!StringUtil.isEmpty(description.getTitleExpression())) {
            try {
                name = interpreter.evaluateString(selection, description.getTitleExpression());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(description, DescriptionPackage.eINSTANCE.getRepresentationDescription_TitleExpression(), e);
            }
        }
    }
}
