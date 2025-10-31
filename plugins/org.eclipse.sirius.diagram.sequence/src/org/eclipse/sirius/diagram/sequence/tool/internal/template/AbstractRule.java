/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.tool.internal.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.sequence.template.TTransformer;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

/**
 * The base contract a transformation rule has :
 * <ul>
 * <li>it should define a transformation process from a given type to another F (from) to T (to) .</li>
 * <li>A given rule is might create several types in the output model from a given type in the input model (1 to n) but
 * it can't create several elements of the same type (the get method can only return one element of a given type).</li>
 * <li>A given rule might tell which features will be completely overriden, the tooling will then be able to provide a
 * feedback to the user when he is adding things which are going to be deleted.</li>
 * </ul>
 * 
 * @author cbrun
 * 
 * @param <F>
 * @param <T>
 */
public abstract class AbstractRule<F extends TTransformer, T extends EObject> implements Transformer<F, T> {

    private ModelGeneratedMaker marker;

    /**
     * Create a new transformation rule.
     * 
     * @param marker
     *            the class responsible for marking elements as generated ones.
     */
    public AbstractRule(ModelGeneratedMaker marker) {
        this.marker = marker;
    }

    /**
     * Provide an instance of the expected type created by the transformation rule.
     * 
     * @param from
     *            the origi element.
     * @param expected
     *            the type of the output element.
     * @return an output element if one has been created from the given input, null otherwise.
     */
    @SuppressWarnings("unchecked")
    public T get(final F from, EClass expected) {
        for (EObject out : from.getOutputs()) {
            if (out.eClass() == expected) {
                return (T) out;
            }
        }
        return null;
    }

    /**
     * Return an existing element of the expected type or create a new one keeping track of it.
     * 
     * @param from
     *            the origin element.
     * @param outputType
     *            the expected type of the output element.
     * @return an existing element of the expected type or create a new one keeping track of it. Cannot return null;
     */
    @SuppressWarnings("unchecked")
    protected T getOrCreate(final TTransformer from, EClass outputType) {
        T existing = get((F) from, outputType);
        if (existing == null) {
            existing = (T) EcoreUtil.create(outputType);
            marker.markGenerated(existing);
            from.getOutputs().add(existing);
        }
        return existing;
    }

    /**
     * Provide an instance of the expected type created by the transformation rule.
     * 
     * @param from
     *            the origi element.
     * @param expected
     *            the type of the output element.
     * @return an output element if one has been created from the given input, null otherwise.
     */
    public EObject getEObject(TTransformer from, EClass expected) {
        for (EObject out : from.getOutputs()) {
            if (out.eClass() == expected) {
                return out;
            }
        }
        return null;
    }

    /**
     * Collect all the elements generated so far by the given rule from the given set of element and from the expected
     * type.
     * 
     * @param rule
     *            transformation rule to query.
     * @param expected
     *            the type of the elements to retrieve.
     * @param source
     *            the list of input elements.
     * @return a list of output elements having the expected type.
     */
    public static Collection<EObject> collectGeneratedElements(AbstractRule<?, ?> rule, EClass expected, List<? extends EObject> source) {
        Collection<EObject> found = new ArrayList<>();
        for (TTransformer srcElement : Iterables.filter(source, TTransformer.class)) {
            EObject transformed = rule.getEObject(srcElement, expected);
            if (transformed != null) {
                found.add(transformed);
            }
        }
        return found;
    }

    /**
     * Returns a collection that applies {@code function} to each element of {@code fromCollection}. The returned
     * collection is a live view of {@code fromCollection}; changes to one affect the other.
     * 
     * <p>
     * The returned collection's {@code add()} and {@code addAll()} methods throw an
     * {@link UnsupportedOperationException}. All other collection methods are supported, as long as
     * {@code fromCollection} supports them.
     * 
     * <p>
     * The returned collection isn't threadsafe or serializable, even if {@code fromCollection} is.
     * 
     * <p>
     * When a live view is <i>not</i> needed, it may be faster to copy the transformed collection and use the copy.
     * 
     * @param fromCollection
     *            the original collection.
     * @param transformer
     *            transformer of the collection.
     * 
     * 
     * @param <FC>
     *            type of the original collection.
     * 
     * @param <TC>
     *            type of the target collection.
     * @return a collection with transformed element.
     */
    public static <FC, TC> Collection<TC> transform(Collection<FC> fromCollection, final Transformer<? super FC, TC> transformer) {
        return Collections2.transform(fromCollection, new Function<FC, TC>() {
            @Override
            public TC apply(FC input) {
                return transformer.apply(input);
            }
        });
    }

}
