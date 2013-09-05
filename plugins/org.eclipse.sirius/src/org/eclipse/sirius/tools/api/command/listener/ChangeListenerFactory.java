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
package org.eclipse.sirius.tools.api.command.listener;

import org.eclipse.sirius.common.tools.api.util.SmartAdapter;
import org.eclipse.sirius.tools.internal.command.listener.ChangeListener;

/**
 * A factory that create new listener which record Created, Updated and Deleted
 * elements and which is able to launch a trigger operation with these elements
 * as parameters.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class ChangeListenerFactory {
    /**
     * The singleton instance of the factory.
     */
    public static final ChangeListenerFactory INSTANCE = new ChangeListenerFactory();

    /**
     * Default constructor to avoid instantiation .
     */
    protected ChangeListenerFactory() {
    }

    /**
     * Create a new listener which record Created, Updated and Deleted elements
     * and which is able to launch a trigger operation with these elements as
     * parameters.
     * 
     * @return a new change listener.
     */
    public IChangeListener getNewChangeListener() {
        return (IChangeListener) new SmartAdapter.Factory().newInstance(ChangeListener.class);
    }
}
