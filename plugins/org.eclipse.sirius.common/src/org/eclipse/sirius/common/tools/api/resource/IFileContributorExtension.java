/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.resource;

/**
 * The interface to implement to contribute to the
 * org.eclipse.sirius.common.fileContributor extension point.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public interface IFileContributorExtension {

    /** The extension point ID. */
    String ID = "org.eclipse.sirius.common.fileContributor";

    /** The attribute name. */
    String ATTRIBUTE = "extension";

    /** The attribute element name. */
    String FILE = "file";

    /** The attribute element name. */
    String CATEGORY = "category";

}
