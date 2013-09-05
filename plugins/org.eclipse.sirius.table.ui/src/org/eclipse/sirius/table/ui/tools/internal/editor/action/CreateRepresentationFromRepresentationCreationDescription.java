/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.DRepresentationElement;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.description.RepresentationDescription;
import org.eclipse.sirius.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.description.tool.ToolPackage;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.image.ImagesPath;

/**
 * Create a new Representation from a {@link RepresentationCreationDescription}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @deprecated
 */
@Deprecated
public class CreateRepresentationFromRepresentationCreationDescription extends Action {

    private static final AdapterFactory ADAPTER_FACTORY = DialectUIManager.INSTANCE.createAdapterFactory();

    private final RepresentationCreationDescription desc;

    private final DRepresentationElement target;

    private final TransactionalEditingDomain editingDomain;

    private final ITableCommandFactory tableCommandFactory;

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
     * @param tableCommandFactory
     *            The {@link ITableCommandFactory}.
     */
    public CreateRepresentationFromRepresentationCreationDescription(final RepresentationCreationDescription desc, final DRepresentationElement target, final TransactionalEditingDomain editingDomain,
            final ITableCommandFactory tableCommandFactory) {
        this.desc = desc;
        this.target = target;
        this.editingDomain = editingDomain;
        ImageDescriptor imageDescriptor = null;
        RepresentationDescription representationDescription = desc.getRepresentationDescription();
        if (representationDescription != null) {
            // Search the icon for this representation description
            final IItemLabelProvider labelProvider = (IItemLabelProvider) CreateRepresentationFromRepresentationCreationDescription.ADAPTER_FACTORY.adapt(representationDescription,
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
        this.tableCommandFactory = tableCommandFactory;
    }

    @Override
    public String getText() {
        return new StringBuffer("New detail : ").append(desc.getName()).toString();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        final CompoundCommand compoundCommand = new CompoundCommand();

        final IInterpreter interpreter = InterpreterUtil.getInterpreter(target.getTarget());
        // default name
        String name = "new " + desc.getName();
        if (!StringUtil.isEmpty(desc.getTitleExpression())) {
            try {
                name = interpreter.evaluateString(target.getTarget(), desc.getTitleExpression());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(desc, ToolPackage.eINSTANCE.getRepresentationCreationDescription_TitleExpression(), e);
            }
        } else if (desc.getRepresentationDescription() != null && !StringUtil.isEmpty(desc.getRepresentationDescription().getTitleExpression())) {
            try {
                name = interpreter.evaluateString(target.getTarget(), desc.getRepresentationDescription().getTitleExpression());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(desc.getRepresentationDescription(), DescriptionPackage.eINSTANCE.getRepresentationDescription_TitleExpression(), e);
            }
        }

        try {
            name = tableCommandFactory.getUserInterfaceCallBack().askForDetailName(name, desc.getRepresentationDescription().getEndUserDocumentation());

            if (desc.getInitialOperation() != null && desc.getInitialOperation().getFirstModelOperations() != null) {
                final Command emfCommandTool = tableCommandFactory.buildDoExecuteDetailsOperation(target, desc, name);
                compoundCommand.append(emfCommandTool);
            }
            final CreateRepresentationCommand command = tableCommandFactory.buildCreateRepresentationFromDescription(desc, target, name);
            compoundCommand.append(command);
            editingDomain.getCommandStack().execute(compoundCommand);

            final Session session = SessionManager.INSTANCE.getSession(target.getTarget());

            DialectUIManager.INSTANCE.openEditor(session, command.getCreatedRepresentation());
        } catch (final InterruptedException e) {
            // the user pressed "cancel", let's do nothing
        }

    }
}
