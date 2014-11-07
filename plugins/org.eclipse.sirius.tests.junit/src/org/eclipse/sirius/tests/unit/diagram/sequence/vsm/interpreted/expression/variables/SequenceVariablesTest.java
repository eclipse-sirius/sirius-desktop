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
package org.eclipse.sirius.tests.unit.diagram.sequence.vsm.interpreted.expression.variables;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.sequence.SequencePackage;
import org.eclipse.sirius.tests.support.api.AbstractInterpretedExpressionTestCase;

/**
 * Test documentation of interpreted expressions.
 * 
 * @author mporhel
 */
public class SequenceVariablesTest extends
		AbstractInterpretedExpressionTestCase {

	@Override
	protected void setUp() throws Exception {
		setBasePackage(SequencePackage.eINSTANCE);
		super.setUp();
	}

	@Override
	protected EPackage getDialectPackage() {
		return DiagramPackage.eINSTANCE;
	}
}
