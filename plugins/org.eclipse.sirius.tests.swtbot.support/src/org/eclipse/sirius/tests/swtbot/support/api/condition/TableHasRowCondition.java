/**
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

    /**
     * Default constructor.
     * 
     * @param swtBotTable
     *            the {@link SWTBotTable} to test
     */
    public TableHasRowCondition(SWTBotTable swtBotTable) {
        this.swtBotTable = swtBotTable;
    }

    @Override
    public boolean test() throws Exception {
        return swtBotTable.rowCount() > 0;
    }

    @Override
    public String getFailureMessage() {
        return "There is no row on the table";
    }
}
