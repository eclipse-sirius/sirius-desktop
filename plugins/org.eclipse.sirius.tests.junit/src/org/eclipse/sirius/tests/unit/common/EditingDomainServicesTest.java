/*******************************************************************************
 * Copyright (c) 2016, 2020 Obeo.
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
package org.eclipse.sirius.tests.unit.common;

import static org.junit.Assert.assertNull;

import java.util.List;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.ext.emf.edit.EditingDomainServices;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the EditingDomainServices service class.
 * 
 * @author pcdavid
 */
public class EditingDomainServicesTest {
    @Test
    public void can_find_ecore_label_providers() {
        EditingDomainServices svc = new EditingDomainServices();
        EDataType eStringType = EcorePackage.Literals.ESTRING;
        String text = svc.getLabelProviderText(eStringType);
        Assert.assertEquals("EString [java.lang.String]", text);
    }
    
    @Test
    public void avoid_npe_when_no_choiceOfValues() {
        EditingDomainServices svc = new EditingDomainServices();
        EObject self = EcoreFactory.eINSTANCE.createEClass();
        List<?> actual = svc.getPropertyDescriptorChoiceOfValues(self, EcorePackage.Literals.ECLASS__ABSTRACT.getName());
        assertNull(actual);
    }
}
