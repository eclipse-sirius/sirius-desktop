/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common;

import org.eclipse.core.expressions.PropertyTester;

/**
 * Property tester to avoid CoreException while
 * org.eclipse.debug.ui.actions.LaunchShortcutsAction (menu Run > External tools
 * > Run as > ) computes the available sub-menus for ant when a
 * {@link org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem} or an
 * {@link org.eclipse.emf.ecore.EObject} is selected.
 * 
 * The concerned properties are "matchesContentType" and "matchesPattern" but we just want to avoid the
 * exception, not to make some tool available so we just have to return false in
 * the test method.
 * 
 * @author mporhel
 * 
 */
public class ResourceExtender extends PropertyTester {

    /**
     * {@inheritDoc}
     */
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        return false;
    }
}
