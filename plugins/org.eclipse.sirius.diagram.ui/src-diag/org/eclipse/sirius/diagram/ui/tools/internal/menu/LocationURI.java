/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Optional;

import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * Class inspired from {@link java.net.URI} but simpler. It is used to define the location of an element.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class LocationURI {
    /**
     * The scheme to use to locate the element in the contextual menu.
     */
    public static final String MENU_SCHEME = "menu"; //$NON-NLS-1$

    /**
     * The scheme to use to locate the element in the tabbar.
     */
    public static final String TABBAR_SCHEME = "tabbar"; //$NON-NLS-1$

    /**
     * The separator used to define 2 locations in one LocationURI (one for menu scheme and one for tabbar scheme).
     */
    public static final String LOCATION_SEPARATOR = "|"; //$NON-NLS-1$

    /**
     * The separator after the scheme and before the id of the menu.
     */
    public static final String SEPARATOR = ":"; //$NON-NLS-1$

    /**
     * Id used to represent the global contextual menu.
     */
    public static final String ROOT_MENU_ID = "root"; //$NON-NLS-1$

    /**
     * The string form of this URI.
     */
    private String string;

    /**
     * The menuId, if any, extracted from the string.
     */
    private String menuId;

    /**
     * The tabbarId, if any, extracted from the string.
     */
    private String tabbarId;

    /**
     * Constructs an URI by parsing the given string.
     * 
     * @param locationURI
     *            The string to be parsed into a URI
     * @throws URISyntaxException
     *             In case of a wrong syntax of the locationURI
     */
    public LocationURI(String locationURI) throws URISyntaxException {
        this.string = locationURI;
        parse();
    }

    /**
     * Return the menu id of this {@link LocationURI} if this {@link LocationURI} concerns the menu.
     * 
     * @return an optional menu id
     */
    public Optional<String> getMenuId() {
        return Optional.ofNullable(menuId);
    }

    /**
     * Return the tabbar id of this {@link LocationURI} if this {@link LocationURI} concerns the tabbar.
     * 
     * @return an optional tabbar id
     */

    public Optional<String> getTabbarId() {
        return Optional.ofNullable(tabbarId);
    }

    private void parse() throws URISyntaxException {
        if (string.trim().length() == 0) {
            throw new URISyntaxException(string, MessageFormat.format(Messages.LocationURI_ParsePb_Blank, MENU_SCHEME, TABBAR_SCHEME));
        }
        String[] locationsURI = string.split("\\" + LOCATION_SEPARATOR); //$NON-NLS-1$
        if (locationsURI.length > 2) {
            throw new URISyntaxException(string, MessageFormat.format(Messages.LocationURI_ParsePb_MoreThanTwoLocations, LOCATION_SEPARATOR, locationsURI.length));
        }
        for (int i = 0; i < locationsURI.length; i++) {
            String trimmedURI = locationsURI[i].trim();
            if (trimmedURI.startsWith(MENU_SCHEME)) {
                parseMenuURI(trimmedURI);
            } else if (trimmedURI.startsWith(TABBAR_SCHEME)) {
                parseTabbarURI(trimmedURI);
            } else {
                throw new URISyntaxException(string, MessageFormat.format(Messages.LocationURI_ParsePb_WrongScheme, MENU_SCHEME, TABBAR_SCHEME));
            }
        }
    }

    private void parseMenuURI(String menuURI) throws URISyntaxException {
        if (menuId != null) {
            throw new URISyntaxException(string, MessageFormat.format(Messages.LocationURI_ParsePb_OnlyOneLocationURIPerScheme, MENU_SCHEME));
        }
        if (menuURI.substring(MENU_SCHEME.length()).trim().startsWith(SEPARATOR)) {
            String id = menuURI.substring(MENU_SCHEME.length()).trim().substring(SEPARATOR.length()).trim();
            if (id.length() == 0) {
                throw new URISyntaxException(string, MessageFormat.format(Messages.LocationURI_ParsePb_NoId, MENU_SCHEME + SEPARATOR));
            }
            menuId = id;
        } else if (menuURI.length() == MENU_SCHEME.length()) {
            menuId = ROOT_MENU_ID;
        } else {
            throw new URISyntaxException(string, MessageFormat.format(Messages.LocationURI_ParsePb_WrongFormat, MENU_SCHEME + SEPARATOR + "menuId", menuURI)); //$NON-NLS-1$
        }
    }

    private void parseTabbarURI(String tabbarURI) throws URISyntaxException {
        if (tabbarId != null) {
            throw new URISyntaxException(string, MessageFormat.format(Messages.LocationURI_ParsePb_OnlyOneLocationURIPerScheme, TABBAR_SCHEME));
        }
        if (tabbarURI.substring(TABBAR_SCHEME.length()).trim().startsWith(SEPARATOR)) {
            String id = tabbarURI.substring(TABBAR_SCHEME.length()).trim().substring(SEPARATOR.length()).trim();
            if (id.length() == 0) {
                throw new URISyntaxException(string, MessageFormat.format(Messages.LocationURI_ParsePb_NoId, TABBAR_SCHEME + SEPARATOR));
            }
            tabbarId = id;
        } else {
            throw new URISyntaxException(string, MessageFormat.format(Messages.LocationURI_ParsePb_WrongFormat, TABBAR_SCHEME + SEPARATOR + "tabbarId", tabbarURI)); //$NON-NLS-1$
        }
    }
}
