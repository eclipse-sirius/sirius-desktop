/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.layout;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.flag.SequenceDiagramAbsoluteBoundsFlagger;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.horizontal.SequenceHorizontalLayout;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.observation.SequenceObservationLayout;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.vertical.SequenceVerticalLayout;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.collect.Iterables;

/**
 * Computes the appropriate graphical locations of sequence events and lifelines on a sequence diagram to reflect the
 * semantic order.
 * 
 * @author pcdavid, mporhel
 */
public class SequenceLayout {

    private final Option<SequenceDiagram> sequenceDiagram;

    private SequenceHorizontalLayout sequenceHorizontalLayout;

    private SequenceVerticalLayout sequenceVerticalLayout;

    private SequenceObservationLayout sequenceObservationLayout;

    /**
     * Constructor.
     * 
     * @param diagram
     *            the sequence diagram for which to compute the messages locations.
     */
    public SequenceLayout(Diagram diagram) {
        this.sequenceDiagram = ISequenceElementAccessor.getSequenceDiagram(diagram);
        this.sequenceHorizontalLayout = new SequenceHorizontalLayout(sequenceDiagram.get());
        this.sequenceVerticalLayout = new SequenceVerticalLayout(sequenceDiagram.get());
        this.sequenceObservationLayout = new SequenceObservationLayout(sequenceDiagram.get());
    }

    public Option<SequenceDiagram> getSequenceDiagram() {
        return sequenceDiagram;
    }

    /**
     * Compute and apply horizontal layout. Should be use in a {@link org.eclipse.emf.transaction.RecordingCommand}.
     * 
     * @param pack
     *            pack the space between instance roles.
     * @return true if horizontal layout has been done
     */
    public boolean horizontalLayout(boolean pack) {
        if (this.sequenceHorizontalLayout != null && this.sequenceDiagram.some()) {
            return this.sequenceHorizontalLayout.layout(pack);
        }
        return false;
    }

    /**
     * Compute and apply vertical layout. Should be use in a {@link org.eclipse.emf.transaction.RecordingCommand}.
     * 
     * @param pack
     *            pack the space between sequence events
     * @return true if vertical layout has been done
     */
    public boolean verticalLayout(boolean pack) {
        if (this.sequenceVerticalLayout != null && this.sequenceDiagram.some()) {
            return this.sequenceVerticalLayout.layout(pack);
        }
        return false;
    }

    /**
     * Compute and apply observation layout. Should be use in a {@link org.eclipse.emf.transaction.RecordingCommand} and
     * after vertical and horizontal layout.
     * 
     * It will place the ObservationPoint.
     * 
     * @param pack
     *            pack the space between sequence events
     * @return true if horizontal layout has been done
     */
    public boolean observationLayout(boolean pack) {
        if (this.sequenceObservationLayout != null && this.sequenceDiagram.some()) {
            return this.sequenceObservationLayout.layout(pack);
        }
        return false;
    }

    /**
     * Flag DDiagramElement with their absolute bounds.
     */
    public void flagSequenceEvents() {
        // Flag event with their new position
        if (this.sequenceDiagram.some()) {
            SequenceDiagramAbsoluteBoundsFlagger flagHelper = new SequenceDiagramAbsoluteBoundsFlagger(sequenceDiagram.get());
            flagHelper.flag();

            updateCollapseFilters();
        }
    }

    private void updateCollapseFilters() {
        for (ISequenceNode isn : Iterables.concat(sequenceDiagram.get().getAllAbstractNodeEvents(), sequenceDiagram.get().getAllObservationPoints(), sequenceDiagram.get().getAllLifelines())) {
            Node node = isn.getNotationNode();
            if (node.getElement() instanceof DDiagramElement && node.getLayoutConstraint() instanceof Size) {
                DDiagramElement dde = (DDiagramElement) node.getElement();
                if (new DDiagramElementQuery(dde).isIndirectlyCollapsed()) {
                    Size size = (Size) node.getLayoutConstraint();
                    for (CollapseFilter collapseFilter : Iterables.filter(dde.getGraphicalFilters(), CollapseFilter.class)) {
                        collapseFilter.setHeight(size.getHeight());
                    }
                }
            }
        }
    }
}
