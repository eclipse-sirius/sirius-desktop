/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
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

import java.util.Optional;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DescriptionCompartmentEditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.TextAlignment;
import org.eclipse.gmf.runtime.notation.TextStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;

/**
 * A specific DescriptionCompartmentEditPart to:
 * <UL>
 * continue the selection with its parents. This allows to handle the
 * <LI>direct edit (slow double click) directly on Note and Text,</LI>
 * <LI>and to have specific behavior for Sirius Note and Text: capability to change vertical alignment of text through a
 * specific EAnnotation.</LI>
 * </UL>
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusDescriptionCompartmentEditPart extends DescriptionCompartmentEditPart {

    /**
     * Constructor
     * 
     * @param view
     *            the view controlled by this edit part
     */
    public SiriusDescriptionCompartmentEditPart(View view) {
        super(view);
    }

    /* Send the request to the parent (Note or Text). */
    @Override
    public DragTracker getDragTracker(Request request) {
        return getParent().getDragTracker(request);
    }

    @Override
    protected void addNotationalListeners() {
        super.addNotationalListeners();
        if (hasNotationView()) {
            ViewQuery viewQuery = new ViewQuery((View) getModel());
            Optional<DDiagram> diagram = viewQuery.getDDiagram();
            if (diagram.isPresent()) {
                addListenerFilter("ShowingMode", this, diagram.get(), DiagramPackage.eINSTANCE.getDDiagram_IsInShowingMode()); //$NON-NLS-1$
            }
        }
    }

    @Override
    protected void removeNotationalListeners() {
        super.removeNotationalListeners();
        removeListenerFilter("ShowingMode"); //$NON-NLS-1$
    }
    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#setVisibility(boolean)
     */
    @Override
    protected void setVisibility(boolean vis) {
        ShowingViewUtil.setVisibility(this, vis, SELECTED_NONE, getFlag(FLAG__AUTO_CONNECTIONS_VISIBILITY));
    }

    /*
     * (non-Javadoc) Overridden to allow to consider the specific EAnnotation to change vertical alignment.
     */
    @Override
    protected void refreshTextAlignment() {
        int verticalAlignment = PositionConstants.MIDDLE;
        View view = getPrimaryView();
        EAnnotation specificStyles = view.getEAnnotation(ViewQuery.SPECIFIC_STYLES);
        if (specificStyles != null) {
            String stringVerticalAlignment = specificStyles.getDetails().get(ViewQuery.VERTICAL_ALIGNMENT);
            if (stringVerticalAlignment != null) {
                verticalAlignment = new Integer(stringVerticalAlignment).intValue();
            }
        }
        TextStyle style = (TextStyle) view.getStyle(NotationPackage.eINSTANCE.getTextStyle());
        if (style != null) {
            if (style.getTextAlignment() == TextAlignment.RIGHT_LITERAL) {
                if ((getLabelDelegate().getIcon(0) == null))
                    if (verticalAlignment != PositionConstants.MIDDLE) {
                        getLabelDelegate().setAlignment(verticalAlignment | PositionConstants.RIGHT);
                    } else {
                        getLabelDelegate().setAlignment(PositionConstants.RIGHT);
                    }
                else
                    getLabelDelegate().setTextJustification(PositionConstants.RIGHT);
            } else if (style.getTextAlignment() == TextAlignment.CENTER_LITERAL) {
                if ((getLabelDelegate().getIcon(0) == null))
                    if (verticalAlignment == PositionConstants.TOP) {
                        getLabelDelegate().setAlignment(PositionConstants.TOP);
                    } else if (verticalAlignment == PositionConstants.BOTTOM) {
                        getLabelDelegate().setAlignment(PositionConstants.BOTTOM);
                    } else {
                        getLabelDelegate().setAlignment(PositionConstants.CENTER);
                    }
                else {
                    getLabelDelegate().setTextJustification(PositionConstants.CENTER);
                }
            } else {
                // default to TextAlignment.LEFT_LITERAL
                if ((getLabelDelegate().getIcon(0) == null))
                    if (verticalAlignment != PositionConstants.MIDDLE) {
                        getLabelDelegate().setAlignment(verticalAlignment | PositionConstants.LEFT);
                    } else {
                        getLabelDelegate().setAlignment(PositionConstants.LEFT);
                    }
                else
                    getLabelDelegate().setTextJustification(PositionConstants.LEFT);
            }
        }
    }

}
