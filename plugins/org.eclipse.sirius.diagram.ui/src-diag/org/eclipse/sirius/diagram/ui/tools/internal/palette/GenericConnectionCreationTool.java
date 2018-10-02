/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.ui.business.internal.helper.CreateConnectionRequestHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.command.DoNothingCommand;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.internal.part.SiriusDiagramGraphicalViewer;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * The Generic Edge Creation Tool. This tool allows to execute any applicable edge creation tool on the selected start
 * and end elements.
 * 
 * @author fbarbin
 *
 */
public class GenericConnectionCreationTool extends ConnectionCreationTool {
    private PaletteRoot paletteRoot;

    private List<ConnectionCreationToolEntry> connectionToolEntries;

    private Map<ConnectionCreationToolEntry, CreateConnectionRequest> startToolToRequest;

    private Map<ConnectionCreationToolEntry, CreateConnectionRequest> endToolToRequest;

    private boolean targetHasChanged;

    @Override
    public void activate() {
        super.activate();
        if (paletteRoot == null) {
            paletteRoot = getDomain().getPaletteViewer().getPaletteRoot();
        }
        this.connectionToolEntries = new ArrayList<>();
        // We retrieve all available connection tools entries.
        for (Object container : paletteRoot.getChildren()) {
            PaletteContainer paletteContainer = (PaletteContainer) container;
            computeToolEntriesFromContainer(paletteContainer);
        }
        this.startToolToRequest = null;
        this.endToolToRequest = null;
        setFactory(new GenericConnectionRequestCreationFactory(null));
    }

    @Override
    protected boolean updateTargetUnderMouse() {
        targetHasChanged = super.updateTargetUnderMouse();
        return targetHasChanged;
    }

    private void computeToolEntriesFromContainer(PaletteContainer paletteContainer) {
        for (Object entry : paletteContainer.getChildren()) {
            if (entry instanceof ConnectionCreationToolEntry) {
                connectionToolEntries.add((ConnectionCreationToolEntry) entry);
            } else if (entry instanceof PaletteContainer) {
                computeToolEntriesFromContainer((PaletteContainer) entry);
            }
        }
    }

    @Override
    protected Command getCommand() {
        Command commandToReturn = UnexecutableCommand.INSTANCE;
        CreateConnectionRequest request = (CreateConnectionRequest) getTargetRequest();
        if (RequestConstants.REQ_CONNECTION_START.equals(request.getType())) {
            commandToReturn = handleConnectionStart(request);
        } else if (RequestConstants.REQ_CONNECTION_END.equals(request.getType())) {
            commandToReturn = handleConnectionEnd();
        }
        return commandToReturn;
    }

    /**
     * Returns an executable command if at least one edge connection tool is applicable.
     * 
     * @param request
     *            the default target request.
     * @return an {@link UnexecutableCommand} if no tool is available, a {@link DoNothingCommand} otherwise.
     */
    private Command handleConnectionStart(CreateConnectionRequest request) {
        EditPart editPart = getTargetEditPart();
        if (targetHasChanged) {
            this.startToolToRequest = new HashMap<>();
            // We compute the list of applicable tools at the start.
            for (Iterator<ConnectionCreationToolEntry> iterator = connectionToolEntries.iterator(); iterator.hasNext(); /**/) {
                ConnectionCreationToolEntry toolEntry = iterator.next();
                Optional<EdgeCreationDescription> optional = getCreationToolDescription(EdgeCreationDescription.class, toolEntry);
                if (optional.isPresent()) {
                    // We need to create a request per specific tool. Indeed, we cannot share the current target request
                    // since we cannot modify the org.eclipse.gef.requests.CreateRequest.newObject value (which holds
                    // the concrete ToolDescription) once org.eclipse.gef.requests.CreateRequest.getNewObject() has been
                    // called.
                    CreateConnectionRequest newRequest = createNewConnectionRequest(request, optional.get());
                    Command currentCommand = editPart.getCommand(newRequest);
                    // If the command is executable for this tool, we add this tool and its request to the executable
                    // tool for this start edit part.
                    if (currentCommand != null && currentCommand.canExecute()) {
                        startToolToRequest.put(toolEntry, newRequest);
                    }
                }
            }
        } else {
            updateRequestLocation(startToolToRequest.values(), request, editPart, true);
        }
        return startToolToRequest.isEmpty() ? UnexecutableCommand.INSTANCE : DoNothingCommand.INSTANCE;
    }

    /**
     * Updates all requests in cache to keep their location up-to-date. We need to recompute the extended data (that
     * keep the start connection context) since the location may have changed.
     */
    private void updateRequestLocation(Collection<CreateConnectionRequest> requests, CreateConnectionRequest originalRequest, EditPart editPart, boolean start) {
        requests.stream().forEach(current -> {
            current.setLocation(originalRequest.getLocation().getCopy());
            // If the connection start has no yet been determined (the user did not select the start location yet, we
            // need to recompute the extended data.
            if (start) {
                CreateConnectionRequestHelper.computeConnectionStartExtendedData(current, (INodeEditPart) editPart);
            }
        });

    }

    /**
     * Returns the Command of this tool. During the execution, the command will display a popup menu if several concrete
     * tools are available. If only one tool is possible, it will be applied. If no tool is available with this edge
     * target, an {@link UnexecutableCommand} is returned.
     *
     * @return the tool command if the Generic Edge creation tool can be applied, an {@link UnexecutableCommand}
     *         otherwise.
     */
    private Command handleConnectionEnd() {
        EditPart editPart = getTargetEditPart();
        // We compute the applicable tools map if we ask for the command for the first time or if the target has
        // changed.
        if (endToolToRequest == null || targetHasChanged) {
            endToolToRequest = new HashMap<>();
            for (Iterator<ConnectionCreationToolEntry> iterator = startToolToRequest.keySet().iterator(); iterator.hasNext(); /**/) {
                ConnectionCreationToolEntry toolEntry = iterator.next();
                Optional<EdgeCreationDescription> optional = getCreationToolDescription(EdgeCreationDescription.class, toolEntry);
                if (optional.isPresent()) {
                    CreateConnectionRequest request = startToolToRequest.get(toolEntry);
                    Command currentCommand = null;
                    if (request != null) {
                        // update the Request
                        updateRequest(request);
                        currentCommand = editPart.getCommand(request);
                    }
                    // If at least one of the connection tool is executable, we return an executable command.
                    if (currentCommand != null && currentCommand.canExecute()) {
                        endToolToRequest.put(toolEntry, request);
                    }
                }
            }
        } else {
            updateRequestLocation(endToolToRequest.values(), (CreateConnectionRequest) getTargetRequest(), editPart, false);
        }
        return endToolToRequest.isEmpty() ? UnexecutableCommand.INSTANCE : createCompleteCommand();
    }

    private Command createCompleteCommand() {
        EditPartViewer editPartViewer = getCurrentViewer();
        EdgeTarget source = getEdgeTargetFromEditPart(getTargetEditPart());
        if (editPartViewer instanceof SiriusDiagramGraphicalViewer) {
            TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(source);
            return new ICommandProxy(new GMFCommandWrapper(editingDomain, new GenericEdgeCreationToolCommand(editingDomain, editPartViewer.getControl(), endToolToRequest)));
        }
        return UnexecutableCommand.INSTANCE;
    }

    private EdgeTarget getEdgeTargetFromEditPart(EditPart editPart) {
        if (editPart instanceof IDiagramElementEditPart) {
            DDiagramElement element = ((IDiagramElementEditPart) editPart).resolveDiagramElement();
            if (element instanceof EdgeTarget) {
                return (EdgeTarget) element;
            }
        }
        return null;
    }

    private <E extends MappingBasedToolDescription> Optional<E> getCreationToolDescription(Class<E> type, CreationToolEntry entry) {
        Object toolProperty = entry.getToolProperty(CreationTool.PROPERTY_CREATION_FACTORY);
        Optional<E> edgeCreationDescriptionOptional = Optional.ofNullable(toolProperty).filter(CreationFactory.class::isInstance).map(factory -> ((CreationFactory) toolProperty).getNewObject())
                .filter(type::isInstance).map(type::cast);
        return edgeCreationDescriptionOptional;
    }

    private void updateRequest(CreateConnectionRequest requestToUpdate) {
        requestToUpdate.setType(getCommandName());
        requestToUpdate.setLocation(((CreateConnectionRequest) getTargetRequest()).getLocation());
        requestToUpdate.setSourceEditPart(((CreateConnectionRequest) getTargetRequest()).getSourceEditPart());
        requestToUpdate.setTargetEditPart(((CreateConnectionRequest) getTargetRequest()).getTargetEditPart());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private CreateConnectionRequest createNewConnectionRequest(CreateConnectionRequest request, EdgeCreationDescription description) {
        CreateConnectionRequest connectionRequest = new CreateConnectionRequest();
        connectionRequest.setLocation(request.getLocation().getCopy());
        connectionRequest.setSourceEditPart(request.getSourceEditPart());
        connectionRequest.setTargetEditPart(request.getTargetEditPart());
        connectionRequest.setStartCommand(request.getStartCommand());
        connectionRequest.setType(request.getType());
        connectionRequest.setSnapToEnabled(request.isSnapToEnabled());
        connectionRequest.setFactory(new GenericConnectionRequestCreationFactory(description));
        Map extendedData = new HashMap(request.getExtendedData());
        connectionRequest.setExtendedData(extendedData);

        return connectionRequest;
    }

    @Override
    public void deactivate() {
        paletteRoot = null;
        connectionToolEntries.clear();
        startToolToRequest = null;
        endToolToRequest = null;
        super.deactivate();
    }

    /**
     * The Command returned by this tool.
     * 
     * @author fbarbin
     *
     */
    private static class GenericEdgeCreationToolCommand extends RecordingCommand {
        private Control control;

        private Map<ConnectionCreationToolEntry, CreateConnectionRequest> toolToRequest;

        GenericEdgeCreationToolCommand(TransactionalEditingDomain domain, Control control, Map<ConnectionCreationToolEntry, CreateConnectionRequest> toolToRequest) {
            super(domain, Messages.GenericConnectionCreationTool_label);
            this.control = control;
            this.toolToRequest = toolToRequest;
        }

        @Override
        protected void doExecute() {

            if (toolToRequest.size() == 1) {
                toolToRequest.values().stream().map(r -> r.getTargetEditPart().getCommand(r)).findFirst().ifPresent(cmd -> cmd.execute());
            } else {
                Menu menu = new Menu(control);
                for (ConnectionCreationToolEntry entry : toolToRequest.keySet()) {
                    CreateConnectionRequest request = toolToRequest.get(entry);
                    Command currentCommand = request.getTargetEditPart().getCommand(request);
                    createMenuItem(entry, menu, currentCommand);
                }
                menu.setVisible(true);

                // Handles touch screen case (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=480318)
                Display display = menu.getDisplay();
                while (!menu.isDisposed() && menu.isVisible()) {
                    if (!display.readAndDispatch()) {
                        display.sleep();
                    }
                }
                // CHECKSTYLE:OFF
                while (display.readAndDispatch()); // needed, to get the selection event, which is fired AFTER the menu
                                                   // is hidden
                // CHECKSTYLE:ON
                if (!menu.isDisposed()) {
                    menu.dispose();
                }
            }
        }

        private void createMenuItem(ConnectionCreationToolEntry entry, Menu menu, Command command) {
            final MenuItem item = new MenuItem(menu, SWT.CASCADE);
            ImageDescriptor descriptor = entry.getSmallIcon();
            if (descriptor != null) {
                item.setImage(descriptor.createImage());
            }

            item.setText(entry.getLabel());
            item.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    super.widgetSelected(e);
                    command.execute();
                }
            });
        }
    }

    /**
     * A specific CreationFactory .
     * 
     * @author fbarbin
     *
     */
    private static class GenericConnectionRequestCreationFactory implements CreationFactory {

        private AbstractToolDescription abstractToolDescription;

        /**
         * Default constructor
         * 
         * @param abstractToolDescription
         *            the Connection Creation tool description.
         */
        GenericConnectionRequestCreationFactory(AbstractToolDescription abstractToolDescription) {
            this.abstractToolDescription = abstractToolDescription;
        }

        @Override
        public Object getNewObject() {
            return abstractToolDescription;
        }

        @Override
        public Object getObjectType() {
            return abstractToolDescription.getClass();
        }

    }
}
