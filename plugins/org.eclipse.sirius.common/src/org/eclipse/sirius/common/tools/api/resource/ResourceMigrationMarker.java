/*******************************************************************************
 * Copyright (c) 2014, 2024 Obeo.
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
package org.eclipse.sirius.common.tools.api.resource;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Utility class used to mark a {@link Resource} as having been migrated. A
 * {@link Resource} having such a marker *might* have been processed by a
 * migration logic during its loading and hence have some changes pendings from
 * a serialization point of view.
 * 
 * This class should be used when an adopter wants the runtime to consider this
 * resource as "potentially changed" even if no logical change has been made on
 * the model.
 * 
 * @author cedric
 */
public class ResourceMigrationMarker extends AdapterImpl {
    /**
     * Add a marker on an existing resource if it is not already there.
     * 
     * @param res
     *            resource to mark.
     */
    public static void addMigrationMarker(Resource res) {
        if (!hasMigrationMarker(res)) {
            res.eAdapters().add(new ResourceMigrationMarker());
        }
    }

    /**
     * Clear the {@link ResourceMigrationMarker} from the resource.
     * 
     * @param res
     *            resource to clear.
     */
    public static void clearMigrationMarker(Resource res) {
        Iterator<Adapter> iterator = res.eAdapters().iterator();
        while (iterator.hasNext()) {
            Adapter adapter = iterator.next();
            if (adapter instanceof ResourceMigrationMarker) {
                iterator.remove();
            }
        }
    }

    /**
     * Return true if the {@link Resource} has a migration marker.
     * 
     * @param res
     *            resource to test.
     * @return true if the {@link Resource} has a migration marker.
     */
    public static boolean hasMigrationMarker(Resource res) {
        return res.eAdapters().stream().anyMatch(ResourceMigrationMarker.class::isInstance);
    }
}
