/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper.task;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.danalysis.DanglingRefRemovalTrigger;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * A task allowing to delete any DRepresentationElement or any EObject.
 * Typically uesd in Command factories to build delete commands.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class AbstractDeleteDRepresentationElementTask extends AbstractCommandTask {

    /** The object to delete. */
    protected final EObject objectToDelete;

    /** The model accessor to use to perform the deletion. */
    private ModelAccessor accessor;

    private Predicate<EReference> danglingEReferencesToIgnores;

    /**
     * Creates a new Delete task.
     * 
     * @param objectToDelete
     *            the object to delete
     * @param accessor
     *            the {@link ModelAccessor} to use to perform the deletion
     */
    public AbstractDeleteDRepresentationElementTask(EObject objectToDelete, ModelAccessor accessor) {
        this.objectToDelete = objectToDelete;
        this.accessor = accessor;
        this.danglingEReferencesToIgnores = DanglingRefRemovalTrigger.DSEMANTICDECORATOR_REFERENCE_TO_IGNORE_PREDICATE;
    }

    /**
     * Creates a new Delete task.
     * 
     * @param objectToDelete
     *            the object to delete
     * @param accessor
     *            the {@link ModelAccessor} to use to perform the deletion
     * @param danglingEReferencesToIgnores
     *            a {@link Predicate} to tell which {@link EReference} to ignore
     *            in the dangling references deletion
     */
    public AbstractDeleteDRepresentationElementTask(EObject objectToDelete, ModelAccessor accessor, Predicate<EReference> danglingEReferencesToIgnores) {
        this.objectToDelete = objectToDelete;
        this.accessor = accessor;
        this.danglingEReferencesToIgnores = Predicates.or(DanglingRefRemovalTrigger.DSEMANTICDECORATOR_REFERENCE_TO_IGNORE_PREDICATE, danglingEReferencesToIgnores);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
        Session session = null;

        // Step 1: determine the delete type (i.e. decide whether all references
        // to the element should be deleted, all references except
        // DSemanticDecorators, or no reference)

        // Case 1.1: the element to delete is a semantic decorator
        // => we should delete all references to this element
        if (objectToDelete instanceof DSemanticDecorator) {
            session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) objectToDelete).getTarget());
        } else {
            // Here we have a semantic element
            // Case 1.2 : references to this element should be deleted
            // => we should delete all references to this element except
            // from DSemanticDecorators
            session = SessionManager.INSTANCE.getSession(objectToDelete);
        }

        // Step 2 : perform delete
        ECrossReferenceAdapter semanticCrossReferencer = null;
        if (session != null) {
            semanticCrossReferencer = session.getSemanticCrossReferencer();
        }
        accessor.eDelete(objectToDelete, semanticCrossReferencer, danglingEReferencesToIgnores);

    }

    /**
     * The label associated to this command. {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "delete view point element task";
    }

}
