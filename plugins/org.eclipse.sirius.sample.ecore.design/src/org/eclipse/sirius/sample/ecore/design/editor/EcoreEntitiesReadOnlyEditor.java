/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
