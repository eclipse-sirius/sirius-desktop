/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusStyleClipboard;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.PasteStyleCommandProvider;
import org.eclipse.sirius.ecore.extender.business.api.permission.ISimpleAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Pastes the style of the copied element. Also works for semantically and graphically different element types.
 * 
 * @author Séraphin Costa
 */
public class PasteStylePureGraphicalAction extends Action implements IDisposableAction {

    private boolean isDisposed = true;

    private Optional<TransactionalEditingDomain> editingDomainOpt = Optional.empty();

    private Optional<IPropertyChangeListener> changeListenerOpt = Optional.empty();

    // Listeners:
    // - Selection
    private ISelectionListener onChangeSelection = (part, selection) -> {
        EclipseUIUtil.displaySyncExec(() -> updateActionState(getTargetEditParts(selection)));
    };

    // - Permission
    private ISimpleAuthorityListener onChangePermission = () -> {
        EclipseUIUtil.displaySyncExec(() -> updateActionState(getTargetEditParts()));
    };

    // - Style Clipboard
    private SiriusStyleClipboard.Listener onChangeClipboard = (gmfView, siriusStyle) -> {
        EclipseUIUtil.displaySyncExec(() -> updateActionState(getTargetEditParts()));
    };

    /**
     * Default constructor.
     */
    public PasteStylePureGraphicalAction() {
        super();

        setId(ActionIds.PASTE_STYLE_PURE_GRAPHICAL);
        setText(Messages.PasteStylePureGraphicalAction_text);
        setToolTipText(Messages.PasteStylePureGraphicalAction_toolTipText);
        setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_STYLE_ICON));
    }

    @Override
    public void init() {
        if (isDisposed) {
            IWorkbenchPage page = EclipseUIUtil.getActivePage();
            if (page != null) {
                page.addSelectionListener(this.onChangeSelection);
            }
            updateActionState(getTargetEditParts());
            SiriusStyleClipboard.getInstance().addListener(onChangeClipboard);
            changeListenerOpt.ifPresent(changeListener -> {
                this.addPropertyChangeListener(changeListener);
            });
            isDisposed = false;
        }
    }

    @Override
    public void dispose() {
        if (!isDisposed) {
            isDisposed = true;
            editingDomainOpt.ifPresent(editingDomain -> {
                var permissionAuthoriry = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(editingDomain.getResourceSet());
                permissionAuthoriry.removeAuthorityListener(onChangePermission);
            });
            changeListenerOpt.ifPresent(changeListener -> {
                this.removePropertyChangeListener(changeListener);
            });
            SiriusStyleClipboard.getInstance().removeListener(onChangeClipboard);
            onChangePermission = null;
            IWorkbenchPage page = EclipseUIUtil.getActivePage();
            if (page != null) {
                page.removeSelectionListener(this.onChangeSelection);
                this.onChangeSelection = null;
            }
            // else, already removed by deleting page
            // see ISelectionService.addSelectionListener(ISelectionListener)
        }
    }

    @Override
    public boolean isDisposed() {
        return isDisposed;
    }

    private String getCommandLabel() {
        return Messages.PasteStylePureGraphicalAction_commandLabel;
    }

    private boolean canEditElement(DDiagramElement dDiagramElement) {
        Resource resource = dDiagramElement.eResource();
        if (resource == null) {
            return false;
        } else {
            var permissionAuthoriry = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(resource.getResourceSet());
            return permissionAuthoriry.canEditInstance(dDiagramElement.getParentDiagram());
        }
    }

    private void updatePermissionListener(Optional<TransactionalEditingDomain> newEditingDomainOpt) {
        editingDomainOpt.ifPresent(editingDomain -> {
            var permissionAuthoriry = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(editingDomain.getResourceSet());
            permissionAuthoriry.removeAuthorityListener(onChangePermission);
        });
        editingDomainOpt = newEditingDomainOpt;
        editingDomainOpt.ifPresent(editingDomain -> {
            var permissionAuthoriry = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(editingDomain.getResourceSet());
            permissionAuthoriry.addAuthorityListener(onChangePermission);
        });
    }

    private void updateActionState(List<IGraphicalEditPart> targetEditParts) {
        Optional<TransactionalEditingDomain> newEditingDomainOpt = getEditingDomain(targetEditParts);
        SiriusStyleClipboard clipboard = SiriusStyleClipboard.getInstance();

        updatePermissionListener(newEditingDomainOpt);

        boolean haveDomain = newEditingDomainOpt.isPresent();
        boolean haveClipboard = clipboard.getGmfView().isPresent() || clipboard.getSiriusStyle().isPresent();
        boolean haveEditableSelection = targetEditParts.stream() //
                .map(IGraphicalEditPart::resolveSemanticElement) //
                .filter(DDiagramElement.class::isInstance) //
                .map(DDiagramElement.class::cast) //
                .anyMatch(this::canEditElement);
        setEnabled(haveDomain && haveClipboard && haveEditableSelection);
    }

    /**
     * Set the listener for the changes of the paste image action state. This listener is called after a property (text,
     * tooltip, enabled, checked...) has changed. This method must called <strong>before</strong> <code>init()</code>:
     * the listener will be effective from <code>init()</code> to <code>dispose()</code>. The listener will be removed
     * when action is disposed.
     * 
     * @param listener
     *            the optional property change listener
     */
    public void onChangeState(Optional<IPropertyChangeListener> listener) {
        if (!isDisposed()) {
            throw new IllegalStateException("onChangeState must be called before action is initialized"); //$NON-NLS-1$
        }
        changeListenerOpt = listener;
    }

    private Object getTargetEditPart(Object selection) {
        if (selection instanceof LabelEditPart editPart) {
            return editPart.getParent();
        } else {
            return selection;
        }
    }

    private List<IGraphicalEditPart> getTargetEditParts() {
        IWorkbenchPage page = EclipseUIUtil.getActivePage();
        if (page != null) {
            return getTargetEditParts(page.getSelection());
        } else {
            return List.of();
        }

    }

    private List<IGraphicalEditPart> getTargetEditParts(ISelection selection) {
        if (selection instanceof IStructuredSelection structuredSelection) {
            Spliterator<?> selectionIterator = structuredSelection.spliterator();
            return StreamSupport.stream(selectionIterator, false) //
                    .map(this::getTargetEditPart) //
                    .filter(IGraphicalEditPart.class::isInstance) //
                    .map(IGraphicalEditPart.class::cast) //
                    .toList();
        } else {
            return List.of();
        }
    }

    private Optional<TransactionalEditingDomain> getEditingDomain(List<IGraphicalEditPart> targetEditParts) {
        return targetEditParts.stream() //
                .map(editPart -> editPart.getEditingDomain()) //
                .filter(Objects::nonNull) //
                .findAny();
    }

    @Override
    public void run() {
        List<IGraphicalEditPart> targetEditParts = getTargetEditParts();
        SiriusStyleClipboard clipboard = SiriusStyleClipboard.getInstance();
        getEditingDomain(targetEditParts).ifPresent((TransactionalEditingDomain domain) -> {
            final CompoundCommand command = new CompoundCommand();
            command.setDebugLabel(Messages.SiriusPropertyHandlerEditPolicy_chainedStyleCommandDebugLabel);

            for (IGraphicalEditPart targetEditPart : targetEditParts) {
                if (targetEditPart.resolveSemanticElement() instanceof DDiagramElement targetElement && canEditElement(targetElement)) {
                    clipboard.getSiriusStyle().ifPresent(siriusStyle -> {
                        clipboard.getGmfView().ifPresent(gmfView -> {
                            // exclude copy-paste from Edge to Node/Container
                            if (!(siriusStyle instanceof EdgeStyle) || targetElement instanceof DEdge) {
                                // create command
                                command.add(PasteStyleCommandProvider.createGMFCommand(domain, targetEditPart, gmfView));
                                command.add(PasteStyleCommandProvider.createSiriusCommand(domain, targetElement, siriusStyle));
                            }
                        });
                    });
                }
            }
            if (!command.isEmpty()) {
                domain.getCommandStack().execute(new RecordingCommand(domain, getCommandLabel()) {
                    @Override
                    protected void doExecute() {
                        command.execute();
                    }
                });
            }
        });
    }
}
