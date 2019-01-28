/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo.
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
package org.eclipse.sirius.tests.services.graphql.api;

import org.eclipse.sirius.tests.services.graphql.internal.SiriusGraphQLEMFEPackagesTests;
import org.eclipse.sirius.tests.services.graphql.internal.SiriusGraphQLProjectTests;
import org.eclipse.sirius.tests.services.graphql.internal.SiriusGraphQLSchemaTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The main tests suite of the GraphQL tests.
 * 
 * @author sbegaudeau
 */
@RunWith(Suite.class)
@SuiteClasses({ SiriusGraphQLSchemaTests.class, SiriusGraphQLProjectTests.class, SiriusGraphQLEMFEPackagesTests.class })
public class AllSiriusGraphQLTests {
    // Do nothing
}
