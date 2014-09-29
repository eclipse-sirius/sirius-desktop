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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Unit tests for the model contribution mechanism, used for reuse and
 * customization of VSM elements.
 * 
 * @author pierre-charles.david@obeo.fr
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ FeatureContributionTest.class, ReferenceResolverTest.class, ModelContributionTest.class, TableContributionTest.class, UpdaterTest.class, SiriusURIQueryTest.class, IncrementalModelContributionTest.class })
public class AllContributionTests {

}
