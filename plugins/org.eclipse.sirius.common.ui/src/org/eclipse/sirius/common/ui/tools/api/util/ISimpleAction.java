/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.util;

import java.util.Map;

/**
 * Represents a simple action.
 * 
 * @author ymortier
 */
public interface ISimpleAction {

    /**
     * Return the hint data of the action.
     * 
     * @return the hint data of the action.
     */
    Map<?, ?> getHintData();

    /**
     * Execute the action.
     */
    void executeAction();
}
