/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.color.AbstractColorUpdater;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AirXYLayoutEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.ContainerCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LaunchBehaviorToolEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.RefreshSiriusElementEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.ResetOriginEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.RevealElementsEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.RevealSelectedElementsEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusContainerDropPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusPropertyHandlerEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DDiagramItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.policy.CompoundEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.diagram.ui.tools.internal.part.SiriusDiagramGraphicalViewer;
import org.eclipse.sirius.diagram.ui.tools.internal.ruler.SiriusSnapToHelperUtil;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.swt.graphics.Color;

/**
 * @was-generated
 */
public class DDiagramEditPart extends AbstractDDiagramEditPart {

    /**
     * @was-generated
     */
    public final static String MODEL_ID = "Sirius"; //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 1000;

    private static final RGBValues WHITE = RGBValues.create(255, 255, 255);

    /**
     * @was-generated
     */
    public DDiagramEditPart(final View view) {
        super(view);
    }

    /**
     * @not-generated add the compound edit policy
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DDiagramItemSemanticEditPolicy());
        installEditPolicy(EditPolicy.CONTAINER_ROLE, new ContainerCreationEditPolicy());
        installEditPolicy(RequestConstants.REQ_LAUNCH_TOOL, new LaunchToolEditPolicy());
        final CompoundEditPolicy compoundEditPolicy = new CompoundEditPolicy();
        compoundEditPolicy.addEditPolicy(new RefreshSiriusElementEditPolicy());
        compoundEditPolicy.addEditPolicy(new RevealElementsEditPolicy());
        compoundEditPolicy.addEditPolicy(new RevealSelectedElementsEditPolicy());
        compoundEditPolicy.addEditPolicy(new LaunchBehaviorToolEditPolicy());
        compoundEditPolicy.addEditPolicy(new ResetOriginEditPolicy());
        if (this.getEditPolicy(EditPolicy.COMPONENT_ROLE) != null) {
            compoundEditPolicy.addEditPolicy(this.getEditPolicy(EditPolicy.COMPONENT_ROLE));
            removeEditPolicy(EditPolicy.COMPONENT_ROLE);
        }
        installEditPolicy(EditPolicy.COMPONENT_ROLE, compoundEditPolicy);
        removeEditPolicy(EditPolicy.LAYOUT_ROLE);
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new AirXYLayoutEditPolicy());
        removeEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE);
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new SiriusContainerDropPolicy());

        // Enables Font and Style action
        removeEditPolicy(EditPolicyRoles.PROPERTY_HANDLER_ROLE);
        installEditPolicy(EditPolicyRoles.PROPERTY_HANDLER_ROLE, new SiriusPropertyHandlerEditPolicy());
    }

    @Override
    public void deactivate() {
        deactivateModes();
        super.deactivate();
    }

    @Override
    protected IFigure createFigure() {
        IFigure fig = super.createFigure();
        configureBackground(fig);
        return fig;
    }

    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        configureBackground(getFigure());
    }

    /**
     * Deactivates the Layouting and show/hide Modes of the {@link DDiagram} (if needed).
     */
    private void deactivateModes() {
        if (getModel() instanceof Diagram) {
            Diagram diagramGMF = (Diagram) getModel();
            try {
                if (diagramGMF.getElement() instanceof DDiagram) {

                    DDiagram dDiagram = (DDiagram) diagramGMF.getElement();
                    if (dDiagram.isIsInLayoutingMode()) {
                        getEditingDomain().getCommandStack().execute(new SetLayoutingModeCommand(getEditingDomain(), dDiagram, false));
                    }
                    if (dDiagram.isIsInShowingMode()) {
                        String commandLabel = dDiagram.isIsInShowingMode() ? Messages.SetShowingModeCommandAndUpdateActionImage_deactivateLabel
                                : Messages.SetShowingModeCommandAndUpdateActionImage_activateLabel;

                        getEditingDomain().getCommandStack().execute(new SetShowingModeCommand(getEditingDomain(), dDiagram, commandLabel, false));
                    }
                }
            } catch (IllegalStateException e) {
                // If the DDiagram associated to this GMF Diagram is not
                // accessible any more, we do not modify layouting mode
            }
        }
    }

    /**
     * A command that changes the show/hide activation status of the given {@link DDiagram}.
     *
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    public static class SetShowingModeCommand extends SiriusCommand {

        /**
         * The {@link DDiagram} on witch the layouting mode should be switched.
         */
        private DDiagram diagram;

        /**
         * Indicates whether the Layouting Mode should be enabled.
         */
        private boolean showingModeShouldBeEnabled;

        /**
         * Constructor.
         *
         * @param editingDomain
         *            the editing domain
         * @param diagram
         *            the {@link DDiagram} on witch the layouting mode should be switched
         * @param showingModeShouldBeEnabled
         *            indicates whether the show/hide mode should be enabled or disabled
         */
        public SetShowingModeCommand(TransactionalEditingDomain editingDomain, DDiagram diagram, String label, boolean showingModeShouldBeEnabled) {
            super(editingDomain, label);
            this.diagram = diagram;
            this.showingModeShouldBeEnabled = showingModeShouldBeEnabled;
        }

        @Override
        protected void doExecute() {
            this.diagram.setIsInShowingMode(showingModeShouldBeEnabled);
        }

    }

    /**
     * A command that changes the LayoutingMode of the given {@link DDiagram}.
     *
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     *
     */
    public static class SetLayoutingModeCommand extends SiriusCommand {

        /**
         * The {@link DDiagram} on witch the layouting mode should be switched.
         */
        private DDiagram diagram;

        /**
         * Indicates whether the Layouting Mode should be enabled.
         */
        private boolean layoutingModeShouldBeEnabled;

        /**
         * Constructor.
         *
         * @param editingDomain
         *            the editing domain
         * @param diagram
         *            the {@link DDiagram} on witch the layouting mode should be switched
         * @param layoutingModeShouldBeEnabled
         *            indicates whether the layouting mode should be enabled or disabled
         */
        public SetLayoutingModeCommand(TransactionalEditingDomain editingDomain, DDiagram diagram, boolean layoutingModeShouldBeEnabled) {
            super(editingDomain, Messages.SetLayoutingModeCommand_deactivateLabel);
            this.diagram = diagram;
            this.layoutingModeShouldBeEnabled = layoutingModeShouldBeEnabled;
            if (layoutingModeShouldBeEnabled) {
                setLabel(Messages.SetLayoutingModeCommand_activateLabel);
            }
        }

        @Override
        protected void doExecute() {
            this.diagram.setIsInLayoutingMode(layoutingModeShouldBeEnabled);
        }

    }

    @Override
    public Object getAdapter(Class key) {
        if (key == SnapToHelper.class) {
            return SiriusSnapToHelperUtil.getSnapHelper(this);
        }

        Object adapter = null;
        try {
            adapter = super.getAdapter(key);
        } catch (IllegalStateException e) {
            // We do not log this exception that might be caused by an unreachable distant resource.
        }
        return adapter;
    }

    /**
     * Configure the background of a given figure by using color defined on semantic diagram description.
     *
     * @param fig
     *            figure with the background to configure
     */
    public synchronized void configureBackground(IFigure fig) {
        if (resolveDDiagram().some() && fig != null) {
            DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) this.resolveDDiagram().get();
            ColorDescription colorDesc = dSemanticDiagram.getDescription().getBackgroundColor();
            RGBValues rgb = WHITE;
            if (colorDesc != null) {
                rgb = new AbstractColorUpdater().getRGBValuesFromColorDescription(dSemanticDiagram.getTarget(), colorDesc);
            }

            Color previousColor = fig.getBackgroundColor();
            if (!sameColor(previousColor, rgb)) {
                EditPartViewer viewer = this.getViewer();
                if (viewer instanceof SiriusDiagramGraphicalViewer) {
                    Color backgroundColor;
                    if (WHITE.equals(rgb)) {
                        backgroundColor = null;
                    } else {
                        backgroundColor = new Color(viewer.getControl().getDisplay(), rgb.getRed(), rgb.getGreen(), rgb.getBlue());
                    }
                    fig.setBackgroundColor(backgroundColor);
                    fig.setOpaque(backgroundColor != null);
                    ((SiriusDiagramGraphicalViewer) viewer).setBackgroundColor(backgroundColor);
                }
            }
        }
    }

    private boolean sameColor(Color current, RGBValues expected) {
        if (current == null) {
            return WHITE.equals(expected);
        } else {
            return current.getRed() == expected.getRed() && current.getGreen() == expected.getGreen() && current.getBlue() == expected.getBlue();
        }
    }
}
