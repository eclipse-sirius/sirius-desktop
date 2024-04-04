/*******************************************************************************
 * Copyright (c) 2018, 2024 Obeo
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

import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.DefaultLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;

import com.google.inject.Injector;

/**
 * Layout node provider allowing to apply an ELK layout algorithm while arranging diagram elements.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
@SuppressWarnings("restriction")
public class ELKLayoutNodeProvider extends DefaultLayoutProvider {

    @SuppressWarnings({ "rawtypes" })
    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint) {
        return layoutEditParts(selectedObjects, layoutHint, false);
    }

    @SuppressWarnings({ "rawtypes" })
    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint, final boolean isArrangeAll) {
        List<IELKLayoutExtension> elkLayoutExtensions = IELKLayoutExtension.getLayoutExtensions();
        DiagramEditPart diagramEditPart = layoutHint.getAdapter(DiagramEditPart.class);

        boolean layoutOnDiagram = true;
        if (diagramEditPart == null) {
            layoutOnDiagram = false;
            IGraphicalEditPart editPart = layoutHint.getAdapter(IGraphicalEditPart.class);
            diagramEditPart = Optional.ofNullable(editPart).map(graphicalEditPart -> new EditPartQuery(graphicalEditPart).getFirstAncestorOfType(DDiagramEditPart.class)).get();
        }
        if (diagramEditPart == null) {
            return UnexecutableCommand.INSTANCE;
        }
        Injector injector = LayoutConnectorsService.getInstance().getInjector(null, selectedObjects);
        ElkDiagramLayoutConnector connector = injector.getInstance(ElkDiagramLayoutConnector.class);

        connector.setLayoutConfiguration(layoutConfiguration);
        boolean isArrangeAtOpening = SiriusLayoutDataManager.LAYOUT_TYPE_ARRANGE_AT_OPENING.equals(layoutHint.getAdapter(String.class));
        LayoutMapping layoutMapping = connector.buildLayoutGraph(diagramEditPart, selectedObjects, isArrangeAll, isArrangeAtOpening);

        DiagramElkPlugin.getPlugin().traceForDebug(layoutMapping.getLayoutGraph(), "1_initialState");

        // We perform "before" actions provided by extension point.
        elkLayoutExtensions.forEach(e -> e.beforeELKLayout(layoutMapping));

        DiagramElkPlugin.getPlugin().traceForDebug(layoutMapping.getLayoutGraph(), "2_beforeELKLayout");

        connector.layout(layoutMapping);

        DiagramElkPlugin.getPlugin().traceForDebug(layoutMapping.getLayoutGraph(), "3_afterELKLayout");

        // We perform "after" actions provided by extension point.
        elkLayoutExtensions.forEach(e -> e.afterELKLayout(layoutMapping));

        DiagramElkPlugin.getPlugin().traceForDebug(layoutMapping.getLayoutGraph(), "4_afterExtensionUpdate");
        connector.transferLayout(layoutMapping, isArrangeAll || (layoutOnDiagram && isArrangeAtOpening));
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
}
