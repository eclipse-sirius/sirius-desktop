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
package org.eclipse.sirius.tests.sample.docbook.components;

// Start of user code for imports
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.eef.components.SiriusAwarePropertiesEditingComponent;
import org.eclipse.sirius.tests.sample.docbook.Book;
import org.eclipse.sirius.tests.sample.docbook.Chapter;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart;
import org.eclipse.sirius.tests.sample.docbook.parts.DocbookViewsRepository;

// End of user code

/**
 *
 *
 */
public class BookPropertiesEditionComponent extends SiriusAwarePropertiesEditingComponent {

    public static String BASE_PART = "Base"; //$NON-NLS-1$

    /**
     * Settings for chapter ReferencesTable
     */
    protected ReferencesTableSettings chapterSettings;

    /**
     * Default constructor
     *
     */
    public BookPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject book, String editing_mode) {
        super(editingContext, book, editing_mode);
        parts = new String[] { BookPropertiesEditionComponent.BASE_PART };
        repositoryKey = DocbookViewsRepository.class;
        partKey = DocbookViewsRepository.Book.class;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object,
     *      int, org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.resource.ResourceSet)
     *
     */
    @Override
    public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
        setInitializing(true);
        if (editingPart != null && key == partKey) {
            editingPart.setContext(elt, allResource);

            final Book book = (Book) elt;
            final BookPropertiesEditionPart basePart = (BookPropertiesEditionPart) editingPart;
            // init values
            if (isAccessible(DocbookViewsRepository.Book.Properties.chapter)) {
                chapterSettings = new ReferencesTableSettings(book, DocbookPackage.eINSTANCE.getBook_Chapter());
                basePart.initChapter(chapterSettings);
            }
            if (isAccessible(DocbookViewsRepository.Book.Properties.id)) {
                basePart.setId(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, book.getId()));
            }

            if (isAccessible(DocbookViewsRepository.Book.Properties.lang)) {
                basePart.setLang(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, book.getLang()));
            }

            if (isAccessible(DocbookViewsRepository.Book.Properties.version)) {
                basePart.setVersion(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, book.getVersion()));
            }

            // init filters
            if (isAccessible(DocbookViewsRepository.Book.Properties.chapter)) {
                basePart.addFilterToChapter(new ViewerFilter() {
                    /**
                     * {@inheritDoc}
                     *
                     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
                     *      java.lang.Object, java.lang.Object)
                     */
                    @Override
                    public boolean select(Viewer viewer, Object parentElement, Object element) {
                        return (element instanceof String && element.equals("")) || (element instanceof Chapter); //$NON-NLS-1$
                    }

                });
                // Start of user code for additional businessfilters for chapter
                // End of user code
            }

            // init values for referenced views

            // init filters for referenced views

        }
        setInitializing(false);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
     */
    @Override
    public EStructuralFeature associatedFeature(Object editorKey) {
        if (editorKey == DocbookViewsRepository.Book.Properties.chapter) {
            return DocbookPackage.eINSTANCE.getBook_Chapter();
        }
        if (editorKey == DocbookViewsRepository.Book.Properties.id) {
            return DocbookPackage.eINSTANCE.getBook_Id();
        }
        if (editorKey == DocbookViewsRepository.Book.Properties.lang) {
            return DocbookPackage.eINSTANCE.getBook_Lang();
        }
        if (editorKey == DocbookViewsRepository.Book.Properties.version) {
            return DocbookPackage.eINSTANCE.getBook_Version();
        }
        return super.associatedFeature(editorKey);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     *
     */
    @Override
    public void updateSemanticModel(final IPropertiesEditionEvent event) {
        Book book = (Book) semanticObject;
        if (DocbookViewsRepository.Book.Properties.chapter == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.ADD) {
                EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, chapterSettings, editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy policy = provider.getPolicy(context);
                    if (policy instanceof CreateEditingPolicy) {
                        policy.execute();
                    }
                }
            } else if (event.getKind() == PropertiesEditionEvent.EDIT) {
                EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
                    if (editionPolicy != null) {
                        editionPolicy.execute();
                    }
                }
            } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
                chapterSettings.removeFromReference((EObject) event.getNewValue());
            } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
                chapterSettings.move(event.getNewIndex(), (Chapter) event.getNewValue());
            }
        }
        if (DocbookViewsRepository.Book.Properties.id == event.getAffectedEditor()) {
            book.setId((java.lang.String) EEFConverterUtil.createFromString(EcorePackage.Literals.ESTRING, (String) event.getNewValue()));
        }
        if (DocbookViewsRepository.Book.Properties.lang == event.getAffectedEditor()) {
            book.setLang((java.lang.String) EEFConverterUtil.createFromString(EcorePackage.Literals.ESTRING, (String) event.getNewValue()));
        }
        if (DocbookViewsRepository.Book.Properties.version == event.getAffectedEditor()) {
            book.setVersion((java.lang.String) EEFConverterUtil.createFromString(EcorePackage.Literals.ESTRING, (String) event.getNewValue()));
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void updatePart(Notification msg) {
        super.updatePart(msg);
        if (editingPart.isVisible()) {
            BookPropertiesEditionPart basePart = (BookPropertiesEditionPart) editingPart;
            if (DocbookPackage.eINSTANCE.getBook_Chapter().equals(msg.getFeature()) && isAccessible(DocbookViewsRepository.Book.Properties.chapter)) {
                basePart.updateChapter();
            }
            if (DocbookPackage.eINSTANCE.getBook_Id().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null
                    && isAccessible(DocbookViewsRepository.Book.Properties.id)) {
                if (msg.getNewValue() != null) {
                    basePart.setId(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, msg.getNewValue()));
                } else {
                    basePart.setId("");
                }
            }
            if (DocbookPackage.eINSTANCE.getBook_Lang().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null
                    && isAccessible(DocbookViewsRepository.Book.Properties.lang)) {
                if (msg.getNewValue() != null) {
                    basePart.setLang(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, msg.getNewValue()));
                } else {
                    basePart.setLang("");
                }
            }
            if (DocbookPackage.eINSTANCE.getBook_Version().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null
                    && isAccessible(DocbookViewsRepository.Book.Properties.version)) {
                if (msg.getNewValue() != null) {
                    basePart.setVersion(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, msg.getNewValue()));
                } else {
                    basePart.setVersion("");
                }
            }

        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
     */
    @Override
    protected NotificationFilter[] getNotificationFilters() {
        NotificationFilter filter = new EStructuralFeatureNotificationFilter(DocbookPackage.eINSTANCE.getBook_Chapter(), DocbookPackage.eINSTANCE.getBook_Id(),
                DocbookPackage.eINSTANCE.getBook_Lang(), DocbookPackage.eINSTANCE.getBook_Version());
        return new NotificationFilter[] { filter, };
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     *
     */
    @Override
    public Diagnostic validateValue(IPropertiesEditionEvent event) {
        Diagnostic ret = Diagnostic.OK_INSTANCE;
        if (event.getNewValue() != null) {
            try {
                if (DocbookViewsRepository.Book.Properties.id == event.getAffectedEditor()) {
                    Object newValue = event.getNewValue();
                    if (newValue instanceof String) {
                        newValue = EEFConverterUtil.createFromString(DocbookPackage.eINSTANCE.getBook_Id().getEAttributeType(), (String) newValue);
                    }
                    ret = Diagnostician.INSTANCE.validate(DocbookPackage.eINSTANCE.getBook_Id().getEAttributeType(), newValue);
                }
                if (DocbookViewsRepository.Book.Properties.lang == event.getAffectedEditor()) {
                    Object newValue = event.getNewValue();
                    if (newValue instanceof String) {
                        newValue = EEFConverterUtil.createFromString(DocbookPackage.eINSTANCE.getBook_Lang().getEAttributeType(), (String) newValue);
                    }
                    ret = Diagnostician.INSTANCE.validate(DocbookPackage.eINSTANCE.getBook_Lang().getEAttributeType(), newValue);
                }
                if (DocbookViewsRepository.Book.Properties.version == event.getAffectedEditor()) {
                    Object newValue = event.getNewValue();
                    if (newValue instanceof String) {
                        newValue = EEFConverterUtil.createFromString(DocbookPackage.eINSTANCE.getBook_Version().getEAttributeType(), (String) newValue);
                    }
                    ret = Diagnostician.INSTANCE.validate(DocbookPackage.eINSTANCE.getBook_Version().getEAttributeType(), newValue);
                }
            } catch (IllegalArgumentException iae) {
                ret = BasicDiagnostic.toDiagnostic(iae);
            } catch (WrappedException we) {
                ret = BasicDiagnostic.toDiagnostic(we);
            }
        }
        return ret;
    }

}
