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
package org.eclipse.sirius.tests.unit.table.unit.dialect;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;

import junit.framework.TestCase;

/**
 * Test the good early registration of dialect manager
 * 
 * @author mchauvin
 */
public class DialectManagerTest extends TestCase {

    private ResourceSet set = new ResourceSetImpl();

    private Group dialectGroup;

    private Model umlModel;

    @Override
    protected void setUp() throws Exception {
        dialectGroup = (Group) ModelUtils.load(URI.createPlatformPluginURI("/org.eclipse.sirius.tests.junit/data/table/unit/dialect/dialect.odesign", true), set);
        umlModel = (Model) ModelUtils.load(URI.createPlatformPluginURI("/org.eclipse.sirius.tests.junit/data/table/unit/dialect/dialect.uml", true), set);
    }

    public void testPreconditionOnAvailableTableDescription() throws Exception {
        Viewpoint vp = findViewpoint(dialectGroup, "Dialect Testing Viewpoint");
        Collection<Viewpoint> currentVp = new ArrayList<Viewpoint>();
        currentVp.add(vp);
        org.eclipse.uml2.uml.Class class1 = (Class) umlModel.getPackagedElement("Class1");
        Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(currentVp, class1);
        assertEquals("we should have one possible representation", 1, descriptions.size());

        final TableDescription tableDescription = findTableDescription(dialectGroup, "Class Table");
        tableDescription.setPreconditionExpression("aql:false");
        descriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(currentVp, class1);

        assertEquals("we should have zero possible representation", 0, descriptions.size());
    }

    /**
     * Find a viewpoint by name.
     * 
     * @param group
     *            group.
     * @param name
     *            name to look for.
     * @return the viewpoint or null if not found.
     */
    private Viewpoint findViewpoint(Group group, String name) {
        for (Viewpoint vp : group.getOwnedViewpoints()) {
            if (vp.getName().equals(name))
                return vp;
        }
        return null;
    }

    /**
     * Find a table description by name.
     * 
     * @param group
     *            group.
     * @param name
     *            name to look for.
     * @return the diagram description or null if not found.
     */
    private TableDescription findTableDescription(Group group, String name) {

        for (Viewpoint vp : group.getOwnedViewpoints()) {
            for (RepresentationDescription representation : vp.getOwnedRepresentations()) {
                if (representation instanceof TableDescription && ((TableDescription) representation).getName().equals(name))
                    return (TableDescription) representation;
            }

        }
        return null;
    }

}
