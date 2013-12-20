/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.util;

import java.util.Iterator;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;

import com.google.common.collect.Iterators;

/**
 * A tree iterator to iterate on hierarchies of ISequenceEvent.
 * 
 * @author pcdavid
 */
public class ISequenceEventsTreeIterator extends AbstractTreeIterator<ISequenceEvent> {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 3234398995856675133L;

    /**
     * Creates a new tree iterator on the specified event and all its descendant
     * events.
     * 
     * @param root
     *            the root event of the iteration.
     */
    public ISequenceEventsTreeIterator(ISequenceEvent root) {
        super(root);
    }

    /**
     * Creates a new tree iterator on the descendants of the specified event.
     * 
     * @param root
     *            the root event of the iteration.
     * @param includeRoot
     *            if <code>true</code>, the iterator includes the root element.
     */
    public ISequenceEventsTreeIterator(ISequenceEvent root, boolean includeRoot) {
        super(root, includeRoot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Iterator<? extends ISequenceEvent> getChildren(Object object) {
        if (object instanceof ISequenceEvent) {
            Iterable<ISequenceEvent> children = ((ISequenceEvent) object).getSubEvents();
            return children.iterator();
        } else {
            return Iterators.emptyIterator();
        }
    }

}
