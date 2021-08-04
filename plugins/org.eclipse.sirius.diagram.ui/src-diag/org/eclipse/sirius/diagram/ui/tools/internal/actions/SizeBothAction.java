/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.query.model.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.ui.IWorkbenchPage;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Sirius specific size both action:
 * <UL>
 * <LI>disable the action on Region and Region container.</LI>
 * <LI>check authority permission in calculateEnabled()./LI>
 * </UL>
 * 
 * @author mporhel
 * 
 */
@SuppressWarnings("restriction")
public class SizeBothAction extends org.eclipse.gmf.runtime.diagram.ui.actions.internal.SizeBothAction {

    /**
     * Creates the Make Same Size Both Action.
     * 
     * @param workbenchPage
     *            the workbench page.
     */
    public SizeBothAction(IWorkbenchPage workbenchPage) {
        super(workbenchPage);
    }

    @Override
    protected boolean calculateEnabled() {
        boolean enabled = super.calculateEnabled();
        return enabled && canEditInstance();
    }

    /**
     * Inspired from org.eclipse.gmf.runtime.diagram.ui.actions.internal.SizeBothAction.getCommand().
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected Command getCommand() {
        // Create a compound command to hold the resize commands
        CompoundCommand doResizeCmd = new CompoundCommand();

        // Create an iterator for the selection
        Iterator iter = getSelectedObjects().iterator();

        // Get the Primary Selection
        int last = getSelectedObjects().size() - 1;
        IGraphicalEditPart primary = (IGraphicalEditPart) getSelectedObjects().get(last);

        if (concernRegion(primary)) {
            List<AbstractDiagramElementContainerEditPart> primaryRegions = getRegionParts((AbstractDiagramContainerEditPart) primary);

            while (iter.hasNext()) {
                IGraphicalEditPart toResize = (IGraphicalEditPart) iter.next();
                if (toResize != primary && concernRegion(toResize)) {
                    List<AbstractDiagramElementContainerEditPart> toResizeRegions = getRegionParts((AbstractDiagramContainerEditPart) toResize);
                    int toResizeSize = toResizeRegions.size();
                    int primarySize = primaryRegions.size();
                    if (toResizeSize <= primarySize) {
                        for (int i = 0; i < toResizeSize; i++) {
                            AbstractDiagramElementContainerEditPart toResizeRegion = toResizeRegions.get(i);
                            AbstractDiagramElementContainerEditPart primaryRegion = primaryRegions.get(i);

                            Dimension newDimension = null;

                            // Case where there is more region in the primary container than the one we are resizing. In
                            // that case, the last region takes the size of the remaining regions in the primary region
                            // container.
                            if (i == toResizeSize - 1 && primarySize > toResizeSize) {
                                newDimension = computeLastRegionDimension(primary, primaryRegions, primarySize, i, primaryRegion);

                            }
                            if (newDimension == null) {
                                newDimension = getPrimaryRegionSize(primaryRegion);
                            }
                            View resizeView = (View) toResizeRegion.getModel();

                            doResizeCmd.add(new ICommandProxy(new SetBoundsCommand(toResize.getEditingDomain(), "", new EObjectAdapter(resizeView), newDimension))); //$NON-NLS-1$
                        }

                    }
                }
            }

            return doResizeCmd.unwrap();
        } else {
            return super.getCommand();
        }

    }

    private Dimension computeLastRegionDimension(IGraphicalEditPart primary, List<AbstractDiagramElementContainerEditPart> primaryRegions, int primarySize, int currentIndex,
            AbstractDiagramElementContainerEditPart primaryRegion) {
        Dimension newDimension = null;
        Optional<DNodeContainer> optional = Optional.of(((AbstractDiagramContainerEditPart) primary).resolveDiagramElement()).filter(DNodeContainer.class::isInstance).map(DNodeContainer.class::cast);
        if (optional.isPresent()) {
            DNodeContainerExperimentalQuery query = new DNodeContainerExperimentalQuery(optional.get());
            newDimension = getPrimaryRegionSize(primaryRegion);
            for (int j = currentIndex + 1; j < primarySize; j++) {
                Dimension tempDim = getPrimaryRegionSize(primaryRegions.get(j));

                if (query.isHorizontaltackContainer()) {
                    newDimension.expand(tempDim.width, 0);
                } else {

                    newDimension.expand(0, tempDim.height);
                }
            }
        }
        return newDimension;
    }

    private Dimension getPrimaryRegionSize(AbstractDiagramElementContainerEditPart primaryRegion) {
        Dimension newDimension;
        View primaryView = (View) primaryRegion.getModel();
        Integer width = (Integer) ViewUtil.getStructuralFeatureValue(primaryView, NotationPackage.eINSTANCE.getSize_Width());
        Integer height = (Integer) ViewUtil.getStructuralFeatureValue(primaryView, NotationPackage.eINSTANCE.getSize_Height());
        if (width.intValue() == -1 || height.intValue() == -1)
            newDimension = primaryRegion.getFigure().getSize().getCopy();
        else
            newDimension = new Dimension(width.intValue(), height.intValue());
        return newDimension;
    }

    /**
     * Check authority permission.
     *
     * @return true if the action can be launched on the diagram
     */
    protected boolean canEditInstance() {
        boolean canEditInstance = true;
        if (getWorkbenchPart() instanceof DDiagramEditor && ((DDiagramEditor) getWorkbenchPart()).getRepresentation() instanceof DDiagram) {
            final DDiagramEditor editor = (DDiagramEditor) getWorkbenchPart();
            final DDiagram editorDiagram = (DDiagram) editor.getRepresentation();
            Resource sessionResource = editor.getSession().getSessionResource();
            if (sessionResource != null) {
                IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(sessionResource.getResourceSet());
                canEditInstance = permissionAuthority.canEditInstance(editorDiagram);
            }
        }

        return canEditInstance;
    }

    private boolean concernRegion(EditPart hostPart) {
        if (hostPart instanceof AbstractDiagramContainerEditPart) {
            return ((AbstractDiagramContainerEditPart) hostPart).isRegionContainer();
        }
        return false;
    }

    private List<AbstractDiagramElementContainerEditPart> getRegionParts(AbstractDiagramContainerEditPart host) {
        AbstractDNodeContainerCompartmentEditPart comp = Iterables.getFirst(Iterables.filter(host.getChildren(), AbstractDNodeContainerCompartmentEditPart.class), null);
        if (comp != null) {
            return Lists.newArrayList(Iterables.filter(comp.getChildren(), AbstractDiagramElementContainerEditPart.class));
        }
        return Collections.emptyList();
    }
}
