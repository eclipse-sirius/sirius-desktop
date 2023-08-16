/******************************************************************************
 * Copyright (c) 2004, 2014, 2022 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Obeo - Adaptations.
 *    Vincent LORENZO (CEA LIST) - vincent.lorenzo@cea.fr - Bug 580836
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.actions.delete;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.actions.DeleteFromModelAction;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.delete.DeleteHookHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramActionBarContributor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.Iterables;

/**
 * Delete Action which can call hooks.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public class DeleteFromModelWithHookAction extends DeleteFromModelAction {
    /** The associated key binding. */
    public static final int KEY_BINDING = SWT.CTRL | 'D';

    private IWorkbenchPart representationPart;

    /**
     * Creates a <code>DeleteFromModeWithHooklAction</code> with a default
     * label.
     * 
     * @param part
     *            The part this action will be associated with.
     */
    public DeleteFromModelWithHookAction(final IWorkbenchPart part) {
        super(part);
    }

    /**
     * Creates a <code>PromptingDeleteFromModelAction</code> with a default
     * label.
     * 
     * @param workbenchPage
     *            The page this action will be associated with.
     */
    public DeleteFromModelWithHookAction(final IWorkbenchPage workbenchPage) {
        super(workbenchPage);
    }

    /**
     * Constructor that provides page parameter to super and set actionPart
     * attribute with part parameter.
     * 
     * @param page
     *            workbenchPage The page this action will be associated with.
     * @param part
     *            The part this action will be associated with.
     */
    public DeleteFromModelWithHookAction(IWorkbenchPage page, IWorkbenchPart part) {
        super(page);
        this.representationPart = part;

    }

    /**
     * {@inheritDoc} Initialize the key binding and modify the image.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DeleteFromModelAction#init()
     */
    @Override
    public void init() {
        super.init();
        setToolTipText(SiriusDiagramActionBarContributor.DELETE_FROM_MODEL);
        setAccelerator(KEY_BINDING);
        setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETE_FROM_MODEL_ICON));
        setHoverImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETE_FROM_MODEL_ICON));
        // Setting disabled image descriptor to null, so that it can be computed
        // from the new image descriptor
        setDisabledImageDescriptor(null);
    }

    @Override
    public void dispose() {
        super.dispose();
        representationPart = null;
    }

    /**
     * Return the semantic request to destroy the element. {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.AbstractDeleteFromAction#createTargetRequest()
     */
    @Override
    protected Request createTargetRequest() {
        final TransactionalEditingDomain editingDomain = getEditingDomain();
        if (editingDomain != null) {
            final DestroyElementRequest destroyRequest = new DestroyElementRequest(editingDomain, false);
            return new EditCommandRequestWrapper(destroyRequest);
        }
        return super.createTargetRequest();
    }

    private Collection<DSemanticDecorator> computeSelections() {

        final Set<DSemanticDecorator> diagramElements = new LinkedHashSet<>();
        final List<?> operationSet = getOperationSet();
        for (IGraphicalEditPart gEditPart : Iterables.filter(operationSet, IGraphicalEditPart.class)) {
            final View view = (View) gEditPart.getModel();
            // Don't delete diagram from model only if it is the top most
            // diagram
            final EObject element = ViewUtil.resolveSemanticElement(view);
            if (element instanceof DSemanticDecorator) {
                diagramElements.add((DSemanticDecorator) element);
            }
        }
        return diagramElements;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DeleteFromModelAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        IWorkbenchPart part = getWorkbenchPart();
        if (representationPart != null && part != null && part.getAdapter(representationPart.getClass()) != null) {
            //try to adapt the part to the current representationPart
            //see bug 580836, to work in a multipage editor
            part = part.getAdapter(this.representationPart.getClass());
        }
        if (representationPart != null && !representationPart.equals(part)) {
            return super.isEnabled();
        }
        return canExecute();
    }

    private boolean canExecute() {
        List<?> operationSet = getOperationSet();
        Iterator<EditPart> editParts = Iterables.filter(operationSet, EditPart.class).iterator();
        Collection<DDiagramElement> potentialDeletion = new HashSet<>();
        boolean delete = editParts.hasNext();
        while (editParts.hasNext() && delete) {
            EditPart editPart = editParts.next();
            // disable on diagram links
            if (editPart instanceof IGraphicalEditPart) {
                if (editPart instanceof TextEditPart || editPart instanceof NoteEditPart || editPart instanceof NoteAttachmentEditPart) {
                    delete = false;
                } else {
                    IGraphicalEditPart gEditPart = (IGraphicalEditPart) editPart;
                    View view = (View) gEditPart.getModel();
                    // Don't delete diagram from model only if it is the top
                    // most diagram
                    EObject element = ViewUtil.resolveSemanticElement(view);
                    if (element instanceof DDiagramElement) {
                        DDiagramElement dDiagramElement = (DDiagramElement) element;

                        // Deletion of semantic elements when
                        // automatic
                        // refresh is off causes exceptions
                        // ensuring that semantic element is valid
                        if (dDiagramElement.getTarget() == null || dDiagramElement.getTarget().eResource() == null) {
                            return false;
                        }

                        // also checking that permission authority
                        // allows
                        // this modification
                        delete = checkDeletionIsAllowed(dDiagramElement);

                        // Checking tool precondition
                        if (delete) {
                            delete = checkDeletionPrecondition(dDiagramElement, !editParts.hasNext(), potentialDeletion);
                        }

                    } else if (element instanceof DRepresentation) {
                        // Can't delete a DDiagram/Diagram/RootElementOfDiagram
                        delete = false;
                    }
                }
            }
        }
        return delete;
    }

    /**
     * Ensures that if any Deletion tool is defined for the Mapping associated
     * to the given {@link DDiagramElement}, its precondition is checked.
     * 
     * @param dDiagramElement
     *            the {@link DDiagramElement} to check
     * @param lastElement
     *            is this the last selected element ?
     * @param potentialDeletion
     * @return true if no Deletion Tool is defined or if its precondition is
     *         checked, false otherwise
     */
    private boolean checkDeletionPrecondition(DDiagramElement dDiagramElement, boolean lastElement, Collection<DDiagramElement> potentialDeletion) {
        boolean delete = true;
        DiagramElementMapping mapping = dDiagramElement.getDiagramElementMapping();
        if (mapping != null) {
            DeleteElementDescription tool = mapping.getDeletionDescription();
            if (tool != null) {
                if (tool.getPrecondition() != null && !StringUtil.isEmpty(tool.getPrecondition().trim())) {
                    delete = evaluatePrecondition(tool, dDiagramElement);
                }
            } else {
                // diagram elements without semantic elements (relation based
                // edge with no defined tool and emtpy semantic elements
                // expression for example)
                delete = !(dDiagramElement.getSemanticElements().isEmpty() && lastElement && potentialDeletion.isEmpty());
            }
        }

        if (delete && !dDiagramElement.getSemanticElements().isEmpty()) {
            potentialDeletion.add(dDiagramElement);
        }
        return delete;
    }

    /**
     * Ensures that the PermissionAuthority allows the deletion of the given
     * {@link DDiagramElement} and its associated semantic element can be
     * deleted.
     * 
     * @param dDiagramElement
     *            the {@link DDiagramElement} to check
     * @return
     */
    private boolean checkDeletionIsAllowed(DDiagramElement dDiagramElement) {
        boolean delete = true;
        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dDiagramElement);
        if (permissionAuthority != null) {
            if (!permissionAuthority.canEditInstance(dDiagramElement.getTarget()) || !permissionAuthority.canEditInstance(dDiagramElement)) {
                delete = false;
            } else {
                EObject targetContainer = dDiagramElement.getTarget().eContainer();
                EObject diagramElementContainer = dDiagramElement.getTarget().eContainer();
                if (targetContainer != null && !permissionAuthority.canEditInstance(targetContainer)) {
                    delete = false;
                } else {
                    if (diagramElementContainer != null && !permissionAuthority.canEditInstance(diagramElementContainer)) {
                        delete = false;
                    }
                }
            }
        }
        return delete;
    }

    private boolean evaluatePrecondition(DeleteElementDescription tool, DDiagramElement dDiagramElement) {
        boolean delete = true;
        final EObject semanticContainer = ((DSemanticDecorator) dDiagramElement).getTarget();
        final EObject viewContainer = dDiagramElement.eContainer();

        final IInterpreter interpreter = InterpreterUtil.getInterpreter(dDiagramElement);
        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, viewContainer);
        interpreter.setVariable(IInterpreterSiriusVariables.VIEW, dDiagramElement);
        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, semanticContainer);

        try {
            delete = interpreter.evaluateBoolean(semanticContainer, tool.getPrecondition());
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(tool, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
        }
        interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
        interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);

        return delete;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#doRun(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected void doRun(final IProgressMonitor progressMonitor) {
        setTargetRequest(null);
        Command command = getCommand();

        boolean granted = true;

        final DeleteHookHelper deleteHookHelper = new DeleteHookHelper(computeSelections());

        if (command != null && command.canExecute()) {
            granted = deleteHookHelper.checkDeleteHook();
        }

        if (granted) {
            if ((command instanceof CompoundCommand) && (((CompoundCommand) command).getChildren().length > 0)) {
                final CompositeTransactionalCommand compositeModelActionCommand = new CompositeTransactionalCommand(getEditingDomain(), getCommandLabel());
                final CompoundCommand compoundCommand = (CompoundCommand) command;
                final Iterator<? extends Command> iterator = compoundCommand.getCommands().iterator();
                while (iterator.hasNext()) {
                    compositeModelActionCommand.compose(new CommandProxy(iterator.next()));
                }
                command = new ICommandProxy(compositeModelActionCommand);
            }
            if (command != null) {
                execute(command, progressMonitor);
            }
        }
    }
}
