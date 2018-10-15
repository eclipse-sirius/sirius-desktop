/**
 * Copyright (c) 2018 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

/**
 * A {@link DefaultCondition} to test that a {@link DTable} has a specific number of columns.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class TableHasColumnCondition extends DefaultCondition {

    private final DTable table;

    private int expectedColumnCount;

    /**
     * Default constructor.
     * 
     * @param table
     *            the {@link SWTBotTable} to test
     * @param expectedColumnCount
     *            the expected column count.
     */
    public TableHasColumnCondition(DTable table, int expectedColumnCount) {
        this.table = table;
        this.expectedColumnCount = expectedColumnCount;
    }

    @Override
    public boolean test() throws Exception {
        return table.getColumns().size() == expectedColumnCount;
    }

    @Override
    public String getFailureMessage() {
        return "Unexpected number of column in table";
    }
}
