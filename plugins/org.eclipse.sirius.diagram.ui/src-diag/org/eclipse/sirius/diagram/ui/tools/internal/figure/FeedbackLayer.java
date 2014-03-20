/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import org.eclipse.draw2d.FreeformLayer;

/**
 * GEF does not scale the FEEDBACK_LAYER but we do with the
 * SCALED_FEEDBACK_LAYER.
 * 
 * @author edugueperoux
 */
public class FeedbackLayer extends FreeformLayer {

    /**
     * Default constructor.
     */
    public FeedbackLayer() {
        setEnabled(false);
    }

}
