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
package org.eclipse.sirius.tests.sample.xtext.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

public abstract class AbstractStatemachineValidator extends AbstractDeclarativeValidator {
	
	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> result = new ArrayList<EPackage>();
		result.add(org.eclipse.sirius.tests.sample.xtext.statemachine.StatemachinePackage.eINSTANCE);
		return result;
	}
	
}
