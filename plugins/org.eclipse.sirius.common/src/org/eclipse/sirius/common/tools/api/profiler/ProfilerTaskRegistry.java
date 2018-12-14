/*******************************************************************************
 * Copyright (c) 2011-2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.tools.api.profiler;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.sirius.common.tools.Messages;

/**
 * An profilerTask registry maintains a mapping between symbolic profilerTask
 * names and the profilerTask.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class ProfilerTaskRegistry {
    private Map<String, ProfilerTask> table;

    /**
     * Creates an empty profilerTask registry.
     */
    public ProfilerTaskRegistry() {
    }

    /**
     * Returns an option with the profiler task associated with the given key in
     * this registry, or <code>null</code> if none.
     * 
     * @param key
     *            the key
     * @return an option of ProfilerTask
     */
    public Optional<ProfilerTask> get(String key) {
        Optional<ProfilerTask> result = Optional.empty();
        // can be null
        if (key != null) {
            ProfilerTask profilerTask = getTable().get(key);
            if (profilerTask != null) {
                result = Optional.of(profilerTask);
            }
        }
        return result;
    }

    /**
     * Adds a ProfilerTask to this registry. This method fails if there is
     * already a ProfilerTask for the given key.
     * 
     * @param key
     *            the key
     * @param value
     *            the ProfilerTask, should not be <code>null</code>
     * @exception IllegalArgumentException
     *                if the key already exists
     */
    public void put(String key, ProfilerTask value) throws IllegalArgumentException {
        if (getTable().get(key) != null) {
            throw new IllegalArgumentException(MessageFormat.format(Messages.ProfilerTaskRegistry_keyConflict, key));            
        } else if (getTable().values().contains(value)) {
            throw new IllegalArgumentException(MessageFormat.format(Messages.ProfilerTaskRegistry_valueConflict, value));
        } else {
            getTable().put(key, value);
        }
    }

    private Map<String, ProfilerTask> getTable() {
        if (table == null) {
            table = new HashMap<String, ProfilerTask>();
        }
        return table;
    }
}
