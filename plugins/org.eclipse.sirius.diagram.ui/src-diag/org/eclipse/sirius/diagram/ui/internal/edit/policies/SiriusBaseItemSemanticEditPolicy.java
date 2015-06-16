/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IEditHelperContext;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.internal.edit.helpers.SiriusBaseEditHelper;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AirDestroyElementRequest;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies.DeleteHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;

/**
 * @was-generated
 */
public class SiriusBaseItemSemanticEditPolicy extends SemanticEditPolicy {

    /**
     * Extended request data key to hold editpart visual id.
     * 
     * @was-generated
     */
    public static final String VISUAL_ID_KEY = "visual_id"; //$NON-NLS-1$

    /**
     * Extended request data key to hold editpart visual id. Add visual id of
     * edited editpart to extended data of the request so command switch can
     * decide what kind of diagram element is being edited. It is done in those
     * cases when it's not possible to deduce diagram element kind from domain
     * element.
     * 
     * @was-generated
     */
    @Override
    public Command getCommand(Request request) {
        if (request instanceof ReconnectRequest) {
            Object view = ((ReconnectRequest) request).getConnectionEditPart().getModel();
            if (view instanceof View) {
                Integer id = Integer.valueOf(SiriusVisualIDRegistry.getVisualID((View) view));
                request.getExtendedData().put(VISUAL_ID_KEY, id);
            }
        }
        return super.getCommand(request);
    }

    /**
     * Returns visual id from request parameters.
     * 
     * @was-generated
     */
    protected int getVisualID(IEditCommandRequest request) {
        Object id = request.getParameter(VISUAL_ID_KEY);
        return id instanceof Integer ? ((Integer) id).intValue() : -1;
    }

    /**
     * @not-generated Add the capability to delete the semantic element.
     */
    @Override
    protected Command getSemanticCommand(IEditCommandRequest request) {
        IEditCommandRequest completedRequest = completeRequest(request);
        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
        Object editHelperContext = completedRequest.getEditHelperContext();
        if (editHelperContext instanceof View || (editHelperContext instanceof IEditHelperContext && ((IEditHelperContext) editHelperContext).getEObject() instanceof View)) {
            // no semantic commands are provided for pure design elements
            return null;
        }
        if (editHelperContext == null) {
            editHelperContext = ViewUtil.resolveSemanticElement((View) getHost().getModel());
        }
        IElementType elementType = ElementTypeRegistry.getInstance().getElementType(editHelperContext);
        if (elementType == ElementTypeRegistry.getInstance().getType("org.eclipse.gmf.runtime.emf.type.core.default")) { //$NON-NLS-1$
            elementType = null;
        }
        Command semanticCommand = getSemanticCommandSwitch(completedRequest);
        if (semanticCommand != null) {
            ICommand command = semanticCommand instanceof ICommandProxy ? ((ICommandProxy) semanticCommand).getICommand() : new CommandProxy(semanticCommand);
            completedRequest.setParameter(SiriusBaseEditHelper.EDIT_POLICY_COMMAND, command);
        }
        if (elementType != null) {
            ICommand command = elementType.getEditCommand(completedRequest);
            if (command != null) {
                if (!(command instanceof CompositeTransactionalCommand)) {
                    command = new CompositeTransactionalCommand(editingDomain, null).compose(command);
                }
                semanticCommand = new ICommandProxy(command);
            }
        }
        boolean shouldProceed = true;
        if (completedRequest instanceof DestroyRequest) {
            shouldProceed = shouldProceed((DestroyRequest) completedRequest);
        }
        if (shouldProceed) {
            if (completedRequest instanceof DestroyRequest) {
                if (completedRequest instanceof AirDestroyElementRequest && !((AirDestroyElementRequest) completedRequest).shouldDestroySemantic()) {
                    // Delete from diagram

                    CompoundCommand cc = new CompoundCommand();
                    View view = (View) getHost().getModel();

                    Diagram gmfDiagram = ((IGraphicalEditPart) getHost()).getNotationView().getDiagram();
                    DDiagram diagram = ((DDiagram) gmfDiagram.getElement());
                    if (diagram instanceof DSemanticDiagram) {
                        final boolean canDeleteFromDiagram = !((DSemanticDiagram) diagram).getTarget().equals(((DDiagramElement) view.getElement()).getTarget())
                                || !new DiagramElementMappingQuery((DiagramElementMapping) ((DDiagramElement) view.getElement()).getMapping()).isSynchronizedAndCreateElement(diagram);
                        if (canDeleteFromDiagram) {
                            if (view.getElement() instanceof DDiagramElement) {
                                DDiagramElement viewPointElement = (DDiagramElement) view.getElement();
                                DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                                Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
                                CompositeCommand compositeCommand = new CompositeCommand("Delete element from diagram");
                                IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) (adapter);
                                org.eclipse.emf.common.command.Command cmd = cmdFactoryProvider.getCommandFactory(editingDomain).buildDeleteFromDiagramCommand(viewPointElement);
                                if (shouldImpactLinkedNotesOnHideOrRemove()) {
                                    DeleteHelper.addDeleteLinkedNotesTask(cmd, view);
                                } else {
                                    DeleteHelper.addDeleteLinkedNoteAttachmentsTask(cmd, view);
                                }
                                compositeCommand.add(new GMFCommandWrapper(getEditingDomain(), cmd));
                                cc.add(new ICommandProxy(compositeCommand.reduce()));
                            }
                        }
                    }
                    return cc;
                }
                // Delete from model.
                CompoundCommand cc = new CompoundCommand();
                View view = (View) getHost().getModel();
                if (view.getElement() instanceof DDiagramElement) {
                    DDiagramElement viewPointElement = (DDiagramElement) view.getElement();
                    IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(viewPointElement.getTarget());
                    if (!permissionAuthority.canEditInstance(viewPointElement.getTarget())) {
                        return UnexecutableCommand.INSTANCE;
                    }
                    DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                    Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
                    IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) (adapter);
                    org.eclipse.emf.common.command.Command cmd = cmdFactoryProvider.getCommandFactory(editingDomain).buildDeleteDiagramElement(viewPointElement);
                    if (cmd.canExecute()) {
                        CompositeCommand compositeCommand = new CompositeCommand("Delete element");
                        if (shouldImpactLinkedNotesOnHideOrRemove()) {
                            DeleteHelper.addDeleteLinkedNotesTask(cmd, view);
                        }
                        compositeCommand.add(new GMFCommandWrapper(this.getEditingDomain(), cmd));
                        cc.add(new ICommandProxy(compositeCommand.reduce()));
                    }
                }
                return cc;
            }
            return semanticCommand;
        }
        return null;
    }

    private boolean shouldImpactLinkedNotesOnHideOrRemove() {
        return DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name());
    }

    /**
     * @was-generated
     */
    protected Command getSemanticCommandSwitch(IEditCommandRequest req) {
        if (req instanceof CreateRelationshipRequest) {
            return getCreateRelationshipCommand((CreateRelationshipRequest) req);
        } else if (req instanceof CreateElementRequest) {
            return getCreateCommand((CreateElementRequest) req);
        } else if (req instanceof ConfigureRequest) {
            return getConfigureCommand((ConfigureRequest) req);
        } else if (req instanceof DestroyElementRequest) {
            return getDestroyElementCommand((DestroyElementRequest) req);
        } else if (req instanceof DestroyReferenceRequest) {
            return getDestroyReferenceCommand((DestroyReferenceRequest) req);
        } else if (req instanceof DuplicateElementsRequest) {
            return getDuplicateCommand((DuplicateElementsRequest) req);
        } else if (req instanceof GetEditContextRequest) {
            return getEditContextCommand((GetEditContextRequest) req);
        } else if (req instanceof MoveRequest) {
            return getMoveCommand((MoveRequest) req);
        } else if (req instanceof ReorientReferenceRelationshipRequest) {
            return getReorientReferenceRelationshipCommand((ReorientReferenceRelationshipRequest) req);
        } else if (req instanceof SetRequest) {
            return getSetCommand((SetRequest) req);
        }
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getConfigureCommand(ConfigureRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getSetCommand(SetRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getEditContextCommand(GetEditContextRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getDuplicateCommand(DuplicateElementsRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getMoveCommand(MoveRequest req) {
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getReorientReferenceRelationshipCommand(ReorientReferenceRelationshipRequest req) {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * @was-generated
     */
    protected final Command getGEFWrapper(ICommand cmd) {
        return new ICommandProxy(cmd);
    }

    /**
     * @was-generated
     */
    protected EObject getSemanticElement() {
        return ViewUtil.resolveSemanticElement((View) getHost().getModel());
    }

    /**
     * Returns editing domain from the host edit part.
     * 
     * @was-generated
     */
    protected TransactionalEditingDomain getEditingDomain() {
        return ((IGraphicalEditPart) getHost()).getEditingDomain();
    }

    /**
     * Creates command to destroy the link.
     * 
     * @not-generated : add a guard in the case of a port with a hidden link
     */
    protected Command getDestroyElementCommand(View view) {
        EditPart editPart = (EditPart) getHost().getViewer().getEditPartRegistry().get(view);
        DestroyElementRequest request = new DestroyElementRequest(getEditingDomain(), false);
        if (editPart != null) {
            return editPart.getCommand(new EditCommandRequestWrapper(request, Collections.EMPTY_MAP));
        }
        return null;
    }

    /**
     * Creates command to destroy the link not destroying the semantic
     * 
     */
    protected Command getDestroyElementCommandWithoutSemantic(View view) {
        EditPart editPart = (EditPart) getHost().getViewer().getEditPartRegistry().get(view);
        DestroyElementRequest request = new AirDestroyElementRequest(getEditingDomain(), false, false);
        if (editPart != null) {
            return editPart.getCommand(new EditCommandRequestWrapper(request, Collections.EMPTY_MAP));
        }
        return null;
    }

    /**
     * Creates commands to destroy all host incoming and outgoing links.
     * 
     * @was-generated
     */
    protected CompoundCommand getDestroyEdgesCommand() {
        CompoundCommand cmd = new CompoundCommand();
        View view = (View) getHost().getModel();
        for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
            cmd.add(getDestroyElementCommand((Edge) it.next()));
        }
        for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
            cmd.add(getDestroyElementCommand((Edge) it.next()));
        }
        return cmd;
    }

    /**
     * @was-generated
     */
    protected void addDestroyShortcutsCommand(CompoundCommand command) {
        View view = (View) getHost().getModel();
        if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
            return;
        }
        for (Iterator<?> it = view.getDiagram().getChildren().iterator(); it.hasNext();) {
            View nextView = (View) it.next();
            if (nextView.getEAnnotation("Shortcut") == null || !nextView.isSetElement() || nextView.getElement() != view.getElement()) { //$NON-NLS-1$
                continue;
            }
            command.add(getDestroyElementCommand(nextView));
        }
    }

    /**
     * @was-generated
     */
    public static class LinkConstraints {

        /**
         * @was-generated
         */
        public static boolean canCreateDEdge_4001(DDiagram container, EdgeTarget source, EdgeTarget target) {
            return LinkConstraints.canExistDEdge_4001(container, source, target);
        }

        /**
         * @was-generated
         */
        public static boolean canExistDEdge_4001(DDiagram container, EdgeTarget source, EdgeTarget target) {
            return true;
        }

    }

}
