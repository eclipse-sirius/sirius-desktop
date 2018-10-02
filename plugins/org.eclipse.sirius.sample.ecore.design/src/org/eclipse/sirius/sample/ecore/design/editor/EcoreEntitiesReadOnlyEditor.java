/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.sample.ecore.design.editor;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.diagram.ui.tools.api.editor.AbstractSpecificDDiagramEditor;

/**
 * Example specific editor.
 * 
 * @author mchauvin
 */
public class EcoreEntitiesReadOnlyEditor extends AbstractSpecificDDiagramEditor {

    public String getDiagramDescriptionName() {
        return "Entities";
    }

    public URI getViewpointURI() {
        return URI.createURI("viewpoint:/org.eclipse.sirius.sample.ecore.design/Design");
    }

    public boolean isSessionStoredInWorkspace() {
        return false;
    }
}
