/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.api.menu;

import java.util.Collection;

import org.eclipse.emf.edit.command.CommandParameter;

/**
 * A specific menu builder providing any action not provided by the given
 * builders. Also known as the "Others" menu.
 * 
 * @author mporhel
 * 
 */
public class CompositeMenuBuilder extends AbstractMenuBuilder {

    private final Collection<AbstractMenuBuilder> menus;

    private String label;

    /**
     * Create a new builder.
     * 
     * @param label
     *            of this composite menu builder
     * @param menus
     *            the builders to consider when checking for dupplicates.
     */
    public CompositeMenuBuilder(final String label, final Collection<AbstractMenuBuilder> menus) {
        this.label = label;
        this.menus = menus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLabel() {
        return label;
    }

    @Override
    protected boolean isMine(CommandParameter object) {
        for (AbstractMenuBuilder builder : menus) {
            if (builder.isMine(object)) {
                return true;
            }
        }
        return false;
    }
}
