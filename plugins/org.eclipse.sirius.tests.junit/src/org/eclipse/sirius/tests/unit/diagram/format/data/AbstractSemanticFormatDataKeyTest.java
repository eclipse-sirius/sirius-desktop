/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.format.data;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticNodeFormatDataKey;
import org.eclipse.sirius.tests.support.api.EqualsHashCodeTestCase;

/**
 * Comme behavior for *FormatDataKey tests.
 * 
 * @author dlecan
 */
public abstract class AbstractSemanticFormatDataKeyTest extends EqualsHashCodeTestCase {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object createInstance() throws Exception {
        final Resource resource = new ResourceImpl(URI.createURI("urn://truc"));
        final EClass createEClass = EcoreFactory.eINSTANCE.createEClass();

        resource.getContents().add(createEClass);
        resource.getContents().add(EcoreFactory.eINSTANCE.createEClass());

        return new SemanticNodeFormatDataKey(createEClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object createNotEqualInstance() throws Exception {
        final Resource resource = new ResourceImpl(URI.createURI("http://bidule"));
        final EPackage createEPackage = EcoreFactory.eINSTANCE.createEPackage();

        resource.getContents().add(EcoreFactory.eINSTANCE.createEPackage());
        resource.getContents().add(createEPackage);

        return new SemanticNodeFormatDataKey(createEPackage);
    }

    protected abstract FormatDataKey createFormatDataKey(EObject eObject);

}
