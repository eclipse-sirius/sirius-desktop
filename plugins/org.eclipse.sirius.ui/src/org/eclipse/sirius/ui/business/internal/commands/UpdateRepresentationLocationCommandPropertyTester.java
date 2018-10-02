/*******************************************************************************
 * Copyright (c) 2017 Obeo
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
package org.eclipse.sirius.ui.business.internal.commands;

import java.util.Collections;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;

/**
 * A property tester to check enablement of the Update Representation Location command.
 * 
 * @author fbarbin
 *
 */
public class UpdateRepresentationLocationCommandPropertyTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (Boolean.getBoolean("createLocalRepresentationInSeparateResource")) { //$NON-NLS-1$
            return receiver instanceof IResource && !UpdateRepresentationsLocationHandler.getAirdFiles(Collections.singletonList(receiver)).isEmpty();
        } else {
            return false;
        }
    }
}
