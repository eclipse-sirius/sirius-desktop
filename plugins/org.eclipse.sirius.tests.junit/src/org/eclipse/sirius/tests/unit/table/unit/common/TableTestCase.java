/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.table.unit.common;

import java.util.Iterator;

import org.eclipse.sirius.table.business.api.refresh.DTableSynchronizer;
import org.eclipse.sirius.table.business.internal.dialect.TableDialectServices;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.command.TableCommandFactoryService;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public abstract class TableTestCase extends SiriusTestCase {

    /** Incorrect data message. */
    public static final String THE_UNIT_TEST_DATA_SEEMS_INCORRECT = "The unit test data seems incorrect";

    public static final String PATH = "/data/table/unit/refresh/";

    private static final String SEMANTIC_MODEL_FILENAME = "tables.uml";

    private static final String MODELER_MODEL_FILENAME = "tables.odesign";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_MODEL_FILENAME;

    private ITableCommandFactory tableCommandFactory;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        init();
        tableCommandFactory = TableCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
    }

    protected void init() throws Exception {
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, MODELER_PATH);

        activateViewpoint("UML2 tables for tests");
    }

    /**
     * Creates a synchronizer.
     * 
     * @param descr description of table
     * @return synchronizer
     */
    protected DTableSynchronizer createTableSynchronizer(TableDescription descr) {
    	return new TableDialectServices().createTableSynchronizer(descr, accessor, interpreter);
    }
    
    /**
     * Return the searched tableDescription.
     * 
     * @param group
     *            A group
     * @param name
     *            The name of the searched table description
     * @return The searched TableDescription of null
     */
    protected TableDescription find(final String name) {
        for (Viewpoint firstSiriusFromRegistry : viewpoints) {
            Viewpoint viewpointFromLocalSession = getViewpointFromName(firstSiriusFromRegistry.getName(), session);
            if (viewpointFromLocalSession != null) {
                final Iterator<RepresentationDescription> it = viewpointFromLocalSession.getOwnedRepresentations().iterator();
                while (it.hasNext()) {
                    final RepresentationDescription next = it.next();
                    if (next instanceof TableDescription) {
                        if (name.equals(((TableDescription) next).getName()))
                            return (TableDescription) next;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Return the searched crossTableDescription.
     * 
     * @param name
     *            The name of the searched cross table description
     * @return The searched CrossTableDescription of null
     */
    protected CrossTableDescription findCrossTable(final String name) {
        Viewpoint firstSiriusFromRegistry = viewpoints.iterator().next();
        Viewpoint viewpointFromLocalSession = getViewpointFromName(firstSiriusFromRegistry.getName(), session);
        if (viewpointFromLocalSession != null) {
            final Iterator<RepresentationDescription> it = viewpointFromLocalSession.getOwnedRepresentations().iterator();
            while (it.hasNext()) {
                final RepresentationDescription next = it.next();
                if (next instanceof CrossTableDescription) {
                    if (name.equals(((CrossTableDescription) next).getName()))
                        return (CrossTableDescription) next;
                }
            }
        }
        return null;
    }

    /**
     * @return the tableCommandFactory
     */
    protected ITableCommandFactory getCommandFactory() {
        if (tableCommandFactory == null) {
            tableCommandFactory = TableCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        }
        return tableCommandFactory;
    }
}
