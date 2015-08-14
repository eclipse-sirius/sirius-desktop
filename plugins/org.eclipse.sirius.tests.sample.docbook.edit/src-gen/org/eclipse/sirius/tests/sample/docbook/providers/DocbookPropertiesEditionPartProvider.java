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

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionPartProvider;
import org.eclipse.sirius.tests.sample.docbook.parts.DocbookViewsRepository;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.AuthorPropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.BookPropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.ChapterPropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.EmphasisPropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.ExamplePropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.InfoPropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.ItemizedListPropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.OrderedListPropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.ParaPropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.Sect1PropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.Sect2PropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.Sect3PropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.Title_PropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.ULinkPropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.forms.XRefPropertiesEditionPartForm;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.AuthorPropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.BookPropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.ChapterPropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.EmphasisPropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.ExamplePropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.InfoPropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.ItemizedListPropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.OrderedListPropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.ParaPropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.Sect1PropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.Sect2PropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.Sect3PropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.Title_PropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.ULinkPropertiesEditionPartImpl;
import org.eclipse.sirius.tests.sample.docbook.parts.impl.XRefPropertiesEditionPartImpl;

/**
 *
 *
 */
public class DocbookPropertiesEditionPartProvider implements IPropertiesEditionPartProvider {

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPartProvider#provides(java.lang.Object)
     *
     */
    @Override
    public boolean provides(Object key) {
        return key == DocbookViewsRepository.class;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPartProvider#getPropertiesEditionPart(java.lang.Object,
     *      int,
     *      org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent)
     *
     */
    @Override
    public IPropertiesEditionPart getPropertiesEditionPart(Object key, int kind, IPropertiesEditionComponent component) {
        if (key == DocbookViewsRepository.Book.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new BookPropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new BookPropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.Info.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new InfoPropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new InfoPropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.Author.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new AuthorPropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new AuthorPropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.Chapter.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new ChapterPropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new ChapterPropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.Title_.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new Title_PropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new Title_PropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.Para.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new ParaPropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new ParaPropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.ItemizedList.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new ItemizedListPropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new ItemizedListPropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.OrderedList.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new OrderedListPropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new OrderedListPropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.Sect1.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new Sect1PropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new Sect1PropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.Sect2.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new Sect2PropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new Sect2PropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.Emphasis.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new EmphasisPropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new EmphasisPropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.ULink.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new ULinkPropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new ULinkPropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.XRef.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new XRefPropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new XRefPropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.Example.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new ExamplePropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new ExamplePropertiesEditionPartForm(component);
            }
        }
        if (key == DocbookViewsRepository.Sect3.class) {
            if (kind == DocbookViewsRepository.SWT_KIND) {
                return new Sect3PropertiesEditionPartImpl(component);
            }
            if (kind == DocbookViewsRepository.FORM_KIND) {
                return new Sect3PropertiesEditionPartForm(component);
            }
        }
        return null;
    }

}
