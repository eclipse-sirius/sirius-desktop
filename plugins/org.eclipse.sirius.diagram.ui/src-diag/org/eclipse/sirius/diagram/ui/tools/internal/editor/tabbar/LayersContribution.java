/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.ImageProvider;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.LayersActivationAction;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.layers.LayersActivationAdapter;
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

    private LayersActivationAdapter adapter;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractMenuContributionItem#getMenuImage()
     */
    @Override
    protected Image getMenuImage() {
        final Diagram gmfDiagram = this.part.getDiagram();
        if (gmfDiagram != null) {
            EObject diagram = gmfDiagram.getElement();
            if (diagram instanceof DDiagram) {
                super.setDiagram((DDiagram) diagram);
                if (!getActivatedLayers().isEmpty()) {
                    return DiagramUIPlugin.Implementation.getDecoratedCheckedImage(DESC_LAYER);
                }
            }
        }
        return DiagramUIPlugin.getPlugin().getImage(DESC_LAYER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getLabel() {
        return "Layers";
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractTabbarContribution#setDiagram(org.eclipse.sirius.diagram.DDiagram)
     */
    @Override
    protected void setDiagram(DDiagram diagram) {
        super.setDiagram(diagram);
        final DDiagramEditor diagramEditor = (DDiagramEditor) part.getDiagramGraphicalViewer().getProperty(DDiagramEditor.EDITOR_ID);
        adapter = new LayersActivationAdapter();
        adapter.setPaletteManager(diagramEditor.getPaletteManager());
        if (!diagram.eAdapters().contains(adapter)) {
            diagram.eAdapters().add(adapter);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractMenuContributionItem#menuShow(org.eclipse.jface.action.IMenuManager)
     */
    @Override
    protected void menuShow(IMenuManager manager) {
        for (final Layer layer : getLayers()) {
            addLayerMenuItem(manager, layer);
        }
    }

    private Collection<Layer> getLayers() {
        DiagramDescription diagramDesc = diagram.getDescription();
        Collection<Layer> allLayers = new ArrayList<Layer>(new DiagramComponentizationManager().getAllLayers(session.getSelectedViewpoints(false), diagramDesc));

        allLayers.remove(diagramDesc.getDefaultLayer());
        Iterables.removeIf(allLayers, new Predicate<Layer>() {

            public boolean apply(Layer layer) {
                if (layer instanceof AdditionalLayer) {
                    return !((AdditionalLayer) layer).isOptional();
                }
                return false;
            }
        });
        return allLayers;
    }

    private Collection<Layer> getActivatedLayers() {
        DiagramDescription diagramDesc = diagram.getDescription();
        Collection<Layer> allLayers = new ArrayList<Layer>(diagram.getActivatedLayers());
        allLayers.remove(diagramDesc.getDefaultLayer());
        return allLayers;
    }

    private void addLayerMenuItem(IMenuManager manager, final Layer layer) {
        IAction action = new LayersActivationAction(new IdentifiedElementQuery(layer).getLabel(), IAction.AS_CHECK_BOX, diagram, layer);
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
        return new IdentifiedElementQuery(layer).getLabel();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractTabbarContribution#dispose()
     */
    @Override
    public void dispose() {
        // Diagram can be null if the editor has been opened from the project
        // explorer
        if (diagram != null) {
            diagram.eAdapters().remove(adapter);
        }
        adapter = null;
        super.dispose();
    }

}
