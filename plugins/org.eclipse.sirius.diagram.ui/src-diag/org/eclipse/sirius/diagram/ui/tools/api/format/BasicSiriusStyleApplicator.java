/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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

package org.eclipse.sirius.diagram.ui.tools.api.format;

/**
 * This class use the default implementation of the interface and no more, so there is no code in this class.
 * 
 * See {@link SiriusStyleApplicator} for more information about the default implementation.
 * 
 * @see SiriusStyleApplicator
 * 
 * @author Séraphin Costa
 *
 */
public final class BasicSiriusStyleApplicator implements SiriusStyleApplicator {
    /** The singleton instance. */
    public static final BasicSiriusStyleApplicator INSTANCE = new BasicSiriusStyleApplicator();

    private BasicSiriusStyleApplicator() {
    }
}
