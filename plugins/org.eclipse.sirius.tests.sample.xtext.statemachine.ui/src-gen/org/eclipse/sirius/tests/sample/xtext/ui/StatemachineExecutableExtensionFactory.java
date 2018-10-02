/*******************************************************************************
* Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.tests.sample.xtext.ui;

import com.google.inject.Injector;
import org.eclipse.sirius.tests.sample.xtext.statemachine.ui.internal.StatemachineActivator;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class StatemachineExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return StatemachineActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return StatemachineActivator.getInstance().getInjector(StatemachineActivator.ORG_ECLIPSE_SIRIUS_TESTS_SAMPLE_XTEXT_STATEMACHINE);
	}
	
}
