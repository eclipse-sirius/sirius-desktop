/*******************************************************************************
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource;

import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapter;

/**
 * This class overrides CrossReferenceAdapter to have it installed only on the
 * AirDResource.
 * 
 * @author smonnier
 * @deprecated replaced by {@link SiriusCrossReferenceAdapter} for the
 *             resolveProxy capability
 */
@Deprecated
public interface AirDCrossReferenceAdapter extends SiriusCrossReferenceAdapter {
}
