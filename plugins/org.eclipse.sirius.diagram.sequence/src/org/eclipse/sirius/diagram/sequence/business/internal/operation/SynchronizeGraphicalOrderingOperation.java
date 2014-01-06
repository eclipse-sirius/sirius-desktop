/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.operation;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.SequenceLayout;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Preconditions;

/**
 * Refreshes the graphical locations of the elements in a sequence diagram to
 * reflect the current semantic ordering. This command assumes that the
 * {@link org.eclipse.sirius.diagram.sequence.ordering.GraphicalOrdering} and
 * the {@link org.eclipse.sirius.diagram.sequence.ordering.SemanticOrdering}
 * are up to date according to the current visual (resp. semantic) order but
 * that when they do not match, the semantic ordering is the authoritative one
 * and the graphical ordering should be changed to match it.
 * 
 * @author pcdavid, smonnier
 */
public class SynchronizeGraphicalOrderingOperation extends AbstractModelChangeOperation<Void> {

    private final Diagram sequenceDiagram;

    private final boolean pack;

    /**
     * Constructor.
     * 
     * @param sequenceDiagram
     *            the sequence diagram to refresh.
     * @param pack
     *            if <code>true</code> the diagram will be packed
     */
    public SynchronizeGraphicalOrderingOperation(Diagram sequenceDiagram, boolean pack) {
        super("Synchronize graphical ordering");
        this.sequenceDiagram = Preconditions.checkNotNull(sequenceDiagram);
        this.pack = pack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        Preconditions.checkNotNull(sequenceDiagram);
        SequenceLayout sequenceLayout = new SequenceLayout(sequenceDiagram);
        Option<SequenceDiagram> sd = sequenceLayout.getSequenceDiagram();

        if (sd.some()) {
            SequenceDDiagram diagram = (SequenceDDiagram) sd.get().getNotationDiagram().getElement();

            if (diagram != null && (diagram.getGraphicalOrdering().getEventEnds().size() == diagram.getSemanticOrdering().getEventEnds().size())) {
                boolean verticalLayout = sequenceLayout.verticalLayout(pack);
                boolean horizontalLayout = sequenceLayout.horizontalLayout(pack);
                boolean observationLayout = sequenceLayout.observationLayout(pack);

                // Refresh flags when a layout made modifications.
                if (verticalLayout || horizontalLayout || observationLayout) {
                    sequenceLayout.flagSequenceEvents();
                }
            }
        }
        return null;
    }
}
