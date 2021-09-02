/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
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

import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.tools.api.Messages;

/**
 * Command that is able to hide elements.
 * 
 * @author cbrun
 */
public class HideDDiagramElement extends RecordingCommand {

    /**
     * Label for hide element.
     * 
     * @since 0.9.0
     */
    public static final String HIDE_ELEMENT_LABEL = Messages.HideDDiagramElement_hideElementLabel;

    /**
     * Label for hide many elements.
     * 
     * @since 0.9.0
     */
    public static final String HIDE_ELEMENTS_LABEL = Messages.HideDDiagramElement_hideElementsLabel;

    /** The objects to hide. */
    private final Set<?> objectsToHide;

    /**
     * Create a new {@link HideDDiagramElement}.
     * 
     * @param domain
     *            the editing domain.
     * @param elementsToHide
     *            the elements to hide.
     */
    public HideDDiagramElement(final TransactionalEditingDomain domain, final Set<?> elementsToHide) {
        super(domain);
        if (elementsToHide != null && elementsToHide.size() > 1) {
            setLabel(HideDDiagramElement.HIDE_ELEMENTS_LABEL);
        } else {
            setLabel(HideDDiagramElement.HIDE_ELEMENT_LABEL);
        }
        this.objectsToHide = elementsToHide;
    }

    /**
     * Sets the element invisible.
     * 
     * @param element
     *            the element.
     */
    protected void setInvisible(final DDiagramElement element) {
        if (!new DDiagramElementQuery(element).isHidden()) {
            HideFilterHelper.INSTANCE.hide(element);
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
