/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Utility class used to store the input object and its matching output object
 * during a bijective model transformation.
 * 
 * @author sbegaudeau
 * @author mbats
 */
public class TransformationCache {
    /**
     * The map containing the output object for an input one.
     */
    private Map<Object, Object> input2output = new HashMap<>();

    /**
     * The map containing the input object for an output one.
     */
    private Map<Object, Object> output2input = new HashMap<>();

    /**
     * Puts the input object and its matching output object.
     * 
     * @param input
     *            The input
     * @param output
     *            The Eoutput
     */
    public void put(Object input, Object output) {
        this.input2output.put(input, output);
        this.output2input.put(output, input);
    }

    /**
     * Returns the input object matching the given output one.
     * 
     * @param output
     *            The output
     * @return The input object matching the given output one or or an empty
     *         optional if none matched
     */
    public Optional<Object> getInput(Object output) {
        return Optional.ofNullable(this.output2input.get(output));
    }

    /**
     * Returns all the input objects of the cache.
     * 
     * @return All the input objects of the cache.
     */
    public Set<Object> getAllInputs() {
        return Collections.unmodifiableSet(this.input2output.keySet());
    }

    /**
     * Returns the output object matching the given input one.
     * 
     * @param input
     *            The input
     * @return The output object matching the given input one or an empty
     *         optional if none matched
     */
    public Optional<Object> getOutput(Object input) {
        return Optional.ofNullable(this.input2output.get(input));
    }

    /**
     * Returns all the output objects of the cache.
     * 
     * @return All the output objects of the cache.
     */
    public Set<Object> getAllOutputs() {
        return Collections.unmodifiableSet(this.output2input.keySet());
    }
}
