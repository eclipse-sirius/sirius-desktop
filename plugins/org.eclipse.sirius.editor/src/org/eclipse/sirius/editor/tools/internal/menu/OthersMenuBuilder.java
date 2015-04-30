/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.menu;

import java.util.Collection;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;

/**
 * A specific menu builder providing any action not provided by the given
 * builders. Also known as the "Others" menu.
 * 
 * @author cbrun
 * 
 */
public class OthersMenuBuilder extends AbstractMenuBuilder {

    private final Collection<AbstractMenuBuilder> menus;

    /**
     * Create a new builder.
     * 
     * @param menus
     *            the builders to consider when checking for dupplicates.
     */
    public OthersMenuBuilder(final Collection<AbstractMenuBuilder> menus) {
        this.menus = menus;
    }

    @Override
    public String getLabel() {
        return "New";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.OTHERS;
    }

    @Override
    protected boolean isMine(final CommandParameter object) {
        for (final AbstractMenuBuilder builder : menus) {
            if (builder.getMyDescriptors().contains(object)) {
                return false;
            }
        }
        return true;
    }

}
