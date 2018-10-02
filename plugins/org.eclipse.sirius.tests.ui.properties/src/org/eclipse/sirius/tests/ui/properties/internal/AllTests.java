/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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
package org.eclipse.sirius.tests.ui.properties.internal;

import org.eclipse.sirius.tests.ui.properties.internal.converters.ConverterTests;
import org.eclipse.sirius.tests.ui.properties.internal.migration.CategoryMigrationTests;
import org.eclipse.sirius.tests.ui.properties.internal.migration.ReferenceMigrationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This class will be used to run the unit tests of the plugin.
 * 
 * @author sbegaudeau
 */
@RunWith(Suite.class)
@SuiteClasses({ ConverterTests.class, ReferenceMigrationTests.class, CategoryMigrationTests.class })
public final class AllTests {
    /**
     * The constructor.
     */
    private AllTests() {
        // Prevent instantiation
    }
}
