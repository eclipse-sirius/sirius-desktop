/*******************************************************************************
 * Copyright (c) 2011-2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.tools.api.query;

import java.util.Optional;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

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
    public Optional<? extends Adapter> getAdapter(Class<?> classKind) {
        for (Adapter adapter : notifier.eAdapters()) {
            if (classKind.isInstance(adapter)) {
                return Optional.of(adapter);
            }
        }
        return Optional.empty();
    }
}
