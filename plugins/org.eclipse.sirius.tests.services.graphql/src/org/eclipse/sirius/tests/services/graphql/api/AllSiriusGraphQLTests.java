/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.services.graphql.api;

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
@SuiteClasses({ SiriusGraphQLSchemaTests.class })
public class AllSiriusGraphQLTests {
    // Do nothing
}
