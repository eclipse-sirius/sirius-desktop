/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 *
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
package org.eclipse.sirius.editor.tools.internal.commands;

import org.eclipse.core.expressions.PropertyTester;

/**
 * PropertyTester class for the reloadVSM command.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class ReloadVSMPropertyTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        // This handler is enabled only if the eclipse is a runtime application launched with pde (from a launch config
        // for example)
        return Boolean.getBoolean("eclipse.pde.launch"); //$NON-NLS-1$
    }

}
