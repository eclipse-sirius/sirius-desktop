/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ecore.extender.business.api.accessor;

/**
 * A Marker interface for descriptors representing metamodels. These instances
 * will be passed around by Sirius from the MetamodelDescriptorProviders to the
 * interpreters and model accessors which have to query for a more specific type
 * they can handle.
 * 
 * @author cbrun
 */
public interface MetamodelDescriptor {

}
