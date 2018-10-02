/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.helper.display;

/**
 * Display mode to use with {@link DisplayServiceManager}.
 * 
 * @author mchauvin
 */
public enum DisplayMode {

    /**
     * all elements are displayed.
     */
    ALL_IS_DISPLAYED,
    /**
     * Only the direct parent and connected elements are taken into account to
     * compute visibility.
     * 
     * @since 0.9.0
     */
    CREATION,
    /**
     * Filters, layers, and visibility are taken in account to check the
     * visibility.
     */
    NORMAL

}
