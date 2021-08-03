/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.ImageProvider;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.LayerModelHelper;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.LayersActivationAction;
import org.eclipse.swt.graphics.Image;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Contribute a menu to tab bar to activate and deactivate layers.
 * 
 * @author mchauvin
 */
public class LayersContribution extends AbstractMenuContributionItem {

    /** The layers icon descriptor. */
    private static final ImageDescriptor DESC_LAYER = DiagramUIPlugin.Implementation.getBundledImageDescriptor("icons/layers.gif"); //$NON-NLS-1$

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractMenuContributionItem#getMenuImage()
     */
    @Override
    protected Image getMenuImage() {
        final Diagram gmfDiagram = this.part.getDiagram();
        if (gmfDiagram != null) {
            try {
                EObject diagram = gmfDiagram.getElement();
                if (diagram instanceof DDiagram) {
                    super.setDiagram((DDiagram) diagram);
                    if (!getActivatedOptionalLayers().isEmpty()) {
                        return DiagramUIPlugin.Implementation.getDecoratedCheckedImage(DESC_LAYER);
                    }
                }
            } catch (IllegalStateException e) {
                // Nothing to log here, this can happen if the resource is not accessible anymore (distant resource).
            }
        }
        return DiagramUIPlugin.getPlugin().getImage(DESC_LAYER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getLabel() {
        return Messages.LayersContribution_label;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractTabbarContribution#setDiagram(org.eclipse.sirius.diagram.DDiagram)
     */
    @Override
    protected void setDiagram(DDiagram diagram) {
        super.setDiagram(diagram);
    }

    @Override
    protected void menuShow(IMenuManager manager) {
        Collection<Layer> layers = getOptionalLayers();
        List<Layer> nonTransientlayers = new ArrayList<>();
        List<Layer> transientLayers = new ArrayList<>();
        for (Layer layer : layers) {
            if (LayerModelHelper.isTransientLayer(layer)) {
                transientLayers.add(layer);
            } else {
                nonTransientlayers.add(layer);
            }
        }
        for (Layer layer : nonTransientlayers) {
            addLayerMenuItem(manager, layer);
        }
        manager.add(new Separator("Transient layers")); //$NON-NLS-1$
        for (Layer layer : transientLayers) {
            addLayerMenuItem(manager, layer);
        }
    }

    /**
     * @return All {@link Layer} of the current {@link DDiagram}.
     */
    private Collection<Layer> getOptionalLayers() {
        DiagramDescription diagramDesc = diagram.getDescription();
        Collection<Layer> allLayers = new ArrayList<Layer>(new DiagramComponentizationManager().getAllLayers(session.getSelectedViewpoints(false), diagramDesc));

        allLayers.remove(diagramDesc.getDefaultLayer());
        Iterables.removeIf(allLayers, new Predicate<Layer>() {

            @Override
            public boolean apply(Layer layer) {
                if (layer instanceof AdditionalLayer) {
                    return !((AdditionalLayer) layer).isOptional();
                }
                return false;
            }
        });
        return allLayers;
    }

    private Collection<Layer> getActivatedOptionalLayers() {
        Collection<Layer> allLayers = new ArrayList<Layer>(new DDiagramQuery(diagram).getAllActivatedLayers()).stream().filter(layer -> {
            if (layer instanceof AdditionalLayer) {
                return ((AdditionalLayer) layer).isOptional();
            }
            return false;
        }).collect(Collectors.toList());
        return allLayers;
    }

    private void addLayerMenuItem(IMenuManager manager, final Layer layer) {
        final String nameEntry = MessageTranslator.INSTANCE.getMessage(layer, new IdentifiedElementQuery(layer).getLabel());
        IAction action = new LayersActivationAction(nameEntry, IAction.AS_CHECK_BOX, diagram, layer);
        // Warning : doesn't work in gtk
        if (!StringUtil.isEmpty(layer.getIcon())) {
            action.setImageDescriptor(ImageProvider.getImageDescriptor(layer.getIcon()));
        }
        manager.add(new ActionContributionItem(action) {
            @Override
            public void dispose() {
                super.dispose();
                ((LayersActivationAction) getAction()).dispose();
            }
        });
        addTooltip(getTooltipText(layer));
    }

    /* will be called else */
    private String getTooltipText(final Layer layer) {
        String endUserDoc = layer.getEndUserDocumentation();
        if (endUserDoc != null && endUserDoc.trim().length() > 0) {
            return endUserDoc;
        }
        final String nameEntry = MessageTranslator.INSTANCE.getMessage(layer, new IdentifiedElementQuery(layer).getLabel());
        return nameEntry;
    }
}
