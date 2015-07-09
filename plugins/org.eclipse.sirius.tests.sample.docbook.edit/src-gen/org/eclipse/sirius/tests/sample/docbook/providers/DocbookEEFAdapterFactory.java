/**
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.tests.sample.docbook.providers;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory;

/**
 *
 *
 */
public class DocbookEEFAdapterFactory extends DocbookAdapterFactory {

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createBookAdapter()
     *
     */
    @Override
    public Adapter createBookAdapter() {
        return new BookPropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createInfoAdapter()
     *
     */
    @Override
    public Adapter createInfoAdapter() {
        return new InfoPropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createAuthorAdapter()
     *
     */
    @Override
    public Adapter createAuthorAdapter() {
        return new AuthorPropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createChapterAdapter()
     *
     */
    @Override
    public Adapter createChapterAdapter() {
        return new ChapterPropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createTitleAdapter()
     *
     */
    @Override
    public Adapter createTitleAdapter() {
        return new Title_PropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createParaAdapter()
     *
     */
    @Override
    public Adapter createParaAdapter() {
        return new ParaPropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createItemizedListAdapter()
     *
     */
    @Override
    public Adapter createItemizedListAdapter() {
        return new ItemizedListPropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createOrderedListAdapter()
     *
     */
    @Override
    public Adapter createOrderedListAdapter() {
        return new OrderedListPropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createSect1Adapter()
     *
     */
    @Override
    public Adapter createSect1Adapter() {
        return new Sect1PropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createSect2Adapter()
     *
     */
    @Override
    public Adapter createSect2Adapter() {
        return new Sect2PropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createEmphasisAdapter()
     *
     */
    @Override
    public Adapter createEmphasisAdapter() {
        return new EmphasisPropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createULinkAdapter()
     *
     */
    @Override
    public Adapter createULinkAdapter() {
        return new ULinkPropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createXRefAdapter()
     *
     */
    @Override
    public Adapter createXRefAdapter() {
        return new XRefPropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createExampleAdapter()
     *
     */
    @Override
    public Adapter createExampleAdapter() {
        return new ExamplePropertiesEditionProvider();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.util.DocbookAdapterFactory#createSect3Adapter()
     *
     */
    @Override
    public Adapter createSect3Adapter() {
        return new Sect3PropertiesEditionProvider();
    }

}
