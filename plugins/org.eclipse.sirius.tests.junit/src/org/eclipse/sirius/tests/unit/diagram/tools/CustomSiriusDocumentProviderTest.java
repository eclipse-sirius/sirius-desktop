/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

package org.eclipse.sirius.tests.unit.diagram.tools;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryService;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.CustomSiriusDocumentProvider;

import junit.framework.TestCase;

/**
 * A JUnit test to check the CustomSiriusDocumentProvider behavior with diagram with no uri fragment.
 * 
 * @author fbarbin
 *
 */
public class CustomSiriusDocumentProviderTest extends TestCase {

    /**
     * Tests that if no URI is associated with the diagram, the CustomSiriusDocumentProvider will not attempt to load
     * the diagram an raise a FileNotFoundException.
     */
    public void testEmptyURIDiagram() {
        ResourceSet set = new ResourceSetImpl();
        final TransactionalEditingDomain transactionalEditingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain(set);
        IDocumentProvider provider = new CustomSiriusDocumentProvider(transactionalEditingDomain);
        URI uri = URI.createURI("");
        try {
            provider.connect(new URIEditorInput(uri));
        } catch (CoreException e) {
            fail("The CustomSiriusDocumentProvider should not attempt to load a diagram without URI: " + e.getMessage());
        }
    }


}
