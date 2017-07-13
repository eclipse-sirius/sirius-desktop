/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
