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
package org.eclipse.sirius.tests.unit.api.vsm.interpreted.expression.variables;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.tests.support.api.AbstractInterpretedExpressionTestCase;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Test documentation of interpreted expressions.
 * 
 * @author mporhel
 */
public class SiriusVariablesTest extends AbstractInterpretedExpressionTestCase {

	@Override
	protected void setUp() throws Exception {
		setBasePackage(ViewpointPackage.eINSTANCE);
		super.setUp();
	}

	@Override
	protected EPackage getDialectPackage() {
		// Required until Bug 450473 correction: viewpoint.ecore contains
		// variables typed with diagram.Diagram
		return DiagramPackage.eINSTANCE;
	}
}
