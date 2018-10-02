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
package org.eclipse.sirius.diagram.business.internal.helper.display;

import org.eclipse.sirius.diagram.business.api.helper.display.DisplayMode;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayService;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;

/**
 * Default implementation of {@link DisplayServiceManager}.
 * 
 * @author mchauvin
 */
public final class DisplayServiceManagerImpl implements DisplayServiceManager {

    private static DisplayService defaultService = DisplayServiceImpl.init();

    private static DisplayService displayAllService = DisplayAllDisplayServiceImpl.init();

    private static DisplayService displayCreationService = CreationDisplayServiceImpl.init();

    private DisplayService service = defaultService;

    private DisplayMode mode = DisplayMode.NORMAL;

    /**
     * Avoid instantiation.
     */
    private DisplayServiceManagerImpl() {

    }

    /**
     * Create a new instance.
     * 
     * @return a new created instance
     */
    public static DisplayServiceManager init() {
        return new DisplayServiceManagerImpl();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager#getDisplayService()
     */
    public DisplayService getDisplayService() {
        return service;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager#activateMode(org.eclipse.sirius.diagram.business.api.helper.display.DisplayMode)
     */
    public void activateMode(final DisplayMode modeToActivate) {
        this.mode = modeToActivate;
        switch (mode) {
        case ALL_IS_DISPLAYED:
            service = displayAllService;
            break;
        case CREATION:
            service = displayCreationService;
            break;
        case NORMAL:
            service = defaultService;
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager#getMode()
     */
    public DisplayMode getMode() {
        return mode;
    }

    /**
     * {@inheritDoc}
     */
    public DisplayService getDisplayService(final DisplayMode wantedMode) {
        DisplayService wantedService = null;
        switch (wantedMode) {
        case ALL_IS_DISPLAYED:
            wantedService = displayAllService;
            break;
        case CREATION:
            wantedService = displayCreationService;
            break;
        case NORMAL:
            wantedService = defaultService;
            break;
        default:
            break;
        }
        return wantedService;
    }
}
