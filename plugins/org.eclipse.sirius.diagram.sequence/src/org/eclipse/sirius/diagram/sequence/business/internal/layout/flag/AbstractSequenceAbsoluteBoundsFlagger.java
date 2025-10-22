/*******************************************************************************
 * Copyright (c) 2011, 2025 THALES GLOBAL SERVICES and others
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
package org.eclipse.sirius.diagram.sequence.business.internal.layout.flag;

import java.util.Collection;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Helper to compute and attach absolute bounds flag for sequence events.
 * 
 * @author mporhel
 */
public abstract class AbstractSequenceAbsoluteBoundsFlagger {

    /**
     * Compute absolute bounds flags for each delimited sequence events of the current diagram.
     */
    public final void flag() {
        for (ISequenceElement ise : getEventsToFlag()) {
            flag(ise);
        }
    }

    private void flag(ISequenceElement ise) {
        // add flag
        EObject element = ise.getNotationView().getElement();
        if (element instanceof DDiagramElement) {
            DDiagramElement dde = (DDiagramElement) element;
            Rectangle absBounds = ise.getProperLogicalBounds();

            AbsoluteBoundsFilter flag = getOrCreateFlag(dde);

            if (ise instanceof LostMessageEnd && !((LostMessageEnd) ise).getMessage().some()) {
                flag.setX(LayoutConstants.TOOL_CREATION_FLAG_FROM_SEMANTIC.x);
            }

            // Update flag
            if (flag != null && absBounds != null) {
                if (ise instanceof Message) {
                    // Reset default values, x and width are not used for
                    // Message standard layout.
                    // Specific flags set by commands and refresh
                    // extensions (LayoutConstants.TOOL_CREATION_FLAG,
                    // LayoutConstants.TOOL_CREATION_FLAG_FROM_SEMANTIC,
                    // LayoutConstants.EXTERNAL_CHANGE_FLAG) to detect unsafe
                    // situations have been used by the SequenceLayout triggered
                    // by those commands.
                    flag.setX((Integer) DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_X().getDefaultValue());
                    flag.setWidth((Integer) DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_Width().getDefaultValue());
                } else {
                    flag.setX(absBounds.x);
                    flag.setWidth(absBounds.width);
                }
                flag.setY(absBounds.y);
                flag.setHeight(absBounds.height);
            }
        }
    }

    /**
     * Gets events to flag.
     * 
     * @return a collection of events to flag.
     */
    protected abstract Collection<ISequenceElement> getEventsToFlag();

    private AbsoluteBoundsFilter getOrCreateFlag(DDiagramElement dde) {
        AbsoluteBoundsFilter flag = null;
        Collection<AbsoluteBoundsFilter> flags = Lists.newArrayList(Iterables.filter(dde.getGraphicalFilters(), AbsoluteBoundsFilter.class));
        for (AbsoluteBoundsFilter prevFlag : flags) {
            flag = prevFlag;
            break;
        }

        if (flag == null) {
            flag = DiagramFactory.eINSTANCE.createAbsoluteBoundsFilter();
            dde.getGraphicalFilters().add(flag);
        }
        return flag;
    }

}
