/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.interpreter;

import org.eclipse.sirius.viewpoint.Messages;

/**
 * All the Acceleo interpreter messages.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public interface IInterpreterMessages {

    /** Error while modifying model. */
    String EVALUATION_ERROR_ON_MODEL_MODIFICATION = Messages.TaskExecutor_errorModifyingModelMsg;

    /** Invalid feature as name. */
    String DEFAULT_NAME_ON_FACTORY_EXCEPTION = Messages.IInterpreterMessages_invalidFeatureErrorMsg;

}
