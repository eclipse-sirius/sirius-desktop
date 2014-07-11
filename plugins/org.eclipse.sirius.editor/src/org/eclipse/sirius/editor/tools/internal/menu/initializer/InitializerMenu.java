/*******************************************************************************
 * Copyright (c) 2014 - Joao Martins and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Joao Martins <joaomartins27396@gmail.com> - initial API and implementation 
 *   Maxime Porhel <maxime.porhel@obeo.fr> Obeo - Bug 438074, remarks and correction during review.
 *******************************************************************************/

package org.eclipse.sirius.editor.tools.internal.menu.initializer;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;

/**
 * Specific menu builder to create an Initializer menu.
 * 
 * @author Joao Martins
 * 
 */
public class InitializerMenu extends AbstractMenuBuilder {

    /**
     * Initializer menu label.
     */
    public static final String INITIALIZER_MENU_LABEL = "Initializer";

    @Override
    public String getLabel() {
        return INITIALIZER_MENU_LABEL;
    }

    @Override
    protected boolean isMine(CommandParameter object) {
        return false;
    }

}
