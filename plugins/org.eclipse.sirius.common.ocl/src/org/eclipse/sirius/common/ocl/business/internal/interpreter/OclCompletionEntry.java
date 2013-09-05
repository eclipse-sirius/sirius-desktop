/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ocl.business.internal.interpreter;

import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;

/**
 * This utility class evaluates the content proposals of Assist contents for
 * Acceleo expressions.
 * 
 * @author ggebhart
 */
public final class OclCompletionEntry {

    /**
     * Avoid instantiation.
     */
    private OclCompletionEntry() {
    }

    /**
     * Evaluates the content proposals for a given expression and returns the
     * result as an List.
     * 
     * @param context
     *            the context
     * @return the content proposals.
     */
    public static List<ContentProposal> computeCompletionEntry(final ContentContext context) {
        final List<ContentProposal> result = Collections.emptyList();
        return result;
    }

}
