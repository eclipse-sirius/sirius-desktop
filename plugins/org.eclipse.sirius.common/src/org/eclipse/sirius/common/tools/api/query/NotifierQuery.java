/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.query;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;

/**
 * Queries on EMF Notifier.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 *
 */
public class NotifierQuery {
    private final Notifier notifier;

    /**
     * Constructor.
     * 
     * @param notifier
     *            the notifier to query.
     */
    public NotifierQuery(Notifier notifier) {
        this.notifier = Preconditions.checkNotNull(notifier);
    }
    /**
     * Return an optional adapter, the first adapter of <code>notifier</code> of
     * kind <code>classKind</code>.
     *
     * @param classKind
     *            The kind of adapter searched
     * @return an optional adapter, the first adapter of <code>notifier</code>
     *         of kind <code>classKind</code>.
     */
    public Option<? extends Adapter> getAdapter(Class<?> classKind) {
        for (Adapter adapter : notifier.eAdapters()) {
            if (classKind.isInstance(adapter)) {
                return Options.newSome(adapter);
            }
        }
        return Options.newNone();
    }
}
