/*******************************************************************************
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tree.ui.tools.internal.editor.provider;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.description.TreeDragSource;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TransferData;

/**
 * Drop Listener used to validate and perform Drag and Drop operations on DTreeItems.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DTreeItemDropListener extends ViewerDropAdapter implements DropTargetListener {
    private TransactionalEditingDomain domain;

    private ModelAccessor accessor;

    private ITreeCommandFactory commandFactory;

    /**
     * A cache memorizing the sources of the current dragAndDrop.
     */
    private final Set<DSemanticDecorator> droppedData = new LinkedHashSet<>();

    /**
     * A cache memorizing the semantic sources of the current dragAndDrop.
     */
    private final Map<EObject, TreeItemContainerDropTool> semanticDroppedData = new LinkedHashMap<>();

    /**
     * The cache value of the DnD target.
     */
    private DTreeItemContainer dropTarget;

    /**
     * The preceding siblings of the current DnD operation.
     */
    private final Collection<DTreeItem> precedingSiblings = new ArrayList<>();

    /**
     * Creates a new DTreeItemDropListener.
     * 
     * @param viewer
     *            the viewer on which this ViewerDropListener will be installed
     * @param domain
     *            the Editing domain in which execute the DnD actions
     * @param treeCommandFactory
     *            the Tree command factory to use
     * @param accessor
     *            the model accessor to use
     */
    public DTreeItemDropListener(Viewer viewer, TransactionalEditingDomain domain, ITreeCommandFactory treeCommandFactory, ModelAccessor accessor) {
        super(viewer);
        this.domain = domain;
        this.commandFactory = treeCommandFactory;
        this.accessor = accessor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ViewerDropAdapter#validateDrop(java.lang.Object, int,
     *      org.eclipse.swt.dnd.TransferData)
     */
    @Override
    public boolean validateDrop(Object target, int operation, TransferData transferType) {
        resetContext();
        computeContext(target, operation, transferType);

        boolean valid = validateContext();

        if (!valid) {
            resetContext();
        }

        return valid;
    }

    private void resetContext() {
        dropTarget = null;
        precedingSiblings.clear();
        droppedData.clear();
        semanticDroppedData.clear();
    }

    private void computeContext(Object target, int operation, TransferData transferType) {
        // Result are stored to avoid unnecessary computation of
        // the TreeItems when performing drag

        // Step A : get the correct target and preceding siblings
        computeDropTargetAndPrecedingSiblings(target);

        // Step B : get the sources of the drop by checking
        // the local selection transfer
        computeDraggedDataFromLocalSelectionTransfer();

    }

    /**
     * Computes the actual Target of the DnD and the preceding siblings variables , according to the current Location
     * value :
     * <ul>
     * <li>if the currentLocation is LOCATION_BEFORE : then the target of the DnD is the container of the default
     * target. Preceding siblings will be all TreeItems contained by this container and located before the current
     * target.</li>
     * <li>if the currentLocation is LOCATION_AFTER : then the target of the DnD is the container of the default target.
     * Preceding siblings will be all TreeItems contained by this container and located before the current target,
     * including the current target.</li>
     * <li>for all other values of currentLocation, then the target of the DnD is the current target, and the
     * precedingSiblings will be all TreeItmes contained in the current target.</li>
     * <li>if the target of the DnD is null, then the real container is the tree itself.</li>
     * </ul>
     * 
     * @param target
     *            the current DnD target (calculated by SWT)
     * @return the actual Target of the DnD
     */
    private void computeDropTargetAndPrecedingSiblings(Object target) {
        int currentLocation = getCurrentLocation();

        Object mouseTarget = target;
        if (mouseTarget == null) {
            // receiver is tree
            mouseTarget = getViewer().getInput();
            currentLocation = LOCATION_ON;
        }

        if (mouseTarget instanceof DTreeItem) {
            DTreeItem itemMouseTarget = (DTreeItem) mouseTarget;

            // If the cursor is between 2 treeItems,
            // the dropTarget should be the container
            if ((currentLocation == LOCATION_BEFORE) || (currentLocation == LOCATION_AFTER)) {
                if (itemMouseTarget.eContainer() instanceof DTreeItemContainer) {
                    dropTarget = (DTreeItemContainer) itemMouseTarget.eContainer();
                }
            } else {
                dropTarget = (DTreeItem) mouseTarget;
            }
        } else if (mouseTarget instanceof DTree) {
            dropTarget = (DTree) mouseTarget;
        }

        precedingSiblings.clear();
        if (dropTarget != null) {
            List<DTreeItem> ownedTreeItems = dropTarget.getOwnedTreeItems();

            // The preceding sliblings are all TreeItems contained
            // before the current dropTarget.
            if ((currentLocation == LOCATION_BEFORE) || (currentLocation == LOCATION_AFTER)) {
                if (mouseTarget instanceof DTreeItem) {
                    DTreeItem ticMouseTarget = (DTreeItem) mouseTarget;
                    int dropTargetIndex = ownedTreeItems.indexOf(ticMouseTarget);
                    if (dropTargetIndex > 0) {
                        precedingSiblings.addAll(ownedTreeItems.subList(0, dropTargetIndex));
                    }

                    // If the items are dropped after the dropTarget
                    if (currentLocation == LOCATION_AFTER) {
                        // Then the current drop Target must be considered as a
                        // preceding slibling
                        precedingSiblings.add(ticMouseTarget);
                    }
                }
            } else {
                // If the sources are dropped directly on a tree item container
                // the we consider that all children of the drop target are
                // preceding siblings of this target.
                precedingSiblings.addAll(dropTarget.getOwnedTreeItems());
            }
        }
    }

    /**
     * Compute the sources of the drag and drop from the local selection transfer's selection (for example the Model
     * Explorer View).
     * 
     */
    private void computeDraggedDataFromLocalSelectionTransfer() {
        ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();

        droppedData.clear();
        semanticDroppedData.clear();

        if (selection instanceof IStructuredSelection) {
            IStructuredSelection sel = (IStructuredSelection) selection;

            List<?> selectedElements = sel.toList();
            if (selectedElements.stream().anyMatch(DSemanticDecorator.class::isInstance)) {
                // @formatter:off
                selectedElements.stream()
                                .filter(DSemanticDecorator.class::isInstance)
                                .map(DSemanticDecorator.class::cast)
                                .forEachOrdered(semDec -> {
                    droppedData.add(semDec);
                    semanticDroppedData.put(semDec.getTarget(), null);
                });
                // @formatter:on
            } else {
                // @formatter:off
                selectedElements.stream()
                                .filter(EObject.class::isInstance)
                                .map(EObject.class::cast)
                                .forEach(eobject -> semanticDroppedData.put(eobject, null));
                // @formatter:on
            }
        }
    }

    private boolean validateContext() {
        boolean valid = false;

        // Check 1 : Graphical considerations
        // Check 1.1 : the target must be a treeItem and the selection not empty
        if (dropTarget != null && !semanticDroppedData.isEmpty()) {
            // Look for a tool for each dropped semantic element

            // Check 1.2 : the sources must no contain the target
            valid = !sourceIsTargetContainer();
            if (valid) {
                // Check 2 : ensure that the target of the DnD can be edited
                valid = canEditSemanticDecorator(dropTarget);

                // Check 3 : For each source of the DnD
                // a DnD tool must be defined and applicable
                if (!droppedData.isEmpty()) {
                    // Check 3.1 : dropping viewpoint elements : more variables
                    // and tool BOTH or TREE
                    for (DSemanticDecorator dSem : droppedData) {
                        valid = valid && canEditSemanticDecorator(dSem);

                        EObject oldContainer = null;
                        if (dSem.eContainer() instanceof DSemanticDecorator) {
                            oldContainer = ((DSemanticDecorator) dSem.eContainer()).getTarget();
                        }
                        Option<TreeItemContainerDropTool> dropTool = getBestDropDescription(dSem.getTarget(), oldContainer, TreeDragSource.TREE, dSem);
                        if (dropTool.some()) {
                            semanticDroppedData.put(dSem.getTarget(), dropTool.get());
                        } else {
                            valid = false;
                        }

                        if (!valid) {
                            break;
                        }
                    }
                } else {
                    // Check 3.2 : dropping semantic elements : variables and
                    // tool BOTH or PROJECT_EXPLORER
                    for (EObject droppedElement : new ArrayList<EObject>(semanticDroppedData.keySet())) {
                        valid = valid && canEditEObject(droppedElement);

                        Option<TreeItemContainerDropTool> dropTool = getBestDropDescription(droppedElement, null, TreeDragSource.PROJECT_EXPLORER, null);

                        if (dropTool.some()) {
                            semanticDroppedData.put(droppedElement, dropTool.get());
                        } else {
                            valid = false;
                        }

                        if (!valid) {
                            break;
                        }
                    }
                }
            }
        }
        return valid;
    }

    /**
     * Indicates if one of the dragged sources is a container of the target.
     * 
     * @param dragSources
     *            the dragged element
     * @param dropTarget
     *            the drop target
     * @return true if one of the dragged sources is a container of the target, false otherwise
     */
    private boolean sourceIsTargetContainer() {
        Object targetContainer = dropTarget;
        while (targetContainer instanceof DTreeItem) {
            if (droppedData.contains(targetContainer)) {
                return true;
            }
            targetContainer = ((DTreeItem) targetContainer).getContainer();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ViewerDropAdapter#performDrop(java.lang.Object)
     */
    @Override
    public boolean performDrop(Object data) {
        boolean dropped = false;
        if (dropTarget != null) {
            // Step 1 : create a command containing all semantic
            // instructions
            // associated to the DnD tools
            Command dndCommand = buildCommand();

            // Step 2 : execute the created command
            if (dndCommand.canExecute()) {
                domain.getCommandStack().execute(dndCommand);
                dropped = true;
            }
        }
        resetContext();
        return dropped;

    }

    private CompoundCommand buildCommand() {
        CompoundCommand dndCommand = new CompoundCommand(Messages.DTreeItemDropListener_dragAndDropCommand);
        if (!droppedData.isEmpty()) {
            for (DSemanticDecorator semDec : droppedData) {
                EObject droppedElement = semDec.getTarget();
                TreeItemContainerDropTool tool = semanticDroppedData.get(droppedElement);
                if (tool == null) {
                    dndCommand.append(UnexecutableCommand.INSTANCE);
                }
                dndCommand.append(commandFactory.buildDropItemFromTool(semDec, dropTarget, precedingSiblings, tool));
            }
        } else {
            for (Entry<EObject, TreeItemContainerDropTool> entry : semanticDroppedData.entrySet()) {
                if (entry.getValue() == null) {
                    dndCommand.append(UnexecutableCommand.INSTANCE);
                }
                dndCommand.append(commandFactory.buildDropItemFromTool(entry.getKey(), dropTarget, precedingSiblings, entry.getValue()));
            }
        }
        return dndCommand;
    }

    /**
     * Indicates whether the given DSemanticDecorator (TreeItem or DTree) can be edited or not, using the Permission
     * Authority and the CanEdit feature.
     * 
     * @param decorator
     *            the item to determine if it is editable or not
     * @return true if the given DSemanticDecorator and its target can be edited, false otherwise
     */
    protected boolean canEditSemanticDecorator(DSemanticDecorator decorator) {
        // Permission Authority must allow edition of the DTreeItem and its
        // semantic target

        IPermissionAuthority authority = accessor != null ? accessor.getPermissionAuthority() : null;
        boolean canEdit = authority != null && authority.canEditInstance(decorator.getTarget());
        canEdit = canEdit && authority.canEditInstance(decorator);

        return canEdit;
    }

    /**
     * Indicates whether the given object can be edited or not, using the Permission Authority and the CanEdit feature.
     * 
     * @param obj
     *            the item to determine if it is editable or not
     * @return true if the given object can be edited, false otherwise
     */
    protected boolean canEditEObject(EObject obj) {
        // Permission Authority must allow edition of the DTreeItem and its
        // semantic target

        IPermissionAuthority authority = accessor != null ? accessor.getPermissionAuthority() : null;
        boolean canEdit = authority != null && authority.canEditInstance(obj);

        return canEdit;
    }

    /**
     * Return the best drop description.
     * 
     * @param description
     *            .
     * @param droppedElement
     *            The semantic dropped element
     * @param oldContainer
     *            The old semantic container, can be null (for instance if drop comes from project explorer)
     * @param newContainer
     *            The new semantic container
     * @param newViewContainer
     *            The new view container
     * @param dragSource
     *            the drag source.
     * @param droppedSemanticDecorator
     *            The graphical dropped element (Optional).
     * @return he best drop description
     */
    private Option<TreeItemContainerDropTool> getBestDropDescription(final EObject droppedElement, final EObject oldContainer, final TreeDragSource dragSource,
            final DSemanticDecorator droppedSemanticDecorator) {

        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(droppedElement);
        if (oldContainer != null) {
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_OLD, oldContainer);
        }
        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_NEW, dropTarget.getTarget());
        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW_NEW, dropTarget);
        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, droppedElement);
        if (droppedSemanticDecorator != null) {
            interpreter.setVariable(IInterpreterSiriusVariables.VIEW, droppedSemanticDecorator);
        }
        RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);

        TreeItemContainerDropTool bestDropDescription = null;

        /* find valid candidates */
        for (TreeItemContainerDropTool dropTool : getDropTools(dragSource)) {
            if (checkPrecondition(dropTool, safeInterpreter, droppedElement)) {
                if (bestDropDescription == null) {
                    bestDropDescription = dropTool;
                } else {
                    SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.DTreeItemDropListener_ambigousDropWarning, droppedElement, bestDropDescription.getName(), dropTool.getName()),
                            new RuntimeException());
                    break;
                }
            }
        }

        // Clean variables
        if (oldContainer != null) {
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_OLD);
        }
        interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_NEW);
        interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW_NEW);
        interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
        if (droppedSemanticDecorator != null) {
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        }

        return Options.newSome(bestDropDescription);
    }

    private boolean checkPrecondition(TreeItemContainerDropTool dropTool, RuntimeLoggerInterpreter safeInterpreter, EObject droppedElement) {
        final String precondition = dropTool.getPrecondition();
        if (precondition != null && !StringUtil.isEmpty(precondition.trim())) {
            return safeInterpreter.evaluateBoolean(droppedElement, dropTool, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
        }
        return true;
    }

    private Iterable<TreeItemContainerDropTool> getDropTools(final TreeDragSource dragSource) {
        Predicate<TreeItemContainerDropTool> checkedDragSource = (TreeItemContainerDropTool input) -> {
            return input.getDragSource() == TreeDragSource.BOTH || input.getDragSource() == dragSource;
        };

        final Collection<TreeItemContainerDropTool> availableTools;
        if (dropTarget instanceof DTree) {
            availableTools = ((DTree) dropTarget).getDescription().getDropTools();
        } else if (dropTarget instanceof DTreeItem) {
            availableTools = ((DTreeItem) dropTarget).getActualMapping().getDropTools();
        } else {
            availableTools = Collections.emptyList();
        }

        return availableTools.stream().filter(checkedDragSource).collect(Collectors.toList());
    }

}
