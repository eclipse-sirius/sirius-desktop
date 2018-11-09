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

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
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

    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint) {
        Injector injector = LayoutConnectorsService.getInstance().getInjector(null, selectedObjects);
        ElkDiagramLayoutConnector connector = injector.getInstance(ElkDiagramLayoutConnector.class);
        LayoutMapping layoutMapping = connector.buildLayoutGraph(layoutHint.getAdapter(DiagramEditPart.class), selectedObjects);
        connector.layout(layoutMapping);
        connector.transferLayout(layoutMapping);
        return connector.getApplyCommand(layoutMapping);
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
