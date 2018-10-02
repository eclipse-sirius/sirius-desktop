/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl;

import org.eclipse.emf.ecore.EObject;

public class BasicService {
	/**
	 * A very basic service method, just to test A3 completion.
	 * 
	 * @param ctx the service's target.
	 * @return a fixed string.
	 */
	public String sampleService(EObject ctx) {
		return "sampleService";
	}
}
