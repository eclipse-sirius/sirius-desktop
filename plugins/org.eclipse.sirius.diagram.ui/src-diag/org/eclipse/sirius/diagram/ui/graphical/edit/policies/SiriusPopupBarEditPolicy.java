/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.PopupBarEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.tools.PopupBarTool;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.swt.graphics.Image;

/**
 * A {@link PopupBarEditPolicy} with less memory for the array list which store
 * image to be disposed. A memory study showed that the array if often empty.
 * 
 * @author mchauvin, pcdavid
 * @since 0.9.0
 */
public class SiriusPopupBarEditPolicy extends PopupBarEditPolicy {
    /**
     * Constructor. Tweaks memory usage.
     */
    public SiriusPopupBarEditPolicy() {
        this.imagesToBeDisposed = new ArrayList<Object>(3);
    }

    /**
     * Overridden to fill the popup bars using the currently visible tools in
     * the palette.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.PopupBarEditPolicy#fillPopupBarDescriptors()
     */
    @Override
    protected void fillPopupBarDescriptors() {
        if (popupEnabledForDiagram()) {
            if (getPopupBarDescriptors().isEmpty()) {
                fillBasedOnOpenPaletteDrawer();
            }
        }
    }

    /**
     * Tests whether popups are enabled for the current diagram according to its
     * {@link org.eclipse.sirius.viewpoint.description.DiagramDescription}.
     * 
     * @see import
     *      org.eclipse.sirius.description.DiagramDescription#isEnablePopupBars
     *      ()
     */
    private boolean popupEnabledForDiagram() {
        final DDiagram diagram = getCurrentDiagram();
        if (diagram != null && diagram.getDescription() != null) {
            return diagram.getDescription().isEnablePopupBars();
        } else {
            return (Boolean) DescriptionPackage.eINSTANCE.getDiagramDescription_EnablePopupBars().getDefaultValue();
        }
    }

    /**
     * Returns the Sirius DDiagram of which this policy's host is part.
     */
    private DDiagram getCurrentDiagram() {
        final Object model = getHost().getModel();
        DDiagram result = null;
        if (model instanceof View) {
            final EObject element = ((View) model).getElement();
            if (element instanceof DDiagram) {
                result = (DDiagram) element;
            } else if (element instanceof DDiagramElement) {
                result = ((DDiagramElement) element).getParentDiagram();
            }
        }
        return result;
    }

    /**
     * Adds popup bar descriptors for all the shape tools in the palette drawer
     * that is initially open.
     */
    private void fillBasedOnOpenPaletteDrawer() {
        final PaletteViewer paletteViewer = getHost().getViewer().getEditDomain().getPaletteViewer();
        if (paletteViewer != null) {
            for (Object child : paletteViewer.getPaletteRoot().getChildren()) {
                if (child instanceof PaletteDrawer) {
                    final PaletteDrawer drawer = (PaletteDrawer) child;
                    if (drawer.isVisible()) {
                        fillWithPaletteToolsInContainer(drawer);
                    }
                }
            }
        }
    }

    /**
     * Adds popup bar descriptors for all the shape tools in the given palette
     * container. Subclasses may override if they wish to customize this
     * behavior for their diagram.
     * 
     * @param palContainer
     *            the <code>PaletteContainer</code>
     */
    private void fillWithPaletteToolsInContainer(final PaletteContainer palContainer) {
        final List<?> theEntries = palContainer.getChildren();
        for (int i = 0; i < theEntries.size(); i++) {
            final PaletteEntry theEntry = (PaletteEntry) theEntries.get(i);
            if (theEntry instanceof CreationToolEntry) {
                addPopupBarEntryFor((CreationToolEntry) theEntry);
            } else if (theEntry instanceof PaletteStack) {
                fillWithPaletteToolsInContainer((PaletteStack) theEntry);
            } else {
                // Ignore other kinds of entries.
            }
        }
    }

    /**
     * Add a popup bar entry corresponding to the specified CreationTool if it
     * is enabled for the current element.
     */
    private void addPopupBarEntryFor(final CreationToolEntry tool) {
        final CreationFactory factory = (CreationFactory) tool.getToolProperty(org.eclipse.gef.tools.CreationTool.PROPERTY_CREATION_FACTORY);
        if (factory != null) {
            final AbstractToolDescription desc = (AbstractToolDescription) factory.getNewObject();
            final IElementType elementType = getElementType(desc);
            if (elementType != null) {
                final String tip = tool.getLabel();
                final Image icon;
                if (tool.getSmallIcon() != null) {
                    icon = DiagramUIPlugin.getPlugin().getImage(tool.getSmallIcon());
                } else {
                    icon = null;
                }
                final CreateRequest req = new CreateRequest();
                req.setFactory(factory);
                final PopupBarTool popupBarTool = new PopupBarTool(getHost(), req);
                if (popupBarTool.isCommandEnabled()) {
                    addPopupBarDescriptor(elementType, icon, popupBarTool, tip);
                }
            }
        }
    }

    /**
     * Returns the type of element created by the specified
     * {@link AbstractToolDescription} in the format expected by GMF, or
     * <code>null</code> if the AbstractToolDescription's type is not supported.
     */
    private IElementType getElementType(final AbstractToolDescription desc) {
        EClass klass = null;

        if (desc instanceof NodeCreationDescription) {
            klass = DiagramPackage.eINSTANCE.getDNode();
        } else if (desc instanceof ContainerCreationDescription) {
            klass = DiagramPackage.eINSTANCE.getDDiagramElementContainer();
        } else if (desc instanceof SelectionWizardDescription || desc instanceof PaneBasedSelectionWizardDescription) {
            /*
             * return a fake class as element type is not used by popup bar
             * descriptor
             */
            klass = DiagramPackage.eINSTANCE.getDNode();
        } else if (desc instanceof ToolDescription) {
            /*
             * return a fake class as element type is not used by popup bar
             * descriptor
             */
            klass = DiagramPackage.eINSTANCE.getDNode();
        }

        if (klass != null) {
            return ElementTypeRegistry.getInstance().getElementType(klass);
        } else {
            return null;
        }
    }
}
