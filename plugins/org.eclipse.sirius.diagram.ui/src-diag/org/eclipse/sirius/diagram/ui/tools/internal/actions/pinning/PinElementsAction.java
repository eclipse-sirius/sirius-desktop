/*******************************************************************************
 * Copyright (c) 2023, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.pinning;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.Disposable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.tools.internal.commands.PinElementsCommand;
import org.eclipse.sirius.diagram.tools.internal.commands.UnpinElementsCommand;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.ecore.extender.business.api.permission.ISimpleAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Checkable action to pin and unpin elements to the diagram.
 * 
 * @author scosta
 */
public final class PinElementsAction extends Action implements Disposable {

    private Map<DDiagramElement, ResourceSetListener> selectionElements;

    private Set<ResourceSet> selectionResourceSets;

    private ISelectionListener onChangeSelection = (IWorkbenchPart part, ISelection selection) -> {
        EclipseUIUtil.displaySyncExec(() -> updateActionState(selection));
    };

    private ISimpleAuthorityListener authorityListener = () -> {
        EclipseUIUtil.displaySyncExec(() -> updateActionState(getCurrentSelection()));
    };

    private boolean isDisposed;

    /**
     * Constructor.
     */
    private PinElementsAction() {
        super(Messages.PinElementsEclipseAction_text, Action.AS_CHECK_BOX);
        setId(ActionIds.PIN_ELEMENTS);

        IWorkbenchPage page = EclipseUIUtil.getActivePage();
        if (page != null) {
            page.addSelectionListener(this.onChangeSelection);
        }
        updateActionState(getCurrentSelection());

        isDisposed = false;
    }

    // factories
    /**
     * Create the pin elements action for toolbar (with image and tooltip).
     * 
     * @return a new PinElementsCheckableAction
     */
    public static PinElementsAction createForToolbar() {
        var action = new PinElementsAction();
        action.setToolTipText(Messages.PinElementsEclipseAction_text);
        action.setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PIN_ELEMENTS_ICON));
        return action;
    }

    /**
     * Create the pin elements action for menu (without image and tooltip).
     * 
     * @return a new PinElementsCheckableAction
     */
    public static PinElementsAction createForMenu() {
        var action = new PinElementsAction();
        action.setToolTipText(Messages.PinElementsEclipseAction_tooltip);
        return action;
    }

    @Override
    public void run() {
        final Set<DDiagramElement> elements = getSelectedDiagramElements(getCurrentSelection()).keySet();
        if (!elements.isEmpty()) {
            // Note: in this method, we are after the button has been pressed.
            // So, isChecked() == true means that the button has just been checked,
            // and isChecked() == false means that the button has just been unchecked.
            final Command cmd;
            if (isChecked()) {
                cmd = new PinElementsCommand(elements);
            } else {
                cmd = new UnpinElementsCommand(elements);
            }
            TransactionUtil.getEditingDomain(getAny(elements).orElseThrow()).getCommandStack().execute(cmd);
        }
    }

    @Override
    public void dispose() {
        if (!isDisposed) {
            removePinListener();
            authorityListener = null;
            IWorkbenchPage page = EclipseUIUtil.getActivePage();
            if (page != null) {
                page.removeSelectionListener(this.onChangeSelection);
                this.onChangeSelection = null;
            }
            // else, already removed by deleting page
            // see ISelectionService.addSelectionListener(ISelectionListener)
            isDisposed = true;
        }
    }

    // listen/update state
    /**
     * Updates action state according selection.
     * 
     * This method update the action state according selection:
     * <ul>
     * <li>Update the enable state (is the pin action available for this selection?)</li>
     * <li>Update the checked state (is the selection pinned?)</li>
     * <li>Update the pin listener (we want to listen to the pin of the current selection to update the checked state,
     * not the old one).</li>
     * </ul>
     * 
     * This method needs to be executed in thread UI.
     * 
     * @param selection
     *            the current selection
     */
    private void updateActionState(ISelection selection) {
        // remove the listener of old selection
        removePinListener();
        setEnabled(isAvailableFor(selection));
        selectionElements = getSelectedDiagramElements(selection);
        updatePinState();
        // add listener of new selection
        addPinListener();
    }

    /**
     * Add listener of the pin/unpin feature of the last selected element selection (selectionLastElement) if present.
     */
    private void addPinListener() {
        if (selectionElements != null) {
            selectionResourceSets = new LinkedHashSet<ResourceSet>();
            selectionElements = selectionElements.keySet().stream().mapMulti((DDiagramElement elem, Consumer<Entry<DDiagramElement, ResourceSetListener>> consumer) -> {
                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(elem);
                if (domain != null) {
                    ResourceSetListener pinListener = new PinHelper().createPinListener(elem, () -> {
                        EclipseUIUtil.displayAsyncExec(this::updatePinState);
                    });
                    domain.addResourceSetListener(pinListener);

                    Resource resource = elem.eResource();
                    if (resource != null) {
                        selectionResourceSets.add(resource.getResourceSet());
                    }
                    consumer.accept(Map.entry(elem, pinListener));
                }
            }).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
            for (ResourceSet resourceSet : selectionResourceSets) {
                var permissionAuthoriry = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(resourceSet);
                permissionAuthoriry.addAuthorityListener(authorityListener);
            }
        }
    }

    /**
     * Remove the listener of the pin/unpin feature of the last selected element selection (selectionLastElement) if
     * present.
     */
    private void removePinListener() {
        if (selectionElements != null) {
            for (ResourceSet resourceSet : selectionResourceSets) {
                var permissionAuthoriry = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(resourceSet);
                permissionAuthoriry.removeAuthorityListener(authorityListener);
            }
            for (Entry<DDiagramElement, ResourceSetListener> entry : selectionElements.entrySet()) {
                DDiagramElement element = entry.getKey();
                ResourceSetListener pinListener = entry.getValue();
                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(element);
                if (domain != null) {
                    domain.removeResourceSetListener(pinListener);
                } // else, nothing: if domain has been deleted, the listener is no longer attached to it
            }
            selectionResourceSets = null;
            selectionElements = null;
        }
    }

    /**
     * Update pin state (the check state of the action). Needs to be executed in thread UI.
     */
    private void updatePinState() {
        boolean isPinned = !selectionElements.isEmpty() && selectionElements.keySet().stream().allMatch(elem -> new PinHelper().isPinned(elem));
        setChecked(isPinned);
    }

    // selection utility
    /**
     * Get current selected diagram elements.
     * 
     * @param selection
     *            Selection
     * @return Selected elements.
     */
    private Map<DDiagramElement, ResourceSetListener> getSelectedDiagramElements(final ISelection selection) {
        if (selection instanceof IStructuredSelection structuredSelection) {
            Iterable<?> iterableSelection = structuredSelection;
            return StreamSupport.stream(iterableSelection.spliterator(), false) //
                    .filter(IGraphicalEditPart.class::isInstance) //
                    .map(IGraphicalEditPart.class::cast) //
                    .map(IGraphicalEditPart::resolveSemanticElement) //
                    .filter(DDiagramElement.class::isInstance) //
                    .map(DDiagramElement.class::cast) //
                    .collect(HashMap::new, (map, element) -> map.put(element, null), Map::putAll);
        } else {
            return Map.of();
        }
    }

    /**
     * Get the current selection.
     * 
     * @return The current selection.
     */
    private ISelection getCurrentSelection() {
        IWorkbenchPage page = EclipseUIUtil.getActivePage();
        if (page != null) {
            return page.getSelection();
        } else {
            return StructuredSelection.EMPTY;
        }
    }

    /**
     * Return if the pin/unpin is available for the given selection.
     */
    private boolean isAvailableFor(ISelection selection) {
        Set<DDiagramElement> selectedDiagramElements = getSelectedDiagramElements(selection).keySet();
        if (!selectedDiagramElements.isEmpty()) {
            return selectedDiagramElements.stream().allMatch(this::canPinUnpin);
        } else {
            return false;
        }
    }

    /**
     * Return if permission authority allow you to edit the given diagram element.
     */
    private boolean canEditElement(DDiagramElement dDiagramElement) {
        Resource resource = dDiagramElement.eResource();
        if (resource == null) {
            return false;
        } else {
            var permissionAuthoriry = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(resource.getResourceSet());
            return permissionAuthoriry.canEditInstance(dDiagramElement.getParentDiagram());
        }
    }

    /**
     * Return if an element can be pin or unpin (and if it is allowed)
     */
    private boolean canPinUnpin(DDiagramElement dDiagramElement) {
        return PinHelper.allowsPinUnpin(dDiagramElement) && canEditElement(dDiagramElement);
    }

    // utility
    private Optional<DDiagramElement> getAny(final Collection<DDiagramElement> elements) {
        return elements.stream().findAny();
    }
}
