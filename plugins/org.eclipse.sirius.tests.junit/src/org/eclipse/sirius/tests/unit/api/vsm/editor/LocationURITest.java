/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.vsm.editor;

import java.net.URISyntaxException;
import java.text.MessageFormat;

import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.menu.LocationURI;

import junit.framework.TestCase;

/**
 * Check the Location URI parsing, independently of the existence of the id part (menu or tabbar).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class LocationURITest extends TestCase {

    public void testMenuLocationURI() {
        testValidLocationURI("menu:myId");
    }

    public void testMenuLocationURIWithSpace() {
        testValidLocationURI("menu : myId");
    }

    public void testMenuLocationURIWithDefaultId() {
        testValidLocationURI("menu");
    }

    public void testTabbarLocationURI() {
        testValidLocationURI("tabbar:myId");
    }

    public void testTabbarLocationURIWithSpace() {
        testValidLocationURI("tabbar : myId");
    }

    public void testMenuAndTabbarLocationURI() {
        testValidLocationURI("menu:myId|tabbar:myIdBis");
    }

    public void testMenuAndTabbarLocationURIWithDefaultIdForMenu() {
        testValidLocationURI("menu|tabbar:myId");
    }

    public void testWrongEmptyLocationURI() {
        String uri = "";
        testWrongLocationURI(uri, MessageFormat.format(Messages.LocationURI_ParsePb_Blank, LocationURI.MENU_SCHEME, LocationURI.TABBAR_SCHEME));
    }

    public void testWrongLocationURIWith3URIParts() {
        String uri = "a|b|c";
        testWrongLocationURI(uri, MessageFormat.format(Messages.LocationURI_ParsePb_MoreThanTwoLocations, LocationURI.LOCATION_SEPARATOR, 3));
    }

    public void testWrongLocationURIWith2MenuURIParts() {
        String uri = "menu:myId|menu:myIdBis";
        testWrongLocationURI(uri, MessageFormat.format(Messages.LocationURI_ParsePb_OnlyOneLocationURIPerScheme, LocationURI.MENU_SCHEME));
    }

    public void testWrongLocationURIWith2TabbarURIParts() {
        String uri = "tabbar:myId|tabbar:myIdBis";
        testWrongLocationURI(uri, MessageFormat.format(Messages.LocationURI_ParsePb_OnlyOneLocationURIPerScheme, LocationURI.TABBAR_SCHEME));
    }

    public void testWrongMenuLocationURIWithoutId() {
        String uri = "menu:   |tabbar:myIdBis";
        testWrongLocationURI(uri, MessageFormat.format(Messages.LocationURI_ParsePb_NoId, LocationURI.MENU_SCHEME + LocationURI.SEPARATOR));
    }

    public void testWrongTabbarLocationURIWithoutId() {
        String uri = "menu:myId|tabbar:";
        testWrongLocationURI(uri, MessageFormat.format(Messages.LocationURI_ParsePb_NoId, LocationURI.TABBAR_SCHEME + LocationURI.SEPARATOR));
    }

    public void testWrongShemeLocationURI() {
        String uri = "wrongSheme:myId";
        testWrongLocationURI(uri, MessageFormat.format(Messages.LocationURI_ParsePb_WrongScheme, LocationURI.MENU_SCHEME, LocationURI.TABBAR_SCHEME));
    }

    public void testWrongMenuSchemeLocationURI() {
        String uri = "menuAAAA:myId";
        testWrongLocationURI(uri, MessageFormat.format(Messages.LocationURI_ParsePb_WrongFormat, LocationURI.MENU_SCHEME + LocationURI.SEPARATOR + "menuId", uri));
    }

    public void testWrongTabbarShemeLocationURI() {
        String uri = "tabbarAAAA:myId";
        testWrongLocationURI(uri, MessageFormat.format(Messages.LocationURI_ParsePb_WrongFormat, LocationURI.TABBAR_SCHEME + LocationURI.SEPARATOR + "tabbarId", uri));
    }

    private void testWrongLocationURI(String wrongLocationURI, String expectedMessage) {
        try {
            new LocationURI(wrongLocationURI);
            fail("The instantiation of location URI \"" + wrongLocationURI + "\" should failed.");
        } catch (URISyntaxException e) {
            // Expected
            assertEquals("The parsing fails as expected but the exception message is not the execpted.", expectedMessage + ": " + wrongLocationURI, e.getMessage());
        }
    }

    private void testValidLocationURI(String validLocationURI) {
        try {
            new LocationURI(validLocationURI);
        } catch (URISyntaxException e) {
            fail("The instantiation of location URI \"" + validLocationURI + "\" should not failed:" + e.getMessage());
        }
    }

}
