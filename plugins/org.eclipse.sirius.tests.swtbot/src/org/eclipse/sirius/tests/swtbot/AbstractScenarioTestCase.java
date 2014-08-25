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
package org.eclipse.sirius.tests.swtbot;

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
 * @author cbrun
 */
public abstract class AbstractScenarioTestCase extends AbstractSiriusSwtBotGefTestCase {
    /**
     * Name of the first created EClass with Class tool.
     */
    protected static final String NEW_ECLASS_1 = "NewEClass21";

    /**
     * Name of the second created EClass with Class tool.
     */
    protected static final String NEW_ECLASS_2 = "NewEClass22";

    /**
     * Models forlder.
     */
    protected static final String MODELS_DIR = "Models";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {

        final Resource ecoreRes = new ResourceSetImpl().createResource(URI.createPlatformResourceURI("/" + getProjectName() + "/" + MODELS_DIR + "/" + "Ecore.ecore", true));
        ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
        ecoreRes.save(Collections.EMPTY_MAP);
    }
}
