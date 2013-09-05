/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.modelingproject;

/**
 * Common protocol for all elements provided by the Sirius modeling model.
 * Sirius modeling model elements are exposed to clients as handles to the
 * actual underlying element. The Sirius modeling model may hand out any
 * number of handles for each element. Handles that refer to the same element
 * are guaranteed to be equal, but not necessarily identical.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public interface IModelingElement {

}
