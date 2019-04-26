/*******************************************************************************
 * Copyright (c) 2019 Obeo
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
package org.eclipse.sirius.diagram.elk.debug;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.HierarchyHandling;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.common.core.service.IProvider;
import org.eclipse.gmf.runtime.common.core.util.ObjectAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.layout.CanLayoutNodesOperation;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.layout.LayoutNode;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.LayoutOptionTarget;
import org.eclipse.sirius.diagram.elk.ElkDiagramLayoutConnector;
import org.eclipse.sirius.diagram.ui.internal.layout.GenericLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.inject.Injector;

/**
 * The Export To Elk Graph command handler.
 * 
 * @author Laurent Redor
 * 
 */
public class ExportToElkGraphHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        IWorkbenchPart workbenchPart = HandlerUtil.getActivePart(event);
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            if (structuredSelection.getFirstElement() instanceof DiagramEditPart) {
                DiagramEditPart diagramEditPart = (DiagramEditPart) structuredSelection.getFirstElement();
                CustomLayoutConfiguration customLayoutConfiguration;
                Option<CustomLayoutConfiguration> optionnalCustomLayoutConfiguration = getAssociatedElkLayoutConfiguration(diagramEditPart);
                if (optionnalCustomLayoutConfiguration.some()) {
                    customLayoutConfiguration = optionnalCustomLayoutConfiguration.get();
                } else {
                    MessageDialog.openWarning(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.ExportToElkGraphHandler_elkExportDialogTitle,
                            Messages.ExportToElkGraphHandler_elkExportDialogNoAssociatedLayoutMessage);
                    DescriptionFactory layoutDescriptionFactory = DescriptionFactory.eINSTANCE;
                    customLayoutConfiguration = layoutDescriptionFactory.createCustomLayoutConfiguration();
                    customLayoutConfiguration.setId("org.eclipse.elk.layered");
                    EnumLayoutOption enumLayoutOption = layoutDescriptionFactory.createEnumLayoutOption();
                    enumLayoutOption.setId("org.eclipse.elk.hierarchyHandling");
                    EnumLayoutValue enumLayoutValue = layoutDescriptionFactory.createEnumLayoutValue();
                    enumLayoutValue.setName(HierarchyHandling.INCLUDE_CHILDREN.name());
                    enumLayoutOption.setValue(enumLayoutValue);
                    enumLayoutOption.getTargets().add(LayoutOptionTarget.PARENT);
                    enumLayoutOption.getTargets().add(LayoutOptionTarget.NODE);
                    customLayoutConfiguration.getLayoutOptions().add(enumLayoutOption);
                }

                // Temporarily reset home and set coordinates to 0,0 (to have better result)
                Command command = diagramEditPart.getCommand(new Request(RequestConstants.REQ_RESET_ORIGIN));
                DiagramCommandStack commandStack = getDiagramCommandStack(workbenchPart);
                if (commandStack != null) {
                    commandStack.execute(command);
                }

                Injector injector = LayoutConnectorsService.getInstance().getInjector(null, diagramEditPart.getChildren());
                ElkDiagramLayoutConnector connector = injector.getInstance(ElkDiagramLayoutConnector.class);
                connector.setLayoutConfiguration(customLayoutConfiguration);

                LayoutMapping layoutMapping = connector.buildLayoutGraph(diagramEditPart, diagramEditPart.getChildren());
                storeResult(layoutMapping.getLayoutGraph(),
                        ((org.eclipse.sirius.viewpoint.DRepresentation) ((org.eclipse.gmf.runtime.notation.Diagram) diagramEditPart.getModel()).getElement()).getName());

                if (commandStack != null) {
                    // Undo the reset home
                    commandStack.undo();
                }
            }
        }
        return null;
    }

    public Option<CustomLayoutConfiguration> getAssociatedElkLayoutConfiguration(DiagramEditPart diagramEditPart) {
        Option<CustomLayoutConfiguration> result = Options.newNone();
        org.eclipse.sirius.diagram.elk.debug.gmf.layout.LayoutService layoutService = org.eclipse.sirius.diagram.elk.debug.gmf.layout.LayoutService.getInstance();
        List<Object> hints = new ArrayList<>(2);
        hints.add(LayoutType.DEFAULT);
        hints.add(diagramEditPart);
        IAdaptable layoutHint = new ObjectAdapter(hints);
        List<IGraphicalEditPart> editparts = diagramEditPart.getChildren();
        List<LayoutNode> nodes = new ArrayList<>(editparts.size());
        Iterator<IGraphicalEditPart> li = editparts.iterator();
        while (li.hasNext()) {
            IGraphicalEditPart ep = li.next();
            View view = ep.getNotationView();
            if (ep.isActive() && view != null && view instanceof Node && ep != layoutHint.getAdapter(EditPart.class)) {
                Rectangle bounds = ep.getFigure().getBounds();
                nodes.add(new LayoutNode((Node) view, bounds.width, bounds.height));
            }
        }
        Option<IProvider> optionalProvider = layoutService.getMainProvider(new CanLayoutNodesOperation(nodes, true, layoutHint));
        if (optionalProvider.some()) {
            if (optionalProvider.get() instanceof AbstractLayoutProvider) {
                LayoutProvider layoutProvider = ((AbstractLayoutProvider) optionalProvider.get()).getDiagramLayoutProvider(diagramEditPart, layoutHint);
                if (layoutProvider instanceof GenericLayoutProvider) {
                    CustomLayoutConfiguration customLayoutConfiguration = ((GenericLayoutProvider) layoutProvider).getLayoutConfiguration(diagramEditPart);
                    if (customLayoutConfiguration != null) {
                        result = Options.newSome(customLayoutConfiguration);
                    }

                }
            }
        }
        return result;
    }

    /**
     * Export the given layout graph in a file. This can be useful as debug output.
     * 
     * @param graphToStore
     *            the layout graph to store
     */
    protected void storeResult(final ElkNode graphToStore, final String diagramName) {
        URI exportUri = URI.createFileURI(System.getProperty("java.io.tmpdir") + diagramName + ".elkg");
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.createResource(exportUri);
        // Disable the layout stored in this graph to avoid an automatic layout during the opening in "Layout Graph"
        // view
        graphToStore.setProperty(CoreOptions.NO_LAYOUT, true);
        resource.getContents().add(graphToStore);

        try {
            resource.save(Collections.emptyMap());
            MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.ExportToElkGraphHandler_elkExportDialogTitle,
                    MessageFormat.format(Messages.ExportToElkGraphHandler_elkExportDialogMessage, URI.decode(exportUri.toString())));
        } catch (IOException e) {
            // ignore the exception
        }
        graphToStore.setProperty(CoreOptions.NO_LAYOUT, false);
    }

    private DiagramCommandStack getDiagramCommandStack(IWorkbenchPart workbenchPart) {
        Object stack = workbenchPart.getAdapter(CommandStack.class);
        return (stack instanceof DiagramCommandStack) ? (DiagramCommandStack) stack : null;
    }
}
