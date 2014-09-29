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
