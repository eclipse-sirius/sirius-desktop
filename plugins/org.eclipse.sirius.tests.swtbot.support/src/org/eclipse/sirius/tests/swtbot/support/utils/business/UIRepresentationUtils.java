/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.utils.business;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.sirius.tests.swtbot.support.api.business.AbstractUIRepresentation;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Utils for representations.
 * 
 * @author dlecan
 * 
 */
public final class UIRepresentationUtils {

    private UIRepresentationUtils() {
        // Nothing.
    }

    /**
     * Select an instance of representation, <i>ie</i> a diagram or a table.
     * 
     * @param <R>
     *            Type of representation.
     * @param treeItem
     *            Tree item. May be <code>null</code>.
     * @param representationInstanceLabelName
     *            Representation instance label name.
     * @param representationType
     *            Representation type.
     * @return A representation.
     */
    public static <R extends AbstractUIRepresentation<?>> R buildRepresentation(final SWTBotTreeItem treeItem, final String representationInstanceLabelName, final Class<R> representationType) {
        try {
            final Constructor<R> constructor = representationType.getConstructor(new Class<?>[] { SWTBotTreeItem.class, String.class });

            return constructor.newInstance(treeItem, representationInstanceLabelName);
        } catch (final InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (final SecurityException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (final NoSuchMethodException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (final InvocationTargetException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
