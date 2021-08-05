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

import java.util.Iterator;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.tools.internal.Messages;

import com.google.common.collect.Lists;

/**
 * Command that is able to reveal all elements of a viewpoint.
 * 
 * @author cbrun
 */
public class RevealAllElementsCommand extends RecordingCommand {

    /**
     * Label for hide element.
     * 
     * @since 0.9.0
     */
    public static final String REVEAL_ALL_ELEMENTS_LABEL = Messages.RevealAllElementsCommand_revealAllElementsLabel;

    /** The viewpoint. */
    private final DDiagram viewpoint;

    /**
     * Create a new {@link RevealAllElementsCommand}.
     * 
     * @param domain
     *            the editing domain.
     * @param vp
     *            the viewpoint.
     */
    public RevealAllElementsCommand(final TransactionalEditingDomain domain, final DDiagram vp) {
        super(domain, REVEAL_ALL_ELEMENTS_LABEL);
        this.viewpoint = vp;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        // Required to avoid ConcurrentModificationException in CDONative mode
        final Iterator<EObject> it = new NonConcurrentEAllContentIterator(viewpoint);
        while (it.hasNext()) {
            final EObject eObj = it.next();
            if (eObj instanceof DDiagramElement) {
                HideFilterHelper.INSTANCE.reveal((DDiagramElement) eObj);
            }
        }
    }

    /**
     * An iterator similar the one returned by a call to eAllContents(), but
     * using a copy of the list to avoid
     * {@link java.util.ConcurrentModificationException}s.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     * 
     */
    private static final class NonConcurrentEAllContentIterator extends AbstractTreeIterator<EObject> {

        private static final long serialVersionUID = 1L;

        NonConcurrentEAllContentIterator(EObject object) {
            super(object, false);
        }

        @Override
        protected Iterator<EObject> getChildren(Object object) {
            return Lists.newArrayList(((EObject) object).eContents()).iterator();
        }
    }

}
