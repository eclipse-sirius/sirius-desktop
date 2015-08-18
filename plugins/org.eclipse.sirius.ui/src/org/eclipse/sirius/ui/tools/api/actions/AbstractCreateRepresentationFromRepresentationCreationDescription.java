/*******************************************************************************
 * Copyright (c) 2008, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.image.ImagesPath;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Create a new Representation from a {@link RepresentationCreationDescription}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public abstract class AbstractCreateRepresentationFromRepresentationCreationDescription extends Action {

    private static final AdapterFactory ADAPTER_FACTORY = DialectUIManager.INSTANCE.createAdapterFactory();

    private final RepresentationCreationDescription desc;

    private final DRepresentationElement target;

    private final TransactionalEditingDomain editingDomain;

    private final ICommandFactory commandFactory;

    /**
     * Build the action.
     * 
     * @param desc
     *            {@link RepresentationCreationDescription} to use.
     * @param target
     *            element on which the user requested the creation of a new
     *            representation.
     * @param editingDomain
     *            current {@link org.eclipse.emf.edit.domain.EditingDomain}.
     * @param commandFactory
     *            The {@link ICommandFactory}.
     */
    public AbstractCreateRepresentationFromRepresentationCreationDescription(final RepresentationCreationDescription desc, final DRepresentationElement target,
            final TransactionalEditingDomain editingDomain, final ICommandFactory commandFactory) {
        this.desc = desc;
        this.target = target;
        this.editingDomain = editingDomain;
        ImageDescriptor imageDescriptor = null;
        RepresentationDescription representationDescription = desc.getRepresentationDescription();
        if (representationDescription != null) {
            // Search the icon for this representation description
            final IItemLabelProvider labelProvider = (IItemLabelProvider) AbstractCreateRepresentationFromRepresentationCreationDescription.ADAPTER_FACTORY.adapt(representationDescription,
                    IItemLabelProvider.class);
            if (labelProvider != null) {
                imageDescriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(representationDescription));
            }
        }
        if (imageDescriptor != null) {
            setImageDescriptor(imageDescriptor);
        } else {
            setImageDescriptor(SiriusTransPlugin.getBundledImageDescriptor(ImagesPath.CREATE_VIEW_ICON));
        }
        this.commandFactory = commandFactory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#isEnabled()
     */
    @Override
    public boolean isEnabled() {

        boolean isEnabled = super.isEnabled();
        if (isEnabled) {
            if (desc.getRepresentationDescription() == null) {
                isEnabled = false;
            } else {
                final CreateRepresentationCommand command = commandFactory.buildCreateRepresentationFromDescription(desc, target, ""); //$NON-NLS-1$
                return command.canExecute();
            }
        }
        return isEnabled;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public String getText() {
        final IdentifiedElementQuery query = new IdentifiedElementQuery(desc);
        return query.getLabel();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        doCreateRepresentation();
    }

    private void doCreateRepresentation() {

        final IdentifiedElementQuery query = new IdentifiedElementQuery(desc);

        // default name
        String name = "new " + query.getLabel();
        final String computedName = computeName(target);
        if (computedName != null) {
            name = computedName;
        }

        try {
            name = commandFactory.getUserInterfaceCallBack().askForDetailName(name, desc.getRepresentationDescription().getEndUserDocumentation());
        } catch (final InterruptedException e) {
            // the user pressed "cancel", we should exit
            return;
        }

        Option<DRepresentation> optionalCreatedRepresentation = executeCreationCommand(getInitialOperationCommand(name), getCreateRepresentationCommand(name));
        if (target != null) {
            if (!optionalCreatedRepresentation.some()) {
                final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                MessageDialog.openWarning(shell, "Error creating the representation", "An error occured when trying to create the representation.\nPlease check the representation specification.");
            } else {
                DialectUIManager.INSTANCE.openEditor(SessionManager.INSTANCE.getSession(target.getTarget()), optionalCreatedRepresentation.get(), new NullProgressMonitor());
            }
        }
    }

    /**
     * Creates the new representation and returns it.
     * 
     * @param initialOperationCommand
     *            Optional command that executes all the initial operations
     * @param createRepresentationCommand
     *            Command that creates the new representation.
     * @return The created representation
     */
    protected abstract Option<DRepresentation> executeCreationCommand(Option<Command> initialOperationCommand, CreateRepresentationCommand createRepresentationCommand);

    /**
     * Return the command that executes all the initial operations. This command
     * can be null if there is not initial operation.
     * 
     * @param defaultRepresentationName
     *            The default representation name of the new representation.
     * @return an optional command that executes all the initial operations.
     */
    protected Option<Command> getInitialOperationCommand(String defaultRepresentationName) {
        if (isInitialOperation()) {
            return Options.newSome(commandFactory.buildDoExecuteDetailsOperation(target, desc, defaultRepresentationName));
        }
        return Options.newNone();
    }

    /**
     * Return the command that creates the new representation.
     * 
     * @param defaultRepresentationName
     *            The default representation name of the new representation.
     * @return a command that creates the new representation.
     */
    protected CreateRepresentationCommand getCreateRepresentationCommand(String defaultRepresentationName) {
        return commandFactory.buildCreateRepresentationFromDescription(desc, target, defaultRepresentationName);
    }

    /**
     * Return the editing domain.
     * 
     * @return the editing domain.
     */
    protected TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * Return the target.
     * 
     * @return the target
     */
    protected DRepresentationElement getTarget() {
        return target;
    }

    protected boolean isInitialOperation() {
        return desc.getInitialOperation() != null && desc.getInitialOperation().getFirstModelOperations() != null;
    }

    private String computeName(final DSemanticDecorator decorator) {

        final IInterpreter interpreter = InterpreterUtil.getInterpreter(decorator.getTarget());
        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);

        String computedName = null;

        if (!StringUtil.isEmpty(desc.getTitleExpression())) {
            computedName = safeInterpreter.evaluateString(decorator.getTarget(), desc, ToolPackage.eINSTANCE.getRepresentationCreationDescription_TitleExpression());
        } else if (desc.getRepresentationDescription() != null && !StringUtil.isEmpty(desc.getRepresentationDescription().getTitleExpression())) {
            computedName = safeInterpreter.evaluateString(decorator.getTarget(), desc.getRepresentationDescription(), DescriptionPackage.eINSTANCE.getRepresentationDescription_TitleExpression());
        }

        return computedName;
    }

}
