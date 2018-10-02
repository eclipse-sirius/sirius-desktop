/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.test;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;

/**
 * Class to handle things on ecore model just before closing welcome page.
 * 
 * @author amartin
 */
public abstract class AbstractMMEcoreBasedScenarioTestCase extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Models forlder.
     */
    protected static final String MODELS_DIR = "Models";

    private static final String SEPARATOR = "/";

    /**
     * Set up.
     * 
     * @throws Exception
     *             the exception when error.
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {

        final Resource ecoreRes = new ResourceSetImpl().createResource(URI.createPlatformResourceURI(AbstractMMEcoreBasedScenarioTestCase.SEPARATOR + getProjectName()
                + AbstractMMEcoreBasedScenarioTestCase.SEPARATOR + AbstractMMEcoreBasedScenarioTestCase.MODELS_DIR + AbstractMMEcoreBasedScenarioTestCase.SEPARATOR + "Ecore.ecore", true));
        ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
        ecoreRes.save(Collections.EMPTY_MAP);
    }
}
