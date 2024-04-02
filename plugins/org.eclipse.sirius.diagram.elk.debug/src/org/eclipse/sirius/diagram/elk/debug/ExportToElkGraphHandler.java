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
import com.google.inject.Injector;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.elk.core.options.HierarchyHandling;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
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
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.LayoutOptionTarget;
import org.eclipse.sirius.diagram.elk.DiagramElkPlugin;
import org.eclipse.sirius.diagram.elk.ElkDiagramLayoutConnector;
import org.eclipse.sirius.diagram.elk.ElkDiagramLayoutTracer;
import org.eclipse.sirius.diagram.elk.IELKLayoutExtension;
import org.eclipse.sirius.diagram.elk.debug.gmf.layout.LayoutService;
import org.eclipse.sirius.diagram.ui.internal.layout.GenericLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The Export To Elk Graph command handler.
 * 
 * @author Laurent Redor
 * 
 */
public abstract class ExportToElkGraphHandler extends AbstractHandler {

    private static ElkDiagramLayoutTracer getTracer() {
        return DiagramElkPlugin.getPlugin().getTracer();
    }
    
    /** Implementation to save as text format. */
    public static class AsText extends ExportToElkGraphHandler {
        @Override
        protected Path saveToFile(final URI sessionUri, final ElkNode graph, final String diagramName) {
            return getTracer().saveAsText(sessionUri, graph, diagramName, ""); //$NON-NLS-1$
        }
    }

    /** Implementation to save as XMI format. */
    public static class AsXmi extends ExportToElkGraphHandler {
        @Override
        protected Path saveToFile(final URI sessionUri, final ElkNode graph, final String diagramName) {
            return getTracer().saveAsGraph(sessionUri, graph, diagramName, ""); //$NON-NLS-1$
        }
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        IWorkbenchPart workbenchPart = HandlerUtil.getActivePart(event);
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            Object first = structuredSelection.getFirstElement();
            if (first instanceof DiagramEditPart) {
                exportToElkGraph(workbenchPart, (DiagramEditPart) first);
            }
        }
        return null;
    }

    private void exportToElkGraph(IWorkbenchPart workbenchPart, DiagramEditPart diagramEditPart) {
        String[] warning = { null }; // Warning is postponed to avoid popup hell.
        CustomLayoutConfiguration layoutConfiguration = getAssociatedElkLayoutConfiguration(diagramEditPart)
                .orElseGet(() -> {
                    warning[0] = Messages.ExportToElkGraphHandler_elkExportDialogNoAssociatedLayoutMessage;
                    return createLayoutConfigurationStub();
                });

        // Temporarily reset home and set coordinates to 0,0 (to have better result)
        Command command = diagramEditPart.getCommand(new Request(RequestConstants.REQ_RESET_ORIGIN));
        DiagramCommandStack commandStack = getDiagramCommandStack(workbenchPart);
        if (commandStack != null) {
            commandStack.execute(command);
        }

        LayoutMapping layoutMapping = createElkMapping(diagramEditPart, layoutConfiguration);
        URI session = ElkDiagramLayoutConnector.getSessionUri(layoutMapping);
        
        // Store the result in ELKG file
        Path result = saveToFile(session, layoutMapping.getLayoutGraph(), getDiagramName(diagramEditPart));
        if (commandStack != null) {
            // Undo the reset home
            commandStack.undo();
        }
        
        showResultMessage(result, warning[0]);
    }

    private void showResultMessage(Path result, String warning) {
        if (result != null) {
            Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
            String message = MessageFormat.format(Messages.ExportToElkGraphHandler_elkExportDialogMessage, result);
            if (warning == null) {
                MessageDialog.openInformation(shell, Messages.ExportToElkGraphHandler_elkExportDialogTitle, message);
            } else {
                MessageDialog.openWarning(shell, Messages.ExportToElkGraphHandler_elkExportDialogTitle, 
                        message + '\n' + warning);
            }
        }
    }

    private LayoutMapping createElkMapping(DiagramEditPart diagramEditPart,
            CustomLayoutConfiguration layoutConfiguration) {
        Injector injector = LayoutConnectorsService.getInstance().getInjector(null, diagramEditPart.getChildren());
        ElkDiagramLayoutConnector connector = injector.getInstance(ElkDiagramLayoutConnector.class);
        connector.setLayoutConfiguration(layoutConfiguration);

        LayoutMapping layoutMapping = connector.buildLayoutGraph(diagramEditPart, diagramEditPart.getChildren(), true, false);

        // Perform "before" actions provided by extension point.
        List<IELKLayoutExtension> elkLayoutExtensions = IELKLayoutExtension.getLayoutExtensions();
        elkLayoutExtensions.forEach(e -> e.beforeELKLayout(layoutMapping));
        return layoutMapping;
    }

    private String getDiagramName(DiagramEditPart diagramEditPart) {
        View gmfDiagram = (View) diagramEditPart.getModel();
        String diagramName = ((DRepresentation) gmfDiagram.getElement()).getName();
        return diagramName;
    }

    protected abstract Path saveToFile(URI sessionUri, ElkNode graph, String diagramName);
    
    private CustomLayoutConfiguration createLayoutConfigurationStub() {
        DescriptionFactory fct = DescriptionFactory.eINSTANCE;
        CustomLayoutConfiguration result = fct.createCustomLayoutConfiguration();
        result.setId("org.eclipse.elk.layered"); //$NON-NLS-1$
        EnumLayoutOption hierarchyHld = fct.createEnumLayoutOption();
        hierarchyHld.setId("org.eclipse.elk.hierarchyHandling"); //$NON-NLS-1$
        EnumLayoutValue includeChildren = fct.createEnumLayoutValue();
        includeChildren.setName(HierarchyHandling.INCLUDE_CHILDREN.name());
        hierarchyHld.setValue(includeChildren);
        hierarchyHld.getTargets().add(LayoutOptionTarget.PARENT);
        hierarchyHld.getTargets().add(LayoutOptionTarget.NODE);
        result.getLayoutOptions().add(hierarchyHld);
        return result;
    }

    public Optional<CustomLayoutConfiguration> getAssociatedElkLayoutConfiguration(DiagramEditPart diagramEditPart) {
        LayoutService layoutService = LayoutService.getInstance();
        IAdaptable layoutHint = new ObjectAdapter(Arrays.asList(LayoutType.DEFAULT, diagramEditPart));
                
        List<?> gefEditparts = diagramEditPart.getChildren();
        List<LayoutNode> nodes = new ArrayList<>(gefEditparts.size());
        for (Object ep : gefEditparts) {
            if (ep instanceof IGraphicalEditPart ep_) {
                View view = ep_.getNotationView();
                if (ep_.isActive() && view != null && view instanceof Node && ep_ != layoutHint.getAdapter(EditPart.class)) {
                    Rectangle bounds = ep_.getFigure().getBounds();
                    nodes.add(new LayoutNode((Node) view, bounds.width, bounds.height));
                }
            }
        }
        Option<IProvider> optionalProvider = layoutService.getMainProvider(new CanLayoutNodesOperation(nodes, true, layoutHint));
        if (optionalProvider.some() && optionalProvider.get() instanceof AbstractLayoutProvider) {
            LayoutProvider layoutProvider = ((AbstractLayoutProvider) optionalProvider.get()).getDiagramLayoutProvider(diagramEditPart, layoutHint);
            if (layoutProvider instanceof GenericLayoutProvider lp) {
                return lp.getLayoutConfiguration(diagramEditPart);
            }
            
        }
        return Optional.empty();
    }

    private DiagramCommandStack getDiagramCommandStack(IWorkbenchPart workbenchPart) {
        Object stack = workbenchPart.getAdapter(CommandStack.class);
        return (stack instanceof DiagramCommandStack) ? (DiagramCommandStack) stack : null;
    }
}
