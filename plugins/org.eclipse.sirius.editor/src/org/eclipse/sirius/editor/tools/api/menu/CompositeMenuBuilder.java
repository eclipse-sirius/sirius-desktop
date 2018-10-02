/*******************************************************************************
 * Copyright (c) 2014, 2015 Obeo.
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

    private int priority;

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
        if (menus != null && menus.toArray().length > 0) {
            AbstractMenuBuilder firstMenu = (AbstractMenuBuilder) menus.toArray()[0];
            priority = firstMenu.getPriority();
        }
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public int getPriority() {
        return priority;
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
