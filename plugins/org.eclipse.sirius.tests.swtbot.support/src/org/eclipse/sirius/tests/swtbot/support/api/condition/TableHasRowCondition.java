/**
 * Copyright (c) 2013, 2018 THALES GLOBAL SERVICES
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

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

/**
 * A {@link DefaultCondition} to test that a {@link SWTBotTable} has rows.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TableHasRowCondition extends DefaultCondition {

    private final SWTBotTable swtBotTable;

    private int expectedRowsCount;

    /**
     * Default constructor.
     * 
     * @param swtBotTable
     *            the {@link SWTBotTable} to test
     */
    public TableHasRowCondition(SWTBotTable swtBotTable) {
        this.swtBotTable = swtBotTable;
        this.expectedRowsCount = -1;
    }

    /**
     * Default constructor.
     * 
     * @param swtBotTable
     *            the {@link SWTBotTable} to test
     * @param expectedRowsCount
     *            the expected rows count.
     */
    public TableHasRowCondition(SWTBotTable swtBotTable, int expectedRowsCount) {
        this.swtBotTable = swtBotTable;
        this.expectedRowsCount = expectedRowsCount;
    }

    @Override
    public boolean test() throws Exception {
        return expectedRowsCount == -1 ? swtBotTable.rowCount() > 0 : swtBotTable.rowCount() == expectedRowsCount;
    }

    @Override
    public String getFailureMessage() {
        if (expectedRowsCount == -1) {
            return "There is no row on the table";
        } else {
            return "Wrong number of rows in table, expected " + expectedRowsCount + " but was " + swtBotTable.rowCount();
        }
    }
}
