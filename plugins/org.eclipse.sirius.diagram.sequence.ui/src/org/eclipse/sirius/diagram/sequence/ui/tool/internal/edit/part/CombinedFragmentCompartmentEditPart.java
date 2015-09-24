/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.CombinedFragmentInvisibleResizableCompartmentFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;

/**
 * A specific DNodeContainerViewNodeContainerCompartmentEditPart to remove the
 * scroll bars.
 * 
 * @author smonnier
 */
public class CombinedFragmentCompartmentEditPart extends DNodeContainerViewNodeContainerCompartmentEditPart {
    /**
     * The visual ID. Same as a normal container compartment.
     * 
     * @see DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID.
     */
    public static final int VISUAL_ID = 7001;

    /**
     * Constructor.
     * 
     * @param view
     *            the view <code>controlled</code> by this edit part.
     */
    public CombinedFragmentCompartmentEditPart(View view) {
        super(view);
    }

    /**
     * Overridden to install a specific edit policy managing the moving and
     * resizing requests on combined fragment.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (!EditPolicy.CONTAINER_ROLE.equals(key)) {
            super.installEditPolicy(key, editPolicy);
        }
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    /**
     * Overridden to remove the scroll bars.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public IFigure createFigure() {
        ShapeCompartmentFigure scf = new CombinedFragmentInvisibleResizableCompartmentFigure(getCompartmentName(), getMapMode());
        // Remove the shadow border to avoid unwanted spacing
        scf.setBorder(null);
        scf.getContentPane().setLayoutManager(getLayoutManager());
        scf.getContentPane().addLayoutListener(LayoutAnimator.getDefault());
        scf.setTitleVisibility(false);
        scf.setToolTip((IFigure) null);
        scf.getScrollPane().setHorizontalScrollBarVisibility(ScrollPane.NEVER);
        scf.getScrollPane().setVerticalScrollBarVisibility(ScrollPane.NEVER);
        return scf;
    }

    @Override
    protected void configureBorder(ResizableCompartmentFigure rcf) {
        rcf.setBorder(null);
    }

    @Override
    protected void configureScrollPaneBorder(ScrollPane scrollPane, ContainerStyle ownedStyle) {
        int mb = getMapMode().DPtoLP(0);
        scrollPane.setBorder(new MarginBorder(mb, mb, mb, mb));
    }
}
