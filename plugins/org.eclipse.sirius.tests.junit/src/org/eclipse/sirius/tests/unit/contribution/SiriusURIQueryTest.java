/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.contribution;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.query.ViewpointURIQuery;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link ViewpointURIQuery}.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class SiriusURIQueryTest {

    @Test
    public void null_uri_is_invalid() {
        Assert.assertFalse(ViewpointURIQuery.isValidViewpointURI(null));
    }

    @Test
    public void blank_uri_is_invalid() {
        Assert.assertFalse(ViewpointURIQuery.isValidViewpointURI(URI.createURI("")));
        Assert.assertFalse(ViewpointURIQuery.isValidViewpointURI(URI.createURI("   ")));
    }

    @Test
    public void http_uri_is_invalid() {
        Assert.assertFalse(ViewpointURIQuery.isValidViewpointURI(URI.createURI("htttp://www.eclipse.org/")));
        Assert.assertFalse(ViewpointURIQuery.isValidViewpointURI(URI.createURI("htttp://www.eclipse.org/sirius")));
    }

    @Test
    public void basic_viewpoint_query_is_valid() {
        URI uri = URI.createURI("viewpoint:/myPlugin/MySirius");
        Assert.assertTrue(ViewpointURIQuery.isValidViewpointURI(uri));
        ViewpointURIQuery q = new ViewpointURIQuery(uri);
        Assert.assertNotNull(q);
        Assert.assertEquals("myPlugin", q.getPluginId());
        Assert.assertEquals("MySirius", q.getViewpointName());
    }

    @Test
    public void plugin_id_can_have_dots() {
        URI uri = URI.createURI("viewpoint:/org.eclipse.sirius.tests.something/MySirius");
        Assert.assertTrue(ViewpointURIQuery.isValidViewpointURI(uri));
        ViewpointURIQuery q = new ViewpointURIQuery(uri);
        Assert.assertNotNull(q);
        Assert.assertEquals("org.eclipse.sirius.tests.something", q.getPluginId());
        Assert.assertEquals("MySirius", q.getViewpointName());
    }

    @Test
    public void viewpoint_name_can_have_spaces() {
        URI uri = URI.createURI("viewpoint:/org.eclipse.sirius.tests.something/My Viewpoint");
        Assert.assertTrue(ViewpointURIQuery.isValidViewpointURI(uri));
        ViewpointURIQuery q = new ViewpointURIQuery(uri);
        Assert.assertNotNull(q);
        Assert.assertEquals("org.eclipse.sirius.tests.something", q.getPluginId());
        Assert.assertEquals("My Viewpoint", q.getViewpointName());
    }

}
