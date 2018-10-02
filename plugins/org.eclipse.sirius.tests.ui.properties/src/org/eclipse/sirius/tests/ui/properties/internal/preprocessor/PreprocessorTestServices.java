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
package org.eclipse.sirius.tests.ui.properties.internal.preprocessor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Utility services for tests.
 *
 * @author sbegaudeau
 */
public class PreprocessorTestServices {
	/**
	 * Sets the value of the given structural feature for the given EObject.
	 * 
	 * @param eObject
	 *            The EObject
	 * @param eStructuralFeature
	 *            The structural feature
	 * @param newValue
	 *            The new value
	 */
	public void eSet(EObject eObject, EStructuralFeature eStructuralFeature, Object newValue) {
		eObject.eSet(eStructuralFeature, newValue);
	}
}
