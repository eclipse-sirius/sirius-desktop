/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Collection;
import java.util.Optional;
import java.util.OptionalInt;

import org.eclipse.emf.ecore.EObject;

/**
 * Provides coercion from raw values obtained from evaluating an expression. The rules followed for the conversions are
 * implementation-defined.
 * 
 * @author pcdavid
 */
public interface IConverter {
    /**
     * Try to convert a raw value into an {@code int}.
     * 
     * @param rawValue
     *            the raw value.
     * @return an int "equivalent" to the raw value, or an empty optional of the value could not be converted
     *         meaningfully.
     */
    OptionalInt toInt(Object rawValue);

    /**
     * Try to convert a raw value into a {@code Boolean}.
     * 
     * @param rawValue
     *            the raw value.
     * @return a boolean "equivalent" to the raw value, or an empty optional of the value could not be converted
     *         meaningfully.
     */
    Optional<Boolean> toBoolean(Object rawValue);

    /**
     * Try to convert a raw value into an {@code String}.
     * 
     * @param rawValue
     *            the raw value.
     * @return an string "equivalent" to the raw value, or an empty optional of the value could not be converted
     *         meaningfully.
     */
    Optional<String> toString(Object rawValue);

    /**
     * Try to convert a raw value into an {@code EObject}.
     * 
     * @param rawValue
     *            the raw value.
     * @return an EObject "equivalent" to the raw value, or an empty optional of the value could not be converted
     *         meaningfully.
     */
    Optional<EObject> toEObject(Object rawValue);

    /**
     * Try to convert a raw value into a collection of {@link EObject}s.
     * 
     * @param rawValue
     *            the raw value.
     * @return a collection of {@link EObject}s "equivalent" to the raw value, or an empty optional of the value could
     *         not be converted meaningfully.
     */
    Optional<Collection<EObject>> toEObjectCollection(Object rawValue);
}
