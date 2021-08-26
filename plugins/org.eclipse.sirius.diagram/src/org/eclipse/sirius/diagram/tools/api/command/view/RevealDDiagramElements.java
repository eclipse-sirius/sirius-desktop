/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.api.command.view;

import java.util.Set;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.tools.internal.Messages;

/**
 * A simple command to reveal diagram elements.
 * 
 * @author Damien Lecan (damien.lecan@obeo.fr)
 * @since 0.9.0
 */
public class RevealDDiagramElements extends RecordingCommand {

    /**
     * Label for hide element.
     */
    public static final String REVEAL_ELEMENTS_LABEL = Messages.RevealDDiagramElements_revealElementsLabel;

    /**
     * Label for hide element.
     */
    public static final String REVEAL_ELEMENT_LABEL = Messages.RevealDDiagramElements_revealElementLabel;

    /** The viewpoint elements to reveal. */
    private final Set<DDiagramElement> diagramElements;

    /**
     * Create a new {@link RevealDDiagramElements}.
     * 
     * @param domain
     *            the editing domain.
     * @param diagramElements
     *            the diagram element.
     */
    public RevealDDiagramElements(final TransactionalEditingDomain domain, final Set<DDiagramElement> diagramElements) {
        super(domain, RevealDDiagramElements.getLabel(diagramElements));
        this.diagramElements = diagramElements;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        for (final DDiagramElement diagramElement : diagramElements) {
            HideFilterHelper.INSTANCE.reveal(diagramElement);
        }
    }

    /**
     * Compute label.
     * 
     * @param diagramElements
     *            Elements.
     * @return Label.
     */
    public static String getLabel(final Set<DDiagramElement> diagramElements) {
        String result;
        if (diagramElements != null && diagramElements.size() > 1) {
            result = RevealDDiagramElements.REVEAL_ELEMENTS_LABEL;
        } else {
            result = RevealDDiagramElements.REVEAL_ELEMENT_LABEL;
        }
        return result;
    }
}
