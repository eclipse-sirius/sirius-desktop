/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutExtender;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.DefaultLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.ExtendableLayoutProvider;

import com.google.inject.Injector;

/**
 * Layout node provider allowing to apply an ELK layout algorithm while arranging diagram elements.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public class ELKLayoutNodeProvider extends DefaultLayoutProvider implements ExtendableLayoutProvider {
    private final LayoutExtender extender = new LayoutExtender(this);

    @SuppressWarnings("rawtypes")
    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint) {
        List<IELKLayoutExtension> elkLayoutExtensions = getLayoutExtensions();
        DiagramEditPart diagramEditPart = layoutHint.getAdapter(DiagramEditPart.class);
        Injector injector = LayoutConnectorsService.getInstance().getInjector(null, selectedObjects);
        ElkDiagramLayoutConnector connector = injector.getInstance(ElkDiagramLayoutConnector.class);

        connector.setLayoutConfiguration(layoutConfiguration);
        LayoutMapping layoutMapping = connector.buildLayoutGraph(diagramEditPart, selectedObjects);

        if (DiagramElkPlugin.getDefault().isDebugging()) {
            ElkDiagramLayoutConnector.storeResult(layoutMapping.getLayoutGraph(),
                    ((org.eclipse.sirius.viewpoint.DRepresentation) ((org.eclipse.gmf.runtime.notation.Diagram) diagramEditPart.getModel()).getElement()).getName(), "initialState", false);
        }

        // We perform "before" actions provided by extension point.
        elkLayoutExtensions.forEach(e -> e.beforeELKLayout(layoutMapping));

        if (DiagramElkPlugin.getDefault().isDebugging()) {
            ElkDiagramLayoutConnector.storeResult(layoutMapping.getLayoutGraph(),
                    ((org.eclipse.sirius.viewpoint.DRepresentation) ((org.eclipse.gmf.runtime.notation.Diagram) diagramEditPart.getModel()).getElement()).getName(), "beforeELKLayout", false);
        }

        connector.layout(layoutMapping);

        if (DiagramElkPlugin.getDefault().isDebugging()) {
            ElkDiagramLayoutConnector.storeResult(layoutMapping.getLayoutGraph(),
                    ((org.eclipse.sirius.viewpoint.DRepresentation) ((org.eclipse.gmf.runtime.notation.Diagram) diagramEditPart.getModel()).getElement()).getName(), "afterELKLayout", false);
        }

        // We perform "after" actions provided by extension point.
        elkLayoutExtensions.forEach(e -> e.afterELKLayout(layoutMapping));

        if (DiagramElkPlugin.getDefault().isDebugging()) {
            ElkDiagramLayoutConnector.storeResult(layoutMapping.getLayoutGraph(),
                    ((org.eclipse.sirius.viewpoint.DRepresentation) ((org.eclipse.gmf.runtime.notation.Diagram) diagramEditPart.getModel()).getElement()).getName(),
                    "afterExtensionUpdate", false);
        }
        connector.transferLayout(layoutMapping);
        Command gmfLayoutCommand = connector.getApplyCommand(layoutMapping);
        Optional<GmfLayoutCommand> concreteGmfLayoutCommand = getConcreteGMFLayoutCommand(gmfLayoutCommand);
        if (concreteGmfLayoutCommand.isPresent()) {
            return gmfLayoutCommand.chain(new ICommandProxy(new ELKLayoutExtensionCommand(concreteGmfLayoutCommand.get(), elkLayoutExtensions, layoutMapping)));
        }
        return gmfLayoutCommand;
    }

    private Optional<GmfLayoutCommand> getConcreteGMFLayoutCommand(Command gmfLayoutCommand) {
        return Optional.ofNullable(gmfLayoutCommand).filter(ICommandProxy.class::isInstance).map(c -> ((ICommandProxy) c).getICommand()).filter(GmfLayoutCommand.class::isInstance)
                .map(GmfLayoutCommand.class::cast);
    }

    private List<IELKLayoutExtension> getLayoutExtensions() {
        List<IELKLayoutExtension> layoutExtensions = new ArrayList<>();
        IConfigurationElement[] config = EclipseUtil.getConfigurationElementsFor(IELKLayoutExtension.EXTENSION_ID); // $NON-NLS-1$
        for (IConfigurationElement configurationElement : config) {
            try {

                Object contribution = configurationElement.createExecutableExtension("class"); //$NON-NLS-1$
                if (contribution instanceof IELKLayoutExtension) {
                    layoutExtensions.add((IELKLayoutExtension) contribution);
                }

            } catch (CoreException e) {
                // Do nothing, we return an empty list.
            }
        }
        return layoutExtensions;
    }

    @Override
    public boolean handleConnectableListItems() {
        return true;
    }

    @Override
    public Rectangle provideNodeMetrics(Node node) {
        return null;
    }

    @Override
    public LayoutExtender getExtender() {
        return extender;
    }
}
