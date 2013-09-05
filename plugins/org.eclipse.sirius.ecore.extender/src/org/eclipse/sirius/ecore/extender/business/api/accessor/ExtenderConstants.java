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
package org.eclipse.sirius.ecore.extender.business.api.accessor;

/**
 * This interface is used to keep track of the different constants available in
 * the extender plugin.
 * 
 * @author cbrun
 * 
 */
public interface ExtenderConstants {
    /**
     * The lowest priority.
     */
    int LOWEST_PRIORITY = 20;

    /**
     * A low priority.
     */
    int LOW_PRIORITY = 10;

    /**
     * A normal priority (the <code>EcoreIntrinsicExtender</code> use this one.
     */
    int NORMAL_PRIORITY = 5;

    /**
     * A high priority.
     */
    int HIGH_PRIORITY = 1;

    /**
     * The highest priority.
     */
    int HIGHEST_PRIORITY = 0;
}
