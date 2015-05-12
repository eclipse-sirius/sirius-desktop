/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.docbook.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tests.sample.docbook.AbstractSect;
import org.eclipse.sirius.tests.sample.docbook.Author;
import org.eclipse.sirius.tests.sample.docbook.Book;
import org.eclipse.sirius.tests.sample.docbook.Chapter;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.Emphasis;
import org.eclipse.sirius.tests.sample.docbook.Example;
import org.eclipse.sirius.tests.sample.docbook.Info;
import org.eclipse.sirius.tests.sample.docbook.ItemizedList;
import org.eclipse.sirius.tests.sample.docbook.Link;
import org.eclipse.sirius.tests.sample.docbook.ListItem;
import org.eclipse.sirius.tests.sample.docbook.OrderedList;
import org.eclipse.sirius.tests.sample.docbook.Para;
import org.eclipse.sirius.tests.sample.docbook.Sect1;
import org.eclipse.sirius.tests.sample.docbook.Sect2;
import org.eclipse.sirius.tests.sample.docbook.Sect3;
import org.eclipse.sirius.tests.sample.docbook.SimpleList;
import org.eclipse.sirius.tests.sample.docbook.Title;
import org.eclipse.sirius.tests.sample.docbook.ULink;
import org.eclipse.sirius.tests.sample.docbook.XRef;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage
 * @generated
 */
public class DocbookAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static DocbookPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public DocbookAdapterFactory() {
        if (DocbookAdapterFactory.modelPackage == null) {
            DocbookAdapterFactory.modelPackage = DocbookPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This implementation returns <code>true</code> if
     * the object is either the model's package or is an instance object of the
     * model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == DocbookAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == DocbookAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DocbookSwitch<Adapter> modelSwitch = new DocbookSwitch<Adapter>() {
        @Override
        public Adapter caseBook(Book object) {
            return createBookAdapter();
        }

        @Override
        public Adapter caseInfo(Info object) {
            return createInfoAdapter();
        }

        @Override
        public Adapter caseAuthor(Author object) {
            return createAuthorAdapter();
        }

        @Override
        public Adapter caseChapter(Chapter object) {
            return createChapterAdapter();
        }

        @Override
        public Adapter caseTitle(Title object) {
            return createTitleAdapter();
        }

        @Override
        public Adapter casePara(Para object) {
            return createParaAdapter();
        }

        @Override
        public Adapter caseSimpleList(SimpleList object) {
            return createSimpleListAdapter();
        }

        @Override
        public Adapter caseItemizedList(ItemizedList object) {
            return createItemizedListAdapter();
        }

        @Override
        public Adapter caseOrderedList(OrderedList object) {
            return createOrderedListAdapter();
        }

        @Override
        public Adapter caseSect1(Sect1 object) {
            return createSect1Adapter();
        }

        @Override
        public Adapter caseAbstractSect(AbstractSect object) {
            return createAbstractSectAdapter();
        }

        @Override
        public Adapter caseSect2(Sect2 object) {
            return createSect2Adapter();
        }

        @Override
        public Adapter caseEmphasis(Emphasis object) {
            return createEmphasisAdapter();
        }

        @Override
        public Adapter caseULink(ULink object) {
            return createULinkAdapter();
        }

        @Override
        public Adapter caseLink(Link object) {
            return createLinkAdapter();
        }

        @Override
        public Adapter caseXRef(XRef object) {
            return createXRefAdapter();
        }

        @Override
        public Adapter caseExample(Example object) {
            return createExampleAdapter();
        }

        @Override
        public Adapter caseSect3(Sect3 object) {
            return createSect3Adapter();
        }

        @Override
        public Adapter caseListItem(ListItem object) {
            return createListItemAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Book <em>Book</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Book
     * @generated
     */
    public Adapter createBookAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Info <em>Info</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Info
     * @generated
     */
    public Adapter createInfoAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Author <em>Author</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Author
     * @generated
     */
    public Adapter createAuthorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Chapter <em>Chapter</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Chapter
     * @generated
     */
    public Adapter createChapterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Title <em>Title</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Title
     * @generated
     */
    public Adapter createTitleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Para <em>Para</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Para
     * @generated
     */
    public Adapter createParaAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.SimpleList
     * <em>Simple List</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.SimpleList
     * @generated
     */
    public Adapter createSimpleListAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.ItemizedList
     * <em>Itemized List</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.ItemizedList
     * @generated
     */
    public Adapter createItemizedListAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.OrderedList
     * <em>Ordered List</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.OrderedList
     * @generated
     */
    public Adapter createOrderedListAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect1 <em>Sect1</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Sect1
     * @generated
     */
    public Adapter createSect1Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.AbstractSect
     * <em>Abstract Sect</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.AbstractSect
     * @generated
     */
    public Adapter createAbstractSectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect2 <em>Sect2</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Sect2
     * @generated
     */
    public Adapter createSect2Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Emphasis
     * <em>Emphasis</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Emphasis
     * @generated
     */
    public Adapter createEmphasisAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.ULink <em>ULink</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.ULink
     * @generated
     */
    public Adapter createULinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Link <em>Link</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Link
     * @generated
     */
    public Adapter createLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.XRef <em>XRef</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.XRef
     * @generated
     */
    public Adapter createXRefAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Example <em>Example</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Example
     * @generated
     */
    public Adapter createExampleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect3 <em>Sect3</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.Sect3
     * @generated
     */
    public Adapter createSect3Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.docbook.ListItem
     * <em>List Item</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.docbook.ListItem
     * @generated
     */
    public Adapter createListItemAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This
     * default implementation returns null. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // DocbookAdapterFactory
