/*******************************************************************************
* Copyright (c) 2018, 2023 Obeo.
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
package org.eclipse.sirius.tests.sample.xtext.statemachine.ui.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.sirius.tests.sample.xtext.StatemachineRuntimeModule;
import org.eclipse.sirius.tests.sample.xtext.ui.StatemachineUiModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.util.Modules2;
import org.osgi.framework.BundleContext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class StatemachineActivator extends AbstractUIPlugin {

	public static final String ORG_ECLIPSE_SIRIUS_TESTS_SAMPLE_XTEXT_STATEMACHINE = "org.eclipse.sirius.tests.sample.xtext.Statemachine";
	
	private static StatemachineActivator INSTANCE;
	
	private Map<String, Injector> injectors = Collections.synchronizedMap(new HashMap<>());
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		injectors.clear();
		INSTANCE = null;
		super.stop(context);
	}
	
	public static StatemachineActivator getInstance() {
		return INSTANCE;
	}
	
	public Injector getInjector(String language) {
		synchronized (injectors) {
			Injector injector = injectors.get(language);
			if (injector == null) {
				injectors.put(language, injector = createInjector(language));
			}
			return injector;
		}
	}
	
	protected Injector createInjector(String language) {
		try {
			Module runtimeModule = getRuntimeModule(language);
			Module sharedStateModule = getSharedStateModule();
			Module uiModule = getUiModule(language);
			Module mergedModule = Modules2.mixin(runtimeModule, sharedStateModule, uiModule);
			return Guice.createInjector(mergedModule);
		} catch (Exception e) {
			getLog().error("Failed to create injector for " + language);
			getLog().error(e.getMessage(), e);
			throw new RuntimeException("Failed to create injector for " + language, e);
		}
	}
	
	protected Module getRuntimeModule(String grammar) {
		if (ORG_ECLIPSE_SIRIUS_TESTS_SAMPLE_XTEXT_STATEMACHINE.equals(grammar)) {
			return new StatemachineRuntimeModule();
		}
		throw new IllegalArgumentException(grammar);
	}
	
	protected Module getUiModule(String grammar) {
		if (ORG_ECLIPSE_SIRIUS_TESTS_SAMPLE_XTEXT_STATEMACHINE.equals(grammar)) {
			return new StatemachineUiModule(this);
		}
		throw new IllegalArgumentException(grammar);
	}
	
	protected Module getSharedStateModule() {
		return new SharedStateModule();
	}
	
}
