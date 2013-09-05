/*******************************************************************************
 * Copyright (c) 2009, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.DRepresentationElement;
import org.eclipse.sirius.DSemanticDecorator;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.description.RepresentationDescription;
import org.eclipse.sirius.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.tools.internal.commands.InitializeLayoutCommand;
import org.eclipse.sirius.provider.SiriusEditPlugin;
import org.eclipse.sirius.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.image.ImagesPath;

/**
 * Create a new Representation from a {@link RepresentationCreationDescription}.
 * 
 * @author cbrun
 * 
 */
public class CreateRepresentationFromRepresentationCreationDescription extends Action {

    private static final AdapterFactory ADAPTER_FACTORY = DialectUIManager.INSTANCE.createAdapterFactory();

    private final RepresentationCreationDescription desc;

    private final DRepresentationElement node;

    private final IGraphicalEditPart editPart;

    private final TransactionalEditingDomain editingDomain;

    private final UICallBack uiCallBack;

    /**
     * Build the action.
     * 
     * @param desc
     *            {@link RepresentationCreationDescription} to use.
     * @param dNode
     *            node on which the user requested the creation of a new
     *            representation.
     * @param editingDomain
     *            current {@link org.eclipse.emf.edit.domain.EditingDomain}.
     * @param curPart
     *            editpart of the current {@link org.eclipse.sirius.DNode}.
     * @since 2.0
     */
    public CreateRepresentationFromRepresentationCreationDescription(final RepresentationCreationDescription desc, final DRepresentationElement dNode, final TransactionalEditingDomain editingDomain,
            final IGraphicalEditPart curPart) {
        this.desc = desc;
        this.node = dNode;
        this.editingDomain = editingDomain;
        this.editPart = curPart;
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
        this.uiCallBack = SiriusEditPlugin.getPlugin().getUiCallback();
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
                final IDiagramCommandFactory diagramCommandFactory = getDiagramCommandFactory();
                final CreateRepresentationCommand command = diagramCommandFactory.buildCreateRepresentationFromDescription(desc, node, "");
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
        return new StringBuffer("New detail : ").append(query.getLabel()).toString();
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
        final String computedName = computeName(node);
        if (computedName != null) {
            name = computedName;
        }

        try {
            name = uiCallBack.askForDetailName(name, desc.getRepresentationDescription().getEndUserDocumentation());

        } catch (final InterruptedException e) {
            // the user pressed "cancel", we should exit
            return;
        }
        final IDiagramCommandFactory emfCommandFactory = getDiagramCommandFactory();
        final CompositeTransactionalCommand compositeCommand = new CompositeTransactionalCommand(editingDomain, "Create and open representation");

        if (isInitialOperation()) {
            final Command emfCommandTool = emfCommandFactory.buildDoExecuteDetailsOperation(node, desc, name);
            compositeCommand.compose(new GMFCommandWrapper(editingDomain, emfCommandTool));
        }
        CreateRepresentationCommand command = emfCommandFactory.buildCreateRepresentationFromDescription(desc, node, name);
        InitializeLayoutCommand layoutCommand = null;
        DRepresentation[] createdRepresentation = { null };
        compositeCommand.compose(new GMFCommandWrapper(editingDomain, command));
        if (node != null) {
            layoutCommand = new InitializeLayoutCommand(editingDomain, command, editPart);
            compositeCommand.compose(new GMFCommandWrapper(editingDomain, layoutCommand));
        }

        editPart.getRoot().getViewer().getEditDomain().getCommandStack().execute(new ICommandProxy(compositeCommand));

        if (command.getCreatedRepresentation() != null) {
            createdRepresentation[0] = command.getCreatedRepresentation();
        } else if (layoutCommand != null && layoutCommand.getLayoutedRepresentation() != null) {
            createdRepresentation[0] = layoutCommand.getLayoutedRepresentation();
        }

        if (node != null) {
            final EObject target = ((DSemanticDecorator) node).getTarget();
            if (createdRepresentation[0] == null) {
                final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                MessageDialog.openWarning(shell, "Error creating the representation", "An error occured when trying to create the representation.\nPlease check the diagram specification.");
            } else {
                DialectUIManager.INSTANCE.openEditor(SessionManager.INSTANCE.getSession(target), createdRepresentation[0], new NullProgressMonitor());
            }
        }
    }

    private boolean isInitialOperation() {
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

    /**
     * Returns the emf command factory.
     * 
     * @return the emf command factory.
     */
    private IDiagramCommandFactory getDiagramCommandFactory() {
        final DDiagramEditor diagramEditor = (DDiagramEditor) editPart.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (diagramEditor == null) {
            return null;
        }
        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider diagramCmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final IDiagramCommandFactory diagramCommandFactory = diagramCmdFactoryProvider.getCommandFactory(editingDomain);
        return diagramCommandFactory;
    }
}
