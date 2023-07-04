/*******************************************************************************
 * Copyright (c) 2019, 2023 Obeo
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.elk.core.options.HierarchyHandling;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.service.LayoutMapping;
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
import org.eclipse.sirius.diagram.elk.IELKLayoutExtension;
import org.eclipse.sirius.diagram.ui.internal.layout.GenericLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.ext.base.Option;
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
                Optional<CustomLayoutConfiguration> optionnalCustomLayoutConfiguration = getAssociatedElkLayoutConfiguration(diagramEditPart);
                if (optionnalCustomLayoutConfiguration.isPresent()) {
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

                LayoutMapping layoutMapping = connector.buildLayoutGraph(diagramEditPart, diagramEditPart.getChildren(), true, false);
                // Perform "before" actions provided by extension point.
                List<IELKLayoutExtension> elkLayoutExtensions = IELKLayoutExtension.getLayoutExtensions();
                elkLayoutExtensions.forEach(e -> e.beforeELKLayout(layoutMapping));
                // Store the result in ELKG file
                ElkDiagramLayoutConnector.storeResult(layoutMapping.getLayoutGraph(),
                        ((org.eclipse.sirius.viewpoint.DRepresentation) ((org.eclipse.gmf.runtime.notation.Diagram) diagramEditPart.getModel()).getElement()).getName(), "", true);

                if (commandStack != null) {
                    // Undo the reset home
                    commandStack.undo();
                }
            }
        }
        return null;
    }

    public Optional<CustomLayoutConfiguration> getAssociatedElkLayoutConfiguration(DiagramEditPart diagramEditPart) {
        Optional<CustomLayoutConfiguration> result = Optional.empty();
        org.eclipse.sirius.diagram.elk.debug.gmf.layout.LayoutService layoutService = org.eclipse.sirius.diagram.elk.debug.gmf.layout.LayoutService.getInstance();
        List<Object> hints = new ArrayList<>(2);
        hints.add(LayoutType.DEFAULT);
        hints.add(diagramEditPart);
        IAdaptable layoutHint = new ObjectAdapter(hints);
        var editparts = diagramEditPart.getChildren();
        List<LayoutNode> nodes = new ArrayList<>(editparts.size());
        var li = editparts.iterator();
        while (li.hasNext()) {
            IGraphicalEditPart ep = (IGraphicalEditPart) li.next();
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
                    result = ((GenericLayoutProvider) layoutProvider).getLayoutConfiguration(diagramEditPart);
                }
            }
        }
        return result;
    }

    private DiagramCommandStack getDiagramCommandStack(IWorkbenchPart workbenchPart) {
        Object stack = workbenchPart.getAdapter(CommandStack.class);
        return (stack instanceof DiagramCommandStack) ? (DiagramCommandStack) stack : null;
    }
}
