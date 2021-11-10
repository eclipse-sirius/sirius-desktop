/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.tools.api.Messages;

/**
 * Command that is able to hide labels of elements.
 * 
 * @author lredor
 */
public class HideDDiagramElementLabel extends RecordingCommand {

    /**
     * Label for hide label.
     * 
     * @since 0.9.0
     */
    public static final String HIDE_LABEL = Messages.HideDDiagramElementLabel_hideLabel;

    /**
     * Label for hide many labels.
     * 
     * @since 0.9.0
     */
    public static final String HIDE_LABELS = Messages.HideDDiagramElementLabel_hideLabels;

    /** The objects to hide. */
    private final Set<?> objectsToHide;

    private final List<Integer> selectedLabelVisualIds;

    /**
     * Create a new {@link HideDDiagramElement}.
     * 
     * @param domain
     *            the editing domain.
     * @param elementsToHide
     *            the elements for which you want to hide the label.
     */
    public HideDDiagramElementLabel(final TransactionalEditingDomain domain, final Set<?> elementsToHide) {
        super(domain);
        if (elementsToHide != null && elementsToHide.size() > 1) {
            setLabel(HideDDiagramElementLabel.HIDE_LABELS);
        } else {
            setLabel(HideDDiagramElementLabel.HIDE_LABEL);
        }
        this.objectsToHide = elementsToHide;
        this.selectedLabelVisualIds = Collections.EMPTY_LIST;
    }

    /**
     * Create a new {@link HideDDiagramElement}.
     * 
     * @param domain
     *            the editing domain.
     * @param elementsToHide
     *            the elements for which you want to hide the label.
     */
    public HideDDiagramElementLabel(final TransactionalEditingDomain domain, final Set<?> elementsToHide, final List<Integer> selectedLabelVisualIds) {
        super(domain);
        if (elementsToHide != null && elementsToHide.size() > 1) {
            setLabel(HideDDiagramElementLabel.HIDE_LABELS);
        } else {
            setLabel(HideDDiagramElementLabel.HIDE_LABEL);
        }
        this.objectsToHide = elementsToHide;
        this.selectedLabelVisualIds = selectedLabelVisualIds;
    }

    /**
     * Sets the label of the element invisible if possible.
     * 
     * @param element
     *            the element.
     */
    protected void setInvisible(final DDiagramElement element) {
        DDiagramElementQuery diagramElementQuery = new DDiagramElementQuery(element);
        if (!diagramElementQuery.isLabelHidden() && diagramElementQuery.canHideLabel()) {
            HideFilterHelper.INSTANCE.hideLabel(element, selectedLabelVisualIds);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        final Iterator<?> it = objectsToHide.iterator();
        while (it.hasNext()) {
            final Object obj = it.next();
            if (obj instanceof DDiagramElement) {
                setInvisible((DDiagramElement) obj);
            }
        }
    }
}
