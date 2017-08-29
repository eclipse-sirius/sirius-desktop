/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.assist;

import org.eclipse.sirius.common.ui.tools.internal.interpreter.FeatureProposalProvider;

/**
 * A Specific {@link SiriusFeatureContentProposalProvider} for the CreateInstance reference name. It overrides the
 * {@link FeatureProposalProvider} to make possible to restrict the feature proposal according to the type.
 * 
 * @author fbarbin
 *
 */
public class CreateInstanceSiriusFeatureContentProposalProvider extends SiriusFeatureContentProposalProvider {
    @Override
    protected FeatureProposalProvider createFeatureProposalProvider() {
        return new CreateInstanceFeatureProposalProvider();
    }
}
